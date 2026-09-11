/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.inventory.meta;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ItemMeta
extends Cloneable,
ConfigurationSerializable,
PersistentDataHolder {
    public boolean hasDisplayName();

    @NotNull
    public String getDisplayName();

    public void setDisplayName(@Nullable String var1);

    public boolean hasLocalizedName();

    @NotNull
    public String getLocalizedName();

    public void setLocalizedName(@Nullable String var1);

    public boolean hasLore();

    @Nullable
    public List<String> getLore();

    public void setLore(@Nullable List<String> var1);

    public boolean hasCustomModelData();

    public int getCustomModelData();

    public void setCustomModelData(@Nullable Integer var1);

    public boolean hasEnchants();

    public boolean hasEnchant(@NotNull Enchantment var1);

    public int getEnchantLevel(@NotNull Enchantment var1);

    @NotNull
    public Map<Enchantment, Integer> getEnchants();

    public boolean addEnchant(@NotNull Enchantment var1, int var2, boolean var3);

    public boolean removeEnchant(@NotNull Enchantment var1);

    public boolean hasConflictingEnchant(@NotNull Enchantment var1);

    public void addItemFlags(ItemFlag ... var1);

    public void removeItemFlags(ItemFlag ... var1);

    @NotNull
    public Set<ItemFlag> getItemFlags();

    public boolean hasItemFlag(@NotNull ItemFlag var1);

    public boolean isUnbreakable();

    public void setUnbreakable(boolean var1);

    public boolean hasAttributeModifiers();

    @Nullable
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers();

    @NotNull
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@NotNull EquipmentSlot var1);

    @Nullable
    public Collection<AttributeModifier> getAttributeModifiers(@NotNull Attribute var1);

    public boolean addAttributeModifier(@NotNull Attribute var1, @NotNull AttributeModifier var2);

    public void setAttributeModifiers(@Nullable Multimap<Attribute, AttributeModifier> var1);

    public boolean removeAttributeModifier(@NotNull Attribute var1);

    public boolean removeAttributeModifier(@NotNull EquipmentSlot var1);

    public boolean removeAttributeModifier(@NotNull Attribute var1, @NotNull AttributeModifier var2);

    @NotNull
    public String getAsString();

    @Deprecated
    @NotNull
    public CustomItemTagContainer getCustomTagContainer();

    @Deprecated
    public void setVersion(int var1);

    @NotNull
    public ItemMeta clone();
}

