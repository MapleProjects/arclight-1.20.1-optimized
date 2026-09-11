/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.dedicated.DedicatedPlayerList
 *  net.minecraft.server.players.PlayerList
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.server.management;

import io.izzel.arclight.common.bridge.bukkit.CraftServerBridge;
import net.minecraft.server.dedicated.DedicatedPlayerList;
import net.minecraft.server.players.PlayerList;
import org.bukkit.Bukkit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={DedicatedPlayerList.class})
public class DedicatedPlayerListMixin {
    @Inject(method={"<init>"}, at={@At(value="TAIL")})
    private void arclight$afterSuper(CallbackInfo ci) {
        ((CraftServerBridge)((Object)Bukkit.getServer())).bridge$setPlayerList((PlayerList)((DedicatedPlayerList)this));
    }
}

