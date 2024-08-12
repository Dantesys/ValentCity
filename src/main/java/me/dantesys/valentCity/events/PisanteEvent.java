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
import java.util.Objects;

public class PisanteEvent implements Listener {
    @EventHandler
    public void colocou(PlayerArmorChangeEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getNewItem();
        if(item.isSimilar(Reliquias.pisante_md1) && event.getSlotType().equals(PlayerArmorChangeEvent.SlotType.FEET)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
        }else if(item.isSimilar(Reliquias.pisante_md2) && event.getSlotType().equals(PlayerArmorChangeEvent.SlotType.FEET)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
        }
    }
    @EventHandler
    public void movimento(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location location = event.getFrom();
        double y1 = location.getY();
        double y2 = event.getTo().getY();
        if(Objects.requireNonNull(player.getInventory().getBoots()).isSimilar(Reliquias.pisante_md1)){
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
        }else if(Objects.requireNonNull(player.getInventory().getBoots()).isSimilar(Reliquias.pisante_md2)){
            if(y1>y2 && player.isSneaking()){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,-1,1));
            }else if(player.isSneaking()){
                player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,-1,1));
            }else{
                player.removePotionEffect(PotionEffectType.SLOW_FALLING);
                player.removePotionEffect(PotionEffectType.LEVITATION);
            }
            Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,5,5,5);
            while(pressf.iterator().hasNext()){
                Entity lento = pressf.iterator().next();
                if(lento instanceof LivingEntity vivo){
                    if(vivo instanceof Player p2){
                        if(player==p2){
                            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
                        }else{
                            vivo.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,20,1));
                        }
                    }else{
                        vivo.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,20,1));
                    }
                }
                pressf.remove(lento);
            }
        }
    }
}