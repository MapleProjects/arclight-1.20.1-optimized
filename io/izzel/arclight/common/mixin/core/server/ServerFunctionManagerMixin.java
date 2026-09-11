/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.CommandDispatcher
 *  net.minecraft.commands.CommandSourceStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.ServerFunctionManager
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.server;

import com.mojang.brigadier.CommandDispatcher;
import io.izzel.arclight.common.bridge.core.server.MinecraftServerBridge;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerFunctionManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ServerFunctionManager.class})
public class ServerFunctionManagerMixin {
    @Shadow
    @Final
    MinecraftServer f_136101_;

    @Inject(method={"getDispatcher"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$useVanillaDispatcher(CallbackInfoReturnable<CommandDispatcher<CommandSourceStack>> cir) {
        cir.setReturnValue((Object)((MinecraftServerBridge)this.f_136101_).bridge$getVanillaCommands().m_82094_());
    }
}

