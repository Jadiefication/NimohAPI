package src.main.jadiefication.nimohAPI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.logging.LogRecord;

public final class NimohAPI extends JavaPlugin {


    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().enablePlugin(this);
        getLogger().info("NimohAPI has been enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getPluginManager().disablePlugin(this);
    }
}
