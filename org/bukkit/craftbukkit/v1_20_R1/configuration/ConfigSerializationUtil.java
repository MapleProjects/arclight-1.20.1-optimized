/*
 * Decompiled with CFR 0.152.
 */
package org.bukkit.craftbukkit.v1_20_R1.configuration;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public final class ConfigSerializationUtil {
    public static String getString(Map<?, ?> map, String key, boolean nullable) {
        return ConfigSerializationUtil.getObject(String.class, map, key, nullable);
    }

    public static UUID getUuid(Map<?, ?> map, String key, boolean nullable) {
        String uuidString = ConfigSerializationUtil.getString(map, key, nullable);
        if (uuidString == null) {
            return null;
        }
        return UUID.fromString(uuidString);
    }

    public static <T> T getObject(Class<T> clazz, Map<?, ?> map, String key, boolean nullable) {
        Object object = map.get(key);
        if (clazz.isInstance(object)) {
            return clazz.cast(object);
        }
        if (object == null) {
            if (!nullable) {
                throw new NoSuchElementException(map + " does not contain " + key);
            }
            return null;
        }
        throw new IllegalArgumentException(String.valueOf(key) + "(" + object + ") is not a valid " + clazz);
    }

    private ConfigSerializationUtil() {
    }
}

