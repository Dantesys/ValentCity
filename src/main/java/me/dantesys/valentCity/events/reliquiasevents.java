package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.Properties;
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
            }else if(item.isSimilar(reliquias.picareta_md1)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 9,true,false));
            }else if(item.isSimilar(reliquias.picareta_md2)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 9,true,false));
            }else if(item.isSimilar(reliquias.arco_modelo1)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(item.isSimilar(reliquias.farm_modelo1)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1,true,false));
            }else if(item.isSimilar(reliquias.arco_modelo2)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 1,true,false));
            }else if(item.isSimilar(reliquias.spy_modelo1)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.01);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(item.isSimilar(reliquias.spy_modelo2)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.25);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
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
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.25);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(omao.isSimilar(reliquias.spy_modelo2)){
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
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
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.25);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
            }else if(omao.isSimilar(reliquias.spy_modelo2)){
                player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 3,true,false));
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
                }else if(item.isSimilar(reliquias.picareta_md1)){
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 9,true,false));
                }else if(item.isSimilar(reliquias.picareta_md2)){
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 9,true,false));
                }else if(item.isSimilar(reliquias.arco_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(item.isSimilar(reliquias.arco_modelo2)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 1, true, false));
                }else if(item.isSimilar(reliquias.farm_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1,true,false));
                }else if(item.isSimilar(reliquias.spy_modelo1)){
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.01);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else if(item.isSimilar(reliquias.spy_modelo2)) {
                    player.getAttribute(Attribute.GENERIC_SCALE).setBaseValue(0.25);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1, true, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1, true, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, -1, 1, true, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 3, true, false));
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
                        if(killer.getMaxHealth()*1.5>200){
                            killer.setMaxHealth(killer.getMaxHealth()+1);
                        }else{
                            killer.setMaxHealth(killer.getMaxHealth()*1.5);
                        }
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        double dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*1.5;
                        if(dano>200){
                            dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+1;
                        }
                        killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dano);
                        killer.sendMessage("Seu dano agora é "+killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
                    }
                }else if(dead.getType() == EntityType.ENDER_DRAGON){
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        if(killer.getMaxHealth()*2>200){
                            killer.setMaxHealth(killer.getMaxHealth()+1);
                        }else{
                            killer.setMaxHealth(killer.getMaxHealth()*2);
                        }
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        double dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*2;
                        if(dano>200){
                            dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+1;
                        }
                        killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dano);
                        killer.sendMessage("Seu dano agora é "+killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
                    }
                }else if(dead.getType() == EntityType.WARDEN){
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        if(killer.getMaxHealth()*2.5>200){
                            killer.setMaxHealth(killer.getMaxHealth()+1);
                        }else{
                            killer.setMaxHealth(killer.getMaxHealth()*2.5);
                        }
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        double dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()*2.5;
                        if(dano>200){
                            dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+1;
                        }
                        killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dano);
                        killer.sendMessage("Seu dano agora é "+killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
                    }
                }else{
                    if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                        if(killer.getMaxHealth()+1>200){
                            killer.sendMessage("§d Aviso");
                            killer.sendMessage("§d Sua vida chegou ao limite para esse mobs comuns");
                            killer.sendMessage("§d Se cotinuar sua vida vai ficar sempre a mesma");
                            killer.setMaxHealth(200);
                        }else{
                            killer.setMaxHealth(killer.getMaxHealth()+1);
                        }
                    }else if(killer.getInventory().getItemInMainHand().equals(reliquias.espadamd)){
                        double dano = killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue()+1;
                        if(dano>200){
                            killer.sendMessage("§d Aviso");
                            killer.sendMessage("§d Sua força chegou no limite");
                            killer.sendMessage("§d Se cotinuar ela nunca vai aumentar");
                            killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(200);
                        }else{
                            killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(dano);
                        }
                        killer.sendMessage("Seu dano agora é "+killer.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue());
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
        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
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
    @EventHandler(priority = EventPriority.LOWEST)
    public void kaboom(EntityExplodeEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof WindCharge vento) {
            ProjectileSource atirador = vento.getShooter();
            if (atirador instanceof Player player){
                if (player.getInventory().getItemInMainHand().isSimilar(reliquias.vento) || player.getInventory().getItemInOffHand().isSimilar(reliquias.vento)){
                    event.setYield(10);
                }
            }
        }
    }
    @EventHandler
    public void acertou(ProjectileHitEvent event){
        if(event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            if (player.getInventory().getItemInMainHand().isSimilar(reliquias.crossbow)) {
                Arrow arrow = (Arrow) event.getEntity();
                Random rd = new Random();
                int ver = rd.nextInt(0, 100);
                if (ver >= 0 && ver <= 50) {
                    Location location = event.getEntity().getLocation();
                    World world = arrow.getWorld();
                    world.spawn(location, Firework.class);
                    Firework fw = (Firework) arrow.getWorld().spawn(
                            arrow.getLocation(), Firework.class);
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
                    Firework fw = (Firework) arrow.getWorld().spawn(
                            arrow.getLocation(), Firework.class);
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
}
