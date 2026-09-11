/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Directional;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Redstone;

@Deprecated
public class Comparator
extends MaterialData
implements Directional,
Redstone {
    protected static final BlockFace DEFAULT_DIRECTION = BlockFace.NORTH;
    protected static final boolean DEFAULT_SUBTRACTION_MODE = false;
    protected static final boolean DEFAULT_STATE = false;

    public Comparator() {
        this(DEFAULT_DIRECTION, false, false);
    }

    public Comparator(BlockFace facingDirection) {
        this(facingDirection, false, false);
    }

    public Comparator(BlockFace facingDirection, boolean isSubtraction) {
        this(facingDirection, isSubtraction, false);
    }

    public Comparator(BlockFace facingDirection, boolean isSubtraction, boolean state) {
        super(state ? Material.LEGACY_REDSTONE_COMPARATOR_ON : Material.LEGACY_REDSTONE_COMPARATOR_OFF);
        this.setFacingDirection(facingDirection);
        this.setSubtractionMode(isSubtraction);
    }

    public Comparator(Material type) {
        super(type);
    }

    @Deprecated
    public Comparator(Material type, byte data) {
        super(type, data);
    }

    public void setSubtractionMode(boolean isSubtraction) {
        this.setData((byte)(this.getData() & 0xB | (isSubtraction ? 4 : 0)));
    }

    public boolean isSubtractionMode() {
        return (this.getData() & 4) != 0;
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        int data = this.getData() & 0xC;
        switch (face) {
            case EAST: {
                data |= 1;
                break;
            }
            case SOUTH: {
                data |= 2;
                break;
            }
            case WEST: {
                data |= 3;
                break;
            }
            default: {
                data |= 0;
            }
        }
        this.setData((byte)data);
    }

    @Override
    public BlockFace getFacing() {
        byte data = (byte)(this.getData() & 3);
        switch (data) {
            default: {
                return BlockFace.NORTH;
            }
            case 1: {
                return BlockFace.EAST;
            }
            case 2: {
                return BlockFace.SOUTH;
            }
            case 3: 
        }
        return BlockFace.WEST;
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " facing " + (Object)((Object)this.getFacing()) + " in " + (this.isSubtractionMode() ? "subtraction" : "comparator") + " mode";
    }

    @Override
    public Comparator clone() {
        return (Comparator)super.clone();
    }

    @Override
    public boolean isPowered() {
        return this.getItemType() == Material.LEGACY_REDSTONE_COMPARATOR_ON;
    }

    public boolean isBeingPowered() {
        return (this.getData() & 8) != 0;
    }
}

