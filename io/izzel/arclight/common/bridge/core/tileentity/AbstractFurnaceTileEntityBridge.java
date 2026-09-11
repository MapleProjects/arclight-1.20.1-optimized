/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 */
package io.izzel.arclight.common.bridge.core.tileentity;

import java.util.List;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public interface AbstractFurnaceTileEntityBridge {
    public List<Recipe<?>> bridge$dropExp(ServerPlayer var1, ItemStack var2, int var3);

    public int bridge$getBurnDuration(ItemStack var1);

    public boolean bridge$isLit();
}

