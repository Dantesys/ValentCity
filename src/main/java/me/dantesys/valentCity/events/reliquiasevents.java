package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static org.bukkit.Bukkit.getServer;


public class reliquiasevents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Seja bem vindo");
    }
    @EventHandler
    public void mao(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        int slot2 = event.getPreviousSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        if(item == null){
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
            player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1);
        }else if(item.isSimilar(reliquias.espadamd)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
        }else if(item.isSimilar(reliquias.totem)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
        }else if(item.isSimilar(reliquias.enxada)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 1,true,false));
        }else if(item.isSimilar(reliquias.spy)){
            player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.01);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1,true,false));
        }else if(item.isSimilar(reliquias.tridente_modelo1) || item.isSimilar(reliquias.tridente_modelo2)){
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"Tridente "+item);
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
            for(int i = 0; i < 9; i++) {
                ItemStack itemver = player.getInventory().getItem(i);
                if(itemver==null){
                    i=9;
                }else if(itemver.isSimilar(reliquias.tridente_modelo1) || itemver.isSimilar(reliquias.tridente_modelo2)){
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"["+i%2+"] ");
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"Item Antes"+item);
                    if(i%2==0){
                        item = reliquias.tridente_modelo2;
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                    }else{
                        item = reliquias.tridente_modelo1;
                        player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 1));
                    }
                    getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"Item Depois"+item);
                    player.updateInventory();
                };
            }
        }else{
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
            player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1);
        }
        if(omao.isSimilar(reliquias.espadamd)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
        }else if(omao.isSimilar(reliquias.totem)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
        }else if(omao.isSimilar(reliquias.spy)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
        }else if(omao.isSimilar(reliquias.enxada)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
        }else if(omao.isSimilar(reliquias.tridente_modelo1)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
            player.getWorld().setStorm(true);
            player.getWorld().setWeatherDuration(12000);
            player.getWorld().setThundering(true);
            player.getWorld().setThunderDuration(12000);
        }else if(omao.isSimilar(reliquias.tridente_modelo2)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
            player.getWorld().setStorm(true);
            player.getWorld().setWeatherDuration(12000);
        }else{
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
            player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1);
        }
    }
    @EventHandler
    public void mudamao(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        ItemStack imao = player.getInventory().getItemInMainHand();
        ItemStack omao = player.getInventory().getItemInOffHand();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"Aqui o item que foi para a outra "+imao);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN +"Aqui o item que foi para a mão "+omao);
        if(imao == null || omao == null){
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
            player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1);
        }else if(imao.isSimilar(reliquias.espadamd)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
        }else if(imao.isSimilar(reliquias.totem)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
        }else if(imao.isSimilar(reliquias.spy)) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
        }else if(imao.isSimilar(reliquias.enxada)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
        }else if(imao.isSimilar(reliquias.tridente_modelo1)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
            player.getWorld().setStorm(true);
            player.getWorld().setWeatherDuration(12000);
            player.getWorld().setThundering(true);
            player.getWorld().setThunderDuration(12000);
        }else if(imao.isSimilar(reliquias.tridente_modelo2)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
            player.getWorld().setStorm(true);
            player.getWorld().setWeatherDuration(12000);
        }else{
            for (PotionEffect effect : player.getActivePotionEffects())
                player.removePotionEffect(effect.getType());
            player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1);
        }
    }
    @EventHandler
    public void getLife(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            if(dead instanceof Monster) {
                if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                    killer.setMaxHealth(killer.getMaxHealth() + 1.0);
                }
            }
        }
        if (dead instanceof Player) {
            Player f = (Player) dead;
            if(f.getInventory().first(reliquias.enxada) != 0){
                f.setMaxHealth(20.0);
            }
        }
    }
}
