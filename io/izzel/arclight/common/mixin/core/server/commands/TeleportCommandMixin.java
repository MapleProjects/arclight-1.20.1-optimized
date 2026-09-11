/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.brigadier.exceptions.SimpleCommandExceptionType
 *  javax.annotation.Nullable
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.commands.TeleportCommand
 *  net.minecraft.server.commands.TeleportCommand$LookAt
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.PathfinderMob
 *  net.minecraft.world.entity.RelativeMovement
 *  net.minecraft.world.level.Level
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.EntityTeleportEvent$TeleportCommand
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.server.commands;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.commands.TeleportCommand;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.RelativeMovement;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={TeleportCommand.class})
public class TeleportCommandMixin {
    @Shadow
    @Final
    private static SimpleCommandExceptionType f_139006_;

    @Overwrite
    private static void m_139014_(CommandSourceStack source, Entity entity, ServerLevel level, double x, double y, double z, Set<RelativeMovement> set, float yaw, float pitch, @Nullable TeleportCommand.LookAt p_139024_) throws CommandSyntaxException {
        boolean result;
        EntityTeleportEvent.TeleportCommand event = ForgeEventFactory.onEntityTeleportCommand((Entity)entity, (double)x, (double)y, (double)z);
        if (event.isCanceled()) {
            return;
        }
        x = event.getTargetX();
        BlockPos blockpos = BlockPos.m_274561_((double)x, (double)(y = event.getTargetY()), (double)(z = event.getTargetZ()));
        if (!Level.m_46741_((BlockPos)blockpos)) {
            throw f_139006_.create();
        }
        float f = Mth.m_14177_((float)yaw);
        float f1 = Mth.m_14177_((float)pitch);
        if (entity instanceof ServerPlayer) {
            ServerPlayer player = (ServerPlayer)entity;
            ((ServerPlayerEntityBridge)player).bridge$pushChangeDimensionCause(PlayerTeleportEvent.TeleportCause.COMMAND);
            result = player.m_264318_(level, x, y, z, set, f, f1);
        } else {
            Location to = new Location(((ServerWorldBridge)level).bridge$getWorld(), x, y, z, yaw, pitch);
            EntityTeleportEvent e = new EntityTeleportEvent(((EntityBridge)entity).bridge$getBukkitEntity(), ((EntityBridge)entity).bridge$getBukkitEntity().getLocation(), to);
            Bukkit.getPluginManager().callEvent(e);
            if (e.isCancelled()) {
                return;
            }
            x = to.getX();
            y = to.getY();
            z = to.getZ();
            f = to.getYaw();
            f1 = to.getPitch();
            level = ((CraftWorld)to.getWorld()).getHandle();
            result = entity.m_264318_(level, x, y, z, set, f, f1);
        }
        if (result) {
            LivingEntity livingentity;
            if (p_139024_ != null) {
                p_139024_.m_139060_(source, entity);
            }
            if (!(entity instanceof LivingEntity) || !(livingentity = (LivingEntity)entity).m_21255_()) {
                entity.m_20256_(entity.m_20184_().m_82542_(1.0, 0.0, 1.0));
                entity.m_6853_(true);
            }
            if (entity instanceof PathfinderMob) {
                PathfinderMob pathfindermob = (PathfinderMob)entity;
                pathfindermob.m_21573_().m_26573_();
            }
        }
    }
}

