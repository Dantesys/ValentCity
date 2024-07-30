package me.dantesys.valentCity.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinquit implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.DARK_PURPLE + "Bem Vindo ao Valent City! :)");
    }
}
