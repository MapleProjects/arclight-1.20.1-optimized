/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.Advancement
 *  net.minecraft.server.PlayerAdvancements
 *  net.minecraft.server.level.ServerPlayer
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.server;

import io.izzel.arclight.common.bridge.core.advancement.AdvancementBridge;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.advancements.Advancement;
import net.minecraft.server.PlayerAdvancements;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={PlayerAdvancements.class})
public class PlayerAdvancementsMixin {
    @Shadow
    private ServerPlayer f_135968_;

    @Inject(method={"award"}, at={@At(value="INVOKE", target="Lnet/minecraft/advancements/Advancement;getRewards()Lnet/minecraft/advancements/AdvancementRewards;")})
    public void arclight$callEvent(Advancement advancementIn, String criterionKey, CallbackInfoReturnable<Boolean> cir) {
        Bukkit.getPluginManager().callEvent(new PlayerAdvancementDoneEvent((Player)((Object)((EntityBridge)this.f_135968_).bridge$getBukkitEntity()), ((AdvancementBridge)advancementIn).bridge$getBukkit()));
    }
}

