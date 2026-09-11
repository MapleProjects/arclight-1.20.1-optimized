/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.ServerLevelAccessor
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world;

import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import java.util.Iterator;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={ServerLevelAccessor.class})
public interface IServerWorldMixin
extends LevelAccessor,
ServerWorldBridge {
    @Shadow
    public ServerLevel m_6018_();

    @Override
    default public ServerLevel bridge$getMinecraftWorld() {
        return this.m_6018_();
    }

    @Overwrite
    default public void m_47205_(Entity entity) {
        CreatureSpawnEvent.SpawnReason spawnReason = this.bridge$getAddEntityReason();
        Iterator iterator = entity.m_20199_().iterator();
        while (iterator.hasNext()) {
            Entity next = (Entity)iterator.next();
            this.bridge$pushAddEntityReason(spawnReason);
            this.m_7967_(next);
        }
    }

    default public boolean addFreshEntityWithPassengers(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        Iterator iterator = entity.m_20199_().iterator();
        while (iterator.hasNext()) {
            Entity next = (Entity)iterator.next();
            this.bridge$pushAddEntityReason(reason);
            this.m_7967_(next);
        }
        return !entity.m_213877_();
    }

    @Override
    default public boolean bridge$addAllEntities(Entity entity, CreatureSpawnEvent.SpawnReason reason) {
        return this.addFreshEntityWithPassengers(entity, reason);
    }
}

