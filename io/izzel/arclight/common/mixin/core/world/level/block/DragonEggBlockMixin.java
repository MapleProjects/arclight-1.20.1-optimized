/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.DragonEggBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.border.WorldBorder
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
import net.minecraft.world.level.block.DragonEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockFromToEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={DragonEggBlock.class})
public class DragonEggBlockMixin {
    private transient BlockPos arclight$toBlock;

    @Inject(method={"teleport"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="FIELD", target="Lnet/minecraft/world/level/Level;isClientSide:Z")})
    public void arclight$blockFromTo(BlockState blockState, Level world, BlockPos blockPos, CallbackInfo ci, WorldBorder wb, int i, BlockPos pos) {
        CraftBlock from = CraftBlock.at((LevelAccessor)world, blockPos);
        CraftBlock to = CraftBlock.at((LevelAccessor)world, pos);
        BlockFromToEvent event = new BlockFromToEvent((Block)from, to);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
        } else {
            this.arclight$toBlock = new BlockPos(event.getToBlock().getX(), event.getToBlock().getY(), event.getToBlock().getZ());
        }
    }

    @ModifyVariable(method={"teleport"}, ordinal=1, name={"blockpos"}, at=@At(value="JUMP", opcode=153, ordinal=2))
    public BlockPos arclight$setPos(BlockPos pos) {
        return this.arclight$toBlock;
    }
}

