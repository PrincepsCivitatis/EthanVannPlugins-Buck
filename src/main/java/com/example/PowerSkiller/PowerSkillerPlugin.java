package com.example.PowerSkiller;

import com.example.EthanApiPlugin.Collections.Inventory;
import com.example.EthanApiPlugin.EthanApiPlugin;
import com.example.PacketUtils.PacketUtilsPlugin;
import com.google.inject.Provides;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import java.lang.reflect.Array;
import java.util.Arrays;

@PluginDependency(EthanApiPlugin.class)
@PluginDependency(PacketUtilsPlugin.class)
@PluginDescriptor(
        name = "Power Skiller",
        description = "Will interact with an object and drop all items when inventory is full",
        tags = {"ethan", "skilling"}
)
public class PowerSkillerPlugin extends Plugin {

    @Inject
    private Client client;
    @Inject
    private PowerSkillerConfig config;

    @Provides
    private PowerSkillerConfig getConfig(ConfigManager configManager) {
        configManager.getConfig(PowerSkillerConfig.class);
    }

    @Subscribe
    private void onGameTick(GameTick event) {
        if (!EthanApiPlugin.loggedIn()) {
            return;
        }
    }
    private boolean isInventoryReset() {
        return Inventory.search()
                .filter(item -> !shouldKeep(item.getName())) //using our shouldKeep methond, we can filter the tiems here to only include the ones we want to drop.
                .result()
                .size() == 28 - config.emptySlots(); // this will let us know that the invenotry is reset if the size becomes the amount of slots - empty slots
    }

    private boolean isDroppingItems() {
        return state == State.DROP_ITEMS;

    }
    private boolean shouldKeep(String name) {
        String[] itemsToKeep = config.itemsToKeep().split(","); // split the items listed by comma, no space.

        return Arrays.stream(itemsToKeep)

                .anyMatch(i ->
                        name.toLowerCase().contains(i.toLowerCase()))
    }

    private boolean hasTools() {
        String[] tools = config.toolsToUse().split(",");

        return Inventory.search()
                .filter(item -> isTool(item.getName()))
                .result().size() == tools.length;
    }
    private boolean isTool(String name) {
        String[] tools = config.toolsToUse().split(",");

        return Arrays.stream(tools)
                .anyMatch(i ->
                        name.toLowerCase().contains(i.toLowerCase()));
    }

}
