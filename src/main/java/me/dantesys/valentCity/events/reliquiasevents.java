package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class reliquiasevents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Seja bem vindo");
    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player) {
            Player atacantepl = (Player) atacante;
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.espadamd)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 2));
                if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.enxada)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 40, 1));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.totem)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 5));
                if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                }
            }
        }
    }
    @EventHandler
    public void mao(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        efeitos(player);
    }
    @EventHandler
    public void mudamao(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        efeitos(player);
    }
    public void efeitos(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = board.registerNewTeam("Ceifador");
        if(player.getInventory().getItemInMainHand().isSimilar(reliquias.espadamd)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1));
        }else if(player.getInventory().getItemInMainHand().isSimilar(reliquias.totem)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, -1, 1));
        }else if(player.getInventory().getItemInMainHand().isSimilar(reliquias.enxada)){
            player.setGlowing(true);
            team.setColor(ChatColor.BLACK);
            team.addEntry(player.getUniqueId().toString());
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
        }else if(player.getInventory().getItemInOffHand().isSimilar(reliquias.espadamd)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1));
        }else if(player.getInventory().getItemInOffHand().isSimilar(reliquias.totem)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
        }else if(player.getInventory().getItemInOffHand().isSimilar(reliquias.enxada)){
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, -1, 1));
        }{
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.removePotionEffect(PotionEffectType.RESISTANCE);
            player.removePotionEffect(PotionEffectType.REGENERATION);
            player.removePotionEffect(PotionEffectType.SPEED);
            player.setGlowing(false);
            team.removeEntity(player);
        }
    }
    @EventHandler
    public void getLife(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            if(dead instanceof Monster) {
                if (killer.getInventory().getItemInMainHand().equals(reliquias.enxada)) {
                    killer.setMaxHealth(killer.getMaxHealth() + 1.0);
                }
            }
        }
        if (dead instanceof Player) {
            Player f = (Player) dead;
            if(f.getInventory().first(reliquias.enxada) != 0){
                f.setMaxHealth(20.0);
            }
        }
    }
}
