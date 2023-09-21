package dev.myclxss.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.myclxss.API;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (API.getInstance().getLocations().getString("LOBBY.WORLD") == null ||
                API.getInstance().getLocations().getString("LOBBY.X") == null ||
                API.getInstance().getLocations().getString("LOBBY.Y") == null ||
                API.getInstance().getLocations().getString("LOBBY.Z") == null ||
                API.getInstance().getLocations().getString("LOBBY.YAW") == null ||
                API.getInstance().getLocations().getString("LOBBY.PITCH") == null) {
            player.sendMessage(API.getInstance().getLang().getString("ERROR.SPAWN-LOCATION", true));
            return true;
        }

        if (API.getInstance().getArenaUsers().contains(player.getUniqueId())) {
            API.getInstance().getArenaUsers().remove(player.getUniqueId());

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
        return false;
    }
}
