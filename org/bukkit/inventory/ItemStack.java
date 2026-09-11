/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableMap
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Translatable;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemStack
implements Cloneable,
ConfigurationSerializable,
Translatable {
    private Material type = Material.AIR;
    private int amount = 0;
    private MaterialData data = null;
    private ItemMeta meta;

    protected ItemStack() {
    }

    public ItemStack(@NotNull Material type) {
        this(type, 1);
    }

    public ItemStack(@NotNull Material type, int amount) {
        this(type, amount, 0);
    }

    public ItemStack(@NotNull Material type, int amount, short damage) {
        this(type, amount, damage, null);
    }

    @Deprecated
    public ItemStack(@NotNull Material type, int amount, short damage, @Nullable Byte data) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        this.type = type;
        this.amount = amount;
        if (damage != 0) {
            this.setDurability(damage);
        }
        if (data != null) {
            this.createData(data);
        }
    }

    public ItemStack(@NotNull ItemStack stack) throws IllegalArgumentException {
        Preconditions.checkArgument((stack != null ? 1 : 0) != 0, (Object)"Cannot copy null stack");
        this.type = stack.getType();
        this.amount = stack.getAmount();
        if (this.type.isLegacy()) {
            this.data = stack.getData();
        }
        if (stack.hasItemMeta()) {
            this.setItemMeta0(stack.getItemMeta(), this.type);
        }
    }

    @NotNull
    public Material getType() {
        return this.type;
    }

    public void setType(@NotNull Material type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Material cannot be null");
        this.type = type;
        if (this.meta != null) {
            this.meta = Bukkit.getItemFactory().asMetaFor(this.meta, type);
        }
        if (type.isLegacy()) {
            this.createData((byte)0);
        } else {
            this.data = null;
        }
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Nullable
    public MaterialData getData() {
        Material mat = Bukkit.getUnsafe().toLegacy(this.getType());
        if (this.data == null && mat != null && mat.getData() != null) {
            this.data = mat.getNewData((byte)this.getDurability());
        }
        return this.data;
    }

    public void setData(@Nullable MaterialData data) {
        if (data == null) {
            this.data = data;
        } else {
            Material mat = Bukkit.getUnsafe().toLegacy(this.getType());
            if (data.getClass() == mat.getData() || data.getClass() == MaterialData.class) {
                this.data = data;
            } else {
                throw new IllegalArgumentException("Provided data is not of type " + mat.getData().getName() + ", found " + data.getClass().getName());
            }
        }
    }

    @Deprecated
    public void setDurability(short durability) {
        ItemMeta meta = this.getItemMeta();
        if (meta != null) {
            ((Damageable)meta).setDamage(durability);
            this.setItemMeta(meta);
        }
    }

    @Deprecated
    public short getDurability() {
        ItemMeta meta = this.getItemMeta();
        return meta == null ? (short)0 : (short)((Damageable)meta).getDamage();
    }

    public int getMaxStackSize() {
        Material material = this.getType();
        if (material != null) {
            return material.getMaxStackSize();
        }
        return -1;
    }

    private void createData(byte data) {
        this.data = this.type.getNewData(data);
    }

    public String toString() {
        StringBuilder toString = new StringBuilder("ItemStack{").append(this.getType().name()).append(" x ").append(this.getAmount());
        if (this.hasItemMeta()) {
            toString.append(", ").append(this.getItemMeta());
        }
        return toString.append('}').toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ItemStack)) {
            return false;
        }
        ItemStack stack = (ItemStack)obj;
        return this.getAmount() == stack.getAmount() && this.isSimilar(stack);
    }

    public boolean isSimilar(@Nullable ItemStack stack) {
        Material comparisonType;
        if (stack == null) {
            return false;
        }
        if (stack == this) {
            return true;
        }
        Material material = comparisonType = this.type.isLegacy() ? Bukkit.getUnsafe().fromLegacy(this.getData(), true) : this.type;
        return comparisonType == stack.getType() && this.getDurability() == stack.getDurability() && this.hasItemMeta() == stack.hasItemMeta() && (!this.hasItemMeta() || Bukkit.getItemFactory().equals(this.getItemMeta(), stack.getItemMeta()));
    }

    @NotNull
    public ItemStack clone() {
        try {
            ItemStack itemStack = (ItemStack)super.clone();
            if (this.meta != null) {
                itemStack.meta = this.meta.clone();
            }
            if (this.data != null) {
                itemStack.data = this.data.clone();
            }
            return itemStack;
        }
        catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }

    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + this.getType().hashCode();
        hash = hash * 31 + this.getAmount();
        hash = hash * 31 + (this.getDurability() & 0xFFFF);
        hash = hash * 31 + (this.hasItemMeta() ? (this.meta == null ? this.getItemMeta().hashCode() : this.meta.hashCode()) : 0);
        return hash;
    }

    public boolean containsEnchantment(@NotNull Enchantment ench) {
        return this.meta == null ? false : this.meta.hasEnchant(ench);
    }

    public int getEnchantmentLevel(@NotNull Enchantment ench) {
        return this.meta == null ? 0 : this.meta.getEnchantLevel(ench);
    }

    @NotNull
    public Map<Enchantment, Integer> getEnchantments() {
        return this.meta == null ? ImmutableMap.of() : this.meta.getEnchants();
    }

    public void addEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        Preconditions.checkArgument((enchantments != null ? 1 : 0) != 0, (Object)"Enchantments cannot be null");
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            this.addEnchantment(entry.getKey(), entry.getValue());
        }
    }

    public void addEnchantment(@NotNull Enchantment ench, int level) {
        Preconditions.checkArgument((ench != null ? 1 : 0) != 0, (Object)"Enchantment cannot be null");
        if (level < ench.getStartLevel() || level > ench.getMaxLevel()) {
            throw new IllegalArgumentException("Enchantment level is either too low or too high (given " + level + ", bounds are " + ench.getStartLevel() + " to " + ench.getMaxLevel() + ")");
        }
        if (!ench.canEnchantItem(this)) {
            throw new IllegalArgumentException("Specified enchantment cannot be applied to this itemstack");
        }
        this.addUnsafeEnchantment(ench, level);
    }

    public void addUnsafeEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
        for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
            this.addUnsafeEnchantment(entry.getKey(), entry.getValue());
        }
    }

    public void addUnsafeEnchantment(@NotNull Enchantment ench, int level) {
        ItemMeta itemMeta;
        ItemMeta itemMeta2 = itemMeta = this.meta == null ? (this.meta = Bukkit.getItemFactory().getItemMeta(this.type)) : this.meta;
        if (itemMeta != null) {
            itemMeta.addEnchant(ench, level, true);
        }
    }

    public int removeEnchantment(@NotNull Enchantment ench) {
        int level = this.getEnchantmentLevel(ench);
        if (level == 0 || this.meta == null) {
            return level;
        }
        this.meta.removeEnchant(ench);
        return level;
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("v", Bukkit.getUnsafe().getDataVersion());
        result.put("type", this.getType().name());
        if (this.getAmount() != 1) {
            result.put("amount", this.getAmount());
        }
        ItemMeta meta = this.getItemMeta();
        if (!Bukkit.getItemFactory().equals(meta, null)) {
            result.put("meta", meta);
        }
        return result;
    }

    @NotNull
    public static ItemStack deserialize(@NotNull Map<String, Object> args) {
        Object raw;
        Material type;
        int version = args.containsKey("v") ? ((Number)args.get("v")).intValue() : -1;
        short damage = 0;
        int amount = 1;
        if (args.containsKey("damage")) {
            damage = ((Number)args.get("damage")).shortValue();
        }
        if (version < 0) {
            type = Material.getMaterial("LEGACY_" + (String)args.get("type"));
            byte dataVal = type != null && type.getMaxDurability() == 0 ? (byte)damage : (byte)0;
            type = Bukkit.getUnsafe().fromLegacy(new MaterialData(type, dataVal), true);
            if (dataVal != 0) {
                damage = 0;
            }
        } else {
            type = Bukkit.getUnsafe().getMaterial((String)args.get("type"), version);
        }
        if (args.containsKey("amount")) {
            amount = ((Number)args.get("amount")).intValue();
        }
        ItemStack result = new ItemStack(type, amount, damage);
        if (args.containsKey("enchantments")) {
            Object raw2 = args.get("enchantments");
            if (raw2 instanceof Map) {
                Map map = (Map)raw2;
                for (Map.Entry entry : map.entrySet()) {
                    Enchantment enchantment = Enchantment.getByName(entry.getKey().toString());
                    if (enchantment == null || !(entry.getValue() instanceof Integer)) continue;
                    result.addUnsafeEnchantment(enchantment, (Integer)entry.getValue());
                }
            }
        } else if (args.containsKey("meta") && (raw = args.get("meta")) instanceof ItemMeta) {
            ((ItemMeta)raw).setVersion(version);
            result.setItemMeta((ItemMeta)raw);
        }
        if (version < 0 && args.containsKey("damage")) {
            result.setDurability(damage);
        }
        return result;
    }

    @Nullable
    public ItemMeta getItemMeta() {
        return this.meta == null ? Bukkit.getItemFactory().getItemMeta(this.type) : this.meta.clone();
    }

    public boolean hasItemMeta() {
        return !Bukkit.getItemFactory().equals(this.meta, null);
    }

    public boolean setItemMeta(@Nullable ItemMeta itemMeta) {
        return this.setItemMeta0(itemMeta, this.type);
    }

    private boolean setItemMeta0(@Nullable ItemMeta itemMeta, @NotNull Material material) {
        if (itemMeta == null) {
            this.meta = null;
            return true;
        }
        if (!Bukkit.getItemFactory().isApplicable(itemMeta, material)) {
            return false;
        }
        this.meta = Bukkit.getItemFactory().asMetaFor(itemMeta, material);
        Material newType = Bukkit.getItemFactory().updateMaterial(this.meta, material);
        if (this.type != newType) {
            this.type = newType;
        }
        if (this.meta == itemMeta) {
            this.meta = itemMeta.clone();
        }
        return true;
    }

    @Override
    @NotNull
    public String getTranslationKey() {
        return Bukkit.getUnsafe().getTranslationKey(this);
    }
}

