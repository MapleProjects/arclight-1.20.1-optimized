/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.item.ItemEntity
 */
package io.izzel.arclight.common.bridge.core.entity;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTransformEvent;

public interface MobEntityBridge
extends LivingEntityBridge {
    public void bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason var1, boolean var2);

    public void bridge$pushTransformReason(EntityTransformEvent.TransformReason var1);

    public boolean bridge$setGoalTarget(LivingEntity var1, EntityTargetEvent.TargetReason var2, boolean var3);

    public boolean bridge$lastGoalTargetResult();

    public ResourceLocation bridge$getLootTable();

    public boolean bridge$isPersistenceRequired();

    public void bridge$setPersistenceRequired(boolean var1);

    public void bridge$setAware(boolean var1);

    public void bridge$captureItemDrop(ItemEntity var1);
}

