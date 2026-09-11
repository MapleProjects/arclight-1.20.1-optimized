/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange;

import io.izzel.arclight.common.bridge.optimization.EntityBridge_ActivationRange;
import net.minecraft.world.entity.Entity;
import org.spigotmc.ActivationRange;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={ActivationRange.class}, remap=false)
public class ActivationRangeMixin {
    @Overwrite
    private static void activateEntity(Entity entity) {
        ((EntityBridge_ActivationRange)entity).bridge$updateActivation();
    }
}

