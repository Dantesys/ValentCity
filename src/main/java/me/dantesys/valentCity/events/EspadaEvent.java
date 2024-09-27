package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.StructureSearchResult;
import org.bukkit.util.Vector;

import java.util.Collection;

public class EspadaEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.espadamd1)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
            }else if(item != null && item.isSimilar(Reliquias.espadamd2)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.espadamd1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                }else if(item != null && item.isSimilar(Reliquias.espadamd2)){
                    ReliquiasEvent.limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
        Inventory iv = player.getInventory();
        if(iv.contains(Reliquias.espadamd2)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
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
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd1)){
                        is = Reliquias.power;
                        is.add(2);
                    }
                }else if(dead.getType() == EntityType.ELDER_GUARDIAN){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd1)){
                        is = Reliquias.power;
                        is.add();
                    }
                }else if(dead.getType() == EntityType.ENDER_DRAGON){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd1)){
                        is = Reliquias.power;
                        is.add(3);
                    }
                }else if(dead.getType() == EntityType.WARDEN){
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd1)){
                        is = Reliquias.power;
                        is.add(4);
                    }
                }else{
                    if(killer.getInventory().getItemInMainHand().equals(Reliquias.espadamd1)){
                        is = Reliquias.power;
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
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.espadamd1)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }else if(atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.espadamd2)){
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
            }
        }
    }
    @EventHandler
    public void corte(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.espadamd1)){
            if(action.isLeftClick() && !player.hasCooldown(Reliquias.espadamd1.getType())){
                int slot = -1;
                if(player.getInventory().contains(Reliquias.power)){
                    slot = player.getInventory().first(Reliquias.power);
                }
                if(slot>=0){
                    ItemStack item = player.getInventory().getItem(slot);
                    if(item!=null){
                        item.subtract(1);
                        player.getInventory().setItem(slot,item);
                        player.updateInventory();
                        int range = 50;
                        double damage = 1;
                        AttributeInstance ataque = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        if(ataque != null){
                            damage = ataque.getBaseValue();
                        }
                        final int finalRange = range;
                        final double finalDamage = damage;
                        final Location location = player.getLocation();
                        final boolean[] passa = {true};
                        final Vector direction = location.getDirection().normalize();
                        final double[] tp = {0};
                        Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                                ()->{
                                },()-> {
                                },(t)->{
                                    tp[0] = tp[0]+3.4;
                                    double x = direction.getX()*tp[0];
                                    double y = direction.getY()*tp[0]+1.4;
                                    double z = direction.getZ()*tp[0];
                                    location.add(x,y,z);
                                    location.getWorld().spawnParticle(Particle.SWEEP_ATTACK,location,1,0,0,0,0);
                                    passa[0] = location.getBlock().isPassable();
                                    location.getWorld().playSound(location, Sound.ENTITY_PLAYER_ATTACK_SWEEP,0.5f,0.7f);
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
                                    }
                                });
                        timer.scheduleTimer(5L);
                        player.setCooldown(Reliquias.espadamd1.getType(),600);
                    }
                }
            }
        }
    }
}