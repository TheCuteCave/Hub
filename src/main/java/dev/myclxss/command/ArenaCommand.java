package dev.myclxss.command;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import dev.myclxss.API;
import dev.myclxss.components.Color;
import dev.myclxss.components.Items;
import dev.myclxss.components.TitleAPI;

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

                World world = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("ARENA.WORLD"));
                double x = API.getInstance().getLocations().getDouble("ARENA.X");
                double y = API.getInstance().getLocations().getDouble("ARENA.Y");
                double z = API.getInstance().getLocations().getDouble("ARENA.Z");
                float yaw = (float) API.getInstance().getLocations().getDouble("ARENA.YAW");
                float pitch = (float) API.getInstance().getLocations().getDouble("ARENA.PITCH");
                Location location = new Location(world, x, y, z, yaw, pitch);
                player.teleport(location);

                //Quitar los efectos de posion
                for (PotionEffect effect : player.getActivePotionEffects()) {
                    player.removePotionEffect(effect.getType());
                }

                // Equipacion del kit - Eliminar primero los items
                player.getInventory().clear();
                player.getInventory().setHelmet(null);
                player.getInventory().setChestplate(null);
                player.getInventory().setLeggings(null);
                player.getInventory().setBoots(null);

                // Equipacion del kit - Equipar los Items
                player.getInventory().setItem(0, Items.swordKit);
                player.getInventory().setItem(1, Items.rodKit);
                player.getInventory().setHelmet(Items.helmetKit);
                player.getInventory().setChestplate(Items.chestplateKit);
                player.getInventory().setLeggings(Items.legginsKit);
                player.getInventory().setBoots(Items.bootsKit);

                TitleAPI.sendTitle(player, 15, 40, 15, Color.set("&4&LARENA"), Color.set("&Centraste en combate"));

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

                player.getInventory().clear();
                player.getInventory().setHelmet(null);
                player.getInventory().setChestplate(null);
                player.getInventory().setLeggings(null);
                player.getInventory().setBoots(null);

                player.getInventory().setItem(1, Items.joinArenaItem);
                player.getInventory().setItem(2, Items.serverSelectorItem);
                
                TitleAPI.sendTitle(player, 15, 40, 15, Color.set("&4&LARENA"), Color.set("&Csaliste del combate"));


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
            }
        }
        return false;
    }
}
