/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Translatable;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.AnaloguePowerable;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Brushable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Hatchable;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.block.data.Orientable;
import org.bukkit.block.data.Powerable;
import org.bukkit.block.data.Rail;
import org.bukkit.block.data.Rotatable;
import org.bukkit.block.data.Snowable;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.block.data.type.AmethystCluster;
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.block.data.type.Barrel;
import org.bukkit.block.data.type.Beehive;
import org.bukkit.block.data.type.Bell;
import org.bukkit.block.data.type.BigDripleaf;
import org.bukkit.block.data.type.BrewingStand;
import org.bukkit.block.data.type.BubbleColumn;
import org.bukkit.block.data.type.Cake;
import org.bukkit.block.data.type.CalibratedSculkSensor;
import org.bukkit.block.data.type.Campfire;
import org.bukkit.block.data.type.Candle;
import org.bukkit.block.data.type.CaveVines;
import org.bukkit.block.data.type.CaveVinesPlant;
import org.bukkit.block.data.type.Chain;
import org.bukkit.block.data.type.Chest;
import org.bukkit.block.data.type.ChiseledBookshelf;
import org.bukkit.block.data.type.Cocoa;
import org.bukkit.block.data.type.CommandBlock;
import org.bukkit.block.data.type.Comparator;
import org.bukkit.block.data.type.CoralWallFan;
import org.bukkit.block.data.type.DaylightDetector;
import org.bukkit.block.data.type.DecoratedPot;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.block.data.type.Dripleaf;
import org.bukkit.block.data.type.EndPortalFrame;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.block.data.type.Fence;
import org.bukkit.block.data.type.Fire;
import org.bukkit.block.data.type.GlassPane;
import org.bukkit.block.data.type.GlowLichen;
import org.bukkit.block.data.type.Grindstone;
import org.bukkit.block.data.type.HangingSign;
import org.bukkit.block.data.type.Jigsaw;
import org.bukkit.block.data.type.Jukebox;
import org.bukkit.block.data.type.Lantern;
import org.bukkit.block.data.type.Leaves;
import org.bukkit.block.data.type.Lectern;
import org.bukkit.block.data.type.Light;
import org.bukkit.block.data.type.LightningRod;
import org.bukkit.block.data.type.MangrovePropagule;
import org.bukkit.block.data.type.NoteBlock;
import org.bukkit.block.data.type.PinkPetals;
import org.bukkit.block.data.type.Piston;
import org.bukkit.block.data.type.PistonHead;
import org.bukkit.block.data.type.PitcherCrop;
import org.bukkit.block.data.type.PointedDripstone;
import org.bukkit.block.data.type.RedstoneRail;
import org.bukkit.block.data.type.RedstoneWallTorch;
import org.bukkit.block.data.type.RedstoneWire;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.block.data.type.RespawnAnchor;
import org.bukkit.block.data.type.Scaffolding;
import org.bukkit.block.data.type.SculkCatalyst;
import org.bukkit.block.data.type.SculkSensor;
import org.bukkit.block.data.type.SculkShrieker;
import org.bukkit.block.data.type.SculkVein;
import org.bukkit.block.data.type.SeaPickle;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.SmallDripleaf;
import org.bukkit.block.data.type.Snow;
import org.bukkit.block.data.type.StructureBlock;
import org.bukkit.block.data.type.Switch;
import org.bukkit.block.data.type.TNT;
import org.bukkit.block.data.type.TechnicalPiston;
import org.bukkit.block.data.type.TrapDoor;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.block.data.type.TurtleEgg;
import org.bukkit.block.data.type.Wall;
import org.bukkit.block.data.type.WallHangingSign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.inventory.CreativeCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.material.Banner;
import org.bukkit.material.Bed;
import org.bukkit.material.Button;
import org.bukkit.material.Cauldron;
import org.bukkit.material.Coal;
import org.bukkit.material.CocoaPlant;
import org.bukkit.material.Command;
import org.bukkit.material.Crops;
import org.bukkit.material.DetectorRail;
import org.bukkit.material.Diode;
import org.bukkit.material.Door;
import org.bukkit.material.Dye;
import org.bukkit.material.EnderChest;
import org.bukkit.material.FlowerPot;
import org.bukkit.material.Furnace;
import org.bukkit.material.Gate;
import org.bukkit.material.Hopper;
import org.bukkit.material.Ladder;
import org.bukkit.material.Lever;
import org.bukkit.material.LongGrass;
import org.bukkit.material.MaterialData;
import org.bukkit.material.MonsterEggs;
import org.bukkit.material.Mushroom;
import org.bukkit.material.NetherWarts;
import org.bukkit.material.Observer;
import org.bukkit.material.PistonBaseMaterial;
import org.bukkit.material.PistonExtensionMaterial;
import org.bukkit.material.PoweredRail;
import org.bukkit.material.PressurePlate;
import org.bukkit.material.Pumpkin;
import org.bukkit.material.Rails;
import org.bukkit.material.RedstoneTorch;
import org.bukkit.material.Sandstone;
import org.bukkit.material.Sapling;
import org.bukkit.material.Sign;
import org.bukkit.material.Skull;
import org.bukkit.material.SmoothBrick;
import org.bukkit.material.SpawnEgg;
import org.bukkit.material.Stairs;
import org.bukkit.material.Step;
import org.bukkit.material.Torch;
import org.bukkit.material.Tree;
import org.bukkit.material.TripwireHook;
import org.bukkit.material.Vine;
import org.bukkit.material.Wood;
import org.bukkit.material.WoodenStep;
import org.bukkit.material.Wool;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Material implements Keyed,
Translatable
{
    AIR(9648, 0),
    STONE(22948),
    GRANITE(21091),
    POLISHED_GRANITE(5477),
    DIORITE(24688),
    POLISHED_DIORITE(31615),
    ANDESITE(25975),
    POLISHED_ANDESITE(8335),
    DEEPSLATE(26842, Orientable.class),
    COBBLED_DEEPSLATE(8021),
    POLISHED_DEEPSLATE(31772),
    CALCITE(20311),
    TUFF(24364),
    DRIPSTONE_BLOCK(26227),
    GRASS_BLOCK(28346, Snowable.class),
    DIRT(10580),
    COARSE_DIRT(15411),
    PODZOL(24068, Snowable.class),
    ROOTED_DIRT(11410),
    MUD(32418),
    CRIMSON_NYLIUM(18139),
    WARPED_NYLIUM(26396),
    COBBLESTONE(32147),
    OAK_PLANKS(14905),
    SPRUCE_PLANKS(14593),
    BIRCH_PLANKS(29322),
    JUNGLE_PLANKS(26445),
    ACACIA_PLANKS(31312),
    CHERRY_PLANKS(8354),
    DARK_OAK_PLANKS(20869),
    MANGROVE_PLANKS(7078),
    BAMBOO_PLANKS(8520),
    CRIMSON_PLANKS(18812),
    WARPED_PLANKS(16045),
    BAMBOO_MOSAIC(10715),
    OAK_SAPLING(9636, org.bukkit.block.data.type.Sapling.class),
    SPRUCE_SAPLING(19874, org.bukkit.block.data.type.Sapling.class),
    BIRCH_SAPLING(31533, org.bukkit.block.data.type.Sapling.class),
    JUNGLE_SAPLING(17951, org.bukkit.block.data.type.Sapling.class),
    ACACIA_SAPLING(20806, org.bukkit.block.data.type.Sapling.class),
    CHERRY_SAPLING(25204, org.bukkit.block.data.type.Sapling.class),
    DARK_OAK_SAPLING(14933, org.bukkit.block.data.type.Sapling.class),
    MANGROVE_PROPAGULE(18688, MangrovePropagule.class),
    BEDROCK(23130),
    SAND(11542),
    SUSPICIOUS_SAND(18410, Brushable.class),
    SUSPICIOUS_GRAVEL(7353, Brushable.class),
    RED_SAND(16279),
    GRAVEL(7804),
    COAL_ORE(30965),
    DEEPSLATE_COAL_ORE(16823),
    IRON_ORE(19834),
    DEEPSLATE_IRON_ORE(26021),
    COPPER_ORE(32666),
    DEEPSLATE_COPPER_ORE(6588),
    GOLD_ORE(32625),
    DEEPSLATE_GOLD_ORE(13582),
    REDSTONE_ORE(10887, Lightable.class),
    DEEPSLATE_REDSTONE_ORE(6331, Lightable.class),
    EMERALD_ORE(16630),
    DEEPSLATE_EMERALD_ORE(5299),
    LAPIS_ORE(22934),
    DEEPSLATE_LAPIS_ORE(13598),
    DIAMOND_ORE(9292),
    DEEPSLATE_DIAMOND_ORE(17792),
    NETHER_GOLD_ORE(4185),
    NETHER_QUARTZ_ORE(4807),
    ANCIENT_DEBRIS(18198),
    COAL_BLOCK(27968),
    RAW_IRON_BLOCK(32210),
    RAW_COPPER_BLOCK(17504),
    RAW_GOLD_BLOCK(23246),
    AMETHYST_BLOCK(18919),
    BUDDING_AMETHYST(13963),
    IRON_BLOCK(24754),
    COPPER_BLOCK(12880),
    GOLD_BLOCK(27392),
    DIAMOND_BLOCK(5944),
    NETHERITE_BLOCK(6527),
    EXPOSED_COPPER(28488),
    WEATHERED_COPPER(19699),
    OXIDIZED_COPPER(19490),
    CUT_COPPER(32519),
    EXPOSED_CUT_COPPER(18000),
    WEATHERED_CUT_COPPER(21158),
    OXIDIZED_CUT_COPPER(5382),
    CUT_COPPER_STAIRS(25925, org.bukkit.block.data.type.Stairs.class),
    EXPOSED_CUT_COPPER_STAIRS(31621, org.bukkit.block.data.type.Stairs.class),
    WEATHERED_CUT_COPPER_STAIRS(5851, org.bukkit.block.data.type.Stairs.class),
    OXIDIZED_CUT_COPPER_STAIRS(25379, org.bukkit.block.data.type.Stairs.class),
    CUT_COPPER_SLAB(28988, Slab.class),
    EXPOSED_CUT_COPPER_SLAB(26694, Slab.class),
    WEATHERED_CUT_COPPER_SLAB(4602, Slab.class),
    OXIDIZED_CUT_COPPER_SLAB(29642, Slab.class),
    WAXED_COPPER_BLOCK(14638),
    WAXED_EXPOSED_COPPER(27989),
    WAXED_WEATHERED_COPPER(5960),
    WAXED_OXIDIZED_COPPER(25626),
    WAXED_CUT_COPPER(11030),
    WAXED_EXPOSED_CUT_COPPER(30043),
    WAXED_WEATHERED_CUT_COPPER(13823),
    WAXED_OXIDIZED_CUT_COPPER(22582),
    WAXED_CUT_COPPER_STAIRS(23125, org.bukkit.block.data.type.Stairs.class),
    WAXED_EXPOSED_CUT_COPPER_STAIRS(15532, org.bukkit.block.data.type.Stairs.class),
    WAXED_WEATHERED_CUT_COPPER_STAIRS(29701, org.bukkit.block.data.type.Stairs.class),
    WAXED_OXIDIZED_CUT_COPPER_STAIRS(9842, org.bukkit.block.data.type.Stairs.class),
    WAXED_CUT_COPPER_SLAB(6271, Slab.class),
    WAXED_EXPOSED_CUT_COPPER_SLAB(22091, Slab.class),
    WAXED_WEATHERED_CUT_COPPER_SLAB(20035, Slab.class),
    WAXED_OXIDIZED_CUT_COPPER_SLAB(11202, Slab.class),
    OAK_LOG(26723, Orientable.class),
    SPRUCE_LOG(9726, Orientable.class),
    BIRCH_LOG(26727, Orientable.class),
    JUNGLE_LOG(20721, Orientable.class),
    ACACIA_LOG(8385, Orientable.class),
    CHERRY_LOG(20847, Orientable.class),
    DARK_OAK_LOG(14831, Orientable.class),
    MANGROVE_LOG(23890, Orientable.class),
    MANGROVE_ROOTS(22124, Waterlogged.class),
    MUDDY_MANGROVE_ROOTS(23244, Orientable.class),
    CRIMSON_STEM(27920, Orientable.class),
    WARPED_STEM(28920, Orientable.class),
    BAMBOO_BLOCK(20770, Orientable.class),
    STRIPPED_OAK_LOG(20523, Orientable.class),
    STRIPPED_SPRUCE_LOG(6140, Orientable.class),
    STRIPPED_BIRCH_LOG(8838, Orientable.class),
    STRIPPED_JUNGLE_LOG(15476, Orientable.class),
    STRIPPED_ACACIA_LOG(18167, Orientable.class),
    STRIPPED_CHERRY_LOG(18061, Orientable.class),
    STRIPPED_DARK_OAK_LOG(6492, Orientable.class),
    STRIPPED_MANGROVE_LOG(15197, Orientable.class),
    STRIPPED_CRIMSON_STEM(16882, Orientable.class),
    STRIPPED_WARPED_STEM(15627, Orientable.class),
    STRIPPED_OAK_WOOD(31455, Orientable.class),
    STRIPPED_SPRUCE_WOOD(6467, Orientable.class),
    STRIPPED_BIRCH_WOOD(22350, Orientable.class),
    STRIPPED_JUNGLE_WOOD(30315, Orientable.class),
    STRIPPED_ACACIA_WOOD(27193, Orientable.class),
    STRIPPED_CHERRY_WOOD(19647, Orientable.class),
    STRIPPED_DARK_OAK_WOOD(16000, Orientable.class),
    STRIPPED_MANGROVE_WOOD(4828, Orientable.class),
    STRIPPED_CRIMSON_HYPHAE(27488, Orientable.class),
    STRIPPED_WARPED_HYPHAE(7422, Orientable.class),
    STRIPPED_BAMBOO_BLOCK(14799, Orientable.class),
    OAK_WOOD(7378, Orientable.class),
    SPRUCE_WOOD(32328, Orientable.class),
    BIRCH_WOOD(20913, Orientable.class),
    JUNGLE_WOOD(10341, Orientable.class),
    ACACIA_WOOD(9541, Orientable.class),
    CHERRY_WOOD(9826, Orientable.class),
    DARK_OAK_WOOD(16995, Orientable.class),
    MANGROVE_WOOD(25484, Orientable.class),
    CRIMSON_HYPHAE(6550, Orientable.class),
    WARPED_HYPHAE(18439, Orientable.class),
    OAK_LEAVES(4385, Leaves.class),
    SPRUCE_LEAVES(20039, Leaves.class),
    BIRCH_LEAVES(12601, Leaves.class),
    JUNGLE_LEAVES(5133, Leaves.class),
    ACACIA_LEAVES(16606, Leaves.class),
    CHERRY_LEAVES(20856, Leaves.class),
    DARK_OAK_LEAVES(22254, Leaves.class),
    MANGROVE_LEAVES(15310, Leaves.class),
    AZALEA_LEAVES(23001, Leaves.class),
    FLOWERING_AZALEA_LEAVES(7139, Leaves.class),
    SPONGE(15860),
    WET_SPONGE(9043),
    GLASS(6195),
    TINTED_GLASS(19154),
    LAPIS_BLOCK(14485),
    SANDSTONE(13141),
    CHISELED_SANDSTONE(31763),
    CUT_SANDSTONE(6118),
    COBWEB(9469),
    GRASS(6155),
    FERN(15794),
    AZALEA(29386),
    FLOWERING_AZALEA(28270),
    DEAD_BUSH(22888),
    SEAGRASS(23942),
    SEA_PICKLE(19562, SeaPickle.class),
    WHITE_WOOL(8624),
    ORANGE_WOOL(23957),
    MAGENTA_WOOL(11853),
    LIGHT_BLUE_WOOL(21073),
    YELLOW_WOOL(29507),
    LIME_WOOL(10443),
    PINK_WOOL(7611),
    GRAY_WOOL(27209),
    LIGHT_GRAY_WOOL(22936),
    CYAN_WOOL(12221),
    PURPLE_WOOL(11922),
    BLUE_WOOL(15738),
    BROWN_WOOL(32638),
    GREEN_WOOL(25085),
    RED_WOOL(11621),
    BLACK_WOOL(16693),
    DANDELION(30558),
    POPPY(12851),
    BLUE_ORCHID(13432),
    ALLIUM(6871),
    AZURE_BLUET(17608),
    RED_TULIP(16781),
    ORANGE_TULIP(26038),
    WHITE_TULIP(31495),
    PINK_TULIP(27319),
    OXEYE_DAISY(11709),
    CORNFLOWER(15405),
    LILY_OF_THE_VALLEY(7185),
    WITHER_ROSE(8619),
    TORCHFLOWER(4501),
    PITCHER_PLANT(28172, Bisected.class),
    SPORE_BLOSSOM(20627),
    BROWN_MUSHROOM(9665),
    RED_MUSHROOM(19728),
    CRIMSON_FUNGUS(26268),
    WARPED_FUNGUS(19799),
    CRIMSON_ROOTS(14064),
    WARPED_ROOTS(13932),
    NETHER_SPROUTS(10431),
    WEEPING_VINES(29267, Ageable.class),
    TWISTING_VINES(27283, Ageable.class),
    SUGAR_CANE(7726, Ageable.class),
    KELP(21916, Ageable.class),
    MOSS_CARPET(8221),
    PINK_PETALS(10420, PinkPetals.class),
    MOSS_BLOCK(9175),
    HANGING_ROOTS(15498, Waterlogged.class),
    BIG_DRIPLEAF(26173, BigDripleaf.class),
    SMALL_DRIPLEAF(17540, SmallDripleaf.class),
    BAMBOO(18728, Bamboo.class),
    OAK_SLAB(12002, Slab.class),
    SPRUCE_SLAB(28798, Slab.class),
    BIRCH_SLAB(13807, Slab.class),
    JUNGLE_SLAB(19117, Slab.class),
    ACACIA_SLAB(23730, Slab.class),
    CHERRY_SLAB(16673, Slab.class),
    DARK_OAK_SLAB(28852, Slab.class),
    MANGROVE_SLAB(13704, Slab.class),
    BAMBOO_SLAB(17798, Slab.class),
    BAMBOO_MOSAIC_SLAB(22118, Slab.class),
    CRIMSON_SLAB(4691, Slab.class),
    WARPED_SLAB(27150, Slab.class),
    STONE_SLAB(19838, Slab.class),
    SMOOTH_STONE_SLAB(24129, Slab.class),
    SANDSTONE_SLAB(29830, Slab.class),
    CUT_SANDSTONE_SLAB(30944, Slab.class),
    PETRIFIED_OAK_SLAB(18658, Slab.class),
    COBBLESTONE_SLAB(6340, Slab.class),
    BRICK_SLAB(26333, Slab.class),
    STONE_BRICK_SLAB(19676, Slab.class),
    MUD_BRICK_SLAB(10611, Slab.class),
    NETHER_BRICK_SLAB(26586, Slab.class),
    QUARTZ_SLAB(4423, Slab.class),
    RED_SANDSTONE_SLAB(17550, Slab.class),
    CUT_RED_SANDSTONE_SLAB(7220, Slab.class),
    PURPUR_SLAB(11487, Slab.class),
    PRISMARINE_SLAB(31323, Slab.class),
    PRISMARINE_BRICK_SLAB(25624, Slab.class),
    DARK_PRISMARINE_SLAB(7577, Slab.class),
    SMOOTH_QUARTZ(14415),
    SMOOTH_RED_SANDSTONE(25180),
    SMOOTH_SANDSTONE(30039),
    SMOOTH_STONE(21910),
    BRICKS(14165),
    BOOKSHELF(10069),
    CHISELED_BOOKSHELF(8099, ChiseledBookshelf.class),
    DECORATED_POT(8720, 1, DecoratedPot.class),
    MOSSY_COBBLESTONE(21900),
    OBSIDIAN(32723),
    TORCH(6063),
    END_ROD(24832, Directional.class),
    CHORUS_PLANT(28243, MultipleFacing.class),
    CHORUS_FLOWER(28542, Ageable.class),
    PURPUR_BLOCK(7538),
    PURPUR_PILLAR(26718, Orientable.class),
    PURPUR_STAIRS(8921, org.bukkit.block.data.type.Stairs.class),
    SPAWNER(7018),
    CHEST(22969, Chest.class),
    CRAFTING_TABLE(20706),
    FARMLAND(31166, Farmland.class),
    FURNACE(8133, org.bukkit.block.data.type.Furnace.class),
    LADDER(23599, org.bukkit.block.data.type.Ladder.class),
    COBBLESTONE_STAIRS(24715, org.bukkit.block.data.type.Stairs.class),
    SNOW(14146, Snow.class),
    ICE(30428),
    SNOW_BLOCK(19913),
    CACTUS(12191, Ageable.class),
    CLAY(27880),
    JUKEBOX(19264, Jukebox.class),
    OAK_FENCE(6442, Fence.class),
    SPRUCE_FENCE(25416, Fence.class),
    BIRCH_FENCE(17347, Fence.class),
    JUNGLE_FENCE(14358, Fence.class),
    ACACIA_FENCE(4569, Fence.class),
    CHERRY_FENCE(32047, Fence.class),
    DARK_OAK_FENCE(21767, Fence.class),
    MANGROVE_FENCE(15021, Fence.class),
    BAMBOO_FENCE(17207, Fence.class),
    CRIMSON_FENCE(21075, Fence.class),
    WARPED_FENCE(18438, Fence.class),
    PUMPKIN(19170),
    CARVED_PUMPKIN(25833, Directional.class),
    JACK_O_LANTERN(13758, Directional.class),
    NETHERRACK(23425),
    SOUL_SAND(16841),
    SOUL_SOIL(31140),
    BASALT(28478, Orientable.class),
    POLISHED_BASALT(11659, Orientable.class),
    SMOOTH_BASALT(13617),
    SOUL_TORCH(14292),
    GLOWSTONE(32713),
    INFESTED_STONE(18440),
    INFESTED_COBBLESTONE(4348),
    INFESTED_STONE_BRICKS(19749),
    INFESTED_MOSSY_STONE_BRICKS(9850),
    INFESTED_CRACKED_STONE_BRICKS(7476),
    INFESTED_CHISELED_STONE_BRICKS(4728),
    INFESTED_DEEPSLATE(9472, Orientable.class),
    STONE_BRICKS(6962),
    MOSSY_STONE_BRICKS(16415),
    CRACKED_STONE_BRICKS(27869),
    CHISELED_STONE_BRICKS(9087),
    PACKED_MUD(7472),
    MUD_BRICKS(29168),
    DEEPSLATE_BRICKS(13193),
    CRACKED_DEEPSLATE_BRICKS(17105),
    DEEPSLATE_TILES(11250),
    CRACKED_DEEPSLATE_TILES(26249),
    CHISELED_DEEPSLATE(23825),
    REINFORCED_DEEPSLATE(10949),
    BROWN_MUSHROOM_BLOCK(6291, MultipleFacing.class),
    RED_MUSHROOM_BLOCK(20766, MultipleFacing.class),
    MUSHROOM_STEM(16543, MultipleFacing.class),
    IRON_BARS(9378, Fence.class),
    CHAIN(28265, Chain.class),
    GLASS_PANE(5709, Fence.class),
    MELON(25172),
    VINE(14564, MultipleFacing.class),
    GLOW_LICHEN(19165, GlowLichen.class),
    BRICK_STAIRS(21534, org.bukkit.block.data.type.Stairs.class),
    STONE_BRICK_STAIRS(27032, org.bukkit.block.data.type.Stairs.class),
    MUD_BRICK_STAIRS(13620, org.bukkit.block.data.type.Stairs.class),
    MYCELIUM(9913, Snowable.class),
    LILY_PAD(19271),
    NETHER_BRICKS(27802),
    CRACKED_NETHER_BRICKS(10888),
    CHISELED_NETHER_BRICKS(21613),
    NETHER_BRICK_FENCE(5286, Fence.class),
    NETHER_BRICK_STAIRS(12085, org.bukkit.block.data.type.Stairs.class),
    SCULK(17870),
    SCULK_VEIN(11615, SculkVein.class),
    SCULK_CATALYST(12017, SculkCatalyst.class),
    SCULK_SHRIEKER(20985, SculkShrieker.class),
    ENCHANTING_TABLE(16255),
    END_PORTAL_FRAME(15480, EndPortalFrame.class),
    END_STONE(29686),
    END_STONE_BRICKS(20314),
    DRAGON_EGG(29946),
    SANDSTONE_STAIRS(18474, org.bukkit.block.data.type.Stairs.class),
    ENDER_CHEST(32349, org.bukkit.block.data.type.EnderChest.class),
    EMERALD_BLOCK(9914),
    OAK_STAIRS(5449, org.bukkit.block.data.type.Stairs.class),
    SPRUCE_STAIRS(11192, org.bukkit.block.data.type.Stairs.class),
    BIRCH_STAIRS(7657, org.bukkit.block.data.type.Stairs.class),
    JUNGLE_STAIRS(20636, org.bukkit.block.data.type.Stairs.class),
    ACACIA_STAIRS(17453, org.bukkit.block.data.type.Stairs.class),
    CHERRY_STAIRS(18380, org.bukkit.block.data.type.Stairs.class),
    DARK_OAK_STAIRS(22921, org.bukkit.block.data.type.Stairs.class),
    MANGROVE_STAIRS(27641, org.bukkit.block.data.type.Stairs.class),
    BAMBOO_STAIRS(25674, org.bukkit.block.data.type.Stairs.class),
    BAMBOO_MOSAIC_STAIRS(20977, org.bukkit.block.data.type.Stairs.class),
    CRIMSON_STAIRS(32442, org.bukkit.block.data.type.Stairs.class),
    WARPED_STAIRS(17721, org.bukkit.block.data.type.Stairs.class),
    COMMAND_BLOCK(4355, CommandBlock.class),
    BEACON(6608),
    COBBLESTONE_WALL(12616, Wall.class),
    MOSSY_COBBLESTONE_WALL(11536, Wall.class),
    BRICK_WALL(18995, Wall.class),
    PRISMARINE_WALL(18184, Wall.class),
    RED_SANDSTONE_WALL(4753, Wall.class),
    MOSSY_STONE_BRICK_WALL(18259, Wall.class),
    GRANITE_WALL(23279, Wall.class),
    STONE_BRICK_WALL(29073, Wall.class),
    MUD_BRICK_WALL(18292, Wall.class),
    NETHER_BRICK_WALL(10398, Wall.class),
    ANDESITE_WALL(14938, Wall.class),
    RED_NETHER_BRICK_WALL(4580, Wall.class),
    SANDSTONE_WALL(18470, Wall.class),
    END_STONE_BRICK_WALL(27225, Wall.class),
    DIORITE_WALL(17412, Wall.class),
    BLACKSTONE_WALL(17327, Wall.class),
    POLISHED_BLACKSTONE_WALL(15119, Wall.class),
    POLISHED_BLACKSTONE_BRICK_WALL(9540, Wall.class),
    COBBLED_DEEPSLATE_WALL(21893, Wall.class),
    POLISHED_DEEPSLATE_WALL(6574, Wall.class),
    DEEPSLATE_BRICK_WALL(13304, Wall.class),
    DEEPSLATE_TILE_WALL(17077, Wall.class),
    ANVIL(18718, Directional.class),
    CHIPPED_ANVIL(10623, Directional.class),
    DAMAGED_ANVIL(10274, Directional.class),
    CHISELED_QUARTZ_BLOCK(30964),
    QUARTZ_BLOCK(11987),
    QUARTZ_BRICKS(23358),
    QUARTZ_PILLAR(16452, Orientable.class),
    QUARTZ_STAIRS(24079, org.bukkit.block.data.type.Stairs.class),
    WHITE_TERRACOTTA(20975),
    ORANGE_TERRACOTTA(18684),
    MAGENTA_TERRACOTTA(25900),
    LIGHT_BLUE_TERRACOTTA(31779),
    YELLOW_TERRACOTTA(32129),
    LIME_TERRACOTTA(24013),
    PINK_TERRACOTTA(23727),
    GRAY_TERRACOTTA(18004),
    LIGHT_GRAY_TERRACOTTA(26388),
    CYAN_TERRACOTTA(25940),
    PURPLE_TERRACOTTA(10387),
    BLUE_TERRACOTTA(5236),
    BROWN_TERRACOTTA(23664),
    GREEN_TERRACOTTA(4105),
    RED_TERRACOTTA(5086),
    BLACK_TERRACOTTA(26691),
    BARRIER(26453),
    LIGHT(17829, Light.class),
    HAY_BLOCK(17461, Orientable.class),
    WHITE_CARPET(15117),
    ORANGE_CARPET(24752),
    MAGENTA_CARPET(6180),
    LIGHT_BLUE_CARPET(21194),
    YELLOW_CARPET(18149),
    LIME_CARPET(15443),
    PINK_CARPET(27381),
    GRAY_CARPET(26991),
    LIGHT_GRAY_CARPET(11317),
    CYAN_CARPET(9742),
    PURPLE_CARPET(5574),
    BLUE_CARPET(13292),
    BROWN_CARPET(23352),
    GREEN_CARPET(7780),
    RED_CARPET(5424),
    BLACK_CARPET(6056),
    TERRACOTTA(16544),
    PACKED_ICE(28993),
    DIRT_PATH(10846),
    SUNFLOWER(7408, Bisected.class),
    LILAC(22837, Bisected.class),
    ROSE_BUSH(6080, Bisected.class),
    PEONY(21155, Bisected.class),
    TALL_GRASS(21559, Bisected.class),
    LARGE_FERN(30177, Bisected.class),
    WHITE_STAINED_GLASS(31190),
    ORANGE_STAINED_GLASS(25142),
    MAGENTA_STAINED_GLASS(26814),
    LIGHT_BLUE_STAINED_GLASS(17162),
    YELLOW_STAINED_GLASS(12182),
    LIME_STAINED_GLASS(24266),
    PINK_STAINED_GLASS(16164),
    GRAY_STAINED_GLASS(29979),
    LIGHT_GRAY_STAINED_GLASS(5843),
    CYAN_STAINED_GLASS(30604),
    PURPLE_STAINED_GLASS(21845),
    BLUE_STAINED_GLASS(7107),
    BROWN_STAINED_GLASS(20945),
    GREEN_STAINED_GLASS(22503),
    RED_STAINED_GLASS(9717),
    BLACK_STAINED_GLASS(13941),
    WHITE_STAINED_GLASS_PANE(10557, GlassPane.class),
    ORANGE_STAINED_GLASS_PANE(21089, GlassPane.class),
    MAGENTA_STAINED_GLASS_PANE(14082, GlassPane.class),
    LIGHT_BLUE_STAINED_GLASS_PANE(18721, GlassPane.class),
    YELLOW_STAINED_GLASS_PANE(20298, GlassPane.class),
    LIME_STAINED_GLASS_PANE(10610, GlassPane.class),
    PINK_STAINED_GLASS_PANE(24637, GlassPane.class),
    GRAY_STAINED_GLASS_PANE(25272, GlassPane.class),
    LIGHT_GRAY_STAINED_GLASS_PANE(19008, GlassPane.class),
    CYAN_STAINED_GLASS_PANE(11784, GlassPane.class),
    PURPLE_STAINED_GLASS_PANE(10948, GlassPane.class),
    BLUE_STAINED_GLASS_PANE(28484, GlassPane.class),
    BROWN_STAINED_GLASS_PANE(17557, GlassPane.class),
    GREEN_STAINED_GLASS_PANE(4767, GlassPane.class),
    RED_STAINED_GLASS_PANE(8630, GlassPane.class),
    BLACK_STAINED_GLASS_PANE(13201, GlassPane.class),
    PRISMARINE(7539),
    PRISMARINE_BRICKS(29118),
    DARK_PRISMARINE(19940),
    PRISMARINE_STAIRS(19217, org.bukkit.block.data.type.Stairs.class),
    PRISMARINE_BRICK_STAIRS(15445, org.bukkit.block.data.type.Stairs.class),
    DARK_PRISMARINE_STAIRS(26511, org.bukkit.block.data.type.Stairs.class),
    SEA_LANTERN(20780),
    RED_SANDSTONE(9092),
    CHISELED_RED_SANDSTONE(15529),
    CUT_RED_SANDSTONE(29108),
    RED_SANDSTONE_STAIRS(25466, org.bukkit.block.data.type.Stairs.class),
    REPEATING_COMMAND_BLOCK(12405, CommandBlock.class),
    CHAIN_COMMAND_BLOCK(26798, CommandBlock.class),
    MAGMA_BLOCK(25927),
    NETHER_WART_BLOCK(15486),
    WARPED_WART_BLOCK(15463),
    RED_NETHER_BRICKS(18056),
    BONE_BLOCK(17312, Orientable.class),
    STRUCTURE_VOID(30806),
    SHULKER_BOX(7776, 1, Directional.class),
    WHITE_SHULKER_BOX(31750, 1, Directional.class),
    ORANGE_SHULKER_BOX(21673, 1, Directional.class),
    MAGENTA_SHULKER_BOX(21566, 1, Directional.class),
    LIGHT_BLUE_SHULKER_BOX(18226, 1, Directional.class),
    YELLOW_SHULKER_BOX(28700, 1, Directional.class),
    LIME_SHULKER_BOX(28360, 1, Directional.class),
    PINK_SHULKER_BOX(24968, 1, Directional.class),
    GRAY_SHULKER_BOX(12754, 1, Directional.class),
    LIGHT_GRAY_SHULKER_BOX(21345, 1, Directional.class),
    CYAN_SHULKER_BOX(28123, 1, Directional.class),
    PURPLE_SHULKER_BOX(10373, 1, Directional.class),
    BLUE_SHULKER_BOX(11476, 1, Directional.class),
    BROWN_SHULKER_BOX(24230, 1, Directional.class),
    GREEN_SHULKER_BOX(9377, 1, Directional.class),
    RED_SHULKER_BOX(32448, 1, Directional.class),
    BLACK_SHULKER_BOX(24076, 1, Directional.class),
    WHITE_GLAZED_TERRACOTTA(11326, Directional.class),
    ORANGE_GLAZED_TERRACOTTA(27451, Directional.class),
    MAGENTA_GLAZED_TERRACOTTA(8067, Directional.class),
    LIGHT_BLUE_GLAZED_TERRACOTTA(4336, Directional.class),
    YELLOW_GLAZED_TERRACOTTA(10914, Directional.class),
    LIME_GLAZED_TERRACOTTA(13861, Directional.class),
    PINK_GLAZED_TERRACOTTA(10260, Directional.class),
    GRAY_GLAZED_TERRACOTTA(6256, Directional.class),
    LIGHT_GRAY_GLAZED_TERRACOTTA(10707, Directional.class),
    CYAN_GLAZED_TERRACOTTA(9550, Directional.class),
    PURPLE_GLAZED_TERRACOTTA(4818, Directional.class),
    BLUE_GLAZED_TERRACOTTA(23823, Directional.class),
    BROWN_GLAZED_TERRACOTTA(5655, Directional.class),
    GREEN_GLAZED_TERRACOTTA(6958, Directional.class),
    RED_GLAZED_TERRACOTTA(24989, Directional.class),
    BLACK_GLAZED_TERRACOTTA(29678, Directional.class),
    WHITE_CONCRETE(6281),
    ORANGE_CONCRETE(19914),
    MAGENTA_CONCRETE(20591),
    LIGHT_BLUE_CONCRETE(29481),
    YELLOW_CONCRETE(15722),
    LIME_CONCRETE(5863),
    PINK_CONCRETE(5227),
    GRAY_CONCRETE(13959),
    LIGHT_GRAY_CONCRETE(14453),
    CYAN_CONCRETE(26522),
    PURPLE_CONCRETE(20623),
    BLUE_CONCRETE(18756),
    BROWN_CONCRETE(19006),
    GREEN_CONCRETE(17949),
    RED_CONCRETE(8032),
    BLACK_CONCRETE(13338),
    WHITE_CONCRETE_POWDER(10363),
    ORANGE_CONCRETE_POWDER(30159),
    MAGENTA_CONCRETE_POWDER(8272),
    LIGHT_BLUE_CONCRETE_POWDER(31206),
    YELLOW_CONCRETE_POWDER(10655),
    LIME_CONCRETE_POWDER(28859),
    PINK_CONCRETE_POWDER(6421),
    GRAY_CONCRETE_POWDER(13031),
    LIGHT_GRAY_CONCRETE_POWDER(21589),
    CYAN_CONCRETE_POWDER(15734),
    PURPLE_CONCRETE_POWDER(26808),
    BLUE_CONCRETE_POWDER(17773),
    BROWN_CONCRETE_POWDER(21485),
    GREEN_CONCRETE_POWDER(6904),
    RED_CONCRETE_POWDER(13286),
    BLACK_CONCRETE_POWDER(16150),
    TURTLE_EGG(32101, TurtleEgg.class),
    SNIFFER_EGG(12980, Hatchable.class),
    DEAD_TUBE_CORAL_BLOCK(28350),
    DEAD_BRAIN_CORAL_BLOCK(12979),
    DEAD_BUBBLE_CORAL_BLOCK(28220),
    DEAD_FIRE_CORAL_BLOCK(5307),
    DEAD_HORN_CORAL_BLOCK(15103),
    TUBE_CORAL_BLOCK(23723),
    BRAIN_CORAL_BLOCK(30618),
    BUBBLE_CORAL_BLOCK(15437),
    FIRE_CORAL_BLOCK(12119),
    HORN_CORAL_BLOCK(19958),
    TUBE_CORAL(23048, Waterlogged.class),
    BRAIN_CORAL(31316, Waterlogged.class),
    BUBBLE_CORAL(12464, Waterlogged.class),
    FIRE_CORAL(29151, Waterlogged.class),
    HORN_CORAL(19511, Waterlogged.class),
    DEAD_BRAIN_CORAL(9116, Waterlogged.class),
    DEAD_BUBBLE_CORAL(30583, Waterlogged.class),
    DEAD_FIRE_CORAL(8365, Waterlogged.class),
    DEAD_HORN_CORAL(5755, Waterlogged.class),
    DEAD_TUBE_CORAL(18028, Waterlogged.class),
    TUBE_CORAL_FAN(19929, Waterlogged.class),
    BRAIN_CORAL_FAN(13849, Waterlogged.class),
    BUBBLE_CORAL_FAN(10795, Waterlogged.class),
    FIRE_CORAL_FAN(11112, Waterlogged.class),
    HORN_CORAL_FAN(13610, Waterlogged.class),
    DEAD_TUBE_CORAL_FAN(17628, Waterlogged.class),
    DEAD_BRAIN_CORAL_FAN(26150, Waterlogged.class),
    DEAD_BUBBLE_CORAL_FAN(17322, Waterlogged.class),
    DEAD_FIRE_CORAL_FAN(27073, Waterlogged.class),
    DEAD_HORN_CORAL_FAN(11387, Waterlogged.class),
    BLUE_ICE(22449),
    CONDUIT(5148, Waterlogged.class),
    POLISHED_GRANITE_STAIRS(29588, org.bukkit.block.data.type.Stairs.class),
    SMOOTH_RED_SANDSTONE_STAIRS(17561, org.bukkit.block.data.type.Stairs.class),
    MOSSY_STONE_BRICK_STAIRS(27578, org.bukkit.block.data.type.Stairs.class),
    POLISHED_DIORITE_STAIRS(4625, org.bukkit.block.data.type.Stairs.class),
    MOSSY_COBBLESTONE_STAIRS(29210, org.bukkit.block.data.type.Stairs.class),
    END_STONE_BRICK_STAIRS(28831, org.bukkit.block.data.type.Stairs.class),
    STONE_STAIRS(23784, org.bukkit.block.data.type.Stairs.class),
    SMOOTH_SANDSTONE_STAIRS(21183, org.bukkit.block.data.type.Stairs.class),
    SMOOTH_QUARTZ_STAIRS(19560, org.bukkit.block.data.type.Stairs.class),
    GRANITE_STAIRS(21840, org.bukkit.block.data.type.Stairs.class),
    ANDESITE_STAIRS(17747, org.bukkit.block.data.type.Stairs.class),
    RED_NETHER_BRICK_STAIRS(26374, org.bukkit.block.data.type.Stairs.class),
    POLISHED_ANDESITE_STAIRS(7573, org.bukkit.block.data.type.Stairs.class),
    DIORITE_STAIRS(13134, org.bukkit.block.data.type.Stairs.class),
    COBBLED_DEEPSLATE_STAIRS(20699, org.bukkit.block.data.type.Stairs.class),
    POLISHED_DEEPSLATE_STAIRS(19513, org.bukkit.block.data.type.Stairs.class),
    DEEPSLATE_BRICK_STAIRS(29624, org.bukkit.block.data.type.Stairs.class),
    DEEPSLATE_TILE_STAIRS(6361, org.bukkit.block.data.type.Stairs.class),
    POLISHED_GRANITE_SLAB(4521, Slab.class),
    SMOOTH_RED_SANDSTONE_SLAB(16304, Slab.class),
    MOSSY_STONE_BRICK_SLAB(14002, Slab.class),
    POLISHED_DIORITE_SLAB(18303, Slab.class),
    MOSSY_COBBLESTONE_SLAB(12139, Slab.class),
    END_STONE_BRICK_SLAB(23239, Slab.class),
    SMOOTH_SANDSTONE_SLAB(9030, Slab.class),
    SMOOTH_QUARTZ_SLAB(26543, Slab.class),
    GRANITE_SLAB(10901, Slab.class),
    ANDESITE_SLAB(32124, Slab.class),
    RED_NETHER_BRICK_SLAB(12462, Slab.class),
    POLISHED_ANDESITE_SLAB(24573, Slab.class),
    DIORITE_SLAB(25526, Slab.class),
    COBBLED_DEEPSLATE_SLAB(17388, Slab.class),
    POLISHED_DEEPSLATE_SLAB(32201, Slab.class),
    DEEPSLATE_BRICK_SLAB(23910, Slab.class),
    DEEPSLATE_TILE_SLAB(13315, Slab.class),
    SCAFFOLDING(15757, Scaffolding.class),
    REDSTONE(11233),
    REDSTONE_TORCH(22547, Lightable.class),
    REDSTONE_BLOCK(19496),
    REPEATER(28823, Repeater.class),
    COMPARATOR(18911, Comparator.class),
    PISTON(21130, Piston.class),
    STICKY_PISTON(18127, Piston.class),
    SLIME_BLOCK(31892),
    HONEY_BLOCK(30615),
    OBSERVER(10726, org.bukkit.block.data.type.Observer.class),
    HOPPER(31974, org.bukkit.block.data.type.Hopper.class),
    DISPENSER(20871, Dispenser.class),
    DROPPER(31273, Dispenser.class),
    LECTERN(23490, Lectern.class),
    TARGET(22637, AnaloguePowerable.class),
    LEVER(15319, Switch.class),
    LIGHTNING_ROD(30770, LightningRod.class),
    DAYLIGHT_DETECTOR(8864, DaylightDetector.class),
    SCULK_SENSOR(5598, SculkSensor.class),
    CALIBRATED_SCULK_SENSOR(21034, CalibratedSculkSensor.class),
    TRIPWIRE_HOOK(8130, org.bukkit.block.data.type.TripwireHook.class),
    TRAPPED_CHEST(18970, Chest.class),
    TNT(7896, TNT.class),
    REDSTONE_LAMP(8217, Lightable.class),
    NOTE_BLOCK(20979, NoteBlock.class),
    STONE_BUTTON(12279, Switch.class),
    POLISHED_BLACKSTONE_BUTTON(20760, Switch.class),
    OAK_BUTTON(13510, Switch.class),
    SPRUCE_BUTTON(23281, Switch.class),
    BIRCH_BUTTON(26934, Switch.class),
    JUNGLE_BUTTON(25317, Switch.class),
    ACACIA_BUTTON(13993, Switch.class),
    CHERRY_BUTTON(9058, Switch.class),
    DARK_OAK_BUTTON(6214, Switch.class),
    MANGROVE_BUTTON(9838, Switch.class),
    BAMBOO_BUTTON(21810, Switch.class),
    CRIMSON_BUTTON(26799, Switch.class),
    WARPED_BUTTON(25264, Switch.class),
    STONE_PRESSURE_PLATE(22591, Powerable.class),
    POLISHED_BLACKSTONE_PRESSURE_PLATE(32340, Powerable.class),
    LIGHT_WEIGHTED_PRESSURE_PLATE(14875, AnaloguePowerable.class),
    HEAVY_WEIGHTED_PRESSURE_PLATE(16970, AnaloguePowerable.class),
    OAK_PRESSURE_PLATE(20108, Powerable.class),
    SPRUCE_PRESSURE_PLATE(15932, Powerable.class),
    BIRCH_PRESSURE_PLATE(9664, Powerable.class),
    JUNGLE_PRESSURE_PLATE(11376, Powerable.class),
    ACACIA_PRESSURE_PLATE(17586, Powerable.class),
    CHERRY_PRESSURE_PLATE(8651, Powerable.class),
    DARK_OAK_PRESSURE_PLATE(31375, Powerable.class),
    MANGROVE_PRESSURE_PLATE(9748, Powerable.class),
    BAMBOO_PRESSURE_PLATE(26740, Powerable.class),
    CRIMSON_PRESSURE_PLATE(18316, Powerable.class),
    WARPED_PRESSURE_PLATE(29516, Powerable.class),
    IRON_DOOR(4788, org.bukkit.block.data.type.Door.class),
    OAK_DOOR(20341, org.bukkit.block.data.type.Door.class),
    SPRUCE_DOOR(10642, org.bukkit.block.data.type.Door.class),
    BIRCH_DOOR(14759, org.bukkit.block.data.type.Door.class),
    JUNGLE_DOOR(28163, org.bukkit.block.data.type.Door.class),
    ACACIA_DOOR(23797, org.bukkit.block.data.type.Door.class),
    CHERRY_DOOR(12684, org.bukkit.block.data.type.Door.class),
    DARK_OAK_DOOR(10669, org.bukkit.block.data.type.Door.class),
    MANGROVE_DOOR(18964, org.bukkit.block.data.type.Door.class),
    BAMBOO_DOOR(19971, org.bukkit.block.data.type.Door.class),
    CRIMSON_DOOR(19544, org.bukkit.block.data.type.Door.class),
    WARPED_DOOR(15062, org.bukkit.block.data.type.Door.class),
    IRON_TRAPDOOR(17095, TrapDoor.class),
    OAK_TRAPDOOR(16927, TrapDoor.class),
    SPRUCE_TRAPDOOR(10289, TrapDoor.class),
    BIRCH_TRAPDOOR(32585, TrapDoor.class),
    JUNGLE_TRAPDOOR(8626, TrapDoor.class),
    ACACIA_TRAPDOOR(18343, TrapDoor.class),
    CHERRY_TRAPDOOR(6293, TrapDoor.class),
    DARK_OAK_TRAPDOOR(10355, TrapDoor.class),
    MANGROVE_TRAPDOOR(17066, TrapDoor.class),
    BAMBOO_TRAPDOOR(9174, TrapDoor.class),
    CRIMSON_TRAPDOOR(25056, TrapDoor.class),
    WARPED_TRAPDOOR(7708, TrapDoor.class),
    OAK_FENCE_GATE(16689, org.bukkit.block.data.type.Gate.class),
    SPRUCE_FENCE_GATE(26423, org.bukkit.block.data.type.Gate.class),
    BIRCH_FENCE_GATE(6322, org.bukkit.block.data.type.Gate.class),
    JUNGLE_FENCE_GATE(21360, org.bukkit.block.data.type.Gate.class),
    ACACIA_FENCE_GATE(14145, org.bukkit.block.data.type.Gate.class),
    CHERRY_FENCE_GATE(28222, org.bukkit.block.data.type.Gate.class),
    DARK_OAK_FENCE_GATE(10679, org.bukkit.block.data.type.Gate.class),
    MANGROVE_FENCE_GATE(28476, org.bukkit.block.data.type.Gate.class),
    BAMBOO_FENCE_GATE(14290, org.bukkit.block.data.type.Gate.class),
    CRIMSON_FENCE_GATE(15602, org.bukkit.block.data.type.Gate.class),
    WARPED_FENCE_GATE(11115, org.bukkit.block.data.type.Gate.class),
    POWERED_RAIL(11064, RedstoneRail.class),
    DETECTOR_RAIL(13475, RedstoneRail.class),
    RAIL(13285, Rail.class),
    ACTIVATOR_RAIL(5834, RedstoneRail.class),
    SADDLE(30206, 1),
    MINECART(14352, 1),
    CHEST_MINECART(4497, 1),
    FURNACE_MINECART(14196, 1),
    TNT_MINECART(4277, 1),
    HOPPER_MINECART(19024, 1),
    CARROT_ON_A_STICK(27809, 1, 25),
    WARPED_FUNGUS_ON_A_STICK(11706, 1, 100),
    ELYTRA(23829, 1, 432),
    OAK_BOAT(17570, 1),
    OAK_CHEST_BOAT(7765, 1),
    SPRUCE_BOAT(31427, 1),
    SPRUCE_CHEST_BOAT(30841, 1),
    BIRCH_BOAT(28104, 1),
    BIRCH_CHEST_BOAT(18546, 1),
    JUNGLE_BOAT(4495, 1),
    JUNGLE_CHEST_BOAT(20133, 1),
    ACACIA_BOAT(27326, 1),
    ACACIA_CHEST_BOAT(28455, 1),
    CHERRY_BOAT(13628, 1),
    CHERRY_CHEST_BOAT(7165, 1),
    DARK_OAK_BOAT(28618, 1),
    DARK_OAK_CHEST_BOAT(8733, 1),
    MANGROVE_BOAT(20792, 1),
    MANGROVE_CHEST_BOAT(18572, 1),
    BAMBOO_RAFT(25901, 1),
    BAMBOO_CHEST_RAFT(20056, 1),
    STRUCTURE_BLOCK(26831, StructureBlock.class),
    JIGSAW(17398, Jigsaw.class),
    TURTLE_HELMET(30120, 1, 275),
    SCUTE(11914),
    FLINT_AND_STEEL(28620, 1, 64),
    APPLE(7720),
    BOW(8745, 1, 384),
    ARROW(31091),
    COAL(29067),
    CHARCOAL(5390),
    DIAMOND(20865),
    EMERALD(5654),
    LAPIS_LAZULI(11075),
    QUARTZ(23608),
    AMETHYST_SHARD(7613),
    RAW_IRON(5329),
    IRON_INGOT(24895),
    RAW_COPPER(6162),
    COPPER_INGOT(12611),
    RAW_GOLD(19564),
    GOLD_INGOT(28927),
    NETHERITE_INGOT(32457),
    NETHERITE_SCRAP(29331),
    WOODEN_SWORD(7175, 1, 59),
    WOODEN_SHOVEL(28432, 1, 59),
    WOODEN_PICKAXE(12792, 1, 59),
    WOODEN_AXE(6292, 1, 59),
    WOODEN_HOE(16043, 1, 59),
    STONE_SWORD(25084, 1, 131),
    STONE_SHOVEL(9520, 1, 131),
    STONE_PICKAXE(14611, 1, 131),
    STONE_AXE(6338, 1, 131),
    STONE_HOE(22855, 1, 131),
    GOLDEN_SWORD(10505, 1, 32),
    GOLDEN_SHOVEL(15597, 1, 32),
    GOLDEN_PICKAXE(25898, 1, 32),
    GOLDEN_AXE(4878, 1, 32),
    GOLDEN_HOE(19337, 1, 32),
    IRON_SWORD(10904, 1, 250),
    IRON_SHOVEL(30045, 1, 250),
    IRON_PICKAXE(8842, 1, 250),
    IRON_AXE(15894, 1, 250),
    IRON_HOE(11339, 1, 250),
    DIAMOND_SWORD(27707, 1, 1561),
    DIAMOND_SHOVEL(25415, 1, 1561),
    DIAMOND_PICKAXE(24291, 1, 1561),
    DIAMOND_AXE(27277, 1, 1561),
    DIAMOND_HOE(24050, 1, 1561),
    NETHERITE_SWORD(23871, 1, 2031),
    NETHERITE_SHOVEL(29728, 1, 2031),
    NETHERITE_PICKAXE(9930, 1, 2031),
    NETHERITE_AXE(29533, 1, 2031),
    NETHERITE_HOE(27385, 1, 2031),
    STICK(9773),
    BOWL(32661),
    MUSHROOM_STEW(16336, 1),
    STRING(12806),
    FEATHER(30548),
    GUNPOWDER(29974),
    WHEAT_SEEDS(28742),
    WHEAT(27709, Ageable.class),
    BREAD(32049),
    LEATHER_HELMET(11624, 1, 55),
    LEATHER_CHESTPLATE(29275, 1, 80),
    LEATHER_LEGGINGS(28210, 1, 75),
    LEATHER_BOOTS(15282, 1, 65),
    CHAINMAIL_HELMET(26114, 1, 165),
    CHAINMAIL_CHESTPLATE(23602, 1, 240),
    CHAINMAIL_LEGGINGS(19087, 1, 225),
    CHAINMAIL_BOOTS(17953, 1, 195),
    IRON_HELMET(12025, 1, 165),
    IRON_CHESTPLATE(28112, 1, 240),
    IRON_LEGGINGS(18951, 1, 225),
    IRON_BOOTS(8531, 1, 195),
    DIAMOND_HELMET(10755, 1, 363),
    DIAMOND_CHESTPLATE(32099, 1, 528),
    DIAMOND_LEGGINGS(26500, 1, 495),
    DIAMOND_BOOTS(16522, 1, 429),
    GOLDEN_HELMET(7945, 1, 77),
    GOLDEN_CHESTPLATE(4507, 1, 112),
    GOLDEN_LEGGINGS(21002, 1, 105),
    GOLDEN_BOOTS(7859, 1, 91),
    NETHERITE_HELMET(15907, 1, 407),
    NETHERITE_CHESTPLATE(6106, 1, 592),
    NETHERITE_LEGGINGS(25605, 1, 555),
    NETHERITE_BOOTS(8923, 1, 481),
    FLINT(23596),
    PORKCHOP(30896),
    COOKED_PORKCHOP(27231),
    PAINTING(23945),
    GOLDEN_APPLE(27732),
    ENCHANTED_GOLDEN_APPLE(8280),
    OAK_SIGN(8192, 16, org.bukkit.block.data.type.Sign.class),
    SPRUCE_SIGN(21502, 16, org.bukkit.block.data.type.Sign.class),
    BIRCH_SIGN(11351, 16, org.bukkit.block.data.type.Sign.class),
    JUNGLE_SIGN(24717, 16, org.bukkit.block.data.type.Sign.class),
    ACACIA_SIGN(29808, 16, org.bukkit.block.data.type.Sign.class),
    CHERRY_SIGN(16520, 16, org.bukkit.block.data.type.Sign.class),
    DARK_OAK_SIGN(15127, 16, org.bukkit.block.data.type.Sign.class),
    MANGROVE_SIGN(21975, 16, org.bukkit.block.data.type.Sign.class),
    BAMBOO_SIGN(26139, 16, org.bukkit.block.data.type.Sign.class),
    CRIMSON_SIGN(12162, 16, org.bukkit.block.data.type.Sign.class),
    WARPED_SIGN(10407, 16, org.bukkit.block.data.type.Sign.class),
    OAK_HANGING_SIGN(20116, 16, HangingSign.class),
    SPRUCE_HANGING_SIGN(24371, 16, HangingSign.class),
    BIRCH_HANGING_SIGN(17938, 16, HangingSign.class),
    JUNGLE_HANGING_SIGN(27671, 16, HangingSign.class),
    ACACIA_HANGING_SIGN(30257, 16, HangingSign.class),
    CHERRY_HANGING_SIGN(5088, 16, HangingSign.class),
    DARK_OAK_HANGING_SIGN(23360, 16, HangingSign.class),
    MANGROVE_HANGING_SIGN(25106, 16, HangingSign.class),
    BAMBOO_HANGING_SIGN(4726, 16, HangingSign.class),
    CRIMSON_HANGING_SIGN(20696, 16, HangingSign.class),
    WARPED_HANGING_SIGN(8195, 16, HangingSign.class),
    BUCKET(15215, 16),
    WATER_BUCKET(8802, 1),
    LAVA_BUCKET(9228, 1),
    POWDER_SNOW_BUCKET(31101, 1),
    SNOWBALL(19487, 16),
    LEATHER(16414),
    MILK_BUCKET(9680, 1),
    PUFFERFISH_BUCKET(8861, 1),
    SALMON_BUCKET(9606, 1),
    COD_BUCKET(28601, 1),
    TROPICAL_FISH_BUCKET(29995, 1),
    AXOLOTL_BUCKET(20669, 1),
    TADPOLE_BUCKET(9731, 1),
    BRICK(6820),
    CLAY_BALL(24603),
    DRIED_KELP_BLOCK(12966),
    PAPER(9923),
    BOOK(23097),
    SLIME_BALL(5242),
    EGG(21603, 16),
    COMPASS(24139),
    RECOVERY_COMPASS(12710),
    BUNDLE(16835, 1),
    FISHING_ROD(4167, 1, 64),
    CLOCK(14980),
    SPYGLASS(27490, 1),
    GLOWSTONE_DUST(6665),
    COD(24691),
    SALMON(18516),
    TROPICAL_FISH(24879),
    PUFFERFISH(8115),
    COOKED_COD(9681),
    COOKED_SALMON(5615),
    INK_SAC(7184),
    GLOW_INK_SAC(9686),
    COCOA_BEANS(30186),
    WHITE_DYE(10758),
    ORANGE_DYE(13866),
    MAGENTA_DYE(11788),
    LIGHT_BLUE_DYE(28738),
    YELLOW_DYE(5952),
    LIME_DYE(6147),
    PINK_DYE(31151),
    GRAY_DYE(9184),
    LIGHT_GRAY_DYE(27643),
    CYAN_DYE(8043),
    PURPLE_DYE(6347),
    BLUE_DYE(11588),
    BROWN_DYE(7648),
    GREEN_DYE(23215),
    RED_DYE(5728),
    BLACK_DYE(6202),
    BONE_MEAL(32458),
    BONE(5686),
    SUGAR(30638),
    CAKE(27048, 1, Cake.class),
    WHITE_BED(8185, 1, org.bukkit.block.data.type.Bed.class),
    ORANGE_BED(11194, 1, org.bukkit.block.data.type.Bed.class),
    MAGENTA_BED(20061, 1, org.bukkit.block.data.type.Bed.class),
    LIGHT_BLUE_BED(20957, 1, org.bukkit.block.data.type.Bed.class),
    YELLOW_BED(30410, 1, org.bukkit.block.data.type.Bed.class),
    LIME_BED(27860, 1, org.bukkit.block.data.type.Bed.class),
    PINK_BED(13795, 1, org.bukkit.block.data.type.Bed.class),
    GRAY_BED(15745, 1, org.bukkit.block.data.type.Bed.class),
    LIGHT_GRAY_BED(5090, 1, org.bukkit.block.data.type.Bed.class),
    CYAN_BED(16746, 1, org.bukkit.block.data.type.Bed.class),
    PURPLE_BED(29755, 1, org.bukkit.block.data.type.Bed.class),
    BLUE_BED(12714, 1, org.bukkit.block.data.type.Bed.class),
    BROWN_BED(26672, 1, org.bukkit.block.data.type.Bed.class),
    GREEN_BED(13797, 1, org.bukkit.block.data.type.Bed.class),
    RED_BED(30910, 1, org.bukkit.block.data.type.Bed.class),
    BLACK_BED(20490, 1, org.bukkit.block.data.type.Bed.class),
    COOKIE(27431),
    FILLED_MAP(23504),
    SHEARS(27971, 1, 238),
    MELON_SLICE(5347),
    DRIED_KELP(21042),
    PUMPKIN_SEEDS(28985),
    MELON_SEEDS(18340),
    BEEF(4803),
    COOKED_BEEF(21595),
    CHICKEN(17281),
    COOKED_CHICKEN(16984),
    ROTTEN_FLESH(21591),
    ENDER_PEARL(5259, 16),
    BLAZE_ROD(8289),
    GHAST_TEAR(18222),
    GOLD_NUGGET(28814),
    NETHER_WART(29227, Ageable.class),
    POTION(24020, 1),
    GLASS_BOTTLE(6116),
    SPIDER_EYE(9318),
    FERMENTED_SPIDER_EYE(19386),
    BLAZE_POWDER(18941),
    MAGMA_CREAM(25097),
    BREWING_STAND(14539, BrewingStand.class),
    CAULDRON(26531),
    ENDER_EYE(24860),
    GLISTERING_MELON_SLICE(20158),
    ALLAY_SPAWN_EGG(7909),
    AXOLOTL_SPAWN_EGG(30381),
    BAT_SPAWN_EGG(14607),
    BEE_SPAWN_EGG(22924),
    BLAZE_SPAWN_EGG(4759),
    CAT_SPAWN_EGG(29583),
    CAMEL_SPAWN_EGG(14760),
    CAVE_SPIDER_SPAWN_EGG(23341),
    CHICKEN_SPAWN_EGG(5462),
    COD_SPAWN_EGG(27248),
    COW_SPAWN_EGG(14761),
    CREEPER_SPAWN_EGG(9653),
    DOLPHIN_SPAWN_EGG(20787),
    DONKEY_SPAWN_EGG(14513),
    DROWNED_SPAWN_EGG(19368),
    ELDER_GUARDIAN_SPAWN_EGG(11418),
    ENDER_DRAGON_SPAWN_EGG(28092),
    ENDERMAN_SPAWN_EGG(29488),
    ENDERMITE_SPAWN_EGG(16617),
    EVOKER_SPAWN_EGG(21271),
    FOX_SPAWN_EGG(22376),
    FROG_SPAWN_EGG(26682),
    GHAST_SPAWN_EGG(9970),
    GLOW_SQUID_SPAWN_EGG(31578),
    GOAT_SPAWN_EGG(30639),
    GUARDIAN_SPAWN_EGG(20113),
    HOGLIN_SPAWN_EGG(14088),
    HORSE_SPAWN_EGG(25981),
    HUSK_SPAWN_EGG(20178),
    IRON_GOLEM_SPAWN_EGG(12781),
    LLAMA_SPAWN_EGG(23640),
    MAGMA_CUBE_SPAWN_EGG(26638),
    MOOSHROOM_SPAWN_EGG(22125),
    MULE_SPAWN_EGG(11229),
    OCELOT_SPAWN_EGG(30080),
    PANDA_SPAWN_EGG(23759),
    PARROT_SPAWN_EGG(23614),
    PHANTOM_SPAWN_EGG(24648),
    PIG_SPAWN_EGG(22584),
    PIGLIN_SPAWN_EGG(16193),
    PIGLIN_BRUTE_SPAWN_EGG(30230),
    PILLAGER_SPAWN_EGG(28659),
    POLAR_BEAR_SPAWN_EGG(17015),
    PUFFERFISH_SPAWN_EGG(24570),
    RABBIT_SPAWN_EGG(26496),
    RAVAGER_SPAWN_EGG(8726),
    SALMON_SPAWN_EGG(18739),
    SHEEP_SPAWN_EGG(24488),
    SHULKER_SPAWN_EGG(31848),
    SILVERFISH_SPAWN_EGG(14537),
    SKELETON_SPAWN_EGG(15261),
    SKELETON_HORSE_SPAWN_EGG(21356),
    SLIME_SPAWN_EGG(17196),
    SNIFFER_SPAWN_EGG(27473),
    SNOW_GOLEM_SPAWN_EGG(24732),
    SPIDER_SPAWN_EGG(14984),
    SQUID_SPAWN_EGG(10682),
    STRAY_SPAWN_EGG(30153),
    STRIDER_SPAWN_EGG(6203),
    TADPOLE_SPAWN_EGG(32467),
    TRADER_LLAMA_SPAWN_EGG(8439),
    TROPICAL_FISH_SPAWN_EGG(19713),
    TURTLE_SPAWN_EGG(17324),
    VEX_SPAWN_EGG(27751),
    VILLAGER_SPAWN_EGG(30348),
    VINDICATOR_SPAWN_EGG(25324),
    WANDERING_TRADER_SPAWN_EGG(17904),
    WARDEN_SPAWN_EGG(27553),
    WITCH_SPAWN_EGG(11837),
    WITHER_SPAWN_EGG(8024),
    WITHER_SKELETON_SPAWN_EGG(10073),
    WOLF_SPAWN_EGG(21692),
    ZOGLIN_SPAWN_EGG(7442),
    ZOMBIE_SPAWN_EGG(5814),
    ZOMBIE_HORSE_SPAWN_EGG(4275),
    ZOMBIE_VILLAGER_SPAWN_EGG(10311),
    ZOMBIFIED_PIGLIN_SPAWN_EGG(6626),
    EXPERIENCE_BOTTLE(12858),
    FIRE_CHARGE(4842),
    WRITABLE_BOOK(13393, 1),
    WRITTEN_BOOK(24164, 16),
    ITEM_FRAME(27318),
    GLOW_ITEM_FRAME(26473),
    FLOWER_POT(30567),
    CARROT(22824),
    POTATO(21088),
    BAKED_POTATO(14624),
    POISONOUS_POTATO(32640),
    MAP(21655),
    GOLDEN_CARROT(5300),
    SKELETON_SKULL(13270, Rotatable.class),
    WITHER_SKELETON_SKULL(31487, Rotatable.class),
    PLAYER_HEAD(21174, Rotatable.class),
    ZOMBIE_HEAD(9304, Rotatable.class),
    CREEPER_HEAD(29146, Rotatable.class),
    DRAGON_HEAD(20084, Rotatable.class),
    PIGLIN_HEAD(5512, Rotatable.class),
    NETHER_STAR(12469),
    PUMPKIN_PIE(28725),
    FIREWORK_ROCKET(23841),
    FIREWORK_STAR(12190),
    ENCHANTED_BOOK(11741, 1),
    NETHER_BRICK(19996),
    PRISMARINE_SHARD(10993),
    PRISMARINE_CRYSTALS(31546),
    RABBIT(23068),
    COOKED_RABBIT(4454),
    RABBIT_STEW(25318, 1),
    RABBIT_FOOT(13864),
    RABBIT_HIDE(12467),
    ARMOR_STAND(12852, 16),
    IRON_HORSE_ARMOR(30108, 1),
    GOLDEN_HORSE_ARMOR(7996, 1),
    DIAMOND_HORSE_ARMOR(10321, 1),
    LEATHER_HORSE_ARMOR(30667, 1),
    LEAD(29539),
    NAME_TAG(30731),
    COMMAND_BLOCK_MINECART(7992, 1),
    MUTTON(4792),
    COOKED_MUTTON(31447),
    WHITE_BANNER(17562, 16, Rotatable.class),
    ORANGE_BANNER(4839, 16, Rotatable.class),
    MAGENTA_BANNER(15591, 16, Rotatable.class),
    LIGHT_BLUE_BANNER(18060, 16, Rotatable.class),
    YELLOW_BANNER(30382, 16, Rotatable.class),
    LIME_BANNER(18887, 16, Rotatable.class),
    PINK_BANNER(19439, 16, Rotatable.class),
    GRAY_BANNER(12053, 16, Rotatable.class),
    LIGHT_GRAY_BANNER(11417, 16, Rotatable.class),
    CYAN_BANNER(9839, 16, Rotatable.class),
    PURPLE_BANNER(29027, 16, Rotatable.class),
    BLUE_BANNER(18481, 16, Rotatable.class),
    BROWN_BANNER(11481, 16, Rotatable.class),
    GREEN_BANNER(10698, 16, Rotatable.class),
    RED_BANNER(26961, 16, Rotatable.class),
    BLACK_BANNER(9365, 16, Rotatable.class),
    END_CRYSTAL(19090),
    CHORUS_FRUIT(7652),
    POPPED_CHORUS_FRUIT(27844),
    TORCHFLOWER_SEEDS(18153),
    PITCHER_POD(7977),
    BEETROOT(23305),
    BEETROOT_SEEDS(21282),
    BEETROOT_SOUP(16036, 1),
    DRAGON_BREATH(20154),
    SPLASH_POTION(30248, 1),
    SPECTRAL_ARROW(4568),
    TIPPED_ARROW(25164),
    LINGERING_POTION(25857, 1),
    SHIELD(29943, 1, 336),
    TOTEM_OF_UNDYING(10139, 1),
    SHULKER_SHELL(27848),
    IRON_NUGGET(13715),
    KNOWLEDGE_BOOK(12646, 1),
    DEBUG_STICK(24562, 1),
    MUSIC_DISC_13(16359, 1),
    MUSIC_DISC_CAT(16246, 1),
    MUSIC_DISC_BLOCKS(26667, 1),
    MUSIC_DISC_CHIRP(19436, 1),
    MUSIC_DISC_FAR(31742, 1),
    MUSIC_DISC_MALL(11517, 1),
    MUSIC_DISC_MELLOHI(26117, 1),
    MUSIC_DISC_STAL(14989, 1),
    MUSIC_DISC_STRAD(16785, 1),
    MUSIC_DISC_WARD(24026, 1),
    MUSIC_DISC_11(27426, 1),
    MUSIC_DISC_WAIT(26499, 1),
    MUSIC_DISC_OTHERSIDE(12974, 1),
    MUSIC_DISC_RELIC(8200, 1),
    MUSIC_DISC_5(9212, 1),
    MUSIC_DISC_PIGSTEP(21323, 1),
    DISC_FRAGMENT_5(29729),
    TRIDENT(7534, 1, 250),
    PHANTOM_MEMBRANE(18398),
    NAUTILUS_SHELL(19989),
    HEART_OF_THE_SEA(11807),
    CROSSBOW(4340, 1, 465),
    SUSPICIOUS_STEW(8173, 1),
    LOOM(14276, Directional.class),
    FLOWER_BANNER_PATTERN(5762, 1),
    CREEPER_BANNER_PATTERN(15774, 1),
    SKULL_BANNER_PATTERN(7680, 1),
    MOJANG_BANNER_PATTERN(11903, 1),
    GLOBE_BANNER_PATTERN(27753, 1),
    PIGLIN_BANNER_PATTERN(22028, 1),
    GOAT_HORN(28237, 1),
    COMPOSTER(31247, Levelled.class),
    BARREL(22396, Barrel.class),
    SMOKER(24781, org.bukkit.block.data.type.Furnace.class),
    BLAST_FURNACE(31157, org.bukkit.block.data.type.Furnace.class),
    CARTOGRAPHY_TABLE(28529),
    FLETCHING_TABLE(30838),
    GRINDSTONE(26260, Grindstone.class),
    SMITHING_TABLE(9082),
    STONECUTTER(25170, Directional.class),
    BELL(20000, Bell.class),
    LANTERN(5992, Lantern.class),
    SOUL_LANTERN(27778, Lantern.class),
    SWEET_BERRIES(19747),
    GLOW_BERRIES(11584),
    CAMPFIRE(8488, Campfire.class),
    SOUL_CAMPFIRE(4238, Campfire.class),
    SHROOMLIGHT(20424),
    HONEYCOMB(9482),
    BEE_NEST(8825, Beehive.class),
    BEEHIVE(11830, Beehive.class),
    HONEY_BOTTLE(22927, 16),
    HONEYCOMB_BLOCK(28780),
    LODESTONE(23127),
    CRYING_OBSIDIAN(31545),
    BLACKSTONE(7354),
    BLACKSTONE_SLAB(11948, Slab.class),
    BLACKSTONE_STAIRS(14646, org.bukkit.block.data.type.Stairs.class),
    GILDED_BLACKSTONE(8498),
    POLISHED_BLACKSTONE(18144),
    POLISHED_BLACKSTONE_SLAB(23430, Slab.class),
    POLISHED_BLACKSTONE_STAIRS(8653, org.bukkit.block.data.type.Stairs.class),
    CHISELED_POLISHED_BLACKSTONE(21942),
    POLISHED_BLACKSTONE_BRICKS(19844),
    POLISHED_BLACKSTONE_BRICK_SLAB(12219, Slab.class),
    POLISHED_BLACKSTONE_BRICK_STAIRS(17983, org.bukkit.block.data.type.Stairs.class),
    CRACKED_POLISHED_BLACKSTONE_BRICKS(16846),
    RESPAWN_ANCHOR(4099, RespawnAnchor.class),
    CANDLE(16122, Candle.class),
    WHITE_CANDLE(26410, Candle.class),
    ORANGE_CANDLE(22668, Candle.class),
    MAGENTA_CANDLE(25467, Candle.class),
    LIGHT_BLUE_CANDLE(28681, Candle.class),
    YELLOW_CANDLE(14351, Candle.class),
    LIME_CANDLE(21778, Candle.class),
    PINK_CANDLE(28259, Candle.class),
    GRAY_CANDLE(10721, Candle.class),
    LIGHT_GRAY_CANDLE(10031, Candle.class),
    CYAN_CANDLE(24765, Candle.class),
    PURPLE_CANDLE(19606, Candle.class),
    BLUE_CANDLE(29047, Candle.class),
    BROWN_CANDLE(26145, Candle.class),
    GREEN_CANDLE(29756, Candle.class),
    RED_CANDLE(4214, Candle.class),
    BLACK_CANDLE(12617, Candle.class),
    SMALL_AMETHYST_BUD(14958, AmethystCluster.class),
    MEDIUM_AMETHYST_BUD(8429, AmethystCluster.class),
    LARGE_AMETHYST_BUD(7279, AmethystCluster.class),
    AMETHYST_CLUSTER(13142, AmethystCluster.class),
    POINTED_DRIPSTONE(18755, PointedDripstone.class),
    OCHRE_FROGLIGHT(25330, Orientable.class),
    VERDANT_FROGLIGHT(22793, Orientable.class),
    PEARLESCENT_FROGLIGHT(21441, Orientable.class),
    FROGSPAWN(8350),
    ECHO_SHARD(12529),
    BRUSH(30569, 1, 64),
    NETHERITE_UPGRADE_SMITHING_TEMPLATE(7615),
    SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE(16124),
    DUNE_ARMOR_TRIM_SMITHING_TEMPLATE(30925),
    COAST_ARMOR_TRIM_SMITHING_TEMPLATE(25501),
    WILD_ARMOR_TRIM_SMITHING_TEMPLATE(5870),
    WARD_ARMOR_TRIM_SMITHING_TEMPLATE(24534),
    EYE_ARMOR_TRIM_SMITHING_TEMPLATE(14663),
    VEX_ARMOR_TRIM_SMITHING_TEMPLATE(25818),
    TIDE_ARMOR_TRIM_SMITHING_TEMPLATE(20420),
    SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE(14386),
    RIB_ARMOR_TRIM_SMITHING_TEMPLATE(6010),
    SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE(29143),
    WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE(4957),
    SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE(20537),
    SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE(7070),
    RAISER_ARMOR_TRIM_SMITHING_TEMPLATE(29116),
    HOST_ARMOR_TRIM_SMITHING_TEMPLATE(12165),
    ANGLER_POTTERY_SHERD(9952),
    ARCHER_POTTERY_SHERD(21629),
    ARMS_UP_POTTERY_SHERD(5484),
    BLADE_POTTERY_SHERD(25079),
    BREWER_POTTERY_SHERD(23429),
    BURN_POTTERY_SHERD(21259),
    DANGER_POTTERY_SHERD(30506),
    EXPLORER_POTTERY_SHERD(5124),
    FRIEND_POTTERY_SHERD(18221),
    HEART_POTTERY_SHERD(17607),
    HEARTBREAK_POTTERY_SHERD(21108),
    HOWL_POTTERY_SHERD(24900),
    MINER_POTTERY_SHERD(30602),
    MOURNER_POTTERY_SHERD(23993),
    PLENTY_POTTERY_SHERD(28236),
    PRIZE_POTTERY_SHERD(4341),
    SHEAF_POTTERY_SHERD(23652),
    SHELTER_POTTERY_SHERD(28390),
    SKULL_POTTERY_SHERD(16980),
    SNORT_POTTERY_SHERD(15921),
    WATER(24998, Levelled.class),
    LAVA(8415, Levelled.class),
    TALL_SEAGRASS(27189, Bisected.class),
    PISTON_HEAD(30226, PistonHead.class),
    MOVING_PISTON(13831, TechnicalPiston.class),
    WALL_TORCH(25890, Directional.class),
    FIRE(16396, Fire.class),
    SOUL_FIRE(30163),
    REDSTONE_WIRE(25984, RedstoneWire.class),
    OAK_WALL_SIGN(12984, 16, WallSign.class),
    SPRUCE_WALL_SIGN(7352, 16, WallSign.class),
    BIRCH_WALL_SIGN(9887, 16, WallSign.class),
    ACACIA_WALL_SIGN(20316, 16, WallSign.class),
    CHERRY_WALL_SIGN(20188, 16, WallSign.class),
    JUNGLE_WALL_SIGN(29629, 16, WallSign.class),
    DARK_OAK_WALL_SIGN(9508, 16, WallSign.class),
    MANGROVE_WALL_SIGN(27203, 16, WallSign.class),
    BAMBOO_WALL_SIGN(18857, 16, WallSign.class),
    OAK_WALL_HANGING_SIGN(15637, WallHangingSign.class),
    SPRUCE_WALL_HANGING_SIGN(18833, WallHangingSign.class),
    BIRCH_WALL_HANGING_SIGN(15937, WallHangingSign.class),
    ACACIA_WALL_HANGING_SIGN(22477, WallHangingSign.class),
    CHERRY_WALL_HANGING_SIGN(10953, WallHangingSign.class),
    JUNGLE_WALL_HANGING_SIGN(16691, WallHangingSign.class),
    DARK_OAK_WALL_HANGING_SIGN(14296, WallHangingSign.class),
    MANGROVE_WALL_HANGING_SIGN(16974, WallHangingSign.class),
    CRIMSON_WALL_HANGING_SIGN(28982, WallHangingSign.class),
    WARPED_WALL_HANGING_SIGN(20605, WallHangingSign.class),
    BAMBOO_WALL_HANGING_SIGN(6669, WallHangingSign.class),
    REDSTONE_WALL_TORCH(7595, RedstoneWallTorch.class),
    SOUL_WALL_TORCH(27500, Directional.class),
    NETHER_PORTAL(19469, Orientable.class),
    ATTACHED_PUMPKIN_STEM(12724, Directional.class),
    ATTACHED_MELON_STEM(30882, Directional.class),
    PUMPKIN_STEM(19021, Ageable.class),
    MELON_STEM(8247, Ageable.class),
    WATER_CAULDRON(32008, Levelled.class),
    LAVA_CAULDRON(4514),
    POWDER_SNOW_CAULDRON(31571, Levelled.class),
    END_PORTAL(16782),
    COCOA(29709, Cocoa.class),
    TRIPWIRE(8810, Tripwire.class),
    POTTED_TORCHFLOWER(21278),
    POTTED_OAK_SAPLING(11905),
    POTTED_SPRUCE_SAPLING(29498),
    POTTED_BIRCH_SAPLING(32484),
    POTTED_JUNGLE_SAPLING(7525),
    POTTED_ACACIA_SAPLING(14096),
    POTTED_CHERRY_SAPLING(30785),
    POTTED_DARK_OAK_SAPLING(6486),
    POTTED_MANGROVE_PROPAGULE(22003),
    POTTED_FERN(23315),
    POTTED_DANDELION(9727),
    POTTED_POPPY(7457),
    POTTED_BLUE_ORCHID(6599),
    POTTED_ALLIUM(13184),
    POTTED_AZURE_BLUET(8754),
    POTTED_RED_TULIP(28594),
    POTTED_ORANGE_TULIP(28807),
    POTTED_WHITE_TULIP(24330),
    POTTED_PINK_TULIP(10089),
    POTTED_OXEYE_DAISY(19707),
    POTTED_CORNFLOWER(28917),
    POTTED_LILY_OF_THE_VALLEY(9364),
    POTTED_WITHER_ROSE(26876),
    POTTED_RED_MUSHROOM(22881),
    POTTED_BROWN_MUSHROOM(14481),
    POTTED_DEAD_BUSH(13020),
    POTTED_CACTUS(8777),
    CARROTS(17258, Ageable.class),
    POTATOES(10879, Ageable.class),
    SKELETON_WALL_SKULL(31650, Directional.class),
    WITHER_SKELETON_WALL_SKULL(9326, Directional.class),
    ZOMBIE_WALL_HEAD(16296, Directional.class),
    PLAYER_WALL_HEAD(13164, Directional.class),
    CREEPER_WALL_HEAD(30123, Directional.class),
    DRAGON_WALL_HEAD(19818, Directional.class),
    PIGLIN_WALL_HEAD(4446, Directional.class),
    WHITE_WALL_BANNER(15967, Directional.class),
    ORANGE_WALL_BANNER(9936, Directional.class),
    MAGENTA_WALL_BANNER(23291, Directional.class),
    LIGHT_BLUE_WALL_BANNER(12011, Directional.class),
    YELLOW_WALL_BANNER(32004, Directional.class),
    LIME_WALL_BANNER(21422, Directional.class),
    PINK_WALL_BANNER(9421, Directional.class),
    GRAY_WALL_BANNER(24275, Directional.class),
    LIGHT_GRAY_WALL_BANNER(31088, Directional.class),
    CYAN_WALL_BANNER(10889, Directional.class),
    PURPLE_WALL_BANNER(14298, Directional.class),
    BLUE_WALL_BANNER(17757, Directional.class),
    BROWN_WALL_BANNER(14731, Directional.class),
    GREEN_WALL_BANNER(15046, Directional.class),
    RED_WALL_BANNER(4378, Directional.class),
    BLACK_WALL_BANNER(4919, Directional.class),
    TORCHFLOWER_CROP(28460, Ageable.class),
    PITCHER_CROP(15420, PitcherCrop.class),
    BEETROOTS(22075, Ageable.class),
    END_GATEWAY(26605),
    FROSTED_ICE(21814, Ageable.class),
    KELP_PLANT(29697),
    DEAD_TUBE_CORAL_WALL_FAN(5128, CoralWallFan.class),
    DEAD_BRAIN_CORAL_WALL_FAN(23718, CoralWallFan.class),
    DEAD_BUBBLE_CORAL_WALL_FAN(18453, CoralWallFan.class),
    DEAD_FIRE_CORAL_WALL_FAN(23375, CoralWallFan.class),
    DEAD_HORN_CORAL_WALL_FAN(27550, CoralWallFan.class),
    TUBE_CORAL_WALL_FAN(25282, CoralWallFan.class),
    BRAIN_CORAL_WALL_FAN(22685, CoralWallFan.class),
    BUBBLE_CORAL_WALL_FAN(20382, CoralWallFan.class),
    FIRE_CORAL_WALL_FAN(20100, CoralWallFan.class),
    HORN_CORAL_WALL_FAN(28883, CoralWallFan.class),
    BAMBOO_SAPLING(8478),
    POTTED_BAMBOO(22542),
    VOID_AIR(13668),
    CAVE_AIR(17422),
    BUBBLE_COLUMN(31612, BubbleColumn.class),
    SWEET_BERRY_BUSH(11958, Ageable.class),
    WEEPING_VINES_PLANT(19437),
    TWISTING_VINES_PLANT(25338),
    CRIMSON_WALL_SIGN(19242, 16, WallSign.class),
    WARPED_WALL_SIGN(13534, 16, WallSign.class),
    POTTED_CRIMSON_FUNGUS(5548),
    POTTED_WARPED_FUNGUS(30800),
    POTTED_CRIMSON_ROOTS(13852),
    POTTED_WARPED_ROOTS(6403),
    CANDLE_CAKE(25423, Lightable.class),
    WHITE_CANDLE_CAKE(12674, Lightable.class),
    ORANGE_CANDLE_CAKE(24982, Lightable.class),
    MAGENTA_CANDLE_CAKE(11022, Lightable.class),
    LIGHT_BLUE_CANDLE_CAKE(7787, Lightable.class),
    YELLOW_CANDLE_CAKE(17157, Lightable.class),
    LIME_CANDLE_CAKE(14309, Lightable.class),
    PINK_CANDLE_CAKE(20405, Lightable.class),
    GRAY_CANDLE_CAKE(6777, Lightable.class),
    LIGHT_GRAY_CANDLE_CAKE(11318, Lightable.class),
    CYAN_CANDLE_CAKE(21202, Lightable.class),
    PURPLE_CANDLE_CAKE(22663, Lightable.class),
    BLUE_CANDLE_CAKE(26425, Lightable.class),
    BROWN_CANDLE_CAKE(26024, Lightable.class),
    GREEN_CANDLE_CAKE(16334, Lightable.class),
    RED_CANDLE_CAKE(24151, Lightable.class),
    BLACK_CANDLE_CAKE(15191, Lightable.class),
    POWDER_SNOW(24077),
    CAVE_VINES(7339, CaveVines.class),
    CAVE_VINES_PLANT(30645, CaveVinesPlant.class),
    BIG_DRIPLEAF_STEM(13167, Dripleaf.class),
    POTTED_AZALEA_BUSH(20430),
    POTTED_FLOWERING_AZALEA_BUSH(10609),
    LEGACY_AIR(0, 0),
    LEGACY_STONE(1),
    LEGACY_GRASS(2),
    LEGACY_DIRT(3),
    LEGACY_COBBLESTONE(4),
    LEGACY_WOOD(5, Wood.class),
    LEGACY_SAPLING(6, Sapling.class),
    LEGACY_BEDROCK(7),
    LEGACY_WATER(8, MaterialData.class),
    LEGACY_STATIONARY_WATER(9, MaterialData.class),
    LEGACY_LAVA(10, MaterialData.class),
    LEGACY_STATIONARY_LAVA(11, MaterialData.class),
    LEGACY_SAND(12),
    LEGACY_GRAVEL(13),
    LEGACY_GOLD_ORE(14),
    LEGACY_IRON_ORE(15),
    LEGACY_COAL_ORE(16),
    LEGACY_LOG(17, Tree.class),
    LEGACY_LEAVES(18, org.bukkit.material.Leaves.class),
    LEGACY_SPONGE(19),
    LEGACY_GLASS(20),
    LEGACY_LAPIS_ORE(21),
    LEGACY_LAPIS_BLOCK(22),
    LEGACY_DISPENSER(23, org.bukkit.material.Dispenser.class),
    LEGACY_SANDSTONE(24, Sandstone.class),
    LEGACY_NOTE_BLOCK(25),
    LEGACY_BED_BLOCK(26, Bed.class),
    LEGACY_POWERED_RAIL(27, PoweredRail.class),
    LEGACY_DETECTOR_RAIL(28, DetectorRail.class),
    LEGACY_PISTON_STICKY_BASE(29, PistonBaseMaterial.class),
    LEGACY_WEB(30),
    LEGACY_LONG_GRASS(31, LongGrass.class),
    LEGACY_DEAD_BUSH(32),
    LEGACY_PISTON_BASE(33, PistonBaseMaterial.class),
    LEGACY_PISTON_EXTENSION(34, PistonExtensionMaterial.class),
    LEGACY_WOOL(35, Wool.class),
    LEGACY_PISTON_MOVING_PIECE(36),
    LEGACY_YELLOW_FLOWER(37),
    LEGACY_RED_ROSE(38),
    LEGACY_BROWN_MUSHROOM(39),
    LEGACY_RED_MUSHROOM(40),
    LEGACY_GOLD_BLOCK(41),
    LEGACY_IRON_BLOCK(42),
    LEGACY_DOUBLE_STEP(43, Step.class),
    LEGACY_STEP(44, Step.class),
    LEGACY_BRICK(45),
    LEGACY_TNT(46),
    LEGACY_BOOKSHELF(47),
    LEGACY_MOSSY_COBBLESTONE(48),
    LEGACY_OBSIDIAN(49),
    LEGACY_TORCH(50, Torch.class),
    LEGACY_FIRE(51),
    LEGACY_MOB_SPAWNER(52),
    LEGACY_WOOD_STAIRS(53, Stairs.class),
    LEGACY_CHEST(54, org.bukkit.material.Chest.class),
    LEGACY_REDSTONE_WIRE(55, org.bukkit.material.RedstoneWire.class),
    LEGACY_DIAMOND_ORE(56),
    LEGACY_DIAMOND_BLOCK(57),
    LEGACY_WORKBENCH(58),
    LEGACY_CROPS(59, Crops.class),
    LEGACY_SOIL(60, MaterialData.class),
    LEGACY_FURNACE(61, Furnace.class),
    LEGACY_BURNING_FURNACE(62, Furnace.class),
    LEGACY_SIGN_POST(63, 64, Sign.class),
    LEGACY_WOODEN_DOOR(64, Door.class),
    LEGACY_LADDER(65, Ladder.class),
    LEGACY_RAILS(66, Rails.class),
    LEGACY_COBBLESTONE_STAIRS(67, Stairs.class),
    LEGACY_WALL_SIGN(68, 64, Sign.class),
    LEGACY_LEVER(69, Lever.class),
    LEGACY_STONE_PLATE(70, PressurePlate.class),
    LEGACY_IRON_DOOR_BLOCK(71, Door.class),
    LEGACY_WOOD_PLATE(72, PressurePlate.class),
    LEGACY_REDSTONE_ORE(73),
    LEGACY_GLOWING_REDSTONE_ORE(74),
    LEGACY_REDSTONE_TORCH_OFF(75, RedstoneTorch.class),
    LEGACY_REDSTONE_TORCH_ON(76, RedstoneTorch.class),
    LEGACY_STONE_BUTTON(77, Button.class),
    LEGACY_SNOW(78),
    LEGACY_ICE(79),
    LEGACY_SNOW_BLOCK(80),
    LEGACY_CACTUS(81, MaterialData.class),
    LEGACY_CLAY(82),
    LEGACY_SUGAR_CANE_BLOCK(83, MaterialData.class),
    LEGACY_JUKEBOX(84),
    LEGACY_FENCE(85),
    LEGACY_PUMPKIN(86, Pumpkin.class),
    LEGACY_NETHERRACK(87),
    LEGACY_SOUL_SAND(88),
    LEGACY_GLOWSTONE(89),
    LEGACY_PORTAL(90),
    LEGACY_JACK_O_LANTERN(91, Pumpkin.class),
    LEGACY_CAKE_BLOCK(92, 64, org.bukkit.material.Cake.class),
    LEGACY_DIODE_BLOCK_OFF(93, Diode.class),
    LEGACY_DIODE_BLOCK_ON(94, Diode.class),
    LEGACY_STAINED_GLASS(95),
    LEGACY_TRAP_DOOR(96, org.bukkit.material.TrapDoor.class),
    LEGACY_MONSTER_EGGS(97, MonsterEggs.class),
    LEGACY_SMOOTH_BRICK(98, SmoothBrick.class),
    LEGACY_HUGE_MUSHROOM_1(99, Mushroom.class),
    LEGACY_HUGE_MUSHROOM_2(100, Mushroom.class),
    LEGACY_IRON_FENCE(101),
    LEGACY_THIN_GLASS(102),
    LEGACY_MELON_BLOCK(103),
    LEGACY_PUMPKIN_STEM(104, MaterialData.class),
    LEGACY_MELON_STEM(105, MaterialData.class),
    LEGACY_VINE(106, Vine.class),
    LEGACY_FENCE_GATE(107, Gate.class),
    LEGACY_BRICK_STAIRS(108, Stairs.class),
    LEGACY_SMOOTH_STAIRS(109, Stairs.class),
    LEGACY_MYCEL(110),
    LEGACY_WATER_LILY(111),
    LEGACY_NETHER_BRICK(112),
    LEGACY_NETHER_FENCE(113),
    LEGACY_NETHER_BRICK_STAIRS(114, Stairs.class),
    LEGACY_NETHER_WARTS(115, NetherWarts.class),
    LEGACY_ENCHANTMENT_TABLE(116),
    LEGACY_BREWING_STAND(117, MaterialData.class),
    LEGACY_CAULDRON(118, Cauldron.class),
    LEGACY_ENDER_PORTAL(119),
    LEGACY_ENDER_PORTAL_FRAME(120),
    LEGACY_ENDER_STONE(121),
    LEGACY_DRAGON_EGG(122),
    LEGACY_REDSTONE_LAMP_OFF(123),
    LEGACY_REDSTONE_LAMP_ON(124),
    LEGACY_WOOD_DOUBLE_STEP(125, Wood.class),
    LEGACY_WOOD_STEP(126, WoodenStep.class),
    LEGACY_COCOA(127, CocoaPlant.class),
    LEGACY_SANDSTONE_STAIRS(128, Stairs.class),
    LEGACY_EMERALD_ORE(129),
    LEGACY_ENDER_CHEST(130, EnderChest.class),
    LEGACY_TRIPWIRE_HOOK(131, TripwireHook.class),
    LEGACY_TRIPWIRE(132, org.bukkit.material.Tripwire.class),
    LEGACY_EMERALD_BLOCK(133),
    LEGACY_SPRUCE_WOOD_STAIRS(134, Stairs.class),
    LEGACY_BIRCH_WOOD_STAIRS(135, Stairs.class),
    LEGACY_JUNGLE_WOOD_STAIRS(136, Stairs.class),
    LEGACY_COMMAND(137, Command.class),
    LEGACY_BEACON(138),
    LEGACY_COBBLE_WALL(139),
    LEGACY_FLOWER_POT(140, FlowerPot.class),
    LEGACY_CARROT(141, Crops.class),
    LEGACY_POTATO(142, Crops.class),
    LEGACY_WOOD_BUTTON(143, Button.class),
    LEGACY_SKULL(144, Skull.class),
    LEGACY_ANVIL(145),
    LEGACY_TRAPPED_CHEST(146, org.bukkit.material.Chest.class),
    LEGACY_GOLD_PLATE(147),
    LEGACY_IRON_PLATE(148),
    LEGACY_REDSTONE_COMPARATOR_OFF(149, org.bukkit.material.Comparator.class),
    LEGACY_REDSTONE_COMPARATOR_ON(150, org.bukkit.material.Comparator.class),
    LEGACY_DAYLIGHT_DETECTOR(151),
    LEGACY_REDSTONE_BLOCK(152),
    LEGACY_QUARTZ_ORE(153),
    LEGACY_HOPPER(154, Hopper.class),
    LEGACY_QUARTZ_BLOCK(155),
    LEGACY_QUARTZ_STAIRS(156, Stairs.class),
    LEGACY_ACTIVATOR_RAIL(157, PoweredRail.class),
    LEGACY_DROPPER(158, org.bukkit.material.Dispenser.class),
    LEGACY_STAINED_CLAY(159),
    LEGACY_STAINED_GLASS_PANE(160),
    LEGACY_LEAVES_2(161, org.bukkit.material.Leaves.class),
    LEGACY_LOG_2(162, Tree.class),
    LEGACY_ACACIA_STAIRS(163, Stairs.class),
    LEGACY_DARK_OAK_STAIRS(164, Stairs.class),
    LEGACY_SLIME_BLOCK(165),
    LEGACY_BARRIER(166),
    LEGACY_IRON_TRAPDOOR(167, org.bukkit.material.TrapDoor.class),
    LEGACY_PRISMARINE(168),
    LEGACY_SEA_LANTERN(169),
    LEGACY_HAY_BLOCK(170),
    LEGACY_CARPET(171),
    LEGACY_HARD_CLAY(172),
    LEGACY_COAL_BLOCK(173),
    LEGACY_PACKED_ICE(174),
    LEGACY_DOUBLE_PLANT(175),
    LEGACY_STANDING_BANNER(176, Banner.class),
    LEGACY_WALL_BANNER(177, Banner.class),
    LEGACY_DAYLIGHT_DETECTOR_INVERTED(178),
    LEGACY_RED_SANDSTONE(179),
    LEGACY_RED_SANDSTONE_STAIRS(180, Stairs.class),
    LEGACY_DOUBLE_STONE_SLAB2(181),
    LEGACY_STONE_SLAB2(182),
    LEGACY_SPRUCE_FENCE_GATE(183, Gate.class),
    LEGACY_BIRCH_FENCE_GATE(184, Gate.class),
    LEGACY_JUNGLE_FENCE_GATE(185, Gate.class),
    LEGACY_DARK_OAK_FENCE_GATE(186, Gate.class),
    LEGACY_ACACIA_FENCE_GATE(187, Gate.class),
    LEGACY_SPRUCE_FENCE(188),
    LEGACY_BIRCH_FENCE(189),
    LEGACY_JUNGLE_FENCE(190),
    LEGACY_DARK_OAK_FENCE(191),
    LEGACY_ACACIA_FENCE(192),
    LEGACY_SPRUCE_DOOR(193, Door.class),
    LEGACY_BIRCH_DOOR(194, Door.class),
    LEGACY_JUNGLE_DOOR(195, Door.class),
    LEGACY_ACACIA_DOOR(196, Door.class),
    LEGACY_DARK_OAK_DOOR(197, Door.class),
    LEGACY_END_ROD(198),
    LEGACY_CHORUS_PLANT(199),
    LEGACY_CHORUS_FLOWER(200),
    LEGACY_PURPUR_BLOCK(201),
    LEGACY_PURPUR_PILLAR(202),
    LEGACY_PURPUR_STAIRS(203, Stairs.class),
    LEGACY_PURPUR_DOUBLE_SLAB(204),
    LEGACY_PURPUR_SLAB(205),
    LEGACY_END_BRICKS(206),
    LEGACY_BEETROOT_BLOCK(207, Crops.class),
    LEGACY_GRASS_PATH(208),
    LEGACY_END_GATEWAY(209),
    LEGACY_COMMAND_REPEATING(210, Command.class),
    LEGACY_COMMAND_CHAIN(211, Command.class),
    LEGACY_FROSTED_ICE(212),
    LEGACY_MAGMA(213),
    LEGACY_NETHER_WART_BLOCK(214),
    LEGACY_RED_NETHER_BRICK(215),
    LEGACY_BONE_BLOCK(216),
    LEGACY_STRUCTURE_VOID(217),
    LEGACY_OBSERVER(218, Observer.class),
    LEGACY_WHITE_SHULKER_BOX(219, 1),
    LEGACY_ORANGE_SHULKER_BOX(220, 1),
    LEGACY_MAGENTA_SHULKER_BOX(221, 1),
    LEGACY_LIGHT_BLUE_SHULKER_BOX(222, 1),
    LEGACY_YELLOW_SHULKER_BOX(223, 1),
    LEGACY_LIME_SHULKER_BOX(224, 1),
    LEGACY_PINK_SHULKER_BOX(225, 1),
    LEGACY_GRAY_SHULKER_BOX(226, 1),
    LEGACY_SILVER_SHULKER_BOX(227, 1),
    LEGACY_CYAN_SHULKER_BOX(228, 1),
    LEGACY_PURPLE_SHULKER_BOX(229, 1),
    LEGACY_BLUE_SHULKER_BOX(230, 1),
    LEGACY_BROWN_SHULKER_BOX(231, 1),
    LEGACY_GREEN_SHULKER_BOX(232, 1),
    LEGACY_RED_SHULKER_BOX(233, 1),
    LEGACY_BLACK_SHULKER_BOX(234, 1),
    LEGACY_WHITE_GLAZED_TERRACOTTA(235),
    LEGACY_ORANGE_GLAZED_TERRACOTTA(236),
    LEGACY_MAGENTA_GLAZED_TERRACOTTA(237),
    LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA(238),
    LEGACY_YELLOW_GLAZED_TERRACOTTA(239),
    LEGACY_LIME_GLAZED_TERRACOTTA(240),
    LEGACY_PINK_GLAZED_TERRACOTTA(241),
    LEGACY_GRAY_GLAZED_TERRACOTTA(242),
    LEGACY_SILVER_GLAZED_TERRACOTTA(243),
    LEGACY_CYAN_GLAZED_TERRACOTTA(244),
    LEGACY_PURPLE_GLAZED_TERRACOTTA(245),
    LEGACY_BLUE_GLAZED_TERRACOTTA(246),
    LEGACY_BROWN_GLAZED_TERRACOTTA(247),
    LEGACY_GREEN_GLAZED_TERRACOTTA(248),
    LEGACY_RED_GLAZED_TERRACOTTA(249),
    LEGACY_BLACK_GLAZED_TERRACOTTA(250),
    LEGACY_CONCRETE(251),
    LEGACY_CONCRETE_POWDER(252),
    LEGACY_STRUCTURE_BLOCK(255),
    LEGACY_IRON_SPADE(256, 1, 250),
    LEGACY_IRON_PICKAXE(257, 1, 250),
    LEGACY_IRON_AXE(258, 1, 250),
    LEGACY_FLINT_AND_STEEL(259, 1, 64),
    LEGACY_APPLE(260),
    LEGACY_BOW(261, 1, 384),
    LEGACY_ARROW(262),
    LEGACY_COAL(263, Coal.class),
    LEGACY_DIAMOND(264),
    LEGACY_IRON_INGOT(265),
    LEGACY_GOLD_INGOT(266),
    LEGACY_IRON_SWORD(267, 1, 250),
    LEGACY_WOOD_SWORD(268, 1, 59),
    LEGACY_WOOD_SPADE(269, 1, 59),
    LEGACY_WOOD_PICKAXE(270, 1, 59),
    LEGACY_WOOD_AXE(271, 1, 59),
    LEGACY_STONE_SWORD(272, 1, 131),
    LEGACY_STONE_SPADE(273, 1, 131),
    LEGACY_STONE_PICKAXE(274, 1, 131),
    LEGACY_STONE_AXE(275, 1, 131),
    LEGACY_DIAMOND_SWORD(276, 1, 1561),
    LEGACY_DIAMOND_SPADE(277, 1, 1561),
    LEGACY_DIAMOND_PICKAXE(278, 1, 1561),
    LEGACY_DIAMOND_AXE(279, 1, 1561),
    LEGACY_STICK(280),
    LEGACY_BOWL(281),
    LEGACY_MUSHROOM_SOUP(282, 1),
    LEGACY_GOLD_SWORD(283, 1, 32),
    LEGACY_GOLD_SPADE(284, 1, 32),
    LEGACY_GOLD_PICKAXE(285, 1, 32),
    LEGACY_GOLD_AXE(286, 1, 32),
    LEGACY_STRING(287),
    LEGACY_FEATHER(288),
    LEGACY_SULPHUR(289),
    LEGACY_WOOD_HOE(290, 1, 59),
    LEGACY_STONE_HOE(291, 1, 131),
    LEGACY_IRON_HOE(292, 1, 250),
    LEGACY_DIAMOND_HOE(293, 1, 1561),
    LEGACY_GOLD_HOE(294, 1, 32),
    LEGACY_SEEDS(295),
    LEGACY_WHEAT(296),
    LEGACY_BREAD(297),
    LEGACY_LEATHER_HELMET(298, 1, 55),
    LEGACY_LEATHER_CHESTPLATE(299, 1, 80),
    LEGACY_LEATHER_LEGGINGS(300, 1, 75),
    LEGACY_LEATHER_BOOTS(301, 1, 65),
    LEGACY_CHAINMAIL_HELMET(302, 1, 165),
    LEGACY_CHAINMAIL_CHESTPLATE(303, 1, 240),
    LEGACY_CHAINMAIL_LEGGINGS(304, 1, 225),
    LEGACY_CHAINMAIL_BOOTS(305, 1, 195),
    LEGACY_IRON_HELMET(306, 1, 165),
    LEGACY_IRON_CHESTPLATE(307, 1, 240),
    LEGACY_IRON_LEGGINGS(308, 1, 225),
    LEGACY_IRON_BOOTS(309, 1, 195),
    LEGACY_DIAMOND_HELMET(310, 1, 363),
    LEGACY_DIAMOND_CHESTPLATE(311, 1, 528),
    LEGACY_DIAMOND_LEGGINGS(312, 1, 495),
    LEGACY_DIAMOND_BOOTS(313, 1, 429),
    LEGACY_GOLD_HELMET(314, 1, 77),
    LEGACY_GOLD_CHESTPLATE(315, 1, 112),
    LEGACY_GOLD_LEGGINGS(316, 1, 105),
    LEGACY_GOLD_BOOTS(317, 1, 91),
    LEGACY_FLINT(318),
    LEGACY_PORK(319),
    LEGACY_GRILLED_PORK(320),
    LEGACY_PAINTING(321),
    LEGACY_GOLDEN_APPLE(322),
    LEGACY_SIGN(323, 16),
    LEGACY_WOOD_DOOR(324, 64),
    LEGACY_BUCKET(325, 16),
    LEGACY_WATER_BUCKET(326, 1),
    LEGACY_LAVA_BUCKET(327, 1),
    LEGACY_MINECART(328, 1),
    LEGACY_SADDLE(329, 1),
    LEGACY_IRON_DOOR(330, 64),
    LEGACY_REDSTONE(331),
    LEGACY_SNOW_BALL(332, 16),
    LEGACY_BOAT(333, 1),
    LEGACY_LEATHER(334),
    LEGACY_MILK_BUCKET(335, 1),
    LEGACY_CLAY_BRICK(336),
    LEGACY_CLAY_BALL(337),
    LEGACY_SUGAR_CANE(338),
    LEGACY_PAPER(339),
    LEGACY_BOOK(340),
    LEGACY_SLIME_BALL(341),
    LEGACY_STORAGE_MINECART(342, 1),
    LEGACY_POWERED_MINECART(343, 1),
    LEGACY_EGG(344, 16),
    LEGACY_COMPASS(345),
    LEGACY_FISHING_ROD(346, 1, 64),
    LEGACY_WATCH(347),
    LEGACY_GLOWSTONE_DUST(348),
    LEGACY_RAW_FISH(349),
    LEGACY_COOKED_FISH(350),
    LEGACY_INK_SACK(351, Dye.class),
    LEGACY_BONE(352),
    LEGACY_SUGAR(353),
    LEGACY_CAKE(354, 1),
    LEGACY_BED(355, 1),
    LEGACY_DIODE(356),
    LEGACY_COOKIE(357),
    LEGACY_MAP(358, MaterialData.class),
    LEGACY_SHEARS(359, 1, 238),
    LEGACY_MELON(360),
    LEGACY_PUMPKIN_SEEDS(361),
    LEGACY_MELON_SEEDS(362),
    LEGACY_RAW_BEEF(363),
    LEGACY_COOKED_BEEF(364),
    LEGACY_RAW_CHICKEN(365),
    LEGACY_COOKED_CHICKEN(366),
    LEGACY_ROTTEN_FLESH(367),
    LEGACY_ENDER_PEARL(368, 16),
    LEGACY_BLAZE_ROD(369),
    LEGACY_GHAST_TEAR(370),
    LEGACY_GOLD_NUGGET(371),
    LEGACY_NETHER_STALK(372),
    LEGACY_POTION(373, 1, MaterialData.class),
    LEGACY_GLASS_BOTTLE(374),
    LEGACY_SPIDER_EYE(375),
    LEGACY_FERMENTED_SPIDER_EYE(376),
    LEGACY_BLAZE_POWDER(377),
    LEGACY_MAGMA_CREAM(378),
    LEGACY_BREWING_STAND_ITEM(379),
    LEGACY_CAULDRON_ITEM(380),
    LEGACY_EYE_OF_ENDER(381),
    LEGACY_SPECKLED_MELON(382),
    LEGACY_MONSTER_EGG(383, 64, SpawnEgg.class),
    LEGACY_EXP_BOTTLE(384, 64),
    LEGACY_FIREBALL(385, 64),
    LEGACY_BOOK_AND_QUILL(386, 1),
    LEGACY_WRITTEN_BOOK(387, 16),
    LEGACY_EMERALD(388, 64),
    LEGACY_ITEM_FRAME(389),
    LEGACY_FLOWER_POT_ITEM(390),
    LEGACY_CARROT_ITEM(391),
    LEGACY_POTATO_ITEM(392),
    LEGACY_BAKED_POTATO(393),
    LEGACY_POISONOUS_POTATO(394),
    LEGACY_EMPTY_MAP(395),
    LEGACY_GOLDEN_CARROT(396),
    LEGACY_SKULL_ITEM(397),
    LEGACY_CARROT_STICK(398, 1, 25),
    LEGACY_NETHER_STAR(399),
    LEGACY_PUMPKIN_PIE(400),
    LEGACY_FIREWORK(401),
    LEGACY_FIREWORK_CHARGE(402),
    LEGACY_ENCHANTED_BOOK(403, 1),
    LEGACY_REDSTONE_COMPARATOR(404),
    LEGACY_NETHER_BRICK_ITEM(405),
    LEGACY_QUARTZ(406),
    LEGACY_EXPLOSIVE_MINECART(407, 1),
    LEGACY_HOPPER_MINECART(408, 1),
    LEGACY_PRISMARINE_SHARD(409),
    LEGACY_PRISMARINE_CRYSTALS(410),
    LEGACY_RABBIT(411),
    LEGACY_COOKED_RABBIT(412),
    LEGACY_RABBIT_STEW(413, 1),
    LEGACY_RABBIT_FOOT(414),
    LEGACY_RABBIT_HIDE(415),
    LEGACY_ARMOR_STAND(416, 16),
    LEGACY_IRON_BARDING(417, 1),
    LEGACY_GOLD_BARDING(418, 1),
    LEGACY_DIAMOND_BARDING(419, 1),
    LEGACY_LEASH(420),
    LEGACY_NAME_TAG(421),
    LEGACY_COMMAND_MINECART(422, 1),
    LEGACY_MUTTON(423),
    LEGACY_COOKED_MUTTON(424),
    LEGACY_BANNER(425, 16),
    LEGACY_END_CRYSTAL(426),
    LEGACY_SPRUCE_DOOR_ITEM(427),
    LEGACY_BIRCH_DOOR_ITEM(428),
    LEGACY_JUNGLE_DOOR_ITEM(429),
    LEGACY_ACACIA_DOOR_ITEM(430),
    LEGACY_DARK_OAK_DOOR_ITEM(431),
    LEGACY_CHORUS_FRUIT(432),
    LEGACY_CHORUS_FRUIT_POPPED(433),
    LEGACY_BEETROOT(434),
    LEGACY_BEETROOT_SEEDS(435),
    LEGACY_BEETROOT_SOUP(436, 1),
    LEGACY_DRAGONS_BREATH(437),
    LEGACY_SPLASH_POTION(438, 1),
    LEGACY_SPECTRAL_ARROW(439),
    LEGACY_TIPPED_ARROW(440),
    LEGACY_LINGERING_POTION(441, 1),
    LEGACY_SHIELD(442, 1, 336),
    LEGACY_ELYTRA(443, 1, 431),
    LEGACY_BOAT_SPRUCE(444, 1),
    LEGACY_BOAT_BIRCH(445, 1),
    LEGACY_BOAT_JUNGLE(446, 1),
    LEGACY_BOAT_ACACIA(447, 1),
    LEGACY_BOAT_DARK_OAK(448, 1),
    LEGACY_TOTEM(449, 1),
    LEGACY_SHULKER_SHELL(450),
    LEGACY_IRON_NUGGET(452),
    LEGACY_KNOWLEDGE_BOOK(453, 1),
    LEGACY_GOLD_RECORD(2256, 1),
    LEGACY_GREEN_RECORD(2257, 1),
    LEGACY_RECORD_3(2258, 1),
    LEGACY_RECORD_4(2259, 1),
    LEGACY_RECORD_5(2260, 1),
    LEGACY_RECORD_6(2261, 1),
    LEGACY_RECORD_7(2262, 1),
    LEGACY_RECORD_8(2263, 1),
    LEGACY_RECORD_9(2264, 1),
    LEGACY_RECORD_10(2265, 1),
    LEGACY_RECORD_11(2266, 1),
    LEGACY_RECORD_12(2267, 1);

    @Deprecated
    public static final String LEGACY_PREFIX = "LEGACY_";
    private final int id;
    private final Constructor<? extends MaterialData> ctor;
    private static final Map<String, Material> BY_NAME;
    private final int maxStack;
    private final short durability;
    public final Class<?> data;
    private final boolean legacy;
    private final NamespacedKey key;

    /*
     * Opcode count of 21781 triggered aggressive code reduction.  Override with --aggressivesizethreshold.
     */
    static {
        BY_NAME = Maps.newHashMap();
        Material[] materialArray = Material.values();
        int n = materialArray.length;
        int n2 = 0;
        while (n2 < n) {
            Material material = materialArray[n2];
            BY_NAME.put(material.name(), material);
            ++n2;
        }
    }

    private Material(int id) {
        this(id, 64);
    }

    private Material(int id, int stack) {
        this(id, stack, MaterialData.class);
    }

    private Material(int id, int stack, int durability) {
        this(id, stack, durability, MaterialData.class);
    }

    private Material(int id, Class<?> data) {
        this(id, 64, data);
    }

    private Material(int id, int stack, Class<?> data) {
        this(id, stack, 0, data);
    }

    private Material(int id, int stack, int durability, Class<?> data) {
        this.id = id;
        this.durability = (short)durability;
        this.maxStack = stack;
        this.data = data;
        this.legacy = this.name().startsWith(LEGACY_PREFIX);
        this.key = NamespacedKey.minecraft(this.name().toLowerCase(Locale.ROOT));
        try {
            this.ctor = MaterialData.class.isAssignableFrom(data) ? data.getConstructor(Material.class, Byte.TYPE) : null;
        }
        catch (NoSuchMethodException ex) {
            throw new AssertionError((Object)ex);
        }
        catch (SecurityException ex) {
            throw new AssertionError((Object)ex);
        }
    }

    @Deprecated
    public int getId() {
        Preconditions.checkArgument((boolean)this.legacy, (Object)"Cannot get ID of Modern Material");
        return this.id;
    }

    @Deprecated
    public boolean isLegacy() {
        return this.legacy;
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        Preconditions.checkArgument((!this.legacy ? 1 : 0) != 0, (Object)"Cannot get key of Legacy Material");
        return this.key;
    }

    public int getMaxStackSize() {
        return this.maxStack;
    }

    public short getMaxDurability() {
        return this.durability;
    }

    @NotNull
    public BlockData createBlockData() {
        return Bukkit.createBlockData(this);
    }

    @NotNull
    public BlockData createBlockData(@Nullable Consumer<BlockData> consumer) {
        return Bukkit.createBlockData(this, consumer);
    }

    @NotNull
    public BlockData createBlockData(@Nullable String data) throws IllegalArgumentException {
        return Bukkit.createBlockData(this, data);
    }

    @NotNull
    public Class<? extends MaterialData> getData() {
        Preconditions.checkArgument((boolean)this.legacy, (Object)"Cannot get data class of Modern Material");
        return this.ctor.getDeclaringClass();
    }

    @Deprecated
    @NotNull
    public MaterialData getNewData(byte raw) {
        Preconditions.checkArgument((boolean)this.legacy, (Object)"Cannot get new data of Modern Material");
        try {
            return this.ctor.newInstance(this, raw);
        }
        catch (InstantiationException ex) {
            Throwable t = ex.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException)t;
            }
            if (t instanceof Error) {
                throw (Error)t;
            }
            throw new AssertionError((Object)t);
        }
        catch (Throwable t) {
            throw new AssertionError((Object)t);
        }
    }

    public boolean isBlock() {
        switch (this) {
            case AIR: 
            case STONE: 
            case GRANITE: 
            case POLISHED_GRANITE: 
            case DIORITE: 
            case POLISHED_DIORITE: 
            case ANDESITE: 
            case POLISHED_ANDESITE: 
            case DEEPSLATE: 
            case COBBLED_DEEPSLATE: 
            case POLISHED_DEEPSLATE: 
            case CALCITE: 
            case TUFF: 
            case DRIPSTONE_BLOCK: 
            case GRASS_BLOCK: 
            case DIRT: 
            case COARSE_DIRT: 
            case PODZOL: 
            case ROOTED_DIRT: 
            case MUD: 
            case CRIMSON_NYLIUM: 
            case WARPED_NYLIUM: 
            case COBBLESTONE: 
            case OAK_PLANKS: 
            case SPRUCE_PLANKS: 
            case BIRCH_PLANKS: 
            case JUNGLE_PLANKS: 
            case ACACIA_PLANKS: 
            case CHERRY_PLANKS: 
            case DARK_OAK_PLANKS: 
            case MANGROVE_PLANKS: 
            case BAMBOO_PLANKS: 
            case CRIMSON_PLANKS: 
            case WARPED_PLANKS: 
            case BAMBOO_MOSAIC: 
            case OAK_SAPLING: 
            case SPRUCE_SAPLING: 
            case BIRCH_SAPLING: 
            case JUNGLE_SAPLING: 
            case ACACIA_SAPLING: 
            case CHERRY_SAPLING: 
            case DARK_OAK_SAPLING: 
            case MANGROVE_PROPAGULE: 
            case BEDROCK: 
            case SAND: 
            case SUSPICIOUS_SAND: 
            case SUSPICIOUS_GRAVEL: 
            case RED_SAND: 
            case GRAVEL: 
            case COAL_ORE: 
            case DEEPSLATE_COAL_ORE: 
            case IRON_ORE: 
            case DEEPSLATE_IRON_ORE: 
            case COPPER_ORE: 
            case DEEPSLATE_COPPER_ORE: 
            case GOLD_ORE: 
            case DEEPSLATE_GOLD_ORE: 
            case REDSTONE_ORE: 
            case DEEPSLATE_REDSTONE_ORE: 
            case EMERALD_ORE: 
            case DEEPSLATE_EMERALD_ORE: 
            case LAPIS_ORE: 
            case DEEPSLATE_LAPIS_ORE: 
            case DIAMOND_ORE: 
            case DEEPSLATE_DIAMOND_ORE: 
            case NETHER_GOLD_ORE: 
            case NETHER_QUARTZ_ORE: 
            case ANCIENT_DEBRIS: 
            case COAL_BLOCK: 
            case RAW_IRON_BLOCK: 
            case RAW_COPPER_BLOCK: 
            case RAW_GOLD_BLOCK: 
            case AMETHYST_BLOCK: 
            case BUDDING_AMETHYST: 
            case IRON_BLOCK: 
            case COPPER_BLOCK: 
            case GOLD_BLOCK: 
            case DIAMOND_BLOCK: 
            case NETHERITE_BLOCK: 
            case EXPOSED_COPPER: 
            case WEATHERED_COPPER: 
            case OXIDIZED_COPPER: 
            case CUT_COPPER: 
            case EXPOSED_CUT_COPPER: 
            case WEATHERED_CUT_COPPER: 
            case OXIDIZED_CUT_COPPER: 
            case CUT_COPPER_STAIRS: 
            case EXPOSED_CUT_COPPER_STAIRS: 
            case WEATHERED_CUT_COPPER_STAIRS: 
            case OXIDIZED_CUT_COPPER_STAIRS: 
            case CUT_COPPER_SLAB: 
            case EXPOSED_CUT_COPPER_SLAB: 
            case WEATHERED_CUT_COPPER_SLAB: 
            case OXIDIZED_CUT_COPPER_SLAB: 
            case WAXED_COPPER_BLOCK: 
            case WAXED_EXPOSED_COPPER: 
            case WAXED_WEATHERED_COPPER: 
            case WAXED_OXIDIZED_COPPER: 
            case WAXED_CUT_COPPER: 
            case WAXED_EXPOSED_CUT_COPPER: 
            case WAXED_WEATHERED_CUT_COPPER: 
            case WAXED_OXIDIZED_CUT_COPPER: 
            case WAXED_CUT_COPPER_STAIRS: 
            case WAXED_EXPOSED_CUT_COPPER_STAIRS: 
            case WAXED_WEATHERED_CUT_COPPER_STAIRS: 
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS: 
            case WAXED_CUT_COPPER_SLAB: 
            case WAXED_EXPOSED_CUT_COPPER_SLAB: 
            case WAXED_WEATHERED_CUT_COPPER_SLAB: 
            case WAXED_OXIDIZED_CUT_COPPER_SLAB: 
            case OAK_LOG: 
            case SPRUCE_LOG: 
            case BIRCH_LOG: 
            case JUNGLE_LOG: 
            case ACACIA_LOG: 
            case CHERRY_LOG: 
            case DARK_OAK_LOG: 
            case MANGROVE_LOG: 
            case MANGROVE_ROOTS: 
            case MUDDY_MANGROVE_ROOTS: 
            case CRIMSON_STEM: 
            case WARPED_STEM: 
            case BAMBOO_BLOCK: 
            case STRIPPED_OAK_LOG: 
            case STRIPPED_SPRUCE_LOG: 
            case STRIPPED_BIRCH_LOG: 
            case STRIPPED_JUNGLE_LOG: 
            case STRIPPED_ACACIA_LOG: 
            case STRIPPED_CHERRY_LOG: 
            case STRIPPED_DARK_OAK_LOG: 
            case STRIPPED_MANGROVE_LOG: 
            case STRIPPED_CRIMSON_STEM: 
            case STRIPPED_WARPED_STEM: 
            case STRIPPED_OAK_WOOD: 
            case STRIPPED_SPRUCE_WOOD: 
            case STRIPPED_BIRCH_WOOD: 
            case STRIPPED_JUNGLE_WOOD: 
            case STRIPPED_ACACIA_WOOD: 
            case STRIPPED_CHERRY_WOOD: 
            case STRIPPED_DARK_OAK_WOOD: 
            case STRIPPED_MANGROVE_WOOD: 
            case STRIPPED_CRIMSON_HYPHAE: 
            case STRIPPED_WARPED_HYPHAE: 
            case STRIPPED_BAMBOO_BLOCK: 
            case OAK_WOOD: 
            case SPRUCE_WOOD: 
            case BIRCH_WOOD: 
            case JUNGLE_WOOD: 
            case ACACIA_WOOD: 
            case CHERRY_WOOD: 
            case DARK_OAK_WOOD: 
            case MANGROVE_WOOD: 
            case CRIMSON_HYPHAE: 
            case WARPED_HYPHAE: 
            case OAK_LEAVES: 
            case SPRUCE_LEAVES: 
            case BIRCH_LEAVES: 
            case JUNGLE_LEAVES: 
            case ACACIA_LEAVES: 
            case CHERRY_LEAVES: 
            case DARK_OAK_LEAVES: 
            case MANGROVE_LEAVES: 
            case AZALEA_LEAVES: 
            case FLOWERING_AZALEA_LEAVES: 
            case SPONGE: 
            case WET_SPONGE: 
            case GLASS: 
            case TINTED_GLASS: 
            case LAPIS_BLOCK: 
            case SANDSTONE: 
            case CHISELED_SANDSTONE: 
            case CUT_SANDSTONE: 
            case COBWEB: 
            case GRASS: 
            case FERN: 
            case AZALEA: 
            case FLOWERING_AZALEA: 
            case DEAD_BUSH: 
            case SEAGRASS: 
            case SEA_PICKLE: 
            case WHITE_WOOL: 
            case ORANGE_WOOL: 
            case MAGENTA_WOOL: 
            case LIGHT_BLUE_WOOL: 
            case YELLOW_WOOL: 
            case LIME_WOOL: 
            case PINK_WOOL: 
            case GRAY_WOOL: 
            case LIGHT_GRAY_WOOL: 
            case CYAN_WOOL: 
            case PURPLE_WOOL: 
            case BLUE_WOOL: 
            case BROWN_WOOL: 
            case GREEN_WOOL: 
            case RED_WOOL: 
            case BLACK_WOOL: 
            case DANDELION: 
            case POPPY: 
            case BLUE_ORCHID: 
            case ALLIUM: 
            case AZURE_BLUET: 
            case RED_TULIP: 
            case ORANGE_TULIP: 
            case WHITE_TULIP: 
            case PINK_TULIP: 
            case OXEYE_DAISY: 
            case CORNFLOWER: 
            case LILY_OF_THE_VALLEY: 
            case WITHER_ROSE: 
            case TORCHFLOWER: 
            case PITCHER_PLANT: 
            case SPORE_BLOSSOM: 
            case BROWN_MUSHROOM: 
            case RED_MUSHROOM: 
            case CRIMSON_FUNGUS: 
            case WARPED_FUNGUS: 
            case CRIMSON_ROOTS: 
            case WARPED_ROOTS: 
            case NETHER_SPROUTS: 
            case WEEPING_VINES: 
            case TWISTING_VINES: 
            case SUGAR_CANE: 
            case KELP: 
            case MOSS_CARPET: 
            case PINK_PETALS: 
            case MOSS_BLOCK: 
            case HANGING_ROOTS: 
            case BIG_DRIPLEAF: 
            case SMALL_DRIPLEAF: 
            case BAMBOO: 
            case OAK_SLAB: 
            case SPRUCE_SLAB: 
            case BIRCH_SLAB: 
            case JUNGLE_SLAB: 
            case ACACIA_SLAB: 
            case CHERRY_SLAB: 
            case DARK_OAK_SLAB: 
            case MANGROVE_SLAB: 
            case BAMBOO_SLAB: 
            case BAMBOO_MOSAIC_SLAB: 
            case CRIMSON_SLAB: 
            case WARPED_SLAB: 
            case STONE_SLAB: 
            case SMOOTH_STONE_SLAB: 
            case SANDSTONE_SLAB: 
            case CUT_SANDSTONE_SLAB: 
            case PETRIFIED_OAK_SLAB: 
            case COBBLESTONE_SLAB: 
            case BRICK_SLAB: 
            case STONE_BRICK_SLAB: 
            case MUD_BRICK_SLAB: 
            case NETHER_BRICK_SLAB: 
            case QUARTZ_SLAB: 
            case RED_SANDSTONE_SLAB: 
            case CUT_RED_SANDSTONE_SLAB: 
            case PURPUR_SLAB: 
            case PRISMARINE_SLAB: 
            case PRISMARINE_BRICK_SLAB: 
            case DARK_PRISMARINE_SLAB: 
            case SMOOTH_QUARTZ: 
            case SMOOTH_RED_SANDSTONE: 
            case SMOOTH_SANDSTONE: 
            case SMOOTH_STONE: 
            case BRICKS: 
            case BOOKSHELF: 
            case CHISELED_BOOKSHELF: 
            case DECORATED_POT: 
            case MOSSY_COBBLESTONE: 
            case OBSIDIAN: 
            case TORCH: 
            case END_ROD: 
            case CHORUS_PLANT: 
            case CHORUS_FLOWER: 
            case PURPUR_BLOCK: 
            case PURPUR_PILLAR: 
            case PURPUR_STAIRS: 
            case SPAWNER: 
            case CHEST: 
            case CRAFTING_TABLE: 
            case FARMLAND: 
            case FURNACE: 
            case LADDER: 
            case COBBLESTONE_STAIRS: 
            case SNOW: 
            case ICE: 
            case SNOW_BLOCK: 
            case CACTUS: 
            case CLAY: 
            case JUKEBOX: 
            case OAK_FENCE: 
            case SPRUCE_FENCE: 
            case BIRCH_FENCE: 
            case JUNGLE_FENCE: 
            case ACACIA_FENCE: 
            case CHERRY_FENCE: 
            case DARK_OAK_FENCE: 
            case MANGROVE_FENCE: 
            case BAMBOO_FENCE: 
            case CRIMSON_FENCE: 
            case WARPED_FENCE: 
            case PUMPKIN: 
            case CARVED_PUMPKIN: 
            case JACK_O_LANTERN: 
            case NETHERRACK: 
            case SOUL_SAND: 
            case SOUL_SOIL: 
            case BASALT: 
            case POLISHED_BASALT: 
            case SMOOTH_BASALT: 
            case SOUL_TORCH: 
            case GLOWSTONE: 
            case INFESTED_STONE: 
            case INFESTED_COBBLESTONE: 
            case INFESTED_STONE_BRICKS: 
            case INFESTED_MOSSY_STONE_BRICKS: 
            case INFESTED_CRACKED_STONE_BRICKS: 
            case INFESTED_CHISELED_STONE_BRICKS: 
            case INFESTED_DEEPSLATE: 
            case STONE_BRICKS: 
            case MOSSY_STONE_BRICKS: 
            case CRACKED_STONE_BRICKS: 
            case CHISELED_STONE_BRICKS: 
            case PACKED_MUD: 
            case MUD_BRICKS: 
            case DEEPSLATE_BRICKS: 
            case CRACKED_DEEPSLATE_BRICKS: 
            case DEEPSLATE_TILES: 
            case CRACKED_DEEPSLATE_TILES: 
            case CHISELED_DEEPSLATE: 
            case REINFORCED_DEEPSLATE: 
            case BROWN_MUSHROOM_BLOCK: 
            case RED_MUSHROOM_BLOCK: 
            case MUSHROOM_STEM: 
            case IRON_BARS: 
            case CHAIN: 
            case GLASS_PANE: 
            case MELON: 
            case VINE: 
            case GLOW_LICHEN: 
            case BRICK_STAIRS: 
            case STONE_BRICK_STAIRS: 
            case MUD_BRICK_STAIRS: 
            case MYCELIUM: 
            case LILY_PAD: 
            case NETHER_BRICKS: 
            case CRACKED_NETHER_BRICKS: 
            case CHISELED_NETHER_BRICKS: 
            case NETHER_BRICK_FENCE: 
            case NETHER_BRICK_STAIRS: 
            case SCULK: 
            case SCULK_VEIN: 
            case SCULK_CATALYST: 
            case SCULK_SHRIEKER: 
            case ENCHANTING_TABLE: 
            case END_PORTAL_FRAME: 
            case END_STONE: 
            case END_STONE_BRICKS: 
            case DRAGON_EGG: 
            case SANDSTONE_STAIRS: 
            case ENDER_CHEST: 
            case EMERALD_BLOCK: 
            case OAK_STAIRS: 
            case SPRUCE_STAIRS: 
            case BIRCH_STAIRS: 
            case JUNGLE_STAIRS: 
            case ACACIA_STAIRS: 
            case CHERRY_STAIRS: 
            case DARK_OAK_STAIRS: 
            case MANGROVE_STAIRS: 
            case BAMBOO_STAIRS: 
            case BAMBOO_MOSAIC_STAIRS: 
            case CRIMSON_STAIRS: 
            case WARPED_STAIRS: 
            case COMMAND_BLOCK: 
            case BEACON: 
            case COBBLESTONE_WALL: 
            case MOSSY_COBBLESTONE_WALL: 
            case BRICK_WALL: 
            case PRISMARINE_WALL: 
            case RED_SANDSTONE_WALL: 
            case MOSSY_STONE_BRICK_WALL: 
            case GRANITE_WALL: 
            case STONE_BRICK_WALL: 
            case MUD_BRICK_WALL: 
            case NETHER_BRICK_WALL: 
            case ANDESITE_WALL: 
            case RED_NETHER_BRICK_WALL: 
            case SANDSTONE_WALL: 
            case END_STONE_BRICK_WALL: 
            case DIORITE_WALL: 
            case BLACKSTONE_WALL: 
            case POLISHED_BLACKSTONE_WALL: 
            case POLISHED_BLACKSTONE_BRICK_WALL: 
            case COBBLED_DEEPSLATE_WALL: 
            case POLISHED_DEEPSLATE_WALL: 
            case DEEPSLATE_BRICK_WALL: 
            case DEEPSLATE_TILE_WALL: 
            case ANVIL: 
            case CHIPPED_ANVIL: 
            case DAMAGED_ANVIL: 
            case CHISELED_QUARTZ_BLOCK: 
            case QUARTZ_BLOCK: 
            case QUARTZ_BRICKS: 
            case QUARTZ_PILLAR: 
            case QUARTZ_STAIRS: 
            case WHITE_TERRACOTTA: 
            case ORANGE_TERRACOTTA: 
            case MAGENTA_TERRACOTTA: 
            case LIGHT_BLUE_TERRACOTTA: 
            case YELLOW_TERRACOTTA: 
            case LIME_TERRACOTTA: 
            case PINK_TERRACOTTA: 
            case GRAY_TERRACOTTA: 
            case LIGHT_GRAY_TERRACOTTA: 
            case CYAN_TERRACOTTA: 
            case PURPLE_TERRACOTTA: 
            case BLUE_TERRACOTTA: 
            case BROWN_TERRACOTTA: 
            case GREEN_TERRACOTTA: 
            case RED_TERRACOTTA: 
            case BLACK_TERRACOTTA: 
            case BARRIER: 
            case LIGHT: 
            case HAY_BLOCK: 
            case WHITE_CARPET: 
            case ORANGE_CARPET: 
            case MAGENTA_CARPET: 
            case LIGHT_BLUE_CARPET: 
            case YELLOW_CARPET: 
            case LIME_CARPET: 
            case PINK_CARPET: 
            case GRAY_CARPET: 
            case LIGHT_GRAY_CARPET: 
            case CYAN_CARPET: 
            case PURPLE_CARPET: 
            case BLUE_CARPET: 
            case BROWN_CARPET: 
            case GREEN_CARPET: 
            case RED_CARPET: 
            case BLACK_CARPET: 
            case TERRACOTTA: 
            case PACKED_ICE: 
            case DIRT_PATH: 
            case SUNFLOWER: 
            case LILAC: 
            case ROSE_BUSH: 
            case PEONY: 
            case TALL_GRASS: 
            case LARGE_FERN: 
            case WHITE_STAINED_GLASS: 
            case ORANGE_STAINED_GLASS: 
            case MAGENTA_STAINED_GLASS: 
            case LIGHT_BLUE_STAINED_GLASS: 
            case YELLOW_STAINED_GLASS: 
            case LIME_STAINED_GLASS: 
            case PINK_STAINED_GLASS: 
            case GRAY_STAINED_GLASS: 
            case LIGHT_GRAY_STAINED_GLASS: 
            case CYAN_STAINED_GLASS: 
            case PURPLE_STAINED_GLASS: 
            case BLUE_STAINED_GLASS: 
            case BROWN_STAINED_GLASS: 
            case GREEN_STAINED_GLASS: 
            case RED_STAINED_GLASS: 
            case BLACK_STAINED_GLASS: 
            case WHITE_STAINED_GLASS_PANE: 
            case ORANGE_STAINED_GLASS_PANE: 
            case MAGENTA_STAINED_GLASS_PANE: 
            case LIGHT_BLUE_STAINED_GLASS_PANE: 
            case YELLOW_STAINED_GLASS_PANE: 
            case LIME_STAINED_GLASS_PANE: 
            case PINK_STAINED_GLASS_PANE: 
            case GRAY_STAINED_GLASS_PANE: 
            case LIGHT_GRAY_STAINED_GLASS_PANE: 
            case CYAN_STAINED_GLASS_PANE: 
            case PURPLE_STAINED_GLASS_PANE: 
            case BLUE_STAINED_GLASS_PANE: 
            case BROWN_STAINED_GLASS_PANE: 
            case GREEN_STAINED_GLASS_PANE: 
            case RED_STAINED_GLASS_PANE: 
            case BLACK_STAINED_GLASS_PANE: 
            case PRISMARINE: 
            case PRISMARINE_BRICKS: 
            case DARK_PRISMARINE: 
            case PRISMARINE_STAIRS: 
            case PRISMARINE_BRICK_STAIRS: 
            case DARK_PRISMARINE_STAIRS: 
            case SEA_LANTERN: 
            case RED_SANDSTONE: 
            case CHISELED_RED_SANDSTONE: 
            case CUT_RED_SANDSTONE: 
            case RED_SANDSTONE_STAIRS: 
            case REPEATING_COMMAND_BLOCK: 
            case CHAIN_COMMAND_BLOCK: 
            case MAGMA_BLOCK: 
            case NETHER_WART_BLOCK: 
            case WARPED_WART_BLOCK: 
            case RED_NETHER_BRICKS: 
            case BONE_BLOCK: 
            case STRUCTURE_VOID: 
            case SHULKER_BOX: 
            case WHITE_SHULKER_BOX: 
            case ORANGE_SHULKER_BOX: 
            case MAGENTA_SHULKER_BOX: 
            case LIGHT_BLUE_SHULKER_BOX: 
            case YELLOW_SHULKER_BOX: 
            case LIME_SHULKER_BOX: 
            case PINK_SHULKER_BOX: 
            case GRAY_SHULKER_BOX: 
            case LIGHT_GRAY_SHULKER_BOX: 
            case CYAN_SHULKER_BOX: 
            case PURPLE_SHULKER_BOX: 
            case BLUE_SHULKER_BOX: 
            case BROWN_SHULKER_BOX: 
            case GREEN_SHULKER_BOX: 
            case RED_SHULKER_BOX: 
            case BLACK_SHULKER_BOX: 
            case WHITE_GLAZED_TERRACOTTA: 
            case ORANGE_GLAZED_TERRACOTTA: 
            case MAGENTA_GLAZED_TERRACOTTA: 
            case LIGHT_BLUE_GLAZED_TERRACOTTA: 
            case YELLOW_GLAZED_TERRACOTTA: 
            case LIME_GLAZED_TERRACOTTA: 
            case PINK_GLAZED_TERRACOTTA: 
            case GRAY_GLAZED_TERRACOTTA: 
            case LIGHT_GRAY_GLAZED_TERRACOTTA: 
            case CYAN_GLAZED_TERRACOTTA: 
            case PURPLE_GLAZED_TERRACOTTA: 
            case BLUE_GLAZED_TERRACOTTA: 
            case BROWN_GLAZED_TERRACOTTA: 
            case GREEN_GLAZED_TERRACOTTA: 
            case RED_GLAZED_TERRACOTTA: 
            case BLACK_GLAZED_TERRACOTTA: 
            case WHITE_CONCRETE: 
            case ORANGE_CONCRETE: 
            case MAGENTA_CONCRETE: 
            case LIGHT_BLUE_CONCRETE: 
            case YELLOW_CONCRETE: 
            case LIME_CONCRETE: 
            case PINK_CONCRETE: 
            case GRAY_CONCRETE: 
            case LIGHT_GRAY_CONCRETE: 
            case CYAN_CONCRETE: 
            case PURPLE_CONCRETE: 
            case BLUE_CONCRETE: 
            case BROWN_CONCRETE: 
            case GREEN_CONCRETE: 
            case RED_CONCRETE: 
            case BLACK_CONCRETE: 
            case WHITE_CONCRETE_POWDER: 
            case ORANGE_CONCRETE_POWDER: 
            case MAGENTA_CONCRETE_POWDER: 
            case LIGHT_BLUE_CONCRETE_POWDER: 
            case YELLOW_CONCRETE_POWDER: 
            case LIME_CONCRETE_POWDER: 
            case PINK_CONCRETE_POWDER: 
            case GRAY_CONCRETE_POWDER: 
            case LIGHT_GRAY_CONCRETE_POWDER: 
            case CYAN_CONCRETE_POWDER: 
            case PURPLE_CONCRETE_POWDER: 
            case BLUE_CONCRETE_POWDER: 
            case BROWN_CONCRETE_POWDER: 
            case GREEN_CONCRETE_POWDER: 
            case RED_CONCRETE_POWDER: 
            case BLACK_CONCRETE_POWDER: 
            case TURTLE_EGG: 
            case SNIFFER_EGG: 
            case DEAD_TUBE_CORAL_BLOCK: 
            case DEAD_BRAIN_CORAL_BLOCK: 
            case DEAD_BUBBLE_CORAL_BLOCK: 
            case DEAD_FIRE_CORAL_BLOCK: 
            case DEAD_HORN_CORAL_BLOCK: 
            case TUBE_CORAL_BLOCK: 
            case BRAIN_CORAL_BLOCK: 
            case BUBBLE_CORAL_BLOCK: 
            case FIRE_CORAL_BLOCK: 
            case HORN_CORAL_BLOCK: 
            case TUBE_CORAL: 
            case BRAIN_CORAL: 
            case BUBBLE_CORAL: 
            case FIRE_CORAL: 
            case HORN_CORAL: 
            case DEAD_BRAIN_CORAL: 
            case DEAD_BUBBLE_CORAL: 
            case DEAD_FIRE_CORAL: 
            case DEAD_HORN_CORAL: 
            case DEAD_TUBE_CORAL: 
            case TUBE_CORAL_FAN: 
            case BRAIN_CORAL_FAN: 
            case BUBBLE_CORAL_FAN: 
            case FIRE_CORAL_FAN: 
            case HORN_CORAL_FAN: 
            case DEAD_TUBE_CORAL_FAN: 
            case DEAD_BRAIN_CORAL_FAN: 
            case DEAD_BUBBLE_CORAL_FAN: 
            case DEAD_FIRE_CORAL_FAN: 
            case DEAD_HORN_CORAL_FAN: 
            case BLUE_ICE: 
            case CONDUIT: 
            case POLISHED_GRANITE_STAIRS: 
            case SMOOTH_RED_SANDSTONE_STAIRS: 
            case MOSSY_STONE_BRICK_STAIRS: 
            case POLISHED_DIORITE_STAIRS: 
            case MOSSY_COBBLESTONE_STAIRS: 
            case END_STONE_BRICK_STAIRS: 
            case STONE_STAIRS: 
            case SMOOTH_SANDSTONE_STAIRS: 
            case SMOOTH_QUARTZ_STAIRS: 
            case GRANITE_STAIRS: 
            case ANDESITE_STAIRS: 
            case RED_NETHER_BRICK_STAIRS: 
            case POLISHED_ANDESITE_STAIRS: 
            case DIORITE_STAIRS: 
            case COBBLED_DEEPSLATE_STAIRS: 
            case POLISHED_DEEPSLATE_STAIRS: 
            case DEEPSLATE_BRICK_STAIRS: 
            case DEEPSLATE_TILE_STAIRS: 
            case POLISHED_GRANITE_SLAB: 
            case SMOOTH_RED_SANDSTONE_SLAB: 
            case MOSSY_STONE_BRICK_SLAB: 
            case POLISHED_DIORITE_SLAB: 
            case MOSSY_COBBLESTONE_SLAB: 
            case END_STONE_BRICK_SLAB: 
            case SMOOTH_SANDSTONE_SLAB: 
            case SMOOTH_QUARTZ_SLAB: 
            case GRANITE_SLAB: 
            case ANDESITE_SLAB: 
            case RED_NETHER_BRICK_SLAB: 
            case POLISHED_ANDESITE_SLAB: 
            case DIORITE_SLAB: 
            case COBBLED_DEEPSLATE_SLAB: 
            case POLISHED_DEEPSLATE_SLAB: 
            case DEEPSLATE_BRICK_SLAB: 
            case DEEPSLATE_TILE_SLAB: 
            case SCAFFOLDING: 
            case REDSTONE_TORCH: 
            case REDSTONE_BLOCK: 
            case REPEATER: 
            case COMPARATOR: 
            case PISTON: 
            case STICKY_PISTON: 
            case SLIME_BLOCK: 
            case HONEY_BLOCK: 
            case OBSERVER: 
            case HOPPER: 
            case DISPENSER: 
            case DROPPER: 
            case LECTERN: 
            case TARGET: 
            case LEVER: 
            case LIGHTNING_ROD: 
            case DAYLIGHT_DETECTOR: 
            case SCULK_SENSOR: 
            case CALIBRATED_SCULK_SENSOR: 
            case TRIPWIRE_HOOK: 
            case TRAPPED_CHEST: 
            case TNT: 
            case REDSTONE_LAMP: 
            case NOTE_BLOCK: 
            case STONE_BUTTON: 
            case POLISHED_BLACKSTONE_BUTTON: 
            case OAK_BUTTON: 
            case SPRUCE_BUTTON: 
            case BIRCH_BUTTON: 
            case JUNGLE_BUTTON: 
            case ACACIA_BUTTON: 
            case CHERRY_BUTTON: 
            case DARK_OAK_BUTTON: 
            case MANGROVE_BUTTON: 
            case BAMBOO_BUTTON: 
            case CRIMSON_BUTTON: 
            case WARPED_BUTTON: 
            case STONE_PRESSURE_PLATE: 
            case POLISHED_BLACKSTONE_PRESSURE_PLATE: 
            case LIGHT_WEIGHTED_PRESSURE_PLATE: 
            case HEAVY_WEIGHTED_PRESSURE_PLATE: 
            case OAK_PRESSURE_PLATE: 
            case SPRUCE_PRESSURE_PLATE: 
            case BIRCH_PRESSURE_PLATE: 
            case JUNGLE_PRESSURE_PLATE: 
            case ACACIA_PRESSURE_PLATE: 
            case CHERRY_PRESSURE_PLATE: 
            case DARK_OAK_PRESSURE_PLATE: 
            case MANGROVE_PRESSURE_PLATE: 
            case BAMBOO_PRESSURE_PLATE: 
            case CRIMSON_PRESSURE_PLATE: 
            case WARPED_PRESSURE_PLATE: 
            case IRON_DOOR: 
            case OAK_DOOR: 
            case SPRUCE_DOOR: 
            case BIRCH_DOOR: 
            case JUNGLE_DOOR: 
            case ACACIA_DOOR: 
            case CHERRY_DOOR: 
            case DARK_OAK_DOOR: 
            case MANGROVE_DOOR: 
            case BAMBOO_DOOR: 
            case CRIMSON_DOOR: 
            case WARPED_DOOR: 
            case IRON_TRAPDOOR: 
            case OAK_TRAPDOOR: 
            case SPRUCE_TRAPDOOR: 
            case BIRCH_TRAPDOOR: 
            case JUNGLE_TRAPDOOR: 
            case ACACIA_TRAPDOOR: 
            case CHERRY_TRAPDOOR: 
            case DARK_OAK_TRAPDOOR: 
            case MANGROVE_TRAPDOOR: 
            case BAMBOO_TRAPDOOR: 
            case CRIMSON_TRAPDOOR: 
            case WARPED_TRAPDOOR: 
            case OAK_FENCE_GATE: 
            case SPRUCE_FENCE_GATE: 
            case BIRCH_FENCE_GATE: 
            case JUNGLE_FENCE_GATE: 
            case ACACIA_FENCE_GATE: 
            case CHERRY_FENCE_GATE: 
            case DARK_OAK_FENCE_GATE: 
            case MANGROVE_FENCE_GATE: 
            case BAMBOO_FENCE_GATE: 
            case CRIMSON_FENCE_GATE: 
            case WARPED_FENCE_GATE: 
            case POWERED_RAIL: 
            case DETECTOR_RAIL: 
            case RAIL: 
            case ACTIVATOR_RAIL: 
            case STRUCTURE_BLOCK: 
            case JIGSAW: 
            case WHEAT: 
            case OAK_SIGN: 
            case SPRUCE_SIGN: 
            case BIRCH_SIGN: 
            case JUNGLE_SIGN: 
            case ACACIA_SIGN: 
            case CHERRY_SIGN: 
            case DARK_OAK_SIGN: 
            case MANGROVE_SIGN: 
            case BAMBOO_SIGN: 
            case CRIMSON_SIGN: 
            case WARPED_SIGN: 
            case OAK_HANGING_SIGN: 
            case SPRUCE_HANGING_SIGN: 
            case BIRCH_HANGING_SIGN: 
            case JUNGLE_HANGING_SIGN: 
            case ACACIA_HANGING_SIGN: 
            case CHERRY_HANGING_SIGN: 
            case DARK_OAK_HANGING_SIGN: 
            case MANGROVE_HANGING_SIGN: 
            case BAMBOO_HANGING_SIGN: 
            case CRIMSON_HANGING_SIGN: 
            case WARPED_HANGING_SIGN: 
            case DRIED_KELP_BLOCK: 
            case CAKE: 
            case WHITE_BED: 
            case ORANGE_BED: 
            case MAGENTA_BED: 
            case LIGHT_BLUE_BED: 
            case YELLOW_BED: 
            case LIME_BED: 
            case PINK_BED: 
            case GRAY_BED: 
            case LIGHT_GRAY_BED: 
            case CYAN_BED: 
            case PURPLE_BED: 
            case BLUE_BED: 
            case BROWN_BED: 
            case GREEN_BED: 
            case RED_BED: 
            case BLACK_BED: 
            case NETHER_WART: 
            case BREWING_STAND: 
            case CAULDRON: 
            case FLOWER_POT: 
            case SKELETON_SKULL: 
            case WITHER_SKELETON_SKULL: 
            case PLAYER_HEAD: 
            case ZOMBIE_HEAD: 
            case CREEPER_HEAD: 
            case DRAGON_HEAD: 
            case PIGLIN_HEAD: 
            case WHITE_BANNER: 
            case ORANGE_BANNER: 
            case MAGENTA_BANNER: 
            case LIGHT_BLUE_BANNER: 
            case YELLOW_BANNER: 
            case LIME_BANNER: 
            case PINK_BANNER: 
            case GRAY_BANNER: 
            case LIGHT_GRAY_BANNER: 
            case CYAN_BANNER: 
            case PURPLE_BANNER: 
            case BLUE_BANNER: 
            case BROWN_BANNER: 
            case GREEN_BANNER: 
            case RED_BANNER: 
            case BLACK_BANNER: 
            case LOOM: 
            case COMPOSTER: 
            case BARREL: 
            case SMOKER: 
            case BLAST_FURNACE: 
            case CARTOGRAPHY_TABLE: 
            case FLETCHING_TABLE: 
            case GRINDSTONE: 
            case SMITHING_TABLE: 
            case STONECUTTER: 
            case BELL: 
            case LANTERN: 
            case SOUL_LANTERN: 
            case CAMPFIRE: 
            case SOUL_CAMPFIRE: 
            case SHROOMLIGHT: 
            case BEE_NEST: 
            case BEEHIVE: 
            case HONEYCOMB_BLOCK: 
            case LODESTONE: 
            case CRYING_OBSIDIAN: 
            case BLACKSTONE: 
            case BLACKSTONE_SLAB: 
            case BLACKSTONE_STAIRS: 
            case GILDED_BLACKSTONE: 
            case POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_SLAB: 
            case POLISHED_BLACKSTONE_STAIRS: 
            case CHISELED_POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_BRICKS: 
            case POLISHED_BLACKSTONE_BRICK_SLAB: 
            case POLISHED_BLACKSTONE_BRICK_STAIRS: 
            case CRACKED_POLISHED_BLACKSTONE_BRICKS: 
            case RESPAWN_ANCHOR: 
            case CANDLE: 
            case WHITE_CANDLE: 
            case ORANGE_CANDLE: 
            case MAGENTA_CANDLE: 
            case LIGHT_BLUE_CANDLE: 
            case YELLOW_CANDLE: 
            case LIME_CANDLE: 
            case PINK_CANDLE: 
            case GRAY_CANDLE: 
            case LIGHT_GRAY_CANDLE: 
            case CYAN_CANDLE: 
            case PURPLE_CANDLE: 
            case BLUE_CANDLE: 
            case BROWN_CANDLE: 
            case GREEN_CANDLE: 
            case RED_CANDLE: 
            case BLACK_CANDLE: 
            case SMALL_AMETHYST_BUD: 
            case MEDIUM_AMETHYST_BUD: 
            case LARGE_AMETHYST_BUD: 
            case AMETHYST_CLUSTER: 
            case POINTED_DRIPSTONE: 
            case OCHRE_FROGLIGHT: 
            case VERDANT_FROGLIGHT: 
            case PEARLESCENT_FROGLIGHT: 
            case FROGSPAWN: 
            case WATER: 
            case LAVA: 
            case TALL_SEAGRASS: 
            case PISTON_HEAD: 
            case MOVING_PISTON: 
            case WALL_TORCH: 
            case FIRE: 
            case SOUL_FIRE: 
            case REDSTONE_WIRE: 
            case OAK_WALL_SIGN: 
            case SPRUCE_WALL_SIGN: 
            case BIRCH_WALL_SIGN: 
            case ACACIA_WALL_SIGN: 
            case CHERRY_WALL_SIGN: 
            case JUNGLE_WALL_SIGN: 
            case DARK_OAK_WALL_SIGN: 
            case MANGROVE_WALL_SIGN: 
            case BAMBOO_WALL_SIGN: 
            case OAK_WALL_HANGING_SIGN: 
            case SPRUCE_WALL_HANGING_SIGN: 
            case BIRCH_WALL_HANGING_SIGN: 
            case ACACIA_WALL_HANGING_SIGN: 
            case CHERRY_WALL_HANGING_SIGN: 
            case JUNGLE_WALL_HANGING_SIGN: 
            case DARK_OAK_WALL_HANGING_SIGN: 
            case MANGROVE_WALL_HANGING_SIGN: 
            case CRIMSON_WALL_HANGING_SIGN: 
            case WARPED_WALL_HANGING_SIGN: 
            case BAMBOO_WALL_HANGING_SIGN: 
            case REDSTONE_WALL_TORCH: 
            case SOUL_WALL_TORCH: 
            case NETHER_PORTAL: 
            case ATTACHED_PUMPKIN_STEM: 
            case ATTACHED_MELON_STEM: 
            case PUMPKIN_STEM: 
            case MELON_STEM: 
            case WATER_CAULDRON: 
            case LAVA_CAULDRON: 
            case POWDER_SNOW_CAULDRON: 
            case END_PORTAL: 
            case COCOA: 
            case TRIPWIRE: 
            case POTTED_TORCHFLOWER: 
            case POTTED_OAK_SAPLING: 
            case POTTED_SPRUCE_SAPLING: 
            case POTTED_BIRCH_SAPLING: 
            case POTTED_JUNGLE_SAPLING: 
            case POTTED_ACACIA_SAPLING: 
            case POTTED_CHERRY_SAPLING: 
            case POTTED_DARK_OAK_SAPLING: 
            case POTTED_MANGROVE_PROPAGULE: 
            case POTTED_FERN: 
            case POTTED_DANDELION: 
            case POTTED_POPPY: 
            case POTTED_BLUE_ORCHID: 
            case POTTED_ALLIUM: 
            case POTTED_AZURE_BLUET: 
            case POTTED_RED_TULIP: 
            case POTTED_ORANGE_TULIP: 
            case POTTED_WHITE_TULIP: 
            case POTTED_PINK_TULIP: 
            case POTTED_OXEYE_DAISY: 
            case POTTED_CORNFLOWER: 
            case POTTED_LILY_OF_THE_VALLEY: 
            case POTTED_WITHER_ROSE: 
            case POTTED_RED_MUSHROOM: 
            case POTTED_BROWN_MUSHROOM: 
            case POTTED_DEAD_BUSH: 
            case POTTED_CACTUS: 
            case CARROTS: 
            case POTATOES: 
            case SKELETON_WALL_SKULL: 
            case WITHER_SKELETON_WALL_SKULL: 
            case ZOMBIE_WALL_HEAD: 
            case PLAYER_WALL_HEAD: 
            case CREEPER_WALL_HEAD: 
            case DRAGON_WALL_HEAD: 
            case PIGLIN_WALL_HEAD: 
            case WHITE_WALL_BANNER: 
            case ORANGE_WALL_BANNER: 
            case MAGENTA_WALL_BANNER: 
            case LIGHT_BLUE_WALL_BANNER: 
            case YELLOW_WALL_BANNER: 
            case LIME_WALL_BANNER: 
            case PINK_WALL_BANNER: 
            case GRAY_WALL_BANNER: 
            case LIGHT_GRAY_WALL_BANNER: 
            case CYAN_WALL_BANNER: 
            case PURPLE_WALL_BANNER: 
            case BLUE_WALL_BANNER: 
            case BROWN_WALL_BANNER: 
            case GREEN_WALL_BANNER: 
            case RED_WALL_BANNER: 
            case BLACK_WALL_BANNER: 
            case TORCHFLOWER_CROP: 
            case PITCHER_CROP: 
            case BEETROOTS: 
            case END_GATEWAY: 
            case FROSTED_ICE: 
            case KELP_PLANT: 
            case DEAD_TUBE_CORAL_WALL_FAN: 
            case DEAD_BRAIN_CORAL_WALL_FAN: 
            case DEAD_BUBBLE_CORAL_WALL_FAN: 
            case DEAD_FIRE_CORAL_WALL_FAN: 
            case DEAD_HORN_CORAL_WALL_FAN: 
            case TUBE_CORAL_WALL_FAN: 
            case BRAIN_CORAL_WALL_FAN: 
            case BUBBLE_CORAL_WALL_FAN: 
            case FIRE_CORAL_WALL_FAN: 
            case HORN_CORAL_WALL_FAN: 
            case BAMBOO_SAPLING: 
            case POTTED_BAMBOO: 
            case VOID_AIR: 
            case CAVE_AIR: 
            case BUBBLE_COLUMN: 
            case SWEET_BERRY_BUSH: 
            case WEEPING_VINES_PLANT: 
            case TWISTING_VINES_PLANT: 
            case CRIMSON_WALL_SIGN: 
            case WARPED_WALL_SIGN: 
            case POTTED_CRIMSON_FUNGUS: 
            case POTTED_WARPED_FUNGUS: 
            case POTTED_CRIMSON_ROOTS: 
            case POTTED_WARPED_ROOTS: 
            case CANDLE_CAKE: 
            case WHITE_CANDLE_CAKE: 
            case ORANGE_CANDLE_CAKE: 
            case MAGENTA_CANDLE_CAKE: 
            case LIGHT_BLUE_CANDLE_CAKE: 
            case YELLOW_CANDLE_CAKE: 
            case LIME_CANDLE_CAKE: 
            case PINK_CANDLE_CAKE: 
            case GRAY_CANDLE_CAKE: 
            case LIGHT_GRAY_CANDLE_CAKE: 
            case CYAN_CANDLE_CAKE: 
            case PURPLE_CANDLE_CAKE: 
            case BLUE_CANDLE_CAKE: 
            case BROWN_CANDLE_CAKE: 
            case GREEN_CANDLE_CAKE: 
            case RED_CANDLE_CAKE: 
            case BLACK_CANDLE_CAKE: 
            case POWDER_SNOW: 
            case CAVE_VINES: 
            case CAVE_VINES_PLANT: 
            case BIG_DRIPLEAF_STEM: 
            case POTTED_AZALEA_BUSH: 
            case POTTED_FLOWERING_AZALEA_BUSH: {
                return true;
            }
        }
        return this.id >= 0 && this.id < 256;
    }

    public boolean isEdible() {
        switch (this) {
            case APPLE: 
            case MUSHROOM_STEW: 
            case BREAD: 
            case PORKCHOP: 
            case COOKED_PORKCHOP: 
            case GOLDEN_APPLE: 
            case ENCHANTED_GOLDEN_APPLE: 
            case COD: 
            case SALMON: 
            case TROPICAL_FISH: 
            case PUFFERFISH: 
            case COOKED_COD: 
            case COOKED_SALMON: 
            case COOKIE: 
            case MELON_SLICE: 
            case DRIED_KELP: 
            case BEEF: 
            case COOKED_BEEF: 
            case CHICKEN: 
            case COOKED_CHICKEN: 
            case ROTTEN_FLESH: 
            case SPIDER_EYE: 
            case CARROT: 
            case POTATO: 
            case BAKED_POTATO: 
            case POISONOUS_POTATO: 
            case GOLDEN_CARROT: 
            case PUMPKIN_PIE: 
            case RABBIT: 
            case COOKED_RABBIT: 
            case RABBIT_STEW: 
            case MUTTON: 
            case COOKED_MUTTON: 
            case CHORUS_FRUIT: 
            case BEETROOT: 
            case BEETROOT_SOUP: 
            case SUSPICIOUS_STEW: 
            case SWEET_BERRIES: 
            case GLOW_BERRIES: 
            case HONEY_BOTTLE: 
            case LEGACY_APPLE: 
            case LEGACY_MUSHROOM_SOUP: 
            case LEGACY_BREAD: 
            case LEGACY_PORK: 
            case LEGACY_GRILLED_PORK: 
            case LEGACY_GOLDEN_APPLE: 
            case LEGACY_RAW_FISH: 
            case LEGACY_COOKED_FISH: 
            case LEGACY_COOKIE: 
            case LEGACY_MELON: 
            case LEGACY_RAW_BEEF: 
            case LEGACY_COOKED_BEEF: 
            case LEGACY_RAW_CHICKEN: 
            case LEGACY_COOKED_CHICKEN: 
            case LEGACY_ROTTEN_FLESH: 
            case LEGACY_SPIDER_EYE: 
            case LEGACY_CARROT_ITEM: 
            case LEGACY_POTATO_ITEM: 
            case LEGACY_BAKED_POTATO: 
            case LEGACY_POISONOUS_POTATO: 
            case LEGACY_GOLDEN_CARROT: 
            case LEGACY_PUMPKIN_PIE: 
            case LEGACY_RABBIT: 
            case LEGACY_COOKED_RABBIT: 
            case LEGACY_RABBIT_STEW: 
            case LEGACY_MUTTON: 
            case LEGACY_COOKED_MUTTON: 
            case LEGACY_CHORUS_FRUIT: 
            case LEGACY_BEETROOT: 
            case LEGACY_BEETROOT_SOUP: {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public static Material getMaterial(@NotNull String name) {
        return Material.getMaterial(name, false);
    }

    @Nullable
    public static Material getMaterial(@NotNull String name, boolean legacyName) {
        if (legacyName) {
            if (!name.startsWith(LEGACY_PREFIX)) {
                name = LEGACY_PREFIX + name;
            }
            Material match = BY_NAME.get(name);
            return Bukkit.getUnsafe().fromLegacy(match);
        }
        return BY_NAME.get(name);
    }

    @Nullable
    public static Material matchMaterial(@NotNull String name) {
        return Material.matchMaterial(name, false);
    }

    @Nullable
    public static Material matchMaterial(@NotNull String name, boolean legacyName) {
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Name cannot be null");
        String filtered = name;
        if (filtered.startsWith("minecraft:")) {
            filtered = filtered.substring("minecraft:".length());
        }
        filtered = filtered.toUpperCase(Locale.ENGLISH);
        filtered = filtered.replaceAll("\\s+", "_").replaceAll("\\W", "");
        return Material.getMaterial(filtered, legacyName);
    }

    public boolean isRecord() {
        switch (this) {
            case MUSIC_DISC_13: 
            case MUSIC_DISC_CAT: 
            case MUSIC_DISC_BLOCKS: 
            case MUSIC_DISC_CHIRP: 
            case MUSIC_DISC_FAR: 
            case MUSIC_DISC_MALL: 
            case MUSIC_DISC_MELLOHI: 
            case MUSIC_DISC_STAL: 
            case MUSIC_DISC_STRAD: 
            case MUSIC_DISC_WARD: 
            case MUSIC_DISC_11: 
            case MUSIC_DISC_WAIT: 
            case MUSIC_DISC_OTHERSIDE: 
            case MUSIC_DISC_RELIC: 
            case MUSIC_DISC_5: 
            case MUSIC_DISC_PIGSTEP: {
                return true;
            }
        }
        return this.id >= Material.LEGACY_GOLD_RECORD.id && this.id <= Material.LEGACY_RECORD_12.id;
    }

    public boolean isSolid() {
        if (!this.isBlock() || this.id == 0) {
            return false;
        }
        switch (this) {
            case STONE: 
            case GRANITE: 
            case POLISHED_GRANITE: 
            case DIORITE: 
            case POLISHED_DIORITE: 
            case ANDESITE: 
            case POLISHED_ANDESITE: 
            case DEEPSLATE: 
            case COBBLED_DEEPSLATE: 
            case POLISHED_DEEPSLATE: 
            case CALCITE: 
            case TUFF: 
            case DRIPSTONE_BLOCK: 
            case GRASS_BLOCK: 
            case DIRT: 
            case COARSE_DIRT: 
            case PODZOL: 
            case ROOTED_DIRT: 
            case MUD: 
            case CRIMSON_NYLIUM: 
            case WARPED_NYLIUM: 
            case COBBLESTONE: 
            case OAK_PLANKS: 
            case SPRUCE_PLANKS: 
            case BIRCH_PLANKS: 
            case JUNGLE_PLANKS: 
            case ACACIA_PLANKS: 
            case CHERRY_PLANKS: 
            case DARK_OAK_PLANKS: 
            case MANGROVE_PLANKS: 
            case BAMBOO_PLANKS: 
            case CRIMSON_PLANKS: 
            case WARPED_PLANKS: 
            case BAMBOO_MOSAIC: 
            case BEDROCK: 
            case SAND: 
            case SUSPICIOUS_SAND: 
            case SUSPICIOUS_GRAVEL: 
            case RED_SAND: 
            case GRAVEL: 
            case COAL_ORE: 
            case DEEPSLATE_COAL_ORE: 
            case IRON_ORE: 
            case DEEPSLATE_IRON_ORE: 
            case COPPER_ORE: 
            case DEEPSLATE_COPPER_ORE: 
            case GOLD_ORE: 
            case DEEPSLATE_GOLD_ORE: 
            case REDSTONE_ORE: 
            case DEEPSLATE_REDSTONE_ORE: 
            case EMERALD_ORE: 
            case DEEPSLATE_EMERALD_ORE: 
            case LAPIS_ORE: 
            case DEEPSLATE_LAPIS_ORE: 
            case DIAMOND_ORE: 
            case DEEPSLATE_DIAMOND_ORE: 
            case NETHER_GOLD_ORE: 
            case NETHER_QUARTZ_ORE: 
            case ANCIENT_DEBRIS: 
            case COAL_BLOCK: 
            case RAW_IRON_BLOCK: 
            case RAW_COPPER_BLOCK: 
            case RAW_GOLD_BLOCK: 
            case AMETHYST_BLOCK: 
            case BUDDING_AMETHYST: 
            case IRON_BLOCK: 
            case COPPER_BLOCK: 
            case GOLD_BLOCK: 
            case DIAMOND_BLOCK: 
            case NETHERITE_BLOCK: 
            case EXPOSED_COPPER: 
            case WEATHERED_COPPER: 
            case OXIDIZED_COPPER: 
            case CUT_COPPER: 
            case EXPOSED_CUT_COPPER: 
            case WEATHERED_CUT_COPPER: 
            case OXIDIZED_CUT_COPPER: 
            case CUT_COPPER_STAIRS: 
            case EXPOSED_CUT_COPPER_STAIRS: 
            case WEATHERED_CUT_COPPER_STAIRS: 
            case OXIDIZED_CUT_COPPER_STAIRS: 
            case CUT_COPPER_SLAB: 
            case EXPOSED_CUT_COPPER_SLAB: 
            case WEATHERED_CUT_COPPER_SLAB: 
            case OXIDIZED_CUT_COPPER_SLAB: 
            case WAXED_COPPER_BLOCK: 
            case WAXED_EXPOSED_COPPER: 
            case WAXED_WEATHERED_COPPER: 
            case WAXED_OXIDIZED_COPPER: 
            case WAXED_CUT_COPPER: 
            case WAXED_EXPOSED_CUT_COPPER: 
            case WAXED_WEATHERED_CUT_COPPER: 
            case WAXED_OXIDIZED_CUT_COPPER: 
            case WAXED_CUT_COPPER_STAIRS: 
            case WAXED_EXPOSED_CUT_COPPER_STAIRS: 
            case WAXED_WEATHERED_CUT_COPPER_STAIRS: 
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS: 
            case WAXED_CUT_COPPER_SLAB: 
            case WAXED_EXPOSED_CUT_COPPER_SLAB: 
            case WAXED_WEATHERED_CUT_COPPER_SLAB: 
            case WAXED_OXIDIZED_CUT_COPPER_SLAB: 
            case OAK_LOG: 
            case SPRUCE_LOG: 
            case BIRCH_LOG: 
            case JUNGLE_LOG: 
            case ACACIA_LOG: 
            case CHERRY_LOG: 
            case DARK_OAK_LOG: 
            case MANGROVE_LOG: 
            case MANGROVE_ROOTS: 
            case MUDDY_MANGROVE_ROOTS: 
            case CRIMSON_STEM: 
            case WARPED_STEM: 
            case BAMBOO_BLOCK: 
            case STRIPPED_OAK_LOG: 
            case STRIPPED_SPRUCE_LOG: 
            case STRIPPED_BIRCH_LOG: 
            case STRIPPED_JUNGLE_LOG: 
            case STRIPPED_ACACIA_LOG: 
            case STRIPPED_CHERRY_LOG: 
            case STRIPPED_DARK_OAK_LOG: 
            case STRIPPED_MANGROVE_LOG: 
            case STRIPPED_CRIMSON_STEM: 
            case STRIPPED_WARPED_STEM: 
            case STRIPPED_OAK_WOOD: 
            case STRIPPED_SPRUCE_WOOD: 
            case STRIPPED_BIRCH_WOOD: 
            case STRIPPED_JUNGLE_WOOD: 
            case STRIPPED_ACACIA_WOOD: 
            case STRIPPED_CHERRY_WOOD: 
            case STRIPPED_DARK_OAK_WOOD: 
            case STRIPPED_MANGROVE_WOOD: 
            case STRIPPED_CRIMSON_HYPHAE: 
            case STRIPPED_WARPED_HYPHAE: 
            case STRIPPED_BAMBOO_BLOCK: 
            case OAK_WOOD: 
            case SPRUCE_WOOD: 
            case BIRCH_WOOD: 
            case JUNGLE_WOOD: 
            case ACACIA_WOOD: 
            case CHERRY_WOOD: 
            case DARK_OAK_WOOD: 
            case MANGROVE_WOOD: 
            case CRIMSON_HYPHAE: 
            case WARPED_HYPHAE: 
            case OAK_LEAVES: 
            case SPRUCE_LEAVES: 
            case BIRCH_LEAVES: 
            case JUNGLE_LEAVES: 
            case ACACIA_LEAVES: 
            case CHERRY_LEAVES: 
            case DARK_OAK_LEAVES: 
            case MANGROVE_LEAVES: 
            case AZALEA_LEAVES: 
            case FLOWERING_AZALEA_LEAVES: 
            case SPONGE: 
            case WET_SPONGE: 
            case GLASS: 
            case TINTED_GLASS: 
            case LAPIS_BLOCK: 
            case SANDSTONE: 
            case CHISELED_SANDSTONE: 
            case CUT_SANDSTONE: 
            case WHITE_WOOL: 
            case ORANGE_WOOL: 
            case MAGENTA_WOOL: 
            case LIGHT_BLUE_WOOL: 
            case YELLOW_WOOL: 
            case LIME_WOOL: 
            case PINK_WOOL: 
            case GRAY_WOOL: 
            case LIGHT_GRAY_WOOL: 
            case CYAN_WOOL: 
            case PURPLE_WOOL: 
            case BLUE_WOOL: 
            case BROWN_WOOL: 
            case GREEN_WOOL: 
            case RED_WOOL: 
            case BLACK_WOOL: 
            case MOSS_BLOCK: 
            case BAMBOO: 
            case OAK_SLAB: 
            case SPRUCE_SLAB: 
            case BIRCH_SLAB: 
            case JUNGLE_SLAB: 
            case ACACIA_SLAB: 
            case CHERRY_SLAB: 
            case DARK_OAK_SLAB: 
            case MANGROVE_SLAB: 
            case BAMBOO_SLAB: 
            case BAMBOO_MOSAIC_SLAB: 
            case CRIMSON_SLAB: 
            case WARPED_SLAB: 
            case STONE_SLAB: 
            case SMOOTH_STONE_SLAB: 
            case SANDSTONE_SLAB: 
            case CUT_SANDSTONE_SLAB: 
            case PETRIFIED_OAK_SLAB: 
            case COBBLESTONE_SLAB: 
            case BRICK_SLAB: 
            case STONE_BRICK_SLAB: 
            case MUD_BRICK_SLAB: 
            case NETHER_BRICK_SLAB: 
            case QUARTZ_SLAB: 
            case RED_SANDSTONE_SLAB: 
            case CUT_RED_SANDSTONE_SLAB: 
            case PURPUR_SLAB: 
            case PRISMARINE_SLAB: 
            case PRISMARINE_BRICK_SLAB: 
            case DARK_PRISMARINE_SLAB: 
            case SMOOTH_QUARTZ: 
            case SMOOTH_RED_SANDSTONE: 
            case SMOOTH_SANDSTONE: 
            case SMOOTH_STONE: 
            case BRICKS: 
            case BOOKSHELF: 
            case CHISELED_BOOKSHELF: 
            case DECORATED_POT: 
            case MOSSY_COBBLESTONE: 
            case OBSIDIAN: 
            case PURPUR_BLOCK: 
            case PURPUR_PILLAR: 
            case PURPUR_STAIRS: 
            case SPAWNER: 
            case CHEST: 
            case CRAFTING_TABLE: 
            case FARMLAND: 
            case FURNACE: 
            case COBBLESTONE_STAIRS: 
            case ICE: 
            case SNOW_BLOCK: 
            case CACTUS: 
            case CLAY: 
            case JUKEBOX: 
            case OAK_FENCE: 
            case SPRUCE_FENCE: 
            case BIRCH_FENCE: 
            case JUNGLE_FENCE: 
            case ACACIA_FENCE: 
            case CHERRY_FENCE: 
            case DARK_OAK_FENCE: 
            case MANGROVE_FENCE: 
            case BAMBOO_FENCE: 
            case CRIMSON_FENCE: 
            case WARPED_FENCE: 
            case PUMPKIN: 
            case CARVED_PUMPKIN: 
            case JACK_O_LANTERN: 
            case NETHERRACK: 
            case SOUL_SAND: 
            case SOUL_SOIL: 
            case BASALT: 
            case POLISHED_BASALT: 
            case SMOOTH_BASALT: 
            case GLOWSTONE: 
            case INFESTED_STONE: 
            case INFESTED_COBBLESTONE: 
            case INFESTED_STONE_BRICKS: 
            case INFESTED_MOSSY_STONE_BRICKS: 
            case INFESTED_CRACKED_STONE_BRICKS: 
            case INFESTED_CHISELED_STONE_BRICKS: 
            case INFESTED_DEEPSLATE: 
            case STONE_BRICKS: 
            case MOSSY_STONE_BRICKS: 
            case CRACKED_STONE_BRICKS: 
            case CHISELED_STONE_BRICKS: 
            case PACKED_MUD: 
            case MUD_BRICKS: 
            case DEEPSLATE_BRICKS: 
            case CRACKED_DEEPSLATE_BRICKS: 
            case DEEPSLATE_TILES: 
            case CRACKED_DEEPSLATE_TILES: 
            case CHISELED_DEEPSLATE: 
            case REINFORCED_DEEPSLATE: 
            case BROWN_MUSHROOM_BLOCK: 
            case RED_MUSHROOM_BLOCK: 
            case MUSHROOM_STEM: 
            case IRON_BARS: 
            case CHAIN: 
            case GLASS_PANE: 
            case MELON: 
            case BRICK_STAIRS: 
            case STONE_BRICK_STAIRS: 
            case MUD_BRICK_STAIRS: 
            case MYCELIUM: 
            case NETHER_BRICKS: 
            case CRACKED_NETHER_BRICKS: 
            case CHISELED_NETHER_BRICKS: 
            case NETHER_BRICK_FENCE: 
            case NETHER_BRICK_STAIRS: 
            case SCULK: 
            case SCULK_VEIN: 
            case SCULK_CATALYST: 
            case SCULK_SHRIEKER: 
            case ENCHANTING_TABLE: 
            case END_PORTAL_FRAME: 
            case END_STONE: 
            case END_STONE_BRICKS: 
            case DRAGON_EGG: 
            case SANDSTONE_STAIRS: 
            case ENDER_CHEST: 
            case EMERALD_BLOCK: 
            case OAK_STAIRS: 
            case SPRUCE_STAIRS: 
            case BIRCH_STAIRS: 
            case JUNGLE_STAIRS: 
            case ACACIA_STAIRS: 
            case CHERRY_STAIRS: 
            case DARK_OAK_STAIRS: 
            case MANGROVE_STAIRS: 
            case BAMBOO_STAIRS: 
            case BAMBOO_MOSAIC_STAIRS: 
            case CRIMSON_STAIRS: 
            case WARPED_STAIRS: 
            case COMMAND_BLOCK: 
            case BEACON: 
            case COBBLESTONE_WALL: 
            case MOSSY_COBBLESTONE_WALL: 
            case BRICK_WALL: 
            case PRISMARINE_WALL: 
            case RED_SANDSTONE_WALL: 
            case MOSSY_STONE_BRICK_WALL: 
            case GRANITE_WALL: 
            case STONE_BRICK_WALL: 
            case MUD_BRICK_WALL: 
            case NETHER_BRICK_WALL: 
            case ANDESITE_WALL: 
            case RED_NETHER_BRICK_WALL: 
            case SANDSTONE_WALL: 
            case END_STONE_BRICK_WALL: 
            case DIORITE_WALL: 
            case BLACKSTONE_WALL: 
            case POLISHED_BLACKSTONE_WALL: 
            case POLISHED_BLACKSTONE_BRICK_WALL: 
            case COBBLED_DEEPSLATE_WALL: 
            case POLISHED_DEEPSLATE_WALL: 
            case DEEPSLATE_BRICK_WALL: 
            case DEEPSLATE_TILE_WALL: 
            case ANVIL: 
            case CHIPPED_ANVIL: 
            case DAMAGED_ANVIL: 
            case CHISELED_QUARTZ_BLOCK: 
            case QUARTZ_BLOCK: 
            case QUARTZ_BRICKS: 
            case QUARTZ_PILLAR: 
            case QUARTZ_STAIRS: 
            case WHITE_TERRACOTTA: 
            case ORANGE_TERRACOTTA: 
            case MAGENTA_TERRACOTTA: 
            case LIGHT_BLUE_TERRACOTTA: 
            case YELLOW_TERRACOTTA: 
            case LIME_TERRACOTTA: 
            case PINK_TERRACOTTA: 
            case GRAY_TERRACOTTA: 
            case LIGHT_GRAY_TERRACOTTA: 
            case CYAN_TERRACOTTA: 
            case PURPLE_TERRACOTTA: 
            case BLUE_TERRACOTTA: 
            case BROWN_TERRACOTTA: 
            case GREEN_TERRACOTTA: 
            case RED_TERRACOTTA: 
            case BLACK_TERRACOTTA: 
            case BARRIER: 
            case HAY_BLOCK: 
            case TERRACOTTA: 
            case PACKED_ICE: 
            case DIRT_PATH: 
            case WHITE_STAINED_GLASS: 
            case ORANGE_STAINED_GLASS: 
            case MAGENTA_STAINED_GLASS: 
            case LIGHT_BLUE_STAINED_GLASS: 
            case YELLOW_STAINED_GLASS: 
            case LIME_STAINED_GLASS: 
            case PINK_STAINED_GLASS: 
            case GRAY_STAINED_GLASS: 
            case LIGHT_GRAY_STAINED_GLASS: 
            case CYAN_STAINED_GLASS: 
            case PURPLE_STAINED_GLASS: 
            case BLUE_STAINED_GLASS: 
            case BROWN_STAINED_GLASS: 
            case GREEN_STAINED_GLASS: 
            case RED_STAINED_GLASS: 
            case BLACK_STAINED_GLASS: 
            case WHITE_STAINED_GLASS_PANE: 
            case ORANGE_STAINED_GLASS_PANE: 
            case MAGENTA_STAINED_GLASS_PANE: 
            case LIGHT_BLUE_STAINED_GLASS_PANE: 
            case YELLOW_STAINED_GLASS_PANE: 
            case LIME_STAINED_GLASS_PANE: 
            case PINK_STAINED_GLASS_PANE: 
            case GRAY_STAINED_GLASS_PANE: 
            case LIGHT_GRAY_STAINED_GLASS_PANE: 
            case CYAN_STAINED_GLASS_PANE: 
            case PURPLE_STAINED_GLASS_PANE: 
            case BLUE_STAINED_GLASS_PANE: 
            case BROWN_STAINED_GLASS_PANE: 
            case GREEN_STAINED_GLASS_PANE: 
            case RED_STAINED_GLASS_PANE: 
            case BLACK_STAINED_GLASS_PANE: 
            case PRISMARINE: 
            case PRISMARINE_BRICKS: 
            case DARK_PRISMARINE: 
            case PRISMARINE_STAIRS: 
            case PRISMARINE_BRICK_STAIRS: 
            case DARK_PRISMARINE_STAIRS: 
            case SEA_LANTERN: 
            case RED_SANDSTONE: 
            case CHISELED_RED_SANDSTONE: 
            case CUT_RED_SANDSTONE: 
            case RED_SANDSTONE_STAIRS: 
            case REPEATING_COMMAND_BLOCK: 
            case CHAIN_COMMAND_BLOCK: 
            case MAGMA_BLOCK: 
            case NETHER_WART_BLOCK: 
            case WARPED_WART_BLOCK: 
            case RED_NETHER_BRICKS: 
            case BONE_BLOCK: 
            case SHULKER_BOX: 
            case WHITE_SHULKER_BOX: 
            case ORANGE_SHULKER_BOX: 
            case MAGENTA_SHULKER_BOX: 
            case LIGHT_BLUE_SHULKER_BOX: 
            case YELLOW_SHULKER_BOX: 
            case LIME_SHULKER_BOX: 
            case PINK_SHULKER_BOX: 
            case GRAY_SHULKER_BOX: 
            case LIGHT_GRAY_SHULKER_BOX: 
            case CYAN_SHULKER_BOX: 
            case PURPLE_SHULKER_BOX: 
            case BLUE_SHULKER_BOX: 
            case BROWN_SHULKER_BOX: 
            case GREEN_SHULKER_BOX: 
            case RED_SHULKER_BOX: 
            case BLACK_SHULKER_BOX: 
            case WHITE_GLAZED_TERRACOTTA: 
            case ORANGE_GLAZED_TERRACOTTA: 
            case MAGENTA_GLAZED_TERRACOTTA: 
            case LIGHT_BLUE_GLAZED_TERRACOTTA: 
            case YELLOW_GLAZED_TERRACOTTA: 
            case LIME_GLAZED_TERRACOTTA: 
            case PINK_GLAZED_TERRACOTTA: 
            case GRAY_GLAZED_TERRACOTTA: 
            case LIGHT_GRAY_GLAZED_TERRACOTTA: 
            case CYAN_GLAZED_TERRACOTTA: 
            case PURPLE_GLAZED_TERRACOTTA: 
            case BLUE_GLAZED_TERRACOTTA: 
            case BROWN_GLAZED_TERRACOTTA: 
            case GREEN_GLAZED_TERRACOTTA: 
            case RED_GLAZED_TERRACOTTA: 
            case BLACK_GLAZED_TERRACOTTA: 
            case WHITE_CONCRETE: 
            case ORANGE_CONCRETE: 
            case MAGENTA_CONCRETE: 
            case LIGHT_BLUE_CONCRETE: 
            case YELLOW_CONCRETE: 
            case LIME_CONCRETE: 
            case PINK_CONCRETE: 
            case GRAY_CONCRETE: 
            case LIGHT_GRAY_CONCRETE: 
            case CYAN_CONCRETE: 
            case PURPLE_CONCRETE: 
            case BLUE_CONCRETE: 
            case BROWN_CONCRETE: 
            case GREEN_CONCRETE: 
            case RED_CONCRETE: 
            case BLACK_CONCRETE: 
            case WHITE_CONCRETE_POWDER: 
            case ORANGE_CONCRETE_POWDER: 
            case MAGENTA_CONCRETE_POWDER: 
            case LIGHT_BLUE_CONCRETE_POWDER: 
            case YELLOW_CONCRETE_POWDER: 
            case LIME_CONCRETE_POWDER: 
            case PINK_CONCRETE_POWDER: 
            case GRAY_CONCRETE_POWDER: 
            case LIGHT_GRAY_CONCRETE_POWDER: 
            case CYAN_CONCRETE_POWDER: 
            case PURPLE_CONCRETE_POWDER: 
            case BLUE_CONCRETE_POWDER: 
            case BROWN_CONCRETE_POWDER: 
            case GREEN_CONCRETE_POWDER: 
            case RED_CONCRETE_POWDER: 
            case BLACK_CONCRETE_POWDER: 
            case TURTLE_EGG: 
            case SNIFFER_EGG: 
            case DEAD_TUBE_CORAL_BLOCK: 
            case DEAD_BRAIN_CORAL_BLOCK: 
            case DEAD_BUBBLE_CORAL_BLOCK: 
            case DEAD_FIRE_CORAL_BLOCK: 
            case DEAD_HORN_CORAL_BLOCK: 
            case TUBE_CORAL_BLOCK: 
            case BRAIN_CORAL_BLOCK: 
            case BUBBLE_CORAL_BLOCK: 
            case FIRE_CORAL_BLOCK: 
            case HORN_CORAL_BLOCK: 
            case DEAD_BRAIN_CORAL: 
            case DEAD_BUBBLE_CORAL: 
            case DEAD_FIRE_CORAL: 
            case DEAD_HORN_CORAL: 
            case DEAD_TUBE_CORAL: 
            case DEAD_TUBE_CORAL_FAN: 
            case DEAD_BRAIN_CORAL_FAN: 
            case DEAD_BUBBLE_CORAL_FAN: 
            case DEAD_FIRE_CORAL_FAN: 
            case DEAD_HORN_CORAL_FAN: 
            case BLUE_ICE: 
            case CONDUIT: 
            case POLISHED_GRANITE_STAIRS: 
            case SMOOTH_RED_SANDSTONE_STAIRS: 
            case MOSSY_STONE_BRICK_STAIRS: 
            case POLISHED_DIORITE_STAIRS: 
            case MOSSY_COBBLESTONE_STAIRS: 
            case END_STONE_BRICK_STAIRS: 
            case STONE_STAIRS: 
            case SMOOTH_SANDSTONE_STAIRS: 
            case SMOOTH_QUARTZ_STAIRS: 
            case GRANITE_STAIRS: 
            case ANDESITE_STAIRS: 
            case RED_NETHER_BRICK_STAIRS: 
            case POLISHED_ANDESITE_STAIRS: 
            case DIORITE_STAIRS: 
            case COBBLED_DEEPSLATE_STAIRS: 
            case POLISHED_DEEPSLATE_STAIRS: 
            case DEEPSLATE_BRICK_STAIRS: 
            case DEEPSLATE_TILE_STAIRS: 
            case POLISHED_GRANITE_SLAB: 
            case SMOOTH_RED_SANDSTONE_SLAB: 
            case MOSSY_STONE_BRICK_SLAB: 
            case POLISHED_DIORITE_SLAB: 
            case MOSSY_COBBLESTONE_SLAB: 
            case END_STONE_BRICK_SLAB: 
            case SMOOTH_SANDSTONE_SLAB: 
            case SMOOTH_QUARTZ_SLAB: 
            case GRANITE_SLAB: 
            case ANDESITE_SLAB: 
            case RED_NETHER_BRICK_SLAB: 
            case POLISHED_ANDESITE_SLAB: 
            case DIORITE_SLAB: 
            case COBBLED_DEEPSLATE_SLAB: 
            case POLISHED_DEEPSLATE_SLAB: 
            case DEEPSLATE_BRICK_SLAB: 
            case DEEPSLATE_TILE_SLAB: 
            case REDSTONE_BLOCK: 
            case PISTON: 
            case STICKY_PISTON: 
            case SLIME_BLOCK: 
            case HONEY_BLOCK: 
            case OBSERVER: 
            case HOPPER: 
            case DISPENSER: 
            case DROPPER: 
            case LECTERN: 
            case TARGET: 
            case LIGHTNING_ROD: 
            case DAYLIGHT_DETECTOR: 
            case SCULK_SENSOR: 
            case CALIBRATED_SCULK_SENSOR: 
            case TRAPPED_CHEST: 
            case TNT: 
            case REDSTONE_LAMP: 
            case NOTE_BLOCK: 
            case STONE_PRESSURE_PLATE: 
            case POLISHED_BLACKSTONE_PRESSURE_PLATE: 
            case LIGHT_WEIGHTED_PRESSURE_PLATE: 
            case HEAVY_WEIGHTED_PRESSURE_PLATE: 
            case OAK_PRESSURE_PLATE: 
            case SPRUCE_PRESSURE_PLATE: 
            case BIRCH_PRESSURE_PLATE: 
            case JUNGLE_PRESSURE_PLATE: 
            case ACACIA_PRESSURE_PLATE: 
            case CHERRY_PRESSURE_PLATE: 
            case DARK_OAK_PRESSURE_PLATE: 
            case MANGROVE_PRESSURE_PLATE: 
            case BAMBOO_PRESSURE_PLATE: 
            case CRIMSON_PRESSURE_PLATE: 
            case WARPED_PRESSURE_PLATE: 
            case IRON_DOOR: 
            case OAK_DOOR: 
            case SPRUCE_DOOR: 
            case BIRCH_DOOR: 
            case JUNGLE_DOOR: 
            case ACACIA_DOOR: 
            case CHERRY_DOOR: 
            case DARK_OAK_DOOR: 
            case MANGROVE_DOOR: 
            case BAMBOO_DOOR: 
            case CRIMSON_DOOR: 
            case WARPED_DOOR: 
            case IRON_TRAPDOOR: 
            case OAK_TRAPDOOR: 
            case SPRUCE_TRAPDOOR: 
            case BIRCH_TRAPDOOR: 
            case JUNGLE_TRAPDOOR: 
            case ACACIA_TRAPDOOR: 
            case CHERRY_TRAPDOOR: 
            case DARK_OAK_TRAPDOOR: 
            case MANGROVE_TRAPDOOR: 
            case BAMBOO_TRAPDOOR: 
            case CRIMSON_TRAPDOOR: 
            case WARPED_TRAPDOOR: 
            case OAK_FENCE_GATE: 
            case SPRUCE_FENCE_GATE: 
            case BIRCH_FENCE_GATE: 
            case JUNGLE_FENCE_GATE: 
            case ACACIA_FENCE_GATE: 
            case CHERRY_FENCE_GATE: 
            case DARK_OAK_FENCE_GATE: 
            case MANGROVE_FENCE_GATE: 
            case BAMBOO_FENCE_GATE: 
            case CRIMSON_FENCE_GATE: 
            case WARPED_FENCE_GATE: 
            case STRUCTURE_BLOCK: 
            case JIGSAW: 
            case OAK_SIGN: 
            case SPRUCE_SIGN: 
            case BIRCH_SIGN: 
            case JUNGLE_SIGN: 
            case ACACIA_SIGN: 
            case CHERRY_SIGN: 
            case DARK_OAK_SIGN: 
            case MANGROVE_SIGN: 
            case BAMBOO_SIGN: 
            case CRIMSON_SIGN: 
            case WARPED_SIGN: 
            case OAK_HANGING_SIGN: 
            case SPRUCE_HANGING_SIGN: 
            case BIRCH_HANGING_SIGN: 
            case JUNGLE_HANGING_SIGN: 
            case ACACIA_HANGING_SIGN: 
            case CHERRY_HANGING_SIGN: 
            case DARK_OAK_HANGING_SIGN: 
            case MANGROVE_HANGING_SIGN: 
            case BAMBOO_HANGING_SIGN: 
            case CRIMSON_HANGING_SIGN: 
            case WARPED_HANGING_SIGN: 
            case DRIED_KELP_BLOCK: 
            case CAKE: 
            case WHITE_BED: 
            case ORANGE_BED: 
            case MAGENTA_BED: 
            case LIGHT_BLUE_BED: 
            case YELLOW_BED: 
            case LIME_BED: 
            case PINK_BED: 
            case GRAY_BED: 
            case LIGHT_GRAY_BED: 
            case CYAN_BED: 
            case PURPLE_BED: 
            case BLUE_BED: 
            case BROWN_BED: 
            case GREEN_BED: 
            case RED_BED: 
            case BLACK_BED: 
            case BREWING_STAND: 
            case CAULDRON: 
            case WHITE_BANNER: 
            case ORANGE_BANNER: 
            case MAGENTA_BANNER: 
            case LIGHT_BLUE_BANNER: 
            case YELLOW_BANNER: 
            case LIME_BANNER: 
            case PINK_BANNER: 
            case GRAY_BANNER: 
            case LIGHT_GRAY_BANNER: 
            case CYAN_BANNER: 
            case PURPLE_BANNER: 
            case BLUE_BANNER: 
            case BROWN_BANNER: 
            case GREEN_BANNER: 
            case RED_BANNER: 
            case BLACK_BANNER: 
            case LOOM: 
            case COMPOSTER: 
            case BARREL: 
            case SMOKER: 
            case BLAST_FURNACE: 
            case CARTOGRAPHY_TABLE: 
            case FLETCHING_TABLE: 
            case GRINDSTONE: 
            case SMITHING_TABLE: 
            case STONECUTTER: 
            case BELL: 
            case LANTERN: 
            case SOUL_LANTERN: 
            case CAMPFIRE: 
            case SOUL_CAMPFIRE: 
            case SHROOMLIGHT: 
            case BEE_NEST: 
            case BEEHIVE: 
            case HONEYCOMB_BLOCK: 
            case LODESTONE: 
            case CRYING_OBSIDIAN: 
            case BLACKSTONE: 
            case BLACKSTONE_SLAB: 
            case BLACKSTONE_STAIRS: 
            case GILDED_BLACKSTONE: 
            case POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_SLAB: 
            case POLISHED_BLACKSTONE_STAIRS: 
            case CHISELED_POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_BRICKS: 
            case POLISHED_BLACKSTONE_BRICK_SLAB: 
            case POLISHED_BLACKSTONE_BRICK_STAIRS: 
            case CRACKED_POLISHED_BLACKSTONE_BRICKS: 
            case RESPAWN_ANCHOR: 
            case SMALL_AMETHYST_BUD: 
            case MEDIUM_AMETHYST_BUD: 
            case LARGE_AMETHYST_BUD: 
            case AMETHYST_CLUSTER: 
            case POINTED_DRIPSTONE: 
            case OCHRE_FROGLIGHT: 
            case VERDANT_FROGLIGHT: 
            case PEARLESCENT_FROGLIGHT: 
            case PISTON_HEAD: 
            case MOVING_PISTON: 
            case OAK_WALL_SIGN: 
            case SPRUCE_WALL_SIGN: 
            case BIRCH_WALL_SIGN: 
            case ACACIA_WALL_SIGN: 
            case CHERRY_WALL_SIGN: 
            case JUNGLE_WALL_SIGN: 
            case DARK_OAK_WALL_SIGN: 
            case MANGROVE_WALL_SIGN: 
            case BAMBOO_WALL_SIGN: 
            case OAK_WALL_HANGING_SIGN: 
            case SPRUCE_WALL_HANGING_SIGN: 
            case BIRCH_WALL_HANGING_SIGN: 
            case ACACIA_WALL_HANGING_SIGN: 
            case CHERRY_WALL_HANGING_SIGN: 
            case JUNGLE_WALL_HANGING_SIGN: 
            case DARK_OAK_WALL_HANGING_SIGN: 
            case MANGROVE_WALL_HANGING_SIGN: 
            case CRIMSON_WALL_HANGING_SIGN: 
            case WARPED_WALL_HANGING_SIGN: 
            case BAMBOO_WALL_HANGING_SIGN: 
            case WATER_CAULDRON: 
            case LAVA_CAULDRON: 
            case POWDER_SNOW_CAULDRON: 
            case WHITE_WALL_BANNER: 
            case ORANGE_WALL_BANNER: 
            case MAGENTA_WALL_BANNER: 
            case LIGHT_BLUE_WALL_BANNER: 
            case YELLOW_WALL_BANNER: 
            case LIME_WALL_BANNER: 
            case PINK_WALL_BANNER: 
            case GRAY_WALL_BANNER: 
            case LIGHT_GRAY_WALL_BANNER: 
            case CYAN_WALL_BANNER: 
            case PURPLE_WALL_BANNER: 
            case BLUE_WALL_BANNER: 
            case BROWN_WALL_BANNER: 
            case GREEN_WALL_BANNER: 
            case RED_WALL_BANNER: 
            case BLACK_WALL_BANNER: 
            case FROSTED_ICE: 
            case DEAD_TUBE_CORAL_WALL_FAN: 
            case DEAD_BRAIN_CORAL_WALL_FAN: 
            case DEAD_BUBBLE_CORAL_WALL_FAN: 
            case DEAD_FIRE_CORAL_WALL_FAN: 
            case DEAD_HORN_CORAL_WALL_FAN: 
            case CRIMSON_WALL_SIGN: 
            case WARPED_WALL_SIGN: 
            case CANDLE_CAKE: 
            case WHITE_CANDLE_CAKE: 
            case ORANGE_CANDLE_CAKE: 
            case MAGENTA_CANDLE_CAKE: 
            case LIGHT_BLUE_CANDLE_CAKE: 
            case YELLOW_CANDLE_CAKE: 
            case LIME_CANDLE_CAKE: 
            case PINK_CANDLE_CAKE: 
            case GRAY_CANDLE_CAKE: 
            case LIGHT_GRAY_CANDLE_CAKE: 
            case CYAN_CANDLE_CAKE: 
            case PURPLE_CANDLE_CAKE: 
            case BLUE_CANDLE_CAKE: 
            case BROWN_CANDLE_CAKE: 
            case GREEN_CANDLE_CAKE: 
            case RED_CANDLE_CAKE: 
            case BLACK_CANDLE_CAKE: 
            case LEGACY_STONE: 
            case LEGACY_GRASS: 
            case LEGACY_DIRT: 
            case LEGACY_COBBLESTONE: 
            case LEGACY_WOOD: 
            case LEGACY_BEDROCK: 
            case LEGACY_SAND: 
            case LEGACY_GRAVEL: 
            case LEGACY_GOLD_ORE: 
            case LEGACY_IRON_ORE: 
            case LEGACY_COAL_ORE: 
            case LEGACY_LOG: 
            case LEGACY_LEAVES: 
            case LEGACY_SPONGE: 
            case LEGACY_GLASS: 
            case LEGACY_LAPIS_ORE: 
            case LEGACY_LAPIS_BLOCK: 
            case LEGACY_DISPENSER: 
            case LEGACY_SANDSTONE: 
            case LEGACY_NOTE_BLOCK: 
            case LEGACY_BED_BLOCK: 
            case LEGACY_PISTON_STICKY_BASE: 
            case LEGACY_PISTON_BASE: 
            case LEGACY_PISTON_EXTENSION: 
            case LEGACY_WOOL: 
            case LEGACY_PISTON_MOVING_PIECE: 
            case LEGACY_GOLD_BLOCK: 
            case LEGACY_IRON_BLOCK: 
            case LEGACY_DOUBLE_STEP: 
            case LEGACY_STEP: 
            case LEGACY_BRICK: 
            case LEGACY_TNT: 
            case LEGACY_BOOKSHELF: 
            case LEGACY_MOSSY_COBBLESTONE: 
            case LEGACY_OBSIDIAN: 
            case LEGACY_MOB_SPAWNER: 
            case LEGACY_WOOD_STAIRS: 
            case LEGACY_CHEST: 
            case LEGACY_DIAMOND_ORE: 
            case LEGACY_DIAMOND_BLOCK: 
            case LEGACY_WORKBENCH: 
            case LEGACY_SOIL: 
            case LEGACY_FURNACE: 
            case LEGACY_BURNING_FURNACE: 
            case LEGACY_SIGN_POST: 
            case LEGACY_WOODEN_DOOR: 
            case LEGACY_COBBLESTONE_STAIRS: 
            case LEGACY_WALL_SIGN: 
            case LEGACY_STONE_PLATE: 
            case LEGACY_IRON_DOOR_BLOCK: 
            case LEGACY_WOOD_PLATE: 
            case LEGACY_REDSTONE_ORE: 
            case LEGACY_GLOWING_REDSTONE_ORE: 
            case LEGACY_ICE: 
            case LEGACY_SNOW_BLOCK: 
            case LEGACY_CACTUS: 
            case LEGACY_CLAY: 
            case LEGACY_JUKEBOX: 
            case LEGACY_FENCE: 
            case LEGACY_PUMPKIN: 
            case LEGACY_NETHERRACK: 
            case LEGACY_SOUL_SAND: 
            case LEGACY_GLOWSTONE: 
            case LEGACY_JACK_O_LANTERN: 
            case LEGACY_CAKE_BLOCK: 
            case LEGACY_STAINED_GLASS: 
            case LEGACY_TRAP_DOOR: 
            case LEGACY_MONSTER_EGGS: 
            case LEGACY_SMOOTH_BRICK: 
            case LEGACY_HUGE_MUSHROOM_1: 
            case LEGACY_HUGE_MUSHROOM_2: 
            case LEGACY_IRON_FENCE: 
            case LEGACY_THIN_GLASS: 
            case LEGACY_MELON_BLOCK: 
            case LEGACY_FENCE_GATE: 
            case LEGACY_BRICK_STAIRS: 
            case LEGACY_SMOOTH_STAIRS: 
            case LEGACY_MYCEL: 
            case LEGACY_NETHER_BRICK: 
            case LEGACY_NETHER_FENCE: 
            case LEGACY_NETHER_BRICK_STAIRS: 
            case LEGACY_ENCHANTMENT_TABLE: 
            case LEGACY_BREWING_STAND: 
            case LEGACY_CAULDRON: 
            case LEGACY_ENDER_PORTAL_FRAME: 
            case LEGACY_ENDER_STONE: 
            case LEGACY_DRAGON_EGG: 
            case LEGACY_REDSTONE_LAMP_OFF: 
            case LEGACY_REDSTONE_LAMP_ON: 
            case LEGACY_WOOD_DOUBLE_STEP: 
            case LEGACY_WOOD_STEP: 
            case LEGACY_SANDSTONE_STAIRS: 
            case LEGACY_EMERALD_ORE: 
            case LEGACY_ENDER_CHEST: 
            case LEGACY_EMERALD_BLOCK: 
            case LEGACY_SPRUCE_WOOD_STAIRS: 
            case LEGACY_BIRCH_WOOD_STAIRS: 
            case LEGACY_JUNGLE_WOOD_STAIRS: 
            case LEGACY_COMMAND: 
            case LEGACY_BEACON: 
            case LEGACY_COBBLE_WALL: 
            case LEGACY_ANVIL: 
            case LEGACY_TRAPPED_CHEST: 
            case LEGACY_GOLD_PLATE: 
            case LEGACY_IRON_PLATE: 
            case LEGACY_DAYLIGHT_DETECTOR: 
            case LEGACY_REDSTONE_BLOCK: 
            case LEGACY_QUARTZ_ORE: 
            case LEGACY_HOPPER: 
            case LEGACY_QUARTZ_BLOCK: 
            case LEGACY_QUARTZ_STAIRS: 
            case LEGACY_DROPPER: 
            case LEGACY_STAINED_CLAY: 
            case LEGACY_STAINED_GLASS_PANE: 
            case LEGACY_LEAVES_2: 
            case LEGACY_LOG_2: 
            case LEGACY_ACACIA_STAIRS: 
            case LEGACY_DARK_OAK_STAIRS: 
            case LEGACY_SLIME_BLOCK: 
            case LEGACY_BARRIER: 
            case LEGACY_IRON_TRAPDOOR: 
            case LEGACY_PRISMARINE: 
            case LEGACY_SEA_LANTERN: 
            case LEGACY_HAY_BLOCK: 
            case LEGACY_HARD_CLAY: 
            case LEGACY_COAL_BLOCK: 
            case LEGACY_PACKED_ICE: 
            case LEGACY_STANDING_BANNER: 
            case LEGACY_WALL_BANNER: 
            case LEGACY_DAYLIGHT_DETECTOR_INVERTED: 
            case LEGACY_RED_SANDSTONE: 
            case LEGACY_RED_SANDSTONE_STAIRS: 
            case LEGACY_DOUBLE_STONE_SLAB2: 
            case LEGACY_STONE_SLAB2: 
            case LEGACY_SPRUCE_FENCE_GATE: 
            case LEGACY_BIRCH_FENCE_GATE: 
            case LEGACY_JUNGLE_FENCE_GATE: 
            case LEGACY_DARK_OAK_FENCE_GATE: 
            case LEGACY_ACACIA_FENCE_GATE: 
            case LEGACY_SPRUCE_FENCE: 
            case LEGACY_BIRCH_FENCE: 
            case LEGACY_JUNGLE_FENCE: 
            case LEGACY_DARK_OAK_FENCE: 
            case LEGACY_ACACIA_FENCE: 
            case LEGACY_SPRUCE_DOOR: 
            case LEGACY_BIRCH_DOOR: 
            case LEGACY_JUNGLE_DOOR: 
            case LEGACY_ACACIA_DOOR: 
            case LEGACY_DARK_OAK_DOOR: 
            case LEGACY_PURPUR_BLOCK: 
            case LEGACY_PURPUR_PILLAR: 
            case LEGACY_PURPUR_STAIRS: 
            case LEGACY_PURPUR_DOUBLE_SLAB: 
            case LEGACY_PURPUR_SLAB: 
            case LEGACY_END_BRICKS: 
            case LEGACY_GRASS_PATH: 
            case LEGACY_COMMAND_REPEATING: 
            case LEGACY_COMMAND_CHAIN: 
            case LEGACY_FROSTED_ICE: 
            case LEGACY_MAGMA: 
            case LEGACY_NETHER_WART_BLOCK: 
            case LEGACY_RED_NETHER_BRICK: 
            case LEGACY_BONE_BLOCK: 
            case LEGACY_OBSERVER: 
            case LEGACY_WHITE_SHULKER_BOX: 
            case LEGACY_ORANGE_SHULKER_BOX: 
            case LEGACY_MAGENTA_SHULKER_BOX: 
            case LEGACY_LIGHT_BLUE_SHULKER_BOX: 
            case LEGACY_YELLOW_SHULKER_BOX: 
            case LEGACY_LIME_SHULKER_BOX: 
            case LEGACY_PINK_SHULKER_BOX: 
            case LEGACY_GRAY_SHULKER_BOX: 
            case LEGACY_SILVER_SHULKER_BOX: 
            case LEGACY_CYAN_SHULKER_BOX: 
            case LEGACY_PURPLE_SHULKER_BOX: 
            case LEGACY_BLUE_SHULKER_BOX: 
            case LEGACY_BROWN_SHULKER_BOX: 
            case LEGACY_GREEN_SHULKER_BOX: 
            case LEGACY_RED_SHULKER_BOX: 
            case LEGACY_BLACK_SHULKER_BOX: 
            case LEGACY_WHITE_GLAZED_TERRACOTTA: 
            case LEGACY_ORANGE_GLAZED_TERRACOTTA: 
            case LEGACY_MAGENTA_GLAZED_TERRACOTTA: 
            case LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA: 
            case LEGACY_YELLOW_GLAZED_TERRACOTTA: 
            case LEGACY_LIME_GLAZED_TERRACOTTA: 
            case LEGACY_PINK_GLAZED_TERRACOTTA: 
            case LEGACY_GRAY_GLAZED_TERRACOTTA: 
            case LEGACY_SILVER_GLAZED_TERRACOTTA: 
            case LEGACY_CYAN_GLAZED_TERRACOTTA: 
            case LEGACY_PURPLE_GLAZED_TERRACOTTA: 
            case LEGACY_BLUE_GLAZED_TERRACOTTA: 
            case LEGACY_BROWN_GLAZED_TERRACOTTA: 
            case LEGACY_GREEN_GLAZED_TERRACOTTA: 
            case LEGACY_RED_GLAZED_TERRACOTTA: 
            case LEGACY_BLACK_GLAZED_TERRACOTTA: 
            case LEGACY_CONCRETE: 
            case LEGACY_CONCRETE_POWDER: 
            case LEGACY_STRUCTURE_BLOCK: {
                return true;
            }
        }
        return false;
    }

    public boolean isAir() {
        switch (this) {
            case AIR: 
            case VOID_AIR: 
            case CAVE_AIR: 
            case LEGACY_AIR: {
                return true;
            }
        }
        return false;
    }

    @Deprecated
    public boolean isTransparent() {
        if (!this.isBlock()) {
            return false;
        }
        switch (this) {
            case AIR: 
            case OAK_SAPLING: 
            case SPRUCE_SAPLING: 
            case BIRCH_SAPLING: 
            case JUNGLE_SAPLING: 
            case ACACIA_SAPLING: 
            case DARK_OAK_SAPLING: 
            case GRASS: 
            case FERN: 
            case DEAD_BUSH: 
            case DANDELION: 
            case POPPY: 
            case BLUE_ORCHID: 
            case ALLIUM: 
            case AZURE_BLUET: 
            case RED_TULIP: 
            case ORANGE_TULIP: 
            case WHITE_TULIP: 
            case PINK_TULIP: 
            case OXEYE_DAISY: 
            case BROWN_MUSHROOM: 
            case RED_MUSHROOM: 
            case SUGAR_CANE: 
            case TORCH: 
            case END_ROD: 
            case CHORUS_PLANT: 
            case CHORUS_FLOWER: 
            case LADDER: 
            case SNOW: 
            case VINE: 
            case LILY_PAD: 
            case BARRIER: 
            case WHITE_CARPET: 
            case ORANGE_CARPET: 
            case MAGENTA_CARPET: 
            case LIGHT_BLUE_CARPET: 
            case YELLOW_CARPET: 
            case LIME_CARPET: 
            case PINK_CARPET: 
            case GRAY_CARPET: 
            case LIGHT_GRAY_CARPET: 
            case CYAN_CARPET: 
            case PURPLE_CARPET: 
            case BLUE_CARPET: 
            case BROWN_CARPET: 
            case GREEN_CARPET: 
            case RED_CARPET: 
            case BLACK_CARPET: 
            case SUNFLOWER: 
            case LILAC: 
            case ROSE_BUSH: 
            case PEONY: 
            case TALL_GRASS: 
            case LARGE_FERN: 
            case STRUCTURE_VOID: 
            case REDSTONE_TORCH: 
            case REPEATER: 
            case COMPARATOR: 
            case LEVER: 
            case TRIPWIRE_HOOK: 
            case STONE_BUTTON: 
            case OAK_BUTTON: 
            case SPRUCE_BUTTON: 
            case BIRCH_BUTTON: 
            case JUNGLE_BUTTON: 
            case ACACIA_BUTTON: 
            case DARK_OAK_BUTTON: 
            case POWERED_RAIL: 
            case DETECTOR_RAIL: 
            case RAIL: 
            case ACTIVATOR_RAIL: 
            case WHEAT: 
            case NETHER_WART: 
            case FLOWER_POT: 
            case SKELETON_SKULL: 
            case WITHER_SKELETON_SKULL: 
            case PLAYER_HEAD: 
            case ZOMBIE_HEAD: 
            case CREEPER_HEAD: 
            case DRAGON_HEAD: 
            case WALL_TORCH: 
            case FIRE: 
            case REDSTONE_WIRE: 
            case REDSTONE_WALL_TORCH: 
            case NETHER_PORTAL: 
            case ATTACHED_PUMPKIN_STEM: 
            case ATTACHED_MELON_STEM: 
            case PUMPKIN_STEM: 
            case MELON_STEM: 
            case END_PORTAL: 
            case COCOA: 
            case TRIPWIRE: 
            case POTTED_OAK_SAPLING: 
            case POTTED_SPRUCE_SAPLING: 
            case POTTED_BIRCH_SAPLING: 
            case POTTED_JUNGLE_SAPLING: 
            case POTTED_ACACIA_SAPLING: 
            case POTTED_DARK_OAK_SAPLING: 
            case POTTED_FERN: 
            case POTTED_DANDELION: 
            case POTTED_POPPY: 
            case POTTED_BLUE_ORCHID: 
            case POTTED_ALLIUM: 
            case POTTED_AZURE_BLUET: 
            case POTTED_RED_TULIP: 
            case POTTED_ORANGE_TULIP: 
            case POTTED_WHITE_TULIP: 
            case POTTED_PINK_TULIP: 
            case POTTED_OXEYE_DAISY: 
            case POTTED_RED_MUSHROOM: 
            case POTTED_BROWN_MUSHROOM: 
            case POTTED_DEAD_BUSH: 
            case POTTED_CACTUS: 
            case CARROTS: 
            case POTATOES: 
            case SKELETON_WALL_SKULL: 
            case WITHER_SKELETON_WALL_SKULL: 
            case ZOMBIE_WALL_HEAD: 
            case PLAYER_WALL_HEAD: 
            case CREEPER_WALL_HEAD: 
            case DRAGON_WALL_HEAD: 
            case BEETROOTS: 
            case END_GATEWAY: 
            case VOID_AIR: 
            case CAVE_AIR: 
            case POTTED_AZALEA_BUSH: 
            case POTTED_FLOWERING_AZALEA_BUSH: 
            case LEGACY_AIR: 
            case LEGACY_SAPLING: 
            case LEGACY_POWERED_RAIL: 
            case LEGACY_DETECTOR_RAIL: 
            case LEGACY_LONG_GRASS: 
            case LEGACY_DEAD_BUSH: 
            case LEGACY_YELLOW_FLOWER: 
            case LEGACY_RED_ROSE: 
            case LEGACY_BROWN_MUSHROOM: 
            case LEGACY_RED_MUSHROOM: 
            case LEGACY_TORCH: 
            case LEGACY_FIRE: 
            case LEGACY_REDSTONE_WIRE: 
            case LEGACY_CROPS: 
            case LEGACY_LADDER: 
            case LEGACY_RAILS: 
            case LEGACY_LEVER: 
            case LEGACY_REDSTONE_TORCH_OFF: 
            case LEGACY_REDSTONE_TORCH_ON: 
            case LEGACY_STONE_BUTTON: 
            case LEGACY_SNOW: 
            case LEGACY_SUGAR_CANE_BLOCK: 
            case LEGACY_PORTAL: 
            case LEGACY_DIODE_BLOCK_OFF: 
            case LEGACY_DIODE_BLOCK_ON: 
            case LEGACY_PUMPKIN_STEM: 
            case LEGACY_MELON_STEM: 
            case LEGACY_VINE: 
            case LEGACY_WATER_LILY: 
            case LEGACY_NETHER_WARTS: 
            case LEGACY_ENDER_PORTAL: 
            case LEGACY_COCOA: 
            case LEGACY_TRIPWIRE_HOOK: 
            case LEGACY_TRIPWIRE: 
            case LEGACY_FLOWER_POT: 
            case LEGACY_CARROT: 
            case LEGACY_POTATO: 
            case LEGACY_WOOD_BUTTON: 
            case LEGACY_SKULL: 
            case LEGACY_REDSTONE_COMPARATOR_OFF: 
            case LEGACY_REDSTONE_COMPARATOR_ON: 
            case LEGACY_ACTIVATOR_RAIL: 
            case LEGACY_CARPET: 
            case LEGACY_DOUBLE_PLANT: 
            case LEGACY_END_ROD: 
            case LEGACY_CHORUS_PLANT: 
            case LEGACY_CHORUS_FLOWER: 
            case LEGACY_BEETROOT_BLOCK: 
            case LEGACY_END_GATEWAY: 
            case LEGACY_STRUCTURE_VOID: {
                return true;
            }
        }
        return false;
    }

    public boolean isFlammable() {
        if (!this.isBlock()) {
            return false;
        }
        switch (this) {
            case OAK_PLANKS: 
            case SPRUCE_PLANKS: 
            case BIRCH_PLANKS: 
            case JUNGLE_PLANKS: 
            case ACACIA_PLANKS: 
            case CHERRY_PLANKS: 
            case DARK_OAK_PLANKS: 
            case MANGROVE_PLANKS: 
            case BAMBOO_PLANKS: 
            case BAMBOO_MOSAIC: 
            case OAK_LOG: 
            case SPRUCE_LOG: 
            case BIRCH_LOG: 
            case JUNGLE_LOG: 
            case ACACIA_LOG: 
            case CHERRY_LOG: 
            case DARK_OAK_LOG: 
            case MANGROVE_LOG: 
            case MANGROVE_ROOTS: 
            case BAMBOO_BLOCK: 
            case STRIPPED_OAK_LOG: 
            case STRIPPED_SPRUCE_LOG: 
            case STRIPPED_BIRCH_LOG: 
            case STRIPPED_JUNGLE_LOG: 
            case STRIPPED_ACACIA_LOG: 
            case STRIPPED_CHERRY_LOG: 
            case STRIPPED_DARK_OAK_LOG: 
            case STRIPPED_MANGROVE_LOG: 
            case STRIPPED_OAK_WOOD: 
            case STRIPPED_SPRUCE_WOOD: 
            case STRIPPED_BIRCH_WOOD: 
            case STRIPPED_JUNGLE_WOOD: 
            case STRIPPED_ACACIA_WOOD: 
            case STRIPPED_CHERRY_WOOD: 
            case STRIPPED_DARK_OAK_WOOD: 
            case STRIPPED_MANGROVE_WOOD: 
            case STRIPPED_BAMBOO_BLOCK: 
            case OAK_WOOD: 
            case SPRUCE_WOOD: 
            case BIRCH_WOOD: 
            case JUNGLE_WOOD: 
            case ACACIA_WOOD: 
            case CHERRY_WOOD: 
            case DARK_OAK_WOOD: 
            case MANGROVE_WOOD: 
            case OAK_LEAVES: 
            case SPRUCE_LEAVES: 
            case BIRCH_LEAVES: 
            case JUNGLE_LEAVES: 
            case ACACIA_LEAVES: 
            case CHERRY_LEAVES: 
            case DARK_OAK_LEAVES: 
            case MANGROVE_LEAVES: 
            case AZALEA_LEAVES: 
            case FLOWERING_AZALEA_LEAVES: 
            case GRASS: 
            case FERN: 
            case DEAD_BUSH: 
            case WHITE_WOOL: 
            case ORANGE_WOOL: 
            case MAGENTA_WOOL: 
            case LIGHT_BLUE_WOOL: 
            case YELLOW_WOOL: 
            case LIME_WOOL: 
            case PINK_WOOL: 
            case GRAY_WOOL: 
            case LIGHT_GRAY_WOOL: 
            case CYAN_WOOL: 
            case PURPLE_WOOL: 
            case BLUE_WOOL: 
            case BROWN_WOOL: 
            case GREEN_WOOL: 
            case RED_WOOL: 
            case BLACK_WOOL: 
            case PITCHER_PLANT: 
            case HANGING_ROOTS: 
            case BAMBOO: 
            case OAK_SLAB: 
            case SPRUCE_SLAB: 
            case BIRCH_SLAB: 
            case JUNGLE_SLAB: 
            case ACACIA_SLAB: 
            case CHERRY_SLAB: 
            case DARK_OAK_SLAB: 
            case MANGROVE_SLAB: 
            case BAMBOO_SLAB: 
            case BAMBOO_MOSAIC_SLAB: 
            case BOOKSHELF: 
            case CHISELED_BOOKSHELF: 
            case CHEST: 
            case CRAFTING_TABLE: 
            case JUKEBOX: 
            case OAK_FENCE: 
            case SPRUCE_FENCE: 
            case BIRCH_FENCE: 
            case JUNGLE_FENCE: 
            case ACACIA_FENCE: 
            case CHERRY_FENCE: 
            case DARK_OAK_FENCE: 
            case MANGROVE_FENCE: 
            case BAMBOO_FENCE: 
            case BROWN_MUSHROOM_BLOCK: 
            case RED_MUSHROOM_BLOCK: 
            case MUSHROOM_STEM: 
            case VINE: 
            case GLOW_LICHEN: 
            case OAK_STAIRS: 
            case SPRUCE_STAIRS: 
            case BIRCH_STAIRS: 
            case JUNGLE_STAIRS: 
            case ACACIA_STAIRS: 
            case CHERRY_STAIRS: 
            case DARK_OAK_STAIRS: 
            case MANGROVE_STAIRS: 
            case BAMBOO_STAIRS: 
            case BAMBOO_MOSAIC_STAIRS: 
            case WHITE_CARPET: 
            case ORANGE_CARPET: 
            case MAGENTA_CARPET: 
            case LIGHT_BLUE_CARPET: 
            case YELLOW_CARPET: 
            case LIME_CARPET: 
            case PINK_CARPET: 
            case GRAY_CARPET: 
            case LIGHT_GRAY_CARPET: 
            case CYAN_CARPET: 
            case PURPLE_CARPET: 
            case BLUE_CARPET: 
            case BROWN_CARPET: 
            case GREEN_CARPET: 
            case RED_CARPET: 
            case BLACK_CARPET: 
            case SUNFLOWER: 
            case LILAC: 
            case ROSE_BUSH: 
            case PEONY: 
            case TALL_GRASS: 
            case LARGE_FERN: 
            case LECTERN: 
            case DAYLIGHT_DETECTOR: 
            case TRAPPED_CHEST: 
            case TNT: 
            case NOTE_BLOCK: 
            case OAK_PRESSURE_PLATE: 
            case SPRUCE_PRESSURE_PLATE: 
            case BIRCH_PRESSURE_PLATE: 
            case JUNGLE_PRESSURE_PLATE: 
            case ACACIA_PRESSURE_PLATE: 
            case CHERRY_PRESSURE_PLATE: 
            case DARK_OAK_PRESSURE_PLATE: 
            case MANGROVE_PRESSURE_PLATE: 
            case BAMBOO_PRESSURE_PLATE: 
            case OAK_DOOR: 
            case SPRUCE_DOOR: 
            case BIRCH_DOOR: 
            case JUNGLE_DOOR: 
            case ACACIA_DOOR: 
            case CHERRY_DOOR: 
            case DARK_OAK_DOOR: 
            case MANGROVE_DOOR: 
            case BAMBOO_DOOR: 
            case OAK_TRAPDOOR: 
            case SPRUCE_TRAPDOOR: 
            case BIRCH_TRAPDOOR: 
            case JUNGLE_TRAPDOOR: 
            case ACACIA_TRAPDOOR: 
            case CHERRY_TRAPDOOR: 
            case DARK_OAK_TRAPDOOR: 
            case MANGROVE_TRAPDOOR: 
            case BAMBOO_TRAPDOOR: 
            case OAK_FENCE_GATE: 
            case SPRUCE_FENCE_GATE: 
            case BIRCH_FENCE_GATE: 
            case JUNGLE_FENCE_GATE: 
            case ACACIA_FENCE_GATE: 
            case CHERRY_FENCE_GATE: 
            case DARK_OAK_FENCE_GATE: 
            case MANGROVE_FENCE_GATE: 
            case BAMBOO_FENCE_GATE: 
            case OAK_SIGN: 
            case SPRUCE_SIGN: 
            case BIRCH_SIGN: 
            case JUNGLE_SIGN: 
            case ACACIA_SIGN: 
            case CHERRY_SIGN: 
            case DARK_OAK_SIGN: 
            case MANGROVE_SIGN: 
            case BAMBOO_SIGN: 
            case OAK_HANGING_SIGN: 
            case SPRUCE_HANGING_SIGN: 
            case BIRCH_HANGING_SIGN: 
            case JUNGLE_HANGING_SIGN: 
            case ACACIA_HANGING_SIGN: 
            case CHERRY_HANGING_SIGN: 
            case DARK_OAK_HANGING_SIGN: 
            case MANGROVE_HANGING_SIGN: 
            case BAMBOO_HANGING_SIGN: 
            case WHITE_BED: 
            case ORANGE_BED: 
            case MAGENTA_BED: 
            case LIGHT_BLUE_BED: 
            case YELLOW_BED: 
            case LIME_BED: 
            case PINK_BED: 
            case GRAY_BED: 
            case LIGHT_GRAY_BED: 
            case CYAN_BED: 
            case PURPLE_BED: 
            case BLUE_BED: 
            case BROWN_BED: 
            case GREEN_BED: 
            case RED_BED: 
            case BLACK_BED: 
            case WHITE_BANNER: 
            case ORANGE_BANNER: 
            case MAGENTA_BANNER: 
            case LIGHT_BLUE_BANNER: 
            case YELLOW_BANNER: 
            case LIME_BANNER: 
            case PINK_BANNER: 
            case GRAY_BANNER: 
            case LIGHT_GRAY_BANNER: 
            case CYAN_BANNER: 
            case PURPLE_BANNER: 
            case BLUE_BANNER: 
            case BROWN_BANNER: 
            case GREEN_BANNER: 
            case RED_BANNER: 
            case BLACK_BANNER: 
            case LOOM: 
            case COMPOSTER: 
            case BARREL: 
            case CARTOGRAPHY_TABLE: 
            case FLETCHING_TABLE: 
            case SMITHING_TABLE: 
            case CAMPFIRE: 
            case SOUL_CAMPFIRE: 
            case BEE_NEST: 
            case BEEHIVE: 
            case OAK_WALL_SIGN: 
            case SPRUCE_WALL_SIGN: 
            case BIRCH_WALL_SIGN: 
            case ACACIA_WALL_SIGN: 
            case CHERRY_WALL_SIGN: 
            case JUNGLE_WALL_SIGN: 
            case DARK_OAK_WALL_SIGN: 
            case MANGROVE_WALL_SIGN: 
            case BAMBOO_WALL_SIGN: 
            case OAK_WALL_HANGING_SIGN: 
            case SPRUCE_WALL_HANGING_SIGN: 
            case BIRCH_WALL_HANGING_SIGN: 
            case ACACIA_WALL_HANGING_SIGN: 
            case CHERRY_WALL_HANGING_SIGN: 
            case JUNGLE_WALL_HANGING_SIGN: 
            case DARK_OAK_WALL_HANGING_SIGN: 
            case MANGROVE_WALL_HANGING_SIGN: 
            case BAMBOO_WALL_HANGING_SIGN: 
            case WHITE_WALL_BANNER: 
            case ORANGE_WALL_BANNER: 
            case MAGENTA_WALL_BANNER: 
            case LIGHT_BLUE_WALL_BANNER: 
            case YELLOW_WALL_BANNER: 
            case LIME_WALL_BANNER: 
            case PINK_WALL_BANNER: 
            case GRAY_WALL_BANNER: 
            case LIGHT_GRAY_WALL_BANNER: 
            case CYAN_WALL_BANNER: 
            case PURPLE_WALL_BANNER: 
            case BLUE_WALL_BANNER: 
            case BROWN_WALL_BANNER: 
            case GREEN_WALL_BANNER: 
            case RED_WALL_BANNER: 
            case BLACK_WALL_BANNER: 
            case BAMBOO_SAPLING: 
            case LEGACY_WOOD: 
            case LEGACY_LOG: 
            case LEGACY_LEAVES: 
            case LEGACY_NOTE_BLOCK: 
            case LEGACY_BED_BLOCK: 
            case LEGACY_LONG_GRASS: 
            case LEGACY_DEAD_BUSH: 
            case LEGACY_WOOL: 
            case LEGACY_TNT: 
            case LEGACY_BOOKSHELF: 
            case LEGACY_WOOD_STAIRS: 
            case LEGACY_CHEST: 
            case LEGACY_WORKBENCH: 
            case LEGACY_SIGN_POST: 
            case LEGACY_WOODEN_DOOR: 
            case LEGACY_WALL_SIGN: 
            case LEGACY_WOOD_PLATE: 
            case LEGACY_JUKEBOX: 
            case LEGACY_FENCE: 
            case LEGACY_TRAP_DOOR: 
            case LEGACY_HUGE_MUSHROOM_1: 
            case LEGACY_HUGE_MUSHROOM_2: 
            case LEGACY_VINE: 
            case LEGACY_FENCE_GATE: 
            case LEGACY_WOOD_DOUBLE_STEP: 
            case LEGACY_WOOD_STEP: 
            case LEGACY_SPRUCE_WOOD_STAIRS: 
            case LEGACY_BIRCH_WOOD_STAIRS: 
            case LEGACY_JUNGLE_WOOD_STAIRS: 
            case LEGACY_TRAPPED_CHEST: 
            case LEGACY_DAYLIGHT_DETECTOR: 
            case LEGACY_LEAVES_2: 
            case LEGACY_LOG_2: 
            case LEGACY_ACACIA_STAIRS: 
            case LEGACY_DARK_OAK_STAIRS: 
            case LEGACY_CARPET: 
            case LEGACY_DOUBLE_PLANT: 
            case LEGACY_STANDING_BANNER: 
            case LEGACY_WALL_BANNER: 
            case LEGACY_DAYLIGHT_DETECTOR_INVERTED: 
            case LEGACY_SPRUCE_FENCE_GATE: 
            case LEGACY_BIRCH_FENCE_GATE: 
            case LEGACY_JUNGLE_FENCE_GATE: 
            case LEGACY_DARK_OAK_FENCE_GATE: 
            case LEGACY_ACACIA_FENCE_GATE: 
            case LEGACY_SPRUCE_FENCE: 
            case LEGACY_BIRCH_FENCE: 
            case LEGACY_JUNGLE_FENCE: 
            case LEGACY_DARK_OAK_FENCE: 
            case LEGACY_ACACIA_FENCE: 
            case LEGACY_SPRUCE_DOOR: 
            case LEGACY_BIRCH_DOOR: 
            case LEGACY_JUNGLE_DOOR: 
            case LEGACY_ACACIA_DOOR: 
            case LEGACY_DARK_OAK_DOOR: {
                return true;
            }
        }
        return false;
    }

    public boolean isBurnable() {
        if (!this.isBlock()) {
            return false;
        }
        switch (this) {
            case OAK_PLANKS: 
            case SPRUCE_PLANKS: 
            case BIRCH_PLANKS: 
            case JUNGLE_PLANKS: 
            case ACACIA_PLANKS: 
            case CHERRY_PLANKS: 
            case DARK_OAK_PLANKS: 
            case MANGROVE_PLANKS: 
            case BAMBOO_PLANKS: 
            case BAMBOO_MOSAIC: 
            case COAL_BLOCK: 
            case OAK_LOG: 
            case SPRUCE_LOG: 
            case BIRCH_LOG: 
            case JUNGLE_LOG: 
            case ACACIA_LOG: 
            case CHERRY_LOG: 
            case DARK_OAK_LOG: 
            case MANGROVE_LOG: 
            case MANGROVE_ROOTS: 
            case BAMBOO_BLOCK: 
            case STRIPPED_OAK_LOG: 
            case STRIPPED_SPRUCE_LOG: 
            case STRIPPED_BIRCH_LOG: 
            case STRIPPED_JUNGLE_LOG: 
            case STRIPPED_ACACIA_LOG: 
            case STRIPPED_CHERRY_LOG: 
            case STRIPPED_DARK_OAK_LOG: 
            case STRIPPED_MANGROVE_LOG: 
            case STRIPPED_OAK_WOOD: 
            case STRIPPED_SPRUCE_WOOD: 
            case STRIPPED_BIRCH_WOOD: 
            case STRIPPED_JUNGLE_WOOD: 
            case STRIPPED_ACACIA_WOOD: 
            case STRIPPED_CHERRY_WOOD: 
            case STRIPPED_DARK_OAK_WOOD: 
            case STRIPPED_MANGROVE_WOOD: 
            case STRIPPED_BAMBOO_BLOCK: 
            case OAK_WOOD: 
            case SPRUCE_WOOD: 
            case BIRCH_WOOD: 
            case JUNGLE_WOOD: 
            case ACACIA_WOOD: 
            case CHERRY_WOOD: 
            case DARK_OAK_WOOD: 
            case MANGROVE_WOOD: 
            case OAK_LEAVES: 
            case SPRUCE_LEAVES: 
            case BIRCH_LEAVES: 
            case JUNGLE_LEAVES: 
            case ACACIA_LEAVES: 
            case CHERRY_LEAVES: 
            case DARK_OAK_LEAVES: 
            case MANGROVE_LEAVES: 
            case AZALEA_LEAVES: 
            case FLOWERING_AZALEA_LEAVES: 
            case GRASS: 
            case FERN: 
            case AZALEA: 
            case FLOWERING_AZALEA: 
            case DEAD_BUSH: 
            case WHITE_WOOL: 
            case ORANGE_WOOL: 
            case MAGENTA_WOOL: 
            case LIGHT_BLUE_WOOL: 
            case YELLOW_WOOL: 
            case LIME_WOOL: 
            case PINK_WOOL: 
            case GRAY_WOOL: 
            case LIGHT_GRAY_WOOL: 
            case CYAN_WOOL: 
            case PURPLE_WOOL: 
            case BLUE_WOOL: 
            case BROWN_WOOL: 
            case GREEN_WOOL: 
            case RED_WOOL: 
            case BLACK_WOOL: 
            case DANDELION: 
            case POPPY: 
            case BLUE_ORCHID: 
            case ALLIUM: 
            case AZURE_BLUET: 
            case RED_TULIP: 
            case ORANGE_TULIP: 
            case WHITE_TULIP: 
            case PINK_TULIP: 
            case OXEYE_DAISY: 
            case CORNFLOWER: 
            case LILY_OF_THE_VALLEY: 
            case WITHER_ROSE: 
            case TORCHFLOWER: 
            case PITCHER_PLANT: 
            case SPORE_BLOSSOM: 
            case PINK_PETALS: 
            case HANGING_ROOTS: 
            case BIG_DRIPLEAF: 
            case SMALL_DRIPLEAF: 
            case BAMBOO: 
            case OAK_SLAB: 
            case SPRUCE_SLAB: 
            case BIRCH_SLAB: 
            case JUNGLE_SLAB: 
            case ACACIA_SLAB: 
            case CHERRY_SLAB: 
            case DARK_OAK_SLAB: 
            case MANGROVE_SLAB: 
            case BAMBOO_SLAB: 
            case BAMBOO_MOSAIC_SLAB: 
            case BOOKSHELF: 
            case OAK_FENCE: 
            case SPRUCE_FENCE: 
            case BIRCH_FENCE: 
            case JUNGLE_FENCE: 
            case ACACIA_FENCE: 
            case CHERRY_FENCE: 
            case DARK_OAK_FENCE: 
            case MANGROVE_FENCE: 
            case BAMBOO_FENCE: 
            case VINE: 
            case GLOW_LICHEN: 
            case OAK_STAIRS: 
            case SPRUCE_STAIRS: 
            case BIRCH_STAIRS: 
            case JUNGLE_STAIRS: 
            case ACACIA_STAIRS: 
            case CHERRY_STAIRS: 
            case DARK_OAK_STAIRS: 
            case MANGROVE_STAIRS: 
            case BAMBOO_STAIRS: 
            case BAMBOO_MOSAIC_STAIRS: 
            case HAY_BLOCK: 
            case WHITE_CARPET: 
            case ORANGE_CARPET: 
            case MAGENTA_CARPET: 
            case LIGHT_BLUE_CARPET: 
            case YELLOW_CARPET: 
            case LIME_CARPET: 
            case PINK_CARPET: 
            case GRAY_CARPET: 
            case LIGHT_GRAY_CARPET: 
            case CYAN_CARPET: 
            case PURPLE_CARPET: 
            case BLUE_CARPET: 
            case BROWN_CARPET: 
            case GREEN_CARPET: 
            case RED_CARPET: 
            case BLACK_CARPET: 
            case SUNFLOWER: 
            case LILAC: 
            case ROSE_BUSH: 
            case PEONY: 
            case TALL_GRASS: 
            case LARGE_FERN: 
            case SCAFFOLDING: 
            case LECTERN: 
            case TARGET: 
            case TNT: 
            case OAK_FENCE_GATE: 
            case SPRUCE_FENCE_GATE: 
            case BIRCH_FENCE_GATE: 
            case JUNGLE_FENCE_GATE: 
            case ACACIA_FENCE_GATE: 
            case CHERRY_FENCE_GATE: 
            case DARK_OAK_FENCE_GATE: 
            case MANGROVE_FENCE_GATE: 
            case BAMBOO_FENCE_GATE: 
            case DRIED_KELP_BLOCK: 
            case COMPOSTER: 
            case BEE_NEST: 
            case BEEHIVE: 
            case SWEET_BERRY_BUSH: 
            case CAVE_VINES: 
            case CAVE_VINES_PLANT: 
            case BIG_DRIPLEAF_STEM: 
            case LEGACY_WOOD: 
            case LEGACY_LOG: 
            case LEGACY_LEAVES: 
            case LEGACY_LONG_GRASS: 
            case LEGACY_DEAD_BUSH: 
            case LEGACY_WOOL: 
            case LEGACY_YELLOW_FLOWER: 
            case LEGACY_RED_ROSE: 
            case LEGACY_TNT: 
            case LEGACY_BOOKSHELF: 
            case LEGACY_WOOD_STAIRS: 
            case LEGACY_FENCE: 
            case LEGACY_VINE: 
            case LEGACY_FENCE_GATE: 
            case LEGACY_WOOD_DOUBLE_STEP: 
            case LEGACY_WOOD_STEP: 
            case LEGACY_SPRUCE_WOOD_STAIRS: 
            case LEGACY_BIRCH_WOOD_STAIRS: 
            case LEGACY_JUNGLE_WOOD_STAIRS: 
            case LEGACY_LEAVES_2: 
            case LEGACY_LOG_2: 
            case LEGACY_ACACIA_STAIRS: 
            case LEGACY_DARK_OAK_STAIRS: 
            case LEGACY_HAY_BLOCK: 
            case LEGACY_CARPET: 
            case LEGACY_COAL_BLOCK: 
            case LEGACY_DOUBLE_PLANT: 
            case LEGACY_SPRUCE_FENCE_GATE: 
            case LEGACY_BIRCH_FENCE_GATE: 
            case LEGACY_JUNGLE_FENCE_GATE: 
            case LEGACY_DARK_OAK_FENCE_GATE: 
            case LEGACY_ACACIA_FENCE_GATE: 
            case LEGACY_SPRUCE_FENCE: 
            case LEGACY_BIRCH_FENCE: 
            case LEGACY_JUNGLE_FENCE: 
            case LEGACY_DARK_OAK_FENCE: 
            case LEGACY_ACACIA_FENCE: {
                return true;
            }
        }
        return false;
    }

    public boolean isFuel() {
        switch (this) {
            case OAK_PLANKS: 
            case SPRUCE_PLANKS: 
            case BIRCH_PLANKS: 
            case JUNGLE_PLANKS: 
            case ACACIA_PLANKS: 
            case CHERRY_PLANKS: 
            case DARK_OAK_PLANKS: 
            case MANGROVE_PLANKS: 
            case BAMBOO_PLANKS: 
            case BAMBOO_MOSAIC: 
            case OAK_SAPLING: 
            case SPRUCE_SAPLING: 
            case BIRCH_SAPLING: 
            case JUNGLE_SAPLING: 
            case ACACIA_SAPLING: 
            case CHERRY_SAPLING: 
            case DARK_OAK_SAPLING: 
            case MANGROVE_PROPAGULE: 
            case COAL_BLOCK: 
            case OAK_LOG: 
            case SPRUCE_LOG: 
            case BIRCH_LOG: 
            case JUNGLE_LOG: 
            case ACACIA_LOG: 
            case CHERRY_LOG: 
            case DARK_OAK_LOG: 
            case MANGROVE_LOG: 
            case MANGROVE_ROOTS: 
            case BAMBOO_BLOCK: 
            case STRIPPED_OAK_LOG: 
            case STRIPPED_SPRUCE_LOG: 
            case STRIPPED_BIRCH_LOG: 
            case STRIPPED_JUNGLE_LOG: 
            case STRIPPED_ACACIA_LOG: 
            case STRIPPED_CHERRY_LOG: 
            case STRIPPED_DARK_OAK_LOG: 
            case STRIPPED_MANGROVE_LOG: 
            case STRIPPED_OAK_WOOD: 
            case STRIPPED_SPRUCE_WOOD: 
            case STRIPPED_BIRCH_WOOD: 
            case STRIPPED_JUNGLE_WOOD: 
            case STRIPPED_ACACIA_WOOD: 
            case STRIPPED_CHERRY_WOOD: 
            case STRIPPED_DARK_OAK_WOOD: 
            case STRIPPED_MANGROVE_WOOD: 
            case STRIPPED_BAMBOO_BLOCK: 
            case OAK_WOOD: 
            case SPRUCE_WOOD: 
            case BIRCH_WOOD: 
            case JUNGLE_WOOD: 
            case ACACIA_WOOD: 
            case CHERRY_WOOD: 
            case DARK_OAK_WOOD: 
            case MANGROVE_WOOD: 
            case AZALEA: 
            case FLOWERING_AZALEA: 
            case DEAD_BUSH: 
            case WHITE_WOOL: 
            case ORANGE_WOOL: 
            case MAGENTA_WOOL: 
            case LIGHT_BLUE_WOOL: 
            case YELLOW_WOOL: 
            case LIME_WOOL: 
            case PINK_WOOL: 
            case GRAY_WOOL: 
            case LIGHT_GRAY_WOOL: 
            case CYAN_WOOL: 
            case PURPLE_WOOL: 
            case BLUE_WOOL: 
            case BROWN_WOOL: 
            case GREEN_WOOL: 
            case RED_WOOL: 
            case BLACK_WOOL: 
            case BAMBOO: 
            case OAK_SLAB: 
            case SPRUCE_SLAB: 
            case BIRCH_SLAB: 
            case JUNGLE_SLAB: 
            case ACACIA_SLAB: 
            case CHERRY_SLAB: 
            case DARK_OAK_SLAB: 
            case MANGROVE_SLAB: 
            case BAMBOO_SLAB: 
            case BAMBOO_MOSAIC_SLAB: 
            case BOOKSHELF: 
            case CHISELED_BOOKSHELF: 
            case CHEST: 
            case CRAFTING_TABLE: 
            case LADDER: 
            case JUKEBOX: 
            case OAK_FENCE: 
            case SPRUCE_FENCE: 
            case BIRCH_FENCE: 
            case JUNGLE_FENCE: 
            case ACACIA_FENCE: 
            case CHERRY_FENCE: 
            case DARK_OAK_FENCE: 
            case MANGROVE_FENCE: 
            case BAMBOO_FENCE: 
            case OAK_STAIRS: 
            case SPRUCE_STAIRS: 
            case BIRCH_STAIRS: 
            case JUNGLE_STAIRS: 
            case ACACIA_STAIRS: 
            case CHERRY_STAIRS: 
            case DARK_OAK_STAIRS: 
            case MANGROVE_STAIRS: 
            case BAMBOO_STAIRS: 
            case BAMBOO_MOSAIC_STAIRS: 
            case WHITE_CARPET: 
            case ORANGE_CARPET: 
            case MAGENTA_CARPET: 
            case LIGHT_BLUE_CARPET: 
            case YELLOW_CARPET: 
            case LIME_CARPET: 
            case PINK_CARPET: 
            case GRAY_CARPET: 
            case LIGHT_GRAY_CARPET: 
            case CYAN_CARPET: 
            case PURPLE_CARPET: 
            case BLUE_CARPET: 
            case BROWN_CARPET: 
            case GREEN_CARPET: 
            case RED_CARPET: 
            case BLACK_CARPET: 
            case SCAFFOLDING: 
            case LECTERN: 
            case DAYLIGHT_DETECTOR: 
            case TRAPPED_CHEST: 
            case NOTE_BLOCK: 
            case OAK_BUTTON: 
            case SPRUCE_BUTTON: 
            case BIRCH_BUTTON: 
            case JUNGLE_BUTTON: 
            case ACACIA_BUTTON: 
            case CHERRY_BUTTON: 
            case DARK_OAK_BUTTON: 
            case MANGROVE_BUTTON: 
            case BAMBOO_BUTTON: 
            case OAK_PRESSURE_PLATE: 
            case SPRUCE_PRESSURE_PLATE: 
            case BIRCH_PRESSURE_PLATE: 
            case JUNGLE_PRESSURE_PLATE: 
            case ACACIA_PRESSURE_PLATE: 
            case CHERRY_PRESSURE_PLATE: 
            case DARK_OAK_PRESSURE_PLATE: 
            case MANGROVE_PRESSURE_PLATE: 
            case BAMBOO_PRESSURE_PLATE: 
            case OAK_DOOR: 
            case SPRUCE_DOOR: 
            case BIRCH_DOOR: 
            case JUNGLE_DOOR: 
            case ACACIA_DOOR: 
            case CHERRY_DOOR: 
            case DARK_OAK_DOOR: 
            case MANGROVE_DOOR: 
            case BAMBOO_DOOR: 
            case OAK_TRAPDOOR: 
            case SPRUCE_TRAPDOOR: 
            case BIRCH_TRAPDOOR: 
            case JUNGLE_TRAPDOOR: 
            case ACACIA_TRAPDOOR: 
            case CHERRY_TRAPDOOR: 
            case DARK_OAK_TRAPDOOR: 
            case MANGROVE_TRAPDOOR: 
            case BAMBOO_TRAPDOOR: 
            case OAK_FENCE_GATE: 
            case SPRUCE_FENCE_GATE: 
            case BIRCH_FENCE_GATE: 
            case JUNGLE_FENCE_GATE: 
            case ACACIA_FENCE_GATE: 
            case CHERRY_FENCE_GATE: 
            case DARK_OAK_FENCE_GATE: 
            case MANGROVE_FENCE_GATE: 
            case BAMBOO_FENCE_GATE: 
            case OAK_BOAT: 
            case OAK_CHEST_BOAT: 
            case SPRUCE_BOAT: 
            case SPRUCE_CHEST_BOAT: 
            case BIRCH_BOAT: 
            case BIRCH_CHEST_BOAT: 
            case JUNGLE_BOAT: 
            case JUNGLE_CHEST_BOAT: 
            case ACACIA_BOAT: 
            case ACACIA_CHEST_BOAT: 
            case CHERRY_BOAT: 
            case CHERRY_CHEST_BOAT: 
            case DARK_OAK_BOAT: 
            case DARK_OAK_CHEST_BOAT: 
            case MANGROVE_BOAT: 
            case MANGROVE_CHEST_BOAT: 
            case BAMBOO_RAFT: 
            case BAMBOO_CHEST_RAFT: 
            case BOW: 
            case COAL: 
            case CHARCOAL: 
            case WOODEN_SWORD: 
            case WOODEN_SHOVEL: 
            case WOODEN_PICKAXE: 
            case WOODEN_AXE: 
            case WOODEN_HOE: 
            case STICK: 
            case BOWL: 
            case OAK_SIGN: 
            case SPRUCE_SIGN: 
            case BIRCH_SIGN: 
            case JUNGLE_SIGN: 
            case ACACIA_SIGN: 
            case CHERRY_SIGN: 
            case DARK_OAK_SIGN: 
            case MANGROVE_SIGN: 
            case BAMBOO_SIGN: 
            case OAK_HANGING_SIGN: 
            case SPRUCE_HANGING_SIGN: 
            case BIRCH_HANGING_SIGN: 
            case JUNGLE_HANGING_SIGN: 
            case ACACIA_HANGING_SIGN: 
            case CHERRY_HANGING_SIGN: 
            case DARK_OAK_HANGING_SIGN: 
            case MANGROVE_HANGING_SIGN: 
            case BAMBOO_HANGING_SIGN: 
            case LAVA_BUCKET: 
            case DRIED_KELP_BLOCK: 
            case FISHING_ROD: 
            case BLAZE_ROD: 
            case WHITE_BANNER: 
            case ORANGE_BANNER: 
            case MAGENTA_BANNER: 
            case LIGHT_BLUE_BANNER: 
            case YELLOW_BANNER: 
            case LIME_BANNER: 
            case PINK_BANNER: 
            case GRAY_BANNER: 
            case LIGHT_GRAY_BANNER: 
            case CYAN_BANNER: 
            case PURPLE_BANNER: 
            case BLUE_BANNER: 
            case BROWN_BANNER: 
            case GREEN_BANNER: 
            case RED_BANNER: 
            case BLACK_BANNER: 
            case CROSSBOW: 
            case LOOM: 
            case COMPOSTER: 
            case BARREL: 
            case CARTOGRAPHY_TABLE: 
            case FLETCHING_TABLE: 
            case SMITHING_TABLE: 
            case LEGACY_WOOD: 
            case LEGACY_SAPLING: 
            case LEGACY_LOG: 
            case LEGACY_NOTE_BLOCK: 
            case LEGACY_WOOL: 
            case LEGACY_BOOKSHELF: 
            case LEGACY_WOOD_STAIRS: 
            case LEGACY_CHEST: 
            case LEGACY_WORKBENCH: 
            case LEGACY_LADDER: 
            case LEGACY_WOOD_PLATE: 
            case LEGACY_JUKEBOX: 
            case LEGACY_FENCE: 
            case LEGACY_TRAP_DOOR: 
            case LEGACY_FENCE_GATE: 
            case LEGACY_WOOD_STEP: 
            case LEGACY_SPRUCE_WOOD_STAIRS: 
            case LEGACY_BIRCH_WOOD_STAIRS: 
            case LEGACY_JUNGLE_WOOD_STAIRS: 
            case LEGACY_WOOD_BUTTON: 
            case LEGACY_TRAPPED_CHEST: 
            case LEGACY_DAYLIGHT_DETECTOR: 
            case LEGACY_LOG_2: 
            case LEGACY_ACACIA_STAIRS: 
            case LEGACY_DARK_OAK_STAIRS: 
            case LEGACY_CARPET: 
            case LEGACY_COAL_BLOCK: 
            case LEGACY_SPRUCE_FENCE_GATE: 
            case LEGACY_BIRCH_FENCE_GATE: 
            case LEGACY_JUNGLE_FENCE_GATE: 
            case LEGACY_DARK_OAK_FENCE_GATE: 
            case LEGACY_ACACIA_FENCE_GATE: 
            case LEGACY_SPRUCE_FENCE: 
            case LEGACY_BIRCH_FENCE: 
            case LEGACY_JUNGLE_FENCE: 
            case LEGACY_DARK_OAK_FENCE: 
            case LEGACY_ACACIA_FENCE: 
            case LEGACY_BOW: 
            case LEGACY_COAL: 
            case LEGACY_WOOD_SWORD: 
            case LEGACY_WOOD_SPADE: 
            case LEGACY_WOOD_PICKAXE: 
            case LEGACY_WOOD_AXE: 
            case LEGACY_STICK: 
            case LEGACY_BOWL: 
            case LEGACY_WOOD_HOE: 
            case LEGACY_SIGN: 
            case LEGACY_WOOD_DOOR: 
            case LEGACY_LAVA_BUCKET: 
            case LEGACY_BOAT: 
            case LEGACY_FISHING_ROD: 
            case LEGACY_BLAZE_ROD: 
            case LEGACY_BANNER: 
            case LEGACY_SPRUCE_DOOR_ITEM: 
            case LEGACY_BIRCH_DOOR_ITEM: 
            case LEGACY_JUNGLE_DOOR_ITEM: 
            case LEGACY_ACACIA_DOOR_ITEM: 
            case LEGACY_DARK_OAK_DOOR_ITEM: 
            case LEGACY_BOAT_SPRUCE: 
            case LEGACY_BOAT_BIRCH: 
            case LEGACY_BOAT_JUNGLE: 
            case LEGACY_BOAT_ACACIA: 
            case LEGACY_BOAT_DARK_OAK: {
                return true;
            }
        }
        return false;
    }

    public boolean isOccluding() {
        if (!this.isBlock()) {
            return false;
        }
        switch (this) {
            case STONE: 
            case GRANITE: 
            case POLISHED_GRANITE: 
            case DIORITE: 
            case POLISHED_DIORITE: 
            case ANDESITE: 
            case POLISHED_ANDESITE: 
            case DEEPSLATE: 
            case COBBLED_DEEPSLATE: 
            case POLISHED_DEEPSLATE: 
            case CALCITE: 
            case TUFF: 
            case DRIPSTONE_BLOCK: 
            case GRASS_BLOCK: 
            case DIRT: 
            case COARSE_DIRT: 
            case PODZOL: 
            case ROOTED_DIRT: 
            case MUD: 
            case CRIMSON_NYLIUM: 
            case WARPED_NYLIUM: 
            case COBBLESTONE: 
            case OAK_PLANKS: 
            case SPRUCE_PLANKS: 
            case BIRCH_PLANKS: 
            case JUNGLE_PLANKS: 
            case ACACIA_PLANKS: 
            case CHERRY_PLANKS: 
            case DARK_OAK_PLANKS: 
            case MANGROVE_PLANKS: 
            case BAMBOO_PLANKS: 
            case CRIMSON_PLANKS: 
            case WARPED_PLANKS: 
            case BAMBOO_MOSAIC: 
            case BEDROCK: 
            case SAND: 
            case SUSPICIOUS_SAND: 
            case SUSPICIOUS_GRAVEL: 
            case RED_SAND: 
            case GRAVEL: 
            case COAL_ORE: 
            case DEEPSLATE_COAL_ORE: 
            case IRON_ORE: 
            case DEEPSLATE_IRON_ORE: 
            case COPPER_ORE: 
            case DEEPSLATE_COPPER_ORE: 
            case GOLD_ORE: 
            case DEEPSLATE_GOLD_ORE: 
            case REDSTONE_ORE: 
            case DEEPSLATE_REDSTONE_ORE: 
            case EMERALD_ORE: 
            case DEEPSLATE_EMERALD_ORE: 
            case LAPIS_ORE: 
            case DEEPSLATE_LAPIS_ORE: 
            case DIAMOND_ORE: 
            case DEEPSLATE_DIAMOND_ORE: 
            case NETHER_GOLD_ORE: 
            case NETHER_QUARTZ_ORE: 
            case ANCIENT_DEBRIS: 
            case COAL_BLOCK: 
            case RAW_IRON_BLOCK: 
            case RAW_COPPER_BLOCK: 
            case RAW_GOLD_BLOCK: 
            case AMETHYST_BLOCK: 
            case BUDDING_AMETHYST: 
            case IRON_BLOCK: 
            case COPPER_BLOCK: 
            case GOLD_BLOCK: 
            case DIAMOND_BLOCK: 
            case NETHERITE_BLOCK: 
            case EXPOSED_COPPER: 
            case WEATHERED_COPPER: 
            case OXIDIZED_COPPER: 
            case CUT_COPPER: 
            case EXPOSED_CUT_COPPER: 
            case WEATHERED_CUT_COPPER: 
            case OXIDIZED_CUT_COPPER: 
            case WAXED_COPPER_BLOCK: 
            case WAXED_EXPOSED_COPPER: 
            case WAXED_WEATHERED_COPPER: 
            case WAXED_OXIDIZED_COPPER: 
            case WAXED_CUT_COPPER: 
            case WAXED_EXPOSED_CUT_COPPER: 
            case WAXED_WEATHERED_CUT_COPPER: 
            case WAXED_OXIDIZED_CUT_COPPER: 
            case OAK_LOG: 
            case SPRUCE_LOG: 
            case BIRCH_LOG: 
            case JUNGLE_LOG: 
            case ACACIA_LOG: 
            case CHERRY_LOG: 
            case DARK_OAK_LOG: 
            case MANGROVE_LOG: 
            case MANGROVE_ROOTS: 
            case MUDDY_MANGROVE_ROOTS: 
            case CRIMSON_STEM: 
            case WARPED_STEM: 
            case BAMBOO_BLOCK: 
            case STRIPPED_OAK_LOG: 
            case STRIPPED_SPRUCE_LOG: 
            case STRIPPED_BIRCH_LOG: 
            case STRIPPED_JUNGLE_LOG: 
            case STRIPPED_ACACIA_LOG: 
            case STRIPPED_CHERRY_LOG: 
            case STRIPPED_DARK_OAK_LOG: 
            case STRIPPED_MANGROVE_LOG: 
            case STRIPPED_CRIMSON_STEM: 
            case STRIPPED_WARPED_STEM: 
            case STRIPPED_OAK_WOOD: 
            case STRIPPED_SPRUCE_WOOD: 
            case STRIPPED_BIRCH_WOOD: 
            case STRIPPED_JUNGLE_WOOD: 
            case STRIPPED_ACACIA_WOOD: 
            case STRIPPED_CHERRY_WOOD: 
            case STRIPPED_DARK_OAK_WOOD: 
            case STRIPPED_MANGROVE_WOOD: 
            case STRIPPED_CRIMSON_HYPHAE: 
            case STRIPPED_WARPED_HYPHAE: 
            case STRIPPED_BAMBOO_BLOCK: 
            case OAK_WOOD: 
            case SPRUCE_WOOD: 
            case BIRCH_WOOD: 
            case JUNGLE_WOOD: 
            case ACACIA_WOOD: 
            case CHERRY_WOOD: 
            case DARK_OAK_WOOD: 
            case MANGROVE_WOOD: 
            case CRIMSON_HYPHAE: 
            case WARPED_HYPHAE: 
            case SPONGE: 
            case WET_SPONGE: 
            case LAPIS_BLOCK: 
            case SANDSTONE: 
            case CHISELED_SANDSTONE: 
            case CUT_SANDSTONE: 
            case WHITE_WOOL: 
            case ORANGE_WOOL: 
            case MAGENTA_WOOL: 
            case LIGHT_BLUE_WOOL: 
            case YELLOW_WOOL: 
            case LIME_WOOL: 
            case PINK_WOOL: 
            case GRAY_WOOL: 
            case LIGHT_GRAY_WOOL: 
            case CYAN_WOOL: 
            case PURPLE_WOOL: 
            case BLUE_WOOL: 
            case BROWN_WOOL: 
            case GREEN_WOOL: 
            case RED_WOOL: 
            case BLACK_WOOL: 
            case MOSS_BLOCK: 
            case SMOOTH_QUARTZ: 
            case SMOOTH_RED_SANDSTONE: 
            case SMOOTH_SANDSTONE: 
            case SMOOTH_STONE: 
            case BRICKS: 
            case BOOKSHELF: 
            case CHISELED_BOOKSHELF: 
            case MOSSY_COBBLESTONE: 
            case OBSIDIAN: 
            case PURPUR_BLOCK: 
            case PURPUR_PILLAR: 
            case SPAWNER: 
            case CRAFTING_TABLE: 
            case FURNACE: 
            case SNOW_BLOCK: 
            case CLAY: 
            case JUKEBOX: 
            case PUMPKIN: 
            case CARVED_PUMPKIN: 
            case JACK_O_LANTERN: 
            case NETHERRACK: 
            case SOUL_SAND: 
            case SOUL_SOIL: 
            case BASALT: 
            case POLISHED_BASALT: 
            case SMOOTH_BASALT: 
            case INFESTED_STONE: 
            case INFESTED_COBBLESTONE: 
            case INFESTED_STONE_BRICKS: 
            case INFESTED_MOSSY_STONE_BRICKS: 
            case INFESTED_CRACKED_STONE_BRICKS: 
            case INFESTED_CHISELED_STONE_BRICKS: 
            case INFESTED_DEEPSLATE: 
            case STONE_BRICKS: 
            case MOSSY_STONE_BRICKS: 
            case CRACKED_STONE_BRICKS: 
            case CHISELED_STONE_BRICKS: 
            case PACKED_MUD: 
            case MUD_BRICKS: 
            case DEEPSLATE_BRICKS: 
            case CRACKED_DEEPSLATE_BRICKS: 
            case DEEPSLATE_TILES: 
            case CRACKED_DEEPSLATE_TILES: 
            case CHISELED_DEEPSLATE: 
            case REINFORCED_DEEPSLATE: 
            case BROWN_MUSHROOM_BLOCK: 
            case RED_MUSHROOM_BLOCK: 
            case MUSHROOM_STEM: 
            case MELON: 
            case MYCELIUM: 
            case NETHER_BRICKS: 
            case CRACKED_NETHER_BRICKS: 
            case CHISELED_NETHER_BRICKS: 
            case SCULK: 
            case SCULK_CATALYST: 
            case END_STONE: 
            case END_STONE_BRICKS: 
            case EMERALD_BLOCK: 
            case COMMAND_BLOCK: 
            case CHISELED_QUARTZ_BLOCK: 
            case QUARTZ_BLOCK: 
            case QUARTZ_BRICKS: 
            case QUARTZ_PILLAR: 
            case WHITE_TERRACOTTA: 
            case ORANGE_TERRACOTTA: 
            case MAGENTA_TERRACOTTA: 
            case LIGHT_BLUE_TERRACOTTA: 
            case YELLOW_TERRACOTTA: 
            case LIME_TERRACOTTA: 
            case PINK_TERRACOTTA: 
            case GRAY_TERRACOTTA: 
            case LIGHT_GRAY_TERRACOTTA: 
            case CYAN_TERRACOTTA: 
            case PURPLE_TERRACOTTA: 
            case BLUE_TERRACOTTA: 
            case BROWN_TERRACOTTA: 
            case GREEN_TERRACOTTA: 
            case RED_TERRACOTTA: 
            case BLACK_TERRACOTTA: 
            case BARRIER: 
            case HAY_BLOCK: 
            case TERRACOTTA: 
            case PACKED_ICE: 
            case PRISMARINE: 
            case PRISMARINE_BRICKS: 
            case DARK_PRISMARINE: 
            case RED_SANDSTONE: 
            case CHISELED_RED_SANDSTONE: 
            case CUT_RED_SANDSTONE: 
            case REPEATING_COMMAND_BLOCK: 
            case CHAIN_COMMAND_BLOCK: 
            case MAGMA_BLOCK: 
            case NETHER_WART_BLOCK: 
            case WARPED_WART_BLOCK: 
            case RED_NETHER_BRICKS: 
            case BONE_BLOCK: 
            case SHULKER_BOX: 
            case WHITE_SHULKER_BOX: 
            case ORANGE_SHULKER_BOX: 
            case MAGENTA_SHULKER_BOX: 
            case LIGHT_BLUE_SHULKER_BOX: 
            case YELLOW_SHULKER_BOX: 
            case LIME_SHULKER_BOX: 
            case PINK_SHULKER_BOX: 
            case GRAY_SHULKER_BOX: 
            case LIGHT_GRAY_SHULKER_BOX: 
            case CYAN_SHULKER_BOX: 
            case PURPLE_SHULKER_BOX: 
            case BLUE_SHULKER_BOX: 
            case BROWN_SHULKER_BOX: 
            case GREEN_SHULKER_BOX: 
            case RED_SHULKER_BOX: 
            case BLACK_SHULKER_BOX: 
            case WHITE_GLAZED_TERRACOTTA: 
            case ORANGE_GLAZED_TERRACOTTA: 
            case MAGENTA_GLAZED_TERRACOTTA: 
            case LIGHT_BLUE_GLAZED_TERRACOTTA: 
            case YELLOW_GLAZED_TERRACOTTA: 
            case LIME_GLAZED_TERRACOTTA: 
            case PINK_GLAZED_TERRACOTTA: 
            case GRAY_GLAZED_TERRACOTTA: 
            case LIGHT_GRAY_GLAZED_TERRACOTTA: 
            case CYAN_GLAZED_TERRACOTTA: 
            case PURPLE_GLAZED_TERRACOTTA: 
            case BLUE_GLAZED_TERRACOTTA: 
            case BROWN_GLAZED_TERRACOTTA: 
            case GREEN_GLAZED_TERRACOTTA: 
            case RED_GLAZED_TERRACOTTA: 
            case BLACK_GLAZED_TERRACOTTA: 
            case WHITE_CONCRETE: 
            case ORANGE_CONCRETE: 
            case MAGENTA_CONCRETE: 
            case LIGHT_BLUE_CONCRETE: 
            case YELLOW_CONCRETE: 
            case LIME_CONCRETE: 
            case PINK_CONCRETE: 
            case GRAY_CONCRETE: 
            case LIGHT_GRAY_CONCRETE: 
            case CYAN_CONCRETE: 
            case PURPLE_CONCRETE: 
            case BLUE_CONCRETE: 
            case BROWN_CONCRETE: 
            case GREEN_CONCRETE: 
            case RED_CONCRETE: 
            case BLACK_CONCRETE: 
            case WHITE_CONCRETE_POWDER: 
            case ORANGE_CONCRETE_POWDER: 
            case MAGENTA_CONCRETE_POWDER: 
            case LIGHT_BLUE_CONCRETE_POWDER: 
            case YELLOW_CONCRETE_POWDER: 
            case LIME_CONCRETE_POWDER: 
            case PINK_CONCRETE_POWDER: 
            case GRAY_CONCRETE_POWDER: 
            case LIGHT_GRAY_CONCRETE_POWDER: 
            case CYAN_CONCRETE_POWDER: 
            case PURPLE_CONCRETE_POWDER: 
            case BLUE_CONCRETE_POWDER: 
            case BROWN_CONCRETE_POWDER: 
            case GREEN_CONCRETE_POWDER: 
            case RED_CONCRETE_POWDER: 
            case BLACK_CONCRETE_POWDER: 
            case DEAD_TUBE_CORAL_BLOCK: 
            case DEAD_BRAIN_CORAL_BLOCK: 
            case DEAD_BUBBLE_CORAL_BLOCK: 
            case DEAD_FIRE_CORAL_BLOCK: 
            case DEAD_HORN_CORAL_BLOCK: 
            case TUBE_CORAL_BLOCK: 
            case BRAIN_CORAL_BLOCK: 
            case BUBBLE_CORAL_BLOCK: 
            case FIRE_CORAL_BLOCK: 
            case HORN_CORAL_BLOCK: 
            case BLUE_ICE: 
            case SLIME_BLOCK: 
            case DISPENSER: 
            case DROPPER: 
            case TARGET: 
            case REDSTONE_LAMP: 
            case NOTE_BLOCK: 
            case STRUCTURE_BLOCK: 
            case JIGSAW: 
            case DRIED_KELP_BLOCK: 
            case LOOM: 
            case BARREL: 
            case SMOKER: 
            case BLAST_FURNACE: 
            case CARTOGRAPHY_TABLE: 
            case FLETCHING_TABLE: 
            case SMITHING_TABLE: 
            case SHROOMLIGHT: 
            case BEE_NEST: 
            case BEEHIVE: 
            case HONEYCOMB_BLOCK: 
            case LODESTONE: 
            case CRYING_OBSIDIAN: 
            case BLACKSTONE: 
            case GILDED_BLACKSTONE: 
            case POLISHED_BLACKSTONE: 
            case CHISELED_POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_BRICKS: 
            case CRACKED_POLISHED_BLACKSTONE_BRICKS: 
            case RESPAWN_ANCHOR: 
            case OCHRE_FROGLIGHT: 
            case VERDANT_FROGLIGHT: 
            case PEARLESCENT_FROGLIGHT: 
            case LEGACY_STONE: 
            case LEGACY_GRASS: 
            case LEGACY_DIRT: 
            case LEGACY_COBBLESTONE: 
            case LEGACY_WOOD: 
            case LEGACY_BEDROCK: 
            case LEGACY_SAND: 
            case LEGACY_GRAVEL: 
            case LEGACY_GOLD_ORE: 
            case LEGACY_IRON_ORE: 
            case LEGACY_COAL_ORE: 
            case LEGACY_LOG: 
            case LEGACY_SPONGE: 
            case LEGACY_LAPIS_ORE: 
            case LEGACY_LAPIS_BLOCK: 
            case LEGACY_DISPENSER: 
            case LEGACY_SANDSTONE: 
            case LEGACY_NOTE_BLOCK: 
            case LEGACY_WOOL: 
            case LEGACY_GOLD_BLOCK: 
            case LEGACY_IRON_BLOCK: 
            case LEGACY_DOUBLE_STEP: 
            case LEGACY_BRICK: 
            case LEGACY_BOOKSHELF: 
            case LEGACY_MOSSY_COBBLESTONE: 
            case LEGACY_OBSIDIAN: 
            case LEGACY_MOB_SPAWNER: 
            case LEGACY_DIAMOND_ORE: 
            case LEGACY_DIAMOND_BLOCK: 
            case LEGACY_WORKBENCH: 
            case LEGACY_FURNACE: 
            case LEGACY_BURNING_FURNACE: 
            case LEGACY_REDSTONE_ORE: 
            case LEGACY_GLOWING_REDSTONE_ORE: 
            case LEGACY_SNOW_BLOCK: 
            case LEGACY_CLAY: 
            case LEGACY_JUKEBOX: 
            case LEGACY_PUMPKIN: 
            case LEGACY_NETHERRACK: 
            case LEGACY_SOUL_SAND: 
            case LEGACY_JACK_O_LANTERN: 
            case LEGACY_MONSTER_EGGS: 
            case LEGACY_SMOOTH_BRICK: 
            case LEGACY_HUGE_MUSHROOM_1: 
            case LEGACY_HUGE_MUSHROOM_2: 
            case LEGACY_MELON_BLOCK: 
            case LEGACY_MYCEL: 
            case LEGACY_NETHER_BRICK: 
            case LEGACY_ENDER_STONE: 
            case LEGACY_REDSTONE_LAMP_OFF: 
            case LEGACY_REDSTONE_LAMP_ON: 
            case LEGACY_WOOD_DOUBLE_STEP: 
            case LEGACY_EMERALD_ORE: 
            case LEGACY_EMERALD_BLOCK: 
            case LEGACY_COMMAND: 
            case LEGACY_QUARTZ_ORE: 
            case LEGACY_QUARTZ_BLOCK: 
            case LEGACY_DROPPER: 
            case LEGACY_STAINED_CLAY: 
            case LEGACY_LOG_2: 
            case LEGACY_SLIME_BLOCK: 
            case LEGACY_BARRIER: 
            case LEGACY_PRISMARINE: 
            case LEGACY_HAY_BLOCK: 
            case LEGACY_HARD_CLAY: 
            case LEGACY_COAL_BLOCK: 
            case LEGACY_PACKED_ICE: 
            case LEGACY_RED_SANDSTONE: 
            case LEGACY_DOUBLE_STONE_SLAB2: 
            case LEGACY_PURPUR_BLOCK: 
            case LEGACY_PURPUR_PILLAR: 
            case LEGACY_PURPUR_DOUBLE_SLAB: 
            case LEGACY_END_BRICKS: 
            case LEGACY_COMMAND_REPEATING: 
            case LEGACY_COMMAND_CHAIN: 
            case LEGACY_MAGMA: 
            case LEGACY_NETHER_WART_BLOCK: 
            case LEGACY_RED_NETHER_BRICK: 
            case LEGACY_BONE_BLOCK: 
            case LEGACY_WHITE_GLAZED_TERRACOTTA: 
            case LEGACY_ORANGE_GLAZED_TERRACOTTA: 
            case LEGACY_MAGENTA_GLAZED_TERRACOTTA: 
            case LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA: 
            case LEGACY_YELLOW_GLAZED_TERRACOTTA: 
            case LEGACY_LIME_GLAZED_TERRACOTTA: 
            case LEGACY_PINK_GLAZED_TERRACOTTA: 
            case LEGACY_GRAY_GLAZED_TERRACOTTA: 
            case LEGACY_SILVER_GLAZED_TERRACOTTA: 
            case LEGACY_CYAN_GLAZED_TERRACOTTA: 
            case LEGACY_PURPLE_GLAZED_TERRACOTTA: 
            case LEGACY_BLUE_GLAZED_TERRACOTTA: 
            case LEGACY_BROWN_GLAZED_TERRACOTTA: 
            case LEGACY_GREEN_GLAZED_TERRACOTTA: 
            case LEGACY_RED_GLAZED_TERRACOTTA: 
            case LEGACY_BLACK_GLAZED_TERRACOTTA: 
            case LEGACY_CONCRETE: 
            case LEGACY_CONCRETE_POWDER: 
            case LEGACY_STRUCTURE_BLOCK: {
                return true;
            }
        }
        return false;
    }

    public boolean hasGravity() {
        if (!this.isBlock()) {
            return false;
        }
        switch (this) {
            case SAND: 
            case RED_SAND: 
            case GRAVEL: 
            case DRAGON_EGG: 
            case ANVIL: 
            case CHIPPED_ANVIL: 
            case DAMAGED_ANVIL: 
            case WHITE_CONCRETE_POWDER: 
            case ORANGE_CONCRETE_POWDER: 
            case MAGENTA_CONCRETE_POWDER: 
            case LIGHT_BLUE_CONCRETE_POWDER: 
            case YELLOW_CONCRETE_POWDER: 
            case LIME_CONCRETE_POWDER: 
            case PINK_CONCRETE_POWDER: 
            case GRAY_CONCRETE_POWDER: 
            case LIGHT_GRAY_CONCRETE_POWDER: 
            case CYAN_CONCRETE_POWDER: 
            case PURPLE_CONCRETE_POWDER: 
            case BLUE_CONCRETE_POWDER: 
            case BROWN_CONCRETE_POWDER: 
            case GREEN_CONCRETE_POWDER: 
            case RED_CONCRETE_POWDER: 
            case BLACK_CONCRETE_POWDER: 
            case LEGACY_SAND: 
            case LEGACY_GRAVEL: 
            case LEGACY_ANVIL: 
            case LEGACY_CONCRETE_POWDER: {
                return true;
            }
        }
        return false;
    }

    public boolean isItem() {
        switch (this) {
            case WATER: 
            case LAVA: 
            case TALL_SEAGRASS: 
            case PISTON_HEAD: 
            case MOVING_PISTON: 
            case WALL_TORCH: 
            case FIRE: 
            case SOUL_FIRE: 
            case REDSTONE_WIRE: 
            case OAK_WALL_SIGN: 
            case SPRUCE_WALL_SIGN: 
            case BIRCH_WALL_SIGN: 
            case ACACIA_WALL_SIGN: 
            case CHERRY_WALL_SIGN: 
            case JUNGLE_WALL_SIGN: 
            case DARK_OAK_WALL_SIGN: 
            case MANGROVE_WALL_SIGN: 
            case BAMBOO_WALL_SIGN: 
            case OAK_WALL_HANGING_SIGN: 
            case SPRUCE_WALL_HANGING_SIGN: 
            case BIRCH_WALL_HANGING_SIGN: 
            case ACACIA_WALL_HANGING_SIGN: 
            case CHERRY_WALL_HANGING_SIGN: 
            case JUNGLE_WALL_HANGING_SIGN: 
            case DARK_OAK_WALL_HANGING_SIGN: 
            case MANGROVE_WALL_HANGING_SIGN: 
            case CRIMSON_WALL_HANGING_SIGN: 
            case WARPED_WALL_HANGING_SIGN: 
            case BAMBOO_WALL_HANGING_SIGN: 
            case REDSTONE_WALL_TORCH: 
            case SOUL_WALL_TORCH: 
            case NETHER_PORTAL: 
            case ATTACHED_PUMPKIN_STEM: 
            case ATTACHED_MELON_STEM: 
            case PUMPKIN_STEM: 
            case MELON_STEM: 
            case WATER_CAULDRON: 
            case LAVA_CAULDRON: 
            case POWDER_SNOW_CAULDRON: 
            case END_PORTAL: 
            case COCOA: 
            case TRIPWIRE: 
            case POTTED_TORCHFLOWER: 
            case POTTED_OAK_SAPLING: 
            case POTTED_SPRUCE_SAPLING: 
            case POTTED_BIRCH_SAPLING: 
            case POTTED_JUNGLE_SAPLING: 
            case POTTED_ACACIA_SAPLING: 
            case POTTED_CHERRY_SAPLING: 
            case POTTED_DARK_OAK_SAPLING: 
            case POTTED_MANGROVE_PROPAGULE: 
            case POTTED_FERN: 
            case POTTED_DANDELION: 
            case POTTED_POPPY: 
            case POTTED_BLUE_ORCHID: 
            case POTTED_ALLIUM: 
            case POTTED_AZURE_BLUET: 
            case POTTED_RED_TULIP: 
            case POTTED_ORANGE_TULIP: 
            case POTTED_WHITE_TULIP: 
            case POTTED_PINK_TULIP: 
            case POTTED_OXEYE_DAISY: 
            case POTTED_CORNFLOWER: 
            case POTTED_LILY_OF_THE_VALLEY: 
            case POTTED_WITHER_ROSE: 
            case POTTED_RED_MUSHROOM: 
            case POTTED_BROWN_MUSHROOM: 
            case POTTED_DEAD_BUSH: 
            case POTTED_CACTUS: 
            case CARROTS: 
            case POTATOES: 
            case SKELETON_WALL_SKULL: 
            case WITHER_SKELETON_WALL_SKULL: 
            case ZOMBIE_WALL_HEAD: 
            case PLAYER_WALL_HEAD: 
            case CREEPER_WALL_HEAD: 
            case DRAGON_WALL_HEAD: 
            case PIGLIN_WALL_HEAD: 
            case WHITE_WALL_BANNER: 
            case ORANGE_WALL_BANNER: 
            case MAGENTA_WALL_BANNER: 
            case LIGHT_BLUE_WALL_BANNER: 
            case YELLOW_WALL_BANNER: 
            case LIME_WALL_BANNER: 
            case PINK_WALL_BANNER: 
            case GRAY_WALL_BANNER: 
            case LIGHT_GRAY_WALL_BANNER: 
            case CYAN_WALL_BANNER: 
            case PURPLE_WALL_BANNER: 
            case BLUE_WALL_BANNER: 
            case BROWN_WALL_BANNER: 
            case GREEN_WALL_BANNER: 
            case RED_WALL_BANNER: 
            case BLACK_WALL_BANNER: 
            case TORCHFLOWER_CROP: 
            case PITCHER_CROP: 
            case BEETROOTS: 
            case END_GATEWAY: 
            case FROSTED_ICE: 
            case KELP_PLANT: 
            case DEAD_TUBE_CORAL_WALL_FAN: 
            case DEAD_BRAIN_CORAL_WALL_FAN: 
            case DEAD_BUBBLE_CORAL_WALL_FAN: 
            case DEAD_FIRE_CORAL_WALL_FAN: 
            case DEAD_HORN_CORAL_WALL_FAN: 
            case TUBE_CORAL_WALL_FAN: 
            case BRAIN_CORAL_WALL_FAN: 
            case BUBBLE_CORAL_WALL_FAN: 
            case FIRE_CORAL_WALL_FAN: 
            case HORN_CORAL_WALL_FAN: 
            case BAMBOO_SAPLING: 
            case POTTED_BAMBOO: 
            case VOID_AIR: 
            case CAVE_AIR: 
            case BUBBLE_COLUMN: 
            case SWEET_BERRY_BUSH: 
            case WEEPING_VINES_PLANT: 
            case TWISTING_VINES_PLANT: 
            case CRIMSON_WALL_SIGN: 
            case WARPED_WALL_SIGN: 
            case POTTED_CRIMSON_FUNGUS: 
            case POTTED_WARPED_FUNGUS: 
            case POTTED_CRIMSON_ROOTS: 
            case POTTED_WARPED_ROOTS: 
            case CANDLE_CAKE: 
            case WHITE_CANDLE_CAKE: 
            case ORANGE_CANDLE_CAKE: 
            case MAGENTA_CANDLE_CAKE: 
            case LIGHT_BLUE_CANDLE_CAKE: 
            case YELLOW_CANDLE_CAKE: 
            case LIME_CANDLE_CAKE: 
            case PINK_CANDLE_CAKE: 
            case GRAY_CANDLE_CAKE: 
            case LIGHT_GRAY_CANDLE_CAKE: 
            case CYAN_CANDLE_CAKE: 
            case PURPLE_CANDLE_CAKE: 
            case BLUE_CANDLE_CAKE: 
            case BROWN_CANDLE_CAKE: 
            case GREEN_CANDLE_CAKE: 
            case RED_CANDLE_CAKE: 
            case BLACK_CANDLE_CAKE: 
            case POWDER_SNOW: 
            case CAVE_VINES: 
            case CAVE_VINES_PLANT: 
            case BIG_DRIPLEAF_STEM: 
            case POTTED_AZALEA_BUSH: 
            case POTTED_FLOWERING_AZALEA_BUSH: 
            case LEGACY_WATER: 
            case LEGACY_STATIONARY_WATER: 
            case LEGACY_LAVA: 
            case LEGACY_STATIONARY_LAVA: 
            case LEGACY_BED_BLOCK: 
            case LEGACY_PISTON_EXTENSION: 
            case LEGACY_PISTON_MOVING_PIECE: 
            case LEGACY_DOUBLE_STEP: 
            case LEGACY_FIRE: 
            case LEGACY_REDSTONE_WIRE: 
            case LEGACY_CROPS: 
            case LEGACY_BURNING_FURNACE: 
            case LEGACY_SIGN_POST: 
            case LEGACY_WOODEN_DOOR: 
            case LEGACY_WALL_SIGN: 
            case LEGACY_IRON_DOOR_BLOCK: 
            case LEGACY_GLOWING_REDSTONE_ORE: 
            case LEGACY_REDSTONE_TORCH_OFF: 
            case LEGACY_SUGAR_CANE_BLOCK: 
            case LEGACY_PORTAL: 
            case LEGACY_CAKE_BLOCK: 
            case LEGACY_DIODE_BLOCK_OFF: 
            case LEGACY_DIODE_BLOCK_ON: 
            case LEGACY_PUMPKIN_STEM: 
            case LEGACY_MELON_STEM: 
            case LEGACY_NETHER_WARTS: 
            case LEGACY_BREWING_STAND: 
            case LEGACY_CAULDRON: 
            case LEGACY_ENDER_PORTAL: 
            case LEGACY_REDSTONE_LAMP_ON: 
            case LEGACY_WOOD_DOUBLE_STEP: 
            case LEGACY_COCOA: 
            case LEGACY_TRIPWIRE: 
            case LEGACY_FLOWER_POT: 
            case LEGACY_CARROT: 
            case LEGACY_POTATO: 
            case LEGACY_SKULL: 
            case LEGACY_REDSTONE_COMPARATOR_OFF: 
            case LEGACY_REDSTONE_COMPARATOR_ON: 
            case LEGACY_STANDING_BANNER: 
            case LEGACY_WALL_BANNER: 
            case LEGACY_DAYLIGHT_DETECTOR_INVERTED: 
            case LEGACY_DOUBLE_STONE_SLAB2: 
            case LEGACY_SPRUCE_DOOR: 
            case LEGACY_BIRCH_DOOR: 
            case LEGACY_JUNGLE_DOOR: 
            case LEGACY_ACACIA_DOOR: 
            case LEGACY_DARK_OAK_DOOR: 
            case LEGACY_PURPUR_DOUBLE_SLAB: 
            case LEGACY_BEETROOT_BLOCK: 
            case LEGACY_END_GATEWAY: 
            case LEGACY_FROSTED_ICE: {
                return false;
            }
        }
        return true;
    }

    public boolean isInteractable() {
        switch (this) {
            case REDSTONE_ORE: 
            case DEEPSLATE_REDSTONE_ORE: 
            case CUT_COPPER_STAIRS: 
            case EXPOSED_CUT_COPPER_STAIRS: 
            case WEATHERED_CUT_COPPER_STAIRS: 
            case OXIDIZED_CUT_COPPER_STAIRS: 
            case WAXED_CUT_COPPER_STAIRS: 
            case WAXED_EXPOSED_CUT_COPPER_STAIRS: 
            case WAXED_WEATHERED_CUT_COPPER_STAIRS: 
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS: 
            case CHISELED_BOOKSHELF: 
            case PURPUR_STAIRS: 
            case CHEST: 
            case CRAFTING_TABLE: 
            case FURNACE: 
            case COBBLESTONE_STAIRS: 
            case JUKEBOX: 
            case OAK_FENCE: 
            case SPRUCE_FENCE: 
            case BIRCH_FENCE: 
            case JUNGLE_FENCE: 
            case ACACIA_FENCE: 
            case CHERRY_FENCE: 
            case DARK_OAK_FENCE: 
            case MANGROVE_FENCE: 
            case BAMBOO_FENCE: 
            case CRIMSON_FENCE: 
            case WARPED_FENCE: 
            case PUMPKIN: 
            case BRICK_STAIRS: 
            case STONE_BRICK_STAIRS: 
            case MUD_BRICK_STAIRS: 
            case NETHER_BRICK_FENCE: 
            case NETHER_BRICK_STAIRS: 
            case ENCHANTING_TABLE: 
            case DRAGON_EGG: 
            case SANDSTONE_STAIRS: 
            case ENDER_CHEST: 
            case OAK_STAIRS: 
            case SPRUCE_STAIRS: 
            case BIRCH_STAIRS: 
            case JUNGLE_STAIRS: 
            case ACACIA_STAIRS: 
            case CHERRY_STAIRS: 
            case DARK_OAK_STAIRS: 
            case MANGROVE_STAIRS: 
            case BAMBOO_STAIRS: 
            case BAMBOO_MOSAIC_STAIRS: 
            case CRIMSON_STAIRS: 
            case WARPED_STAIRS: 
            case COMMAND_BLOCK: 
            case BEACON: 
            case ANVIL: 
            case CHIPPED_ANVIL: 
            case DAMAGED_ANVIL: 
            case QUARTZ_STAIRS: 
            case LIGHT: 
            case PRISMARINE_STAIRS: 
            case PRISMARINE_BRICK_STAIRS: 
            case DARK_PRISMARINE_STAIRS: 
            case RED_SANDSTONE_STAIRS: 
            case REPEATING_COMMAND_BLOCK: 
            case CHAIN_COMMAND_BLOCK: 
            case SHULKER_BOX: 
            case WHITE_SHULKER_BOX: 
            case ORANGE_SHULKER_BOX: 
            case MAGENTA_SHULKER_BOX: 
            case LIGHT_BLUE_SHULKER_BOX: 
            case YELLOW_SHULKER_BOX: 
            case LIME_SHULKER_BOX: 
            case PINK_SHULKER_BOX: 
            case GRAY_SHULKER_BOX: 
            case LIGHT_GRAY_SHULKER_BOX: 
            case CYAN_SHULKER_BOX: 
            case PURPLE_SHULKER_BOX: 
            case BLUE_SHULKER_BOX: 
            case BROWN_SHULKER_BOX: 
            case GREEN_SHULKER_BOX: 
            case RED_SHULKER_BOX: 
            case BLACK_SHULKER_BOX: 
            case POLISHED_GRANITE_STAIRS: 
            case SMOOTH_RED_SANDSTONE_STAIRS: 
            case MOSSY_STONE_BRICK_STAIRS: 
            case POLISHED_DIORITE_STAIRS: 
            case MOSSY_COBBLESTONE_STAIRS: 
            case END_STONE_BRICK_STAIRS: 
            case STONE_STAIRS: 
            case SMOOTH_SANDSTONE_STAIRS: 
            case SMOOTH_QUARTZ_STAIRS: 
            case GRANITE_STAIRS: 
            case ANDESITE_STAIRS: 
            case RED_NETHER_BRICK_STAIRS: 
            case POLISHED_ANDESITE_STAIRS: 
            case DIORITE_STAIRS: 
            case COBBLED_DEEPSLATE_STAIRS: 
            case POLISHED_DEEPSLATE_STAIRS: 
            case DEEPSLATE_BRICK_STAIRS: 
            case DEEPSLATE_TILE_STAIRS: 
            case REPEATER: 
            case COMPARATOR: 
            case HOPPER: 
            case DISPENSER: 
            case DROPPER: 
            case LECTERN: 
            case LEVER: 
            case DAYLIGHT_DETECTOR: 
            case TRAPPED_CHEST: 
            case TNT: 
            case NOTE_BLOCK: 
            case STONE_BUTTON: 
            case POLISHED_BLACKSTONE_BUTTON: 
            case OAK_BUTTON: 
            case SPRUCE_BUTTON: 
            case BIRCH_BUTTON: 
            case JUNGLE_BUTTON: 
            case ACACIA_BUTTON: 
            case CHERRY_BUTTON: 
            case DARK_OAK_BUTTON: 
            case MANGROVE_BUTTON: 
            case BAMBOO_BUTTON: 
            case CRIMSON_BUTTON: 
            case WARPED_BUTTON: 
            case IRON_DOOR: 
            case OAK_DOOR: 
            case SPRUCE_DOOR: 
            case BIRCH_DOOR: 
            case JUNGLE_DOOR: 
            case ACACIA_DOOR: 
            case CHERRY_DOOR: 
            case DARK_OAK_DOOR: 
            case MANGROVE_DOOR: 
            case BAMBOO_DOOR: 
            case CRIMSON_DOOR: 
            case WARPED_DOOR: 
            case IRON_TRAPDOOR: 
            case OAK_TRAPDOOR: 
            case SPRUCE_TRAPDOOR: 
            case BIRCH_TRAPDOOR: 
            case JUNGLE_TRAPDOOR: 
            case ACACIA_TRAPDOOR: 
            case CHERRY_TRAPDOOR: 
            case DARK_OAK_TRAPDOOR: 
            case MANGROVE_TRAPDOOR: 
            case BAMBOO_TRAPDOOR: 
            case CRIMSON_TRAPDOOR: 
            case WARPED_TRAPDOOR: 
            case OAK_FENCE_GATE: 
            case SPRUCE_FENCE_GATE: 
            case BIRCH_FENCE_GATE: 
            case JUNGLE_FENCE_GATE: 
            case ACACIA_FENCE_GATE: 
            case CHERRY_FENCE_GATE: 
            case DARK_OAK_FENCE_GATE: 
            case MANGROVE_FENCE_GATE: 
            case BAMBOO_FENCE_GATE: 
            case CRIMSON_FENCE_GATE: 
            case WARPED_FENCE_GATE: 
            case STRUCTURE_BLOCK: 
            case JIGSAW: 
            case OAK_SIGN: 
            case SPRUCE_SIGN: 
            case BIRCH_SIGN: 
            case JUNGLE_SIGN: 
            case ACACIA_SIGN: 
            case CHERRY_SIGN: 
            case DARK_OAK_SIGN: 
            case MANGROVE_SIGN: 
            case BAMBOO_SIGN: 
            case CRIMSON_SIGN: 
            case WARPED_SIGN: 
            case OAK_HANGING_SIGN: 
            case SPRUCE_HANGING_SIGN: 
            case BIRCH_HANGING_SIGN: 
            case JUNGLE_HANGING_SIGN: 
            case ACACIA_HANGING_SIGN: 
            case CHERRY_HANGING_SIGN: 
            case DARK_OAK_HANGING_SIGN: 
            case MANGROVE_HANGING_SIGN: 
            case BAMBOO_HANGING_SIGN: 
            case CRIMSON_HANGING_SIGN: 
            case WARPED_HANGING_SIGN: 
            case CAKE: 
            case WHITE_BED: 
            case ORANGE_BED: 
            case MAGENTA_BED: 
            case LIGHT_BLUE_BED: 
            case YELLOW_BED: 
            case LIME_BED: 
            case PINK_BED: 
            case GRAY_BED: 
            case LIGHT_GRAY_BED: 
            case CYAN_BED: 
            case PURPLE_BED: 
            case BLUE_BED: 
            case BROWN_BED: 
            case GREEN_BED: 
            case RED_BED: 
            case BLACK_BED: 
            case BREWING_STAND: 
            case CAULDRON: 
            case FLOWER_POT: 
            case LOOM: 
            case COMPOSTER: 
            case BARREL: 
            case SMOKER: 
            case BLAST_FURNACE: 
            case CARTOGRAPHY_TABLE: 
            case FLETCHING_TABLE: 
            case GRINDSTONE: 
            case SMITHING_TABLE: 
            case STONECUTTER: 
            case BELL: 
            case CAMPFIRE: 
            case SOUL_CAMPFIRE: 
            case BEE_NEST: 
            case BEEHIVE: 
            case BLACKSTONE_STAIRS: 
            case POLISHED_BLACKSTONE_STAIRS: 
            case POLISHED_BLACKSTONE_BRICK_STAIRS: 
            case RESPAWN_ANCHOR: 
            case CANDLE: 
            case WHITE_CANDLE: 
            case ORANGE_CANDLE: 
            case MAGENTA_CANDLE: 
            case LIGHT_BLUE_CANDLE: 
            case YELLOW_CANDLE: 
            case LIME_CANDLE: 
            case PINK_CANDLE: 
            case GRAY_CANDLE: 
            case LIGHT_GRAY_CANDLE: 
            case CYAN_CANDLE: 
            case PURPLE_CANDLE: 
            case BLUE_CANDLE: 
            case BROWN_CANDLE: 
            case GREEN_CANDLE: 
            case RED_CANDLE: 
            case BLACK_CANDLE: 
            case MOVING_PISTON: 
            case REDSTONE_WIRE: 
            case OAK_WALL_SIGN: 
            case SPRUCE_WALL_SIGN: 
            case BIRCH_WALL_SIGN: 
            case ACACIA_WALL_SIGN: 
            case CHERRY_WALL_SIGN: 
            case JUNGLE_WALL_SIGN: 
            case DARK_OAK_WALL_SIGN: 
            case MANGROVE_WALL_SIGN: 
            case BAMBOO_WALL_SIGN: 
            case OAK_WALL_HANGING_SIGN: 
            case SPRUCE_WALL_HANGING_SIGN: 
            case BIRCH_WALL_HANGING_SIGN: 
            case ACACIA_WALL_HANGING_SIGN: 
            case CHERRY_WALL_HANGING_SIGN: 
            case JUNGLE_WALL_HANGING_SIGN: 
            case DARK_OAK_WALL_HANGING_SIGN: 
            case MANGROVE_WALL_HANGING_SIGN: 
            case CRIMSON_WALL_HANGING_SIGN: 
            case WARPED_WALL_HANGING_SIGN: 
            case BAMBOO_WALL_HANGING_SIGN: 
            case WATER_CAULDRON: 
            case LAVA_CAULDRON: 
            case POWDER_SNOW_CAULDRON: 
            case POTTED_TORCHFLOWER: 
            case POTTED_OAK_SAPLING: 
            case POTTED_SPRUCE_SAPLING: 
            case POTTED_BIRCH_SAPLING: 
            case POTTED_JUNGLE_SAPLING: 
            case POTTED_ACACIA_SAPLING: 
            case POTTED_CHERRY_SAPLING: 
            case POTTED_DARK_OAK_SAPLING: 
            case POTTED_MANGROVE_PROPAGULE: 
            case POTTED_FERN: 
            case POTTED_DANDELION: 
            case POTTED_POPPY: 
            case POTTED_BLUE_ORCHID: 
            case POTTED_ALLIUM: 
            case POTTED_AZURE_BLUET: 
            case POTTED_RED_TULIP: 
            case POTTED_ORANGE_TULIP: 
            case POTTED_WHITE_TULIP: 
            case POTTED_PINK_TULIP: 
            case POTTED_OXEYE_DAISY: 
            case POTTED_CORNFLOWER: 
            case POTTED_LILY_OF_THE_VALLEY: 
            case POTTED_WITHER_ROSE: 
            case POTTED_RED_MUSHROOM: 
            case POTTED_BROWN_MUSHROOM: 
            case POTTED_DEAD_BUSH: 
            case POTTED_CACTUS: 
            case POTTED_BAMBOO: 
            case SWEET_BERRY_BUSH: 
            case CRIMSON_WALL_SIGN: 
            case WARPED_WALL_SIGN: 
            case POTTED_CRIMSON_FUNGUS: 
            case POTTED_WARPED_FUNGUS: 
            case POTTED_CRIMSON_ROOTS: 
            case POTTED_WARPED_ROOTS: 
            case CANDLE_CAKE: 
            case WHITE_CANDLE_CAKE: 
            case ORANGE_CANDLE_CAKE: 
            case MAGENTA_CANDLE_CAKE: 
            case LIGHT_BLUE_CANDLE_CAKE: 
            case YELLOW_CANDLE_CAKE: 
            case LIME_CANDLE_CAKE: 
            case PINK_CANDLE_CAKE: 
            case GRAY_CANDLE_CAKE: 
            case LIGHT_GRAY_CANDLE_CAKE: 
            case CYAN_CANDLE_CAKE: 
            case PURPLE_CANDLE_CAKE: 
            case BLUE_CANDLE_CAKE: 
            case BROWN_CANDLE_CAKE: 
            case GREEN_CANDLE_CAKE: 
            case RED_CANDLE_CAKE: 
            case BLACK_CANDLE_CAKE: 
            case CAVE_VINES: 
            case CAVE_VINES_PLANT: 
            case POTTED_AZALEA_BUSH: 
            case POTTED_FLOWERING_AZALEA_BUSH: {
                return true;
            }
        }
        return false;
    }

    public float getHardness() {
        Preconditions.checkArgument((boolean)this.isBlock(), (Object)"The Material is not a block!");
        switch (this) {
            case BEDROCK: 
            case END_PORTAL_FRAME: 
            case COMMAND_BLOCK: 
            case BARRIER: 
            case LIGHT: 
            case REPEATING_COMMAND_BLOCK: 
            case CHAIN_COMMAND_BLOCK: 
            case STRUCTURE_BLOCK: 
            case JIGSAW: 
            case MOVING_PISTON: 
            case NETHER_PORTAL: 
            case END_PORTAL: 
            case END_GATEWAY: {
                return -1.0f;
            }
            case MOSS_CARPET: 
            case MOSS_BLOCK: 
            case BIG_DRIPLEAF: 
            case SNOW: 
            case WHITE_CARPET: 
            case ORANGE_CARPET: 
            case MAGENTA_CARPET: 
            case LIGHT_BLUE_CARPET: 
            case YELLOW_CARPET: 
            case LIME_CARPET: 
            case PINK_CARPET: 
            case GRAY_CARPET: 
            case LIGHT_GRAY_CARPET: 
            case CYAN_CARPET: 
            case PURPLE_CARPET: 
            case BLUE_CARPET: 
            case BROWN_CARPET: 
            case GREEN_CARPET: 
            case RED_CARPET: 
            case BLACK_CARPET: 
            case CANDLE: 
            case WHITE_CANDLE: 
            case ORANGE_CANDLE: 
            case MAGENTA_CANDLE: 
            case LIGHT_BLUE_CANDLE: 
            case YELLOW_CANDLE: 
            case LIME_CANDLE: 
            case PINK_CANDLE: 
            case GRAY_CANDLE: 
            case LIGHT_GRAY_CANDLE: 
            case CYAN_CANDLE: 
            case PURPLE_CANDLE: 
            case BLUE_CANDLE: 
            case BROWN_CANDLE: 
            case GREEN_CANDLE: 
            case RED_CANDLE: 
            case BLACK_CANDLE: 
            case BIG_DRIPLEAF_STEM: {
                return 0.1f;
            }
            case OAK_LEAVES: 
            case SPRUCE_LEAVES: 
            case BIRCH_LEAVES: 
            case JUNGLE_LEAVES: 
            case ACACIA_LEAVES: 
            case CHERRY_LEAVES: 
            case DARK_OAK_LEAVES: 
            case MANGROVE_LEAVES: 
            case AZALEA_LEAVES: 
            case FLOWERING_AZALEA_LEAVES: 
            case SNOW_BLOCK: 
            case BROWN_MUSHROOM_BLOCK: 
            case RED_MUSHROOM_BLOCK: 
            case MUSHROOM_STEM: 
            case VINE: 
            case GLOW_LICHEN: 
            case SCULK: 
            case SCULK_VEIN: 
            case DAYLIGHT_DETECTOR: 
            case WHITE_BED: 
            case ORANGE_BED: 
            case MAGENTA_BED: 
            case LIGHT_BLUE_BED: 
            case YELLOW_BED: 
            case LIME_BED: 
            case PINK_BED: 
            case GRAY_BED: 
            case LIGHT_GRAY_BED: 
            case CYAN_BED: 
            case PURPLE_BED: 
            case BLUE_BED: 
            case BROWN_BED: 
            case GREEN_BED: 
            case RED_BED: 
            case BLACK_BED: 
            case COCOA: {
                return 0.2f;
            }
            case SUSPICIOUS_SAND: 
            case SUSPICIOUS_GRAVEL: 
            case POWDER_SNOW: {
                return 0.25f;
            }
            case GLASS: 
            case TINTED_GLASS: 
            case GLOWSTONE: 
            case GLASS_PANE: 
            case WHITE_STAINED_GLASS: 
            case ORANGE_STAINED_GLASS: 
            case MAGENTA_STAINED_GLASS: 
            case LIGHT_BLUE_STAINED_GLASS: 
            case YELLOW_STAINED_GLASS: 
            case LIME_STAINED_GLASS: 
            case PINK_STAINED_GLASS: 
            case GRAY_STAINED_GLASS: 
            case LIGHT_GRAY_STAINED_GLASS: 
            case CYAN_STAINED_GLASS: 
            case PURPLE_STAINED_GLASS: 
            case BLUE_STAINED_GLASS: 
            case BROWN_STAINED_GLASS: 
            case GREEN_STAINED_GLASS: 
            case RED_STAINED_GLASS: 
            case BLACK_STAINED_GLASS: 
            case WHITE_STAINED_GLASS_PANE: 
            case ORANGE_STAINED_GLASS_PANE: 
            case MAGENTA_STAINED_GLASS_PANE: 
            case LIGHT_BLUE_STAINED_GLASS_PANE: 
            case YELLOW_STAINED_GLASS_PANE: 
            case LIME_STAINED_GLASS_PANE: 
            case PINK_STAINED_GLASS_PANE: 
            case GRAY_STAINED_GLASS_PANE: 
            case LIGHT_GRAY_STAINED_GLASS_PANE: 
            case CYAN_STAINED_GLASS_PANE: 
            case PURPLE_STAINED_GLASS_PANE: 
            case BLUE_STAINED_GLASS_PANE: 
            case BROWN_STAINED_GLASS_PANE: 
            case GREEN_STAINED_GLASS_PANE: 
            case RED_STAINED_GLASS_PANE: 
            case BLACK_STAINED_GLASS_PANE: 
            case SEA_LANTERN: 
            case REDSTONE_LAMP: 
            case BEE_NEST: 
            case OCHRE_FROGLIGHT: 
            case VERDANT_FROGLIGHT: 
            case PEARLESCENT_FROGLIGHT: {
                return 0.3f;
            }
            case CRIMSON_NYLIUM: 
            case WARPED_NYLIUM: 
            case CHORUS_PLANT: 
            case CHORUS_FLOWER: 
            case LADDER: 
            case CACTUS: 
            case NETHERRACK: {
                return 0.4f;
            }
            case DIRT: 
            case COARSE_DIRT: 
            case PODZOL: 
            case ROOTED_DIRT: 
            case MUD: 
            case SAND: 
            case RED_SAND: 
            case ICE: 
            case SOUL_SAND: 
            case SOUL_SOIL: 
            case HAY_BLOCK: 
            case PACKED_ICE: 
            case MAGMA_BLOCK: 
            case WHITE_CONCRETE_POWDER: 
            case ORANGE_CONCRETE_POWDER: 
            case MAGENTA_CONCRETE_POWDER: 
            case LIGHT_BLUE_CONCRETE_POWDER: 
            case YELLOW_CONCRETE_POWDER: 
            case LIME_CONCRETE_POWDER: 
            case PINK_CONCRETE_POWDER: 
            case GRAY_CONCRETE_POWDER: 
            case LIGHT_GRAY_CONCRETE_POWDER: 
            case CYAN_CONCRETE_POWDER: 
            case PURPLE_CONCRETE_POWDER: 
            case BLUE_CONCRETE_POWDER: 
            case BROWN_CONCRETE_POWDER: 
            case GREEN_CONCRETE_POWDER: 
            case RED_CONCRETE_POWDER: 
            case BLACK_CONCRETE_POWDER: 
            case TURTLE_EGG: 
            case SNIFFER_EGG: 
            case TARGET: 
            case LEVER: 
            case STONE_BUTTON: 
            case POLISHED_BLACKSTONE_BUTTON: 
            case OAK_BUTTON: 
            case SPRUCE_BUTTON: 
            case BIRCH_BUTTON: 
            case JUNGLE_BUTTON: 
            case ACACIA_BUTTON: 
            case CHERRY_BUTTON: 
            case DARK_OAK_BUTTON: 
            case MANGROVE_BUTTON: 
            case BAMBOO_BUTTON: 
            case CRIMSON_BUTTON: 
            case WARPED_BUTTON: 
            case STONE_PRESSURE_PLATE: 
            case POLISHED_BLACKSTONE_PRESSURE_PLATE: 
            case LIGHT_WEIGHTED_PRESSURE_PLATE: 
            case HEAVY_WEIGHTED_PRESSURE_PLATE: 
            case OAK_PRESSURE_PLATE: 
            case SPRUCE_PRESSURE_PLATE: 
            case BIRCH_PRESSURE_PLATE: 
            case JUNGLE_PRESSURE_PLATE: 
            case ACACIA_PRESSURE_PLATE: 
            case CHERRY_PRESSURE_PLATE: 
            case DARK_OAK_PRESSURE_PLATE: 
            case MANGROVE_PRESSURE_PLATE: 
            case BAMBOO_PRESSURE_PLATE: 
            case CRIMSON_PRESSURE_PLATE: 
            case WARPED_PRESSURE_PLATE: 
            case DRIED_KELP_BLOCK: 
            case CAKE: 
            case BREWING_STAND: 
            case FROSTED_ICE: 
            case CANDLE_CAKE: 
            case WHITE_CANDLE_CAKE: 
            case ORANGE_CANDLE_CAKE: 
            case MAGENTA_CANDLE_CAKE: 
            case LIGHT_BLUE_CANDLE_CAKE: 
            case YELLOW_CANDLE_CAKE: 
            case LIME_CANDLE_CAKE: 
            case PINK_CANDLE_CAKE: 
            case GRAY_CANDLE_CAKE: 
            case LIGHT_GRAY_CANDLE_CAKE: 
            case CYAN_CANDLE_CAKE: 
            case PURPLE_CANDLE_CAKE: 
            case BLUE_CANDLE_CAKE: 
            case BROWN_CANDLE_CAKE: 
            case GREEN_CANDLE_CAKE: 
            case RED_CANDLE_CAKE: 
            case BLACK_CANDLE_CAKE: {
                return 0.5f;
            }
            case GRASS_BLOCK: 
            case GRAVEL: 
            case SPONGE: 
            case WET_SPONGE: 
            case FARMLAND: 
            case CLAY: 
            case MYCELIUM: 
            case COMPOSTER: 
            case BEEHIVE: 
            case HONEYCOMB_BLOCK: {
                return 0.6f;
            }
            case DIRT_PATH: {
                return 0.65f;
            }
            case MANGROVE_ROOTS: 
            case MUDDY_MANGROVE_ROOTS: 
            case POWERED_RAIL: 
            case DETECTOR_RAIL: 
            case RAIL: 
            case ACTIVATOR_RAIL: {
                return 0.7f;
            }
            case CALCITE: 
            case INFESTED_STONE: 
            case INFESTED_STONE_BRICKS: 
            case INFESTED_MOSSY_STONE_BRICKS: 
            case INFESTED_CRACKED_STONE_BRICKS: 
            case INFESTED_CHISELED_STONE_BRICKS: {
                return 0.75f;
            }
            case SANDSTONE: 
            case CHISELED_SANDSTONE: 
            case CUT_SANDSTONE: 
            case WHITE_WOOL: 
            case ORANGE_WOOL: 
            case MAGENTA_WOOL: 
            case LIGHT_BLUE_WOOL: 
            case YELLOW_WOOL: 
            case LIME_WOOL: 
            case PINK_WOOL: 
            case GRAY_WOOL: 
            case LIGHT_GRAY_WOOL: 
            case CYAN_WOOL: 
            case PURPLE_WOOL: 
            case BLUE_WOOL: 
            case BROWN_WOOL: 
            case GREEN_WOOL: 
            case RED_WOOL: 
            case BLACK_WOOL: 
            case SANDSTONE_STAIRS: 
            case RED_SANDSTONE_WALL: 
            case SANDSTONE_WALL: 
            case CHISELED_QUARTZ_BLOCK: 
            case QUARTZ_BLOCK: 
            case QUARTZ_BRICKS: 
            case QUARTZ_PILLAR: 
            case QUARTZ_STAIRS: 
            case RED_SANDSTONE: 
            case CHISELED_RED_SANDSTONE: 
            case CUT_RED_SANDSTONE: 
            case RED_SANDSTONE_STAIRS: 
            case NOTE_BLOCK: {
                return 0.8f;
            }
            case BAMBOO: 
            case PUMPKIN: 
            case CARVED_PUMPKIN: 
            case JACK_O_LANTERN: 
            case INFESTED_COBBLESTONE: 
            case PACKED_MUD: 
            case MELON: 
            case NETHER_WART_BLOCK: 
            case WARPED_WART_BLOCK: 
            case OAK_SIGN: 
            case SPRUCE_SIGN: 
            case BIRCH_SIGN: 
            case JUNGLE_SIGN: 
            case ACACIA_SIGN: 
            case CHERRY_SIGN: 
            case DARK_OAK_SIGN: 
            case MANGROVE_SIGN: 
            case BAMBOO_SIGN: 
            case CRIMSON_SIGN: 
            case WARPED_SIGN: 
            case OAK_HANGING_SIGN: 
            case SPRUCE_HANGING_SIGN: 
            case BIRCH_HANGING_SIGN: 
            case JUNGLE_HANGING_SIGN: 
            case ACACIA_HANGING_SIGN: 
            case CHERRY_HANGING_SIGN: 
            case DARK_OAK_HANGING_SIGN: 
            case MANGROVE_HANGING_SIGN: 
            case BAMBOO_HANGING_SIGN: 
            case CRIMSON_HANGING_SIGN: 
            case WARPED_HANGING_SIGN: 
            case SKELETON_SKULL: 
            case WITHER_SKELETON_SKULL: 
            case PLAYER_HEAD: 
            case ZOMBIE_HEAD: 
            case CREEPER_HEAD: 
            case DRAGON_HEAD: 
            case PIGLIN_HEAD: 
            case WHITE_BANNER: 
            case ORANGE_BANNER: 
            case MAGENTA_BANNER: 
            case LIGHT_BLUE_BANNER: 
            case YELLOW_BANNER: 
            case LIME_BANNER: 
            case PINK_BANNER: 
            case GRAY_BANNER: 
            case LIGHT_GRAY_BANNER: 
            case CYAN_BANNER: 
            case PURPLE_BANNER: 
            case BLUE_BANNER: 
            case BROWN_BANNER: 
            case GREEN_BANNER: 
            case RED_BANNER: 
            case BLACK_BANNER: 
            case SHROOMLIGHT: 
            case OAK_WALL_SIGN: 
            case SPRUCE_WALL_SIGN: 
            case BIRCH_WALL_SIGN: 
            case ACACIA_WALL_SIGN: 
            case CHERRY_WALL_SIGN: 
            case JUNGLE_WALL_SIGN: 
            case DARK_OAK_WALL_SIGN: 
            case MANGROVE_WALL_SIGN: 
            case BAMBOO_WALL_SIGN: 
            case OAK_WALL_HANGING_SIGN: 
            case SPRUCE_WALL_HANGING_SIGN: 
            case BIRCH_WALL_HANGING_SIGN: 
            case ACACIA_WALL_HANGING_SIGN: 
            case CHERRY_WALL_HANGING_SIGN: 
            case JUNGLE_WALL_HANGING_SIGN: 
            case DARK_OAK_WALL_HANGING_SIGN: 
            case MANGROVE_WALL_HANGING_SIGN: 
            case CRIMSON_WALL_HANGING_SIGN: 
            case WARPED_WALL_HANGING_SIGN: 
            case BAMBOO_WALL_HANGING_SIGN: 
            case SKELETON_WALL_SKULL: 
            case WITHER_SKELETON_WALL_SKULL: 
            case ZOMBIE_WALL_HEAD: 
            case PLAYER_WALL_HEAD: 
            case CREEPER_WALL_HEAD: 
            case DRAGON_WALL_HEAD: 
            case PIGLIN_WALL_HEAD: 
            case WHITE_WALL_BANNER: 
            case ORANGE_WALL_BANNER: 
            case MAGENTA_WALL_BANNER: 
            case LIGHT_BLUE_WALL_BANNER: 
            case YELLOW_WALL_BANNER: 
            case LIME_WALL_BANNER: 
            case PINK_WALL_BANNER: 
            case GRAY_WALL_BANNER: 
            case LIGHT_GRAY_WALL_BANNER: 
            case CYAN_WALL_BANNER: 
            case PURPLE_WALL_BANNER: 
            case BLUE_WALL_BANNER: 
            case BROWN_WALL_BANNER: 
            case GREEN_WALL_BANNER: 
            case RED_WALL_BANNER: 
            case BLACK_WALL_BANNER: 
            case BAMBOO_SAPLING: 
            case CRIMSON_WALL_SIGN: 
            case WARPED_WALL_SIGN: {
                return 1.0f;
            }
            case BASALT: 
            case POLISHED_BASALT: 
            case SMOOTH_BASALT: 
            case WHITE_TERRACOTTA: 
            case ORANGE_TERRACOTTA: 
            case MAGENTA_TERRACOTTA: 
            case LIGHT_BLUE_TERRACOTTA: 
            case YELLOW_TERRACOTTA: 
            case LIME_TERRACOTTA: 
            case PINK_TERRACOTTA: 
            case GRAY_TERRACOTTA: 
            case LIGHT_GRAY_TERRACOTTA: 
            case CYAN_TERRACOTTA: 
            case PURPLE_TERRACOTTA: 
            case BLUE_TERRACOTTA: 
            case BROWN_TERRACOTTA: 
            case GREEN_TERRACOTTA: 
            case RED_TERRACOTTA: 
            case BLACK_TERRACOTTA: 
            case TERRACOTTA: {
                return 1.25f;
            }
            case WHITE_GLAZED_TERRACOTTA: 
            case ORANGE_GLAZED_TERRACOTTA: 
            case MAGENTA_GLAZED_TERRACOTTA: 
            case LIGHT_BLUE_GLAZED_TERRACOTTA: 
            case YELLOW_GLAZED_TERRACOTTA: 
            case LIME_GLAZED_TERRACOTTA: 
            case PINK_GLAZED_TERRACOTTA: 
            case GRAY_GLAZED_TERRACOTTA: 
            case LIGHT_GRAY_GLAZED_TERRACOTTA: 
            case CYAN_GLAZED_TERRACOTTA: 
            case PURPLE_GLAZED_TERRACOTTA: 
            case BLUE_GLAZED_TERRACOTTA: 
            case BROWN_GLAZED_TERRACOTTA: 
            case GREEN_GLAZED_TERRACOTTA: 
            case RED_GLAZED_TERRACOTTA: 
            case BLACK_GLAZED_TERRACOTTA: {
                return 1.4f;
            }
            case STONE: 
            case GRANITE: 
            case POLISHED_GRANITE: 
            case DIORITE: 
            case POLISHED_DIORITE: 
            case ANDESITE: 
            case POLISHED_ANDESITE: 
            case TUFF: 
            case DRIPSTONE_BLOCK: 
            case AMETHYST_BLOCK: 
            case BUDDING_AMETHYST: 
            case MUD_BRICK_SLAB: 
            case PRISMARINE_SLAB: 
            case PRISMARINE_BRICK_SLAB: 
            case DARK_PRISMARINE_SLAB: 
            case BOOKSHELF: 
            case CHISELED_BOOKSHELF: 
            case PURPUR_BLOCK: 
            case PURPUR_PILLAR: 
            case PURPUR_STAIRS: 
            case INFESTED_DEEPSLATE: 
            case STONE_BRICKS: 
            case MOSSY_STONE_BRICKS: 
            case CRACKED_STONE_BRICKS: 
            case CHISELED_STONE_BRICKS: 
            case MUD_BRICKS: 
            case STONE_BRICK_STAIRS: 
            case MUD_BRICK_STAIRS: 
            case PRISMARINE_WALL: 
            case MOSSY_STONE_BRICK_WALL: 
            case GRANITE_WALL: 
            case STONE_BRICK_WALL: 
            case MUD_BRICK_WALL: 
            case ANDESITE_WALL: 
            case DIORITE_WALL: 
            case BLACKSTONE_WALL: 
            case POLISHED_BLACKSTONE_BRICK_WALL: 
            case PRISMARINE: 
            case PRISMARINE_BRICKS: 
            case DARK_PRISMARINE: 
            case PRISMARINE_STAIRS: 
            case PRISMARINE_BRICK_STAIRS: 
            case DARK_PRISMARINE_STAIRS: 
            case DEAD_TUBE_CORAL_BLOCK: 
            case DEAD_BRAIN_CORAL_BLOCK: 
            case DEAD_BUBBLE_CORAL_BLOCK: 
            case DEAD_FIRE_CORAL_BLOCK: 
            case DEAD_HORN_CORAL_BLOCK: 
            case TUBE_CORAL_BLOCK: 
            case BRAIN_CORAL_BLOCK: 
            case BUBBLE_CORAL_BLOCK: 
            case FIRE_CORAL_BLOCK: 
            case HORN_CORAL_BLOCK: 
            case POLISHED_GRANITE_STAIRS: 
            case MOSSY_STONE_BRICK_STAIRS: 
            case POLISHED_DIORITE_STAIRS: 
            case STONE_STAIRS: 
            case GRANITE_STAIRS: 
            case ANDESITE_STAIRS: 
            case POLISHED_ANDESITE_STAIRS: 
            case DIORITE_STAIRS: 
            case POLISHED_GRANITE_SLAB: 
            case MOSSY_STONE_BRICK_SLAB: 
            case POLISHED_DIORITE_SLAB: 
            case GRANITE_SLAB: 
            case ANDESITE_SLAB: 
            case POLISHED_ANDESITE_SLAB: 
            case DIORITE_SLAB: 
            case PISTON: 
            case STICKY_PISTON: 
            case SCULK_SENSOR: 
            case CALIBRATED_SCULK_SENSOR: 
            case BLACKSTONE: 
            case BLACKSTONE_STAIRS: 
            case GILDED_BLACKSTONE: 
            case CHISELED_POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_BRICKS: 
            case POLISHED_BLACKSTONE_BRICK_STAIRS: 
            case CRACKED_POLISHED_BLACKSTONE_BRICKS: 
            case SMALL_AMETHYST_BUD: 
            case MEDIUM_AMETHYST_BUD: 
            case LARGE_AMETHYST_BUD: 
            case AMETHYST_CLUSTER: 
            case POINTED_DRIPSTONE: 
            case PISTON_HEAD: {
                return 1.5f;
            }
            case WHITE_CONCRETE: 
            case ORANGE_CONCRETE: 
            case MAGENTA_CONCRETE: 
            case LIGHT_BLUE_CONCRETE: 
            case YELLOW_CONCRETE: 
            case LIME_CONCRETE: 
            case PINK_CONCRETE: 
            case GRAY_CONCRETE: 
            case LIGHT_GRAY_CONCRETE: 
            case CYAN_CONCRETE: 
            case PURPLE_CONCRETE: 
            case BLUE_CONCRETE: 
            case BROWN_CONCRETE: 
            case GREEN_CONCRETE: 
            case RED_CONCRETE: 
            case BLACK_CONCRETE: {
                return 1.8f;
            }
            case COBBLESTONE: 
            case OAK_PLANKS: 
            case SPRUCE_PLANKS: 
            case BIRCH_PLANKS: 
            case JUNGLE_PLANKS: 
            case ACACIA_PLANKS: 
            case CHERRY_PLANKS: 
            case DARK_OAK_PLANKS: 
            case MANGROVE_PLANKS: 
            case BAMBOO_PLANKS: 
            case CRIMSON_PLANKS: 
            case WARPED_PLANKS: 
            case BAMBOO_MOSAIC: 
            case OAK_LOG: 
            case SPRUCE_LOG: 
            case BIRCH_LOG: 
            case JUNGLE_LOG: 
            case ACACIA_LOG: 
            case CHERRY_LOG: 
            case DARK_OAK_LOG: 
            case MANGROVE_LOG: 
            case CRIMSON_STEM: 
            case WARPED_STEM: 
            case BAMBOO_BLOCK: 
            case STRIPPED_OAK_LOG: 
            case STRIPPED_SPRUCE_LOG: 
            case STRIPPED_BIRCH_LOG: 
            case STRIPPED_JUNGLE_LOG: 
            case STRIPPED_ACACIA_LOG: 
            case STRIPPED_CHERRY_LOG: 
            case STRIPPED_DARK_OAK_LOG: 
            case STRIPPED_MANGROVE_LOG: 
            case STRIPPED_CRIMSON_STEM: 
            case STRIPPED_WARPED_STEM: 
            case STRIPPED_OAK_WOOD: 
            case STRIPPED_SPRUCE_WOOD: 
            case STRIPPED_BIRCH_WOOD: 
            case STRIPPED_JUNGLE_WOOD: 
            case STRIPPED_ACACIA_WOOD: 
            case STRIPPED_CHERRY_WOOD: 
            case STRIPPED_DARK_OAK_WOOD: 
            case STRIPPED_MANGROVE_WOOD: 
            case STRIPPED_CRIMSON_HYPHAE: 
            case STRIPPED_WARPED_HYPHAE: 
            case STRIPPED_BAMBOO_BLOCK: 
            case OAK_WOOD: 
            case SPRUCE_WOOD: 
            case BIRCH_WOOD: 
            case JUNGLE_WOOD: 
            case ACACIA_WOOD: 
            case CHERRY_WOOD: 
            case DARK_OAK_WOOD: 
            case MANGROVE_WOOD: 
            case CRIMSON_HYPHAE: 
            case WARPED_HYPHAE: 
            case OAK_SLAB: 
            case SPRUCE_SLAB: 
            case BIRCH_SLAB: 
            case JUNGLE_SLAB: 
            case ACACIA_SLAB: 
            case CHERRY_SLAB: 
            case DARK_OAK_SLAB: 
            case MANGROVE_SLAB: 
            case BAMBOO_SLAB: 
            case BAMBOO_MOSAIC_SLAB: 
            case CRIMSON_SLAB: 
            case WARPED_SLAB: 
            case STONE_SLAB: 
            case SMOOTH_STONE_SLAB: 
            case SANDSTONE_SLAB: 
            case CUT_SANDSTONE_SLAB: 
            case PETRIFIED_OAK_SLAB: 
            case COBBLESTONE_SLAB: 
            case BRICK_SLAB: 
            case STONE_BRICK_SLAB: 
            case NETHER_BRICK_SLAB: 
            case QUARTZ_SLAB: 
            case RED_SANDSTONE_SLAB: 
            case CUT_RED_SANDSTONE_SLAB: 
            case PURPUR_SLAB: 
            case SMOOTH_QUARTZ: 
            case SMOOTH_RED_SANDSTONE: 
            case SMOOTH_SANDSTONE: 
            case SMOOTH_STONE: 
            case BRICKS: 
            case MOSSY_COBBLESTONE: 
            case COBBLESTONE_STAIRS: 
            case JUKEBOX: 
            case OAK_FENCE: 
            case SPRUCE_FENCE: 
            case BIRCH_FENCE: 
            case JUNGLE_FENCE: 
            case ACACIA_FENCE: 
            case CHERRY_FENCE: 
            case DARK_OAK_FENCE: 
            case MANGROVE_FENCE: 
            case BAMBOO_FENCE: 
            case CRIMSON_FENCE: 
            case WARPED_FENCE: 
            case BRICK_STAIRS: 
            case NETHER_BRICKS: 
            case CRACKED_NETHER_BRICKS: 
            case CHISELED_NETHER_BRICKS: 
            case NETHER_BRICK_FENCE: 
            case NETHER_BRICK_STAIRS: 
            case OAK_STAIRS: 
            case SPRUCE_STAIRS: 
            case BIRCH_STAIRS: 
            case JUNGLE_STAIRS: 
            case ACACIA_STAIRS: 
            case CHERRY_STAIRS: 
            case DARK_OAK_STAIRS: 
            case MANGROVE_STAIRS: 
            case BAMBOO_STAIRS: 
            case BAMBOO_MOSAIC_STAIRS: 
            case CRIMSON_STAIRS: 
            case WARPED_STAIRS: 
            case COBBLESTONE_WALL: 
            case MOSSY_COBBLESTONE_WALL: 
            case BRICK_WALL: 
            case NETHER_BRICK_WALL: 
            case RED_NETHER_BRICK_WALL: 
            case POLISHED_BLACKSTONE_WALL: 
            case RED_NETHER_BRICKS: 
            case BONE_BLOCK: 
            case SHULKER_BOX: 
            case WHITE_SHULKER_BOX: 
            case ORANGE_SHULKER_BOX: 
            case MAGENTA_SHULKER_BOX: 
            case LIGHT_BLUE_SHULKER_BOX: 
            case YELLOW_SHULKER_BOX: 
            case LIME_SHULKER_BOX: 
            case PINK_SHULKER_BOX: 
            case GRAY_SHULKER_BOX: 
            case LIGHT_GRAY_SHULKER_BOX: 
            case CYAN_SHULKER_BOX: 
            case PURPLE_SHULKER_BOX: 
            case BLUE_SHULKER_BOX: 
            case BROWN_SHULKER_BOX: 
            case GREEN_SHULKER_BOX: 
            case RED_SHULKER_BOX: 
            case BLACK_SHULKER_BOX: 
            case SMOOTH_RED_SANDSTONE_STAIRS: 
            case MOSSY_COBBLESTONE_STAIRS: 
            case SMOOTH_SANDSTONE_STAIRS: 
            case SMOOTH_QUARTZ_STAIRS: 
            case RED_NETHER_BRICK_STAIRS: 
            case SMOOTH_RED_SANDSTONE_SLAB: 
            case MOSSY_COBBLESTONE_SLAB: 
            case SMOOTH_SANDSTONE_SLAB: 
            case SMOOTH_QUARTZ_SLAB: 
            case RED_NETHER_BRICK_SLAB: 
            case OAK_FENCE_GATE: 
            case SPRUCE_FENCE_GATE: 
            case BIRCH_FENCE_GATE: 
            case JUNGLE_FENCE_GATE: 
            case ACACIA_FENCE_GATE: 
            case CHERRY_FENCE_GATE: 
            case DARK_OAK_FENCE_GATE: 
            case MANGROVE_FENCE_GATE: 
            case BAMBOO_FENCE_GATE: 
            case CRIMSON_FENCE_GATE: 
            case WARPED_FENCE_GATE: 
            case CAULDRON: 
            case GRINDSTONE: 
            case CAMPFIRE: 
            case SOUL_CAMPFIRE: 
            case BLACKSTONE_SLAB: 
            case POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_SLAB: 
            case POLISHED_BLACKSTONE_STAIRS: 
            case POLISHED_BLACKSTONE_BRICK_SLAB: 
            case WATER_CAULDRON: 
            case LAVA_CAULDRON: 
            case POWDER_SNOW_CAULDRON: {
                return 2.0f;
            }
            case CHEST: 
            case CRAFTING_TABLE: 
            case LECTERN: 
            case TRAPPED_CHEST: 
            case LOOM: 
            case BARREL: 
            case CARTOGRAPHY_TABLE: 
            case FLETCHING_TABLE: 
            case SMITHING_TABLE: {
                return 2.5f;
            }
            case BLUE_ICE: {
                return 2.8f;
            }
            case DEEPSLATE: 
            case COAL_ORE: 
            case IRON_ORE: 
            case COPPER_ORE: 
            case GOLD_ORE: 
            case REDSTONE_ORE: 
            case EMERALD_ORE: 
            case LAPIS_ORE: 
            case DIAMOND_ORE: 
            case NETHER_GOLD_ORE: 
            case NETHER_QUARTZ_ORE: 
            case COPPER_BLOCK: 
            case GOLD_BLOCK: 
            case EXPOSED_COPPER: 
            case WEATHERED_COPPER: 
            case OXIDIZED_COPPER: 
            case CUT_COPPER: 
            case EXPOSED_CUT_COPPER: 
            case WEATHERED_CUT_COPPER: 
            case OXIDIZED_CUT_COPPER: 
            case CUT_COPPER_STAIRS: 
            case EXPOSED_CUT_COPPER_STAIRS: 
            case WEATHERED_CUT_COPPER_STAIRS: 
            case OXIDIZED_CUT_COPPER_STAIRS: 
            case CUT_COPPER_SLAB: 
            case EXPOSED_CUT_COPPER_SLAB: 
            case WEATHERED_CUT_COPPER_SLAB: 
            case OXIDIZED_CUT_COPPER_SLAB: 
            case WAXED_COPPER_BLOCK: 
            case WAXED_EXPOSED_COPPER: 
            case WAXED_WEATHERED_COPPER: 
            case WAXED_OXIDIZED_COPPER: 
            case WAXED_CUT_COPPER: 
            case WAXED_EXPOSED_CUT_COPPER: 
            case WAXED_WEATHERED_CUT_COPPER: 
            case WAXED_OXIDIZED_CUT_COPPER: 
            case WAXED_CUT_COPPER_STAIRS: 
            case WAXED_EXPOSED_CUT_COPPER_STAIRS: 
            case WAXED_WEATHERED_CUT_COPPER_STAIRS: 
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS: 
            case WAXED_CUT_COPPER_SLAB: 
            case WAXED_EXPOSED_CUT_COPPER_SLAB: 
            case WAXED_WEATHERED_CUT_COPPER_SLAB: 
            case WAXED_OXIDIZED_CUT_COPPER_SLAB: 
            case LAPIS_BLOCK: 
            case SCULK_CATALYST: 
            case SCULK_SHRIEKER: 
            case END_STONE: 
            case END_STONE_BRICKS: 
            case DRAGON_EGG: 
            case BEACON: 
            case END_STONE_BRICK_WALL: 
            case CONDUIT: 
            case END_STONE_BRICK_STAIRS: 
            case END_STONE_BRICK_SLAB: 
            case OBSERVER: 
            case HOPPER: 
            case LIGHTNING_ROD: 
            case OAK_DOOR: 
            case SPRUCE_DOOR: 
            case BIRCH_DOOR: 
            case JUNGLE_DOOR: 
            case ACACIA_DOOR: 
            case CHERRY_DOOR: 
            case DARK_OAK_DOOR: 
            case MANGROVE_DOOR: 
            case BAMBOO_DOOR: 
            case CRIMSON_DOOR: 
            case WARPED_DOOR: 
            case OAK_TRAPDOOR: 
            case SPRUCE_TRAPDOOR: 
            case BIRCH_TRAPDOOR: 
            case JUNGLE_TRAPDOOR: 
            case ACACIA_TRAPDOOR: 
            case CHERRY_TRAPDOOR: 
            case DARK_OAK_TRAPDOOR: 
            case MANGROVE_TRAPDOOR: 
            case BAMBOO_TRAPDOOR: 
            case CRIMSON_TRAPDOOR: 
            case WARPED_TRAPDOOR: {
                return 3.0f;
            }
            case COBBLED_DEEPSLATE: 
            case POLISHED_DEEPSLATE: 
            case FURNACE: 
            case DEEPSLATE_BRICKS: 
            case CRACKED_DEEPSLATE_BRICKS: 
            case DEEPSLATE_TILES: 
            case CRACKED_DEEPSLATE_TILES: 
            case CHISELED_DEEPSLATE: 
            case COBBLED_DEEPSLATE_WALL: 
            case POLISHED_DEEPSLATE_WALL: 
            case DEEPSLATE_BRICK_WALL: 
            case DEEPSLATE_TILE_WALL: 
            case COBBLED_DEEPSLATE_STAIRS: 
            case POLISHED_DEEPSLATE_STAIRS: 
            case DEEPSLATE_BRICK_STAIRS: 
            case DEEPSLATE_TILE_STAIRS: 
            case COBBLED_DEEPSLATE_SLAB: 
            case POLISHED_DEEPSLATE_SLAB: 
            case DEEPSLATE_BRICK_SLAB: 
            case DEEPSLATE_TILE_SLAB: 
            case DISPENSER: 
            case DROPPER: 
            case SMOKER: 
            case BLAST_FURNACE: 
            case STONECUTTER: 
            case LANTERN: 
            case SOUL_LANTERN: 
            case LODESTONE: {
                return 3.5f;
            }
            case COBWEB: {
                return 4.0f;
            }
            case DEEPSLATE_COAL_ORE: 
            case DEEPSLATE_IRON_ORE: 
            case DEEPSLATE_COPPER_ORE: 
            case DEEPSLATE_GOLD_ORE: 
            case DEEPSLATE_REDSTONE_ORE: 
            case DEEPSLATE_EMERALD_ORE: 
            case DEEPSLATE_LAPIS_ORE: 
            case DEEPSLATE_DIAMOND_ORE: {
                return 4.5f;
            }
            case COAL_BLOCK: 
            case RAW_IRON_BLOCK: 
            case RAW_COPPER_BLOCK: 
            case RAW_GOLD_BLOCK: 
            case IRON_BLOCK: 
            case DIAMOND_BLOCK: 
            case SPAWNER: 
            case IRON_BARS: 
            case CHAIN: 
            case ENCHANTING_TABLE: 
            case EMERALD_BLOCK: 
            case ANVIL: 
            case CHIPPED_ANVIL: 
            case DAMAGED_ANVIL: 
            case REDSTONE_BLOCK: 
            case IRON_DOOR: 
            case IRON_TRAPDOOR: 
            case BELL: {
                return 5.0f;
            }
            case ENDER_CHEST: {
                return 22.5f;
            }
            case ANCIENT_DEBRIS: {
                return 30.0f;
            }
            case NETHERITE_BLOCK: 
            case OBSIDIAN: 
            case CRYING_OBSIDIAN: 
            case RESPAWN_ANCHOR: {
                return 50.0f;
            }
            case REINFORCED_DEEPSLATE: {
                return 55.0f;
            }
            case WATER: 
            case LAVA: {
                return 100.0f;
            }
        }
        return 0.0f;
    }

    public float getBlastResistance() {
        Preconditions.checkArgument((boolean)this.isBlock(), (Object)"The Material is not a block!");
        switch (this) {
            case MOSS_CARPET: 
            case MOSS_BLOCK: 
            case BIG_DRIPLEAF: 
            case SNOW: 
            case WHITE_CARPET: 
            case ORANGE_CARPET: 
            case MAGENTA_CARPET: 
            case LIGHT_BLUE_CARPET: 
            case YELLOW_CARPET: 
            case LIME_CARPET: 
            case PINK_CARPET: 
            case GRAY_CARPET: 
            case LIGHT_GRAY_CARPET: 
            case CYAN_CARPET: 
            case PURPLE_CARPET: 
            case BLUE_CARPET: 
            case BROWN_CARPET: 
            case GREEN_CARPET: 
            case RED_CARPET: 
            case BLACK_CARPET: 
            case CANDLE: 
            case WHITE_CANDLE: 
            case ORANGE_CANDLE: 
            case MAGENTA_CANDLE: 
            case LIGHT_BLUE_CANDLE: 
            case YELLOW_CANDLE: 
            case LIME_CANDLE: 
            case PINK_CANDLE: 
            case GRAY_CANDLE: 
            case LIGHT_GRAY_CANDLE: 
            case CYAN_CANDLE: 
            case PURPLE_CANDLE: 
            case BLUE_CANDLE: 
            case BROWN_CANDLE: 
            case GREEN_CANDLE: 
            case RED_CANDLE: 
            case BLACK_CANDLE: 
            case BIG_DRIPLEAF_STEM: {
                return 0.1f;
            }
            case OAK_LEAVES: 
            case SPRUCE_LEAVES: 
            case BIRCH_LEAVES: 
            case JUNGLE_LEAVES: 
            case ACACIA_LEAVES: 
            case CHERRY_LEAVES: 
            case DARK_OAK_LEAVES: 
            case MANGROVE_LEAVES: 
            case AZALEA_LEAVES: 
            case FLOWERING_AZALEA_LEAVES: 
            case SNOW_BLOCK: 
            case BROWN_MUSHROOM_BLOCK: 
            case RED_MUSHROOM_BLOCK: 
            case MUSHROOM_STEM: 
            case VINE: 
            case GLOW_LICHEN: 
            case SCULK: 
            case SCULK_VEIN: 
            case DAYLIGHT_DETECTOR: 
            case WHITE_BED: 
            case ORANGE_BED: 
            case MAGENTA_BED: 
            case LIGHT_BLUE_BED: 
            case YELLOW_BED: 
            case LIME_BED: 
            case PINK_BED: 
            case GRAY_BED: 
            case LIGHT_GRAY_BED: 
            case CYAN_BED: 
            case PURPLE_BED: 
            case BLUE_BED: 
            case BROWN_BED: 
            case GREEN_BED: 
            case RED_BED: 
            case BLACK_BED: {
                return 0.2f;
            }
            case SUSPICIOUS_SAND: 
            case SUSPICIOUS_GRAVEL: 
            case POWDER_SNOW: {
                return 0.25f;
            }
            case GLASS: 
            case TINTED_GLASS: 
            case GLOWSTONE: 
            case GLASS_PANE: 
            case WHITE_STAINED_GLASS: 
            case ORANGE_STAINED_GLASS: 
            case MAGENTA_STAINED_GLASS: 
            case LIGHT_BLUE_STAINED_GLASS: 
            case YELLOW_STAINED_GLASS: 
            case LIME_STAINED_GLASS: 
            case PINK_STAINED_GLASS: 
            case GRAY_STAINED_GLASS: 
            case LIGHT_GRAY_STAINED_GLASS: 
            case CYAN_STAINED_GLASS: 
            case PURPLE_STAINED_GLASS: 
            case BLUE_STAINED_GLASS: 
            case BROWN_STAINED_GLASS: 
            case GREEN_STAINED_GLASS: 
            case RED_STAINED_GLASS: 
            case BLACK_STAINED_GLASS: 
            case WHITE_STAINED_GLASS_PANE: 
            case ORANGE_STAINED_GLASS_PANE: 
            case MAGENTA_STAINED_GLASS_PANE: 
            case LIGHT_BLUE_STAINED_GLASS_PANE: 
            case YELLOW_STAINED_GLASS_PANE: 
            case LIME_STAINED_GLASS_PANE: 
            case PINK_STAINED_GLASS_PANE: 
            case GRAY_STAINED_GLASS_PANE: 
            case LIGHT_GRAY_STAINED_GLASS_PANE: 
            case CYAN_STAINED_GLASS_PANE: 
            case PURPLE_STAINED_GLASS_PANE: 
            case BLUE_STAINED_GLASS_PANE: 
            case BROWN_STAINED_GLASS_PANE: 
            case GREEN_STAINED_GLASS_PANE: 
            case RED_STAINED_GLASS_PANE: 
            case BLACK_STAINED_GLASS_PANE: 
            case SEA_LANTERN: 
            case REDSTONE_LAMP: 
            case BEE_NEST: 
            case OCHRE_FROGLIGHT: 
            case VERDANT_FROGLIGHT: 
            case PEARLESCENT_FROGLIGHT: {
                return 0.3f;
            }
            case CRIMSON_NYLIUM: 
            case WARPED_NYLIUM: 
            case CHORUS_PLANT: 
            case CHORUS_FLOWER: 
            case LADDER: 
            case CACTUS: 
            case NETHERRACK: {
                return 0.4f;
            }
            case DIRT: 
            case COARSE_DIRT: 
            case PODZOL: 
            case ROOTED_DIRT: 
            case MUD: 
            case SAND: 
            case RED_SAND: 
            case ICE: 
            case SOUL_SAND: 
            case SOUL_SOIL: 
            case HAY_BLOCK: 
            case PACKED_ICE: 
            case MAGMA_BLOCK: 
            case WHITE_CONCRETE_POWDER: 
            case ORANGE_CONCRETE_POWDER: 
            case MAGENTA_CONCRETE_POWDER: 
            case LIGHT_BLUE_CONCRETE_POWDER: 
            case YELLOW_CONCRETE_POWDER: 
            case LIME_CONCRETE_POWDER: 
            case PINK_CONCRETE_POWDER: 
            case GRAY_CONCRETE_POWDER: 
            case LIGHT_GRAY_CONCRETE_POWDER: 
            case CYAN_CONCRETE_POWDER: 
            case PURPLE_CONCRETE_POWDER: 
            case BLUE_CONCRETE_POWDER: 
            case BROWN_CONCRETE_POWDER: 
            case GREEN_CONCRETE_POWDER: 
            case RED_CONCRETE_POWDER: 
            case BLACK_CONCRETE_POWDER: 
            case TURTLE_EGG: 
            case SNIFFER_EGG: 
            case TARGET: 
            case LEVER: 
            case STONE_BUTTON: 
            case POLISHED_BLACKSTONE_BUTTON: 
            case OAK_BUTTON: 
            case SPRUCE_BUTTON: 
            case BIRCH_BUTTON: 
            case JUNGLE_BUTTON: 
            case ACACIA_BUTTON: 
            case CHERRY_BUTTON: 
            case DARK_OAK_BUTTON: 
            case MANGROVE_BUTTON: 
            case BAMBOO_BUTTON: 
            case CRIMSON_BUTTON: 
            case WARPED_BUTTON: 
            case STONE_PRESSURE_PLATE: 
            case POLISHED_BLACKSTONE_PRESSURE_PLATE: 
            case LIGHT_WEIGHTED_PRESSURE_PLATE: 
            case HEAVY_WEIGHTED_PRESSURE_PLATE: 
            case OAK_PRESSURE_PLATE: 
            case SPRUCE_PRESSURE_PLATE: 
            case BIRCH_PRESSURE_PLATE: 
            case JUNGLE_PRESSURE_PLATE: 
            case ACACIA_PRESSURE_PLATE: 
            case CHERRY_PRESSURE_PLATE: 
            case DARK_OAK_PRESSURE_PLATE: 
            case MANGROVE_PRESSURE_PLATE: 
            case BAMBOO_PRESSURE_PLATE: 
            case CRIMSON_PRESSURE_PLATE: 
            case WARPED_PRESSURE_PLATE: 
            case CAKE: 
            case BREWING_STAND: 
            case FROSTED_ICE: 
            case CANDLE_CAKE: 
            case WHITE_CANDLE_CAKE: 
            case ORANGE_CANDLE_CAKE: 
            case MAGENTA_CANDLE_CAKE: 
            case LIGHT_BLUE_CANDLE_CAKE: 
            case YELLOW_CANDLE_CAKE: 
            case LIME_CANDLE_CAKE: 
            case PINK_CANDLE_CAKE: 
            case GRAY_CANDLE_CAKE: 
            case LIGHT_GRAY_CANDLE_CAKE: 
            case CYAN_CANDLE_CAKE: 
            case PURPLE_CANDLE_CAKE: 
            case BLUE_CANDLE_CAKE: 
            case BROWN_CANDLE_CAKE: 
            case GREEN_CANDLE_CAKE: 
            case RED_CANDLE_CAKE: 
            case BLACK_CANDLE_CAKE: {
                return 0.5f;
            }
            case GRASS_BLOCK: 
            case GRAVEL: 
            case SPONGE: 
            case WET_SPONGE: 
            case FARMLAND: 
            case CLAY: 
            case MYCELIUM: 
            case COMPOSTER: 
            case BEEHIVE: 
            case HONEYCOMB_BLOCK: {
                return 0.6f;
            }
            case DIRT_PATH: {
                return 0.65f;
            }
            case MANGROVE_ROOTS: 
            case MUDDY_MANGROVE_ROOTS: 
            case POWERED_RAIL: 
            case DETECTOR_RAIL: 
            case RAIL: 
            case ACTIVATOR_RAIL: {
                return 0.7f;
            }
            case CALCITE: 
            case INFESTED_STONE: 
            case INFESTED_COBBLESTONE: 
            case INFESTED_STONE_BRICKS: 
            case INFESTED_MOSSY_STONE_BRICKS: 
            case INFESTED_CRACKED_STONE_BRICKS: 
            case INFESTED_CHISELED_STONE_BRICKS: 
            case INFESTED_DEEPSLATE: {
                return 0.75f;
            }
            case SANDSTONE: 
            case CHISELED_SANDSTONE: 
            case CUT_SANDSTONE: 
            case WHITE_WOOL: 
            case ORANGE_WOOL: 
            case MAGENTA_WOOL: 
            case LIGHT_BLUE_WOOL: 
            case YELLOW_WOOL: 
            case LIME_WOOL: 
            case PINK_WOOL: 
            case GRAY_WOOL: 
            case LIGHT_GRAY_WOOL: 
            case CYAN_WOOL: 
            case PURPLE_WOOL: 
            case BLUE_WOOL: 
            case BROWN_WOOL: 
            case GREEN_WOOL: 
            case RED_WOOL: 
            case BLACK_WOOL: 
            case SANDSTONE_STAIRS: 
            case RED_SANDSTONE_WALL: 
            case SANDSTONE_WALL: 
            case CHISELED_QUARTZ_BLOCK: 
            case QUARTZ_BLOCK: 
            case QUARTZ_BRICKS: 
            case QUARTZ_PILLAR: 
            case QUARTZ_STAIRS: 
            case RED_SANDSTONE: 
            case CHISELED_RED_SANDSTONE: 
            case CUT_RED_SANDSTONE: 
            case RED_SANDSTONE_STAIRS: 
            case NOTE_BLOCK: {
                return 0.8f;
            }
            case DRIPSTONE_BLOCK: 
            case BAMBOO: 
            case PUMPKIN: 
            case CARVED_PUMPKIN: 
            case JACK_O_LANTERN: 
            case MELON: 
            case NETHER_WART_BLOCK: 
            case WARPED_WART_BLOCK: 
            case OAK_SIGN: 
            case SPRUCE_SIGN: 
            case BIRCH_SIGN: 
            case JUNGLE_SIGN: 
            case ACACIA_SIGN: 
            case CHERRY_SIGN: 
            case DARK_OAK_SIGN: 
            case MANGROVE_SIGN: 
            case BAMBOO_SIGN: 
            case CRIMSON_SIGN: 
            case WARPED_SIGN: 
            case OAK_HANGING_SIGN: 
            case SPRUCE_HANGING_SIGN: 
            case BIRCH_HANGING_SIGN: 
            case JUNGLE_HANGING_SIGN: 
            case ACACIA_HANGING_SIGN: 
            case CHERRY_HANGING_SIGN: 
            case DARK_OAK_HANGING_SIGN: 
            case MANGROVE_HANGING_SIGN: 
            case BAMBOO_HANGING_SIGN: 
            case CRIMSON_HANGING_SIGN: 
            case WARPED_HANGING_SIGN: 
            case SKELETON_SKULL: 
            case WITHER_SKELETON_SKULL: 
            case PLAYER_HEAD: 
            case ZOMBIE_HEAD: 
            case CREEPER_HEAD: 
            case DRAGON_HEAD: 
            case PIGLIN_HEAD: 
            case WHITE_BANNER: 
            case ORANGE_BANNER: 
            case MAGENTA_BANNER: 
            case LIGHT_BLUE_BANNER: 
            case YELLOW_BANNER: 
            case LIME_BANNER: 
            case PINK_BANNER: 
            case GRAY_BANNER: 
            case LIGHT_GRAY_BANNER: 
            case CYAN_BANNER: 
            case PURPLE_BANNER: 
            case BLUE_BANNER: 
            case BROWN_BANNER: 
            case GREEN_BANNER: 
            case RED_BANNER: 
            case BLACK_BANNER: 
            case SHROOMLIGHT: 
            case OAK_WALL_SIGN: 
            case SPRUCE_WALL_SIGN: 
            case BIRCH_WALL_SIGN: 
            case ACACIA_WALL_SIGN: 
            case CHERRY_WALL_SIGN: 
            case JUNGLE_WALL_SIGN: 
            case DARK_OAK_WALL_SIGN: 
            case MANGROVE_WALL_SIGN: 
            case BAMBOO_WALL_SIGN: 
            case OAK_WALL_HANGING_SIGN: 
            case SPRUCE_WALL_HANGING_SIGN: 
            case BIRCH_WALL_HANGING_SIGN: 
            case ACACIA_WALL_HANGING_SIGN: 
            case CHERRY_WALL_HANGING_SIGN: 
            case JUNGLE_WALL_HANGING_SIGN: 
            case DARK_OAK_WALL_HANGING_SIGN: 
            case MANGROVE_WALL_HANGING_SIGN: 
            case CRIMSON_WALL_HANGING_SIGN: 
            case WARPED_WALL_HANGING_SIGN: 
            case BAMBOO_WALL_HANGING_SIGN: 
            case SKELETON_WALL_SKULL: 
            case WITHER_SKELETON_WALL_SKULL: 
            case ZOMBIE_WALL_HEAD: 
            case PLAYER_WALL_HEAD: 
            case CREEPER_WALL_HEAD: 
            case DRAGON_WALL_HEAD: 
            case PIGLIN_WALL_HEAD: 
            case WHITE_WALL_BANNER: 
            case ORANGE_WALL_BANNER: 
            case MAGENTA_WALL_BANNER: 
            case LIGHT_BLUE_WALL_BANNER: 
            case YELLOW_WALL_BANNER: 
            case LIME_WALL_BANNER: 
            case PINK_WALL_BANNER: 
            case GRAY_WALL_BANNER: 
            case LIGHT_GRAY_WALL_BANNER: 
            case CYAN_WALL_BANNER: 
            case PURPLE_WALL_BANNER: 
            case BLUE_WALL_BANNER: 
            case BROWN_WALL_BANNER: 
            case GREEN_WALL_BANNER: 
            case RED_WALL_BANNER: 
            case BLACK_WALL_BANNER: 
            case BAMBOO_SAPLING: 
            case CRIMSON_WALL_SIGN: 
            case WARPED_WALL_SIGN: {
                return 1.0f;
            }
            case WHITE_GLAZED_TERRACOTTA: 
            case ORANGE_GLAZED_TERRACOTTA: 
            case MAGENTA_GLAZED_TERRACOTTA: 
            case LIGHT_BLUE_GLAZED_TERRACOTTA: 
            case YELLOW_GLAZED_TERRACOTTA: 
            case LIME_GLAZED_TERRACOTTA: 
            case PINK_GLAZED_TERRACOTTA: 
            case GRAY_GLAZED_TERRACOTTA: 
            case LIGHT_GRAY_GLAZED_TERRACOTTA: 
            case CYAN_GLAZED_TERRACOTTA: 
            case PURPLE_GLAZED_TERRACOTTA: 
            case BLUE_GLAZED_TERRACOTTA: 
            case BROWN_GLAZED_TERRACOTTA: 
            case GREEN_GLAZED_TERRACOTTA: 
            case RED_GLAZED_TERRACOTTA: 
            case BLACK_GLAZED_TERRACOTTA: {
                return 1.4f;
            }
            case AMETHYST_BLOCK: 
            case BUDDING_AMETHYST: 
            case BOOKSHELF: 
            case CHISELED_BOOKSHELF: 
            case PISTON: 
            case STICKY_PISTON: 
            case SCULK_SENSOR: 
            case CALIBRATED_SCULK_SENSOR: 
            case SMALL_AMETHYST_BUD: 
            case MEDIUM_AMETHYST_BUD: 
            case LARGE_AMETHYST_BUD: 
            case AMETHYST_CLUSTER: 
            case PISTON_HEAD: {
                return 1.5f;
            }
            case WHITE_CONCRETE: 
            case ORANGE_CONCRETE: 
            case MAGENTA_CONCRETE: 
            case LIGHT_BLUE_CONCRETE: 
            case YELLOW_CONCRETE: 
            case LIME_CONCRETE: 
            case PINK_CONCRETE: 
            case GRAY_CONCRETE: 
            case LIGHT_GRAY_CONCRETE: 
            case CYAN_CONCRETE: 
            case PURPLE_CONCRETE: 
            case BLUE_CONCRETE: 
            case BROWN_CONCRETE: 
            case GREEN_CONCRETE: 
            case RED_CONCRETE: 
            case BLACK_CONCRETE: {
                return 1.8f;
            }
            case OAK_LOG: 
            case SPRUCE_LOG: 
            case BIRCH_LOG: 
            case JUNGLE_LOG: 
            case ACACIA_LOG: 
            case CHERRY_LOG: 
            case DARK_OAK_LOG: 
            case MANGROVE_LOG: 
            case CRIMSON_STEM: 
            case WARPED_STEM: 
            case BAMBOO_BLOCK: 
            case STRIPPED_OAK_LOG: 
            case STRIPPED_SPRUCE_LOG: 
            case STRIPPED_BIRCH_LOG: 
            case STRIPPED_JUNGLE_LOG: 
            case STRIPPED_ACACIA_LOG: 
            case STRIPPED_CHERRY_LOG: 
            case STRIPPED_DARK_OAK_LOG: 
            case STRIPPED_MANGROVE_LOG: 
            case STRIPPED_CRIMSON_STEM: 
            case STRIPPED_WARPED_STEM: 
            case STRIPPED_OAK_WOOD: 
            case STRIPPED_SPRUCE_WOOD: 
            case STRIPPED_BIRCH_WOOD: 
            case STRIPPED_JUNGLE_WOOD: 
            case STRIPPED_ACACIA_WOOD: 
            case STRIPPED_CHERRY_WOOD: 
            case STRIPPED_DARK_OAK_WOOD: 
            case STRIPPED_MANGROVE_WOOD: 
            case STRIPPED_CRIMSON_HYPHAE: 
            case STRIPPED_WARPED_HYPHAE: 
            case STRIPPED_BAMBOO_BLOCK: 
            case OAK_WOOD: 
            case SPRUCE_WOOD: 
            case BIRCH_WOOD: 
            case JUNGLE_WOOD: 
            case ACACIA_WOOD: 
            case CHERRY_WOOD: 
            case DARK_OAK_WOOD: 
            case MANGROVE_WOOD: 
            case CRIMSON_HYPHAE: 
            case WARPED_HYPHAE: 
            case BONE_BLOCK: 
            case SHULKER_BOX: 
            case WHITE_SHULKER_BOX: 
            case ORANGE_SHULKER_BOX: 
            case MAGENTA_SHULKER_BOX: 
            case LIGHT_BLUE_SHULKER_BOX: 
            case YELLOW_SHULKER_BOX: 
            case LIME_SHULKER_BOX: 
            case PINK_SHULKER_BOX: 
            case GRAY_SHULKER_BOX: 
            case LIGHT_GRAY_SHULKER_BOX: 
            case CYAN_SHULKER_BOX: 
            case PURPLE_SHULKER_BOX: 
            case BLUE_SHULKER_BOX: 
            case BROWN_SHULKER_BOX: 
            case GREEN_SHULKER_BOX: 
            case RED_SHULKER_BOX: 
            case BLACK_SHULKER_BOX: 
            case CAULDRON: 
            case CAMPFIRE: 
            case SOUL_CAMPFIRE: 
            case WATER_CAULDRON: 
            case LAVA_CAULDRON: 
            case POWDER_SNOW_CAULDRON: {
                return 2.0f;
            }
            case CHEST: 
            case CRAFTING_TABLE: 
            case LECTERN: 
            case TRAPPED_CHEST: 
            case DRIED_KELP_BLOCK: 
            case LOOM: 
            case BARREL: 
            case CARTOGRAPHY_TABLE: 
            case FLETCHING_TABLE: 
            case SMITHING_TABLE: {
                return 2.5f;
            }
            case BLUE_ICE: {
                return 2.8f;
            }
            case OAK_PLANKS: 
            case SPRUCE_PLANKS: 
            case BIRCH_PLANKS: 
            case JUNGLE_PLANKS: 
            case ACACIA_PLANKS: 
            case CHERRY_PLANKS: 
            case DARK_OAK_PLANKS: 
            case MANGROVE_PLANKS: 
            case BAMBOO_PLANKS: 
            case CRIMSON_PLANKS: 
            case WARPED_PLANKS: 
            case BAMBOO_MOSAIC: 
            case COAL_ORE: 
            case DEEPSLATE_COAL_ORE: 
            case IRON_ORE: 
            case DEEPSLATE_IRON_ORE: 
            case COPPER_ORE: 
            case DEEPSLATE_COPPER_ORE: 
            case GOLD_ORE: 
            case DEEPSLATE_GOLD_ORE: 
            case REDSTONE_ORE: 
            case DEEPSLATE_REDSTONE_ORE: 
            case EMERALD_ORE: 
            case DEEPSLATE_EMERALD_ORE: 
            case LAPIS_ORE: 
            case DEEPSLATE_LAPIS_ORE: 
            case DIAMOND_ORE: 
            case DEEPSLATE_DIAMOND_ORE: 
            case NETHER_GOLD_ORE: 
            case NETHER_QUARTZ_ORE: 
            case LAPIS_BLOCK: 
            case OAK_SLAB: 
            case SPRUCE_SLAB: 
            case BIRCH_SLAB: 
            case JUNGLE_SLAB: 
            case ACACIA_SLAB: 
            case CHERRY_SLAB: 
            case DARK_OAK_SLAB: 
            case MANGROVE_SLAB: 
            case BAMBOO_SLAB: 
            case BAMBOO_MOSAIC_SLAB: 
            case CRIMSON_SLAB: 
            case WARPED_SLAB: 
            case MUD_BRICK_SLAB: 
            case OAK_FENCE: 
            case SPRUCE_FENCE: 
            case BIRCH_FENCE: 
            case JUNGLE_FENCE: 
            case ACACIA_FENCE: 
            case CHERRY_FENCE: 
            case DARK_OAK_FENCE: 
            case MANGROVE_FENCE: 
            case BAMBOO_FENCE: 
            case CRIMSON_FENCE: 
            case WARPED_FENCE: 
            case PACKED_MUD: 
            case MUD_BRICKS: 
            case MUD_BRICK_STAIRS: 
            case SCULK_CATALYST: 
            case SCULK_SHRIEKER: 
            case OAK_STAIRS: 
            case SPRUCE_STAIRS: 
            case BIRCH_STAIRS: 
            case JUNGLE_STAIRS: 
            case ACACIA_STAIRS: 
            case CHERRY_STAIRS: 
            case DARK_OAK_STAIRS: 
            case MANGROVE_STAIRS: 
            case BAMBOO_STAIRS: 
            case BAMBOO_MOSAIC_STAIRS: 
            case CRIMSON_STAIRS: 
            case WARPED_STAIRS: 
            case BEACON: 
            case MUD_BRICK_WALL: 
            case CONDUIT: 
            case OBSERVER: 
            case OAK_DOOR: 
            case SPRUCE_DOOR: 
            case BIRCH_DOOR: 
            case JUNGLE_DOOR: 
            case ACACIA_DOOR: 
            case CHERRY_DOOR: 
            case DARK_OAK_DOOR: 
            case MANGROVE_DOOR: 
            case BAMBOO_DOOR: 
            case CRIMSON_DOOR: 
            case WARPED_DOOR: 
            case OAK_TRAPDOOR: 
            case SPRUCE_TRAPDOOR: 
            case BIRCH_TRAPDOOR: 
            case JUNGLE_TRAPDOOR: 
            case ACACIA_TRAPDOOR: 
            case CHERRY_TRAPDOOR: 
            case DARK_OAK_TRAPDOOR: 
            case MANGROVE_TRAPDOOR: 
            case BAMBOO_TRAPDOOR: 
            case CRIMSON_TRAPDOOR: 
            case WARPED_TRAPDOOR: 
            case OAK_FENCE_GATE: 
            case SPRUCE_FENCE_GATE: 
            case BIRCH_FENCE_GATE: 
            case JUNGLE_FENCE_GATE: 
            case ACACIA_FENCE_GATE: 
            case CHERRY_FENCE_GATE: 
            case DARK_OAK_FENCE_GATE: 
            case MANGROVE_FENCE_GATE: 
            case BAMBOO_FENCE_GATE: 
            case CRIMSON_FENCE_GATE: 
            case WARPED_FENCE_GATE: 
            case POINTED_DRIPSTONE: 
            case COCOA: {
                return 3.0f;
            }
            case FURNACE: 
            case DISPENSER: 
            case DROPPER: 
            case SMOKER: 
            case BLAST_FURNACE: 
            case STONECUTTER: 
            case LANTERN: 
            case SOUL_LANTERN: 
            case LODESTONE: {
                return 3.5f;
            }
            case COBWEB: {
                return 4.0f;
            }
            case BASALT: 
            case POLISHED_BASALT: 
            case SMOOTH_BASALT: 
            case WHITE_TERRACOTTA: 
            case ORANGE_TERRACOTTA: 
            case MAGENTA_TERRACOTTA: 
            case LIGHT_BLUE_TERRACOTTA: 
            case YELLOW_TERRACOTTA: 
            case LIME_TERRACOTTA: 
            case PINK_TERRACOTTA: 
            case GRAY_TERRACOTTA: 
            case LIGHT_GRAY_TERRACOTTA: 
            case CYAN_TERRACOTTA: 
            case PURPLE_TERRACOTTA: 
            case BLUE_TERRACOTTA: 
            case BROWN_TERRACOTTA: 
            case GREEN_TERRACOTTA: 
            case RED_TERRACOTTA: 
            case BLACK_TERRACOTTA: 
            case TERRACOTTA: {
                return 4.2f;
            }
            case HOPPER: {
                return 4.8f;
            }
            case SPAWNER: 
            case IRON_DOOR: 
            case IRON_TRAPDOOR: 
            case BELL: {
                return 5.0f;
            }
            case STONE: 
            case GRANITE: 
            case POLISHED_GRANITE: 
            case DIORITE: 
            case POLISHED_DIORITE: 
            case ANDESITE: 
            case POLISHED_ANDESITE: 
            case DEEPSLATE: 
            case COBBLED_DEEPSLATE: 
            case POLISHED_DEEPSLATE: 
            case TUFF: 
            case COBBLESTONE: 
            case COAL_BLOCK: 
            case RAW_IRON_BLOCK: 
            case RAW_COPPER_BLOCK: 
            case RAW_GOLD_BLOCK: 
            case IRON_BLOCK: 
            case COPPER_BLOCK: 
            case GOLD_BLOCK: 
            case DIAMOND_BLOCK: 
            case EXPOSED_COPPER: 
            case WEATHERED_COPPER: 
            case OXIDIZED_COPPER: 
            case CUT_COPPER: 
            case EXPOSED_CUT_COPPER: 
            case WEATHERED_CUT_COPPER: 
            case OXIDIZED_CUT_COPPER: 
            case CUT_COPPER_STAIRS: 
            case EXPOSED_CUT_COPPER_STAIRS: 
            case WEATHERED_CUT_COPPER_STAIRS: 
            case OXIDIZED_CUT_COPPER_STAIRS: 
            case CUT_COPPER_SLAB: 
            case EXPOSED_CUT_COPPER_SLAB: 
            case WEATHERED_CUT_COPPER_SLAB: 
            case OXIDIZED_CUT_COPPER_SLAB: 
            case WAXED_COPPER_BLOCK: 
            case WAXED_EXPOSED_COPPER: 
            case WAXED_WEATHERED_COPPER: 
            case WAXED_OXIDIZED_COPPER: 
            case WAXED_CUT_COPPER: 
            case WAXED_EXPOSED_CUT_COPPER: 
            case WAXED_WEATHERED_CUT_COPPER: 
            case WAXED_OXIDIZED_CUT_COPPER: 
            case WAXED_CUT_COPPER_STAIRS: 
            case WAXED_EXPOSED_CUT_COPPER_STAIRS: 
            case WAXED_WEATHERED_CUT_COPPER_STAIRS: 
            case WAXED_OXIDIZED_CUT_COPPER_STAIRS: 
            case WAXED_CUT_COPPER_SLAB: 
            case WAXED_EXPOSED_CUT_COPPER_SLAB: 
            case WAXED_WEATHERED_CUT_COPPER_SLAB: 
            case WAXED_OXIDIZED_CUT_COPPER_SLAB: 
            case STONE_SLAB: 
            case SMOOTH_STONE_SLAB: 
            case SANDSTONE_SLAB: 
            case CUT_SANDSTONE_SLAB: 
            case PETRIFIED_OAK_SLAB: 
            case COBBLESTONE_SLAB: 
            case BRICK_SLAB: 
            case STONE_BRICK_SLAB: 
            case NETHER_BRICK_SLAB: 
            case QUARTZ_SLAB: 
            case RED_SANDSTONE_SLAB: 
            case CUT_RED_SANDSTONE_SLAB: 
            case PURPUR_SLAB: 
            case PRISMARINE_SLAB: 
            case PRISMARINE_BRICK_SLAB: 
            case DARK_PRISMARINE_SLAB: 
            case SMOOTH_QUARTZ: 
            case SMOOTH_RED_SANDSTONE: 
            case SMOOTH_SANDSTONE: 
            case SMOOTH_STONE: 
            case BRICKS: 
            case MOSSY_COBBLESTONE: 
            case PURPUR_BLOCK: 
            case PURPUR_PILLAR: 
            case PURPUR_STAIRS: 
            case COBBLESTONE_STAIRS: 
            case JUKEBOX: 
            case STONE_BRICKS: 
            case MOSSY_STONE_BRICKS: 
            case CRACKED_STONE_BRICKS: 
            case CHISELED_STONE_BRICKS: 
            case DEEPSLATE_BRICKS: 
            case CRACKED_DEEPSLATE_BRICKS: 
            case DEEPSLATE_TILES: 
            case CRACKED_DEEPSLATE_TILES: 
            case CHISELED_DEEPSLATE: 
            case IRON_BARS: 
            case CHAIN: 
            case BRICK_STAIRS: 
            case STONE_BRICK_STAIRS: 
            case NETHER_BRICKS: 
            case CRACKED_NETHER_BRICKS: 
            case CHISELED_NETHER_BRICKS: 
            case NETHER_BRICK_FENCE: 
            case NETHER_BRICK_STAIRS: 
            case EMERALD_BLOCK: 
            case COBBLESTONE_WALL: 
            case MOSSY_COBBLESTONE_WALL: 
            case BRICK_WALL: 
            case PRISMARINE_WALL: 
            case MOSSY_STONE_BRICK_WALL: 
            case GRANITE_WALL: 
            case STONE_BRICK_WALL: 
            case NETHER_BRICK_WALL: 
            case ANDESITE_WALL: 
            case RED_NETHER_BRICK_WALL: 
            case DIORITE_WALL: 
            case BLACKSTONE_WALL: 
            case POLISHED_BLACKSTONE_WALL: 
            case POLISHED_BLACKSTONE_BRICK_WALL: 
            case COBBLED_DEEPSLATE_WALL: 
            case POLISHED_DEEPSLATE_WALL: 
            case DEEPSLATE_BRICK_WALL: 
            case DEEPSLATE_TILE_WALL: 
            case PRISMARINE: 
            case PRISMARINE_BRICKS: 
            case DARK_PRISMARINE: 
            case PRISMARINE_STAIRS: 
            case PRISMARINE_BRICK_STAIRS: 
            case DARK_PRISMARINE_STAIRS: 
            case RED_NETHER_BRICKS: 
            case DEAD_TUBE_CORAL_BLOCK: 
            case DEAD_BRAIN_CORAL_BLOCK: 
            case DEAD_BUBBLE_CORAL_BLOCK: 
            case DEAD_FIRE_CORAL_BLOCK: 
            case DEAD_HORN_CORAL_BLOCK: 
            case TUBE_CORAL_BLOCK: 
            case BRAIN_CORAL_BLOCK: 
            case BUBBLE_CORAL_BLOCK: 
            case FIRE_CORAL_BLOCK: 
            case HORN_CORAL_BLOCK: 
            case POLISHED_GRANITE_STAIRS: 
            case SMOOTH_RED_SANDSTONE_STAIRS: 
            case MOSSY_STONE_BRICK_STAIRS: 
            case POLISHED_DIORITE_STAIRS: 
            case MOSSY_COBBLESTONE_STAIRS: 
            case STONE_STAIRS: 
            case SMOOTH_SANDSTONE_STAIRS: 
            case SMOOTH_QUARTZ_STAIRS: 
            case GRANITE_STAIRS: 
            case ANDESITE_STAIRS: 
            case RED_NETHER_BRICK_STAIRS: 
            case POLISHED_ANDESITE_STAIRS: 
            case DIORITE_STAIRS: 
            case COBBLED_DEEPSLATE_STAIRS: 
            case POLISHED_DEEPSLATE_STAIRS: 
            case DEEPSLATE_BRICK_STAIRS: 
            case DEEPSLATE_TILE_STAIRS: 
            case POLISHED_GRANITE_SLAB: 
            case SMOOTH_RED_SANDSTONE_SLAB: 
            case MOSSY_STONE_BRICK_SLAB: 
            case POLISHED_DIORITE_SLAB: 
            case MOSSY_COBBLESTONE_SLAB: 
            case SMOOTH_SANDSTONE_SLAB: 
            case SMOOTH_QUARTZ_SLAB: 
            case GRANITE_SLAB: 
            case ANDESITE_SLAB: 
            case RED_NETHER_BRICK_SLAB: 
            case POLISHED_ANDESITE_SLAB: 
            case DIORITE_SLAB: 
            case COBBLED_DEEPSLATE_SLAB: 
            case POLISHED_DEEPSLATE_SLAB: 
            case DEEPSLATE_BRICK_SLAB: 
            case DEEPSLATE_TILE_SLAB: 
            case REDSTONE_BLOCK: 
            case LIGHTNING_ROD: 
            case GRINDSTONE: 
            case BLACKSTONE: 
            case BLACKSTONE_SLAB: 
            case BLACKSTONE_STAIRS: 
            case GILDED_BLACKSTONE: 
            case POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_SLAB: 
            case POLISHED_BLACKSTONE_STAIRS: 
            case CHISELED_POLISHED_BLACKSTONE: 
            case POLISHED_BLACKSTONE_BRICKS: 
            case POLISHED_BLACKSTONE_BRICK_SLAB: 
            case POLISHED_BLACKSTONE_BRICK_STAIRS: 
            case CRACKED_POLISHED_BLACKSTONE_BRICKS: {
                return 6.0f;
            }
            case END_STONE: 
            case END_STONE_BRICKS: 
            case DRAGON_EGG: 
            case END_STONE_BRICK_WALL: 
            case END_STONE_BRICK_STAIRS: 
            case END_STONE_BRICK_SLAB: {
                return 9.0f;
            }
            case WATER: 
            case LAVA: {
                return 100.0f;
            }
            case ENDER_CHEST: {
                return 600.0f;
            }
            case ANCIENT_DEBRIS: 
            case NETHERITE_BLOCK: 
            case OBSIDIAN: 
            case REINFORCED_DEEPSLATE: 
            case ENCHANTING_TABLE: 
            case ANVIL: 
            case CHIPPED_ANVIL: 
            case DAMAGED_ANVIL: 
            case CRYING_OBSIDIAN: 
            case RESPAWN_ANCHOR: {
                return 1200.0f;
            }
            case BEDROCK: 
            case END_PORTAL_FRAME: 
            case COMMAND_BLOCK: 
            case REPEATING_COMMAND_BLOCK: 
            case CHAIN_COMMAND_BLOCK: 
            case STRUCTURE_BLOCK: 
            case JIGSAW: 
            case END_PORTAL: 
            case END_GATEWAY: {
                return 3600000.0f;
            }
            case BARRIER: 
            case LIGHT: {
                return 3600000.8f;
            }
        }
        return 0.0f;
    }

    public float getSlipperiness() {
        Preconditions.checkArgument((boolean)this.isBlock(), (Object)"The Material is not a block!");
        switch (this) {
            default: {
                return 0.6f;
            }
            case SLIME_BLOCK: {
                return 0.8f;
            }
            case ICE: 
            case PACKED_ICE: 
            case FROSTED_ICE: {
                return 0.98f;
            }
            case BLUE_ICE: 
        }
        return 0.989f;
    }

    @Nullable
    public Material getCraftingRemainingItem() {
        Preconditions.checkArgument((boolean)this.isItem(), (Object)"The Material is not an item!");
        switch (this) {
            case WATER_BUCKET: 
            case LAVA_BUCKET: 
            case MILK_BUCKET: {
                return BUCKET;
            }
            case DRAGON_BREATH: 
            case HONEY_BOTTLE: {
                return GLASS_BOTTLE;
            }
        }
        return null;
    }

    @NotNull
    public EquipmentSlot getEquipmentSlot() {
        Preconditions.checkArgument((boolean)this.isItem(), (Object)"The Material is not an item!");
        switch (this) {
            case CARVED_PUMPKIN: 
            case TURTLE_HELMET: 
            case LEATHER_HELMET: 
            case CHAINMAIL_HELMET: 
            case IRON_HELMET: 
            case DIAMOND_HELMET: 
            case GOLDEN_HELMET: 
            case NETHERITE_HELMET: 
            case SKELETON_SKULL: 
            case WITHER_SKELETON_SKULL: 
            case PLAYER_HEAD: 
            case ZOMBIE_HEAD: 
            case CREEPER_HEAD: 
            case DRAGON_HEAD: 
            case PIGLIN_HEAD: {
                return EquipmentSlot.HEAD;
            }
            case ELYTRA: 
            case LEATHER_CHESTPLATE: 
            case CHAINMAIL_CHESTPLATE: 
            case IRON_CHESTPLATE: 
            case DIAMOND_CHESTPLATE: 
            case GOLDEN_CHESTPLATE: 
            case NETHERITE_CHESTPLATE: {
                return EquipmentSlot.CHEST;
            }
            case LEATHER_LEGGINGS: 
            case CHAINMAIL_LEGGINGS: 
            case IRON_LEGGINGS: 
            case DIAMOND_LEGGINGS: 
            case GOLDEN_LEGGINGS: 
            case NETHERITE_LEGGINGS: {
                return EquipmentSlot.LEGS;
            }
            case LEATHER_BOOTS: 
            case CHAINMAIL_BOOTS: 
            case IRON_BOOTS: 
            case DIAMOND_BOOTS: 
            case GOLDEN_BOOTS: 
            case NETHERITE_BOOTS: {
                return EquipmentSlot.FEET;
            }
            case SHIELD: {
                return EquipmentSlot.OFF_HAND;
            }
        }
        return EquipmentSlot.HAND;
    }

    @NotNull
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot slot) {
        Preconditions.checkArgument((boolean)this.isItem(), (Object)"The Material is not an item!");
        return Bukkit.getUnsafe().getDefaultAttributeModifiers(this, slot);
    }

    @Nullable
    public CreativeCategory getCreativeCategory() {
        return Bukkit.getUnsafe().getCreativeCategory(this);
    }

    @Override
    @NotNull
    public String getTranslationKey() {
        if (this.isItem()) {
            return Bukkit.getUnsafe().getItemTranslationKey(this);
        }
        return Bukkit.getUnsafe().getBlockTranslationKey(this);
    }

    @Nullable
    public String getBlockTranslationKey() {
        return Bukkit.getUnsafe().getBlockTranslationKey(this);
    }

    @Nullable
    public String getItemTranslationKey() {
        return Bukkit.getUnsafe().getItemTranslationKey(this);
    }

    public boolean isEnabledByFeature(@NotNull World world) {
        return Bukkit.getDataPackManager().isEnabledByFeature(this, world);
    }
}

