/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.world.phys.AABB;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.VoxelShape;

public final class CraftVoxelShape
implements VoxelShape {
    private final net.minecraft.world.phys.shapes.VoxelShape shape;

    public CraftVoxelShape(net.minecraft.world.phys.shapes.VoxelShape shape) {
        this.shape = shape;
    }

    @Override
    public Collection<BoundingBox> getBoundingBoxes() {
        List boxes = this.shape.m_83299_();
        ArrayList<BoundingBox> craftBoxes = new ArrayList<BoundingBox>(boxes.size());
        for (AABB aabb : boxes) {
            craftBoxes.add(new BoundingBox(aabb.f_82288_, aabb.f_82289_, aabb.f_82290_, aabb.f_82291_, aabb.f_82292_, aabb.f_82293_));
        }
        return craftBoxes;
    }

    @Override
    public boolean overlaps(BoundingBox other) {
        Preconditions.checkArgument((other != null ? 1 : 0) != 0, (Object)"Other cannot be null");
        for (BoundingBox box : this.getBoundingBoxes()) {
            if (!box.overlaps(other)) continue;
            return true;
        }
        return false;
    }
}

