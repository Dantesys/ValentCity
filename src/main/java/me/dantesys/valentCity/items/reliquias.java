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

public class reliquias {
    public static ItemStack espadamd;
    public static ItemStack totem;
    public static ItemStack enxada;
    /*public static ItemStack arco;
    public static ItemStack besta;
    public static ItemStack escudo;
    public static ItemStack picareta;
    public static ItemStack machado;
    public static ItemStack pa;*/
    public static void init() {
        createEnxada();
        createEspadamd();
        createTotem();
    }private static void createEnxada() {
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
        meta.addEnchant(Enchantment.SHARPNESS,30,true);
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
        meta.addEnchant(Enchantment.BANE_OF_ARTHROPODS,10,true);
        meta.addEnchant(Enchantment.FIRE_ASPECT,2,true);
        meta.addEnchant(Enchantment.LOOTING,3,true);
        meta.addEnchant(Enchantment.SHARPNESS,10,true);
        meta.addEnchant(Enchantment.SMITE,10,true);
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
}
