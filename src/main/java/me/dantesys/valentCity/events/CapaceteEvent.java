package me.dantesys.valentCity.events;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
    @EventHandler
    public void terremoto(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        ItemStack calca = player.getInventory().getHelmet();
        if(calca != null && calca.isSimilar(Reliquias.capacete) && !player.hasCooldown(Reliquias.capacete.getType())){
            int range = 50;
            int damage = 5;
            final int finalRange = range;
            final int finalDamage = damage;
            final Location location = player.getLocation();
            final boolean[] passa = {true};
            final Vector direction = location.getDirection().normalize();
            final double[] tp = {0};
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                    ()->{
                    },()->{},(t)->{
                tp[0] = tp[0]+3.4;
                double x = direction.getX()*tp[0];
                double y = direction.getY()*tp[0]+1.4;
                double z = direction.getZ()*tp[0];
                location.add(x,y,z);
                location.getWorld().spawnParticle(Particle.ANGRY_VILLAGER,location,1,0,0,0,0);
                passa[0] = location.getBlock().isPassable();
                location.getWorld().playSound(location, Sound.BLOCK_AMETHYST_BLOCK_STEP,0.5f,0.7f);
                Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                while(pressf.iterator().hasNext()){
                    Entity surdo = pressf.iterator().next();
                    if(surdo instanceof LivingEntity vivo){
                        vivo.damage(finalDamage);
                        vivo.setFireTicks(vivo.getMaxFireTicks()*2);
                    }
                    pressf.remove(surdo);
                }
                location.subtract(x,y,z);
                if(t.getSegundosRestantes()>finalRange || !passa[0]){
                    t.stop();
                    location.getWorld().createExplosion(location,2,true,false);
                }
            });
            timer.scheduleTimer(5L);
            player.setCooldown(Reliquias.capacete.getType(),600);
        }
    }
}