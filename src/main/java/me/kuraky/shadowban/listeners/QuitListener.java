package me.kuraky.shadowban.listeners;

import me.kuraky.shadowban.DataManager;
import me.kuraky.shadowban.Shadowban;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.*;

@SuppressWarnings("all")
public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) throws IOException {
        DataManager.INSTANCE.removeFromNastyPlayers(event.getPlayer());
        File file = new File(Shadowban.instance.getDataFolder().getPath() + "/data/" + event.getPlayer().getUniqueId() + ".txt");
        if(file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            if(!Boolean.getBoolean(reader.readLine())) {
                reader.close();
                file.delete();
                return;
            }
            reader.close();
        }
    }
}
