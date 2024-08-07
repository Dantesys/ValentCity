package me.dantesys.valentCity;

import me.dantesys.valentCity.commands.giveItems;
import me.dantesys.valentCity.events.reliquiasevents;
import me.dantesys.valentCity.items.reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;


public final class ValentCity extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        reliquias.init();
        Objects.requireNonNull(getCommand("reliquia")).setExecutor(new giveItems());
        Objects.requireNonNull(getCommand("livro")).setExecutor(new giveItems());
        getServer().getPluginManager().registerEvents(new reliquiasevents(), this);
        getServer().getPluginManager().registerEvents(this, this);
        NamespacedKey key_spy1 = new NamespacedKey(this, "SPY1");
        ItemStack spy1 = new ItemStack(reliquias.spy_modelo1);
        NamespacedKey key_spy2 = new NamespacedKey(this, "SPY2");
        ItemStack spy2 = new ItemStack(reliquias.spy_modelo2);
        NamespacedKey key_tri1 = new NamespacedKey(this, "TRI1");
        ItemStack tri1 = new ItemStack(reliquias.tridente_modelo1);
        NamespacedKey key_tri2 = new NamespacedKey(this, "TRI2");
        ItemStack tri2 = new ItemStack(reliquias.tridente_modelo2);
        NamespacedKey key_arc1 = new NamespacedKey(this, "ARC1");
        ItemStack arco1 = new ItemStack(reliquias.arco_modelo1);
        NamespacedKey key_arc2 = new NamespacedKey(this, "ARC2");
        ItemStack arco2 = new ItemStack(reliquias.arco_modelo2);
        NamespacedKey key_far1 = new NamespacedKey(this, "FAR1");
        ItemStack farm1 = new ItemStack(reliquias.farm_modelo1);
        NamespacedKey key_far2 = new NamespacedKey(this, "FAR2");
        ItemStack farm2 = new ItemStack(reliquias.farm_modelo2);
        NamespacedKey key_pic1 = new NamespacedKey(this, "PIC1");
        ItemStack pick1 = new ItemStack(reliquias.picareta_md1);
        NamespacedKey key_pic2 = new NamespacedKey(this, "PIC2");
        ItemStack pick2 = new ItemStack(reliquias.picareta_md2);
        NamespacedKey key_bk = new NamespacedKey(this, "BKINFO");
        ItemStack bk = new ItemStack(reliquias.livro);
        ShapelessRecipe spy1_recipe = new ShapelessRecipe(key_spy1,spy1);
        ShapelessRecipe spy2_recipe = new ShapelessRecipe(key_spy2,spy2);
        ShapelessRecipe tri1_recipe = new ShapelessRecipe(key_tri1,tri1);
        ShapelessRecipe tri2_recipe = new ShapelessRecipe(key_tri2,tri2);
        ShapelessRecipe arco1_recipe = new ShapelessRecipe(key_arc1,arco1);
        ShapelessRecipe arco2_recipe = new ShapelessRecipe(key_arc2,arco2);
        ShapelessRecipe farm1_recipe = new ShapelessRecipe(key_far1,farm1);
        ShapelessRecipe farm2_recipe = new ShapelessRecipe(key_far2,farm2);
        ShapelessRecipe pick1_recipe = new ShapelessRecipe(key_pic1,pick1);
        ShapelessRecipe pick2_recipe = new ShapelessRecipe(key_pic2,pick2);
        ShapelessRecipe bk_recipe = new ShapelessRecipe(key_bk,bk);
        spy1_recipe.addIngredient(reliquias.spy_modelo2);
        spy2_recipe.addIngredient(reliquias.spy_modelo1);
        tri1_recipe.addIngredient(reliquias.tridente_modelo2);
        tri2_recipe.addIngredient(reliquias.tridente_modelo1);
        arco1_recipe.addIngredient(reliquias.arco_modelo2);
        arco2_recipe.addIngredient(reliquias.arco_modelo1);
        farm1_recipe.addIngredient(reliquias.farm_modelo2);
        farm2_recipe.addIngredient(reliquias.farm_modelo1);
        pick1_recipe.addIngredient(reliquias.picareta_md2);
        pick2_recipe.addIngredient(reliquias.picareta_md1);
        bk_recipe.addIngredient(Material.BOOK);
        getServer().addRecipe(spy1_recipe);
        getServer().addRecipe(spy2_recipe);
        getServer().addRecipe(tri1_recipe);
        getServer().addRecipe(tri2_recipe);
        getServer().addRecipe(arco1_recipe);
        getServer().addRecipe(arco2_recipe);
        getServer().addRecipe(farm1_recipe);
        getServer().addRecipe(farm2_recipe);
        getServer().addRecipe(pick1_recipe);
        getServer().addRecipe(pick2_recipe);
        getServer().addRecipe(bk_recipe);
        getServer().getConsoleSender().sendMessage("§2[Valent City]: Plugin Ativado!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().clearRecipes();
        getServer().getConsoleSender().sendMessage("§4[Valent City]: Plugin Desativado!");
    }
    @EventHandler
    public void onTotem(EntityResurrectEvent e) {
        LivingEntity deadEntity = e.getEntity();
        EntityEquipment equip = deadEntity.getEquipment();
        ItemStack hand = null;
        boolean main = true;
        if (equip != null && equip.getItemInMainHand().getType() == Material.TOTEM_OF_UNDYING) {
            hand = equip.getItemInMainHand();
            if (!hand.getEnchantments().containsKey(Enchantment.INFINITY)) {
                hand = null;
            }
        }
        if (equip != null && equip.getItemInOffHand().getType() == Material.TOTEM_OF_UNDYING) {
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
                        deadEntity.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100,5));
                    },
                    () -> {
                        if (finalMain) {
                            deadEntity.getEquipment().setItemInMainHand(finalHand);
                        } else {
                            deadEntity.getEquipment().setItemInOffHand(finalHand);
                        }
                    },
                    (t) -> deadEntity.sendMessage("Falta "+ (t.getSecondsLeft()) + " Segundo para reativar")
            );
            timer.scheduleTimer();
        }
    }
    public void sumonarapoza(Player player,Entity entity) {
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
            ItemStack finalHand = reliquias.domador;
            ItemStack espada = new ItemStack(Material.NETHERITE_SWORD);
            ItemMeta meta = espada.getItemMeta();
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE.getKey(),9, AttributeModifier.Operation.ADD_NUMBER));
            meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,new AttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
            meta.addEnchant(Enchantment.SHARPNESS,10,true);
            meta.addEnchant(Enchantment.FIRE_ASPECT,10,true);
            espada.setItemMeta(meta);
            Fox wolf = (Fox) player.getWorld().spawnEntity(entity.getLocation(), EntityType.FOX);
            wolf.setFirstTrustedPlayer(player);
            wolf.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(200);
            wolf.attack(entity);
            wolf.setTarget((LivingEntity) entity);
            wolf.getEquipment().setItemInMainHand(espada);
            Fox wolf2 = (Fox) player.getWorld().spawnEntity(entity.getLocation(), EntityType.FOX);
            wolf2.setFirstTrustedPlayer(player);
            wolf2.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf2.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
            wolf2.attack(entity);
            wolf2.setTarget((LivingEntity) entity);
            wolf2.getEquipment().setItemInMainHand(espada);
            Fox wolf3 = (Fox) player.getWorld().spawnEntity(entity.getLocation(), EntityType.FOX);
            wolf3.setFirstTrustedPlayer(player);
            wolf3.registerAttribute(Attribute.GENERIC_MAX_HEALTH);
            Objects.requireNonNull(wolf3.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(100);
            wolf3.attack(entity);
            wolf3.setTarget((LivingEntity) entity);
            wolf3.getEquipment().setItemInMainHand(espada);
            Temporizador timer = new Temporizador(ValentCity.this, 30,
                    () -> {
                        player.sendMessage("Rapoza Ativado!");
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
                wolf.customName(Component.text("Lider ("+(t.getSecondsLeft())+"s)"));
                wolf2.customName(Component.text("Soldado ("+(t.getSecondsLeft())+"s)"));
                wolf3.customName(Component.text("Soldado ("+(t.getSecondsLeft())+"s)"));
                wolf.setCustomNameVisible(true);
                wolf2.setCustomNameVisible(true);
                wolf3.setCustomNameVisible(true);
                player.sendMessage("Falta "+ (t.getSecondsLeft()) + " Segundo para reativar");
            }
            );
            timer.scheduleTimer();
        }
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
            ItemStack finalHand = reliquias.domador;
            ItemStack armadura = new ItemStack(Material.WOLF_ARMOR);
            ItemMeta meta = armadura.getItemMeta();
            meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE,new AttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE.getKey(),9, AttributeModifier.Operation.ADD_NUMBER));
            meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,new AttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
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
            Temporizador timer = new Temporizador(ValentCity.this, 30,
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
                    wolf.customName(Component.text("Lider ("+(t.getSecondsLeft())+"s)"));
                    wolf2.customName(Component.text("Soldado ("+(t.getSecondsLeft())+"s)"));
                    wolf3.customName(Component.text("Soldado ("+(t.getSecondsLeft())+"s)"));
                    wolf.setCustomNameVisible(true);
                    wolf2.setCustomNameVisible(true);
                    wolf3.setCustomNameVisible(true);
                    player.sendMessage("Falta "+ (t.getSecondsLeft()) + " Segundo para reativar o lobo");
                }
            );
            timer.scheduleTimer();
        }
    }
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.espadamd)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.domador)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                if(ver<=50){
                    sumonalobo(atacantepl,presa);
                }else{
                    sumonarapoza(atacantepl,presa);
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.enxada)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 40, 1));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.totem)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 5));
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.tridente_modelo1)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.getWorld().strikeLightning(lepresa.getLocation());
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.tridente_modelo1) || atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.tridente_modelo2)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.setRemainingAir(0);
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.spy_modelo1)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 5));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.spy_modelo2)) {
                if (presa instanceof LivingEntity lepresa) {
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 100, 5));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 1));
                    lepresa.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 1));
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.farm_modelo1)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(presa instanceof Player){
                    atacantepl.sendMessage("Você não pode plantar jogadores!");
                }else{
                    if(presa instanceof LivingEntity vivo){
                        double hp = Objects.requireNonNull(vivo.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        if(vivo.getHealth()>(hp/2)){
                            atacantepl.sendMessage("Você ainda não pode plantar ele!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (hp/2))+" de vida");
                            return;
                        }
                        presa.remove();
                        atacantepl.sendMessage(presa.getName()+" agora está plantado!");
                        Location l2 = l.add(0,1,0);
                        l.add(0,-1,0);
                        if(ver<=25){
                            w.getBlockAt(l).setType(Material.FARMLAND);
                            w.getBlockAt(l2).setType(Material.WHEAT);
                        }else if(ver<=50){
                            w.getBlockAt(l).setType(Material.FARMLAND);
                            w.getBlockAt(l2).setType(Material.BEETROOTS);
                        }else if(ver<=75){
                            w.getBlockAt(l).setType(Material.FARMLAND);
                            w.getBlockAt(l2).setType(Material.POTATOES);
                        }else{
                            w.getBlockAt(l).setType(Material.FARMLAND);
                            w.getBlockAt(l2).setType(Material.CARROTS);
                        }
                    }
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.mago)) {
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
                    else{
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
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.farm_modelo2)) {
                if (presa instanceof Player) {
                    atacantepl.sendMessage("Você não pode pegar jogadores!");
                }else if(atacantepl.hasCooldown(reliquias.farm_modelo2.getType())){
                    atacantepl.sendMessage("Está e cooldown aguarde "+atacantepl.getCooldown(reliquias.farm_modelo2.getType()));
                }else if (presa instanceof LivingEntity lepresa) {
                    if (lepresa instanceof Tameable domado){
                        if(domado.isTamed()){
                            if(!(domado.getOwner() == atacantepl)){
                                atacantepl.sendMessage("Você não pode pegar pet de outros jogadores!");
                                return;
                            }
                        }
                    }
                    ItemStack item = new ItemStack(Objects.requireNonNull(MobsList.getEggType(lepresa)).getMaterial());
                    String nome = lepresa.getName();
                    ItemMeta meta = item.getItemMeta();
                    meta.displayName(Component.text(nome));
                    item.setItemMeta(meta);
                    if(lepresa instanceof Pig pig) {
                        if(pig.hasSaddle()) {
                            pig.getWorld().dropItem(pig.getLocation(), new ItemStack(Material.SADDLE, 1));
                        }
                    }
                    if(lepresa instanceof Horse cavalo) {
                        if(cavalo.getInventory().getSaddle() != null){
                            cavalo.getWorld().dropItemNaturally(cavalo.getLocation(), cavalo.getInventory().getSaddle());
                        }
                        if(cavalo.getInventory().getArmor() != null){
                            cavalo.getWorld().dropItemNaturally(cavalo.getLocation(), cavalo.getInventory().getArmor());
                        }
                        ItemStack[] itens = cavalo.getInventory().getContents();
                        if(itens.length>1){
                            Block bloco = cavalo.getWorld().getBlockAt(cavalo.getLocation());
                            Chest bau = (Chest) bloco;
                            bau.getBlockInventory().addItem(itens);
                        }
                    }
                    if(lepresa instanceof InventoryHolder) {
                        ItemStack[] items = ((InventoryHolder) lepresa).getInventory().getContents();
                        for(ItemStack itemStack : items) {
                            if(itemStack!=null){
                                lepresa.getWorld().dropItemNaturally(lepresa.getLocation(), itemStack);
                            }
                        }
                    }
                    lepresa.remove();
                    lepresa.getWorld().dropItem(lepresa.getLocation(), item);
                    atacantepl.setCooldown(reliquias.farm_modelo2.getType(),50);
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.picareta_md1)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                int qtd = rd.nextInt(1,2);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(presa instanceof Player){
                    atacantepl.sendMessage("Você não pode minerar jogadores!");
                }else{
                    if(presa instanceof LivingEntity vivo){
                        double hp = Objects.requireNonNull(vivo.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        if(vivo.getHealth()>(hp/2)){
                            atacantepl.sendMessage("Você ainda não pode minerar!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (hp/2))+" de vida");
                            return;
                        }
                        vivo.remove();
                        atacantepl.sendMessage(presa.getName()+" agora é mineravel!");
                        if(ver<=25){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.COAL_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_COAL_ORE);
                            }
                        }else if(ver<=45){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.COPPER_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_COPPER_ORE);
                            }
                        }else if(ver<=65){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.IRON_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_IRON_ORE);
                            }
                        }else if(ver<=80){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.GOLD_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_GOLD_ORE);
                            }
                        }else if(ver<=90){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.LAPIS_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_LAPIS_ORE);
                            }
                        }else if(ver<=95){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.REDSTONE_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_REDSTONE_ORE);
                            }
                        }else if(ver<=99){
                            if(qtd<=1){
                                w.getBlockAt(l).setType(Material.DIAMOND_ORE);
                            }else{
                                w.getBlockAt(l).setType(Material.DEEPSLATE_DIAMOND_ORE);
                            }
                        }else{
                            w.getBlockAt(l).setType(Material.ANCIENT_DEBRIS);
                        }
                    }
                }
            }
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.picareta_md2)) {
                if(presa instanceof Player){
                    atacantepl.sendMessage("Você não pode destruir jogadores!");
                }else{
                    Location l = presa.getLocation();
                    World w = presa.getWorld();
                    if(presa instanceof LivingEntity vivo){
                        double hp = Objects.requireNonNull(vivo.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue();
                        if(vivo.getHealth()>(hp/2)){
                            atacantepl.sendMessage("Você ainda não pode destruir!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (hp/2))+" de vida");
                            return;
                        }
                        atacantepl.sendMessage(presa.getName()+" destruido!");
                        vivo.remove();
                        w.createExplosion(l,4,false,false);
                    }
                }
            }
        }
    }
    @EventHandler
    public void vcviu(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(item != null && item.isSimilar(reliquias.vento)){
            if(action.isRightClick()){
                player.getInventory().removeItem(reliquias.vento);
                player.setCooldown(reliquias.vento.getType(),0);
                player.getInventory().addItem(reliquias.vento);
            }
        }else if(item != null && item.isSimilar(reliquias.arco_modelo2)){
            event.setCancelled(true);
            player.launchProjectile(Arrow.class);
        }else if(item != null && item.isSimilar(reliquias.farm_modelo1)){
            if(event.getClickedBlock() != null){
                if(event.getClickedBlock().isBuildable()){ return; }
                Material mat = event.getClickedBlock().getType();
                if (plantavel(mat)) {
                    event.getClickedBlock().applyBoneMeal(event.getBlockFace());
                }

            }
        }else if(item != null && item.isSimilar(reliquias.farm_modelo2)){
            if(action.isRightClick()){
                if(event.getClickedBlock() != null){
                    if(event.getClickedBlock().isBuildable()){ return; }
                    Material mat = event.getClickedBlock().getType();
                    if (plantavel(mat)) {
                        event.getClickedBlock().applyBoneMeal(event.getBlockFace());
                    }
                }
            }
        }else if(item != null && item.isSimilar(reliquias.picareta_md2)){
            if(action.isRightClick()){
                if(event.getClickedBlock() != null){
                    Block block = event.getClickedBlock();
                    if(block.getType() == Material.DEEPSLATE && player.hasCooldown(reliquias.picareta_md2.getType())){
                        Location l = event.getClickedBlock().getLocation();
                        World w = event.getClickedBlock().getWorld();
                        mina(w,l,player);
                    }else{
                        player.sendMessage("Só pode colocar dinamite na deepslate");
                    }
                }
            }
        }else if(item != null && item.isSimilar(reliquias.domador)){
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
        }else if(item != null && item.isSimilar(reliquias.mago)){
            if(action == Action.RIGHT_CLICK_AIR && player.hasCooldown(reliquias.mago.getType())){
                event.setCancelled(true);
                Vector vec = player.getEyeLocation().getDirection();
                Random rd = new Random();
                int ver = rd.nextInt(0, 100);
                if(ver<=15){
                    player.sendMessage("Mahō no ya");
                    Arrow arrow = player.launchProjectile(Arrow.class);
                    arrow.setGlowing(true);
                    arrow.setMetadata("magic",new FixedMetadataValue(ValentCity.this,(double) 10));
                    arrow.setVelocity(vec.multiply(50));
                    player.setCooldown(reliquias.mago.getType(),100);
                }else if(ver<=30){
                    player.sendMessage("Hinotama");
                    Fireball fire = player.launchProjectile(Fireball.class);
                    fire.setGlowing(true);
                    fire.setDirection(vec.multiply(2));
                    fire.setVelocity(vec.multiply(2));
                    fire.setYield(4);
                    player.setCooldown(reliquias.mago.getType(),400);
                }else if(ver<=45){
                    player.sendMessage("Yukidaruma");
                    Snowball bola = player.launchProjectile(Snowball.class);
                    bola.setGlowing(true);
                    bola.setVelocity(vec.multiply(2));
                    bola.setMetadata("freeze",new FixedMetadataValue(ValentCity.this, 10));
                    player.setCooldown(reliquias.mago.getType(),200);
                }else if(ver<=60){
                    player.sendMessage("Bakuhatsu-on");
                    int range = 40;
                    int damage = 150;
                    final int finalRange = range;
                    final int finalDamage = damage;
                    (new BukkitRunnable() {
                        double t = 0.0;
                        final Location location = player.getLocation();
                        Boolean isPassableBlock = true;
                        final Vector direction;
                        {
                            this.direction = this.location.getDirection().normalize();
                        }
                        public void run() {
                            this.t += 3.2;
                            double x = this.direction.getX() * this.t;
                            double y = this.direction.getY() * this.t + 1.4;
                            double z = this.direction.getZ() * this.t;
                            this.location.add(x, y, z);
                            this.location.getWorld().spawnParticle(Particle.SONIC_BOOM, this.location, 1, 0.0, 0.0, 0.0, 0.0);
                            if (!this.location.getBlock().isPassable()) {
                                this.isPassableBlock = false;
                            }
                            this.location.getWorld().playSound(this.location, Sound.ENTITY_WARDEN_SONIC_BOOM, 0.5F, 0.7F);
                            Collection<Entity> entities = this.location.getWorld().getNearbyEntities(this.location, 2.0, 2.0, 2.0);
                            while(entities.iterator().hasNext()){
                                Entity atigido = entities.iterator().next();
                                if(atigido instanceof LivingEntity vivo){
                                    vivo.damage(finalDamage);
                                }
                            }
                            this.location.subtract(x, y, z);
                            if (this.t > (double)finalRange || !this.isPassableBlock) {
                                this.cancel();
                            }
                        }
                    }).runTaskTimer(ValentCity.this, 0L, 1L);
                    player.setCooldown(reliquias.mago.getType(),1200);
                }else if(ver<=75){
                    player.sendMessage("Furainguheddo");
                    WitherSkull wither = player.launchProjectile(WitherSkull.class);
                    wither.setGlowing(true);
                    wither.setDirection(vec.multiply(2));
                    wither.setVelocity(vec.multiply(2));
                    wither.setCharged(true);
                    player.setCooldown(reliquias.mago.getType(),600);
                }else if(ver<=90){
                    player.sendMessage("Hanabi");
                    Firework fw = player.launchProjectile(Firework.class);
                    fw.setGlowing(true);
                    fw.setShotAtAngle(true);
                    fw.setVelocity(vec.multiply(2));
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
                    player.setCooldown(reliquias.mago.getType(),400);
                }else{
                    player.sendMessage("Pōshonmikkusu");
                    ThrownPotion tp = player.launchProjectile(ThrownPotion.class);
                    PotionMeta pm = tp.getPotionMeta();
                    int tempo = 600;
                    int power = 1;
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.NAUSEA,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.HUNGER,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.POISON,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.WITHER,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.LEVITATION,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.UNLUCK,tempo,power),true);
                    pm.addCustomEffect(new PotionEffect(PotionEffectType.DARKNESS,tempo,power),true);
                    tp.setPotionMeta(pm);
                    tp.setVelocity(vec.multiply(2));
                    player.setCooldown(reliquias.mago.getType(),600);
                }
            }
        }
    }
    private boolean plantavel(Material material) {
        return switch (material) {
            case WHEAT, CARROTS, POTATOES, BEETROOTS, NETHER_WART -> true;
            default -> false;
        };
    }
    private ItemStack makeSpawnerItem(EntityType entityType) {
        final ItemStack itemStack = new ItemStack(Material.SPAWNER, 1);
        ItemMeta meta = itemStack.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(this, "SPAWNER_ENTITY_TYPE"), PersistentDataType.STRING, entityType.name());
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    @EventHandler
    public void onSpawnerPlace(BlockPlaceEvent event) {
        final ItemStack hand = event.getItemInHand();
        if (hand.getType() == Material.AIR) return;
        ItemMeta meta = hand.getItemMeta();
        NamespacedKey key = new NamespacedKey(this, "SPAWNER_ENTITY_TYPE");
        if (!meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) return;
        EntityType type = EntityType.valueOf(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
        final Block placedBlock = event.getBlockPlaced();
        if (placedBlock.getType() != Material.SPAWNER)
            placedBlock.setType(Material.SPAWNER);
        CreatureSpawner creatureSpawner = (CreatureSpawner) placedBlock.getState();
        creatureSpawner.setSpawnedType(type);
        creatureSpawner.update();
    }
    public void mina(World w,Location l, Player player) {
        EntityEquipment equip = player.getEquipment();
        ItemStack hand = null;
        boolean main = true;
        if (equip.getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
            hand = equip.getItemInMainHand();
            if (!hand.isSimilar(reliquias.picareta_md2)) {
                hand = null;
            }
        }
        if (equip.getItemInOffHand().getType() == Material.NETHERITE_PICKAXE) {
            hand = equip.getItemInOffHand();
            if (!hand.isSimilar(reliquias.picareta_md2)) {
                hand = null;
            }
            main = false;
        }
        if (hand != null) {
            TNTPrimed tnt = w.spawn(l, TNTPrimed.class);
            tnt.setFuseTicks(10000);
            boolean finalMain = main;
            ItemStack finalHand = reliquias.picareta_md2;
            Temporizador timer = new Temporizador(ValentCity.this,10,
                () -> player.sendMessage("Dinamite ativada!"),
                () -> {
                    if (finalMain) {
                        player.getEquipment().setItemInMainHand(finalHand);
                    } else {
                        player.getEquipment().setItemInOffHand(finalHand);
                    }
                    tnt.remove();
                    w.createExplosion(l,40,false,true);
                },(t) -> {
                    player.sendMessage("Falta "+ (t.getSecondsLeft()) + " Segundo para explosão!");
                    tnt.customName(Component.text((t.getSecondsLeft())+"s"));
                    tnt.setCustomNameVisible(true);
                }
            );
            timer.scheduleTimer();
        }
    }
}
