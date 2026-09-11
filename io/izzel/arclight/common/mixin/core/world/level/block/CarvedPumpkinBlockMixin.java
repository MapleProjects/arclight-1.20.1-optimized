/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.CarvedPumpkinBlock
 *  net.minecraft.world.level.block.state.pattern.BlockPattern$BlockPatternMatch
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={CarvedPumpkinBlock.class})
public class CarvedPumpkinBlockMixin {
    @Redirect(method={"spawnGolemInWorld"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/CarvedPumpkinBlock;clearPatternBlocks(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/pattern/BlockPattern$BlockPatternMatch;)V"))
    private static void arclight$clearLater(Level level, BlockPattern.BlockPatternMatch match) {
    }

    @Inject(method={"spawnGolemInWorld"}, cancellable=true, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    private static void arclight$clearPattern(Level p_249110_, BlockPattern.BlockPatternMatch p_251293_, Entity entity, BlockPos p_251189_, CallbackInfo ci) {
        if (!entity.isAddedToWorld()) {
            ci.cancel();
        } else {
            CarvedPumpkinBlock.m_245585_((Level)p_249110_, (BlockPattern.BlockPatternMatch)p_251293_);
        }
    }
}

