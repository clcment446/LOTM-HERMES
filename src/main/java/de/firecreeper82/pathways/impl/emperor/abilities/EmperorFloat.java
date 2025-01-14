package de.firecreeper82.pathways.impl.emperor.abilities;

import de.firecreeper82.lotm.Beyonder;
import de.firecreeper82.lotm.Plugin;
import de.firecreeper82.pathways.Items;
import de.firecreeper82.pathways.Pathway;
import de.firecreeper82.pathways.Recordable;
import de.firecreeper82.pathways.impl.emperor.EmperorItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class EmperorFloat extends Recordable {
    public EmperorFloat(int identifier, Pathway pathway, int sequence, Items items) {
        super(identifier, pathway, sequence, items);
        items.addToSequenceItems(identifier - 1, sequence);
    }

    @Override
    public void useAbility(Player p, double multiplier, Beyonder beyonder, boolean recorded) {
        if (!recorded) pathway.getSequence().getUsesAbilities()[identifier - 1] = true;

        destroy(beyonder, recorded);
        {
            new BukkitRunnable() {
                int counter = 15;

                @Override
                public void run() {

                    if (pathway.getBeyonder().getSpirituality() >= 100) {
                        if (p.isSneaking()) {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 10, false, false, false));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 80, 4, false, false, false));
                        } else {
                            p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 80, 10, false, false, false));
                            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 80, 10, false, false, false));
                        }
                        pathway.getBeyonder().setSpirituality(pathway.getBeyonder().getSpirituality() - 100);
                    } else {
                        cancel();
                    }

                    counter--;
                    if (counter == 0) {
                        cancel();
                    }
                }
            }.runTaskTimer(Plugin.instance, 0, 20);
        }
    }

    @Override
    public ItemStack getItem() {
        return EmperorItems.createItem(Material.FEATHER, "Float", "100/s", identifier, sequence, pathway.getBeyonder().getPlayer().getName());
    }
}
