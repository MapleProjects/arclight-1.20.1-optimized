/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.util;

import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

@ParametersAreNonnullByDefault
public class WrappedContents
extends NonNullList<ItemStack> {
    private final Container inventory;

    public WrappedContents(Container inventory) {
        super(null, null);
        this.inventory = inventory;
    }

    @NotNull
    public ItemStack get(int i) {
        return this.inventory.m_8020_(i);
    }

    public ItemStack set(int i, ItemStack stack) {
        ItemStack ret = this.inventory.m_8020_(i);
        this.inventory.m_6836_(i, stack);
        return ret;
    }

    public void add(int i, ItemStack stack) {
        if (!this.inventory.m_8020_(i).m_41619_()) {
            throw new UnsupportedOperationException();
        }
        this.inventory.m_6836_(i, stack);
    }

    public ItemStack remove(int i) {
        return this.inventory.m_8016_(i);
    }

    public int size() {
        return this.inventory.m_6643_();
    }

    public void clear() {
        this.inventory.m_6211_();
    }
}

