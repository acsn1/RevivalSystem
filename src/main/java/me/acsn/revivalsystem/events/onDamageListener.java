package me.acsn.revivalsystem.events;

import me.acsn.revivalsystem.RevivalSystem;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.concurrent.TimeUnit;


public class onDamageListener implements Listener {

    private RevivalSystem core;
    public onDamageListener(RevivalSystem core){
        this.core = core;
    }

    @EventHandler
    public void customDeath(EntityDamageEvent event){

        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();

            if(player.getHealth() - event.getFinalDamage() <= 0){
            if(!(core.dead.containsKey(player.getUniqueId()))) {


                ArmorStand astand = (ArmorStand) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                astand.setVisible(false);

                astand.addPassenger(player);
                astand.setCustomName(player.getUniqueId().toString());

                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100000, 20));

                long waiting_time = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60);
                core.dead.put(player.getUniqueId(), waiting_time);

                event.setCancelled(true);

                 }
            }

        }
    }

}
