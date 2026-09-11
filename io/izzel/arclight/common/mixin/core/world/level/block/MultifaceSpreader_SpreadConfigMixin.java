/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.MultifaceSpreader$SpreadConfig
 *  net.minecraft.world.level.block.MultifaceSpreader$SpreadPos
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.mod.util.ArclightCaptures;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.MultifaceSpreader;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={MultifaceSpreader.SpreadConfig.class})
public interface MultifaceSpreader_SpreadConfigMixin {
    @Shadow
    @Nullable
    public BlockState m_214136_(BlockState var1, BlockGetter var2, BlockPos var3, Direction var4);

    @Overwrite
    default public boolean m_221701_(LevelAccessor level, MultifaceSpreader.SpreadPos spreadPos, BlockState state, boolean p_221705_) {
        BlockState blockstate = this.m_214136_(state, (BlockGetter)level, spreadPos.f_221717_(), spreadPos.f_221718_());
        if (blockstate != null) {
            if (p_221705_) {
                level.m_46865_(spreadPos.f_221717_()).m_8113_(spreadPos.f_221717_());
            }
            return CraftEventFactory.handleBlockSpreadEvent(level, ArclightCaptures.getSpreadPos(), spreadPos.f_221717_(), blockstate, 2);
        }
        return false;
    }
}

