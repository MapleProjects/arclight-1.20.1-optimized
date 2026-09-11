/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Sets
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaEntityTag
extends CraftMetaItem {
    private static final Set<Material> ENTITY_TAGGABLE_MATERIALS = Sets.newHashSet((Object[])new Material[]{Material.COD_BUCKET, Material.PUFFERFISH_BUCKET, Material.SALMON_BUCKET, Material.ITEM_FRAME, Material.GLOW_ITEM_FRAME, Material.PAINTING});
    static final CraftMetaItem.ItemMetaKey ENTITY_TAG = new CraftMetaItem.ItemMetaKey("EntityTag", "entity-tag");
    CompoundTag entityTag;

    CraftMetaEntityTag(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaEntityTag)) {
            return;
        }
        CraftMetaEntityTag entity = (CraftMetaEntityTag)meta;
        this.entityTag = entity.entityTag;
    }

    CraftMetaEntityTag(CompoundTag tag) {
        super(tag);
        if (tag.m_128441_(CraftMetaEntityTag.ENTITY_TAG.NBT)) {
            this.entityTag = tag.m_128469_(CraftMetaEntityTag.ENTITY_TAG.NBT).m_6426_();
        }
    }

    CraftMetaEntityTag(Map<String, Object> map) {
        super(map);
    }

    @Override
    void deserializeInternal(CompoundTag tag, Object context) {
        super.deserializeInternal(tag, context);
        if (tag.m_128441_(CraftMetaEntityTag.ENTITY_TAG.NBT)) {
            this.entityTag = tag.m_128469_(CraftMetaEntityTag.ENTITY_TAG.NBT);
        }
    }

    @Override
    void serializeInternal(Map<String, Tag> internalTags) {
        if (this.entityTag != null && !this.entityTag.m_128456_()) {
            internalTags.put(CraftMetaEntityTag.ENTITY_TAG.NBT, (Tag)this.entityTag);
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.entityTag != null) {
            tag.m_128365_(CraftMetaEntityTag.ENTITY_TAG.NBT, (Tag)this.entityTag);
        }
    }

    @Override
    boolean applicableTo(Material type) {
        return ENTITY_TAGGABLE_MATERIALS.contains(type);
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isEntityTagEmpty();
    }

    boolean isEntityTagEmpty() {
        return this.entityTag == null;
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaEntityTag) {
            CraftMetaEntityTag that = (CraftMetaEntityTag)meta;
            return this.entityTag != null ? that.entityTag != null && this.entityTag.equals((Object)that.entityTag) : this.entityTag == null;
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaEntityTag || this.isEntityTagEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.entityTag != null) {
            hash = 73 * hash + this.entityTag.hashCode();
        }
        return original != hash ? CraftMetaEntityTag.class.hashCode() ^ hash : hash;
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        return builder;
    }

    @Override
    public CraftMetaEntityTag clone() {
        CraftMetaEntityTag clone = (CraftMetaEntityTag)super.clone();
        if (this.entityTag != null) {
            clone.entityTag = this.entityTag.m_6426_();
        }
        return clone;
    }
}

