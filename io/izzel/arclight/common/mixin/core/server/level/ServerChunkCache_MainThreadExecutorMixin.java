/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerChunkCache
 *  net.minecraft.util.thread.BlockableEventLoop
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.server.level;

import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import io.izzel.arclight.common.bridge.core.world.server.ChunkMapBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerChunkProviderBridge;
import io.izzel.arclight.common.mod.server.ArclightServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.util.thread.BlockableEventLoop;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets={"net.minecraft.server.level.ServerChunkCache$MainThreadExecutor"})
public abstract class ServerChunkCache_MainThreadExecutorMixin
extends BlockableEventLoop<Runnable> {
    @Shadow(aliases={"this$0", "f_8491_"}, remap=false)
    @Final
    private ServerChunkCache outer;

    protected ServerChunkCache_MainThreadExecutorMixin(String nameIn) {
        super(nameIn);
    }

    @Overwrite
    public boolean m_7245_() {
        try {
            if (((ServerChunkProviderBridge)this.outer).bridge$tickDistanceManager()) {
                boolean bl = true;
                return bl;
            }
            ((ServerChunkProviderBridge)this.outer).bridge$getLightManager().m_9409_();
            boolean bl = super.m_7245_();
            return bl;
        }
        finally {
            ((ChunkMapBridge)this.outer.f_8325_).bridge$getCallbackExecutor().run();
            ((MinecraftServerBridge)ArclightServer.getMinecraftServer()).bridge$drainQueuedTasks();
        }
    }
}

