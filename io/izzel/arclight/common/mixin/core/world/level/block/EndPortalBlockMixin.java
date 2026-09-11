/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.EndPortalBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EndPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={EndPortalBlock.class})
public class EndPortalBlockMixin {
    @Redirect(method={"entityInside"}, at=@At(value="INVOKE", target="Lnet/minecraft/server/MinecraftServer;getLevel(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/server/level/ServerLevel;"))
    public ServerLevel arclight$enterPortal(MinecraftServer minecraftServer, ResourceKey<Level> dimension, BlockState state, Level worldIn, BlockPos pos, Entity entityIn) {
        ServerLevel world = minecraftServer.m_129880_(dimension);
        EntityPortalEnterEvent event = new EntityPortalEnterEvent(((EntityBridge)entityIn).bridge$getBukkitEntity(), new Location(((WorldBridge)worldIn).bridge$getWorld(), pos.m_123341_(), pos.m_123342_(), pos.m_123343_()));
        Bukkit.getPluginManager().callEvent(event);
        if (entityIn instanceof ServerPlayer && world != null) {
            ((ServerPlayerEntityBridge)entityIn).bridge$changeDimension(world, PlayerTeleportEvent.TeleportCause.END_PORTAL);
            return null;
        }
        return world;
    }
}

