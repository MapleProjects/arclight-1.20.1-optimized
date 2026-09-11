/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.CommandBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.CommandBlockEntity
 *  net.minecraft.world.level.block.entity.CommandBlockEntity$Mode
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CommandBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={CommandBlock.class})
public abstract class CommandBlockMixin {
    @Overwrite
    public void m_6861_(BlockState state, Level worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        BlockEntity tileentity;
        if (!worldIn.f_46443_ && (tileentity = worldIn.m_7702_(pos)) instanceof CommandBlockEntity) {
            CommandBlockEntity commandblocktileentity = (CommandBlockEntity)tileentity;
            boolean flag = worldIn.m_276867_(pos);
            boolean flag1 = commandblocktileentity.m_59142_();
            CraftBlock bukkitBlock = CraftBlock.at((LevelAccessor)worldIn, pos);
            int old = flag1 ? 15 : 0;
            int current = flag ? 15 : 0;
            BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bukkitBlock, old, current);
            Bukkit.getPluginManager().callEvent(eventRedstone);
            flag = eventRedstone.getNewCurrent() > 0;
            commandblocktileentity.m_59135_(flag);
            if (!flag1 && !commandblocktileentity.m_59143_() && commandblocktileentity.m_59148_() != CommandBlockEntity.Mode.SEQUENCE && flag) {
                commandblocktileentity.m_59146_();
                worldIn.m_186460_(pos, (Block)((CommandBlock)this), 1);
            }
        }
    }
}

