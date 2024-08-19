package me.dantesys.valentCity.events;

import me.dantesys.valentCity.ValentCity;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class JoinQuitEvent implements Listener {
    FileConfiguration config = ValentCity.getPlugin(ValentCity.class).getConfig();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.getName().equals(config.get("guardiao"))){
            player.displayName(Component.text("Guardião"));
            player.playerListName(Component.text("Guardião"));
            player.sendPlayerListHeaderAndFooter(Component.text("Guardião"),Component.text("Guardião"));
            event.joinMessage(Component.text("§2O Guardião despertou!"));
        }else{
            event.joinMessage(Component.text("§2"+player.getName()+" foi sumonado!"));
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(player.getName().equals(config.get("guardiao"))){
            event.quitMessage(Component.text("§4O Guardião descansou!"));
        }else{
            event.quitMessage(Component.text("§4"+player.getName()+" despawnou!"));
        }
    }
}