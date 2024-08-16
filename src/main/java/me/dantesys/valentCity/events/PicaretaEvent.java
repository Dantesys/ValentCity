package me.dantesys.valentCity.events;

import me.dantesys.valentCity.Temporizador;
import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class PicaretaEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.picareta_md1)){
                ReliquiasEvent.limparEfeito(player);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 9,true,false));
            }else if(item != null && item.isSimilar(Reliquias.picareta_md2)){
                ReliquiasEvent.limparEfeito(player);
                Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
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
                if(item != null && item.isSimilar(Reliquias.picareta_md1)){
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 9,true,false));
                }else if(item != null && item.isSimilar(Reliquias.picareta_md2)){
                    Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_SCALE)).setBaseValue(0.5);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, -1, 1,true,false));
                }else{
                    ReliquiasEvent.limparEfeito(player);
                }
            }
        } catch (NullPointerException e){
            ReliquiasEvent.limparEfeito(player);
        }
    }
    @EventHandler
    public void ataque(EntityDamageByEntityEvent event) {
        Entity atacante = event.getDamager();
        Entity presa = event.getEntity();
        if(atacante instanceof Player atacantepl) {
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.picareta_md1)) {
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.picareta_md2)) {
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
    public void plantarmina(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(item != null && item.isSimilar(Reliquias.picareta_md2)){
            if(action.isRightClick()){
                if(event.getClickedBlock() != null){
                    Block block = event.getClickedBlock();
                    if(block.getType() == Material.DEEPSLATE && !player.hasCooldown(Reliquias.picareta_md2.getType())){
                        Location l = event.getClickedBlock().getLocation();
                        World w = event.getClickedBlock().getWorld();
                        player.setCooldown(Reliquias.picareta_md2.getType(),1200);
                        mina(w,l,player);
                    }else if(!player.hasCooldown(Reliquias.picareta_md2.getType())){
                        player.sendMessage("Só pode colocar dinamite na deepslate");
                    }else{
                        player.sendMessage("Aguarde a dinamite ser feita!");
                    }
                }
            }
        }
    }
    @EventHandler
    public void radar(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().isSimilar(Reliquias.picareta_md1) && !player.hasCooldown(Reliquias.picareta_md1.getType())){
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
            player.setCooldown(Reliquias.picareta_md1.getType(),2400);
        }
    }
    public void mina(World w,Location l, Player player) {
        EntityEquipment equip = player.getEquipment();
        ItemStack hand = null;
        boolean main = true;
        if (equip.getItemInMainHand().getType() == Material.NETHERITE_PICKAXE) {
            hand = equip.getItemInMainHand();
            if (!hand.isSimilar(Reliquias.picareta_md2)) {
                hand = null;
            }
        }
        if (equip.getItemInOffHand().getType() == Material.NETHERITE_PICKAXE) {
            hand = equip.getItemInOffHand();
            if (!hand.isSimilar(Reliquias.picareta_md2)) {
                hand = null;
            }
            main = false;
        }
        if (hand != null) {
            TNTPrimed tnt = w.spawn(l, TNTPrimed.class);
            tnt.setFuseTicks(10000);
            boolean finalMain = main;
            ItemStack finalHand = Reliquias.picareta_md2;
            Temporizador timer = new Temporizador(ValentCity.getPlugin(ValentCity.class),10,
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
                player.sendMessage("Falta "+ (t.getSegundosRestantes()) + " Segundo para explosão!");
                tnt.customName(Component.text((t.getSegundosRestantes())+"s"));
                tnt.setCustomNameVisible(true);
            }
            );
            timer.scheduleTimer(20L);
        }
    }
}