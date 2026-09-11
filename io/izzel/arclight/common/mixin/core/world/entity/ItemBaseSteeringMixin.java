/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.world.entity.ItemBasedSteering
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.ItemBasedSteering;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ItemBasedSteering.class})
public class ItemBaseSteeringMixin {
    @Shadow
    public boolean f_20834_;
    @Shadow
    public int f_20835_;
    @Shadow
    @Final
    private SynchedEntityData f_20837_;
    @Shadow
    @Final
    private EntityDataAccessor<Integer> f_20838_;

    public void setBoostTicks(int ticks) {
        this.f_20834_ = true;
        this.f_20835_ = 0;
        this.f_20837_.m_135381_(this.f_20838_, (Object)ticks);
    }
}

