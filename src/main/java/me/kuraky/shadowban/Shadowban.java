package me.kuraky.shadowban;

import com.comphenix.protocol.ProtocolLibrary;
import me.kuraky.shadowban.listeners.Bamboozler;
import me.kuraky.shadowban.listeners.JoinListener;
import me.kuraky.shadowban.listeners.QuitListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@SuppressWarnings("all")
public class Shadowban extends JavaPlugin {

    public static Shadowban instance;
    public String playerDataPath;

    @Override
    public void onEnable() {
        instance = this;
        PluginCommand shadowban = getCommand("shadowban");
        if(shadowban != null) {
            shadowban.getAliases().add("sb");
            shadowban.setExecutor(new ShadowbanCommand());
        }
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        ProtocolLibrary.getProtocolManager().addPacketListener(new Bamboozler());
        getDataFolder().mkdir();
        playerDataPath = getDataFolder().getPath() + "/data";
        (new File(playerDataPath)).mkdir();
        playerDataPath += "/";
    }
}
