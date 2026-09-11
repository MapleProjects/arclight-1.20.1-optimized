/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.bossevents.CustomBossEvent
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.server;

import io.izzel.arclight.common.bridge.core.server.CustomServerBossInfoBridge;
import net.minecraft.server.bossevents.CustomBossEvent;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.craftbukkit.v1_20_R1.boss.CraftKeyedBossbar;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={CustomBossEvent.class})
public class CustomServerBossInfoMixin
implements CustomServerBossInfoBridge {
    private KeyedBossBar bossBar;

    public KeyedBossBar getBukkitEntity() {
        if (this.bossBar == null) {
            this.bossBar = new CraftKeyedBossbar((CustomBossEvent)this);
        }
        return this.bossBar;
    }

    @Override
    public KeyedBossBar bridge$getBukkitEntity() {
        return this.getBukkitEntity();
    }
}

