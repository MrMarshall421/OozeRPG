/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills.human;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HumanMovementSkills {

    private List<UUID> mysticalCooldown;

    public HumanMovementSkills() {
        mysticalCooldown = new ArrayList<>();
    }

    public double calculateSpeed1(int level) {
        double percentagePerLevel = 1.5;
        return percentagePerLevel * (double) level;
    }

    public double calculateDodge1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateMystical1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateScamper1(int level) {
        double percentagePerLevel = 2.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateMystical2(int level) {
        double percentagePerLevel = 1.5;
        return percentagePerLevel * (double) level;
    }

    public double calculateSpeed2(int level) {
        double percentagePerLevel = 2.0;
        return percentagePerLevel * (double) level;
    }

    public void setPlayerWalkingSpeed(Player p) {
        File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId());
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);
        p.setWalkSpeed(0.2F);
        float currentPlayerSpeed = p.getWalkSpeed();

        int speed1Level = Integer.parseInt(playerFileCfg.getString("skills.movement.speed1.level").substring(0, 1));
        float speed1SkillBuff = (float) ((currentPlayerSpeed / 100.0F) * calculateSpeed1(speed1Level));
        p.setWalkSpeed(p.getWalkSpeed() + speed1SkillBuff);

        int speed2Level = Integer.parseInt(playerFileCfg.getString("skills.movement.speed2.level").substring(0, 1));
        float speed2SkillBuff = (float) ((currentPlayerSpeed / 100.0F) * calculateSpeed2(speed2Level));
        p.setWalkSpeed(p.getWalkSpeed() + speed2SkillBuff);
    }

    public List<UUID> getMysticalCooldown() {
        return mysticalCooldown;
    }
}
