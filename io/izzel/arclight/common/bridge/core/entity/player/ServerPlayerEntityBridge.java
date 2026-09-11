/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 */
package io.izzel.arclight.common.bridge.core.entity.player;

import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.event.player.PlayerSpawnChangeEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public interface ServerPlayerEntityBridge
extends PlayerEntityBridge {
    @Override
    public CraftPlayer bridge$getBukkitEntity();

    public void bridge$pushChangeDimensionCause(PlayerTeleportEvent.TeleportCause var1);

    public void bridge$pushChangeSpawnCause(PlayerSpawnChangeEvent.Cause var1);

    public Optional<PlayerTeleportEvent.TeleportCause> bridge$getTeleportCause();

    public BlockPos bridge$getSpawnPoint(ServerLevel var1);

    public boolean bridge$isMovementBlocked();

    public void bridge$setCompassTarget(Location var1);

    public boolean bridge$isJoining();

    public void bridge$reset();

    public Entity bridge$changeDimension(ServerLevel var1, PlayerTeleportEvent.TeleportCause var2);

    public boolean bridge$initialized();

    public boolean bridge$isTrackerDirty();

    public void bridge$setTrackerDirty(boolean var1);
}

