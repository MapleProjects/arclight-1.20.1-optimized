/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.item.PlaceOnWaterBlockItem
 *  net.minecraft.world.item.SolidBucketItem
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.shapes.CollisionContext
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.bukkit.Bukkit;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={BlockItem.class})
public abstract class BlockItemMixin {
    private transient BlockState arclight$state;

    @Shadow
    protected abstract boolean m_6652_();

    @Shadow
    private static <T extends Comparable<T>> net.minecraft.world.level.block.state.BlockState m_40593_(net.minecraft.world.level.block.state.BlockState p_219988_0_, Property<T> p_219988_1_, String p_219988_2_) {
        return null;
    }

    @Inject(method={"place"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/item/BlockItem;getPlacementState(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;")})
    private void arclight$prePlaceLilypad(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir, BlockPlaceContext context1) {
        if (this instanceof PlaceOnWaterBlockItem || this instanceof SolidBucketItem) {
            this.arclight$state = CraftBlockStates.getBlockState((LevelAccessor)context1.m_43725_(), context1.m_8083_());
        }
    }

    @Inject(method={"place"}, locals=LocalCapture.CAPTURE_FAILHARD, cancellable=true, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/level/block/Block;setPlacedBy(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;)V")})
    private void arclight$postPlaceLilypad(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir, BlockPlaceContext context1) {
        BlockPlaceEvent placeEvent;
        BlockState state = this.arclight$state;
        this.arclight$state = null;
        BlockPos pos = context1.m_8083_();
        if (state != null && DistValidate.isValid((UseOnContext)context) && (placeEvent = CraftEventFactory.callBlockPlaceEvent((ServerLevel)context1.m_43725_(), context1.m_43723_(), context1.m_43724_(), state, pos.m_123341_(), pos.m_123342_(), pos.m_123343_())) != null && (placeEvent.isCancelled() || !placeEvent.canBuild())) {
            state.update(true, false);
            if (this instanceof SolidBucketItem) {
                ((ServerPlayerEntityBridge)context1.m_43723_()).bridge$getBukkitEntity().updateInventory();
            }
            cir.setReturnValue((Object)InteractionResult.FAIL);
        }
    }

    @Inject(method={"place"}, at={@At(value="RETURN")})
    private void arclight$cleanup(BlockPlaceContext context, CallbackInfoReturnable<InteractionResult> cir) {
        this.arclight$state = null;
    }

    private static net.minecraft.world.level.block.state.BlockState getBlockState(net.minecraft.world.level.block.state.BlockState blockState, CompoundTag nbt) {
        StateDefinition statecontainer = blockState.m_60734_().m_49965_();
        for (String s : nbt.m_128431_()) {
            Property iproperty = statecontainer.m_61081_(s);
            if (iproperty == null) continue;
            String s1 = nbt.m_128423_(s).m_7916_();
            blockState = BlockItemMixin.m_40593_(blockState, iproperty, s1);
        }
        return blockState;
    }

    @Overwrite
    protected boolean m_40610_(BlockPlaceContext context, net.minecraft.world.level.block.state.BlockState state) {
        Player playerentity = context.m_43723_();
        CollisionContext iselectioncontext = playerentity == null ? CollisionContext.m_82749_() : CollisionContext.m_82750_((Entity)playerentity);
        boolean original = (!this.m_6652_() || state.m_60710_((LevelReader)context.m_43725_(), context.m_8083_())) && context.m_43725_().m_45752_(state, context.m_8083_(), iselectioncontext);
        CraftPlayer player = context.m_43723_() instanceof ServerPlayerEntityBridge ? ((ServerPlayerEntityBridge)context.m_43723_()).bridge$getBukkitEntity() : null;
        BlockCanBuildEvent event = new BlockCanBuildEvent(CraftBlock.at((LevelAccessor)context.m_43725_(), context.m_8083_()), player, CraftBlockData.fromData(state), original);
        if (DistValidate.isValid((UseOnContext)context)) {
            Bukkit.getPluginManager().callEvent(event);
        }
        return event.isBuildable();
    }
}

