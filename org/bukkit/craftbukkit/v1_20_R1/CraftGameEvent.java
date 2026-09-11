/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.world.level.gameevent.GameEvent
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import net.minecraft.core.registries.Registries;
import org.bukkit.GameEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.v1_20_R1.CraftRegistry;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.jetbrains.annotations.NotNull;

public class CraftGameEvent
extends GameEvent {
    private final NamespacedKey key;
    private final net.minecraft.world.level.gameevent.GameEvent handle;

    public static GameEvent minecraftToBukkit(net.minecraft.world.level.gameevent.GameEvent minecraft) {
        Preconditions.checkArgument((minecraft != null ? 1 : 0) != 0);
        net.minecraft.core.Registry registry = CraftRegistry.getMinecraftRegistry(Registries.f_256827_);
        GameEvent bukkit = Registry.GAME_EVENT.get(CraftNamespacedKey.fromMinecraft(registry.m_7981_((Object)minecraft)));
        Preconditions.checkArgument((bukkit != null ? 1 : 0) != 0);
        return bukkit;
    }

    public static net.minecraft.world.level.gameevent.GameEvent bukkitToMinecraft(GameEvent bukkit) {
        Preconditions.checkArgument((bukkit != null ? 1 : 0) != 0);
        return ((CraftGameEvent)bukkit).getHandle();
    }

    public CraftGameEvent(NamespacedKey key, net.minecraft.world.level.gameevent.GameEvent handle) {
        this.key = key;
        this.handle = handle;
    }

    public net.minecraft.world.level.gameevent.GameEvent getHandle() {
        return this.handle;
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CraftGameEvent)) {
            return false;
        }
        return this.getKey().equals(((GameEvent)other).getKey());
    }

    public int hashCode() {
        return this.getKey().hashCode();
    }

    public String toString() {
        return "CraftGameEvent{key=" + this.key + "}";
    }
}

