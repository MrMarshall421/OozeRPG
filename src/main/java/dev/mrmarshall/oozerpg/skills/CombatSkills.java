/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills;

public class CombatSkills {

    public double calculateDamage1(int level) {
        double percentagePerLevel = 1.5;
        return percentagePerLevel * (double) level;
    }

    public double calculateTank1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateLifesteal1(int level) {
        double percentagePerLevel = 1.5;
        return percentagePerLevel * (double) level;
    }

    public double calculateTraining1(int level) {
        double percentagePerLevel = 1.0;
        return percentagePerLevel * (double) level;
    }

    public double calculateTank2(int level) {
        double percentagePerLevel = 1.5;
        return percentagePerLevel * (double) level;
    }

    public double calculateDamage2(int level) {
        double percentagePerLevel = 1.5;
        return percentagePerLevel * (double) level;
    }

    public double calculateGreed1(int level) {
        double percentagePerLevel = 1.5;
        return percentagePerLevel * (double) level;
    }

    public double calculateMastertank(int level) {
        double percentagePerLevel = 8.0;
        return percentagePerLevel * (double) level;
    }
}
