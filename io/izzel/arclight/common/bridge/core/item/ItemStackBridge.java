/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 */
package io.izzel.arclight.common.bridge.core.item;

import net.minecraft.nbt.CompoundTag;

public interface ItemStackBridge {
    public void bridge$convertStack(int var1);

    public CompoundTag bridge$getForgeCaps();

    public void bridge$setForgeCaps(CompoundTag var1);
}

