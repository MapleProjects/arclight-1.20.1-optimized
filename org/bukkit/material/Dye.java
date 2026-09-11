/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.material.Colorable;
import org.bukkit.material.MaterialData;

@Deprecated
public class Dye
extends MaterialData
implements Colorable {
    public Dye() {
        super(Material.LEGACY_INK_SACK);
    }

    public Dye(Material type) {
        super(type);
    }

    @Deprecated
    public Dye(Material type, byte data) {
        super(type, data);
    }

    public Dye(DyeColor color) {
        super(Material.LEGACY_INK_SACK, color.getDyeData());
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.getByDyeData(this.getData());
    }

    @Override
    public void setColor(DyeColor color) {
        this.setData(color.getDyeData());
    }

    @Override
    public String toString() {
        return (Object)((Object)this.getColor()) + " DYE(" + this.getData() + ")";
    }

    @Override
    public Dye clone() {
        return (Dye)super.clone();
    }
}

