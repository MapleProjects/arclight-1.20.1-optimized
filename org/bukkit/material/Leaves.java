/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.material.Wood;

@Deprecated
public class Leaves
extends Wood {
    protected static final Material DEFAULT_TYPE = Material.LEGACY_LEAVES;
    protected static final boolean DEFAULT_DECAYABLE = true;

    public Leaves() {
        this(DEFAULT_TYPE, DEFAULT_SPECIES, true);
    }

    public Leaves(TreeSpecies species) {
        this(DEFAULT_TYPE, species, true);
    }

    public Leaves(TreeSpecies species, boolean isDecayable) {
        this(DEFAULT_TYPE, species, isDecayable);
    }

    public Leaves(Material type) {
        this(type, DEFAULT_SPECIES, true);
    }

    public Leaves(Material type, TreeSpecies species) {
        this(type, species, true);
    }

    public Leaves(Material type, TreeSpecies species, boolean isDecayable) {
        super(type, species);
        this.setDecayable(isDecayable);
    }

    @Deprecated
    public Leaves(Material type, byte data) {
        super(type, data);
    }

    public boolean isDecaying() {
        return (this.getData() & 8) != 0;
    }

    public void setDecaying(boolean isDecaying) {
        this.setData((byte)(this.getData() & 3 | (isDecaying ? 8 : this.getData() & 4)));
    }

    public boolean isDecayable() {
        return (this.getData() & 4) == 0;
    }

    public void setDecayable(boolean isDecayable) {
        this.setData((byte)(this.getData() & 3 | (isDecayable ? this.getData() & 8 : 4)));
    }

    @Override
    public String toString() {
        return (Object)((Object)this.getSpecies()) + (this.isDecayable() ? " DECAYABLE " : " PERMANENT ") + (this.isDecaying() ? " DECAYING " : " ") + super.toString();
    }

    @Override
    public Leaves clone() {
        return (Leaves)super.clone();
    }
}

