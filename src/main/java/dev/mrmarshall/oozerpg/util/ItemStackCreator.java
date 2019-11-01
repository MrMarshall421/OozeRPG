package dev.mrmarshall.oozerpg.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackCreator {

    public ItemStack create(Material material, int amount, String title, boolean glow) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(title);
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (glow) {
            itemMeta.addEnchant(Enchantment.PROTECTION_FALL, 1, true);
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        item.setItemMeta(itemMeta);
        return item;
    }
}
