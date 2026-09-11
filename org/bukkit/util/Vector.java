/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.primitives.Doubles
 *  org.jetbrains.annotations.NotNull
 *  org.joml.Vector3d
 *  org.joml.Vector3f
 *  org.joml.Vector3i
 */
package org.bukkit.util;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.BlockVector;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3d;
import org.joml.Vector3f;
import org.joml.Vector3i;

@SerializableAs(value="Vector")
public class Vector
implements Cloneable,
ConfigurationSerializable {
    private static final long serialVersionUID = -2657651106777219169L;
    private static Random random = new Random();
    private static final double epsilon = 1.0E-6;
    protected double x;
    protected double y;
    protected double z;

    public Vector() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public Vector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @NotNull
    public Vector add(@NotNull Vector vec) {
        this.x += vec.x;
        this.y += vec.y;
        this.z += vec.z;
        return this;
    }

    @NotNull
    public Vector subtract(@NotNull Vector vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        this.z -= vec.z;
        return this;
    }

    @NotNull
    public Vector multiply(@NotNull Vector vec) {
        this.x *= vec.x;
        this.y *= vec.y;
        this.z *= vec.z;
        return this;
    }

    @NotNull
    public Vector divide(@NotNull Vector vec) {
        this.x /= vec.x;
        this.y /= vec.y;
        this.z /= vec.z;
        return this;
    }

    @NotNull
    public Vector copy(@NotNull Vector vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        return this;
    }

    public double length() {
        return Math.sqrt(NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z));
    }

    public double lengthSquared() {
        return NumberConversions.square(this.x) + NumberConversions.square(this.y) + NumberConversions.square(this.z);
    }

    public double distance(@NotNull Vector o) {
        return Math.sqrt(NumberConversions.square(this.x - o.x) + NumberConversions.square(this.y - o.y) + NumberConversions.square(this.z - o.z));
    }

    public double distanceSquared(@NotNull Vector o) {
        return NumberConversions.square(this.x - o.x) + NumberConversions.square(this.y - o.y) + NumberConversions.square(this.z - o.z);
    }

    public float angle(@NotNull Vector other) {
        double dot = Doubles.constrainToRange((double)(this.dot(other) / (this.length() * other.length())), (double)-1.0, (double)1.0);
        return (float)Math.acos(dot);
    }

    @NotNull
    public Vector midpoint(@NotNull Vector other) {
        this.x = (this.x + other.x) / 2.0;
        this.y = (this.y + other.y) / 2.0;
        this.z = (this.z + other.z) / 2.0;
        return this;
    }

    @NotNull
    public Vector getMidpoint(@NotNull Vector other) {
        double x = (this.x + other.x) / 2.0;
        double y = (this.y + other.y) / 2.0;
        double z = (this.z + other.z) / 2.0;
        return new Vector(x, y, z);
    }

    @NotNull
    public Vector multiply(int m) {
        this.x *= (double)m;
        this.y *= (double)m;
        this.z *= (double)m;
        return this;
    }

    @NotNull
    public Vector multiply(double m) {
        this.x *= m;
        this.y *= m;
        this.z *= m;
        return this;
    }

    @NotNull
    public Vector multiply(float m) {
        this.x *= (double)m;
        this.y *= (double)m;
        this.z *= (double)m;
        return this;
    }

    public double dot(@NotNull Vector other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    @NotNull
    public Vector crossProduct(@NotNull Vector o) {
        double newX = this.y * o.z - o.y * this.z;
        double newY = this.z * o.x - o.z * this.x;
        double newZ = this.x * o.y - o.x * this.y;
        this.x = newX;
        this.y = newY;
        this.z = newZ;
        return this;
    }

    @NotNull
    public Vector getCrossProduct(@NotNull Vector o) {
        double x = this.y * o.z - o.y * this.z;
        double y = this.z * o.x - o.z * this.x;
        double z = this.x * o.y - o.x * this.y;
        return new Vector(x, y, z);
    }

    @NotNull
    public Vector normalize() {
        double length = this.length();
        this.x /= length;
        this.y /= length;
        this.z /= length;
        return this;
    }

    @NotNull
    public Vector zero() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        return this;
    }

    public boolean isZero() {
        return this.x == 0.0 && this.y == 0.0 && this.z == 0.0;
    }

    @NotNull
    Vector normalizeZeros() {
        if (this.x == -0.0) {
            this.x = 0.0;
        }
        if (this.y == -0.0) {
            this.y = 0.0;
        }
        if (this.z == -0.0) {
            this.z = 0.0;
        }
        return this;
    }

    public boolean isInAABB(@NotNull Vector min, @NotNull Vector max) {
        return this.x >= min.x && this.x <= max.x && this.y >= min.y && this.y <= max.y && this.z >= min.z && this.z <= max.z;
    }

    public boolean isInSphere(@NotNull Vector origin, double radius) {
        return NumberConversions.square(origin.x - this.x) + NumberConversions.square(origin.y - this.y) + NumberConversions.square(origin.z - this.z) <= NumberConversions.square(radius);
    }

    public boolean isNormalized() {
        return Math.abs(this.lengthSquared() - 1.0) < Vector.getEpsilon();
    }

    @NotNull
    public Vector rotateAroundX(double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);
        double y = angleCos * this.getY() - angleSin * this.getZ();
        double z = angleSin * this.getY() + angleCos * this.getZ();
        return this.setY(y).setZ(z);
    }

    @NotNull
    public Vector rotateAroundY(double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);
        double x = angleCos * this.getX() + angleSin * this.getZ();
        double z = -angleSin * this.getX() + angleCos * this.getZ();
        return this.setX(x).setZ(z);
    }

    @NotNull
    public Vector rotateAroundZ(double angle) {
        double angleCos = Math.cos(angle);
        double angleSin = Math.sin(angle);
        double x = angleCos * this.getX() - angleSin * this.getY();
        double y = angleSin * this.getX() + angleCos * this.getY();
        return this.setX(x).setY(y);
    }

    @NotNull
    public Vector rotateAroundAxis(@NotNull Vector axis, double angle) throws IllegalArgumentException {
        Preconditions.checkArgument((axis != null ? 1 : 0) != 0, (Object)"The provided axis vector was null");
        return this.rotateAroundNonUnitAxis(axis.isNormalized() ? axis : axis.clone().normalize(), angle);
    }

    @NotNull
    public Vector rotateAroundNonUnitAxis(@NotNull Vector axis, double angle) throws IllegalArgumentException {
        Preconditions.checkArgument((axis != null ? 1 : 0) != 0, (Object)"The provided axis vector was null");
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        double x2 = axis.getX();
        double y2 = axis.getY();
        double z2 = axis.getZ();
        double cosTheta = Math.cos(angle);
        double sinTheta = Math.sin(angle);
        double dotProduct = this.dot(axis);
        double xPrime = x2 * dotProduct * (1.0 - cosTheta) + x * cosTheta + (-z2 * y + y2 * z) * sinTheta;
        double yPrime = y2 * dotProduct * (1.0 - cosTheta) + y * cosTheta + (z2 * x - x2 * z) * sinTheta;
        double zPrime = z2 * dotProduct * (1.0 - cosTheta) + z * cosTheta + (-y2 * x + x2 * y) * sinTheta;
        return this.setX(xPrime).setY(yPrime).setZ(zPrime);
    }

    public double getX() {
        return this.x;
    }

    public int getBlockX() {
        return NumberConversions.floor(this.x);
    }

    public double getY() {
        return this.y;
    }

    public int getBlockY() {
        return NumberConversions.floor(this.y);
    }

    public double getZ() {
        return this.z;
    }

    public int getBlockZ() {
        return NumberConversions.floor(this.z);
    }

    @NotNull
    public Vector setX(int x) {
        this.x = x;
        return this;
    }

    @NotNull
    public Vector setX(double x) {
        this.x = x;
        return this;
    }

    @NotNull
    public Vector setX(float x) {
        this.x = x;
        return this;
    }

    @NotNull
    public Vector setY(int y) {
        this.y = y;
        return this;
    }

    @NotNull
    public Vector setY(double y) {
        this.y = y;
        return this;
    }

    @NotNull
    public Vector setY(float y) {
        this.y = y;
        return this;
    }

    @NotNull
    public Vector setZ(int z) {
        this.z = z;
        return this;
    }

    @NotNull
    public Vector setZ(double z) {
        this.z = z;
        return this;
    }

    @NotNull
    public Vector setZ(float z) {
        this.z = z;
        return this;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }
        Vector other = (Vector)obj;
        return Math.abs(this.x - other.x) < 1.0E-6 && Math.abs(this.y - other.y) < 1.0E-6 && Math.abs(this.z - other.z) < 1.0E-6 && this.getClass().equals(obj.getClass());
    }

    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int)(Double.doubleToLongBits(this.x) ^ Double.doubleToLongBits(this.x) >>> 32);
        hash = 79 * hash + (int)(Double.doubleToLongBits(this.y) ^ Double.doubleToLongBits(this.y) >>> 32);
        hash = 79 * hash + (int)(Double.doubleToLongBits(this.z) ^ Double.doubleToLongBits(this.z) >>> 32);
        return hash;
    }

    @NotNull
    public Vector clone() {
        try {
            return (Vector)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    public String toString() {
        return String.valueOf(this.x) + "," + this.y + "," + this.z;
    }

    @NotNull
    public Location toLocation(@NotNull World world) {
        return new Location(world, this.x, this.y, this.z);
    }

    @NotNull
    public Location toLocation(@NotNull World world, float yaw, float pitch) {
        return new Location(world, this.x, this.y, this.z, yaw, pitch);
    }

    @NotNull
    public BlockVector toBlockVector() {
        return new BlockVector(this.x, this.y, this.z);
    }

    @NotNull
    public Vector3f toVector3f() {
        return new Vector3f((float)this.x, (float)this.y, (float)this.z);
    }

    @NotNull
    public Vector3d toVector3d() {
        return new Vector3d(this.x, this.y, this.z);
    }

    @NotNull
    public Vector3i toVector3i(int roundingMode) {
        return new Vector3i(this.x, this.y, this.z, roundingMode);
    }

    @NotNull
    public Vector3i toVector3i() {
        return this.toVector3i(2);
    }

    public void checkFinite() throws IllegalArgumentException {
        NumberConversions.checkFinite(this.x, "x not finite");
        NumberConversions.checkFinite(this.y, "y not finite");
        NumberConversions.checkFinite(this.z, "z not finite");
    }

    public static double getEpsilon() {
        return 1.0E-6;
    }

    @NotNull
    public static Vector getMinimum(@NotNull Vector v1, @NotNull Vector v2) {
        return new Vector(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y), Math.min(v1.z, v2.z));
    }

    @NotNull
    public static Vector getMaximum(@NotNull Vector v1, @NotNull Vector v2) {
        return new Vector(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y), Math.max(v1.z, v2.z));
    }

    @NotNull
    public static Vector getRandom() {
        return new Vector(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    @NotNull
    public static Vector fromJOML(@NotNull Vector3f vector) {
        return new Vector(vector.x(), vector.y(), vector.z());
    }

    @NotNull
    public static Vector fromJOML(@NotNull Vector3d vector) {
        return new Vector(vector.x(), vector.y(), vector.z());
    }

    @NotNull
    public static Vector fromJOML(@NotNull Vector3i vector) {
        return new Vector(vector.x(), vector.y(), vector.z());
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("x", this.getX());
        result.put("y", this.getY());
        result.put("z", this.getZ());
        return result;
    }

    @NotNull
    public static Vector deserialize(@NotNull Map<String, Object> args) {
        double x = 0.0;
        double y = 0.0;
        double z = 0.0;
        if (args.containsKey("x")) {
            x = (Double)args.get("x");
        }
        if (args.containsKey("y")) {
            y = (Double)args.get("y");
        }
        if (args.containsKey("z")) {
            z = (Double)args.get("z");
        }
        return new Vector(x, y, z);
    }
}

