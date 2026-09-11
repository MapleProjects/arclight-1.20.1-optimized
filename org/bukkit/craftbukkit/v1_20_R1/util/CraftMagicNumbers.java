/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.ImmutableMultimap$Builder
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  com.google.common.io.Files
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  net.minecraft.SharedConstants
 *  net.minecraft.advancements.Advancement$Builder
 *  net.minecraft.advancements.critereon.DeserializationContext
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.NbtOps
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.TagParser
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.ServerAdvancementManager
 *  net.minecraft.util.GsonHelper
 *  net.minecraft.util.datafix.DataFixers
 *  net.minecraft.util.datafix.fixes.References
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.material.Fluid
 *  net.minecraft.world.level.storage.LevelResource
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.io.Files;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.SharedConstants;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.datafix.DataFixers;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.LevelResource;
import org.bukkit.Bukkit;
import org.bukkit.FeatureFlag;
import org.bukkit.Fluid;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.UnsafeValues;
import org.bukkit.advancement.Advancement;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.data.BlockData;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.CraftFeatureFlag;
import org.bukkit.craftbukkit.v1_20_R1.attribute.CraftAttributeInstance;
import org.bukkit.craftbukkit.v1_20_R1.attribute.CraftAttributeMap;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.legacy.CraftLegacy;
import org.bukkit.craftbukkit.v1_20_R1.util.Commodore;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.CreativeCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.PluginDescriptionFile;

public final class CraftMagicNumbers
implements UnsafeValues {
    public static final UnsafeValues INSTANCE = new CraftMagicNumbers();
    private static final Map<Block, Material> BLOCK_MATERIAL = new HashMap<Block, Material>();
    private static final Map<Item, Material> ITEM_MATERIAL = new HashMap<Item, Material>();
    private static final BiMap<net.minecraft.world.level.material.Fluid, Fluid> FLUIDTYPE_FLUID = HashBiMap.create();
    private static final Map<Material, Item> MATERIAL_ITEM = new HashMap<Material, Item>();
    private static final Map<Material, Block> MATERIAL_BLOCK = new HashMap<Material, Block>();
    private static final List<String> SUPPORTED_API;

    static {
        for (Block block2 : BuiltInRegistries.f_256975_) {
            BLOCK_MATERIAL.put(block2, Material.getMaterial(BuiltInRegistries.f_256975_.m_7981_((Object)block2).m_135815_().toUpperCase(Locale.ROOT)));
        }
        for (Item item2 : BuiltInRegistries.f_257033_) {
            ITEM_MATERIAL.put(item2, Material.getMaterial(BuiltInRegistries.f_257033_.m_7981_((Object)item2).m_135815_().toUpperCase(Locale.ROOT)));
        }
        for (net.minecraft.world.level.material.Fluid fluidType : BuiltInRegistries.f_257020_) {
            Fluid fluid = Registry.FLUID.get(CraftNamespacedKey.fromMinecraft(BuiltInRegistries.f_257020_.m_7981_((Object)fluidType)));
            FLUIDTYPE_FLUID.put((Object)fluidType, (Object)fluid);
        }
        Material[] materialArray = Material.values();
        int n = materialArray.length;
        int n2 = 0;
        while (n2 < n) {
            Material material = materialArray[n2];
            if (!material.isLegacy()) {
                ResourceLocation key = CraftMagicNumbers.key(material);
                BuiltInRegistries.f_257033_.m_6612_(key).ifPresent(item -> MATERIAL_ITEM.put(material, (Item)item));
                BuiltInRegistries.f_256975_.m_6612_(key).ifPresent(block -> MATERIAL_BLOCK.put(material, (Block)block));
            }
            ++n2;
        }
        SUPPORTED_API = Arrays.asList("1.13", "1.14", "1.15", "1.16", "1.17", "1.18", "1.19", "1.20");
    }

    private CraftMagicNumbers() {
    }

    public static BlockState getBlock(MaterialData material) {
        return CraftMagicNumbers.getBlock(material.getItemType(), material.getData());
    }

    public static BlockState getBlock(Material material, byte data) {
        return CraftLegacy.fromLegacyData(CraftLegacy.toLegacy(material), data);
    }

    public static MaterialData getMaterial(BlockState data) {
        return CraftLegacy.toLegacy(CraftMagicNumbers.getMaterial(data.m_60734_())).getNewData(CraftMagicNumbers.toLegacyData(data));
    }

    public static Item getItem(Material material, short data) {
        if (material.isLegacy()) {
            return CraftLegacy.fromLegacyData(CraftLegacy.toLegacy(material), data);
        }
        return CraftMagicNumbers.getItem(material);
    }

    public static MaterialData getMaterialData(Item item) {
        return CraftLegacy.toLegacyData(CraftMagicNumbers.getMaterial(item));
    }

    public static Material getMaterial(Block block) {
        return BLOCK_MATERIAL.get(block);
    }

    public static Material getMaterial(Item item) {
        return ITEM_MATERIAL.getOrDefault(item, Material.AIR);
    }

    public static Fluid getFluid(net.minecraft.world.level.material.Fluid fluid) {
        return (Fluid)FLUIDTYPE_FLUID.get((Object)fluid);
    }

    public static Item getItem(Material material) {
        if (material != null && material.isLegacy()) {
            material = CraftLegacy.fromLegacy(material);
        }
        return MATERIAL_ITEM.get(material);
    }

    public static Block getBlock(Material material) {
        if (material != null && material.isLegacy()) {
            material = CraftLegacy.fromLegacy(material);
        }
        return MATERIAL_BLOCK.get(material);
    }

    public static net.minecraft.world.level.material.Fluid getFluid(Fluid fluid) {
        return (net.minecraft.world.level.material.Fluid)FLUIDTYPE_FLUID.inverse().get((Object)fluid);
    }

    public static ResourceLocation key(Material mat) {
        return CraftNamespacedKey.toMinecraft(mat.getKey());
    }

    public static byte toLegacyData(BlockState data) {
        return CraftLegacy.toLegacyData(data);
    }

    @Override
    public Material toLegacy(Material material) {
        return CraftLegacy.toLegacy(material);
    }

    @Override
    public Material fromLegacy(Material material) {
        return CraftLegacy.fromLegacy(material);
    }

    @Override
    public Material fromLegacy(MaterialData material) {
        return CraftLegacy.fromLegacy(material);
    }

    @Override
    public Material fromLegacy(MaterialData material, boolean itemPriority) {
        return CraftLegacy.fromLegacy(material, itemPriority);
    }

    @Override
    public BlockData fromLegacy(Material material, byte data) {
        return CraftBlockData.fromData(CraftMagicNumbers.getBlock(material, data));
    }

    @Override
    public Material getMaterial(String material, int version) {
        Dynamic converted;
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"material == null");
        Preconditions.checkArgument((version <= this.getDataVersion() ? 1 : 0) != 0, (Object)"Newer version! Server downgrades are not supported!");
        if (version == this.getDataVersion()) {
            return Material.getMaterial(material);
        }
        Dynamic name = new Dynamic((DynamicOps)NbtOps.f_128958_, (Object)StringTag.m_129297_((String)("minecraft:" + material.toLowerCase(Locale.ROOT))));
        if (name.equals((Object)(converted = DataFixers.m_14512_().update(References.f_16788_, name, version, this.getDataVersion())))) {
            converted = DataFixers.m_14512_().update(References.f_16787_, name, version, this.getDataVersion());
        }
        return Material.matchMaterial(converted.asString(""));
    }

    public String getMappingsVersion() {
        return "bcf3dcb22ad42792794079f9443df2c0";
    }

    @Override
    public int getDataVersion() {
        return SharedConstants.m_183709_().m_183476_().m_193006_();
    }

    @Override
    public ItemStack modifyItemStack(ItemStack stack, String arguments) {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        try {
            nmsStack.m_41751_(TagParser.m_129359_((String)arguments));
        }
        catch (CommandSyntaxException ex) {
            Logger.getLogger(CraftMagicNumbers.class.getName()).log(Level.SEVERE, null, ex);
        }
        stack.setItemMeta(CraftItemStack.getItemMeta(nmsStack));
        return stack;
    }

    private static File getBukkitDataPackFolder() {
        return new File(MinecraftServer.getServer().m_129843_(LevelResource.f_78180_).toFile(), "bukkit");
    }

    @Override
    public Advancement loadAdvancement(NamespacedKey key, String advancement) {
        Preconditions.checkArgument((Bukkit.getAdvancement(key) == null ? 1 : 0) != 0, (String)"Advancement %s already exists", (Object)key);
        ResourceLocation minecraftkey = CraftNamespacedKey.toMinecraft(key);
        JsonElement jsonelement = (JsonElement)ServerAdvancementManager.f_136022_.fromJson(advancement, JsonElement.class);
        JsonObject jsonobject = GsonHelper.m_13918_((JsonElement)jsonelement, (String)"advancement");
        Advancement.Builder nms = Advancement.Builder.m_138380_((JsonObject)jsonobject, (DeserializationContext)new DeserializationContext(minecraftkey, MinecraftServer.getServer().m_278653_()));
        if (nms != null) {
            MinecraftServer.getServer().m_129889_().f_136023_.m_139333_((Map)Maps.newHashMap(Collections.singletonMap(minecraftkey, nms)));
            Advancement bukkit = Bukkit.getAdvancement(key);
            if (bukkit != null) {
                File file = new File(CraftMagicNumbers.getBukkitDataPackFolder(), "data" + File.separator + key.getNamespace() + File.separator + "advancements" + File.separator + key.getKey() + ".json");
                file.getParentFile().mkdirs();
                try {
                    Files.write((CharSequence)advancement, (File)file, (Charset)Charsets.UTF_8);
                }
                catch (IOException ex) {
                    Bukkit.getLogger().log(Level.SEVERE, "Error saving advancement " + key, ex);
                }
                MinecraftServer.getServer().m_6846_().m_11315_();
                return bukkit;
            }
        }
        return null;
    }

    @Override
    public boolean removeAdvancement(NamespacedKey key) {
        File file = new File(CraftMagicNumbers.getBukkitDataPackFolder(), "data" + File.separator + key.getNamespace() + File.separator + "advancements" + File.separator + key.getKey() + ".json");
        return file.delete();
    }

    @Override
    public void checkSupported(PluginDescriptionFile pdf) throws InvalidPluginException {
        String minimumVersion = MinecraftServer.getServer().server.minimumAPI;
        int minimumIndex = SUPPORTED_API.indexOf(minimumVersion);
        if (pdf.getAPIVersion() != null) {
            int pluginIndex = SUPPORTED_API.indexOf(pdf.getAPIVersion());
            if (pluginIndex == -1) {
                throw new InvalidPluginException("Unsupported API version " + pdf.getAPIVersion());
            }
            if (pluginIndex < minimumIndex) {
                throw new InvalidPluginException("Plugin API version " + pdf.getAPIVersion() + " is lower than the minimum allowed version. Please update or replace it.");
            }
        } else if (minimumIndex == -1) {
            CraftLegacy.init();
            Bukkit.getLogger().log(Level.WARNING, "Legacy plugin " + pdf.getFullName() + " does not specify an api-version.");
        } else {
            throw new InvalidPluginException("Plugin API version " + pdf.getAPIVersion() + " is lower than the minimum allowed version. Please update or replace it.");
        }
    }

    public static boolean isLegacy(PluginDescriptionFile pdf) {
        return pdf.getAPIVersion() == null;
    }

    @Override
    public byte[] processClass(PluginDescriptionFile pdf, String path, byte[] clazz) {
        try {
            clazz = Commodore.convert(clazz, !CraftMagicNumbers.isLegacy(pdf));
        }
        catch (Exception ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Fatal error trying to convert " + pdf.getFullName() + ":" + path, ex);
        }
        return clazz;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(Material material, EquipmentSlot slot) {
        ImmutableMultimap.Builder defaultAttributes = ImmutableMultimap.builder();
        Multimap nmsDefaultAttributes = CraftMagicNumbers.getItem(material).m_7167_(CraftEquipmentSlot.getNMS(slot));
        for (Map.Entry mapEntry : nmsDefaultAttributes.entries()) {
            Attribute attribute = CraftAttributeMap.fromMinecraft(BuiltInRegistries.f_256951_.m_7981_((Object)((net.minecraft.world.entity.ai.attributes.Attribute)mapEntry.getKey())).toString());
            defaultAttributes.put((Object)attribute, (Object)CraftAttributeInstance.convert((net.minecraft.world.entity.ai.attributes.AttributeModifier)mapEntry.getValue(), slot));
        }
        return defaultAttributes.build();
    }

    @Override
    public CreativeCategory getCreativeCategory(Material material) {
        return CreativeCategory.BUILDING_BLOCKS;
    }

    @Override
    public String getBlockTranslationKey(Material material) {
        Block block = CraftMagicNumbers.getBlock(material);
        return block != null ? block.m_7705_() : null;
    }

    @Override
    public String getItemTranslationKey(Material material) {
        Item item = CraftMagicNumbers.getItem(material);
        return item != null ? item.m_5524_() : null;
    }

    @Override
    public String getTranslationKey(EntityType entityType) {
        Preconditions.checkArgument((entityType.getName() != null ? 1 : 0) != 0, (String)"Invalid name of EntityType %s for translation key", (Object)entityType);
        return net.minecraft.world.entity.EntityType.m_20632_((String)entityType.getName()).map(net.minecraft.world.entity.EntityType::m_20675_).orElseThrow();
    }

    @Override
    public String getTranslationKey(ItemStack itemStack) {
        net.minecraft.world.item.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        return nmsItemStack.m_41720_().m_5671_(nmsItemStack);
    }

    @Override
    public FeatureFlag getFeatureFlag(NamespacedKey namespacedKey) {
        Preconditions.checkArgument((namespacedKey != null ? 1 : 0) != 0, (Object)"NamespaceKey cannot be null");
        return CraftFeatureFlag.getFromNMS(namespacedKey);
    }

    public static class NBT {
        public static final int TAG_END = 0;
        public static final int TAG_BYTE = 1;
        public static final int TAG_SHORT = 2;
        public static final int TAG_INT = 3;
        public static final int TAG_LONG = 4;
        public static final int TAG_FLOAT = 5;
        public static final int TAG_DOUBLE = 6;
        public static final int TAG_BYTE_ARRAY = 7;
        public static final int TAG_STRING = 8;
        public static final int TAG_LIST = 9;
        public static final int TAG_COMPOUND = 10;
        public static final int TAG_INT_ARRAY = 11;
        public static final int TAG_ANY_NUMBER = 99;
    }
}

