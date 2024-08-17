package me.dantesys.valentCity.events;

import io.papermc.paper.event.player.PlayerDeepSleepEvent;
import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
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
                    /*Falta 2-6*/
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
                        if(mana>=1000000000){
                            player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),true));
                            player.sendMessage("Pele de mana ativada");
                            mana = mana-100;
                            player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
                            player.sendActionBar(Component.text("Mana: "+mana));
                            pele(player,w);
                        }else{
                            player.sendMessage("Você não tem mana suficiente");
                        }
                    }
                    else if(hb3 != null && hb3.isSimilar(Reliquias.varinha)){
                        if(mana>=1000000000){
                            player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),true));
                            player.sendMessage("Pele de mana ativada");
                            mana = mana-100;
                            player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
                            player.sendActionBar(Component.text("Mana: "+mana));
                            pele(player,w);
                        }else{
                            player.sendMessage("Você não tem mana suficiente");
                        }
                    }
                    else if(hb4 != null && hb4.isSimilar(Reliquias.varinha)){
                        if(mana>=1000000000){
                            player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),true));
                            player.sendMessage("Pele de mana ativada");
                            mana = mana-100;
                            player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
                            player.sendActionBar(Component.text("Mana: "+mana));
                            pele(player,w);
                        }else{
                            player.sendMessage("Você não tem mana suficiente");
                        }
                    }
                    else if(hb5 != null && hb5.isSimilar(Reliquias.varinha)){
                        if(mana>=1000000000){
                            player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),true));
                            player.sendMessage("Pele de mana ativada");
                            mana = mana-100;
                            player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
                            player.sendActionBar(Component.text("Mana: "+mana));
                            pele(player,w);
                        }else{
                            player.sendMessage("Você não tem mana suficiente");
                        }
                    }
                    else if(hb6 != null && hb6.isSimilar(Reliquias.varinha)){
                        if(mana>=1000000000){
                            player.setMetadata("pele",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),true));
                            player.sendMessage("Pele de mana ativada");
                            mana = mana-100;
                            player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),mana));
                            player.sendActionBar(Component.text("Mana: "+mana));
                            pele(player,w);
                        }else{
                            player.sendMessage("Você não tem mana suficiente");
                        }
                    }
                    else if(hb7 != null && hb7.isSimilar(Reliquias.varinha)){
                        if(mana>=100000){
                            int range = 50;
                            player.sendMessage("DeathRay!");
                            mana = mana-100000;
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
                                        location.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME,location,1,0,0,0,0);
                                        passa[0] = location.getBlock().isPassable();
                                        location.getWorld().playSound(location,Sound.ENTITY_WITHER_DEATH,0.5f,0.7f);
                                        Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                                        while(pressf.iterator().hasNext()){
                                            Entity surdo = pressf.iterator().next();
                                            if(surdo instanceof LivingEntity vivo){
                                                double dmg = vivo.getHealth();
                                                vivo.damage(dmg);
                                            }
                                            pressf.remove(surdo);
                                        }
                                        location.subtract(x,y,z);
                                        if(tp[0]>finalRange || !passa[0]){
                                            t.stop();
                                            location.getWorld().createExplosion(location,20,false,false);
                                        }
                                    });
                            timer.scheduleTimer(5L);
                        }else{
                            player.sendMessage("Você não tem mana suficiente para DeathRay!");
                        }
                    }
                    else if(hb8 != null && hb8.isSimilar(Reliquias.varinha)){
                        if(mana>=500){
                            int range = 500;
                            player.sendMessage("EXPLOSION!");
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
                                            World vw = vivo.getWorld();
                                            vivo.damage(5);
                                            if(vw.getEnvironment().equals(World.Environment.NORMAL)){
                                                vw.createExplosion(vivo,5,false,false);
                                            }else if(vw.getEnvironment().equals(World.Environment.NETHER)){
                                                vw.createExplosion(vivo,5,true,false);
                                            }else if(vw.getEnvironment().equals(World.Environment.THE_END)){
                                                vw.createExplosion(vivo,10,false,false);
                                            }
                                        }
                                        pressf.remove(surdo);
                                    }
                                    location.subtract(x,y,z);
                                    if(tp[0]>finalRange || !passa[0]){
                                        t.stop();
                                        location.getWorld().createExplosion(location,20,false,false);
                                    }
                                });
                                timer.scheduleTimer(5L);
                            }else{
                                player.sendMessage("Você não tem mana suficiente para EXPLOSION!");
                            }
                        }
                    else if(hb9 != null && hb9.isSimilar(Reliquias.varinha)){
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
                player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),1));
            }
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
                player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),1));
            }
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
                player.setMetadata("mana",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),1));
            }
            player.sendActionBar(Component.text("Mana: "+mana));
        }
    }
    public void pele(Player toma, World w){
        toma.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,1));
        toma.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,-1,4));
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