package dev.myclxss.listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import dev.myclxss.API;
import dev.myclxss.components.Color;
import dev.myclxss.components.Items;
import dev.myclxss.components.TitleAPI;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);
        player.setFoodLevel(20);
        player.setHealth(20);
        player.getPlayer().setGameMode(GameMode.CREATIVE);
        player.playSound(player.getLocation(), Sound.valueOf(API.getInstance().getLang().getString("SOUND.TYPE")), 15,10);
        TitleAPI.sendTitle(player, 30, 80, 30, Color.set("&6Bienvenido"), Color.set("&eal servidor"));

        List<String> joinMessageString = API.getInstance().getLang().getStringList("WELCOME-MESSAGE");

        for (int i = 0; i < joinMessageString.size(); i++) {
            String joinMessage = joinMessageString.get(i);

            player.sendMessage(Color.set(joinMessage));
        }

        // Eliminacion de todo el inventario
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        
        // Colocar los Items
        player.getInventory().setItem(1, Items.joinArenaItem);
        player.getInventory().setItem(2, Items.serverSelectorItem);

        if (API.getInstance().getLocations().getConfigurationSection("LOBBY") == null) {
            player.sendMessage(API.getInstance().getLang().getString("ERROR.ARENA-LOCATION", true));
            return;
        }
        (new BukkitRunnable() {
            public void run() {
                World w = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("LOBBY.WORLD"));
                double x = API.getInstance().getLocations().getDouble("LOBBY.X");
                double y = API.getInstance().getLocations().getDouble("LOBBY.Y");
                double z = API.getInstance().getLocations().getDouble("LOBBY.Z");
                float yaw = (float) API.getInstance().getLocations().getDouble("LOBBY.YAW");
                float pitch = (float) API.getInstance().getLocations().getDouble("LOBBY.PITCH");
                Location loc = new Location(w, x, y, z, yaw, pitch);
                player.teleport(loc);
            }
        }).runTaskLater(API.getInstance().getMain(), 3L);
    }
}
