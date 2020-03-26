package me.epicgodmc.listeners;

import me.epicgodmc.NinjaSneak;
import me.epicgodmc.util.VisibilityManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerJoin implements Listener
{

    private NinjaSneak plugin = NinjaSneak.getInstance();
    private VisibilityManager visibilityManager = VisibilityManager.getInstance();

    @EventHandler
    public void playerJoin(PlayerJoinEvent e)
    {
        if (!visibilityManager.isEmpty())
        {
            for (Player targets : Bukkit.getOnlinePlayers())
            {
                for (UUID uuid : visibilityManager.getVanishedPlayers())
                {
                    Player player = Bukkit.getPlayer(uuid);
                    if (player != null)
                    {
                        targets.hidePlayer(plugin, player);
                    }
                }
            }

        }

    }


}
