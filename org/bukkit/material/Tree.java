/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Wood;

@Deprecated
public class Tree
extends Wood {
    protected static final Material DEFAULT_TYPE = Material.LEGACY_LOG;
    protected static final BlockFace DEFAULT_DIRECTION = BlockFace.UP;

    public Tree() {
        this(DEFAULT_TYPE, DEFAULT_SPECIES, DEFAULT_DIRECTION);
    }

    public Tree(TreeSpecies species) {
        this(DEFAULT_TYPE, species, DEFAULT_DIRECTION);
    }

    public Tree(TreeSpecies species, BlockFace dir) {
        this(DEFAULT_TYPE, species, dir);
    }

    public Tree(Material type) {
        this(type, DEFAULT_SPECIES, DEFAULT_DIRECTION);
    }

    public Tree(Material type, TreeSpecies species) {
        this(type, species, DEFAULT_DIRECTION);
    }

    public Tree(Material type, TreeSpecies species, BlockFace dir) {
        super(type, species);
        this.setDirection(dir);
    }

    @Deprecated
    public Tree(Material type, byte data) {
        super(type, data);
    }

    public BlockFace getDirection() {
        switch (this.getData() >> 2 & 3) {
            default: {
                return BlockFace.UP;
            }
            case 1: {
                return BlockFace.WEST;
            }
            case 2: {
                return BlockFace.NORTH;
            }
            case 3: 
        }
        return BlockFace.SELF;
    }

    public void setDirection(BlockFace dir) {
        int dat;
        switch (dir) {
            default: {
                dat = 0;
                break;
            }
            case EAST: 
            case WEST: {
                dat = 4;
                break;
            }
            case NORTH: 
            case SOUTH: {
                dat = 8;
                break;
            }
            case SELF: {
                dat = 12;
            }
        }
        this.setData((byte)(this.getData() & 3 | dat));
    }

    @Override
    public String toString() {
        return (Object)((Object)this.getSpecies()) + " " + (Object)((Object)this.getDirection()) + " " + super.toString();
    }

    @Override
    public Tree clone() {
        return (Tree)super.clone();
    }
}

