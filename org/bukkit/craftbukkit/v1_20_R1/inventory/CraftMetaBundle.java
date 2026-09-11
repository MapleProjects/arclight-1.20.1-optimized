/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.item.ItemStack
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BundleMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaBundle
extends CraftMetaItem
implements BundleMeta {
    static final CraftMetaItem.ItemMetaKey ITEMS = new CraftMetaItem.ItemMetaKey("Items", "items");
    private List<ItemStack> items;

    CraftMetaBundle(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaBundle)) {
            return;
        }
        CraftMetaBundle bundle = (CraftMetaBundle)meta;
        if (bundle.hasItems()) {
            this.items = new ArrayList<ItemStack>(bundle.items);
        }
    }

    CraftMetaBundle(CompoundTag tag) {
        super(tag);
        ListTag list;
        if (tag.m_128425_(CraftMetaBundle.ITEMS.NBT, 9) && (list = tag.m_128437_(CraftMetaBundle.ITEMS.NBT, 10)) != null && !list.isEmpty()) {
            this.items = new ArrayList<ItemStack>();
            int i = 0;
            while (i < list.size()) {
                CompoundTag nbttagcompound1 = list.m_128728_(i);
                CraftItemStack itemStack = CraftItemStack.asCraftMirror(net.minecraft.world.item.ItemStack.m_41712_((CompoundTag)nbttagcompound1));
                if (!((ItemStack)itemStack).getType().isAir()) {
                    this.addItem(itemStack);
                }
                ++i;
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    CraftMetaBundle(Map<String, Object> map) {
        super(map);
        Iterable items = CraftMetaItem.SerializableMeta.getObject(Iterable.class, map, CraftMetaBundle.ITEMS.BUKKIT, true);
        if (items != null) {
            for (Object stack : items) {
                void itemStack;
                ItemStack itemStack2;
                Object t = stack;
                if (!(t instanceof ItemStack) || (itemStack2 = (ItemStack)t) != (ItemStack)t || itemStack.getType().isAir()) continue;
                this.addItem((ItemStack)itemStack);
            }
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        if (this.hasItems()) {
            ListTag list = new ListTag();
            for (ItemStack item : this.items) {
                CompoundTag saved = new CompoundTag();
                CraftItemStack.asNMSCopy(item).m_41739_(saved);
                list.add((Object)saved);
            }
            tag.m_128365_(CraftMetaBundle.ITEMS.NBT, (Tag)list);
        }
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.BUNDLE;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isBundleEmpty();
    }

    boolean isBundleEmpty() {
        return !this.hasItems();
    }

    @Override
    public boolean hasItems() {
        return this.items != null && !this.items.isEmpty();
    }

    @Override
    public List<ItemStack> getItems() {
        return this.items == null ? ImmutableList.of() : ImmutableList.copyOf(this.items);
    }

    @Override
    public void setItems(List<ItemStack> items) {
        this.items = null;
        if (items == null) {
            return;
        }
        for (ItemStack i : items) {
            this.addItem(i);
        }
    }

    @Override
    public void addItem(ItemStack item) {
        Preconditions.checkArgument((item != null && !item.getType().isAir() ? 1 : 0) != 0, (Object)"item is null or air");
        if (this.items == null) {
            this.items = new ArrayList<ItemStack>();
        }
        this.items.add(item);
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaBundle) {
            CraftMetaBundle that = (CraftMetaBundle)meta;
            return this.hasItems() ? that.hasItems() && this.items.equals(that.items) : !that.hasItems();
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaBundle || this.isBundleEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasItems()) {
            hash = 61 * hash + this.items.hashCode();
        }
        return original != hash ? CraftMetaBundle.class.hashCode() ^ hash : hash;
    }

    @Override
    public CraftMetaBundle clone() {
        return (CraftMetaBundle)super.clone();
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        if (this.hasItems()) {
            builder.put((Object)CraftMetaBundle.ITEMS.BUKKIT, this.items);
        }
        return builder;
    }
}

