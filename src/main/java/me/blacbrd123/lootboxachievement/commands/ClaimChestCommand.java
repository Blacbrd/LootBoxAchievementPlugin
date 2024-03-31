package me.blacbrd123.lootboxachievement.commands;

import me.blacbrd123.lootboxachievement.LootboxAchievement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ClaimChestCommand implements CommandExecutor {

    LootboxAchievement plugin;

    public ClaimChestCommand(LootboxAchievement plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player player)){
            return true;
        }

        if(!(command.getName().equalsIgnoreCase("claim"))){
            return true;
        }

        UUID playerUUID = player.getUniqueId();

        if(plugin.getClaimCounter().get(playerUUID) == null || plugin.getClaimCounter().get(playerUUID) <= 0){

            player.sendMessage(Component.text("You cannot use this command as you have no claims!")
                    .color(TextColor.color(255, 0,0)));
            return true;

        }

        // Send message to player that makes them accept or decline the thing

        player.sendMessage(Component.text("Once you open the claimed lootbox, you won't be able to open it again, so make sure you have enough inventory space.")
                .append(Component.text("\n[ACCEPT]"))
                .clickEvent(ClickEvent.runCommand("/acceptclaim"))
                .color(TextColor.color(0,255,255)));

        return true;
    }
}
