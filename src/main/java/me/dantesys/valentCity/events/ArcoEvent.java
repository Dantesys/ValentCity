package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

public class ArcoEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.arco_modelo1)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1));
            }else if(item != null && item.isSimilar(Reliquias.arco_modelo2)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.arco_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1));
                }else if(item != null && item.isSimilar(Reliquias.arco_modelo2)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, -1, 0));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void disparar(ProjectileLaunchEvent event) {
        ProjectileSource atirador = event.getEntity().getShooter();
        if (atirador instanceof Player player){
            if (player.getInventory().getItemInMainHand().isSimilar(Reliquias.arco_modelo1)){
                Arrow arrow = (Arrow) event.getEntity();
                arrow.setCritical(true);
                arrow.setGlowing(true);
                arrow.setColor(Color.YELLOW);
                Vector vec = player.getLocation().getDirection();
                arrow.setVelocity(vec.multiply(50));
            }
        }
    }
    @EventHandler
    public void disparo(EntityShootBowEvent event){
        if(event.getEntity() instanceof Player player){
            if (player.getInventory().getItemInMainHand().isSimilar(Reliquias.arco_modelo1)){
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setCritical(true);
                arrow.setGlowing(true);
                arrow.setColor(Color.YELLOW);
                Vector vec = player.getLocation().getDirection();
                arrow.setVelocity(vec.multiply(50));
            }
        }
    }
    @EventHandler
    public void minigun(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item != null && item.isSimilar(Reliquias.arco_modelo2)){
            event.setCancelled(true);
            player.launchProjectile(Arrow.class);
        }
    }
}