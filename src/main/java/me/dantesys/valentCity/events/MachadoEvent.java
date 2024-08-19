package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;

public class MachadoEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.machado)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.machado)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, -1, 1));
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
        if(presa instanceof Player player) {
            if(player.getInventory().contains(Reliquias.machado)){
                double furia;
                if(player.hasMetadata("furia")){
                    furia = player.getMetadata("furia").getFirst().asDouble();
                    furia = furia+event.getDamage();
                    player.setMetadata("furia",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),furia));
                }else{
                    furia = event.getDamage();
                    player.setMetadata("furia",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),furia));
                }
                player.sendActionBar(Component.text("Fúria: "+furia));
            }
        }
    }
    @EventHandler
    public void corte(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.machado)){
            if(action.isLeftClick() && player.hasMetadata("furia")){
                player.sendActionBar(Component.text("Fúria: 0"));
                double rgdm = player.getMetadata("furia").getFirst().asDouble();
                if(rgdm>0){
                    player.setMetadata("furia",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),0));
                    final double finalRange = rgdm;
                    final double finalDamage = rgdm;
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
                        location.getWorld().spawnParticle(Particle.ANGRY_VILLAGER,location,1,0,0,0,0);
                        passa[0] = location.getBlock().isPassable();
                        location.getWorld().playSound(location, Sound.ENTITY_PLAYER_ATTACK_SWEEP,0.5f,0.7f);
                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                        while(pressf.iterator().hasNext()){
                            Entity surdo = pressf.iterator().next();
                            if(surdo instanceof LivingEntity vivo){
                                vivo.damage(finalDamage);
                            }
                            pressf.remove(surdo);
                        }
                        location.subtract(x,y,z);
                        if(t.getSegundosRestantes()>finalRange || !passa[0]){
                            t.stop();
                        }
                    });
                    timer.scheduleTimer(5L);
                }
            }
        }
    }
}