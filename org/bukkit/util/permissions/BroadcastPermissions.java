/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util.permissions;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.util.permissions.DefaultPermissions;
import org.jetbrains.annotations.NotNull;

public final class BroadcastPermissions {
    private static final String ROOT = "bukkit.broadcast";
    private static final String PREFIX = "bukkit.broadcast.";

    private BroadcastPermissions() {
    }

    @NotNull
    public static Permission registerPermissions(@NotNull Permission parent) {
        Permission broadcasts = DefaultPermissions.registerPermission(ROOT, "Allows the user to receive all broadcast messages", parent);
        DefaultPermissions.registerPermission("bukkit.broadcast.admin", "Allows the user to receive administrative broadcasts", PermissionDefault.OP, broadcasts);
        DefaultPermissions.registerPermission("bukkit.broadcast.user", "Allows the user to receive user broadcasts", PermissionDefault.TRUE, broadcasts);
        broadcasts.recalculatePermissibles();
        return broadcasts;
    }
}

