/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.animal.allay.Allay
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.MobMixin;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.allay.Allay;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Allay.class})
public abstract class AllayMixin
extends MobMixin {
    @Shadow
    @Final
    private static EntityDataAccessor<Boolean> f_238802_;
    public boolean forceDancing = false;
    private transient Allay arclight$duplicate;

    @Shadow
    private void shadow$m_218376_() {
    }

    public void setCanDuplicate(boolean canDuplicate) {
        this.f_19804_.m_135381_(f_238802_, (Object)canDuplicate);
    }

    @Inject(method={"aiStep"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/animal/allay/Allay;heal(F)V")})
    private void arclight$healReason(CallbackInfo ci) {
        this.bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.REGEN);
    }

    @Inject(method={"mobInteract"}, cancellable=true, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/animal/allay/Allay;duplicateAllay()V")})
    private void arclight$cancelDuplicate(Player p_218361_, InteractionHand p_218362_, CallbackInfoReturnable<InteractionResult> cir) {
        Allay allay = this.arclight$duplicate;
        this.arclight$duplicate = null;
        if (allay == null) {
            cir.setReturnValue((Object)InteractionResult.SUCCESS);
        }
    }

    @Inject(method={"shouldStopDancing"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$stopDancing(CallbackInfoReturnable<Boolean> cir) {
        if (this.forceDancing) {
            cir.setReturnValue((Object)false);
        }
    }

    @Redirect(method={"duplicateAllay"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$captureDuplicate(Level instance, Entity entity) {
        ((WorldBridge)instance).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.DUPLICATION);
        if (instance.m_7967_(entity)) {
            this.arclight$duplicate = (Allay)entity;
            return true;
        }
        return false;
    }

    public Allay duplicateAllay() {
        try {
            this.shadow$m_218376_();
            Allay allay = this.arclight$duplicate;
            return allay;
        }
        finally {
            this.arclight$duplicate = null;
        }
    }
}

