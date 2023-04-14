package de.firecreeper82.pathways.impl.fool.abilities;

import de.firecreeper82.lotm.Plugin;
import de.firecreeper82.lotm.util.Util;
import de.firecreeper82.pathways.Ability;
import de.firecreeper82.pathways.Items;
import de.firecreeper82.pathways.Pathway;
import de.firecreeper82.pathways.impl.fool.FoolItems;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class RealmOfMysteries extends Ability implements Listener {

    private int radius;

    private ArrayList<Entity> concealedEntities;

    public RealmOfMysteries(int identifier, Pathway pathway, int sequence, Items items) {
        super(identifier, pathway, sequence, items);

        items.addToSequenceItems(identifier - 1, sequence);
        radius = 10;

        Plugin.instance.getServer().getPluginManager().registerEvents(this, Plugin.instance);
        concealedEntities = new ArrayList<>();
    }

    @Override
    public void useAbility() {
        p = pathway.getBeyonder().getPlayer();

        pathway.getSequence().getUsesAbilities()[identifier - 1] = true;
        Location loc = p.getLocation();

        concealedEntities = new ArrayList<>();
        concealedEntities.addAll(p.getNearbyEntities(radius, radius, radius));
        concealedEntities.add(p);

        Plugin.instance.addToConcealedEntities(concealedEntities);

        Particle.DustOptions dust = new Particle.DustOptions(Color.fromBGR(0, 0, 0), 85f);

        new BukkitRunnable() {
            final int currentRadius = radius;
            @Override
            public void run() {
                Util.drawCircle(loc, currentRadius, 23, dust, Material.BARRIER);

                if(loc.getWorld() == null)
                    return;

                for(Entity entity : loc.getWorld().getNearbyEntities(loc, radius, radius, radius)) {
                    if(!concealedEntities.contains(entity)) {
                        Vector dir = new Vector(0, .5, 1);
                        Location entLoc = entity.getLocation();
                        while(entLoc.distance(loc) < (radius + 5) || entLoc.getBlock().getType().isSolid()) {
                            entLoc.add(dir);
                        }
                        entity.teleport(entLoc);
                    }
                }

                for(Entity entity : concealedEntities) {
                    if(!(entity instanceof LivingEntity livingEntity))
                        continue;
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 12, 1, false, false));
                    livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 80, 1, false, false));
                }

                if(!pathway.getSequence().getUsesAbilities()[identifier - 1]) {
                    Util.drawCircle(loc, currentRadius, 23, dust, Material.AIR);
                    Plugin.instance.removeFromConcealedEntities(concealedEntities);
                    concealedEntities.clear();
                    cancel();
                }
            }
        }.runTaskTimer(Plugin.instance, 0, 10);
    }

    @Override
    public ItemStack getItem() {
        return FoolItems.createItem(Material.BLACK_DYE, "Realm of Mysteries", "20000", identifier, sequence, pathway.getBeyonder().getPlayer().getName());
    }

    @Override
    public void leftClick() {
        p = pathway.getBeyonder().getPlayer();
        radius++;
        if(radius > 10)
            radius = 5;

        p.sendMessage("§5Radius is now " + radius);
    }


    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent e) {
        if(!concealedEntities.contains(e.getEntity()))
            return;

        if(!concealedEntities.contains(e.getDamager()))
            e.setCancelled(true);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if(!concealedEntities.contains(e.getPlayer()))
            return;

        for(Player player : e.getRecipients()) {
            if(concealedEntities.contains(player))
                continue;
            e.getRecipients().remove(player);
        }
    }
}
