package dev.myclxss.listener;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import dev.myclxss.API;
import dev.myclxss.components.Color;
import dev.myclxss.components.Items;

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

            // Entrega de items de arena despues de morir
            event.getPlayer().getInventory().setItem(0, Items.swordKit);
            event.getPlayer().getInventory().setItem(1, Items.rodKit);
            event.getPlayer().getInventory().setHelmet(Items.helmetKit);
            event.getPlayer().getInventory().setChestplate(Items.chestplateKit);
            event.getPlayer().getInventory().setLeggings(Items.legginsKit);
            event.getPlayer().getInventory().setBoots(Items.bootsKit);

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

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Item droppedItem = event.getItemDrop();
        droppedItem.remove(); // Esto elimina instantáneamente el objeto que el jugador ha soltado
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {

        Player player = event.getEntity();
        Player killer = player.getKiller();

        event.getDrops().clear(); // Borra todos los ítems que se iban a soltar al morir
        event.getEntity().getInventory().clear(); // Borra el inventario del jugador

        if (killer != null) {
            // Regenerar la salud del asesino al máximo
            killer.setHealth(killer.getMaxHealth());
        }
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onblockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onblockBreake(BlockBreakEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
        return;
    }

    @EventHandler
    public void bucketFill(PlayerBucketEmptyEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
        return;
    }

    @EventHandler
    public void bucketEmpty(PlayerBucketFillEvent event) {
        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
        return;
    }

    @EventHandler
    public void entityExplode(EntityExplodeEvent event) {
        event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getWhoClicked().getGameMode().equals(GameMode.CREATIVE))
            event.setCancelled(true);
        return;
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        if (event.toWeatherState()) {
            event.setCancelled(true);
        }
    }
}
