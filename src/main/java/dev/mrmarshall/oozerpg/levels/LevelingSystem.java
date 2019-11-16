package dev.mrmarshall.oozerpg.levels;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class LevelingSystem implements Listener {

    @EventHandler
    public void onEntityKill(EntityDeathEvent e) {
        LivingEntity killedEntity = e.getEntity();

        if (!(killedEntity instanceof Player)) {
            if (killedEntity.getKiller() instanceof Player) {
                Player p = killedEntity.getKiller();


            }
        }
    }
}
