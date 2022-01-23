package me.kuraky.shadowban;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShadowbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender.hasPermission("shadowban.use")) {
            if(strings.length == 0) {
                commandSender.sendMessage("§cNot enough arguments");
            }
            else if(strings.length == 1) {
                String name = strings[0];
                if(DataManager.INSTANCE.togglePlayer(name)) {
                    if(DataManager.INSTANCE.getPlayer(name)) {
                        Bukkit.broadcast("§f" + commandSender.getName() + " §eshadowbanned §c" + name, "shadowban.use");
                    }
                    else {
                        Bukkit.broadcast("§f" + commandSender.getName() + " §eUn-shadowbanned §a" + name, "shadowban.use");
                    }
                }
                else {
                    commandSender.sendMessage("§cInvalid player name");
                }
            }
            else if(strings.length == 2) {
                if(strings[0].equals("info")) {
                    String name = strings[1];
                    if(DataManager.INSTANCE.getPlayerIfValid(name).isPresent()) {
                        if(DataManager.INSTANCE.getPlayer(name)) {
                            commandSender.sendMessage("§c" + name + " §eis shadowbanned");
                        }
                        else {
                            commandSender.sendMessage("§a" + name + " §eis not shadowbanned");
                        }
                    }
                    else {
                        commandSender.sendMessage("§cInvalid player name");
                    }
                }
                else {
                    commandSender.sendMessage("§cInvalid subcommand: §e" + strings[0]);
                }
            }
            else {
                commandSender.sendMessage("§cToo many arguments");
            }
        }
        else {
            commandSender.sendMessage("Unknown command. Type \"/help\" for help.");
        }
        return true;
    }
}
