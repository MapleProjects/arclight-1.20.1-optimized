/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.level.material.Fluid
 */
package org.bukkit.craftbukkit.v1_20_R1.tag;

import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;
import org.bukkit.Fluid;
import org.bukkit.craftbukkit.v1_20_R1.tag.CraftTag;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;

public class CraftFluidTag
extends CraftTag<net.minecraft.world.level.material.Fluid, Fluid> {
    public CraftFluidTag(Registry<net.minecraft.world.level.material.Fluid> registry, TagKey<net.minecraft.world.level.material.Fluid> tag) {
        super(registry, tag);
    }

    @Override
    public boolean isTagged(Fluid fluid) {
        return CraftMagicNumbers.getFluid(fluid).m_205067_(this.tag);
    }

    @Override
    public Set<Fluid> getValues() {
        return this.getHandle().m_203614_().map(fluid -> CraftMagicNumbers.getFluid((net.minecraft.world.level.material.Fluid)fluid.m_203334_())).collect(Collectors.toUnmodifiableSet());
    }
}

