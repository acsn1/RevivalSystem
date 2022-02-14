package me.acsn.revivalsystem.events;

import me.acsn.revivalsystem.RevivalSystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class onDismountListener implements Listener {

    private RevivalSystem core;
    public onDismountListener(RevivalSystem core){
        this.core = core;
    }

    @EventHandler
    public void onDismount(EntityDismountEvent event){

        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();

            if(core.dead.containsKey(player.getUniqueId())){
                event.setCancelled(true);
            }
        }
    }

}
