/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.valueproviders.IntProvider
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.context.BlockPlaceContext
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.item.enchantment.Enchantments
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.common.extensions.IForgeBlock
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.block.BlockBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.state.BlockBehaviourMixin;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.common.mod.util.DistValidate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeBlock;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Block.class})
public abstract class BlockMixin
extends BlockBehaviourMixin
implements BlockBridge {
    @Shadow
    public abstract net.minecraft.world.level.block.state.BlockState m_49966_();

    @Shadow
    @Nullable
    public net.minecraft.world.level.block.state.BlockState m_5573_(BlockPlaceContext context) {
        return null;
    }

    @Overwrite
    public static void m_49840_(Level worldIn, BlockPos pos, ItemStack stack) {
        if (!worldIn.f_46443_ && !stack.m_41619_() && worldIn.m_46469_().m_46207_(GameRules.f_46136_) && !worldIn.restoringBlockSnapshots) {
            float f = 0.5f;
            double d0 = (double)(worldIn.f_46441_.m_188501_() * 0.5f) + 0.25;
            double d1 = (double)(worldIn.f_46441_.m_188501_() * 0.5f) + 0.25;
            double d2 = (double)(worldIn.f_46441_.m_188501_() * 0.5f) + 0.25;
            ItemEntity itemEntity = new ItemEntity(worldIn, (double)pos.m_123341_() + d0, (double)pos.m_123342_() + d1, (double)pos.m_123343_() + d2, stack);
            itemEntity.m_32060_();
            List<ItemEntity> blockDrops = ArclightCaptures.getBlockDrops();
            if (blockDrops == null) {
                worldIn.m_7967_((Entity)itemEntity);
            } else {
                blockDrops.add(itemEntity);
            }
        }
    }

    public int getExpDrop(net.minecraft.world.level.block.state.BlockState blockState, ServerLevel world, BlockPos blockPos, ItemStack itemStack, boolean flag) {
        int silkTouch = itemStack.getEnchantmentLevel(Enchantments.f_44985_);
        int fortune = itemStack.getEnchantmentLevel(Enchantments.f_44987_);
        return ((IForgeBlock)this).getExpDrop(blockState, (LevelReader)world, world.f_46441_, blockPos, fortune, silkTouch);
    }

    protected int tryDropExperience(ServerLevel worldserver, BlockPos blockposition, ItemStack itemstack, IntProvider intprovider) {
        int i;
        if (EnchantmentHelper.m_44843_((Enchantment)Enchantments.f_44985_, (ItemStack)itemstack) == 0 && (i = intprovider.m_214085_(worldserver.f_46441_)) > 0) {
            return i;
        }
        return 0;
    }

    @Override
    public int bridge$getExpDrop(net.minecraft.world.level.block.state.BlockState blockState, ServerLevel world, BlockPos blockPos, ItemStack itemStack) {
        return this.getExpDrop(blockState, world, blockPos, itemStack, true);
    }

    @Inject(method={"playerDestroy"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;causeFoodExhaustion(F)V")})
    private void arclight$reason(Level p_49827_, Player player, BlockPos p_49829_, net.minecraft.world.level.block.state.BlockState p_49830_, BlockEntity p_49831_, ItemStack p_49832_, CallbackInfo ci) {
        ((PlayerEntityBridge)player).bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.BLOCK_MINED);
    }

    @Inject(method={"playerDestroy"}, at={@At(value="RETURN")})
    private void arclight$handleBlockDrops(Level worldIn, Player player, BlockPos pos, net.minecraft.world.level.block.state.BlockState blockState, BlockEntity te, ItemStack stack, CallbackInfo ci) {
        ArclightCaptures.BlockBreakEventContext breakEventContext = ArclightCaptures.popPrimaryBlockBreakEvent();
        if (breakEventContext != null) {
            BlockBreakEvent breakEvent = breakEventContext.getEvent();
            ArrayList<ItemEntity> blockDrops = breakEventContext.getBlockDrops();
            BlockState state = breakEventContext.getBlockBreakPlayerState();
            if (player instanceof ServerPlayer && blockDrops != null && (breakEvent == null || breakEvent.isDropItems()) && DistValidate.isValid((LevelAccessor)worldIn)) {
                CraftBlock craftBlock = CraftBlock.at((LevelAccessor)((CraftWorld)state.getWorld()).getHandle(), pos);
                CraftEventFactory.handleBlockDropItemEvent(craftBlock, state, (ServerPlayer)player, blockDrops);
            }
        }
    }
}

