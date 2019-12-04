/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.events;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.Location;
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

                                double greed1SkillBuff = OozeRPG.getInstance().getHumanCombatSkills().calculateGreed1(Integer.parseInt(playerFileCfg.getString("skills.combat.greed1.level").substring(0, 1)));
                                Random greedRandom = new Random();
                                int greedRandomResult = 1 + greedRandom.nextInt(100);
                                if (greedRandomResult <= greed1SkillBuff) {
                                    ((LivingEntity) e.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 5, 2));
                                }
                            }

                            double damage1SkillBuff = OozeRPG.getInstance().getHumanCombatSkills().calculateDamage1(Integer.parseInt(playerFileCfg.getString("skills.combat.damage1.level").substring(0, 1)));
                            damage = damage + (e.getDamage() / 100.0) * damage1SkillBuff;

                            double damage2SkillBuff = OozeRPG.getInstance().getHumanCombatSkills().calculateDamage2(Integer.parseInt(playerFileCfg.getString("skills.combat.damage2.level").substring(0, 1)));
                            damage = damage + (e.getDamage() / 100.0) * damage2SkillBuff;

                            double lifesteal1SkillBuff = OozeRPG.getInstance().getHumanCombatSkills().calculateLifesteal1(Integer.parseInt(playerFileCfg.getString("skills.combat.lifesteal1.level").substring(0, 1)));
                            p.setHealth(p.getHealth() + ((e.getDamage() / 100.0) * lifesteal1SkillBuff));
                            ((LivingEntity) e.getEntity()).setHealth((e.getDamage() / 100.0) * lifesteal1SkillBuff);

                            double mystical1SkillBuff = OozeRPG.getInstance().getHumanMovementSkills().calculateMystical1(Integer.parseInt(playerFileCfg.getString("skills.movement.mystical1.level").substring(0, 1)));
                            Random mystical1Random = new Random();
                            int mystical1RandomResult = 1 + mystical1Random.nextInt(100);
                            if (mystical1RandomResult <= mystical1SkillBuff) {
                                double behindEnemyX;
                                double behindEnemyZ;
                                float nang = e.getEntity().getLocation().getYaw() + 90;

                                if (nang < 0) nang += 360;

                                behindEnemyX = Math.cos(Math.toRadians(nang));
                                behindEnemyZ = Math.sin(Math.toRadians(nang));

                                Location newDamagerLoc = new Location(e.getEntity().getLocation().getWorld(), e.getEntity().getLocation().getX() - behindEnemyX,
                                        e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ() - behindEnemyZ, e.getEntity().getLocation().getYaw(), e.getEntity().getLocation().getPitch());

                                p.teleport(newDamagerLoc);
                            } else {
                                double mystical2SkillBuff = OozeRPG.getInstance().getHumanMovementSkills().calculateMystical2(Integer.parseInt(playerFileCfg.getString("skills.movement.mystical2.level").substring(0, 1)));
                                Random mystical2Random = new Random();
                                int mystical2RandomResult = 1 + mystical2Random.nextInt(100);
                                if (mystical2RandomResult <= mystical2SkillBuff) {
                                    double behindEnemyX;
                                    double behindEnemyZ;
                                    float nang = e.getEntity().getLocation().getYaw() + 90;

                                    if (nang < 0) nang += 360;

                                    behindEnemyX = Math.cos(Math.toRadians(nang));
                                    behindEnemyZ = Math.sin(Math.toRadians(nang));

                                    Location newDamagerLoc = new Location(e.getEntity().getLocation().getWorld(), e.getEntity().getLocation().getX() - behindEnemyX,
                                            e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ() - behindEnemyZ, e.getEntity().getLocation().getYaw(), e.getEntity().getLocation().getPitch());

                                    p.teleport(newDamagerLoc);
                                }
                            }

                            break;
                        case "ELF":
                            if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Bow")) {
                                damage = (e.getDamage() / 100.0) * 10.0;
                            }

                            double mystical1ElfSkillBuff = OozeRPG.getInstance().getElfMovementSkills().calculateMystical1(Integer.parseInt(playerFileCfg.getString("skills.movement.mystical1.level").substring(0, 1)));
                            Random mystical1ElfRandom = new Random();
                            int mystical1ElfRandomResult = 1 + mystical1ElfRandom.nextInt(100);
                            if (mystical1ElfRandomResult <= mystical1ElfSkillBuff) {
                                double behindEnemyX;
                                double behindEnemyZ;
                                float nang = e.getEntity().getLocation().getYaw() + 90;

                                if (nang < 0) nang += 360;

                                behindEnemyX = Math.cos(Math.toRadians(nang));
                                behindEnemyZ = Math.sin(Math.toRadians(nang));

                                Location newDamagerLoc = new Location(e.getEntity().getLocation().getWorld(), e.getEntity().getLocation().getX() - behindEnemyX,
                                        e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ() - behindEnemyZ, e.getEntity().getLocation().getYaw(), e.getEntity().getLocation().getPitch());

                                p.teleport(newDamagerLoc);
                            } else {
                                double mystical2SkillBuff = OozeRPG.getInstance().getElfMovementSkills().calculateMystical2(Integer.parseInt(playerFileCfg.getString("skills.movement.mystical2.level").substring(0, 1)));
                                Random mystical2Random = new Random();
                                int mystical2RandomResult = 1 + mystical2Random.nextInt(100);
                                if (mystical2RandomResult <= mystical2SkillBuff) {
                                    double behindEnemyX;
                                    double behindEnemyZ;
                                    float nang = e.getEntity().getLocation().getYaw() + 90;

                                    if (nang < 0) nang += 360;

                                    behindEnemyX = Math.cos(Math.toRadians(nang));
                                    behindEnemyZ = Math.sin(Math.toRadians(nang));

                                    Location newDamagerLoc = new Location(e.getEntity().getLocation().getWorld(), e.getEntity().getLocation().getX() - behindEnemyX,
                                            e.getEntity().getLocation().getY(), e.getEntity().getLocation().getZ() - behindEnemyZ, e.getEntity().getLocation().getYaw(), e.getEntity().getLocation().getPitch());

                                    p.teleport(newDamagerLoc);
                                }
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
                            double tank1SkillBuff = OozeRPG.getInstance().getHumanCombatSkills().calculateTank1(Integer.parseInt(playerFileCfg.getString("skills.combat.tank1.level").substring(0, 1)));
                            damage = damage - (e.getDamage() / 100.0) * tank1SkillBuff;

                            double tank2SkillBuff = OozeRPG.getInstance().getHumanCombatSkills().calculateTank2(Integer.parseInt(playerFileCfg.getString("skills.combat.tank2.level").substring(0, 1)));
                            damage = damage - (e.getDamage() / 100.0) * tank2SkillBuff;

                            double mastertankSkillBuff = OozeRPG.getInstance().getHumanCombatSkills().calculateMastertank(Integer.parseInt(playerFileCfg.getString("skills.combat.mastertank.level").substring(0, 1)));
                            damage = damage - (e.getDamage() / 100.0) * mastertankSkillBuff;

                            Random trainingRandom = new Random();
                            int trainingRandomResult = 1 + trainingRandom.nextInt(100);
                            double training1SkillBuff = OozeRPG.getInstance().getHumanCombatSkills().calculateTraining1(Integer.parseInt(playerFileCfg.getString("skills.combat.training1.level").substring(0, 1)));
                            if (trainingRandomResult <= training1SkillBuff) {
                                //> Dodge attack
                                e.setCancelled(true);
                                p.sendMessage(PluginMessage.prefix + "§d§oYou dodged the attack from " + e.getDamager().getName());
                                if (e.getDamager() instanceof Player) {
                                    p.sendMessage(PluginMessage.prefix + "§d§o" + p.getName() + " dodged your attack!");
                                }
                            }

                            Random dodgeRandom = new Random();
                            int dodgeRandomResult = 1 + dodgeRandom.nextInt(100);
                            double dodge1SkillBuff = OozeRPG.getInstance().getHumanMovementSkills().calculateDodge1(Integer.parseInt(playerFileCfg.getString("skills.movement.dodge1.level")));
                            if (dodgeRandomResult <= dodge1SkillBuff) {
                                //> Dodge attack
                                e.setCancelled(true);
                                p.sendMessage(PluginMessage.prefix + "§d§oYou dodged the attack from " + e.getDamager().getName());
                                if (e.getDamager() instanceof Player) {
                                    p.sendMessage(PluginMessage.prefix + "§d§o" + p.getName() + " dodged your attack!");
                                }
                            }

                            if (p.getHealth() <= 8.0) {
                                if (!OozeRPG.getInstance().getHumanMovementSkills().getMysticalCooldown().contains(p.getUniqueId())) {
                                    Random scamperRandom = new Random();
                                    int scamperRandomResult = 1 + scamperRandom.nextInt(100);
                                    double scamper1SkillBuff = OozeRPG.getInstance().getHumanMovementSkills().calculateScamper1(Integer.parseInt(playerFileCfg.getString("skills.movement.scamper1.level")));
                                    if (scamperRandomResult <= scamper1SkillBuff) {
                                        //> Give speed 4 and start 25s cooldown
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 8, 4));
                                        OozeRPG.getInstance().getSchedulerManager().mysticalCooldown(p.getUniqueId());
                                    }
                                }
                            }

                            break;
                        case "ELF":
                            Random dodge1Random = new Random();
                            int dodge1RandomResult = 1 + dodge1Random.nextInt(100);
                            double dodge1ElfSkillBuff = OozeRPG.getInstance().getElfMovementSkills().calculateDodge1(Integer.parseInt(playerFileCfg.getString("skills.movement.dodge1.level")));
                            if (dodge1RandomResult <= dodge1ElfSkillBuff) {
                                //> Dodge attack
                                e.setCancelled(true);
                                p.sendMessage(PluginMessage.prefix + "§d§oYou dodged the attack from " + e.getDamager().getName());
                                if (e.getDamager() instanceof Player) {
                                    p.sendMessage(PluginMessage.prefix + "§d§o" + p.getName() + " dodged your attack!");
                                }
                            }

                            if (p.getHealth() <= 8.0) {
                                if (!OozeRPG.getInstance().getElfMovementSkills().getMysticalCooldown().contains(p.getUniqueId())) {
                                    Random scamperRandom = new Random();
                                    int scamperRandomResult = 1 + scamperRandom.nextInt(100);
                                    double scamper1SkillBuff = OozeRPG.getInstance().getElfMovementSkills().calculateScamper1(Integer.parseInt(playerFileCfg.getString("skills.movement.scamper1.level")));
                                    if (scamperRandomResult <= scamper1SkillBuff) {
                                        //> Give speed 5 and start 25s cooldown
                                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 8, 5));
                                        OozeRPG.getInstance().getSchedulerManager().mysticalCooldown(p.getUniqueId());
                                    }
                                }
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
