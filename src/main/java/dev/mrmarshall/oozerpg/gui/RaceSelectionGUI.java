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

public class RaceSelectionGUI implements Listener {

    public void open(Player p) {
        //> GUI
        Inventory gui = Bukkit.createInventory(null, 9, "§5Select a Race");

        //> Items
        ItemStack placeholder = OozeRPG.getInstance().getItemStackCreator().create(Material.PINK_STAINED_GLASS_PANE, 1, " ", true);
        ItemStack human = OozeRPG.getInstance().getItemStackCreator().create(Material.IRON_SWORD, 1, "§9Human", false);
        ItemStack elf = OozeRPG.getInstance().getItemStackCreator().create(Material.BOW, 1, "§bElf", false);
        ItemStack dwarf = OozeRPG.getInstance().getItemStackCreator().create(Material.WOODEN_AXE, 1, "§eDwarf", false);

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


        }
    }
}
