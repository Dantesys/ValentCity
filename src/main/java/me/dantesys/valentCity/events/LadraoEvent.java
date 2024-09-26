package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.BundleMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
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
                if(item != null && item.isSimilar(Reliquias.ladrao)){
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
                            ItemStack[] items = fazol.getContents();
                            Random rd = new Random();
                            int ver = rd.nextInt(0, items.length);
                            bmeta.addItem(items[ver]);
                            ItemStack item = new ItemStack(Material.PAPER, 1);
                            ItemMeta meta = item.getItemMeta();
                            meta.displayName(Component.text("Você foi roubado"));
                            List<Component> loreitem = new ArrayList<>();
                            loreitem.add(items[ver].getItemMeta().displayName().append(Component.text("x"+items[ver].getAmount())));
                            meta.lore(loreitem);
                            meta.setRarity(ItemRarity.EPIC);
                            meta.setUnbreakable(true);
                            meta.setFireResistant(true);
                            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            item.setItemMeta(meta);
                            fazol.remove(items[ver]);
                            fazol.addItem(item);
                        }
                        roubado.updateInventory();
                        is.setItemMeta(bmeta);
                    }
                }
            }
        }
    }
    @EventHandler
    public void roubo(PlayerInteractEvent event){
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
                    i.remove(items[ver]);
                }
                ih.getInventory().setContents(i.getContents());
                is.setItemMeta(bmeta);
            }
        }
    }
}