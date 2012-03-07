package net.zetaeta.plugins.chatextras.commands;

import net.zetaeta.plugins.chatextras.ChatExtras;
import net.zetaeta.plugins.libraries.commands.Executor;

import static net.zetaeta.plugins.libraries.CEUtil.*;

import org.bukkit.command.CommandSender;

public class LineCommand implements Executor {
	
	ChatExtras plugin;
	
	public LineCommand(ChatExtras plugin) {
		this.plugin = plugin;
	}

/*	@Override
	public boolean onCommand(CommandSender sndr, Command cmd, String cmdlabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("line")) {
			if(sndr.hasPermission("chatextras.line")) {
				String line = "";
				for(String t : args) {
					line = line.concat(t + " ");
				}
				plugin.getServer().broadcastMessage(addBasicColour(line).replace("&k", "§k"));
				return true;
			} else {
				sndr.sendMessage("§cYou do not have access to that command!");
				return true;
			}
		}
		return false;
	}*/
	
	@net.zetaeta.plugins.libraries.commands.Command(value = "line", permission = "chatextras.line")
	public boolean lineCommand(CommandSender sndr, String[] args) {
		String line = "";
		for(String t : args) {
			line = line.concat(t + " ");
		}
		plugin.getServer().broadcastMessage(addBasicColour(line).replace("&k", "§k"));
		return true;
	}
}
