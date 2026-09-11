/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.InteractionResultHolder
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.BucketItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.BucketPickup
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.item;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundBlockUpdatePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.DummyGeneratorAccess;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={BucketItem.class})
public abstract class BucketItemMixin {
    private transient @Nullable ItemStack arclight$captureItem;
    private transient Direction arclight$direction;
    private transient BlockPos arclight$click;
    private transient InteractionHand arclight$hand;

    @Shadow
    public abstract boolean m_142073_(@Nullable Player var1, Level var2, BlockPos var3, @javax.annotation.Nullable BlockHitResult var4);

    @Shadow(remap=false)
    public abstract boolean emptyContents(@Nullable Player var1, Level var2, BlockPos var3, @Nullable BlockHitResult var4, @Nullable net.minecraft.world.item.ItemStack var5);

    @Inject(method={"use"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/block/BucketPickup;pickupBlock(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/item/ItemStack;")})
    private void arclight$bucketFill(Level worldIn, Player playerIn, InteractionHand handIn, CallbackInfoReturnable<InteractionResultHolder<net.minecraft.world.item.ItemStack>> cir, net.minecraft.world.item.ItemStack stack, BlockHitResult result) {
        if (!DistValidate.isValid((LevelAccessor)worldIn)) {
            return;
        }
        BlockPos pos = result.m_82425_();
        BlockState state = worldIn.m_8055_(pos);
        net.minecraft.world.item.ItemStack dummyFluid = ((BucketPickup)state.m_60734_()).m_142598_((LevelAccessor)DummyGeneratorAccess.INSTANCE, pos, state);
        PlayerBucketFillEvent event = CraftEventFactory.callPlayerBucketFillEvent((ServerLevel)worldIn, playerIn, pos, pos, result.m_82434_(), stack, dummyFluid.m_41720_(), handIn);
        if (event.isCancelled()) {
            ((ServerPlayer)playerIn).f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)worldIn, pos));
            ((ServerPlayerEntityBridge)playerIn).bridge$getBukkitEntity().updateInventory();
            cir.setReturnValue((Object)new InteractionResultHolder(InteractionResult.FAIL, (Object)stack));
        } else {
            this.arclight$captureItem = event.getItemStack();
        }
    }

    @Inject(method={"use"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", remap=false, target="Lnet/minecraft/world/item/BucketItem;emptyContents(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;Lnet/minecraft/world/item/ItemStack;)Z")})
    private void arclight$capture(Level worldIn, Player playerIn, InteractionHand handIn, CallbackInfoReturnable<InteractionResultHolder<net.minecraft.world.item.ItemStack>> cir, net.minecraft.world.item.ItemStack stack, BlockHitResult result) {
        this.arclight$direction = result.m_82434_();
        this.arclight$click = result.m_82425_();
        this.arclight$hand = handIn;
    }

    @Inject(method={"use"}, at={@At(value="RETURN")})
    private void arclight$clean(Level worldIn, Player playerIn, InteractionHand handIn, CallbackInfoReturnable<InteractionResultHolder<net.minecraft.world.item.ItemStack>> cir) {
        this.arclight$captureItem = null;
        this.arclight$direction = null;
        this.arclight$click = null;
    }

    @ModifyArg(method={"use"}, index=2, at=@At(value="INVOKE", target="Lnet/minecraft/world/item/ItemUtils;createFilledResult(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;"))
    private net.minecraft.world.item.ItemStack arclight$useEventItem(net.minecraft.world.item.ItemStack itemStack) {
        return this.arclight$captureItem == null ? itemStack : CraftItemStack.asNMSCopy(this.arclight$captureItem);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean emptyContents(Player entity, Level world, BlockPos pos, @Nullable BlockHitResult result, Direction direction, BlockPos clicked, net.minecraft.world.item.ItemStack itemstack, InteractionHand hand) {
        this.arclight$direction = direction;
        this.arclight$click = clicked;
        this.arclight$hand = hand;
        try {
            boolean bl = this.emptyContents(entity, world, pos, result, itemstack);
            return bl;
        }
        finally {
            this.arclight$direction = null;
            this.arclight$click = null;
        }
    }

    @Inject(method={"emptyContents(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;Lnet/minecraft/world/item/ItemStack;)Z"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/dimension/DimensionType;ultraWarm()Z")})
    private void arclight$bucketEmpty(Player player, Level worldIn, BlockPos posIn, BlockHitResult rayTrace, net.minecraft.world.item.ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        PlayerBucketEmptyEvent event;
        if (!DistValidate.isValid((LevelAccessor)worldIn)) {
            return;
        }
        if (player != null && stack != null && (event = CraftEventFactory.callPlayerBucketEmptyEvent((ServerLevel)worldIn, player, posIn, this.arclight$click, this.arclight$direction, stack, this.arclight$hand == null ? InteractionHand.MAIN_HAND : this.arclight$hand)).isCancelled()) {
            ((ServerPlayer)player).f_8906_.m_9829_((Packet)new ClientboundBlockUpdatePacket((BlockGetter)worldIn, posIn));
            ((ServerPlayerEntityBridge)player).bridge$getBukkitEntity().updateInventory();
            cir.setReturnValue((Object)false);
        }
    }
}

