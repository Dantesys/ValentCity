package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class LadraoEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.ladrao)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
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
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.ladrao)) {
                ItemStack is = atacantepl.getInventory().getItemInMainHand();
                if (presa instanceof LivingEntity lepresa) {
                    if(lepresa instanceof Player roubado){
                        Inventory fazol = roubado.getInventory();
                        BundleMeta bmeta = (BundleMeta) is.getItemMeta();
                        while(bmeta.getItems().size()<is.getMaxStackSize()){
                            ItemStack[] items = roubado.getInventory().getContents();
                            Random rd = new Random();
                            int ver = rd.nextInt(0, items.length);
                            bmeta.addItem(items[ver]);
                        }
                        is.setItemMeta(bmeta);
                    }
                }
            }
        }
    }
    @EventHandler
    public void corte(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.ladrao)){
            ItemStack is = player.getInventory().getItemInMainHand();
            Block bloco = event.getClickedBlock();
            if(bloco.getState() instanceof InventoryHolder ih){
                Inventory i = ih.getInventory();
                BundleMeta bmeta = (BundleMeta) is.getItemMeta();
                while(bmeta.getItems().size()<is.getMaxStackSize()){
                    ItemStack[] items = i.getContents();
                    Random rd = new Random();
                    int ver = rd.nextInt(0, items.length);
                    bmeta.addItem(items[ver]);
                }
                is.setItemMeta(bmeta);
            }
        }
    }
}