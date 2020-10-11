package dev.fadest.nick.command;

import dev.fadest.nick.Nick;
import dev.fadest.nick.utils.ColorUtils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCommand implements CommandExecutor {

    private final Nick plugin;

    public NickCommand(Nick plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can execute this command");
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("nick.change")) {
            player.sendMessage(ChatColor.RED + "You don't have enough permissions to use this command!");
            return true;
        }

        if(args.length == 0) {
            player.sendMessage(ChatColor.RED + "Correct Usage:");
            player.sendMessage(ChatColor.GREEN + "/nick <nickname>");
            return true;
        }

        String nickName = args[0];
        if(nickName.equalsIgnoreCase(player.getName())) {
            if(!plugin.playerHasNickname(player)) {
                player.sendMessage(ChatColor.GREEN + "You don't have a nickname to remove");
            } else {
                player.sendMessage(ChatColor.GREEN + "Removed your nickname!");
                plugin.removeNickname(player);
            }
            return true;
        }

        plugin.changeNickname(player, nickName);
        player.sendMessage(ChatColor.GREEN + "Changed your Nickname to: " + ChatColor.RESET + ColorUtils.parseColor(nickName));
        return true;
    }
}
