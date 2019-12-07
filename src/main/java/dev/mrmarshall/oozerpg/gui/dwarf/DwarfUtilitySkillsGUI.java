/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.gui.dwarf;

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

public class DwarfUtilitySkillsGUI implements Listener {

    public void open(Player p) {
        //> GUI
        Inventory gui = Bukkit.createInventory(null, 45, "§6Utility Skills");
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
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§4Growing Skin §l1")) {
                            //> Growing Skin 1
                            buySkillUpgrade("skills.utility.growingskin1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§2Fierce Anger §l1")) {
                            //> Fierce Anger 1
                            buySkillUpgrade("skills.utility.fierceanger1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§1Fired Hands")) {
                            //> Fired Hands
                            buySkillUpgrade("skills.utility.firedhands.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§5Magic Aura §l1")) {
                            //> Magic Aura 1
                            buySkillUpgrade("skills.utility.magicaura1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3Growing Skin §l2")) {
                            //> Growing Skin 2
                            buySkillUpgrade("skills.utility.growingskin2.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eCursed Armor §l1")) {
                            //> Cursed Armor 1
                            buySkillUpgrade("skills.utility.cursedarmor1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aFierce Anger §l2")) {
                            //> Fierce Anger 2
                            buySkillUpgrade("skills.utility.fierceanger2.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§8Growing Skin §l3")) {
                            //> Growing Skin 3
                            buySkillUpgrade("skills.utility.growingskin3.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bDark Depths §l1")) {
                            //> Dark Depths 1
                            buySkillUpgrade("skills.utility.darkdepths1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§dDreadsdemise")) {
                            //> Dreadsdemise
                            buySkillUpgrade("skills.utility.dreadsdemise.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Beefy §l1")) {
                            //> Beefy 1
                            buySkillUpgrade("skills.utility.beefy1.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§fMagic Aura §l2")) {
                            //> Magic Aura 2
                            buySkillUpgrade("skills.utility.magicaura2.level", e.getCurrentItem(), e.getSlot(), p);
                        } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Unbreakable")) {
                            //> Unbreakable
                            buySkillUpgrade("skills.utility.unbreakable.level", e.getCurrentItem(), e.getSlot(), p);
                        }
                    } else {
                        if (e.getCurrentItem().getType() != Material.ORANGE_STAINED_GLASS_PANE) {
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
            if (p.getOpenInventory().getTopInventory().getItem(slot - 1).getType() != Material.ORANGE_STAINED_GLASS_PANE) {
                previousItemSlot = slot - 1;
            } else {
                previousItemSlot = slot - 2;
            }

            if (p.getOpenInventory().getTopInventory().getItem(previousItemSlot).getType() != Material.ORANGE_STAINED_GLASS_PANE) {
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
                OozeRPG.getInstance().getDwarfUtilitySkills().updateMaxHealth(p.getUniqueId());

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
        ItemStack placeholder = OozeRPG.getInstance().getItemStackCreator().create(Material.ORANGE_STAINED_GLASS_PANE, 1, " ", true);

        String growingSkin1Level = playerFileCfg.getString("skills.utility.growingSkin1.level");
        double growingSkin1Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateGrowingSkin1(Integer.parseInt(growingSkin1Level.substring(0, 1)));
        ItemStack growingSkin1 = OozeRPG.getInstance().getItemStackCreator().create(Material.ROSE_RED, 1, "§4Growing Skin §l1", false);
        List<String> growingSkin1Lore = new ArrayList<>();
        growingSkin1Lore.add("§9§o" + growingSkin1Level + " Levels unlocked");
        growingSkin1Lore.add("§9§oGains " + growingSkin1Percentage / 2 + " additional Hearts");
        OozeRPG.getInstance().getItemStackCreator().addLore(growingSkin1, growingSkin1Lore);

        String fierceAnger1Level = playerFileCfg.getString("skills.utility.fierceAnger1.level");
        double fierceAnger1Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateFierceAnger1(Integer.parseInt(fierceAnger1Level.substring(0, 1)));
        ItemStack fierceAnger1 = OozeRPG.getInstance().getItemStackCreator().create(Material.CACTUS_GREEN, 1, "§2Fierce Anger §l1", false);
        List<String> fierceAnger1Lore = new ArrayList<>();
        fierceAnger1Lore.add("§9§o" + fierceAnger1Level + " Levels unlocked");
        fierceAnger1Lore.add("§9§oGains " + fierceAnger1Percentage + "% Chance to catch attacker on fire");
        OozeRPG.getInstance().getItemStackCreator().addLore(fierceAnger1, fierceAnger1Lore);

        String firedHandsLevel = playerFileCfg.getString("skills.utility.firedHands.level");
        ItemStack firedHands = OozeRPG.getInstance().getItemStackCreator().create(Material.LAPIS_LAZULI, 1, "§1Fired Hands", false);
        List<String> firedHandsLore = new ArrayList<>();
        firedHandsLore.add("§9§o" + firedHandsLevel + " Levels unlocked");
        firedHandsLore.add("§9§oGains fire immunity");
        OozeRPG.getInstance().getItemStackCreator().addLore(firedHands, firedHandsLore);

        String magicAura1Level = playerFileCfg.getString("skills.utility.magicAura1.level");
        double magicAura1Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateMagicAura1(Integer.parseInt(magicAura1Level.substring(0, 1)));
        ItemStack magicAura1 = OozeRPG.getInstance().getItemStackCreator().create(Material.MAGENTA_DYE, 1, "§5Magic Aura §l1", false);
        List<String> magicAura1Lore = new ArrayList<>();
        magicAura1Lore.add("§9§o" + magicAura1Level + " Levels unlocked");
        magicAura1Lore.add("§9§oGains " + magicAura1Percentage + "% Passive Thorns");
        OozeRPG.getInstance().getItemStackCreator().addLore(magicAura1, magicAura1Lore);

        String growingSkin2Level = playerFileCfg.getString("skills.utility.growingSkin2.level");
        double growingSkin2Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateGrowingSkin2(Integer.parseInt(growingSkin2Level.substring(0, 1)));
        ItemStack growingSkin2 = OozeRPG.getInstance().getItemStackCreator().create(Material.CYAN_DYE, 1, "§3Growing Skin §l2", false);
        List<String> growingSkin2Lore = new ArrayList<>();
        growingSkin2Lore.add("§9§o" + growingSkin2Level + " Levels unlocked");
        growingSkin2Lore.add("§9§oGains " + growingSkin2Percentage / 2 + " additional Hearts");
        OozeRPG.getInstance().getItemStackCreator().addLore(growingSkin2, growingSkin2Lore);

        String cursedArmor1Level = playerFileCfg.getString("skills.utility.cursedArmor1.level");
        double cursedArmor1Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateCursedArmor1(Integer.parseInt(cursedArmor1Level.substring(0, 1)));
        ItemStack cursedArmor1 = OozeRPG.getInstance().getItemStackCreator().create(Material.DANDELION_YELLOW, 1, "§eCursed Armor §l1", false);
        List<String> cursedArmor1Lore = new ArrayList<>();
        cursedArmor1Lore.add("§9§o" + cursedArmor1Level + " Levels unlocked");
        cursedArmor1Lore.add("§9§oGains " + cursedArmor1Percentage / 2 + " additional Hearts");
        OozeRPG.getInstance().getItemStackCreator().addLore(cursedArmor1, cursedArmor1Lore);

        String fierceAnger2Level = playerFileCfg.getString("skills.utility.fierceAnger2.level");
        double fierceAnger2Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateFierceAnger2(Integer.parseInt(fierceAnger2Level.substring(0, 1)));
        ItemStack fierceAnger2 = OozeRPG.getInstance().getItemStackCreator().create(Material.LIME_DYE, 1, "§aFierce Anger §l2", false);
        List<String> fierceAnger2Lore = new ArrayList<>();
        fierceAnger2Lore.add("§9§o" + fierceAnger2Level + " Levels unlocked");
        fierceAnger2Lore.add("§9§oGains " + fierceAnger2Percentage + "% Chance to catch attacker on fire");
        OozeRPG.getInstance().getItemStackCreator().addLore(fierceAnger2, fierceAnger2Lore);

        String growingSkin3Level = playerFileCfg.getString("skills.utility.growingSkin3.level");
        double growingSkin3Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateGrowingSkin3(Integer.parseInt(growingSkin3Level.substring(0, 1)));
        ItemStack growingSkin3 = OozeRPG.getInstance().getItemStackCreator().create(Material.GRAY_DYE, 1, "§8Growing Skin §l3", false);
        List<String> growingSkin3Lore = new ArrayList<>();
        growingSkin3Lore.add("§9§o" + growingSkin3Level + " Levels unlocked");
        growingSkin3Lore.add("§9§oGains " + growingSkin3Percentage / 2 + " additional Hearts");
        OozeRPG.getInstance().getItemStackCreator().addLore(growingSkin3, growingSkin3Lore);

        String darkDepths1Level = playerFileCfg.getString("skills.utility.darkDepths1.level");
        double darkDepths1Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateDarkDepths1(Integer.parseInt(darkDepths1Level.substring(0, 1)));
        double darkDepths1Percentage2 = OozeRPG.getInstance().getDwarfUtilitySkills().calculateDarkDepths1b(Integer.parseInt(darkDepths1Level.substring(0, 1)));
        ItemStack darkDepths1 = OozeRPG.getInstance().getItemStackCreator().create(Material.LIGHT_BLUE_DYE, 1, "§bDark Depths §l1", false);
        List<String> darkDepths1Lore = new ArrayList<>();
        darkDepths1Lore.add("§9§o" + darkDepths1Level + " Levels unlocked");
        darkDepths1Lore.add("§9§oGains " + darkDepths1Percentage + "% Chance to blind attacker");
        darkDepths1Lore.add("§9§oGains " + darkDepths1Percentage2 + "% Chance to freeze attacker");
        OozeRPG.getInstance().getItemStackCreator().addLore(darkDepths1, darkDepths1Lore);

        String dreadsdemiseLevel = playerFileCfg.getString("skills.utility.dreadsdemise.level");
        ItemStack dreadsdemise = OozeRPG.getInstance().getItemStackCreator().create(Material.PINK_DYE, 1, "§dDreadsdemise", false);
        List<String> dreadsdemiseLore = new ArrayList<>();
        dreadsdemiseLore.add("§9§o" + dreadsdemiseLevel + " Levels unlocked");
        dreadsdemiseLore.add("§9§oGains wither immunity");
        OozeRPG.getInstance().getItemStackCreator().addLore(dreadsdemise, dreadsdemiseLore);

        String beefy1Level = playerFileCfg.getString("skills.utility.beefy1.level");
        double beefy1Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateBeefy1(Integer.parseInt(beefy1Level.substring(0, 1)));
        ItemStack beefy1 = OozeRPG.getInstance().getItemStackCreator().create(Material.LIGHT_GRAY_DYE, 1, "§7Beefy §l1", false);
        List<String> beefy1Lore = new ArrayList<>();
        beefy1Lore.add("§9§o" + beefy1Level + " Levels unlocked");
        beefy1Lore.add("§9§oGains " + beefy1Percentage + "% Chance to get 6 absorption hearts");
        OozeRPG.getInstance().getItemStackCreator().addLore(beefy1, beefy1Lore);

        String magicAura2Level = playerFileCfg.getString("skills.utility.magicAura2.level");
        double magicAura2Percentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateMagicAura2(Integer.parseInt(magicAura2Level.substring(0, 1)));
        ItemStack magicAura2 = OozeRPG.getInstance().getItemStackCreator().create(Material.SUGAR, 1, "§fMagic Aura §l2", false);
        List<String> magicAura2Lore = new ArrayList<>();
        magicAura2Lore.add("§9§o" + magicAura2Level + " Levels unlocked");
        magicAura2Lore.add("§9§oGains " + magicAura2Percentage + "% Passive Thorns");
        OozeRPG.getInstance().getItemStackCreator().addLore(magicAura2, magicAura2Lore);

        String unbreakableLevel = playerFileCfg.getString("skills.utility.unbreakable.level");
        double unbreakablePercentage = OozeRPG.getInstance().getDwarfUtilitySkills().calculateUnbreakable(Integer.parseInt(unbreakableLevel.substring(0, 1)));
        ItemStack unbreakable = OozeRPG.getInstance().getItemStackCreator().create(Material.ORANGE_DYE, 1, "§6Unbreakable", false);
        List<String> unbreakableLore = new ArrayList<>();
        unbreakableLore.add("§9§o" + unbreakableLevel + " Levels unlocked");
        unbreakableLore.add("§9§oGains " + unbreakablePercentage + "% Chance to gain resistance 5 and weakness 5 when hit under 4 hearts");
        OozeRPG.getInstance().getItemStackCreator().addLore(unbreakable, unbreakableLore);

        ItemStack playerInfo = OozeRPG.getInstance().getItemStackCreator().create(Material.PLAYER_HEAD, 1, "§e§lYOU", false);
        SkullMeta playerInfoMeta = (SkullMeta) playerInfo.getItemMeta();
        playerInfoMeta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
        playerInfo.setItemMeta(playerInfoMeta);
        List<String> playerInfoLore = new ArrayList<>();
        playerInfoLore.add("§9§oCurrent race: §b§lDWARF");
        playerInfoLore.add("§9§oGains +10% Axe Damage");
        playerInfoLore.add(" ");
        playerInfoLore.add("§9§oSkillpoints left: §l" + playerFileCfg.getInt("skillpoints"));
        OozeRPG.getInstance().getItemStackCreator().addLore(playerInfo, playerInfoLore);

        ItemStack back = OozeRPG.getInstance().getItemStackCreator().create(Material.NETHER_STAR, 1, "§f§lBACK", false);

        //> Set Items
        for (int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, placeholder);
        }

        gui.setItem(10, growingSkin1);
        gui.setItem(11, fierceAnger1);
        gui.setItem(12, firedHands);
        gui.setItem(13, magicAura1);
        gui.setItem(14, growingSkin2);
        gui.setItem(15, cursedArmor1);
        gui.setItem(16, fierceAnger2);
        gui.setItem(20, growingSkin3);
        gui.setItem(21, darkDepths1);
        gui.setItem(22, dreadsdemise);
        gui.setItem(23, beefy1);
        gui.setItem(24, magicAura2);
        gui.setItem(30, unbreakable);
        gui.setItem(36, playerInfo);
        gui.setItem(44, back);
    }
}