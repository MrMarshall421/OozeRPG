/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.skills.elf;

public class ElfUtilitySkills {

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
}
