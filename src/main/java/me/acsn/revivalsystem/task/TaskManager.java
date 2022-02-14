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

                    /*
                    #This is if you want to remove the invisible armor-stand if the player gets forcefully killed (passing the 1 minute)        
                    
                    
                    Player player = Bukkit.getPlayer(entry.getKey());
                    for (Entity entities : player.getNearbyEntities(player.getLocation().getX(),
                            player.getLocation().getY(), player.getLocation().getZ())) {

                        if (entities.getType().equals(EntityType.ARMOR_STAND)) {
                            if (entities.getCustomName() != null && entities.getCustomName().equals(player.getUniqueId().toString())) {
                                entities.remove();
                            }

                        }
                    
                    
                }*/

                    }
                }
            }

        }.runTaskTimer(core, 0, 20L);
    }


}
