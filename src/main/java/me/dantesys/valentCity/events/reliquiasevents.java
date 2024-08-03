package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
    public void mao(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item == null && omao == null){
                limparEfeito(player);
            }else if(item.isSimilar(reliquias.espadamd)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
            }else if(item.isSimilar(reliquias.totem)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
            }else if(item.isSimilar(reliquias.enxada)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(item.isSimilar(reliquias.spy_modelo1)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.01);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(item.isSimilar(reliquias.spy_modelo2)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 1,true,false));
            }else if(item.isSimilar(reliquias.tridente_modelo1)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
            }else if(item.isSimilar(reliquias.tridente_modelo2)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 1));
            }else{
                if(omao.isSimilar(reliquias.espadamd)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
                }else if(omao.isSimilar(reliquias.totem)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                }else if(omao.isSimilar(reliquias.enxada)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
                }else if(omao.isSimilar(reliquias.spy_modelo1)){
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(omao.isSimilar(reliquias.spy_modelo2)){
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.75);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 1,true,false));
                }else if(omao.isSimilar(reliquias.tridente_modelo1) || omao.isSimilar(reliquias.tridente_modelo2)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                    if(omao.isSimilar(reliquias.tridente_modelo1)){
                        player.getWorld().setStorm(true);
                        player.getWorld().setWeatherDuration(12000);
                        player.getWorld().setThundering(true);
                        player.getWorld().setThunderDuration(12000);
                    }else if(omao.isSimilar(reliquias.tridente_modelo2)){
                        player.getWorld().setStorm(true);
                        player.getWorld().setWeatherDuration(12000);
                    }
                }else{
                    limparEfeito(player);
                }
            }
        } catch (NullPointerException e){

        }try{
            if(item == null && omao == null){
                limparEfeito(player);
            }else if(omao.isSimilar(reliquias.espadamd)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
            }else if(omao.isSimilar(reliquias.totem)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
            }else if(omao.isSimilar(reliquias.enxada)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
            }else if(omao.isSimilar(reliquias.spy_modelo1)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(omao.isSimilar(reliquias.spy_modelo2)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.75);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 1,true,false));
            }else if(omao.isSimilar(reliquias.tridente_modelo1) || omao.isSimilar(reliquias.tridente_modelo2)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                if(omao.isSimilar(reliquias.tridente_modelo1)){
                    player.getWorld().setStorm(true);
                    player.getWorld().setWeatherDuration(12000);
                    player.getWorld().setThundering(true);
                    player.getWorld().setThunderDuration(12000);
                }else if(omao.isSimilar(reliquias.tridente_modelo2)){
                    player.getWorld().setStorm(true);
                    player.getWorld().setWeatherDuration(12000);
                }
            }else{
                if(item.isSimilar(reliquias.espadamd)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                }else if(item.isSimilar(reliquias.totem)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
                }else if(item.isSimilar(reliquias.enxada)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(item.isSimilar(reliquias.spy_modelo1)){
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(item.isSimilar(reliquias.spy_modelo2)){
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 1,true,false));
                }else if(item.isSimilar(reliquias.tridente_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                }else if(item.isSimilar(reliquias.tridente_modelo2)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, -1, 1));
                }else{
                    limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            limparEfeito(player);
        }
    }
    public void limparEfeito(Player player){
        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
        player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(1);
    }
    @EventHandler
    public void getPower(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            if(dead instanceof Monster) {
                if(dead.getType() == EntityType.WITHER){
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        killer.setMaxHealth(killer.getMaxHealth()*1.5);
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        double dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*1.5;
                        killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dano);
                        killer.sendMessage("Seu dano agora é "+killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)+" sem a reliquia");
                    }
                }else if(dead.getType() == EntityType.ENDER_DRAGON){
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        killer.setMaxHealth(killer.getMaxHealth()*2);
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        double dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*2;
                        killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dano);
                        killer.sendMessage("Seu dano agora é "+killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)+" sem a reliquia");
                    }
                }else{
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        killer.setMaxHealth(killer.getMaxHealth()+1);
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        double dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+1;
                        killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dano);
                        killer.sendMessage("Seu dano agora é "+killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)+" sem a reliquia");
                    }
                }
            }
        }
        if (dead instanceof Player) {
            Player f = (Player) dead;
            f.setMaxHealth(20.0);
            f.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1);
        }
    }
}
