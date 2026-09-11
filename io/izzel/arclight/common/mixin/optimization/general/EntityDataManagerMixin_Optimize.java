/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.world.entity.Entity
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.optimization.general;

import io.izzel.arclight.common.mod.util.optimization.NoopReadWriteLock;
import java.util.concurrent.locks.ReadWriteLock;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SynchedEntityData.class})
public class EntityDataManagerMixin_Optimize {
    @Shadow
    @Final
    @Mutable
    private ReadWriteLock f_135346_;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void optimize$dropLock(Entity entityIn, CallbackInfo ci) {
        this.f_135346_ = NoopReadWriteLock.instance();
    }
}

