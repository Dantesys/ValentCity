package me.dantesys.valentCity.events;

import io.papermc.paper.event.player.PlayerDeepSleepEvent;
import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.Random;

public class VarinhasEvent implements Listener {
    @EventHandler
    public void magia(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.varinha)){
            event.setCancelled(true);
            if(action.isLeftClick()) {
                if (player.hasMetadata("mana")) {
                    int mana = player.getMetadata("mana").getFirst().asInt();
                    World w = player.getWorld();
                    Inventory iv = player.getInventory();
                    ItemStack hb1 = iv.getItem(0);
                    ItemStack hb2 = iv.getItem(1);
                    ItemStack hb3 = iv.getItem(2);
                    ItemStack hb4 = iv.getItem(3);
                    ItemStack hb5 = iv.getItem(4);
                    ItemStack hb6 = iv.getItem(5);
                    ItemStack hb7 = iv.getItem(6);
                    ItemStack hb8 = iv.getItem(7);
                    ItemStack hb9 = iv.getItem(8);
                    if(hb1 != null && hb1.isSimilar(Reliquias.varinha)){
                        if(mana>=50){
                            player.sendMessage("Retorno");
                            mana = mana-50;
                            Location l = player.getRespawnLocation();
                            if(l!=null){
                                player.teleport(l);
                            }
                        }else{
                            player.sendMessage("Você não tem mana suficiente para Retorno");
                        }
                    }
                    else if(hb2 != null && hb2.isSimilar(Reliquias.varinha)){
                        if(mana>=1000){
                            int range = 500;
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("LEVITATION!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("ROCKET!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("SPACE RIFT!");
                            }
                            mana = mana-1000;
                            final int finalRange = range;
                            final Location location = player.getLocation();
                            final boolean[] passa = {true};
                            final Vector direction = location.getDirection().normalize();
                            final double[] tp = {0};
                            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                                    ()->{},
                                    ()->{},
                                    (t)->{
                                        tp[0] = tp[0]+3.4;
                                        double x = direction.getX()*tp[0];
                                        double y = direction.getY()*tp[0]+1.4;
                                        double z = direction.getZ()*tp[0];
                                        location.add(x,y,z);
                                        location.getWorld().spawnParticle(Particle.BUBBLE,location,1,0,0,0,0);
                                        passa[0] = location.getBlock().isPassable();
                                        location.getWorld().playSound(location,Sound.ENTITY_CREEPER_PRIMED,0.5f,0.7f);
                                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                                        while(pressf.iterator().hasNext()){
                                            Entity surdo = pressf.iterator().next();
                                            if(surdo instanceof LivingEntity vivo){
                                                if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,200,1,true,false));
                                                }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,200,2,true,false));
                                                    vivo.setFireTicks(200);
                                                }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,400,5,true,false));
                                                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,400,5,true,false));
                                                }
                                            }
                                            pressf.remove(surdo);
                                        }
                                        location.subtract(x,y,z);
                                        if(tp[0]>finalRange || !passa[0]){
                                            t.stop();
                                        }
                                    });
                            timer.scheduleTimer(5L);
                        }else{
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("Você não tem mana suficiente para LEVITATION!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("Você não tem mana suficiente para ROCKET!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("Você não tem mana suficiente para SPACE RIFT!");
                            }
                        }
                    }
                    else if(hb3 != null && hb3.isSimilar(Reliquias.varinha)){
                        if(mana>=2000){
                            int range = 500;
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("THUNDERBOLT!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("FIRE SPIN!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("METEOR!");
                            }
                            mana = mana-2000;
                            final int finalRange = range;
                            final Location location = player.getLocation();
                            final boolean[] passa = {true};
                            final Vector direction = location.getDirection().normalize();
                            final double[] tp = {0};
                            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                                    ()->{},
                                    ()->{},
                                    (t)->{
                                        tp[0] = tp[0]+3.4;
                                        double x = direction.getX()*tp[0];
                                        double y = direction.getY()*tp[0]+1.4;
                                        double z = direction.getZ()*tp[0];
                                        location.add(x,y,z);
                                        location.getWorld().spawnParticle(Particle.END_ROD,location,1,0,0,0,0);
                                        passa[0] = location.getBlock().isPassable();
                                        location.getWorld().playSound(location,Sound.ENTITY_CREEPER_PRIMED,0.5f,0.7f);
                                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                                        while(pressf.iterator().hasNext()){
                                            Entity surdo = pressf.iterator().next();
                                            if(surdo instanceof LivingEntity vivo){
                                                Location vl = vivo.getLocation();
                                                if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                                    LightningStrike ls = w.spawn(vl,LightningStrike.class);
                                                    ls.setFlashCount(10);
                                                    ls.setLifeTicks(600);
                                                    vivo.damage(5);
                                                }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                                    vivo.setFireTicks(t.getSegundosRestantes()*20);
                                                    for (double i = 0; i <3; i += 0.05) {
                                                        double x2 = vl.getX();
                                                        double y2 = vl.getY();
                                                        double z2 = vl.getZ();
                                                        Location particle = new Location(w, x2, y2+i, z2);
                                                        w.spawnParticle(Particle.FLAME,particle,1);
                                                        vivo.damage(i);
                                                    }
                                                }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                                    Location meteor = new Location(w, vl.getX(),vl.getY()+100,vl.getZ());
                                                    Fireball fr = w.spawn(meteor, Fireball.class);
                                                    fr.setYield(20);
                                                    Vector queda = vl.getDirection();
                                                    queda.add(new Vector(0,-1,0));
                                                    fr.setDirection(queda);
                                                    fr.setAcceleration(queda);
                                                    fr.setVelocity(fr.getAcceleration().multiply(10));
                                                }
                                            }
                                            pressf.remove(surdo);
                                        }
                                        location.subtract(x,y,z);
                                        if(tp[0]>finalRange || !passa[0]){
                                            t.stop();
                                        }
                                    });
                            timer.scheduleTimer(5L);
                        }else{
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("Você não tem mana suficiente para THUNDERBOLT!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("Você não tem mana suficiente para FIRE SPIN!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("Você não tem mana suficiente para METEOR!");
                            }
                        }
                    }
                    else if(hb4 != null && hb4.isSimilar(Reliquias.varinha)){
                        if(mana>=3000){
                            int range = 50;
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("CORRUPTION!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("COLD WATER!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("DIMENSIONAL CRACK!");
                            }
                            mana = mana-3000;
                            final int finalRange = range;
                            final Location location = player.getLocation();
                            final boolean[] passa = {true};
                            final Vector direction = location.getDirection().normalize();
                            final double[] tp = {0};
                            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                                    ()->{},
                                    ()->{},
                                    (t)->{
                                        tp[0] = tp[0]+3.4;
                                        double x = direction.getX()*tp[0];
                                        double y = direction.getY()*tp[0]+1.4;
                                        double z = direction.getZ()*tp[0];
                                        location.add(x,y,z);
                                        location.getWorld().spawnParticle(Particle.SCULK_SOUL,location,1,0,0,0,0);
                                        passa[0] = location.getBlock().isPassable();
                                        Block bloco = location.getBlock();
                                        if(w.getEnvironment().equals(World.Environment.NORMAL) && !bloco.isPassable()){
                                            bloco.setType(Material.SCULK);
                                            t.stop();
                                        }else if(w.getEnvironment().equals(World.Environment.NETHER) && !bloco.isPassable()){
                                            bloco.setType(Material.WATER);
                                            t.stop();
                                        }else if(w.getEnvironment().equals(World.Environment.THE_END) && !bloco.isPassable()){
                                            bloco.setType(Material.END_PORTAL);
                                            t.stop();
                                        }
                                        location.getWorld().playSound(location,Sound.ENTITY_CREEPER_PRIMED,0.5f,0.7f);
                                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                                        while(pressf.iterator().hasNext()){
                                            Entity surdo = pressf.iterator().next();
                                            if(surdo instanceof LivingEntity vivo){
                                                Location vl = vivo.getLocation();
                                                if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,400,4,true,false));
                                                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,400,1,true,false));
                                                }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                                    vivo.setFireTicks(0);
                                                    vivo.setFreezeTicks(vivo.getMaxFreezeTicks());
                                                    for (double i = 0; i <3; i += 0.05) {
                                                        double x2 = vl.getX();
                                                        double y2 = vl.getY();
                                                        double z2 = vl.getZ();
                                                        Location particle = new Location(w, x2, y2+i, z2);
                                                        w.spawnParticle(Particle.BUBBLE,particle,1);
                                                        vivo.setFreezeTicks((int) (vivo.getMaxFreezeTicks()+(i*20)));
                                                    }
                                                }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                                    Random rd = new Random();
                                                    double x2 = rd.nextDouble(-300,300);
                                                    double z2 = rd.nextDouble(-300,300);
                                                    Location chegada = new Location(w,vl.getX(),vl.getY(),vl.getZ());
                                                    chegada.add(x2,-1,z2);
                                                    Block blocos = chegada.getBlock();
                                                    blocos.setType(Material.END_STONE);
                                                    chegada.add(0,2,0);
                                                    vivo.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,100,5,false,true));
                                                    vivo.teleport(chegada);
                                                }
                                            }
                                            pressf.remove(surdo);
                                        }
                                        location.subtract(x,y,z);
                                        if(tp[0]>finalRange || !passa[0]){
                                            t.stop();
                                        }
                                    });
                            timer.scheduleTimer(5L);
                        }else{
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("Você não tem mana suficiente para CORRUPTION!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("Você não tem mana suficiente para COLD WATER!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("Você não tem mana suficiente para DIMENSIONAL CRACK!");
                            }
                        }
                    }
                    else if(hb5 != null && hb5.isSimilar(Reliquias.varinha)){
                        if(mana>=10000){
                            int range = 20;
                            player.sendMessage("STRAIGHT!");
                            mana = mana-10000;
                            final int finalRange = range;
                            final Location location = player.getLocation();
                            final Vector direction = location.getDirection().normalize();
                            final double[] tp = {0};
                            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                                    ()->{},
                                    ()->{},
                                    (t)->{
                                        tp[0] = tp[0]+3.4;
                                        double x = direction.getX()*tp[0];
                                        double y = direction.getY()*tp[0]+1.4;
                                        double z = direction.getZ()*tp[0];
                                        location.add(x,y,z);
                                        location.getWorld().spawnParticle(Particle.EGG_CRACK,location,1,0,0,0,0);
                                        location.getWorld().playSound(location,Sound.BLOCK_STONE_BREAK,0.5f,0.7f);
                                        Block bloco = location.getBlock();
                                        ItemStack drop = new ItemStack(bloco.getType());
                                        w.dropItemNaturally(location,drop);
                                        bloco.setType(Material.AIR);
                                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                                        while(pressf.iterator().hasNext()){
                                            Entity surdo = pressf.iterator().next();
                                            if(surdo instanceof LivingEntity vivo){
                                                vivo.setHealth(1);
                                            }
                                            pressf.remove(surdo);
                                        }
                                        location.subtract(x,y,z);
                                        if(tp[0]>finalRange){
                                            t.stop();
                                        }
                                    });
                            timer.scheduleTimer(5L);
                        }else{
                            player.sendMessage("Você não tem mana suficiente para STRAIGHT!");
                        }
                    }
                    else if(hb6 != null && hb6.isSimilar(Reliquias.varinha)){
                        if(mana>=1000){
                            int range = 20;
                            player.sendMessage("STOP OR XP!");
                            mana = mana-1000;
                            final int finalRange = range;
                            final Location location = player.getLocation();
                            final Vector direction = location.getDirection().normalize();
                            final double[] tp = {0};
                            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                                    ()->{},
                                    ()->{},
                                    (t)->{
                                        tp[0] = tp[0]+3.4;
                                        double x = direction.getX()*tp[0];
                                        double y = direction.getY()*tp[0]+1.4;
                                        double z = direction.getZ()*tp[0];
                                        location.add(x,y,z);
                                        location.getWorld().spawnParticle(Particle.EGG_CRACK,location,1,0,0,0,0);
                                        location.getWorld().playSound(location,Sound.BLOCK_STONE_BREAK,0.5f,0.7f);
                                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                                        while(pressf.iterator().hasNext()){
                                            Entity surdo = pressf.iterator().next();
                                            if(surdo instanceof LivingEntity vivo){
                                                if(surdo instanceof Player p){
                                                    int exp = p.getLevel();
                                                    player.giveExpLevels(exp);
                                                    p.setLevel(0);
                                                }else{
                                                    vivo.setAI(false);
                                                }
                                            }
                                            pressf.remove(surdo);
                                        }
                                        location.subtract(x,y,z);
                                        if(tp[0]>finalRange){
                                            t.stop();
                                        }
                                    });
                            timer.scheduleTimer(5L);
                        }else{
                            player.sendMessage("Você não tem mana suficiente para STOP OR XP!");
                        }
                    }
                    else if(hb7 != null && hb7.isSimilar(Reliquias.varinha)){
                        if(player.hasMetadata("pele")){
                            boolean pele = player.getMetadata("pele").getFirst().asBoolean();
                            if(pele){
                                player.sendMessage("Pele de mana desativada!");
                                ReliquiasEvent.limparEfeito(player);
                            }else{
                                if(mana>=100){
                                    player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),true));
                                    player.sendMessage("Pele de mana ativada!");
                                    mana = mana-100;
                                    player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
                                    pele(player,w);
                                }else{
                                    player.sendMessage("Você não tem mana suficiente para Pele de mana");
                                }
                            }
                        }
                    }
                    else if(hb8 != null && hb8.isSimilar(Reliquias.varinha)){
                        if(mana>=500){
                            int range = 500;
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("EXPLOSION!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("FIRE EXPLOSION!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("VOID EXPLOSION!");
                            }
                            mana = mana-500;
                            final int finalRange = range;
                            final Location location = player.getLocation();
                            final boolean[] passa = {true};
                            final Vector direction = location.getDirection().normalize();
                            final double[] tp = {0};
                            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                                    ()->{},
                                    ()->{},
                                    (t)->{
                                        tp[0] = tp[0]+3.4;
                                        double x = direction.getX()*tp[0];
                                        double y = direction.getY()*tp[0]+1.4;
                                        double z = direction.getZ()*tp[0];
                                        location.add(x,y,z);
                                        location.getWorld().spawnParticle(Particle.ANGRY_VILLAGER,location,1,0,0,0,0);
                                        passa[0] = location.getBlock().isPassable();
                                        location.getWorld().playSound(location,Sound.ENTITY_CREEPER_PRIMED,0.5f,0.7f);
                                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                                        while(pressf.iterator().hasNext()){
                                            Entity surdo = pressf.iterator().next();
                                            if(surdo instanceof LivingEntity vivo){
                                                vivo.damage(5);
                                                if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                                    w.createExplosion(vivo,5,false,false);
                                                }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                                    w.createExplosion(vivo,5,true,false);
                                                }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                                    w.createExplosion(vivo,15,false,true);
                                                }
                                            }
                                            pressf.remove(surdo);
                                        }
                                        location.subtract(x,y,z);
                                        if(tp[0]>finalRange || !passa[0]){
                                            t.stop();
                                        }
                                    });
                            timer.scheduleTimer(5L);
                        }else{
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("Você não tem mana suficiente para EXPLOSION!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("Você não tem mana suficiente para FIRE EXPLOSION!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("Você não tem mana suficiente para VOID EXPLOSION!");
                            }
                        }
                        }
                    else if(hb9 != null && hb9.isSimilar(Reliquias.varinha)){
                        if(mana>=1){
                            int range = mana;
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("EXPLO-OVERWORLD!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("EXPLO-NETHER!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("ANTIMATTER!");
                            }
                            mana = 0;
                            final int finalRange = range;
                            final Location location = player.getLocation();
                            final boolean[] passa = {true};
                            final Vector direction = location.getDirection().normalize();
                            final double[] tp = {0};
                            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                                    ()->{},
                                    ()->{},
                                    (t)->{
                                        tp[0] = tp[0]+3.4;
                                        double x = direction.getX()*tp[0];
                                        double y = direction.getY()*tp[0]+1.4;
                                        double z = direction.getZ()*tp[0];
                                        location.add(x,y,z);
                                        location.getWorld().spawnParticle(Particle.ANGRY_VILLAGER,location,1,0,0,0,0);
                                        passa[0] = location.getBlock().isPassable();
                                        location.getWorld().playSound(location,Sound.ENTITY_CREEPER_PRIMED,0.5f,0.7f);
                                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                                        while(pressf.iterator().hasNext()){
                                            Entity surdo = pressf.iterator().next();
                                            if(surdo instanceof LivingEntity vivo){
                                                vivo.damage(range);
                                            }
                                            pressf.remove(surdo);
                                        }
                                        location.subtract(x,y,z);
                                        if(tp[0]>finalRange || !passa[0]){
                                            t.stop();
                                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                                w.createExplosion(location,range,false,false);
                                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                                w.createExplosion(location,range,true,false);
                                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                                w.createExplosion(location,range,true,true);
                                            }
                                        }
                                    });
                            timer.scheduleTimer(5L);
                        }else{
                            if(w.getEnvironment().equals(World.Environment.NORMAL)){
                                player.sendMessage("Você não tem mana suficiente para EXPLO-OVERWORLD!");
                            }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                                player.sendMessage("Você não tem mana suficiente para EXPLO-NETHER!");
                            }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                                player.sendMessage("Você não tem mana suficiente para ANTIMATTER!");
                            }
                        }
                    }
                    player.sendActionBar(Component.text("Mana: "+mana));
                }
            }
        }
    }
    @EventHandler
    public void comer(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        int mana;
        if(player.getInventory().contains(Reliquias.varinha)){
            if(player.hasMetadata("mana")){
                mana = player.getMetadata("mana").getFirst().asInt();
                mana=mana+10;
            }else{
                mana = 1;
            }
            player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
            player.sendActionBar(Component.text("Mana: "+mana));
        }
    }
    @EventHandler
    public void dormir(PlayerDeepSleepEvent event){
        Player player = event.getPlayer();
        int mana;
        if(player.getInventory().contains(Reliquias.varinha)){
            if(player.hasMetadata("mana")){
                mana = player.getMetadata("mana").getFirst().asInt();
                if(player.hasMetadata("pele")){
                    boolean pele = player.getMetadata("pele").getFirst().asBoolean();
                    if(pele){
                        event.setCancelled(true);
                        player.sendMessage("Você não pode dormir com a pele de mana ativa!");
                    }else{
                        mana=mana+100;
                    }
                }else{
                    mana=mana+100;
                }
            }else{
                mana = 1;
            }
            player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
            player.sendActionBar(Component.text("Mana: "+mana));
        }
    }
    @EventHandler
    public void andar(PlayerMoveEvent event){
        Player player = event.getPlayer();
        World w = player.getWorld();
        int mana;
        if(player.getInventory().contains(Reliquias.varinha)){
            if(player.hasMetadata("mana")){
                mana = player.getMetadata("mana").getFirst().asInt();
                if(player.hasMetadata("pele")){
                    boolean pele = player.getMetadata("pele").getFirst().asBoolean();
                    if(w.getEnvironment().equals(World.Environment.NORMAL)){
                        if(pele){
                            pele(player,w);
                            if(mana>=1){
                                mana=mana-1;
                            }else{
                                ReliquiasEvent.limparEfeito(player);
                                player.sendMessage("Pele de mana desativada!");
                                player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),false));
                                player.sendMessage("Você não tem mana suficiente");
                            }
                        }else{
                            mana=mana+10;
                        }
                    }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                        if(pele){
                            pele(player,w);
                            if(mana>=2){
                                mana=mana-2;
                            }else{
                                ReliquiasEvent.limparEfeito(player);
                                player.sendMessage("Pele de mana desativada!");
                                player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),false));
                                player.sendMessage("Você não tem mana suficiente");
                            }
                        }else{
                            mana=mana+20;
                        }
                    }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                        if(pele){
                            pele(player,w);
                            if(mana>=3){
                                mana=mana-3;
                            }else{
                                ReliquiasEvent.limparEfeito(player);
                                player.sendMessage("Pele de mana desativada!");
                                player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),false));
                                player.sendMessage("Você não tem mana suficiente");
                            }
                        }else{
                            mana=mana+30;
                        }
                    }
                }else{
                    if(w.getEnvironment().equals(World.Environment.NORMAL)){
                        mana=mana+10;
                    }else if(w.getEnvironment().equals(World.Environment.NETHER)){
                        mana=mana+20;
                    }else if(w.getEnvironment().equals(World.Environment.THE_END)){
                        mana=mana+30;
                    }
                }
            }else{
                mana = 1;
            }
            player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
            player.sendActionBar(Component.text("Mana: "+mana));
        }
    }
    public void pele(Player toma, World w){
        toma.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,-1,4));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,-1,1));
        if(w.getEnvironment().equals(World.Environment.NORMAL)){
            toma.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            toma.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,-1,2));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,-1,1));
        }else if(w.getEnvironment().equals(World.Environment.NETHER)){
            toma.removePotionEffect(PotionEffectType.WATER_BREATHING);
            toma.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,-1,3));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,-1,2));
        }else if(w.getEnvironment().equals(World.Environment.THE_END)){
            toma.removePotionEffect(PotionEffectType.WATER_BREATHING);
            toma.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            toma.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,-1,4));
        }
    }
}