/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.FireBlock
 *  net.minecraft.world.level.block.TntBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.block.FireBlockBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.BaseFireBlockMixin;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.TNTPrimeEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={FireBlock.class})
public abstract class FireBlockMixin
extends BaseFireBlockMixin
implements FireBlockBridge {
    @Shadow
    @Final
    private Object2IntMap<Block> f_53422_;

    @Shadow
    protected abstract BlockState m_53470_(BlockGetter var1, BlockPos var2);

    @Redirect(method={"tick"}, at=@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/server/level/ServerLevel;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z"))
    public boolean arclight$fireSpread(ServerLevel world, BlockPos mutablePos, BlockState newState, int flags, BlockState state, ServerLevel worldIn, BlockPos pos) {
        if (world.m_8055_(mutablePos).m_60734_() != Blocks.f_50083_ && !CraftEventFactory.callBlockIgniteEvent((Level)world, mutablePos, pos).isCancelled()) {
            return CraftEventFactory.handleBlockSpreadEvent((LevelAccessor)world, pos, mutablePos, newState, flags);
        }
        return false;
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"))
    public boolean arclight$extinguish1(ServerLevel world, BlockPos pos, boolean isMoving) {
        if (!CraftEventFactory.callBlockFadeEvent((LevelAccessor)world, pos, Blocks.f_50016_.m_49966_()).isCancelled()) {
            world.m_7471_(pos, isMoving);
        }
        return false;
    }

    @Inject(method={"tryCatchFire"}, cancellable=true, at={@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/world/level/Level;getBlockState(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;")})
    public void arclight$blockBurn(Level worldIn, BlockPos pos, int chance, RandomSource random, int age, Direction face, CallbackInfo ci) {
        CraftBlock theBlock = CraftBlock.at((LevelAccessor)worldIn, pos);
        CraftBlock sourceBlock = CraftBlock.at((LevelAccessor)worldIn, pos.m_121945_(face));
        BlockBurnEvent event = new BlockBurnEvent(theBlock, sourceBlock);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
            return;
        }
        if (worldIn.m_8055_(pos).m_60734_() instanceof TntBlock && !CraftEventFactory.callTNTPrimeEvent(worldIn, pos, TNTPrimeEvent.PrimeCause.FIRE, null, pos.m_121945_(face))) {
            ci.cancel();
        }
    }

    @Redirect(method={"updateShape"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/Block;defaultBlockState()Lnet/minecraft/world/level/block/state/BlockState;"))
    public BlockState arclight$blockFade(Block block, BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!(worldIn instanceof Level)) {
            return Blocks.f_50016_.m_49966_();
        }
        CraftBlockState blockState = CraftBlockStates.getBlockState(worldIn, currentPos);
        blockState.setData(Blocks.f_50016_.m_49966_());
        BlockFadeEvent event = new BlockFadeEvent(blockState.getBlock(), blockState);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return (BlockState)this.m_53470_((BlockGetter)worldIn, currentPos).m_61124_((Property)FireBlock.f_53408_, (Comparable)((Integer)stateIn.m_61143_((Property)FireBlock.f_53408_)));
        }
        return blockState.getHandle();
    }

    @Override
    public boolean bridge$canBurn(Block block) {
        return this.f_53422_.containsKey((Object)block);
    }
}

