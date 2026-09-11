/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import org.bukkit.Bukkit;
import org.bukkit.Nameable;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftContainer;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;

public abstract class CraftLootable<T extends RandomizableContainerBlockEntity>
extends CraftContainer<T>
implements Nameable,
Lootable {
    public CraftLootable(World world, T tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public void applyTo(T lootable) {
        super.applyTo(lootable);
        if (((RandomizableContainerBlockEntity)this.getSnapshot()).f_59605_ == null) {
            lootable.m_59626_(null, 0L);
        }
    }

    @Override
    public LootTable getLootTable() {
        if (((RandomizableContainerBlockEntity)this.getSnapshot()).f_59605_ == null) {
            return null;
        }
        ResourceLocation key = ((RandomizableContainerBlockEntity)this.getSnapshot()).f_59605_;
        return Bukkit.getLootTable(CraftNamespacedKey.fromMinecraft(key));
    }

    @Override
    public void setLootTable(LootTable table) {
        this.setLootTable(table, this.getSeed());
    }

    @Override
    public long getSeed() {
        return ((RandomizableContainerBlockEntity)this.getSnapshot()).f_59606_;
    }

    @Override
    public void setSeed(long seed) {
        this.setLootTable(this.getLootTable(), seed);
    }

    private void setLootTable(LootTable table, long seed) {
        ResourceLocation key = table == null ? null : CraftNamespacedKey.toMinecraft(table.getKey());
        ((RandomizableContainerBlockEntity)this.getSnapshot()).m_59626_(key, seed);
    }
}

