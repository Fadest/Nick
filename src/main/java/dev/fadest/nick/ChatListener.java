package dev.fadest.nick;

import dev.fadest.nick.utils.ColorUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private final Nick plugin;

    public ChatListener(Nick plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (plugin.playerHasNickname(player)) {
            String nickName = plugin.getPlayerNickname(player);
            event.setFormat(event.getFormat().replace("%1$s", ColorUtils.parseColor(nickName) + ChatColor.RESET));
        }
    }

}
