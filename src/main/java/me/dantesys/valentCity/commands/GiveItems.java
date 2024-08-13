package me.dantesys.valentCity.commands;

import me.dantesys.valentCity.items.Reliquias;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GiveItems implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("reliquia")){
            if (args.length == 0) {
                sender.sendMessage("Recebedor ou alvo não definido");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage("Apenas jogadores podem receber reliquias!");
                } else {
                    if(args[1].equalsIgnoreCase("all")){
                        target.getInventory().addItem(Reliquias.espadamd);
                        target.getInventory().addItem(Reliquias.enxada);
                        target.getInventory().addItem(Reliquias.totem);
                        target.getInventory().addItem(Reliquias.spy_modelo1);
                        target.getInventory().addItem(Reliquias.tridente_modelo1);
                        target.getInventory().addItem(Reliquias.vento);
                        target.getInventory().addItem(Reliquias.arco_modelo1);
                        target.getInventory().addItem(Reliquias.farm_modelo1);
                        target.getInventory().addItem(Reliquias.picareta_md1);
                        target.getInventory().addItem(Reliquias.crossbow);
                        target.getInventory().addItem(Reliquias.domador);
                        target.getInventory().addItem(Reliquias.mago);
                        target.getInventory().addItem(Reliquias.pisante_md1);
                        target.getInventory().addItem(Reliquias.escudo_md1);
                        target.getInventory().addItem(Reliquias.marreta);
                        target.getInventory().addItem(Reliquias.capacete);
                    }else if(args[1].equalsIgnoreCase("espada")){
                        target.getInventory().addItem(Reliquias.espadamd);
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
                        target.getInventory().addItem(Reliquias.crossbow);
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
