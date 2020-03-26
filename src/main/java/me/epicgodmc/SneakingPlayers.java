package me.epicgodmc;

import java.util.HashMap;
import java.util.UUID;

public class SneakingPlayers
{

    private static SneakingPlayers instance;
    public static SneakingPlayers getInstance()
    {
        return instance;
    }

    public SneakingPlayers() {
        instance = this;
    }

    //Keeps all players currently sneaking in memory
    private HashMap<UUID, Long> sneakingPlayersMemory = new HashMap<>();

    public void setSneaking(UUID uuid, boolean bool)
    {
        if (bool)sneakingPlayersMemory.putIfAbsent(uuid, System.currentTimeMillis());
        else sneakingPlayersMemory.remove(uuid);
    }

    public HashMap<UUID, Long> getSneakingPlayersMap() {
        return sneakingPlayersMemory;
    }
}
