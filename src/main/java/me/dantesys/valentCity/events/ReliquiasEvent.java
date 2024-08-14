package me.dantesys.valentCity.events;

import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.*;

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
    public static void limparEfeito(Player player){
        for (PotionEffect effect : player.getActivePotionEffects()){
            if(effect.getDuration()<=-1){
                player.removePotionEffect(effect.getType());
            }
        }
        Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(1);
    }
}