/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.material.TexturedMaterial;

@Deprecated
public class Step
extends TexturedMaterial {
    private static final List<Material> textures = new ArrayList<Material>();

    static {
        textures.add(Material.LEGACY_STONE);
        textures.add(Material.LEGACY_SANDSTONE);
        textures.add(Material.LEGACY_WOOD);
        textures.add(Material.LEGACY_COBBLESTONE);
        textures.add(Material.LEGACY_BRICK);
        textures.add(Material.LEGACY_SMOOTH_BRICK);
        textures.add(Material.LEGACY_NETHER_BRICK);
        textures.add(Material.LEGACY_QUARTZ_BLOCK);
    }

    public Step() {
        super(Material.LEGACY_STEP);
    }

    public Step(Material type) {
        super(textures.contains(type) ? Material.LEGACY_STEP : type);
        if (textures.contains(type)) {
            this.setMaterial(type);
        }
    }

    @Deprecated
    public Step(Material type, byte data) {
        super(type, data);
    }

    @Override
    public List<Material> getTextures() {
        return textures;
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
    protected int getTextureIndex() {
        return this.getData() & 7;
    }

    @Override
    @Deprecated
    protected void setTextureIndex(int idx) {
        this.setData((byte)(this.getData() & 8 | idx));
    }

    @Override
    public Step clone() {
        return (Step)super.clone();
    }

    @Override
    public String toString() {
        return String.valueOf(super.toString()) + (this.isInverted() ? "inverted" : "");
    }
}

