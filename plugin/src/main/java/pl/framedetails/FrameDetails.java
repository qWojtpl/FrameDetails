package pl.framedetails;

import org.bukkit.plugin.java.JavaPlugin;
import pl.framedetails.events.Events;

public final class FrameDetails extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Events(), this);
        getLogger().info("Loaded.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled.");
    }
}
