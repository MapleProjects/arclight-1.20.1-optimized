/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.world.level.levelgen.structure.StructureType
 */
package org.bukkit.craftbukkit.v1_20_R1.generator.structure;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;

public class CraftStructureType
extends org.bukkit.generator.structure.StructureType {
    private final NamespacedKey key;
    private final StructureType<?> structureType;

    public static org.bukkit.generator.structure.StructureType minecraftToBukkit(StructureType<?> minecraft) {
        if (minecraft == null) {
            return null;
        }
        return Registry.STRUCTURE_TYPE.get(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.f_256763_.m_7981_(minecraft)));
    }

    public static StructureType<?> bukkitToMinecraft(org.bukkit.generator.structure.StructureType bukkit) {
        if (bukkit == null) {
            return null;
        }
        return ((CraftStructureType)bukkit).getHandle();
    }

    public CraftStructureType(NamespacedKey key, StructureType<?> structureType) {
        this.key = key;
        this.structureType = structureType;
    }

    public StructureType<?> getHandle() {
        return this.structureType;
    }

    @Override
    public NamespacedKey getKey() {
        return this.key;
    }
}

