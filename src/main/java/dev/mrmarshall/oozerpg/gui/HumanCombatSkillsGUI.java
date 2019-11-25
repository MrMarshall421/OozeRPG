/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.gui;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.Bukkit;
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

public class HumanCombatSkillsGUI implements Listener {

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
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains("§f§lBACK")) {
                    p.closeInventory();
                    skillsGUI.open(p);
                } else if (!e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lYOU")) {
                    //> Select skill
                    if (skillpoints > 0) {
                        if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§4Damage §l1")) {
                            //> Damage 1
                            buySkillUpgrade("skills.combat.damage1.level", e.getCurrentItem(), p);
                        }
                    } else {
                        p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cYou have no skillpoints to spend!");
                    }
                }
            } catch (NullPointerException ex) {
            }
        }
    }

    private void buySkillUpgrade(String skill, ItemStack item, Player p) {
        File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId());
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);
        int currentSkillLevel = Integer.parseInt(item.getItemMeta().getLore().get(0).replaceAll("§9§o", "").substring(0, 1));
        int maxSkillLevel = Integer.parseInt(item.getItemMeta().getLore().get(0).replaceAll("§9§o", "").substring(2, 3));
        int skillpoints = playerFileCfg.getInt("skillpoints");

        if (currentSkillLevel < maxSkillLevel) {
            //> Upgrade Skill
            playerFileCfg.set(skill, (currentSkillLevel + 1) + "/" + maxSkillLevel);
            OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
            OozeRPG.getInstance().getPlayerDataHandler().setPlayerSkillpoints(p.getUniqueId(), skillpoints - 1);

            refreshInventory(p.getOpenInventory().getTopInventory(), p);

            p.sendMessage(PluginMessage.prefix + "§aSkill Successfully upgraded!");
        } else {
            p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cThis skill is already Max-level!");
        }
    }

    private void refreshInventory(Inventory gui, Player p) {
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

        //> Items
        ItemStack placeholder = OozeRPG.getInstance().getItemStackCreator().create(Material.RED_STAINED_GLASS_PANE, 1, " ", true);

        String damage1Level = playerFileCfg.getString("skills.combat.damage1.level");
        double damage1Percentage = OozeRPG.getInstance().getCombatSkills().calculateDamage1(Integer.parseInt(damage1Level.substring(0, 1)));
        ItemStack damage1 = OozeRPG.getInstance().getItemStackCreator().create(Material.ROSE_RED, 1, "§4Damage §l1", false);
        List<String> damage1Lore = new ArrayList<>();
        damage1Lore.add("§9§o" + damage1Level + " Levels unlocked");
        damage1Lore.add("§9§oGains " + damage1Percentage + "% Damage buff");
        OozeRPG.getInstance().getItemStackCreator().addLore(damage1, damage1Lore);

        String tank1Level = playerFileCfg.getString("skills.combat.tank1.level");
        double tank1Percentage = OozeRPG.getInstance().getCombatSkills().calculateTank1(Integer.parseInt(tank1Level.substring(0, 1)));
        ItemStack tank1 = OozeRPG.getInstance().getItemStackCreator().create(Material.CACTUS_GREEN, 1, "§2Tank §l1", false);
        List<String> tank1Lore = new ArrayList<>();
        tank1Lore.add("§9§o" + tank1Level + " Levels unlocked");
        tank1Lore.add("§9§oGains " + tank1Percentage + "% Damage reduction");
        OozeRPG.getInstance().getItemStackCreator().addLore(tank1, tank1Lore);

        String lifesteal1Level = playerFileCfg.getString("skills.combat.lifesteal1.level");
        double lifesteal1Percentage = OozeRPG.getInstance().getCombatSkills().calculateLifesteal1(Integer.parseInt(lifesteal1Level.substring(0, 1)));
        ItemStack lifesteal1 = OozeRPG.getInstance().getItemStackCreator().create(Material.LAPIS_LAZULI, 1, "§1Lifesteal §l1", false);
        List<String> lifesteal1Lore = new ArrayList<>();
        lifesteal1Lore.add("§9§o" + lifesteal1Level + " Levels unlocked");
        lifesteal1Lore.add("§9§oSteals " + lifesteal1Percentage + "% Health based on dealt damage");
        OozeRPG.getInstance().getItemStackCreator().addLore(lifesteal1, lifesteal1Lore);

        String training1Level = playerFileCfg.getString("skills.combat.training1.level");
        double training1Percentage = OozeRPG.getInstance().getCombatSkills().calculateTraining1(Integer.parseInt(training1Level.substring(0, 1)));
        ItemStack training1 = OozeRPG.getInstance().getItemStackCreator().create(Material.MAGENTA_DYE, 1, "§5Training §l1", false);
        List<String> training1Lore = new ArrayList<>();
        training1Lore.add("§9§o" + training1Level + " Levels unlocked");
        training1Lore.add("§9§oGains " + training1Percentage + "% Chance to dodge an attack");
        OozeRPG.getInstance().getItemStackCreator().addLore(training1, training1Lore);

        String tank2Level = playerFileCfg.getString("skills.combat.tank2.level");
        double tank2Percentage = OozeRPG.getInstance().getCombatSkills().calculateTank2(Integer.parseInt(tank2Level.substring(0, 1)));
        ItemStack tank2 = OozeRPG.getInstance().getItemStackCreator().create(Material.CYAN_DYE, 1, "§3Tank §l2", false);
        List<String> tank2Lore = new ArrayList<>();
        tank2Lore.add("§9§o" + tank2Level + " Levels unlocked");
        tank2Lore.add("§9§oGains " + tank2Percentage + "% Damage reduction");
        OozeRPG.getInstance().getItemStackCreator().addLore(tank2, tank2Lore);

        String damage2Level = playerFileCfg.getString("skills.combat.damage2.level");
        double damage2Percentage = OozeRPG.getInstance().getCombatSkills().calculateDamage2(Integer.parseInt(damage2Level.substring(0, 1)));
        ItemStack damage2 = OozeRPG.getInstance().getItemStackCreator().create(Material.DANDELION_YELLOW, 1, "§eDamage §l2", false);
        List<String> damage2Lore = new ArrayList<>();
        damage2Lore.add("§9§o" + damage2Level + " Levels unlocked");
        damage2Lore.add("§9§oGains " + damage2Percentage + "% Damage buff");
        OozeRPG.getInstance().getItemStackCreator().addLore(damage2, damage2Lore);

        String greed1Level = playerFileCfg.getString("skills.combat.greed1.level");
        double greed1Percentage = OozeRPG.getInstance().getCombatSkills().calculateGreed1(Integer.parseInt(greed1Level.substring(0, 1)));
        ItemStack greed1 = OozeRPG.getInstance().getItemStackCreator().create(Material.LIME_DYE, 1, "§aGreed §l1", false);
        List<String> greed1Lore = new ArrayList<>();
        greed1Lore.add("§9§o" + greed1Level + " Levels unlocked");
        greed1Lore.add("§9§oGains " + greed1Percentage + "% Chance to poison enemy");
        OozeRPG.getInstance().getItemStackCreator().addLore(greed1, greed1Lore);

        String mastertankLevel = playerFileCfg.getString("skills.combat.mastertank.level");
        double mastertankPercentage = OozeRPG.getInstance().getCombatSkills().calculateMastertank(Integer.parseInt(mastertankLevel.substring(0, 1)));
        ItemStack mastertank = OozeRPG.getInstance().getItemStackCreator().create(Material.GRAY_DYE, 1, "§8Mastertank", false);
        List<String> mastertankLore = new ArrayList<>();
        mastertankLore.add("§9§o" + mastertankLevel + " Levels unlocked");
        mastertankLore.add("§9§oGains " + mastertankPercentage + "% Damage reduction");
        OozeRPG.getInstance().getItemStackCreator().addLore(mastertank, mastertankLore);

        ItemStack playerInfo = OozeRPG.getInstance().getItemStackCreator().create(Material.PLAYER_HEAD, 1, "§e§lYOU", false);
        SkullMeta playerInfoMeta = (SkullMeta) playerInfo.getItemMeta();
        playerInfoMeta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
        playerInfo.setItemMeta(playerInfoMeta);
        List<String> playerInfoLore = new ArrayList<>();
        playerInfoLore.add("§9§oCurrent race: §b§lHUMAN");
        playerInfoLore.add("§9§oGains +10% Sword Damage");
        playerInfoLore.add(" ");
        playerInfoLore.add("§9§oSkillpoints left: §l" + playerFileCfg.getInt("skillpoints"));
        OozeRPG.getInstance().getItemStackCreator().addLore(playerInfo, playerInfoLore);

        ItemStack back = OozeRPG.getInstance().getItemStackCreator().create(Material.NETHER_STAR, 1, "§f§lBACK", false);

        //> Set Items
        for (int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, placeholder);
        }

        gui.setItem(10, damage1);
        gui.setItem(11, tank1);
        gui.setItem(12, lifesteal1);
        gui.setItem(13, training1);
        gui.setItem(14, tank2);
        gui.setItem(15, damage2);
        gui.setItem(16, greed1);
        gui.setItem(20, mastertank);
        gui.setItem(27, playerInfo);
        gui.setItem(35, back);
    }
}
