package me.kuraky.shadowban.listeners;

import me.kuraky.shadowban.DataManager;
import me.kuraky.shadowban.Shadowban;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

@SuppressWarnings("all")
public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        File file = new File(Shadowban.instance.playerDataPath + event.getPlayer().getUniqueId() + ".txt");
        if(file.exists()) {
            DataManager.INSTANCE.addToNastyPlayers(event.getPlayer());
        }
    }
}
