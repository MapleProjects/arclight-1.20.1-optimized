/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.world.item.Instrument
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Instrument;
import org.bukkit.MusicInstrument;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.v1_20_R1.CraftRegistry;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.jetbrains.annotations.NotNull;

public class CraftMusicInstrument
extends MusicInstrument {
    private final NamespacedKey key;
    private final Instrument handle;

    public static MusicInstrument minecraftToBukkit(Instrument minecraft) {
        Preconditions.checkArgument((minecraft != null ? 1 : 0) != 0);
        net.minecraft.core.Registry registry = CraftRegistry.getMinecraftRegistry(Registries.f_257010_);
        MusicInstrument bukkit = Registry.INSTRUMENT.get(CraftNamespacedKey.fromMinecraft(registry.m_7981_((Object)minecraft)));
        Preconditions.checkArgument((bukkit != null ? 1 : 0) != 0);
        return bukkit;
    }

    public static Instrument bukkitToMinecraft(MusicInstrument bukkit) {
        Preconditions.checkArgument((bukkit != null ? 1 : 0) != 0);
        return ((CraftMusicInstrument)bukkit).getHandle();
    }

    public CraftMusicInstrument(NamespacedKey key, Instrument handle) {
        this.key = key;
        this.handle = handle;
    }

    public Instrument getHandle() {
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
        if (!(other instanceof CraftMusicInstrument)) {
            return false;
        }
        return this.getKey().equals(((MusicInstrument)other).getKey());
    }

    public int hashCode() {
        return this.getKey().hashCode();
    }

    public String toString() {
        return "CraftMusicInstrument{key=" + this.key + "}";
    }
}

