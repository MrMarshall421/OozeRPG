/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.gui;

import dev.mrmarshall.oozerpg.OozeRPG;
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

import java.util.ArrayList;
import java.util.List;

public class SkillsGUI implements Listener {

    public void open(Player p) {
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

        //> GUI
        Inventory gui = Bukkit.createInventory(null, 27, "§5Your Skills");

        //> Items
        String amplifier = "";

        ItemStack placeholder = null;
        switch (playerFileCfg.getString("race")) {
            case "HUMAN":
                placeholder = OozeRPG.getInstance().getItemStackCreator().create(Material.RED_STAINED_GLASS_PANE, 1, " ", true);
                amplifier = "Sword";
                break;
            case "ELF":
                placeholder = OozeRPG.getInstance().getItemStackCreator().create(Material.LIME_STAINED_GLASS_PANE, 1, " ", true);
                amplifier = "Bow";
                break;
            case "DWARF":
                placeholder = OozeRPG.getInstance().getItemStackCreator().create(Material.ORANGE_STAINED_GLASS_PANE, 1, " ", true);
                amplifier = "Axe";
                break;
            default:
                break;
        }

        ItemStack playerInfo = OozeRPG.getInstance().getItemStackCreator().create(Material.PLAYER_HEAD, 1, "§e§lYOU", false);
        SkullMeta playerInfoMeta = (SkullMeta) playerInfo.getItemMeta();
        playerInfoMeta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
        playerInfo.setItemMeta(playerInfoMeta);
        List<String> playerInfoLore = new ArrayList<>();
        playerInfoLore.add("§9§oCurrent race: §b§l" + playerFileCfg.getString("race"));
        playerInfoLore.add("§9§oGains +10% " + amplifier + " Damage");
        OozeRPG.getInstance().getItemStackCreator().addLore(playerInfo, playerInfoLore);

        ItemStack combat = OozeRPG.getInstance().getItemStackCreator().create(Material.DIAMOND_SWORD, 1, "§cCombat", false);
        ItemStack movement = OozeRPG.getInstance().getItemStackCreator().create(Material.DIAMOND_BOOTS, 1, "§3Movement", false);
        ItemStack utility = OozeRPG.getInstance().getItemStackCreator().create(Material.SHIELD, 1, "§6Utility", false);
        ItemStack rebirth = OozeRPG.getInstance().getItemStackCreator().create(Material.NETHER_STAR, 1, "§f§lRebirth", false);

        //> Set Items
        for (int i = 0; i < gui.getSize(); i++) {
            gui.setItem(i, placeholder);
        }

        gui.setItem(9, playerInfo);
        gui.setItem(17, rebirth);

        switch (playerFileCfg.getString("race")) {
            case "HUMAN":
                gui.setItem(12, combat);
                gui.setItem(14, movement);
                break;
            case "ELF":
                gui.setItem(12, movement);
                gui.setItem(14, utility);
                break;
            case "DWARF":
                gui.setItem(12, combat);
                gui.setItem(14, utility);
                break;
            default:
                break;
        }

        //> Open GUI
        p.openInventory(gui);
        p.playSound(p.getLocation(), Sound.BLOCK_FENCE_GATE_OPEN, 1.0F, 1.0F);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        HumanCombatSkillsGUI humanCombatSkillsGUI = new HumanCombatSkillsGUI();

        if (e.getView().getTitle().equals("§5Your Skills")) {
            e.setCancelled(true);

            try {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cCombat")) {
                    p.closeInventory();
                    humanCombatSkillsGUI.open(p);
                }
            } catch (NullPointerException ex) {
            }
        }
    }
}