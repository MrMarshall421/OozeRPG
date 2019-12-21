/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.events;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        OozeRPG.getInstance().getPlayerDataHandler().createPlayerFile(p.getUniqueId());
        OozeRPG.getInstance().getElfUtilitySkills().updateSleightTimer(p.getUniqueId());
        OozeRPG.getInstance().getDwarfUtilitySkills().updateMaxHealth(p.getUniqueId());
        OozeRPG.getInstance().getDwarfCombatSkills().updateFurious(p.getUniqueId());
        OozeRPG.getInstance().getRebirthsHandler().loadRebirthEffects(p);
    }
}
