/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  io.izzel.arclight.api.EnumHelper
 *  io.izzel.arclight.api.Unsafe
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParam
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 */
package io.izzel.arclight.common.mod;

import com.google.common.collect.ImmutableList;
import io.izzel.arclight.api.EnumHelper;
import io.izzel.arclight.api.Unsafe;
import java.util.List;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.bukkit.TreeType;

public class ArclightConstants {
    public static final TreeType MOD = (TreeType)((Object)EnumHelper.addEnum(TreeType.class, (String)"MOD", (List)ImmutableList.of(), (List)ImmutableList.of()));
    public static final LootContextParam<Integer> LOOTING_MOD = (LootContextParam)Unsafe.getStatic(LootContextParams.class, (String)"LOOTING_MOD");
    public static final int ARCLIGHT_DIMENSION = -1564403385;
    public static int currentTick;
}

