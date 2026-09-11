/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.goal.EatBlockGoal
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={EatBlockGoal.class})
public class EatBlockGoalMixin {
    private transient BlockPos arclight$pos;

    @Inject(method={"tick"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", ordinal=0, remap=false, target="Lnet/minecraftforge/event/ForgeEventFactory;getMobGriefingEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)Z")})
    public void arclight$capturePos1(CallbackInfo ci, BlockPos pos) {
        this.arclight$pos = pos;
    }

    @Inject(method={"tick"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", ordinal=1, remap=false, target="Lnet/minecraftforge/event/ForgeEventFactory;getMobGriefingEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)Z")})
    public void arclight$capturePos2(CallbackInfo ci, BlockPos pos) {
        this.arclight$pos = pos.m_7495_();
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", remap=false, target="Lnet/minecraftforge/event/ForgeEventFactory;getMobGriefingEvent(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;)Z"))
    public boolean arclight$entityChangeBlock(Level world, Entity entity) {
        boolean b = ForgeEventFactory.getMobGriefingEvent((Level)world, (Entity)entity);
        boolean result = CraftEventFactory.callEntityChangeBlockEvent(entity, this.arclight$pos, Blocks.f_50016_.m_49966_(), !b);
        this.arclight$pos = null;
        return result;
    }
}

