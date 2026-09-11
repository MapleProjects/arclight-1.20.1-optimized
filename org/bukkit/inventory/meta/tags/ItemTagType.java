/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory.meta.tags;

import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagAdapterContext;
import org.jetbrains.annotations.NotNull;

@Deprecated
public interface ItemTagType<T, Z> {
    public static final ItemTagType<Byte, Byte> BYTE = new PrimitiveTagType<Byte>(Byte.class);
    public static final ItemTagType<Short, Short> SHORT = new PrimitiveTagType<Short>(Short.class);
    public static final ItemTagType<Integer, Integer> INTEGER = new PrimitiveTagType<Integer>(Integer.class);
    public static final ItemTagType<Long, Long> LONG = new PrimitiveTagType<Long>(Long.class);
    public static final ItemTagType<Float, Float> FLOAT = new PrimitiveTagType<Float>(Float.class);
    public static final ItemTagType<Double, Double> DOUBLE = new PrimitiveTagType<Double>(Double.class);
    public static final ItemTagType<String, String> STRING = new PrimitiveTagType<String>(String.class);
    public static final ItemTagType<byte[], byte[]> BYTE_ARRAY = new PrimitiveTagType<byte[]>(byte[].class);
    public static final ItemTagType<int[], int[]> INTEGER_ARRAY = new PrimitiveTagType<int[]>(int[].class);
    public static final ItemTagType<long[], long[]> LONG_ARRAY = new PrimitiveTagType<long[]>(long[].class);
    public static final ItemTagType<CustomItemTagContainer, CustomItemTagContainer> TAG_CONTAINER = new PrimitiveTagType<CustomItemTagContainer>(CustomItemTagContainer.class);

    @NotNull
    public Class<T> getPrimitiveType();

    @NotNull
    public Class<Z> getComplexType();

    @NotNull
    public T toPrimitive(@NotNull Z var1, @NotNull ItemTagAdapterContext var2);

    @NotNull
    public Z fromPrimitive(@NotNull T var1, @NotNull ItemTagAdapterContext var2);

    public static class PrimitiveTagType<T>
    implements ItemTagType<T, T> {
        private final Class<T> primitiveType;

        PrimitiveTagType(@NotNull Class<T> primitiveType) {
            this.primitiveType = primitiveType;
        }

        @Override
        @NotNull
        public Class<T> getPrimitiveType() {
            return this.primitiveType;
        }

        @Override
        @NotNull
        public Class<T> getComplexType() {
            return this.primitiveType;
        }

        @Override
        @NotNull
        public T toPrimitive(@NotNull T complex, @NotNull ItemTagAdapterContext context) {
            return complex;
        }

        @Override
        @NotNull
        public T fromPrimitive(@NotNull T primitive, @NotNull ItemTagAdapterContext context) {
            return primitive;
        }
    }
}

