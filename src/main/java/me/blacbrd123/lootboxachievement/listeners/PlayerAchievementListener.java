package me.blacbrd123.lootboxachievement.listeners;

import me.blacbrd123.lootboxachievement.LootboxAchievement;
import me.blacbrd123.lootboxachievement.randomItemCreator.CreateItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class PlayerAchievementListener implements Listener {

    LootboxAchievement plugin;
    CreateItems createItems;

    public PlayerAchievementListener(LootboxAchievement plugin, CreateItems createItems){
        this.plugin = plugin;
        this.createItems = createItems;
    }

    @EventHandler
    public void onAchievement(PlayerAdvancementDoneEvent event){

        if(event.getAdvancement().getKey().toString().contains("minecraft:recipes")) {
            return;
        }

        Player player = event.getPlayer();

        player.sendMessage(Component.text("This happened!!"));



        Location chestPlaceLocation = null;

        BlockFace blockFace = player.getFacing();

        switch (blockFace){

            case NORTH:
                chestPlaceLocation = player.getLocation().add(0, 0, -1);
                break;
            case SOUTH:
                chestPlaceLocation = player .getLocation().add(0,0,1);
                break;
            case EAST:
                chestPlaceLocation = player.getLocation().add(1,0,0);
                break;
            case WEST:
                chestPlaceLocation = player.getLocation().add(-1,0,0);
                break;
            default:
                player.sendMessage("The switch case is not working"); // Debug
                break;

        }

        if(chestPlaceLocation.getBlock().getType() != Material.AIR){

            player.sendMessage(Component.text("A lootbox could not be spawned, type /claim to claim your reward!")
                    .color(TextColor.color(255,0,0)));

            UUID playerUUID = player.getUniqueId();

            // Check if player is in the hashmap, if not, add them and make the counter 1, if they are

            if(!(plugin.getClaimCounter().containsKey(playerUUID))){
                plugin.getClaimCounter().put(playerUUID, 1);
                return;
            }

            int claimCounter = plugin.getClaimCounter().get(playerUUID);

            claimCounter++;

            plugin.getClaimCounter().replace(playerUUID, claimCounter);
            return;
        }

        Block block = chestPlaceLocation.getBlock();
        block.setType(Material.CHEST);
        Chest lootbox = (Chest) block.getState();
        ItemStack[] items = createItems.createItems();
        lootbox.getInventory().addItem(items);

    }


}
