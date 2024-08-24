package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class EnxadaEvent implements Listener {
    HashMap<Player, Team> tempTeams = new HashMap<>();
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.enxada)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                glowColor(player, NamedTextColor.BLACK);
            }else{
                if(omao.isSimilar(Reliquias.totem)){
                    ReliquiasEvent.limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                }else{
                    stopGlowing(player);
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException ignored){

        }try{
            if(omao.isSimilar(Reliquias.totem)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
            }else{
                if(item != null && item.isSimilar(Reliquias.enxada)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1,true,false));
                    glowColor(player, NamedTextColor.BLACK);
                }else{
                    stopGlowing(player);
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void matou(EntityDeathEvent e) {
        Entity dead = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            if(dead instanceof Monster) {
                ItemStack is = null;
                World w = dead.getWorld();
                Location l = dead.getLocation();
                if(dead.getType() == EntityType.WITHER){
                    if (killer.getInventory().getItemInMainHand().equals(Reliquias.enxada)) {
                        is = Reliquias.life;
                        is.add(2);
                    }
                }else if(dead.getType() == EntityType.ELDER_GUARDIAN){
                    if (killer.getInventory().getItemInMainHand().equals(Reliquias.enxada)) {
                        is = Reliquias.life;
                        is.add();
                    }
                }else if(dead.getType() == EntityType.ENDER_DRAGON){
                    if (killer.getInventory().getItemInMainHand().equals(Reliquias.enxada)) {
                        is = Reliquias.life;
                        is.add(3);
                    }
                }else if(dead.getType() == EntityType.WARDEN){
                    if (killer.getInventory().getItemInMainHand().equals(Reliquias.enxada)) {
                        is = Reliquias.life;
                        is.add(4);
                    }
                }else{
                    if (killer.getInventory().getItemInMainHand().equals(Reliquias.enxada)) {
                        is = Reliquias.life;
                    }
                }
                if(is!=null){
                    w.dropItemNaturally(l, is);
                }
            }
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.enxada)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 40, 1));
                }
            }
        }
    }
    @EventHandler
    public void corte(PlayerInteractEvent event){
        Player player = event.getPlayer();
        Action action = event.getAction();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.enxada)){
            if(action.isLeftClick() && !player.hasCooldown(Reliquias.enxada.getType())){
                int slot = -1;
                if(player.getInventory().contains(Reliquias.life)){
                    slot = player.getInventory().first(Reliquias.life);
                }
                if(slot>=0){
                    ItemStack item = player.getInventory().getItem(slot);
                    if(item!=null){
                        item.subtract(1);
                        player.getInventory().setItem(slot,item);
                        player.updateInventory();
                        int range = 50;
                        double damage = 1;
                        AttributeInstance ataque = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                        if(ataque != null){
                            damage = ataque.getBaseValue();
                        }
                        final int finalRange = range;
                        final double finalDamage = damage;
                        final Location location = player.getLocation();
                        final boolean[] passa = {true};
                        final Vector direction = location.getDirection().normalize();
                        final double[] tp = {0};
                        Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 10,
                                ()->{
                                },()-> {
                        },(t)->{
                            tp[0] = tp[0]+3.4;
                            double x = direction.getX()*tp[0];
                            double y = direction.getY()*tp[0]+1.4;
                            double z = direction.getZ()*tp[0];
                            location.add(x,y,z);
                            location.getWorld().spawnParticle(Particle.SOUL,location,1,0,0,0,0);
                            passa[0] = location.getBlock().isPassable();
                            location.getWorld().playSound(location, Sound.ENTITY_PLAYER_ATTACK_SWEEP,0.5f,0.7f);
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
                        player.setCooldown(Reliquias.enxada.getType(),600);
                    }
                }
            }
        }
    }
    public void glowColor(Player player, NamedTextColor color) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.registerNewTeam("temp-color-team-" + UUID.randomUUID());
        team.color(color);
        team.addEntry(PlainTextComponentSerializer.plainText().serialize(player.displayName()));
        tempTeams.put(player, team);
        player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, -1, 0));
    }
    public void stopGlowing(Player player) {
        player.removePotionEffect(PotionEffectType.GLOWING);
        tempTeams.get(player).unregister();
        tempTeams.remove(player);
    }
}