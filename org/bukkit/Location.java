/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Location
implements Cloneable,
ConfigurationSerializable {
    private Reference<World> world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    public Location(@Nullable World world, double x, double y, double z) {
        this(world, x, y, z, 0.0f, 0.0f);
    }

    public Location(@Nullable World world, double x, double y, double z, float yaw, float pitch) {
        if (world != null) {
            this.world = new WeakReference<World>(world);
        }
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public void setWorld(@Nullable World world) {
        this.world = world == null ? null : new WeakReference<World>(world);
    }

    public boolean isWorldLoaded() {
        if (this.world == null) {
            return false;
        }
        World world = this.world.get();
        return world != null && Bukkit.getWorld(world.getUID()) != null;
    }

    @Nullable
    public World getWorld() {
        if (this.world == null) {
            return null;
        }
        World world = this.world.get();
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"World unloaded");
        return world;
    }

    @NotNull
    public Chunk getChunk() {
        return this.getWorld().getChunkAt(this);
    }

    @NotNull
    public Block getBlock() {
        return this.getWorld().getBlockAt(this);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return this.x;
    }

    public int getBlockX() {
        return Location.locToBlock(this.x);
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return this.y;
    }

    public int getBlockY() {
        return Location.locToBlock(this.y);
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getZ() {
        return this.z;
    }

    public int getBlockZ() {
        return Location.locToBlock(this.z);
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getPitch() {
        return this.pitch;
    }

    @NotNull
    public Vector getDirection() {
        Vector vector = new Vector();
        double rotX = this.getYaw();
        double rotY = this.getPitch();
        vector.setY(-Math.sin(Math.toRadians(rotY)));
        double xz = Math.cos(Math.toRadians(rotY));
        vector.setX(-xz * Math.sin(Math.toRadians(rotX)));
        vector.setZ(xz * Math.cos(Math.toRadians(rotX)));
        return vector;
    }

    @NotNull
    public Location setDirection(@NotNull Vector vector) {
        double _2PI = Math.PI * 2;
        double x = vector.getX();
        double z = vector.getZ();
        if (x == 0.0 && z == 0.0) {
            this.pitch = vector.getY() > 0.0 ? -90 : 90;
            return this;
        }
        double theta = Math.atan2(-x, z);
        this.yaw = (float)Math.toDegrees((theta + Math.PI * 2) % (Math.PI * 2));
        double x2 = NumberConversions.square(x);
        double z2 = NumberConversions.square(z);
        double xz = Math.sqrt(x2 + z2);
        this.pitch = (float)Math.toDegrees(Math.atan(-vector.getY() / xz));
        return this;
    }

    @NotNull
    public Location add(@NotNull Location vec) {
        if (vec == null || vec.getWorld() != this.getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }
        this.x += vec.x;
        this.y += vec.y;
        this.z += vec.z;
        return this;
    }

    @NotNull
    public Location add(@NotNull Vector vec) {
        this.x += vec.getX();
        this.y += vec.getY();
        this.z += vec.getZ();
        return this;
    }

    @NotNull
    public Location add(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
        return this;
    }

    @NotNull
    public Location subtract(@NotNull Location vec) {
        if (vec == null || vec.getWorld() != this.getWorld()) {
            throw new IllegalArgumentException("Cannot add Locations of differing worlds");
        }
        this.x -= vec.x;
        this.y -= vec.y;
        this.z -= vec.z;
        return this;
    }

    @NotNull
    public Location subtract(@NotNull Vector vec) {
        this.x -= vec.getX();
        this.y -= vec.getY();
        this.z -= vec.getZ();
        return this;
    }

    @NotNull
    public Location subtract(double x, double y, double z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
        return this;
    }

    public double length() {
        return Math.sqrt(NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z));
    }

    public double lengthSquared() {
        return NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z);
    }

    public double distance(@NotNull Location o) {
        return Math.sqrt(this.distanceSquared(o));
    }

    public double distanceSquared(@NotNull Location o) {
        if (o == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null location");
        }
        if (o.getWorld() == null || this.getWorld() == null) {
            throw new IllegalArgumentException("Cannot measure distance to a null world");
        }
        if (o.getWorld() != this.getWorld()) {
            throw new IllegalArgumentException("Cannot measure distance between " + this.getWorld().getName() + " and " + o.getWorld().getName());
        }
        return NumberConversions.square(this.x - o.x) + NumberConversions.square(this.y - o.y) + NumberConversions.square(this.z - o.z);
    }

    @NotNull
    public Location multiply(double m) {
        this.x *= m;
        this.y *= m;
        this.z *= m;
        return this;
    }

    @NotNull
    public Location zero() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        return this;
    }

    public boolean equals(Object obj) {
        World otherWorld;
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Location other = (Location)obj;
        World world = this.world == null ? null : this.world.get();
        World world2 = otherWorld = other.world == null ? null : other.world.get();
        if (!(world == otherWorld || world != null && world.equals(otherWorld))) {
            return false;
        }
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.z) != Double.doubleToLongBits(other.z)) {
            return false;
        }
        if (Float.floatToIntBits(this.pitch) != Float.floatToIntBits(other.pitch)) {
            return false;
        }
        return Float.floatToIntBits(this.yaw) == Float.floatToIntBits(other.yaw);
    }

    public int hashCode() {
        int hash = 3;
        World world = this.world == null ? null : this.world.get();
        hash = 19 * hash + (world != null ? world.hashCode() : 0);
        hash = 19 * hash + (int)(Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32);
        hash = 19 * hash + (int)(Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32);
        hash = 19 * hash + (int)(Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32);
        hash = 19 * hash + Float.floatToIntBits(this.pitch);
        hash = 19 * hash + Float.floatToIntBits(this.yaw);
        return hash;
    }

    public String toString() {
        World world = this.world == null ? null : this.world.get();
        return "Location{world=" + world + ",x=" + this.x + ",y=" + this.y + ",z=" + this.z + ",pitch=" + this.pitch + ",yaw=" + this.yaw + '}';
    }

    @NotNull
    public Vector toVector() {
        return new Vector(this.x, this.y, this.z);
    }

    @NotNull
    public Location clone() {
        try {
            return (Location)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    public void checkFinite() throws IllegalArgumentException {
        NumberConversions.checkFinite(this.x, "x not finite");
        NumberConversions.checkFinite(this.y, "y not finite");
        NumberConversions.checkFinite(this.z, "z not finite");
        NumberConversions.checkFinite(this.pitch, "pitch not finite");
        NumberConversions.checkFinite(this.yaw, "yaw not finite");
    }

    public static int locToBlock(double loc) {
        return NumberConversions.floor(loc);
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        if (this.world != null) {
            data.put("world", this.getWorld().getName());
        }
        data.put("x", this.x);
        data.put("y", this.y);
        data.put("z", this.z);
        data.put("yaw", Float.valueOf(this.yaw));
        data.put("pitch", Float.valueOf(this.pitch));
        return data;
    }

    @NotNull
    public static Location deserialize(@NotNull Map<String, Object> args) {
        World world = null;
        if (args.containsKey("world") && (world = Bukkit.getWorld((String)args.get("world"))) == null) {
            throw new IllegalArgumentException("unknown world");
        }
        return new Location(world, NumberConversions.toDouble(args.get("x")), NumberConversions.toDouble(args.get("y")), NumberConversions.toDouble(args.get("z")), NumberConversions.toFloat(args.get("yaw")), NumberConversions.toFloat(args.get("pitch")));
    }

    public static float normalizeYaw(float yaw) {
        if ((yaw %= 360.0f) >= 180.0f) {
            yaw -= 360.0f;
        } else if (yaw < -180.0f) {
            yaw += 360.0f;
        }
        return yaw;
    }

    public static float normalizePitch(float pitch) {
        if (pitch > 90.0f) {
            pitch = 90.0f;
        } else if (pitch < -90.0f) {
            pitch = -90.0f;
        }
        return pitch;
    }
}

