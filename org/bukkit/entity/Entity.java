/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.ApiStatus$Experimental
 *  org.jetbrains.annotations.Contract
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Nameable;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pose;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.metadata.Metadatable;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Entity
extends Metadatable,
CommandSender,
Nameable,
PersistentDataHolder {
    @NotNull
    public Location getLocation();

    @Contract(value="null -> null; !null -> !null")
    @Nullable
    public Location getLocation(@Nullable Location var1);

    public void setVelocity(@NotNull Vector var1);

    @NotNull
    public Vector getVelocity();

    public double getHeight();

    public double getWidth();

    @NotNull
    public BoundingBox getBoundingBox();

    public boolean isOnGround();

    public boolean isInWater();

    @NotNull
    public World getWorld();

    public void setRotation(float var1, float var2);

    public boolean teleport(@NotNull Location var1);

    public boolean teleport(@NotNull Location var1, @NotNull PlayerTeleportEvent.TeleportCause var2);

    public boolean teleport(@NotNull Entity var1);

    public boolean teleport(@NotNull Entity var1, @NotNull PlayerTeleportEvent.TeleportCause var2);

    @NotNull
    public List<Entity> getNearbyEntities(double var1, double var3, double var5);

    public int getEntityId();

    public int getFireTicks();

    public int getMaxFireTicks();

    public void setFireTicks(int var1);

    public void setVisualFire(boolean var1);

    public boolean isVisualFire();

    public int getFreezeTicks();

    public int getMaxFreezeTicks();

    public void setFreezeTicks(int var1);

    public boolean isFrozen();

    public void remove();

    public boolean isDead();

    public boolean isValid();

    @Override
    @NotNull
    public Server getServer();

    public boolean isPersistent();

    public void setPersistent(boolean var1);

    @Deprecated
    @Nullable
    public Entity getPassenger();

    @Deprecated
    public boolean setPassenger(@NotNull Entity var1);

    @NotNull
    public List<Entity> getPassengers();

    public boolean addPassenger(@NotNull Entity var1);

    public boolean removePassenger(@NotNull Entity var1);

    public boolean isEmpty();

    public boolean eject();

    public float getFallDistance();

    public void setFallDistance(float var1);

    public void setLastDamageCause(@Nullable EntityDamageEvent var1);

    @Nullable
    public EntityDamageEvent getLastDamageCause();

    @NotNull
    public UUID getUniqueId();

    public int getTicksLived();

    public void setTicksLived(int var1);

    public void playEffect(@NotNull EntityEffect var1);

    @NotNull
    public EntityType getType();

    @NotNull
    public Sound getSwimSound();

    @NotNull
    public Sound getSwimSplashSound();

    @NotNull
    public Sound getSwimHighSpeedSplashSound();

    public boolean isInsideVehicle();

    public boolean leaveVehicle();

    @Nullable
    public Entity getVehicle();

    public void setCustomNameVisible(boolean var1);

    public boolean isCustomNameVisible();

    @ApiStatus.Experimental
    public void setVisibleByDefault(boolean var1);

    @ApiStatus.Experimental
    public boolean isVisibleByDefault();

    public void setGlowing(boolean var1);

    public boolean isGlowing();

    public void setInvulnerable(boolean var1);

    public boolean isInvulnerable();

    public boolean isSilent();

    public void setSilent(boolean var1);

    public boolean hasGravity();

    public void setGravity(boolean var1);

    public int getPortalCooldown();

    public void setPortalCooldown(int var1);

    @NotNull
    public Set<String> getScoreboardTags();

    public boolean addScoreboardTag(@NotNull String var1);

    public boolean removeScoreboardTag(@NotNull String var1);

    @NotNull
    public PistonMoveReaction getPistonMoveReaction();

    @NotNull
    public BlockFace getFacing();

    @NotNull
    public Pose getPose();

    @NotNull
    public SpawnCategory getSpawnCategory();

    @Override
    @NotNull
    public Spigot spigot();

    public static class Spigot
    extends CommandSender.Spigot {
    }
}

