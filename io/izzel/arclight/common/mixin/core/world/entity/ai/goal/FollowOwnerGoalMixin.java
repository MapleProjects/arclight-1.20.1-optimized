/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.TamableAnimal
 *  net.minecraft.world.entity.ai.goal.FollowOwnerGoal
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.goal;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={FollowOwnerGoal.class})
public class FollowOwnerGoalMixin {
    @Shadow
    @Final
    private TamableAnimal f_25283_;
    private transient boolean arclight$cancelled;

    @Redirect(method={"maybeTeleportTo"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/TamableAnimal;moveTo(DDDFF)V"))
    public void arclight$teleport(TamableAnimal tameableEntity, double x, double y, double z, float yaw, float pitch) {
        CraftEntity craftEntity = ((EntityBridge)this.f_25283_).bridge$getBukkitEntity();
        Location location = new Location(craftEntity.getWorld(), x, y, z, yaw, pitch);
        EntityTeleportEvent event = new EntityTeleportEvent(craftEntity, craftEntity.getLocation(), location);
        Bukkit.getPluginManager().callEvent(event);
        this.arclight$cancelled = event.isCancelled();
        if (!this.arclight$cancelled) {
            tameableEntity.m_7678_(event.getTo().getX(), event.getTo().getY(), event.getTo().getZ(), event.getTo().getYaw(), event.getTo().getPitch());
        }
    }

    @Inject(method={"maybeTeleportTo"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/ai/navigation/PathNavigation;stop()V")})
    public void arclight$returnIfFail(int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (this.arclight$cancelled) {
            cir.setReturnValue((Object)false);
        }
        this.arclight$cancelled = false;
    }
}

