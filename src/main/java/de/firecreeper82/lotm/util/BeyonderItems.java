package de.firecreeper82.lotm.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public class BeyonderItems {

    private static final List<ItemStack> list = new ArrayList<>();
    private static final UUID characteristicUUID = UUID.fromString("4fba5f2f-cc36-4dc2-9b77-6064bb10788d");

    public static List<ItemStack> returnAllItems() {
        return list;
    }

    public static ItemStack getLavosSquidBlood() {
        final ItemStack item = new ItemStack(Material.REDSTONE);
        ItemMeta magentaPaneMeta = item.getItemMeta();
        assert magentaPaneMeta != null;
        magentaPaneMeta.setDisplayName("§4Lavos Squid Blood");
        item.setItemMeta(magentaPaneMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getStellarAquaCrystal() {
        final ItemStack item = new ItemStack(Material.PRISMARINE_CRYSTALS);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§bStellar Aqua Crystal");
        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getGoatHorn() {
        final ItemStack item = new ItemStack(Material.GOAT_HORN);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§7Hornacis Gray Mountain Goat Horn");
        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getRose() {
        final ItemStack item = new ItemStack(Material.ROSE_BUSH);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§4Human Faced Rose");
        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getRoot() {
        final ItemStack item = new ItemStack(Material.HANGING_ROOTS);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§6Root of a Mist Treant");
        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getPanther() {
        final ItemStack item = new ItemStack(Material.BLACK_DYE);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§8Spinal Fluid of a Black Patterned Panther");
        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getPituitaryGland() {
        final ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§5Mutated Pituitary Gland of a Thousand-Faced Hunter");

        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getShadowCharacteristic() {
        final ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§8Characteristic of a Human-Skinned Shadow");

        PlayerProfile profile = Bukkit.createPlayerProfile(characteristicUUID);
        PlayerTextures textures = profile.getTextures();

        try { textures.setSkin(new URL("http://textures.minecraft.net/texture/3ccc8a690c89ebf01adf0440c0a3d540e2db89cfc97ad3b8e01810bf3289f67a")); }
        catch (MalformedURLException ignored) {}

        itemMeta.setOwnerProfile(profile);

        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getWraithDust() {
        final ItemStack item = new ItemStack(Material.GUNPOWDER);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§7Dust of Ancient Wraiths");

        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getGargoyleCrystal() {
        final ItemStack item = new ItemStack(Material.CONDUIT);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§5Core Crystal of Six Winged Gargoyle");

        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getBizarroEye() {
        final ItemStack item = new ItemStack(Material.FERMENTED_SPIDER_EYE);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§5Eye of a Bizarro Bane");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addEnchant(Enchantment.CHANNELING, 1, true);

        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }

    public static ItemStack getPlundererBody() {
        final ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta = item.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName("§5Soul Body of a Spirit World Plunderer");
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addEnchant(Enchantment.CHANNELING, 1, true);

        item.setItemMeta(itemMeta);

        list.add(item);

        return item;
    }
}