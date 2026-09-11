/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.entity;

import org.bukkit.entity.Animals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Ocelot
extends Animals {
    public boolean isTrusting();

    public void setTrusting(boolean var1);

    @Deprecated
    @NotNull
    public Type getCatType();

    @Deprecated
    public void setCatType(@NotNull Type var1);

    @Deprecated
    public static enum Type {
        WILD_OCELOT(0),
        BLACK_CAT(1),
        RED_CAT(2),
        SIAMESE_CAT(3);

        private static final Type[] types;
        private final int id;

        static {
            types = new Type[Type.values().length];
            Type[] typeArray = Type.values();
            int n = typeArray.length;
            int n2 = 0;
            while (n2 < n) {
                Type type;
                Type.types[type.getId()] = type = typeArray[n2];
                ++n2;
            }
        }

        private Type(int id) {
            this.id = id;
        }

        @Deprecated
        public int getId() {
            return this.id;
        }

        @Deprecated
        @Nullable
        public static Type getType(int id) {
            return id >= types.length ? null : types[id];
        }
    }
}

