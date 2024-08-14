package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class CalcaEvent implements Listener {
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
                                p2.damage(1);
                                player.heal(1);
                            }
                        }
                    }else{
                        if(vivo.getHealth()>=2){
                            vivo.damage(1);
                            player.heal(1);
                        }
                    }
                }
                pressf.remove(lento);
            }
        }
    }
    @EventHandler
    public void terremoto(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        ItemStack calca = player.getInventory().getLeggings();
        if(calca != null && calca.isSimilar(Reliquias.calca) && !player.hasCooldown(Reliquias.calca.getType())){
            int range = 20;
            final int finalDamage = 5;
            final Location location = player.getLocation();
            final double[] tp = {0};
            final World world = player.getWorld();
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 5,
                    ()->{
                    },()->{},(t)->{
                double area = range/(t.getSegundosRestantes()-tp[0]);
                for (double i = 0; i <= 2*Math.PI*area; i += 0.05) {
                    double x = (area * Math.cos(i)) + location.getX();
                    double z = (location.getZ() + area * Math.sin(i));
                    Location particle = new Location(world, x, location.getY() + 1, z);
                    world.spawnParticle(Particle.BLOCK,particle,5);
                }
                Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,area,0,area);
                while(pressf.iterator().hasNext()){
                    Entity surdo = pressf.iterator().next();
                    if(surdo instanceof LivingEntity vivo){
                        vivo.damage(finalDamage);
                    }
                    pressf.remove(surdo);
                }
                tp[0]=tp[0]+1;
            });
            timer.scheduleTimer(5L);
            player.setCooldown(Reliquias.calca.getType(),600);
        }
    }
}