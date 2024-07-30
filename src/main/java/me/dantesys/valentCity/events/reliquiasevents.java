package me.dantesys.valentCity.events;

import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.reliquias;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class reliquiasevents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Seja bem vindo");
    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player) {
            Player atacantepl = (Player) atacante;
            if (atacantepl.getInventory().getItemInMainHand().equals(reliquias.espadamd.getItemMeta())) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 2));
                if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }
        }
    }
    @EventHandler
    public void getLife(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            if(dead instanceof Monster){
                if(killer.getInventory().getItemInMainHand().equals(reliquias.enxada)){
                    killer.setMaxHealth(killer.getMaxHealth()+1.0);
                }
            } else if (dead instanceof Player) {
                Player f = (Player) dead;
                if(f.getInventory().first(reliquias.enxada) != 0){
                    f.setMaxHealth(20.0);
                }
            }
        }
    }
    @EventHandler(ignoreCancelled = true)
    public void onTotem(EntityResurrectEvent e) {
        EquipmentSlot hand = EquipmentSlot.HAND;
        ItemStack totemItemStack = e.getEntity().getEquipment().getItem(hand); // Get the item in the main hand

        if (totemItemStack == null || totemItemStack.getType() != Material.TOTEM_OF_UNDYING) { // If it's not in the main hand,
            totemItemStack = e.getEntity().getEquipment().getItem(hand = EquipmentSlot.OFF_HAND); // Get it in the off hand
            if (totemItemStack == null || totemItemStack.getType() != Material.TOTEM_OF_UNDYING) {
                return; // Good to double check, just in case
            }
        }

        // You can operate on totemItemStack here now. Check if it's the one you want
        if(totemItemStack.equals(reliquias.totem)){
            ValentCity vc = new ValentCity();
            Bukkit.getScheduler().runTaskLater(vc, () -> {
                e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 100,5),true);
                e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100,5),true);
                e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 100,5),true);
                e.getEntity().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 20,5),true);
            },1);
            ItemStack finalTotemItemStack = totemItemStack;
            Bukkit.getScheduler().runTaskLater(vc, () -> {
                finalTotemItemStack.setAmount(finalTotemItemStack.getAmount() + 1);
            },100);
        }
    }
}
