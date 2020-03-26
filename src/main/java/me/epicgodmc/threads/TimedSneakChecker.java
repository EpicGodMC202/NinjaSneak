package me.epicgodmc.threads;

import me.epicgodmc.NinjaSneak;
import me.epicgodmc.SneakingPlayers;
import me.epicgodmc.util.VisibilityManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TimedSneakChecker {



    public TimedSneakChecker(NinjaSneak plugin) {
        int definedPeriod = plugin.getConfig().getInt("timer.updater");
        int actualPeriod = 20*definedPeriod;

        int sneak = plugin.getConfig().getInt("timer.sneak");

        HashMap<UUID, Long> sneakingPlayers = SneakingPlayers.getInstance().getSneakingPlayersMap();


        new BukkitRunnable() {
            @Override
            public void run() {
                for (UUID uuid : sneakingPlayers.keySet())
                {
                    long sneakStart = sneakingPlayers.get(uuid);
                    long sneakStartSeconds = TimeUnit.MILLISECONDS.toSeconds(sneakStart);
                    long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

                    if (sneakStartSeconds+sneak <= currentTime)
                    {
                        if (!VisibilityManager.getInstance().isVanished(uuid)) {
                            Player player = Bukkit.getPlayer(uuid);
                            if (player != null) {
                                player.sendMessage(plugin.mm.getMessage("sneakStart"));
                                VisibilityManager.getInstance().setVanished(player, true);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, actualPeriod);
    }

}
