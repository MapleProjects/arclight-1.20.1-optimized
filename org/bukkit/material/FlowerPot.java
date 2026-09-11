/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.material.LongGrass;
import org.bukkit.material.MaterialData;
import org.bukkit.material.Tree;

@Deprecated
public class FlowerPot
extends MaterialData {
    public FlowerPot() {
        super(Material.LEGACY_FLOWER_POT);
    }

    public FlowerPot(Material type) {
        super(type);
    }

    @Deprecated
    public FlowerPot(Material type, byte data) {
        super(type, data);
    }

    public MaterialData getContents() {
        switch (this.getData()) {
            case 1: {
                return new MaterialData(Material.LEGACY_RED_ROSE);
            }
            case 2: {
                return new MaterialData(Material.LEGACY_YELLOW_FLOWER);
            }
            case 3: {
                return new Tree(TreeSpecies.GENERIC);
            }
            case 4: {
                return new Tree(TreeSpecies.REDWOOD);
            }
            case 5: {
                return new Tree(TreeSpecies.BIRCH);
            }
            case 6: {
                return new Tree(TreeSpecies.JUNGLE);
            }
            case 7: {
                return new MaterialData(Material.LEGACY_RED_MUSHROOM);
            }
            case 8: {
                return new MaterialData(Material.LEGACY_BROWN_MUSHROOM);
            }
            case 9: {
                return new MaterialData(Material.LEGACY_CACTUS);
            }
            case 10: {
                return new MaterialData(Material.LEGACY_DEAD_BUSH);
            }
            case 11: {
                return new LongGrass(GrassSpecies.FERN_LIKE);
            }
        }
        return null;
    }

    public void setContents(MaterialData materialData) {
        GrassSpecies species;
        Material mat = materialData.getItemType();
        if (mat == Material.LEGACY_RED_ROSE) {
            this.setData((byte)1);
        } else if (mat == Material.LEGACY_YELLOW_FLOWER) {
            this.setData((byte)2);
        } else if (mat == Material.LEGACY_RED_MUSHROOM) {
            this.setData((byte)7);
        } else if (mat == Material.LEGACY_BROWN_MUSHROOM) {
            this.setData((byte)8);
        } else if (mat == Material.LEGACY_CACTUS) {
            this.setData((byte)9);
        } else if (mat == Material.LEGACY_DEAD_BUSH) {
            this.setData((byte)10);
        } else if (mat == Material.LEGACY_SAPLING) {
            TreeSpecies species2 = ((Tree)materialData).getSpecies();
            if (species2 == TreeSpecies.GENERIC) {
                this.setData((byte)3);
            } else if (species2 == TreeSpecies.REDWOOD) {
                this.setData((byte)4);
            } else if (species2 == TreeSpecies.BIRCH) {
                this.setData((byte)5);
            } else {
                this.setData((byte)6);
            }
        } else if (mat == Material.LEGACY_LONG_GRASS && (species = ((LongGrass)materialData).getSpecies()) == GrassSpecies.FERN_LIKE) {
            this.setData((byte)11);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " containing " + this.getContents();
    }

    @Override
    public FlowerPot clone() {
        return (FlowerPot)super.clone();
    }
}

