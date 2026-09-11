/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.event.entity;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.jetbrains.annotations.NotNull;

public class EntityBreakDoorEvent
extends EntityChangeBlockEvent {
    public EntityBreakDoorEvent(@NotNull LivingEntity entity, @NotNull Block targetBlock) {
        super(entity, targetBlock, Material.AIR.createBlockData());
    }

    @Override
    @NotNull
    public LivingEntity getEntity() {
        return (LivingEntity)this.entity;
    }
}

