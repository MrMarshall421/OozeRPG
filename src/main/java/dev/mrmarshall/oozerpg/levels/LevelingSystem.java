package dev.mrmarshall.oozerpg.levels;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.IOException;
import java.util.UUID;

public class LevelingSystem implements Listener {

    @EventHandler
    public void onEntityKill(EntityDeathEvent e) {
        LivingEntity killedEntity = e.getEntity();

        if (!(killedEntity instanceof Player)) {
            if (killedEntity.getKiller() instanceof Player) {
                Player p = killedEntity.getKiller();

                FileConfiguration levelsCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getLevelingData().getLevelsFile());
                FileConfiguration mobsCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getLevelingData().getMobsFile());
                FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

                String mobCategory = OozeRPG.getInstance().getMobManager().getMobCategory(killedEntity);
                int exp = mobsCfg.getInt(mobCategory + "." + killedEntity.getName());

                int nextPlayerLevel = playerFileCfg.getInt("level") + 1;
                int expToNextLevel = levelsCfg.getInt(String.valueOf(nextPlayerLevel));

                if (exp > expToNextLevel) {
                    //> Player levels up
                    levelUp(p.getUniqueId());
                    p.sendMessage(PluginMessage.prefix + "§e§lLEVEL UP! §aYou are now Level §e" + nextPlayerLevel + "§a!");
                    p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1.0F, 1.0F);
                } else {
                    //> Player receives exp
                    playerFileCfg.set("experience", playerFileCfg.getInt("experience") + exp);

                    try {
                        playerFileCfg.save(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public void levelUp(UUID uuid) {
        FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(uuid));
        FileConfiguration levelsCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getLevelingData().getLevelsFile());
        playerFileCfg.set("level", playerFileCfg.getInt("level") + 1);
        playerFileCfg.set("experience", playerFileCfg.getInt("experience") - levelsCfg.getInt(String.valueOf(playerFileCfg.getInt("level"))));

        try {
            playerFileCfg.save(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(uuid));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
