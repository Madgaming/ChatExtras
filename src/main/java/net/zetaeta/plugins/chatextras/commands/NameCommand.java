package net.zetaeta.plugins.chatextras.commands;

import net.zetaeta.plugins.chatextras.ChatExtras;
import net.zetaeta.plugins.libraries.commands.Executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NameCommand implements Executor {

	ChatExtras plugin;
	public static int i;
	public NameCommand(ChatExtras plugin) {
		this.plugin = plugin;
	}
	
/*	@Override
	public boolean onCommand(CommandSender sndr, Command cmd, String cmdlabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("name")) {
			if(args.length == 0) return false;
			if(plugin.getServer().getPlayerExact(args[0]) == null || args.length == 1) {
				if(!(sndr instanceof Player)) {
					sndr.sendMessage("§aThis command can only be run by a player!");
					return true;
				} 
				if(!(sndr.hasPermission("chatextras.name"))) {
					sndr.sendMessage("§cYou do not have access to that command!");
					return true;
				}
				String argstot = "";
				for(String s : args) {
					argstot = argstot.concat(s).concat(" ");
				}
				plugin.names.put((Player) sndr, argstot);
				plugin.playersWithNameActive.add((Player) sndr);
				plugin.config.set("names." + sndr.getName(), argstot);
				plugin.saveConfig();
				sndr.sendMessage("§aPrefices changed to \"§f" + argstot + "§a\"!");
				return true;
			} else {
				Player target = plugin.getServer().getPlayerExact(args[0]);
				if(!sndr.hasPermission("chatextras.name.other")) {
					sndr.sendMessage("§cYou do not have access to that command!");
					return true;
				}
				String argstot = "";
				for(int i = 1; i <= args.length -1; i++) {
					argstot = argstot.concat(args[i]).concat(" ");
				}
				plugin.names.put(target, argstot);
				plugin.playersWithNameActive.add(target);
				plugin.config.set("names." + target.getName(), argstot);
				plugin.saveConfig();
				sndr.sendMessage("§aPrefices of \"§f" + target.getName() + "§a\" set to \"§f" + argstot + "§a\"!");
				return true;
			}
		} else if(cmd.getName().equalsIgnoreCase("unname")) {
			if(args.length == 0) {
				if(!(sndr instanceof Player)) {
					sndr.sendMessage("§aThis command can only be run by a player!");
					return true;
				}
				if(!(sndr.hasPermission("chatextras.unname"))) {
					sndr.sendMessage("§cYou do not have access to that command!");
					return true;
				} 
				if(!(plugin.playersWithNameActive.contains((Player) sndr))) {
					sndr.sendMessage("§aYou do not have a different name!");
					return true;
				}
				plugin.names.put((Player) sndr, "");
				plugin.playersWithNameActive.remove((Player) sndr);
				plugin.config.set("names." + sndr.getName(), "");
				plugin.saveConfig();
				sndr.sendMessage("§aPrefices reset!");
				return true;
			} else {
				if(plugin.getServer().getPlayer(args[0]) == null) {
					sndr.sendMessage("§aNot an online player!");
					return true;
				}
				Player target = plugin.getServer().getPlayer(args[0]);
				if(!sndr.hasPermission("chatextras.unname.other")) {
					sndr.sendMessage("§cYou do not have access to that command!");
					return true;
				} if(!(plugin.playersWithNameActive.contains(target))) {
					sndr.sendMessage("§aThis player does not have a different name!");
					return true;
				}
				plugin.names.put(target, "");
				plugin.playersWithNameActive.remove(target);
				plugin.config.set("names." + target.getName(), "");
				plugin.saveConfig();
				sndr.sendMessage("§aPrefices of \"§f" + plugin.getServer().getPlayer(args[0]).getName() + "§a\" reset!");
				return true;
			}
		}
		return false;
	}*/
	
	@net.zetaeta.plugins.libraries.commands.Command("name")
	public boolean nameCommand(CommandSender sndr, String[] args) {
		if(args.length == 0) return false;
		if(plugin.getServer().getPlayerExact(args[0]) == null || args.length == 1) {
			if(!(sndr instanceof Player)) {
				sndr.sendMessage("§aThis command can only be run by a player!");
				return true;
			} 
			if(!(sndr.hasPermission("chatextras.name"))) {
				sndr.sendMessage("§cYou do not have access to that command!");
				return true;
			}
			String argstot = "";
			for(String s : args) {
				argstot = argstot.concat(s).concat(" ");
			}
			plugin.names.put((Player) sndr, argstot);
			plugin.playersWithNameActive.add((Player) sndr);
			plugin.config.set("names." + sndr.getName(), argstot);
			plugin.saveConfig();
			sndr.sendMessage("§aPrefices changed to \"§f" + argstot + "§a\"!");
			return true;
		} else {
			Player target = plugin.getServer().getPlayerExact(args[0]);
			if(!sndr.hasPermission("chatextras.name.other")) {
				sndr.sendMessage("§cYou do not have access to that command!");
				return true;
			}
			String argstot = "";
			for(int i = 1; i <= args.length -1; i++) {
				argstot = argstot.concat(args[i]).concat(" ");
			}
			plugin.names.put(target, argstot);
			plugin.playersWithNameActive.add(target);
			plugin.config.set("names." + target.getName(), argstot);
			plugin.saveConfig();
			sndr.sendMessage("§aPrefices of \"§f" + target.getName() + "§a\" set to \"§f" + argstot + "§a\"!");
			return true;
		}
	}
	
	@net.zetaeta.plugins.libraries.commands.Command("unname")
	public boolean unnameCommand(CommandSender sndr, String[] args) {
		if(args.length == 0) {
			if(!(sndr instanceof Player)) {
				sndr.sendMessage("§aThis command can only be run by a player!");
				return true;
			}
			if(!(sndr.hasPermission("chatextras.unname"))) {
				sndr.sendMessage("§cYou do not have access to that command!");
				return true;
			} 
			if(!(plugin.playersWithNameActive.contains((Player) sndr))) {
				sndr.sendMessage("§aYou do not have a different name!");
				return true;
			}
			plugin.names.put((Player) sndr, "");
			plugin.playersWithNameActive.remove((Player) sndr);
			plugin.config.set("names." + sndr.getName(), "");
			plugin.saveConfig();
			sndr.sendMessage("§aPrefices reset!");
			return true;
		} else {
			if(plugin.getServer().getPlayer(args[0]) == null) {
				sndr.sendMessage("§aNot an online player!");
				return true;
			}
			Player target = plugin.getServer().getPlayer(args[0]);
			if(!sndr.hasPermission("chatextras.unname.other")) {
				sndr.sendMessage("§cYou do not have access to that command!");
				return true;
			} if(!(plugin.playersWithNameActive.contains(target))) {
				sndr.sendMessage("§aThis player does not have a different name!");
				return true;
			}
			plugin.names.put(target, "");
			plugin.playersWithNameActive.remove(target);
			plugin.config.set("names." + target.getName(), "");
			plugin.saveConfig();
			sndr.sendMessage("§aPrefices of \"§f" + plugin.getServer().getPlayer(args[0]).getName() + "§a\" reset!");
			return true;
		}
	}

}
