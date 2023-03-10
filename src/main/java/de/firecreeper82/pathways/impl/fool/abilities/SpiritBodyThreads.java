package de.firecreeper82.pathways.impl.fool.abilities;

import de.firecreeper82.pathways.Ability;
import de.firecreeper82.pathways.Pathway;
import de.firecreeper82.pathways.impl.fool.FoolItems;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashMap;

public class SpiritBodyThreads extends Ability {

    private final HashMap<Integer, int[]> mobColors;
    private final HashMap<EntityCategory, Integer> categoryToInt;

    public SpiritBodyThreads(int identifier, Pathway pathway) {
        super(identifier, pathway);

        mobColors = new HashMap<>();
        categoryToInt = new HashMap<>();

        categoryToInt.put(EntityCategory.UNDEAD, 1);
        categoryToInt.put(EntityCategory.ARTHROPOD, 2);
        categoryToInt.put(EntityCategory.ILLAGER, 3);
        categoryToInt.put(EntityCategory.NONE, 4);
        categoryToInt.put(EntityCategory.WATER, 4);

        mobColors.put(0, new int[]{78, 78, 78});
        mobColors.put(1, new int[]{75, 133, 0});
        mobColors.put(2, new int[]{57, 0, 133});
        mobColors.put(3, new int[]{87, 43, 0});
        mobColors.put(4, new int[]{0, 38, 69});
    }

    @Override
    public void useAbility() {

    }

    @Override
    public ItemStack getItem() {
        return FoolItems.createItem(Material.LEAD, "Spirit Body Threads", "100", identifier, 5, pathway.getBeyonder().getPlayer().getName());
    }

    @Override
    public void onHold() {
        Player p = pathway.getBeyonder().getPlayer();

        //Loop through hall entities and check their respective color and "draw" the Thread
        //Indicate selected entity on the actionbar
        for(Entity e : p.getNearbyEntities(60, 35, 60)) {
            if(e == p || !(e instanceof LivingEntity))
                return;

            int[] colors;

            if(e instanceof Player)
                colors = mobColors.get(0);
            else
                colors = mobColors.get(categoryToInt.get(((LivingEntity) e).getCategory()));

            Location entityLoc = e.getLocation();
            Location playerLoc = p.getEyeLocation();
            Vector dir = entityLoc.toVector().subtract(playerLoc.toVector()).normalize().multiply(0.5);

            int counter = 0;
            while(playerLoc.distance(entityLoc) > 1.5 && counter < 150) {
                Particle.DustOptions dust = new Particle.DustOptions(Color.fromBGR(colors[0], colors[1], colors[2]), 1f);
                p.spawnParticle(Particle.REDSTONE, playerLoc, 1, dust);
                playerLoc.add(dir);
                counter++;
            }
        }
    }
}
