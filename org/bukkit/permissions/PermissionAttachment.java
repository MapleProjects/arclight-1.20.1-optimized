/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.permissions;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionRemovedExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PermissionAttachment {
    private PermissionRemovedExecutor removed;
    private final Map<String, Boolean> permissions = new LinkedHashMap<String, Boolean>();
    private final Permissible permissible;
    private final Plugin plugin;

    public PermissionAttachment(@NotNull Plugin plugin, @NotNull Permissible permissible) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }
        this.permissible = permissible;
        this.plugin = plugin;
    }

    @NotNull
    public Plugin getPlugin() {
        return this.plugin;
    }

    public void setRemovalCallback(@Nullable PermissionRemovedExecutor ex) {
        this.removed = ex;
    }

    @Nullable
    public PermissionRemovedExecutor getRemovalCallback() {
        return this.removed;
    }

    @NotNull
    public Permissible getPermissible() {
        return this.permissible;
    }

    @NotNull
    public Map<String, Boolean> getPermissions() {
        return new LinkedHashMap<String, Boolean>(this.permissions);
    }

    public void setPermission(@NotNull String name, boolean value) {
        this.permissions.put(name.toLowerCase(Locale.ENGLISH), value);
        this.permissible.recalculatePermissions();
    }

    public void setPermission(@NotNull Permission perm, boolean value) {
        this.setPermission(perm.getName(), value);
    }

    public void unsetPermission(@NotNull String name) {
        this.permissions.remove(name.toLowerCase(Locale.ENGLISH));
        this.permissible.recalculatePermissions();
    }

    public void unsetPermission(@NotNull Permission perm) {
        this.unsetPermission(perm.getName());
    }

    public boolean remove() {
        try {
            this.permissible.removeAttachment(this);
            return true;
        }
        catch (IllegalArgumentException ex) {
            return false;
        }
    }
}

