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
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

            String playerRace = playerFileCfg.getString("race");
            double damage = (e.getDamage() / 100) * 10;

            switch (playerRace) {
                case "HUMAN":
                    if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Sword")) {
                        e.setDamage(e.getDamage() + damage);
                    }

                    break;
                case "ELF":
                    if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Bow")) {
                        e.setDamage(e.getDamage() + damage);
                    }

                    break;
                case "DWARF":
                    if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Axe")) {
                        e.setDamage(e.getDamage() + damage);
                    }

                    break;
                default:
                    break;
            }
        }
    }
}
