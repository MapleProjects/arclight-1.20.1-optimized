/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Multimap
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.collect.Multimap;
import org.bukkit.FeatureFlag;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.Advancement;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.CreativeCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public interface UnsafeValues {
    public Material toLegacy(Material var1);

    public Material fromLegacy(Material var1);

    public Material fromLegacy(MaterialData var1);

    public Material fromLegacy(MaterialData var1, boolean var2);

    public BlockData fromLegacy(Material var1, byte var2);

    public Material getMaterial(String var1, int var2);

    public int getDataVersion();

    public ItemStack modifyItemStack(ItemStack var1, String var2);

    public void checkSupported(PluginDescriptionFile var1) throws InvalidPluginException;

    public byte[] processClass(PluginDescriptionFile var1, String var2, byte[] var3);

    public Advancement loadAdvancement(NamespacedKey var1, String var2);

    public boolean removeAdvancement(NamespacedKey var1);

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(Material var1, EquipmentSlot var2);

    public CreativeCategory getCreativeCategory(Material var1);

    public String getBlockTranslationKey(Material var1);

    public String getItemTranslationKey(Material var1);

    public String getTranslationKey(EntityType var1);

    public String getTranslationKey(ItemStack var1);

    @Nullable
    public FeatureFlag getFeatureFlag(@NotNull NamespacedKey var1);
}

