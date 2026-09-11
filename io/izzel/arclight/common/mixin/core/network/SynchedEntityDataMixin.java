/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket
 *  net.minecraft.network.syncher.EntityDataAccessor
 *  net.minecraft.network.syncher.SynchedEntityData
 *  net.minecraft.network.syncher.SynchedEntityData$DataItem
 *  net.minecraft.network.syncher.SynchedEntityData$DataValue
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.network;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.network.datasync.SynchedEntityDataBridge;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SynchedEntityData.class})
public abstract class SynchedEntityDataMixin
implements SynchedEntityDataBridge {
    @Shadow
    private boolean f_135348_;
    @Shadow
    @Final
    private Entity f_135344_;

    @Shadow
    protected abstract <T> SynchedEntityData.DataItem<T> m_135379_(EntityDataAccessor<T> var1);

    @Shadow
    @Nullable
    public abstract List<SynchedEntityData.DataValue<?>> m_252804_();

    @Shadow
    public abstract boolean m_135388_();

    @Inject(method={"set(Lnet/minecraft/network/syncher/EntityDataAccessor;Ljava/lang/Object;Z)V"}, at={@At(value="HEAD")})
    private <T> void arclight$syncHealth(EntityDataAccessor<T> key, T value, boolean b, CallbackInfo ci) {
        if (key == LivingEntity.f_20961_ && this.f_135344_ instanceof ServerPlayerEntityBridge && ((ServerPlayerEntityBridge)this.f_135344_).bridge$initialized()) {
            CraftPlayer player = ((ServerPlayerEntityBridge)this.f_135344_).bridge$getBukkitEntity();
            player.setRealHealth(((Float)value).floatValue());
        }
    }

    public <T> void markDirty(EntityDataAccessor<T> key) {
        SynchedEntityData.DataItem<T> entry = this.m_135379_(key);
        entry.m_135401_(true);
        this.f_135348_ = true;
    }

    @Override
    public <T> void bridge$markDirty(EntityDataAccessor<T> key) {
        this.markDirty(key);
    }

    public void refresh(ServerPlayer player) {
        List<SynchedEntityData.DataValue<?>> list;
        if (!this.m_135388_() && (list = this.m_252804_()) != null) {
            player.f_8906_.m_9829_((Packet)new ClientboundSetEntityDataPacket(this.f_135344_.m_19879_(), list));
        }
    }

    @Override
    public void bridge$refresh(ServerPlayer player) {
        this.refresh(player);
    }
}

