/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Strings
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.ImmutableMultimap
 *  com.google.common.collect.LinkedHashMultimap
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Multimap
 *  com.google.common.collect.Sets
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.NbtIo
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.item.BlockItem
 *  net.minecraft.world.level.block.state.BlockState
 *  org.apache.commons.lang3.EnumUtils
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.lang3.EnumUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.Overridden;
import org.bukkit.craftbukkit.v1_20_R1.attribute.CraftAttributeInstance;
import org.bukkit.craftbukkit.v1_20_R1.attribute.CraftAttributeMap;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;
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
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaKnowledgeBook;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaLeatherArmor;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaMap;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaMusicInstrument;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaPotion;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSkull;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSpawnEgg;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaSuspiciousStew;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaTropicalFishBucket;
import org.bukkit.craftbukkit.v1_20_R1.inventory.tags.DeprecatedCustomTagContainer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNBTTagConfigSerializer;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;
import org.spigotmc.ValidateUtils;

@DelegateDeserialization(value=SerializableMeta.class)
public class CraftMetaItem
implements ItemMeta,
Damageable,
Repairable,
BlockDataMeta {
    static final ItemMetaKey NAME = new ItemMetaKey("Name", "display-name");
    static final ItemMetaKey LOCNAME = new ItemMetaKey("LocName", "loc-name");
    static final ItemMetaKey DISPLAY = new ItemMetaKey("display");
    static final ItemMetaKey LORE = new ItemMetaKey("Lore", "lore");
    static final ItemMetaKey CUSTOM_MODEL_DATA = new ItemMetaKey("CustomModelData", "custom-model-data");
    static final ItemMetaKey ENCHANTMENTS = new ItemMetaKey("Enchantments", "enchants");
    static final ItemMetaKey ENCHANTMENTS_ID = new ItemMetaKey("id");
    static final ItemMetaKey ENCHANTMENTS_LVL = new ItemMetaKey("lvl");
    static final ItemMetaKey REPAIR = new ItemMetaKey("RepairCost", "repair-cost");
    static final ItemMetaKey ATTRIBUTES = new ItemMetaKey("AttributeModifiers", "attribute-modifiers");
    static final ItemMetaKey ATTRIBUTES_IDENTIFIER = new ItemMetaKey("AttributeName");
    static final ItemMetaKey ATTRIBUTES_NAME = new ItemMetaKey("Name");
    static final ItemMetaKey ATTRIBUTES_VALUE = new ItemMetaKey("Amount");
    static final ItemMetaKey ATTRIBUTES_TYPE = new ItemMetaKey("Operation");
    static final ItemMetaKey ATTRIBUTES_UUID_HIGH = new ItemMetaKey("UUIDMost");
    static final ItemMetaKey ATTRIBUTES_UUID_LOW = new ItemMetaKey("UUIDLeast");
    static final ItemMetaKey ATTRIBUTES_SLOT = new ItemMetaKey("Slot");
    static final ItemMetaKey HIDEFLAGS = new ItemMetaKey("HideFlags", "ItemFlags");
    static final ItemMetaKey UNBREAKABLE = new ItemMetaKey("Unbreakable");
    static final ItemMetaKey DAMAGE = new ItemMetaKey("Damage");
    static final ItemMetaKey BLOCK_DATA = new ItemMetaKey("BlockStateTag");
    static final ItemMetaKey BUKKIT_CUSTOM_TAG = new ItemMetaKey("PublicBukkitValues");
    private String displayName;
    private String locName;
    private List<String> lore;
    private Integer customModelData;
    private CompoundTag blockData;
    private Map<Enchantment, Integer> enchantments;
    private Multimap<Attribute, AttributeModifier> attributeModifiers;
    private int repairCost;
    private int hideFlag;
    private boolean unbreakable;
    private int damage;
    private static final Set<String> HANDLED_TAGS = Sets.newHashSet();
    private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
    private CompoundTag internalTag;
    final Map<String, Tag> unhandledTags = new HashMap<String, Tag>();
    private CraftPersistentDataContainer persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);
    private int version = CraftMagicNumbers.INSTANCE.getDataVersion();

    public CraftMetaItem(CraftMetaItem meta) {
        if (meta == null) {
            return;
        }
        this.displayName = meta.displayName;
        this.locName = meta.locName;
        if (meta.lore != null) {
            this.lore = new ArrayList<String>(meta.lore);
        }
        this.customModelData = meta.customModelData;
        this.blockData = meta.blockData;
        if (meta.enchantments != null) {
            this.enchantments = new LinkedHashMap<Enchantment, Integer>(meta.enchantments);
        }
        if (meta.hasAttributeModifiers()) {
            this.attributeModifiers = LinkedHashMultimap.create(meta.attributeModifiers);
        }
        this.repairCost = meta.repairCost;
        this.hideFlag = meta.hideFlag;
        this.unbreakable = meta.unbreakable;
        this.damage = meta.damage;
        this.unhandledTags.putAll(meta.unhandledTags);
        this.persistentDataContainer.putAll(meta.persistentDataContainer.getRaw());
        this.internalTag = meta.internalTag;
        if (this.internalTag != null) {
            this.deserializeInternal(this.internalTag, meta);
        }
        this.version = meta.version;
    }

    public CraftMetaItem(CompoundTag tag) {
        if (tag.m_128441_(CraftMetaItem.DISPLAY.NBT)) {
            CompoundTag display = tag.m_128469_(CraftMetaItem.DISPLAY.NBT);
            if (display.m_128441_(CraftMetaItem.NAME.NBT)) {
                this.displayName = ValidateUtils.limit(display.m_128461_(CraftMetaItem.NAME.NBT), 8192);
            }
            if (display.m_128441_(CraftMetaItem.LOCNAME.NBT)) {
                this.locName = ValidateUtils.limit(display.m_128461_(CraftMetaItem.LOCNAME.NBT), 8192);
            }
            if (display.m_128441_(CraftMetaItem.LORE.NBT)) {
                ListTag list = display.m_128437_(CraftMetaItem.LORE.NBT, 8);
                this.lore = new ArrayList<String>(list.size());
                int index = 0;
                while (index < list.size()) {
                    String line = ValidateUtils.limit(list.m_128778_(index), 8192);
                    this.lore.add(line);
                    ++index;
                }
            }
        }
        if (tag.m_128425_(CraftMetaItem.CUSTOM_MODEL_DATA.NBT, 3)) {
            this.customModelData = tag.m_128451_(CraftMetaItem.CUSTOM_MODEL_DATA.NBT);
        }
        if (tag.m_128425_(CraftMetaItem.BLOCK_DATA.NBT, 10)) {
            this.blockData = tag.m_128469_(CraftMetaItem.BLOCK_DATA.NBT).m_6426_();
        }
        this.enchantments = CraftMetaItem.buildEnchantments(tag, ENCHANTMENTS);
        this.attributeModifiers = CraftMetaItem.buildModifiers(tag, ATTRIBUTES);
        if (tag.m_128441_(CraftMetaItem.REPAIR.NBT)) {
            this.repairCost = tag.m_128451_(CraftMetaItem.REPAIR.NBT);
        }
        if (tag.m_128441_(CraftMetaItem.HIDEFLAGS.NBT)) {
            this.hideFlag = tag.m_128451_(CraftMetaItem.HIDEFLAGS.NBT);
        }
        if (tag.m_128441_(CraftMetaItem.UNBREAKABLE.NBT)) {
            this.unbreakable = tag.m_128471_(CraftMetaItem.UNBREAKABLE.NBT);
        }
        if (tag.m_128441_(CraftMetaItem.DAMAGE.NBT)) {
            this.damage = tag.m_128451_(CraftMetaItem.DAMAGE.NBT);
        }
        if (tag.m_128441_(CraftMetaItem.BUKKIT_CUSTOM_TAG.NBT)) {
            CompoundTag compound = tag.m_128469_(CraftMetaItem.BUKKIT_CUSTOM_TAG.NBT);
            Set keys = compound.m_128431_();
            for (String key : keys) {
                this.persistentDataContainer.put(key, compound.m_128423_(key).m_6426_());
            }
        }
        Set keys = tag.m_128431_();
        for (String key : keys) {
            if (CraftMetaItem.getHandledTags().contains(key)) continue;
            this.unhandledTags.put(key, tag.m_128423_(key).m_6426_());
        }
    }

    static Map<Enchantment, Integer> buildEnchantments(CompoundTag tag, ItemMetaKey key) {
        if (!tag.m_128441_(key.NBT)) {
            return null;
        }
        ListTag ench = tag.m_128437_(key.NBT, 10);
        LinkedHashMap<Enchantment, Integer> enchantments = new LinkedHashMap<Enchantment, Integer>(ench.size());
        int i = 0;
        while (i < ench.size()) {
            String id = ((CompoundTag)ench.get(i)).m_128461_(CraftMetaItem.ENCHANTMENTS_ID.NBT);
            int level = 0xFFFF & ((CompoundTag)ench.get(i)).m_128448_(CraftMetaItem.ENCHANTMENTS_LVL.NBT);
            Enchantment enchant = Enchantment.getByKey(CraftNamespacedKey.fromStringOrNull(id));
            if (enchant != null) {
                enchantments.put(enchant, level);
            }
            ++i;
        }
        return enchantments;
    }

    /*
     * Unable to fully structure code
     */
    static Multimap<Attribute, AttributeModifier> buildModifiers(CompoundTag tag, ItemMetaKey key) {
        modifiers = LinkedHashMultimap.create();
        if (!tag.m_128425_(key.NBT, 9)) {
            return modifiers;
        }
        mods = tag.m_128437_(key.NBT, 10);
        size = mods.size();
        i = 0;
        while (i < size) {
            block8: {
                entry = mods.m_128728_(i);
                if (entry.m_128456_() || (nmsModifier = net.minecraft.world.entity.ai.attributes.AttributeModifier.m_22212_((CompoundTag)entry)) == null) break block8;
                attribMod = CraftAttributeInstance.convert(nmsModifier);
                attributeName = entry.m_128461_(CraftMetaItem.ATTRIBUTES_IDENTIFIER.NBT);
                if (attributeName == null || attributeName.isEmpty() || (attribute = CraftAttributeMap.fromMinecraft(attributeName)) == null) break block8;
                if (!entry.m_128425_(CraftMetaItem.ATTRIBUTES_SLOT.NBT, 8)) ** GOTO lbl30
                slotName = entry.m_128461_(CraftMetaItem.ATTRIBUTES_SLOT.NBT);
                if (slotName == null || slotName.isEmpty()) {
                    modifiers.put((Object)attribute, (Object)attribMod);
                } else {
                    slot = null;
                    try {
                        slot = CraftEquipmentSlot.getSlot(EquipmentSlot.m_20747_((String)slotName.toLowerCase(Locale.ROOT)));
                    }
                    catch (IllegalArgumentException var13_13) {
                        // empty catch block
                    }
                    if (slot == null) {
                        modifiers.put((Object)attribute, (Object)attribMod);
                    } else {
                        attribMod = new AttributeModifier(attribMod.getUniqueId(), attribMod.getName(), attribMod.getAmount(), attribMod.getOperation(), slot);
lbl30:
                        // 2 sources

                        modifiers.put((Object)attribute, (Object)attribMod);
                    }
                }
            }
            ++i;
        }
        return modifiers;
    }

    CraftMetaItem(Map<String, Object> map) {
        Map nbtMap;
        String internal;
        Integer damage;
        Boolean unbreakable;
        Iterable hideFlags;
        Map blockData;
        Integer customModelData;
        this.displayName = CraftChatMessage.fromJSONOrStringOrNullToJSON(SerializableMeta.getString(map, CraftMetaItem.NAME.BUKKIT, true));
        this.locName = CraftChatMessage.fromJSONOrStringOrNullToJSON(SerializableMeta.getString(map, CraftMetaItem.LOCNAME.BUKKIT, true));
        Iterable lore = SerializableMeta.getObject(Iterable.class, map, CraftMetaItem.LORE.BUKKIT, true);
        if (lore != null) {
            this.lore = new ArrayList<String>();
            CraftMetaItem.safelyAdd(lore, this.lore, true);
        }
        if ((customModelData = SerializableMeta.getObject(Integer.class, map, CraftMetaItem.CUSTOM_MODEL_DATA.BUKKIT, true)) != null) {
            this.setCustomModelData(customModelData);
        }
        if ((blockData = SerializableMeta.getObject(Map.class, map, CraftMetaItem.BLOCK_DATA.BUKKIT, true)) != null) {
            this.blockData = (CompoundTag)CraftNBTTagConfigSerializer.deserialize(blockData);
        }
        this.enchantments = CraftMetaItem.buildEnchantments(map, ENCHANTMENTS);
        this.attributeModifiers = CraftMetaItem.buildModifiers(map, ATTRIBUTES);
        Integer repairCost = SerializableMeta.getObject(Integer.class, map, CraftMetaItem.REPAIR.BUKKIT, true);
        if (repairCost != null) {
            this.setRepairCost(repairCost);
        }
        if ((hideFlags = SerializableMeta.getObject(Iterable.class, map, CraftMetaItem.HIDEFLAGS.BUKKIT, true)) != null) {
            for (Object hideFlagObject : hideFlags) {
                String hideFlagString = (String)hideFlagObject;
                try {
                    ItemFlag hideFlatEnum = ItemFlag.valueOf(hideFlagString);
                    this.addItemFlags(hideFlatEnum);
                }
                catch (IllegalArgumentException hideFlatEnum) {
                    // empty catch block
                }
            }
        }
        if ((unbreakable = SerializableMeta.getObject(Boolean.class, map, CraftMetaItem.UNBREAKABLE.BUKKIT, true)) != null) {
            this.setUnbreakable(unbreakable);
        }
        if ((damage = SerializableMeta.getObject(Integer.class, map, CraftMetaItem.DAMAGE.BUKKIT, true)) != null) {
            this.setDamage(damage);
        }
        if ((internal = SerializableMeta.getString(map, "internal", true)) != null) {
            ByteArrayInputStream buf = new ByteArrayInputStream(Base64.getDecoder().decode(internal));
            try {
                this.internalTag = NbtIo.m_128939_((InputStream)buf);
                this.deserializeInternal(this.internalTag, map);
                Set keys = this.internalTag.m_128431_();
                for (String key : keys) {
                    if (CraftMetaItem.getHandledTags().contains(key)) continue;
                    this.unhandledTags.put(key, this.internalTag.m_128423_(key));
                }
            }
            catch (IOException ex) {
                Logger.getLogger(CraftMetaItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ((nbtMap = SerializableMeta.getObject(Map.class, map, CraftMetaItem.BUKKIT_CUSTOM_TAG.BUKKIT, true)) != null) {
            this.persistentDataContainer.putAll((CompoundTag)CraftNBTTagConfigSerializer.deserialize(nbtMap));
        }
    }

    void deserializeInternal(CompoundTag tag, Object context) {
        if (tag.m_128425_(CraftMetaItem.ATTRIBUTES.NBT, 9)) {
            this.attributeModifiers = CraftMetaItem.buildModifiers(tag, ATTRIBUTES);
        }
    }

    static Map<Enchantment, Integer> buildEnchantments(Map<String, Object> map, ItemMetaKey key) {
        Map ench = SerializableMeta.getObject(Map.class, map, key.BUKKIT, true);
        if (ench == null) {
            return null;
        }
        LinkedHashMap<Enchantment, Integer> enchantments = new LinkedHashMap<Enchantment, Integer>(ench.size());
        for (Map.Entry entry : ench.entrySet()) {
            Enchantment enchantment;
            String enchantKey = entry.getKey().toString();
            if (enchantKey.equals("SWEEPING")) {
                enchantKey = "SWEEPING_EDGE";
            }
            if ((enchantment = Enchantment.getByName(enchantKey)) == null || !(entry.getValue() instanceof Integer)) continue;
            enchantments.put(enchantment, (Integer)entry.getValue());
        }
        return enchantments;
    }

    static Multimap<Attribute, AttributeModifier> buildModifiers(Map<String, Object> map, ItemMetaKey key) {
        Map mods = SerializableMeta.getObject(Map.class, map, key.BUKKIT, true);
        LinkedHashMultimap result = LinkedHashMultimap.create();
        if (mods == null) {
            return result;
        }
        for (Object obj : mods.keySet()) {
            String attributeName;
            if (!(obj instanceof String) || Strings.isNullOrEmpty((String)(attributeName = (String)obj))) continue;
            List list = SerializableMeta.getObject(List.class, mods, attributeName, true);
            if (list == null || list.isEmpty()) {
                return result;
            }
            for (Object o : list) {
                if (!(o instanceof AttributeModifier)) continue;
                AttributeModifier modifier = (AttributeModifier)o;
                Attribute attribute = (Attribute)EnumUtils.getEnum(Attribute.class, (String)attributeName.toUpperCase(Locale.ROOT));
                if (attribute == null) continue;
                result.put((Object)attribute, (Object)modifier);
            }
        }
        return result;
    }

    @Overridden
    void applyToItem(CompoundTag itemTag) {
        if (this.hasDisplayName()) {
            this.setDisplayTag(itemTag, CraftMetaItem.NAME.NBT, (Tag)StringTag.m_129297_((String)this.displayName));
        }
        if (this.hasLocalizedName()) {
            this.setDisplayTag(itemTag, CraftMetaItem.LOCNAME.NBT, (Tag)StringTag.m_129297_((String)this.locName));
        }
        if (this.lore != null) {
            this.setDisplayTag(itemTag, CraftMetaItem.LORE.NBT, (Tag)this.createStringList(this.lore));
        }
        if (this.hasCustomModelData()) {
            itemTag.m_128405_(CraftMetaItem.CUSTOM_MODEL_DATA.NBT, this.customModelData.intValue());
        }
        if (this.hasBlockData()) {
            itemTag.m_128365_(CraftMetaItem.BLOCK_DATA.NBT, (Tag)this.blockData);
        }
        if (this.hideFlag != 0) {
            itemTag.m_128405_(CraftMetaItem.HIDEFLAGS.NBT, this.hideFlag);
        }
        CraftMetaItem.applyEnchantments(this.enchantments, itemTag, ENCHANTMENTS);
        CraftMetaItem.applyModifiers(this.attributeModifiers, itemTag, ATTRIBUTES);
        if (this.hasRepairCost()) {
            itemTag.m_128405_(CraftMetaItem.REPAIR.NBT, this.repairCost);
        }
        if (this.isUnbreakable()) {
            itemTag.m_128379_(CraftMetaItem.UNBREAKABLE.NBT, this.unbreakable);
        }
        if (this.hasDamage()) {
            itemTag.m_128405_(CraftMetaItem.DAMAGE.NBT, this.damage);
        }
        for (Map.Entry<String, Tag> e : this.unhandledTags.entrySet()) {
            itemTag.m_128365_(e.getKey(), e.getValue());
        }
        if (!this.persistentDataContainer.isEmpty()) {
            CompoundTag bukkitCustomCompound = new CompoundTag();
            Map<String, Tag> rawPublicMap = this.persistentDataContainer.getRaw();
            for (Map.Entry<String, Tag> nbtBaseEntry : rawPublicMap.entrySet()) {
                bukkitCustomCompound.m_128365_(nbtBaseEntry.getKey(), nbtBaseEntry.getValue());
            }
            itemTag.m_128365_(CraftMetaItem.BUKKIT_CUSTOM_TAG.NBT, (Tag)bukkitCustomCompound);
        }
    }

    ListTag createStringList(List<String> list) {
        if (list == null) {
            return null;
        }
        ListTag tagList = new ListTag();
        for (String value : list) {
            tagList.add((Object)StringTag.m_129297_((String)(this.version <= 0 || this.version >= 1803 ? value : CraftChatMessage.fromJSONComponent(value))));
        }
        return tagList;
    }

    static void applyEnchantments(Map<Enchantment, Integer> enchantments, CompoundTag tag, ItemMetaKey key) {
        if (enchantments == null) {
            return;
        }
        ListTag list = new ListTag();
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            CompoundTag subtag = new CompoundTag();
            subtag.m_128359_(CraftMetaItem.ENCHANTMENTS_ID.NBT, entry.getKey().getKey().toString());
            subtag.m_128376_(CraftMetaItem.ENCHANTMENTS_LVL.NBT, entry.getValue().shortValue());
            list.add((Object)subtag);
        }
        tag.m_128365_(key.NBT, (Tag)list);
    }

    static void applyModifiers(Multimap<Attribute, AttributeModifier> modifiers, CompoundTag tag, ItemMetaKey key) {
        if (modifiers == null || modifiers.isEmpty()) {
            return;
        }
        ListTag list = new ListTag();
        for (Map.Entry entry : modifiers.entries()) {
            EquipmentSlot slot;
            String name;
            net.minecraft.world.entity.ai.attributes.AttributeModifier nmsModifier;
            CompoundTag sub;
            if (entry.getKey() == null || entry.getValue() == null || (sub = (nmsModifier = CraftAttributeInstance.convert((AttributeModifier)entry.getValue())).m_22219_()).m_128456_() || (name = ((Attribute)entry.getKey()).getKey().toString()) == null || name.isEmpty()) continue;
            sub.m_128359_(CraftMetaItem.ATTRIBUTES_IDENTIFIER.NBT, name);
            if (((AttributeModifier)entry.getValue()).getSlot() != null && (slot = CraftEquipmentSlot.getNMS(((AttributeModifier)entry.getValue()).getSlot())) != null) {
                sub.m_128359_(CraftMetaItem.ATTRIBUTES_SLOT.NBT, slot.m_20751_());
            }
            list.add((Object)sub);
        }
        tag.m_128365_(key.NBT, (Tag)list);
    }

    void setDisplayTag(CompoundTag tag, String key, Tag value) {
        CompoundTag display = tag.m_128469_(CraftMetaItem.DISPLAY.NBT);
        if (!tag.m_128441_(CraftMetaItem.DISPLAY.NBT)) {
            tag.m_128365_(CraftMetaItem.DISPLAY.NBT, (Tag)display);
        }
        display.m_128365_(key, value);
    }

    @Overridden
    boolean applicableTo(Material type) {
        return type != Material.AIR;
    }

    @Overridden
    boolean isEmpty() {
        return !this.hasDisplayName() && !this.hasLocalizedName() && !this.hasEnchants() && this.lore == null && !this.hasCustomModelData() && !this.hasBlockData() && !this.hasRepairCost() && this.unhandledTags.isEmpty() && this.persistentDataContainer.isEmpty() && this.hideFlag == 0 && !this.isUnbreakable() && !this.hasDamage() && !this.hasAttributeModifiers();
    }

    @Override
    public String getDisplayName() {
        return CraftChatMessage.fromJSONComponent(this.displayName);
    }

    @Override
    public final void setDisplayName(String name) {
        this.displayName = CraftChatMessage.fromStringOrNullToJSON(name);
    }

    @Override
    public boolean hasDisplayName() {
        return this.displayName != null;
    }

    @Override
    public String getLocalizedName() {
        return CraftChatMessage.fromJSONComponent(this.locName);
    }

    @Override
    public void setLocalizedName(String name) {
        this.locName = CraftChatMessage.fromStringOrNullToJSON(name);
    }

    @Override
    public boolean hasLocalizedName() {
        return this.locName != null;
    }

    @Override
    public boolean hasLore() {
        return this.lore != null && !this.lore.isEmpty();
    }

    @Override
    public boolean hasRepairCost() {
        return this.repairCost > 0;
    }

    @Override
    public boolean hasEnchant(Enchantment ench) {
        Preconditions.checkArgument((ench != null ? 1 : 0) != 0, (Object)"Enchantment cannot be null");
        return this.hasEnchants() && this.enchantments.containsKey(ench);
    }

    @Override
    public int getEnchantLevel(Enchantment ench) {
        Integer level;
        Preconditions.checkArgument((ench != null ? 1 : 0) != 0, (Object)"Enchantment cannot be null");
        Integer n = level = this.hasEnchants() ? this.enchantments.get(ench) : null;
        if (level == null) {
            return 0;
        }
        return level;
    }

    @Override
    public Map<Enchantment, Integer> getEnchants() {
        return this.hasEnchants() ? ImmutableMap.copyOf(this.enchantments) : ImmutableMap.of();
    }

    @Override
    public boolean addEnchant(Enchantment ench, int level, boolean ignoreRestrictions) {
        Preconditions.checkArgument((ench != null ? 1 : 0) != 0, (Object)"Enchantment cannot be null");
        if (this.enchantments == null) {
            this.enchantments = new LinkedHashMap<Enchantment, Integer>(4);
        }
        if (ignoreRestrictions || level >= ench.getStartLevel() && level <= ench.getMaxLevel()) {
            Integer old = this.enchantments.put(ench, level);
            return old == null || old != level;
        }
        return false;
    }

    @Override
    public boolean removeEnchant(Enchantment ench) {
        boolean b;
        Preconditions.checkArgument((ench != null ? 1 : 0) != 0, (Object)"Enchantment cannot be null");
        boolean bl = b = this.hasEnchants() && this.enchantments.remove(ench) != null;
        if (this.enchantments != null && this.enchantments.isEmpty()) {
            this.enchantments = null;
        }
        return b;
    }

    @Override
    public boolean hasEnchants() {
        return this.enchantments != null && !this.enchantments.isEmpty();
    }

    @Override
    public boolean hasConflictingEnchant(Enchantment ench) {
        return CraftMetaItem.checkConflictingEnchants(this.enchantments, ench);
    }

    @Override
    public void addItemFlags(ItemFlag ... hideFlags) {
        ItemFlag[] itemFlagArray = hideFlags;
        int n = hideFlags.length;
        int n2 = 0;
        while (n2 < n) {
            ItemFlag f = itemFlagArray[n2];
            this.hideFlag |= this.getBitModifier(f);
            ++n2;
        }
    }

    @Override
    public void removeItemFlags(ItemFlag ... hideFlags) {
        ItemFlag[] itemFlagArray = hideFlags;
        int n = hideFlags.length;
        int n2 = 0;
        while (n2 < n) {
            ItemFlag f = itemFlagArray[n2];
            this.hideFlag &= ~this.getBitModifier(f);
            ++n2;
        }
    }

    @Override
    public Set<ItemFlag> getItemFlags() {
        EnumSet<ItemFlag> currentFlags = EnumSet.noneOf(ItemFlag.class);
        ItemFlag[] itemFlagArray = ItemFlag.values();
        int n = itemFlagArray.length;
        int n2 = 0;
        while (n2 < n) {
            ItemFlag f = itemFlagArray[n2];
            if (this.hasItemFlag(f)) {
                currentFlags.add(f);
            }
            ++n2;
        }
        return currentFlags;
    }

    @Override
    public boolean hasItemFlag(ItemFlag flag) {
        byte bitModifier = this.getBitModifier(flag);
        return (this.hideFlag & bitModifier) == bitModifier;
    }

    private byte getBitModifier(ItemFlag hideFlag) {
        return (byte)(1 << hideFlag.ordinal());
    }

    @Override
    public List<String> getLore() {
        return this.lore == null ? null : new ArrayList(Lists.transform(this.lore, CraftChatMessage::fromJSONComponent));
    }

    @Override
    public void setLore(List<String> lore) {
        if (lore == null || lore.isEmpty()) {
            this.lore = null;
        } else {
            if (this.lore == null) {
                this.lore = new ArrayList<String>(lore.size());
            } else {
                this.lore.clear();
            }
            CraftMetaItem.safelyAdd(lore, this.lore, false);
        }
    }

    @Override
    public boolean hasCustomModelData() {
        return this.customModelData != null;
    }

    @Override
    public int getCustomModelData() {
        Preconditions.checkState((boolean)this.hasCustomModelData(), (Object)"We don't have CustomModelData! Check hasCustomModelData first!");
        return this.customModelData;
    }

    @Override
    public void setCustomModelData(Integer data) {
        this.customModelData = data;
    }

    @Override
    public boolean hasBlockData() {
        return this.blockData != null;
    }

    @Override
    public BlockData getBlockData(Material material) {
        BlockState defaultData = CraftMagicNumbers.getBlock(material).m_49966_();
        return CraftBlockData.fromData(this.hasBlockData() ? BlockItem.getBlockState((BlockState)defaultData, (CompoundTag)this.blockData) : defaultData);
    }

    @Override
    public void setBlockData(BlockData blockData) {
        this.blockData = blockData == null ? null : ((CraftBlockData)blockData).toStates();
    }

    @Override
    public int getRepairCost() {
        return this.repairCost;
    }

    @Override
    public void setRepairCost(int cost) {
        this.repairCost = cost;
    }

    @Override
    public boolean isUnbreakable() {
        return this.unbreakable;
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    @Override
    public boolean hasAttributeModifiers() {
        return this.attributeModifiers != null && !this.attributeModifiers.isEmpty();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
        return this.hasAttributeModifiers() ? ImmutableMultimap.copyOf(this.attributeModifiers) : null;
    }

    private void checkAttributeList() {
        if (this.attributeModifiers == null) {
            this.attributeModifiers = LinkedHashMultimap.create();
        }
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@Nullable org.bukkit.inventory.EquipmentSlot slot) {
        this.checkAttributeList();
        LinkedHashMultimap result = LinkedHashMultimap.create();
        for (Map.Entry entry : this.attributeModifiers.entries()) {
            if (((AttributeModifier)entry.getValue()).getSlot() != null && ((AttributeModifier)entry.getValue()).getSlot() != slot) continue;
            result.put((Object)((Attribute)entry.getKey()), (Object)((AttributeModifier)entry.getValue()));
        }
        return result;
    }

    @Override
    public Collection<AttributeModifier> getAttributeModifiers(@Nonnull Attribute attribute) {
        Preconditions.checkNotNull((Object)attribute, (Object)"Attribute cannot be null");
        return this.attributeModifiers.containsKey((Object)attribute) ? ImmutableList.copyOf((Collection)this.attributeModifiers.get((Object)attribute)) : null;
    }

    @Override
    public boolean addAttributeModifier(@Nonnull Attribute attribute, @Nonnull AttributeModifier modifier) {
        Preconditions.checkNotNull((Object)attribute, (Object)"Attribute cannot be null");
        Preconditions.checkNotNull((Object)modifier, (Object)"AttributeModifier cannot be null");
        this.checkAttributeList();
        for (Map.Entry entry : this.attributeModifiers.entries()) {
            Preconditions.checkArgument((!((AttributeModifier)entry.getValue()).getUniqueId().equals(modifier.getUniqueId()) ? 1 : 0) != 0, (String)"Cannot register AttributeModifier. Modifier is already applied! %s", (Object)modifier);
        }
        return this.attributeModifiers.put((Object)attribute, (Object)modifier);
    }

    @Override
    public void setAttributeModifiers(@Nullable Multimap<Attribute, AttributeModifier> attributeModifiers) {
        if (attributeModifiers == null || attributeModifiers.isEmpty()) {
            this.attributeModifiers = LinkedHashMultimap.create();
            return;
        }
        this.checkAttributeList();
        this.attributeModifiers.clear();
        Iterator iterator = attributeModifiers.entries().iterator();
        while (iterator.hasNext()) {
            Map.Entry next = (Map.Entry)iterator.next();
            if (next.getKey() == null || next.getValue() == null) {
                iterator.remove();
                continue;
            }
            this.attributeModifiers.put((Object)((Attribute)next.getKey()), (Object)((AttributeModifier)next.getValue()));
        }
    }

    @Override
    public boolean removeAttributeModifier(@Nonnull Attribute attribute) {
        Preconditions.checkNotNull((Object)attribute, (Object)"Attribute cannot be null");
        this.checkAttributeList();
        return !this.attributeModifiers.removeAll((Object)attribute).isEmpty();
    }

    @Override
    public boolean removeAttributeModifier(@Nullable org.bukkit.inventory.EquipmentSlot slot) {
        this.checkAttributeList();
        int removed = 0;
        Iterator iter = this.attributeModifiers.entries().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            if (((AttributeModifier)entry.getValue()).getSlot() != null && ((AttributeModifier)entry.getValue()).getSlot() != slot) continue;
            iter.remove();
            ++removed;
        }
        return removed > 0;
    }

    @Override
    public boolean removeAttributeModifier(@Nonnull Attribute attribute, @Nonnull AttributeModifier modifier) {
        Preconditions.checkNotNull((Object)attribute, (Object)"Attribute cannot be null");
        Preconditions.checkNotNull((Object)modifier, (Object)"AttributeModifier cannot be null");
        this.checkAttributeList();
        int removed = 0;
        Iterator iter = this.attributeModifiers.entries().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            if (entry.getKey() == null || entry.getValue() == null) {
                iter.remove();
                ++removed;
                continue;
            }
            if (entry.getKey() != attribute || !((AttributeModifier)entry.getValue()).getUniqueId().equals(modifier.getUniqueId())) continue;
            iter.remove();
            ++removed;
        }
        return removed > 0;
    }

    @Override
    public String getAsString() {
        CompoundTag tag = new CompoundTag();
        this.applyToItem(tag);
        return tag.toString();
    }

    @Override
    public CustomItemTagContainer getCustomTagContainer() {
        return new DeprecatedCustomTagContainer(this.getPersistentDataContainer());
    }

    @Override
    public PersistentDataContainer getPersistentDataContainer() {
        return this.persistentDataContainer;
    }

    private static boolean compareModifiers(Multimap<Attribute, AttributeModifier> first, Multimap<Attribute, AttributeModifier> second) {
        if (first == null || second == null) {
            return false;
        }
        for (Map.Entry entry : first.entries()) {
            if (second.containsEntry(entry.getKey(), entry.getValue())) continue;
            return false;
        }
        for (Map.Entry entry : second.entries()) {
            if (first.containsEntry(entry.getKey(), entry.getValue())) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean hasDamage() {
        return this.damage > 0;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }

    public final boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }
        if (!(object instanceof CraftMetaItem)) {
            return false;
        }
        return CraftItemFactory.instance().equals((ItemMeta)this, (ItemMeta)object);
    }

    @Overridden
    boolean equalsCommon(CraftMetaItem that) {
        return (this.hasDisplayName() ? that.hasDisplayName() && this.displayName.equals(that.displayName) : !that.hasDisplayName()) && (this.hasLocalizedName() ? that.hasLocalizedName() && this.locName.equals(that.locName) : !that.hasLocalizedName()) && (this.hasEnchants() ? that.hasEnchants() && this.enchantments.equals(that.enchantments) : !that.hasEnchants()) && Objects.equals(this.lore, that.lore) && (this.hasCustomModelData() ? that.hasCustomModelData() && this.customModelData.equals(that.customModelData) : !that.hasCustomModelData()) && (this.hasBlockData() ? that.hasBlockData() && this.blockData.equals((Object)that.blockData) : !that.hasBlockData()) && (this.hasRepairCost() ? that.hasRepairCost() && this.repairCost == that.repairCost : !that.hasRepairCost()) && (this.hasAttributeModifiers() ? that.hasAttributeModifiers() && CraftMetaItem.compareModifiers(this.attributeModifiers, that.attributeModifiers) : !that.hasAttributeModifiers()) && this.unhandledTags.equals(that.unhandledTags) && this.persistentDataContainer.equals(that.persistentDataContainer) && this.hideFlag == that.hideFlag && this.isUnbreakable() == that.isUnbreakable() && (this.hasDamage() ? that.hasDamage() && this.damage == that.damage : !that.hasDamage()) && this.version == that.version;
    }

    @Overridden
    boolean notUncommon(CraftMetaItem meta) {
        return true;
    }

    public final int hashCode() {
        return this.applyHash();
    }

    @Overridden
    int applyHash() {
        int hash = 3;
        hash = 61 * hash + (this.hasDisplayName() ? this.displayName.hashCode() : 0);
        hash = 61 * hash + (this.hasLocalizedName() ? this.locName.hashCode() : 0);
        hash = 61 * hash + (this.lore != null ? this.lore.hashCode() : 0);
        hash = 61 * hash + (this.hasCustomModelData() ? this.customModelData.hashCode() : 0);
        hash = 61 * hash + (this.hasBlockData() ? this.blockData.hashCode() : 0);
        hash = 61 * hash + (this.hasEnchants() ? this.enchantments.hashCode() : 0);
        hash = 61 * hash + (this.hasRepairCost() ? this.repairCost : 0);
        hash = 61 * hash + this.unhandledTags.hashCode();
        hash = 61 * hash + (!this.persistentDataContainer.isEmpty() ? this.persistentDataContainer.hashCode() : 0);
        hash = 61 * hash + this.hideFlag;
        hash = 61 * hash + (this.isUnbreakable() ? 1231 : 1237);
        hash = 61 * hash + (this.hasDamage() ? this.damage : 0);
        hash = 61 * hash + (this.hasAttributeModifiers() ? this.attributeModifiers.hashCode() : 0);
        hash = 61 * hash + this.version;
        return hash;
    }

    @Override
    @Overridden
    public CraftMetaItem clone() {
        try {
            CraftMetaItem clone = (CraftMetaItem)super.clone();
            if (this.lore != null) {
                clone.lore = new ArrayList<String>(this.lore);
            }
            clone.customModelData = this.customModelData;
            clone.blockData = this.blockData;
            if (this.enchantments != null) {
                clone.enchantments = new LinkedHashMap<Enchantment, Integer>(this.enchantments);
            }
            if (this.hasAttributeModifiers()) {
                clone.attributeModifiers = LinkedHashMultimap.create(this.attributeModifiers);
            }
            clone.persistentDataContainer = new CraftPersistentDataContainer(this.persistentDataContainer.getRaw(), DATA_TYPE_REGISTRY);
            clone.hideFlag = this.hideFlag;
            clone.unbreakable = this.unbreakable;
            clone.damage = this.damage;
            clone.version = this.version;
            return clone;
        }
        catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    @Override
    public final Map<String, Object> serialize() {
        ImmutableMap.Builder map = ImmutableMap.builder();
        map.put((Object)"meta-type", SerializableMeta.classMap.get(this.getClass()));
        this.serialize((ImmutableMap.Builder<String, Object>)map);
        return map.build();
    }

    @Overridden
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        if (this.hasDisplayName()) {
            builder.put((Object)CraftMetaItem.NAME.BUKKIT, (Object)this.displayName);
        }
        if (this.hasLocalizedName()) {
            builder.put((Object)CraftMetaItem.LOCNAME.BUKKIT, (Object)this.locName);
        }
        if (this.lore != null) {
            builder.put((Object)CraftMetaItem.LORE.BUKKIT, (Object)ImmutableList.copyOf(this.lore));
        }
        if (this.hasCustomModelData()) {
            builder.put((Object)CraftMetaItem.CUSTOM_MODEL_DATA.BUKKIT, (Object)this.customModelData);
        }
        if (this.hasBlockData()) {
            builder.put((Object)CraftMetaItem.BLOCK_DATA.BUKKIT, CraftNBTTagConfigSerializer.serialize((Tag)this.blockData));
        }
        CraftMetaItem.serializeEnchantments(this.enchantments, builder, ENCHANTMENTS);
        CraftMetaItem.serializeModifiers(this.attributeModifiers, builder, ATTRIBUTES);
        if (this.hasRepairCost()) {
            builder.put((Object)CraftMetaItem.REPAIR.BUKKIT, (Object)this.repairCost);
        }
        ArrayList<String> hideFlags = new ArrayList<String>();
        for (ItemFlag hideFlagEnum : this.getItemFlags()) {
            hideFlags.add(hideFlagEnum.name());
        }
        if (!hideFlags.isEmpty()) {
            builder.put((Object)CraftMetaItem.HIDEFLAGS.BUKKIT, hideFlags);
        }
        if (this.isUnbreakable()) {
            builder.put((Object)CraftMetaItem.UNBREAKABLE.BUKKIT, (Object)this.unbreakable);
        }
        if (this.hasDamage()) {
            builder.put((Object)CraftMetaItem.DAMAGE.BUKKIT, (Object)this.damage);
        }
        HashMap<String, Tag> internalTags = new HashMap<String, Tag>(this.unhandledTags);
        this.serializeInternal(internalTags);
        if (!internalTags.isEmpty()) {
            CompoundTag internal = new CompoundTag();
            for (Map.Entry e : internalTags.entrySet()) {
                internal.m_128365_((String)e.getKey(), (Tag)e.getValue());
            }
            try {
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                NbtIo.m_128947_((CompoundTag)internal, (OutputStream)buf);
                builder.put((Object)"internal", (Object)Base64.getEncoder().encodeToString(buf.toByteArray()));
            }
            catch (IOException ex) {
                Logger.getLogger(CraftMetaItem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!this.persistentDataContainer.isEmpty()) {
            builder.put((Object)CraftMetaItem.BUKKIT_CUSTOM_TAG.BUKKIT, this.persistentDataContainer.serialize());
        }
        return builder;
    }

    void serializeInternal(Map<String, Tag> unhandledTags) {
    }

    Material updateMaterial(Material material) {
        return material;
    }

    static void serializeEnchantments(Map<Enchantment, Integer> enchantments, ImmutableMap.Builder<String, Object> builder, ItemMetaKey key) {
        if (enchantments == null || enchantments.isEmpty()) {
            return;
        }
        ImmutableMap.Builder enchants = ImmutableMap.builder();
        for (Map.Entry<Enchantment, Integer> enchant : enchantments.entrySet()) {
            enchants.put((Object)enchant.getKey().getName(), (Object)enchant.getValue());
        }
        builder.put((Object)key.BUKKIT, (Object)enchants.build());
    }

    static void serializeModifiers(Multimap<Attribute, AttributeModifier> modifiers, ImmutableMap.Builder<String, Object> builder, ItemMetaKey key) {
        if (modifiers == null || modifiers.isEmpty()) {
            return;
        }
        LinkedHashMap mods = new LinkedHashMap();
        for (Map.Entry entry : modifiers.entries()) {
            Collection modCollection;
            if (entry.getKey() == null || (modCollection = modifiers.get((Object)((Attribute)entry.getKey()))) == null || modCollection.isEmpty()) continue;
            mods.put(((Attribute)entry.getKey()).name(), new ArrayList(modCollection));
        }
        builder.put((Object)key.BUKKIT, mods);
    }

    static void safelyAdd(Iterable<?> addFrom, Collection<String> addTo, boolean possiblyJsonInput) {
        if (addFrom == null) {
            return;
        }
        for (Object object : addFrom) {
            if (!(object instanceof String)) {
                if (object != null) {
                    throw new IllegalArgumentException(addFrom + " cannot contain non-string " + object.getClass().getName());
                }
                addTo.add(CraftChatMessage.toJSON((Component)Component.m_237119_()));
                continue;
            }
            String entry = object.toString();
            if (possiblyJsonInput) {
                addTo.add(CraftChatMessage.fromJSONOrStringToJSON(entry));
                continue;
            }
            addTo.add(CraftChatMessage.fromStringToJSON(entry));
        }
    }

    static boolean checkConflictingEnchants(Map<Enchantment, Integer> enchantments, Enchantment ench) {
        if (enchantments == null || enchantments.isEmpty()) {
            return false;
        }
        for (Enchantment enchant : enchantments.keySet()) {
            if (!enchant.conflictsWith(ench)) continue;
            return true;
        }
        return false;
    }

    public final String toString() {
        return String.valueOf((String)SerializableMeta.classMap.get(this.getClass())) + "_META:" + this.serialize();
    }

    public int getVersion() {
        return this.version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static Set<String> getHandledTags() {
        Set<String> set = HANDLED_TAGS;
        synchronized (set) {
            if (HANDLED_TAGS.isEmpty()) {
                HANDLED_TAGS.addAll(Arrays.asList(CraftMetaItem.DISPLAY.NBT, CraftMetaItem.CUSTOM_MODEL_DATA.NBT, CraftMetaItem.BLOCK_DATA.NBT, CraftMetaItem.REPAIR.NBT, CraftMetaItem.ENCHANTMENTS.NBT, CraftMetaItem.HIDEFLAGS.NBT, CraftMetaItem.UNBREAKABLE.NBT, CraftMetaItem.DAMAGE.NBT, CraftMetaItem.BUKKIT_CUSTOM_TAG.NBT, CraftMetaItem.ATTRIBUTES.NBT, CraftMetaItem.ATTRIBUTES_IDENTIFIER.NBT, CraftMetaItem.ATTRIBUTES_NAME.NBT, CraftMetaItem.ATTRIBUTES_VALUE.NBT, CraftMetaItem.ATTRIBUTES_UUID_HIGH.NBT, CraftMetaItem.ATTRIBUTES_UUID_LOW.NBT, CraftMetaItem.ATTRIBUTES_SLOT.NBT, CraftMetaArmor.TRIM.NBT, CraftMetaArmor.TRIM_MATERIAL.NBT, CraftMetaArmor.TRIM_PATTERN.NBT, CraftMetaMap.MAP_SCALING.NBT, CraftMetaMap.MAP_COLOR.NBT, CraftMetaMap.MAP_ID.NBT, CraftMetaPotion.POTION_EFFECTS.NBT, CraftMetaPotion.DEFAULT_POTION.NBT, CraftMetaPotion.POTION_COLOR.NBT, CraftMetaSkull.SKULL_OWNER.NBT, CraftMetaSkull.SKULL_PROFILE.NBT, CraftMetaSpawnEgg.ENTITY_TAG.NBT, CraftMetaBlockState.BLOCK_ENTITY_TAG.NBT, CraftMetaBook.BOOK_TITLE.NBT, CraftMetaBook.BOOK_AUTHOR.NBT, CraftMetaBook.BOOK_PAGES.NBT, CraftMetaBook.RESOLVED.NBT, CraftMetaBook.GENERATION.NBT, CraftMetaFirework.FIREWORKS.NBT, CraftMetaEnchantedBook.STORED_ENCHANTMENTS.NBT, CraftMetaCharge.EXPLOSION.NBT, CraftMetaBlockState.BLOCK_ENTITY_TAG.NBT, CraftMetaKnowledgeBook.BOOK_RECIPES.NBT, CraftMetaTropicalFishBucket.VARIANT.NBT, CraftMetaAxolotlBucket.VARIANT.NBT, CraftMetaCrossbow.CHARGED.NBT, CraftMetaCrossbow.CHARGED_PROJECTILES.NBT, CraftMetaSuspiciousStew.EFFECTS.NBT, CraftMetaCompass.LODESTONE_DIMENSION.NBT, CraftMetaCompass.LODESTONE_POS.NBT, CraftMetaCompass.LODESTONE_TRACKED.NBT, CraftMetaBundle.ITEMS.NBT, CraftMetaMusicInstrument.GOAT_HORN_INSTRUMENT.NBT));
            }
            return HANDLED_TAGS;
        }
    }

    static class ItemMetaKey {
        final String BUKKIT;
        final String NBT;

        ItemMetaKey(String both) {
            this(both, both);
        }

        ItemMetaKey(String nbt, String bukkit) {
            this.NBT = nbt;
            this.BUKKIT = bukkit;
        }

        @Retention(value=RetentionPolicy.SOURCE)
        @Target(value={ElementType.FIELD})
        static @interface Specific {
            public To value();

            public static enum To {
                BUKKIT,
                NBT;

            }
        }
    }

    @SerializableAs(value="ItemMeta")
    public static final class SerializableMeta
    implements ConfigurationSerializable {
        static final String TYPE_FIELD = "meta-type";
        static final ImmutableMap<Class<? extends CraftMetaItem>, String> classMap = ImmutableMap.builder().put(CraftMetaArmor.class, (Object)"ARMOR").put(CraftMetaArmorStand.class, (Object)"ARMOR_STAND").put(CraftMetaBanner.class, (Object)"BANNER").put(CraftMetaBlockState.class, (Object)"TILE_ENTITY").put(CraftMetaBook.class, (Object)"BOOK").put(CraftMetaBookSigned.class, (Object)"BOOK_SIGNED").put(CraftMetaSkull.class, (Object)"SKULL").put(CraftMetaLeatherArmor.class, (Object)"LEATHER_ARMOR").put(CraftMetaColorableArmor.class, (Object)"COLORABLE_ARMOR").put(CraftMetaMap.class, (Object)"MAP").put(CraftMetaPotion.class, (Object)"POTION").put(CraftMetaSpawnEgg.class, (Object)"SPAWN_EGG").put(CraftMetaEnchantedBook.class, (Object)"ENCHANTED").put(CraftMetaFirework.class, (Object)"FIREWORK").put(CraftMetaCharge.class, (Object)"FIREWORK_EFFECT").put(CraftMetaKnowledgeBook.class, (Object)"KNOWLEDGE_BOOK").put(CraftMetaTropicalFishBucket.class, (Object)"TROPICAL_FISH_BUCKET").put(CraftMetaAxolotlBucket.class, (Object)"AXOLOTL_BUCKET").put(CraftMetaCrossbow.class, (Object)"CROSSBOW").put(CraftMetaSuspiciousStew.class, (Object)"SUSPICIOUS_STEW").put(CraftMetaEntityTag.class, (Object)"ENTITY_TAG").put(CraftMetaCompass.class, (Object)"COMPASS").put(CraftMetaBundle.class, (Object)"BUNDLE").put(CraftMetaMusicInstrument.class, (Object)"MUSIC_INSTRUMENT").put(CraftMetaItem.class, (Object)"UNSPECIFIC").build();
        static final ImmutableMap<String, Constructor<? extends CraftMetaItem>> constructorMap;

        static {
            ImmutableMap.Builder classConstructorBuilder = ImmutableMap.builder();
            for (Map.Entry mapping : classMap.entrySet()) {
                try {
                    classConstructorBuilder.put((Object)((String)mapping.getValue()), ((Class)mapping.getKey()).getDeclaredConstructor(Map.class));
                }
                catch (NoSuchMethodException e) {
                    throw new AssertionError((Object)e);
                }
            }
            constructorMap = classConstructorBuilder.build();
        }

        private SerializableMeta() {
        }

        public static ItemMeta deserialize(Map<String, Object> map) throws Throwable {
            Preconditions.checkArgument((map != null ? 1 : 0) != 0, (Object)"Cannot deserialize null map");
            String type = SerializableMeta.getString(map, TYPE_FIELD, false);
            Constructor constructor = (Constructor)constructorMap.get((Object)type);
            if (constructor == null) {
                throw new IllegalArgumentException(String.valueOf(type) + " is not a valid " + TYPE_FIELD);
            }
            try {
                return (ItemMeta)constructor.newInstance(map);
            }
            catch (InstantiationException e) {
                throw new AssertionError((Object)e);
            }
            catch (IllegalAccessException e) {
                throw new AssertionError((Object)e);
            }
            catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }

        @Override
        public Map<String, Object> serialize() {
            throw new AssertionError();
        }

        static String getString(Map<?, ?> map, Object field, boolean nullable) {
            return SerializableMeta.getObject(String.class, map, field, nullable);
        }

        static boolean getBoolean(Map<?, ?> map, Object field) {
            Boolean value = SerializableMeta.getObject(Boolean.class, map, field, true);
            return value != null && value != false;
        }

        static <T> T getObject(Class<T> clazz, Map<?, ?> map, Object field, boolean nullable) {
            Object object = map.get(field);
            if (clazz.isInstance(object)) {
                return clazz.cast(object);
            }
            if (object == null) {
                if (!nullable) {
                    throw new NoSuchElementException(map + " does not contain " + field);
                }
                return null;
            }
            throw new IllegalArgumentException(field + "(" + object + ") is not a valid " + clazz);
        }
    }
}

