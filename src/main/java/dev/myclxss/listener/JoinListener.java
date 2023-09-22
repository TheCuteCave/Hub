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

import dev.myclxss.API;
import dev.myclxss.components.Color;
import dev.myclxss.components.Items;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        event.setJoinMessage(null);

        player.setGameMode(GameMode.ADVENTURE);

        // Activar un sonido al jugador al ingresar al servidor
        if (API.getInstance().getSettings().getString("SOUND.ACTIVE").equals("true")) {
            player.playSound(player.getLocation(),
                    Sound.valueOf(API.getInstance().getSettings().getString("SOUND.TYPE")), 15, 10);
        }

        if (API.getInstance().getSettings().getString("WELCOME-MESSAGE.ACTIVE").equals("true")) {
            List<String> joinMessageString = API.getInstance().getLang().getStringList("WELCOME-MESSAGE");

            for (int i = 0; i < joinMessageString.size(); i++) {
                String joinMessage = joinMessageString.get(i);

                player.sendMessage(Color.set(joinMessage));
            }
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

        // Verificar si las cordenadas del Spawn estan colocadas, si no lo estan envia
        // un mensaje de error
        if (API.getInstance().getLocations().getString("LOBBY.WORLD") == null ||
                API.getInstance().getLocations().getString("LOBBY.X") == null ||
                API.getInstance().getLocations().getString("LOBBY.Y") == null ||
                API.getInstance().getLocations().getString("LOBBY.Z") == null ||
                API.getInstance().getLocations().getString("LOBBY.YAW") == null ||
                API.getInstance().getLocations().getString("LOBBY.PITCH") == null) {
            player.sendMessage(API.getInstance().getLang().getString("ERROR.SPAWN-LOCATION", true));
            return;
        }

        // Si las cordenadas existen el jugador es enviado al spawn o cordenadas
        // establecidas
        World world = Bukkit.getServer().getWorld(API.getInstance().getLocations().getString("LOBBY.WORLD"));
        double x = API.getInstance().getLocations().getDouble("LOBBY.X");
        double y = API.getInstance().getLocations().getDouble("LOBBY.Y");
        double z = API.getInstance().getLocations().getDouble("LOBBY.Z");
        float yaw = (float) API.getInstance().getLocations().getDouble("LOBBY.YAW");
        float pitch = (float) API.getInstance().getLocations().getDouble("LOBBY.PITCH");
        Location location = new Location(world, x, y, z, yaw, pitch);
        player.teleport(location);
        player.sendMessage(API.getInstance().getLang().getString("SPAWN.COMMAND", true));
        return;
    }
}
