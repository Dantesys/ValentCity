package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

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
    public void hitEvent(ProjectileHitEvent event){
        Projectile projectile = event.getEntity();
        if(projectile instanceof Trident tri){
            if(tri.getItemStack().isSimilar(Reliquias.tridente_modelo1)){
                Block block = event.getHitBlock();
                Entity entity = event.getHitEntity();
                Location l = projectile.getLocation();
                World w = projectile.getWorld();
                if(block != null){
                    w = block.getWorld();
                    l = block.getLocation();
                    block.setType(Material.SEA_LANTERN);
                }else if(entity != null){
                    w = entity.getWorld();
                    l = entity.getLocation();
                    if(entity instanceof Zombie zombie){
                        zombie.setShouldBurnInDay(false);
                        zombie.setConversionTime(0);
                        zombie.setHealth(100);
                        zombie.customName(Component.text("Zombie Ocean Lord"));
                        zombie.setCustomNameVisible(true);
                        zombie.registerAttribute(Attribute.GENERIC_SCALE);
                        Objects.requireNonNull(zombie.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(1.25);
                        zombie.setGlowing(true);
                        zombie.setVisualFire(true);
                    }else if(entity instanceof LivingEntity vivo){
                        vivo.setRemainingAir(0);
                    }
                }
                w.strikeLightning(l);
            }else if(tri.getItemStack().isSimilar(Reliquias.tridente_modelo2)){
                Entity entity = event.getHitEntity();
                if(entity != null){
                    if(entity instanceof LivingEntity vivo){
                        vivo.setRemainingAir(0);
                    }
                }
            }
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