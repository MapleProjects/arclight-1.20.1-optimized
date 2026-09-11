/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.Constant
 *  org.spongepowered.asm.mixin.injection.ModifyConstant
 */
package io.izzel.arclight.common.mixin.bukkit;

import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value={StandardMessenger.class}, remap=false)
public abstract class StandardMessengerMixin
implements Messenger {
    @ModifyConstant(method={"validateAndCorrectChannel"}, constant={@Constant(intValue=64)})
    private static int modifyMaxChannelSize(int original) {
        return 256;
    }
}

