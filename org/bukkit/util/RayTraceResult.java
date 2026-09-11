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
import java.util.Objects;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RayTraceResult {
    private final Vector hitPosition;
    private final Block hitBlock;
    private final BlockFace hitBlockFace;
    private final Entity hitEntity;

    private RayTraceResult(@NotNull Vector hitPosition, @Nullable Block hitBlock, @Nullable BlockFace hitBlockFace, @Nullable Entity hitEntity) {
        Preconditions.checkArgument((hitPosition != null ? 1 : 0) != 0, (Object)"Hit position is null!");
        this.hitPosition = hitPosition.clone();
        this.hitBlock = hitBlock;
        this.hitBlockFace = hitBlockFace;
        this.hitEntity = hitEntity;
    }

    public RayTraceResult(@NotNull Vector hitPosition) {
        this(hitPosition, null, null, null);
    }

    public RayTraceResult(@NotNull Vector hitPosition, @Nullable BlockFace hitBlockFace) {
        this(hitPosition, null, hitBlockFace, null);
    }

    public RayTraceResult(@NotNull Vector hitPosition, @Nullable Block hitBlock, @Nullable BlockFace hitBlockFace) {
        this(hitPosition, hitBlock, hitBlockFace, null);
    }

    public RayTraceResult(@NotNull Vector hitPosition, @Nullable Entity hitEntity) {
        this(hitPosition, null, null, hitEntity);
    }

    public RayTraceResult(@NotNull Vector hitPosition, @Nullable Entity hitEntity, @Nullable BlockFace hitBlockFace) {
        this(hitPosition, null, hitBlockFace, hitEntity);
    }

    @NotNull
    public Vector getHitPosition() {
        return this.hitPosition.clone();
    }

    @Nullable
    public Block getHitBlock() {
        return this.hitBlock;
    }

    @Nullable
    public BlockFace getHitBlockFace() {
        return this.hitBlockFace;
    }

    @Nullable
    public Entity getHitEntity() {
        return this.hitEntity;
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + this.hitPosition.hashCode();
        result = 31 * result + (this.hitBlock == null ? 0 : this.hitBlock.hashCode());
        result = 31 * result + (this.hitBlockFace == null ? 0 : this.hitBlockFace.hashCode());
        result = 31 * result + (this.hitEntity == null ? 0 : this.hitEntity.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RayTraceResult)) {
            return false;
        }
        RayTraceResult other = (RayTraceResult)obj;
        if (!this.hitPosition.equals(other.hitPosition)) {
            return false;
        }
        if (!Objects.equals(this.hitBlock, other.hitBlock)) {
            return false;
        }
        if (!Objects.equals((Object)this.hitBlockFace, (Object)other.hitBlockFace)) {
            return false;
        }
        return Objects.equals(this.hitEntity, other.hitEntity);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RayTraceResult [hitPosition=");
        builder.append(this.hitPosition);
        builder.append(", hitBlock=");
        builder.append(this.hitBlock);
        builder.append(", hitBlockFace=");
        builder.append((Object)this.hitBlockFace);
        builder.append(", hitEntity=");
        builder.append(this.hitEntity);
        builder.append("]");
        return builder.toString();
    }
}

