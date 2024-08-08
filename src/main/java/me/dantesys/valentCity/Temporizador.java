package me.dantesys.valentCity;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.function.Consumer;

public class Temporizador implements Runnable {
    private final JavaPlugin plugin;
    private Integer assignedTaskId;
    private final int seconds;
    private int secondsLeft;
    private final Consumer<Temporizador> everySecond;
    private final Runnable beforeTimer;
    private final Runnable afterTimer;

    public Temporizador(JavaPlugin plugin, int seconds,
                          Runnable beforeTimer, Runnable afterTimer,
                          Consumer<Temporizador> everySecond) {
        this.plugin = plugin;

        this.seconds = seconds;
        this.secondsLeft = seconds;

        this.beforeTimer = beforeTimer;
        this.afterTimer = afterTimer;
        this.everySecond = everySecond;
    }
    @Override
    public void run() {
        if (secondsLeft < 1) {
            afterTimer.run();
            if (assignedTaskId != null) Bukkit.getScheduler().cancelTask(assignedTaskId);
            return;
        }
        if (secondsLeft == seconds) beforeTimer.run();
        everySecond.accept(this);
        secondsLeft--;
    }
    public void setSecondsLeft(int i) {
        secondsLeft+=i;
    }
    public int getSecondsLeft() {
        return secondsLeft;
    }
    public void scheduleTimer() {
        this.assignedTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, this, 0L, 20L);
    }

}