package me.blacbrd123.lootboxachievement.randomItemCreator;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.random.*;

public class CreateItems {

    public ItemStack[] createItems(){

        // Will make random item generator with different chances for different items

        ItemStack testItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack testItem2 = new ItemStack(Material.SHIELD);

        return new ItemStack[] {testItem2, testItem};
    }


}
