/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.BannerBlockEntity
 *  net.minecraft.world.level.block.entity.BarrelBlockEntity
 *  net.minecraft.world.level.block.entity.BeaconBlockEntity
 *  net.minecraft.world.level.block.entity.BedBlockEntity
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity
 *  net.minecraft.world.level.block.entity.BellBlockEntity
 *  net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BrewingStandBlockEntity
 *  net.minecraft.world.level.block.entity.BrushableBlockEntity
 *  net.minecraft.world.level.block.entity.CalibratedSculkSensorBlockEntity
 *  net.minecraft.world.level.block.entity.CampfireBlockEntity
 *  net.minecraft.world.level.block.entity.ChestBlockEntity
 *  net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity
 *  net.minecraft.world.level.block.entity.CommandBlockEntity
 *  net.minecraft.world.level.block.entity.ComparatorBlockEntity
 *  net.minecraft.world.level.block.entity.ConduitBlockEntity
 *  net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity
 *  net.minecraft.world.level.block.entity.DecoratedPotBlockEntity
 *  net.minecraft.world.level.block.entity.DispenserBlockEntity
 *  net.minecraft.world.level.block.entity.DropperBlockEntity
 *  net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity
 *  net.minecraft.world.level.block.entity.EnderChestBlockEntity
 *  net.minecraft.world.level.block.entity.FurnaceBlockEntity
 *  net.minecraft.world.level.block.entity.HangingSignBlockEntity
 *  net.minecraft.world.level.block.entity.HopperBlockEntity
 *  net.minecraft.world.level.block.entity.JigsawBlockEntity
 *  net.minecraft.world.level.block.entity.JukeboxBlockEntity
 *  net.minecraft.world.level.block.entity.LecternBlockEntity
 *  net.minecraft.world.level.block.entity.SculkCatalystBlockEntity
 *  net.minecraft.world.level.block.entity.SculkSensorBlockEntity
 *  net.minecraft.world.level.block.entity.SculkShriekerBlockEntity
 *  net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity
 *  net.minecraft.world.level.block.entity.SignBlockEntity
 *  net.minecraft.world.level.block.entity.SkullBlockEntity
 *  net.minecraft.world.level.block.entity.SmokerBlockEntity
 *  net.minecraft.world.level.block.entity.SpawnerBlockEntity
 *  net.minecraft.world.level.block.entity.StructureBlockEntity
 *  net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity
 *  net.minecraft.world.level.block.entity.TheEndPortalBlockEntity
 *  net.minecraft.world.level.block.entity.TrappedChestBlockEntity
 *  net.minecraft.world.level.block.piston.PistonMovingBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import net.minecraft.world.level.block.entity.CalibratedSculkSensorBlockEntity;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import net.minecraft.world.level.block.entity.ComparatorBlockEntity;
import net.minecraft.world.level.block.entity.ConduitBlockEntity;
import net.minecraft.world.level.block.entity.DaylightDetectorBlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.DropperBlockEntity;
import net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity;
import net.minecraft.world.level.block.entity.EnderChestBlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.entity.SculkCatalystBlockEntity;
import net.minecraft.world.level.block.entity.SculkSensorBlockEntity;
import net.minecraft.world.level.block.entity.SculkShriekerBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.entity.TheEndPortalBlockEntity;
import net.minecraft.world.level.block.entity.TrappedChestBlockEntity;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBanner;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBarrel;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBeacon;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBed;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBeehive;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBell;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlastFurnace;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBrewingStand;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBrushableBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftCalibratedSculkSensor;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftCampfire;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftChest;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftChiseledBookshelf;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftCommandBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftComparator;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftConduit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftCreatureSpawner;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftDaylightDetector;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftDecoratedPot;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftDispenser;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftDropper;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftEnchantingTable;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftEndGateway;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftEndPortal;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftEnderChest;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftFurnaceFurnace;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftHangingSign;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftHopper;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftJigsaw;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftJukebox;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftLectern;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftMovingPiston;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSculkCatalyst;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSculkSensor;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSculkShrieker;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftShulkerBox;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSign;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSkull;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSmoker;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftStructureBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftSuspiciousSand;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;

public final class CraftBlockStates {
    private static final Map<Material, BlockStateFactory<?>> FACTORIES = new HashMap();
    private static final BlockStateFactory<?> DEFAULT_FACTORY = new BlockStateFactory<CraftBlockState>(CraftBlockState.class){

        @Override
        public CraftBlockState createBlockState(World world, BlockPos blockPosition, net.minecraft.world.level.block.state.BlockState blockData, BlockEntity tileEntity) {
            if (tileEntity != null) {
                return new CraftBlockEntityState<BlockEntity>(world, tileEntity);
            }
            Preconditions.checkState((tileEntity == null ? 1 : 0) != 0, (String)"Unexpected BlockState for %s", (Object)CraftMagicNumbers.getMaterial(blockData.m_60734_()));
            return new CraftBlockState(world, blockPosition, blockData);
        }
    };

    static {
        CraftBlockStates.register(Arrays.asList(Material.ACACIA_SIGN, Material.ACACIA_WALL_SIGN, Material.BAMBOO_SIGN, Material.BAMBOO_WALL_SIGN, Material.BIRCH_SIGN, Material.BIRCH_WALL_SIGN, Material.CHERRY_SIGN, Material.CHERRY_WALL_SIGN, Material.CRIMSON_SIGN, Material.CRIMSON_WALL_SIGN, Material.DARK_OAK_SIGN, Material.DARK_OAK_WALL_SIGN, Material.JUNGLE_SIGN, Material.JUNGLE_WALL_SIGN, Material.MANGROVE_SIGN, Material.MANGROVE_WALL_SIGN, Material.OAK_SIGN, Material.OAK_WALL_SIGN, Material.SPRUCE_SIGN, Material.SPRUCE_WALL_SIGN, Material.WARPED_SIGN, Material.WARPED_WALL_SIGN), CraftSign.class, CraftSign::new, SignBlockEntity::new);
        CraftBlockStates.register(Arrays.asList(Material.ACACIA_HANGING_SIGN, Material.ACACIA_WALL_HANGING_SIGN, Material.BAMBOO_HANGING_SIGN, Material.BAMBOO_WALL_HANGING_SIGN, Material.BIRCH_HANGING_SIGN, Material.BIRCH_WALL_HANGING_SIGN, Material.CHERRY_HANGING_SIGN, Material.CHERRY_WALL_HANGING_SIGN, Material.CRIMSON_HANGING_SIGN, Material.CRIMSON_WALL_HANGING_SIGN, Material.DARK_OAK_HANGING_SIGN, Material.DARK_OAK_WALL_HANGING_SIGN, Material.JUNGLE_HANGING_SIGN, Material.JUNGLE_WALL_HANGING_SIGN, Material.MANGROVE_HANGING_SIGN, Material.MANGROVE_WALL_HANGING_SIGN, Material.OAK_HANGING_SIGN, Material.OAK_WALL_HANGING_SIGN, Material.SPRUCE_HANGING_SIGN, Material.SPRUCE_WALL_HANGING_SIGN, Material.WARPED_HANGING_SIGN, Material.WARPED_WALL_HANGING_SIGN), CraftHangingSign.class, CraftHangingSign::new, HangingSignBlockEntity::new);
        CraftBlockStates.register(Arrays.asList(Material.CREEPER_HEAD, Material.CREEPER_WALL_HEAD, Material.DRAGON_HEAD, Material.DRAGON_WALL_HEAD, Material.PIGLIN_HEAD, Material.PIGLIN_WALL_HEAD, Material.PLAYER_HEAD, Material.PLAYER_WALL_HEAD, Material.SKELETON_SKULL, Material.SKELETON_WALL_SKULL, Material.WITHER_SKELETON_SKULL, Material.WITHER_SKELETON_WALL_SKULL, Material.ZOMBIE_HEAD, Material.ZOMBIE_WALL_HEAD), CraftSkull.class, CraftSkull::new, SkullBlockEntity::new);
        CraftBlockStates.register(Arrays.asList(Material.COMMAND_BLOCK, Material.REPEATING_COMMAND_BLOCK, Material.CHAIN_COMMAND_BLOCK), CraftCommandBlock.class, CraftCommandBlock::new, CommandBlockEntity::new);
        CraftBlockStates.register(Arrays.asList(Material.BLACK_BANNER, Material.BLACK_WALL_BANNER, Material.BLUE_BANNER, Material.BLUE_WALL_BANNER, Material.BROWN_BANNER, Material.BROWN_WALL_BANNER, Material.CYAN_BANNER, Material.CYAN_WALL_BANNER, Material.GRAY_BANNER, Material.GRAY_WALL_BANNER, Material.GREEN_BANNER, Material.GREEN_WALL_BANNER, Material.LIGHT_BLUE_BANNER, Material.LIGHT_BLUE_WALL_BANNER, Material.LIGHT_GRAY_BANNER, Material.LIGHT_GRAY_WALL_BANNER, Material.LIME_BANNER, Material.LIME_WALL_BANNER, Material.MAGENTA_BANNER, Material.MAGENTA_WALL_BANNER, Material.ORANGE_BANNER, Material.ORANGE_WALL_BANNER, Material.PINK_BANNER, Material.PINK_WALL_BANNER, Material.PURPLE_BANNER, Material.PURPLE_WALL_BANNER, Material.RED_BANNER, Material.RED_WALL_BANNER, Material.WHITE_BANNER, Material.WHITE_WALL_BANNER, Material.YELLOW_BANNER, Material.YELLOW_WALL_BANNER), CraftBanner.class, CraftBanner::new, BannerBlockEntity::new);
        CraftBlockStates.register(Arrays.asList(Material.SHULKER_BOX, Material.WHITE_SHULKER_BOX, Material.ORANGE_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX, Material.LIGHT_BLUE_SHULKER_BOX, Material.YELLOW_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.PINK_SHULKER_BOX, Material.GRAY_SHULKER_BOX, Material.LIGHT_GRAY_SHULKER_BOX, Material.CYAN_SHULKER_BOX, Material.PURPLE_SHULKER_BOX, Material.BLUE_SHULKER_BOX, Material.BROWN_SHULKER_BOX, Material.GREEN_SHULKER_BOX, Material.RED_SHULKER_BOX, Material.BLACK_SHULKER_BOX), CraftShulkerBox.class, CraftShulkerBox::new, ShulkerBoxBlockEntity::new);
        CraftBlockStates.register(Arrays.asList(Material.BLACK_BED, Material.BLUE_BED, Material.BROWN_BED, Material.CYAN_BED, Material.GRAY_BED, Material.GREEN_BED, Material.LIGHT_BLUE_BED, Material.LIGHT_GRAY_BED, Material.LIME_BED, Material.MAGENTA_BED, Material.ORANGE_BED, Material.PINK_BED, Material.PURPLE_BED, Material.RED_BED, Material.WHITE_BED, Material.YELLOW_BED), CraftBed.class, CraftBed::new, BedBlockEntity::new);
        CraftBlockStates.register(Arrays.asList(Material.BEEHIVE, Material.BEE_NEST), CraftBeehive.class, CraftBeehive::new, BeehiveBlockEntity::new);
        CraftBlockStates.register(Arrays.asList(Material.CAMPFIRE, Material.SOUL_CAMPFIRE), CraftCampfire.class, CraftCampfire::new, CampfireBlockEntity::new);
        CraftBlockStates.register(Material.BARREL, CraftBarrel.class, CraftBarrel::new, BarrelBlockEntity::new);
        CraftBlockStates.register(Material.BEACON, CraftBeacon.class, CraftBeacon::new, BeaconBlockEntity::new);
        CraftBlockStates.register(Material.BELL, CraftBell.class, CraftBell::new, BellBlockEntity::new);
        CraftBlockStates.register(Material.BLAST_FURNACE, CraftBlastFurnace.class, CraftBlastFurnace::new, BlastFurnaceBlockEntity::new);
        CraftBlockStates.register(Material.BREWING_STAND, CraftBrewingStand.class, CraftBrewingStand::new, BrewingStandBlockEntity::new);
        CraftBlockStates.register(Material.CHEST, CraftChest.class, CraftChest::new, ChestBlockEntity::new);
        CraftBlockStates.register(Material.CHISELED_BOOKSHELF, CraftChiseledBookshelf.class, CraftChiseledBookshelf::new, ChiseledBookShelfBlockEntity::new);
        CraftBlockStates.register(Material.COMPARATOR, CraftComparator.class, CraftComparator::new, ComparatorBlockEntity::new);
        CraftBlockStates.register(Material.CONDUIT, CraftConduit.class, CraftConduit::new, ConduitBlockEntity::new);
        CraftBlockStates.register(Material.DAYLIGHT_DETECTOR, CraftDaylightDetector.class, CraftDaylightDetector::new, DaylightDetectorBlockEntity::new);
        CraftBlockStates.register(Material.DECORATED_POT, CraftDecoratedPot.class, CraftDecoratedPot::new, DecoratedPotBlockEntity::new);
        CraftBlockStates.register(Material.DISPENSER, CraftDispenser.class, CraftDispenser::new, DispenserBlockEntity::new);
        CraftBlockStates.register(Material.DROPPER, CraftDropper.class, CraftDropper::new, DropperBlockEntity::new);
        CraftBlockStates.register(Material.ENCHANTING_TABLE, CraftEnchantingTable.class, CraftEnchantingTable::new, EnchantmentTableBlockEntity::new);
        CraftBlockStates.register(Material.ENDER_CHEST, CraftEnderChest.class, CraftEnderChest::new, EnderChestBlockEntity::new);
        CraftBlockStates.register(Material.END_GATEWAY, CraftEndGateway.class, CraftEndGateway::new, TheEndGatewayBlockEntity::new);
        CraftBlockStates.register(Material.END_PORTAL, CraftEndPortal.class, CraftEndPortal::new, TheEndPortalBlockEntity::new);
        CraftBlockStates.register(Material.FURNACE, CraftFurnaceFurnace.class, CraftFurnaceFurnace::new, FurnaceBlockEntity::new);
        CraftBlockStates.register(Material.HOPPER, CraftHopper.class, CraftHopper::new, HopperBlockEntity::new);
        CraftBlockStates.register(Material.JIGSAW, CraftJigsaw.class, CraftJigsaw::new, JigsawBlockEntity::new);
        CraftBlockStates.register(Material.JUKEBOX, CraftJukebox.class, CraftJukebox::new, JukeboxBlockEntity::new);
        CraftBlockStates.register(Material.LECTERN, CraftLectern.class, CraftLectern::new, LecternBlockEntity::new);
        CraftBlockStates.register(Material.MOVING_PISTON, CraftMovingPiston.class, CraftMovingPiston::new, PistonMovingBlockEntity::new);
        CraftBlockStates.register(Material.SCULK_CATALYST, CraftSculkCatalyst.class, CraftSculkCatalyst::new, SculkCatalystBlockEntity::new);
        CraftBlockStates.register(Material.CALIBRATED_SCULK_SENSOR, CraftCalibratedSculkSensor.class, CraftCalibratedSculkSensor::new, CalibratedSculkSensorBlockEntity::new);
        CraftBlockStates.register(Material.SCULK_SENSOR, CraftSculkSensor.class, CraftSculkSensor::new, SculkSensorBlockEntity::new);
        CraftBlockStates.register(Material.SCULK_SHRIEKER, CraftSculkShrieker.class, CraftSculkShrieker::new, SculkShriekerBlockEntity::new);
        CraftBlockStates.register(Material.SMOKER, CraftSmoker.class, CraftSmoker::new, SmokerBlockEntity::new);
        CraftBlockStates.register(Material.SPAWNER, CraftCreatureSpawner.class, CraftCreatureSpawner::new, SpawnerBlockEntity::new);
        CraftBlockStates.register(Material.STRUCTURE_BLOCK, CraftStructureBlock.class, CraftStructureBlock::new, StructureBlockEntity::new);
        CraftBlockStates.register(Material.SUSPICIOUS_SAND, CraftSuspiciousSand.class, CraftSuspiciousSand::new, BrushableBlockEntity::new);
        CraftBlockStates.register(Material.SUSPICIOUS_GRAVEL, CraftBrushableBlock.class, CraftBrushableBlock::new, BrushableBlockEntity::new);
        CraftBlockStates.register(Material.TRAPPED_CHEST, CraftChest.class, CraftChest::new, TrappedChestBlockEntity::new);
    }

    private static void register(Material blockType, BlockStateFactory<?> factory) {
        FACTORIES.put(blockType, factory);
    }

    private static <T extends BlockEntity, B extends CraftBlockEntityState<T>> void register(Material blockType, Class<B> blockStateType, BiFunction<World, T, B> blockStateConstructor, BiFunction<BlockPos, net.minecraft.world.level.block.state.BlockState, T> tileEntityConstructor) {
        CraftBlockStates.register(Collections.singletonList(blockType), blockStateType, blockStateConstructor, tileEntityConstructor);
    }

    private static <T extends BlockEntity, B extends CraftBlockEntityState<T>> void register(List<Material> blockTypes, Class<B> blockStateType, BiFunction<World, T, B> blockStateConstructor, BiFunction<BlockPos, net.minecraft.world.level.block.state.BlockState, T> tileEntityConstructor) {
        BlockEntityStateFactory<T, B> factory = new BlockEntityStateFactory<T, B>(blockStateType, blockStateConstructor, tileEntityConstructor);
        for (Material blockType : blockTypes) {
            CraftBlockStates.register(blockType, factory);
        }
    }

    private static BlockStateFactory<?> getFactory(Material material) {
        return FACTORIES.getOrDefault(material, DEFAULT_FACTORY);
    }

    public static Class<? extends CraftBlockState> getBlockStateType(Material material) {
        Preconditions.checkNotNull((Object)material, (Object)"material is null");
        return CraftBlockStates.getFactory((Material)material).blockStateType;
    }

    public static BlockEntity createNewTileEntity(Material material) {
        BlockStateFactory<?> factory = CraftBlockStates.getFactory(material);
        if (factory instanceof BlockEntityStateFactory) {
            return ((BlockEntityStateFactory)factory).createTileEntity(BlockPos.f_121853_, CraftMagicNumbers.getBlock(material).m_49966_());
        }
        return null;
    }

    public static BlockState getBlockState(Block block) {
        Preconditions.checkNotNull((Object)block, (Object)"block is null");
        CraftBlock craftBlock = (CraftBlock)block;
        CraftWorld world = (CraftWorld)block.getWorld();
        BlockPos blockPosition = craftBlock.getPosition();
        net.minecraft.world.level.block.state.BlockState blockData = craftBlock.getNMS();
        BlockEntity tileEntity = craftBlock.getHandle().m_7702_(blockPosition);
        CraftBlockState blockState = CraftBlockStates.getBlockState(world, blockPosition, blockData, tileEntity);
        blockState.setWorldHandle(craftBlock.getHandle());
        return blockState;
    }

    public static BlockState getBlockState(Material material, @Nullable CompoundTag blockEntityTag) {
        return CraftBlockStates.getBlockState(BlockPos.f_121853_, material, blockEntityTag);
    }

    public static BlockState getBlockState(BlockPos blockPosition, Material material, @Nullable CompoundTag blockEntityTag) {
        Preconditions.checkNotNull((Object)material, (Object)"material is null");
        net.minecraft.world.level.block.state.BlockState blockData = CraftMagicNumbers.getBlock(material).m_49966_();
        return CraftBlockStates.getBlockState(blockPosition, blockData, blockEntityTag);
    }

    public static BlockState getBlockState(net.minecraft.world.level.block.state.BlockState blockData, @Nullable CompoundTag blockEntityTag) {
        return CraftBlockStates.getBlockState(BlockPos.f_121853_, blockData, blockEntityTag);
    }

    public static BlockState getBlockState(BlockPos blockPosition, net.minecraft.world.level.block.state.BlockState blockData, @Nullable CompoundTag blockEntityTag) {
        Preconditions.checkNotNull((Object)blockPosition, (Object)"blockPosition is null");
        Preconditions.checkNotNull((Object)blockData, (Object)"blockData is null");
        BlockEntity tileEntity = blockEntityTag == null ? null : BlockEntity.m_155241_((BlockPos)blockPosition, (net.minecraft.world.level.block.state.BlockState)blockData, (CompoundTag)blockEntityTag);
        return CraftBlockStates.getBlockState(null, blockPosition, blockData, tileEntity);
    }

    private static CraftBlockState getBlockState(World world, BlockPos blockPosition, net.minecraft.world.level.block.state.BlockState blockData, BlockEntity tileEntity) {
        Material material = CraftMagicNumbers.getMaterial(blockData.m_60734_());
        BlockStateFactory<?> factory = world != null && tileEntity == null && CraftBlockStates.isTileEntityOptional(material) ? DEFAULT_FACTORY : CraftBlockStates.getFactory(material);
        return factory.createBlockState(world, blockPosition, blockData, tileEntity);
    }

    public static boolean isTileEntityOptional(Material material) {
        return material == Material.MOVING_PISTON;
    }

    public static CraftBlockState getBlockState(LevelAccessor world, BlockPos pos) {
        return new CraftBlockState(CraftBlock.at(world, pos));
    }

    public static CraftBlockState getBlockState(LevelAccessor world, BlockPos pos, int flag) {
        return new CraftBlockState(CraftBlock.at(world, pos), flag);
    }

    private CraftBlockStates() {
    }

    private static class BlockEntityStateFactory<T extends BlockEntity, B extends CraftBlockEntityState<T>>
    extends BlockStateFactory<B> {
        private final BiFunction<World, T, B> blockStateConstructor;
        private final BiFunction<BlockPos, net.minecraft.world.level.block.state.BlockState, T> tileEntityConstructor;

        protected BlockEntityStateFactory(Class<B> blockStateType, BiFunction<World, T, B> blockStateConstructor, BiFunction<BlockPos, net.minecraft.world.level.block.state.BlockState, T> tileEntityConstructor) {
            super(blockStateType);
            this.blockStateConstructor = blockStateConstructor;
            this.tileEntityConstructor = tileEntityConstructor;
        }

        @Override
        public final B createBlockState(World world, BlockPos blockPosition, net.minecraft.world.level.block.state.BlockState blockData, BlockEntity tileEntity) {
            if (world != null) {
                Preconditions.checkState((tileEntity != null ? 1 : 0) != 0, (String)"Tile is null, asynchronous access? %s", (Object)CraftBlock.at((LevelAccessor)((CraftWorld)world).getHandle(), blockPosition));
            } else if (tileEntity == null) {
                tileEntity = this.createTileEntity(blockPosition, blockData);
            }
            return this.createBlockState(world, tileEntity);
        }

        private T createTileEntity(BlockPos blockPosition, net.minecraft.world.level.block.state.BlockState blockData) {
            return (T)((BlockEntity)this.tileEntityConstructor.apply(blockPosition, blockData));
        }

        private B createBlockState(World world, T tileEntity) {
            return (B)((CraftBlockEntityState)this.blockStateConstructor.apply(world, (World)tileEntity));
        }
    }

    private static abstract class BlockStateFactory<B extends CraftBlockState> {
        public final Class<B> blockStateType;

        public BlockStateFactory(Class<B> blockStateType) {
            this.blockStateType = blockStateType;
        }

        public abstract B createBlockState(World var1, BlockPos var2, net.minecraft.world.level.block.state.BlockState var3, BlockEntity var4);
    }
}

