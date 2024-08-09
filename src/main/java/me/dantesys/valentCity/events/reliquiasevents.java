package me.dantesys.valentCity.events;

import me.dantesys.valentCity.MobsList;
import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

public class reliquiasevents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Seja bem vindo");
    }
    @EventHandler
    public void onTotem(EntityResurrectEvent e) {
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
            ItemStack finalHand = reliquias.totem;
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
            if(dano+1>100){
                player.sendMessage("Sem efeito, você atingiu o limite");
                return;
            }
            double armor = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).getValue();
            double armortoug = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).getValue();
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(dano+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(armor+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(armortoug+1);
        }else if(is.isSimilar(reliquias.life)){
            double vida = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
            if(vida+1>200){
                player.sendMessage("Sem efeito, você atingiu o limite");
                return;
            }
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
                if(omao.isSimilar(reliquias.totem)){
                    limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                }else{
                    limparEfeito(player);
                }
            }
        } catch (NullPointerException ignored){

        }try{
            if(omao.isSimilar(reliquias.totem)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
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
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, -1, 0, true, false));
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
                    limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            limparEfeito(player);
        }
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
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(0);
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(0);
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_MAX_ABSORPTION)).setBaseValue(0);
            Objects.requireNonNull(pressf.getAttribute(Attribute.GENERIC_OXYGEN_BONUS)).setBaseValue(0);
        }
    }
    @EventHandler
    public void lancarProjetil(ProjectileLaunchEvent event) {
        ProjectileSource atirador = event.getEntity().getShooter();
        if (atirador instanceof Player player){
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
                    }else if(ver<=99){
                        arrow.addCustomEffect(new PotionEffect(PotionEffectType.WITHER,200,2),true);
                    }else{
                        arrow.setMetadata("kaboom",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(float) 10));
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
            }else if(flecha.hasMetadata("kaboom")){
                float damage = flecha.getMetadata("kaboom").getFirst().asFloat();
                event.getEntity().getWorld().createExplosion(event.getEntity(),damage,false,false);
            }
        }
        if(event.getDamager() instanceof Snowball bola) {
            if(bola.hasMetadata("freeze")) {
                int gelo = bola.getMetadata("freeze").getFirst().asInt();
                event.getEntity().setFreezeTicks(event.getEntity().getMaxFreezeTicks()*gelo);
            }
        }
        if(event.getDamager() instanceof WindCharge vento){
            if(vento.hasMetadata("vento")) {
                int voar = vento.getMetadata("vento").getFirst().asInt();
                if(event.getEntity() instanceof LivingEntity vivo){
                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,voar*20,voar/2));
                }
            }
        }
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.espadamd)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.domador)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                if(ver<=50){
                    sumonalobo(atacantepl,presa);
                }else{
                    sumonarapoza(atacantepl,presa);
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.enxada)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 40, 1));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.totem)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 5));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.tridente_modelo1)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.getWorld().strikeLightning(lepresa.getLocation());
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.tridente_modelo1) || atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.tridente_modelo2)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.setRemainingAir(0);
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.spy_modelo1)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 5));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.spy_modelo2)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 5));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.farm_modelo1)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(presa instanceof Player){
                    atacantepl.sendMessage("Você não pode plantar jogadores!");
                }else{
                    if(presa instanceof LivingEntity vivo){
                        double hp = Objects.requireNonNull(vivo.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        if(vivo.getHealth()>(hp/2)){
                            atacantepl.sendMessage("Você ainda não pode plantar ele!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (hp/2))+" de vida");
                            return;
                        }
                        presa.remove();
                        atacantepl.sendMessage(presa.getName()+" agora está plantado!");
                        Location l2 = l.add(0,1,0);
                        l.subtract(0,1,0);
                        if(ver<=25){
                            w.getBlockAt(l).setType(Material.FARMLAND);
                            w.getBlockAt(l2).setType(Material.WHEAT);
                        }else if(ver<=50){
                            w.getBlockAt(l).setType(Material.FARMLAND);
                            w.getBlockAt(l2).setType(Material.BEETROOTS);
                        }else if(ver<=75){
                            w.getBlockAt(l).setType(Material.FARMLAND);
                            w.getBlockAt(l2).setType(Material.POTATOES);
                        }else{
                            w.getBlockAt(l).setType(Material.FARMLAND);
                            w.getBlockAt(l2).setType(Material.CARROTS);
                        }
                    }
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.mago)) {
                if(presa instanceof LivingEntity toma){
                    Random rd = new Random();
                    int ver = rd.nextInt(0,100);
                    int tempo = rd.nextInt(1,60);
                    int power = rd.nextInt(1,4);
                    tempo = tempo*20;
                    if(ver<=8) toma.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,tempo,power));
                    else if(ver<=16) toma.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,tempo,power));
                    else if(ver<=24) toma.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,tempo,power));
                    else if(ver<=32) toma.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,tempo,power));
                    else if(ver<=40) toma.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,tempo,power));
                    else if(ver<=48) toma.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,tempo,power));
                    else if(ver<=56) toma.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,tempo,power));
                    else if(ver<=64) toma.addPotionEffect(new PotionEffect(PotionEffectType.POISON,tempo,power));
                    else if(ver<=72) toma.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,tempo,power));
                    else if(ver<=80) toma.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,tempo,power));
                    else if(ver<=88) toma.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,tempo,power));
                    else if(ver<=96) toma.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,tempo,power));
                    else{
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.POISON,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,tempo,power));
                        toma.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,tempo,power));
                    }
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.farm_modelo2)) {
                if (presa instanceof Player) {
                    atacantepl.sendMessage("Você não pode pegar jogadores!");
                }else if(atacantepl.hasCooldown(reliquias.farm_modelo2.getType())){
                    atacantepl.sendMessage("Está e cooldown aguarde "+atacantepl.getCooldown(reliquias.farm_modelo2.getType()));
                }else if (presa instanceof LivingEntity lepresa) {
                    if (lepresa instanceof Tameable domado){
                        if(domado.isTamed()){
                            if(!(domado.getOwner() == atacantepl)){
                                atacantepl.sendMessage("Você não pode pegar pet de outros jogadores!");
                                return;
                            }
                        }
                    }
                    ItemStack item = new ItemStack(Objects.requireNonNull(MobsList.getEggType(lepresa)).getMaterial());
                    String nome = lepresa.getName();
                    ItemMeta meta = item.getItemMeta();
                    meta.displayName(Component.text(nome));
                    item.setItemMeta(meta);
                    if(lepresa instanceof Pig pig) {
                        if(pig.hasSaddle()) {
                            pig.getWorld().dropItem(pig.getLocation(), new ItemStack(Material.SADDLE, 1));
                        }
                    }
                    if(lepresa instanceof Horse cavalo) {
                        if(cavalo.getInventory().getSaddle() != null){
                            cavalo.getWorld().dropItemNaturally(cavalo.getLocation(), cavalo.getInventory().getSaddle());
                        }
                        if(cavalo.getInventory().getArmor() != null){
                            cavalo.getWorld().dropItemNaturally(cavalo.getLocation(), cavalo.getInventory().getArmor());
                        }
                        ItemStack[] itens = cavalo.getInventory().getContents();
                        if(itens.length>1){
                            Block bloco = cavalo.getWorld().getBlockAt(cavalo.getLocation());
                            Chest bau = (Chest) bloco;
                            bau.getBlockInventory().addItem(itens);
                        }
                    }
                    if(lepresa instanceof InventoryHolder) {
                        ItemStack[] items = ((InventoryHolder) lepresa).getInventory().getContents();
                        for(ItemStack itemStack : items) {
                            if(itemStack!=null){
                                lepresa.getWorld().dropItemNaturally(lepresa.getLocation(), itemStack);
                            }
                        }
                    }
                    lepresa.remove();
                    lepresa.getWorld().dropItem(lepresa.getLocation(), item);
                    atacantepl.setCooldown(reliquias.farm_modelo2.getType(),50);
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.picareta_md1)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                int qtd = rd.nextInt(1,2);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(presa instanceof Player){
                    atacantepl.sendMessage("Você não pode minerar jogadores!");
                }else{
                    if(presa instanceof LivingEntity vivo){
                        double hp = Objects.requireNonNull(vivo.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        if(vivo.getHealth()>(hp/2)){
                            atacantepl.sendMessage("Você ainda não pode minerar!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (hp/2))+" de vida");
                            return;
                        }
                        vivo.remove();
                        atacantepl.sendMessage(presa.getName()+" agora é mineravel!");
                        if(ver<=25){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.COAL_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_COAL_ORE);
                            }
                        }else if(ver<=45){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.COPPER_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_COPPER_ORE);
                            }
                        }else if(ver<=65){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.IRON_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_IRON_ORE);
                            }
                        }else if(ver<=80){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.GOLD_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_GOLD_ORE);
                            }
                        }else if(ver<=90){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.LAPIS_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_LAPIS_ORE);
                            }
                        }else if(ver<=95){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.REDSTONE_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_REDSTONE_ORE);
                            }
                        }else if(ver<=99){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.DIAMOND_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_DIAMOND_ORE);
                            }
                        }else{
                            w.getBlockAt(l).setType(Material.ANCIENT_DEBRIS);
                        }
                    }
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.picareta_md2)) {
                if(presa instanceof Player){
                    atacantepl.sendMessage("Você não pode destruir jogadores!");
                }else{
                    Location l = presa.getLocation();
                    World w = presa.getWorld();
                    if(presa instanceof LivingEntity vivo){
                        double hp = Objects.requireNonNull(vivo.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        if(vivo.getHealth()>(hp/2)){
                            atacantepl.sendMessage("Você ainda não pode destruir!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (hp/2))+" de vida");
                            return;
                        }
                        atacantepl.sendMessage(presa.getName()+" destruido!");
                        vivo.remove();
                        w.createExplosion(l,4,false,false);
                    }
                }
            }
        }
    }
    @EventHandler
    public void acertou(ProjectileHitEvent event){
        if(event.getEntity() instanceof Snowball bola){
            int gelo = bola.getMetadata("freeze").getFirst().asInt();
            if(gelo>0 && event.getHitBlock() != null){
                event.getHitBlock().setType(Material.BLUE_ICE);
            }
        }
        if(event.getEntity() instanceof Fireball bola){
            if(event.getEntity() instanceof WindCharge vento){
                int forca = vento.getMetadata("vento").getFirst().asInt();
                if(forca>0 && event.getHitBlock() != null){
                    vento.setYield(10);
                    vento.explode();
                }
            }else if(bola.getMetadata("fire").getFirst()!=null){
                int fogo = bola.getMetadata("fire").getFirst().asInt();
                if(fogo>0 && event.getHitBlock() != null){
                    event.getHitBlock().setType(Material.MAGMA_BLOCK);
                    event.getEntity().remove();
                }
            }
        }
        if(event.getEntity() instanceof Arrow flecha){
            int magic = flecha.getMetadata("magic").getFirst().asInt();
            if(magic>0 && event.getHitBlock() != null){
                event.getHitBlock().setType(Material.BUDDING_AMETHYST);
                event.getEntity().remove();
            }
        }
        if(event.getEntity() instanceof WindCharge vento){
            int forca = vento.getMetadata("vento").getFirst().asInt();
            if(forca>0 && event.getHitBlock() != null){
                vento.setYield(50);
                vento.explode();
            }
        }
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
            }
        }
    }
    @EventHandler
    public void vcviu(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getActiveItem();
        if(item.isSimilar(reliquias.domador)){
            event.setCancelled(true);
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
    @EventHandler
    public void vcviu(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(item != null && item.isSimilar(reliquias.vento)){
            if(action.isRightClick()){
                Vector vec = player.getEyeLocation().getDirection();
                event.setCancelled(true);
                int slot = player.getInventory().getHeldItemSlot();
                player.getInventory().removeItem(reliquias.vento);
                player.setCooldown(reliquias.vento.getType(),0);
                player.getInventory().setItem(slot,reliquias.vento);
                WindCharge wc = player.launchProjectile(WindCharge.class);
                wc.setYield(50);
                wc.setAcceleration(vec.multiply(2));
                wc.setDirection(vec.multiply(2));
                wc.setGlowing(true);
                wc.setMetadata("vento",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(double) 10));
            }
        }else if(item != null && item.isSimilar(reliquias.arco_modelo2)){
            event.setCancelled(true);
            player.launchProjectile(Arrow.class);
        }else if(item != null && item.isSimilar(reliquias.farm_modelo1)){
            if(event.getClickedBlock() != null){
                if(event.getClickedBlock().isBuildable()){ return; }
                Material mat = event.getClickedBlock().getType();
                if (plantavel(mat)) {
                    event.getClickedBlock().applyBoneMeal(event.getBlockFace());
                }

            }
        }else if(item != null && item.isSimilar(reliquias.farm_modelo2)){
            if(action.isRightClick()){
                if(event.getClickedBlock() != null){
                    if(event.getClickedBlock().isBuildable()){ return; }
                    Material mat = event.getClickedBlock().getType();
                    if (plantavel(mat)) {
                        event.getClickedBlock().applyBoneMeal(event.getBlockFace());
                    }
                }
            }
        }else if(item != null && item.isSimilar(reliquias.picareta_md2)){
            if(action.isRightClick()){
                if(event.getClickedBlock() != null){
                    Block block = event.getClickedBlock();
                    if(block.getType() == Material.DEEPSLATE && !player.hasCooldown(reliquias.picareta_md2.getType())){
                        Location l = event.getClickedBlock().getLocation();
                        World w = event.getClickedBlock().getWorld();
                        player.setCooldown(reliquias.picareta_md2.getType(),1200);
                        mina(w,l,player);
                    }else if(!player.hasCooldown(reliquias.picareta_md2.getType())){
                        player.sendMessage("Só pode colocar dinamite na deepslate");
                    }else{
                        player.sendMessage("Aguarde a dinamite ser feita!");
                    }
                }
            }
        }else if(item != null && item.isSimilar(reliquias.domador)){
            if(action.isRightClick()){
                Material bloco = Objects.requireNonNull(event.getClickedBlock()).getType();
                if(bloco.equals(Material.SPAWNER)){
                    CreatureSpawner spawner = (CreatureSpawner) event.getClickedBlock().getState();
                    spawner.setType(Material.SPAWNER);
                    EntityType spawnedType = spawner.getSpawnedType();
                    if(spawnedType != null){
                        player.getInventory().addItem(makeSpawnerItem(spawnedType));
                        player.breakBlock(event.getClickedBlock());
                    }else{
                        player.sendMessage("Spawner é sem tipo");
                    }
                }
            }
        }else if(item != null && item.isSimilar(reliquias.mago)){
            event.setCancelled(true);
            if(!player.hasCooldown(reliquias.mago.getType())){
                event.setCancelled(true);
                Vector vec = player.getEyeLocation().getDirection();
                Random rd = new Random();
                int ver = rd.nextInt(0, 100);
                if(ver<=15){
                    player.sendMessage("Mahō no ya");
                    Arrow arrow = player.launchProjectile(Arrow.class);
                    arrow.setGlowing(true);
                    arrow.setMetadata("magic",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(double) 10));
                    arrow.setVelocity(vec.multiply(10));
                    player.setCooldown(reliquias.mago.getType(),100);
                }else if(ver<=30){
                    player.sendMessage("Hinotama");
                    Fireball fire = player.launchProjectile(Fireball.class);
                    fire.setGlowing(true);
                    fire.setMetadata("fire",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(double) 10));
                    fire.setDirection(vec.multiply(2));
                    fire.setVelocity(vec.multiply(2));
                    fire.setYield(4);
                    player.setCooldown(reliquias.mago.getType(),200);
                }else if(ver<=45){
                    player.sendMessage("Yukidaruma");
                    Snowball bola = player.launchProjectile(Snowball.class);
                    bola.setGlowing(true);
                    bola.setVelocity(vec.multiply(5));
                    bola.setMetadata("freeze",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class), 10));
                    player.setCooldown(reliquias.mago.getType(),100);
                }else if(ver<=60){
                    player.sendMessage("Bakuhatsu-on");
                    int range = 50;
                    int damage = 10;
                    final int finalRange = range;
                    final int finalDamage = damage;
                    final Location location = player.getLocation();
                    final boolean[] passa = {true};
                    final Vector direction = location.getDirection().normalize();
                    final double[] tp = {0};
                    Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                            ()->{
                            },()->{},(t)->{
                        tp[0] = tp[0]+3.4;
                        double x = direction.getX()*tp[0];
                        double y = direction.getY()*tp[0]+1.4;
                        double z = direction.getZ()*tp[0];
                        location.add(x,y,z);
                        location.getWorld().spawnParticle(Particle.SONIC_BOOM,location,1,0,0,0,0);
                        passa[0] = location.getBlock().isPassable();
                        location.getWorld().playSound(location,Sound.ENTITY_WARDEN_SONIC_BOOM,0.5f,0.7f);
                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                        while(pressf.iterator().hasNext()){
                            Entity surdo = pressf.iterator().next();
                            if(surdo instanceof LivingEntity vivo){
                                vivo.damage(finalDamage);
                            }
                            pressf.remove(surdo);
                        }
                        location.subtract(x,y,z);
                        if(t.getSegundosRestantes()>finalRange || !passa[0]){
                            t.stop();
                            location.getWorld().createExplosion(location,10,false,false);
                        }
                    });
                    timer.scheduleTimer(5L);
                    player.setCooldown(reliquias.mago.getType(),600);
                }else if(ver<=75){
                    player.sendMessage("Furainguheddo");
                    WitherSkull wither = player.launchProjectile(WitherSkull.class);
                    wither.setGlowing(true);
                    wither.setDirection(vec.multiply(2));
                    wither.setVelocity(vec.multiply(2));
                    wither.setCharged(true);
                    player.setCooldown(reliquias.mago.getType(),300);
                }else if(ver<=90){
                    player.sendMessage("Hanabi");
                    Firework fw = player.launchProjectile(Firework.class);
                    fw.setGlowing(true);
                    fw.setShotAtAngle(true);
                    fw.setVelocity(vec.multiply(5));
                    FireworkMeta fm = fw.getFireworkMeta();
                    fm.setPower(1);
                    fm.addEffect(FireworkEffect.builder()
                            .flicker(false)
                            .trail(true)
                            .with(FireworkEffect.Type.BALL_LARGE)
                            .withColor(Color.PURPLE)
                            .withFade(Color.BLACK)
                            .build());
                    fw.setFireworkMeta(fm);
                    player.setCooldown(reliquias.mago.getType(),200);
                }else{
                    player.sendMessage("Pōshonmikkusu");
                    ThrownPotion tp = player.launchProjectile(ThrownPotion.class);
                    PotionMeta pm = tp.getPotionMeta();
                    int tempo = 600;
                    int power = 1;
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.NAUSEA,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.POISON,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.WITHER,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.UNLUCK,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.DARKNESS,tempo,power),true);
                    tp.setPotionMeta(pm);
                    tp.setVelocity(vec.multiply(5));
                    player.setCooldown(reliquias.mago.getType(),300);
                }
            }
        }
    }
    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        final ItemStack hand = event.getItemInHand();
        if (hand.getType() == Material.AIR) return;
        ItemMeta meta = hand.getItemMeta();
        NamespacedKey key = new NamespacedKey(ValentCity.getPlugin(ValentCity.class), "SPAWNER_ENTITY_TYPE");
        if (!meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;
        EntityType type = EntityType.valueOf(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        final Block placedBlock = event.getBlockPlaced();
        if (placedBlock.getType() != Material.SPAWNER)
            placedBlock.setType(Material.SPAWNER);
        CreatureSpawner creatureSpawner = (CreatureSpawner) placedBlock.getState();
        creatureSpawner.setSpawnedType(type);
        creatureSpawner.update();
    }
    public void mina(World w,Location l, Player player) {
        EntityEquipment equip = player.getEquipment();
        ItemStack hand = null;
        boolean main = true;
        if (equip.getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
            hand = equip.getItemInMainHand();
            if (!hand.isSimilar(reliquias.picareta_md2)) {
                hand = null;
            }
        }
        if (equip.getItemInOffHand().getType() == Material.NETHERITE_PICKAXE) {
            hand = equip.getItemInOffHand();
            if (!hand.isSimilar(reliquias.picareta_md2)) {
                hand = null;
            }
            main = false;
        }
        if (hand != null) {
            TNTPrimed tnt = w.spawn(l, TNTPrimed.class);
            tnt.setFuseTicks(10000);
            boolean finalMain = main;
            ItemStack finalHand = reliquias.picareta_md2;
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class),10,
                    () -> player.sendMessage("Dinamite ativada!"),
                    () -> {
                        if (finalMain) {
                            player.getEquipment().setItemInMainHand(finalHand);
                        } else {
                            player.getEquipment().setItemInOffHand(finalHand);
                        }
                        tnt.remove();
                        w.createExplosion(l,40,false,true);
                    },(t) -> {
                player.sendMessage("Falta "+ (t.getSegundosRestantes()) + " Segundo para explosão!");
                tnt.customName(Component.text((t.getSegundosRestantes())+"s"));
                tnt.setCustomNameVisible(true);
            }
            );
            timer.scheduleTimer(20L);
        }
    }
    public void sumonarapoza(Player player,Entity entity) {
        EntityEquipment equip = player.getEquipment();
        ItemStack hand = null;
        boolean main = true;
        if (equip.getItemInMainHand().getType() == Material.STICK) {
            hand = equip.getItemInMainHand();
            if (!hand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                hand = null;
            }
        }
        if (equip.getItemInOffHand().getType() == Material.STICK) {
            hand = equip.getItemInOffHand();
            if (!hand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                hand = null;
            }
            main = false;
        }
        if (hand != null) {
            boolean finalMain = main;
            ItemStack finalHand = reliquias.domador;
            ItemStack espada = new ItemStack(Material.NETHERITE_SWORD);
            ItemMeta meta = espada.getItemMeta();
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE.getKey(),9, AttributeModifier.Operation.ADD_NUMBER));
            meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,new AttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
            meta.addEnchant(Enchantment.SHARPNESS,10,true);
            meta.addEnchant(Enchantment.FIRE_ASPECT,10,true);
            espada.setItemMeta(meta);
            Fox wolf = (Fox) player.getWorld().spawnEntity(entity.getLocation(), EntityType.FOX);
            wolf.setFirstTrustedPlayer(player);
            wolf.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(200);
            wolf.attack(entity);
            wolf.setTarget((LivingEntity) entity);
            wolf.getEquipment().setItemInMainHand(espada);
            wolf.setCanPickupItems(false);
            Fox wolf2 = (Fox) player.getWorld().spawnEntity(entity.getLocation(), EntityType.FOX);
            wolf2.setFirstTrustedPlayer(player);
            wolf2.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf2.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
            wolf2.attack(entity);
            wolf2.setTarget((LivingEntity) entity);
            wolf2.getEquipment().setItemInMainHand(espada);
            wolf2.setCanPickupItems(false);
            Fox wolf3 = (Fox) player.getWorld().spawnEntity(entity.getLocation(), EntityType.FOX);
            wolf3.setFirstTrustedPlayer(player);
            wolf3.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf3.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
            wolf3.attack(entity);
            wolf3.setTarget((LivingEntity) entity);
            wolf3.getEquipment().setItemInMainHand(espada);
            wolf3.setCanPickupItems(false);
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                    () -> {
                        player.sendMessage("Rapoza Ativado!");
                        player.getEquipment().setItemInMainHand(null);
                        wolf.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                        wolf2.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                        wolf3.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                    },() -> {
                if (finalMain) {
                    player.getEquipment().setItemInMainHand(finalHand);
                } else {
                    player.getEquipment().setItemInOffHand(finalHand);
                }
                wolf.remove();
                wolf2.remove();
                wolf3.remove();
            },(t) -> {
                wolf.customName(Component.text("Lider ("+(t.getSegundosRestantes())+"s)"));
                wolf2.customName(Component.text("Soldado ("+(t.getSegundosRestantes())+"s)"));
                wolf3.customName(Component.text("Soldado ("+(t.getSegundosRestantes())+"s)"));
                wolf.setCustomNameVisible(true);
                wolf2.setCustomNameVisible(true);
                wolf3.setCustomNameVisible(true);
                player.sendMessage("Falta "+ (t.getSegundosRestantes()) + " Segundo para reativar");
            }
            );
            timer.scheduleTimer(20L);
        }
    }
    public void sumonalobo(Player player,Entity entity) {
        EntityEquipment equip = player.getEquipment();
        ItemStack hand = null;
        boolean main = true;
        if (equip.getItemInMainHand().getType() == Material.STICK) {
            hand = equip.getItemInMainHand();
            if (!hand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                hand = null;
            }
        }
        if (equip.getItemInOffHand().getType() == Material.STICK) {
            hand = equip.getItemInOffHand();
            if (!hand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                hand = null;
            }
            main = false;
        }
        if (hand != null) {
            boolean finalMain = main;
            ItemStack finalHand = reliquias.domador;
            ItemStack armadura = new ItemStack(Material.WOLF_ARMOR);
            ItemMeta meta = armadura.getItemMeta();
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE.getKey(),9, AttributeModifier.Operation.ADD_NUMBER));
            meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,new AttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
            armadura.setItemMeta(meta);
            Wolf wolf = (Wolf) player.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
            wolf.setOwner(player);
            wolf.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(200);
            wolf.attack(entity);
            wolf.setTamed(true);
            wolf.getEquipment().setChestplate(armadura);
            Wolf wolf2 = (Wolf) player.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
            wolf2.setOwner(player);
            wolf2.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf2.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
            wolf2.attack(entity);
            wolf2.setTamed(true);
            wolf2.getEquipment().setChestplate(armadura);
            Wolf wolf3 = (Wolf) player.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
            wolf3.setOwner(player);
            wolf3.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf3.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
            wolf3.attack(entity);
            wolf3.setTamed(true);
            wolf3.getEquipment().setChestplate(armadura);
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                    () -> {
                        player.sendMessage("Lobo Ativado!");
                        player.getEquipment().setItemInMainHand(null);
                        wolf.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                        wolf2.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                        wolf3.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                    },() -> {
                if (finalMain) {
                    player.getEquipment().setItemInMainHand(finalHand);
                } else {
                    player.getEquipment().setItemInOffHand(finalHand);
                }
                wolf.remove();
                wolf2.remove();
                wolf3.remove();
            },(t) -> {
                wolf.customName(Component.text("Lider ("+(t.getSegundosRestantes())+"s)"));
                wolf2.customName(Component.text("Soldado ("+(t.getSegundosRestantes())+"s)"));
                wolf3.customName(Component.text("Soldado ("+(t.getSegundosRestantes())+"s)"));
                wolf.setCustomNameVisible(true);
                wolf2.setCustomNameVisible(true);
                wolf3.setCustomNameVisible(true);
                player.sendMessage("Falta "+ (t.getSegundosRestantes()) + " Segundo para reativar o lobo");
            }
            );
            timer.scheduleTimer(20L);
        }
    }
    private boolean plantavel(Material material) {
        return switch (material) {
            case WHEAT, CARROTS, POTATOES, BEETROOTS, NETHER_WART -> true;
            default -> false;
        };
    }
    private ItemStack makeSpawnerItem(EntityType entityType) {
        final ItemStack itemStack = new ItemStack(Material.SPAWNER, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(ValentCity.getPlugin(ValentCity.class), "SPAWNER_ENTITY_TYPE"), PersistentDataType.STRING, entityType.name());
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    public void limparEfeito(Player player){
        for (PotionEffect effect : player.getActivePotionEffects()){
            if(effect.getDuration()<=-1){
                player.removePotionEffect(effect.getType());
            }
        }
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(1);
    }
}