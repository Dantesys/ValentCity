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
            List<Block> ferro = new ArrayList<>();
            List<Block> ouro = new ArrayList<>();
            List<Block> carvao = new ArrayList<>();
            List<Block> diamante = new ArrayList<>();
            List<Block> esmeralda = new ArrayList<>();
            List<Block> quartzos = new ArrayList<>();
            List<Block> cobre = new ArrayList<>();
            List<Block> redstone = new ArrayList<>();
            List<Block> lapis = new ArrayList<>();
            List<Block> netheritas = new ArrayList<>();
            Location loc = event.getBlock().getLocation();
            World world = event.getBlock().getWorld();
            double base_x = loc.getX();
            double base_y = loc.getY();
            double base_z = loc.getZ();
            int raio = 10;
            for (int x = ((int) base_x - raio); x <= base_x + raio; x++) {
                for (int y = ((int) base_y - raio); y <= base_y + raio; y++) {
                    for (int z = ((int) base_z - raio); z <= raio + raio; z++) {
                        double distance = ((base_x - x) * (base_x - x) + (base_z - z) * (base_z - z) + (base_y - y) * (base_y - y));
                        if (distance < raio * raio && (distance < (raio - 1) * (raio - 1))) {
                            Block bloco = new Location(world, x, y, z).getBlock();
                            Material mat = bloco.getType();
                            if(mat.equals(Material.IRON_ORE) || mat.equals(Material.DEEPSLATE_IRON_ORE)){
                                ferro.add(bloco);
                            }else if(mat.equals(Material.GOLD_ORE) || mat.equals(Material.DEEPSLATE_GOLD_ORE) || mat.equals(Material.NETHER_GOLD_ORE)){
                                ouro.add(bloco);
                            }else if(mat.equals(Material.COAL_ORE) || mat.equals(Material.DEEPSLATE_COAL_ORE)){
                                carvao.add(bloco);
                            }else if(mat.equals(Material.DIAMOND_ORE) || mat.equals(Material.DEEPSLATE_DIAMOND_ORE)){
                                diamante.add(bloco);
                            }else if(mat.equals(Material.EMERALD_ORE) || mat.equals(Material.DEEPSLATE_EMERALD_ORE)){
                                esmeralda.add(bloco);
                            }else if(mat.equals(Material.NETHER_QUARTZ_ORE)){
                                quartzos.add(bloco);
                            }else if(mat.equals(Material.COPPER_ORE) || mat.equals(Material.DEEPSLATE_COPPER_ORE)){
                                cobre.add(bloco);
                            }else if(mat.equals(Material.REDSTONE_ORE) || mat.equals(Material.DEEPSLATE_REDSTONE_ORE)){
                                redstone.add(bloco);
                            }else if(mat.equals(Material.LAPIS_ORE) || mat.equals(Material.DEEPSLATE_LAPIS_ORE)){
                                lapis.add(bloco);
                            }else if(mat.equals(Material.ANCIENT_DEBRIS)){
                                netheritas.add(bloco);
                            }
                        }
                    }
                }
            }
            if(!carvao.isEmpty()){
                player.sendMessage("===Carvão===");
                for(int i = 0; i<=carvao.size();i++){
                    Block bloco = carvao.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!cobre.isEmpty()){
                player.sendMessage("===Cobre===");
                for(int i = 0; i<=cobre.size();i++){
                    Block bloco = cobre.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!ferro.isEmpty()){
                player.sendMessage("===Ferro===");
                for(int i = 0; i<=ferro.size();i++){
                    Block bloco = ferro.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!ouro.isEmpty()){
                player.sendMessage("===Ouro===");
                for(int i = 0; i<=ouro.size();i++){
                    Block bloco = ouro.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!redstone.isEmpty()){
                player.sendMessage("===Redstone===");
                for(int i = 0; i<=redstone.size();i++){
                    Block bloco = redstone.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!lapis.isEmpty()){
                player.sendMessage("===Lapis Lazuli===");
                for(int i = 0; i<=lapis.size();i++){
                    Block bloco = lapis.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!esmeralda.isEmpty()){
                player.sendMessage("===Esmeralda===");
                for(int i = 0; i<=esmeralda.size();i++){
                    Block bloco = esmeralda.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!diamante.isEmpty()){
                player.sendMessage("===Diamante===");
                for(int i = 0; i<=diamante.size();i++){
                    Block bloco = diamante.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!quartzos.isEmpty()){
                player.sendMessage("===Quartzo===");
                for(int i = 0; i<=quartzos.size();i++){
                    Block bloco = quartzos.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
            }
            if(!netheritas.isEmpty()){
                player.sendMessage("===Netherita===");
                for(int i = 0; i<=netheritas.size();i++){
                    Block bloco = netheritas.get(i);
                    Location local = bloco.getLocation();
                    player.sendMessage("X: "+local.getX()+" Y: "+local.getY()+" Z:"+local.getZ());
                }
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