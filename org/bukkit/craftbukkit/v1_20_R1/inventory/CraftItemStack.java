/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.level.ItemLike
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ItemLike;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.enchantments.CraftEnchantment;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemFactory;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

@DelegateDeserialization(value=ItemStack.class)
public final class CraftItemStack
extends ItemStack {
    net.minecraft.world.item.ItemStack handle;

    public static net.minecraft.world.item.ItemStack asNMSCopy(ItemStack original) {
        if (original instanceof CraftItemStack) {
            CraftItemStack stack = (CraftItemStack)original;
            return stack.handle == null ? net.minecraft.world.item.ItemStack.f_41583_ : stack.handle.m_41777_();
        }
        if (original == null || original.getType() == Material.AIR) {
            return net.minecraft.world.item.ItemStack.f_41583_;
        }
        Item item = CraftMagicNumbers.getItem(original.getType(), original.getDurability());
        if (item == null) {
            return net.minecraft.world.item.ItemStack.f_41583_;
        }
        net.minecraft.world.item.ItemStack stack = new net.minecraft.world.item.ItemStack((ItemLike)item, original.getAmount());
        if (original.hasItemMeta()) {
            CraftItemStack.setItemMeta(stack, original.getItemMeta());
        }
        return stack;
    }

    public static net.minecraft.world.item.ItemStack copyNMSStack(net.minecraft.world.item.ItemStack original, int amount) {
        net.minecraft.world.item.ItemStack stack = original.m_41777_();
        stack.m_41764_(amount);
        return stack;
    }

    public static ItemStack asBukkitCopy(net.minecraft.world.item.ItemStack original) {
        if (original.m_41619_()) {
            return new ItemStack(Material.AIR);
        }
        ItemStack stack = new ItemStack(CraftMagicNumbers.getMaterial(original.m_41720_()), original.m_41613_());
        if (CraftItemStack.hasItemMeta(original)) {
            stack.setItemMeta(CraftItemStack.getItemMeta(original));
        }
        return stack;
    }

    public static CraftItemStack asCraftMirror(net.minecraft.world.item.ItemStack original) {
        return new CraftItemStack(original == null || original.m_41619_() ? null : original);
    }

    public static CraftItemStack asCraftCopy(ItemStack original) {
        if (original instanceof CraftItemStack) {
            CraftItemStack stack = (CraftItemStack)original;
            return new CraftItemStack(stack.handle == null ? null : stack.handle.m_41777_());
        }
        return new CraftItemStack(original);
    }

    public static CraftItemStack asNewCraftStack(Item item) {
        return CraftItemStack.asNewCraftStack(item, 1);
    }

    public static CraftItemStack asNewCraftStack(Item item, int amount) {
        return new CraftItemStack(CraftMagicNumbers.getMaterial(item), amount, 0, null);
    }

    private CraftItemStack(net.minecraft.world.item.ItemStack item) {
        this.handle = item;
    }

    private CraftItemStack(ItemStack item) {
        this(item.getType(), item.getAmount(), item.getDurability(), item.hasItemMeta() ? item.getItemMeta() : null);
    }

    private CraftItemStack(Material type, int amount, short durability, ItemMeta itemMeta) {
        this.setType(type);
        this.setAmount(amount);
        this.setDurability(durability);
        this.setItemMeta(itemMeta);
    }

    @Override
    public MaterialData getData() {
        return this.handle != null ? CraftMagicNumbers.getMaterialData(this.handle.m_41720_()) : super.getData();
    }

    @Override
    public Material getType() {
        return this.handle != null ? CraftMagicNumbers.getMaterial(this.handle.m_41720_()) : Material.AIR;
    }

    @Override
    public void setType(Material type) {
        if (this.getType() == type) {
            return;
        }
        if (type == Material.AIR) {
            this.handle = null;
        } else if (CraftMagicNumbers.getItem(type) == null) {
            this.handle = null;
        } else if (this.handle == null) {
            this.handle = new net.minecraft.world.item.ItemStack((ItemLike)CraftMagicNumbers.getItem(type), 1);
        } else {
            this.handle.setItem(CraftMagicNumbers.getItem(type));
            if (this.hasItemMeta()) {
                CraftItemStack.setItemMeta(this.handle, CraftItemStack.getItemMeta(this.handle));
            }
        }
        this.setData(null);
    }

    @Override
    public int getAmount() {
        return this.handle != null ? this.handle.m_41613_() : 0;
    }

    @Override
    public void setAmount(int amount) {
        if (this.handle == null) {
            return;
        }
        this.handle.m_41764_(amount);
        if (amount == 0) {
            this.handle = null;
        }
    }

    @Override
    public void setDurability(short durability) {
        if (this.handle != null) {
            this.handle.m_41721_((int)durability);
        }
    }

    @Override
    public short getDurability() {
        if (this.handle != null) {
            return (short)this.handle.m_41773_();
        }
        return -1;
    }

    @Override
    public int getMaxStackSize() {
        return this.handle == null ? Material.AIR.getMaxStackSize() : this.handle.m_41720_().m_41459_();
    }

    @Override
    public void addUnsafeEnchantment(org.bukkit.enchantments.Enchantment ench, int level) {
        Preconditions.checkArgument((ench != null ? 1 : 0) != 0, (Object)"Enchantment cannot be null");
        if (!CraftItemStack.makeTag(this.handle)) {
            return;
        }
        ListTag list = CraftItemStack.getEnchantmentList(this.handle);
        if (list == null) {
            list = new ListTag();
            this.handle.m_41783_().m_128365_(CraftMetaItem.ENCHANTMENTS.NBT, (Tag)list);
        }
        int size = list.size();
        int i = 0;
        while (i < size) {
            CompoundTag tag = (CompoundTag)list.get(i);
            String id = tag.m_128461_(CraftMetaItem.ENCHANTMENTS_ID.NBT);
            if (ench.getKey().equals(NamespacedKey.fromString(id))) {
                tag.m_128376_(CraftMetaItem.ENCHANTMENTS_LVL.NBT, (short)level);
                return;
            }
            ++i;
        }
        CompoundTag tag = new CompoundTag();
        tag.m_128359_(CraftMetaItem.ENCHANTMENTS_ID.NBT, ench.getKey().toString());
        tag.m_128376_(CraftMetaItem.ENCHANTMENTS_LVL.NBT, (short)level);
        list.add((Object)tag);
    }

    static boolean makeTag(net.minecraft.world.item.ItemStack item) {
        if (item == null) {
            return false;
        }
        if (item.m_41783_() == null) {
            item.m_41751_(new CompoundTag());
        }
        return true;
    }

    @Override
    public boolean containsEnchantment(org.bukkit.enchantments.Enchantment ench) {
        return this.getEnchantmentLevel(ench) > 0;
    }

    @Override
    public int getEnchantmentLevel(org.bukkit.enchantments.Enchantment ench) {
        Preconditions.checkArgument((ench != null ? 1 : 0) != 0, (Object)"Enchantment cannot be null");
        if (this.handle == null) {
            return 0;
        }
        return EnchantmentHelper.m_44843_((Enchantment)CraftEnchantment.getRaw(ench), (net.minecraft.world.item.ItemStack)this.handle);
    }

    @Override
    public int removeEnchantment(org.bukkit.enchantments.Enchantment ench) {
        Preconditions.checkArgument((ench != null ? 1 : 0) != 0, (Object)"Enchantment cannot be null");
        ListTag list = CraftItemStack.getEnchantmentList(this.handle);
        if (list == null) {
            return 0;
        }
        int index = Integer.MIN_VALUE;
        int level = Integer.MIN_VALUE;
        int size = list.size();
        int i = 0;
        while (i < size) {
            CompoundTag enchantment = (CompoundTag)list.get(i);
            String id = enchantment.m_128461_(CraftMetaItem.ENCHANTMENTS_ID.NBT);
            if (ench.getKey().equals(NamespacedKey.fromString(id))) {
                index = i;
                level = 0xFFFF & enchantment.m_128448_(CraftMetaItem.ENCHANTMENTS_LVL.NBT);
                break;
            }
            ++i;
        }
        if (index == Integer.MIN_VALUE) {
            return 0;
        }
        if (size == 1) {
            this.handle.m_41783_().m_128473_(CraftMetaItem.ENCHANTMENTS.NBT);
            if (this.handle.m_41783_().m_128456_()) {
                this.handle.m_41751_(null);
            }
            return level;
        }
        ListTag listCopy = new ListTag();
        i = 0;
        while (i < size) {
            if (i != index) {
                listCopy.add((Object)list.get(i));
            }
            ++i;
        }
        this.handle.m_41783_().m_128365_(CraftMetaItem.ENCHANTMENTS.NBT, (Tag)listCopy);
        return level;
    }

    @Override
    public Map<org.bukkit.enchantments.Enchantment, Integer> getEnchantments() {
        return CraftItemStack.getEnchantments(this.handle);
    }

    static Map<org.bukkit.enchantments.Enchantment, Integer> getEnchantments(net.minecraft.world.item.ItemStack item) {
        ListTag list;
        ListTag listTag = list = item != null && item.m_41793_() ? item.m_41785_() : null;
        if (list == null || list.size() == 0) {
            return ImmutableMap.of();
        }
        ImmutableMap.Builder result = ImmutableMap.builder();
        int i = 0;
        while (i < list.size()) {
            String id = ((CompoundTag)list.get(i)).m_128461_(CraftMetaItem.ENCHANTMENTS_ID.NBT);
            int level = 0xFFFF & ((CompoundTag)list.get(i)).m_128448_(CraftMetaItem.ENCHANTMENTS_LVL.NBT);
            org.bukkit.enchantments.Enchantment enchant = org.bukkit.enchantments.Enchantment.getByKey(CraftNamespacedKey.fromStringOrNull(id));
            if (enchant != null) {
                result.put((Object)enchant, (Object)level);
            }
            ++i;
        }
        return result.build();
    }

    static ListTag getEnchantmentList(net.minecraft.world.item.ItemStack item) {
        return item != null && item.m_41793_() ? item.m_41785_() : null;
    }

    @Override
    public CraftItemStack clone() {
        CraftItemStack itemStack = (CraftItemStack)super.clone();
        if (this.handle != null) {
            itemStack.handle = this.handle.m_41777_();
        }
        return itemStack;
    }

    @Override
    public ItemMeta getItemMeta() {
        return CraftItemStack.getItemMeta(this.handle);
    }

    public static ItemMeta getItemMeta(net.minecraft.world.item.ItemStack item) {
        if (!CraftItemStack.hasItemMeta(item)) {
            return CraftItemFactory.instance().getItemMeta(CraftItemStack.getType(item));
        }
        switch (CraftItemStack.getType(item)) {
            case WRITTEN_BOOK: {
                return new CraftMetaBookSigned(item.m_41783_());
            }
            case WRITABLE_BOOK: {
                return new CraftMetaBook(item.m_41783_());
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
                return new CraftMetaSkull(item.m_41783_());
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
                return new CraftMetaArmor(item.m_41783_());
            }
            case LEATHER_HELMET: 
            case LEATHER_CHESTPLATE: 
            case LEATHER_LEGGINGS: 
            case LEATHER_BOOTS: {
                return new CraftMetaColorableArmor(item.m_41783_());
            }
            case LEATHER_HORSE_ARMOR: {
                return new CraftMetaLeatherArmor(item.m_41783_());
            }
            case POTION: 
            case SPLASH_POTION: 
            case TIPPED_ARROW: 
            case LINGERING_POTION: {
                return new CraftMetaPotion(item.m_41783_());
            }
            case FILLED_MAP: {
                return new CraftMetaMap(item.m_41783_());
            }
            case FIREWORK_ROCKET: {
                return new CraftMetaFirework(item.m_41783_());
            }
            case FIREWORK_STAR: {
                return new CraftMetaCharge(item.m_41783_());
            }
            case ENCHANTED_BOOK: {
                return new CraftMetaEnchantedBook(item.m_41783_());
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
                return new CraftMetaBanner(item.m_41783_());
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
                return new CraftMetaSpawnEgg(item.m_41783_());
            }
            case ARMOR_STAND: {
                return new CraftMetaArmorStand(item.m_41783_());
            }
            case KNOWLEDGE_BOOK: {
                return new CraftMetaKnowledgeBook(item.m_41783_());
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
                return new CraftMetaBlockState(item.m_41783_(), CraftMagicNumbers.getMaterial(item.m_41720_()));
            }
            case TROPICAL_FISH_BUCKET: {
                return new CraftMetaTropicalFishBucket(item.m_41783_());
            }
            case AXOLOTL_BUCKET: {
                return new CraftMetaAxolotlBucket(item.m_41783_());
            }
            case CROSSBOW: {
                return new CraftMetaCrossbow(item.m_41783_());
            }
            case SUSPICIOUS_STEW: {
                return new CraftMetaSuspiciousStew(item.m_41783_());
            }
            case PAINTING: 
            case PUFFERFISH_BUCKET: 
            case SALMON_BUCKET: 
            case COD_BUCKET: 
            case ITEM_FRAME: 
            case GLOW_ITEM_FRAME: {
                return new CraftMetaEntityTag(item.m_41783_());
            }
            case COMPASS: {
                return new CraftMetaCompass(item.m_41783_());
            }
            case BUNDLE: {
                return new CraftMetaBundle(item.m_41783_());
            }
            case GOAT_HORN: {
                return new CraftMetaMusicInstrument(item.m_41783_());
            }
        }
        return new CraftMetaItem(item.m_41783_());
    }

    static Material getType(net.minecraft.world.item.ItemStack item) {
        return item == null ? Material.AIR : CraftMagicNumbers.getMaterial(item.m_41720_());
    }

    @Override
    public boolean setItemMeta(ItemMeta itemMeta) {
        return CraftItemStack.setItemMeta(this.handle, itemMeta);
    }

    public static boolean setItemMeta(net.minecraft.world.item.ItemStack item, ItemMeta itemMeta) {
        Item newItem;
        if (item == null) {
            return false;
        }
        if (CraftItemFactory.instance().equals(itemMeta, null)) {
            item.m_41751_(null);
            return true;
        }
        if (!CraftItemFactory.instance().isApplicable(itemMeta, CraftItemStack.getType(item))) {
            return false;
        }
        itemMeta = CraftItemFactory.instance().asMetaFor(itemMeta, CraftItemStack.getType(item));
        if (itemMeta == null) {
            return true;
        }
        Item oldItem = item.m_41720_();
        if (oldItem != (newItem = CraftMagicNumbers.getItem(CraftItemFactory.instance().updateMaterial(itemMeta, CraftMagicNumbers.getMaterial(oldItem))))) {
            item.setItem(newItem);
        }
        CompoundTag tag = new CompoundTag();
        item.m_41751_(tag);
        ((CraftMetaItem)itemMeta).applyToItem(tag);
        item.convertStack(((CraftMetaItem)itemMeta).getVersion());
        if (item.m_41720_() != null && item.m_41720_().m_41465_()) {
            item.m_41721_(item.m_41773_());
        }
        return true;
    }

    @Override
    public boolean isSimilar(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (stack == this) {
            return true;
        }
        if (!(stack instanceof CraftItemStack)) {
            return stack.getClass() == ItemStack.class && stack.isSimilar(this);
        }
        CraftItemStack that = (CraftItemStack)stack;
        if (this.handle == that.handle) {
            return true;
        }
        if (this.handle == null || that.handle == null) {
            return false;
        }
        Material comparisonType = CraftLegacy.fromLegacy(that.getType());
        if (comparisonType != this.getType() || this.getDurability() != that.getDurability()) {
            return false;
        }
        return this.hasItemMeta() ? that.hasItemMeta() && this.handle.m_41783_().equals((Object)that.handle.m_41783_()) : !that.hasItemMeta();
    }

    @Override
    public boolean hasItemMeta() {
        return CraftItemStack.hasItemMeta(this.handle) && !CraftItemFactory.instance().equals(this.getItemMeta(), null);
    }

    static boolean hasItemMeta(net.minecraft.world.item.ItemStack item) {
        return item != null && item.m_41783_() != null && !item.m_41783_().m_128456_();
    }
}

