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
        if (label.equalsIgnoreCase("registro")){
            if (args.length <= 3) {
                sender.sendMessage("Tente:");
                sender.sendMessage("/registro list");
                sender.sendMessage("/registro registrar");
            }else if(args.equals("list")){
                sender.sendMessage("Guerreiro:"+config.get("guerreiro"));
                sender.sendMessage("Ceifador:"+config.get("ceifador"));
                sender.sendMessage("Infinidade:"+config.get("infinidade"));
                sender.sendMessage("Espião:"+config.get("espiao"));
                sender.sendMessage("Tridente:"+config.get("tridente"));
                sender.sendMessage("Vento:"+config.get("vento"));
                sender.sendMessage("Arco:"+config.get("arco"));
                sender.sendMessage("Fazendeiro:"+config.get("fazendeiro"));
                sender.sendMessage("Crossbow:"+config.get("crossbow"));
                sender.sendMessage("Mineiro:"+config.get("mineiro"));
                sender.sendMessage("Domador:"+config.get("domador"));
                sender.sendMessage("Mago:"+config.get("mago"));
                sender.sendMessage("Pisante:"+config.get("pisante"));
                sender.sendMessage("Escudo:"+config.get("escudo"));
                sender.sendMessage("Marreta:"+config.get("marreta"));
                sender.sendMessage("Capacete:"+config.get("capacete"));
                sender.sendMessage("Pescador:"+config.get("pescador"));
                sender.sendMessage("Peitoral:"+config.get("peitoral"));
                sender.sendMessage("Calça:"+config.get("calca"));
                sender.sendMessage("Barbaro:"+config.get("barbaro"));
                sender.sendMessage("Escavação:"+config.get("escavacao"));
                sender.sendMessage("Alquimia:"+config.get("alquimia"));
                sender.sendMessage("Ladrão:"+config.get("ladrao"));
            }else if(args.equals("registrar")){
                //TODO
            }
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
                        target.getInventory().addItem(Reliquias.alquimia);
                        target.getInventory().addItem(Reliquias.ladrao);
                        target.getInventory().addItem(Reliquias.pescador);
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
                    }else if(args[1].equalsIgnoreCase("alquimia")){
                        target.getInventory().addItem(Reliquias.alquimia);
                    }else if(args[1].equalsIgnoreCase("ladrao")){
                        target.getInventory().addItem(Reliquias.ladrao);
                    }else if(args[1].equalsIgnoreCase("pescador")){
                        target.getInventory().addItem(Reliquias.pescador);
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
