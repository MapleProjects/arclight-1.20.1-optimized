/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.Strider
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import net.minecraft.world.entity.monster.Strider;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Strider.class})
public class StriderMixin {
    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/Strider;setSuffocating(Z)V"))
    private void arclight$temperatureChange(Strider instance, boolean flag) {
        if (flag ^ instance.m_33935_() && CraftEventFactory.callStriderTemperatureChangeEvent(instance, flag)) {
            instance.m_33951_(flag);
        }
    }
}

