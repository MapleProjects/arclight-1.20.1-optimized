/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.levelgen.structure.templatesystem.BlockRotProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate$StructureEntityInfo
 */
package org.bukkit.craftbukkit.v1_20_R1.structure;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockRotProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.RegionAccessor;
import org.bukkit.World;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.craftbukkit.v1_20_R1.CraftRegionAccessor;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.structure.CraftPalette;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftBlockVector;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.RandomSourceWrapper;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.structure.Palette;
import org.bukkit.structure.Structure;
import org.bukkit.util.BlockVector;

public class CraftStructure
implements Structure {
    private final StructureTemplate structure;

    public CraftStructure(StructureTemplate structure) {
        this.structure = structure;
    }

    @Override
    public void place(Location location, boolean includeEntities, StructureRotation structureRotation, Mirror mirror, int palette, float integrity, Random random) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        location.checkFinite();
        World world = location.getWorld();
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"The World of Location cannot be null");
        BlockVector blockVector = new BlockVector(location.getBlockX(), location.getBlockY(), location.getBlockZ());
        this.place(world, blockVector, includeEntities, structureRotation, mirror, palette, integrity, random);
    }

    @Override
    public void place(RegionAccessor regionAccessor, BlockVector location, boolean includeEntities, StructureRotation structureRotation, Mirror mirror, int palette, float integrity, Random random) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"Location cannot be null");
        Preconditions.checkArgument((regionAccessor != null ? 1 : 0) != 0, (Object)"RegionAccessor cannot be null");
        location.checkFinite();
        Preconditions.checkArgument((integrity >= 0.0f && integrity <= 1.0f ? 1 : 0) != 0, (String)"Integrity value (%S) must be between 0 and 1 inclusive", (Object)Float.valueOf(integrity));
        RandomSourceWrapper randomSource = new RandomSourceWrapper(random);
        StructurePlaceSettings definedstructureinfo = new StructurePlaceSettings().m_74377_(net.minecraft.world.level.block.Mirror.valueOf((String)mirror.name())).m_74379_(Rotation.valueOf((String)structureRotation.name())).m_74392_(!includeEntities).m_74383_((StructureProcessor)new BlockRotProcessor(integrity)).m_230324_((RandomSource)randomSource);
        definedstructureinfo.f_74369_ = palette;
        BlockPos blockPosition = CraftBlockVector.toBlockPosition(location);
        this.structure.m_230328_((ServerLevelAccessor)((CraftRegionAccessor)regionAccessor).getHandle(), blockPosition, blockPosition, definedstructureinfo, (RandomSource)randomSource, 2);
    }

    @Override
    public void fill(Location corner1, Location corner2, boolean includeEntities) {
        Preconditions.checkArgument((corner1 != null ? 1 : 0) != 0, (Object)"Location corner1 cannot be null");
        Preconditions.checkArgument((corner2 != null ? 1 : 0) != 0, (Object)"Location corner2 cannot be null");
        World world = corner1.getWorld();
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"World of corner1 Location cannot be null");
        Location origin = new Location(world, Math.min(corner1.getBlockX(), corner2.getBlockX()), Math.min(corner1.getBlockY(), corner2.getBlockY()), Math.min(corner1.getBlockZ(), corner2.getBlockZ()));
        BlockVector size = new BlockVector(Math.abs(corner1.getBlockX() - corner2.getBlockX()), Math.abs(corner1.getBlockY() - corner2.getBlockY()), Math.abs(corner1.getBlockZ() - corner2.getBlockZ()));
        this.fill(origin, size, includeEntities);
    }

    @Override
    public void fill(Location origin, BlockVector size, boolean includeEntities) {
        Preconditions.checkArgument((origin != null ? 1 : 0) != 0, (Object)"Location origin cannot be null");
        World world = origin.getWorld();
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"World of Location origin cannot be null");
        Preconditions.checkArgument((size != null ? 1 : 0) != 0, (Object)"BlockVector size cannot be null");
        Preconditions.checkArgument((size.getBlockX() >= 1 && size.getBlockY() >= 1 && size.getBlockZ() >= 1 ? 1 : 0) != 0, (String)"Size must be at least 1x1x1 but was %sx%sx%s", (Object)size.getBlockX(), (Object)size.getBlockY(), (Object)size.getBlockZ());
        this.structure.m_163802_((Level)((CraftWorld)world).getHandle(), CraftLocation.toBlockPosition(origin), (Vec3i)CraftBlockVector.toBlockPosition(size), includeEntities, Blocks.f_50454_);
    }

    @Override
    public BlockVector getSize() {
        return CraftBlockVector.toBukkit(this.structure.m_163801_());
    }

    @Override
    public List<Entity> getEntities() {
        ArrayList entities = new ArrayList();
        for (StructureTemplate.StructureEntityInfo entity : this.structure.f_74483_) {
            EntityType.m_20642_((CompoundTag)entity.f_74685_, (Level)((CraftWorld)Bukkit.getServer().getWorlds().get(0)).getHandle()).ifPresent(dummyEntity -> {
                dummyEntity.m_6034_(structureEntityInfo.f_74683_.f_82479_, structureEntityInfo.f_74683_.f_82480_, structureEntityInfo.f_74683_.f_82481_);
                entities.add(dummyEntity.getBukkitEntity());
            });
        }
        return Collections.unmodifiableList(entities);
    }

    @Override
    public int getEntityCount() {
        return this.structure.f_74483_.size();
    }

    @Override
    public List<Palette> getPalettes() {
        return this.structure.f_74482_.stream().map(CraftPalette::new).collect(Collectors.toList());
    }

    @Override
    public int getPaletteCount() {
        return this.structure.f_74482_.size();
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return this.getHandle().persistentDataContainer;
    }

    public StructureTemplate getHandle() {
        return this.structure;
    }
}

