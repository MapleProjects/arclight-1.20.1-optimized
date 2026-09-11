/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.entity.CampfireBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import org.bukkit.World;
import org.bukkit.block.Campfire;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;

public class CraftCampfire
extends CraftBlockEntityState<CampfireBlockEntity>
implements Campfire {
    public CraftCampfire(World world, CampfireBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public int getSize() {
        return ((CampfireBlockEntity)this.getSnapshot()).m_59065_().size();
    }

    @Override
    public org.bukkit.inventory.ItemStack getItem(int index) {
        ItemStack item = (ItemStack)((CampfireBlockEntity)this.getSnapshot()).m_59065_().get(index);
        return item.m_41619_() ? null : CraftItemStack.asCraftMirror(item);
    }

    @Override
    public void setItem(int index, org.bukkit.inventory.ItemStack item) {
        ((CampfireBlockEntity)this.getSnapshot()).m_59065_().set(index, (Object)CraftItemStack.asNMSCopy(item));
    }

    @Override
    public int getCookTime(int index) {
        return ((CampfireBlockEntity)this.getSnapshot()).f_59043_[index];
    }

    @Override
    public void setCookTime(int index, int cookTime) {
        ((CampfireBlockEntity)this.getSnapshot()).f_59043_[index] = cookTime;
    }

    @Override
    public int getCookTimeTotal(int index) {
        return ((CampfireBlockEntity)this.getSnapshot()).f_59044_[index];
    }

    @Override
    public void setCookTimeTotal(int index, int cookTimeTotal) {
        ((CampfireBlockEntity)this.getSnapshot()).f_59044_[index] = cookTimeTotal;
    }
}

