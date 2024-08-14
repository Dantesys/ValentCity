package me.dantesys.valentCity.events;

import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class CrossbowEvent implements Listener {
    @EventHandler
    public void disparo(EntityShootBowEvent event){
        if(event.getEntity() instanceof Player player){
            if (player.getInventory().getItemInMainHand().isSimilar(Reliquias.crossbow)){
                Arrow arrow = (Arrow) event.getProjectile();
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                arrow.addCustomEffect(new PotionEffect(PotionEffectType.GLOWING,1000,2),true);
                if(ver>=50){
                    if (ver<=55){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS,200,2),true);
                    }else if(ver<=60){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,200,2),true);
                    }else if(ver<=65){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.DARKNESS,200,2),true);
                    }else if(ver<=70){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION,200,2),true);
                    }else if(ver<=75){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,200,2),true);
                    }else if(ver<=80){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.NAUSEA,200,2),true);
                    }else if(ver<=85){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER,200,2),true);
                    }else if(ver<=90){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.POISON,200,2),true);
                    }else if(ver<=95){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,2),true);
                    }else if(ver<=99){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.WITHER,200,2),true);
                    }else{
                        arrow.setMetadata("kaboom",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(float) 10));
                    }
                }
                arrow.setVelocity(player.getLocation().getDirection().multiply(5));
                event.setProjectile(arrow);
            }
        }
    }
    @EventHandler
    public void acertou(ProjectileHitEvent event){
        if(event.getEntity().getShooter() instanceof Player player) {
            if (player.getInventory().getItemInMainHand().isSimilar(Reliquias.crossbow)) {
                Arrow arrow = (Arrow) event.getEntity();
                Random rd = new Random();
                int ver = rd.nextInt(0, 100);
                if (ver >= 0 && ver <= 50) {
                    Location location;
                    if (event.getHitBlock() != null){
                        location = event.getHitBlock().getLocation();
                    }else if(event.getHitEntity() != null){
                        location = event.getHitEntity().getLocation();
                    }else{
                        location = event.getEntity().getLocation();
                    }
                    World world = arrow.getWorld();
                    world.spawn(location, Firework.class);
                    Firework fw = arrow.getWorld().spawn(arrow.getLocation(), Firework.class);
                    FireworkMeta fm = fw.getFireworkMeta();
                    fm.addEffect(FireworkEffect.builder()
                            .flicker(false)
                            .trail(false)
                            .with(FireworkEffect.Type.BALL)
                            .withColor(Color.RED)
                            .withFade(Color.BLACK)
                            .build());
                    fm.setPower(5);
                    fw.setFireworkMeta(fm);
                    fw.setTicksToDetonate(0);
                }else if (ver >= 75 && ver <= 99) {
                    Location location = event.getEntity().getLocation();
                    World world = arrow.getWorld();
                    world.spawn(location, Firework.class);
                    Firework fw = arrow.getWorld().spawn(arrow.getLocation(), Firework.class);
                    FireworkMeta fm = fw.getFireworkMeta();
                    fm.addEffect(FireworkEffect.builder()
                            .flicker(false)
                            .trail(false)
                            .with(FireworkEffect.Type.BALL_LARGE)
                            .withColor(Color.RED)
                            .withFade(Color.BLACK)
                            .build());
                    fm.setPower(10);
                    fw.setFireworkMeta(fm);
                    fw.setTicksToDetonate(0);
                }else{
                    Location location = event.getEntity().getLocation();
                    World world = arrow.getWorld();
                    world.createExplosion(location,10,false,false);
                }
            }
        }
    }
}