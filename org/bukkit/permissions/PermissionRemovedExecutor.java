/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.permissions;

import org.bukkit.permissions.PermissionAttachment;
import org.jetbrains.annotations.NotNull;

public interface PermissionRemovedExecutor {
    public void attachmentRemoved(@NotNull PermissionAttachment var1);
}

