/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.level.saveddata.SavedData
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.server.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.jetbrains.annotations.NotNull;

public class LevelPersistentData
extends SavedData {
    private CompoundTag tag;

    public LevelPersistentData(CompoundTag tag) {
        this.tag = tag == null ? new CompoundTag() : tag;
    }

    public CompoundTag getTag() {
        return this.tag;
    }

    public void save(CraftWorld world) {
        this.tag = new CompoundTag();
        world.storeBukkitValues(this.tag);
    }

    @NotNull
    public CompoundTag m_7176_(@NotNull CompoundTag it) {
        return this.tag;
    }
}

