package dev.mrmarshall.oozerpg.events;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        OozeRPG.getInstance().getPlayerDataHandler().createPlayerFile(e.getPlayer().getUniqueId());
    }
}
