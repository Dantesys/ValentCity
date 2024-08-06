package me.dantesys.valentCity;

import io.papermc.paper.event.player.PlayerStopUsingItemEvent;
import me.dantesys.valentCity.commands.giveItems;
import me.dantesys.valentCity.events.reliquiasevents;
import me.dantesys.valentCity.items.reliquias;
import org.bukkit.*;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.block.spawner.SpawnerEntry;
import org.bukkit.damage.DamageSource;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Crops;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.spawner.Spawner;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;


public final class ValentCity extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        reliquias.init();
        getCommand("reliquia").setExecutor(new giveItems());
        getServer().getPluginManager().registerEvents(new reliquiasevents(), this);
        getServer().getPluginManager().registerEvents(this, this);
        NamespacedKey key_bunble = new NamespacedKey(this, "BOLSA");
        ItemStack bundle = new ItemStack(Material.BUNDLE);
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
        ShapedRecipe bundle_recipe = new ShapedRecipe(key_bunble,bundle);
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
        bundle_recipe.shape("lll","cbc","ccc");
        bundle_recipe.setIngredient('l', Material.STRING);
        bundle_recipe.setIngredient('c', Material.LEATHER);
        bundle_recipe.setIngredient('b', Material.CHEST);
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
        getServer().addRecipe(bundle_recipe);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Valent City]: Plugin Ativado!");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().clearRecipes();
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
                        (t) -> deadEntity.sendMessage("Falta "+ (t.getSecondsLeft()) + " Segundo para reativar")
                );
                timer.scheduleTimer();
            }
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
            main = true;
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
            wolf.setMaxHealth(200);
            wolf.attack(entity);
            wolf.setTarget((LivingEntity) entity);
            wolf.getEquipment().setItemInMainHand(espada);
            Fox wolf2 = (Fox) player.getWorld().spawnEntity(entity.getLocation(), EntityType.FOX);
            wolf2.setFirstTrustedPlayer(player);
            wolf2.setMaxHealth(100);
            wolf2.attack(entity);
            wolf2.setTarget((LivingEntity) entity);
            wolf2.getEquipment().setItemInMainHand(espada);
            Fox wolf3 = (Fox) player.getWorld().spawnEntity(entity.getLocation(), EntityType.FOX);
            wolf3.setFirstTrustedPlayer(player);
            wolf3.setMaxHealth(100);
            wolf3.attack(entity);
            wolf3.setTarget((LivingEntity) entity);
            wolf3.getEquipment().setItemInMainHand(espada);
            Temporizador timer = new Temporizador(ValentCity.this, 30,
                    () -> {
                        player.sendMessage("Repoza Ativado!");
                        player.getEquipment().setItemInMainHand(null);
                        wolf.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1),true);
                        wolf2.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1),true);
                        wolf3.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1),true);
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
                wolf.setCustomName("Lider ("+(t.getSecondsLeft())+"s)");
                wolf2.setCustomName("Soldado ("+(t.getSecondsLeft())+"s)");
                wolf3.setCustomName("Soldado ("+(t.getSecondsLeft())+"s)");
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
            main = true;
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
            wolf.setMaxHealth(200);
            wolf.attack(entity);
            wolf.setTamed(true);
            wolf.getEquipment().setChestplate(armadura);
            Wolf wolf2 = (Wolf) player.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
            wolf2.setOwner(player);
            wolf2.setMaxHealth(100);
            wolf2.attack(entity);
            wolf2.setTamed(true);
            wolf2.getEquipment().setChestplate(armadura);
            Wolf wolf3 = (Wolf) player.getWorld().spawnEntity(entity.getLocation(), EntityType.WOLF);
            wolf3.setOwner(player);
            wolf3.setMaxHealth(100);
            wolf3.attack(entity);
            wolf3.setTamed(true);
            wolf3.getEquipment().setChestplate(armadura);
            Temporizador timer = new Temporizador(ValentCity.this, 30,
                () -> {
                    player.sendMessage("Lobo Ativado!");
                    player.getEquipment().setItemInMainHand(null);
                    wolf.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1),true);
                    wolf2.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1),true);
                    wolf3.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING,-1,1),true);
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
                    wolf.setCustomName("Lider ("+(t.getSecondsLeft())+"s)");
                    wolf2.setCustomName("Soldado ("+(t.getSecondsLeft())+"s)");
                    wolf3.setCustomName("Soldado ("+(t.getSecondsLeft())+"s)");
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
        if(atacante instanceof Player) {
            Player atacantepl = (Player) atacante;
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.espadamd)) {
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
                atacantepl.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
                if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.farm_modelo1)) {
                Random rd = new Random();
                int ver = rd.nextInt(0,100);
                Location l = presa.getLocation();
                World w = presa.getWorld();
                if(presa instanceof Player){
                    atacantepl.sendMessage("Você não pode plantar jogadores!");
                }else{
                    if(presa instanceof LivingEntity vivo){
                        if(vivo.getHealth()>(vivo.getMaxHealth()/2)){
                            atacantepl.sendMessage("Você ainda não pode plantar ele!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (vivo.getMaxHealth()/2))+" de vida");
                            return;
                        }
                        presa.remove();
                        atacantepl.sendMessage("Agora ele está plantado!");
                        Location l2 = l.add(0,1,0);
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(reliquias.farm_modelo2)) {
                if (presa instanceof Player) {
                    atacantepl.sendMessage("Você não pode pegar jogadores!");
                }else if(atacantepl.hasCooldown(reliquias.farm_modelo2.getType())){
                    atacantepl.sendMessage("Está e cooldown aguarde "+atacantepl.getCooldown(reliquias.farm_modelo2.getType()));
                }else if (presa instanceof LivingEntity) {
                    LivingEntity lepresa = (LivingEntity) presa;
                    if (lepresa instanceof Tameable domado){
                        if(domado.isTamed()){
                            if(!domado.getOwner().equals(atacantepl)){
                                atacantepl.sendMessage("Você não pode pegar pet de outros jogadores!");
                                return;
                            }
                        }
                    }
                    ItemStack item = new ItemStack(MobsList.getEggType(lepresa).getMaterial());
                    String nome = lepresa.getName();
                    if (nome != null) {
                        ItemMeta meta = item.getItemMeta();
                        meta.setDisplayName(nome);
                        item.setItemMeta(meta);
                    }
                    if(lepresa instanceof Pig) {
                        if(((Pig)lepresa).hasSaddle()) {
                            lepresa.getWorld().dropItem(lepresa.getLocation(), new ItemStack(Material.SADDLE, 1));
                        }
                    }
                    if(lepresa instanceof Horse) {
                        if(((Horse) lepresa).isCarryingChest()){
                            lepresa.getWorld().dropItemNaturally(lepresa.getLocation(), new ItemStack(Material.CHEST));
                        }
                    }
                    if((lepresa instanceof Villager) || (!(lepresa instanceof Villager) && lepresa instanceof InventoryHolder)) {
                        ItemStack[] items = ((InventoryHolder) lepresa).getInventory().getContents();
                        for(ItemStack itemStack : items) {
                            if(itemStack!=null){
                                lepresa.getWorld().dropItemNaturally(lepresa.getLocation(), itemStack);
                            }
                        }
                    }
                    lepresa.remove();
                    lepresa.getWorld().dropItem(lepresa.getLocation(), item);
                    atacantepl.setCooldown(reliquias.farm_modelo2.getType(),10);
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
                        if(vivo.getHealth()>(vivo.getMaxHealth()/2)){
                            atacantepl.sendMessage("Você ainda não pode minerar!");
                            atacantepl.sendMessage("Ele tem muita vida ("+((int) vivo.getHealth())+"), deixe ele");
                            atacantepl.sendMessage("com "+((int) (vivo.getMaxHealth()/2))+" de vida");
                            return;
                        }
                        vivo.remove();
                        atacantepl.sendMessage("Agora você pode minerar ele!");
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
                    atacantepl.sendMessage(presa.getName()+" destruido!");
                    presa.remove();
                    w.createExplosion(l,4,false,false);
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
                if (!isPlanta(mat)) { return; }
                event.getClickedBlock().applyBoneMeal(event.getBlockFace());
            }
        }else if(item != null && item.isSimilar(reliquias.picareta_md2)){
            if(action.isRightClick()){
                if(event.getClickedBlock().breakNaturally(reliquias.picareta_md2)){
                    Location l = event.getClickedBlock().getLocation();
                    World w = event.getClickedBlock().getWorld();
                    mina(w,l,player);
                }
            }
        }else if(item != null && item.isSimilar(reliquias.domador)){
            if(action.isRightClick()){
                if(event.getClickedBlock().getType().equals(Material.SPAWNER)){
                    CreatureSpawner spawner = (CreatureSpawner) event.getClickedBlock().getState();
                    spawner.setType(Material.SPAWNER);
                    EntityType spawnedType = spawner.getSpawnedType();
                    player.getInventory().addItem(makeSpawnerItem(spawnedType));
                    player.breakBlock(event.getClickedBlock());
                }
            }
        }
    }
    private boolean isPlanta(Material material) {
        switch (material) {
            case WHEAT:
            case CARROTS:
            case POTATOES:
            case BEETROOTS:
            case NETHER_WART:
                return true;
            default:
                return false;
        }
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
            if (!hand.getEnchantments().containsKey(Enchantment.INFINITY)) {
                hand = null;
            }
            main = true;
        }
        if (equip.getItemInOffHand().getType() == Material.NETHERITE_PICKAXE) {
            hand = equip.getItemInOffHand();
            if (!hand.getEnchantments().containsKey(Enchantment.INFINITY)) {
                hand = null;
            }
            main = false;
        }
        if (hand != null) {
            TNTPrimed tnt = w.spawn(l, TNTPrimed.class);
            tnt.setFuseTicks(500);
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
                    player.sendMessage("Falta "+ (t.getSecondsLeft()) + " Segundo para explosão");
                    tnt.setCustomName((t.getSecondsLeft())+"s");
                    tnt.setCustomNameVisible(true);
                }
            );
            timer.scheduleTimer();
        }
    }
}
