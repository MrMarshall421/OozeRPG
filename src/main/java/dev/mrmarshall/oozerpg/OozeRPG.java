/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg;

import dev.mrmarshall.oozerpg.data.LevelingData;
import dev.mrmarshall.oozerpg.data.PlayerDataHandler;
import dev.mrmarshall.oozerpg.races.RaceManager;
import dev.mrmarshall.oozerpg.skills.dwarf.DwarfCombatSkills;
import dev.mrmarshall.oozerpg.skills.dwarf.DwarfUtilitySkills;
import dev.mrmarshall.oozerpg.skills.elf.ElfMovementSkills;
import dev.mrmarshall.oozerpg.skills.elf.ElfUtilitySkills;
import dev.mrmarshall.oozerpg.skills.human.HumanCombatSkills;
import dev.mrmarshall.oozerpg.skills.human.HumanMovementSkills;
import dev.mrmarshall.oozerpg.util.ItemStackCreator;
import dev.mrmarshall.oozerpg.util.MobManager;
import dev.mrmarshall.oozerpg.util.SchedulerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class OozeRPG extends JavaPlugin {

    private static OozeRPG instance;
    private InitializationHandler initHandler;
    private ItemStackCreator itemStackCreator;
    private PlayerDataHandler playerDataHandler;
    private RaceManager raceManager;
    private LevelingData levelingData;
    private MobManager mobManager;
    private SchedulerManager schedulerManager;
    private HumanCombatSkills humanCombatSkills;
    private HumanMovementSkills humanMovementSkills;
    private ElfMovementSkills elfMovementSkills;
    private ElfUtilitySkills elfUtilitySkills;
    private DwarfUtilitySkills dwarfUtilitySkills;
    private DwarfCombatSkills dwarfCombatSkills;

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
        schedulerManager = new SchedulerManager();
        humanCombatSkills = new HumanCombatSkills();
        humanMovementSkills = new HumanMovementSkills();
        elfMovementSkills = new ElfMovementSkills();
        elfUtilitySkills = new ElfUtilitySkills();
        dwarfUtilitySkills = new DwarfUtilitySkills();
        dwarfCombatSkills = new DwarfCombatSkills();

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
    public SchedulerManager getSchedulerManager() {
        return schedulerManager;
    }
    public HumanCombatSkills getHumanCombatSkills() {
        return humanCombatSkills;
    }
    public HumanMovementSkills getHumanMovementSkills() {
        return humanMovementSkills;
    }

    public ElfMovementSkills getElfMovementSkills() {
        return elfMovementSkills;
    }

    public ElfUtilitySkills getElfUtilitySkills() {
        return elfUtilitySkills;
    }

    public DwarfUtilitySkills getDwarfUtilitySkills() {
        return dwarfUtilitySkills;
    }

    public DwarfCombatSkills getDwarfCombatSkills() {
        return dwarfCombatSkills;
    }
}
