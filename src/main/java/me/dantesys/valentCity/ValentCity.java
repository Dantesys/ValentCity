package me.dantesys.valentCity;

import io.papermc.paper.event.player.PlayerStopUsingItemEvent;
import me.dantesys.valentCity.commands.giveItems;
import me.dantesys.valentCity.events.reliquiasevents;
import me.dantesys.valentCity.items.reliquias;
import org.bukkit.*;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public final class ValentCity extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        reliquias.init();
        getCommand("reliquia").setExecutor(new giveItems());
        getServer().getPluginManager().registerEvents(new reliquiasevents(), this);
        getServer().getPluginManager().registerEvents(this, this);
        ItemStack bundle = new ItemStack(Material.BUNDLE);
        ItemStack spy1 = new ItemStack(reliquias.spy_modelo1);
        ItemStack spy2 = new ItemStack(reliquias.spy_modelo2);
        ItemStack tri1 = new ItemStack(reliquias.tridente_modelo1);
        ItemStack tri2 = new ItemStack(reliquias.tridente_modelo2);
        ShapedRecipe bundle_recipe = new ShapedRecipe(bundle);
        ShapelessRecipe spy1_recipe = new ShapelessRecipe(spy1);
        ShapelessRecipe spy2_recipe = new ShapelessRecipe(spy2);
        ShapelessRecipe tri1_recipe = new ShapelessRecipe(tri1);
        ShapelessRecipe tri2_recipe = new ShapelessRecipe(tri2);
        bundle_recipe.shape("lll","cbc","ccc");
        bundle_recipe.setIngredient('l', Material.STRING);
        bundle_recipe.setIngredient('c', Material.LEATHER);
        bundle_recipe.setIngredient('b', Material.CHEST);
        spy1_recipe.addIngredient(reliquias.spy_modelo2);
        spy2_recipe.addIngredient(reliquias.spy_modelo1);
        tri1_recipe.addIngredient(reliquias.tridente_modelo2);
        tri2_recipe.addIngredient(reliquias.tridente_modelo1);
        getServer().addRecipe(spy1_recipe);
        getServer().addRecipe(spy2_recipe);
        getServer().addRecipe(tri1_recipe);
        getServer().addRecipe(tri2_recipe);
        getServer().addRecipe(bundle_recipe);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Valent City]: Plugin Ativado!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().clearRecipes();
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Valent City]: Plugin Desativado!");
    }
    public void viewPL(@NotNull Player player, LivingEntity entity) {
        Location local = player.getLocation();
        Temporizador timer = new Temporizador(ValentCity.this,15,
            () -> {
                player.sendMessage("Visão Ativada!");
                player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 300, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 10,true,false));
                player.setSpectatorTarget(entity);
            },() -> {
                player.setSpectatorTarget(null);
                player.setGameMode(GameMode.SURVIVAL);
                player.teleport(local);
            },(t) -> player.sendMessage("Falta "+ (t.getSecondsLeft()) + " Segundo para desativar a visão")
            );
        timer.scheduleTimer();
    }
    @EventHandler
    public void onTotem(EntityResurrectEvent e) {
        Entity entity = e.getEntity();
        if (entity instanceof LivingEntity) {
            LivingEntity deadEntity = (LivingEntity)entity;
            EntityEquipment equip = deadEntity.getEquipment();
            ItemStack hand = null;
            boolean main = true;
            if (equip.getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
                hand = equip.getItemInMainHand();
                if (!hand.getEnchantments().containsKey(Enchantment.INFINITY)) {
                    hand = null;
                }
                main = true;
            }
            if (equip.getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
                hand = equip.getItemInOffHand();
                if (!hand.getEnchantments().containsKey(Enchantment.INFINITY)) {
                    hand = null;
                }
                main = false;
            }
            if (hand != null) {
                boolean finalMain = main;
                ItemStack finalHand = reliquias.totem;
                Temporizador timer = new Temporizador(ValentCity.this,
                        5,
                        () -> {
                            deadEntity.sendMessage("Totem Ativado!");
                            deadEntity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100,5),true);
                        },
                        () -> {
                            if (finalMain) {
                                deadEntity.getEquipment().setItemInMainHand(finalHand);
                            } else {
                                deadEntity.getEquipment().setItemInOffHand(finalHand);
                            }
                        },
                        (t) -> deadEntity.sendMessage("Falta "+ (t.getSecondsLeft()) + " Segundo para reativar o totem")
                );
                timer.scheduleTimer();
            }
        }
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.tridente_modelo1) || atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.tridente_modelo2)) {
                if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
                    lepresa.setRemainingAir(0);
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.spy_modelo1)) {
                if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 5));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.spy_modelo2)) {
                if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 5));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }
        }
    }
    @EventHandler
    public void vcviu(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item != null && item.isSimilar(reliquias.spy_modelo1)){
            World world = player.getWorld();
            List<Player> lista = world.getPlayers();
            for(int i=0;i<lista.size();i++){
                if(player.canSee(lista.get(i))){
                    viewPL(player,lista.get(i));
                }
            }
        }else if(item != null && item.isSimilar(reliquias.vento)){
            player.getInventory().addItem(reliquias.vento);
        }
    }
}
