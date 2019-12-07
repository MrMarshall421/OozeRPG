/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills.dwarf;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DwarfUtilitySkills {

    private List<UUID> fierceAngerCooldown;
    private List<UUID> darkdepthsCooldown;
    private List<UUID> beefyCooldown;
    private List<UUID> unbreakableCooldown;

    public DwarfUtilitySkills() {
        fierceAngerCooldown = new ArrayList<>();
        darkdepthsCooldown = new ArrayList<>();
        beefyCooldown = new ArrayList<>();
        unbreakableCooldown = new ArrayList<>();
    }

    public double calculateGrowingSkin1(int level) {
        double percentagePerLevel = 4.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateFierceAnger1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateMagicAura1(int level) {
        double percentagePerLevel = 0.6;
        return percentagePerLevel * (double) level;
    }

    public double calculateGrowingSkin2(int level) {
        double percentagePerLevel = 6.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateCursedArmor1(int level) {
        double percentagePerLevel = 1.2;
        return percentagePerLevel * (double) level;
    }

    public double calculateFierceAnger2(int level) {
        double percentagePerLevel = 1.2;
        return percentagePerLevel * (double) level;
    }

    public double calculateGrowingSkin3(int level) {
        double percentagePerLevel = 8.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateDarkDepths1(int level) {
        double percentagePerLevel = 3.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateDarkDepths1b(int level) {
        double percentagePerLevel = 2.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateBeefy1(int level) {
        double percentagePerLevel = 8.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateMagicAura2(int level) {
        double percentagePerLevel = 1.2;
        return percentagePerLevel * (double) level;
    }

    public double calculateUnbreakable(int level) {
        double percentagePerLevel = 30.0;
        return percentagePerLevel * (double) level;
    }

    public void updateMaxHealth(UUID uuid) {
        File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(uuid);
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);
        Player p = Bukkit.getPlayer(uuid);

        int growingSkin1Level = Integer.parseInt(playerFileCfg.getString("skills.utility.growingskin1.level").substring(0, 1));
        int growingSkin2Level = Integer.parseInt(playerFileCfg.getString("skills.utility.growingskin2.level").substring(0, 1));
        int growingSkin3Level = Integer.parseInt(playerFileCfg.getString("skills.utility.growingskin3.level").substring(0, 1));

        p.setMaxHealth(20.0);

        if (growingSkin1Level != 0) {
            p.setMaxHealth(p.getMaxHealth() + calculateGrowingSkin1(growingSkin1Level));
        }

        if (growingSkin2Level != 0) {
            p.setMaxHealth(p.getMaxHealth() + calculateGrowingSkin2(growingSkin2Level));
        }

        if (growingSkin3Level != 0) {
            p.setMaxHealth(p.getMaxHealth() + calculateGrowingSkin3(growingSkin3Level));
        }
    }

    public List<UUID> getFierceAngerCooldown() {
        return fierceAngerCooldown;
    }

    public List<UUID> getDarkdepthsCooldown() {
        return darkdepthsCooldown;
    }

    public List<UUID> getBeefyCooldown() {
        return beefyCooldown;
    }

    public List<UUID> getUnbreakableCooldown() {
        return unbreakableCooldown;
    }
}
