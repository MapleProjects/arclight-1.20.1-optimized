/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EntityTargetLivingEntityEvent
extends EntityTargetEvent {
    public EntityTargetLivingEntityEvent(@NotNull Entity entity, @Nullable LivingEntity target, @Nullable EntityTargetEvent.TargetReason reason) {
        super(entity, target, reason);
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        return (LivingEntity)super.getTarget();
    }

    @Override
    public void setTarget(@Nullable Entity target) {
        if (target == null || target instanceof LivingEntity) {
            super.setTarget(target);
        }
    }
}

