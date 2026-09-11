/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.configuration.serialization;

import com.google.common.base.Preconditions;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.banner.Pattern;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConfigurationSerialization {
    public static final String SERIALIZED_TYPE_KEY = "==";
    private final Class<? extends ConfigurationSerializable> clazz;
    private static Map<String, Class<? extends ConfigurationSerializable>> aliases = new HashMap<String, Class<? extends ConfigurationSerializable>>();

    static {
        ConfigurationSerialization.registerClass(Vector.class);
        ConfigurationSerialization.registerClass(BlockVector.class);
        ConfigurationSerialization.registerClass(ItemStack.class);
        ConfigurationSerialization.registerClass(Color.class);
        ConfigurationSerialization.registerClass(PotionEffect.class);
        ConfigurationSerialization.registerClass(FireworkEffect.class);
        ConfigurationSerialization.registerClass(Pattern.class);
        ConfigurationSerialization.registerClass(Location.class);
        ConfigurationSerialization.registerClass(AttributeModifier.class);
        ConfigurationSerialization.registerClass(BoundingBox.class);
    }

    protected ConfigurationSerialization(@NotNull Class<? extends ConfigurationSerializable> clazz) {
        this.clazz = clazz;
    }

    @Nullable
    protected Method getMethod(@NotNull String name, boolean isStatic) {
        Method method;
        block6: {
            block5: {
                method = this.clazz.getDeclaredMethod(name, Map.class);
                if (ConfigurationSerializable.class.isAssignableFrom(method.getReturnType())) break block5;
                return null;
            }
            if (Modifier.isStatic(method.getModifiers()) == isStatic) break block6;
            return null;
        }
        try {
            return method;
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
        catch (SecurityException ex) {
            return null;
        }
    }

    @Nullable
    protected Constructor<? extends ConfigurationSerializable> getConstructor() {
        try {
            return this.clazz.getConstructor(Map.class);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
        catch (SecurityException ex) {
            return null;
        }
    }

    @Nullable
    protected ConfigurationSerializable deserializeViaMethod(@NotNull Method method, @NotNull Map<String, ?> args) {
        try {
            ConfigurationSerializable result = (ConfigurationSerializable)method.invoke(null, args);
            if (result != null) {
                return result;
            }
            Logger.getLogger(ConfigurationSerialization.class.getName()).log(Level.SEVERE, "Could not call method '" + method.toString() + "' of " + this.clazz + " for deserialization: method returned null");
        }
        catch (Throwable ex) {
            Logger.getLogger(ConfigurationSerialization.class.getName()).log(Level.SEVERE, "Could not call method '" + method.toString() + "' of " + this.clazz + " for deserialization", ex instanceof InvocationTargetException ? ex.getCause() : ex);
        }
        return null;
    }

    @Nullable
    protected ConfigurationSerializable deserializeViaCtor(@NotNull Constructor<? extends ConfigurationSerializable> ctor, @NotNull Map<String, ?> args) {
        try {
            return ctor.newInstance(args);
        }
        catch (Throwable ex) {
            Logger.getLogger(ConfigurationSerialization.class.getName()).log(Level.SEVERE, "Could not call constructor '" + ctor.toString() + "' of " + this.clazz + " for deserialization", ex instanceof InvocationTargetException ? ex.getCause() : ex);
            return null;
        }
    }

    @Nullable
    public ConfigurationSerializable deserialize(@NotNull Map<String, ?> args) {
        Constructor<? extends ConfigurationSerializable> constructor;
        Preconditions.checkArgument((args != null ? 1 : 0) != 0, (Object)"Args must not be null");
        ConfigurationSerializable result = null;
        Method method = null;
        if (result == null && (method = this.getMethod("deserialize", true)) != null) {
            result = this.deserializeViaMethod(method, args);
        }
        if (result == null && (method = this.getMethod("valueOf", true)) != null) {
            result = this.deserializeViaMethod(method, args);
        }
        if (result == null && (constructor = this.getConstructor()) != null) {
            result = this.deserializeViaCtor(constructor, args);
        }
        return result;
    }

    @Nullable
    public static ConfigurationSerializable deserializeObject(@NotNull Map<String, ?> args, @NotNull Class<? extends ConfigurationSerializable> clazz) {
        return new ConfigurationSerialization(clazz).deserialize(args);
    }

    @Nullable
    public static ConfigurationSerializable deserializeObject(@NotNull Map<String, ?> args) {
        Class<? extends ConfigurationSerializable> clazz;
        block5: {
            clazz = null;
            if (args.containsKey(SERIALIZED_TYPE_KEY)) {
                try {
                    String alias = (String)args.get(SERIALIZED_TYPE_KEY);
                    if (alias == null) {
                        throw new IllegalArgumentException("Cannot have null alias");
                    }
                    clazz = ConfigurationSerialization.getClassByAlias(alias);
                    if (clazz == null) {
                        throw new IllegalArgumentException("Specified class does not exist ('" + alias + "')");
                    }
                    break block5;
                }
                catch (ClassCastException ex) {
                    ex.fillInStackTrace();
                    throw ex;
                }
            }
            throw new IllegalArgumentException("Args doesn't contain type key ('==')");
        }
        return new ConfigurationSerialization(clazz).deserialize(args);
    }

    public static void registerClass(@NotNull Class<? extends ConfigurationSerializable> clazz) {
        DelegateDeserialization delegate = clazz.getAnnotation(DelegateDeserialization.class);
        if (delegate == null) {
            ConfigurationSerialization.registerClass(clazz, ConfigurationSerialization.getAlias(clazz));
            ConfigurationSerialization.registerClass(clazz, clazz.getName());
        }
    }

    public static void registerClass(@NotNull Class<? extends ConfigurationSerializable> clazz, @NotNull String alias) {
        aliases.put(alias, clazz);
    }

    public static void unregisterClass(@NotNull String alias) {
        aliases.remove(alias);
    }

    public static void unregisterClass(@NotNull Class<? extends ConfigurationSerializable> clazz) {
        while (aliases.values().remove(clazz)) {
        }
    }

    @Nullable
    public static Class<? extends ConfigurationSerializable> getClassByAlias(@NotNull String alias) {
        return aliases.get(alias);
    }

    @NotNull
    public static String getAlias(@NotNull Class<? extends ConfigurationSerializable> clazz) {
        SerializableAs alias;
        DelegateDeserialization delegate = clazz.getAnnotation(DelegateDeserialization.class);
        if (delegate != null) {
            if (delegate.value() == null || delegate.value() == clazz) {
                delegate = null;
            } else {
                return ConfigurationSerialization.getAlias(delegate.value());
            }
        }
        if (delegate == null && (alias = clazz.getAnnotation(SerializableAs.class)) != null && alias.value() != null) {
            return alias.value();
        }
        return clazz.getName();
    }
}

