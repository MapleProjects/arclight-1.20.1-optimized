/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.SignBlock
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.BlockHitResult
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.player.PlayerSignOpenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={SignBlock.class})
public abstract class SignBlockMixin {
    private transient PlayerSignOpenEvent.Cause arclight$edit;

    @Shadow
    public abstract void m_276926_(Player var1, SignBlockEntity var2, boolean var3);

    @Inject(method={"use"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/block/SignBlock;openTextEdit(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/entity/SignBlockEntity;Z)V")})
    private void arclight$beforeEdit(BlockState p_56278_, Level p_56279_, BlockPos p_56280_, Player p_56281_, InteractionHand p_56282_, BlockHitResult p_56283_, CallbackInfoReturnable<InteractionResult> cir) {
        this.arclight$edit = PlayerSignOpenEvent.Cause.INTERACT;
    }

    @Inject(method={"use"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/level/block/SignBlock;openTextEdit(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/block/entity/SignBlockEntity;Z)V")})
    private void arclight$afterEdit(BlockState p_56278_, Level p_56279_, BlockPos p_56280_, Player p_56281_, InteractionHand p_56282_, BlockHitResult p_56283_, CallbackInfoReturnable<InteractionResult> cir) {
        this.arclight$edit = null;
    }

    public void openTextEdit(Player p_277738_, SignBlockEntity p_277467_, boolean p_277771_, PlayerSignOpenEvent.Cause cause) {
        this.arclight$edit = cause;
        this.m_276926_(p_277738_, p_277467_, p_277771_);
        this.arclight$edit = null;
    }

    @Inject(method={"openTextEdit"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$signEdit(Player player, SignBlockEntity signBlockEntity, boolean flag, CallbackInfo ci) {
        if (!CraftEventFactory.callPlayerSignOpenEvent(player, signBlockEntity, flag, this.arclight$edit != null ? this.arclight$edit : (flag ? PlayerSignOpenEvent.Cause.PLACE : PlayerSignOpenEvent.Cause.UNKNOWN))) {
            ci.cancel();
        }
    }
}

