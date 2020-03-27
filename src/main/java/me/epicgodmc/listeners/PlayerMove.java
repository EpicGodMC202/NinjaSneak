package me.epicgodmc.listeners;

import me.epicgodmc.NinjaSneak;
import me.epicgodmc.SneakingPlayers;
import me.epicgodmc.util.VisibilityManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerMove implements Listener
{

    private NinjaSneak plugin = NinjaSneak.getInstance();

    @EventHandler
    public void move(PlayerMoveEvent e)
    {
        Location p2 = e.getTo();
        if (p2 != null)
        {
            if (hasMovedBlock(e.getFrom(), p2))
            {
                if (!e.getPlayer().isOnGround()) {
                    if (VisibilityManager.getInstance().isVanished(e.getPlayer().getUniqueId())) {
                        VisibilityManager.getInstance().setVanished(e.getPlayer(), false);
                        e.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
                        e.getPlayer().sendMessage(plugin.mm.getMessage("sneakStop"));

                        SneakingPlayers.getInstance().forceSetSneaking(e.getPlayer().getUniqueId());
                    }else if (plugin.getConfig().getBoolean("timer.resetTimerOnJump") && SneakingPlayers.getInstance().isSneaking(e.getPlayer().getUniqueId()))
                    {
                        SneakingPlayers.getInstance().forceSetSneaking(e.getPlayer().getUniqueId());
                    }
                }
            }
        }
    }

    public boolean hasMovedBlock(Location pos1, Location pos2)
    {
        return pos1.getBlockX() != pos2.getBlockX() || pos1.getBlockY() != pos2.getBlockY() || pos1.getBlockZ() != pos2.getBlockZ();
    }


}
