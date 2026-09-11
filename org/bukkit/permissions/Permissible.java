/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.permissions;

import java.util.Set;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Permissible
extends ServerOperator {
    public boolean isPermissionSet(@NotNull String var1);

    public boolean isPermissionSet(@NotNull Permission var1);

    public boolean hasPermission(@NotNull String var1);

    public boolean hasPermission(@NotNull Permission var1);

    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin var1, @NotNull String var2, boolean var3);

    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin var1);

    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin var1, @NotNull String var2, boolean var3, int var4);

    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin var1, int var2);

    public void removeAttachment(@NotNull PermissionAttachment var1);

    public void recalculatePermissions();

    @NotNull
    public Set<PermissionAttachmentInfo> getEffectivePermissions();
}

