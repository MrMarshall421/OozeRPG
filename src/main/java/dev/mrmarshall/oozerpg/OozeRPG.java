package dev.mrmarshall.oozerpg;

import dev.mrmarshall.oozerpg.data.LevelingData;
import dev.mrmarshall.oozerpg.data.PlayerDataHandler;
import dev.mrmarshall.oozerpg.races.RaceManager;
import dev.mrmarshall.oozerpg.util.ItemStackCreator;
import org.bukkit.plugin.java.JavaPlugin;

public class OozeRPG extends JavaPlugin {

    private static OozeRPG instance;
    private InitializationHandler initHandler;
    private ItemStackCreator itemStackCreator;
    private PlayerDataHandler playerDataHandler;
    private RaceManager raceManager;
    private LevelingData levelingData;

    @Override
    public void onDisable() {
        instance = null;
    }

    public static OozeRPG getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        initHandler = new InitializationHandler();
        itemStackCreator = new ItemStackCreator();
        playerDataHandler = new PlayerDataHandler();
        raceManager = new RaceManager();
        levelingData = new LevelingData();

        initHandler.initialize();
    }
    public ItemStackCreator getItemStackCreator() {
        return itemStackCreator;
    }

    public PlayerDataHandler getPlayerDataHandler() {
        return playerDataHandler;
    }

    public RaceManager getRaceManager() {
        return raceManager;
    }

    public LevelingData getLevelingData() {
        return levelingData;
    }
}
