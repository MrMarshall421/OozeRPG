/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills.dwarf;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DwarfCombatSkills {

    private List<UUID> thickskinCooldown;
    private List<UUID> welltaughtCooldown;

    public DwarfCombatSkills() {
        thickskinCooldown = new ArrayList<>();
        welltaughtCooldown = new ArrayList<>();
    }

    public double calculateTank1(int level) {
        double percentagePerLevel = 1.25;
        return percentagePerLevel * (double) level;
    }

    public double calculateDamage1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateThickSkin1(int level) {
        double percentagePerLevel = 2.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateTank2(int level) {
        double percentagePerLevel = 2.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateWelltaught(int level) {
        double percentagePerLevel = 2.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateFurious1(int level) {
        double percentagePerLevel = 5.0;
        return percentagePerLevel * (double) level;
    }

    public void updateFurious(UUID uuid) {
        File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(uuid);
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);
        Player p = Bukkit.getPlayer(uuid);
        int furiousLevel = Integer.parseInt(playerFileCfg.getString("skills.combat.furious1.level").substring(0, 1));

        if (furiousLevel != 0) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 999999999, furiousLevel));
        }
    }

    public List<UUID> getThickskinCooldown() {
        return thickskinCooldown;
    }

    public List<UUID> getWelltaughtCooldown() {
        return welltaughtCooldown;
    }
}
