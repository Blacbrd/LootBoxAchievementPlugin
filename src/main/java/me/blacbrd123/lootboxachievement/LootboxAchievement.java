package me.blacbrd123.lootboxachievement;

// Test commit yes

import me.blacbrd123.lootboxachievement.commands.AcceptClaimCommand;
import me.blacbrd123.lootboxachievement.commands.ClaimChestCommand;
import me.blacbrd123.lootboxachievement.listeners.PlayerAchievementListener;
import me.blacbrd123.lootboxachievement.randomItemCreator.CreateItems;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class LootboxAchievement extends JavaPlugin {

    CreateItems createItems;
    FileConfiguration config = null;

    private final HashMap<UUID, Integer> claimCounter = new HashMap<>();

    public HashMap<UUID, Integer> getClaimCounter(){
        return claimCounter;
    }

    @Override
    public void onEnable() {

        File file = new File(this.getDataFolder(), "config.yml");

        this.config = YamlConfiguration.loadConfiguration(file);

        createItems = new CreateItems(this.config);

        loadCommands();
        loadListeners();

    }

    public void loadCommands(){
        Objects.requireNonNull(getCommand("claim")).setExecutor(new ClaimChestCommand(this));
        Objects.requireNonNull(getCommand("acceptclaim")).setExecutor(new AcceptClaimCommand(this, createItems));
    }

    public void loadListeners(){
        getServer().getPluginManager().registerEvents(new PlayerAchievementListener(this, createItems), this);
    }

    @Override
    public void onDisable() {

    }
}
