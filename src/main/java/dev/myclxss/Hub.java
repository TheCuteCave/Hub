package dev.myclxss;

import org.bukkit.plugin.java.JavaPlugin;

public class Hub extends JavaPlugin {

    @Override
    public void onEnable() {

        new API(this);

    }

    public void onDisable() {

    }

}
