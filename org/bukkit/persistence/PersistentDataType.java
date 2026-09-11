/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.persistence;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

public interface PersistentDataType<T, Z> {
    public static final PersistentDataType<Byte, Byte> BYTE = new PrimitivePersistentDataType<Byte>(Byte.class);
    public static final PersistentDataType<Short, Short> SHORT = new PrimitivePersistentDataType<Short>(Short.class);
    public static final PersistentDataType<Integer, Integer> INTEGER = new PrimitivePersistentDataType<Integer>(Integer.class);
    public static final PersistentDataType<Long, Long> LONG = new PrimitivePersistentDataType<Long>(Long.class);
    public static final PersistentDataType<Float, Float> FLOAT = new PrimitivePersistentDataType<Float>(Float.class);
    public static final PersistentDataType<Double, Double> DOUBLE = new PrimitivePersistentDataType<Double>(Double.class);
    public static final PersistentDataType<Byte, Boolean> BOOLEAN = new BooleanPersistentDataType();
    public static final PersistentDataType<String, String> STRING = new PrimitivePersistentDataType<String>(String.class);
    public static final PersistentDataType<byte[], byte[]> BYTE_ARRAY = new PrimitivePersistentDataType<byte[]>(byte[].class);
    public static final PersistentDataType<int[], int[]> INTEGER_ARRAY = new PrimitivePersistentDataType<int[]>(int[].class);
    public static final PersistentDataType<long[], long[]> LONG_ARRAY = new PrimitivePersistentDataType<long[]>(long[].class);
    public static final PersistentDataType<PersistentDataContainer[], PersistentDataContainer[]> TAG_CONTAINER_ARRAY = new PrimitivePersistentDataType<PersistentDataContainer[]>(PersistentDataContainer[].class);
    public static final PersistentDataType<PersistentDataContainer, PersistentDataContainer> TAG_CONTAINER = new PrimitivePersistentDataType<PersistentDataContainer>(PersistentDataContainer.class);

    @NotNull
    public Class<T> getPrimitiveType();

    @NotNull
    public Class<Z> getComplexType();

    @NotNull
    public T toPrimitive(@NotNull Z var1, @NotNull PersistentDataAdapterContext var2);

    @NotNull
    public Z fromPrimitive(@NotNull T var1, @NotNull PersistentDataAdapterContext var2);

    public static class BooleanPersistentDataType
    implements PersistentDataType<Byte, Boolean> {
        @Override
        @NotNull
        public Class<Byte> getPrimitiveType() {
            return Byte.class;
        }

        @Override
        @NotNull
        public Class<Boolean> getComplexType() {
            return Boolean.class;
        }

        @Override
        @NotNull
        public Byte toPrimitive(@NotNull Boolean complex, @NotNull PersistentDataAdapterContext context) {
            return (byte)(complex != false ? 1 : 0);
        }

        @Override
        @NotNull
        public Boolean fromPrimitive(@NotNull Byte primitive, @NotNull PersistentDataAdapterContext context) {
            if (primitive != 0) {
                return true;
            }
            return false;
        }
    }

    public static class PrimitivePersistentDataType<T>
    implements PersistentDataType<T, T> {
        private final Class<T> primitiveType;

        PrimitivePersistentDataType(@NotNull Class<T> primitiveType) {
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
        public T toPrimitive(@NotNull T complex, @NotNull PersistentDataAdapterContext context) {
            return complex;
        }

        @Override
        @NotNull
        public T fromPrimitive(@NotNull T primitive, @NotNull PersistentDataAdapterContext context) {
            return primitive;
        }
    }
}

