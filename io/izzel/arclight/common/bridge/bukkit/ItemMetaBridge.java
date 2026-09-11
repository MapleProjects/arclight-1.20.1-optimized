/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 */
package io.izzel.arclight.common.bridge.bukkit;

import java.util.Map;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;

public interface ItemMetaBridge {
    public CompoundTag bridge$getForgeCaps();

    public void bridge$setForgeCaps(CompoundTag var1);

    public void bridge$offerUnhandledTags(CompoundTag var1);

    public Map<String, Tag> bridge$getUnhandledTags();

    public void bridge$setUnhandledTags(Map<String, Tag> var1);
}

