/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.SectionPos
 *  net.minecraft.server.level.ServerEntity
 *  net.minecraft.world.entity.Entity
 */
package io.izzel.arclight.common.bridge.core.world.server;

import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;

public interface ChunkMap_TrackedEntityBridge {
    public ServerEntity bridge$getServerEntity();

    public Entity bridge$getEntity();

    public SectionPos bridge$getLastSectionPos();

    public void bridge$setLastSectionPos(SectionPos var1);
}

