package me.blacbrd123.lootboxachievement.commands;

import me.blacbrd123.lootboxachievement.LootboxAchievement;
import me.blacbrd123.lootboxachievement.randomItemCreator.CreateItems;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class AcceptClaimCommand implements CommandExecutor {

    LootboxAchievement plugin;
    CreateItems createItems;
    public AcceptClaimCommand(LootboxAchievement plugin, CreateItems createItems){
        this.plugin = plugin;
        this.createItems = createItems;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if(!(sender instanceof Player player)){
            return true;
        }

        if(!(command.getName().equalsIgnoreCase("acceptclaim"))){
            return true;
        }
        UUID playerUUID = player.getUniqueId();

        if(plugin.getClaimCounter().get(playerUUID) <= 0){
            player.sendMessage(Component.text("You can't press accept anymore!"));
            return true;
        }


        int claimCounter = plugin.getClaimCounter().get(playerUUID);
        claimCounter--;
        plugin.getClaimCounter().replace(playerUUID, claimCounter);

        Inventory lootBoxInventory = Bukkit.createInventory(null, 27, ChatColor.BLUE + "LootBox");
        ItemStack[] items = createItems.createItems();

        for(ItemStack item : items){
            lootBoxInventory.addItem(item);
        }

        player.openInventory(lootBoxInventory);

        return true;
    }
}
