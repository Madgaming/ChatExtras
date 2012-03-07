package net.zetaeta.plugins.chatextras;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import net.zetaeta.plugins.chatextras.commands.ChatCommand;
import net.zetaeta.plugins.chatextras.commands.ColourCommand;
import net.zetaeta.plugins.chatextras.commands.LineCommand;
import net.zetaeta.plugins.chatextras.commands.NameCommand;
import net.zetaeta.plugins.chatextras.commands.NickCommand;
import net.zetaeta.plugins.libraries.ZetaPlugin;
import net.zetaeta.plugins.libraries.commands.CommandHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;

import in.mDev.MiracleM4n.mChatSuite.mChatSuite;


public class ChatExtras extends ZetaPlugin {
	
	PluginManager pm;
	CEPlayerListener pl;
	public ColourCommand clrce;
	public LineCommand lnce;
	public NickCommand nce;
	public NameCommand nace;
	public Logger log;
	public Map<Player, String> colours = new HashMap<Player, String>();
	public Map<Player, String> names = new HashMap<Player, String>();
	public Set<Player> playersWithNameActive = new HashSet<Player>();
	public Set<Player> playersWithChatActive = new HashSet<Player>();
	public Map<Player, Player> playerChattingTo = new HashMap<Player, Player>();
	public mChatSuite mChat;
	public ChatCommand cc;
	public FileConfiguration config;
	public CommandHandler ch;
	
	
    public void onDisable() { 
        log.info(this + " is now disabled! D:");
    }

    public void onEnable() {
    	
    	log = getLogger();
    	try {
    	log.info("Loading begun!");
    	ch = new CommandHandler(this);
    	config = getConfig();
    	saveConfig();
    	pm = getServer().getPluginManager();
    	pl = new CEPlayerListener(this);
    	clrce = new ColourCommand(this);
    	lnce = new LineCommand(this);
    	nce = new NickCommand(this);
    	nace = new NameCommand(this);
    	mChat = (mChatSuite) getServer().getPluginManager().getPlugin("mChatSuite");
    	cc = new ChatCommand(this);

    	ch.registerCommandExecutor(cc);
    	ch.registerCommandExecutor(clrce);
    	ch.registerCommandExecutor(lnce);
    	ch.registerCommandExecutor(nce);
    	ch.registerCommandExecutor(nace);
    	
/*    	getCommand("colour").setExecutor(clrce);
    	getCommand("line").setExecutor(lnce);
    	getCommand("nick").setExecutor(nce);
    	getCommand("unnick").setExecutor(nce);
    	getCommand("name").setExecutor(nace);
    	getCommand("unname").setExecutor(nace);
    	getCommand("chat").setExecutor(cc);*/
    	pm.registerEvents(pl, this);
    	
        log.info(this + " is now enabled! :D");
    	} catch(Throwable ex) {
    		log.info("ERROR: " + ex.toString() + ex.getMessage());
    	}
    }
    
    public Logger getPluginLogger() {
    	return log;
    }
    
    public boolean onCommand(CommandSender sndr, Command cmd, String cmdlbl, String[] args) {
    	if(cmd.getName().equalsIgnoreCase("chatextras")) {
    		if(args.length != 1) return false;
    		if(args[0].equalsIgnoreCase("reload")) {
    			if(!sndr.hasPermission("chatextras.reload")) {
    				sndr.sendMessage("§cYou do not have access to that command!");
    				return true;
    			}
    			reloadConfig();
    			config = getConfig();
    			saveConfig();
    			sndr.sendMessage("§aConfig reloaded!");
    			return true;
    		} 
    		else if(args[0].equalsIgnoreCase("version")) {
    			sndr.sendMessage("§aChatExtras is running version " + getDescription().getVersion() + ".");
    			return true;
    		}
    		else if (args[0].equalsIgnoreCase("debug")) {
    			StringBuilder sb = new StringBuilder();
    			sb.append("Chat: ").append(playerChattingTo.get((Player) sndr).getName()).append(" is ").append((playersWithChatActive.contains((Player) sndr) ? "active " : "inactive "));
    			sb.append("; Name: ").append(names.get(sndr)).append(" is ").append((playersWithNameActive.contains(sndr) ? "active " : "inactive ")).append("; Nick: ");
    			sb.append(((Player) sndr).getDisplayName()).append("; Colour: ").append(colours.get(sndr) + ".");
    			
    			sndr.sendMessage(sb.toString());
    			
//    			sndr.sendMessage("Chat: " + playerChattingTo.get((Player) sndr).getName() + " is " + (playersWithChatActive.contains((Player) sndr) ? "active " : "inactive ") +
//    					"; Name: " + names.get(sndr) + " is " + (playersWithNameActive.contains(sndr) ? "active " : "inactive ") + "; Nick: " + ((Player) sndr).getDisplayName() +
//    							"; Colour: " + colours.get(sndr) + ".");
    			return true;
    		}
    	} 
    	else {
    		return ch.onCommand(sndr, cmd, cmdlbl, args);
    	}
    	return false;
    }
    
	

}
