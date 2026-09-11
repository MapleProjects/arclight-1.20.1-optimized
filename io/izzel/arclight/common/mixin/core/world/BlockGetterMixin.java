/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world;

import io.izzel.arclight.common.bridge.core.world.IBlockReaderBridge;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={BlockGetter.class})
public interface BlockGetterMixin
extends IBlockReaderBridge {
    @Shadow
    public BlockState m_8055_(BlockPos var1);

    @Shadow
    public FluidState m_6425_(BlockPos var1);

    @Shadow
    @Nullable
    public BlockHitResult m_45558_(Vec3 var1, Vec3 var2, BlockPos var3, VoxelShape var4, BlockState var5);

    default public BlockHitResult clip(ClipContext context, BlockPos pos) {
        BlockState blockstate = this.m_8055_(pos);
        FluidState ifluidstate = this.m_6425_(pos);
        Vec3 vec3d = context.m_45702_();
        Vec3 vec3d1 = context.m_45702_();
        VoxelShape voxelshape = context.m_45694_(blockstate, (BlockGetter)this, pos);
        BlockHitResult blockraytraceresult = this.m_45558_(vec3d, vec3d1, pos, voxelshape, blockstate);
        VoxelShape voxelshape1 = context.m_45698_(ifluidstate, (BlockGetter)this, pos);
        BlockHitResult blockraytraceresult1 = voxelshape1.m_83220_(vec3d, vec3d1, pos);
        double d0 = blockraytraceresult == null ? Double.MAX_VALUE : context.m_45702_().m_82557_(blockraytraceresult.m_82450_());
        double d1 = blockraytraceresult1 == null ? Double.MAX_VALUE : context.m_45702_().m_82557_(blockraytraceresult1.m_82450_());
        return d0 <= d1 ? blockraytraceresult : blockraytraceresult1;
    }

    @Override
    default public BlockHitResult bridge$rayTraceBlock(ClipContext context, BlockPos pos) {
        return this.clip(context, pos);
    }
}

