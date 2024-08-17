package me.dantesys.valentCity.events;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class JoinQuitEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.getName().equals("HeriteHunter")){
            event.joinMessage(null);
            player.displayName(Component.text(""));
            player.playerListName(Component.text(""));
            player.sendPlayerListHeaderAndFooter(Component.text(""),Component.text(""));
        }else{
            event.joinMessage(Component.text(player.getName()+" apareceu no servidor!"));
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(player.getName().equals("HeriteHunter")){
            event.quitMessage(null);
        }else{
            event.quitMessage(Component.text(player.getName()+" sumiu!"));
        }
    }
}