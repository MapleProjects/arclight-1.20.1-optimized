/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.level.LevelAccessor
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.world;

import io.izzel.arclight.common.bridge.core.world.IWorldBridge;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={LevelAccessor.class})
public interface IWorldMixin
extends IWorldBridge {
    default public ServerLevel getMinecraftWorld() {
        return this.bridge$getMinecraftWorld();
    }
}

