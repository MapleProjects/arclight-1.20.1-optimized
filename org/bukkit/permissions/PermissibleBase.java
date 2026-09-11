/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.permissions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.permissions.PermissionRemovedExecutor;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PermissibleBase
implements Permissible {
    private final ServerOperator opable;
    private final Permissible parent;
    private final List<PermissionAttachment> attachments = new LinkedList<PermissionAttachment>();
    private final Map<String, PermissionAttachmentInfo> permissions = new HashMap<String, PermissionAttachmentInfo>();

    public PermissibleBase(@Nullable ServerOperator opable) {
        this.opable = opable;
        this.parent = opable instanceof Permissible ? (Permissible)opable : this;
        this.recalculatePermissions();
    }

    @Override
    public boolean isOp() {
        if (this.opable == null) {
            return false;
        }
        return this.opable.isOp();
    }

    @Override
    public void setOp(boolean value) {
        if (this.opable == null) {
            throw new UnsupportedOperationException("Cannot change op value as no ServerOperator is set");
        }
        this.opable.setOp(value);
    }

    @Override
    public boolean isPermissionSet(@NotNull String name) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        }
        return this.permissions.containsKey(name.toLowerCase(Locale.ENGLISH));
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm) {
        if (perm == null) {
            throw new IllegalArgumentException("Permission cannot be null");
        }
        return this.isPermissionSet(perm.getName());
    }

    @Override
    public boolean hasPermission(@NotNull String inName) {
        if (inName == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        }
        String name = inName.toLowerCase(Locale.ENGLISH);
        if (this.isPermissionSet(name)) {
            return this.permissions.get(name).getValue();
        }
        Permission perm = Bukkit.getServer().getPluginManager().getPermission(name);
        if (perm != null) {
            return perm.getDefault().getValue(this.isOp());
        }
        return Permission.DEFAULT_PERMISSION.getValue(this.isOp());
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        if (perm == null) {
            throw new IllegalArgumentException("Permission cannot be null");
        }
        String name = perm.getName().toLowerCase(Locale.ENGLISH);
        if (this.isPermissionSet(name)) {
            return this.permissions.get(name).getValue();
        }
        return perm.getDefault().getValue(this.isOp());
    }

    @Override
    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        }
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }
        PermissionAttachment result = this.addAttachment(plugin);
        result.setPermission(name, value);
        this.recalculatePermissions();
        return result;
    }

    @Override
    @NotNull
    public PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }
        PermissionAttachment result = new PermissionAttachment(plugin, this.parent);
        this.attachments.add(result);
        this.recalculatePermissions();
        return result;
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {
        if (attachment == null) {
            throw new IllegalArgumentException("Attachment cannot be null");
        }
        if (this.attachments.remove(attachment)) {
            PermissionRemovedExecutor ex = attachment.getRemovalCallback();
            if (ex != null) {
                ex.attachmentRemoved(attachment);
            }
        } else {
            throw new IllegalArgumentException("Given attachment is not part of Permissible object " + this.parent);
        }
        this.recalculatePermissions();
    }

    @Override
    public void recalculatePermissions() {
        this.clearPermissions();
        Set<Permission> defaults = Bukkit.getServer().getPluginManager().getDefaultPermissions(this.isOp());
        Bukkit.getServer().getPluginManager().subscribeToDefaultPerms(this.isOp(), this.parent);
        for (Permission perm : defaults) {
            String name = perm.getName().toLowerCase(Locale.ENGLISH);
            this.permissions.put(name, new PermissionAttachmentInfo(this.parent, name, null, true));
            Bukkit.getServer().getPluginManager().subscribeToPermission(name, this.parent);
            this.calculateChildPermissions(perm.getChildren(), false, null);
        }
        for (PermissionAttachment attachment : this.attachments) {
            this.calculateChildPermissions(attachment.getPermissions(), false, attachment);
        }
    }

    public synchronized void clearPermissions() {
        Set<String> perms = this.permissions.keySet();
        for (String name : perms) {
            Bukkit.getServer().getPluginManager().unsubscribeFromPermission(name, this.parent);
        }
        Bukkit.getServer().getPluginManager().unsubscribeFromDefaultPerms(false, this.parent);
        Bukkit.getServer().getPluginManager().unsubscribeFromDefaultPerms(true, this.parent);
        this.permissions.clear();
    }

    private void calculateChildPermissions(@NotNull Map<String, Boolean> children, boolean invert, @Nullable PermissionAttachment attachment) {
        for (Map.Entry<String, Boolean> entry : children.entrySet()) {
            String name = entry.getKey();
            Permission perm = Bukkit.getServer().getPluginManager().getPermission(name);
            boolean value = entry.getValue() ^ invert;
            String lname = name.toLowerCase(Locale.ENGLISH);
            this.permissions.put(lname, new PermissionAttachmentInfo(this.parent, lname, attachment, value));
            Bukkit.getServer().getPluginManager().subscribeToPermission(name, this.parent);
            if (perm == null) continue;
            this.calculateChildPermissions(perm.getChildren(), !value, attachment);
        }
    }

    @Override
    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        if (name == null) {
            throw new IllegalArgumentException("Permission name cannot be null");
        }
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }
        PermissionAttachment result = this.addAttachment(plugin, ticks);
        if (result != null) {
            result.setPermission(name, value);
        }
        return result;
    }

    @Override
    @Nullable
    public PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        if (!plugin.isEnabled()) {
            throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
        }
        PermissionAttachment result = this.addAttachment(plugin);
        if (Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new RemoveAttachmentRunnable(result), (long)ticks) == -1) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "Could not add PermissionAttachment to " + this.parent + " for plugin " + plugin.getDescription().getFullName() + ": Scheduler returned -1");
            result.remove();
            return null;
        }
        return result;
    }

    @Override
    @NotNull
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return new HashSet<PermissionAttachmentInfo>(this.permissions.values());
    }

    private static class RemoveAttachmentRunnable
    implements Runnable {
        private final PermissionAttachment attachment;

        public RemoveAttachmentRunnable(@NotNull PermissionAttachment attachment) {
            this.attachment = attachment;
        }

        @Override
        public void run() {
            this.attachment.remove();
        }
    }
}

