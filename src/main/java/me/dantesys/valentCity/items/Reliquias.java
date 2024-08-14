package me.dantesys.valentCity.items;

import net.kyori.adventure.inventory.Book;
import net.kyori.adventure.text.Component;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.attribute.Attribute.*;

public class Reliquias {
    public static ItemStack espadamd;
    public static ItemStack totem;
    public static ItemStack enxada;
    public static ItemStack spy_modelo1;
    public static ItemStack spy_modelo2;
    public static ItemStack tridente_modelo1;
    public static ItemStack tridente_modelo2;
    public static ItemStack vento;
    public static ItemStack arco_modelo1;
    public static ItemStack arco_modelo2;
    public static ItemStack farm_modelo1;
    public static ItemStack farm_modelo2;
    public static ItemStack crossbow;
    public static ItemStack picareta_md1;
    public static ItemStack picareta_md2;
    public static ItemStack domador;
    public static ItemStack mago;
    public static ItemStack pisante_md1;
    public static ItemStack pisante_md2;
    public static ItemStack escudo_md1;
    public static ItemStack escudo_md2;
    public static ItemStack marreta;
    public static ItemStack capacete;
    public static ItemStack pescador;
    public static ItemStack peitoral_md1;
    public static ItemStack peitoral_md2;
    public static ItemStack calca;
    /*Pá, Machado e Varinha*/
    public static ItemStack livro;
    public static ItemStack power;
    public static ItemStack life;
    public static void init() {
        createEnxada();
        createEspadamd();
        createTotem();
        createSpy1();
        createSpy2();
        createTridente1();
        createTridente2();
        createVento();
        createArco1();
        createArco2();
        createFarm1();
        createFarm2();
        createCrossbow();
        createPick1();
        createPick2();
        createDomador();
        createMago();
        createPower();
        createLife();
        createPis1();
        createPis2();
        createEsc1();
        createEsc2();
        createMarreta();
        createCapacete();
        createPescador();
        createPeitoral1();
        createPeitoral2();
        createCalca();
        createLivro();
    }
    private static void createEnxada() {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Ceifador"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Uma foice sinistra"));
        loreitem.add(Component.text("§7capaz de roubar a vida"));
        loreitem.add(Component.text("§7dos monstros!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.SHARPNESS,15,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        enxada = item;
    }
    private static void createEspadamd() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Guerreiro"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7A espada mais poderosa de"));
        loreitem.add(Component.text("§7todo o passado do Minecraft!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.BANE_OF_ARTHROPODS,5,true);
        meta.addEnchant(Enchantment.FIRE_ASPECT,2,true);
        meta.addEnchant(Enchantment.LOOTING,3,true);
        meta.addEnchant(Enchantment.SHARPNESS,5,true);
        meta.addEnchant(Enchantment.SMITE,5,true);
        meta.addEnchant(Enchantment.SWEEPING_EDGE,5,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addAttributeModifier(PLAYER_SWEEPING_DAMAGE_RATIO, new AttributeModifier(PLAYER_SWEEPING_DAMAGE_RATIO.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        espadamd = item;
    }
    private static void createTotem() {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia da infinidade"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7O totem supremo"));
        loreitem.add(Component.text("§7usado pela rainha Elizabeth II"));
        meta.lore(loreitem);
        meta.setFireResistant(true);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.INFINITY,1,true);
        item.setItemMeta(meta);
        totem = item;
    }
    private static void createSpy1() {
        ItemStack item = new ItemStack(Material.SPYGLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Espião (1)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato estranho"));
        loreitem.add(Component.text("§7capaz de alterar a"));
        loreitem.add(Component.text("§7realidade e descobrir"));
        loreitem.add(Component.text("§7segredos"));
        loreitem.add(Component.text("§7Modelo 1: Investigador"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.LOOTING,10,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addAttributeModifier(PLAYER_SNEAKING_SPEED, new AttributeModifier(PLAYER_SNEAKING_SPEED.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        spy_modelo1 = item;
    }
    private static void createSpy2() {
        ItemStack item = new ItemStack(Material.INK_SAC, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Espião (2)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato estranho"));
        loreitem.add(Component.text("§7capaz de alterar a"));
        loreitem.add(Component.text("§7realidade e descobrir"));
        loreitem.add(Component.text("§7segredos"));
        loreitem.add(Component.text("§7Modelo 2: Protetor"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.KNOCKBACK,2,true);
        meta.addAttributeModifier(PLAYER_ENTITY_INTERACTION_RANGE, new AttributeModifier(PLAYER_ENTITY_INTERACTION_RANGE.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        spy_modelo2 = item;
    }
    private static void createTridente1() {
        ItemStack item = new ItemStack(Material.TRIDENT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Tridente (1)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que comanda os mares"));
        loreitem.add(Component.text("§7Modelo 1: Tempest"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addAttributeModifier(GENERIC_ATTACK_DAMAGE, new AttributeModifier(GENERIC_ATTACK_DAMAGE.getKey(),10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addEnchant(Enchantment.IMPALING,20,true);
        meta.addEnchant(Enchantment.LOYALTY,5,true);
        meta.addEnchant(Enchantment.LOOTING,3,true);
        meta.addEnchant(Enchantment.CHANNELING,5,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        tridente_modelo1 = item;
    }
    private static void createTridente2() {
        ItemStack item = new ItemStack(Material.TRIDENT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Tridente (2)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que comanda os mares"));
        loreitem.add(Component.text("§7Modelo 2: Aqua Jet"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addAttributeModifier(GENERIC_MOVEMENT_SPEED, new AttributeModifier(GENERIC_MOVEMENT_SPEED.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
        meta.addEnchant(Enchantment.IMPALING,20,true);
        meta.addEnchant(Enchantment.LOOTING,3,true);
        meta.addEnchant(Enchantment.RIPTIDE,5,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        tridente_modelo2 = item;
    }
    private static void createVento() {
        ItemStack item = new ItemStack(Material.WIND_CHARGE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Vento"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que é a própia essência"));
        loreitem.add(Component.text("§7de um tornado"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addAttributeModifier(GENERIC_ATTACK_KNOCKBACK, new AttributeModifier(GENERIC_ATTACK_KNOCKBACK.getKey(),100, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_ATTACK_SPEED, new AttributeModifier(GENERIC_ATTACK_SPEED.getKey(),100, AttributeModifier.Operation.ADD_NUMBER));
        meta.addEnchant(Enchantment.KNOCKBACK,10000,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        vento = item;
    }
    private static void createArco1() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Arco (1)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que dispara uma flecha"));
        loreitem.add(Component.text("§7com força incoparavel"));
        loreitem.add(Component.text("§7Modelo: Sniper"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.INFINITY,1,true);
        meta.addEnchant(Enchantment.POWER,20,true);
        meta.addAttributeModifier(PLAYER_SNEAKING_SPEED, new AttributeModifier(PLAYER_SNEAKING_SPEED.getKey(),10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        arco_modelo1 = item;
    }
    private static void createArco2() {
        ItemStack item = new ItemStack(Material.BOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Arco (2)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que dispara uma rajada"));
        loreitem.add(Component.text("§7de flechas"));
        loreitem.add(Component.text("§7Modelo: Minigun"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.FLAME,1,true);
        meta.addEnchant(Enchantment.INFINITY,1,true);
        meta.addEnchant(Enchantment.POWER,3,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        arco_modelo2 = item;
    }
    private static void createFarm1() {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Fazendeiro (1)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que fazer crescer"));
        loreitem.add(Component.text("§7suas plantações"));
        loreitem.add(Component.text("§7Modelo: Agro"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.FORTUNE,3,true);
        meta.addEnchant(Enchantment.EFFICIENCY,5,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        farm_modelo1 = item;
    }
    private static void createFarm2() {
        ItemStack item = new ItemStack(Material.LEAD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Fazendeiro (2)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que controla"));
        loreitem.add(Component.text("§7seu animais"));
        loreitem.add(Component.text("§7Modelo: Pecuário"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.INFINITY,1,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        farm_modelo2 = item;
    }
    private static void createCrossbow() {
        ItemStack item = new ItemStack(Material.CROSSBOW, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Crossbow"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato misterioso"));
        loreitem.add(Component.text("§7capaz de disparar"));
        loreitem.add(Component.text("§7flechas misteriosas"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.MULTISHOT,100,true);
        meta.addEnchant(Enchantment.PIERCING,10,true);
        meta.addEnchant(Enchantment.QUICK_CHARGE,3,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        crossbow = item;
    }
    private static void createPick1() {
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Mineiro (1)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato feito"));
        loreitem.add(Component.text("§7pelos anões capaz de"));
        loreitem.add(Component.text("§7derrubar montanhas"));
        loreitem.add(Component.text("§7Modelo: Sortudo!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.FORTUNE,5,true);
        meta.addEnchant(Enchantment.EFFICIENCY,5,true);
        meta.addAttributeModifier(GENERIC_LUCK, new AttributeModifier(GENERIC_LUCK.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        picareta_md1 = item;
    }
    private static void createPick2() {
        ItemStack item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Mineiro (2)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato feito"));
        loreitem.add(Component.text("§7pelos anões capaz de"));
        loreitem.add(Component.text("§7derrubar montanhas"));
        loreitem.add(Component.text("§7Modelo: Destruidor!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.INFINITY,1,true);
        meta.addEnchant(Enchantment.SILK_TOUCH,1,true);
        meta.addEnchant(Enchantment.EFFICIENCY,5,true);
        meta.addAttributeModifier(PLAYER_BLOCK_BREAK_SPEED, new AttributeModifier(PLAYER_BLOCK_BREAK_SPEED.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(PLAYER_MINING_EFFICIENCY, new AttributeModifier(PLAYER_MINING_EFFICIENCY.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(PLAYER_BLOCK_INTERACTION_RANGE, new AttributeModifier(PLAYER_BLOCK_INTERACTION_RANGE.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, new AttributeModifier(GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE.getKey(),100, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),100, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        picareta_md2 = item;
    }
    private static void createDomador() {
        ItemStack item = new ItemStack(Material.STICK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Domador"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato feito"));
        loreitem.add(Component.text("§7pelos anciões"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.SILK_TOUCH,1,true);
        meta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        domador = item;
    }
    private static void createMago() {
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta meta = (BookMeta) item.getItemMeta();
        meta.setAuthor("§kGandalf");
        meta.displayName(Component.text("§6Relíquia do Mago"));
        List<Component> pages = new ArrayList<>();
        pages.add(Component.text("§lMágia Final\n§kLorem ipsum dolor sit amet, consectetur adipiscing elit. In tincidunt gravida tempus. Aliquot at nib nec dolor posuere efficient sit amet non tells. Interdum et dalesman fames ac ante ipsum."));
        Book bk = meta.pages(pages);
        if(bk.author().examinableName().equals(meta.getAuthor())){
            getServer().getConsoleSender().sendMessage("§6Author: "+meta.getAuthor());
        }
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        item.setItemMeta(meta);
        mago = item;
    }
    private static void createPower() {
        ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Power"));
        meta.setRarity(ItemRarity.UNCOMMON);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.POWER,1,true);
        meta.addAttributeModifier(GENERIC_ATTACK_DAMAGE, new AttributeModifier(GENERIC_ATTACK_DAMAGE.getKey(),1, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        power = item;
    }
    private static void createLife() {
        ItemStack item = new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Life"));
        meta.setRarity(ItemRarity.UNCOMMON);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.PROTECTION,1,true);
        meta.addAttributeModifier(GENERIC_MAX_HEALTH, new AttributeModifier(GENERIC_MAX_HEALTH.getKey(),1, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        life = item;
    }
    private static void createPis1() {
        ItemStack item = new ItemStack(Material.NETHERITE_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Pisante (1)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato feito"));
        loreitem.add(Component.text("§7pelos anciões"));
        loreitem.add(Component.text("§7Modelo: Caminhar!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.addEnchant(Enchantment.FIRE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.PROJECTILE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.BLAST_PROTECTION,4,true);
        meta.addEnchant(Enchantment.DEPTH_STRIDER,3,true);
        meta.addEnchant(Enchantment.FEATHER_FALLING,4,true);
        meta.addEnchant(Enchantment.SOUL_SPEED,3,true);
        meta.addEnchant(Enchantment.FROST_WALKER,2,true);
        meta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        pisante_md1 = item;
    }
    private static void createPis2() {
        ItemStack item = new ItemStack(Material.NETHERITE_BOOTS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Pisante (2)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato feito"));
        loreitem.add(Component.text("§7pelos anciões"));
        loreitem.add(Component.text("§7Modelo: Rocket!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.addEnchant(Enchantment.FIRE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.PROJECTILE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.BLAST_PROTECTION,4,true);
        meta.addEnchant(Enchantment.DEPTH_STRIDER,3,true);
        meta.addEnchant(Enchantment.FEATHER_FALLING,20,true);
        meta.addEnchant(Enchantment.SOUL_SPEED,3,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addAttributeModifier(GENERIC_GRAVITY, new AttributeModifier(GENERIC_GRAVITY.getKey(),-1, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        pisante_md2 = item;
    }
    private static void createEsc1() {
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        ItemMeta meta = item.getItemMeta();
        BlockStateMeta bmeta = (BlockStateMeta) meta;
        Banner banner = (Banner) bmeta.getBlockState();
        banner.setBaseColor(DyeColor.RED);
        banner.addPattern(new Pattern(DyeColor.WHITE, PatternType.RHOMBUS));
        banner.addPattern(new Pattern(DyeColor.RED, PatternType.CIRCLE));
        banner.addPattern(new Pattern(DyeColor.RED, PatternType.SQUARE_TOP_RIGHT));
        banner.addPattern(new Pattern(DyeColor.RED, PatternType.SQUARE_BOTTOM_LEFT));
        banner.update();
        bmeta.setBlockState(banner);
        bmeta.displayName(Component.text("§6Relíquia do Escudo (1)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato feito"));
        loreitem.add(Component.text("§7pelos magos anciões"));
        loreitem.add(Component.text("§7Modelo: Reversão!"));
        bmeta.lore(loreitem);
        bmeta.setRarity(ItemRarity.EPIC);
        bmeta.setUnbreakable(true);
        bmeta.setFireResistant(true);
        bmeta.addEnchant(Enchantment.THORNS,5,true);
        bmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bmeta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        bmeta.addAttributeModifier(GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(GENERIC_ARMOR_TOUGHNESS.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        bmeta.addAttributeModifier(GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, new AttributeModifier(GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        bmeta.addAttributeModifier(GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(GENERIC_KNOCKBACK_RESISTANCE.getKey(),5, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(bmeta);
        escudo_md1 = item;
    }
    private static void createEsc2() {
        ItemStack item = new ItemStack(Material.SHIELD, 1);
        ItemMeta meta = item.getItemMeta();
        BlockStateMeta bmeta = (BlockStateMeta) meta;
        Banner banner = (Banner) bmeta.getBlockState();
        banner.setBaseColor(DyeColor.BLUE);
        banner.addPattern(new Pattern(DyeColor.LIGHT_BLUE, PatternType.RHOMBUS));
        banner.addPattern(new Pattern(DyeColor.BLUE, PatternType.FLOWER));
        banner.addPattern(new Pattern(DyeColor.LIGHT_BLUE, PatternType.CIRCLE));
        banner.addPattern(new Pattern(DyeColor.BLUE, PatternType.GLOBE));
        banner.update();
        bmeta.setBlockState(banner);
        bmeta.displayName(Component.text("§6Relíquia do Escudo (2)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato feito"));
        loreitem.add(Component.text("§7pelos magos anciões"));
        loreitem.add(Component.text("§7Modelo: Amplificação!"));
        bmeta.lore(loreitem);
        bmeta.setRarity(ItemRarity.EPIC);
        bmeta.setUnbreakable(true);
        bmeta.setFireResistant(true);
        bmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        bmeta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),20, AttributeModifier.Operation.ADD_NUMBER));
        bmeta.addAttributeModifier(GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(GENERIC_ARMOR_TOUGHNESS.getKey(),20, AttributeModifier.Operation.ADD_NUMBER));
        bmeta.addAttributeModifier(GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, new AttributeModifier(GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE.getKey(),20, AttributeModifier.Operation.ADD_NUMBER));
        bmeta.addAttributeModifier(GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(GENERIC_KNOCKBACK_RESISTANCE.getKey(),20, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(bmeta);
        escudo_md2 = item;
    }
    private static void createMarreta() {
        ItemStack item = new ItemStack(Material.MACE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia da Marreta"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7A marreta mais poderosa de"));
        loreitem.add(Component.text("§7todo o Minecraft!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.BANE_OF_ARTHROPODS,5,true);
        meta.addEnchant(Enchantment.BREACH,4,true);
        meta.addEnchant(Enchantment.DENSITY,5,true);
        meta.addEnchant(Enchantment.FIRE_ASPECT,2,true);
        meta.addEnchant(Enchantment.LOOTING,3,true);
        meta.addEnchant(Enchantment.SHARPNESS,5,true);
        meta.addEnchant(Enchantment.SMITE,5,true);
        meta.addEnchant(Enchantment.WIND_BURST,3,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addAttributeModifier(GENERIC_SAFE_FALL_DISTANCE, new AttributeModifier(GENERIC_SAFE_FALL_DISTANCE.getKey(),500, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_ATTACK_KNOCKBACK, new AttributeModifier(GENERIC_ATTACK_KNOCKBACK.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        marreta = item;
    }
    private static void createCapacete() {
        ItemStack item = new ItemStack(Material.NETHERITE_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Capacete"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um capacete forjado"));
        loreitem.add(Component.text("§7pelos alquimistas!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.AQUA_AFFINITY,1,true);
        meta.addEnchant(Enchantment.BLAST_PROTECTION,4,true);
        meta.addEnchant(Enchantment.FIRE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.PROJECTILE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.addEnchant(Enchantment.THORNS,3,true);
        meta.addEnchant(Enchantment.RESPIRATION,3,true);
        meta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(GENERIC_ARMOR_TOUGHNESS.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_OXYGEN_BONUS, new AttributeModifier(GENERIC_OXYGEN_BONUS.getKey(),10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        capacete = item;
    }
    private static void createPescador() {
        ItemStack item = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Pescador"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Uma vara de pesca"));
        loreitem.add(Component.text("§7que já pescou até"));
        loreitem.add(Component.text("§7mesmo um Elder Guardian!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.LUCK_OF_THE_SEA,3,true);
        meta.addEnchant(Enchantment.LURE,3,true);
        meta.addAttributeModifier(GENERIC_ATTACK_DAMAGE, new AttributeModifier(GENERIC_ATTACK_DAMAGE.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_OXYGEN_BONUS, new AttributeModifier(GENERIC_OXYGEN_BONUS.getKey(),10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        pescador = item;
    }
    private static void createPeitoral1() {
        ItemStack item = new ItemStack(Material.NETHERITE_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Peitoral (1)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Uma armadura"));
        loreitem.add(Component.text("§7capaz de roubar"));
        loreitem.add(Component.text("§7vida dos mobs proxímos!"));
        loreitem.add(Component.text("§7Modelo: Invencivel"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.BLAST_PROTECTION,4,true);
        meta.addEnchant(Enchantment.FIRE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.PROJECTILE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addAttributeModifier(GENERIC_SCALE, new AttributeModifier(GENERIC_SCALE.getKey(),-0.15, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_FALL_DAMAGE_MULTIPLIER, new AttributeModifier(GENERIC_FALL_DAMAGE_MULTIPLIER.getKey(),2, AttributeModifier.Operation.ADD_NUMBER));
        item.setItemMeta(meta);
        peitoral_md1 = item;
    }
    private static void createPeitoral2() {
        ItemStack item = new ItemStack(Material.ELYTRA, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia do Peitoral (2)"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Uma armadura"));
        loreitem.add(Component.text("§7capaz voar!"));
        loreitem.add(Component.text("§7Modelo: Fly"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addAttributeModifier(GENERIC_SAFE_FALL_DISTANCE, new AttributeModifier(GENERIC_SAFE_FALL_DISTANCE.getKey(),10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_FLYING_SPEED, new AttributeModifier(GENERIC_FLYING_SPEED.getKey(),0.5, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_ARMOR, new AttributeModifier(GENERIC_ARMOR.getKey(),10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_MOVEMENT_EFFICIENCY, new AttributeModifier(GENERIC_MOVEMENT_EFFICIENCY.getKey(),1, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        peitoral_md2 = item;
    }
    private static void createCalca() {
        ItemStack item = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.displayName(Component.text("§6Relíquia da Calça"));
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Uma armadura"));
        loreitem.add(Component.text("§7capaz de causar"));
        loreitem.add(Component.text("§7terremoto!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        meta.addEnchant(Enchantment.BLAST_PROTECTION,4,true);
        meta.addEnchant(Enchantment.FIRE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.PROJECTILE_PROTECTION,4,true);
        meta.addEnchant(Enchantment.PROTECTION,4,true);
        meta.addEnchant(Enchantment.SWIFT_SNEAK,3,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        calca = item;
    }
    private static void createLivro() {
        ItemStack item = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta meta = (BookMeta) item.getItemMeta();
        meta.setAuthor("O Explorador");
        meta.displayName(Component.text("§6Manual das reliquias"));
        List<Component> pages = new ArrayList<>();
        pages.add(Component.text("§r§0Reliquias:\nCeifador, Guerreiro, Infinidade, Espião, Tridente, Vento, Arco, Fazendeiro, Crossbow, Mineiro, Domador, Mago, Pisante, Escudo, Marreta, Capacete, Pescador, Peitoral, Calça"));
        pages.add(Component.text("§l§6Relíquia do Ceifador\n§r§0Efeitos:\nNa mão: Visão Noturna e Invisibilidade\nNo ataque: Regeneração, no alvo Escuridão\nHabilidade: Quando mata um mob do tipo Monster dropa uma maçã que aumenta a vida"));
        pages.add(Component.text("§l§6Relíquia do Guerreiro\n§r§0Efeitos:\nNa mão: Resistência\nNo ataque: Força e velocidade, no alvo lentidão, naúse e deixa ele brilhando\nHabilidade: Quando mata um mob do tipo Monster dropa uma maçã que aumenta o ataque"));
        pages.add(Component.text("§l§6Relíquia da Infinidade\n§r§0Efeitos:\nNa mão: Regeneração\nNa outra mão: Saturação\nNo ataque: Regeneração, no alvo regeneração\nHabilidade: Quando ativo ele desaparece e reaparece em 5 segundos"));
        pages.add(Component.text("§l§6Relíquia do Espião - Investigador\n§r§0Efeitos:\nNa mão: Visão noturna, velocidade, invisibilidade e encolhimento\nNo ataque: O alvo fica com escuridão\nHabilidade: -Não possui habilidade especial-"));
        pages.add(Component.text("§l§6Relíquia do Espião - Protetor\n§r§0Efeitos:\nNa mão: Visão noturna, velocidade, resistência e encolhimento\nNo ataque: O alvo fica com escuridão, naúsea e lentidão\nHabilidade: -Não possui habilidade especial-"));
        pages.add(Component.text("§l§6Relíquia do Tridente - Tempest\n§r§0Efeitos:\nNa mão: Respiração aquática, Conduit Power e Graça dos golfinhos\nNo ataque: O alvo fica sem oxigênio\nHabilidade: -Não possui habilidade especial-"));
        pages.add(Component.text("§l§6Relíquia do Tridente - Aqua Jet\n§r§0Efeitos:\nNa mão: Respiração aquática, Conduit Power, Graça dos golfinhos e velocidade\nHabilidade: -Não possui habilidade especial-"));
        pages.add(Component.text("§l§6Relíquia do Vento\n§r§0Efeitos:\nNa mão: -Sem efeito-\nNo ataque: O alvo é jogado para longe\nHabilidade: É infinito, nunca acaba"));
        pages.add(Component.text("§l§6Relíquia do Arco - Sniper\n§r§0Efeitos:\nNa mão: Visão noturna e invisibilidade\nNo ataque: A velocidade da flecha 100 vezes mais rápida\nHabilidade: Não precisa de flechas"));
        pages.add(Component.text("§l§6Relíquia do Arco - Minigun\n§r§0Efeitos:\nNa mão: Brilhante e lentidão\nNo ataque: A velocidade de atirar outra flecha é nula\nHabilidade: Não precisa de flechas"));
        pages.add(Component.text("§l§6Relíquia do Fazendeiro - Agro\n§r§0Efeitos:\nNa mão: -Sem efeito-\nNo ataque: Pode transformar o alvo em uma plantação\nHabilidade: Aplica farinha de osso nas plantações"));
        pages.add(Component.text("§l§6Relíquia do Fazendeiro - Pecuário\n§r§0Efeitos:\nNa mão: -Sem efeito-\nNo ataque: Pode capturar o alvo\nHabilidade: Aplica farinha de osso nas plantações"));
        pages.add(Component.text("§l§6Relíquia do Crossbow\n§r§0Efeitos:\nNa mão: -Sem efeito-\nNo ataque: Dispara uma rajada de flechas especiais\nHabilidade: -Sem habilidade-"));
        pages.add(Component.text("§l§6Relíquia do Mineiro - Sortudo\n§r§0Efeitos:\nNa mão: Sorte e tamanho de anão\nNo ataque: Pode transformar os mobs em minérios\nHabilidade: -Sem habilidade-"));
        pages.add(Component.text("§l§6Relíquia do Mineiro - Destruidor\n§r§0Efeitos:\nNa mão: Resistência e tamanho de anão\nNo ataque: Pode destruir os mobs\nHabilidade: Pode plantar dinamite na deepslate"));
        pages.add(Component.text("§l§6Relíquia do Domador\n§r§0Efeitos:\nNa mão: -Sem efeito-\nNo ataque: Pode convocar lobos ou rapozas para atacar o seu alvo\nHabilidade: Pode coletar spawner"));
        pages.add(Component.text("§l§6Relíquia do Mago\n§r§0Efeitos:\nNa mão: Aleatório\nNo ataque: Aleatório\nHabilidade: Pode disparar feitiços"));
        pages.add(Component.text("§l§6Relíquia do Pisante - Caminhar\n§r§0Efeitos:\nVelocidade\nHabilidade: Criar campo de desaceleração"));
        pages.add(Component.text("§l§6Relíquia do Pisante - Rocket\n§r§0Efeitos:\nJump Bost e Levitação\nHabilidade: Durante a queda, ganha efeito de queda lente"));
        pages.add(Component.text("§l§6Relíquia do Escudo - Reversão\n§r§0Efeitos:\n-Sem efeito-\nHabilidade: Qualquer ataque de projetil volta para o atirador"));
        pages.add(Component.text("§l§6Relíquia do Escudo - Ampificação\n§r§0Efeitos:\n-Sem efeito-\nHabilidade: Impede qualquer mob de se aproximar dele"));
        pages.add(Component.text("§l§6Relíquia da Marreta\n§r§0Efeitos:\nForça e invisibilidade\nHabilidade: Pode disparar uma carga de vento no mundo, bola de fogo no nether e bafo do dragão no end"));
        pages.add(Component.text("§l§6Relíquia do Capacete\n§r§0Efeitos:\nVisão noturna e Respiração aquativa quando esta nadando\nHabilidade: Deixa os outros com inveja (má sorte)"));
        pages.add(Component.text("§l§6Relíquia do Pescador\n§r§0Efeitos:\nRespiração aquativa e sorte\nHabilidade: Pode transformar mobs em peixes"));
        pages.add(Component.text("§l§6Relíquia do Peitoral - Invencivel\n§r§0Efeitos:\n-Sem efeito-\nHabilidade: Consegue se curar com os inimigos proximos"));
        pages.add(Component.text("§l§6Relíquia do Peitoral - Fly\n§r§0Efeitos:\n-Sem efeito-\nHabilidade: Fogetes são infinitos e potentes"));
        pages.add(Component.text("§l§6Relíquia da Calça\n§r§0Efeitos:\n-Sem efeito-\nHabilidade: Quando agacha criar um terremoto"));
        Book bk = meta.pages(pages);
        if(bk.author().examinableName().equals(meta.getAuthor())){
            getServer().getConsoleSender().sendMessage("§6Author: "+meta.getAuthor());
        }
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.setFireResistant(true);
        item.setItemMeta(meta);
        livro = item;
    }
}