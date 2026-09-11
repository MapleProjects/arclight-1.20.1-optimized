/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.state;

import io.izzel.arclight.common.mod.util.ArclightCaptures;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BlockBehaviour.BlockStateBase.class})
public class BlockBehaviour_BlockStateBaseMixin {
    @Inject(method={"entityInside"}, at={@At(value="HEAD")})
    private void arclight$captureBlockCollide(Level worldIn, BlockPos pos, Entity entityIn, CallbackInfo ci) {
        ArclightCaptures.captureDamageEventBlock(pos);
    }

    @Inject(method={"entityInside"}, at={@At(value="RETURN")})
    private void arclight$resetBlockCollide(Level worldIn, BlockPos pos, Entity entityIn, CallbackInfo ci) {
        ArclightCaptures.captureDamageEventBlock(null);
    }
}

