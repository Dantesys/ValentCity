package me.dantesys.valentCity.events;
import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

public class HulkEvent implements Listener {
    @EventHandler
    public void terremoto(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        ItemStack calca = player.getInventory().getChestplate();
        if(calca != null && calca.isSimilar(Reliquias.hulk) && !player.hasCooldown(Reliquias.hulk.getType())){
            int range = 20;
            final double finalDamage = 2*(41-player.getHealth());
            final Location location = player.getLocation();
            final World world = player.getWorld();
            player.sendActionBar(Component.text("SMASH!"));
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 5,
                    ()->{
                    },()->{},(t)->{
                double area = (double) range /(t.getSegundosRestantes());
                for (double i = 0; i <= 2*Math.PI*area; i += 0.05) {
                    double x = (area * Math.cos(i)) + location.getX();
                    double z = (location.getZ() + area * Math.sin(i));
                    Location particle = new Location(world, x, location.getY() + 1, z);
                    world.spawnParticle(Particle.SWEEP_ATTACK,particle,1);
                }
                Collection<Entity> pressf = location.getWorld().getNearbyEntities(location,area,2,area);
                while(pressf.iterator().hasNext()){
                    Entity surdo = pressf.iterator().next();
                    if(surdo instanceof LivingEntity vivo){
                        if(!vivo.getEquipment().getChestplate().isSimilar(Reliquias.hulk)){
                            vivo.damage(finalDamage);
                        }
                    }
                    pressf.remove(surdo);
                }
            });
            timer.scheduleTimer(5L);
            player.setCooldown(Reliquias.hulk.getType(),600);
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getChestplate() != null && atacantepl.getInventory().getChestplate().isSimilar(Reliquias.hulk)) {
                event.setDamage(event.getDamage()*(41-atacantepl.getHealth()));
            }
        }
    }
}