/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Slice
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import io.izzel.arclight.common.bridge.core.entity.EntityTypeBridge;
import io.izzel.arclight.common.bridge.core.world.IWorldWriterBridge;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={EntityType.class})
public abstract class EntityTypeMixin<T extends Entity>
implements EntityTypeBridge<T> {
    @Shadow
    @javax.annotation.Nullable
    public abstract T m_262451_(ServerLevel var1, @Nullable CompoundTag var2, @Nullable Consumer<T> var3, BlockPos var4, MobSpawnType var5, boolean var6, boolean var7);

    @Inject(method={"spawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/MobSpawnType;ZZ)Lnet/minecraft/world/entity/Entity;"}, at={@At(value="HEAD")})
    private void arclight$spawnReason(ServerLevel worldIn, ItemStack p_20594_, Player p_20595_, BlockPos p_20596_, MobSpawnType p_20597_, boolean p_20598_, boolean p_20599_, CallbackInfoReturnable<T> cir) {
        CreatureSpawnEvent.SpawnReason spawnReason = ((IWorldWriterBridge)worldIn).bridge$getAddEntityReason();
        if (spawnReason == null) {
            ((IWorldWriterBridge)worldIn).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.SPAWNER_EGG);
        }
    }

    @Inject(method={"spawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/nbt/CompoundTag;Ljava/util/function/Consumer;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/MobSpawnType;ZZ)Lnet/minecraft/world/entity/Entity;"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="RETURN")}, slice={@Slice(from=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V"))})
    private void arclight$returnIfSuccess(ServerLevel p_262704_, CompoundTag p_262603_, Consumer<T> p_262621_, BlockPos p_262672_, MobSpawnType p_262644_, boolean p_262690_, boolean p_262590_, CallbackInfoReturnable<T> cir, T t) {
        if (t != null) {
            cir.setReturnValue(t.m_213877_() ? null : t);
        }
    }

    public T spawn(ServerLevel p_262634_, BlockPos p_262707_, MobSpawnType p_262597_, CreatureSpawnEvent.SpawnReason spawnReason) {
        return this.spawn(p_262634_, null, null, p_262707_, p_262597_, false, false, spawnReason);
    }

    public T spawn(ServerLevel p_262704_, @javax.annotation.Nullable CompoundTag p_262603_, @javax.annotation.Nullable Consumer<T> p_262621_, BlockPos p_262672_, MobSpawnType p_262644_, boolean p_262690_, boolean p_262590_, CreatureSpawnEvent.SpawnReason spawnReason) {
        T t = this.m_262451_(p_262704_, p_262603_, p_262621_, p_262672_, p_262644_, p_262690_, p_262590_);
        if (t != null) {
            ((IWorldWriterBridge)p_262704_).bridge$pushAddEntityReason(spawnReason);
            p_262704_.m_47205_(t);
            return t.m_213877_() ? null : (T)t;
        }
        return null;
    }

    @Override
    public T bridge$spawnCreature(ServerLevel worldIn, BlockPos pos, MobSpawnType mobSpawnType, CreatureSpawnEvent.SpawnReason spawnReason) {
        return this.spawn(worldIn, pos, mobSpawnType, spawnReason);
    }
}

