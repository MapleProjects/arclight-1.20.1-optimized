/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.Level
 */
package io.izzel.arclight.common.mod.server.world;

import io.izzel.arclight.common.mod.ArclightMod;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Function;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

public class WrappedWorlds {
    private static final HashSet<Class<?>> RESULT = new HashSet();
    private static final HashMap<Class<?>, Field> FIELD = new HashMap();

    public static Optional<Field> getDelegate(Class<?> cl) {
        if (cl.equals(ServerLevel.class)) {
            return Optional.empty();
        }
        return WrappedWorlds.getOrCreate(cl, key -> {
            for (Field f : cl.getDeclaredFields()) {
                if (!Level.class.isAssignableFrom(f.getType())) continue;
                ArclightMod.LOGGER.debug("{} delegates to field {}", (Object)cl, (Object)f.getName());
                f.setAccessible(true);
                return f;
            }
            Optional<Field> delegate = WrappedWorlds.getDelegate(cl.getSuperclass());
            if (delegate.isPresent()) {
                return delegate.get();
            }
            ArclightMod.LOGGER.debug("{} delegates to nothing", (Object)cl);
            return null;
        });
    }

    private static Optional<Field> getOrCreate(Class<?> cl, Function<Class<?>, Field> function) {
        Field field = FIELD.get(cl);
        if (field != null) {
            return Optional.of(field);
        }
        if (RESULT.contains(cl)) {
            return Optional.empty();
        }
        Field delegate = function.apply(cl);
        RESULT.add(cl);
        if (delegate != null) {
            FIELD.put(cl, delegate);
        }
        return Optional.ofNullable(delegate);
    }
}

