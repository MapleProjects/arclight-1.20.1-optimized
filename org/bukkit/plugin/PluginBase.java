/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.plugin;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public abstract class PluginBase
implements Plugin {
    public final int hashCode() {
        return this.getName().hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Plugin)) {
            return false;
        }
        return this.getName().equals(((Plugin)obj).getName());
    }

    @Override
    @NotNull
    public final String getName() {
        return this.getDescription().getName();
    }
}

