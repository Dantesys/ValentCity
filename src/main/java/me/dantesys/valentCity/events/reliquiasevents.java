package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class reliquiasevents implements Listener {
    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().equals(reliquias.espada.getItemMeta())) {
                    Player player = event.getPlayer();
                    espadaActiveEvent(player);
                }
            }
        }
    }
    @EventHandler
    public void onHeldItem(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int item = event.getNewSlot();
        player.getInventory().getItem(item);
        if (player.getInventory().getItem(item) != null) {
            if (Objects.requireNonNull(player.getInventory().getItem(item)).getItemMeta().equals(reliquias.espada.getItemMeta())) {
                espadaPassiveEvent(player);
            }
        }
    }
    public void espadaActiveEvent(@NotNull Player player){
        Collection<PotionEffect> efeitos = List.of();
        efeitos.add(new PotionEffect(PotionEffectType.STRENGTH,-1,5));
        efeitos.add(new PotionEffect(PotionEffectType.SPEED,-1,1));
        efeitos.add(new PotionEffect(PotionEffectType.MINING_FATIGUE,-1,5));
        if(player.getActivePotionEffects().equals(efeitos)){
            for (int i = 0; i<efeitos.size();i++){
                player.removePotionEffect(PotionEffectType.STRENGTH);
                player.removePotionEffect(PotionEffectType.SPEED);
                player.removePotionEffect(PotionEffectType.MINING_FATIGUE);
            }
            player.sendMessage("§6Você desativou a fúria do guerreiro!");
        }else{
            player.addPotionEffects(efeitos);
            player.sendMessage("§6Você ativou a fúria do guerreiro!");
        }
    }
    public void espadaPassiveEvent(@NotNull Player player){
        Collection<PotionEffect> efeitos = List.of();
        efeitos.add(new PotionEffect(PotionEffectType.RESISTANCE,-1,1));
        if(player.getActivePotionEffects().equals(efeitos)){
            for (int i = 0; i<efeitos.size();i++){
                player.removePotionEffect(PotionEffectType.RESISTANCE);
            }
        }else{
            player.addPotionEffects(efeitos);
        }
    }
}
