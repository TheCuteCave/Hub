package dev.myclxss.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import dev.myclxss.API;
import dev.myclxss.components.Board;

import java.util.List;

public class ScoreboardListener {

    public void createScoreboard(Player player) {
        Board helper = Board.createScore(player);
        String sbtitle = API.getInstance().getLang().getString("SCOREBOARD.TITLE");

        helper.setTitle(PlaceholderAPI.setPlaceholders(player, sbtitle));

        List<String> list = API.getInstance().getLang().getStringList("SCOREBOARD.LINES");
        int index = 15;
        for (int i = 0; i < list.size(); i++) {
            String lineText = list.get(i);
            helper.setSlot(index, PlaceholderAPI.setPlaceholders(player, lineText));
            index--;
        }
    }

    public void updateScoreboard(Player player) {
        if (Board.hasScore(player)) {
            Board helper = Board.getByPlayer(player);
            String sbtitle = API.getInstance().getLang().getString("SCOREBOARD.TITLE");
            helper.setTitle(PlaceholderAPI.setPlaceholders(player, sbtitle));

            List<String> list = API.getInstance().getLang().getStringList("SCOREBOARD.LINES");
            int index = 15;
            for (int i = 0; i < list.size(); i++) {
                String lineText = list.get(i);
                helper.setSlot(index, PlaceholderAPI.setPlaceholders(player, lineText));
                index--;
            }
        }
    }
}
