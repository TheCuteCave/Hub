package dev.myclxss.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.myclxss.API;

public class ArenaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("usage arena command");
            return true;
        }
        if (args[0].equalsIgnoreCase("join")) {
            if (!player.hasPermission("hub.joinarena") || !player.hasPermission("hub.all")) {
                player.sendMessage(API.getInstance().getLang().getString("ERROR.NO-PERMISSION", true));
                return true;
            }
            if (API.getInstance().getLocations().getString("ARENA.WORLD") == null ||
                    API.getInstance().getLocations().getString("ARENA.X") == null ||
                    API.getInstance().getLocations().getString("ARENA.Y") == null ||
                    API.getInstance().getLocations().getString("ARENA.Z") == null ||
                    API.getInstance().getLocations().getString("ARENA.YAW") == null ||
                    API.getInstance().getLocations().getString("ARENA.PITCH") == null) {
                player.sendMessage(API.getInstance().getLang().getString("ERROR.ARENA-LOCATION", true));
                return true;
            }

            if (API.getInstance().getArenaUsers().contains(player.getUniqueId())) {
                API.getInstance().getArenaUsers().remove(player.getUniqueId());
                player.sendMessage(API.getInstance().getLang().getString("ARENA.LEAVE", true));

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

                // regresar items de hub
            } else {
                API.getInstance().getArenaUsers().add(player.getUniqueId());

                player.getInventory().clear();

                World world = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("ARENA.WORLD"));
                double x = API.getInstance().getLocations().getDouble("ARENA.X");
                double y = API.getInstance().getLocations().getDouble("ARENA.Y");
                double z = API.getInstance().getLocations().getDouble("ARENA.Z");
                float yaw = (float) API.getInstance().getLocations().getDouble("ARENA.YAW");
                float pitch = (float) API.getInstance().getLocations().getDouble("ARENA.PITCH");
                Location location = new Location(world, x, y, z, yaw, pitch);
                player.teleport(location);

                // equipar kit
                ItemStack swordKit = new ItemStack(Material.DIAMOND_SWORD, 1);
                ItemMeta swordKitMeta = swordKit.getItemMeta();
                swordKitMeta.setDisplayName("kit");
                swordKit.setItemMeta(swordKitMeta);

                ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
                ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
                ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
                ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);

                // Equipa al jugador con la armadura de diamante
                player.getInventory().setHelmet(helmet);
                player.getInventory().setChestplate(chestplate);
                player.getInventory().setLeggings(leggings);
                player.getInventory().setBoots(boots);

                player.getInventory().setItem(1, swordKit);

                player.sendMessage(API.getInstance().getLang().getString("ARENA.JOIN", true));
            }
        }
        if (args[0].equalsIgnoreCase("leave")) {
            if (!player.hasPermission("hub.leavearena") || !player.hasPermission("hub.all")) {
                player.sendMessage(API.getInstance().getLang().getString("ERROR.NO-PERMISSION", true));
                return true;
            }
            if (API.getInstance().getArenaUsers().contains(player.getUniqueId())) {
                API.getInstance().getArenaUsers().remove(player.getUniqueId());

                player.sendMessage(API.getInstance().getLang().getString("ARENA.LEAVE", true));

                if (API.getInstance().getLocations().getString("LOBBY.WORLD") == null ||
                        API.getInstance().getLocations().getString("LOBBY.X") == null ||
                        API.getInstance().getLocations().getString("LOBBY.Y") == null ||
                        API.getInstance().getLocations().getString("LOBBY.Z") == null ||
                        API.getInstance().getLocations().getString("LOBBY.YAW") == null ||
                        API.getInstance().getLocations().getString("LOBBY.PITCH") == null) {
                    player.sendMessage(API.getInstance().getLang().getString("ERROR.SPAWN-LOCATION", true));
                    return true;
                }

                World world = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("LOBBY.WORLD"));
                double x = API.getInstance().getLocations().getDouble("LOBBY.X");
                double y = API.getInstance().getLocations().getDouble("LOBBY.Y");
                double z = API.getInstance().getLocations().getDouble("LOBBY.Z");
                float yaw = (float) API.getInstance().getLocations().getDouble("LOBBY.YAW");
                float pitch = (float) API.getInstance().getLocations().getDouble("LOBBY.PITCH");
                Location location = new Location(world, x, y, z, yaw, pitch);
                player.teleport(location);
                player.sendMessage(API.getInstance().getLang().getString("SPAWN.COMMAND", true));

                // regresar items de hub

                return true;
            }
        }
        return false;
    }
}
