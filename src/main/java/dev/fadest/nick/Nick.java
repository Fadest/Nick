package dev.fadest.nick;

import dev.fadest.nick.command.NickCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Nick extends JavaPlugin {

    private Map<UUID, String> nicknames;

    @Override
    public void onEnable() {
        this.nicknames = new HashMap<>();

        FileConfiguration configuration = getConfig();
        for (String key : configuration.getKeys(false)) {
            String nickName = configuration.getString(key);
            nicknames.put(UUID.fromString(key), nickName);
        }

        getCommand("nick").setExecutor(new NickCommand(this));
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
    }

    public void changeNickname(Player player, String nickname) {
        nicknames.put(player.getUniqueId(), nickname);
        getConfig().set(player.getUniqueId().toString(), nickname);
        saveConfig();
    }

    public void removeNickname(Player player) {
        nicknames.remove(player.getUniqueId());
        getConfig().set(player.getUniqueId().toString(), null);
        saveConfig();
    }

    public boolean playerHasNickname(Player player) {
        return nicknames.containsKey(player.getUniqueId());
    }

    public String getPlayerNickname(Player player) {
        return nicknames.get(player.getUniqueId());
    }


}
