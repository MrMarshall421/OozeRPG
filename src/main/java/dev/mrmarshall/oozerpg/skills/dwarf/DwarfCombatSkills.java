/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills.dwarf;

public class DwarfCombatSkills {

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
}
