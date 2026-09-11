/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.level.border.WorldBorder
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import net.minecraft.core.BlockPos;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;

public class CraftWorldBorder
implements WorldBorder {
    private final World world;
    private final net.minecraft.world.level.border.WorldBorder handle;

    public CraftWorldBorder(CraftWorld world) {
        this.world = world;
        this.handle = world.getHandle().m_6857_();
    }

    public CraftWorldBorder(net.minecraft.world.level.border.WorldBorder handle) {
        this.world = null;
        this.handle = handle;
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public void reset() {
        this.getHandle().m_61931_(net.minecraft.world.level.border.WorldBorder.f_61907_);
    }

    @Override
    public double getSize() {
        return this.handle.m_61959_();
    }

    @Override
    public void setSize(double newSize) {
        this.setSize(newSize, 0L);
    }

    @Override
    public void setSize(double newSize, long time) {
        this.setSize(Math.min(this.getMaxSize(), Math.max(1.0, newSize)), TimeUnit.SECONDS, Math.min(9223372036854775L, Math.max(0L, time)));
    }

    @Override
    public void setSize(double newSize, TimeUnit unit, long time) {
        Preconditions.checkArgument((unit != null ? 1 : 0) != 0, (Object)"TimeUnit cannot be null.");
        Preconditions.checkArgument((time >= 0L ? 1 : 0) != 0, (Object)"time cannot be lower than 0");
        Preconditions.checkArgument((newSize >= 1.0 && newSize <= this.getMaxSize() ? 1 : 0) != 0, (String)"newSize must be between 1.0D and %s", (Object)this.getMaxSize());
        if (time > 0L) {
            this.handle.m_61919_(this.handle.m_61959_(), newSize, unit.toMillis(time));
        } else {
            this.handle.m_61917_(newSize);
        }
    }

    @Override
    public Location getCenter() {
        double x = this.handle.m_6347_();
        double z = this.handle.m_6345_();
        return new Location(this.world, x, 0.0, z);
    }

    @Override
    public void setCenter(double x, double z) {
        Preconditions.checkArgument((Math.abs(x) <= this.getMaxCenterCoordinate() ? 1 : 0) != 0, (String)"x coordinate cannot be outside +- %s", (Object)this.getMaxCenterCoordinate());
        Preconditions.checkArgument((Math.abs(z) <= this.getMaxCenterCoordinate() ? 1 : 0) != 0, (String)"z coordinate cannot be outside +- %s", (Object)this.getMaxCenterCoordinate());
        this.handle.m_61949_(x, z);
    }

    @Override
    public void setCenter(Location location) {
        this.setCenter(location.getX(), location.getZ());
    }

    @Override
    public double getDamageBuffer() {
        return this.handle.m_61964_();
    }

    @Override
    public void setDamageBuffer(double blocks) {
        this.handle.m_61939_(blocks);
    }

    @Override
    public double getDamageAmount() {
        return this.handle.m_61965_();
    }

    @Override
    public void setDamageAmount(double damage) {
        this.handle.m_61947_(damage);
    }

    @Override
    public int getWarningTime() {
        return this.handle.m_61967_();
    }

    @Override
    public void setWarningTime(int time) {
        this.handle.m_61944_(time);
    }

    @Override
    public int getWarningDistance() {
        return this.handle.m_61968_();
    }

    @Override
    public void setWarningDistance(int distance) {
        this.handle.m_61952_(distance);
    }

    @Override
    public boolean isInside(Location location) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"location cannot be null");
        return (this.world == null || location.getWorld().equals(this.world)) && this.handle.m_61937_(BlockPos.m_274561_((double)location.getX(), (double)location.getY(), (double)location.getZ()));
    }

    @Override
    public double getMaxSize() {
        return 5.9999968E7;
    }

    @Override
    public double getMaxCenterCoordinate() {
        return 2.9999984E7;
    }

    public net.minecraft.world.level.border.WorldBorder getHandle() {
        return this.handle;
    }

    public boolean isVirtual() {
        return this.world == null;
    }
}

