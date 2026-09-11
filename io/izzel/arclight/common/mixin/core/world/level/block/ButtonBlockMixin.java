/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.AbstractArrow
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.ButtonBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.BlockHitResult
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.entity.EntityInteractEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={ButtonBlock.class})
public class ButtonBlockMixin {
    @Shadow
    @Final
    public static BooleanProperty f_51045_;

    @Inject(method={"checkPressed"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/block/state/BlockState;getValue(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;")})
    public void arclight$entityInteract(BlockState state, Level worldIn, BlockPos pos, CallbackInfo ci, AbstractArrow abstractarrow, boolean flag) {
        boolean flag1 = (Boolean)state.m_61143_((Property)ButtonBlock.f_51045_);
        if (flag1 != flag && flag) {
            CraftBlock block = CraftBlock.at((LevelAccessor)worldIn, pos);
            EntityInteractEvent event = new EntityInteractEvent(((EntityBridge)abstractarrow).bridge$getBukkitEntity(), block);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
            }
        }
    }

    @Inject(method={"checkPressed"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;setBlock(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z")})
    public void arclight$blockRedstone3(BlockState state, Level worldIn, BlockPos pos, CallbackInfo ci, AbstractArrow abstractarrow, boolean flag, boolean flag1) {
        CraftBlock block = CraftBlock.at((LevelAccessor)worldIn, pos);
        int old = flag1 ? 15 : 0;
        int current = !flag1 ? 15 : 0;
        BlockRedstoneEvent event = new BlockRedstoneEvent(block, old, current);
        Bukkit.getPluginManager().callEvent(event);
        if (flag && event.getNewCurrent() <= 0 || !flag && event.getNewCurrent() > 0) {
            ci.cancel();
        }
    }

    @Inject(method={"use"}, cancellable=true, at={@At(value="HEAD")})
    public void arclight$blockRedstone1(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit, CallbackInfoReturnable<Boolean> cir) {
        if (!((Boolean)state.m_61143_((Property)f_51045_)).booleanValue()) {
            boolean powered = (Boolean)state.m_61143_((Property)f_51045_);
            CraftBlock block = CraftBlock.at((LevelAccessor)worldIn, pos);
            int old = powered ? 15 : 0;
            int current = !powered ? 15 : 0;
            BlockRedstoneEvent event = new BlockRedstoneEvent(block, old, current);
            Bukkit.getPluginManager().callEvent(event);
            if (event.getNewCurrent() > 0 == powered) {
                cir.setReturnValue((Object)true);
            }
        }
    }
}

