/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.GrassSpecies;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

@Deprecated
public class LongGrass
extends MaterialData {
    public LongGrass() {
        super(Material.LEGACY_LONG_GRASS);
    }

    public LongGrass(GrassSpecies species) {
        this();
        this.setSpecies(species);
    }

    public LongGrass(Material type) {
        super(type);
    }

    @Deprecated
    public LongGrass(Material type, byte data) {
        super(type, data);
    }

    public GrassSpecies getSpecies() {
        return GrassSpecies.getByData(this.getData());
    }

    public void setSpecies(GrassSpecies species) {
        this.setData(species.getData());
    }

    @Override
    public String toString() {
        return (Object)((Object)this.getSpecies()) + " " + super.toString();
    }

    @Override
    public LongGrass clone() {
        return (LongGrass)super.clone();
    }
}

