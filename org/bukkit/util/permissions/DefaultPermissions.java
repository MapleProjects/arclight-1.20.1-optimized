/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.util.permissions;

import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.permissions.BroadcastPermissions;
import org.bukkit.util.permissions.CommandPermissions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DefaultPermissions {
    private static final String ROOT = "craftbukkit";
    private static final String LEGACY_PREFIX = "craft";

    private DefaultPermissions() {
    }

    @NotNull
    public static Permission registerPermission(@NotNull Permission perm) {
        return DefaultPermissions.registerPermission(perm, true);
    }

    @NotNull
    public static Permission registerPermission(@NotNull Permission perm, boolean withLegacy) {
        Permission result;
        block3: {
            result = perm;
            try {
                Bukkit.getPluginManager().addPermission(perm);
            }
            catch (IllegalArgumentException ex) {
                result = Bukkit.getPluginManager().getPermission(perm.getName());
                if ($assertionsDisabled || result != null) break block3;
                throw new AssertionError();
            }
        }
        if (withLegacy) {
            Permission legacy = new Permission(LEGACY_PREFIX + result.getName(), result.getDescription(), PermissionDefault.FALSE);
            legacy.getChildren().put(result.getName(), true);
            DefaultPermissions.registerPermission(perm, false);
        }
        return result;
    }

    @NotNull
    public static Permission registerPermission(@NotNull Permission perm, @NotNull Permission parent) {
        parent.getChildren().put(perm.getName(), true);
        return DefaultPermissions.registerPermission(perm);
    }

    @NotNull
    public static Permission registerPermission(@NotNull String name, @Nullable String desc) {
        Permission perm = DefaultPermissions.registerPermission(new Permission(name, desc));
        return perm;
    }

    @NotNull
    public static Permission registerPermission(@NotNull String name, @Nullable String desc, @NotNull Permission parent) {
        Permission perm = DefaultPermissions.registerPermission(name, desc);
        parent.getChildren().put(perm.getName(), true);
        return perm;
    }

    @NotNull
    public static Permission registerPermission(@NotNull String name, @Nullable String desc, @Nullable PermissionDefault def) {
        Permission perm = DefaultPermissions.registerPermission(new Permission(name, desc, def));
        return perm;
    }

    @NotNull
    public static Permission registerPermission(@NotNull String name, @Nullable String desc, @Nullable PermissionDefault def, @NotNull Permission parent) {
        Permission perm = DefaultPermissions.registerPermission(name, desc, def);
        parent.getChildren().put(perm.getName(), true);
        return perm;
    }

    @NotNull
    public static Permission registerPermission(@NotNull String name, @Nullable String desc, @Nullable PermissionDefault def, @Nullable Map<String, Boolean> children) {
        Permission perm = DefaultPermissions.registerPermission(new Permission(name, desc, def, children));
        return perm;
    }

    @NotNull
    public static Permission registerPermission(@NotNull String name, @Nullable String desc, @Nullable PermissionDefault def, @Nullable Map<String, Boolean> children, @NotNull Permission parent) {
        Permission perm = DefaultPermissions.registerPermission(name, desc, def, children);
        parent.getChildren().put(perm.getName(), true);
        return perm;
    }

    public static void registerCorePermissions() {
        Permission parent = DefaultPermissions.registerPermission(ROOT, "Gives the user the ability to use all CraftBukkit utilities and commands");
        CommandPermissions.registerPermissions(parent);
        BroadcastPermissions.registerPermissions(parent);
        parent.recalculatePermissibles();
    }
}

