/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ambient.Bat
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.ambient;

import io.izzel.arclight.common.mixin.core.world.entity.MobMixin;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ambient.Bat;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Bat.class})
public abstract class BatMixin
extends MobMixin {
    @Shadow
    public abstract boolean m_27452_();

    @Inject(method={"customServerAiStep"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/ambient/Bat;setResting(Z)V")})
    private void arclight$toggleSleep(CallbackInfo ci) {
        if (!CraftEventFactory.handleBatToggleSleepEvent((Entity)((Bat)this), !this.m_27452_())) {
            ci.cancel();
        }
    }
}

