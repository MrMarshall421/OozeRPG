package dev.mrmarshall.oozerpg;

import dev.mrmarshall.oozerpg.commands.RaceCMD;
import dev.mrmarshall.oozerpg.gui.RaceSelectionGUI;
import org.bukkit.Bukkit;

public class InitializationHandler {

    public void initialize() {
        registerEvents();
        registerCommands();
        createFiles();
    }

    private void registerEvents() {
        //> GUIs
        Bukkit.getPluginManager().registerEvents(new RaceSelectionGUI(), OozeRPG.getInstance());
    }

    private void registerCommands() {
        RaceCMD cRaceCMD = new RaceCMD();
        OozeRPG.getInstance().getCommand("race").setExecutor(cRaceCMD);
    }

    private void createFiles() {
        OozeRPG.getInstance().getPlayerDataHandler().createDirectories();
        OozeRPG.getInstance().getLevelingData().createFiles();
    }
}
