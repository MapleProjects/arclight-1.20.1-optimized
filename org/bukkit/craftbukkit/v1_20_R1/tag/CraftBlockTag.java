/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.level.block.Block
 */
package org.bukkit.craftbukkit.v1_20_R1.tag;

import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.tag.CraftTag;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;

public class CraftBlockTag
extends CraftTag<Block, Material> {
    public CraftBlockTag(Registry<Block> registry, TagKey<Block> tag) {
        super(registry, tag);
    }

    @Override
    public boolean isTagged(Material item) {
        Block block = CraftMagicNumbers.getBlock(item);
        if (block == null) {
            return false;
        }
        return block.m_204297_().m_203656_(this.tag);
    }

    @Override
    public Set<Material> getValues() {
        return this.getHandle().m_203614_().map(block -> CraftMagicNumbers.getMaterial((Block)block.m_203334_())).collect(Collectors.toUnmodifiableSet());
    }
}

