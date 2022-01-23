package me.kuraky.shadowban.listeners;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import me.kuraky.shadowban.DataManager;
import me.kuraky.shadowban.Shadowban;

import java.util.ArrayList;

public class Bamboozler extends PacketAdapter {

    public Bamboozler() {
        super(Shadowban.instance, ListenerPriority.HIGHEST, getAllPlayPackets());
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if(DataManager.INSTANCE.isNasty(event.getPlayer())) {
            //todo
        }
    }

    @Override
    public void onPacketSending(PacketEvent event) {
        if(DataManager.INSTANCE.isNasty(event.getPlayer())) {
            //todo
        }
    }

    private static PacketType[] getAllPlayPackets() {
        ArrayList<PacketType> types = new ArrayList<>();
        types.addAll(PacketType.Play.Server.getInstance().values());
        types.addAll(PacketType.Play.Client.getInstance().values());

        return types.toArray(new PacketType[]{});
    }
}
