/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.data;

import dev.mrmarshall.oozerpg.OozeRPG;
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
                playerFileCfg.set("experience", 0);
                playerFileCfg.set("skillpoints", 0);
                playerFileCfg.set("rebirth", 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deletePlayerFile(UUID uuid) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        playerFile.delete();
    }

    public File getPlayerFile(UUID uuid) {
        return new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
    }

    public void setPlayerRace(UUID uuid, String race) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        playerFileCfg.set("race", race.toUpperCase());
        OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
    }

    public void setPlayerLevel(UUID uuid, int level) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        playerFileCfg.set("level", level);
        OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
    }

    public void setPlayerExperience(UUID uuid, int experience) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        playerFileCfg.set("experience", experience);
        OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
    }

    public void setPlayerSkillpoints(UUID uuid, int skillpoints) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        playerFileCfg.set("skillpoints", skillpoints);
        OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
    }

    public void setPlayerRebirth(UUID uuid, int rebirthLevel) {
        File playerFile = new File("plugins/OozeRPG/Playerdata/" + uuid.toString() + ".yml");
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        playerFileCfg.set("rebirth", rebirthLevel);
        OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
    }

    public void savePlayerFile(File file, FileConfiguration playerFileCfg) {
        try {
            playerFileCfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}