/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Directional;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Openable;

@Deprecated
public class Door
extends MaterialData
implements Directional,
Openable {
    @Deprecated
    public Door() {
        super(Material.LEGACY_WOODEN_DOOR);
    }

    public Door(Material type) {
        super(type);
    }

    public Door(Material type, BlockFace face) {
        this(type, face, false);
    }

    public Door(Material type, BlockFace face, boolean isOpen) {
        super(type);
        this.setTopHalf(false);
        this.setFacingDirection(face);
        this.setOpen(isOpen);
    }

    public Door(Material type, boolean isHingeRight) {
        super(type);
        this.setTopHalf(true);
        this.setHinge(isHingeRight);
    }

    public Door(TreeSpecies species, BlockFace face) {
        this(Door.getWoodDoorOfSpecies(species), face, false);
    }

    public Door(TreeSpecies species, BlockFace face, boolean isOpen) {
        this(Door.getWoodDoorOfSpecies(species), face, isOpen);
    }

    public Door(TreeSpecies species, boolean isHingeRight) {
        this(Door.getWoodDoorOfSpecies(species), isHingeRight);
    }

    @Deprecated
    public Door(Material type, byte data) {
        super(type, data);
    }

    public static Material getWoodDoorOfSpecies(TreeSpecies species) {
        switch (species) {
            default: {
                return Material.LEGACY_WOODEN_DOOR;
            }
            case BIRCH: {
                return Material.LEGACY_BIRCH_DOOR;
            }
            case REDWOOD: {
                return Material.LEGACY_SPRUCE_DOOR;
            }
            case JUNGLE: {
                return Material.LEGACY_JUNGLE_DOOR;
            }
            case ACACIA: {
                return Material.LEGACY_ACACIA_DOOR;
            }
            case DARK_OAK: 
        }
        return Material.LEGACY_DARK_OAK_DOOR;
    }

    @Override
    public boolean isOpen() {
        return (this.getData() & 4) == 4;
    }

    @Override
    public void setOpen(boolean isOpen) {
        this.setData((byte)(isOpen ? this.getData() | 4 : this.getData() & 0xFFFFFFFB));
    }

    public boolean isTopHalf() {
        return (this.getData() & 8) == 8;
    }

    public void setTopHalf(boolean isTopHalf) {
        this.setData((byte)(isTopHalf ? this.getData() | 8 : this.getData() & 0xFFFFFFF7));
    }

    @Deprecated
    public BlockFace getHingeCorner() {
        return BlockFace.SELF;
    }

    @Override
    public String toString() {
        return String.valueOf(this.isTopHalf() ? "TOP" : "BOTTOM") + " half of " + super.toString();
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        byte data = (byte)(this.getData() & 0xC);
        switch (face) {
            case WEST: {
                data = (byte)(data | 0);
                break;
            }
            case NORTH: {
                data = (byte)(data | 1);
                break;
            }
            case EAST: {
                data = (byte)(data | 2);
                break;
            }
            case SOUTH: {
                data = (byte)(data | 3);
            }
        }
        this.setData(data);
    }

    @Override
    public BlockFace getFacing() {
        byte data = (byte)(this.getData() & 3);
        switch (data) {
            case 0: {
                return BlockFace.WEST;
            }
            case 1: {
                return BlockFace.NORTH;
            }
            case 2: {
                return BlockFace.EAST;
            }
            case 3: {
                return BlockFace.SOUTH;
            }
        }
        throw new IllegalStateException("Unknown door facing (data: " + data + ")");
    }

    public boolean getHinge() {
        return (this.getData() & 1) == 1;
    }

    public void setHinge(boolean isHingeRight) {
        this.setData((byte)(isHingeRight ? this.getData() | 1 : this.getData() & 0xFFFFFFFE));
    }

    @Override
    public Door clone() {
        return (Door)super.clone();
    }
}

