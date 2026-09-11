/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.material.TexturedMaterial;

@Deprecated
public class SmoothBrick
extends TexturedMaterial {
    private static final List<Material> textures = new ArrayList<Material>();

    static {
        textures.add(Material.LEGACY_STONE);
        textures.add(Material.LEGACY_MOSSY_COBBLESTONE);
        textures.add(Material.LEGACY_COBBLESTONE);
        textures.add(Material.LEGACY_SMOOTH_BRICK);
    }

    public SmoothBrick() {
        super(Material.LEGACY_SMOOTH_BRICK);
    }

    public SmoothBrick(Material type) {
        super(textures.contains(type) ? Material.LEGACY_SMOOTH_BRICK : type);
        if (textures.contains(type)) {
            this.setMaterial(type);
        }
    }

    @Deprecated
    public SmoothBrick(Material type, byte data) {
        super(type, data);
    }

    @Override
    public List<Material> getTextures() {
        return textures;
    }

    @Override
    public SmoothBrick clone() {
        return (SmoothBrick)super.clone();
    }
}

