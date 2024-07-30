package me.dantesys.valentCity.commands;

import me.dantesys.valentCity.items.reliquias;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveItems implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Apenas jogadores podem executar esse comando!");
            return true;
        }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("reliquias")) {
            player.getInventory().addItem(reliquias.espada);
        }

        return true;
    }
}
