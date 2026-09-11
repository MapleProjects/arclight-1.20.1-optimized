/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.permissions;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Permission {
    public static final PermissionDefault DEFAULT_PERMISSION = PermissionDefault.OP;
    private final String name;
    private final Map<String, Boolean> children = new LinkedHashMap<String, Boolean>();
    private PermissionDefault defaultValue = DEFAULT_PERMISSION;
    private String description;

    public Permission(@NotNull String name) {
        this(name, null, null, null);
    }

    public Permission(@NotNull String name, @Nullable String description) {
        this(name, description, null, null);
    }

    public Permission(@NotNull String name, @Nullable PermissionDefault defaultValue) {
        this(name, null, defaultValue, null);
    }

    public Permission(@NotNull String name, @Nullable String description, @Nullable PermissionDefault defaultValue) {
        this(name, description, defaultValue, null);
    }

    public Permission(@NotNull String name, @Nullable Map<String, Boolean> children) {
        this(name, null, null, children);
    }

    public Permission(@NotNull String name, @Nullable String description, @Nullable Map<String, Boolean> children) {
        this(name, description, null, children);
    }

    public Permission(@NotNull String name, @Nullable PermissionDefault defaultValue, @Nullable Map<String, Boolean> children) {
        this(name, null, defaultValue, children);
    }

    public Permission(@NotNull String name, @Nullable String description, @Nullable PermissionDefault defaultValue, @Nullable Map<String, Boolean> children) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Name cannot be null");
        this.name = name;
        String string = this.description = description == null ? "" : description;
        if (defaultValue != null) {
            this.defaultValue = defaultValue;
        }
        if (children != null) {
            this.children.putAll(children);
        }
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public Map<String, Boolean> getChildren() {
        return this.children;
    }

    @NotNull
    public PermissionDefault getDefault() {
        return this.defaultValue;
    }

    public void setDefault(@NotNull PermissionDefault value) {
        if (this.defaultValue == null) {
            throw new IllegalArgumentException("Default value cannot be null");
        }
        this.defaultValue = value;
        this.recalculatePermissibles();
    }

    @NotNull
    public String getDescription() {
        return this.description;
    }

    public void setDescription(@Nullable String value) {
        this.description = value == null ? "" : value;
    }

    @NotNull
    public Set<Permissible> getPermissibles() {
        return Bukkit.getServer().getPluginManager().getPermissionSubscriptions(this.name);
    }

    public void recalculatePermissibles() {
        Set<Permissible> perms = this.getPermissibles();
        Bukkit.getServer().getPluginManager().recalculatePermissionDefaults(this);
        for (Permissible p : perms) {
            p.recalculatePermissions();
        }
    }

    @NotNull
    public Permission addParent(@NotNull String name, boolean value) {
        String lname;
        PluginManager pm = Bukkit.getServer().getPluginManager();
        Permission perm = pm.getPermission(lname = name.toLowerCase(Locale.ENGLISH));
        if (perm == null) {
            perm = new Permission(lname);
            pm.addPermission(perm);
        }
        this.addParent(perm, value);
        return perm;
    }

    public void addParent(@NotNull Permission perm, boolean value) {
        perm.getChildren().put(this.getName(), value);
        perm.recalculatePermissibles();
    }

    @NotNull
    public static List<Permission> loadPermissions(@NotNull Map<?, ?> data, @NotNull String error, @Nullable PermissionDefault def) {
        ArrayList<Permission> result = new ArrayList<Permission>();
        for (Map.Entry<?, ?> entry : data.entrySet()) {
            try {
                result.add(Permission.loadPermission(entry.getKey().toString(), (Map)entry.getValue(), def, result));
            }
            catch (Throwable ex) {
                Bukkit.getServer().getLogger().log(Level.SEVERE, String.format(error, entry.getKey()), ex);
            }
        }
        return result;
    }

    @NotNull
    public static Permission loadPermission(@NotNull String name, @NotNull Map<String, Object> data) {
        return Permission.loadPermission(name, data, DEFAULT_PERMISSION, null);
    }

    @NotNull
    public static Permission loadPermission(@NotNull String name, @NotNull Map<?, ?> data, @Nullable PermissionDefault def, @Nullable List<Permission> output) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Name cannot be null");
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"Data cannot be null");
        String desc = null;
        Map<String, Boolean> children = null;
        if (data.get("default") != null) {
            PermissionDefault value = PermissionDefault.getByName(data.get("default").toString());
            if (value != null) {
                def = value;
            } else {
                throw new IllegalArgumentException("'default' key contained unknown value");
            }
        }
        if (data.get("children") != null) {
            Object childrenNode = data.get("children");
            if (childrenNode instanceof Iterable) {
                children = new LinkedHashMap<String, Boolean>();
                for (Object child : (Iterable)childrenNode) {
                    if (child == null) continue;
                    children.put(child.toString(), Boolean.TRUE);
                }
            } else if (childrenNode instanceof Map) {
                children = Permission.extractChildren((Map)childrenNode, name, def, output);
            } else {
                throw new IllegalArgumentException("'children' key is of wrong type");
            }
        }
        if (data.get("description") != null) {
            desc = data.get("description").toString();
        }
        return new Permission(name, desc, def, children);
    }

    @NotNull
    private static Map<String, Boolean> extractChildren(@NotNull Map<?, ?> input, @NotNull String name, @Nullable PermissionDefault def, @Nullable List<Permission> output) {
        LinkedHashMap<String, Boolean> children = new LinkedHashMap<String, Boolean>();
        for (Map.Entry<?, ?> entry : input.entrySet()) {
            if (entry.getValue() instanceof Boolean) {
                children.put(entry.getKey().toString(), (Boolean)entry.getValue());
                continue;
            }
            if (entry.getValue() instanceof Map) {
                try {
                    Permission perm = Permission.loadPermission(entry.getKey().toString(), (Map)entry.getValue(), def, output);
                    children.put(perm.getName(), Boolean.TRUE);
                    if (output == null) continue;
                    output.add(perm);
                    continue;
                }
                catch (Throwable ex) {
                    throw new IllegalArgumentException("Permission node '" + entry.getKey().toString() + "' in child of " + name + " is invalid", ex);
                }
            }
            throw new IllegalArgumentException("Child '" + entry.getKey().toString() + "' contains invalid value");
        }
        return children;
    }
}

