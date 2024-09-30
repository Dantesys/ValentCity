package me.dantesys.valentCity.events;

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
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;

public class FenixEvent implements Listener {
    @EventHandler
    public void movimento(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location location = event.getFrom();
        Inventory iv = player.getInventory();
        if(iv.contains(Reliquias.fenix1)){
            World world = player.getWorld();
            world.spawnParticle(Particle.FLAME,location,1);
            player.setAllowFlight(true);
            player.setFlying(true);
            Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,5,5,5);
            while(pressf.iterator().hasNext()){
                Entity lento = pressf.iterator().next();
                if(lento instanceof LivingEntity vivo){
                    if(vivo instanceof Player p2){
                        if(player!=p2){
                            vivo.setFireTicks(vivo.getMaxFireTicks()*10);
                        }
                    }else{
                        vivo.setFireTicks(vivo.getMaxFireTicks()*10);
                    }
                }
                pressf.remove(lento);
            }
        }
    }
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.fenix1)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 4));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
            }else if(item != null && item.isSimilar(Reliquias.fenix2)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 4));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
            }else{
                if(omao.isSimilar(Reliquias.totem)){
                    ReliquiasEvent.limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException ignored){

        }try{
            if(omao.isSimilar(Reliquias.totem)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
            }else{
                if(item != null && item.isSimilar(Reliquias.espadamd1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 4));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                }else if(item != null && item.isSimilar(Reliquias.espadamd2)){
                    ReliquiasEvent.limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 4));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity presa = event.getEntity();
        if(presa instanceof Player atacantepl) {
            Inventory iv = atacantepl.getInventory();
            if(iv.contains(Reliquias.fenix1)){
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 600, 1));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));
            }
        }
    }
    @EventHandler
    public void corte(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.fenix2)){
            if(action.isLeftClick() && !player.hasCooldown(Reliquias.fenix2.getType())){
                final int finalRange = 50;
                final double finalDamage = 5;
                final Location location = player.getLocation();
                final boolean[] passa = {true};
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                    ()->{
                    },()-> {
                    },(t)->{
                        tp[0] = tp[0]+3.4;
                        double x = direction.getX()*tp[0];
                        double y = direction.getY()*tp[0]+1.4;
                        double z = direction.getZ()*tp[0];
                        location.add(x,y,z);
                        location.getWorld().spawnParticle(Particle.FLAME,location,1,0,0,0,0);
                        passa[0] = location.getBlock().isPassable();
                        location.getWorld().playSound(location, Sound.ENTITY_PLAYER_HURT_ON_FIRE,0.5f,0.7f);
                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                        while(pressf.iterator().hasNext()){
                            Entity surdo = pressf.iterator().next();
                            if(surdo instanceof LivingEntity vivo){
                                vivo.damage(finalDamage);
                                vivo.setFireTicks(vivo.getMaxFireTicks()*100);
                            }
                            pressf.remove(surdo);
                        }
                        location.subtract(x,y,z);
                        if(t.getSegundosRestantes()>finalRange || !passa[0]){
                            t.stop();
                        }
                });
                timer.scheduleTimer(5L);
                player.setCooldown(Reliquias.fenix2.getType(),600);
            }
        }
    }
}
