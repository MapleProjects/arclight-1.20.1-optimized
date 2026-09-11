/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.entity.animal.Turtle
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.gen.Invoker
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.entity.passive.TurtleEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.animal.AnimalMixin;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.Turtle;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Turtle.class})
public abstract class TurtleMixin
extends AnimalMixin
implements TurtleEntityBridge {
    @Override
    @Accessor(value="layEggCounter")
    public abstract int bridge$getDigging();

    @Override
    @Invoker(value="setLayingEgg")
    public abstract void bridge$setDigging(boolean var1);

    @Override
    @Accessor(value="layEggCounter")
    public abstract void bridge$setDigging(int var1);

    @Override
    @Invoker(value="setHasEgg")
    public abstract void bridge$setHasEgg(boolean var1);

    @Inject(method={"ageBoundaryReached"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/Turtle;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/entity/item/ItemEntity;")})
    private void arclight$forceDrop(CallbackInfo ci) {
        this.forceDrops = true;
    }

    @Inject(method={"ageBoundaryReached"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/animal/Turtle;spawnAtLocation(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/entity/item/ItemEntity;")})
    private void arclight$forceDropReset(CallbackInfo ci) {
        this.forceDrops = false;
    }

    @Inject(method={"thunderHit"}, at={@At(value="HEAD")})
    private void arclight$lightning(ServerLevel world, LightningBolt lightningBolt, CallbackInfo ci) {
        CraftEventFactory.entityDamage = lightningBolt;
    }

    @Inject(method={"thunderHit"}, at={@At(value="RETURN")})
    private void arclight$lightningReset(ServerLevel world, LightningBolt lightningBolt, CallbackInfo ci) {
        CraftEventFactory.entityDamage = null;
    }
}

