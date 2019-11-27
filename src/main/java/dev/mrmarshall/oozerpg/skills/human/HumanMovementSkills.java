/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills.human;

public class HumanMovementSkills {

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
}
