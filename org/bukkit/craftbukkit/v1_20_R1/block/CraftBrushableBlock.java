/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.entity.BrushableBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BrushableBlockEntity;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.BrushableBlock;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTable;

public class CraftBrushableBlock
extends CraftBlockEntityState<BrushableBlockEntity>
implements BrushableBlock {
    public CraftBrushableBlock(World world, BrushableBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public ItemStack getItem() {
        return CraftItemStack.asBukkitCopy(((BrushableBlockEntity)this.getSnapshot()).m_277047_());
    }

    @Override
    public void setItem(ItemStack item) {
        ((BrushableBlockEntity)this.getSnapshot()).f_276563_ = CraftItemStack.asNMSCopy(item);
    }

    @Override
    public void applyTo(BrushableBlockEntity lootable) {
        super.applyTo(lootable);
        if (((BrushableBlockEntity)this.getSnapshot()).f_276466_ == null) {
            lootable.m_277049_(null, 0L);
        }
    }

    @Override
    public LootTable getLootTable() {
        if (((BrushableBlockEntity)this.getSnapshot()).f_276466_ == null) {
            return null;
        }
        ResourceLocation key = ((BrushableBlockEntity)this.getSnapshot()).f_276466_;
        return Bukkit.getLootTable(CraftNamespacedKey.fromMinecraft(key));
    }

    @Override
    public void setLootTable(LootTable table) {
        this.setLootTable(table, this.getSeed());
    }

    @Override
    public long getSeed() {
        return ((BrushableBlockEntity)this.getSnapshot()).f_276487_;
    }

    @Override
    public void setSeed(long seed) {
        this.setLootTable(this.getLootTable(), seed);
    }

    private void setLootTable(LootTable table, long seed) {
        ResourceLocation key = table == null ? null : CraftNamespacedKey.toMinecraft(table.getKey());
        ((BrushableBlockEntity)this.getSnapshot()).m_277049_(key, seed);
    }
}

