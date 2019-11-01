package dev.mrmarshall.oozerpg;

import dev.mrmarshall.oozerpg.util.ItemStackCreator;
import org.bukkit.plugin.java.JavaPlugin;

public class OozeRPG extends JavaPlugin {

    private static OozeRPG instance;
    private InitializationHandler initHandler;
    private ItemStackCreator itemStackCreator;

    public static OozeRPG getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    @Override
    public void onEnable() {
        instance = this;
        initHandler = new InitializationHandler();
        itemStackCreator = new ItemStackCreator();

        initHandler.initialize();
    }

    public ItemStackCreator getItemStackCreator() {
        return itemStackCreator;
    }
}
