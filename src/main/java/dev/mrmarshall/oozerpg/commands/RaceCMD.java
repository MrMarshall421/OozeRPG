package dev.mrmarshall.oozerpg.commands;

import dev.mrmarshall.oozerpg.gui.RaceSelectionGUI;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RaceCMD implements CommandExecutor {

    //> Commmand: /race
    //> Permission: oozerpg.race

    RaceSelectionGUI raceSelectionGUI = new RaceSelectionGUI();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (p.hasPermission("oozerpg.race")) {
                if (args.length == 0) {
                    //> Open GUI
                    raceSelectionGUI.open(p);
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
