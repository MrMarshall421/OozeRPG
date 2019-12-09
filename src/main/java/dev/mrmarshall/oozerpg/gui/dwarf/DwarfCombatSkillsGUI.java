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

public class DwarfCombatSkillsGUI implements Listener {

    public void open(Player p) {
        //> GUI
        Inventory gui = Bukkit.createInventory(null, 36, "§cCombat Skills");
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

        if (e.getView().getTitle().equals("§cCombat Skills")) {
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
                previousItemLevel = Integer.parseInt(playerFileCfg.getString("skills.utility." + previousItem + ".level").substring(0, 1));
                previousItemMaxLevel = Integer.parseInt(playerFileCfg.getString("skills.utility." + previousItem + ".level").substring(2, 3));
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

        String tank1Level = playerFileCfg.getString("skills.combat.tank1.level");
        double tank1Percentage = OozeRPG.getInstance().getDwarfCombatSkills().calculateTank1(Integer.parseInt(tank1Level.substring(0, 1)));
        ItemStack tank1 = OozeRPG.getInstance().getItemStackCreator().create(Material.ROSE_RED, 1, "§4Tank §l1", false);
        List<String> tank1Lore = new ArrayList<>();
        tank1Lore.add("§9§o" + tank1Level + " Levels unlocked");
        tank1Lore.add("§9§oGains " + tank1Percentage + "% Damage reduction");
        OozeRPG.getInstance().getItemStackCreator().addLore(tank1, tank1Lore);

        String damage1Level = playerFileCfg.getString("skills.combat.damage1.level");
        double damage1Percentage = OozeRPG.getInstance().getDwarfCombatSkills().calculateDamage1(Integer.parseInt(damage1Level.substring(0, 1)));
        ItemStack damage1 = OozeRPG.getInstance().getItemStackCreator().create(Material.CACTUS_GREEN, 1, "§2Damage §l1", false);
        List<String> damage1Lore = new ArrayList<>();
        damage1Lore.add("§9§o" + damage1Level + " Levels unlocked");
        damage1Lore.add("§9§oGains " + damage1Percentage + "% Damage buff");
        OozeRPG.getInstance().getItemStackCreator().addLore(damage1, damage1Lore);

        String thickskin1Level = playerFileCfg.getString("skills.combat.thickskin1.level");
        double thickskin1Percentage = OozeRPG.getInstance().getDwarfCombatSkills().calculateThickSkin1(Integer.parseInt(thickskin1Level.substring(0, 1)));
        ItemStack thickskin1 = OozeRPG.getInstance().getItemStackCreator().create(Material.LAPIS_LAZULI, 1, "§1Thick Skin §l1", false);
        List<String> thickskin1Lore = new ArrayList<>();
        thickskin1Lore.add("§9§o" + thickskin1Level + " Levels unlocked");
        thickskin1Lore.add("§9§oGains " + thickskin1Percentage + "% Chance to heal 2 hearts");
        OozeRPG.getInstance().getItemStackCreator().addLore(thickskin1, thickskin1Lore);

        String tank2Level = playerFileCfg.getString("skills.combat.tank2.level");
        double tank2Percentage = OozeRPG.getInstance().getDwarfCombatSkills().calculateTank2(Integer.parseInt(tank2Level.substring(0, 1)));
        ItemStack tank2 = OozeRPG.getInstance().getItemStackCreator().create(Material.MAGENTA_DYE, 1, "§5Tank §l2", false);
        List<String> tank2Lore = new ArrayList<>();
        tank2Lore.add("§9§o" + tank2Level + " Levels unlocked");
        tank2Lore.add("§9§oGains " + tank2Percentage + "% Damage reduction");
        OozeRPG.getInstance().getItemStackCreator().addLore(tank2, tank2Lore);

        String welltaughtLevel = playerFileCfg.getString("skills.combat.welltaught.level");
        double welltaughtPercentage = OozeRPG.getInstance().getDwarfCombatSkills().calculateWelltaught(Integer.parseInt(welltaughtLevel.substring(0, 1)));
        ItemStack welltaught = OozeRPG.getInstance().getItemStackCreator().create(Material.CYAN_DYE, 1, "§3Well-Taught", false);
        List<String> welltaughtLore = new ArrayList<>();
        welltaughtLore.add("§9§o" + welltaughtLevel + " Levels unlocked");
        welltaughtLore.add("§9§oGains " + welltaughtPercentage + "% Chance to light victim on fire and heal " + welltaughtPercentage / 2 + " hearts per second");
        OozeRPG.getInstance().getItemStackCreator().addLore(welltaught, welltaughtLore);

        String furious1Level = playerFileCfg.getString("skills.combat.furious1.level");
        double furious1Percentage = OozeRPG.getInstance().getDwarfCombatSkills().calculateFurious1(Integer.parseInt(furious1Level.substring(0, 1)));
        ItemStack furious1 = OozeRPG.getInstance().getItemStackCreator().create(Material.DANDELION_YELLOW, 1, "§eFurious §l1", false);
        List<String> furious1Lore = new ArrayList<>();
        furious1Lore.add("§9§o" + furious1Level + " Levels unlocked");
        furious1Lore.add("§9§oGains " + furious1Percentage + "% extra damage resistance and slowness");
        OozeRPG.getInstance().getItemStackCreator().addLore(furious1, furious1Lore);

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

        gui.setItem(10, tank1);
        gui.setItem(11, damage1);
        gui.setItem(12, thickskin1);
        gui.setItem(13, tank2);
        gui.setItem(14, welltaught);
        gui.setItem(15, furious1);
        gui.setItem(27, playerInfo);
        gui.setItem(35, back);
    }
}