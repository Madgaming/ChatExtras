package net.zetaeta.plugins.chatextras.commands;

import net.zetaeta.plugins.chatextras.ChatExtras;
import net.zetaeta.plugins.libraries.commands.Executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements Executor {
	
	ChatExtras plugin;
	
	public NickCommand(ChatExtras plugin) {
		this.plugin = plugin;
	}

/*	@Override
	public boolean onCommand(CommandSender sndr, Command cmd, String cmdlabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("nick")) {
			if(args.length == 1) {
				if(sndr instanceof Player) {
					if(sndr.hasPermission("chatextras.nick")) {
						((Player) sndr).setDisplayName(args[0]);
						plugin.config.set("nicks." +  sndr.getName(), args[0]);
						plugin.saveConfig();
						sndr.sendMessage("§aDisplay name changed to \"§f" + args[0] + "§a\"");
						return true;
					} else {
						sndr.sendMessage("§cYou do not have access to that command.");
						return true;
					}
				} else {
					sndr.sendMessage("§cThis command can only be run by a player.");
					return true;
				}
			} else if(args.length == 2) {
				if(sndr.hasPermission("chatextras.nick.other")) {
					if(plugin.getServer().getPlayer(args[0]) != null) {
						Player target = plugin.getServer().getPlayer(args[0]);
						target.setDisplayName(args[1]);
						plugin.config.set("nicks." + target.getName(), args[1]);
						plugin.saveConfig();
						sndr.sendMessage("§aDisplay name of \"§f" + target.getName() + "§a\" set to \"§f" + args[1] + "§a\"!");
						return true;
					} else {
						sndr.sendMessage("§aNot a valid player!");
						return true;
					}
				} else {
					sndr.sendMessage("§cYou do not have access to that command");
					return true;
				}
			} else return false;
		} else if(cmd.getName().equalsIgnoreCase("unnick")) {
			if(args.length == 0) {
				if(sndr instanceof Player) {
					if(sndr.hasPermission("chatextras.unnick")) {
						((Player) sndr).setDisplayName(sndr.getName());
						plugin.config.set("nicks." + sndr.getName(), ((Player) sndr).getName());
						plugin.saveConfig();
						sndr.sendMessage("§aDisplay name reset!");
						return true;
					} else {
						sndr.sendMessage("§cYou do not have access to that command.");
						return true;
					}
				} else {
					sndr.sendMessage("§cThis command can only be run by a player.");
					return true;
				}
			} else if(args.length == 1) {
				if(sndr.hasPermission("chatextras.unnick.other")) {
					if(plugin.getServer().getPlayer(args[0]) != null) {
						Player target = plugin.getServer().getPlayer(args[0]);
						target.setDisplayName(target.getName());
						plugin.config.set("nicks." + target.getName(), target.getName());
						plugin.saveConfig();
						sndr.sendMessage("§aDisplay name of \"§f" + plugin.getServer().getPlayer(args[0]).getName() + "§a\" reset!");
						return true;
					} else {
						sndr.sendMessage("§aNot a valid player!");
						return true;
					}
				} else {
					sndr.sendMessage("§cYou do not have access to that command!");
					return true;
				}
			} else return false;
		}
		return false;
	}*/
	
	@net.zetaeta.plugins.libraries.commands.Command("nick")
	public boolean nickCommand(CommandSender sndr, String[] args) {
		if(args.length == 1) {
			if(sndr instanceof Player) {
				if(sndr.hasPermission("chatextras.nick")) {
					((Player) sndr).setDisplayName(args[0]);
					plugin.config.set("nicks." +  sndr.getName(), args[0]);
					plugin.saveConfig();
					sndr.sendMessage("§aDisplay name changed to \"§f" + args[0] + "§a\"");
					return true;
				} else {
					sndr.sendMessage("§cYou do not have access to that command.");
					return true;
				}
			} else {
				sndr.sendMessage("§cThis command can only be run by a player.");
				return true;
			}
		} else if(args.length == 2) {
			if(sndr.hasPermission("chatextras.nick.other")) {
				if(plugin.getServer().getPlayer(args[0]) != null) {
					Player target = plugin.getServer().getPlayer(args[0]);
					target.setDisplayName(args[1]);
					plugin.config.set("nicks." + target.getName(), args[1]);
					plugin.saveConfig();
					sndr.sendMessage("§aDisplay name of \"§f" + target.getName() + "§a\" set to \"§f" + args[1] + "§a\"!");
					return true;
				} else {
					sndr.sendMessage("§aNot a valid player!");
					return true;
				}
			} else {
				sndr.sendMessage("§cYou do not have access to that command");
				return true;
			}
		} else return false;
	}
	
	@net.zetaeta.plugins.libraries.commands.Command("unnick")
	public boolean unnickCommand(CommandSender sndr, String[] args) {
		if(args.length == 0) {
			if(sndr instanceof Player) {
				if(sndr.hasPermission("chatextras.unnick")) {
					((Player) sndr).setDisplayName(sndr.getName());
					plugin.config.set("nicks." + sndr.getName(), ((Player) sndr).getName());
					plugin.saveConfig();
					sndr.sendMessage("§aDisplay name reset!");
					return true;
				} else {
					sndr.sendMessage("§cYou do not have access to that command.");
					return true;
				}
			} else {
				sndr.sendMessage("§cThis command can only be run by a player.");
				return true;
			}
		} else if(args.length == 1) {
			if(sndr.hasPermission("chatextras.unnick.other")) {
				if(plugin.getServer().getPlayer(args[0]) != null) {
					Player target = plugin.getServer().getPlayer(args[0]);
					target.setDisplayName(target.getName());
					plugin.config.set("nicks." + target.getName(), target.getName());
					plugin.saveConfig();
					sndr.sendMessage("§aDisplay name of \"§f" + plugin.getServer().getPlayer(args[0]).getName() + "§a\" reset!");
					return true;
				} else {
					sndr.sendMessage("§aNot a valid player!");
					return true;
				}
			} else {
				sndr.sendMessage("§cYou do not have access to that command!");
				return true;
			}
		} else return false;
	}

}
