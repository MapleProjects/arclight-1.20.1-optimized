/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.tags.TagKey
 *  net.minecraft.world.entity.EntityType
 */
package org.bukkit.craftbukkit.v1_20_R1.tag;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.v1_20_R1.tag.CraftTag;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;

public class CraftEntityTag
extends CraftTag<EntityType<?>, org.bukkit.entity.EntityType> {
    public CraftEntityTag(net.minecraft.core.Registry<EntityType<?>> registry, TagKey<EntityType<?>> tag) {
        super(registry, tag);
    }

    @Override
    public boolean isTagged(org.bukkit.entity.EntityType entity) {
        return this.registry.m_246971_(ResourceKey.m_135785_((ResourceKey)Registries.f_256939_, (ResourceLocation)CraftNamespacedKey.toMinecraft(entity.getKey()))).m_203656_(this.tag);
    }

    @Override
    public Set<org.bukkit.entity.EntityType> getValues() {
        return this.getHandle().m_203614_().map(nms -> Registry.ENTITY_TYPE.get(CraftNamespacedKey.fromMinecraft(EntityType.m_20613_((EntityType)((EntityType)nms.m_203334_()))))).filter(Objects::nonNull).collect(Collectors.toUnmodifiableSet());
    }
}

