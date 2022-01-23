package me.kuraky.shadowban;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.io.*;
import java.util.HashSet;
import java.util.Optional;

public final class DataManager {

    public static final DataManager INSTANCE = new DataManager();
    private final HashSet<OfflinePlayer> nastyPlayers;

    private DataManager() {
        nastyPlayers = new HashSet<>();
    }

    public Optional<OfflinePlayer> getPlayerIfValid(String name) {
        return Optional.ofNullable(Bukkit.getOfflinePlayerIfCached(name));
    }

    public boolean togglePlayer(String name) {
        Optional<OfflinePlayer> optionalOfflinePlayer = getPlayerIfValid(name);
        if(optionalOfflinePlayer.isEmpty()) {
            return false;
        }
        OfflinePlayer player = optionalOfflinePlayer.get();

        File file = new File(player.getUniqueId() + ".txt");
        try {
            if(file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                writer.write("true");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isNasty(player)) {
            removeFromNastyPlayers(player);
        }
        else {
            addToNastyPlayers(player);
        }
        return true;
    }

    public boolean getPlayer(String name) {
        Optional<OfflinePlayer> optionalOfflinePlayer = getPlayerIfValid(name);
        if(optionalOfflinePlayer.isEmpty()) {
            return false;
        }
        return isNasty(optionalOfflinePlayer.get());
    }

    public void addToNastyPlayers(OfflinePlayer player) {
        nastyPlayers.add(player);
    }

    public void removeFromNastyPlayers(OfflinePlayer player) {
        nastyPlayers.remove(player);
    }

    public boolean isNasty(OfflinePlayer player) {
        return nastyPlayers.contains(player);
    }
}
