/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.material.MaterialData;

@Deprecated
public class Wood
extends MaterialData {
    protected static final Material DEFAULT_TYPE = Material.LEGACY_WOOD;
    protected static final TreeSpecies DEFAULT_SPECIES = TreeSpecies.GENERIC;

    public Wood() {
        this(DEFAULT_TYPE, DEFAULT_SPECIES);
    }

    public Wood(TreeSpecies species) {
        this(DEFAULT_TYPE, species);
    }

    public Wood(Material type) {
        this(type, DEFAULT_SPECIES);
    }

    public Wood(Material type, TreeSpecies species) {
        super(Wood.getSpeciesType(type, species));
        this.setSpecies(species);
    }

    @Deprecated
    public Wood(Material type, byte data) {
        super(type, data);
    }

    public TreeSpecies getSpecies() {
        switch (this.getItemType()) {
            case LEGACY_WOOD: 
            case LEGACY_WOOD_DOUBLE_STEP: {
                return TreeSpecies.getByData(this.getData());
            }
            case LEGACY_LOG: 
            case LEGACY_LEAVES: {
                return TreeSpecies.getByData((byte)(this.getData() & 3));
            }
            case LEGACY_LEAVES_2: 
            case LEGACY_LOG_2: {
                return TreeSpecies.getByData((byte)(this.getData() & 3 | 4));
            }
            case LEGACY_SAPLING: 
            case LEGACY_WOOD_STEP: {
                return TreeSpecies.getByData((byte)(this.getData() & 7));
            }
        }
        throw new IllegalArgumentException("Invalid block type for tree species");
    }

    private static Material getSpeciesType(Material type, TreeSpecies species) {
        switch (species) {
            case GENERIC: 
            case REDWOOD: 
            case BIRCH: 
            case JUNGLE: {
                switch (type) {
                    case LEGACY_LOG_2: {
                        return Material.LEGACY_LOG;
                    }
                    case LEGACY_LEAVES_2: {
                        return Material.LEGACY_LEAVES;
                    }
                }
                break;
            }
            case ACACIA: 
            case DARK_OAK: {
                switch (type) {
                    case LEGACY_LOG: {
                        return Material.LEGACY_LOG_2;
                    }
                    case LEGACY_LEAVES: {
                        return Material.LEGACY_LEAVES_2;
                    }
                }
            }
        }
        return type;
    }

    public void setSpecies(TreeSpecies species) {
        boolean firstType = false;
        switch (this.getItemType()) {
            case LEGACY_WOOD: 
            case LEGACY_WOOD_DOUBLE_STEP: {
                this.setData(species.getData());
                break;
            }
            case LEGACY_LOG: 
            case LEGACY_LEAVES: {
                firstType = true;
            }
            case LEGACY_LEAVES_2: 
            case LEGACY_LOG_2: {
                switch (species) {
                    case GENERIC: 
                    case REDWOOD: 
                    case BIRCH: 
                    case JUNGLE: {
                        if (firstType) break;
                        throw new IllegalArgumentException("Invalid tree species for block type, use block type 2 instead");
                    }
                    case ACACIA: 
                    case DARK_OAK: {
                        if (!firstType) break;
                        throw new IllegalArgumentException("Invalid tree species for block type 2, use block type instead");
                    }
                }
                this.setData((byte)(this.getData() & 0xC | species.getData() & 3));
                break;
            }
            case LEGACY_SAPLING: 
            case LEGACY_WOOD_STEP: {
                this.setData((byte)(this.getData() & 8 | species.getData()));
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid block type for tree species");
            }
        }
    }

    @Override
    public String toString() {
        return (Object)((Object)this.getSpecies()) + " " + super.toString();
    }

    @Override
    public Wood clone() {
        return (Wood)super.clone();
    }
}

