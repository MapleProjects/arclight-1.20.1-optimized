/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class Vibration {
    private final Location origin;
    private final Destination destination;
    private final int arrivalTime;

    public Vibration(@NotNull Location origin, @NotNull Destination destination, int arrivalTime) {
        this.origin = origin;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
    }

    @NotNull
    public Location getOrigin() {
        return this.origin;
    }

    @NotNull
    public Destination getDestination() {
        return this.destination;
    }

    public int getArrivalTime() {
        return this.arrivalTime;
    }

    public static interface Destination {

        public static class BlockDestination
        implements Destination {
            private final Location block;

            public BlockDestination(@NotNull Location block) {
                this.block = block;
            }

            public BlockDestination(@NotNull Block block) {
                this(block.getLocation());
            }

            @NotNull
            public Location getLocation() {
                return this.block;
            }

            @NotNull
            public Block getBlock() {
                return this.block.getBlock();
            }
        }

        public static class EntityDestination
        implements Destination {
            private final Entity entity;

            public EntityDestination(@NotNull Entity entity) {
                this.entity = entity;
            }

            @NotNull
            public Entity getEntity() {
                return this.entity;
            }
        }
    }
}

