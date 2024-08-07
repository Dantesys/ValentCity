package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.Objects;
import java.util.Random;


public class reliquiasevents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Seja bem vindo");
    }
    @EventHandler
    public void troca(PlayerSwapHandItemsEvent event){
        Player player = event.getPlayer();
        ItemStack omao = event.getOffHandItem();
        if(omao.isSimilar(reliquias.tridente_modelo2)){
            event.setOffHandItem(reliquias.tridente_modelo1);
            player.getInventory().setItemInOffHand(reliquias.tridente_modelo1);
            player.updateInventory();
        }else if(omao.isSimilar(reliquias.tridente_modelo1)){
            event.setOffHandItem(reliquias.tridente_modelo2);
            player.getInventory().setItemInOffHand(reliquias.tridente_modelo2);
            player.updateInventory();
        }
    }
    @EventHandler
    public void aumento(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack is = event.getItem();
        if(is.isSimilar(reliquias.power)){
            double dano = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getValue();
            double armor = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).getValue();
            double armortoug = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).getValue();
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(dano+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(armor+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(armortoug+1);
        }else if(is.isSimilar(reliquias.life)){
            double vida = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
            double abs = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_ABSORPTION)).getValue();
            double oxy = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_OXYGEN_BONUS)).getValue();
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(vida+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_ABSORPTION)).setBaseValue(abs+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_OXYGEN_BONUS)).setBaseValue(oxy+1);
        }
    }
    @EventHandler
    public void mao(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(reliquias.espadamd)){
                limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
            }else if(item != null && item.isSimilar(reliquias.totem)){
                limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 2));
            }else if(item != null && item.isSimilar(reliquias.enxada)){
                limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(item != null && item.isSimilar(reliquias.picareta_md1)){
                limparEfeito(player);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 9,true,false));
            }else if(item != null && item.isSimilar(reliquias.picareta_md2)){
                limparEfeito(player);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
            }else if(item != null && item.isSimilar(reliquias.arco_modelo1)){
                limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(item != null && item.isSimilar(reliquias.farm_modelo1)){
                limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1,true,false));
            }else if(item != null && item.isSimilar(reliquias.arco_modelo2)){
                limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 1,true,false));
            }else if(item != null && item.isSimilar(reliquias.spy_modelo1)){
                limparEfeito(player);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.01);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(item != null && item.isSimilar(reliquias.spy_modelo2)){
                limparEfeito(player);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.25);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
            }else if(item != null && item.isSimilar(reliquias.tridente_modelo1)){
                limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
            }else if(item != null && item.isSimilar(reliquias.tridente_modelo2)){
                limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 2));
            }else if(item != null && item.isSimilar(reliquias.mago)){
                limparEfeito(player);
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                if(ver<=5) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
                else if (ver<=10) player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,-1,1));
                else if (ver<=15) player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,-1,1));
                else if (ver<=20) player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH,-1,1));
                else if (ver<=25) player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
                else if (ver<=30) player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,1));
                else if (ver<=35) player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,-1,1));
                else if (ver<=40) player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,-1,1));
                else if (ver<=45) player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,-1,1));
                else if (ver<=50) player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,-1,1));
                else if (ver<=55) player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,-1,1));
                else if (ver<=60) player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,-1,1));
                else if (ver<=65) player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,-1,1));
                else if (ver<=70) player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,-1,1));
                else if (ver<=75) player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,-1,1));
                else if (ver<=80) player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,-1,1));
                else if (ver<=85) player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,-1,1));
                else if (ver<=90) player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,-1,1));
                else if (ver<=95) player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE,-1,1));
                else{
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE,-1,1));
                }
            }else{
                if(omao.isSimilar(reliquias.espadamd)){
                    limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
                }else if(omao.isSimilar(reliquias.totem)){
                    limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                }else if(omao.isSimilar(reliquias.enxada)){
                    limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
                }else if(omao.isSimilar(reliquias.spy_modelo1)){
                    limparEfeito(player);
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.25);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(omao.isSimilar(reliquias.spy_modelo2)){
                    limparEfeito(player);
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
                }else if(omao.isSimilar(reliquias.tridente_modelo1) || omao.isSimilar(reliquias.tridente_modelo2)){
                    limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                    if(omao.isSimilar(reliquias.tridente_modelo1)){
                        player.getWorld().setStorm(true);
                        player.getWorld().setThundering(true);
                    }else if(omao.isSimilar(reliquias.tridente_modelo2)){
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 2));
                        player.getWorld().setStorm(true);
                    }
                }else{
                    limparEfeito(player);
                }
            }
        } catch (NullPointerException ignored){

        }try{
            if(omao.isSimilar(reliquias.espadamd)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
            }else if(omao.isSimilar(reliquias.totem)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
            }else if(omao.isSimilar(reliquias.enxada)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
            }else if(omao.isSimilar(reliquias.spy_modelo1)){
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.25);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(omao.isSimilar(reliquias.spy_modelo2)){
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 1,true,false));
            }else if(omao.isSimilar(reliquias.tridente_modelo1) || omao.isSimilar(reliquias.tridente_modelo2)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                if(omao.isSimilar(reliquias.tridente_modelo1)){
                    player.getWorld().setStorm(true);
                    player.getWorld().setThundering(true);
                }else if(omao.isSimilar(reliquias.tridente_modelo2)){
                    player.getWorld().setStorm(true);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 2));
                }
            }else if(item != null && item.isSimilar(reliquias.mago)){
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                if(ver<=5) player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
                else if (ver<=10) player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,-1,1));
                else if (ver<=15) player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,-1,1));
                else if (ver<=20) player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH,-1,1));
                else if (ver<=25) player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
                else if (ver<=30) player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,1));
                else if (ver<=35) player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,-1,1));
                else if (ver<=40) player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,-1,1));
                else if (ver<=45) player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,-1,1));
                else if (ver<=50) player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,-1,1));
                else if (ver<=55) player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,-1,1));
                else if (ver<=60) player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,-1,1));
                else if (ver<=65) player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,-1,1));
                else if (ver<=70) player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,-1,1));
                else if (ver<=75) player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,-1,1));
                else if (ver<=80) player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,-1,1));
                else if (ver<=85) player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,-1,1));
                else if (ver<=90) player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,-1,1));
                else if (ver<=95) player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE,-1,1));
                else{
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE,-1,1));
                }
            }else{
                if(item != null && item.isSimilar(reliquias.espadamd)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                }else if(item != null && item.isSimilar(reliquias.totem)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
                }else if(item != null && item.isSimilar(reliquias.enxada)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(item != null && item.isSimilar(reliquias.picareta_md1)){
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 9,true,false));
                }else if(item != null && item.isSimilar(reliquias.picareta_md2)){
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
                }else if(item != null && item.isSimilar(reliquias.arco_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(item != null && item.isSimilar(reliquias.arco_modelo2)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 1, true, false));
                }else if(item != null && item.isSimilar(reliquias.farm_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1,true,false));
                }else if(item != null && item.isSimilar(reliquias.spy_modelo1)){
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.01);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(item != null && item.isSimilar(reliquias.spy_modelo2)) {
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.25);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1, true, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1, true, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 1, true, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 3, true, false));
                }else if(item != null && item.isSimilar(reliquias.tridente_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                }else if(item != null && item.isSimilar(reliquias.tridente_modelo2)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 2));
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
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(1);
    }
    @EventHandler
    public void getPower(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            if(dead instanceof Monster) {
                ItemStack is = null;
                World w = dead.getWorld();
                Location l = dead.getLocation();
                if(dead.getType() == EntityType.WITHER){
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        is = reliquias.life;
                        is.add(2);
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        is = reliquias.power;
                        is.add(2);
                    }
                }else if(dead.getType() == EntityType.ELDER_GUARDIAN){
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        is = reliquias.life;
                        is.add();
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        is = reliquias.power;
                        is.add();
                    }
                }else if(dead.getType() == EntityType.ENDER_DRAGON){
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        is = reliquias.life;
                        is.add(3);
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        is = reliquias.power;
                        is.add(3);
                    }
                }else if(dead.getType() == EntityType.WARDEN){
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        is = reliquias.life;
                        is.add(4);
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        is = reliquias.power;
                        is.add(4);
                    }
                }else{
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        is = reliquias.life;
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        is = reliquias.power;
                    }
                }
                if(is!=null){
                    w.dropItemNaturally(l, is);
                }
            }
        }
        if (dead instanceof Player pressf) {
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20);
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1);
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(1);
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(1);
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_MAX_ABSORPTION)).setBaseValue(1);
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_OXYGEN_BONUS)).setBaseValue(1);
        }
    }
    @EventHandler
    public void lancarProjetil(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();
        ProjectileSource atirador = event.getEntity().getShooter();
        if (atirador instanceof Player player){
            if (player.getInventory().getItemInMainHand().isSimilar(reliquias.vento) || player.getInventory().getItemInOffHand().isSimilar(reliquias.vento)){
                if (projectile instanceof WindCharge windCharge) {
                    windCharge.setAcceleration(windCharge.getAcceleration().multiply(5));
                }
            }
            if (player.getInventory().getItemInMainHand().isSimilar(reliquias.arco_modelo1)){
                Arrow arrow = (Arrow) event.getEntity();
                arrow.setCritical(true);
                arrow.setGlowing(true);
                arrow.setColor(Color.YELLOW);
                Vector vec = player.getLocation().getDirection();
                arrow.setVelocity(vec.multiply(100));
            }
        }
    }
    @EventHandler
    public void onEntityBow(EntityShootBowEvent event){
        if(event.getEntity() instanceof Player player){
            if (player.getInventory().getItemInMainHand().isSimilar(reliquias.arco_modelo1)){
                Arrow arrow = (Arrow) event.getProjectile();
                arrow.setCritical(true);
                arrow.setGlowing(true);
                arrow.setColor(Color.YELLOW);
                Vector vec = player.getLocation().getDirection();
                arrow.setVelocity(vec.multiply(100));
            }else if (player.getInventory().getItemInMainHand().isSimilar(reliquias.crossbow)){
                Arrow arrow = (Arrow) event.getProjectile();
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                arrow.addCustomEffect(new PotionEffect(PotionEffectType.GLOWING,1000,2),true);
                if(ver>=50){
                    if (ver<=55){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS,200,2),true);
                    }else if(ver<=60){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,200,2),true);
                    }else if(ver<=65){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.DARKNESS,200,2),true);
                    }else if(ver<=70){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION,200,2),true);
                    }else if(ver<=75){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,200,2),true);
                    }else if(ver<=80){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.NAUSEA,200,2),true);
                    }else if(ver<=85){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER,200,2),true);
                    }else if(ver<=90){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.POISON,200,2),true);
                    }else if(ver<=95){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS,200,2),true);
                    }else{
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.WITHER,200,2),true);
                    }
                }
                arrow.setVelocity(player.getLocation().getDirection().multiply(5));
                event.setProjectile(arrow);
            }
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow flecha) {
            if(flecha.hasMetadata("magic")) {
                double damage = flecha.getMetadata("magic").getFirst().asDouble();
                event.setDamage(damage);
            }
        }
        if(event.getDamager() instanceof Snowball bola) {
            if(bola.hasMetadata("freeze")) {
                int gelo = bola.getMetadata("freeze").getFirst().asInt();
                event.getEntity().setFreezeTicks(event.getEntity().getMaxFreezeTicks()*gelo);
            }
        }
    }
    @EventHandler
    public void acertou(ProjectileHitEvent event){
        if(event.getEntity().getShooter() instanceof Player player) {
            if (player.getInventory().getItemInMainHand().isSimilar(reliquias.crossbow)) {
                Arrow arrow = (Arrow) event.getEntity();
                Random rd = new Random();
                int ver = rd.nextInt(0, 100);
                if (ver >= 0 && ver <= 50) {
                    Location location;
                    if (event.getHitBlock() != null){
                        location = event.getHitBlock().getLocation();
                    }else if(event.getHitEntity() != null){
                        location = event.getHitEntity().getLocation();
                    }else{
                        location = event.getEntity().getLocation();
                    }
                    World world = arrow.getWorld();
                    world.spawn(location, Firework.class);
                    Firework fw = arrow.getWorld().spawn(arrow.getLocation(), Firework.class);
                    FireworkMeta fm = fw.getFireworkMeta();
                    fm.addEffect(FireworkEffect.builder()
                            .flicker(false)
                            .trail(false)
                            .with(FireworkEffect.Type.BALL)
                            .withColor(Color.RED)
                            .withFade(Color.BLACK)
                            .build());
                    fm.setPower(5);
                    fw.setFireworkMeta(fm);
                    fw.setTicksToDetonate(0);
                }else if (ver >= 75 && ver <= 99) {
                    Location location = event.getEntity().getLocation();
                    World world = arrow.getWorld();
                    world.spawn(location, Firework.class);
                    Firework fw = arrow.getWorld().spawn(arrow.getLocation(), Firework.class);
                    FireworkMeta fm = fw.getFireworkMeta();
                    fm.addEffect(FireworkEffect.builder()
                            .flicker(false)
                            .trail(false)
                            .with(FireworkEffect.Type.BALL_LARGE)
                            .withColor(Color.RED)
                            .withFade(Color.BLACK)
                            .build());
                    fm.setPower(10);
                    fw.setFireworkMeta(fm);
                    fw.setTicksToDetonate(0);
                }else{
                    Location location = event.getEntity().getLocation();
                    World world = arrow.getWorld();
                    world.createExplosion(location,10,false,false);
                }
            }else if(player.getInventory().getItemInMainHand().isSimilar(reliquias.vento)){
                WindCharge vento = (WindCharge) event.getEntity();
                vento.setYield(10);
                vento.explode();
            }
        }
    }
    @EventHandler
    public void vcviu(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getActiveItem();
        if(item.isSimilar(reliquias.domador)){
            Entity entity = event.getRightClicked();
            if(entity instanceof Tameable doma){
                if(doma.isTamed()){
                    player.sendMessage("Você não pode domar animais já domados");
                }
                doma.setOwner(player);
                doma.setTamed(true);
            }
        }
    }
}