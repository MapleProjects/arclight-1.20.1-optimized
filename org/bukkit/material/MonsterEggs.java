/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.material;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.material.TexturedMaterial;

@Deprecated
public class MonsterEggs
extends TexturedMaterial {
    private static final List<Material> textures = new ArrayList<Material>();

    static {
        textures.add(Material.LEGACY_STONE);
        textures.add(Material.LEGACY_COBBLESTONE);
        textures.add(Material.LEGACY_SMOOTH_BRICK);
    }

    public MonsterEggs() {
        super(Material.LEGACY_MONSTER_EGGS);
    }

    public MonsterEggs(Material type) {
        super(textures.contains(type) ? Material.LEGACY_MONSTER_EGGS : type);
        if (textures.contains(type)) {
            this.setMaterial(type);
        }
    }

    @Deprecated
    public MonsterEggs(Material type, byte data) {
        super(type, data);
    }

    @Override
    public List<Material> getTextures() {
        return textures;
    }

    @Override
    public MonsterEggs clone() {
        return (MonsterEggs)super.clone();
    }
}

