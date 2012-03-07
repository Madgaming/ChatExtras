package net.zetaeta.plugins.chatextras.commands;

import net.zetaeta.plugins.chatextras.ChatExtras;
import net.zetaeta.plugins.libraries.commands.Executor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements Executor {
	
	public ChatExtras plugin;

	
	
	public ChatCommand(ChatExtras plugin) {
		this.plugin = plugin;
	}

/*	@Override
	public boolean onCommand(CommandSender sndr, Command cmd, String cmdlbl, String[] args) {
		if(cmd.getName().equalsIgnoreCase("chat")) {
			if(!(sndr instanceof Player)) {
				sndr.sendMessage("§aThis command can only be run by a player!");
				return true;
			}
			if(!(sndr.hasPermission("chatextras.chat"))) {
				sndr.sendMessage("§cYou do not have access to that command!");
				return true;
			}
			
			if(args.length == 0) {
				if(plugin.playersWithChatActive.contains((Player) sndr)) {
					sndr.sendMessage("§7You are no longer talking to §f " + plugin.playerChattingTo.get((Player) sndr).getDisplayName() + "§7.");
					plugin.config.set("chatpeople." + sndr.getName(), "");
					plugin.playersWithChatActive.remove((Player) sndr);
					
					return true;
				}
				sndr.sendMessage("§7You were not talking to anyone!");
				plugin.playersWithChatActive.remove((Player) sndr);
//				this.plugin.chatppl.put(sndr, new Player());
				return true;
			} else if(args.length == 1) {
				if(plugin.getServer().getPlayer(args[0]) != null) {
					Player target = plugin.getServer().getPlayer(args[0]);
					plugin.playersWithChatActive.add((Player) sndr);
					plugin.playerChattingTo.put((Player) sndr, target);
					plugin.config.set("chatpeople." + sndr.getName(), target.getName());
					plugin.saveConfig();
					sndr.sendMessage("§7You are now chatting with §f" + target.getDisplayName() + "§7. Use /chat to disable.");
					return true;
				} else {
					sndr.sendMessage("§aNot a valid player!");
					return true;
				}
			}
		}
		return false;
	}*/
	
	@net.zetaeta.plugins.libraries.commands.Command(value = "chat", permission = "chatextras.chat")
	public boolean chatCommand(CommandSender sndr, String[] args) {
		if(!(sndr instanceof Player)) {
			sndr.sendMessage("§aThis command can only be run by a player!");
			return true;
		}
		
		if(args.length == 0) {
			if(plugin.playersWithChatActive.contains((Player) sndr)) {
				sndr.sendMessage("§7You are no longer talking to §f " + plugin.playerChattingTo.get((Player) sndr).getDisplayName() + "§7.");
				plugin.config.set("chatpeople." + sndr.getName(), "");
				plugin.playersWithChatActive.remove((Player) sndr);
				
				return true;
			}
			sndr.sendMessage("§7You were not talking to anyone!");
			plugin.playersWithChatActive.remove((Player) sndr);
			return true;
		} else if(args.length == 1) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				Player target = plugin.getServer().getPlayer(args[0]);
				plugin.playersWithChatActive.add((Player) sndr);
				plugin.playerChattingTo.put((Player) sndr, target);
				plugin.config.set("chatpeople." + sndr.getName(), target.getName());
				plugin.saveConfig();
				sndr.sendMessage("§7You are now chatting with §f" + target.getDisplayName() + "§7. Use /chat to disable.");
				return true;
			} else {
				sndr.sendMessage("§aNot a valid player!");
				return true;
			}
		}
		return false;
	}

}
