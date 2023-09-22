package dev.myclxss.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.myclxss.API;
import dev.myclxss.components.Color;
import dev.myclxss.components.Items;
import dev.myclxss.components.TitleAPI;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (API.getInstance().getLocations().getConfigurationSection("LOBBY") == null) {
            player.sendMessage(API.getInstance().getLang().getString("ERROR.SPAWN-LOCATION", true));
            return true;
        }
        if (API.getInstance().getArenaUsers().contains(player.getUniqueId())) {
            API.getInstance().getArenaUsers().remove(player.getUniqueId());
            player.sendMessage(API.getInstance().getLang().getString("ARENA.LEAVE", true));

            player.getInventory().clear();
            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);

            player.getInventory().setItem(1, Items.joinArenaItem);
            player.getInventory().setItem(2, Items.serverSelectorItem);

            TitleAPI.sendTitle(player, 15, 40, 15, Color.set("&4&LARENA"), Color.set("&Csaliste del combate"));

            World world = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("LOBBY.WORLD"));
            double x = API.getInstance().getLocations().getDouble("LOBBY.X");
            double y = API.getInstance().getLocations().getDouble("LOBBY.Y");
            double z = API.getInstance().getLocations().getDouble("LOBBY.Z");
            float yaw = (float) API.getInstance().getLocations().getDouble("LOBBY.YAW");
            float pitch = (float) API.getInstance().getLocations().getDouble("LOBBY.PITCH");
            Location location = new Location(world, x, y, z, yaw, pitch);
            player.teleport(location);
            player.sendMessage(API.getInstance().getLang().getString("SPAWN.COMMAND", true));
            return true;

        } else {
            World world = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("LOBBY.WORLD"));
            double x = API.getInstance().getLocations().getDouble("LOBBY.X");
            double y = API.getInstance().getLocations().getDouble("LOBBY.Y");
            double z = API.getInstance().getLocations().getDouble("LOBBY.Z");
            float yaw = (float) API.getInstance().getLocations().getDouble("LOBBY.YAW");
            float pitch = (float) API.getInstance().getLocations().getDouble("LOBBY.PITCH");
            Location location = new Location(world, x, y, z, yaw, pitch);
            player.teleport(location);
            player.sendMessage(API.getInstance().getLang().getString("SPAWN.COMMAND", true));
            return true;
        }
    }
}
