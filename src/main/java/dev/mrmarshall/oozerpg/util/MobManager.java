package dev.mrmarshall.oozerpg.util;

import org.bukkit.entity.*;

public class MobManager {

    public String getMobCategory(LivingEntity entity) {
        if (entity instanceof Animals) {
            return "animals";
        } else if (entity instanceof Monster) {
            return "monsters";
        } else if (entity instanceof Golem) {
            return "golems";
        } else if (entity instanceof Boss) {
            return "bosses";
        }

        return null;
    }
}
