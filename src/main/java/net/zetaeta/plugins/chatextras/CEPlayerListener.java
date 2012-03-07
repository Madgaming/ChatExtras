package net.zetaeta.plugins.chatextras;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import in.mDev.MiracleM4n.mChatSuite.mChatSuite;

import static net.zetaeta.plugins.libraries.CEUtil.*;


public class CEPlayerListener implements Listener {
	
	ChatExtras plugin;
	mChatSuite mChat;


	
	public CEPlayerListener(ChatExtras plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat(PlayerChatEvent evt) {
		loadDataFromConfig(evt.getPlayer());
		if(evt.isCancelled())
			return;
		if(this.plugin.playersWithChatActive.contains(evt.getPlayer())) {
			evt.getPlayer().sendMessage("§7(To " + plugin.playerChattingTo.get(evt.getPlayer()).getDisplayName() + "): §f" + evt.getMessage());
			plugin.playerChattingTo.get(evt.getPlayer()).sendMessage("§7(From " + evt.getPlayer().getDisplayName() + "): §f" + evt.getMessage());
			plugin.log.info("§7(" + evt.getPlayer().getName() + " -> "  + plugin.playerChattingTo.get(evt.getPlayer()).getName() + "): §f" + evt.getMessage());
			evt.setCancelled(true);
		} else if(plugin.playersWithNameActive.contains(evt.getPlayer())) {
			evt.setFormat(addBasicColour(plugin.names.get(evt.getPlayer()).replace("+dn", evt.getPlayer().getDisplayName()).replace("+n", evt.getPlayer().getName()).concat(evt.getMessage())));
			return;
		} else {
			evt.setFormat(plugin.mChat.getAPI().ParseChatMessage(evt.getPlayer().getName(), evt.getPlayer().getWorld().getName(), colourise(evt.getMessage(), evt.getPlayer())));
			return;
		}
			
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerLogin(PlayerLoginEvent evt) {
		loadDataFromConfig(evt.getPlayer());
	}

	public void loadDataFromConfig(Player player) {
		String name;
		String chattee;
		if(plugin.config.contains("nicks." + player.getName())) {
			player.setDisplayName(plugin.config.getString("nicks." + player.getName()));
		}
		if(plugin.config.contains("names." + player.getName())) {
			name = plugin.config.getString("names." + player.getName());
			if(!name.equalsIgnoreCase("")) {
				plugin.names.put(player, name);
				plugin.playersWithNameActive.add(player);
			} else {
				plugin.names.put(player, name);
				plugin.playersWithNameActive.remove(player);
			}
			
		}
		if(plugin.config.contains("colours." + player.getName())) {
			plugin.colours.put(player, plugin.config.getString("colours." + player.getName()));
		}
		
		if(plugin.config.contains("chatpeople." + player.getName())) {
			derp: {
				chattee = plugin.config.getString("chatpeople." + player.getName());
				if(chattee.equalsIgnoreCase("")) {
					if(plugin.playersWithChatActive.contains(player))
						plugin.playersWithChatActive.remove(player);
					break derp;
				}
				if(plugin.getServer().getOfflinePlayer(chattee) != null) {
					plugin.playerChattingTo.put(player, plugin.getServer().getOfflinePlayer(chattee).getPlayer());
					plugin.playersWithChatActive.add(player);
				}
			}
		}
	}
	
	public String colourise(String msg, Player plr) {
		String s;
		if(plugin.colours.get(plr) == null) {
			s = "";
		} else {
			s = plugin.colours.get(plr);
		}
		String clr = new String();
		String section = "§";
		Random r = new Random();
		
		if (s.equals("r")) {
			int c = r.nextInt() % 16;
			if(c < 0) {
				c *= -1;
			}
			switch (c) {
			case 0:
				clr = "0";
				break;
			case 1:
				clr = "1";
				break;
			case 2:
				clr = "2";
				break;
			case 3:
				clr = "3";
				break;
			case 4:
				clr = "4";
				break;
			case 5:
				clr = "5";
				break;
			case 6:
				clr = "6";
				break;
			case 7:
				clr = "7";
				break;
			case 8:
				clr = "8";
				break;
			case 9:
				clr = "9";
				break;
			case 10:
				clr = "a";
				break;
			case 11:
				clr = "b";
				break;
			case 12:
				clr = "c";
				break;
			case 13:
				clr = "d";
				break;
			case 14:
				clr = "e";
				break;
			case 15:
				clr = "f";
				break;
			}
			
		} else if (s.equals("")) {
			clr = "";
		} else if(s.matches("[a-fA-F0-9k]")) {
			clr = s;
		}
		
		if(s.equals("rb")) {
			clr = "";
			msg = makeRainbow(msg, "");
		}
		if(s.matches("rb>[a-fA-F0-9](,[a-fA-F0-9])*")) {
			clr = "";
			msg = makeRainbow(msg, s.substring(3));
		}
		
		if (!(clr.equals("") || clr.equals("rb"))) {
			msg = section.concat(clr).concat(msg);
		} 
		
		
		return addBasicColour(msg).replace("&k", plr.hasPermission("chatextras.k") ? "§k" : "");
	}
	
	public String makeRainbow(String msg, String leaveOut) {
		char[] cmsg = msg.toCharArray();
		StringBuilder outputMsg = new StringBuilder();
		char clr = 'f';
		for(char c : cmsg) {
			clr = getNextRainbowColour(clr, leaveOut);
			outputMsg.append('§').append(clr).append(c);
			
		}
		
		return outputMsg.toString();
	}


	public char getNextRainbowColour(char clr, String leaveOut) {
		String colours = "0123456789abcdef";
		do {
			int i = colours.indexOf(clr);
			if(clr == 'f') 
				i = -1;
			clr = colours.charAt(i + 1);
		} while(leaveOut.contains(String.valueOf(clr)));
		return clr;
	}
}
