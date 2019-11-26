/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.events;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

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
                            if (p.getInventory().getItemInMainHand().getType().name().contains("SWORD")) {
                                damage = damage + (e.getDamage() / 100.0) * 10.0;

                                double greed1SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateGreed1(Integer.parseInt(playerFileCfg.getString("skills.combat.greed1.level").substring(0, 1)));
                                Random greedRandom = new Random();
                                int greedRandomResult = 1 + greedRandom.nextInt(100);
                                if (greedRandomResult <= greed1SkillBuff) {
                                    ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5, 2));
                                }
                            }

                            double damage1SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateDamage1(Integer.parseInt(playerFileCfg.getString("skills.combat.damage1.level").substring(0, 1)));
                            damage = damage + (e.getDamage() / 100.0) * damage1SkillBuff;

                            double damage2SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateDamage2(Integer.parseInt(playerFileCfg.getString("skills.combat.damage2.level").substring(0, 1)));
                            damage = damage + (e.getDamage() / 100.0) * damage2SkillBuff;

                            double lifesteal1SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateLifesteal1(Integer.parseInt(playerFileCfg.getString("skills.combat.lifesteal1.level").substring(0, 1)));
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

                            double tank2SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateTank2(Integer.parseInt(playerFileCfg.getString("skills.combat.tank2.level").substring(0, 1)));
                            damage = damage - (e.getDamage() / 100.0) * tank2SkillBuff;

                            double mastertankSkillBuff = OozeRPG.getInstance().getCombatSkills().calculateMastertank(Integer.parseInt(playerFileCfg.getString("skills.combat.mastertank.level").substring(0, 1)));
                            damage = damage - (e.getDamage() / 100.0) * mastertankSkillBuff;

                            Random dodgeRandom = new Random();
                            int dodgeRandomResult = 1 + dodgeRandom.nextInt(100);
                            double training1SkillBuff = OozeRPG.getInstance().getCombatSkills().calculateTraining1(Integer.parseInt(playerFileCfg.getString("skills.combat.training1.level").substring(0, 1)));
                            if (dodgeRandomResult <= training1SkillBuff) {
                                //> Dodge attack
                                e.setCancelled(true);
                                p.sendMessage(PluginMessage.prefix + "§d§oYou dodged the attack from " + e.getDamager().getName());
                                if (e.getDamager() instanceof Player) {
                                    p.sendMessage(PluginMessage.prefix + "§d§o" + p.getName() + " dodged your attack!");
                                }
                            }

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
