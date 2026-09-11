/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.permissions;

import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PermissionAttachmentInfo {
    private final Permissible permissible;
    private final String permission;
    private final PermissionAttachment attachment;
    private final boolean value;

    public PermissionAttachmentInfo(@NotNull Permissible permissible, @NotNull String permission, @Nullable PermissionAttachment attachment, boolean value) {
        if (permissible == null) {
            throw new IllegalArgumentException("Permissible may not be null");
        }
        if (permission == null) {
            throw new IllegalArgumentException("Permission may not be null");
        }
        this.permissible = permissible;
        this.permission = permission;
        this.attachment = attachment;
        this.value = value;
    }

    @NotNull
    public Permissible getPermissible() {
        return this.permissible;
    }

    @NotNull
    public String getPermission() {
        return this.permission;
    }

    @Nullable
    public PermissionAttachment getAttachment() {
        return this.attachment;
    }

    public boolean getValue() {
        return this.value;
    }
}

