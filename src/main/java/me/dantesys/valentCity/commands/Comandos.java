package me.dantesys.valentCity.commands;

import me.dantesys.valentCity.ValentCity;
import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Comandos implements CommandExecutor {
    FileConfiguration config = ValentCity.getPlugin(ValentCity.class).getConfig();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("rlist")){
            sender.sendMessage("Guerreiro:"+config.get("registro.guerreiro"));
            sender.sendMessage("Ceifador:"+config.get("registro.ceifador"));
            sender.sendMessage("Infinidade:"+config.get("registro.infinidade"));
            sender.sendMessage("Espião:"+config.get("registro.espiao"));
            sender.sendMessage("Tridente:"+config.get("registro.tridente"));
            sender.sendMessage("Vento:"+config.get("registro.vento"));
            sender.sendMessage("Arco:"+config.get("registro.arco"));
            sender.sendMessage("Fazendeiro:"+config.get("registro.fazendeiro"));
            sender.sendMessage("Crossbow:"+config.get("registro.crossbow"));
            sender.sendMessage("Mineiro:"+config.get("registro.mineiro"));
            sender.sendMessage("Domador:"+config.get("registro.domador"));
            sender.sendMessage("Mago:"+config.get("registro.mago"));
            sender.sendMessage("Pisante:"+config.get("pregistro.isante"));
            sender.sendMessage("Escudo:"+config.get("registro.escudo"));
            sender.sendMessage("Marreta:"+config.get("registro.marreta"));
            sender.sendMessage("Capacete:"+config.get("registro.capacete"));
            sender.sendMessage("Pescador:"+config.get("registro.pescador"));
            sender.sendMessage("Peitoral:"+config.get("registro.peitoral"));
            sender.sendMessage("Calça:"+config.get("registro.calca"));
            sender.sendMessage("Barbaro:"+config.get("registro.barbaro"));
            sender.sendMessage("Escavação:"+config.get("registro.escavacao"));
            sender.sendMessage("Ladrão:"+config.get("registro.ladrao"));
            sender.sendMessage("Hulk:"+config.get("registro.hulk"));
            sender.sendMessage("Fenix:"+config.get("registro.fenix"));
        }else if (label.equalsIgnoreCase("reliquia")){
            if (args.length == 0) {
                sender.sendMessage("Recebedor ou alvo não definido");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage("Apenas jogadores podem receber reliquias!");
                } else {
                    if(args[1].equalsIgnoreCase("all")){
                        target.getInventory().addItem(Reliquias.espadamd1);
                        target.getInventory().addItem(Reliquias.enxada);
                        target.getInventory().addItem(Reliquias.totem);
                        target.getInventory().addItem(Reliquias.spy_modelo1);
                        target.getInventory().addItem(Reliquias.tridente_modelo1);
                        target.getInventory().addItem(Reliquias.vento);
                        target.getInventory().addItem(Reliquias.arco_modelo1);
                        target.getInventory().addItem(Reliquias.farm_modelo1);
                        target.getInventory().addItem(Reliquias.picareta_md1);
                        target.getInventory().addItem(Reliquias.crossbowmd1);
                        target.getInventory().addItem(Reliquias.domador);
                        target.getInventory().addItem(Reliquias.mago);
                        target.getInventory().addItem(Reliquias.pisante_md1);
                        target.getInventory().addItem(Reliquias.escudo_md1);
                        target.getInventory().addItem(Reliquias.marreta);
                        target.getInventory().addItem(Reliquias.capacete);
                        target.getInventory().addItem(Reliquias.peitoral_md1);
                        target.getInventory().addItem(Reliquias.calca);
                        target.getInventory().addItem(Reliquias.machado);
                        target.getInventory().addItem(Reliquias.escavacao);
                        target.getInventory().addItem(Reliquias.ladrao);
                        target.getInventory().addItem(Reliquias.pescador);
                        target.getInventory().addItem(Reliquias.hulk);
                        target.getInventory().addItem(Reliquias.fenix1);
                    }else if(args[1].equalsIgnoreCase("espada")){
                        target.getInventory().addItem(Reliquias.espadamd1);
                    }else if(args[1].equalsIgnoreCase("spy")){
                        target.getInventory().addItem(Reliquias.spy_modelo1);
                    }else if(args[1].equalsIgnoreCase("foice")){
                        target.getInventory().addItem(Reliquias.enxada);
                    }else if(args[1].equalsIgnoreCase("totem")){
                        target.getInventory().addItem(Reliquias.totem);
                    }else if(args[1].equalsIgnoreCase("tridente")){
                        target.getInventory().addItem(Reliquias.tridente_modelo1);
                    }else if(args[1].equalsIgnoreCase("vento")){
                        target.getInventory().addItem(Reliquias.vento);
                    }else if(args[1].equalsIgnoreCase("arco")){
                        target.getInventory().addItem(Reliquias.arco_modelo1);
                    }else if(args[1].equalsIgnoreCase("fazendeiro")){
                        target.getInventory().addItem(Reliquias.farm_modelo1);
                    }else if(args[1].equalsIgnoreCase("crossbow")){
                        target.getInventory().addItem(Reliquias.crossbowmd1);
                    }else if(args[1].equalsIgnoreCase("mineiro")){
                        target.getInventory().addItem(Reliquias.picareta_md1);
                    }else if(args[1].equalsIgnoreCase("domador")){
                        target.getInventory().addItem(Reliquias.domador);
                    }else if(args[1].equalsIgnoreCase("mago")){
                        target.getInventory().addItem(Reliquias.mago);
                    }else if(args[1].equalsIgnoreCase("pisante")){
                        target.getInventory().addItem(Reliquias.pisante_md1);
                    }else if(args[1].equalsIgnoreCase("escudo")){
                        target.getInventory().addItem(Reliquias.escudo_md1);
                    }else if(args[1].equalsIgnoreCase("marreta")){
                        target.getInventory().addItem(Reliquias.marreta);
                    }else if(args[1].equalsIgnoreCase("capacete")){
                        target.getInventory().addItem(Reliquias.capacete);
                    }else if(args[1].equalsIgnoreCase("peitoral")){
                        target.getInventory().addItem(Reliquias.peitoral_md1);
                    }else if(args[1].equalsIgnoreCase("calca")){
                        target.getInventory().addItem(Reliquias.calca);
                    }else if(args[1].equalsIgnoreCase("barbaro")){
                        target.getInventory().addItem(Reliquias.machado);
                    }else if(args[1].equalsIgnoreCase("escavacao")){
                        target.getInventory().addItem(Reliquias.escavacao);
                    }else if(args[1].equalsIgnoreCase("ladrao")){
                        target.getInventory().addItem(Reliquias.ladrao);
                    }else if(args[1].equalsIgnoreCase("pescador")){
                        target.getInventory().addItem(Reliquias.pescador);
                    }else if(args[1].equalsIgnoreCase("hulk")){
                        target.getInventory().addItem(Reliquias.hulk);
                    }else if(args[1].equalsIgnoreCase("fenix")){
                        target.getInventory().addItem(Reliquias.fenix1);
                    }else{
                        sender.sendMessage("Reliquia não encontrada!");
                    }
                }
            }
        }else if (label.equalsIgnoreCase("livro")){
            if (args.length == 0) {
                sender.sendMessage("Recebedor ou alvo não definido");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage("Apenas jogadores podem receber reliquias!");
                } else {
                    target.getInventory().addItem(Reliquias.livro);
                }
            }
        }
        return true;
    }
}
