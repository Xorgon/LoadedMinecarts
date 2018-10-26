package me.xorgon.loadedminecarts;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class LoadedMinecartsPlugin extends JavaPlugin {

    private static me.xorgon.loadedminecarts.LoadedMinecartsPlugin instance;

    public LoadedMinecartsPlugin() {
        instance = this;
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new LMListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static me.xorgon.loadedminecarts.LoadedMinecartsPlugin getInstance() {
        return instance;
    }
}
