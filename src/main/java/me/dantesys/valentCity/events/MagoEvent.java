package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.Random;

public class MagoEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.mago)){
                ReliquiasEvent.limparEfeito(player);
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
                    ReliquiasEvent.efeitos(player,true,-1,1);
                }
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
                if(item != null && item.isSimilar(Reliquias.mago)){
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
                    else ReliquiasEvent.efeitos(player,true,-1,1);
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.mago)) {
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
                    else ReliquiasEvent.efeitos(toma,false,tempo,power);
                }
            }
        }
    }
    @EventHandler
    public void magia(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item != null && item.isSimilar(Reliquias.mago)){
            event.setCancelled(true);
            if(!player.hasCooldown(Reliquias.mago.getType())){
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
                    player.setCooldown(Reliquias.mago.getType(),100);
                }else if(ver<=30){
                    player.sendMessage("Hinotama");
                    Fireball fire = player.launchProjectile(Fireball.class);
                    fire.setGlowing(true);
                    fire.setMetadata("fire",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),(double) 10));
                    fire.setDirection(vec.multiply(2));
                    fire.setVelocity(vec.multiply(2));
                    fire.setYield(4);
                    player.setCooldown(Reliquias.mago.getType(),200);
                }else if(ver<=45){
                    player.sendMessage("Yukidaruma");
                    Snowball bola = player.launchProjectile(Snowball.class);
                    bola.setGlowing(true);
                    bola.setVelocity(vec.multiply(5));
                    bola.setMetadata("freeze",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class), 10));
                    player.setCooldown(Reliquias.mago.getType(),100);
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
                    player.setCooldown(Reliquias.mago.getType(),600);
                }else if(ver<=75){
                    player.sendMessage("Furainguheddo");
                    WitherSkull wither = player.launchProjectile(WitherSkull.class);
                    wither.setGlowing(true);
                    wither.setDirection(vec.multiply(2));
                    wither.setVelocity(vec.multiply(2));
                    wither.setCharged(true);
                    player.setCooldown(Reliquias.mago.getType(),300);
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
                    player.setCooldown(Reliquias.mago.getType(),200);
                }else{
                    player.sendMessage("Pōshonmikkusu");
                    ThrownPotion tp = player.launchProjectile(ThrownPotion.class);
                    tp.setMetadata("magia",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),true));
                    tp.setVelocity(vec.multiply(5));
                    player.setCooldown(Reliquias.mago.getType(),300);
                }
            }
        }
    }
    @EventHandler
    public void pocao(PotionSplashEvent event) {
        ThrownPotion pocao = event.getEntity();
        Collection<LivingEntity> atigidos = event.getAffectedEntities();
        ProjectileSource ps = pocao.getShooter();
        if(pocao.hasMetadata("magia") && ps instanceof Player atirador){
            boolean magia = pocao.getMetadata("magia").getFirst().asBoolean();
            if(magia){
                Random rd = new Random();
                while(!atigidos.iterator().hasNext()){
                    int forca = rd.nextInt(1,4);
                    int tempo = rd.nextInt(1,60);
                    tempo = tempo*20;
                    LivingEntity vivo = atigidos.iterator().next();
                    if(vivo instanceof Player player){
                        ReliquiasEvent.efeitos(player, player.getName().equals(atirador.getName()),tempo,forca);
                    }else{
                        ReliquiasEvent.efeitos(vivo,false,tempo,forca);
                    }
                    atigidos.remove(vivo);
                }
            }
        }
    }
}