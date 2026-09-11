/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.ImmutableSet$Builder
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.arguments.blocks.BlockStateParser
 *  net.minecraft.commands.arguments.blocks.BlockStateParser$BlockResult
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.HolderLookup
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.EmptyBlockGetter
 *  net.minecraft.world.level.LevelReader
 *  net.minecraft.world.level.block.AmethystClusterBlock
 *  net.minecraft.world.level.block.AnvilBlock
 *  net.minecraft.world.level.block.AttachedStemBlock
 *  net.minecraft.world.level.block.BambooStalkBlock
 *  net.minecraft.world.level.block.BannerBlock
 *  net.minecraft.world.level.block.BarrelBlock
 *  net.minecraft.world.level.block.BaseCoralFanBlock
 *  net.minecraft.world.level.block.BaseCoralPlantBlock
 *  net.minecraft.world.level.block.BaseCoralWallFanBlock
 *  net.minecraft.world.level.block.BedBlock
 *  net.minecraft.world.level.block.BeehiveBlock
 *  net.minecraft.world.level.block.BeetrootBlock
 *  net.minecraft.world.level.block.BellBlock
 *  net.minecraft.world.level.block.BigDripleafBlock
 *  net.minecraft.world.level.block.BigDripleafStemBlock
 *  net.minecraft.world.level.block.BlastFurnaceBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.BrewingStandBlock
 *  net.minecraft.world.level.block.BrushableBlock
 *  net.minecraft.world.level.block.BubbleColumnBlock
 *  net.minecraft.world.level.block.ButtonBlock
 *  net.minecraft.world.level.block.CactusBlock
 *  net.minecraft.world.level.block.CakeBlock
 *  net.minecraft.world.level.block.CalibratedSculkSensorBlock
 *  net.minecraft.world.level.block.CampfireBlock
 *  net.minecraft.world.level.block.CandleBlock
 *  net.minecraft.world.level.block.CandleCakeBlock
 *  net.minecraft.world.level.block.CarrotBlock
 *  net.minecraft.world.level.block.CarvedPumpkinBlock
 *  net.minecraft.world.level.block.CaveVinesBlock
 *  net.minecraft.world.level.block.CaveVinesPlantBlock
 *  net.minecraft.world.level.block.CeilingHangingSignBlock
 *  net.minecraft.world.level.block.ChainBlock
 *  net.minecraft.world.level.block.CherryLeavesBlock
 *  net.minecraft.world.level.block.ChestBlock
 *  net.minecraft.world.level.block.ChiseledBookShelfBlock
 *  net.minecraft.world.level.block.ChorusFlowerBlock
 *  net.minecraft.world.level.block.ChorusPlantBlock
 *  net.minecraft.world.level.block.CocoaBlock
 *  net.minecraft.world.level.block.CommandBlock
 *  net.minecraft.world.level.block.ComparatorBlock
 *  net.minecraft.world.level.block.ComposterBlock
 *  net.minecraft.world.level.block.ConduitBlock
 *  net.minecraft.world.level.block.CoralFanBlock
 *  net.minecraft.world.level.block.CoralPlantBlock
 *  net.minecraft.world.level.block.CoralWallFanBlock
 *  net.minecraft.world.level.block.CropBlock
 *  net.minecraft.world.level.block.DaylightDetectorBlock
 *  net.minecraft.world.level.block.DecoratedPotBlock
 *  net.minecraft.world.level.block.DetectorRailBlock
 *  net.minecraft.world.level.block.DispenserBlock
 *  net.minecraft.world.level.block.DoorBlock
 *  net.minecraft.world.level.block.DoublePlantBlock
 *  net.minecraft.world.level.block.DropperBlock
 *  net.minecraft.world.level.block.EndPortalFrameBlock
 *  net.minecraft.world.level.block.EndRodBlock
 *  net.minecraft.world.level.block.EnderChestBlock
 *  net.minecraft.world.level.block.EquipableCarvedPumpkinBlock
 *  net.minecraft.world.level.block.FarmBlock
 *  net.minecraft.world.level.block.FenceBlock
 *  net.minecraft.world.level.block.FenceGateBlock
 *  net.minecraft.world.level.block.FireBlock
 *  net.minecraft.world.level.block.FrostedIceBlock
 *  net.minecraft.world.level.block.FurnaceBlock
 *  net.minecraft.world.level.block.GlazedTerracottaBlock
 *  net.minecraft.world.level.block.GlowLichenBlock
 *  net.minecraft.world.level.block.GrassBlock
 *  net.minecraft.world.level.block.GrindstoneBlock
 *  net.minecraft.world.level.block.HangingRootsBlock
 *  net.minecraft.world.level.block.HayBlock
 *  net.minecraft.world.level.block.HopperBlock
 *  net.minecraft.world.level.block.HugeMushroomBlock
 *  net.minecraft.world.level.block.InfestedRotatedPillarBlock
 *  net.minecraft.world.level.block.IronBarsBlock
 *  net.minecraft.world.level.block.JigsawBlock
 *  net.minecraft.world.level.block.JukeboxBlock
 *  net.minecraft.world.level.block.KelpBlock
 *  net.minecraft.world.level.block.LadderBlock
 *  net.minecraft.world.level.block.LanternBlock
 *  net.minecraft.world.level.block.LayeredCauldronBlock
 *  net.minecraft.world.level.block.LeavesBlock
 *  net.minecraft.world.level.block.LecternBlock
 *  net.minecraft.world.level.block.LeverBlock
 *  net.minecraft.world.level.block.LightBlock
 *  net.minecraft.world.level.block.LightningRodBlock
 *  net.minecraft.world.level.block.LiquidBlock
 *  net.minecraft.world.level.block.LoomBlock
 *  net.minecraft.world.level.block.MangroveLeavesBlock
 *  net.minecraft.world.level.block.MangrovePropaguleBlock
 *  net.minecraft.world.level.block.MangroveRootsBlock
 *  net.minecraft.world.level.block.Mirror
 *  net.minecraft.world.level.block.MyceliumBlock
 *  net.minecraft.world.level.block.NetherPortalBlock
 *  net.minecraft.world.level.block.NetherWartBlock
 *  net.minecraft.world.level.block.NoteBlock
 *  net.minecraft.world.level.block.ObserverBlock
 *  net.minecraft.world.level.block.PiglinWallSkullBlock
 *  net.minecraft.world.level.block.PinkPetalsBlock
 *  net.minecraft.world.level.block.PitcherCropBlock
 *  net.minecraft.world.level.block.PlayerHeadBlock
 *  net.minecraft.world.level.block.PlayerWallHeadBlock
 *  net.minecraft.world.level.block.PointedDripstoneBlock
 *  net.minecraft.world.level.block.PotatoBlock
 *  net.minecraft.world.level.block.PowderSnowCauldronBlock
 *  net.minecraft.world.level.block.PoweredRailBlock
 *  net.minecraft.world.level.block.PressurePlateBlock
 *  net.minecraft.world.level.block.RailBlock
 *  net.minecraft.world.level.block.RedStoneOreBlock
 *  net.minecraft.world.level.block.RedStoneWireBlock
 *  net.minecraft.world.level.block.RedstoneLampBlock
 *  net.minecraft.world.level.block.RedstoneTorchBlock
 *  net.minecraft.world.level.block.RedstoneWallTorchBlock
 *  net.minecraft.world.level.block.RepeaterBlock
 *  net.minecraft.world.level.block.RespawnAnchorBlock
 *  net.minecraft.world.level.block.RotatedPillarBlock
 *  net.minecraft.world.level.block.Rotation
 *  net.minecraft.world.level.block.SaplingBlock
 *  net.minecraft.world.level.block.ScaffoldingBlock
 *  net.minecraft.world.level.block.SculkCatalystBlock
 *  net.minecraft.world.level.block.SculkSensorBlock
 *  net.minecraft.world.level.block.SculkShriekerBlock
 *  net.minecraft.world.level.block.SculkVeinBlock
 *  net.minecraft.world.level.block.SeaPickleBlock
 *  net.minecraft.world.level.block.ShulkerBoxBlock
 *  net.minecraft.world.level.block.SkullBlock
 *  net.minecraft.world.level.block.SlabBlock
 *  net.minecraft.world.level.block.SmallDripleafBlock
 *  net.minecraft.world.level.block.SmokerBlock
 *  net.minecraft.world.level.block.SnifferEggBlock
 *  net.minecraft.world.level.block.SnowLayerBlock
 *  net.minecraft.world.level.block.SnowyDirtBlock
 *  net.minecraft.world.level.block.StainedGlassPaneBlock
 *  net.minecraft.world.level.block.StairBlock
 *  net.minecraft.world.level.block.StandingSignBlock
 *  net.minecraft.world.level.block.StemBlock
 *  net.minecraft.world.level.block.StonecutterBlock
 *  net.minecraft.world.level.block.StructureBlock
 *  net.minecraft.world.level.block.SugarCaneBlock
 *  net.minecraft.world.level.block.SweetBerryBushBlock
 *  net.minecraft.world.level.block.TallFlowerBlock
 *  net.minecraft.world.level.block.TallSeagrassBlock
 *  net.minecraft.world.level.block.TargetBlock
 *  net.minecraft.world.level.block.TntBlock
 *  net.minecraft.world.level.block.TorchflowerCropBlock
 *  net.minecraft.world.level.block.TrapDoorBlock
 *  net.minecraft.world.level.block.TrappedChestBlock
 *  net.minecraft.world.level.block.TripWireBlock
 *  net.minecraft.world.level.block.TripWireHookBlock
 *  net.minecraft.world.level.block.TurtleEggBlock
 *  net.minecraft.world.level.block.TwistingVinesBlock
 *  net.minecraft.world.level.block.VineBlock
 *  net.minecraft.world.level.block.WallBannerBlock
 *  net.minecraft.world.level.block.WallBlock
 *  net.minecraft.world.level.block.WallHangingSignBlock
 *  net.minecraft.world.level.block.WallSignBlock
 *  net.minecraft.world.level.block.WallSkullBlock
 *  net.minecraft.world.level.block.WallTorchBlock
 *  net.minecraft.world.level.block.WeatheringCopperSlabBlock
 *  net.minecraft.world.level.block.WeatheringCopperStairBlock
 *  net.minecraft.world.level.block.WeepingVinesBlock
 *  net.minecraft.world.level.block.WeightedPressurePlateBlock
 *  net.minecraft.world.level.block.WitherSkullBlock
 *  net.minecraft.world.level.block.WitherWallSkullBlock
 *  net.minecraft.world.level.block.piston.MovingPistonBlock
 *  net.minecraft.world.level.block.piston.PistonBaseBlock
 *  net.minecraft.world.level.block.piston.PistonHeadBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateHolder
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 *  net.minecraft.world.level.block.state.properties.EnumProperty
 *  net.minecraft.world.level.block.state.properties.IntegerProperty
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1.block.data;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.commands.arguments.blocks.BlockStateParser;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.EmptyBlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.AnvilBlock;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.BaseCoralFanBlock;
import net.minecraft.world.level.block.BaseCoralPlantBlock;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.BigDripleafBlock;
import net.minecraft.world.level.block.BigDripleafStemBlock;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BrewingStandBlock;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.BubbleColumnBlock;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.CalibratedSculkSensorBlock;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.CandleBlock;
import net.minecraft.world.level.block.CandleCakeBlock;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.CaveVinesBlock;
import net.minecraft.world.level.block.CaveVinesPlantBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.ChainBlock;
import net.minecraft.world.level.block.CherryLeavesBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.ChorusFlowerBlock;
import net.minecraft.world.level.block.ChorusPlantBlock;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CommandBlock;
import net.minecraft.world.level.block.ComparatorBlock;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.ConduitBlock;
import net.minecraft.world.level.block.CoralFanBlock;
import net.minecraft.world.level.block.CoralPlantBlock;
import net.minecraft.world.level.block.CoralWallFanBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DaylightDetectorBlock;
import net.minecraft.world.level.block.DecoratedPotBlock;
import net.minecraft.world.level.block.DetectorRailBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.DropperBlock;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.EnderChestBlock;
import net.minecraft.world.level.block.EquipableCarvedPumpkinBlock;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.GlazedTerracottaBlock;
import net.minecraft.world.level.block.GlowLichenBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.GrindstoneBlock;
import net.minecraft.world.level.block.HangingRootsBlock;
import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.HopperBlock;
import net.minecraft.world.level.block.HugeMushroomBlock;
import net.minecraft.world.level.block.InfestedRotatedPillarBlock;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.JigsawBlock;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.KelpBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.LoomBlock;
import net.minecraft.world.level.block.MangroveLeavesBlock;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.MangroveRootsBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.MyceliumBlock;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.block.PiglinWallSkullBlock;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.PitcherCropBlock;
import net.minecraft.world.level.block.PlayerHeadBlock;
import net.minecraft.world.level.block.PlayerWallHeadBlock;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.PowderSnowCauldronBlock;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RailBlock;
import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.RedStoneWireBlock;
import net.minecraft.world.level.block.RedstoneLampBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.RedstoneWallTorchBlock;
import net.minecraft.world.level.block.RepeaterBlock;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.ScaffoldingBlock;
import net.minecraft.world.level.block.SculkCatalystBlock;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.SculkVeinBlock;
import net.minecraft.world.level.block.SeaPickleBlock;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SmallDripleafBlock;
import net.minecraft.world.level.block.SmokerBlock;
import net.minecraft.world.level.block.SnifferEggBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.SnowyDirtBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.StonecutterBlock;
import net.minecraft.world.level.block.StructureBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.TallSeagrassBlock;
import net.minecraft.world.level.block.TargetBlock;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.TorchflowerCropBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.TrappedChestBlock;
import net.minecraft.world.level.block.TripWireBlock;
import net.minecraft.world.level.block.TripWireHookBlock;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.TwistingVinesBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.WallBannerBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WallSkullBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.WeatheringCopperSlabBlock;
import net.minecraft.world.level.block.WeatheringCopperStairBlock;
import net.minecraft.world.level.block.WeepingVinesBlock;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraft.world.level.block.WitherSkullBlock;
import net.minecraft.world.level.block.WitherWallSkullBlock;
import net.minecraft.world.level.block.piston.MovingPistonBlock;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.piston.PistonHeadBlock;
import net.minecraft.world.level.block.state.StateHolder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SoundGroup;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.BlockSupport;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.structure.StructureRotation;
import org.bukkit.craftbukkit.v1_20_R1.CraftSoundGroup;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockStates;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockSupport;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftAmethystCluster;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftAnvil;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBamboo;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBanner;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBannerWall;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBarrel;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBed;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBeehive;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBeetroot;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBell;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBigDripleaf;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBigDripleafStem;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBlastFurnace;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBrewingStand;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBrushable;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftBubbleColumn;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftButtonAbstract;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCactus;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCake;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCalibratedSculkSensor;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCampfire;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCandle;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCandleCake;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCarrots;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCaveVines;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCaveVinesPlant;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCeilingHangingSign;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftChain;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCherryLeaves;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftChest;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftChestTrapped;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftChiseledBookShelf;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftChorusFlower;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftChorusFruit;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCobbleWall;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCocoa;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCommand;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftComposter;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftConduit;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCoralDead;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCoralFan;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCoralFanAbstract;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCoralFanWall;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCoralFanWallAbstract;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCoralPlant;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftCrops;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftDaylightDetector;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftDecoratedPot;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftDirtSnow;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftDispenser;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftDoor;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftDropper;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftEndRod;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftEnderChest;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftEnderPortalFrame;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftEquipableCarvedPumpkin;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftFence;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftFenceGate;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftFire;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftFloorSign;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftFluids;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftFurnaceFurace;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftGlazedTerracotta;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftGlowLichen;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftGrass;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftGrindstone;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftHangingRoots;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftHay;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftHopper;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftHugeMushroom;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftIceFrost;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftInfestedRotatedPillar;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftIronBars;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftJigsaw;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftJukeBox;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftKelp;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLadder;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLantern;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLayeredCauldron;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLeaves;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLectern;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLever;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLight;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLightningRod;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftLoom;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftMangroveLeaves;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftMangrovePropagule;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftMangroveRoots;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftMinecartDetector;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftMinecartTrack;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftMycel;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftNetherWart;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftNote;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftObserver;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPiglinWallSkull;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPinkPetals;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPiston;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPistonExtension;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPistonMoving;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPitcherCrop;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPointedDripstone;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPortal;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPotatoes;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPowderSnowCauldron;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPoweredRail;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPressurePlateBinary;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPressurePlateWeighted;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftPumpkinCarved;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRedstoneComparator;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRedstoneLamp;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRedstoneOre;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRedstoneTorch;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRedstoneTorchWall;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRedstoneWire;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftReed;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRepeater;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRespawnAnchor;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftRotatable;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSapling;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftScaffolding;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSculkCatalyst;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSculkSensor;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSculkShrieker;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSculkVein;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSeaPickle;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftShulkerBox;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSkull;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSkullPlayer;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSkullPlayerWall;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSkullWall;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSmallDripleaf;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSmoker;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSnifferEgg;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSnow;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSoil;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftStainedGlassPane;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftStairs;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftStem;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftStemAttached;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftStepAbstract;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftStonecutter;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftStructure;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftSweetBerryBush;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTNT;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTallPlant;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTallPlantFlower;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTallSeagrass;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTarget;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTorchWall;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTorchflowerCrop;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTrapdoor;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTripwire;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTripwireHook;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTurtleEgg;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftTwistingVines;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftVine;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftWallHangingSign;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftWallSign;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftWeatheringCopperSlab;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftWeatheringCopperStair;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftWeepingVines;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftWitherSkull;
import org.bukkit.craftbukkit.v1_20_R1.block.impl.CraftWitherSkullWall;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class CraftBlockData
implements BlockData {
    private net.minecraft.world.level.block.state.BlockState state;
    private Map<Property<?>, Comparable<?>> parsedStates;
    private static final Map<Class<? extends Enum<?>>, Enum<?>[]> ENUM_VALUES = new HashMap();
    private static final Map<Class<? extends Block>, Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>> MAP = new HashMap<Class<? extends Block>, Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>>();

    static {
        CraftBlockData.register(AmethystClusterBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftAmethystCluster::new));
        CraftBlockData.register(BigDripleafBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBigDripleaf::new));
        CraftBlockData.register(BigDripleafStemBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBigDripleafStem::new));
        CraftBlockData.register(AnvilBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftAnvil::new));
        CraftBlockData.register(BambooStalkBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBamboo::new));
        CraftBlockData.register(BannerBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBanner::new));
        CraftBlockData.register(WallBannerBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBannerWall::new));
        CraftBlockData.register(BarrelBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBarrel::new));
        CraftBlockData.register(BedBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBed::new));
        CraftBlockData.register(BeehiveBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBeehive::new));
        CraftBlockData.register(BeetrootBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBeetroot::new));
        CraftBlockData.register(BellBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBell::new));
        CraftBlockData.register(BlastFurnaceBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBlastFurnace::new));
        CraftBlockData.register(BrewingStandBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBrewingStand::new));
        CraftBlockData.register(BubbleColumnBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBubbleColumn::new));
        CraftBlockData.register(ButtonBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftButtonAbstract::new));
        CraftBlockData.register(CactusBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCactus::new));
        CraftBlockData.register(CakeBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCake::new));
        CraftBlockData.register(CampfireBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCampfire::new));
        CraftBlockData.register(CarrotBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCarrots::new));
        CraftBlockData.register(ChainBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftChain::new));
        CraftBlockData.register(ChestBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftChest::new));
        CraftBlockData.register(TrappedChestBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftChestTrapped::new));
        CraftBlockData.register(ChorusFlowerBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftChorusFlower::new));
        CraftBlockData.register(ChorusPlantBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftChorusFruit::new));
        CraftBlockData.register(WallBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCobbleWall::new));
        CraftBlockData.register(CocoaBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCocoa::new));
        CraftBlockData.register(CommandBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCommand::new));
        CraftBlockData.register(ComposterBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftComposter::new));
        CraftBlockData.register(ConduitBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftConduit::new));
        CraftBlockData.register(BaseCoralPlantBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCoralDead::new));
        CraftBlockData.register(CoralFanBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCoralFan::new));
        CraftBlockData.register(BaseCoralFanBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCoralFanAbstract::new));
        CraftBlockData.register(CoralWallFanBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCoralFanWall::new));
        CraftBlockData.register(BaseCoralWallFanBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCoralFanWallAbstract::new));
        CraftBlockData.register(CoralPlantBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCoralPlant::new));
        CraftBlockData.register(CropBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCrops::new));
        CraftBlockData.register(DaylightDetectorBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftDaylightDetector::new));
        CraftBlockData.register(SnowyDirtBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftDirtSnow::new));
        CraftBlockData.register(DispenserBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftDispenser::new));
        CraftBlockData.register(DoorBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftDoor::new));
        CraftBlockData.register(DropperBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftDropper::new));
        CraftBlockData.register(EndRodBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftEndRod::new));
        CraftBlockData.register(EnderChestBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftEnderChest::new));
        CraftBlockData.register(EndPortalFrameBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftEnderPortalFrame::new));
        CraftBlockData.register(FenceBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftFence::new));
        CraftBlockData.register(FenceGateBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftFenceGate::new));
        CraftBlockData.register(FireBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftFire::new));
        CraftBlockData.register(StandingSignBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftFloorSign::new));
        CraftBlockData.register(LiquidBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftFluids::new));
        CraftBlockData.register(FurnaceBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftFurnaceFurace::new));
        CraftBlockData.register(GlazedTerracottaBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftGlazedTerracotta::new));
        CraftBlockData.register(GrassBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftGrass::new));
        CraftBlockData.register(GrindstoneBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftGrindstone::new));
        CraftBlockData.register(HayBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftHay::new));
        CraftBlockData.register(HopperBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftHopper::new));
        CraftBlockData.register(HugeMushroomBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftHugeMushroom::new));
        CraftBlockData.register(FrostedIceBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftIceFrost::new));
        CraftBlockData.register(IronBarsBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftIronBars::new));
        CraftBlockData.register(JigsawBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftJigsaw::new));
        CraftBlockData.register(JukeboxBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftJukeBox::new));
        CraftBlockData.register(KelpBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftKelp::new));
        CraftBlockData.register(LadderBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLadder::new));
        CraftBlockData.register(LanternBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLantern::new));
        CraftBlockData.register(LeavesBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLeaves::new));
        CraftBlockData.register(LecternBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLectern::new));
        CraftBlockData.register(LeverBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLever::new));
        CraftBlockData.register(LoomBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLoom::new));
        CraftBlockData.register(DetectorRailBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftMinecartDetector::new));
        CraftBlockData.register(RailBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftMinecartTrack::new));
        CraftBlockData.register(MyceliumBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftMycel::new));
        CraftBlockData.register(NetherWartBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftNetherWart::new));
        CraftBlockData.register(NoteBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftNote::new));
        CraftBlockData.register(ObserverBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftObserver::new));
        CraftBlockData.register(NetherPortalBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPortal::new));
        CraftBlockData.register(PotatoBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPotatoes::new));
        CraftBlockData.register(PoweredRailBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPoweredRail::new));
        CraftBlockData.register(PressurePlateBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPressurePlateBinary::new));
        CraftBlockData.register(WeightedPressurePlateBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPressurePlateWeighted::new));
        CraftBlockData.register(CarvedPumpkinBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPumpkinCarved::new));
        CraftBlockData.register(ComparatorBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRedstoneComparator::new));
        CraftBlockData.register(RedstoneLampBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRedstoneLamp::new));
        CraftBlockData.register(RedStoneOreBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRedstoneOre::new));
        CraftBlockData.register(RedstoneTorchBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRedstoneTorch::new));
        CraftBlockData.register(RedstoneWallTorchBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRedstoneTorchWall::new));
        CraftBlockData.register(RedStoneWireBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRedstoneWire::new));
        CraftBlockData.register(SugarCaneBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftReed::new));
        CraftBlockData.register(RepeaterBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRepeater::new));
        CraftBlockData.register(RespawnAnchorBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRespawnAnchor::new));
        CraftBlockData.register(RotatedPillarBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftRotatable::new));
        CraftBlockData.register(SaplingBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSapling::new));
        CraftBlockData.register(ScaffoldingBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftScaffolding::new));
        CraftBlockData.register(SeaPickleBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSeaPickle::new));
        CraftBlockData.register(ShulkerBoxBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftShulkerBox::new));
        CraftBlockData.register(SkullBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSkull::new));
        CraftBlockData.register(PlayerHeadBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSkullPlayer::new));
        CraftBlockData.register(PlayerWallHeadBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSkullPlayerWall::new));
        CraftBlockData.register(WallSkullBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSkullWall::new));
        CraftBlockData.register(SmokerBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSmoker::new));
        CraftBlockData.register(SnowLayerBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSnow::new));
        CraftBlockData.register(FarmBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSoil::new));
        CraftBlockData.register(StainedGlassPaneBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftStainedGlassPane::new));
        CraftBlockData.register(StairBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftStairs::new));
        CraftBlockData.register(StemBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftStem::new));
        CraftBlockData.register(AttachedStemBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftStemAttached::new));
        CraftBlockData.register(SlabBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftStepAbstract::new));
        CraftBlockData.register(StonecutterBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftStonecutter::new));
        CraftBlockData.register(StructureBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftStructure::new));
        CraftBlockData.register(SweetBerryBushBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSweetBerryBush::new));
        CraftBlockData.register(TntBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTNT::new));
        CraftBlockData.register(DoublePlantBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTallPlant::new));
        CraftBlockData.register(TallFlowerBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTallPlantFlower::new));
        CraftBlockData.register(TargetBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTarget::new));
        CraftBlockData.register(WallTorchBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTorchWall::new));
        CraftBlockData.register(TrapDoorBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTrapdoor::new));
        CraftBlockData.register(TripWireBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTripwire::new));
        CraftBlockData.register(TripWireHookBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTripwireHook::new));
        CraftBlockData.register(TurtleEggBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTurtleEgg::new));
        CraftBlockData.register(TwistingVinesBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTwistingVines::new));
        CraftBlockData.register(VineBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftVine::new));
        CraftBlockData.register(WallSignBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftWallSign::new));
        CraftBlockData.register(WeepingVinesBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftWeepingVines::new));
        CraftBlockData.register(WitherSkullBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftWitherSkull::new));
        CraftBlockData.register(WitherWallSkullBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftWitherSkullWall::new));
        CraftBlockData.register(BrushableBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBrushable::new));
        CraftBlockData.register(CalibratedSculkSensorBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCalibratedSculkSensor::new));
        CraftBlockData.register(CandleBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCandle::new));
        CraftBlockData.register(CandleCakeBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCandleCake::new));
        CraftBlockData.register(CaveVinesBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCaveVines::new));
        CraftBlockData.register(CaveVinesPlantBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCaveVinesPlant::new));
        CraftBlockData.register(CeilingHangingSignBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCeilingHangingSign::new));
        CraftBlockData.register(CherryLeavesBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftCherryLeaves::new));
        CraftBlockData.register(ChiseledBookShelfBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftChiseledBookShelf::new));
        CraftBlockData.register(DecoratedPotBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftDecoratedPot::new));
        CraftBlockData.register(EquipableCarvedPumpkinBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftEquipableCarvedPumpkin::new));
        CraftBlockData.register(GlowLichenBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftGlowLichen::new));
        CraftBlockData.register(HangingRootsBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftHangingRoots::new));
        CraftBlockData.register(InfestedRotatedPillarBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftInfestedRotatedPillar::new));
        CraftBlockData.register(LayeredCauldronBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLayeredCauldron::new));
        CraftBlockData.register(LightBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLight::new));
        CraftBlockData.register(LightningRodBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftLightningRod::new));
        CraftBlockData.register(MangroveLeavesBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftMangroveLeaves::new));
        CraftBlockData.register(MangrovePropaguleBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftMangrovePropagule::new));
        CraftBlockData.register(MangroveRootsBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftMangroveRoots::new));
        CraftBlockData.register(PiglinWallSkullBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPiglinWallSkull::new));
        CraftBlockData.register(PinkPetalsBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPinkPetals::new));
        CraftBlockData.register(PitcherCropBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPitcherCrop::new));
        CraftBlockData.register(PointedDripstoneBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPointedDripstone::new));
        CraftBlockData.register(PowderSnowCauldronBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPowderSnowCauldron::new));
        CraftBlockData.register(SculkCatalystBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSculkCatalyst::new));
        CraftBlockData.register(SculkSensorBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSculkSensor::new));
        CraftBlockData.register(SculkShriekerBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSculkShrieker::new));
        CraftBlockData.register(SculkVeinBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSculkVein::new));
        CraftBlockData.register(SmallDripleafBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSmallDripleaf::new));
        CraftBlockData.register(SnifferEggBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftSnifferEgg::new));
        CraftBlockData.register(TallSeagrassBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTallSeagrass::new));
        CraftBlockData.register(TorchflowerCropBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftTorchflowerCrop::new));
        CraftBlockData.register(WallHangingSignBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftWallHangingSign::new));
        CraftBlockData.register(WeatheringCopperSlabBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftWeatheringCopperSlab::new));
        CraftBlockData.register(WeatheringCopperStairBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftWeatheringCopperStair::new));
        CraftBlockData.register(PistonBaseBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPiston::new));
        CraftBlockData.register(PistonHeadBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPistonExtension::new));
        CraftBlockData.register(MovingPistonBlock.class, (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftPistonMoving::new));
    }

    protected CraftBlockData() {
        throw new AssertionError((Object)"Template Constructor");
    }

    protected CraftBlockData(net.minecraft.world.level.block.state.BlockState state) {
        this.state = state;
    }

    @Override
    public Material getMaterial() {
        return CraftMagicNumbers.getMaterial(this.state.m_60734_());
    }

    public net.minecraft.world.level.block.state.BlockState getState() {
        return this.state;
    }

    protected <B extends Enum<B>> B get(EnumProperty<?> nms, Class<B> bukkit) {
        return CraftBlockData.toBukkit((Enum)((Object)this.state.m_61143_(nms)), bukkit);
    }

    protected <B extends Enum<B>> Set<B> getValues(EnumProperty<?> nms, Class<B> bukkit) {
        ImmutableSet.Builder values = ImmutableSet.builder();
        for (Enum e : nms.m_6908_()) {
            values.add(CraftBlockData.toBukkit(e, bukkit));
        }
        return values.build();
    }

    protected <B extends Enum<B>, N extends Enum<N>> void set(EnumProperty<N> nms, Enum<B> bukkit) {
        this.parsedStates = null;
        this.state = (net.minecraft.world.level.block.state.BlockState)this.state.m_61124_(nms, CraftBlockData.toNMS(bukkit, nms.m_61709_()));
    }

    @Override
    public BlockData merge(BlockData data) {
        CraftBlockData craft = (CraftBlockData)data;
        Preconditions.checkArgument((craft.parsedStates != null ? 1 : 0) != 0, (Object)"Data not created via string parsing");
        Preconditions.checkArgument((this.state.m_60734_() == craft.state.m_60734_() ? 1 : 0) != 0, (String)"States have different types (got %s, expected %s)", (Object)data, (Object)this);
        CraftBlockData clone = (CraftBlockData)this.clone();
        clone.parsedStates = null;
        for (Property<?> parsed : craft.parsedStates.keySet()) {
            clone.state = (net.minecraft.world.level.block.state.BlockState)clone.state.m_61124_(parsed, craft.state.m_61143_(parsed));
        }
        return clone;
    }

    @Override
    public boolean matches(BlockData data) {
        if (data == null) {
            return false;
        }
        if (!(data instanceof CraftBlockData)) {
            return false;
        }
        CraftBlockData craft = (CraftBlockData)data;
        if (this.state.m_60734_() != craft.state.m_60734_()) {
            return false;
        }
        boolean exactMatch = this.equals(data);
        if (!exactMatch && craft.parsedStates != null) {
            return this.merge(data).equals(this);
        }
        return exactMatch;
    }

    private static <B extends Enum<B>> B toBukkit(Enum<?> nms, Class<B> bukkit) {
        if (nms instanceof Direction) {
            return (B)((Object)CraftBlock.notchToBlockFace((Direction)nms));
        }
        return (B)ENUM_VALUES.computeIfAbsent(bukkit, Class::getEnumConstants)[nms.ordinal()];
    }

    private static <N extends Enum<N>> N toNMS(Enum<?> bukkit, Class<N> nms) {
        if (bukkit instanceof BlockFace) {
            return (N)CraftBlock.blockFaceToNotch((BlockFace)bukkit);
        }
        return (N)ENUM_VALUES.computeIfAbsent(nms, Class::getEnumConstants)[bukkit.ordinal()];
    }

    protected <T extends Comparable<T>> T get(Property<T> ibs) {
        return (T)this.state.m_61143_(ibs);
    }

    public <T extends Comparable<T>, V extends T> void set(Property<T> ibs, V v) {
        this.parsedStates = null;
        this.state = (net.minecraft.world.level.block.state.BlockState)this.state.m_61124_(ibs, v);
    }

    @Override
    public String getAsString() {
        return this.toString((Map<Property<?>, Comparable<?>>)this.state.m_61148_());
    }

    @Override
    public String getAsString(boolean hideUnspecified) {
        return hideUnspecified && this.parsedStates != null ? this.toString(this.parsedStates) : this.getAsString();
    }

    @Override
    public BlockData clone() {
        try {
            return (BlockData)super.clone();
        }
        catch (CloneNotSupportedException ex) {
            throw new AssertionError("Clone not supported", ex);
        }
    }

    public String toString() {
        return "CraftBlockData{" + this.getAsString() + "}";
    }

    public String toString(Map<Property<?>, Comparable<?>> states) {
        StringBuilder stateString = new StringBuilder(BuiltInRegistries.f_256975_.m_7981_((Object)this.state.m_60734_()).toString());
        if (!states.isEmpty()) {
            stateString.append('[');
            stateString.append(states.entrySet().stream().map(StateHolder.f_61110_).collect(Collectors.joining(",")));
            stateString.append(']');
        }
        return stateString.toString();
    }

    public CompoundTag toStates() {
        CompoundTag compound = new CompoundTag();
        for (Map.Entry entry : this.state.m_61148_().entrySet()) {
            Property iblockstate = (Property)entry.getKey();
            compound.m_128359_(iblockstate.m_61708_(), iblockstate.m_6940_((Comparable)entry.getValue()));
        }
        return compound;
    }

    public boolean equals(Object obj) {
        return obj instanceof CraftBlockData && this.state.equals(((CraftBlockData)obj).state);
    }

    public int hashCode() {
        return this.state.hashCode();
    }

    protected static BooleanProperty getBoolean(String name) {
        throw new AssertionError((Object)"Template Method");
    }

    protected static BooleanProperty getBoolean(String name, boolean optional) {
        throw new AssertionError((Object)"Template Method");
    }

    protected static EnumProperty<?> getEnum(String name) {
        throw new AssertionError((Object)"Template Method");
    }

    protected static IntegerProperty getInteger(String name) {
        throw new AssertionError((Object)"Template Method");
    }

    protected static BooleanProperty getBoolean(Class<? extends Block> block, String name) {
        return (BooleanProperty)CraftBlockData.getState(block, name, false);
    }

    protected static BooleanProperty getBoolean(Class<? extends Block> block, String name, boolean optional) {
        return (BooleanProperty)CraftBlockData.getState(block, name, optional);
    }

    protected static EnumProperty<?> getEnum(Class<? extends Block> block, String name) {
        return (EnumProperty)CraftBlockData.getState(block, name, false);
    }

    protected static IntegerProperty getInteger(Class<? extends Block> block, String name) {
        return (IntegerProperty)CraftBlockData.getState(block, name, false);
    }

    private static Property<?> getState(Class<? extends Block> block, String name, boolean optional) {
        Property state = null;
        for (Block instance : BuiltInRegistries.f_256975_) {
            if (instance.getClass() != block) continue;
            if (state == null) {
                state = instance.m_49965_().m_61081_(name);
                continue;
            }
            Property newState = instance.m_49965_().m_61081_(name);
            Preconditions.checkState((state == newState ? 1 : 0) != 0, (String)"State mistmatch %s,%s", (Object)state, (Object)newState);
        }
        Preconditions.checkState((optional || state != null ? 1 : 0) != 0, (String)"Null state for %s,%s", block, (Object)name);
        return state;
    }

    protected static int getMin(IntegerProperty state) {
        return state.f_223000_;
    }

    protected static int getMax(IntegerProperty state) {
        return state.f_223001_;
    }

    private static void register(Class<? extends Block> nms, Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData> bukkit) {
        Preconditions.checkState((MAP.put(nms, bukkit) == null ? 1 : 0) != 0, (String)"Duplicate mapping %s->%s", nms, bukkit);
    }

    public static CraftBlockData newData(Material material, String data) {
        net.minecraft.world.level.block.state.BlockState blockData;
        Preconditions.checkArgument((material == null || material.isBlock() ? 1 : 0) != 0, (String)"Cannot get data for not block %s", (Object)material);
        Block block = CraftMagicNumbers.getBlock(material);
        Map parsed = null;
        if (data != null) {
            try {
                if (block != null) {
                    data = BuiltInRegistries.f_256975_.m_7981_((Object)block) + data;
                }
                StringReader reader = new StringReader(data);
                BlockStateParser.BlockResult arg = BlockStateParser.m_234691_((HolderLookup)BuiltInRegistries.f_256975_.m_255303_(), (StringReader)reader, (boolean)false);
                Preconditions.checkArgument((!reader.canRead() ? 1 : 0) != 0, (Object)("Spurious trailing data: " + data));
                blockData = arg.f_234748_();
                parsed = arg.f_234749_();
            }
            catch (CommandSyntaxException ex) {
                throw new IllegalArgumentException("Could not parse data: " + data, ex);
            }
        } else {
            blockData = block.m_49966_();
        }
        CraftBlockData craft = CraftBlockData.fromData(blockData);
        craft.parsedStates = parsed;
        return craft;
    }

    public static CraftBlockData fromData(net.minecraft.world.level.block.state.BlockState data) {
        return (CraftBlockData)MAP.getOrDefault(data.m_60734_().getClass(), (Function<net.minecraft.world.level.block.state.BlockState, CraftBlockData>)((Function)CraftBlockData::new)).apply((Object)data);
    }

    @Override
    public SoundGroup getSoundGroup() {
        return CraftSoundGroup.getSoundGroup(this.state.m_60827_());
    }

    @Override
    public int getLightEmission() {
        return this.state.m_60791_();
    }

    @Override
    public boolean isOccluding() {
        return this.state.m_60815_();
    }

    @Override
    public boolean requiresCorrectToolForDrops() {
        return this.state.m_60834_();
    }

    @Override
    public boolean isPreferredTool(ItemStack tool) {
        Preconditions.checkArgument((tool != null ? 1 : 0) != 0, (Object)"tool must not be null");
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(tool);
        return CraftBlockData.isPreferredTool(this.state, nms);
    }

    public static boolean isPreferredTool(net.minecraft.world.level.block.state.BlockState iblockdata, net.minecraft.world.item.ItemStack nmsItem) {
        return !iblockdata.m_60834_() || nmsItem.m_41735_(iblockdata);
    }

    @Override
    public PistonMoveReaction getPistonMoveReaction() {
        return PistonMoveReaction.getById(this.state.m_60811_().ordinal());
    }

    @Override
    public boolean isSupported(org.bukkit.block.Block block) {
        Preconditions.checkArgument((block != null ? 1 : 0) != 0, (Object)"block must not be null");
        CraftBlock craftBlock = (CraftBlock)block;
        return this.state.m_60710_((LevelReader)craftBlock.getCraftWorld().getHandle(), craftBlock.getPosition());
    }

    @Override
    public boolean isSupported(Location location) {
        Preconditions.checkArgument((location != null ? 1 : 0) != 0, (Object)"location must not be null");
        CraftWorld world = (CraftWorld)location.getWorld();
        Preconditions.checkArgument((world != null ? 1 : 0) != 0, (Object)"location must not have a null world");
        BlockPos position = CraftLocation.toBlockPosition(location);
        return this.state.m_60710_((LevelReader)world.getHandle(), position);
    }

    @Override
    public boolean isFaceSturdy(BlockFace face, BlockSupport support) {
        Preconditions.checkArgument((face != null ? 1 : 0) != 0, (Object)"face must not be null");
        Preconditions.checkArgument((support != null ? 1 : 0) != 0, (Object)"support must not be null");
        return this.state.m_60659_((BlockGetter)EmptyBlockGetter.INSTANCE, BlockPos.f_121853_, CraftBlock.blockFaceToNotch(face), CraftBlockSupport.toNMS(support));
    }

    @Override
    public Material getPlacementMaterial() {
        return CraftMagicNumbers.getMaterial(this.state.m_60734_().m_5456_());
    }

    @Override
    public void rotate(StructureRotation rotation) {
        this.state = this.state.m_60717_(Rotation.valueOf((String)rotation.name()));
    }

    @Override
    public void mirror(org.bukkit.block.structure.Mirror mirror) {
        this.state = this.state.m_60715_(Mirror.valueOf((String)mirror.name()));
    }

    @Override
    @NotNull
    public BlockState createBlockState() {
        return CraftBlockStates.getBlockState(this.state, null);
    }
}

