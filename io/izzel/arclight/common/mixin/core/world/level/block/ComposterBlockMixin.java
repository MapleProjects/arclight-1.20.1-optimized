/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.Object2FloatMap
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.ItemLike
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.ComposterBlock
 *  net.minecraft.world.level.block.ComposterBlock$EmptyContainer
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftBlockInventoryHolder;
import org.bukkit.craftbukkit.v1_20_R1.util.DummyGeneratorAccess;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ComposterBlock.class})
public abstract class ComposterBlockMixin {
    @Shadow
    @Final
    public static IntegerProperty f_51913_;
    @Shadow
    @Final
    public static Object2FloatMap<ItemLike> f_51914_;

    @Shadow
    static BlockState m_269590_(@Nullable Entity p_270236_, BlockState p_270873_, LevelAccessor p_270963_, BlockPos p_270211_) {
        return null;
    }

    @Redirect(method={"getContainer"}, at=@At(value="NEW", target="()Lnet/minecraft/world/level/block/ComposterBlock$EmptyContainer;"))
    public ComposterBlock.EmptyContainer arclight$newEmpty(BlockState blockState, LevelAccessor world, BlockPos blockPos) {
        ComposterBlock.EmptyContainer inventory = new ComposterBlock.EmptyContainer();
        ((IInventoryBridge)inventory).setOwner(new CraftBlockInventoryHolder(world, blockPos, (Container)inventory));
        return inventory;
    }

    @Overwrite
    public static BlockState m_268990_(Entity entity, BlockState state, ServerLevel world, ItemStack stack, BlockPos pos) {
        int i = (Integer)state.m_61143_((Property)f_51913_);
        if (i < 7 && f_51914_.containsKey((Object)stack.m_41720_())) {
            double rand = world.f_46441_.m_188500_();
            BlockState state1 = ComposterBlockMixin.addItem(entity, state, (LevelAccessor)DummyGeneratorAccess.INSTANCE, pos, stack, rand);
            if (state == state1 || entity != null && !CraftEventFactory.callEntityChangeBlockEvent(entity, pos, state1)) {
                return state;
            }
            state1 = ComposterBlockMixin.addItem(entity, state, (LevelAccessor)world, pos, stack, rand);
            stack.m_41774_(1);
            return state1;
        }
        return state;
    }

    @Inject(method={"extractProduce"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$emptyComposter(Entity entity, BlockState state, Level world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        BlockState blockState;
        if (entity != null && !(entity instanceof Player) && !CraftEventFactory.callEntityChangeBlockEvent(entity, pos, blockState = ComposterBlockMixin.m_269590_(entity, state, (LevelAccessor)DummyGeneratorAccess.INSTANCE, pos))) {
            cir.setReturnValue((Object)state);
        }
    }

    private static BlockState addItem(Entity entity, BlockState state, LevelAccessor world, BlockPos pos, ItemStack stack, double rand) {
        int i = (Integer)state.m_61143_((Property)f_51913_);
        float f = f_51914_.getFloat((Object)stack.m_41720_());
        if (!(i == 0 && f > 0.0f || rand < (double)f)) {
            return state;
        }
        int j = i + 1;
        BlockState blockstate = (BlockState)state.m_61124_((Property)f_51913_, (Comparable)Integer.valueOf(j));
        world.m_7731_(pos, blockstate, 3);
        world.m_220407_(GameEvent.f_157792_, pos, GameEvent.Context.m_223719_((Entity)entity, (BlockState)blockstate));
        if (j == 7) {
            world.m_186460_(pos, state.m_60734_(), 20);
        }
        return blockstate;
    }
}

