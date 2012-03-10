package net.zetaeta.plugins.chatextras.commands;

import net.zetaeta.plugins.chatextras.ChatExtras;
import net.zetaeta.plugins.libraries.commands.Executor;

import static net.zetaeta.plugins.libraries.ZPUtil.*;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColourCommand implements Executor {
	
	public ChatExtras plugin;
	
	public ColourCommand(ChatExtras plugin) {
		this.plugin = plugin;
	}
	
/*	@Override
	public boolean onCommand(CommandSender sndr, Command cmd, String cmdlbl, String[] args) {
		if(cmd.getName().equalsIgnoreCase("colour")) {
			if(args.length == 1) {
				if(sndr instanceof Player) {
					if(args[0].matches("[0-9a-fA-Fk]")) {
						colourStandard(sndr, (Player) sndr, args[0], false);
						return true;
					}
					if(args[0].matches("random|rand|r")) {
						colourRandom(sndr, (Player) sndr, false);
						return true;
					}
					if(args[0].matches("reset|rs")) {
						colourReset(sndr, (Player) sndr, false);
						return true;
					}
					if(args[0].matches("rainbow|rb")) {
						colourRainbow(sndr, (Player) sndr, "", false);
						return true;
					}
					return false;
				} 
				sndr.sendMessage("This command can only be run by a player");
				return true;
			}
			if(args.length == 2) {
				if(plugin.getServer().getPlayer(args[0]) != null) {
					if(args[1].matches("[a-fA-F0-9k")) {
						colourStandard(sndr, plugin.getServer().getPlayer(args[0]), args[1], true);
						return true;
					}
					if(args[1].matches("random|rand|r")) {
						colourRandom(sndr, plugin.getServer().getPlayer(args[0]), true);
						return true;
					}
					if(args[1].matches("reset|rs")) {
						colourReset(sndr, plugin.getServer().getPlayer(args[0]), true);
						return true;
					}
					if(args[1].matches("rainbow|rb")) {
						colourRainbow(sndr, plugin.getServer().getPlayer(args[0]), "", true);
						return true;
					}
					return false;
				}
				else {
					sndr.sendMessage("§cNot a valid player!");
					return true;
				}
				
			}
			if(args.length == 3) {
				if(args[0].matches("rainbow|rb") && args[1].equalsIgnoreCase("-n")) {
					return colourRainbow(sndr, (Player) sndr, args[2], false);
				}
				return false;
			}
			if(args.length == 4) {
				if(plugin.getServer().getPlayer(args[0]) != null) {
					if(args[1].matches("rainbow|rb") && args[2].equalsIgnoreCase("-n")) {
						return colourRainbow(sndr, plugin.getServer().getPlayer(args[0]), args[3], true);
					}
				} else {
					sndr.sendMessage("§cNot a valid player!");
					return true;
				}
			}
		}
		return false;
	} */
	
	@net.zetaeta.plugins.libraries.commands.Command("colour")
	public boolean colourCommand(CommandSender sndr, String[] args) {
		if(args.length == 1) {
			if(sndr instanceof Player) {
				if(args[0].matches("[0-9a-fA-Fk]")) {
					colourStandard(sndr, (Player) sndr, args[0], false);
					return true;
				}
				if(args[0].matches("random|rand|r")) {
					colourRandom(sndr, (Player) sndr, false);
					return true;
				}
				if(args[0].matches("reset|rs")) {
					colourReset(sndr, (Player) sndr, false);
					return true;
				}
				if(args[0].matches("rainbow|rb")) {
					colourRainbow(sndr, (Player) sndr, "", false);
					return true;
				}
				return false;
			} 
			sndr.sendMessage("This command can only be run by a player");
			return true;
		}
		if(args.length == 2) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				if(args[1].matches("[a-fA-F0-9k")) {
					colourStandard(sndr, plugin.getServer().getPlayer(args[0]), args[1], true);
					return true;
				}
				if(args[1].matches("random|rand|r")) {
					colourRandom(sndr, plugin.getServer().getPlayer(args[0]), true);
					return true;
				}
				if(args[1].matches("reset|rs")) {
					colourReset(sndr, plugin.getServer().getPlayer(args[0]), true);
					return true;
				}
				if(args[1].matches("rainbow|rb")) {
					colourRainbow(sndr, plugin.getServer().getPlayer(args[0]), "", true);
					return true;
				}
				return false;
			}
			else {
				sndr.sendMessage("§cNot a valid player!");
				return true;
			}
			
		}
		if(args.length == 3) {
			if(args[0].matches("rainbow|rb") && args[1].equalsIgnoreCase("-n")) {
				return colourRainbow(sndr, (Player) sndr, args[2], false);
			}
			return false;
		}
		if(args.length == 4) {
			if(plugin.getServer().getPlayer(args[0]) != null) {
				if(args[1].matches("rainbow|rb") && args[2].equalsIgnoreCase("-n")) {
					return colourRainbow(sndr, plugin.getServer().getPlayer(args[0]), args[3], true);
				}
			} else {
				sndr.sendMessage("§cNot a valid player!");
				return true;
			}
		}
		return false;
	}
	
	public void colourStandard(CommandSender sndr, Player target, String arg, boolean targeted) {
		if(!arg.matches("[0-9A-Fa-fk]")) {
			sndr.sendMessage("§4UNEXPECTED ERROR OCCURRED");
		}
		if(!targeted) {
			if(checkPermission(sndr, "chatextras.colour.simple")) {
				saveColour(target, arg);
				sndr.sendMessage("§"+ arg + "Colour set!");
				return;
			}
		} else {
			if (checkPermission(sndr, "chatextras.colour.simple.other")) {
				saveColour(target, arg);
				sndr.sendMessage("§"+ arg + "Colour of " + target.getName() + " set!");
				return;
			}
		}
		
	}


	public void colourRandom(CommandSender sndr, Player target, boolean targeted) {
		if(!targeted) {
			if (checkPermission(sndr, "chatextras.colour.random")) {
				saveColour(target, "r");
				sndr.sendMessage("§aColour set to random!" );
				return;
			}
		} else {
			if (checkPermission(sndr, "chatextras.colour.random.other")) {
				saveColour(target, "r");
				sndr.sendMessage("§aColour of " + target.getName() + " set to random!" );
				return;
			}
		}
		
	}


	public void colourReset(CommandSender sndr, Player target, boolean targeted) {
		if(!targeted) {
			if (checkPermission(sndr, "chatextras.colour.reset")) {
				saveColour(target, "");
				sndr.sendMessage("§aColour reset!");
				return;
			}
		} else {
			if (checkPermission(sndr, "chatextras.colour.reset.other")) {
				saveColour(target, "");
				sndr.sendMessage("§aColour of " + target.getName() + " reset!");
				return;
			}
		}
	}


	public boolean colourRainbow(CommandSender sndr, Player target, String leaveOut, boolean targeted) {
		if(!targeted) {
			if(checkPermission(sndr, "chatextras.colour.rainbow")) {
				if(leaveOut.equals("")) {
					saveColour(target, "rb");
					sndr.sendMessage("§aColour set to rainbow!");
					return true;
				} else {
					if(leaveOut.matches("[a-fA-F0-9](,[a-fA-F0-9])*")) {
						saveColour(target, "rb>" + leaveOut);
						sndr.sendMessage("§aColour set to rainbow without colours " + leaveOut + "!");
						return true;
					} else {
						sndr.sendMessage("§cInvalid colour list!");
						return true;
					}
				}
			} else return true;
		} else {
			if(checkPermission(sndr, "chatextras.colour.rainbow.other")) {
				if(leaveOut.equals("")) {
					saveColour(target, "rb");
					sndr.sendMessage("§aColour of " + target.getName() + " set to rainbow!");
					return true;
				} else {
					if(leaveOut.matches("[a-fA-F0-9](,[a-fA-F0-9])*")) {
						saveColour(target, "rb>" + leaveOut);
						sndr.sendMessage("§aColour of " + target.getName() + " set to rainbow, without colours " + leaveOut + "!");
						return true;
					} else {
						sndr.sendMessage("§cInvalid colour list!");
						return true;
					}
				}
			} else return true;
		}
	}
	
	protected void saveColour(Player player, String s) {
		plugin.colours.put(player, s);
		plugin.config.set("colours." + player.getName(), s);
		plugin.saveConfig();
	}
}
