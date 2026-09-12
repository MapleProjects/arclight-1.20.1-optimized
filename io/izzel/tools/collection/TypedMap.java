/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.collection;

import io.izzel.tools.collection.TypedKey;
import io.izzel.tools.func.Func0;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class TypedMap {
    private final Map<TypedKey<?>, Object> underlying;

    public TypedMap() {
        this.underlying = new HashMap();
    }

    public TypedMap(TypedMap map) {
        this.underlying = new HashMap(map.underlying);
    }

    private TypedMap(Map<TypedKey<?>, Object> underlying) {
        this.underlying = underlying;
    }

    public <A> A get(TypedKey<A> key) {
        return (A)this.underlying.get(key);
    }

    public <A> A getOrElse(TypedKey<A> key, Func0<A> supplier) {
        Object ret = this.underlying.get(key);
        return (A)(ret == null ? supplier.apply() : ret);
    }

    public <A> A put(TypedKey<A> key, A value) {
        return (A)this.underlying.put(key, value);
    }

    public <A> A remove(TypedKey<A> key) {
        return (A)this.underlying.remove(key);
    }

    public Map<TypedKey<?>, Object> asMap() {
        return this.underlying;
    }

    public static Builder builder() {
        return new Builder(HashMap::new);
    }

    public static Builder builder(Func0<Map<TypedKey<?>, Object>> mapSupplier) {
        Objects.requireNonNull(mapSupplier, "mapSupplier");
        return new Builder(mapSupplier);
    }

    public static final class Builder {
        private final Map<TypedKey<?>, Object> underlying = new HashMap();
        private final Func0<Map<TypedKey<?>, Object>> mapSupplier;

        private Builder(Func0<Map<TypedKey<?>, Object>> mapSupplier) {
            this.mapSupplier = mapSupplier;
        }

        public <A> Builder put(TypedKey<A> key, A value) {
            this.underlying.put(key, value);
            return this;
        }

        public TypedMap build() {
            Map<TypedKey<?>, Object> map = this.mapSupplier.apply();
            map.putAll(this.underlying);
            return new TypedMap(map);
        }
    }
}

