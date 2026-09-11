/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.util;

import com.google.common.base.Preconditions;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SerializableAs(value="BoundingBox")
public class BoundingBox
implements Cloneable,
ConfigurationSerializable {
    private double minX;
    private double minY;
    private double minZ;
    private double maxX;
    private double maxY;
    private double maxZ;

    @NotNull
    public static BoundingBox of(@NotNull Vector corner1, @NotNull Vector corner2) {
        Preconditions.checkArgument((corner1 != null ? 1 : 0) != 0, (Object)"Corner1 is null!");
        Preconditions.checkArgument((corner2 != null ? 1 : 0) != 0, (Object)"Corner2 is null!");
        return new BoundingBox(corner1.getX(), corner1.getY(), corner1.getZ(), corner2.getX(), corner2.getY(), corner2.getZ());
    }

    @NotNull
    public static BoundingBox of(@NotNull Location corner1, @NotNull Location corner2) {
        Preconditions.checkArgument((corner1 != null ? 1 : 0) != 0, (Object)"Corner1 is null!");
        Preconditions.checkArgument((corner2 != null ? 1 : 0) != 0, (Object)"Corner2 is null!");
        Preconditions.checkArgument((boolean)Objects.equals(corner1.getWorld(), corner2.getWorld()), (Object)"Locations from different worlds!");
        return new BoundingBox(corner1.getX(), corner1.getY(), corner1.getZ(), corner2.getX(), corner2.getY(), corner2.getZ());
    }

    @NotNull
    public static BoundingBox of(@NotNull Block corner1, @NotNull Block corner2) {
        Preconditions.checkArgument((corner1 != null ? 1 : 0) != 0, (Object)"Corner1 is null!");
        Preconditions.checkArgument((corner2 != null ? 1 : 0) != 0, (Object)"Corner2 is null!");
        Preconditions.checkArgument((boolean)Objects.equals(corner1.getWorld(), corner2.getWorld()), (Object)"Blocks from different worlds!");
        int x1 = corner1.getX();
        int y1 = corner1.getY();
        int z1 = corner1.getZ();
        int x2 = corner2.getX();
        int y2 = corner2.getY();
        int z2 = corner2.getZ();
        int minX = Math.min(x1, x2);
        int minY = Math.min(y1, y2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2) + 1;
        int maxY = Math.max(y1, y2) + 1;
        int maxZ = Math.max(z1, z2) + 1;
        return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }

    @NotNull
    public static BoundingBox of(@NotNull Block block) {
        Preconditions.checkArgument((block != null ? 1 : 0) != 0, (Object)"Block is null!");
        return new BoundingBox(block.getX(), block.getY(), block.getZ(), block.getX() + 1, block.getY() + 1, block.getZ() + 1);
    }

    @NotNull
    public static BoundingBox of(@NotNull Vector center, double x, double y, double z) {
        Preconditions.checkArgument((center != null ? 1 : 0) != 0, (Object)"Center is null!");
        return new BoundingBox(center.getX() - x, center.getY() - y, center.getZ() - z, center.getX() + x, center.getY() + y, center.getZ() + z);
    }

    @NotNull
    public static BoundingBox of(@NotNull Location center, double x, double y, double z) {
        Preconditions.checkArgument((center != null ? 1 : 0) != 0, (Object)"Center is null!");
        return new BoundingBox(center.getX() - x, center.getY() - y, center.getZ() - z, center.getX() + x, center.getY() + y, center.getZ() + z);
    }

    public BoundingBox() {
        this.resize(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
    }

    public BoundingBox(double x1, double y1, double z1, double x2, double y2, double z2) {
        this.resize(x1, y1, z1, x2, y2, z2);
    }

    @NotNull
    public BoundingBox resize(double x1, double y1, double z1, double x2, double y2, double z2) {
        NumberConversions.checkFinite(x1, "x1 not finite");
        NumberConversions.checkFinite(y1, "y1 not finite");
        NumberConversions.checkFinite(z1, "z1 not finite");
        NumberConversions.checkFinite(x2, "x2 not finite");
        NumberConversions.checkFinite(y2, "y2 not finite");
        NumberConversions.checkFinite(z2, "z2 not finite");
        this.minX = Math.min(x1, x2);
        this.minY = Math.min(y1, y2);
        this.minZ = Math.min(z1, z2);
        this.maxX = Math.max(x1, x2);
        this.maxY = Math.max(y1, y2);
        this.maxZ = Math.max(z1, z2);
        return this;
    }

    public double getMinX() {
        return this.minX;
    }

    public double getMinY() {
        return this.minY;
    }

    public double getMinZ() {
        return this.minZ;
    }

    @NotNull
    public Vector getMin() {
        return new Vector(this.minX, this.minY, this.minZ);
    }

    public double getMaxX() {
        return this.maxX;
    }

    public double getMaxY() {
        return this.maxY;
    }

    public double getMaxZ() {
        return this.maxZ;
    }

    @NotNull
    public Vector getMax() {
        return new Vector(this.maxX, this.maxY, this.maxZ);
    }

    public double getWidthX() {
        return this.maxX - this.minX;
    }

    public double getWidthZ() {
        return this.maxZ - this.minZ;
    }

    public double getHeight() {
        return this.maxY - this.minY;
    }

    public double getVolume() {
        return this.getHeight() * this.getWidthX() * this.getWidthZ();
    }

    public double getCenterX() {
        return this.minX + this.getWidthX() * 0.5;
    }

    public double getCenterY() {
        return this.minY + this.getHeight() * 0.5;
    }

    public double getCenterZ() {
        return this.minZ + this.getWidthZ() * 0.5;
    }

    @NotNull
    public Vector getCenter() {
        return new Vector(this.getCenterX(), this.getCenterY(), this.getCenterZ());
    }

    @NotNull
    public BoundingBox copy(@NotNull BoundingBox other) {
        Preconditions.checkArgument((other != null ? 1 : 0) != 0, (Object)"Other bounding box is null!");
        return this.resize(other.getMinX(), other.getMinY(), other.getMinZ(), other.getMaxX(), other.getMaxY(), other.getMaxZ());
    }

    @NotNull
    public BoundingBox expand(double negativeX, double negativeY, double negativeZ, double positiveX, double positiveY, double positiveZ) {
        if (negativeX == 0.0 && negativeY == 0.0 && negativeZ == 0.0 && positiveX == 0.0 && positiveY == 0.0 && positiveZ == 0.0) {
            return this;
        }
        double newMinX = this.minX - negativeX;
        double newMinY = this.minY - negativeY;
        double newMinZ = this.minZ - negativeZ;
        double newMaxX = this.maxX + positiveX;
        double newMaxY = this.maxY + positiveY;
        double newMaxZ = this.maxZ + positiveZ;
        if (newMinX > newMaxX) {
            double centerX = this.getCenterX();
            if (newMaxX >= centerX) {
                newMinX = newMaxX;
            } else if (newMinX <= centerX) {
                newMaxX = newMinX;
            } else {
                newMinX = centerX;
                newMaxX = centerX;
            }
        }
        if (newMinY > newMaxY) {
            double centerY = this.getCenterY();
            if (newMaxY >= centerY) {
                newMinY = newMaxY;
            } else if (newMinY <= centerY) {
                newMaxY = newMinY;
            } else {
                newMinY = centerY;
                newMaxY = centerY;
            }
        }
        if (newMinZ > newMaxZ) {
            double centerZ = this.getCenterZ();
            if (newMaxZ >= centerZ) {
                newMinZ = newMaxZ;
            } else if (newMinZ <= centerZ) {
                newMaxZ = newMinZ;
            } else {
                newMinZ = centerZ;
                newMaxZ = centerZ;
            }
        }
        return this.resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }

    @NotNull
    public BoundingBox expand(double x, double y, double z) {
        return this.expand(x, y, z, x, y, z);
    }

    @NotNull
    public BoundingBox expand(@NotNull Vector expansion) {
        Preconditions.checkArgument((expansion != null ? 1 : 0) != 0, (Object)"Expansion is null!");
        double x = expansion.getX();
        double y = expansion.getY();
        double z = expansion.getZ();
        return this.expand(x, y, z, x, y, z);
    }

    @NotNull
    public BoundingBox expand(double expansion) {
        return this.expand(expansion, expansion, expansion, expansion, expansion, expansion);
    }

    @NotNull
    public BoundingBox expand(double dirX, double dirY, double dirZ, double expansion) {
        if (expansion == 0.0) {
            return this;
        }
        if (dirX == 0.0 && dirY == 0.0 && dirZ == 0.0) {
            return this;
        }
        double negativeX = dirX < 0.0 ? -dirX * expansion : 0.0;
        double negativeY = dirY < 0.0 ? -dirY * expansion : 0.0;
        double negativeZ = dirZ < 0.0 ? -dirZ * expansion : 0.0;
        double positiveX = dirX > 0.0 ? dirX * expansion : 0.0;
        double positiveY = dirY > 0.0 ? dirY * expansion : 0.0;
        double positiveZ = dirZ > 0.0 ? dirZ * expansion : 0.0;
        return this.expand(negativeX, negativeY, negativeZ, positiveX, positiveY, positiveZ);
    }

    @NotNull
    public BoundingBox expand(@NotNull Vector direction, double expansion) {
        Preconditions.checkArgument((direction != null ? 1 : 0) != 0, (Object)"Direction is null!");
        return this.expand(direction.getX(), direction.getY(), direction.getZ(), expansion);
    }

    @NotNull
    public BoundingBox expand(@NotNull BlockFace blockFace, double expansion) {
        Preconditions.checkArgument((blockFace != null ? 1 : 0) != 0, (Object)"Block face is null!");
        if (blockFace == BlockFace.SELF) {
            return this;
        }
        return this.expand(blockFace.getDirection(), expansion);
    }

    @NotNull
    public BoundingBox expandDirectional(double dirX, double dirY, double dirZ) {
        return this.expand(dirX, dirY, dirZ, 1.0);
    }

    @NotNull
    public BoundingBox expandDirectional(@NotNull Vector direction) {
        Preconditions.checkArgument((direction != null ? 1 : 0) != 0, (Object)"Expansion is null!");
        return this.expand(direction.getX(), direction.getY(), direction.getZ(), 1.0);
    }

    @NotNull
    public BoundingBox union(double posX, double posY, double posZ) {
        double newMinX = Math.min(this.minX, posX);
        double newMinY = Math.min(this.minY, posY);
        double newMinZ = Math.min(this.minZ, posZ);
        double newMaxX = Math.max(this.maxX, posX);
        double newMaxY = Math.max(this.maxY, posY);
        double newMaxZ = Math.max(this.maxZ, posZ);
        if (newMinX == this.minX && newMinY == this.minY && newMinZ == this.minZ && newMaxX == this.maxX && newMaxY == this.maxY && newMaxZ == this.maxZ) {
            return this;
        }
        return this.resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }

    @NotNull
    public BoundingBox union(@NotNull Vector position) {
        Preconditions.checkArgument((position != null ? 1 : 0) != 0, (Object)"Position is null!");
        return this.union(position.getX(), position.getY(), position.getZ());
    }

    @NotNull
    public BoundingBox union(@NotNull Location position) {
        Preconditions.checkArgument((position != null ? 1 : 0) != 0, (Object)"Position is null!");
        return this.union(position.getX(), position.getY(), position.getZ());
    }

    @NotNull
    public BoundingBox union(@NotNull BoundingBox other) {
        Preconditions.checkArgument((other != null ? 1 : 0) != 0, (Object)"Other bounding box is null!");
        if (this.contains(other)) {
            return this;
        }
        double newMinX = Math.min(this.minX, other.minX);
        double newMinY = Math.min(this.minY, other.minY);
        double newMinZ = Math.min(this.minZ, other.minZ);
        double newMaxX = Math.max(this.maxX, other.maxX);
        double newMaxY = Math.max(this.maxY, other.maxY);
        double newMaxZ = Math.max(this.maxZ, other.maxZ);
        return this.resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }

    @NotNull
    public BoundingBox intersection(@NotNull BoundingBox other) {
        Preconditions.checkArgument((other != null ? 1 : 0) != 0, (Object)"Other bounding box is null!");
        Preconditions.checkArgument((boolean)this.overlaps(other), (Object)"The bounding boxes do not overlap!");
        double newMinX = Math.max(this.minX, other.minX);
        double newMinY = Math.max(this.minY, other.minY);
        double newMinZ = Math.max(this.minZ, other.minZ);
        double newMaxX = Math.min(this.maxX, other.maxX);
        double newMaxY = Math.min(this.maxY, other.maxY);
        double newMaxZ = Math.min(this.maxZ, other.maxZ);
        return this.resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }

    @NotNull
    public BoundingBox shift(double shiftX, double shiftY, double shiftZ) {
        if (shiftX == 0.0 && shiftY == 0.0 && shiftZ == 0.0) {
            return this;
        }
        return this.resize(this.minX + shiftX, this.minY + shiftY, this.minZ + shiftZ, this.maxX + shiftX, this.maxY + shiftY, this.maxZ + shiftZ);
    }

    @NotNull
    public BoundingBox shift(@NotNull Vector shift) {
        Preconditions.checkArgument((shift != null ? 1 : 0) != 0, (Object)"Shift is null!");
        return this.shift(shift.getX(), shift.getY(), shift.getZ());
    }

    @NotNull
    public BoundingBox shift(@NotNull Location shift) {
        Preconditions.checkArgument((shift != null ? 1 : 0) != 0, (Object)"Shift is null!");
        return this.shift(shift.getX(), shift.getY(), shift.getZ());
    }

    private boolean overlaps(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.minX < maxX && this.maxX > minX && this.minY < maxY && this.maxY > minY && this.minZ < maxZ && this.maxZ > minZ;
    }

    public boolean overlaps(@NotNull BoundingBox other) {
        Preconditions.checkArgument((other != null ? 1 : 0) != 0, (Object)"Other bounding box is null!");
        return this.overlaps(other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
    }

    public boolean overlaps(@NotNull Vector min, @NotNull Vector max) {
        Preconditions.checkArgument((min != null ? 1 : 0) != 0, (Object)"Min is null!");
        Preconditions.checkArgument((max != null ? 1 : 0) != 0, (Object)"Max is null!");
        double x1 = min.getX();
        double y1 = min.getY();
        double z1 = min.getZ();
        double x2 = max.getX();
        double y2 = max.getY();
        double z2 = max.getZ();
        return this.overlaps(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
    }

    public boolean contains(double x, double y, double z) {
        return x >= this.minX && x < this.maxX && y >= this.minY && y < this.maxY && z >= this.minZ && z < this.maxZ;
    }

    public boolean contains(@NotNull Vector position) {
        Preconditions.checkArgument((position != null ? 1 : 0) != 0, (Object)"Position is null!");
        return this.contains(position.getX(), position.getY(), position.getZ());
    }

    private boolean contains(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
        return this.minX <= minX && this.maxX >= maxX && this.minY <= minY && this.maxY >= maxY && this.minZ <= minZ && this.maxZ >= maxZ;
    }

    public boolean contains(@NotNull BoundingBox other) {
        Preconditions.checkArgument((other != null ? 1 : 0) != 0, (Object)"Other bounding box is null!");
        return this.contains(other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
    }

    public boolean contains(@NotNull Vector min, @NotNull Vector max) {
        Preconditions.checkArgument((min != null ? 1 : 0) != 0, (Object)"Min is null!");
        Preconditions.checkArgument((max != null ? 1 : 0) != 0, (Object)"Max is null!");
        double x1 = min.getX();
        double y1 = min.getY();
        double z1 = min.getZ();
        double x2 = max.getX();
        double y2 = max.getY();
        double z2 = max.getZ();
        return this.contains(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
    }

    @Nullable
    public RayTraceResult rayTrace(@NotNull Vector start, @NotNull Vector direction, double maxDistance) {
        BlockFace hitBlockFace;
        double t;
        BlockFace hitBlockFaceZMax;
        BlockFace hitBlockFaceZMin;
        double tzMax;
        double tzMin;
        BlockFace hitBlockFaceYMax;
        BlockFace hitBlockFaceYMin;
        double tyMax;
        double tyMin;
        BlockFace hitBlockFaceMax;
        BlockFace hitBlockFaceMin;
        double tMax;
        double tMin;
        Preconditions.checkArgument((start != null ? 1 : 0) != 0, (Object)"Start is null!");
        start.checkFinite();
        Preconditions.checkArgument((direction != null ? 1 : 0) != 0, (Object)"Direction is null!");
        direction.checkFinite();
        Preconditions.checkArgument((direction.lengthSquared() > 0.0 ? 1 : 0) != 0, (Object)"Direction's magnitude is 0!");
        if (maxDistance < 0.0) {
            return null;
        }
        double startX = start.getX();
        double startY = start.getY();
        double startZ = start.getZ();
        Vector dir = direction.clone().normalizeZeros().normalize();
        double dirX = dir.getX();
        double dirY = dir.getY();
        double dirZ = dir.getZ();
        double divX = 1.0 / dirX;
        double divY = 1.0 / dirY;
        double divZ = 1.0 / dirZ;
        if (dirX >= 0.0) {
            tMin = (this.minX - startX) * divX;
            tMax = (this.maxX - startX) * divX;
            hitBlockFaceMin = BlockFace.WEST;
            hitBlockFaceMax = BlockFace.EAST;
        } else {
            tMin = (this.maxX - startX) * divX;
            tMax = (this.minX - startX) * divX;
            hitBlockFaceMin = BlockFace.EAST;
            hitBlockFaceMax = BlockFace.WEST;
        }
        if (dirY >= 0.0) {
            tyMin = (this.minY - startY) * divY;
            tyMax = (this.maxY - startY) * divY;
            hitBlockFaceYMin = BlockFace.DOWN;
            hitBlockFaceYMax = BlockFace.UP;
        } else {
            tyMin = (this.maxY - startY) * divY;
            tyMax = (this.minY - startY) * divY;
            hitBlockFaceYMin = BlockFace.UP;
            hitBlockFaceYMax = BlockFace.DOWN;
        }
        if (tMin > tyMax || tMax < tyMin) {
            return null;
        }
        if (tyMin > tMin) {
            tMin = tyMin;
            hitBlockFaceMin = hitBlockFaceYMin;
        }
        if (tyMax < tMax) {
            tMax = tyMax;
            hitBlockFaceMax = hitBlockFaceYMax;
        }
        if (dirZ >= 0.0) {
            tzMin = (this.minZ - startZ) * divZ;
            tzMax = (this.maxZ - startZ) * divZ;
            hitBlockFaceZMin = BlockFace.NORTH;
            hitBlockFaceZMax = BlockFace.SOUTH;
        } else {
            tzMin = (this.maxZ - startZ) * divZ;
            tzMax = (this.minZ - startZ) * divZ;
            hitBlockFaceZMin = BlockFace.SOUTH;
            hitBlockFaceZMax = BlockFace.NORTH;
        }
        if (tMin > tzMax || tMax < tzMin) {
            return null;
        }
        if (tzMin > tMin) {
            tMin = tzMin;
            hitBlockFaceMin = hitBlockFaceZMin;
        }
        if (tzMax < tMax) {
            tMax = tzMax;
            hitBlockFaceMax = hitBlockFaceZMax;
        }
        if (tMax < 0.0) {
            return null;
        }
        if (tMin > maxDistance) {
            return null;
        }
        if (tMin < 0.0) {
            t = tMax;
            hitBlockFace = hitBlockFaceMax;
        } else {
            t = tMin;
            hitBlockFace = hitBlockFaceMin;
        }
        Vector hitPosition = dir.multiply(t).add(start);
        return new RayTraceResult(hitPosition, hitBlockFace);
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        long temp = Double.doubleToLongBits(this.maxX);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.maxY);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.maxZ);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.minX);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.minY);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(this.minZ);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BoundingBox)) {
            return false;
        }
        BoundingBox other = (BoundingBox)obj;
        if (Double.doubleToLongBits(this.maxX) != Double.doubleToLongBits(other.maxX)) {
            return false;
        }
        if (Double.doubleToLongBits(this.maxY) != Double.doubleToLongBits(other.maxY)) {
            return false;
        }
        if (Double.doubleToLongBits(this.maxZ) != Double.doubleToLongBits(other.maxZ)) {
            return false;
        }
        if (Double.doubleToLongBits(this.minX) != Double.doubleToLongBits(other.minX)) {
            return false;
        }
        if (Double.doubleToLongBits(this.minY) != Double.doubleToLongBits(other.minY)) {
            return false;
        }
        return Double.doubleToLongBits(this.minZ) == Double.doubleToLongBits(other.minZ);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BoundingBox [minX=");
        builder.append(this.minX);
        builder.append(", minY=");
        builder.append(this.minY);
        builder.append(", minZ=");
        builder.append(this.minZ);
        builder.append(", maxX=");
        builder.append(this.maxX);
        builder.append(", maxY=");
        builder.append(this.maxY);
        builder.append(", maxZ=");
        builder.append(this.maxZ);
        builder.append("]");
        return builder.toString();
    }

    @NotNull
    public BoundingBox clone() {
        try {
            return (BoundingBox)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("minX", this.minX);
        result.put("minY", this.minY);
        result.put("minZ", this.minZ);
        result.put("maxX", this.maxX);
        result.put("maxY", this.maxY);
        result.put("maxZ", this.maxZ);
        return result;
    }

    @NotNull
    public static BoundingBox deserialize(@NotNull Map<String, Object> args) {
        double minX = 0.0;
        double minY = 0.0;
        double minZ = 0.0;
        double maxX = 0.0;
        double maxY = 0.0;
        double maxZ = 0.0;
        if (args.containsKey("minX")) {
            minX = ((Number)args.get("minX")).doubleValue();
        }
        if (args.containsKey("minY")) {
            minY = ((Number)args.get("minY")).doubleValue();
        }
        if (args.containsKey("minZ")) {
            minZ = ((Number)args.get("minZ")).doubleValue();
        }
        if (args.containsKey("maxX")) {
            maxX = ((Number)args.get("maxX")).doubleValue();
        }
        if (args.containsKey("maxY")) {
            maxY = ((Number)args.get("maxY")).doubleValue();
        }
        if (args.containsKey("maxZ")) {
            maxZ = ((Number)args.get("maxZ")).doubleValue();
        }
        return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
    }
}

