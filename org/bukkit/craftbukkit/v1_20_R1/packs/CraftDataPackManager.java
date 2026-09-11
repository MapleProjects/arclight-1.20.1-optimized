/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.repository.Pack
 *  net.minecraft.server.packs.repository.PackRepository
 *  net.minecraft.world.entity.EntityType
 */
package org.bukkit.craftbukkit.v1_20_R1.packs;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.entity.EntityType;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.packs.CraftDataPack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.packs.DataPack;
import org.bukkit.packs.DataPackManager;

public class CraftDataPackManager
implements DataPackManager {
    private final PackRepository handle;

    public CraftDataPackManager(PackRepository resourcePackRepository) {
        this.handle = resourcePackRepository;
    }

    public PackRepository getHandle() {
        return this.handle;
    }

    @Override
    public Collection<DataPack> getDataPacks() {
        this.getHandle().m_10506_();
        Collection availablePacks = this.getHandle().m_10519_();
        return availablePacks.stream().map(CraftDataPack::new).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public DataPack getDataPack(NamespacedKey namespacedKey) {
        Preconditions.checkArgument((namespacedKey != null ? 1 : 0) != 0, (Object)"namespacedKey cannot be null");
        return new CraftDataPack(this.getHandle().m_10507_(namespacedKey.getKey()));
    }

    @Override
    public Collection<DataPack> getEnabledDataPacks(World world) {
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"world cannot be null");
        CraftWorld craftWorld = (CraftWorld)world;
        return craftWorld.getHandle().K.m_6645_().f_244096_().m_45850_().stream().map(packName -> {
            Pack resourcePackLoader = this.getHandle().m_10507_(packName);
            if (resourcePackLoader != null) {
                return new CraftDataPack(resourcePackLoader);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Collection<DataPack> getDisabledDataPacks(World world) {
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"world cannot be null");
        CraftWorld craftWorld = (CraftWorld)world;
        return craftWorld.getHandle().K.m_6645_().f_244096_().m_45855_().stream().map(packName -> {
            Pack resourcePackLoader = this.getHandle().m_10507_(packName);
            if (resourcePackLoader != null) {
                return new CraftDataPack(resourcePackLoader);
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean isEnabledByFeature(Material material, World world) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"material cannot be null");
        Preconditions.checkArgument((material.isItem() || material.isBlock() ? 1 : 0) != 0, (Object)"material need to be a item or block");
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"world cannot be null");
        CraftWorld craftWorld = (CraftWorld)world;
        if (material.isItem()) {
            return CraftMagicNumbers.getItem(material).m_245993_(craftWorld.getHandle().m_246046_());
        }
        if (material.isBlock()) {
            return CraftMagicNumbers.getBlock(material).m_245993_(craftWorld.getHandle().m_246046_());
        }
        return false;
    }

    @Override
    public boolean isEnabledByFeature(org.bukkit.entity.EntityType entityType, World world) {
        Preconditions.checkArgument((entityType != null ? 1 : 0) != 0, (Object)"entityType cannot be null");
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"world cannot be null");
        Preconditions.checkArgument((entityType != org.bukkit.entity.EntityType.UNKNOWN ? 1 : 0) != 0, (Object)"EntityType.UNKNOWN its not allowed here");
        CraftWorld craftWorld = (CraftWorld)world;
        EntityType nmsEntity = (EntityType)BuiltInRegistries.f_256780_.m_7745_(new ResourceLocation(entityType.getKey().getKey()));
        return nmsEntity.m_245993_(craftWorld.getHandle().m_246046_());
    }
}

