package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.Random;


public class PescadorEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.pescador)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.pescador)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void pesca(PlayerFishEvent event) {
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.pescador)){
            event.setExpToDrop(event.getExpToDrop()*2);
            Random rd = new Random();
            int ver = rd.nextInt(0,100);
            Location l = player.getLocation();
            World w = player.getWorld();
            if(ver>=85 && ver<=90){
                w.dropItemNaturally(l,new ItemStack(Material.DIAMOND_BLOCK));
            }else if(ver > 90 && ver<=99){
                w.dropItemNaturally(l,new ItemStack(Material.ANCIENT_DEBRIS));
            }else if(ver==100){
                w.dropItemNaturally(l,new ItemStack(Material.NETHERITE_INGOT));
            }
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.pescador)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(ver<=25){
                    w.spawn(l, Cod.class);
                }else if(ver<=50){
                    w.spawn(l, Salmon.class);
                }else if(ver<=75){
                    w.spawn(l, PufferFish.class);
                }else if(ver<=99){
                    w.spawn(l, TropicalFish.class);
                }else{
                    w.spawn(l, Axolotl.class);
                }
            }
        }
    }
    @EventHandler
    public void lancapeixe(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.pescador)){
            if(action.isLeftClick() && !player.hasCooldown(Reliquias.pescador.getType())){
                int slot = -1;
                if(player.getInventory().contains(Material.COD)){
                    slot = player.getInventory().first(Material.COD);
                }else if(player.getInventory().contains(Material.SALMON)){
                    slot = player.getInventory().first(Material.SALMON);
                }else if(player.getInventory().contains(Material.PUFFERFISH)){
                    slot = player.getInventory().first(Material.PUFFERFISH);
                }else if(player.getInventory().contains(Material.TROPICAL_FISH)){
                    slot = player.getInventory().first(Material.TROPICAL_FISH);
                }
                if(slot>=0){
                    ItemStack item = player.getInventory().getItem(slot);
                    if(item!=null){
                        item.subtract(1);
                        player.getInventory().setItem(slot,item);
                        player.updateInventory();
                        int range = 50;
                        int damage = 5;
                        final int finalRange = range;
                        final int finalDamage = damage;
                        final Location location = player.getLocation();
                        final boolean[] passa = {true};
                        final Vector direction = location.getDirection().normalize();
                        final double[] tp = {0};
                        Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                                ()->{},()->{},
                                (t)->{
                            tp[0] = tp[0]+3.4;
                            double x = direction.getX()*tp[0];
                            double y = direction.getY()*tp[0]+1.4;
                            double z = direction.getZ()*tp[0];
                            location.add(x,y,z);
                            location.getWorld().spawnParticle(Particle.FISHING,location,1,0,0,0,0);
                            passa[0] = location.getBlock().isPassable();
                            location.getWorld().playSound(location, Sound.ENTITY_FISHING_BOBBER_SPLASH,0.5f,0.7f);
                            Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                            while(pressf.iterator().hasNext()){
                                Entity surdo = pressf.iterator().next();
                                if(surdo instanceof LivingEntity vivo){
                                    vivo.damage(finalDamage);
                                    Random rd = new Random();
                                    int ver = rd.nextInt(0,100);
                                    if(ver<=25){
                                        location.getWorld().spawn(location, Cod.class);
                                    }else if(ver<=50){
                                        location.getWorld().spawn(location, Salmon.class);
                                    }else if(ver<=75){
                                        location.getWorld().spawn(location, PufferFish.class);
                                    }else if(ver<=99){
                                        location.getWorld().spawn(location, TropicalFish.class);
                                    }else{
                                        location.getWorld().spawn(location, Axolotl.class);
                                    }
                                }
                                pressf.remove(surdo);
                            }
                            location.subtract(x,y,z);
                            if(t.getSegundosRestantes()>finalRange || !passa[0]){
                                t.stop();
                            }
                        });
                        timer.scheduleTimer(5L);
                        player.setCooldown(Reliquias.pescador.getType(),600);
                    }
                }
            }
        }
    }
}