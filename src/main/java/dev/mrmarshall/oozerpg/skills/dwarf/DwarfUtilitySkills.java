/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills.dwarf;

public class DwarfUtilitySkills {

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

}
