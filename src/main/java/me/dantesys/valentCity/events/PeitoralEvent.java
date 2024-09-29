package me.dantesys.valentCity.events;

import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Collection;

public class PeitoralEvent implements Listener {
    @EventHandler
    public void movimento(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location location = event.getFrom();
        ItemStack botas = player.getInventory().getChestplate();
        if(botas != null && botas.isSimilar(Reliquias.peitoral_md1)){
            Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,3,3,3);
            while(pressf.iterator().hasNext()){
                Entity lento = pressf.iterator().next();
                if(lento instanceof LivingEntity vivo){
                    if(vivo instanceof Player p2){
                        if(player!=p2){
                            if(p2.getHealth()>=2){
                                player.heal(1);
                            }
                        }
                    }else{
                        if(vivo.getHealth()>=2){
                            player.heal(1);
                        }
                    }
                }
                pressf.remove(lento);
            }
        }
    }
    @EventHandler
    public void nitro(PlayerElytraBoostEvent event) {
        Player player = event.getPlayer();
        ItemStack peitoral = player.getInventory().getChestplate();
        if(peitoral != null && peitoral.isSimilar(Reliquias.peitoral_md2)){
            event.setShouldConsume(false);
            Firework fw = event.getFirework();
            FireworkMeta fm = fw.getFireworkMeta();
            fm.setPower(5);
            fw.setFireworkMeta(fm);
        }
    }
}