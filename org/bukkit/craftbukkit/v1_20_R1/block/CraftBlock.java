/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.Holder
 *  net.minecraft.core.Registry
 *  net.minecraft.core.registries.Registries
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.BoneMealItem
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.ClipContext
 *  net.minecraft.world.level.ClipContext$Block
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.LightLayer
 *  net.minecraft.world.level.biome.Biome
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.RedStoneWireBlock
 *  net.minecraft.world.level.block.SaplingBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.BlockHitResult
 *  net.minecraft.world.phys.HitResult
 *  net.minecraft.world.phys.Vec3
 *  net.minecraft.world.phys.shapes.VoxelShape
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftFluidCollisionMode;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftRayTraceResult;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftVoxelShape;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.BlockVector;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class CraftBlock
implements org.bukkit.block.Block {
    private final LevelAccessor world;
    private final BlockPos position;

    public CraftBlock(LevelAccessor world, BlockPos position) {
        this.world = world;
        this.position = position.m_7949_();
    }

    public static CraftBlock at(LevelAccessor world, BlockPos position) {
        return new CraftBlock(world, position);
    }

    public net.minecraft.world.level.block.state.BlockState getNMS() {
        return this.world.m_8055_(this.position);
    }

    public BlockPos getPosition() {
        return this.position;
    }

    public LevelAccessor getHandle() {
        return this.world;
    }

    @Override
    public World getWorld() {
        return this.world.getMinecraftWorld().getWorld();
    }

    public CraftWorld getCraftWorld() {
        return (CraftWorld)this.getWorld();
    }

    @Override
    public Location getLocation() {
        return CraftLocation.toBukkit(this.position, this.getWorld());
    }

    @Override
    public Location getLocation(Location loc) {
        if (loc != null) {
            loc.setWorld(this.getWorld());
            loc.setX(this.position.m_123341_());
            loc.setY(this.position.m_123342_());
            loc.setZ(this.position.m_123343_());
            loc.setYaw(0.0f);
            loc.setPitch(0.0f);
        }
        return loc;
    }

    public BlockVector getVector() {
        return new BlockVector(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public int getX() {
        return this.position.m_123341_();
    }

    @Override
    public int getY() {
        return this.position.m_123342_();
    }

    @Override
    public int getZ() {
        return this.position.m_123343_();
    }

    @Override
    public Chunk getChunk() {
        return this.getWorld().getChunkAt(this);
    }

    public void setData(byte data) {
        this.setData(data, 3);
    }

    public void setData(byte data, boolean applyPhysics) {
        if (applyPhysics) {
            this.setData(data, 3);
        } else {
            this.setData(data, 2);
        }
    }

    private void setData(byte data, int flag) {
        this.world.m_7731_(this.position, CraftMagicNumbers.getBlock(this.getType(), data), flag);
    }

    @Override
    public byte getData() {
        net.minecraft.world.level.block.state.BlockState blockData = this.world.m_8055_(this.position);
        return CraftMagicNumbers.toLegacyData(blockData);
    }

    @Override
    public BlockData getBlockData() {
        return CraftBlockData.fromData(this.getNMS());
    }

    @Override
    public void setType(Material type) {
        this.setType(type, true);
    }

    @Override
    public void setType(Material type, boolean applyPhysics) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        this.setBlockData(type.createBlockData(), applyPhysics);
    }

    @Override
    public void setBlockData(BlockData data) {
        this.setBlockData(data, true);
    }

    @Override
    public void setBlockData(BlockData data, boolean applyPhysics) {
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"BlockData cannot be null");
        this.setTypeAndData(((CraftBlockData)data).getState(), applyPhysics);
    }

    boolean setTypeAndData(net.minecraft.world.level.block.state.BlockState blockData, boolean applyPhysics) {
        return CraftBlock.setTypeAndData(this.world, this.position, this.getNMS(), blockData, applyPhysics);
    }

    public static boolean setTypeAndData(LevelAccessor world, BlockPos position, net.minecraft.world.level.block.state.BlockState old, net.minecraft.world.level.block.state.BlockState blockData, boolean applyPhysics) {
        if (old.m_155947_() && blockData.m_60734_() != old.m_60734_()) {
            if (world instanceof Level) {
                ((Level)world).m_46747_(position);
            } else {
                world.m_7731_(position, Blocks.f_50016_.m_49966_(), 0);
            }
        }
        if (applyPhysics) {
            return world.m_7731_(position, blockData, 3);
        }
        boolean success = world.m_7731_(position, blockData, 1042);
        if (success && world instanceof Level) {
            world.getMinecraftWorld().m_7260_(position, old, blockData, 3);
        }
        return success;
    }

    @Override
    public Material getType() {
        return CraftMagicNumbers.getMaterial(this.world.m_8055_(this.position).m_60734_());
    }

    @Override
    public byte getLightLevel() {
        return (byte)this.world.getMinecraftWorld().m_46803_(this.position);
    }

    @Override
    public byte getLightFromSky() {
        return (byte)this.world.m_45517_(LightLayer.SKY, this.position);
    }

    @Override
    public byte getLightFromBlocks() {
        return (byte)this.world.m_45517_(LightLayer.BLOCK, this.position);
    }

    public org.bukkit.block.Block getFace(BlockFace face) {
        return this.getRelative(face, 1);
    }

    public org.bukkit.block.Block getFace(BlockFace face, int distance) {
        return this.getRelative(face, distance);
    }

    @Override
    public org.bukkit.block.Block getRelative(int modX, int modY, int modZ) {
        return this.getWorld().getBlockAt(this.getX() + modX, this.getY() + modY, this.getZ() + modZ);
    }

    @Override
    public org.bukkit.block.Block getRelative(BlockFace face) {
        return this.getRelative(face, 1);
    }

    @Override
    public org.bukkit.block.Block getRelative(BlockFace face, int distance) {
        return this.getRelative(face.getModX() * distance, face.getModY() * distance, face.getModZ() * distance);
    }

    @Override
    public BlockFace getFace(org.bukkit.block.Block block) {
        BlockFace[] values;
        BlockFace[] blockFaceArray = values = BlockFace.values();
        int n = values.length;
        int n2 = 0;
        while (n2 < n) {
            BlockFace face = blockFaceArray[n2];
            if (this.getX() + face.getModX() == block.getX() && this.getY() + face.getModY() == block.getY() && this.getZ() + face.getModZ() == block.getZ()) {
                return face;
            }
            ++n2;
        }
        return null;
    }

    public String toString() {
        return "CraftBlock{pos=" + this.position + ",type=" + this.getType() + ",data=" + this.getNMS() + ",fluid=" + this.world.m_6425_(this.position) + '}';
    }

    public static BlockFace notchToBlockFace(Direction notch) {
        if (notch == null) {
            return BlockFace.SELF;
        }
        switch (notch) {
            case DOWN: {
                return BlockFace.DOWN;
            }
            case UP: {
                return BlockFace.UP;
            }
            case NORTH: {
                return BlockFace.NORTH;
            }
            case SOUTH: {
                return BlockFace.SOUTH;
            }
            case WEST: {
                return BlockFace.WEST;
            }
            case EAST: {
                return BlockFace.EAST;
            }
        }
        return BlockFace.SELF;
    }

    public static Direction blockFaceToNotch(BlockFace face) {
        if (face == null) {
            return null;
        }
        switch (face) {
            case DOWN: {
                return Direction.DOWN;
            }
            case UP: {
                return Direction.UP;
            }
            case NORTH: {
                return Direction.NORTH;
            }
            case SOUTH: {
                return Direction.SOUTH;
            }
            case WEST: {
                return Direction.WEST;
            }
            case EAST: {
                return Direction.EAST;
            }
        }
        return null;
    }

    @Override
    public BlockState getState() {
        return CraftBlockStates.getBlockState(this);
    }

    @Override
    public Biome getBiome() {
        return this.getWorld().getBiome(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public void setBiome(Biome bio) {
        this.getWorld().setBiome(this.getX(), this.getY(), this.getZ(), bio);
    }

    public static Biome biomeBaseToBiome(net.minecraft.core.Registry<net.minecraft.world.level.biome.Biome> registry, Holder<net.minecraft.world.level.biome.Biome> base) {
        return CraftBlock.biomeBaseToBiome(registry, (net.minecraft.world.level.biome.Biome)base.m_203334_());
    }

    public static Biome biomeBaseToBiome(net.minecraft.core.Registry<net.minecraft.world.level.biome.Biome> registry, net.minecraft.world.level.biome.Biome base) {
        if (base == null) {
            return null;
        }
        Biome biome = Registry.BIOME.get(CraftNamespacedKey.fromMinecraft(registry.m_7981_((Object)base)));
        return biome == null ? Biome.CUSTOM : biome;
    }

    public static Holder<net.minecraft.world.level.biome.Biome> biomeToBiomeBase(net.minecraft.core.Registry<net.minecraft.world.level.biome.Biome> registry, Biome bio) {
        if (bio == null || bio == Biome.CUSTOM) {
            return null;
        }
        return registry.m_246971_(ResourceKey.m_135785_((ResourceKey)Registries.f_256952_, (ResourceLocation)CraftNamespacedKey.toMinecraft(bio.getKey())));
    }

    @Override
    public double getTemperature() {
        return ((net.minecraft.world.level.biome.Biome)this.world.m_204166_(this.position).m_203334_()).m_47505_(this.position);
    }

    @Override
    public double getHumidity() {
        return this.getWorld().getHumidity(this.getX(), this.getY(), this.getZ());
    }

    @Override
    public boolean isBlockPowered() {
        return this.world.getMinecraftWorld().m_277173_(this.position) > 0;
    }

    @Override
    public boolean isBlockIndirectlyPowered() {
        return this.world.getMinecraftWorld().m_276867_(this.position);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CraftBlock var2_3)) {
            return false;
        }
        return this.position.equals((Object)other.position) && this.getWorld().equals(other.getWorld());
    }

    public int hashCode() {
        return this.position.hashCode() ^ this.getWorld().hashCode();
    }

    @Override
    public boolean isBlockFacePowered(BlockFace face) {
        return this.world.getMinecraftWorld().m_276987_(this.position, CraftBlock.blockFaceToNotch(face));
    }

    @Override
    public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
        int power = this.world.getMinecraftWorld().m_277185_(this.position, CraftBlock.blockFaceToNotch(face));
        org.bukkit.block.Block relative = this.getRelative(face);
        if (relative.getType() == Material.REDSTONE_WIRE) {
            return Math.max(power, relative.getData()) > 0;
        }
        return power > 0;
    }

    @Override
    public int getBlockPower(BlockFace face) {
        int power = 0;
        ServerLevel world = this.world.getMinecraftWorld();
        int x = this.getX();
        int y = this.getY();
        int z = this.getZ();
        if ((face == BlockFace.DOWN || face == BlockFace.SELF) && world.m_276987_(new BlockPos(x, y - 1, z), Direction.DOWN)) {
            power = CraftBlock.getPower(power, world.m_8055_(new BlockPos(x, y - 1, z)));
        }
        if ((face == BlockFace.UP || face == BlockFace.SELF) && world.m_276987_(new BlockPos(x, y + 1, z), Direction.UP)) {
            power = CraftBlock.getPower(power, world.m_8055_(new BlockPos(x, y + 1, z)));
        }
        if ((face == BlockFace.EAST || face == BlockFace.SELF) && world.m_276987_(new BlockPos(x + 1, y, z), Direction.EAST)) {
            power = CraftBlock.getPower(power, world.m_8055_(new BlockPos(x + 1, y, z)));
        }
        if ((face == BlockFace.WEST || face == BlockFace.SELF) && world.m_276987_(new BlockPos(x - 1, y, z), Direction.WEST)) {
            power = CraftBlock.getPower(power, world.m_8055_(new BlockPos(x - 1, y, z)));
        }
        if ((face == BlockFace.NORTH || face == BlockFace.SELF) && world.m_276987_(new BlockPos(x, y, z - 1), Direction.NORTH)) {
            power = CraftBlock.getPower(power, world.m_8055_(new BlockPos(x, y, z - 1)));
        }
        if ((face == BlockFace.SOUTH || face == BlockFace.SELF) && world.m_276987_(new BlockPos(x, y, z + 1), Direction.SOUTH)) {
            power = CraftBlock.getPower(power, world.m_8055_(new BlockPos(x, y, z + 1)));
        }
        return power > 0 ? power : ((face == BlockFace.SELF ? this.isBlockIndirectlyPowered() : this.isBlockFaceIndirectlyPowered(face)) ? 15 : 0);
    }

    private static int getPower(int i, net.minecraft.world.level.block.state.BlockState iblockdata) {
        if (!iblockdata.m_60713_(Blocks.f_50088_)) {
            return i;
        }
        int j = (Integer)iblockdata.m_61143_((Property)RedStoneWireBlock.f_55500_);
        return j > i ? j : i;
    }

    @Override
    public int getBlockPower() {
        return this.getBlockPower(BlockFace.SELF);
    }

    @Override
    public boolean isEmpty() {
        return this.getNMS().m_60795_();
    }

    @Override
    public boolean isLiquid() {
        return this.getNMS().m_278721_();
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return PistonMoveReaction.getById(this.getNMS().m_60811_().ordinal());
    }

    @Override
    public boolean breakNaturally() {
        return this.breakNaturally(null);
    }

    @Override
    public boolean breakNaturally(ItemStack item) {
        net.minecraft.world.level.block.state.BlockState iblockdata = this.getNMS();
        Block block = iblockdata.m_60734_();
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        boolean result = false;
        if (block != Blocks.f_50016_ && (item == null || !iblockdata.m_60834_() || nmsItem.m_41735_(iblockdata))) {
            Block.m_49881_((net.minecraft.world.level.block.state.BlockState)iblockdata, (Level)this.world.getMinecraftWorld(), (BlockPos)this.position, (BlockEntity)this.world.m_7702_(this.position), null, (net.minecraft.world.item.ItemStack)nmsItem);
            result = true;
        }
        return this.world.m_7731_(this.position, Blocks.f_50016_.m_49966_(), 3) && result;
    }

    @Override
    public boolean applyBoneMeal(BlockFace face) {
        Direction direction = CraftBlock.blockFaceToNotch(face);
        BlockFertilizeEvent event = null;
        ServerLevel world = this.getCraftWorld().getHandle();
        UseOnContext context = new UseOnContext((Level)world, null, InteractionHand.MAIN_HAND, Items.f_42499_.m_7968_(), new BlockHitResult(Vec3.f_82478_, direction, this.getPosition(), false));
        world.captureTreeGeneration = true;
        InteractionResult result = BoneMealItem.applyBonemeal((UseOnContext)context);
        world.captureTreeGeneration = false;
        if (world.capturedBlockStates.size() > 0) {
            TreeType treeType = SaplingBlock.treeType;
            SaplingBlock.treeType = null;
            ArrayList<BlockState> blocks = new ArrayList<BlockState>(world.capturedBlockStates.values());
            world.capturedBlockStates.clear();
            StructureGrowEvent structureEvent = null;
            if (treeType != null) {
                structureEvent = new StructureGrowEvent(this.getLocation(), treeType, true, null, blocks);
                Bukkit.getPluginManager().callEvent(structureEvent);
            }
            event = new BlockFertilizeEvent(CraftBlock.at((LevelAccessor)world, this.getPosition()), null, blocks);
            event.setCancelled(structureEvent != null && structureEvent.isCancelled());
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                for (BlockState blockstate : blocks) {
                    blockstate.update(true);
                }
            }
        }
        return result == InteractionResult.SUCCESS && (event == null || !event.isCancelled());
    }

    @Override
    public Collection<ItemStack> getDrops() {
        return this.getDrops(null);
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack item) {
        return this.getDrops(item, null);
    }

    @Override
    public Collection<ItemStack> getDrops(ItemStack item, Entity entity) {
        net.minecraft.world.level.block.state.BlockState iblockdata = this.getNMS();
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
        if (item == null || CraftBlockData.isPreferredTool(iblockdata, nms)) {
            return Block.m_49874_((net.minecraft.world.level.block.state.BlockState)iblockdata, (ServerLevel)this.world.getMinecraftWorld(), (BlockPos)this.position, (BlockEntity)this.world.m_7702_(this.position), (net.minecraft.world.entity.Entity)(entity == null ? null : ((CraftEntity)entity).getHandle()), (net.minecraft.world.item.ItemStack)nms).stream().map(CraftItemStack::asBukkitCopy).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean isPreferredTool(ItemStack item) {
        net.minecraft.world.level.block.state.BlockState iblockdata = this.getNMS();
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(item);
        return CraftBlockData.isPreferredTool(iblockdata, nms);
    }

    @Override
    public float getBreakSpeed(org.bukkit.entity.Player player) {
        Preconditions.checkArgument((player != null ? 1 : 0) != 0, (Object)"player cannot be null");
        return this.getNMS().m_60625_((Player)((CraftPlayer)player).getHandle(), (BlockGetter)this.world, this.position);
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        this.getCraftWorld().getBlockMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) {
        return this.getCraftWorld().getBlockMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) {
        return this.getCraftWorld().getBlockMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        this.getCraftWorld().getBlockMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }

    @Override
    public boolean isPassable() {
        return this.getNMS().m_60812_((BlockGetter)this.world, this.position).m_83281_();
    }

    @Override
    public RayTraceResult rayTrace(Location start, Vector direction, double maxDistance, FluidCollisionMode fluidCollisionMode) {
        Preconditions.checkArgument((start != null ? 1 : 0) != 0, (Object)"Location start cannot be null");
        Preconditions.checkArgument((boolean)this.getWorld().equals(start.getWorld()), (Object)"Location start cannot be a different world");
        start.checkFinite();
        Preconditions.checkArgument((direction != null ? 1 : 0) != 0, (Object)"Vector direction cannot be null");
        direction.checkFinite();
        Preconditions.checkArgument((direction.lengthSquared() > 0.0 ? 1 : 0) != 0, (String)"Direction's magnitude (%s) must be greater than 0", (Object)direction.lengthSquared());
        Preconditions.checkArgument((fluidCollisionMode != null ? 1 : 0) != 0, (Object)"FluidCollisionMode cannot be null");
        if (maxDistance < 0.0) {
            return null;
        }
        Vector dir = direction.clone().normalize().multiply(maxDistance);
        Vec3 startPos = CraftLocation.toVec3D(start);
        Vec3 endPos = startPos.m_82520_(dir.getX(), dir.getY(), dir.getZ());
        BlockHitResult nmsHitResult = this.world.clip(new ClipContext(startPos, endPos, ClipContext.Block.OUTLINE, CraftFluidCollisionMode.toNMS(fluidCollisionMode), null), this.position);
        return CraftRayTraceResult.fromNMS(this.getWorld(), (HitResult)nmsHitResult);
    }

    @Override
    public BoundingBox getBoundingBox() {
        VoxelShape shape = this.getNMS().m_60808_((BlockGetter)this.world, this.position);
        if (shape.m_83281_()) {
            return new BoundingBox();
        }
        AABB aabb = shape.m_83215_();
        return new BoundingBox((double)this.getX() + aabb.f_82288_, (double)this.getY() + aabb.f_82289_, (double)this.getZ() + aabb.f_82290_, (double)this.getX() + aabb.f_82291_, (double)this.getY() + aabb.f_82292_, (double)this.getZ() + aabb.f_82293_);
    }

    @Override
    public org.bukkit.util.VoxelShape getCollisionShape() {
        VoxelShape shape = this.getNMS().m_60812_((BlockGetter)this.world, this.position);
        return new CraftVoxelShape(shape);
    }

    @Override
    public boolean canPlace(BlockData data) {
        Preconditions.checkArgument((data != null ? 1 : 0) != 0, (Object)"BlockData cannot be null");
        net.minecraft.world.level.block.state.BlockState iblockdata = ((CraftBlockData)data).getState();
        ServerLevel world = this.world.getMinecraftWorld();
        return iblockdata.m_60710_((LevelReader)world, this.position);
    }

    @Override
    public String getTranslationKey() {
        return this.getNMS().m_60734_().m_7705_();
    }
}

