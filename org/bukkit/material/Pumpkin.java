/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Directional;
import org.bukkit.material.MaterialData;

@Deprecated
public class Pumpkin
extends MaterialData
implements Directional {
    public Pumpkin() {
        super(Material.LEGACY_PUMPKIN);
    }

    public Pumpkin(BlockFace direction) {
        this();
        this.setFacingDirection(direction);
    }

    public Pumpkin(Material type) {
        super(type);
    }

    @Deprecated
    public Pumpkin(Material type, byte data) {
        super(type, data);
    }

    public boolean isLit() {
        return this.getItemType() == Material.LEGACY_JACK_O_LANTERN;
    }

    @Override
    public void setFacingDirection(BlockFace face) {
        byte data;
        switch (face) {
            case NORTH: {
                data = 0;
                break;
            }
            case EAST: {
                data = 1;
                break;
            }
            case SOUTH: {
                data = 2;
                break;
            }
            default: {
                data = 3;
            }
        }
        this.setData(data);
    }

    @Override
    public BlockFace getFacing() {
        byte data = this.getData();
        switch (data) {
            case 0: {
                return BlockFace.NORTH;
            }
            case 1: {
                return BlockFace.EAST;
            }
            case 2: {
                return BlockFace.SOUTH;
            }
        }
        return BlockFace.EAST;
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " facing " + (Object)((Object)this.getFacing()) + " " + (this.isLit() ? "" : "NOT ") + "LIT";
    }

    @Override
    public Pumpkin clone() {
        return (Pumpkin)super.clone();
    }
}

