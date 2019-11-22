/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.commands;

import dev.mrmarshall.oozerpg.OozeRPG;
import dev.mrmarshall.oozerpg.gui.SkillsGUI;
import dev.mrmarshall.oozerpg.util.PluginMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class SkillsCMD implements CommandExecutor {

    //> Commmand: /skills
    //> Permission: oozerpg.skills

    SkillsGUI skillsGUI = new SkillsGUI();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            FileConfiguration playerFileCfg = YamlConfiguration.loadConfiguration(OozeRPG.getInstance().getPlayerDataHandler().getPlayerFile(p.getUniqueId()));

            if (p.hasPermission("oozerpg.skills")) {
                if (args.length == 0) {
                    //> Open GUI
                    if (playerFileCfg.getString("race") != null) {
                        skillsGUI.open(p);
                    } else {
                        p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cPlease select a race with §7/race §cfirst!");
                    }
                } else {
                    p.sendMessage(PluginMessage.prefix + "§c§lERROR! §cDid you mean §7/skills §c?");
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
