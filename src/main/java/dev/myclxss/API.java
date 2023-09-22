package dev.myclxss;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import dev.myclxss.command.ArenaCommand;
import dev.myclxss.command.FirstCommand;
import dev.myclxss.command.SpawnCommand;
import dev.myclxss.components.Files;
import dev.myclxss.components.Items;
import dev.myclxss.listener.InteractListener;
import dev.myclxss.listener.JoinListener;
import dev.myclxss.listener.ProtectionListener;
import dev.myclxss.listener.QuitListener;

public class API {

    private final List<UUID> arenaUsers = new ArrayList<>();

    private static API instance;
    private final Hub main;

    private Files lang;
    private Files locations;

    public API(final Hub plugin) {

        instance = this;
        main = plugin;

        Items.init();

        lang = new Files(plugin, "lang");
        locations = new Files(plugin, "locations");

        loadListener();
        loadCommand();
    }

    public void loadListener() {

        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new JoinListener(), main);
        pluginManager.registerEvents(new InteractListener(), main);
        pluginManager.registerEvents(new ProtectionListener(), main);
        pluginManager.registerEvents(new QuitListener(), main);

    }

    public void loadCommand() {

        main.getCommand("hub").setExecutor(new FirstCommand());
        main.getCommand("spawn").setExecutor(new SpawnCommand());
        main.getCommand("arena").setExecutor(new ArenaCommand());

    }

    public void loadBungee() {

    }

    public Hub getMain() {
        return main;
    }

    public static API getInstance() {
        return instance;
    }

    public List<UUID> getArenaUsers() {
        return arenaUsers;
    }

    public Files getLang() {
        return lang;
    }

    public Files getLocations() {
        return locations;
    }
}
