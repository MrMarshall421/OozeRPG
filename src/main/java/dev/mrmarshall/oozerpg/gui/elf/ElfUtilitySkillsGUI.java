/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.gui.elf;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.gui.SkillsGUI;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ElfUtilitySkillsGUI implements Listener {

    public void open(Player p) {
        //> GUI
        Inventory gui = Bukkit.createInventory(null, 36, "§6Utility Skills");
        refreshInventory(gui, p);

        //> Open GUI
        p.openInventory(gui);
        p.playSound(p.getLocation(), Sound.BLOCK_FENCE_GATE_OPEN, 1.0F, 1.0F);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));
        SkillsGUI skillsGUI = new SkillsGUI();
        int skillpoints = playerFileCfg.getInt("skillpoints");

        if (e.getView().getTitle().equals("§6Utility Skills")) {
            e.setCancelled(true);

            try {
                if (e.getSlot() == 35) {
                    p.closeInventory();
                    skillsGUI.open(p);
                } else if (!e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lYOU")) {
                    //> Select skill
                    if (skillpoints > 0) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§4Retaliate §l1")) {
                            //> Retaliate 1
                            buySkillUpgrade("skills.utility.retaliate1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Sleight §l1")) {
                            //> Sleight 1
                            buySkillUpgrade("skills.utility.sleight1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§1Anger §l1")) {
                            //> Anger 1
                            buySkillUpgrade("skills.utility.anger1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Hatred §l1")) {
                            //> Hatred 1
                            buySkillUpgrade("skills.utility.hatred1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Sleight §l2")) {
                            //> Sleight 2
                            buySkillUpgrade("skills.utility.sleight2.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§ePoisoned §l1")) {
                            //> Poisoned 1
                            buySkillUpgrade("skills.utility.poisoned1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAnti-Body")) {
                            //> Anti-Body
                            buySkillUpgrade("skills.utility.antibody.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8Dreadshade §l1")) {
                            //> Dreadshade 1
                            buySkillUpgrade("skills.utility.dreadshade1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bDreadsdemise")) {
                            //> Dreadsdemise
                            buySkillUpgrade("skills.utility.dreadsdemise.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§dHatred §l2")) {
                            //> Hatred 2
                            buySkillUpgrade("skills.utility.hatred2.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Sleight §l3")) {
                            //> Sleight 3
                            buySkillUpgrade("skills.utility.sleight3.level", e.getCurrentItem(), e.getSlot(), p);
                        }
                    } else {
                        if (e.getCurrentItem().getType() != Material.LIME_STAINED_GLASS_PANE) {
                            p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cYou have no skillpoints to spend!");
                        }
                    }
                }
            } catch (NullPointerException ex) {
            }
        }
    }

    private void buySkillUpgrade(String skill, ItemStack item, int slot, Player p) {
        File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId());
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);
        int currentSkillLevel = Integer.parseInt(item.getItemMeta().getLore().get(0).replaceAll("§9§o", "").substring(0, 1));
        int maxSkillLevel = Integer.parseInt(item.getItemMeta().getLore().get(0).replaceAll("§9§o", "").substring(2, 3));
        int skillpoints = playerFileCfg.getInt("skillpoints");

        if (currentSkillLevel < maxSkillLevel) {
            //> Check if previous skill is maxed
            String previousItem;
            int previousItemSlot;
            int previousItemLevel = 0;
            int previousItemMaxLevel = 0;
            if (p.getOpenInventory().getTopInventory().getItem(slot - 1).getType() != Material.LIME_STAINED_GLASS_PANE) {
                previousItemSlot = slot - 1;
            } else {
                previousItemSlot = slot - 2;
            }

            if (p.getOpenInventory().getTopInventory().getItem(previousItemSlot).getType() != Material.LIME_STAINED_GLASS_PANE) {
                previousItem = ChatColor.stripColor(p.getOpenInventory().getTopInventory().getItem(previousItemSlot).getItemMeta().getDisplayName()).replaceAll(" ", "").toLowerCase();
                previousItemLevel = Integer.parseInt(playerFileCfg.getString("skills.utility." + previousItem + ".level").substring(0, 1));
                previousItemMaxLevel = Integer.parseInt(playerFileCfg.getString("skills.utility." + previousItem + ".level").substring(2, 3));
            }

            if (previousItemLevel == previousItemMaxLevel) {
                //> Upgrade Skill
                playerFileCfg.set(skill, (currentSkillLevel + 1) + "/" + maxSkillLevel);
                OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
                OozeRPG.getInstance().getPlayerDataHandler().setPlayerSkillpoints(p.getUniqueId(), skillpoints - 1);

                refreshInventory(p.getOpenInventory().getTopInventory(), p);
                OozeRPG.getInstance().getElfUtilitySkills().updateSleightTimer(p.getUniqueId());

                p.sendMessage(PluginMessage.prefix + "§aSkill Successfully upgraded!");
            } else {
                p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cYou need to upgrade the previous skill before you can purchase this one!");
            }
        } else {
            p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cThis skill is already Max-level!");
        }
    }

    private void refreshInventory(Inventory gui, Player p) {
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

        //> Items
        ItemStack placeholder = OozeRPG.getInstance().getItemStackCreator().create(Material.LIME_STAINED_GLASS_PANE, 1, " ", true);

        String retaliate1Level = playerFileCfg.getString("skills.utility.retaliate1.level");
        double retaliate1Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculateRetaliate1(Integer.parseInt(retaliate1Level.substring(0, 1)));
        ItemStack retaliate1 = OozeRPG.getInstance().getItemStackCreator().create(Material.ROSE_RED, 1, "§4Retaliate §l1", false);
        List<String> retaliate1Lore = new ArrayList<>();
        retaliate1Lore.add("§9§o" + retaliate1Level + " Levels unlocked");
        retaliate1Lore.add("§9§oGains " + retaliate1Percentage + "% Thorns");
        OozeRPG.getInstance().getItemStackCreator().addLore(retaliate1, retaliate1Lore);

        String sleight1Level = playerFileCfg.getString("skills.utility.sleight1.level");
        double sleight1Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculateSleight(1);
        ItemStack sleight1 = OozeRPG.getInstance().getItemStackCreator().create(Material.CACTUS_GREEN, 1, "§2Sleight §l1", false);
        List<String> sleight1Lore = new ArrayList<>();
        sleight1Lore.add("§9§o" + sleight1Level + " Levels unlocked");
        sleight1Lore.add("§9§oGains " + sleight1Percentage / 2 + " Hearts passive regen");
        OozeRPG.getInstance().getItemStackCreator().addLore(sleight1, sleight1Lore);

        String anger1Level = playerFileCfg.getString("skills.utility.anger1.level");
        double anger1Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculateAnger1(Integer.parseInt(anger1Level.substring(0, 1)));
        ItemStack anger1 = OozeRPG.getInstance().getItemStackCreator().create(Material.LAPIS_LAZULI, 1, "§1Anger §l1", false);
        List<String> anger1Lore = new ArrayList<>();
        anger1Lore.add("§9§o" + anger1Level + " Levels unlocked");
        anger1Lore.add("§9§oGains " + anger1Percentage + "% Chance to catch attacker on fire");
        OozeRPG.getInstance().getItemStackCreator().addLore(anger1, anger1Lore);

        String hatred1Level = playerFileCfg.getString("skills.utility.hatred1.level");
        double hatred1Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculateHatred1(Integer.parseInt(hatred1Level.substring(0, 1)));
        ItemStack hatred1 = OozeRPG.getInstance().getItemStackCreator().create(Material.MAGENTA_DYE, 1, "§5Hatred §l1", false);
        List<String> hatred1Lore = new ArrayList<>();
        hatred1Lore.add("§9§o" + hatred1Level + " Levels unlocked");
        hatred1Lore.add("§9§oGains " + hatred1Percentage + "% Chance to knockback the enemy");
        OozeRPG.getInstance().getItemStackCreator().addLore(hatred1, hatred1Lore);

        String sleight2Level = playerFileCfg.getString("skills.utility.sleight2.level");
        double sleight2Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculateSleight(2);
        ItemStack sleight2 = OozeRPG.getInstance().getItemStackCreator().create(Material.CYAN_DYE, 1, "§3Sleight §l2", false);
        List<String> sleight2Lore = new ArrayList<>();
        sleight2Lore.add("§9§o" + sleight2Level + " Levels unlocked");
        sleight2Lore.add("§9§oGains " + sleight2Percentage / 2 + " Hearts passive regen");
        OozeRPG.getInstance().getItemStackCreator().addLore(sleight2, sleight2Lore);

        String poisoned1Level = playerFileCfg.getString("skills.utility.poisoned1.level");
        double poisoned1Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculatePoisoned1(Integer.parseInt(poisoned1Level.substring(0, 1)));
        ItemStack poisoned1 = OozeRPG.getInstance().getItemStackCreator().create(Material.DANDELION_YELLOW, 1, "§ePoisoned §l1", false);
        List<String> poisoned1Lore = new ArrayList<>();
        poisoned1Lore.add("§9§o" + poisoned1Level + " Levels unlocked");
        poisoned1Lore.add("§9§oGains " + poisoned1Percentage + "% Chance to poison the enemy");
        OozeRPG.getInstance().getItemStackCreator().addLore(poisoned1, poisoned1Lore);

        String antibodyLevel = playerFileCfg.getString("skills.utility.antibody.level");
        ItemStack antibody = OozeRPG.getInstance().getItemStackCreator().create(Material.LIME_DYE, 1, "§aAnti-Body", false);
        List<String> antibodyLore = new ArrayList<>();
        antibodyLore.add("§9§o" + antibodyLevel + " Levels unlocked");
        antibodyLore.add("§9§oGains poison immunity");
        OozeRPG.getInstance().getItemStackCreator().addLore(antibody, antibodyLore);

        String dreadshade1Level = playerFileCfg.getString("skills.utility.dreadshade1.level");
        double dreadshade1Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculateDreadshade1(Integer.parseInt(dreadshade1Level.substring(0, 1)));
        ItemStack dreadshade1 = OozeRPG.getInstance().getItemStackCreator().create(Material.GRAY_DYE, 1, "§8Dread-Shade §l1", false);
        List<String> dreadshade1Lore = new ArrayList<>();
        dreadshade1Lore.add("§9§o" + dreadshade1Level + " Levels unlocked");
        dreadshade1Lore.add("§9§oGains " + dreadshade1Percentage + "% Chance to wither the enemy");
        OozeRPG.getInstance().getItemStackCreator().addLore(dreadshade1, dreadshade1Lore);

        String dreadsdemiseLevel = playerFileCfg.getString("skills.utility.dreadsdemise.level");
        ItemStack dreadsdemise = OozeRPG.getInstance().getItemStackCreator().create(Material.LIGHT_BLUE_DYE, 1, "§bDreadsdemise", false);
        List<String> dreadsdemiseLore = new ArrayList<>();
        dreadsdemiseLore.add("§9§o" + dreadsdemiseLevel + " Levels unlocked");
        dreadsdemiseLore.add("§9§oGains wither immunity");
        OozeRPG.getInstance().getItemStackCreator().addLore(dreadsdemise, dreadsdemiseLore);

        String hatred2Level = playerFileCfg.getString("skills.utility.hatred2.level");
        double hatred2Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculateHatred2(Integer.parseInt(hatred2Level.substring(0, 1)));
        ItemStack hatred2 = OozeRPG.getInstance().getItemStackCreator().create(Material.PINK_DYE, 1, "§dHatred §l2", false);
        List<String> hatred2Lore = new ArrayList<>();
        hatred2Lore.add("§9§o" + hatred2Level + " Levels unlocked");
        hatred2Lore.add("§9§oGains " + hatred2Percentage + "% Chance to knockback the enemy");
        OozeRPG.getInstance().getItemStackCreator().addLore(hatred2, hatred2Lore);

        String sleight3Level = playerFileCfg.getString("skills.utility.sleight3.level");
        double sleight3Percentage = OozeRPG.getInstance().getElfUtilitySkills().calculateSleight(3);
        ItemStack sleight3 = OozeRPG.getInstance().getItemStackCreator().create(Material.LIGHT_GRAY_DYE, 1, "§7Sleight §l3", false);
        List<String> sleight3Lore = new ArrayList<>();
        sleight3Lore.add("§9§o" + sleight3Level + " Levels unlocked");
        sleight3Lore.add("§9§oGains " + sleight3Percentage / 2 + " Hearts passive regen");
        OozeRPG.getInstance().getItemStackCreator().addLore(sleight3, sleight3Lore);

        ItemStack playerInfo = OozeRPG.getInstance().getItemStackCreator().create(Material.PLAYER_HEAD, 1, "§e§lYOU", false);
        SkullMeta playerInfoMeta = (SkullMeta) playerInfo.getItemMeta();
        playerInfoMeta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
        playerInfo.setItemMeta(playerInfoMeta);
        List<String> playerInfoLore = new ArrayList<>();
        playerInfoLore.add("§9§oCurrent race: §b§lELF");
        playerInfoLore.add("§9§oGains +10% Bow Damage");
        playerInfoLore.add(" ");
        playerInfoLore.add("§9§oSkillpoints left: §l" + playerFileCfg.getInt("skillpoints"));
        OozeRPG.getInstance().getItemStackCreator().addLore(playerInfo, playerInfoLore);

        ItemStack back = OozeRPG.getInstance().getItemStackCreator().create(Material.NETHER_STAR, 1, "§f§lBACK", false);

        //> Set Items
        for (int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, placeholder);
        }

        gui.setItem(10, retaliate1);
        gui.setItem(11, sleight1);
        gui.setItem(12, anger1);
        gui.setItem(13, hatred1);
        gui.setItem(14, sleight2);
        gui.setItem(15, poisoned1);
        gui.setItem(16, antibody);
        gui.setItem(20, dreadshade1);
        gui.setItem(21, dreadsdemise);
        gui.setItem(22, hatred2);
        gui.setItem(23, sleight3);
        gui.setItem(27, playerInfo);
        gui.setItem(35, back);
    }
}