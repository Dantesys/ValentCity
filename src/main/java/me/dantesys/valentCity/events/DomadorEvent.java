package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class DomadorEvent implements Listener {
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.domador)) {
                sumonalobo(atacantepl,presa);
            }
        }
    }
    @EventHandler
    public void interacao(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getActiveItem();
        if(item.isSimilar(Reliquias.domador)){
            event.setCancelled(true);
            Entity entity = event.getRightClicked();
            if(entity instanceof Tameable doma){
                if(doma.isTamed()){
                    player.sendMessage("Você não pode domar animais já domados");
                }
                doma.setOwner(player);
                doma.setTamed(true);
            }
        }
    }
    @EventHandler
    public void interagir(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(item != null && item.isSimilar(Reliquias.domador)){
            if(action.isRightClick()){
                Material bloco = Objects.requireNonNull(event.getClickedBlock()).getType();
                if(bloco.equals(Material.SPAWNER)){
                    CreatureSpawner spawner = (CreatureSpawner) event.getClickedBlock().getState();
                    spawner.setType(Material.SPAWNER);
                    EntityType spawnedType = spawner.getSpawnedType();
                    if(spawnedType != null){
                        player.getInventory().addItem(makeSpawnerItem(spawnedType));
                        player.breakBlock(event.getClickedBlock());
                    }else{
                        player.sendMessage("Spawner é sem tipo");
                    }
                }
            }
        }
    }
    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        final ItemStack hand = event.getItemInHand();
        if (hand.getType() == Material.AIR) return;
        ItemMeta meta = hand.getItemMeta();
        NamespacedKey key = new NamespacedKey(ValentCity.getPlugin(ValentCity.class), "SPAWNER_ENTITY_TYPE");
        if (!meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;
        EntityType type = EntityType.valueOf(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        final Block placedBlock = event.getBlockPlaced();
        if (placedBlock.getType() != Material.SPAWNER)
            placedBlock.setType(Material.SPAWNER);
        CreatureSpawner creatureSpawner = (CreatureSpawner) placedBlock.getState();
        creatureSpawner.setSpawnedType(type);
        creatureSpawner.update();
    }
    public void sumonalobo(Player player,Entity entity) {
        EntityEquipment equip = player.getEquipment();
        ItemStack hand = null;
        boolean main = true;
        if (equip.getItemInMainHand().getType() == Material.STICK) {
            hand = equip.getItemInMainHand();
            if (!hand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                hand = null;
            }
        }
        if (equip.getItemInOffHand().getType() == Material.STICK) {
            hand = equip.getItemInOffHand();
            if (!hand.getEnchantments().containsKey(Enchantment.SILK_TOUCH)) {
                hand = null;
            }
            main = false;
        }
        if (hand != null) {
            boolean finalMain = main;
            ItemStack finalHand = Reliquias.domador;
            ItemStack armadura = new ItemStack(Material.WOLF_ARMOR);
            ItemMeta meta = armadura.getItemMeta();
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE.getKey(),9, AttributeModifier.Operation.ADD_NUMBER));
            armadura.setItemMeta(meta);
            Wolf wolf = (Wolf) player.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
            wolf.setOwner(player);
            wolf.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(200);
            wolf.attack(entity);
            wolf.setTamed(true);
            wolf.getEquipment().setChestplate(armadura);
            Wolf wolf2 = (Wolf) player.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
            wolf2.setOwner(player);
            wolf2.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf2.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
            wolf2.attack(entity);
            wolf2.setTamed(true);
            wolf2.getEquipment().setChestplate(armadura);
            Wolf wolf3 = (Wolf) player.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
            wolf3.setOwner(player);
            wolf3.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf3.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
            wolf3.attack(entity);
            wolf3.setTamed(true);
            wolf3.getEquipment().setChestplate(armadura);
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class), 30,
                    () -> {
                        player.sendMessage("Lobo Ativado!");
                        player.getEquipment().setItemInMainHand(null);
                        wolf.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                        wolf2.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                        wolf3.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1));
                    },() -> {
                if (finalMain) {
                    player.getEquipment().setItemInMainHand(finalHand);
                } else {
                    player.getEquipment().setItemInOffHand(finalHand);
                }
                wolf.remove();
                wolf2.remove();
                wolf3.remove();
            },(t) -> {
                wolf.customName(Component.text("Lider ("+(t.getSegundosRestantes())+"s)"));
                wolf2.customName(Component.text("Soldado ("+(t.getSegundosRestantes())+"s)"));
                wolf3.customName(Component.text("Soldado ("+(t.getSegundosRestantes())+"s)"));
                wolf.setCustomNameVisible(true);
                wolf2.setCustomNameVisible(true);
                wolf3.setCustomNameVisible(true);
                player.sendMessage("Falta "+ (t.getSegundosRestantes()) + " Segundo para reativar o lobo");
            }
            );
            timer.scheduleTimer(20L);
        }
    }
    private ItemStack makeSpawnerItem(EntityType entityType) {
        final ItemStack itemStack = new ItemStack(Material.SPAWNER, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(ValentCity.getPlugin(ValentCity.class), "SPAWNER_ENTITY_TYPE"), PersistentDataType.STRING, entityType.name());
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}