/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.tools.collection;

public class TypedKey<A> {
    private final String displayName;

    private TypedKey(String displayName) {
        this.displayName = displayName;
    }

    public String toString() {
        return this.displayName == null ? super.toString() : this.displayName;
    }

    public static <A> TypedKey<A> of() {
        return new TypedKey<A>(null);
    }

    public static <A> TypedKey<A> of(String displayName) {
        return new TypedKey<A>(displayName);
    }
}

