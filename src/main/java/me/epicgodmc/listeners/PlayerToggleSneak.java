package me.epicgodmc.listeners;

import me.epicgodmc.NinjaSneak;
import me.epicgodmc.SneakingPlayers;
import me.epicgodmc.util.VisibilityManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.UUID;

public class PlayerToggleSneak implements Listener
{

    private NinjaSneak plugin = NinjaSneak.getInstance();
    private SneakingPlayers sneakingPlayers = SneakingPlayers.getInstance();

    @EventHandler
    public void onSneakToggle(PlayerToggleSneakEvent e)
    {
        UUID uuid = e.getPlayer().getUniqueId();
        sneakingPlayers.setSneaking(uuid, e.isSneaking());

        if (!e.isSneaking())
        {
            if (VisibilityManager.getInstance().isVanished(e.getPlayer().getUniqueId()))
            {
                VisibilityManager.getInstance().setVanished(e.getPlayer(), false);
                e.getPlayer().sendMessage(plugin.mm.getMessage("sneakStop"));
            }
        }
    }


}

