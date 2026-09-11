/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.storage.loot.LootDataId
 *  net.minecraft.world.level.storage.loot.LootDataManager
 *  net.minecraft.world.level.storage.loot.LootDataType
 *  net.minecraft.world.level.storage.loot.LootTable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.storage.loot;

import com.google.common.collect.ImmutableMap;
import io.izzel.arclight.common.bridge.core.world.storage.loot.LootDataManagerBridge;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootDataId;
import net.minecraft.world.level.storage.loot.LootDataManager;
import net.minecraft.world.level.storage.loot.LootDataType;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LootDataManager.class})
public class LootDataManagerMixin
implements LootDataManagerBridge {
    @Shadow
    private Map<LootDataId<?>, ?> f_278415_;
    public Map<?, ResourceLocation> lootTableToKey = ImmutableMap.of();

    @Inject(method={"apply"}, at={@At(value="RETURN")})
    private void arclight$buildRev(Map<LootDataType<?>, Map<ResourceLocation, ?>> p_279426_, CallbackInfo ci) {
        ImmutableMap.Builder lootTableToKeyBuilder = ImmutableMap.builder();
        this.f_278415_.forEach((key, lootTable) -> lootTableToKeyBuilder.put(lootTable, (Object)key.f_278500_()));
        this.lootTableToKey = lootTableToKeyBuilder.buildKeepingLast();
    }

    @Override
    public boolean bridge$isRegistered(LootTable lootTable) {
        return this.lootTableToKey.containsKey(lootTable);
    }
}

