package me.dantesys.valentCity.events;

import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;

public class MarretaEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.marreta)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.marreta)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void matou(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            if(dead instanceof Monster) {
                ItemStack is = null;
                World w = dead.getWorld();
                Location l = dead.getLocation();
                if(dead.getType() == EntityType.WITHER){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.marreta)){
                        is = new ItemStack(Material.HEAVY_CORE);
                        is.add(2);
                    }
                }else if(dead.getType() == EntityType.ELDER_GUARDIAN){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.marreta)){
                        is = new ItemStack(Material.HEAVY_CORE);
                        is.add();
                    }
                }else if(dead.getType() == EntityType.ENDER_DRAGON){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.marreta)){
                        is = new ItemStack(Material.HEAVY_CORE);
                        is.add(3);
                    }
                }else if(dead.getType() == EntityType.WARDEN){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.marreta)){
                        is = new ItemStack(Material.HEAVY_CORE);
                        is.add(4);
                    }
                }
                if(is!=null){
                    w.dropItemNaturally(l, is);
                }
            }
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        switch (atacante) {
            case Player atacantepl -> {
                if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.marreta)) {
                    atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 200, 4));
                    if (presa instanceof LivingEntity lepresa) {
                        lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 200, 4));
                        lepresa.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 4));
                        lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 200, 4));
                    }
                }
            }
            case WindCharge vento -> {
                if (vento.hasMetadata("marreta")) {
                    double tornado = vento.getMetadata("marreta").getFirst().asDouble();
                    if (presa instanceof LivingEntity vivo) {
                        vivo.damage(tornado);
                        vivo.setRemainingAir(10);
                        vivo.addPotionEffect(new PotionEffect(PotionEffectType.WIND_CHARGED, -1, 5));
                    }
                }
            }
            case DragonFireball dg -> {
                if (dg.hasMetadata("marreta")) {
                    double fogo = dg.getMetadata("marreta").getFirst().asDouble();
                    if (presa instanceof LivingEntity vivo) {
                        vivo.damage(fogo*2);
                        vivo.addPotionEffect(new PotionEffect(PotionEffectType.WIND_CHARGED, -1, 5));
                    }
                }
            }
            case Fireball fire -> {
                if (fire.hasMetadata("marreta")) {
                    double fogo = fire.getMetadata("marreta").getFirst().asDouble();
                    if (presa instanceof LivingEntity vivo) {
                        vivo.damage(fogo);
                        int tempo_fogo = ((int) fogo * 20);
                        vivo.setFireTicks(tempo_fogo);
                        vivo.addPotionEffect(new PotionEffect(PotionEffectType.WIND_CHARGED, -1, 5));
                    }
                }
            }
            default -> {
            }
        }
    }
    @EventHandler
    public void movimento(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Location location = event.getFrom();
        double y1 = location.getY();
        double y2 = event.getTo().getY();
        ItemStack marreta = player.getInventory().getItemInMainHand();
        if(marreta.isSimilar(Reliquias.marreta)){
            if(y1>y2){
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,10,1));
                Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,5,100,5);
                while(pressf.iterator().hasNext()){
                    Entity lento = pressf.iterator().next();
                    if(lento instanceof LivingEntity vivo){
                        if(vivo instanceof Player p2){
                            if(player!=p2){
                                vivo.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,20,4));
                            }
                        }else{
                            vivo.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,20,4));
                        }
                    }
                    pressf.remove(lento);
                }
            }
        }
    }
    @EventHandler
    public void vento(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item != null && item.isSimilar(Reliquias.marreta)){
            if(!player.hasCooldown(Reliquias.marreta.getType())){
                Vector vec = player.getEyeLocation().getDirection();
                if(player.getWorld().getEnvironment().equals(World.Environment.NORMAL)){
                    WindCharge vento = player.launchProjectile(WindCharge.class);
                    vento.setGlowing(true);
                    vento.setMetadata("marreta",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(double) 5));
                    vento.setVelocity(vec.multiply(2));
                }else if(player.getWorld().getEnvironment().equals(World.Environment.NETHER)){
                    Fireball vento = player.launchProjectile(Fireball.class);
                    vento.setGlowing(true);
                    vento.setMetadata("marreta",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(double) 5));
                    vento.setVelocity(vec.multiply(2));
                }else if(player.getWorld().getEnvironment().equals(World.Environment.THE_END)){
                    DragonFireball vento = player.launchProjectile(DragonFireball.class);
                    vento.setGlowing(true);
                    vento.setMetadata("marreta",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(double) 5));
                    vento.setVelocity(vec.multiply(2));
                }
                player.setCooldown(Reliquias.marreta.getType(),100);
            }
        }
    }
}