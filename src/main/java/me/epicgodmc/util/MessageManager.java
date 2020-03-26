package me.epicgodmc.util;

import me.epicgodmc.NinjaSneak;
import org.bukkit.ChatColor;


public class MessageManager
{

    private NinjaSneak plugin = NinjaSneak.getInstance();


    public String prefix = plugin.getConfig().getString("pluginPrefix");

    public String getMessageFromPath(String path)
    {
        return applyCC(plugin.getConfig().getString(path));
    }

    public String getMessage(String key)
    {
        return applyCC(prefix+plugin.getConfig().getString("messages."+key));
    }

    public String applyCC(String input)
    {
        return ChatColor.translateAlternateColorCodes('&', input);
    }


}
