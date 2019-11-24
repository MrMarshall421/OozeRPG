/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg;

import dev.mrmarshall.oozerpg.data.LevelingData;
import dev.mrmarshall.oozerpg.data.PlayerDataHandler;
import dev.mrmarshall.oozerpg.races.RaceManager;
import dev.mrmarshall.oozerpg.skills.CombatSkills;
import dev.mrmarshall.oozerpg.util.ItemStackCreator;
import dev.mrmarshall.oozerpg.util.MobManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OozeRPG extends JavaPlugin {

    private static OozeRPG instance;
    private InitializationHandler initHandler;
    private ItemStackCreator itemStackCreator;
    private PlayerDataHandler playerDataHandler;
    private RaceManager raceManager;
    private LevelingData levelingData;
    private MobManager mobManager;
    private CombatSkills combatSkills;

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
        mobManager = new MobManager();
        combatSkills = new CombatSkills();

        initHandler.initialize();
    }

    @Override
    public void onDisable() {
        instance = null;
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
    public MobManager getMobManager() {
        return mobManager;
    }

    public CombatSkills getCombatSkills() {
        return combatSkills;
    }
}
