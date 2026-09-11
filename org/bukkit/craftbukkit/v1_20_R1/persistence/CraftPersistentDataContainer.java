/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.persistence;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataAdapterContext;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNBTTagConfigSerializer;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CraftPersistentDataContainer
implements PersistentDataContainer {
    private final Map<String, Tag> customDataTags = new HashMap<String, Tag>();
    private final CraftPersistentDataTypeRegistry registry;
    private final CraftPersistentDataAdapterContext adapterContext;

    public CraftPersistentDataContainer(Map<String, Tag> customTags, CraftPersistentDataTypeRegistry registry) {
        this(registry);
        this.customDataTags.putAll(customTags);
    }

    public CraftPersistentDataContainer(CraftPersistentDataTypeRegistry registry) {
        this.registry = registry;
        this.adapterContext = new CraftPersistentDataAdapterContext(this.registry);
    }

    @Override
    public <T, Z> void set(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"The NamespacedKey key cannot be null");
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"The provided type cannot be null");
        Preconditions.checkArgument((value != null ? 1 : 0) != 0, (Object)"The provided value cannot be null");
        this.customDataTags.put(key.toString(), this.registry.wrap(type.getPrimitiveType(), type.toPrimitive(value, this.adapterContext)));
    }

    @Override
    public <T, Z> boolean has(NamespacedKey key, PersistentDataType<T, Z> type) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"The NamespacedKey key cannot be null");
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"The provided type cannot be null");
        Tag value = this.customDataTags.get(key.toString());
        if (value == null) {
            return false;
        }
        return this.registry.isInstanceOf(type.getPrimitiveType(), value);
    }

    @Override
    public <T, Z> Z get(NamespacedKey key, PersistentDataType<T, Z> type) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"The NamespacedKey key cannot be null");
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"The provided type cannot be null");
        Tag value = this.customDataTags.get(key.toString());
        if (value == null) {
            return null;
        }
        return type.fromPrimitive(this.registry.extract(type.getPrimitiveType(), value), this.adapterContext);
    }

    @Override
    public <T, Z> Z getOrDefault(NamespacedKey key, PersistentDataType<T, Z> type, Z defaultValue) {
        Z z = this.get(key, type);
        return z != null ? z : defaultValue;
    }

    @Override
    public Set<NamespacedKey> getKeys() {
        HashSet<NamespacedKey> keys = new HashSet<NamespacedKey>();
        this.customDataTags.keySet().forEach(key -> {
            String[] keyData = key.split(":", 2);
            if (keyData.length == 2) {
                keys.add(new NamespacedKey(keyData[0], keyData[1]));
            }
        });
        return keys;
    }

    @Override
    public void remove(NamespacedKey key) {
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"The NamespacedKey key cannot be null");
        this.customDataTags.remove(key.toString());
    }

    @Override
    public boolean isEmpty() {
        return this.customDataTags.isEmpty();
    }

    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        return this.adapterContext;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CraftPersistentDataContainer)) {
            return false;
        }
        Map<String, Tag> myRawMap = this.getRaw();
        Map<String, Tag> theirRawMap = ((CraftPersistentDataContainer)obj).getRaw();
        return Objects.equals(myRawMap, theirRawMap);
    }

    public CompoundTag toTagCompound() {
        CompoundTag tag = new CompoundTag();
        for (Map.Entry<String, Tag> entry : this.customDataTags.entrySet()) {
            tag.m_128365_(entry.getKey(), entry.getValue());
        }
        return tag;
    }

    public void put(String key, Tag base) {
        this.customDataTags.put(key, base);
    }

    public void putAll(Map<String, Tag> map) {
        this.customDataTags.putAll(map);
    }

    public void putAll(CompoundTag compound) {
        for (String key : compound.m_128431_()) {
            this.customDataTags.put(key, compound.m_128423_(key));
        }
    }

    public Map<String, Tag> getRaw() {
        return this.customDataTags;
    }

    public CraftPersistentDataTypeRegistry getDataTagTypeRegistry() {
        return this.registry;
    }

    public int hashCode() {
        int hashCode = 3;
        return hashCode += this.customDataTags.hashCode();
    }

    public Map<String, Object> serialize() {
        return (Map)CraftNBTTagConfigSerializer.serialize((Tag)this.toTagCompound());
    }
}

