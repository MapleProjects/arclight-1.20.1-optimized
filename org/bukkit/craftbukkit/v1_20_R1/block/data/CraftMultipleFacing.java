/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public abstract class CraftMultipleFacing
extends CraftBlockData
implements MultipleFacing {
    private static final BooleanProperty[] FACES = new BooleanProperty[]{CraftMultipleFacing.getBoolean("north", true), CraftMultipleFacing.getBoolean("east", true), CraftMultipleFacing.getBoolean("south", true), CraftMultipleFacing.getBoolean("west", true), CraftMultipleFacing.getBoolean("up", true), CraftMultipleFacing.getBoolean("down", true)};

    @Override
    public boolean hasFace(BlockFace face) {
        BooleanProperty state = FACES[face.ordinal()];
        if (state == null) {
            throw new IllegalArgumentException("Non-allowed face " + (Object)((Object)face) + ". Check MultipleFacing.getAllowedFaces.");
        }
        return (Boolean)this.get(state);
    }

    @Override
    public void setFace(BlockFace face, boolean has) {
        BooleanProperty state = FACES[face.ordinal()];
        if (state == null) {
            throw new IllegalArgumentException("Non-allowed face " + (Object)((Object)face) + ". Check MultipleFacing.getAllowedFaces.");
        }
        this.set(state, has);
    }

    @Override
    public Set<BlockFace> getFaces() {
        ImmutableSet.Builder faces = ImmutableSet.builder();
        int i = 0;
        while (i < FACES.length) {
            if (FACES[i] != null && ((Boolean)this.get(FACES[i])).booleanValue()) {
                faces.add((Object)BlockFace.values()[i]);
            }
            ++i;
        }
        return faces.build();
    }

    @Override
    public Set<BlockFace> getAllowedFaces() {
        ImmutableSet.Builder faces = ImmutableSet.builder();
        int i = 0;
        while (i < FACES.length) {
            if (FACES[i] != null) {
                faces.add((Object)BlockFace.values()[i]);
            }
            ++i;
        }
        return faces.build();
    }
}

