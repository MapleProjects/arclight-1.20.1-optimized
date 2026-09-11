/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.block.AbstractFurnaceBlock
 *  net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Furnace;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftContainer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryFurnace;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Recipe;

public abstract class CraftFurnace<T extends AbstractFurnaceBlockEntity>
extends CraftContainer<T>
implements Furnace {
    public CraftFurnace(World world, T tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public FurnaceInventory getSnapshotInventory() {
        return new CraftInventoryFurnace((AbstractFurnaceBlockEntity)this.getSnapshot());
    }

    @Override
    public FurnaceInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }
        return new CraftInventoryFurnace((AbstractFurnaceBlockEntity)this.getTileEntity());
    }

    @Override
    public short getBurnTime() {
        return (short)((AbstractFurnaceBlockEntity)this.getSnapshot()).f_58316_;
    }

    @Override
    public void setBurnTime(short burnTime) {
        ((AbstractFurnaceBlockEntity)this.getSnapshot()).f_58316_ = burnTime;
        this.data = (BlockState)this.data.m_61124_((Property)AbstractFurnaceBlock.f_48684_, (Comparable)Boolean.valueOf(burnTime > 0));
    }

    @Override
    public short getCookTime() {
        return (short)((AbstractFurnaceBlockEntity)this.getSnapshot()).f_58318_;
    }

    @Override
    public void setCookTime(short cookTime) {
        ((AbstractFurnaceBlockEntity)this.getSnapshot()).f_58318_ = cookTime;
    }

    @Override
    public int getCookTimeTotal() {
        return ((AbstractFurnaceBlockEntity)this.getSnapshot()).f_58319_;
    }

    @Override
    public void setCookTimeTotal(int cookTimeTotal) {
        ((AbstractFurnaceBlockEntity)this.getSnapshot()).f_58319_ = cookTimeTotal;
    }

    @Override
    public Map<CookingRecipe<?>, Integer> getRecipesUsed() {
        ImmutableMap.Builder recipesUsed = ImmutableMap.builder();
        for (Map.Entry entrySet : ((AbstractFurnaceBlockEntity)this.getSnapshot()).getRecipesUsed().object2IntEntrySet()) {
            Recipe recipe = Bukkit.getRecipe(CraftNamespacedKey.fromMinecraft((ResourceLocation)entrySet.getKey()));
            if (!(recipe instanceof CookingRecipe var5_5)) continue;
            recipesUsed.put((Object)cookingRecipe, (Object)((Integer)entrySet.getValue()));
        }
        return recipesUsed.build();
    }
}

