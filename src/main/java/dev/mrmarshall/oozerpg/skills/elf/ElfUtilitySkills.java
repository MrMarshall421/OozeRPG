/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills.elf;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ElfUtilitySkills {

    private List<UUID> sleightTimer;
    private List<UUID> hatredCooldown;

    public ElfUtilitySkills() {
        sleightTimer = new ArrayList<>();
        hatredCooldown = new ArrayList<>();
    }

    public double calculateRetaliate1(int level) {
        double percentagePerLevel = 0.5;
        return percentagePerLevel * (double) level;
    }

    public double calculateAnger1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateHatred1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculatePoisoned1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateDreadshade1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateHatred2(int level) {
        double percentagePerLevel = 2.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateSleight(int level) {
        switch (level) {
            case 1:
                return 1.0;
            case 2:
                return 3.0;
            case 3:
                return 6.0;
            default:
                return 0.0;
        }
    }

    private int getPlayerSleightLevel(UUID uuid) {
        File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(uuid);
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        if (Integer.parseInt(playerFileCfg.getString("skills.utility.sleight3.level").substring(0, 1)) == 1) {
            //> Sleight Level 3
            return 3;
        } else if (Integer.parseInt(playerFileCfg.getString("skills.utility.sleight2.level").substring(0, 1)) == 1) {
            //> Sleight Level 2
            return 2;
        } else if (Integer.parseInt(playerFileCfg.getString("skills.utility.sleight1.level").substring(0, 1)) == 1) {
            //> Sleight Level 1
            return 1;
        }

        return 0;
    }

    public void updateSleightTimer(UUID uuid) {
        File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(uuid);
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        if (playerFileCfg.getString("race").equals("ELF")) {
            OozeRPG.getInstance().getElfUtilitySkills().getSleightTimer().remove(uuid);

            new BukkitRunnable() {
                @Override
                public void run() {
                    int sleightLevel = OozeRPG.getInstance().getElfUtilitySkills().getPlayerSleightLevel(uuid);
                    double sleightSkillBuff = OozeRPG.getInstance().getElfUtilitySkills().calculateSleight(sleightLevel);

                    if (sleightLevel != 0) {
                        OozeRPG.getInstance().getElfUtilitySkills().getSleightTimer().add(uuid);
                        OozeRPG.getInstance().getSchedulerManager().sleightTimer(uuid, sleightSkillBuff);
                    }
                }
            }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 21 * 2);
        }
    }

    public List<UUID> getSleightTimer() {
        return sleightTimer;
    }

    public List<UUID> getHatredCooldown() {
        return hatredCooldown;
    }
}
