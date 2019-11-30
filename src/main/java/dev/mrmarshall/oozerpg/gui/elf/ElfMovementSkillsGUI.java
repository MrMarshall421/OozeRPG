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

public class ElfMovementSkillsGUI implements Listener {

    public void open(Player p) {
        //> GUI
        Inventory gui = Bukkit.createInventory(null, 36, "§3Movement Skills");
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

        if (e.getView().getTitle().equals("§3Movement Skills")) {
            e.setCancelled(true);

            try {
                if (e.getSlot() == 35) {
                    p.closeInventory();
                    skillsGUI.open(p);
                } else if (!e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lYOU")) {
                    //> Select skill
                    if (skillpoints > 0) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§4Speed §l1")) {
                            //> Speed 1
                            buySkillUpgrade("skills.movement.speed1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Dodge §l1")) {
                            //> Dodge 1
                            buySkillUpgrade("skills.movement.dodge1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§1Mystical §l1")) {
                            //> Mystical 1
                            buySkillUpgrade("skills.movement.mystical1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Bowspeed")) {
                            //> Bowspeed
                            buySkillUpgrade("skills.movement.bowspeed.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Scamper §l1")) {
                            //> Scamper 1
                            buySkillUpgrade("skills.movement.scamper1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Mystical §l2")) {
                            //> Mystical 2
                            buySkillUpgrade("skills.movement.mystical2.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eSpeed §l2")) {
                            //> Speed 2
                            buySkillUpgrade("skills.movement.speed2.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8Dodge §l2")) {
                            //> Dodge 2
                            buySkillUpgrade("skills.movement.dodge2.level", e.getCurrentItem(), e.getSlot(), p);
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
                previousItemLevel = Integer.parseInt(playerFileCfg.getString("skills.movement." + previousItem + ".level").substring(0, 1));
                previousItemMaxLevel = Integer.parseInt(playerFileCfg.getString("skills.movement." + previousItem + ".level").substring(2, 3));
            }

            if (previousItemLevel == previousItemMaxLevel) {
                //> Upgrade Skill
                playerFileCfg.set(skill, (currentSkillLevel + 1) + "/" + maxSkillLevel);
                OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
                OozeRPG.getInstance().getPlayerDataHandler().setPlayerSkillpoints(p.getUniqueId(), skillpoints - 1);

                refreshInventory(p.getOpenInventory().getTopInventory(), p);
                OozeRPG.getInstance().getElfMovementSkills().setPlayerWalkingSpeed(p);

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

        String speed1Level = playerFileCfg.getString("skills.movement.speed1.level");
        double speed1Percentage = OozeRPG.getInstance().getElfMovementSkills().calculateSpeed1(Integer.parseInt(speed1Level.substring(0, 1)));
        ItemStack speed1 = OozeRPG.getInstance().getItemStackCreator().create(Material.ROSE_RED, 1, "§4Speed §l1", false);
        List<String> speed1Lore = new ArrayList<>();
        speed1Lore.add("§9§o" + speed1Level + " Levels unlocked");
        speed1Lore.add("§9§oGains " + speed1Percentage + "% Increased Movement speed");
        OozeRPG.getInstance().getItemStackCreator().addLore(speed1, speed1Lore);

        String dodge1Level = playerFileCfg.getString("skills.movement.dodge1.level");
        double dodge1Percentage = OozeRPG.getInstance().getElfMovementSkills().calculateDodge1(Integer.parseInt(dodge1Level.substring(0, 1)));
        ItemStack dodge1 = OozeRPG.getInstance().getItemStackCreator().create(Material.CACTUS_GREEN, 1, "§2Dodge §l1", false);
        List<String> dodge1Lore = new ArrayList<>();
        dodge1Lore.add("§9§o" + dodge1Level + " Levels unlocked");
        dodge1Lore.add("§9§oGains " + dodge1Percentage + "% Chance to dodge an attack");
        OozeRPG.getInstance().getItemStackCreator().addLore(dodge1, dodge1Lore);

        String mystical1Level = playerFileCfg.getString("skills.movement.mystical1.level");
        double mystical1Percentage = OozeRPG.getInstance().getElfMovementSkills().calculateMystical1(Integer.parseInt(mystical1Level.substring(0, 1)));
        ItemStack mystical1 = OozeRPG.getInstance().getItemStackCreator().create(Material.LAPIS_LAZULI, 1, "§1Mystical §l1", false);
        List<String> mystical1Lore = new ArrayList<>();
        mystical1Lore.add("§9§o" + mystical1Level + " Levels unlocked");
        mystical1Lore.add("§9§oGains " + mystical1Percentage + "% Chance to TP behind victim");
        OozeRPG.getInstance().getItemStackCreator().addLore(mystical1, mystical1Lore);

        String bowspeedLevel = playerFileCfg.getString("skills.movement.bowspeed.level");
        double bowspeedPercentage = OozeRPG.getInstance().getElfMovementSkills().calculateBowspeed(Integer.parseInt(bowspeedLevel.substring(0, 1)));
        ItemStack bowspeed = OozeRPG.getInstance().getItemStackCreator().create(Material.MAGENTA_DYE, 1, "§5Bowspeed", false);
        List<String> bowspeedLore = new ArrayList<>();
        bowspeedLore.add("§9§o" + bowspeedLevel + " Levels unlocked");
        bowspeedLore.add("§9§oGains " + bowspeedPercentage + "% Increased Movement speed while holding a bow");
        OozeRPG.getInstance().getItemStackCreator().addLore(bowspeed, bowspeedLore);

        String scamper1Level = playerFileCfg.getString("skills.movement.scamper1.level");
        double scamper1Percentage = OozeRPG.getInstance().getElfMovementSkills().calculateScamper1(Integer.parseInt(scamper1Level.substring(0, 1)));
        ItemStack scamper1 = OozeRPG.getInstance().getItemStackCreator().create(Material.CYAN_DYE, 1, "§3Scamper §l1", false);
        List<String> scamper1Lore = new ArrayList<>();
        scamper1Lore.add("§9§o" + scamper1Level + " Levels unlocked");
        scamper1Lore.add("§9§oGains " + scamper1Percentage + "% Chance to get Speed 5");
        OozeRPG.getInstance().getItemStackCreator().addLore(scamper1, scamper1Lore);

        String mystical2Level = playerFileCfg.getString("skills.movement.mystical2.level");
        double mystical2Percentage = OozeRPG.getInstance().getElfMovementSkills().calculateMystical2(Integer.parseInt(mystical2Level.substring(0, 1)));
        ItemStack mystical2 = OozeRPG.getInstance().getItemStackCreator().create(Material.DANDELION_YELLOW, 1, "§eMystical §l2", false);
        List<String> mystical2Lore = new ArrayList<>();
        mystical2Lore.add("§9§o" + mystical2Level + " Levels unlocked");
        mystical2Lore.add("§9§oGains " + mystical2Percentage + "% Chance to TP behind victim");
        OozeRPG.getInstance().getItemStackCreator().addLore(mystical2, mystical2Lore);

        String speed2Level = playerFileCfg.getString("skills.movement.speed2.level");
        double speed2Percentage = OozeRPG.getInstance().getElfMovementSkills().calculateSpeed2(Integer.parseInt(speed2Level.substring(0, 1)));
        ItemStack speed2 = OozeRPG.getInstance().getItemStackCreator().create(Material.LIME_DYE, 1, "§aSpeed §l2", false);
        List<String> speed2Lore = new ArrayList<>();
        speed2Lore.add("§9§o" + speed2Level + " Levels unlocked");
        speed2Lore.add("§9§oGains " + speed2Percentage + "% Increased Movement speed");
        OozeRPG.getInstance().getItemStackCreator().addLore(speed2, speed2Lore);

        String dodge2Level = playerFileCfg.getString("skills.movement.dodge2.level");
        double dodge2Percentage = OozeRPG.getInstance().getElfMovementSkills().calculateDodge2(Integer.parseInt(dodge2Level.substring(0, 1)));
        ItemStack dodge2 = OozeRPG.getInstance().getItemStackCreator().create(Material.GRAY_DYE, 1, "§8Dodge §l2", false);
        List<String> dodge2Lore = new ArrayList<>();
        dodge2Lore.add("§9§o" + dodge2Level + " Levels unlocked");
        dodge2Lore.add("§9§oGains " + dodge2Percentage + "% Chance to dodge an attack");
        OozeRPG.getInstance().getItemStackCreator().addLore(dodge2, dodge2Lore);

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

        gui.setItem(10, speed1);
        gui.setItem(11, dodge1);
        gui.setItem(12, mystical1);
        gui.setItem(13, bowspeed);
        gui.setItem(14, scamper1);
        gui.setItem(15, mystical2);
        gui.setItem(16, speed2);
        gui.setItem(20, dodge2);
        gui.setItem(27, playerInfo);
        gui.setItem(35, back);
    }
}