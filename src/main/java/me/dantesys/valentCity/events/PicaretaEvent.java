package me.dantesys.valentCity.events;

import com.google.common.util.concurrent.AtomicDouble;
import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;

public class PicaretaEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.picareta_md1)){
                ReliquiasEvent.limparEfeito(player);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 9));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
            }else if(item != null && item.isSimilar(Reliquias.picareta_md2)){
                ReliquiasEvent.limparEfeito(player);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
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
                if(item != null && item.isSimilar(Reliquias.picareta_md1)){
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 9));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                }else if(item != null && item.isSimilar(Reliquias.picareta_md2)){
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.picareta_md1)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                int qtd = rd.nextInt(1,10);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(ver<=25){
                    w.dropItemNaturally(l,new ItemStack(Material.COAL,qtd));
                }else if(ver<=45){
                    w.dropItemNaturally(l,new ItemStack(Material.RAW_COPPER,qtd));
                }else if(ver<=65){
                    w.dropItemNaturally(l,new ItemStack(Material.RAW_IRON,qtd));
                }else if(ver<=80){
                    w.dropItemNaturally(l,new ItemStack(Material.RAW_GOLD,qtd));
                }else if(ver<=90){
                    w.dropItemNaturally(l,new ItemStack(Material.LAPIS_LAZULI,qtd));
                }else if(ver<=95){
                    w.dropItemNaturally(l,new ItemStack(Material.REDSTONE,qtd));
                }else if(ver<=99){
                    w.dropItemNaturally(l,new ItemStack(Material.DIAMOND,qtd));
                }else{
                    w.dropItemNaturally(l,new ItemStack(Material.ANCIENT_DEBRIS,qtd));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.picareta_md2)) {
                Location l = presa.getLocation();
                World w = presa.getWorld();
                event.setDamage(event.getFinalDamage()*5);
            }
        }
    }
    @EventHandler
    public void picaretainteracao(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(item != null && item.isSimilar(Reliquias.picareta_md2)){
            if(event.getClickedBlock() != null){
                Block block = event.getClickedBlock();
                Location l = event.getClickedBlock().getLocation();
                World w = event.getClickedBlock().getWorld();
                if(block.getType() == Material.BEDROCK){
                    w.dropItemNaturally(l,new ItemStack(Material.BEDROCK));
                    block.setType(Material.AIR);
                }else if(action.isRightClick()){
                    if(block.getType() == Material.DEEPSLATE && !player.hasCooldown(Reliquias.picareta_md2.getType())){
                        player.setCooldown(Reliquias.picareta_md2.getType(),1200);
                        ReliquiasEvent.mina(w,l,player,false);
                    }else if(!player.hasCooldown(Reliquias.picareta_md2.getType())){
                        player.sendActionBar(Component.text("Só pode colocar dinamite na deepslate"));
                    }else{
                        player.sendActionBar(Component.text("Aguarde "+(player.getCooldown(Reliquias.picareta_md2.getType())/20)+"s a dinamite ser feita!"));
                    }
                }
            }else if(!player.hasCooldown(Reliquias.picareta_md2.getType())){
                final int finalRange = 50;
                final double finalDamage = 5;
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
                    location.getWorld().spawnParticle(Particle.EXPLOSION,location,1,0,0,0,0);
                    passa[0] = location.getBlock().isPassable();
                    location.getWorld().playSound(location,Sound.ENTITY_GENERIC_EXPLODE,0.5f,0.7f);
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
                player.setCooldown(Reliquias.picareta_md2.getType(),1200);
            }
        }else if(item != null && item.isSimilar(Reliquias.picareta_md1) && !player.hasCooldown(Reliquias.picareta_md1.getType())){
            AtomicDouble damage = new AtomicDouble(0);
            boolean atk = false;
            if(player.getInventory().contains(Material.COAL)){
                atk = true;
                HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.COAL);
                if(!map.isEmpty()){
                    map.forEach((slot,i) -> {
                        damage.addAndGet(i.getAmount());
                    });
                }
            }
            if(player.getInventory().contains(Material.RAW_COPPER)){
                atk = true;
                HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.RAW_COPPER);
                if(!map.isEmpty()){
                    map.forEach((slot,i) -> {
                        damage.addAndGet(i.getAmount()*1.25);
                    });
                }
            }
            if(player.getInventory().contains(Material.RAW_IRON)){
                atk = true;
                HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.RAW_IRON);
                if(!map.isEmpty()){
                    map.forEach((slot,i) -> {
                        damage.addAndGet(i.getAmount()*1.5);
                    });
                }
            }
            if(player.getInventory().contains(Material.RAW_GOLD)){
                atk = true;
                HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.RAW_GOLD);
                if(!map.isEmpty()){
                    map.forEach((slot,i) -> {
                        damage.addAndGet(i.getAmount()*1.75);
                    });
                }
            }
            if(player.getInventory().contains(Material.LAPIS_LAZULI)){
                atk = true;
                HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.LAPIS_LAZULI);
                if(!map.isEmpty()){
                    map.forEach((slot,i) -> {
                        damage.addAndGet(i.getAmount()*2);
                    });
                }
            }
            if(player.getInventory().contains(Material.REDSTONE)){
                atk = true;
                HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.REDSTONE);
                if(!map.isEmpty()){
                    map.forEach((slot,i) -> {
                        damage.addAndGet(i.getAmount()*2.25);
                    });
                }
            }
            if(player.getInventory().contains(Material.DIAMOND)){
                atk = true;
                HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.DIAMOND);
                if(!map.isEmpty()){
                    map.forEach((slot,i) -> {
                        damage.addAndGet(i.getAmount()*2.5);
                    });
                }
            }
            if(player.getInventory().contains(Material.ANCIENT_DEBRIS)){
                atk = true;
                HashMap<Integer,? extends ItemStack> map = player.getInventory().all(Material.ANCIENT_DEBRIS);
                if(!map.isEmpty()){
                    map.forEach((slot,i) -> {
                        damage.addAndGet(i.getAmount()*2.75);
                    });
                }
            }
            if(atk){
                final int finalRange = 50;
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
                    location.getWorld().spawnParticle(Particle.CRIT,location,1,0,0,0,0);
                    passa[0] = location.getBlock().isPassable();
                    location.getWorld().playSound(location,Sound.BLOCK_DEEPSLATE_BREAK,0.5f,0.7f);
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
                player.setCooldown(Reliquias.picareta_md2.getType(),1200);
            }
        }
    }
    @EventHandler
    public void sortudo(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.picareta_md1) && !player.hasCooldown(Reliquias.picareta_md1.getType())){
            Random rd = new Random();
            int ver = rd.nextInt(0,100);
            int qtd = rd.nextInt(1,10);
            Location l = event.getBlock().getLocation();
            World w = event.getBlock().getWorld();
            if(ver<=25){
                w.dropItemNaturally(l,new ItemStack(Material.COAL,qtd));
            }else if(ver<=45){
                w.dropItemNaturally(l,new ItemStack(Material.RAW_COPPER,qtd));
            }else if(ver<=65){
                w.dropItemNaturally(l,new ItemStack(Material.RAW_IRON,qtd));
            }else if(ver<=80){
                w.dropItemNaturally(l,new ItemStack(Material.RAW_GOLD,qtd));
            }else if(ver<=90){
                w.dropItemNaturally(l,new ItemStack(Material.LAPIS_LAZULI,qtd));
            }else if(ver<=95){
                w.dropItemNaturally(l,new ItemStack(Material.REDSTONE,qtd));
            }else if(ver<=99){
                w.dropItemNaturally(l,new ItemStack(Material.DIAMOND,qtd));
            }else{
                w.dropItemNaturally(l,new ItemStack(Material.NETHERITE_SCRAP,qtd));
            }
            player.setCooldown(Reliquias.picareta_md1.getType(),600);
        }
    }
}