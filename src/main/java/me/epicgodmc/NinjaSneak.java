package me.epicgodmc;

import me.epicgodmc.listeners.PlayerJoin;
import me.epicgodmc.listeners.PlayerToggleSneak;
import me.epicgodmc.threads.TimedSneakChecker;
import me.epicgodmc.util.MessageManager;
import me.epicgodmc.util.VisibilityManager;
import org.bukkit.plugin.java.JavaPlugin;

//Main plugin class
public class NinjaSneak extends JavaPlugin
{

    private static NinjaSneak instance;
    public static NinjaSneak getInstance()
    {
        return instance;
    }

    public MessageManager mm;
    public Utils utils;


    //Executed when plugin enables
    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        registerInstances();

        this.getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerToggleSneak(), this);

        new TimedSneakChecker(this);
    }


    //Executed when plugin disables
    @Override
    public void onDisable() {
        instance = null;

    }

    //Registers instances i need
    private void registerInstances()
    {
        mm = new MessageManager();
        utils = new Utils();
        new VisibilityManager();
        new SneakingPlayers();

    }


}
