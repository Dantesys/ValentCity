package me.dantesys.valentCity;

import me.dantesys.valentCity.commands.giveItems;
import me.dantesys.valentCity.events.joinquit;
import me.dantesys.valentCity.events.reliquiasevents;
import me.dantesys.valentCity.items.reliquias;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ValentCity extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        reliquias.init();
        getCommand("reliquias").setExecutor(new giveItems());
        getServer().getPluginManager().registerEvents(new joinquit(), this);
        getServer().getPluginManager().registerEvents(new reliquiasevents(), this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Valent City]: Plugin Ativado!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Valent City]: Plugin Desativado!");
    }
}
