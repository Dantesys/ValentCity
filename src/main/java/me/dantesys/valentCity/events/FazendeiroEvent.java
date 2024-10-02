package me.dantesys.valentCity.events;

import com.google.common.util.concurrent.AtomicDouble;
import me.dantesys.valentCity.MobsList;
import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class FazendeiroEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.farm_modelo1)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
            }else if(item != null && item.isSimilar(Reliquias.farm_modelo2)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.farm_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                }else if(item != null && item.isSimilar(Reliquias.farm_modelo2)){
                    ReliquiasEvent.limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.farm_modelo1)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(ver<=25){
                    w.dropItemNaturally(l,new ItemStack(Material.WHEAT));
                }else if(ver<=50){
                    w.dropItemNaturally(l,new ItemStack(Material.BEETROOTS));
                }else if(ver<=75){
                    w.dropItemNaturally(l,new ItemStack(Material.POTATOES));
                }else if(ver<=99){
                    w.dropItemNaturally(l,new ItemStack(Material.CARROTS));
                }else{
                    w.dropItemNaturally(l,new ItemStack(Material.GOLDEN_CARROT));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.farm_modelo2)) {
                if (presa instanceof Player) {
                    atacantepl.sendMessage("Você não pode pegar jogadores!");
                }else if(atacantepl.hasCooldown(Reliquias.farm_modelo2.getType())){
                    atacantepl.sendMessage("Está e cooldown aguarde "+atacantepl.getCooldown(Reliquias.farm_modelo2.getType()));
                }else if (presa instanceof LivingEntity lepresa) {
                    if (lepresa instanceof Tameable domado){
                        if(domado.isTamed()){
                            if(domado.getOwner() != atacantepl){
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
                    lepresa.remove();
                    lepresa.getWorld().dropItem(lepresa.getLocation(), item);
                    atacantepl.setCooldown(Reliquias.farm_modelo2.getType(),100);
                }
            }
        }
    }
    @EventHandler
    public void interacao(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(item != null && item.isSimilar(Reliquias.farm_modelo1)){
            if(event.getClickedBlock() != null){
                Material mat = event.getClickedBlock().getType();
                if (aravel(mat)) {
                    event.getClickedBlock().setType(Material.FARMLAND);
                }
            }else{
                int range = 50;
                AtomicDouble damage = new AtomicDouble(0);
                boolean atk = false;
                if(player.getInventory().contains(Material.WHEAT)){
                    atk = true;
                    HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.WHEAT);
                    if(!map.isEmpty()){
                        map.forEach((slot,i) -> {
                            damage.addAndGet(i.getAmount());
                        });
                    }
                }
                if(player.getInventory().contains(Material.BEETROOTS)){
                    atk = true;
                    HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.BEETROOTS);
                    if(!map.isEmpty()){
                        map.forEach((slot,i) -> {
                            damage.addAndGet(i.getAmount()*1.25);
                        });
                    }
                }
                if(player.getInventory().contains(Material.POTATOES)){
                    atk = true;
                    HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.POTATOES);
                    if(!map.isEmpty()) {
                        map.forEach((slot, i) -> {
                            damage.addAndGet(i.getAmount() * 1.5);
                        });
                    }
                }
                if(player.getInventory().contains(Material.CARROTS)){
                    atk = true;
                    HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.CARROTS);
                    if(!map.isEmpty()){
                        map.forEach((slot,i) -> {
                            damage.addAndGet(i.getAmount()*1.75);
                        });
                    }
                }
                if(player.getInventory().contains(Material.GOLDEN_CARROT)){
                    atk = true;
                    HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.GOLDEN_CARROT);
                    if(!map.isEmpty()){
                        map.forEach((slot,i) -> {
                            damage.addAndGet(i.getAmount()*2);
                        });
                    }
                }
                if(atk){
                    final int finalRange = range;
                    final double finalDamage = damage.get();
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
                        location.getWorld().spawnParticle(Particle.HAPPY_VILLAGER,location,1,0,0,0,0);
                        passa[0] = location.getBlock().isPassable();
                        location.getWorld().playSound(location,Sound.BLOCK_GROWING_PLANT_CROP,0.5f,0.7f);
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
                }
            }
        }else if(item != null && item.isSimilar(Reliquias.farm_modelo2)){
            if(action.isRightClick()){
                if(event.getClickedBlock() != null){
                    Material mat = event.getClickedBlock().getType();
                    if (aravel(mat)) {
                        event.getClickedBlock().applyBoneMeal(event.getBlockFace());
                    }
                }else{
                    event.setCancelled(true);
                }
            }
        }
    }
    private boolean aravel(Material material) {
        return switch (material) {
            case GRASS_BLOCK, DIRT, FARMLAND, ROOTED_DIRT, DIRT_PATH, COARSE_DIRT, PODZOL, MYCELIUM -> true;
            default -> false;
        };
    }
}