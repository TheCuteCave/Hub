package dev.myclxss.menu;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import dev.myclxss.API;
import dev.myclxss.components.Color;
import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class ServerselectorMenu implements Listener {

    public void createServerSelector(Player player) {
        Inventory inv = Bukkit.createInventory((InventoryHolder) null, 27, Color.set("Menu modalidades"));

        ItemStack hub01 = getSkull(
                "https://textures.minecraft.net/texture/c386b448cbe9f1d416dc57f9a30885089815c1f6718a6725b9215430c223ea9f",
                (PlaceholderAPI.setPlaceholders(player, "&f&l● &5&lPractice &7(&6%bungee_hub01%&7) &f&l●")));
        inv.setItem(11, hub01);

        ItemStack hub02 = getSkull(
                "https://textures.minecraft.net/texture/c386b448cbe9f1d416dc57f9a30885089815c1f6718a6725b9215430c223ea9f",
                (PlaceholderAPI.setPlaceholders(player, "&f&l● &5&lSkyWars&7(&6%bungee_hub02%&7) &f&l●")));
        inv.setItem(12, hub02);

        ItemStack hub03 = getSkull(
                "https://textures.minecraft.net/texture/c386b448cbe9f1d416dc57f9a30885089815c1f6718a6725b9215430c223ea9f",
                (PlaceholderAPI.setPlaceholders(player, "&f&l● &5&lFullpvp &7(&6%bungee_hub03%&7) &f&l●")));
        inv.setItem(13, hub03);

        ItemStack hub04 = getSkull(
                "https://textures.minecraft.net/texture/c386b448cbe9f1d416dc57f9a30885089815c1f6718a6725b9215430c223ea9f",
                (PlaceholderAPI.setPlaceholders(player, "&f&l● &5&lHUB 4 &7(&6%bungee_hub04%&7) &f&l●")));
        inv.setItem(14, hub04);

        ItemStack hub05 = getSkull(
                "https://textures.minecraft.net/texture/c386b448cbe9f1d416dc57f9a30885089815c1f6718a6725b9215430c223ea9f",
                (PlaceholderAPI.setPlaceholders(player, "&f&l● &5&lHUB 5 &7(&6%bungee_hub05%&7) &f&l●")));
        inv.setItem(15, hub05);

        ItemStack blackpanel1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(0, blackpanel1);
        ItemStack blackpanel2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(1, blackpanel2);
        ItemStack blackpanel3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(2, blackpanel3);
        ItemStack blackpanel4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(3, blackpanel4);
        ItemStack blackpanel5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(4, blackpanel5);
        ItemStack blackpanel6 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(5, blackpanel6);
        ItemStack blackpanel7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(6, blackpanel7);
        ItemStack blackpanel8 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(7, blackpanel8);

        ItemStack blackpanel9 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(8, blackpanel9);
        ItemStack blackpanel10 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(9, blackpanel10);
        ItemStack blackpanel16 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(10, blackpanel16);
        ItemStack blackpanel17 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(16, blackpanel17);

        ItemStack blackpanel18 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(17, blackpanel18);
        ItemStack blackpanel19 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(19, blackpanel19);
        ItemStack blackpanel20 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(18, blackpanel20);
        ItemStack blackpanel21 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(19, blackpanel21);

        ItemStack blackpanel23 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(20, blackpanel23);
        ItemStack blackpanel24 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(21, blackpanel24);
        ItemStack blackpanel25 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(22, blackpanel25);
        ItemStack blackpanel26 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(23, blackpanel26);
        ItemStack blackpanel27 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(24, blackpanel27);
        ItemStack blackpanel28 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(25, blackpanel28);
        ItemStack blackpanel29 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
        inv.setItem(26, blackpanel29);

        player.openInventory(inv);
    }

    @EventHandler
    public void ClickerInv(final InventoryClickEvent event) {
        final String name = Color.set("Menu modalidades");
        final String name2 = ChatColor.stripColor(name);
        if (ChatColor.stripColor(event.getView().getTitle()).equals(name2)) {
            if (event.getCurrentItem() == null || event.getSlotType() == null
                    || event.getCurrentItem().getType() == Material.AIR) {
                event.setCancelled(true);
                return;
            }
            if (!event.getCurrentItem().hasItemMeta()) {
                event.setCancelled(true);
                return;
            }
            final Player player = (Player) event.getWhoClicked();
            event.setCancelled(true);
            if (event.getSlot() == 11) {
                sendPlayerToServer(player, "practice");

                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 15, 10);
                return;
            }
            if (event.getSlot() == 12) {
                sendPlayerToServer(player, "skywars");

                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 15, 10);
                return;
            }
            if (event.getSlot() == 13) {
                sendPlayerToServer(player, "fullpvp");

                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 15, 10);
                return;
            }
            if (event.getSlot() == 14) {
                sendPlayerToServer(player, "hub04");

                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 15, 10);
                return;
            }
            if (event.getSlot() == 15) {
                sendPlayerToServer(player, "hub05");

                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 15, 10);
                return;
            }
        }
    }

    public static ItemStack getSkull(String textureUrl, String displayName) {
        ItemStack item = new ItemStack(397, 1, (short) 3);
        if (textureUrl.isEmpty())
            return item;

        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        itemMeta.setDisplayName(Color.set(displayName));
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder()
                .encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", textureUrl).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = itemMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(itemMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        item.setItemMeta(itemMeta);
        return item;
    }

    public void sendPlayerToServer(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(API.getInstance().getMain(), "BungeeCord", b.toByteArray());
    }
}
