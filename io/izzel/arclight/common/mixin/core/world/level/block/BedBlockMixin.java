/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.Level$ExplosionInteraction
 *  net.minecraft.world.level.block.BedBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.HorizontalDirectionalBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BedPart
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={BedBlock.class})
public abstract class BedBlockMixin {
    @Shadow
    @Final
    public static EnumProperty<BedPart> f_49440_;
    @Shadow
    @Final
    public static BooleanProperty f_49441_;

    @Shadow
    protected abstract boolean m_49490_(Level var1, BlockPos var2);

    @Overwrite
    public InteractionResult m_6227_(BlockState p_49515_, Level level, BlockPos p_49517_, Player p_49518_, InteractionHand p_49519_, BlockHitResult p_49520_) {
        if (level.f_46443_) {
            return InteractionResult.CONSUME;
        }
        if (p_49515_.m_61143_(f_49440_) != BedPart.HEAD && !(p_49515_ = level.m_8055_(p_49517_ = p_49517_.m_121945_((Direction)p_49515_.m_61143_((Property)HorizontalDirectionalBlock.f_54117_)))).m_60713_((Block)((BedBlock)this))) {
            return InteractionResult.CONSUME;
        }
        if (((Boolean)p_49515_.m_61143_((Property)f_49441_)).booleanValue()) {
            if (!this.m_49490_(level, p_49517_)) {
                p_49518_.m_5661_((Component)Component.m_237115_((String)"block.minecraft.bed.occupied"), true);
            }
            return InteractionResult.SUCCESS;
        }
        BlockPos pos = p_49517_;
        BlockState state = p_49515_;
        p_49518_.m_7720_(pos).ifLeft(p_49477_ -> {
            if (!level.m_6042_().f_63862_()) {
                level.m_7471_(pos, false);
                BlockPos blockpos = pos.m_121945_(((Direction)state.m_61143_((Property)HorizontalDirectionalBlock.f_54117_)).m_122424_());
                if (level.m_8055_(blockpos).m_60713_((Block)((BedBlock)this))) {
                    level.m_7471_(blockpos, false);
                }
                Vec3 vec3d = pos.m_252807_();
                level.m_254951_(null, level.m_269111_().m_269488_(vec3d), null, vec3d, 5.0f, true, Level.ExplosionInteraction.BLOCK);
            } else if (p_49477_.m_36423_() != null) {
                p_49518_.m_5661_(p_49477_.m_36423_(), true);
            }
        });
        return InteractionResult.SUCCESS;
    }
}

