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
 *  net.minecraft.world.item.ArrowItem
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
import net.minecraft.world.item.ArrowItem;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.DelegateDeserialization;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMetaItem;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CrossbowMeta;

@DelegateDeserialization(value=CraftMetaItem.SerializableMeta.class)
public class CraftMetaCrossbow
extends CraftMetaItem
implements CrossbowMeta {
    static final CraftMetaItem.ItemMetaKey CHARGED = new CraftMetaItem.ItemMetaKey("Charged", "charged");
    static final CraftMetaItem.ItemMetaKey CHARGED_PROJECTILES = new CraftMetaItem.ItemMetaKey("ChargedProjectiles", "charged-projectiles");
    private boolean charged;
    private List<ItemStack> chargedProjectiles;

    public CraftMetaCrossbow(CraftMetaItem meta) {
        super(meta);
        if (!(meta instanceof CraftMetaCrossbow)) {
            return;
        }
        CraftMetaCrossbow crossbow = (CraftMetaCrossbow)meta;
        this.charged = crossbow.charged;
        if (crossbow.hasChargedProjectiles()) {
            this.chargedProjectiles = new ArrayList<ItemStack>(crossbow.chargedProjectiles);
        }
    }

    CraftMetaCrossbow(CompoundTag tag) {
        super(tag);
        ListTag list;
        this.charged = tag.m_128471_(CraftMetaCrossbow.CHARGED.NBT);
        if (tag.m_128425_(CraftMetaCrossbow.CHARGED_PROJECTILES.NBT, 9) && (list = tag.m_128437_(CraftMetaCrossbow.CHARGED_PROJECTILES.NBT, 10)) != null && !list.isEmpty()) {
            this.chargedProjectiles = new ArrayList<ItemStack>();
            int i = 0;
            while (i < list.size()) {
                CompoundTag nbttagcompound1 = list.m_128728_(i);
                this.chargedProjectiles.add(CraftItemStack.asCraftMirror(net.minecraft.world.item.ItemStack.m_41712_((CompoundTag)nbttagcompound1)));
                ++i;
            }
        }
    }

    CraftMetaCrossbow(Map<String, Object> map) {
        super(map);
        Iterable projectiles;
        Boolean charged = CraftMetaItem.SerializableMeta.getObject(Boolean.class, map, CraftMetaCrossbow.CHARGED.BUKKIT, true);
        if (charged != null) {
            this.charged = charged;
        }
        if ((projectiles = CraftMetaItem.SerializableMeta.getObject(Iterable.class, map, CraftMetaCrossbow.CHARGED_PROJECTILES.BUKKIT, true)) != null) {
            for (Object stack : projectiles) {
                if (!(stack instanceof ItemStack)) continue;
                this.addChargedProjectile((ItemStack)stack);
            }
        }
    }

    @Override
    void applyToItem(CompoundTag tag) {
        super.applyToItem(tag);
        tag.m_128379_(CraftMetaCrossbow.CHARGED.NBT, this.charged);
        if (this.hasChargedProjectiles()) {
            ListTag list = new ListTag();
            for (ItemStack item : this.chargedProjectiles) {
                CompoundTag saved = new CompoundTag();
                CraftItemStack.asNMSCopy(item).m_41739_(saved);
                list.add((Object)saved);
            }
            tag.m_128365_(CraftMetaCrossbow.CHARGED_PROJECTILES.NBT, (Tag)list);
        }
    }

    @Override
    boolean applicableTo(Material type) {
        return type == Material.CROSSBOW;
    }

    @Override
    boolean isEmpty() {
        return super.isEmpty() && this.isCrossbowEmpty();
    }

    boolean isCrossbowEmpty() {
        return !this.hasChargedProjectiles();
    }

    @Override
    public boolean hasChargedProjectiles() {
        return this.chargedProjectiles != null;
    }

    @Override
    public List<ItemStack> getChargedProjectiles() {
        return this.chargedProjectiles == null ? ImmutableList.of() : ImmutableList.copyOf(this.chargedProjectiles);
    }

    @Override
    public void setChargedProjectiles(List<ItemStack> projectiles) {
        this.chargedProjectiles = null;
        this.charged = false;
        if (projectiles == null) {
            return;
        }
        for (ItemStack i : projectiles) {
            this.addChargedProjectile(i);
        }
    }

    @Override
    public void addChargedProjectile(ItemStack item) {
        Preconditions.checkArgument((item != null ? 1 : 0) != 0, (Object)"item");
        Preconditions.checkArgument((item.getType() == Material.FIREWORK_ROCKET || CraftMagicNumbers.getItem(item.getType()) instanceof ArrowItem ? 1 : 0) != 0, (String)"Item %s is not an arrow or firework rocket", (Object)item);
        if (this.chargedProjectiles == null) {
            this.chargedProjectiles = new ArrayList<ItemStack>();
        }
        this.charged = true;
        this.chargedProjectiles.add(item);
    }

    @Override
    boolean equalsCommon(CraftMetaItem meta) {
        if (!super.equalsCommon(meta)) {
            return false;
        }
        if (meta instanceof CraftMetaCrossbow) {
            CraftMetaCrossbow that = (CraftMetaCrossbow)meta;
            return this.charged == that.charged && (this.hasChargedProjectiles() ? that.hasChargedProjectiles() && this.chargedProjectiles.equals(that.chargedProjectiles) : !that.hasChargedProjectiles());
        }
        return true;
    }

    @Override
    boolean notUncommon(CraftMetaItem meta) {
        return super.notUncommon(meta) && (meta instanceof CraftMetaCrossbow || this.isCrossbowEmpty());
    }

    @Override
    int applyHash() {
        int original;
        int hash = original = super.applyHash();
        if (this.hasChargedProjectiles()) {
            hash = 61 * hash + (this.charged ? 1 : 0);
            hash = 61 * hash + this.chargedProjectiles.hashCode();
        }
        return original != hash ? CraftMetaCrossbow.class.hashCode() ^ hash : hash;
    }

    @Override
    public CraftMetaCrossbow clone() {
        return (CraftMetaCrossbow)super.clone();
    }

    @Override
    ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
        super.serialize(builder);
        builder.put((Object)CraftMetaCrossbow.CHARGED.BUKKIT, (Object)this.charged);
        if (this.hasChargedProjectiles()) {
            builder.put((Object)CraftMetaCrossbow.CHARGED_PROJECTILES.BUKKIT, this.chargedProjectiles);
        }
        return builder;
    }
}

