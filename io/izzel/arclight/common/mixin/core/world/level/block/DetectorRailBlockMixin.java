/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.DetectorRailBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyVariable
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.DetectorRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={DetectorRailBlock.class})
public class DetectorRailBlockMixin {
    private transient boolean arclight$flag;

    @Inject(method={"checkPressed"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="JUMP", ordinal=1, opcode=153)})
    public void arclight$blockRedstone(Level worldIn, BlockPos pos, BlockState state, CallbackInfo ci, boolean flag, boolean flag1) {
        if (flag != flag1) {
            CraftBlock block = CraftBlock.at((LevelAccessor)worldIn, pos);
            BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(block, flag ? 15 : 0, flag1 ? 15 : 0);
            Bukkit.getPluginManager().callEvent(eventRedstone);
            this.arclight$flag = eventRedstone.getNewCurrent() > 0;
        }
    }

    @ModifyVariable(method={"checkPressed"}, index=5, name={"flag1"}, at=@At(value="JUMP", ordinal=1, opcode=153))
    public boolean arclight$blockRedstone(boolean flag1) {
        return this.arclight$flag;
    }
}

