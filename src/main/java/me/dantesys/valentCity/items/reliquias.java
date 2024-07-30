package me.dantesys.valentCity.items;

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

public class reliquias {
    public static ItemStack espada;
    /*public static ItemStack maca;
    public static ItemStack arco;
    public static ItemStack besta;
    public static ItemStack escudo;
    public static ItemStack picareta;
    public static ItemStack machado;
    public static ItemStack pa;
    public static ItemStack enxada;*/
    public static void init() {
        createEspada();
    }
    private static void createEspada() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Relíquia do Cavaleiro");
        List<String> lore = new ArrayList<>();
        lore.add("§7A espada mais poderosa de");
        lore.add("§7todo o passado do Minecraft!");
        lore.add("§7Habilidades:");
        lore.add("§7Passiva: Em guarda");
        lore.add("§7Ativa: Fúria do guerreiro");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.FIRE_ASPECT, 2, false);
        meta.addEnchant(Enchantment.BANE_OF_ARTHROPODS, 5, false);
        meta.addEnchant(Enchantment.SHARPNESS, 5, false);
        meta.addEnchant(Enchantment.LOOTING, 3, false);
        meta.addEnchant(Enchantment.SMITE, 5, false);
        meta.addEnchant(Enchantment.SWEEPING_EDGE, 3, false);
        meta.setRarity(ItemRarity.EPIC);
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        espada = item;
    }
}
