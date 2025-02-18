package de.firecreeper82.listeners;

import de.firecreeper82.lotm.Plugin;
import de.firecreeper82.lotm.util.Util;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {
    @EventHandler
    //Check if Player is Beyonder and the item isn't air
    //Call the useAbility function from the Beyonder
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (!Plugin.beyonders.containsKey(p.getUniqueId()))
            return;
        if (e.getMaterial() == Material.AIR)
            return;

        Plugin.beyonders.get(p.getUniqueId()).getPathway().getSequence().useAbility(e.getItem(), e);
    }


    @EventHandler
    //Check if Player is Beyonder
    //Call the destroyItem function from the Beyonder
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (!Plugin.beyonders.containsKey(p.getUniqueId()))
            return;
        Plugin.beyonders.get(p.getUniqueId()).getPathway().getSequence().destroyItem(e.getItemDrop().getItemStack(), e);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction().toString().contains("LEFT_CLICK") && Plugin.beyonders.containsKey(event.getPlayer().getUniqueId()) && Plugin.beyonders.get(event.getPlayer().getUniqueId()).isMagnifyingReach) {
            Player p = Plugin.beyonders.get(event.getPlayer().getUniqueId()).getPlayer();
            double playerDamage = Util.calculatePlayerDamage(p);
            assert Plugin.beyonders.get(event.getPlayer().getUniqueId()).targetedEntity != null;
            Plugin.beyonders.get(p.getUniqueId()).getPathway().getBeyonder().targetedEntity.damage(playerDamage);
        }
    }
}
