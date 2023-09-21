package com.example.PowerSkiller;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("PowerSkiller")
public interface PowerSkillerConfig extends Config {
        @ConfigItem(
                name = "Game Object name",
                keyName = "objectToInteract",
                description = "game object you will be interacting with",
                position = 0
        )
        default String objectToInteract() {
            return "";
        }

        @ConfigItem(
                name = "Tool(s)",
                keyName = "toolsToUse",
                description = "Tools required to act with your object, can type ' axe' or ' pickaxe' to ignore this type",
                position = 1
        )
    default String toolsToUse() {
            return "";
        }
    @ConfigItem(
            name = "Keep Items",
            keyName = "itemToKeep",
            description = "Items you don't want dropped. Separate items by comma,no space. Good for UIM",
            position = 2
    )
    default String itemsToKeep() {
        return "";
    }

    @ConfigItem(
            name = "Empty slots",
            keyName = "emptySlots",
            description = "Amount of empty slots you have to skill with, mostly a UIM feature",
            position = 3
    )
    default int emptySlots() {
        return 28;
}
