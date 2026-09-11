/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.ArclightConfig
 *  net.minecraftforge.server.permission.PermissionAPI
 *  net.minecraftforge.server.permission.handler.IPermissionHandler
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.forge;

import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.common.mod.server.ArclightPermissionHandler;
import io.izzel.arclight.i18n.ArclightConfig;
import net.minecraftforge.server.permission.PermissionAPI;
import net.minecraftforge.server.permission.handler.IPermissionHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={PermissionAPI.class}, remap=false)
public class PermissionAPIMixin {
    @Shadow
    private static IPermissionHandler activeHandler;

    @Inject(method={"initializePermissionAPI"}, at={@At(value="RETURN")})
    private static void arclight$init(CallbackInfo ci) {
        if (!ArclightConfig.spec().getCompat().isForwardPermission()) {
            return;
        }
        ArclightPermissionHandler handler = new ArclightPermissionHandler(activeHandler);
        ArclightMod.LOGGER.info("Forwarding forge permission[{}] to bukkit", (Object)activeHandler.getIdentifier());
        activeHandler = handler;
    }
}

