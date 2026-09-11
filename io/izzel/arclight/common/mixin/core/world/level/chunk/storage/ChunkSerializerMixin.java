/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.ai.village.poi.PoiManager
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.chunk.ChunkAccess
 *  net.minecraft.world.level.chunk.storage.ChunkSerializer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.chunk.storage;

import io.izzel.arclight.common.bridge.core.world.chunk.ChunkAccessBridge;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ChunkSerializer.class})
public class ChunkSerializerMixin {
    @Redirect(method={"read"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/chunk/ChunkAccess;setLightCorrect(Z)V"))
    private static void arclight$loadPersistent(ChunkAccess instance, boolean correct, ServerLevel level, PoiManager poiManager, ChunkPos pos, CompoundTag tag) {
        Tag persistentBase = tag.m_128423_("ChunkBukkitValues");
        if (persistentBase instanceof CompoundTag) {
            ((CraftPersistentDataContainer)((ChunkAccessBridge)instance).bridge$getPersistentDataContainer()).putAll((CompoundTag)persistentBase);
        }
        instance.m_8094_(correct);
    }

    @Inject(method={"write"}, at={@At(value="RETURN")})
    private static void arclight$savePersistent(ServerLevel level, ChunkAccess chunkAccess, CallbackInfoReturnable<CompoundTag> cir) {
        CraftPersistentDataContainer container = (CraftPersistentDataContainer)((ChunkAccessBridge)chunkAccess).bridge$getPersistentDataContainer();
        if (!container.isEmpty()) {
            ((CompoundTag)cir.getReturnValue()).m_128365_("ChunkBukkitValues", (Tag)container.toTagCompound());
        }
    }
}

