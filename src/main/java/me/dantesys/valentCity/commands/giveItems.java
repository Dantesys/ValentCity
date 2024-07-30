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
        if (label.equalsIgnoreCase("kitrq")){
            if (args.length == 0 || sender instanceof Player) {
                Player player = (Player) sender;
                player.getInventory().addItem(reliquias.espadamd);
                player.getInventory().addItem(reliquias.totem);
                player.getInventory().addItem(reliquias.enxada);
            } else {
                Player target = Bukkit.getPlayerExact(args[0]);
                if (target == null) {
                    sender.sendMessage("Apenas jogadores podem receber reliquias!");
                } else {
                    target.getInventory().addItem(reliquias.espadamd);
                    target.getInventory().addItem(reliquias.enxada);
                }
            }
        }
        return true;
    }
}
