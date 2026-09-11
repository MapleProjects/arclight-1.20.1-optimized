/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.entity.Vehicle;
import org.jetbrains.annotations.NotNull;

public interface Boat
extends Vehicle {
    @Deprecated
    @NotNull
    public TreeSpecies getWoodType();

    @Deprecated
    public void setWoodType(@NotNull TreeSpecies var1);

    @NotNull
    public Type getBoatType();

    public void setBoatType(@NotNull Type var1);

    @Deprecated
    public double getMaxSpeed();

    @Deprecated
    public void setMaxSpeed(double var1);

    @Deprecated
    public double getOccupiedDeceleration();

    @Deprecated
    public void setOccupiedDeceleration(double var1);

    @Deprecated
    public double getUnoccupiedDeceleration();

    @Deprecated
    public void setUnoccupiedDeceleration(double var1);

    @Deprecated
    public boolean getWorkOnLand();

    @Deprecated
    public void setWorkOnLand(boolean var1);

    @NotNull
    public Status getStatus();

    public static enum Status {
        IN_WATER,
        UNDER_WATER,
        UNDER_FLOWING_WATER,
        ON_LAND,
        IN_AIR;

    }

    public static enum Type {
        OAK(Material.OAK_PLANKS),
        SPRUCE(Material.SPRUCE_PLANKS),
        BIRCH(Material.BIRCH_PLANKS),
        JUNGLE(Material.JUNGLE_PLANKS),
        ACACIA(Material.ACACIA_PLANKS),
        CHERRY(Material.CHERRY_PLANKS),
        DARK_OAK(Material.DARK_OAK_PLANKS),
        MANGROVE(Material.MANGROVE_PLANKS),
        BAMBOO(Material.BAMBOO_PLANKS);

        private final Material materialBlock;

        private Type(Material materialBlock) {
            this.materialBlock = materialBlock;
        }

        @NotNull
        public Material getMaterial() {
            return this.materialBlock;
        }
    }
}

