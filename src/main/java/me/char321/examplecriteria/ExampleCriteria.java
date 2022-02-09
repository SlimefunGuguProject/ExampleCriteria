package me.char321.examplecriteria;

import me.char321.examplecriteria.advancements.SFAHook;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ExampleCriteria extends JavaPlugin {
    private static ExampleCriteria instance;

    @Override
    public void onEnable() {
        instance = this;

        if (Bukkit.getPluginManager().isPluginEnabled("SFAdvancements")) {
            getLogger().info("SFAdvancements was detected! Adding custom criteria...");
            new SFAHook().enableAdvancementSupport();
        }
    }

    @Override
    public void onDisable() {

    }

    public static ExampleCriteria getInstance() {
        return instance;
    }
}
