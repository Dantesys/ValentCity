package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class ReliquiasEvent implements Listener {
    FileConfiguration config = ValentCity.getPlugin(ValentCity.class).getConfig();
    @EventHandler
    public void aumento(PlayerItemConsumeEvent event){
        Player player = event.getPlayer();
        ItemStack is = event.getItem();
        if(is.isSimilar(Reliquias.power)){
            double dano = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).getValue();
            double limite = config.getDouble("limite_dano");
            if(dano+1>limite){
                player.sendActionBar(Component.text("Sem efeito, você atingiu o limite"));
                return;
            }
            double armor = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).getValue();
            double armortoug = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).getValue();
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(dano+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR)).setBaseValue(armor+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS)).setBaseValue(armortoug+1);
            player.sendActionBar(Component.text("Força: "+dano));
        }else if(is.isSimilar(Reliquias.life)){
            double vida = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
            double limite = config.getDouble("limite_vida");
            if(vida+1>limite){
                player.sendActionBar(Component.text("Sem efeito, você atingiu o limite"));
                return;
            }
            double abs = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_ABSORPTION)).getValue();
            double oxy = Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_OXYGEN_BONUS)).getValue();
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(vida+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_ABSORPTION)).setBaseValue(abs+1);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_OXYGEN_BONUS)).setBaseValue(oxy+1);
            player.sendActionBar(Component.text("Vida: "+vida));
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
            pressf.sendActionBar(Component.text(""));
        }
    }
    @EventHandler
    public void tpEndeperal(PlayerTeleportEvent event){
        Player p = event.getPlayer();
        if(event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL){
            if(p.getInventory().getItemInMainHand().isSimilar(Reliquias.mago)){
                event.setCancelled(true);
                p.setNoDamageTicks(10);
                p.teleport(event.getTo());
            }
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Arrow flecha) {
            if(flecha.hasMetadata("magic")) {
                double damage = flecha.getMetadata("magic").getFirst().asDouble();
                event.setDamage(event.getDamage()+damage);
            }
        }
        if(event.getDamager() instanceof Snowball bola) {
            if(bola.hasMetadata("freeze")) {
                int gelo = bola.getMetadata("freeze").getFirst().asInt();
                event.getEntity().setFreezeTicks(event.getEntity().getMaxFreezeTicks()*gelo);
            }
        }
        if(event.getDamager() instanceof Fireball fogo) {
            if(fogo.hasMetadata("fire")) {
                int tempo = fogo.getMetadata("fire").getFirst().asInt();
                event.getEntity().setFireTicks(event.getEntity().getMaxFreezeTicks()*tempo);
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
    public static void limparEfeito(Player player){
        for (PotionEffect effect : player.getActivePotionEffects()){
            if(effect.getDuration()<=-1){
                player.removePotionEffect(effect.getType());
            }
        }
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(1);
    }
    public static void efeitos(LivingEntity toma, boolean bom, int tempo, int power){
        if(bom){
            toma.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.HASTE,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.LUCK,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE,tempo,power));
        }else{
            toma.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.POISON,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.UNLUCK,tempo,power));
            toma.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS,tempo,power));
        }
    }
    public static void mina(World w, Location l, Player player,boolean meta) {
        TNTPrimed tnt = w.spawn(l, TNTPrimed.class);
        tnt.setFuseTicks(10000);
        tnt.setMetadata("guardiao",new FixedMetadataValue(ValentCity.getPlugin(ValentCity.class),meta));
        tnt.setYield(10);
        Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class),10,
                () -> player.sendMessage("Dinamite ativada!"),
                () -> {
                    tnt.getLocation().getWorld().createExplosion(tnt.getLocation(),10,false,true);
                    tnt.remove();
                },
                (t) -> {
                    player.sendActionBar(Component.text("Falta "+ (t.getSegundosRestantes()) + " Segundo para explosão!"));
                    tnt.customName(Component.text((t.getSegundosRestantes())+"s"));
                    tnt.setCustomNameVisible(true);
                }
        );
        timer.scheduleTimer(20L);
    }
}