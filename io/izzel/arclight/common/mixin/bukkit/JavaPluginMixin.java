/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.mod.util.log.ArclightPluginLogger;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={JavaPlugin.class})
public class JavaPluginMixin {
    @Redirect(method={"init"}, remap=false, at=@At(value="NEW", target="org/bukkit/plugin/PluginLogger"))
    private PluginLogger arclight$createLogger(Plugin plugin) {
        return new ArclightPluginLogger(plugin);
    }
}

