package dev.myclxss.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.Action;

import dev.myclxss.API;
import dev.myclxss.components.Color;
import dev.myclxss.menu.ServerselectorMenu;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // Arena Item
        if (event.getItem() != null && event.getItem().getItemMeta().getDisplayName() != null
                && event.getItem().getItemMeta().getDisplayName().equals(API.getInstance().getLang().getString("ITEMS.ARENA.NAME", true))) {
            Action action = event.getAction();
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                if (!player.hasPermission("hub.joinarena") || !player.hasPermission("hub.all")) {
                    player.sendMessage(API.getInstance().getLang().getString("ERROR.NO-PERMISSION", true));
                    return;
                }
                if (API.getInstance().getLocations().getConfigurationSection("ARENA") == null) {
                    player.sendMessage(API.getInstance().getLang().getString("ERROR.ARENA-LOCATION", true));
                    return;
                }

                if (API.getInstance().getArenaUsers().contains(player.getUniqueId())) {
                    API.getInstance().getArenaUsers().remove(player.getUniqueId());
                    player.sendMessage(API.getInstance().getLang().getString("ARENA.LEAVE", true));

                } else {

                    API.getInstance().getArenaUsers().add(player.getUniqueId());

                    player.getInventory().clear();

                    World world = Bukkit.getServer()
                            .getWorld(API.getInstance().getLocations().getString("LOBBY.WORLD"));
                    double x = API.getInstance().getLocations().getDouble("ARENA.X");
                    double y = API.getInstance().getLocations().getDouble("ARENA.Y");
                    double z = API.getInstance().getLocations().getDouble("ARENA.Z");
                    float yaw = (float) API.getInstance().getLocations().getDouble("ARENA.YAW");
                    float pitch = (float) API.getInstance().getLocations().getDouble("ARENA.PITCH");
                    Location location = new Location(world, x, y, z, yaw, pitch);
                    player.teleport(location);

                    // equipar kit

                    player.sendMessage(API.getInstance().getLang().getString("ARENA.JOIN", true));
                }

                return;
            }
        }
        if (event.getItem() != null && event.getItem().getItemMeta().getDisplayName() != null
                && event.getItem().getItemMeta().getDisplayName().equals(Color.set("&eServer Selector"))) {
            Action action = event.getAction();
            if (action == Action.RIGHT_CLICK_AIR || action == Action.LEFT_CLICK_AIR) {
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 15, 10);
                ServerselectorMenu inv = new ServerselectorMenu();
                inv.createServerSelector(player);
                return;
            }
        }
    }
}
