package me.dantesys.valentCity;

import me.dantesys.valentCity.commands.giveItems;
import me.dantesys.valentCity.events.reliquiasevents;
import me.dantesys.valentCity.items.reliquias;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public final class ValentCity extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        reliquias.init();
        getCommand("kitrq").setExecutor(new giveItems());
        getServer().getPluginManager().registerEvents(new reliquiasevents(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Valent City]: Plugin Ativado!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Valent City]: Plugin Desativado!");
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
}
