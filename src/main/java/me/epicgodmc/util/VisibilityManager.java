package me.epicgodmc.util;

import me.epicgodmc.NinjaSneak;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class VisibilityManager
{
    private NinjaSneak plugin = NinjaSneak.getInstance();

    private static VisibilityManager instance;
    public static VisibilityManager getInstance()
    {
        return instance;
    }

    public VisibilityManager()
    {
        instance = this;
    }



    private ArrayList<UUID> vanishedPlayers = new ArrayList<>();

    public boolean isVanished(UUID uuid)
    {
        return vanishedPlayers.contains(uuid);
    }

    public boolean isEmpty()
    {
        return vanishedPlayers.isEmpty();
    }

    public ArrayList<UUID> getVanishedPlayers()
    {
        return vanishedPlayers;
    }

    public void setVanished(Player player, boolean bool)
    {
        for (Player targets : Bukkit.getOnlinePlayers())
        {
            if (bool) {
                targets.hidePlayer(plugin, player);
            }
            else targets.showPlayer(plugin, player);
        }
        if (bool) vanishedPlayers.add(player.getUniqueId());
        else vanishedPlayers.remove(player.getUniqueId());
    }

}
