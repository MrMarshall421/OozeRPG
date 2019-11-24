/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.gui;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RaceSelectionGUI implements Listener {

    public void open(Player p) {
        //> GUI
        Inventory gui = Bukkit.createInventory(null, 9, "§5Select a Race");

        //> Items
        ItemStack placeholder = OozeRPG.getInstance().getItemStackCreator().create(Material.PINK_STAINED_GLASS_PANE, 1, " ", true);

        ItemStack human = OozeRPG.getInstance().getItemStackCreator().create(Material.IRON_SWORD, 1, "§9Human", false);
        List<String> humanLore = new ArrayList<>();
        humanLore.add("§9§oFilled with lust they find it easy to conquer the world with force.");
        humanLore.add("§9§oSwords are the answer.");
        OozeRPG.getInstance().getItemStackCreator().addLore(human, humanLore);

        ItemStack elf = OozeRPG.getInstance().getItemStackCreator().create(Material.BOW, 1, "§bElf", false);
        List<String> elfLore = new ArrayList<>();
        elfLore.add("§b§oThey seek to right the wrongs that have been brought into these lands.");
        elfLore.add("§b§oBows are the select choice of this elegant race.");
        OozeRPG.getInstance().getItemStackCreator().addLore(elf, elfLore);

        ItemStack dwarf = OozeRPG.getInstance().getItemStackCreator().create(Material.WOODEN_AXE, 1, "§eDwarf", false);
        List<String> dwarfLore = new ArrayList<>();
        dwarfLore.add("§e§oFinding pride in craftworks they practice their");
        dwarfLore.add("§e§owhole lives to create Legendary Axes.");
        OozeRPG.getInstance().getItemStackCreator().addLore(dwarf, dwarfLore);

        //> Set Items
        gui.setItem(0, placeholder);
        gui.setItem(1, placeholder);
        gui.setItem(2, human);
        gui.setItem(3, placeholder);
        gui.setItem(4, elf);
        gui.setItem(5, placeholder);
        gui.setItem(6, dwarf);
        gui.setItem(7, placeholder);
        gui.setItem(8, placeholder);

        //> Open GUI
        p.openInventory(gui);
        p.playSound(p.getLocation(), Sound.BLOCK_FENCE_GATE_OPEN, 1.0F, 1.0F);
    }

    @EventHandler
    public void onClickRaceSelectionGUI(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("§5Select a Race")) {
            e.setCancelled(true);

            try {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§9Human")) {
                    p.closeInventory();
                    OozeRPG.getInstance().getRaceManager().selectRace(p.getUniqueId(), "HUMAN");
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§bElf")) {
                    p.closeInventory();
                    OozeRPG.getInstance().getRaceManager().selectRace(p.getUniqueId(), "ELF");
                } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eDwarf")) {
                    p.closeInventory();
                    OozeRPG.getInstance().getRaceManager().selectRace(p.getUniqueId(), "DWARF");
                }
            } catch (NullPointerException ex) {
            }
        }
    }
}
