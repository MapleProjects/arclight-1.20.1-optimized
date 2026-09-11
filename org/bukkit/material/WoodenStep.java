/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;
import org.bukkit.material.Wood;

@Deprecated
public class WoodenStep
extends Wood {
    protected static final Material DEFAULT_TYPE = Material.LEGACY_WOOD_STEP;
    protected static final boolean DEFAULT_INVERTED = false;

    public WoodenStep() {
        this(DEFAULT_SPECIES, false);
    }

    public WoodenStep(TreeSpecies species) {
        this(species, false);
    }

    public WoodenStep(TreeSpecies species, boolean inv) {
        super(DEFAULT_TYPE, species);
        this.setInverted(inv);
    }

    @Deprecated
    public WoodenStep(Material type, byte data) {
        super(type, data);
    }

    public boolean isInverted() {
        return (this.getData() & 8) != 0;
    }

    public void setInverted(boolean inv) {
        int dat = this.getData() & 7;
        if (inv) {
            dat |= 8;
        }
        this.setData((byte)dat);
    }

    @Override
    public WoodenStep clone() {
        return (WoodenStep)super.clone();
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + " " + (Object)((Object)this.getSpecies()) + (this.isInverted() ? " inverted" : "");
    }
}

