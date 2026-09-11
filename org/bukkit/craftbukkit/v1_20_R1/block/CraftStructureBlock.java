/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.Vec3i
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.StructureBlock
 *  net.minecraft.world.level.block.entity.StructureBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.level.block.state.properties.StructureMode
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.StructureBlock;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.StructureMode;
import org.bukkit.World;
import org.bukkit.block.Structure;
import org.bukkit.block.structure.Mirror;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.block.structure.UsageMode;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftBlockVector;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BlockVector;

public class CraftStructureBlock
extends CraftBlockEntityState<StructureBlockEntity>
implements Structure {
    private static final int MAX_SIZE = 48;

    public CraftStructureBlock(World world, StructureBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public String getStructureName() {
        return ((StructureBlockEntity)this.getSnapshot()).m_59895_();
    }

    @Override
    public void setStructureName(String name) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Structure name cannot be null");
        ((StructureBlockEntity)this.getSnapshot()).m_59868_(name);
    }

    @Override
    public String getAuthor() {
        return ((StructureBlockEntity)this.getSnapshot()).f_59813_;
    }

    @Override
    public void setAuthor(String author) {
        Preconditions.checkArgument((author != null ? 1 : 0) != 0, (Object)"Author name cannot be null");
        Preconditions.checkArgument((!author.isEmpty() ? 1 : 0) != 0, (Object)"Author name cannot be empty");
        ((StructureBlockEntity)this.getSnapshot()).f_59813_ = author;
    }

    @Override
    public void setAuthor(LivingEntity entity) {
        Preconditions.checkArgument((entity != null ? 1 : 0) != 0, (Object)"Structure Block author entity cannot be null");
        ((StructureBlockEntity)this.getSnapshot()).m_59851_(((CraftLivingEntity)entity).getHandle());
    }

    @Override
    public BlockVector getRelativePosition() {
        return CraftBlockVector.toBukkit((Vec3i)((StructureBlockEntity)this.getSnapshot()).f_59815_);
    }

    @Override
    public void setRelativePosition(BlockVector vector) {
        Preconditions.checkArgument((boolean)CraftStructureBlock.isBetween(vector.getBlockX(), -48, 48), (String)"Structure Size (X) must be between -%s and %s but got %s", (Object)48, (Object)48, (Object)vector.getBlockX());
        Preconditions.checkArgument((boolean)CraftStructureBlock.isBetween(vector.getBlockY(), -48, 48), (String)"Structure Size (Y) must be between -%s and %s but got %s", (Object)48, (Object)48, (Object)vector.getBlockY());
        Preconditions.checkArgument((boolean)CraftStructureBlock.isBetween(vector.getBlockZ(), -48, 48), (String)"Structure Size (Z) must be between -%s and %s but got %s", (Object)48, (Object)48, (Object)vector.getBlockZ());
        ((StructureBlockEntity)this.getSnapshot()).f_59815_ = CraftBlockVector.toBlockPosition(vector);
    }

    @Override
    public BlockVector getStructureSize() {
        return CraftBlockVector.toBukkit(((StructureBlockEntity)this.getSnapshot()).f_59816_);
    }

    @Override
    public void setStructureSize(BlockVector vector) {
        Preconditions.checkArgument((boolean)CraftStructureBlock.isBetween(vector.getBlockX(), 0, 48), (String)"Structure Size (X) must be between %s and %s but got %s", (Object)0, (Object)48, (Object)vector.getBlockX());
        Preconditions.checkArgument((boolean)CraftStructureBlock.isBetween(vector.getBlockY(), 0, 48), (String)"Structure Size (Y) must be between %s and %s but got %s", (Object)0, (Object)48, (Object)vector.getBlockY());
        Preconditions.checkArgument((boolean)CraftStructureBlock.isBetween(vector.getBlockZ(), 0, 48), (String)"Structure Size (Z) must be between %s and %s but got %s", (Object)0, (Object)48, (Object)vector.getBlockZ());
        ((StructureBlockEntity)this.getSnapshot()).f_59816_ = CraftBlockVector.toBlockPosition(vector);
    }

    @Override
    public void setMirror(Mirror mirror) {
        Preconditions.checkArgument((mirror != null ? 1 : 0) != 0, (Object)"Mirror cannot be null");
        ((StructureBlockEntity)this.getSnapshot()).f_59817_ = net.minecraft.world.level.block.Mirror.valueOf((String)mirror.name());
    }

    @Override
    public Mirror getMirror() {
        return Mirror.valueOf(((StructureBlockEntity)this.getSnapshot()).f_59817_.name());
    }

    @Override
    public void setRotation(StructureRotation rotation) {
        Preconditions.checkArgument((rotation != null ? 1 : 0) != 0, (Object)"StructureRotation cannot be null");
        ((StructureBlockEntity)this.getSnapshot()).f_59818_ = Rotation.valueOf((String)rotation.name());
    }

    @Override
    public StructureRotation getRotation() {
        return StructureRotation.valueOf(((StructureBlockEntity)this.getSnapshot()).f_59818_.name());
    }

    @Override
    public void setUsageMode(UsageMode mode) {
        Preconditions.checkArgument((mode != null ? 1 : 0) != 0, (Object)"UsageMode cannot be null");
        ((StructureBlockEntity)this.getSnapshot()).f_59819_ = StructureMode.valueOf((String)mode.name());
    }

    @Override
    public UsageMode getUsageMode() {
        return UsageMode.valueOf(((StructureBlockEntity)this.getSnapshot()).m_59908_().name());
    }

    @Override
    public void setIgnoreEntities(boolean flag) {
        ((StructureBlockEntity)this.getSnapshot()).f_59820_ = flag;
    }

    @Override
    public boolean isIgnoreEntities() {
        return ((StructureBlockEntity)this.getSnapshot()).f_59820_;
    }

    @Override
    public void setShowAir(boolean showAir) {
        ((StructureBlockEntity)this.getSnapshot()).f_59822_ = showAir;
    }

    @Override
    public boolean isShowAir() {
        return ((StructureBlockEntity)this.getSnapshot()).f_59822_;
    }

    @Override
    public void setBoundingBoxVisible(boolean showBoundingBox) {
        ((StructureBlockEntity)this.getSnapshot()).f_59823_ = showBoundingBox;
    }

    @Override
    public boolean isBoundingBoxVisible() {
        return ((StructureBlockEntity)this.getSnapshot()).f_59823_;
    }

    @Override
    public void setIntegrity(float integrity) {
        Preconditions.checkArgument((boolean)CraftStructureBlock.isBetween(integrity, 0.0f, 1.0f), (String)"Integrity must be between 0.0f and 1.0f but got %s", (Object)Float.valueOf(integrity));
        ((StructureBlockEntity)this.getSnapshot()).f_59824_ = integrity;
    }

    @Override
    public float getIntegrity() {
        return ((StructureBlockEntity)this.getSnapshot()).f_59824_;
    }

    @Override
    public void setSeed(long seed) {
        ((StructureBlockEntity)this.getSnapshot()).f_59825_ = seed;
    }

    @Override
    public long getSeed() {
        return ((StructureBlockEntity)this.getSnapshot()).f_59825_;
    }

    @Override
    public void setMetadata(String metadata) {
        Preconditions.checkArgument((metadata != null ? 1 : 0) != 0, (Object)"Structure metadata cannot be null");
        if (this.getUsageMode() == UsageMode.DATA) {
            ((StructureBlockEntity)this.getSnapshot()).f_59814_ = metadata;
        }
    }

    @Override
    public String getMetadata() {
        return ((StructureBlockEntity)this.getSnapshot()).f_59814_;
    }

    @Override
    protected void applyTo(StructureBlockEntity tileEntity) {
        BlockState data;
        super.applyTo(tileEntity);
        LevelAccessor access = this.getWorldHandle();
        if (access instanceof Level) {
            tileEntity.m_59860_(tileEntity.m_59908_());
        } else if (access != null && (data = access.m_8055_(this.getPosition())).m_60713_(Blocks.f_50677_)) {
            access.m_7731_(this.getPosition(), (BlockState)data.m_61124_((Property)StructureBlock.f_57110_, (Comparable)tileEntity.m_59908_()), 2);
        }
    }

    private static boolean isBetween(int num, int min, int max) {
        return num >= min && num <= max;
    }

    private static boolean isBetween(float num, float min, float max) {
        return num >= min && num <= max;
    }
}

