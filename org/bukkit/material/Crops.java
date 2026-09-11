/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

@Deprecated
public class Crops
extends MaterialData {
    protected static final Material DEFAULT_TYPE = Material.LEGACY_CROPS;
    protected static final CropState DEFAULT_STATE = CropState.SEEDED;

    public Crops() {
        this(DEFAULT_TYPE, DEFAULT_STATE);
    }

    public Crops(CropState state) {
        this(DEFAULT_TYPE, state);
        this.setState(state);
    }

    public Crops(Material type, CropState state) {
        super(type);
        this.setState(state);
    }

    public Crops(Material type) {
        this(type, DEFAULT_STATE);
    }

    @Deprecated
    public Crops(Material type, byte data) {
        super(type, data);
    }

    public CropState getState() {
        switch (this.getItemType()) {
            case LEGACY_CROPS: 
            case LEGACY_CARROT: 
            case LEGACY_POTATO: {
                return CropState.getByData((byte)(this.getData() & 7));
            }
            case LEGACY_NETHER_WARTS: 
            case LEGACY_BEETROOT_BLOCK: {
                return CropState.getByData((byte)(((this.getData() & 3) * 7 + 2) / 3));
            }
        }
        throw new IllegalArgumentException("Block type is not a crop");
    }

    public void setState(CropState state) {
        switch (this.getItemType()) {
            case LEGACY_CROPS: 
            case LEGACY_CARROT: 
            case LEGACY_POTATO: {
                this.setData((byte)(this.getData() & 8 | state.getData()));
                break;
            }
            case LEGACY_NETHER_WARTS: 
            case LEGACY_BEETROOT_BLOCK: {
                this.setData((byte)(this.getData() & 0xC | state.getData() >> 1));
                break;
            }
            default: {
                throw new IllegalArgumentException("Block type is not a crop");
            }
        }
    }

    @Override
    public String toString() {
        return (Object)((Object)this.getState()) + " " + super.toString();
    }

    @Override
    public Crops clone() {
        return (Crops)super.clone();
    }
}

