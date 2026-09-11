/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.ChunkPos
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.chunk.storage.EntityStorage
 *  net.minecraft.world.level.entity.ChunkEntities
 *  net.minecraft.world.level.entity.EntityAccess
 *  net.minecraft.world.level.entity.EntityPersistentStorage
 *  net.minecraft.world.level.entity.EntitySection
 *  net.minecraft.world.level.entity.EntitySectionStorage
 *  net.minecraft.world.level.entity.PersistentEntitySectionManager
 *  net.minecraft.world.level.entity.PersistentEntitySectionManager$ChunkLoadStatus
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Coerce
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.entity;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.storage.EntityStorage;
import net.minecraft.world.level.entity.ChunkEntities;
import net.minecraft.world.level.entity.EntityAccess;
import net.minecraft.world.level.entity.EntityPersistentStorage;
import net.minecraft.world.level.entity.EntitySection;
import net.minecraft.world.level.entity.EntitySectionStorage;
import net.minecraft.world.level.entity.PersistentEntitySectionManager;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.world.EntitiesLoadEvent;
import org.bukkit.event.world.EntitiesUnloadEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={PersistentEntitySectionManager.class})
public abstract class PersistentEntitySectionManagerMixin<T extends EntityAccess> {
    @Shadow
    @Final
    private EntityPersistentStorage<T> f_157493_;
    @Shadow
    @Final
    EntitySectionStorage<T> f_157495_;
    @Shadow
    @Final
    private Long2ObjectMap<PersistentEntitySectionManager.ChunkLoadStatus> f_157498_;
    @Unique
    private boolean arclight$fireEvent = false;

    @Shadow
    public abstract void close() throws IOException;

    public void close(boolean save) throws IOException {
        if (save) {
            this.close();
        } else {
            this.f_157493_.close();
        }
    }

    public List<Entity> getEntities(ChunkPos chunkCoordIntPair) {
        return this.f_157495_.m_156888_(chunkCoordIntPair.m_45588_()).flatMap(EntitySection::m_156845_).map(o -> (Entity)o).collect(Collectors.toList());
    }

    public boolean isPending(long cord) {
        return this.f_157498_.get(cord) == PersistentEntitySectionManager.ChunkLoadStatus.PENDING;
    }

    /*
     * WARNING - void declaration
     */
    @Inject(method={"storeChunkSections"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/entity/EntityPersistentStorage;storeEntities(Lnet/minecraft/world/level/entity/ChunkEntities;)V")})
    private void arclight$fireUnload(long l, Consumer<T> consumer, CallbackInfoReturnable<Boolean> callbackInfoReturnable, @Coerce Object object, List<T> list) {
        if (EntitiesUnloadEvent.getHandlerList().getRegisteredListeners().length == 0) {
            return;
        }
        if (this.arclight$fireEvent) {
            void list2;
            void pos;
            CraftEventFactory.callEntitiesUnloadEvent((Level)((EntityStorage)this.f_157493_).f_156538_, new ChunkPos((long)pos), list2.stream().map(entity -> (Entity)entity).collect(Collectors.toList()));
        }
    }

    @Inject(method={"storeChunkSections"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/level/entity/EntityPersistentStorage;storeEntities(Lnet/minecraft/world/level/entity/ChunkEntities;)V")})
    private void arclight$resetFlag(long pos, Consumer<T> consumer, CallbackInfoReturnable<Boolean> cir) {
        this.arclight$fireEvent = false;
    }

    @Inject(method={"processChunkUnload"}, at={@At(value="HEAD")})
    private void arclight$fireEvent(long pChunkPosValue, CallbackInfoReturnable<Boolean> cir) {
        this.arclight$fireEvent = true;
    }

    /*
     * WARNING - void declaration
     */
    @Inject(method={"processPendingLoads"}, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", shift=At.Shift.AFTER, remap=false, target="Lit/unimi/dsi/fastutil/longs/Long2ObjectMap;put(JLjava/lang/Object;)Ljava/lang/Object;")})
    private void arclight$fireLoad(CallbackInfo callbackInfo, ChunkEntities<T> chunkEntities) {
        void chunkEntities2;
        if (EntitiesLoadEvent.getHandlerList().getRegisteredListeners().length == 0) {
            return;
        }
        List<Entity> entities = this.getEntities(chunkEntities2.m_156791_());
        CraftEventFactory.callEntitiesLoadEvent((Level)((EntityStorage)this.f_157493_).f_156538_, chunkEntities2.m_156791_(), entities);
    }
}

