/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.inventory.ContainerLevelAccess
 */
package io.izzel.arclight.common.bridge.core.inventory.container;

import io.izzel.arclight.common.bridge.core.inventory.container.ContainerBridge;
import io.izzel.arclight.common.bridge.core.util.IWorldPosCallableBridge;
import net.minecraft.world.inventory.ContainerLevelAccess;
import org.bukkit.Location;

public interface PosContainerBridge
extends ContainerBridge {
    public ContainerLevelAccess bridge$getWorldPos();

    default public Location bridge$getWorldLocation() {
        return ((IWorldPosCallableBridge)this.bridge$getWorldPos()).bridge$getLocation();
    }
}

