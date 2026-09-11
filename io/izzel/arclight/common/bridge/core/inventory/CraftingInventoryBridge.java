/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Player
 */
package io.izzel.arclight.common.bridge.core.inventory;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;

public interface CraftingInventoryBridge
extends IInventoryBridge {
    public void bridge$setOwner(Player var1);

    public void bridge$setResultInventory(Container var1);
}

