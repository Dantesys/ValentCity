package me.dantesys.valentCity.events;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.Location;
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

public class CapaceteEvent implements Listener {
    @EventHandler
    public void colocou(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getNewItem();
        if(item.isSimilar(Reliquias.capacete) && event.getSlotType().equals(PlayerArmorChangeEvent.SlotType.HEAD)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,-1,1));
        }
    }
    @EventHandler
    public void movimento(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location location = event.getFrom();
        ItemStack cap = player.getInventory().getHelmet();
        if(cap != null && cap.isSimilar(Reliquias.capacete)){
            if(player.isSwimming()){
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,-1,1));
            }
            Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,5,5,5);
            while(pressf.iterator().hasNext()){
                Entity lento = pressf.iterator().next();
                if(lento instanceof LivingEntity vivo){
                    if(vivo instanceof Player p2){
                        if(player==p2){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,-1,1));
                            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,-1,1));
                        }
                    }else{
                        vivo.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,20,1));
                    }
                }
                pressf.remove(lento);
            }
        }
    }
}