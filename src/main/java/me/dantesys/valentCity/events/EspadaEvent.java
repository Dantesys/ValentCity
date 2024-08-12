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

public class EspadaEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.espadamd)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.espadamd)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void matou(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            if(dead instanceof Monster) {
                ItemStack is = null;
                World w = dead.getWorld();
                Location l = dead.getLocation();
                if(dead.getType() == EntityType.WITHER){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd)){
                        is = Reliquias.power;
                        is.add(2);
                    }
                }else if(dead.getType() == EntityType.ELDER_GUARDIAN){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd)){
                        is = Reliquias.power;
                        is.add();
                    }
                }else if(dead.getType() == EntityType.ENDER_DRAGON){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd)){
                        is = Reliquias.power;
                        is.add(3);
                    }
                }else if(dead.getType() == EntityType.WARDEN){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd)){
                        is = Reliquias.power;
                        is.add(4);
                    }
                }else{
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd)){
                        is = Reliquias.power;
                    }
                }
                if(is!=null){
                    w.dropItemNaturally(l, is);
                }
            }
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.espadamd)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }
        }
    }
}