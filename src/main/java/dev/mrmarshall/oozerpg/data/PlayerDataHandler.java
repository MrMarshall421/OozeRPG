package dev.mrmarshall.oozerpg.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerDataHandler {

    public void createDirectories() {
        File mainDir = new File("plugins/OozeRPG");
        File playerdataDir = new File("plugins/OozeRPG/Playerdata");

        if (!mainDir.exists()) {
            mainDir.mkdir();
        }

        if (!playerdataDir.exists()) {
            playerdataDir.mkdir();
        }
    }

    public void createPlayerFile(UUID uuid) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                playerFileCfg.set("level", 1);
                playerFileCfg.set("experience", 0.0);
                playerFileCfg.save(playerFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setPlayerRace(UUID uuid, String race) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        try {
            playerFileCfg.set("race", race.toUpperCase());
            playerFileCfg.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerLevel(UUID uuid, int level) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        try {
            playerFileCfg.set("level", level);
            playerFileCfg.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerExperience(UUID uuid, double experience) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        try {
            playerFileCfg.set("experience", experience);
            playerFileCfg.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}