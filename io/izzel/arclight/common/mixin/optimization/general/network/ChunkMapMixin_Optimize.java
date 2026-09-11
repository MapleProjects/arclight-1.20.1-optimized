/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.objects.ObjectArraySet
 *  it.unimi.dsi.fastutil.objects.ObjectCollection
 *  it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet
 *  net.minecraft.core.SectionPos
 *  net.minecraft.server.level.ChunkMap
 *  net.minecraft.server.level.ChunkMap$TrackedEntity
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.server.network.ServerPlayerConnection
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.entity.EntityAccess
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.optimization.general.network;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.server.ChunkMap_TrackedEntityBridge;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import it.unimi.dsi.fastutil.objects.ObjectCollection;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import net.minecraft.core.SectionPos;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.entity.EntityAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChunkMap.class})
public class ChunkMapMixin_Optimize {
    @Shadow
    @Final
    public Int2ObjectMap<ChunkMap.TrackedEntity> f_140150_;
    @Shadow
    @Final
    public ServerLevel f_140133_;

    @Redirect(method={"move"}, at=@At(value="INVOKE", remap=false, target="Lit/unimi/dsi/fastutil/ints/Int2ObjectMap;values()Lit/unimi/dsi/fastutil/objects/ObjectCollection;"))
    private ObjectCollection<ChunkMap.TrackedEntity> arclight$markDirty(Int2ObjectMap<ChunkMap.TrackedEntity> instance, ServerPlayer player) {
        ((ServerPlayerEntityBridge)player).bridge$setTrackerDirty(true);
        return new ObjectArraySet();
    }

    @Inject(method={"tick()V"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$optimizedTick(CallbackInfo ci) {
        Entity entity;
        ArrayList<ChunkMap.TrackedEntity> list = new ArrayList<ChunkMap.TrackedEntity>(this.f_140133_.m_6907_().size());
        for (ChunkMap.TrackedEntity trackedEntity : this.f_140150_.values()) {
            ServerPlayer player;
            entity = ((ChunkMap_TrackedEntityBridge)trackedEntity).bridge$getEntity();
            if (entity instanceof ServerPlayer && ((ServerPlayerEntityBridge)(player = (ServerPlayer)entity)).bridge$isTrackerDirty()) {
                list.add(trackedEntity);
                ((ServerPlayerEntityBridge)player).bridge$setTrackerDirty(false);
            }
            ((ChunkMap_TrackedEntityBridge)trackedEntity).bridge$getServerEntity().m_8533_();
        }
        for (ChunkMap.TrackedEntity trackedEntity : this.f_140150_.values()) {
            boolean chunkChanged;
            entity = ((ChunkMap_TrackedEntityBridge)trackedEntity).bridge$getEntity();
            SectionPos lastSectionPos = ((ChunkMap_TrackedEntityBridge)trackedEntity).bridge$getLastSectionPos();
            SectionPos newSectionPos = SectionPos.m_235861_((EntityAccess)entity);
            ((ChunkMap_TrackedEntityBridge)trackedEntity).bridge$setLastSectionPos(newSectionPos);
            if (entity instanceof ServerPlayer) {
                ServerPlayer player = (ServerPlayer)entity;
                for (ChunkMap.TrackedEntity otherTracker : list) {
                    ServerPlayer serverPlayer = (ServerPlayer)((ChunkMap_TrackedEntityBridge)otherTracker).bridge$getEntity();
                    if (serverPlayer.m_19879_() <= entity.m_19879_()) continue;
                    trackedEntity.m_140497_(serverPlayer);
                    otherTracker.m_140497_(player);
                }
                continue;
            }
            boolean bl = chunkChanged = !Objects.equals(lastSectionPos, newSectionPos);
            if (chunkChanged) {
                trackedEntity.m_140487_(this.f_140133_.m_6907_());
                continue;
            }
            for (ChunkMap.TrackedEntity trackedEntity2 : list) {
                trackedEntity.m_140497_((ServerPlayer)((ChunkMap_TrackedEntityBridge)trackedEntity2).bridge$getEntity());
            }
        }
        ci.cancel();
    }

    @Mixin(value={ChunkMap.TrackedEntity.class})
    public static class TrackedEntityMixin {
        @Redirect(method={"<init>"}, at=@At(value="INVOKE", remap=false, target="Lcom/google/common/collect/Sets;newIdentityHashSet()Ljava/util/Set;"))
        private Set<ServerPlayerConnection> arclight$useFastUtilSet() {
            return new ReferenceOpenHashSet();
        }
    }
}

