package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TotemEvent implements Listener {
    @EventHandler
    public void reviver(EntityResurrectEvent e) {
        LivingEntity deadEntity = e.getEntity();
        EntityEquipment equip = deadEntity.getEquipment();
        ItemStack hand = null;
        boolean main = true;
        if (equip != null && equip.getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
            hand = equip.getItemInMainHand();
            if (!hand.getEnchantments().containsKey(Enchantment.INFINITY)) {
                hand = null;
            }
        }
        if (equip != null && equip.getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
            hand = equip.getItemInOffHand();
            if (!hand.getEnchantments().containsKey(Enchantment.INFINITY)) {
                hand = null;
            }
            main = false;
        }
        if (hand != null) {
            boolean finalMain = main;
            ItemStack finalHand = Reliquias.totem;
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class),
                    5,
                    () -> {
                        deadEntity.sendMessage("Totem Ativado!");
                        deadEntity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100,5));
                    },
                    () -> {
                        if (finalMain) {
                            deadEntity.getEquipment().setItemInMainHand(finalHand);
                        } else {
                            deadEntity.getEquipment().setItemInOffHand(finalHand);
                        }
                    },
                    (t) -> deadEntity.sendMessage("Falta "+ (t.getSegundosRestantes()) + " Segundo para reativar")
            );
            timer.scheduleTimer(20L);
        }
    }
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.totem)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 2));
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
                if(item != null && item.isSimilar(Reliquias.totem)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.totem)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 5));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                }
            }
        }
    }
}