package me.dantesys.valentCity.events;

import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.generator.structure.Structure;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.StructureSearchResult;

import java.util.Random;

public class EscavacaoEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.escavacao)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, -1, 1));
            }else{
                if(omao.isSimilar(Reliquias.totem)){
                    ReliquiasEvent.limparEfeito(player);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException ignored){

        }try{
            if(omao.isSimilar(Reliquias.totem)){
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1));
            }else{
                if(item != null && item.isSimilar(Reliquias.escavacao)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, -1, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, -1, 1));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void sortudo(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.escavacao) && !player.hasCooldown(Reliquias.escavacao.getType())){
            Random rd = new Random();
            int ver = rd.nextInt(0,100);
            int qtd = rd.nextInt(1,10);
            Location l = event.getBlock().getLocation();
            World w = event.getBlock().getWorld();
            if(ver<=25){
                w.dropItemNaturally(l,new ItemStack(Material.COAL,qtd));
            }else if(ver<=45){
                w.dropItemNaturally(l,new ItemStack(Material.RAW_COPPER,qtd));
            }else if(ver<=65){
                w.dropItemNaturally(l,new ItemStack(Material.RAW_IRON,qtd));
            }else if(ver<=80){
                w.dropItemNaturally(l,new ItemStack(Material.RAW_GOLD,qtd));
            }else if(ver<=90){
                w.dropItemNaturally(l,new ItemStack(Material.LAPIS_LAZULI,qtd));
            }else if(ver<=95){
                w.dropItemNaturally(l,new ItemStack(Material.REDSTONE,qtd));
            }else if(ver<=99){
                w.dropItemNaturally(l,new ItemStack(Material.DIAMOND,qtd));
            }else{
                w.dropItemNaturally(l,new ItemStack(Material.NETHERITE_SCRAP,qtd));
            }
            player.setCooldown(Reliquias.escavacao.getType(),2400);
        }
    }
    @EventHandler
    public void corte(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.escavacao)){
            if(!player.hasCooldown(Reliquias.escavacao.getType())){
                player.setCooldown(Reliquias.escavacao.getType(),1200);
                World w = player.getWorld();
                Location l = player.getLocation();
                Inventory iv = player.getInventory();
                StructureSearchResult ssr;
                ItemStack hb1 = iv.getItem(1);
                ItemStack hb2 = iv.getItem(1);
                ItemStack hb3 = iv.getItem(2);
                ItemStack hb4 = iv.getItem(3);
                ItemStack hb5 = iv.getItem(4);
                ItemStack hb6 = iv.getItem(5);
                ItemStack hb7 = iv.getItem(6);
                ItemStack hb8 = iv.getItem(7);
                ItemStack hb9 = iv.getItem(8);
                ItemStack bussola = new ItemStack(Material.COMPASS);
                if(hb1 != null && hb1.isSimilar(Reliquias.escavacao)){
                    player.setCooldown(Reliquias.escavacao.getType(),0);
                }
                else if(hb2 != null && hb2.isSimilar(Reliquias.escavacao)){
                    ssr = w.locateNearestStructure(l, Structure.MINESHAFT,30000,false);
                    if(ssr!=null){
                        Location ls = ssr.getLocation();
                        CompassMeta meta = (CompassMeta) bussola.getItemMeta();
                        meta.setLodestone(ls);
                        meta.setLodestoneTracked(true);
                        meta.displayName(Component.text("Mina"));
                        bussola.setItemMeta(meta);
                    }else{
                        player.sendMessage(Component.text("Nenhuma Mina encontrada!"));
                        player.setCooldown(Reliquias.escavacao.getType(),0);
                    }
                }
                else if(hb3 != null && hb3.isSimilar(Reliquias.escavacao)){
                    ssr = w.locateNearestStructure(l, Structure.STRONGHOLD,30000,false);
                    if(ssr!=null){
                        Location ls = ssr.getLocation();
                        CompassMeta meta = (CompassMeta) bussola.getItemMeta();
                        meta.setLodestone(ls);
                        meta.setLodestoneTracked(true);
                        meta.displayName(Component.text("Stronghold"));
                        bussola.setItemMeta(meta);
                    }else{
                        player.sendMessage(Component.text("Nenhuma Stronghold encontrada!"));
                        player.setCooldown(Reliquias.escavacao.getType(),0);
                    }
                }
                else if(hb4 != null && hb4.isSimilar(Reliquias.escavacao)){
                    ssr = w.locateNearestStructure(l, Structure.BURIED_TREASURE,30000,false);
                    if(ssr!=null){
                        Location ls = ssr.getLocation();
                        CompassMeta meta = (CompassMeta) bussola.getItemMeta();
                        meta.setLodestone(ls);
                        meta.setLodestoneTracked(true);
                        meta.displayName(Component.text("Baú do Tesouro"));
                        bussola.setItemMeta(meta);
                    }else{
                        player.sendMessage(Component.text("Nenhuma Baú do tesouro encontrada!"));
                        player.setCooldown(Reliquias.escavacao.getType(),0);
                    }
                }
                else if(hb5 != null && hb5.isSimilar(Reliquias.escavacao)){
                    ssr = w.locateNearestStructure(l, Structure.TRAIL_RUINS,30000,false);
                    if(ssr!=null){
                        Location ls = ssr.getLocation();
                        CompassMeta meta = (CompassMeta) bussola.getItemMeta();
                        meta.setLodestone(ls);
                        meta.setLodestoneTracked(true);
                        meta.displayName(Component.text("Ruinas"));
                        bussola.setItemMeta(meta);
                    }else{
                        player.sendMessage(Component.text("Nenhuma Ruinas encontrada!"));
                        player.setCooldown(Reliquias.escavacao.getType(),0);
                    }
                }
                else if(hb6 != null && hb6.isSimilar(Reliquias.escavacao)){
                    ssr = w.locateNearestStructure(l, Structure.TRIAL_CHAMBERS,30000,false);
                    if(ssr!=null){
                        Location ls = ssr.getLocation();
                        CompassMeta meta = (CompassMeta) bussola.getItemMeta();
                        meta.setLodestone(ls);
                        meta.setLodestoneTracked(true);
                        meta.displayName(Component.text("Camâra do Jugamento"));
                        bussola.setItemMeta(meta);
                    }else{
                        player.sendMessage(Component.text("Nenhuma Camara do jugamento encontrada!"));
                        player.setCooldown(Reliquias.escavacao.getType(),0);
                    }
                }
                else if(hb7 != null && hb7.isSimilar(Reliquias.escavacao)){
                    ssr = w.locateNearestStructure(l, Structure.DESERT_PYRAMID,30000,false);
                    if(ssr!=null){
                        Location ls = ssr.getLocation();
                        CompassMeta meta = (CompassMeta) bussola.getItemMeta();
                        meta.setLodestone(ls);
                        meta.setLodestoneTracked(true);
                        meta.displayName(Component.text("Pirâmide"));
                        bussola.setItemMeta(meta);
                    }else{
                        player.sendMessage(Component.text("Nenhuma Pirâmide encontrada!"));
                        player.setCooldown(Reliquias.escavacao.getType(),0);
                    }
                }
                else if(hb8 != null && hb8.isSimilar(Reliquias.escavacao)){
                    ssr = w.locateNearestStructure(l, Structure.JUNGLE_PYRAMID,30000,false);
                    if(ssr!=null){
                        Location ls = ssr.getLocation();
                        CompassMeta meta = (CompassMeta) bussola.getItemMeta();
                        meta.setLodestone(ls);
                        meta.setLodestoneTracked(true);
                        meta.displayName(Component.text("Templo da jungle"));
                        bussola.setItemMeta(meta);
                    }else{
                        player.sendMessage(Component.text("Nenhum Templo da jungle encontrada!"));
                        player.setCooldown(Reliquias.escavacao.getType(),0);
                    }
                }
                else if(hb9 != null && hb9.isSimilar(Reliquias.escavacao)){
                    ssr = w.locateNearestStructure(l, Structure.RUINED_PORTAL,30000,false);
                    if(ssr!=null){
                        Location ls = ssr.getLocation();
                        CompassMeta meta = (CompassMeta) bussola.getItemMeta();
                        meta.setLodestone(ls);
                        meta.setLodestoneTracked(true);
                        meta.displayName(Component.text("Portal em ruina"));
                        bussola.setItemMeta(meta);
                    }else{
                        player.sendMessage(Component.text("Nenhuma Portal em ruina encontrada!"));
                        player.setCooldown(Reliquias.escavacao.getType(),0);
                    }
                }
                if(((CompassMeta) bussola.getItemMeta()).hasLodestone()){
                    player.getInventory().addItem(bussola);
                    player.updateInventory();
                }
            }
        }
    }
}