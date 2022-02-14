package me.acsn.revivalsystem;

import me.acsn.revivalsystem.events.onDamageListener;
import me.acsn.revivalsystem.events.onDismountListener;
import me.acsn.revivalsystem.events.onInteractListener;
import me.acsn.revivalsystem.task.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class RevivalSystem extends JavaPlugin {

    //global-variable
    public HashMap<UUID, Long> dead = new HashMap<>();

    //task
    private TaskManager taskManager;


    @Override
    public void onDisable() {}

    @Override
    public void onEnable() {

        registerListeners();

        taskManager = new TaskManager(this);
        taskManager.runTask();

    }

    private void registerListeners(){
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new onDamageListener(this), this);
        pm.registerEvents(new onInteractListener(this), this);
        pm.registerEvents(new onDismountListener(this), this);
    }

}
