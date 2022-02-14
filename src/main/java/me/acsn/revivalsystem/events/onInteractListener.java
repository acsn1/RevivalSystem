package me.acsn.revivalsystem.events;

import me.acsn.revivalsystem.RevivalSystem;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.potion.PotionEffectType;

public class onInteractListener implements Listener {

    private RevivalSystem core;
    public onInteractListener(RevivalSystem core){
        this.core = core;
    }

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event){

        if(event.getRightClicked() instanceof Player){

            Player player = (Player) event.getRightClicked();
       if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_APPLE)) {
            if(core.dead.containsKey(player.getUniqueId())){
                long time = core.dead.get(player.getUniqueId());

                if(System.currentTimeMillis() <= time){

                    core.dead.remove(player.getUniqueId());
                    player.removePotionEffect(PotionEffectType.BLINDNESS);

                    for(Entity entities:player.getNearbyEntities(player.getLocation().getX(),
                            player.getLocation().getY(), player.getLocation().getZ())){

                        if(entities.getType().equals(EntityType.ARMOR_STAND)){
                            if(entities.getCustomName() != null && entities.getCustomName().equals(player.getUniqueId().toString())){
                                entities.remove();
                            }
                        }

                    }
                }
            }
          }

        }
    }

}
