package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class GuardiaoEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.guardiao) || player.getInventory().contains(Reliquias.guardiao)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
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
                if(item != null && (item.isSimilar(Reliquias.guardiao) || player.getInventory().contains(Reliquias.guardiao) || item.isSimilar(Reliquias.guardiaominer) || player.getInventory().contains(Reliquias.guardiaominer))){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void kaboom(EntityExplodeEvent event){
        Entity entity = event.getEntity();
        if(entity instanceof TNTPrimed tnt){
            if(tnt.hasMetadata("guardiao")){
                if(tnt.getMetadata("guardiao").getFirst().asBoolean()){
                    List<Block> blocos = event.blockList();
                    if(!blocos.isEmpty()){
                        event.blockList().clear();
                        for(int i=0;i<=blocos.size();i++){
                            if(blocos.get(i).getType().equals(Material.STONE) || blocos.get(i).getType().equals(Material.DEEPSLATE)){
                                event.blockList().add(blocos.get(i));
                            }
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void punicao(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item != null && item.isSimilar(Reliquias.guardiao) && !player.hasCooldown(Reliquias.guardiao.getType())){
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
            if(hb1 != null && hb1.isSimilar(Reliquias.guardiao)){
                player.setCooldown(Reliquias.guardiao.getType(),6000);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.guardiao.getType(),0);
                    }
                },(t)->{
                    tp[0] = tp[0]+3.4;
                    double x = direction.getX()*tp[0];
                    double y = direction.getY()*tp[0]+1.4;
                    double z = direction.getZ()*tp[0];
                    location.add(x,y,z);
                    location.getWorld().spawnParticle(Particle.END_ROD,location,1,0,0,0,0);
                    Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                    while(pressf.iterator().hasNext()){
                        Entity surdo = pressf.iterator().next();
                        if(surdo instanceof Player vivo){
                            passa[0] = true;
                            vivo.damage(vivo.getHealth()-1);
                            t.stop();
                        }
                        pressf.remove(surdo);
                    }
                    location.subtract(x,y,z);
                    if(t.getSegundosRestantes()>finalRange){
                        t.stop();
                    }
                });
                timer.scheduleTimer(5L);
            }
            else if(hb2 != null && hb2.isSimilar(Reliquias.guardiao)){
                player.setCooldown(Reliquias.guardiao.getType(),600);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.guardiao.getType(),0);
                    }
                },(t)->{
                    tp[0] = tp[0]+3.4;
                    double x = direction.getX()*tp[0];
                    double y = direction.getY()*tp[0]+1.4;
                    double z = direction.getZ()*tp[0];
                    location.add(x,y,z);
                    location.getWorld().spawnParticle(Particle.DRAGON_BREATH,location,1,0,0,0,0);
                    Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                    while(pressf.iterator().hasNext()){
                        Entity surdo = pressf.iterator().next();
                        if(surdo instanceof Player vivo){
                            vivo.sendMessage("Cisco no olho!");
                            vivo.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,100,1));
                            vivo.damage(2);
                            passa[0] = true;
                            t.stop();
                        }
                        pressf.remove(surdo);
                    }
                    location.subtract(x,y,z);
                    if(t.getSegundosRestantes()>finalRange){
                        t.stop();
                    }
                });
                timer.scheduleTimer(5L);
            }
            else if(hb3 != null && hb3.isSimilar(Reliquias.guardiao)){
                player.setCooldown(Reliquias.guardiao.getType(),600);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                        ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.guardiao.getType(),0);
                    }
                },(t)->{
                    tp[0] = tp[0]+3.4;
                    double x = direction.getX()*tp[0];
                    double y = direction.getY()*tp[0]+1.4;
                    double z = direction.getZ()*tp[0];
                    location.add(x,y,z);
                    location.getWorld().spawnParticle(Particle.SNOWFLAKE,location,1,0,0,0,0);
                    Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                    while(pressf.iterator().hasNext()){
                        Entity surdo = pressf.iterator().next();
                        if(surdo instanceof Player vivo){
                            vivo.sendMessage("Que frio!");
                            vivo.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,100,10));
                            vivo.setFreezeTicks(vivo.getMaxFreezeTicks()+100);
                            passa[0] = true;
                            t.stop();
                        }
                        pressf.remove(surdo);
                    }
                    location.subtract(x,y,z);
                    if(t.getSegundosRestantes()>finalRange){
                        t.stop();
                    }
                });
                timer.scheduleTimer(5L);
            }
            else if(hb4 != null && hb4.isSimilar(Reliquias.guardiao)){
                player.setCooldown(Reliquias.guardiao.getType(),600);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.guardiao.getType(),0);
                    }
                },(t)->{
                    tp[0] = tp[0]+3.4;
                    double x = direction.getX()*tp[0];
                    double y = direction.getY()*tp[0]+1.4;
                    double z = direction.getZ()*tp[0];
                    location.add(x,y,z);
                    location.getWorld().spawnParticle(Particle.FLAME,location,1,0,0,0,0);
                    Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                    while(pressf.iterator().hasNext()){
                        Entity surdo = pressf.iterator().next();
                        if(surdo instanceof Player vivo){
                            vivo.sendMessage("Que calor!");
                            vivo.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,100,10));
                            vivo.setFireTicks(vivo.getMaxFireTicks()+100);
                            passa[0] = true;
                            t.stop();
                        }
                        pressf.remove(surdo);
                    }
                    location.subtract(x,y,z);
                    if(t.getSegundosRestantes()>finalRange){
                        t.stop();
                    }
                });
                timer.scheduleTimer(5L);
            }
            else if(hb5 != null && hb5.isSimilar(Reliquias.guardiao)){
                player.setCooldown(Reliquias.guardiao.getType(),400);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.guardiao.getType(),0);
                    }
                },(t)->{
                    tp[0] = tp[0]+3.4;
                    double x = direction.getX()*tp[0];
                    double y = direction.getY()*tp[0]+1.4;
                    double z = direction.getZ()*tp[0];
                    location.add(x,y,z);
                    location.getWorld().spawnParticle(Particle.CLOUD,location,1,0,0,0,0);
                    Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                    while(pressf.iterator().hasNext()){
                        Entity surdo = pressf.iterator().next();
                        if(surdo instanceof Player vivo){
                            vivo.sendMessage("Gavidade -1?");
                            vivo.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,100,10));
                            passa[0] = true;
                            t.stop();
                        }
                        pressf.remove(surdo);
                    }
                    location.subtract(x,y,z);
                    if(t.getSegundosRestantes()>finalRange){
                        t.stop();
                    }
                });
                timer.scheduleTimer(5L);
            }
            else if(hb6 != null && hb6.isSimilar(Reliquias.guardiao)){
                player.setCooldown(Reliquias.guardiao.getType(),1000);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.guardiao.getType(),0);
                    }
                },(t)->{
                    tp[0] = tp[0]+3.4;
                    double x = direction.getX()*tp[0];
                    double y = direction.getY()*tp[0]+1.4;
                    double z = direction.getZ()*tp[0];
                    location.add(x,y,z);
                    location.getWorld().spawnParticle(Particle.EGG_CRACK,location,1,0,0,0,0);
                    Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                    while(pressf.iterator().hasNext()){
                        Entity surdo = pressf.iterator().next();
                        if(surdo instanceof Player vivo){
                            vivo.sendMessage("Insetos!");
                            Location l = vivo.getLocation();
                            World w = vivo.getWorld();
                            w.spawn(l,Silverfish.class);
                            w.spawn(l,Silverfish.class);
                            w.spawn(l,Silverfish.class);
                            passa[0] = true;
                            t.stop();
                        }
                        pressf.remove(surdo);
                    }
                    location.subtract(x,y,z);
                    if(t.getSegundosRestantes()>finalRange){
                        t.stop();
                    }
                });
                timer.scheduleTimer(5L);
            }
            else if(hb7 != null && hb7.isSimilar(Reliquias.guardiao)){
                player.setCooldown(Reliquias.guardiao.getType(),1000);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.guardiao.getType(),0);
                    }
                },(t)->{
                    tp[0] = tp[0]+3.4;
                    double x = direction.getX()*tp[0];
                    double y = direction.getY()*tp[0]+1.4;
                    double z = direction.getZ()*tp[0];
                    location.add(x,y,z);
                    location.getWorld().spawnParticle(Particle.DRAGON_BREATH,location,1,0,0,0,0);
                    Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                    while(pressf.iterator().hasNext()){
                        Entity surdo = pressf.iterator().next();
                        if(surdo instanceof Player vivo){
                            vivo.sendMessage("Onde é que eu estou?");
                            Location l = vivo.getLocation();
                            Location l2 = player.getLocation();
                            vivo.teleport(l2);
                            player.teleport(l);
                            passa[0] = true;
                            t.stop();
                        }
                        pressf.remove(surdo);
                    }
                    location.subtract(x,y,z);
                    if(t.getSegundosRestantes()>finalRange){
                        t.stop();
                    }
                });
                timer.scheduleTimer(5L);
            }
            else if(hb8 != null && hb8.isSimilar(Reliquias.guardiao)){
                player.setCooldown(Reliquias.guardiao.getType(),400);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                AtomicReference<Location> l = new AtomicReference<>();
                AtomicReference<World> w = new AtomicReference<>();
                AtomicReference<Creeper> c = new AtomicReference<>();
                Temporizador timer2 = new Temporizador(ValentCity.getPlugin(ValentCity.class), 2,
                ()-> {
                    c.set(w.get().spawn(l.get(), Creeper.class));
                    c.get().setPowered(true);
                },()-> c.get().remove(),
                (t)->w.get().playSound(l.get(),Sound.ENTITY_CREEPER_PRIMED, 3.0f,0.5f));
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.guardiao.getType(),0);
                    }
                },(t)->{
                    tp[0] = tp[0]+3.4;
                    double x = direction.getX()*tp[0];
                    double y = direction.getY()*tp[0]+1.4;
                    double z = direction.getZ()*tp[0];
                    location.add(x,y,z);
                    location.getWorld().spawnParticle(Particle.PORTAL,location,1,0,0,0,0);
                    Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,2,2,2);
                    while(pressf.iterator().hasNext()){
                        Entity surdo = pressf.iterator().next();
                        if(surdo instanceof Player vivo){
                            vivo.sendMessage("Aw man!");
                            l.set(vivo.getLocation());
                            w.set(vivo.getWorld());
                            passa[0] = true;
                            timer2.scheduleTimer(20L);
                            t.stop();
                        }
                        pressf.remove(surdo);
                    }
                    location.subtract(x,y,z);
                    if(t.getSegundosRestantes()>finalRange){
                        t.stop();
                    }
                });
                timer.scheduleTimer(5L);
            }
            else if(hb9 != null && hb9.isSimilar(Reliquias.guardiao)){
                Vector vec = player.getEyeLocation().getDirection();
                EnderPearl perola = player.launchProjectile(EnderPearl.class);
                perola.setGlowing(true);
                perola.setVelocity(vec.multiply(5));
                player.setCooldown(Reliquias.guardiao.getType(),200);
            }
        }else if(item != null && item.isSimilar(Reliquias.guardiaominer) && !player.hasCooldown(Reliquias.guardiaominer.getType())){
            if(event.getClickedBlock() != null){
                Block block = event.getClickedBlock();
                if(block.getType() == Material.DEEPSLATE || block.getType() == Material.STONE){
                    Location l = event.getClickedBlock().getLocation();
                    World w = event.getClickedBlock().getWorld();
                    player.setCooldown(Reliquias.guardiaominer.getType(),1200);
                    ReliquiasEvent.mina(w,l,player,true);
                }else if(!player.hasCooldown(Reliquias.guardiaominer.getType())){
                    player.sendMessage("Só pode colocar tnt na ardosia ou na pedra");
                }else{
                    player.sendMessage("Aguarde a tnt ser feita!");
                }
            }
        }
    }
}