/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import java.util.Locale;
import java.util.UUID;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class NamespacedKey {
    public static final String MINECRAFT = "minecraft";
    public static final String BUKKIT = "bukkit";
    private final String namespace;
    private final String key;

    private static boolean isValidNamespaceChar(char c) {
        return c >= 'a' && c <= 'z' || c >= '0' && c <= '9' || c == '.' || c == '_' || c == '-';
    }

    private static boolean isValidKeyChar(char c) {
        return NamespacedKey.isValidNamespaceChar(c) || c == '/';
    }

    private static boolean isValidNamespace(String namespace) {
        int len = namespace.length();
        if (len == 0) {
            return false;
        }
        int i = 0;
        while (i < len) {
            if (!NamespacedKey.isValidNamespaceChar(namespace.charAt(i))) {
                return false;
            }
            ++i;
        }
        return true;
    }

    private static boolean isValidKey(String key) {
        int len = key.length();
        if (len == 0) {
            return false;
        }
        int i = 0;
        while (i < len) {
            if (!NamespacedKey.isValidKeyChar(key.charAt(i))) {
                return false;
            }
            ++i;
        }
        return true;
    }

    @Deprecated
    public NamespacedKey(@NotNull String namespace, @NotNull String key) {
        Preconditions.checkArgument((namespace != null && NamespacedKey.isValidNamespace(namespace) ? 1 : 0) != 0, (String)"Invalid namespace. Must be [a-z0-9._-]: %s", (Object)namespace);
        Preconditions.checkArgument((key != null && NamespacedKey.isValidKey(key) ? 1 : 0) != 0, (String)"Invalid key. Must be [a-z0-9/._-]: %s", (Object)key);
        this.namespace = namespace;
        this.key = key;
        String string = this.toString();
        Preconditions.checkArgument((string.length() < 256 ? 1 : 0) != 0, (String)"NamespacedKey must be less than 256 characters", (Object)string);
    }

    public NamespacedKey(@NotNull Plugin plugin, @NotNull String key) {
        Preconditions.checkArgument((plugin != null ? 1 : 0) != 0, (Object)"Plugin cannot be null");
        Preconditions.checkArgument((key != null ? 1 : 0) != 0, (Object)"Key cannot be null");
        this.namespace = plugin.getName().toLowerCase(Locale.ROOT);
        this.key = key.toLowerCase(Locale.ROOT);
        Preconditions.checkArgument((boolean)NamespacedKey.isValidNamespace(this.namespace), (String)"Invalid namespace. Must be [a-z0-9._-]: %s", (Object)this.namespace);
        Preconditions.checkArgument((boolean)NamespacedKey.isValidKey(this.key), (String)"Invalid key. Must be [a-z0-9/._-]: %s", (Object)this.key);
        String string = this.toString();
        Preconditions.checkArgument((string.length() < 256 ? 1 : 0) != 0, (String)"NamespacedKey must be less than 256 characters (%s)", (Object)string);
    }

    @NotNull
    public String getNamespace() {
        return this.namespace;
    }

    @NotNull
    public String getKey() {
        return this.key;
    }

    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.namespace.hashCode();
        hash = 47 * hash + this.key.hashCode();
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        NamespacedKey other = (NamespacedKey)obj;
        return this.namespace.equals(other.namespace) && this.key.equals(other.key);
    }

    public String toString() {
        return String.valueOf(this.namespace) + ":" + this.key;
    }

    @Deprecated
    @NotNull
    public static NamespacedKey randomKey() {
        return new NamespacedKey(BUKKIT, UUID.randomUUID().toString());
    }

    @NotNull
    public static NamespacedKey minecraft(@NotNull String key) {
        return new NamespacedKey(MINECRAFT, key);
    }

    @Nullable
    public static NamespacedKey fromString(@NotNull String string, @Nullable Plugin defaultNamespace) {
        String key;
        Preconditions.checkArgument((string != null && !string.isEmpty() ? 1 : 0) != 0, (Object)"Input string must not be empty or null");
        String[] components = string.split(":", 3);
        if (components.length > 2) {
            return null;
        }
        String string2 = key = components.length == 2 ? components[1] : "";
        if (components.length == 1) {
            String value = components[0];
            if (value.isEmpty() || !NamespacedKey.isValidKey(value)) {
                return null;
            }
            return defaultNamespace != null ? new NamespacedKey(defaultNamespace, value) : NamespacedKey.minecraft(value);
        }
        if (components.length == 2 && !NamespacedKey.isValidKey(key)) {
            return null;
        }
        String namespace = components[0];
        if (namespace.isEmpty()) {
            return defaultNamespace != null ? new NamespacedKey(defaultNamespace, key) : NamespacedKey.minecraft(key);
        }
        if (!NamespacedKey.isValidNamespace(namespace)) {
            return null;
        }
        return new NamespacedKey(namespace, key);
    }

    @Nullable
    public static NamespacedKey fromString(@NotNull String key) {
        return NamespacedKey.fromString(key, null);
    }
}

