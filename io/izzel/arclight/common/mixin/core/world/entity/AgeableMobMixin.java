/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import io.izzel.arclight.common.bridge.core.entity.AgeableEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.PathfinderMobMixin;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={AgeableMob.class})
public abstract class AgeableMobMixin
extends PathfinderMobMixin
implements AgeableEntityBridge {
    public boolean ageLocked;

    @Shadow
    public abstract boolean m_6162_();

    @Shadow
    @Nullable
    public abstract AgeableMob m_142606_(ServerLevel var1, AgeableMob var2);

    @Shadow
    public abstract void m_146762_(int var1);

    @Inject(method={"addAdditionalSaveData"}, at={@At(value="RETURN")})
    private void arclight$writeAgeLocked(CompoundTag compound, CallbackInfo ci) {
        compound.m_128379_("AgeLocked", this.ageLocked);
    }

    @Inject(method={"readAdditionalSaveData"}, at={@At(value="RETURN")})
    private void arclight$readAgeLocked(CompoundTag compound, CallbackInfo ci) {
        this.ageLocked = compound.m_128471_("AgeLocked");
    }

    @Redirect(method={"aiStep"}, at=@At(value="FIELD", target="Lnet/minecraft/world/level/Level;isClientSide:Z"))
    private boolean arclight$tickIfNotLocked(Level world) {
        return world.f_46443_ || this.ageLocked;
    }

    @Override
    public boolean bridge$isAgeLocked() {
        return this.ageLocked;
    }
}

