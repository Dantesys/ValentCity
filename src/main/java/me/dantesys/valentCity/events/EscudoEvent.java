package me.dantesys.valentCity.events;

import io.papermc.paper.event.player.PlayerShieldDisableEvent;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;

public class EscudoEvent implements Listener {
    @EventHandler
    public void movimento(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location location = event.getFrom();
        ItemStack escudo = player.getInventory().getItemInOffHand();
        if(escudo.isSimilar(Reliquias.escudo_md2)){
            Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,5,5,5);
            while(pressf.iterator().hasNext()){
                Entity lento = pressf.iterator().next();
                if(lento instanceof LivingEntity vivo){
                    if(vivo instanceof Player p2){
                        if(player!=p2){
                            vivo.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,20,10));
                        }
                    }else{
                        vivo.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,20,10));
                    }
                }
                pressf.remove(lento);
            }
        }
    }
    @EventHandler
    public void defender(PlayerShieldDisableEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInOffHand().isSimilar(Reliquias.escudo_md2)){
            event.setCooldown(0);
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void reversao(EntityDamageByEntityEvent event){
        Entity entity = event.getEntity();
        Entity entity2 = event.getDamager();
        if(entity instanceof LivingEntity vivo){
            if(vivo instanceof Player player){
                if(entity2 instanceof Projectile projetil && player.getInventory().getItemInOffHand().isSimilar(Reliquias.escudo_md1)){
                    event.setCancelled(true);
                    Vector vec = projetil.getVelocity();
                    vec.multiply(-1);
                    projetil.setVelocity(vec);
                    Projectile revers = player.launchProjectile(projetil.getClass());
                    projetil.remove();
                    revers.setVelocity(vec);
                }else if(player.getInventory().getItemInOffHand().isSimilar(Reliquias.escudo_md2) && !(entity2 instanceof Projectile)){
                    event.setCancelled(true);
                }
            }
        }
    }
}