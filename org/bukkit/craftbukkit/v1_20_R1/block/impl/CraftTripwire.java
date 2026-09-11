/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  net.minecraft.world.level.block.TripWireBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import com.google.common.collect.ImmutableSet;
import java.util.Set;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Attachable;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftTripwire
extends CraftBlockData
implements Tripwire,
Attachable,
MultipleFacing,
Powerable {
    private static final BooleanProperty DISARMED = CraftTripwire.getBoolean(TripWireBlock.class, "disarmed");
    private static final BooleanProperty ATTACHED = CraftTripwire.getBoolean(TripWireBlock.class, "attached");
    private static final BooleanProperty[] FACES = new BooleanProperty[]{CraftTripwire.getBoolean(TripWireBlock.class, "north", true), CraftTripwire.getBoolean(TripWireBlock.class, "east", true), CraftTripwire.getBoolean(TripWireBlock.class, "south", true), CraftTripwire.getBoolean(TripWireBlock.class, "west", true), CraftTripwire.getBoolean(TripWireBlock.class, "up", true), CraftTripwire.getBoolean(TripWireBlock.class, "down", true)};
    private static final BooleanProperty POWERED = CraftTripwire.getBoolean(TripWireBlock.class, "powered");

    public CraftTripwire() {
    }

    public CraftTripwire(BlockState state) {
        super(state);
    }

    @Override
    public boolean isDisarmed() {
        return (Boolean)this.get(DISARMED);
    }

    @Override
    public void setDisarmed(boolean disarmed) {
        this.set(DISARMED, disarmed);
    }

    @Override
    public boolean isAttached() {
        return (Boolean)this.get(ATTACHED);
    }

    @Override
    public void setAttached(boolean attached) {
        this.set(ATTACHED, attached);
    }

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

    @Override
    public boolean isPowered() {
        return (Boolean)this.get(POWERED);
    }

    @Override
    public void setPowered(boolean powered) {
        this.set(POWERED, powered);
    }
}

