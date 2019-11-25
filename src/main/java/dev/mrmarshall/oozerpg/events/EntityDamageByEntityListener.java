/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.events;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
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
            double damage = e.getDamage();

            if (playerRace != null) {
                try {
                    switch (playerRace) {
                        case "HUMAN":
                            double damage1SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateDamage1(Integer.parseInt(playerFileCfg.getString("skills.combat.damage1.level").substring(0, 1)));
                            double lifesteal1SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateLifesteal1(Integer.parseInt(playerFileCfg.getString("skills.combat.lifesteal1.level").substring(0, 1)));

                            if (p.getInventory().getItemInMainHand().getType().name().contains("SWORD")) {
                                damage = damage + (e.getDamage() / 100.0) * 10.0;
                            }

                            damage = damage + (e.getDamage() / 100.0) * damage1SkillBuff;
                            p.setHealth(p.getHealth() + ((e.getDamage() / 100.0) * lifesteal1SkillBuff));
                            ((LivingEntity) e.getEntity()).setHealth((e.getDamage() / 100.0) * lifesteal1SkillBuff);

                            break;
                        case "ELF":
                            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Bow")) {


                                damage = (e.getDamage() / 100.0) * 10.0;
                            }

                            break;
                        case "DWARF":
                            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Axe")) {


                                damage = (e.getDamage() / 100.0) * 10.0;
                            }

                            break;
                        default:
                            break;
                    }
                } catch (NullPointerException ex) {
                }

                e.setDamage(damage);
            }
        }

        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();

            FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

            String playerRace = playerFileCfg.getString("race");
            double damage = e.getDamage();

            if (playerRace != null) {
                try {
                    switch (playerRace) {
                        case "HUMAN":
                            double tank1SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateTank1(Integer.parseInt(playerFileCfg.getString("skills.combat.tank1.level").substring(0, 1)));

                            damage = damage - (e.getDamage() / 100.0) * tank1SkillBuff;

                            break;
                        case "ELF":
                            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Bow")) {


                            }

                            break;
                        case "DWARF":
                            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Axe")) {


                            }

                            break;
                        default:
                            break;
                    }
                } catch (NullPointerException ex) {
                }

                e.setDamage(damage);
            }
        }
    }
}
