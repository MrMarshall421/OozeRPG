/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.commands;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.gui.RaceSelectionGUI;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class RaceCMD implements CommandExecutor {

    //> Commmand: /race
    //> Permission: oozerpg.race

    RaceSelectionGUI raceSelectionGUI = new RaceSelectionGUI();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

            if (p.hasPermission("oozerpg.race")) {
                if (args.length == 0) {
                    //> Open GUI
                    if (playerFileCfg.getString("race") == null) {
                        raceSelectionGUI.open(p);
                    } else {
                        p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cYou already joined the §e" + playerFileCfg.getString("race") + " §crace!");
                    }
                } else {
                    p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cDid you mean §7/race §c?");
                }
            } else {
                p.sendMessage(PluginMessage.noPermission);
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(PluginMessage.noConsole);
        }

        return false;
    }
}
