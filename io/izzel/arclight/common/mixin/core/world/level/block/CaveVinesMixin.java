/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  jline.internal.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.CaveVines
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import java.util.Collections;
import jline.internal.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CaveVines;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.player.PlayerHarvestBlockEvent;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={CaveVines.class})
public interface CaveVinesMixin {
    @Overwrite
    public static InteractionResult m_269473_(@Nullable Entity entity, BlockState state, Level level, BlockPos pos) {
        if (((Boolean)state.m_61143_((Property)CaveVines.f_152949_)).booleanValue()) {
            if (entity != null) {
                if (!CraftEventFactory.callEntityChangeBlockEvent(entity, pos, (BlockState)state.m_61124_((Property)CaveVines.f_152949_, (Comparable)Boolean.valueOf(false)))) {
                    return InteractionResult.SUCCESS;
                }
                if (entity instanceof Player) {
                    PlayerHarvestBlockEvent event = CraftEventFactory.callPlayerHarvestBlockEvent(level, pos, (Player)entity, InteractionHand.MAIN_HAND, Collections.singletonList(new net.minecraft.world.item.ItemStack((ItemLike)Items.f_151079_, 1)));
                    if (event.isCancelled()) {
                        return InteractionResult.SUCCESS;
                    }
                    for (ItemStack itemStack : event.getItemsHarvested()) {
                        Block.m_49840_((Level)level, (BlockPos)pos, (net.minecraft.world.item.ItemStack)CraftItemStack.asNMSCopy(itemStack));
                    }
                } else {
                    Block.m_49840_((Level)level, (BlockPos)pos, (net.minecraft.world.item.ItemStack)new net.minecraft.world.item.ItemStack((ItemLike)Items.f_151079_, 1));
                }
            }
            Block.m_49840_((Level)level, (BlockPos)pos, (net.minecraft.world.item.ItemStack)new net.minecraft.world.item.ItemStack((ItemLike)Items.f_151079_, 1));
            float f = Mth.m_216283_((RandomSource)level.f_46441_, (float)0.8f, (float)1.2f);
            level.m_5594_(null, pos, SoundEvents.f_144088_, SoundSource.BLOCKS, 1.0f, f);
            BlockState newState = (BlockState)state.m_61124_((Property)CaveVines.f_152949_, (Comparable)Boolean.FALSE);
            level.m_7731_(pos, newState, 2);
            level.m_220407_(GameEvent.f_157792_, pos, GameEvent.Context.m_223719_((Entity)entity, (BlockState)newState));
            return InteractionResult.m_19078_((boolean)level.f_46443_);
        }
        return InteractionResult.PASS;
    }
}

