package dev.myclxss.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

import dev.myclxss.API;

public class QuitListener implements Listener{

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(null);

        if (API.getInstance().getArenaUsers().contains(player.getUniqueId())) {
            API.getInstance().getArenaUsers().remove(player.getUniqueId());
        }
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }
}
