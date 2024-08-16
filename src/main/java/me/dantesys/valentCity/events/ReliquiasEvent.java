package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class ReliquiasEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Seja bem vindo");
        for(Player esconder: getServer().getOnlinePlayers()){
            Player paraesc = getPlayer("HeriteHunter");
            if(paraesc!=null){
                esconder.hidePlayer(ValentCity.getPlugin(ValentCity.class),paraesc);
            }
        }
    }
    @EventHandler
    public void aumento(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack is = event.getItem();
        if(is.isSimilar(Reliquias.power)){
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
        }else if(is.isSimilar(Reliquias.life)){
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
    public void morreu(EntityDeathEvent e) {
        Entity dead = e.getEntity();
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
    }
    @EventHandler
    public void acertou(ProjectileHitEvent event){
        if(event.getEntity() instanceof Snowball bola){
            if(bola.hasMetadata("freeze")){
                int gelo = bola.getMetadata("freeze").getFirst().asInt();
                if(gelo>0 && event.getHitBlock() != null){
                    event.getHitBlock().setType(Material.BLUE_ICE);
                }
            }
        }
        if(event.getEntity() instanceof Fireball bola){
            if(bola.hasMetadata("fire")){
                event.setCancelled(true);
                int fogo = bola.getMetadata("fire").getFirst().asInt();
                if(fogo>0 && event.getHitBlock() != null){
                    event.getHitBlock().setType(Material.MAGMA_BLOCK);
                    event.getEntity().remove();
                }
            }
        }
        if(event.getEntity() instanceof Arrow flecha){
            if(flecha.hasMetadata("magic")){
                int magic = flecha.getMetadata("magic").getFirst().asInt();
                if(magic>0 && event.getHitBlock() != null){
                    event.getHitBlock().setType(Material.BUDDING_AMETHYST);
                    event.getEntity().remove();
                }
            }
        }
    }
    @EventHandler
    public static void punicao(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item != null && item.isSimilar(Reliquias.heritehunter) && !player.hasCooldown(Reliquias.heritehunter.getType())){
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
            if(hb1 != null && hb1.isSimilar(Reliquias.heritehunter)){
                player.setCooldown(Reliquias.heritehunter.getType(),1200);
                final Player[] alvo = {null};
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                AtomicReference<ItemStack> cap = new AtomicReference<>();
                AtomicReference<ItemStack> pei = new AtomicReference<>();
                AtomicReference<ItemStack> cal = new AtomicReference<>();
                AtomicReference<ItemStack> bot = new AtomicReference<>();
                Temporizador timer2 = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{
                    alvo[0].showPlayer(ValentCity.getPlugin(ValentCity.class),player);
                    alvo[0].getInventory().setHelmet(null);
                    alvo[0].getInventory().setChestplate(null);
                    alvo[0].getInventory().setLeggings(null);
                    alvo[0].getInventory().setBoots(null);
                    alvo[0].sendMessage("Maldição Aplicada!");
                    alvo[0].addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,20,1));
                    player.sendMessage("Maldição Aplicada!");
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,20,1));
                },()->{
                    alvo[0].hidePlayer(ValentCity.getPlugin(ValentCity.class),player);
                    alvo[0].getInventory().setHelmet(cap.get());
                    alvo[0].getInventory().setChestplate(pei.get());
                    alvo[0].getInventory().setLeggings(cal.get());
                    alvo[0].getInventory().setBoots(bot.get());
                },(t)->{
                    alvo[0].sendMessage("Sobreviva por "+t.getSegundosRestantes()+"segundos!");
                    player.sendMessage("Se esconda por "+t.getSegundosRestantes()+"segundos!");
                });
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.heritehunter.getType(),0);
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
                            alvo[0] = vivo;
                            timer2.scheduleTimer(20L);
                            cap.set(vivo.getInventory().getHelmet());
                            pei.set(vivo.getInventory().getChestplate());
                            cal.set(vivo.getInventory().getLeggings());
                            bot.set(vivo.getInventory().getBoots());
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
            else if(hb2 != null && hb2.isSimilar(Reliquias.heritehunter)){
                player.setCooldown(Reliquias.heritehunter.getType(),600);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.heritehunter.getType(),0);
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
            else if(hb3 != null && hb3.isSimilar(Reliquias.heritehunter)){
                player.setCooldown(Reliquias.heritehunter.getType(),600);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                        ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.heritehunter.getType(),0);
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
            else if(hb4 != null && hb4.isSimilar(Reliquias.heritehunter)){
                player.setCooldown(Reliquias.heritehunter.getType(),600);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.heritehunter.getType(),0);
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
            else if(hb5 != null && hb5.isSimilar(Reliquias.heritehunter)){
                player.setCooldown(Reliquias.heritehunter.getType(),400);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.heritehunter.getType(),0);
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
            else if(hb6 != null && hb6.isSimilar(Reliquias.heritehunter)){
                player.setCooldown(Reliquias.heritehunter.getType(),1000);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.heritehunter.getType(),0);
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
            else if(hb7 != null && hb7.isSimilar(Reliquias.heritehunter)){
                player.setCooldown(Reliquias.heritehunter.getType(),1000);
                final int finalRange = 10;
                final Location location = player.getLocation();
                final Vector direction = location.getDirection().normalize();
                final double[] tp = {0};
                final boolean[] passa = {false};
                Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                ()->{},
                ()->{
                    if(!passa[0]){
                        player.setCooldown(Reliquias.heritehunter.getType(),0);
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
            else if(hb8 != null && hb8.isSimilar(Reliquias.heritehunter)){
                player.setCooldown(Reliquias.heritehunter.getType(),400);
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
                        player.setCooldown(Reliquias.heritehunter.getType(),0);
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
            else if(hb9 != null && hb9.isSimilar(Reliquias.heritehunter)){
                Vector vec = player.getEyeLocation().getDirection();
                EnderPearl perola = player.launchProjectile(EnderPearl.class);
                perola.setGlowing(true);
                perola.setVelocity(vec.multiply(5));
                player.setCooldown(Reliquias.heritehunter.getType(),200);
            }
        }
    }
    public static void limparEfeito(Player player){
        for (PotionEffect effect : player.getActivePotionEffects()){
            if(effect.getDuration()<=-1){
                player.removePotionEffect(effect.getType());
            }
        }
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(1);
    }
}