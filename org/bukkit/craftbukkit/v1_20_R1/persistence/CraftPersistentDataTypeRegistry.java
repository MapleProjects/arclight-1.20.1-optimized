/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.primitives.Primitives
 *  net.minecraft.nbt.ByteArrayTag
 *  net.minecraft.nbt.ByteTag
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.DoubleTag
 *  net.minecraft.nbt.FloatTag
 *  net.minecraft.nbt.IntArrayTag
 *  net.minecraft.nbt.IntTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.LongArrayTag
 *  net.minecraft.nbt.LongTag
 *  net.minecraft.nbt.ShortTag
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 */
package org.bukkit.craftbukkit.v1_20_R1.persistence;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Primitives;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import net.minecraft.nbt.ByteArrayTag;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongArrayTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.persistence.PersistentDataContainer;

public final class CraftPersistentDataTypeRegistry {
    private final Function<Class, TagAdapter> CREATE_ADAPTER = this::createAdapter;
    private final Map<Class, TagAdapter> adapters = new HashMap<Class, TagAdapter>();

    private <T> TagAdapter createAdapter(Class<T> type) {
        if (!Primitives.isWrapperType(type)) {
            type = Primitives.wrap(type);
        }
        if (Objects.equals(Byte.class, type)) {
            return this.createAdapter(Byte.class, ByteTag.class, ByteTag::m_128266_, ByteTag::m_7063_);
        }
        if (Objects.equals(Short.class, type)) {
            return this.createAdapter(Short.class, ShortTag.class, ShortTag::m_129258_, ShortTag::m_7053_);
        }
        if (Objects.equals(Integer.class, type)) {
            return this.createAdapter(Integer.class, IntTag.class, IntTag::m_128679_, IntTag::m_7047_);
        }
        if (Objects.equals(Long.class, type)) {
            return this.createAdapter(Long.class, LongTag.class, LongTag::m_128882_, LongTag::m_7046_);
        }
        if (Objects.equals(Float.class, type)) {
            return this.createAdapter(Float.class, FloatTag.class, FloatTag::m_128566_, FloatTag::m_7057_);
        }
        if (Objects.equals(Double.class, type)) {
            return this.createAdapter(Double.class, DoubleTag.class, DoubleTag::m_128500_, DoubleTag::m_7061_);
        }
        if (Objects.equals(String.class, type)) {
            return this.createAdapter(String.class, StringTag.class, StringTag::m_129297_, StringTag::m_7916_);
        }
        if (Objects.equals(byte[].class, type)) {
            return this.createAdapter(byte[].class, ByteArrayTag.class, array -> new ByteArrayTag(Arrays.copyOf(array, ((byte[])array).length)), n -> Arrays.copyOf(n.m_128227_(), n.size()));
        }
        if (Objects.equals(int[].class, type)) {
            return this.createAdapter(int[].class, IntArrayTag.class, array -> new IntArrayTag(Arrays.copyOf(array, ((int[])array).length)), n -> Arrays.copyOf(n.m_128648_(), n.size()));
        }
        if (Objects.equals(long[].class, type)) {
            return this.createAdapter(long[].class, LongArrayTag.class, array -> new LongArrayTag(Arrays.copyOf(array, ((long[])array).length)), n -> Arrays.copyOf(n.m_128851_(), n.size()));
        }
        if (Objects.equals(PersistentDataContainer[].class, type)) {
            return this.createAdapter(PersistentDataContainer[].class, ListTag.class, containerArray -> {
                ListTag list = new ListTag();
                int i = 0;
                while (i < ((PersistentDataContainer[])containerArray).length) {
                    list.add((Object)((CraftPersistentDataContainer)containerArray[i]).toTagCompound());
                    ++i;
                }
                return list;
            }, tag -> {
                PersistentDataContainer[] containerArray = new CraftPersistentDataContainer[tag.size()];
                int i = 0;
                while (i < tag.size()) {
                    CraftPersistentDataContainer container = new CraftPersistentDataContainer(this);
                    CompoundTag compound = tag.m_128728_(i);
                    for (String key : compound.m_128431_()) {
                        container.put(key, compound.m_128423_(key));
                    }
                    containerArray[i] = container;
                    ++i;
                }
                return containerArray;
            });
        }
        if (Objects.equals(PersistentDataContainer.class, type)) {
            return this.createAdapter(CraftPersistentDataContainer.class, CompoundTag.class, CraftPersistentDataContainer::toTagCompound, tag -> {
                CraftPersistentDataContainer container = new CraftPersistentDataContainer(this);
                for (String key : tag.m_128431_()) {
                    container.put(key, tag.m_128423_(key));
                }
                return container;
            });
        }
        throw new IllegalArgumentException("Could not find a valid TagAdapter implementation for the requested type " + type.getSimpleName());
    }

    private <T, Z extends Tag> TagAdapter<T, Z> createAdapter(Class<T> primitiveType, Class<Z> nbtBaseType, Function<T, Z> builder, Function<Z, T> extractor) {
        return new TagAdapter<T, Z>(primitiveType, nbtBaseType, builder, extractor);
    }

    public <T> Tag wrap(Class<T> type, T value) {
        return this.adapters.computeIfAbsent(type, this.CREATE_ADAPTER).build(value);
    }

    public <T> boolean isInstanceOf(Class<T> type, Tag base) {
        return this.adapters.computeIfAbsent(type, this.CREATE_ADAPTER).isInstance(base);
    }

    public <T> T extract(Class<T> type, Tag tag) throws ClassCastException, IllegalArgumentException {
        TagAdapter adapter = this.adapters.computeIfAbsent(type, this.CREATE_ADAPTER);
        Preconditions.checkArgument((boolean)adapter.isInstance(tag), (String)"The found tag instance (%s) cannot store %s", (Object)tag.getClass().getSimpleName(), (Object)type.getSimpleName());
        Object foundValue = adapter.extract(tag);
        Preconditions.checkArgument((boolean)type.isInstance(foundValue), (String)"The found object is of the type %s. Expected type %s", (Object)foundValue.getClass().getSimpleName(), (Object)type.getSimpleName());
        return type.cast(foundValue);
    }

    private class TagAdapter<T, Z extends Tag> {
        private final Function<T, Z> builder;
        private final Function<Z, T> extractor;
        private final Class<T> primitiveType;
        private final Class<Z> nbtBaseType;

        public TagAdapter(Class<T> primitiveType, Class<Z> nbtBaseType, Function<T, Z> builder, Function<Z, T> extractor) {
            this.primitiveType = primitiveType;
            this.nbtBaseType = nbtBaseType;
            this.builder = builder;
            this.extractor = extractor;
        }

        T extract(Tag base) {
            Preconditions.checkArgument((boolean)this.nbtBaseType.isInstance(base), (String)"The provided NBTBase was of the type %s. Expected type %s", (Object)base.getClass().getSimpleName(), (Object)this.nbtBaseType.getSimpleName());
            return this.extractor.apply((Tag)this.nbtBaseType.cast(base));
        }

        Z build(Object value) {
            Preconditions.checkArgument((boolean)this.primitiveType.isInstance(value), (String)"The provided value was of the type %s. Expected type %s", (Object)value.getClass().getSimpleName(), (Object)this.primitiveType.getSimpleName());
            return (Z)((Tag)this.builder.apply(this.primitiveType.cast(value)));
        }

        boolean isInstance(Tag base) {
            return this.nbtBaseType.isInstance(base);
        }
    }
}

