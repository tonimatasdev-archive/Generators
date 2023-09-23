package net.tonimatasdev.stuff;

import net.tonimatasdev.stuff.commands.Command;
import net.tonimatasdev.stuff.events.EditorModeEvents;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Stuff extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("generator").setExecutor(new Command());


        getServer().getPluginManager().registerEvents(new EditorModeEvents(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getInstance() {
        return instance;
    }
}
