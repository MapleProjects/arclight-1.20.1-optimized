/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.SectionPos
 *  net.minecraft.server.level.ChunkMap
 *  net.minecraft.server.level.ChunkMap$TrackedEntity
 *  net.minecraft.server.level.ServerEntity
 *  net.minecraft.server.network.ServerPlayerConnection
 *  net.minecraft.world.entity.Entity
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.server.level;

import io.izzel.arclight.common.bridge.core.world.ServerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.server.ChunkMap_TrackedEntityBridge;
import java.util.Set;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChunkMap.TrackedEntity.class})
public abstract class ChunkMap_TrackedEntityMixin
implements ChunkMap_TrackedEntityBridge {
    @Shadow
    @Final
    ServerEntity f_140471_;
    @Shadow
    @Final
    public Set<ServerPlayerConnection> f_140475_;
    @Shadow
    @Final
    Entity f_140472_;
    @Shadow
    SectionPos f_140474_;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$setTrackedPlayers(ChunkMap outer, Entity entity, int range, int updateFrequency, boolean sendVelocityUpdates, CallbackInfo ci) {
        ((ServerEntityBridge)this.f_140471_).bridge$setTrackedPlayers(this.f_140475_);
    }

    @Override
    public ServerEntity bridge$getServerEntity() {
        return this.f_140471_;
    }

    @Override
    public Entity bridge$getEntity() {
        return this.f_140472_;
    }

    @Override
    public SectionPos bridge$getLastSectionPos() {
        return this.f_140474_;
    }

    @Override
    public void bridge$setLastSectionPos(SectionPos pos) {
        this.f_140474_ = pos;
    }
}

