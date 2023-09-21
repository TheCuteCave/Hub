package dev.myclxss.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import dev.myclxss.API;
import dev.myclxss.components.Color;

public class ProtectionListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player target = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            if (!API.getInstance().getArenaUsers().contains(target.getUniqueId()) ||
                    !API.getInstance().getArenaUsers().contains(damager.getUniqueId())) {
                // Cancela el evento si ni el objetivo ni el agresor están en el conjunto
                // permitido
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent event) {
        UUID playerUUID = event.getPlayer().getUniqueId();

        // Verifica si el jugador está en la lista específica
        if (API.getInstance().getArenaUsers().contains(playerUUID)) {

            World world = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("ARENA.WORLD"));
            double x = API.getInstance().getLocations().getDouble("ARENA.X");
            double y = API.getInstance().getLocations().getDouble("ARENA.Y");
            double z = API.getInstance().getLocations().getDouble("ARENA.Z");
            float yaw = (float) API.getInstance().getLocations().getDouble("ARENA.YAW");
            float pitch = (float) API.getInstance().getLocations().getDouble("ARENA.PITCH");
            Location location = new Location(world, x, y, z, yaw, pitch);
            event.setRespawnLocation(location);
            event.getPlayer().sendMessage(Color.set("&aMoriste, pero fuiste enviado al spawn de arena"));

        } else {

            World world = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("LOBBY.WORLD"));
            double x = API.getInstance().getLocations().getDouble("LOBBY.X");
            double y = API.getInstance().getLocations().getDouble("LOBBY.Y");
            double z = API.getInstance().getLocations().getDouble("LOBBY.Z");
            float yaw = (float) API.getInstance().getLocations().getDouble("LOBBY.YAW");
            float pitch = (float) API.getInstance().getLocations().getDouble("LOBBY.PITCH");
            Location location = new Location(world, x, y, z, yaw, pitch);
            event.setRespawnLocation(location);
            event.getPlayer().sendMessage(Color.set("&cMoriste, pero fuiste enviado al spawn principal"));
        }
    }
}
