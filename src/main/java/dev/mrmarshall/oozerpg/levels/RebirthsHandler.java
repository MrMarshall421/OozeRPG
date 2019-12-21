/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.levels;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;

public class RebirthsHandler {

    public void rebirth(Player p, String race) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + p.getUniqueId().toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);
        int playerLevel = playerFileCfg.getInt("level");
        int rebirthLevel = playerFileCfg.getInt("rebirth");

        if (playerLevel >= 50) {
            if (rebirthLevel <= 2) {
                OozeRPG.getInstance().getPlayerDataHandler().deletePlayerFile(p.getUniqueId());
                OozeRPG.getInstance().getPlayerDataHandler().createPlayerFile(p.getUniqueId());
                OozeRPG.getInstance().getRaceManager().selectRace(p.getUniqueId(), race);
                OozeRPG.getInstance().getPlayerDataHandler().setPlayerRebirth(p.getUniqueId(), rebirthLevel + 1);

                loadRebirthEffects(p);
                p.sendMessage(PluginMessage.prefix + "§aSuccessfully rebirthed to §f§lLEVEL " + (rebirthLevel + 1) + "§a!");
            } else {
                p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cYou have reached the maximum Rebirth Level!");
            }
        } else {
            p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cYou have to be level 50 to use rebirth!");
        }
    }

    public void loadRebirthEffects(Player p) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + p.getUniqueId().toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);
        String race = playerFileCfg.getString("race");
        int rebirthLevel = playerFileCfg.getInt("rebirth");

        switch (race) {
            case "HUMAN":
                switch (rebirthLevel) {
                    case 1:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 1));
                        break;
                    case 2:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 2));
                        break;
                    case 3:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 3));
                        break;
                    default:
                        break;
                }

                break;
            case "ELF":
                switch (rebirthLevel) {
                    case 1:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999999, 1));
                        break;
                    case 2:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999999, 2));
                        break;
                    case 3:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999999, 3));
                        break;
                    default:
                        break;
                }

                break;
            case "DWARF":
                switch (rebirthLevel) {
                    case 1:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 1));
                        break;
                    case 2:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 1));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 999999999, 1));
                        break;
                    case 3:
                        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 1));
                        p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 999999999, 2));
                        break;
                    default:
                        break;
                }

                break;
        }
    }
}
