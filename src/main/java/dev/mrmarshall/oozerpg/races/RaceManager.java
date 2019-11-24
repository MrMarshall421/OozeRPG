/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.races;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class RaceManager {

    public void selectRace(UUID uuid, String race) {
        OozeRPG.getInstance().getPlayerDataHandler().setPlayerRace(uuid, race);
        File playerFile = OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(uuid);
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(playerFile);

        switch (race) {
            case "HUMAN":
                //> Combat Skills
                playerFileCfg.set("skills.combat.damage1.level", "0/5");
                playerFileCfg.set("skills.combat.tank1.level", "0/5");
                playerFileCfg.set("skills.combat.lifesteal1.level", "0/5");
                playerFileCfg.set("skills.combat.training1.level", "0/5");
                playerFileCfg.set("skills.combat.tank2.level", "0/5");
                playerFileCfg.set("skills.combat.damage2.level", "0/5");
                playerFileCfg.set("skills.combat.greed1.level", "0/4");
                playerFileCfg.set("skills.combat.mastertank.level", "0/1");

                //> Movement Skills
                playerFileCfg.set("skills.movement.speed1.level", "0/5");
                playerFileCfg.set("skills.movement.doge1.level", "0/5");
                playerFileCfg.set("skills.movement.mystical1.level", "0/5");
                playerFileCfg.set("skills.movement.scamper1.level", "0/5");
                playerFileCfg.set("skills.movement.mystical2.level", "0/5");
                playerFileCfg.set("skills.movement.speed2.level", "0/5");
                break;
            case "ELF":
                //> Movement Skills
                playerFileCfg.set("skills.movement.speed1.level", "0/5");
                playerFileCfg.set("skills.movement.dodge1.level", "0/5");
                playerFileCfg.set("skills.movement.mystical1.level", "0/5");
                playerFileCfg.set("skills.movement.bowspeed.level", "0/5");
                playerFileCfg.set("skills.movement.scamper1.level", "0/5");
                playerFileCfg.set("skills.movement.mystical2.level", "0/5");
                playerFileCfg.set("skills.movement.speed2.level", "0/4");
                playerFileCfg.set("skills.movement.dodge2.level", "0/1");

                //> Utility Skills
                playerFileCfg.set("skills.utility.retaliate1.level", "0/5");
                playerFileCfg.set("skills.utility.sleight1.level", "0/1");
                playerFileCfg.set("skills.utility.anger1.level", "0/4");
                playerFileCfg.set("skills.utility.hatred1.level", "0/5");
                playerFileCfg.set("skills.utility.sleight2.level", "0/1");
                playerFileCfg.set("skills.utility.poisoned1.level", "0/4");
                playerFileCfg.set("skills.utility.antibody.level", "0/1");
                playerFileCfg.set("skills.utility.dreadshade1.level", "0/4");
                playerFileCfg.set("skills.utility.dreadsdemise.level", "0/1");
                playerFileCfg.set("skills.utility.hatred2.level", "0/3");
                playerFileCfg.set("skills.utility.sleight3.level", "0/1");
                break;
            case "DWARF":
                //> Utility Skills
                playerFileCfg.set("skills.utility.growingskin1.level", "0/1");
                playerFileCfg.set("skills.utility.fierceanger1.level", "0/5");
                playerFileCfg.set("skills.utility.firedhands.level", "0/1");
                playerFileCfg.set("skills.utility.magicaura1.level", "0/4");
                playerFileCfg.set("skills.utility.growingskin2.level", "0/1");
                playerFileCfg.set("skills.utility.cursedarmor1.level", "0/3");
                playerFileCfg.set("skills.utility.fierceanger2.level", "0/5");
                playerFileCfg.set("skills.utility.growingskin3.level", "0/1");
                playerFileCfg.set("skills.utility.darkdepths1.level", "0/4");
                playerFileCfg.set("skills.utility.dreadsdemise.level", "0/1");
                playerFileCfg.set("skills.utility.beefy1.level", "0/5");
                playerFileCfg.set("skills.utility.magicaura2.level", "0/3");
                playerFileCfg.set("skills.utility.unbreakable.level", "0/1");

                //> Combat Skills
                playerFileCfg.set("skills.combat.tank1.level", "0/5");
                playerFileCfg.set("skills.combat.damage1.level", "0/5");
                playerFileCfg.set("skills.combat.thickskin1.level", "0/5");
                playerFileCfg.set("skills.combat.tank2.level", "0/5");
                playerFileCfg.set("skills.combat.welltaught.level", "0/5");
                playerFileCfg.set("skills.combat.furious1.level", "0/3");
                break;
        }

        OozeRPG.getInstance().getPlayerDataHandler().savePlayerFile(playerFile, playerFileCfg);
    }
}
