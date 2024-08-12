package me.dantesys.valentCity.events;

import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class VentoEvent implements Listener {
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof WindCharge vento){
            if(vento.hasMetadata("vento")) {
                int voar = vento.getMetadata("vento").getFirst().asInt();
                if(event.getEntity() instanceof LivingEntity vivo){
                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,voar*20,voar/2));
                }
            }
        }
    }
    @EventHandler
    public void acertou(ProjectileHitEvent event){
        if(event.getEntity() instanceof Fireball){
            if(event.getEntity() instanceof WindCharge vento){
                int forca = vento.getMetadata("vento").getFirst().asInt();
                if(forca>0 && event.getHitBlock() != null){
                    vento.setYield(10);
                    vento.explode();
                }
            }
        }
        if(event.getEntity() instanceof WindCharge vento){
            int forca = vento.getMetadata("vento").getFirst().asInt();
            if(forca>0 && event.getHitBlock() != null){
                vento.setYield(50);
                vento.explode();
            }
        }
    }
    @EventHandler
    public void vcinteragiu(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(item != null && item.isSimilar(Reliquias.vento)){
            if(action.isRightClick()){
                Vector vec = player.getEyeLocation().getDirection();
                event.setCancelled(true);
                int slot = player.getInventory().getHeldItemSlot();
                player.getInventory().removeItem(Reliquias.vento);
                player.setCooldown(Reliquias.vento.getType(),0);
                player.getInventory().setItem(slot, Reliquias.vento);
                WindCharge wc = player.launchProjectile(WindCharge.class);
                wc.setYield(50);
                wc.setAcceleration(vec.multiply(2));
                wc.setDirection(vec.multiply(2));
                wc.setGlowing(true);
                wc.setMetadata("vento",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(double) 10));
            }
        }
    }
}