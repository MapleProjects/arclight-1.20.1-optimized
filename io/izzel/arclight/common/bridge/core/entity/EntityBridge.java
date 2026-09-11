/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.PositionImpl
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 */
package io.izzel.arclight.common.bridge.core.entity;

import io.izzel.arclight.common.bridge.core.command.ICommandSourceBridge;
import java.util.List;
import net.minecraft.core.PositionImpl;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.projectiles.ProjectileSource;

public interface EntityBridge
extends ICommandSourceBridge {
    public Entity bridge$teleportTo(ServerLevel var1, PositionImpl var2);

    public void bridge$setOnFire(int var1, boolean var2);

    public CraftEntity bridge$getBukkitEntity();

    public void bridge$setBukkitEntity(CraftEntity var1);

    public boolean bridge$isPersist();

    public void bridge$setPersist(boolean var1);

    public boolean bridge$isValid();

    public void bridge$setValid(boolean var1);

    public ProjectileSource bridge$getProjectileSource();

    public void bridge$setProjectileSource(ProjectileSource var1);

    public float bridge$getBukkitYaw();

    public boolean bridge$isChunkLoaded();

    public boolean bridge$isLastDamageCancelled();

    public void bridge$setLastDamageCancelled(boolean var1);

    public void bridge$postTick();

    public boolean bridge$removePassenger(Entity var1);

    public boolean bridge$addPassenger(Entity var1);

    public List<Entity> bridge$getPassengers();

    public void bridge$setRideCooldown(int var1);

    public int bridge$getRideCooldown();

    public boolean bridge$canCollideWith(Entity var1);
}

