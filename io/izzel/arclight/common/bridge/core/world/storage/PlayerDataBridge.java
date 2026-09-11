/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 */
package io.izzel.arclight.common.bridge.core.world.storage;

import java.io.File;
import net.minecraft.nbt.CompoundTag;

public interface PlayerDataBridge {
    public File bridge$getPlayerDir();

    public CompoundTag bridge$getPlayerData(String var1);
}

