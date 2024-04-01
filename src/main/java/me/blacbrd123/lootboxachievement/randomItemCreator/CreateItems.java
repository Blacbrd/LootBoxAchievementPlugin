package me.blacbrd123.lootboxachievement.randomItemCreator;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class CreateItems {

    FileConfiguration config;

    public CreateItems(FileConfiguration config){
        this.config = config;
    }

    public ItemStack[] createItems(){

        Random random = new Random();
        int itemUpperBound = config.getInt("itemAmountUpperBound");
        int minimumAmountOfItems = config.getInt("minimumItemAmount");
        int numOfItems = random.nextInt(itemUpperBound - minimumAmountOfItems) + minimumAmountOfItems;

        ItemStack[] items = new ItemStack[numOfItems];


        for(int i = 0; i < numOfItems; i++){



            int addRareItem = random.nextInt(100);

            if(addRareItem < config.getInt("itemRarity.rare")){

                List<String> rareItems = config.getStringList("rareItems");
                int index = new Random().nextInt(rareItems.size());
                String rareItemString = rareItems.get(index);

                ItemStack rareItem = new ItemStack(Material.valueOf(rareItemString));

                items[i] = rareItem;

                continue;
            }

            int addMediumItem = random.nextInt(100);

            if(addMediumItem < config.getInt("itemRarity.medium")){

                List<String> mediumItems = config.getStringList("mediumItems");
                int index = new Random().nextInt(mediumItems.size());
                String mediumItemString = mediumItems.get(index);

                ItemStack mediumItem= new ItemStack(Material.valueOf(mediumItemString));

                items[i] = mediumItem;

                continue;

            }

            ItemStack dirt = new ItemStack(Material.DIRT);
            items[i] = dirt;

        }

        return items;
    }


}
