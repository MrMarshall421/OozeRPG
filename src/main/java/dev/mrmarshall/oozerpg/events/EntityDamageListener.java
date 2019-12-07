/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.events;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.File;

public class EntityDamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId());
            FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);
            String playerRace = playerFileCfg.getString("race");

            if (e.getCause() == EntityDamageEvent.DamageCause.POISON) {
                if (playerRace.equals("ELF")) {
                    int antibodySkillBuff = Integer.parseInt(playerFileCfg.getString("skills.utility.poisoned1.level"));

                    if (antibodySkillBuff != 0) {
                        e.setCancelled(true);
                    }
                }
            } else if (e.getCause() == EntityDamageEvent.DamageCause.WITHER) {
                if (playerRace.equals("ELF") || playerRace.equals("DWARF")) {
                    int dreadsdemiseSkillBuff = Integer.parseInt(playerFileCfg.getString("skills.utility.dreadsdemise.level"));

                    if (dreadsdemiseSkillBuff != 0) {
                        e.setCancelled(true);
                    }
                }
            } else if (e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
                if (playerRace.equals("DWARF")) {
                    int firedHandsSkillBuff = Integer.parseInt(playerFileCfg.getString("skills.utility.firedhands.level"));

                    if (firedHandsSkillBuff != 0) {
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
