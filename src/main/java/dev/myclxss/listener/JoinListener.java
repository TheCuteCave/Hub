package dev.myclxss.listener;

import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dev.myclxss.API;
import dev.myclxss.components.Color;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();

        //Establecer efectos de posion al jugador al ingresar al servidor
        if (API.getInstance().getSettings().getString("POTION-EFFECT.ACTIVE").equals("true")) {
            applyEffects(player);
        }
        //Activar un sonido al jugador al ingresar al servidor
        if (API.getInstance().getSettings().getString("SOUND.ACTIVE").equals("true")) {
            player.playSound(player.getLocation(),Sound.valueOf(API.getInstance().getSettings().getString("SOUND.TYPE")), 15, 10);
        }

        if (API.getInstance().getSettings().getString("WELCOME-MESSAGE.ACTIVE").equals("true")) {
            List<String> joinMessageString = API.getInstance().getLang().getStringList("WELCOME-MESSAGE");

            for (int i = 0; i < joinMessageString.size(); i++) {
                String joinMessage = joinMessageString.get(i);

                player.sendMessage(Color.set(joinMessage));
            }
        }

        // arena item
        ItemStack arenaItem = new ItemStack(Material.getMaterial(API.getInstance().getLang().getString("ITEMS.ARENA.ITEM")), 1);
        ItemMeta arenaItemMeta = arenaItem.getItemMeta();
        arenaItemMeta.setDisplayName(API.getInstance().getLang().getString("ITEMS.ARENA.NAME", true));
        List<String> arenaItemLore = API.getInstance().getLang().getStringList("ITEMS.ARENA.LORE");
        arenaItemMeta.setLore(Color.set(arenaItemLore));
        arenaItem.setItemMeta(arenaItemMeta);
        player.getInventory().setItem(API.getInstance().getLang().getInt("ITEMS.ARENA.SLOT"), arenaItem);
        // server selector item
        ItemStack serverItem = new ItemStack(Material.getMaterial(API.getInstance().getLang().getString("ITEMS.SERVER-SELECTO.ITEM")), 1);
        ItemMeta serverItemMeta = serverItem.getItemMeta();
        serverItemMeta.setDisplayName(API.getInstance().getLang().getString("ITEMS.SERVER-SELECTO.NAME", true));
        List<String> serverItemLore = API.getInstance().getLang().getStringList("ITEMS.SERVER-SELECTO.LORE");
        serverItemMeta.setLore(Color.set(serverItemLore));
        serverItem.setItemMeta(serverItemMeta);
        player.getInventory().setItem(API.getInstance().getLang().getInt("ITEMS.SERVER-SELECTO.SLOT"), serverItem);

        //Verificar si las cordenadas del Spawn estan colocadas, si no lo estan envia un mensaje de error
        if (API.getInstance().getLocations().getString("LOBBY.WORLD") == null ||
                API.getInstance().getLocations().getString("LOBBY.X") == null ||
                API.getInstance().getLocations().getString("LOBBY.Y") == null ||
                API.getInstance().getLocations().getString("LOBBY.Z") == null ||
                API.getInstance().getLocations().getString("LOBBY.YAW") == null ||
                API.getInstance().getLocations().getString("LOBBY.PITCH") == null) {
            player.sendMessage(API.getInstance().getLang().getString("ERROR.SPAWN-LOCATION", true));
            return;
        }

       //Si las cordenadas existen el jugador es enviado al spawn o cordenadas establecidas
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

    public void applyEffects(Player player) {
        Set<String> effectKeys = API.getInstance().getSettings().getConfigurationSection("POTION-EFFECT.TYPES")
                .getKeys(false);

        for (String effectKey : effectKeys) {
            String effectType = API.getInstance().getSettings()
                    .getString("POTION-EFFECT.TYPES." + effectKey + ".EFFECT");
            int effectDuration = API.getInstance().getSettings()
                    .getInt("POTION-EFFECT.TYPES." + effectKey + ".DURATION");
            int effectAmplifier = API.getInstance().getSettings()
                    .getInt("POTION-EFFECT.TYPES." + effectKey + ".AMPLIFIER");

            PotionEffectType potionEffectType = PotionEffectType.getByName(effectType);

            if (potionEffectType != null) {
                PotionEffect potionEffect = new PotionEffect(potionEffectType, effectDuration * 20,
                        effectAmplifier - 1);
                player.addPotionEffect(potionEffect);
            } else {
                player.sendMessage(API.getInstance().getLang().getString("ERROR.POTION-EFFECT", true)
                        .replaceAll("<effect>", effectType));
            }
        }
    }
}
