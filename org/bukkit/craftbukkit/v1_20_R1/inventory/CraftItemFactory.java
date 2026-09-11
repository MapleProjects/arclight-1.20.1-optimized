/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.commands.arguments.item.ItemParser
 *  net.minecraft.commands.arguments.item.ItemParser$ItemResult
 *  net.minecraft.core.HolderLookup
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.SpawnEggItem
 *  net.minecraft.world.level.ItemLike
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.arguments.item.ItemParser;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.ItemLike;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaArmor;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaArmorStand;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaAxolotlBucket;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBanner;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBlockState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBook;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBookSigned;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaBundle;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaCharge;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaColorableArmor;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaCompass;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaCrossbow;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaEnchantedBook;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaEntityTag;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaFirework;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaKnowledgeBook;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaLeatherArmor;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaMap;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaMusicInstrument;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaPotion;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSkull;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSpawnEgg;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSuspiciousStew;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaTropicalFishBucket;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLegacy;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class CraftItemFactory
implements ItemFactory {
    static final Color DEFAULT_LEATHER_COLOR = Color.fromRGB(10511680);
    private static final CraftItemFactory instance = new CraftItemFactory();

    static {
        ConfigurationSerialization.registerClass(CraftMetaItem.SerializableMeta.class);
    }

    private CraftItemFactory() {
    }

    @Override
    public boolean isApplicable(ItemMeta meta, ItemStack itemstack) {
        if (itemstack == null) {
            return false;
        }
        return this.isApplicable(meta, itemstack.getType());
    }

    @Override
    public boolean isApplicable(ItemMeta meta, Material type) {
        if ((type = CraftLegacy.fromLegacy(type)) == null || meta == null) {
            return false;
        }
        Preconditions.checkArgument((boolean)(meta instanceof CraftMetaItem), (String)"Meta of %s not created by %s", (Object)meta.getClass().toString(), (Object)CraftItemFactory.class.getName());
        return ((CraftMetaItem)meta).applicableTo(type);
    }

    @Override
    public ItemMeta getItemMeta(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        return this.getItemMeta(material, null);
    }

    private ItemMeta getItemMeta(Material material, CraftMetaItem meta) {
        material = CraftLegacy.fromLegacy(material);
        switch (material) {
            case AIR: {
                return null;
            }
            case WRITTEN_BOOK: {
                return meta instanceof CraftMetaBookSigned ? meta : new CraftMetaBookSigned(meta);
            }
            case WRITABLE_BOOK: {
                return meta != null && meta.getClass().equals(CraftMetaBook.class) ? meta : new CraftMetaBook(meta);
            }
            case SKELETON_SKULL: 
            case WITHER_SKELETON_SKULL: 
            case PLAYER_HEAD: 
            case ZOMBIE_HEAD: 
            case CREEPER_HEAD: 
            case DRAGON_HEAD: 
            case PIGLIN_HEAD: 
            case SKELETON_WALL_SKULL: 
            case WITHER_SKELETON_WALL_SKULL: 
            case ZOMBIE_WALL_HEAD: 
            case PLAYER_WALL_HEAD: 
            case CREEPER_WALL_HEAD: 
            case DRAGON_WALL_HEAD: 
            case PIGLIN_WALL_HEAD: {
                return meta instanceof CraftMetaSkull ? meta : new CraftMetaSkull(meta);
            }
            case TURTLE_HELMET: 
            case CHAINMAIL_HELMET: 
            case CHAINMAIL_CHESTPLATE: 
            case CHAINMAIL_LEGGINGS: 
            case CHAINMAIL_BOOTS: 
            case IRON_HELMET: 
            case IRON_CHESTPLATE: 
            case IRON_LEGGINGS: 
            case IRON_BOOTS: 
            case DIAMOND_HELMET: 
            case DIAMOND_CHESTPLATE: 
            case DIAMOND_LEGGINGS: 
            case DIAMOND_BOOTS: 
            case GOLDEN_HELMET: 
            case GOLDEN_CHESTPLATE: 
            case GOLDEN_LEGGINGS: 
            case GOLDEN_BOOTS: 
            case NETHERITE_HELMET: 
            case NETHERITE_CHESTPLATE: 
            case NETHERITE_LEGGINGS: 
            case NETHERITE_BOOTS: {
                return meta != null && meta.getClass().equals(CraftMetaArmor.class) ? meta : new CraftMetaArmor(meta);
            }
            case LEATHER_HELMET: 
            case LEATHER_CHESTPLATE: 
            case LEATHER_LEGGINGS: 
            case LEATHER_BOOTS: {
                return meta instanceof CraftMetaColorableArmor ? meta : new CraftMetaColorableArmor(meta);
            }
            case LEATHER_HORSE_ARMOR: {
                return meta instanceof CraftMetaLeatherArmor ? meta : new CraftMetaLeatherArmor(meta);
            }
            case POTION: 
            case SPLASH_POTION: 
            case TIPPED_ARROW: 
            case LINGERING_POTION: {
                return meta instanceof CraftMetaPotion ? meta : new CraftMetaPotion(meta);
            }
            case FILLED_MAP: {
                return meta instanceof CraftMetaMap ? meta : new CraftMetaMap(meta);
            }
            case FIREWORK_ROCKET: {
                return meta instanceof CraftMetaFirework ? meta : new CraftMetaFirework(meta);
            }
            case FIREWORK_STAR: {
                return meta instanceof CraftMetaCharge ? meta : new CraftMetaCharge(meta);
            }
            case ENCHANTED_BOOK: {
                return meta instanceof CraftMetaEnchantedBook ? meta : new CraftMetaEnchantedBook(meta);
            }
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
            case BLACK_WALL_BANNER: {
                return meta instanceof CraftMetaBanner ? meta : new CraftMetaBanner(meta);
            }
            case ALLAY_SPAWN_EGG: 
            case AXOLOTL_SPAWN_EGG: 
            case BAT_SPAWN_EGG: 
            case BEE_SPAWN_EGG: 
            case BLAZE_SPAWN_EGG: 
            case CAT_SPAWN_EGG: 
            case CAMEL_SPAWN_EGG: 
            case CAVE_SPIDER_SPAWN_EGG: 
            case CHICKEN_SPAWN_EGG: 
            case COD_SPAWN_EGG: 
            case COW_SPAWN_EGG: 
            case CREEPER_SPAWN_EGG: 
            case DOLPHIN_SPAWN_EGG: 
            case DONKEY_SPAWN_EGG: 
            case DROWNED_SPAWN_EGG: 
            case ELDER_GUARDIAN_SPAWN_EGG: 
            case ENDER_DRAGON_SPAWN_EGG: 
            case ENDERMAN_SPAWN_EGG: 
            case ENDERMITE_SPAWN_EGG: 
            case EVOKER_SPAWN_EGG: 
            case FOX_SPAWN_EGG: 
            case FROG_SPAWN_EGG: 
            case GHAST_SPAWN_EGG: 
            case GLOW_SQUID_SPAWN_EGG: 
            case GOAT_SPAWN_EGG: 
            case GUARDIAN_SPAWN_EGG: 
            case HOGLIN_SPAWN_EGG: 
            case HORSE_SPAWN_EGG: 
            case HUSK_SPAWN_EGG: 
            case IRON_GOLEM_SPAWN_EGG: 
            case LLAMA_SPAWN_EGG: 
            case MAGMA_CUBE_SPAWN_EGG: 
            case MOOSHROOM_SPAWN_EGG: 
            case MULE_SPAWN_EGG: 
            case OCELOT_SPAWN_EGG: 
            case PANDA_SPAWN_EGG: 
            case PARROT_SPAWN_EGG: 
            case PHANTOM_SPAWN_EGG: 
            case PIG_SPAWN_EGG: 
            case PIGLIN_SPAWN_EGG: 
            case PIGLIN_BRUTE_SPAWN_EGG: 
            case PILLAGER_SPAWN_EGG: 
            case POLAR_BEAR_SPAWN_EGG: 
            case PUFFERFISH_SPAWN_EGG: 
            case RABBIT_SPAWN_EGG: 
            case RAVAGER_SPAWN_EGG: 
            case SALMON_SPAWN_EGG: 
            case SHEEP_SPAWN_EGG: 
            case SHULKER_SPAWN_EGG: 
            case SILVERFISH_SPAWN_EGG: 
            case SKELETON_SPAWN_EGG: 
            case SKELETON_HORSE_SPAWN_EGG: 
            case SLIME_SPAWN_EGG: 
            case SNIFFER_SPAWN_EGG: 
            case SNOW_GOLEM_SPAWN_EGG: 
            case SPIDER_SPAWN_EGG: 
            case SQUID_SPAWN_EGG: 
            case STRAY_SPAWN_EGG: 
            case STRIDER_SPAWN_EGG: 
            case TADPOLE_SPAWN_EGG: 
            case TRADER_LLAMA_SPAWN_EGG: 
            case TROPICAL_FISH_SPAWN_EGG: 
            case TURTLE_SPAWN_EGG: 
            case VEX_SPAWN_EGG: 
            case VILLAGER_SPAWN_EGG: 
            case VINDICATOR_SPAWN_EGG: 
            case WANDERING_TRADER_SPAWN_EGG: 
            case WARDEN_SPAWN_EGG: 
            case WITCH_SPAWN_EGG: 
            case WITHER_SPAWN_EGG: 
            case WITHER_SKELETON_SPAWN_EGG: 
            case WOLF_SPAWN_EGG: 
            case ZOGLIN_SPAWN_EGG: 
            case ZOMBIE_SPAWN_EGG: 
            case ZOMBIE_HORSE_SPAWN_EGG: 
            case ZOMBIE_VILLAGER_SPAWN_EGG: 
            case ZOMBIFIED_PIGLIN_SPAWN_EGG: {
                return meta instanceof CraftMetaSpawnEgg ? meta : new CraftMetaSpawnEgg(meta);
            }
            case ARMOR_STAND: {
                return meta instanceof CraftMetaArmorStand ? meta : new CraftMetaArmorStand(meta);
            }
            case KNOWLEDGE_BOOK: {
                return meta instanceof CraftMetaKnowledgeBook ? meta : new CraftMetaKnowledgeBook(meta);
            }
            case SUSPICIOUS_SAND: 
            case SUSPICIOUS_GRAVEL: 
            case CHISELED_BOOKSHELF: 
            case DECORATED_POT: 
            case SPAWNER: 
            case CHEST: 
            case FURNACE: 
            case JUKEBOX: 
            case SCULK_CATALYST: 
            case SCULK_SHRIEKER: 
            case ENCHANTING_TABLE: 
            case ENDER_CHEST: 
            case COMMAND_BLOCK: 
            case BEACON: 
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
            case COMPARATOR: 
            case HOPPER: 
            case DISPENSER: 
            case DROPPER: 
            case LECTERN: 
            case DAYLIGHT_DETECTOR: 
            case SCULK_SENSOR: 
            case CALIBRATED_SCULK_SENSOR: 
            case TRAPPED_CHEST: 
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
            case BREWING_STAND: 
            case SHIELD: 
            case BARREL: 
            case SMOKER: 
            case BLAST_FURNACE: 
            case BELL: 
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
            case CRIMSON_WALL_HANGING_SIGN: 
            case WARPED_WALL_HANGING_SIGN: 
            case BAMBOO_WALL_HANGING_SIGN: 
            case CRIMSON_WALL_SIGN: 
            case WARPED_WALL_SIGN: {
                return new CraftMetaBlockState(meta, material);
            }
            case TROPICAL_FISH_BUCKET: {
                return meta instanceof CraftMetaTropicalFishBucket ? meta : new CraftMetaTropicalFishBucket(meta);
            }
            case AXOLOTL_BUCKET: {
                return meta instanceof CraftMetaAxolotlBucket ? meta : new CraftMetaAxolotlBucket(meta);
            }
            case CROSSBOW: {
                return meta instanceof CraftMetaCrossbow ? meta : new CraftMetaCrossbow(meta);
            }
            case SUSPICIOUS_STEW: {
                return meta instanceof CraftMetaSuspiciousStew ? meta : new CraftMetaSuspiciousStew(meta);
            }
            case PAINTING: 
            case PUFFERFISH_BUCKET: 
            case SALMON_BUCKET: 
            case COD_BUCKET: 
            case ITEM_FRAME: 
            case GLOW_ITEM_FRAME: {
                return meta instanceof CraftMetaEntityTag ? meta : new CraftMetaEntityTag(meta);
            }
            case COMPASS: {
                return meta instanceof CraftMetaCompass ? meta : new CraftMetaCompass(meta);
            }
            case BUNDLE: {
                return meta instanceof CraftMetaBundle ? meta : new CraftMetaBundle(meta);
            }
            case GOAT_HORN: {
                return meta instanceof CraftMetaMusicInstrument ? meta : new CraftMetaMusicInstrument(meta);
            }
        }
        return new CraftMetaItem(meta);
    }

    @Override
    public boolean equals(ItemMeta meta1, ItemMeta meta2) {
        if (meta1 == meta2) {
            return true;
        }
        if (meta1 == null) {
            return ((CraftMetaItem)meta2).isEmpty();
        }
        Preconditions.checkArgument((boolean)(meta1 instanceof CraftMetaItem), (String)"First meta of %s does not belong to %s", (Object)meta1.getClass().getName(), (Object)CraftItemFactory.class.getName());
        if (meta2 == null) {
            return ((CraftMetaItem)meta1).isEmpty();
        }
        Preconditions.checkArgument((boolean)(meta2 instanceof CraftMetaItem), (String)"Second meta of %s does not belong to %s", (Object)meta2.getClass().getName(), (Object)CraftItemFactory.class.getName());
        return this.equals((CraftMetaItem)meta1, (CraftMetaItem)meta2);
    }

    boolean equals(CraftMetaItem meta1, CraftMetaItem meta2) {
        return meta1.equalsCommon(meta2) && meta1.notUncommon(meta2) && meta2.notUncommon(meta1);
    }

    public static CraftItemFactory instance() {
        return instance;
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, ItemStack stack) {
        Preconditions.checkArgument((stack != null ? 1 : 0) != 0, (Object)"ItemStack stack cannot be null");
        return this.asMetaFor(meta, stack.getType());
    }

    @Override
    public ItemMeta asMetaFor(ItemMeta meta, Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        Preconditions.checkArgument((boolean)(meta instanceof CraftMetaItem), (String)"ItemMeta of %s not created by %s", (Object)(meta != null ? meta.getClass().toString() : "null"), (Object)CraftItemFactory.class.getName());
        return this.getItemMeta(material, (CraftMetaItem)meta);
    }

    @Override
    public Color getDefaultLeatherColor() {
        return DEFAULT_LEATHER_COLOR;
    }

    @Override
    public ItemStack createItemStack(String input) throws IllegalArgumentException {
        try {
            ItemParser.ItemResult arg = ItemParser.m_235305_((HolderLookup)BuiltInRegistries.f_257033_.m_255303_(), (StringReader)new StringReader(input));
            Item item = (Item)arg.f_235328_().m_203334_();
            net.minecraft.world.item.ItemStack nmsItemStack = new net.minecraft.world.item.ItemStack((ItemLike)item);
            CompoundTag nbt = arg.f_235329_();
            if (nbt != null) {
                nmsItemStack.m_41751_(nbt);
            }
            return CraftItemStack.asCraftMirror(nmsItemStack);
        }
        catch (CommandSyntaxException ex) {
            throw new IllegalArgumentException("Could not parse ItemStack: " + input, ex);
        }
    }

    @Override
    public Material updateMaterial(ItemMeta meta, Material material) throws IllegalArgumentException {
        return ((CraftMetaItem)meta).updateMaterial(material);
    }

    @Override
    public Material getSpawnEgg(EntityType type) {
        if (type == EntityType.UNKNOWN) {
            return null;
        }
        net.minecraft.world.entity.EntityType nmsType = (net.minecraft.world.entity.EntityType)BuiltInRegistries.f_256780_.m_7745_(CraftNamespacedKey.toMinecraft(type.getKey()));
        SpawnEggItem nmsItem = SpawnEggItem.m_43213_((net.minecraft.world.entity.EntityType)nmsType);
        if (nmsItem == null) {
            return null;
        }
        return CraftMagicNumbers.getMaterial((Item)nmsItem);
    }
}

