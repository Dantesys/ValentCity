package me.dantesys.valentCity.items;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemRarity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.attribute.Attribute.*;

public class reliquias {
    public static ItemStack espadamd;
    public static ItemStack totem;
    public static ItemStack enxada;
    public static ItemStack spy_modelo1;
    public static ItemStack spy_modelo2;
    public static ItemStack tridente_modelo1;
    public static ItemStack tridente_modelo2;
    public static ItemStack vento;
    /*public static ItemStack arco;
    public static ItemStack besta;
    public static ItemStack escudo;
    public static ItemStack picareta;*/
    public static void init() {
        createEnxada();
        createEspadamd();
        createTotem();
        createSpy1();
        createSpy2();
        createTridente1();
        createTridente2();
        createVento();
    }
    private static void createEnxada() {
        ItemStack item = new ItemStack(Material.NETHERITE_HOE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Relíquia do Ceifador");
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Uma foice sinistra"));
        loreitem.add(Component.text("§7capaz de roubar a vida"));
        loreitem.add(Component.text("§7dos monstros!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.SHARPNESS,15,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        enxada = item;
    }
    private static void createEspadamd() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Relíquia do Guerreiro");
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7A espada mais poderosa de"));
        loreitem.add(Component.text("§7todo o passado do Minecraft!"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.BANE_OF_ARTHROPODS,5,true);
        meta.addEnchant(Enchantment.FIRE_ASPECT,2,true);
        meta.addEnchant(Enchantment.LOOTING,3,true);
        meta.addEnchant(Enchantment.SHARPNESS,5,true);
        meta.addEnchant(Enchantment.SMITE,5,true);
        meta.addEnchant(Enchantment.SWEEPING_EDGE,5,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        espadamd = item;
    }
    private static void createTotem() {
        ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Relíquia da infinidade");
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7O totem supremo"));
        loreitem.add(Component.text("§7usado pela rainha Elizabeth II"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.INFINITY,1,true);
        item.setItemMeta(meta);
        totem = item;
    }
    private static void createSpy1() {
        ItemStack item = new ItemStack(Material.SPYGLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Relíquia do Espião (1)");
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato estranho"));
        loreitem.add(Component.text("§7capaz de alterar a"));
        loreitem.add(Component.text("§7realidade"));
        loreitem.add(Component.text("§7Modelo 1: Investigador"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addEnchant(Enchantment.LOOTING,10,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        spy_modelo1 = item;
    }
    private static void createSpy2() {
        ItemStack item = new ItemStack(Material.INK_SAC, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Relíquia do Espião (2)");
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato estranho"));
        loreitem.add(Component.text("§7capaz de alterar a"));
        loreitem.add(Component.text("§7realidade"));
        loreitem.add(Component.text("§7Modelo 2: Protetor"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
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
        meta.setDisplayName("§6Relíquia do Tridente (1)");
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que comanda os mares"));
        loreitem.add(Component.text("§7Modelo 1: Tempest"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addAttributeModifier(GENERIC_ATTACK_DAMAGE, new AttributeModifier(GENERIC_ATTACK_DAMAGE.getKey(),11, AttributeModifier.Operation.ADD_NUMBER));
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
        meta.setDisplayName("§6Relíquia do Tridente (2)");
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que comanda os mares"));
        loreitem.add(Component.text("§7Modelo 2: Aqua Jet"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addAttributeModifier(GENERIC_ATTACK_DAMAGE, new AttributeModifier(GENERIC_ATTACK_DAMAGE.getKey(),11, AttributeModifier.Operation.ADD_NUMBER));
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
        meta.setDisplayName("§6Relíquia do Vento");
        List<Component> loreitem = new ArrayList<>();
        loreitem.add(Component.text("§7Um artefato antigo"));
        loreitem.add(Component.text("§7que é a propia essência"));
        loreitem.add(Component.text("§7de um tornado"));
        meta.lore(loreitem);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addAttributeModifier(GENERIC_ATTACK_KNOCKBACK, new AttributeModifier(GENERIC_ATTACK_KNOCKBACK.getKey(),100, AttributeModifier.Operation.ADD_NUMBER));
        meta.addAttributeModifier(GENERIC_ATTACK_SPEED, new AttributeModifier(GENERIC_ATTACK_SPEED.getKey(),100, AttributeModifier.Operation.ADD_NUMBER));
        meta.addEnchant(Enchantment.KNOCKBACK,10000,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        vento = item;
    }
}
