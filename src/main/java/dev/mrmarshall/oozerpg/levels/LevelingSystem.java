/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.levels;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.UUID;

public class LevelingSystem implements Listener {

    @EventHandler
    public void onEntityKill(EntityDeathEvent e) {
        LivingEntity killedEntity = e.getEntity();

        if (!(killedEntity instanceof Player)) {
            if (killedEntity.getKiller() instanceof Player) {
                Player p = killedEntity.getKiller();

                FileConfiguration levelsCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getLevelingData().getLevelsFile());
                FileConfiguration mobsCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getLevelingData().getMobsFile());
                FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

                String mobCategory = OozeRPG.getInstance().getMobManager().getMobCategory(killedEntity);
                int exp = mobsCfg.getInt(mobCategory + "." + killedEntity.getName());

                int nextPlayerLevel = playerFileCfg.getInt("level") + 1;
                int expToNextLevel = levelsCfg.getInt("" + nextPlayerLevel + "") - playerFileCfg.getInt("experience");

                if (nextPlayerLevel != 51) {
                    if (exp >= expToNextLevel) {
                        //> Player levels up
                        OozeRPG.getInstance().getPlayerDataHandler().setPlayerExperience(p.getUniqueId(), playerFileCfg.getInt("experience") + exp);
                        levelUp(p.getUniqueId());
                        p.sendMessage(PluginMessage.prefix + "§e§lLEVEL UP! §aYou are now Level §e" + nextPlayerLevel + "§a!");
                        p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1.0F, 1.0F);
                    } else {
                        //> Player receives exp
                        OozeRPG.getInstance().getPlayerDataHandler().setPlayerExperience(p.getUniqueId(), playerFileCfg.getInt("experience") + exp);
                    }
                }
            }
        }
    }

    private void levelUp(UUID uuid) {
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(uuid));
        FileConfiguration levelsCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getLevelingData().getLevelsFile());
        Player p = Bukkit.getPlayer(uuid);

        OozeRPG.getInstance().getPlayerDataHandler().setPlayerSkillpoints(uuid, playerFileCfg.getInt("skillpoints") + 1);
        OozeRPG.getInstance().getPlayerDataHandler().setPlayerLevel(uuid, playerFileCfg.getInt("level") + 1);
        OozeRPG.getInstance().getPlayerDataHandler().setPlayerExperience(uuid, playerFileCfg.getInt("experience") - levelsCfg.getInt(playerFileCfg.getInt("level") + ""));

        //> Give player armor permissions
        switch (playerFileCfg.getInt("level")) {
            case 10:
                OozeRPG.getInstance().getPermissionManager().playerAdd(p, "weaponkit.tier2");
                break;
            case 20:
                OozeRPG.getInstance().getPermissionManager().playerAdd(p, "weaponkit.tier3");
                break;
            case 30:
                OozeRPG.getInstance().getPermissionManager().playerAdd(p, "weaponkit.tier4");
                break;
            case 40:
                OozeRPG.getInstance().getPermissionManager().playerAdd(p, "weaponkit.tier5");
                break;
            default:
                break;
        }
    }
}
