package com.creativerse;

import com.creativerse.commands.*;
import com.creativerse.files.CustomConfig;
import com.creativerse.files.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jmc.BlockTypes;
import org.jmc.Configuration;
import org.jmc.EntityTypes;
import org.jmc.registry.Registries;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Creativerse extends JavaPlugin {

    @Override
    public void onEnable() {
        CustomConfig.setup();
        CustomConfig.get().addDefault("ETH-Node", "127.0.0.1:7545");
        CustomConfig.get().addDefault("IPFS-Node", "/ip4/127.0.0.1/tcp/5001");
        CustomConfig.get().addDefault("NFT-Storage-API-Key", "");
        CustomConfig.get().addDefault("Transaction-Domain", "https://www.creativersemc.com");
        CustomConfig.get().addDefault("Contract", "0xe608791A1B4ed2BA44d482b68feFe102a720a4BB");
        CustomConfig.get().addDefault("SQLite", Bukkit.getServer().getPluginManager().getPlugin("Creativerse").getDataFolder() + "/database/");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        AutoSyncAll.run();
        String dbPath = CustomConfig.get().getString("SQLite");
        Database.initiate(dbPath);

        // Initializes jMc2Obj


        try {
            Configuration.initialize();
            try {
                Files.move(Path.of(Bukkit.getServer().getPluginManager().getPlugin("Creativerse").getDataFolder() + "/../../conf"), Path.of(Bukkit.getServer().getPluginManager().getPlugin("Creativerse").getDataFolder() + "/../conf")); // Moves conf folder generated by jMc2Obj to plugins folder
            } catch (FileAlreadyExistsException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
            Registries.initialize();
            BlockTypes.initialize();
            EntityTypes.initialize();

        } catch (Exception e) {
            e.printStackTrace();
        }

        getCommand("save").setExecutor(new Save());
        getCommand("sync").setExecutor(new Sync());
        getCommand("link").setExecutor(new Link());
        getCommand("syncall").setExecutor(new SyncAll());
        getCommand("creativerse reload").setExecutor(new Reload());
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Creativerse is enabled.");
    }

    @Override
    public void onDisable() {
        AutoSyncAll.stop();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Creativerse is disabled.");
    }

}
