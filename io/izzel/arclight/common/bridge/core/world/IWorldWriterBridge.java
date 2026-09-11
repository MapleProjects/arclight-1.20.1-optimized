/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 */
package io.izzel.arclight.common.bridge.core.world;

import net.minecraft.world.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;

public interface IWorldWriterBridge {
    public boolean bridge$addEntity(Entity var1, CreatureSpawnEvent.SpawnReason var2);

    public void bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason var1);

    public CreatureSpawnEvent.SpawnReason bridge$getAddEntityReason();
}

