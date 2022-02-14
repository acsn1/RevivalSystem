package me.acsn.revivalsystem.task;

import me.acsn.revivalsystem.RevivalSystem;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Map;
import java.util.UUID;

public class TaskManager {

    private RevivalSystem core;
    public TaskManager(RevivalSystem core){
        this.core = core;
    }

    public void runTask(){

        new BukkitRunnable(){

            public void run(){

                for(Map.Entry<UUID, Long> entry:core.dead.entrySet()){
                    if(entry.getValue() < System.currentTimeMillis()){
                        Bukkit.getPlayer(entry.getKey()).setHealth(0.0);
                        core.dead.remove(entry.getKey());
                    }
                }
            }

        }.runTaskTimer(core, 0, 20L);
    }


}
