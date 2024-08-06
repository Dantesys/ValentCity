package me.dantesys.valentCity.commands;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveItems implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("reliquia")){
            if (args.length == 0) {
                sender.sendMessage("Recebedor ou alvo não definido");
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage("Apenas jogadores podem receber reliquias!");
                } else {
                    if(args[1].equalsIgnoreCase("all")){
                        target.getInventory().addItem(reliquias.espadamd);
                        target.getInventory().addItem(reliquias.enxada);
                        target.getInventory().addItem(reliquias.totem);
                        target.getInventory().addItem(reliquias.spy_modelo1);
                        target.getInventory().addItem(reliquias.tridente_modelo1);
                        target.getInventory().addItem(reliquias.vento);
                        target.getInventory().addItem(reliquias.arco_modelo1);
                        target.getInventory().addItem(reliquias.farm_modelo1);
                        target.getInventory().addItem(reliquias.picareta_md1);
                        target.getInventory().addItem(reliquias.crossbow);
                        target.getInventory().addItem(reliquias.domador);
                    }else if(args[1].equalsIgnoreCase("espada")){
                        target.getInventory().addItem(reliquias.espadamd);
                    }else if(args[1].equalsIgnoreCase("spy")){
                        target.getInventory().addItem(reliquias.spy_modelo1);
                    }else if(args[1].equalsIgnoreCase("foice")){
                        target.getInventory().addItem(reliquias.enxada);
                    }else if(args[1].equalsIgnoreCase("totem")){
                        target.getInventory().addItem(reliquias.totem);
                    }else if(args[1].equalsIgnoreCase("tridente")){
                        target.getInventory().addItem(reliquias.tridente_modelo1);
                    }else if(args[1].equalsIgnoreCase("vento")){
                        target.getInventory().addItem(reliquias.vento);
                    }else if(args[1].equalsIgnoreCase("arco")){
                        target.getInventory().addItem(reliquias.arco_modelo1);
                    }else if(args[1].equalsIgnoreCase("fazendeiro")){
                        target.getInventory().addItem(reliquias.farm_modelo1);
                    }else if(args[1].equalsIgnoreCase("crossbow")){
                        target.getInventory().addItem(reliquias.crossbow);
                    }else if(args[1].equalsIgnoreCase("mineiro")){
                        target.getInventory().addItem(reliquias.picareta_md1);
                    }else if(args[1].equalsIgnoreCase("domador")){
                        target.getInventory().addItem(reliquias.domador);
                    }else {
                        sender.sendMessage("Reliquia não encontrada!");
                    }
                }
            }
        }
        return true;
    }
}
