/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg;

import dev.mrmarshall.oozerpg.commands.RaceCMD;
import dev.mrmarshall.oozerpg.commands.SkillsCMD;
import dev.mrmarshall.oozerpg.events.EntityDamageByEntityListener;
import dev.mrmarshall.oozerpg.events.PlayerJoinListener;
import dev.mrmarshall.oozerpg.gui.RaceSelectionGUI;
import dev.mrmarshall.oozerpg.gui.SkillsGUI;
import dev.mrmarshall.oozerpg.gui.human.HumanCombatSkillsGUI;
import dev.mrmarshall.oozerpg.gui.human.HumanMovementSkillsGUI;
import dev.mrmarshall.oozerpg.levels.LevelingSystem;
import org.bukkit.Bukkit;

public class InitializationHandler {

    public void initialize() {
        registerEvents();
        registerCommands();
        createFiles();
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), OozeRPG.getInstance());
        Bukkit.getPluginManager().registerEvents(new LevelingSystem(), OozeRPG.getInstance());
        Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), OozeRPG.getInstance());

        //> GUIs
        Bukkit.getPluginManager().registerEvents(new RaceSelectionGUI(), OozeRPG.getInstance());
        Bukkit.getPluginManager().registerEvents(new SkillsGUI(), OozeRPG.getInstance());
        Bukkit.getPluginManager().registerEvents(new HumanCombatSkillsGUI(), OozeRPG.getInstance());
        Bukkit.getPluginManager().registerEvents(new HumanMovementSkillsGUI(), OozeRPG.getInstance());
    }

    private void registerCommands() {
        RaceCMD cRaceCMD = new RaceCMD();
        OozeRPG.getInstance().getCommand("race").setExecutor(cRaceCMD);
        SkillsCMD cSkillsCMD = new SkillsCMD();
        OozeRPG.getInstance().getCommand("skills").setExecutor(cSkillsCMD);
    }

    private void createFiles() {
        OozeRPG.getInstance().getPlayerDataHandler().createDirectories();
        OozeRPG.getInstance().getLevelingData().createFiles();
    }
}
