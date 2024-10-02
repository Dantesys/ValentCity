package me.dantesys.valentCity.events;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class PisanteEvent implements Listener {
    @EventHandler
    public void movimento(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location location = event.getFrom();
        ItemStack botas = player.getInventory().getBoots();
        if(botas != null && botas.isSimilar(Reliquias.pisante_md1)){
            Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,5,5,5);
            while(pressf.iterator().hasNext()){
                Entity lento = pressf.iterator().next();
                if(lento instanceof LivingEntity vivo){
                    if(vivo instanceof Player p2){
                        if(player==p2){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
                        }else{
                            vivo.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,20,1));
                        }
                    }else{
                        vivo.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,20,1));
                    }
                }
                pressf.remove(lento);
            }
        }else if(botas != null && botas.isSimilar(Reliquias.pisante_md2)){
            player.setAllowFlight(true);
            player.setFlying(true);
            if(player.isFlying()){
                World world = player.getWorld();
                world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE,location,1);
            }else{
                float queda = player.getFallDistance();
                if(queda>0){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,100,1));
                }
            }
        }else{
            player.setAllowFlight(false);
            player.setFlying(false);
        }
    }
}