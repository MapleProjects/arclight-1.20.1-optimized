/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.gameevent.GameEvent$Context
 *  net.minecraft.world.level.gameevent.GameEventDispatcher
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.gameevent;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEventDispatcher;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftGameEvent;
import org.bukkit.event.world.GenericGameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={GameEventDispatcher.class})
public class GameEventDispatcherMixin {
    @Shadow
    @Final
    private ServerLevel f_243917_;
    private transient int arclight$newRadius;

    @Inject(method={"post"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$gameEvent(GameEvent gameEvent, Vec3 vec3, GameEvent.Context context, CallbackInfo ci) {
        Entity entity = context.f_223711_();
        int i = gameEvent.m_157827_();
        GenericGameEvent event = new GenericGameEvent(CraftGameEvent.minecraftToBukkit(gameEvent), new Location(((WorldBridge)this.f_243917_).bridge$getWorld(), vec3.m_7096_(), vec3.m_7098_(), vec3.m_7094_()), entity == null ? null : ((EntityBridge)entity).bridge$getBukkitEntity(), i, !Bukkit.isPrimaryThread());
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
        } else {
            this.arclight$newRadius = event.getRadius();
        }
    }

    @Redirect(method={"post"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/gameevent/GameEvent;getNotificationRadius()I"))
    private int arclight$applyRadius(GameEvent instance) {
        return this.arclight$newRadius;
    }
}

