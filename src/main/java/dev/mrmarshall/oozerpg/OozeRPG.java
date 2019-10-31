package dev.mrmarshall.oozerpg;

import org.bukkit.plugin.java.JavaPlugin;

public class OozeRPG extends JavaPlugin {

    private static OozeRPG instance;
    private InitializationHandler initHandler;

    public static OozeRPG getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        initHandler = new InitializationHandler();

        initHandler.initialize();
    }

    @Override
    public void onDisable() {
        instance = null;
    }
}
