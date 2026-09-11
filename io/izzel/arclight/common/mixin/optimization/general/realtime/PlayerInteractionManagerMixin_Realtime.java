/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerPlayerGameMode
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.optimization.general.realtime;

import io.izzel.arclight.common.mod.ArclightConstants;
import net.minecraft.server.level.ServerPlayerGameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={ServerPlayerGameMode.class})
public class PlayerInteractionManagerMixin_Realtime {
    @Shadow
    private int f_9252_;
    private int lastTick = ArclightConstants.currentTick - 1;

    @Redirect(method={"tick"}, at=@At(value="FIELD", opcode=181, target="Lnet/minecraft/server/level/ServerPlayerGameMode;gameTicks:I"))
    private void arclight$useWallTime(ServerPlayerGameMode playerInteractionManager, int value) {
        int elapsedTicks = ArclightConstants.currentTick - this.lastTick;
        if (elapsedTicks < 1) {
            elapsedTicks = 1;
        }
        this.f_9252_ += elapsedTicks;
        this.lastTick = ArclightConstants.currentTick;
    }
}

