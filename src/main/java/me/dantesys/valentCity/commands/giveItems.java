package me.dantesys.valentCity.commands;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

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
                        target.getInventory().addItem(reliquias.spy);
                        target.getInventory().addItem(reliquias.tridente_modelo1);
                    }else if(args[1].equalsIgnoreCase("espada")){
                        target.getInventory().addItem(reliquias.espadamd);
                    }else if(args[1].equalsIgnoreCase("spy")){
                        target.getInventory().addItem(reliquias.spy);
                    }else if(args[1].equalsIgnoreCase("foice")){
                        target.getInventory().addItem(reliquias.enxada);
                    }else if(args[1].equalsIgnoreCase("totem")){
                        target.getInventory().addItem(reliquias.totem);
                    }else if(args[1].equalsIgnoreCase("tridente")){
                        target.getInventory().addItem(reliquias.tridente_modelo1);
                    }else {
                        sender.sendMessage("Reliquia não encontrada!");
                    }
                }
            }
        }
        return true;
    }
}
