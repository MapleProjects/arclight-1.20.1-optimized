/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

public class CraftInventoryCrafting
extends CraftInventory
implements CraftingInventory {
    private final Container resultInventory;

    public CraftInventoryCrafting(Container inventory, Container resultInventory) {
        super(inventory);
        this.resultInventory = resultInventory;
    }

    public Container getResultInventory() {
        return this.resultInventory;
    }

    public Container getMatrixInventory() {
        return this.inventory;
    }

    @Override
    public int getSize() {
        return this.getResultInventory().m_6643_() + this.getMatrixInventory().m_6643_();
    }

    @Override
    public void setContents(ItemStack[] items) {
        Preconditions.checkArgument((items.length <= this.getSize() ? 1 : 0) != 0, (String)"Invalid inventory size (%s); expected %s or less", (int)items.length, (int)this.getSize());
        this.setContents(items[0], Arrays.copyOfRange(items, 1, items.length));
    }

    @Override
    public ItemStack[] getContents() {
        ItemStack[] items = new ItemStack[this.getSize()];
        List mcResultItems = this.getResultInventory().getContents();
        int i = 0;
        i = 0;
        while (i < mcResultItems.size()) {
            items[i] = CraftItemStack.asCraftMirror((net.minecraft.world.item.ItemStack)mcResultItems.get(i));
            ++i;
        }
        List mcItems = this.getMatrixInventory().getContents();
        int j = 0;
        while (j < mcItems.size()) {
            items[i + j] = CraftItemStack.asCraftMirror((net.minecraft.world.item.ItemStack)mcItems.get(j));
            ++j;
        }
        return items;
    }

    public void setContents(ItemStack result, ItemStack[] contents) {
        this.setResult(result);
        this.setMatrix(contents);
    }

    @Override
    public CraftItemStack getItem(int index) {
        if (index < this.getResultInventory().m_6643_()) {
            net.minecraft.world.item.ItemStack item = this.getResultInventory().m_8020_(index);
            return item.m_41619_() ? null : CraftItemStack.asCraftMirror(item);
        }
        net.minecraft.world.item.ItemStack item = this.getMatrixInventory().m_8020_(index - this.getResultInventory().m_6643_());
        return item.m_41619_() ? null : CraftItemStack.asCraftMirror(item);
    }

    @Override
    public void setItem(int index, ItemStack item) {
        if (index < this.getResultInventory().m_6643_()) {
            this.getResultInventory().m_6836_(index, CraftItemStack.asNMSCopy(item));
        } else {
            this.getMatrixInventory().m_6836_(index - this.getResultInventory().m_6643_(), CraftItemStack.asNMSCopy(item));
        }
    }

    @Override
    public ItemStack[] getMatrix() {
        List matrix = this.getMatrixInventory().getContents();
        return this.asCraftMirror(matrix);
    }

    @Override
    public ItemStack getResult() {
        net.minecraft.world.item.ItemStack item = this.getResultInventory().m_8020_(0);
        if (!item.m_41619_()) {
            return CraftItemStack.asCraftMirror(item);
        }
        return null;
    }

    @Override
    public void setMatrix(ItemStack[] contents) {
        Preconditions.checkArgument((contents.length <= this.getMatrixInventory().m_6643_() ? 1 : 0) != 0, (String)"Invalid inventory size (%s); expected %s or less", (int)contents.length, (int)this.getMatrixInventory().m_6643_());
        int i = 0;
        while (i < this.getMatrixInventory().m_6643_()) {
            if (i < contents.length) {
                this.getMatrixInventory().m_6836_(i, CraftItemStack.asNMSCopy(contents[i]));
            } else {
                this.getMatrixInventory().m_6836_(i, net.minecraft.world.item.ItemStack.f_41583_);
            }
            ++i;
        }
    }

    @Override
    public void setResult(ItemStack item) {
        List contents = this.getResultInventory().getContents();
        contents.set(0, CraftItemStack.asNMSCopy(item));
    }

    @Override
    public org.bukkit.inventory.Recipe getRecipe() {
        Recipe recipe = this.getInventory().getCurrentRecipe();
        return recipe == null ? null : recipe.toBukkitRecipe();
    }
}

