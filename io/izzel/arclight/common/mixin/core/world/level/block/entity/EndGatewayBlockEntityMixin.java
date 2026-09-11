/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.network.play.ServerPlayNetHandlerBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.BlockEntityMixin;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={TheEndGatewayBlockEntity.class})
public abstract class EndGatewayBlockEntityMixin
extends BlockEntityMixin {
    @Shadow
    private static void m_155849_(Level p_155850_, BlockPos p_155851_, BlockState p_155852_, TheEndGatewayBlockEntity p_155853_) {
    }

    @Inject(method={"teleportEntity"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;setPortalCooldown()V")})
    private static void arclight$portal(Level level, BlockPos pos, BlockState state, Entity entityIn, TheEndGatewayBlockEntity entity, CallbackInfo ci, ServerLevel serverLevel, BlockPos dest) {
        if (entityIn instanceof ServerPlayer) {
            CraftPlayer player = ((ServerPlayerEntityBridge)entityIn).bridge$getBukkitEntity();
            Location location = new Location(((WorldBridge)level).bridge$getWorld(), (double)dest.m_123341_() + 0.5, (double)dest.m_123342_() + 0.5, (double)dest.m_123343_() + 0.5);
            location.setPitch(player.getLocation().getPitch());
            location.setYaw(player.getLocation().getYaw());
            PlayerTeleportEvent event = new PlayerTeleportEvent(player, player.getLocation(), location, PlayerTeleportEvent.TeleportCause.END_GATEWAY);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                ci.cancel();
                return;
            }
            entityIn.m_20091_();
            ((ServerPlayNetHandlerBridge)((ServerPlayer)entityIn).f_8906_).bridge$teleport(event.getTo());
            EndGatewayBlockEntityMixin.m_155849_(level, pos, state, entity);
            ci.cancel();
        }
    }
}

