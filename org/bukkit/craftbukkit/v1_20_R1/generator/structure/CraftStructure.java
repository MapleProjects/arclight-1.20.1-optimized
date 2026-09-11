/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.world.level.levelgen.structure.Structure
 */
package org.bukkit.craftbukkit.v1_20_R1.generator.structure;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.craftbukkit.v1_20_R1.generator.structure.CraftStructureType;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.generator.structure.Structure;
import org.bukkit.generator.structure.StructureType;

public class CraftStructure
extends Structure {
    private final NamespacedKey key;
    private final net.minecraft.world.level.levelgen.structure.Structure structure;
    private final StructureType structureType;

    public static Structure minecraftToBukkit(net.minecraft.world.level.levelgen.structure.Structure minecraft, RegistryAccess registryHolder) {
        if (minecraft == null) {
            return null;
        }
        return Registry.STRUCTURE.get(CraftNamespacedKey.fromMinecraft(registryHolder.m_175515_(Registries.f_256944_).m_7981_((Object)minecraft)));
    }

    public static net.minecraft.world.level.levelgen.structure.Structure bukkitToMinecraft(Structure bukkit) {
        if (bukkit == null) {
            return null;
        }
        return ((CraftStructure)bukkit).getHandle();
    }

    public CraftStructure(NamespacedKey key, net.minecraft.world.level.levelgen.structure.Structure structure) {
        this.key = key;
        this.structure = structure;
        this.structureType = CraftStructureType.minecraftToBukkit(structure.m_213658_());
    }

    public net.minecraft.world.level.levelgen.structure.Structure getHandle() {
        return this.structure;
    }

    @Override
    public StructureType getStructureType() {
        return this.structureType;
    }

    @Override
    public NamespacedKey getKey() {
        return this.key;
    }
}

