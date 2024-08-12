package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TridenteEvent implements Listener {
    @EventHandler
    public void troca(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();
        ItemStack omao = event.getOffHandItem();
        if(omao.isSimilar(Reliquias.tridente_modelo2)){
            event.setOffHandItem(Reliquias.tridente_modelo1);
            player.getInventory().setItemInOffHand(Reliquias.tridente_modelo1);
            player.updateInventory();
        }else if(omao.isSimilar(Reliquias.tridente_modelo1)){
            event.setOffHandItem(Reliquias.tridente_modelo2);
            player.getInventory().setItemInOffHand(Reliquias.tridente_modelo2);
            player.updateInventory();
        }
    }
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.tridente_modelo1)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
            }else if(item != null && item.isSimilar(Reliquias.tridente_modelo2)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 2));
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
                if(item != null && item.isSimilar(Reliquias.tridente_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                }else if(item != null && item.isSimilar(Reliquias.tridente_modelo2)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 2));
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
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.tridente_modelo1)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.getWorld().strikeLightning(lepresa.getLocation());
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.tridente_modelo1) || atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.tridente_modelo2)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.setRemainingAir(0);
                }
            }
        }
    }
}