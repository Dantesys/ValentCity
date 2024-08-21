package me.dantesys.valentCity.events;

import me.dantesys.valentCity.MobsList;
import me.dantesys.valentCity.items.Reliquias;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.Random;

public class FazendeiroEvent implements Listener {
    @EventHandler
    public void segurar(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        int slot = event.getNewSlot();
        ItemStack item = player.getInventory().getItem(slot);
        ItemStack omao = player.getInventory().getItemInOffHand();
        try{
            if(item != null && item.isSimilar(Reliquias.farm_modelo1)){
                ReliquiasEvent.limparEfeito(player);
                player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1,true,false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1,true,false));
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
                if(item != null && item.isSimilar(Reliquias.farm_modelo1)){
                    player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, -1, 1,true,false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, -1, 1,true,false));
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.farm_modelo1)) {
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
                        l.subtract(0,1,0);
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
            if (atacantepl.getInventory().getItemInMainHand().isSimilar(Reliquias.farm_modelo2)) {
                if (presa instanceof Player) {
                    atacantepl.sendMessage("Você não pode pegar jogadores!");
                }else if(atacantepl.hasCooldown(Reliquias.farm_modelo2.getType())){
                    atacantepl.sendMessage("Está e cooldown aguarde "+atacantepl.getCooldown(Reliquias.farm_modelo2.getType()));
                }else if (presa instanceof LivingEntity lepresa) {
                    if (lepresa instanceof Tameable domado){
                        if(domado.isTamed()){
                            if(domado.getOwner() != atacantepl){
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
                    lepresa.remove();
                    lepresa.getWorld().dropItem(lepresa.getLocation(), item);
                    atacantepl.setCooldown(Reliquias.farm_modelo2.getType(),200);
                }
            }
        }
    }
    @EventHandler
    public void interacao(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        Action action = event.getAction();
        if(item != null && item.isSimilar(Reliquias.farm_modelo1)){
            if(event.getClickedBlock() != null){
                if(event.getClickedBlock().isBuildable()){ return; }
                Material mat = event.getClickedBlock().getType();
                if (aravel(mat)) {
                    event.getClickedBlock().setType(Material.FARMLAND);
                }
            }
        }else if(item != null && item.isSimilar(Reliquias.farm_modelo2)){
            if(action.isRightClick()){
                if(event.getClickedBlock() != null){
                    if(event.getClickedBlock().isBuildable()){ return; }
                    Material mat = event.getClickedBlock().getType();
                    if (aravel(mat)) {
                        event.getClickedBlock().applyBoneMeal(event.getBlockFace());
                    }
                }
            }
        }
    }
    private boolean aravel(Material material) {
        return switch (material) {
            case GRASS_BLOCK, DIRT, FARMLAND, ROOTED_DIRT, DIRT_PATH, COARSE_DIRT, PODZOL, MYCELIUM -> true;
            default -> false;
        };
    }
}