/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.item.Item
 */
package org.bukkit.craftbukkit.v1_20_R1.tag;

import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.tag.CraftTag;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;

public class CraftItemTag
extends CraftTag<Item, Material> {
    public CraftItemTag(Registry<Item> registry, TagKey<Item> tag) {
        super(registry, tag);
    }

    @Override
    public boolean isTagged(Material item) {
        Item minecraft = CraftMagicNumbers.getItem(item);
        if (minecraft == null) {
            return false;
        }
        return minecraft.m_204114_().m_203656_(this.tag);
    }

    @Override
    public Set<Material> getValues() {
        return this.getHandle().m_203614_().map(item -> CraftMagicNumbers.getMaterial((Item)item.m_203334_())).collect(Collectors.toUnmodifiableSet());
    }
}

