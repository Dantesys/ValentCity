package me.dantesys.valentCity.events;

import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.Random;

public class AlquimiaEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.alquimia)){
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
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,-1,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE,-1,1));
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
                if(item != null && item.isSimilar(Reliquias.alquimia)){
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.alquimia)) {
                if(presa instanceof LivingEntity toma){
                    Random rd = new Random();
                    int ver = rd.nextInt(0,200);
                    if(toma instanceof Monster monster){
                        if(ver<=30){
                            Location l = monster.getLocation();
                            World w = monster.getWorld();
                            if(ver==1) w.spawn(l, Armadillo.class);
                            else if(ver==2) w.spawn(l, Axolotl.class);
                            else if(ver==3) w.spawn(l, Bee.class);
                            else if(ver==4) w.spawn(l, Camel.class);
                            else if(ver==5) w.spawn(l, Cat.class);
                            else if(ver==6) w.spawn(l, Chicken.class);
                            else if(ver==7) w.spawn(l, Cow.class);
                            else if(ver==8) w.spawn(l, Donkey.class);
                            else if(ver==9) w.spawn(l, Fox.class);
                            else if(ver==10) w.spawn(l, Frog.class);
                            else if(ver==11) w.spawn(l, Goat.class);
                            else if(ver==12) w.spawn(l, Horse.class);
                            else if(ver==13) w.spawn(l, Llama.class);
                            else if(ver==14) w.spawn(l, Mule.class);
                            else if(ver==15) w.spawn(l, MushroomCow.class);
                            else if(ver==16) w.spawn(l, Ocelot.class);
                            else if(ver==17) w.spawn(l, Panda.class);
                            else if(ver==18) w.spawn(l, Parrot.class);
                            else if(ver==19) w.spawn(l, Pig.class);
                            else if(ver==20) w.spawn(l, PolarBear.class);
                            else if(ver==21) w.spawn(l, Rabbit.class);
                            else if(ver==22) w.spawn(l, Sheep.class);
                            else if(ver==23) w.spawn(l, SkeletonHorse.class);
                            else if(ver==24) w.spawn(l, Sniffer.class);
                            else if(ver==25) w.spawn(l, Steerable.class);
                            else if(ver==26) w.spawn(l, Strider.class);
                            else if(ver==27) w.spawn(l, TraderLlama.class);
                            else if(ver==28) w.spawn(l, Turtle.class);
                            else if(ver==29) w.spawn(l, Wolf.class);
                            else if(ver==30) w.spawn(l, ZombieHorse.class);
                            monster.remove();
                        }
                    }else if(toma instanceof Animals animal){
                        if(ver<=30){
                            Location l = animal.getLocation();
                            World w = animal.getWorld();
                            if(ver==1) w.spawn(l, Blaze.class);
                            else if(ver==2) w.spawn(l, Bogged.class);
                            else if(ver==3) w.spawn(l, Breeze.class);
                            else if(ver==4) w.spawn(l, CaveSpider.class);
                            else if(ver==5) w.spawn(l, Creeper.class);
                            else if(ver==6) w.spawn(l, Drowned.class);
                            else if(ver==7) w.spawn(l, Enderman.class);
                            else if(ver==8) w.spawn(l, Endermite.class);
                            else if(ver==9) w.spawn(l, Guardian.class);
                            else if(ver==10) w.spawn(l, Husk.class);
                            else if(ver==11) w.spawn(l, Illager.class);
                            else if(ver==12) w.spawn(l, Piglin.class);
                            else if(ver==13) w.spawn(l, PiglinBrute.class);
                            else if(ver==14) w.spawn(l, PigZombie.class);
                            else if(ver==15) w.spawn(l, Pillager.class);
                            else if(ver==16) w.spawn(l, Raider.class);
                            else if(ver==17) w.spawn(l, Ravager.class);
                            else if(ver==18) w.spawn(l, Silverfish.class);
                            else if(ver==19) w.spawn(l, Skeleton.class);
                            else if(ver==20) w.spawn(l, Spider.class);
                            else if(ver==21) w.spawn(l, Stray.class);
                            else if(ver==22) w.spawn(l, Vex.class);
                            else if(ver==23) w.spawn(l, Vindicator.class);
                            else if(ver==24) w.spawn(l, Warden.class);
                            else if(ver==25) w.spawn(l, Witch.class);
                            else if(ver==26) w.spawn(l, WitherSkeleton.class);
                            else if(ver==27) w.spawn(l, Zoglin.class);
                            else if(ver==28) w.spawn(l, Zombie.class);
                            else if(ver==29) w.spawn(l, ZombieVillager.class);
                            else if(ver==30) w.spawn(l, Illusioner.class);
                            animal.remove();
                        }
                    }else{
                        event.setDamage(10);
                    }
                }
            }
        }
    }
    @EventHandler
    public void alquimia(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item != null && item.isSimilar(Reliquias.alquimia)){
            event.setCancelled(true);
            if(!player.hasCooldown(Reliquias.alquimia.getType())){
                event.setCancelled(true);
                Vector vec = player.getEyeLocation().getDirection();
                ThrownPotion tp = player.launchProjectile(ThrownPotion.class);
                tp.setMetadata("alquimia",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),true));
                tp.setVelocity(vec.multiply(5));
                player.setCooldown(Reliquias.alquimia.getType(),300);
            }
        }
    }
    @EventHandler
    public void pocao(PotionSplashEvent event) {
        ThrownPotion pocao = event.getEntity();
        Collection<LivingEntity> atigidos = event.getAffectedEntities();
        ProjectileSource ps = pocao.getShooter();
        if(pocao.hasMetadata("alquimia") && ps instanceof Player atirador){
            boolean alquimia = pocao.getMetadata("alquimia").getFirst().asBoolean();
            if(alquimia && event.getHitBlock() != null){
                Block bloco = event.getHitBlock();
                event.setCancelled(true);
                Random rd = new Random();
                int ver = rd.nextInt(0,200);
                if(ver<=25)bloco.setType(Material.COAL_ORE);
                else if(ver<=45)bloco.setType(Material.COPPER_ORE);
                else if(ver<=65)bloco.setType(Material.IRON_ORE);
                else if(ver<=80)bloco.setType(Material.GOLD_ORE);
                else if(ver<=90)bloco.setType(Material.LAPIS_ORE);
                else if(ver<=95)bloco.setType(Material.REDSTONE_ORE);
                else if(ver<=99)bloco.setType(Material.DIAMOND_ORE);
                else if(ver==100)bloco.setType(Material.ANCIENT_DEBRIS);
                else{
                    while(!atigidos.iterator().hasNext()){
                        LivingEntity vivo = atigidos.iterator().next();
                        if(vivo instanceof Player player){
                            ReliquiasEvent.efeitos(player, player.getName().equals(atirador.getName()),1000,1);
                        }else{
                            ReliquiasEvent.efeitos(vivo,false,1000,1);
                        }
                        atigidos.remove(vivo);
                    }
                }
            }else if(alquimia){
                while(!atigidos.iterator().hasNext()){
                    LivingEntity vivo = atigidos.iterator().next();
                    if(vivo instanceof Player player){
                        ReliquiasEvent.efeitos(player, player.getName().equals(atirador.getName()),1000,1);
                    }else{
                        ReliquiasEvent.efeitos(vivo,false,1000,1);
                    }
                    atigidos.remove(vivo);
                }
            }
        }
    }
}