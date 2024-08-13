package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.Random;


public class PescadorEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.pescador)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.pescador)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void pesca(PlayerFishEvent event) {
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.pescador)){
            event.setExpToDrop(event.getExpToDrop()*2);
            Random rd = new Random();
            int ver = rd.nextInt(0,100);
            Location l = player.getLocation();
            World w = player.getWorld();
            if(ver>=85 &&ver<=90){
                w.dropItemNaturally(l,new ItemStack(Material.DIAMOND_BLOCK));
            }else if(ver<=99){
                w.dropItemNaturally(l,new ItemStack(Material.ANCIENT_DEBRIS));
            }else{
                w.dropItemNaturally(l,new ItemStack(Material.NETHERITE_INGOT));
            }
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.pescador)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(presa instanceof Player){
                    atacantepl.sendMessage("Você não pode pescar jogadores!");
                }else{
                    if(presa instanceof LivingEntity vivo){
                        double hp = Objects.requireNonNull(vivo.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        if(vivo.getHealth()>(hp/2)){
                            atacantepl.sendMessage("Você ainda não pode pesca!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (hp/2))+" de vida");
                            return;
                        }
                        vivo.remove();
                        atacantepl.sendMessage(presa.getName()+" agora é um peixe!");
                        if(ver<=25){
                            w.spawn(l, Cod.class);
                        }else if(ver<=50){
                            w.spawn(l, Salmon.class);
                        }else if(ver<=75){
                            w.spawn(l, PufferFish.class);
                        }else if(ver<=99){
                            w.spawn(l, TropicalFish.class);
                        }else{
                            w.spawn(l, Axolotl.class);
                        }
                    }
                }
            }
        }
    }
}