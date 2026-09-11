/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  net.minecraft.SharedConstants
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.Bootstrap
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.datafix.DataFixers
 *  net.minecraft.util.datafix.fixes.BlockStateData
 *  net.minecraft.util.datafix.fixes.ItemIdFix
 *  net.minecraft.util.datafix.fixes.References
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.Items
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.StateDefinition
 *  net.minecraft.world.level.block.state.properties.Property
 */
package org.bukkit.craftbukkit.v1_20_R1.legacy;

import com.google.common.base.Preconditions;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import net.minecraft.SharedConstants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.datafix.fixes.BlockStateData;
import net.minecraft.util.datafix.fixes.ItemIdFix;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.entity.EntityType;
import org.bukkit.material.MaterialData;

@Deprecated
public final class CraftLegacy {
    private static final Map<Byte, Material> SPAWN_EGGS = new HashMap<Byte, Material>();
    private static final Set<String> whitelistedStates = new HashSet<String>(Arrays.asList("explode", "check_decay", "decayable", "facing"));
    private static final Map<MaterialData, Item> materialToItem = new HashMap<MaterialData, Item>(16384);
    private static final Map<Item, MaterialData> itemToMaterial = new HashMap<Item, MaterialData>(1024);
    private static final Map<MaterialData, BlockState> materialToData = new HashMap<MaterialData, BlockState>(4096);
    private static final Map<BlockState, MaterialData> dataToMaterial = new HashMap<BlockState, MaterialData>(4096);
    private static final Map<MaterialData, Block> materialToBlock = new HashMap<MaterialData, Block>(4096);
    private static final Map<Block, MaterialData> blockToMaterial = new HashMap<Block, MaterialData>(1024);

    static {
        System.err.println("Initializing Legacy Material Support. Unless you have legacy plugins and/or data this is a bug!");
        if (MinecraftServer.getServer() != null && MinecraftServer.getServer().isDebugging()) {
            new Exception().printStackTrace();
        }
        SPAWN_EGGS.put((byte)0, Material.PIG_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.BAT.getTypeId(), Material.BAT_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.BLAZE.getTypeId(), Material.BLAZE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.CAVE_SPIDER.getTypeId(), Material.CAVE_SPIDER_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.CHICKEN.getTypeId(), Material.CHICKEN_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.COD.getTypeId(), Material.COD_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.COW.getTypeId(), Material.COW_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.CREEPER.getTypeId(), Material.CREEPER_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.DOLPHIN.getTypeId(), Material.DOLPHIN_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.DONKEY.getTypeId(), Material.DONKEY_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.ELDER_GUARDIAN.getTypeId(), Material.ELDER_GUARDIAN_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.ENDERMAN.getTypeId(), Material.ENDERMAN_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.ENDERMITE.getTypeId(), Material.ENDERMITE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.EVOKER.getTypeId(), Material.EVOKER_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.GHAST.getTypeId(), Material.GHAST_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.GUARDIAN.getTypeId(), Material.GUARDIAN_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.HORSE.getTypeId(), Material.HORSE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.HUSK.getTypeId(), Material.HUSK_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.LLAMA.getTypeId(), Material.LLAMA_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.MAGMA_CUBE.getTypeId(), Material.MAGMA_CUBE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.MUSHROOM_COW.getTypeId(), Material.MOOSHROOM_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.MULE.getTypeId(), Material.MULE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.OCELOT.getTypeId(), Material.OCELOT_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.PARROT.getTypeId(), Material.PARROT_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.PIG.getTypeId(), Material.PIG_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.PHANTOM.getTypeId(), Material.PHANTOM_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.POLAR_BEAR.getTypeId(), Material.POLAR_BEAR_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.PUFFERFISH.getTypeId(), Material.PUFFERFISH_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.RABBIT.getTypeId(), Material.RABBIT_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SALMON.getTypeId(), Material.SALMON_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SHEEP.getTypeId(), Material.SHEEP_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SHULKER.getTypeId(), Material.SHULKER_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SILVERFISH.getTypeId(), Material.SILVERFISH_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SKELETON.getTypeId(), Material.SKELETON_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SKELETON_HORSE.getTypeId(), Material.SKELETON_HORSE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SLIME.getTypeId(), Material.SLIME_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SPIDER.getTypeId(), Material.SPIDER_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.SQUID.getTypeId(), Material.SQUID_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.STRAY.getTypeId(), Material.STRAY_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.TROPICAL_FISH.getTypeId(), Material.TROPICAL_FISH_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.TURTLE.getTypeId(), Material.TURTLE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.VEX.getTypeId(), Material.VEX_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.VILLAGER.getTypeId(), Material.VILLAGER_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.VINDICATOR.getTypeId(), Material.VINDICATOR_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.WITCH.getTypeId(), Material.WITCH_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.WITHER_SKELETON.getTypeId(), Material.WITHER_SKELETON_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.WOLF.getTypeId(), Material.WOLF_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.ZOMBIE.getTypeId(), Material.ZOMBIE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.ZOMBIE_HORSE.getTypeId(), Material.ZOMBIE_HORSE_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.ZOMBIFIED_PIGLIN.getTypeId(), Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);
        SPAWN_EGGS.put((byte)EntityType.ZOMBIE_VILLAGER.getTypeId(), Material.ZOMBIE_VILLAGER_SPAWN_EGG);
        SharedConstants.m_142977_();
        Bootstrap.m_135870_();
        Material[] materialArray = Material.values();
        int n = materialArray.length;
        int n2 = 0;
        while (n2 < n) {
            Material material = materialArray[n2];
            if (material.isLegacy()) {
                byte maxData;
                if (material.isBlock()) {
                    byte data = 0;
                    while (data < 16) {
                        String name;
                        Block block;
                        MaterialData matData = new MaterialData(material, data);
                        Dynamic blockTag = BlockStateData.m_14952_((int)(material.getId() << 4 | data));
                        blockTag = DataFixers.m_14512_().update(References.f_16783_, blockTag, 100, CraftMagicNumbers.INSTANCE.getDataVersion());
                        if (!blockTag.get("Name").asString("").contains("%%FILTER_ME%%") && (block = (Block)BuiltInRegistries.f_256975_.m_7745_(new ResourceLocation(name = blockTag.get("Name").asString("")))) != null) {
                            BlockState blockData = block.m_49966_();
                            StateDefinition states = block.m_49965_();
                            Optional propMap = blockTag.getElement("Properties").result();
                            if (propMap.isPresent()) {
                                CompoundTag properties = (CompoundTag)propMap.get();
                                for (String dataKey : properties.m_128431_()) {
                                    Property state = states.m_61081_(dataKey);
                                    if (state == null) {
                                        Preconditions.checkArgument((boolean)whitelistedStates.contains(dataKey), (String)"No state for %s", (Object)dataKey);
                                        continue;
                                    }
                                    Preconditions.checkState((!properties.m_128461_(dataKey).isEmpty() ? 1 : 0) != 0, (Object)"Empty data string");
                                    Optional opt = state.m_6215_(properties.m_128461_(dataKey));
                                    Preconditions.checkArgument((boolean)opt.isPresent(), (String)"No state value %s for %s", (Object)properties.m_128461_(dataKey), (Object)dataKey);
                                    blockData = (BlockState)blockData.m_61124_(state, (Comparable)opt.get());
                                }
                            }
                            if (block != Blocks.f_50016_) {
                                materialToData.put(matData, blockData);
                                if (!dataToMaterial.containsKey(blockData)) {
                                    dataToMaterial.put(blockData, matData);
                                }
                                materialToBlock.put(matData, block);
                                if (!blockToMaterial.containsKey(block)) {
                                    blockToMaterial.put(block, matData);
                                }
                            }
                        }
                        data = (byte)(data + 1);
                    }
                }
                byte by = maxData = material.getMaxDurability() == 0 ? (byte)16 : 1;
                if (material == Material.LEGACY_MONSTER_EGG) {
                    maxData = 121;
                }
                byte data = 0;
                while (data < maxData) {
                    if (material != Material.LEGACY_MONSTER_EGG && ItemIdFix.m_15942_((int)material.getId()) != null) {
                        Item newMaterial;
                        MaterialData matData = new MaterialData(material, data);
                        CompoundTag stack = new CompoundTag();
                        stack.m_128405_("id", material.getId());
                        stack.m_128376_("Damage", (short)data);
                        Dynamic converted = DataFixers.m_14512_().update(References.f_16782_, new Dynamic((DynamicOps)NbtOps.f_128958_, (Object)stack), -1, CraftMagicNumbers.INSTANCE.getDataVersion());
                        String newId = converted.get("id").asString("");
                        if (newId.equals("minecraft:spawn_egg")) {
                            newId = "minecraft:pig_spawn_egg";
                        }
                        if ((newMaterial = (Item)BuiltInRegistries.f_257033_.m_7745_(new ResourceLocation(newId))) != Items.f_41852_) {
                            materialToItem.put(matData, newMaterial);
                            if (!itemToMaterial.containsKey(newMaterial)) {
                                itemToMaterial.put(newMaterial, matData);
                            }
                        }
                    }
                    data = (byte)(data + 1);
                }
                for (Map.Entry<Byte, Material> entry : SPAWN_EGGS.entrySet()) {
                    MaterialData matData = new MaterialData(Material.LEGACY_MONSTER_EGG, entry.getKey());
                    Item newMaterial = CraftMagicNumbers.getItem(entry.getValue());
                    materialToItem.put(matData, newMaterial);
                    itemToMaterial.put(newMaterial, matData);
                }
            }
            ++n2;
        }
    }

    private CraftLegacy() {
    }

    public static Material toLegacy(Material material) {
        if (material == null || material.isLegacy()) {
            return material;
        }
        return CraftLegacy.toLegacyData(material).getItemType();
    }

    public static MaterialData toLegacyData(Material material) {
        MaterialData mappedData;
        Preconditions.checkArgument((!material.isLegacy() ? 1 : 0) != 0, (Object)"toLegacy on legacy Material");
        if (material.isBlock()) {
            Block block = CraftMagicNumbers.getBlock(material);
            BlockState blockData = block.m_49966_();
            mappedData = dataToMaterial.get(blockData);
            if (mappedData == null && (mappedData = blockToMaterial.get(block)) == null) {
                mappedData = itemToMaterial.get(block.m_5456_());
            }
        } else {
            Item item = CraftMagicNumbers.getItem(material);
            mappedData = itemToMaterial.get(item);
        }
        return mappedData == null ? new MaterialData(Material.LEGACY_AIR) : mappedData;
    }

    public static BlockState fromLegacyData(Material material, byte data) {
        Preconditions.checkArgument((boolean)material.isLegacy(), (Object)"fromLegacyData on modern Material");
        MaterialData materialData = new MaterialData(material, data);
        BlockState converted = materialToData.get(materialData);
        if (converted != null) {
            return converted;
        }
        Block convertedBlock = materialToBlock.get(materialData);
        if (convertedBlock != null) {
            return convertedBlock.m_49966_();
        }
        return Blocks.f_50016_.m_49966_();
    }

    public static Item fromLegacyData(Material material, short data) {
        Preconditions.checkArgument((boolean)material.isLegacy(), (Object)"fromLegacyData on modern Material. Did you forget to define a modern (1.13+) api-version in your plugin.yml?");
        MaterialData materialData = new MaterialData(material, (byte)data);
        Item convertedItem = materialToItem.get(materialData);
        if (convertedItem != null) {
            return convertedItem;
        }
        if (material.isBlock()) {
            BlockState converted = materialToData.get(materialData);
            if (converted != null) {
                return converted.m_60734_().m_5456_();
            }
            Block convertedBlock = materialToBlock.get(materialData);
            if (convertedBlock != null) {
                return convertedBlock.m_5456_();
            }
        }
        return Items.f_41852_;
    }

    public static byte toLegacyData(BlockState blockData) {
        return CraftLegacy.toLegacy(blockData).getData();
    }

    public static Material toLegacyMaterial(BlockState blockData) {
        return CraftLegacy.toLegacy(blockData).getItemType();
    }

    public static MaterialData toLegacy(BlockState blockData) {
        MaterialData mappedData = dataToMaterial.get(blockData);
        if (mappedData == null) {
            mappedData = blockToMaterial.get(blockData.m_60734_());
        }
        return mappedData == null ? new MaterialData(Material.LEGACY_AIR) : mappedData;
    }

    public static Material fromLegacy(Material material) {
        if (material == null || !material.isLegacy()) {
            return material;
        }
        return CraftLegacy.fromLegacy(new MaterialData(material));
    }

    public static Material fromLegacy(MaterialData materialData) {
        return CraftLegacy.fromLegacy(materialData, false);
    }

    public static Material fromLegacy(MaterialData materialData, boolean itemPriority) {
        Item item;
        Material material = materialData.getItemType();
        if (material == null || !material.isLegacy()) {
            return material;
        }
        Material mappedData = null;
        if (itemPriority && (item = materialToItem.get(materialData)) != null) {
            mappedData = CraftMagicNumbers.getMaterial(item);
        }
        if (mappedData == null && material.isBlock()) {
            Block block;
            BlockState iblock = materialToData.get(materialData);
            if (iblock != null) {
                mappedData = CraftMagicNumbers.getMaterial(iblock.m_60734_());
            }
            if (mappedData == null && (block = materialToBlock.get(materialData)) != null) {
                mappedData = CraftMagicNumbers.getMaterial(block);
            }
        }
        if (!itemPriority && mappedData == null && (item = materialToItem.get(materialData)) != null) {
            mappedData = CraftMagicNumbers.getMaterial(item);
        }
        return mappedData == null ? Material.AIR : mappedData;
    }

    public static Material[] values() {
        Material[] values = Material.values();
        return Arrays.copyOfRange(values, Material.LEGACY_AIR.ordinal(), values.length);
    }

    public static Material valueOf(String name) {
        return name.startsWith("LEGACY_") ? Material.valueOf(name) : Material.valueOf("LEGACY_" + name);
    }

    public static Material getMaterial(String name) {
        return name.startsWith("LEGACY_") ? Material.getMaterial(name) : Material.getMaterial("LEGACY_" + name);
    }

    public static Material matchMaterial(String name) {
        return name.startsWith("LEGACY_") ? Material.matchMaterial(name) : Material.matchMaterial("LEGACY_" + name);
    }

    public static int ordinal(Material material) {
        Preconditions.checkArgument((boolean)material.isLegacy(), (Object)"ordinal on modern Material");
        return material.ordinal() - Material.LEGACY_AIR.ordinal();
    }

    public static String name(Material material) {
        return material.name().substring("LEGACY_".length());
    }

    public static String toString(Material material) {
        return CraftLegacy.name(material);
    }

    public static void init() {
    }

    public static void main(String[] args) {
        System.err.println("");
    }
}

