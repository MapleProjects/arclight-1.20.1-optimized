/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.bukkit;

import org.spigotmc.WatchdogThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={WatchdogThread.class}, remap=false)
public class WatchdogThreadMixin {
    @Overwrite
    public static void doStart(int timeoutTime, boolean restart) {
    }

    @Overwrite
    public static void tick() {
    }
}

