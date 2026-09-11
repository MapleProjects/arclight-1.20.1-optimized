/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.saveddata.maps.MapItemSavedData
 *  net.minecraft.world.level.storage.DimensionDataStorage
 *  net.minecraftforge.server.ServerLifecycleHooks
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.server.level;

import com.google.common.collect.Lists;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.storage.MapDataBridge;
import java.util.Iterator;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets={"net/minecraft/server/level/ServerLevel$EntityCallbacks"})
public class ServerLevel_EntityCallbacksMixin {
    @Shadow(aliases={"f_143351_", "this$0"})
    private ServerLevel outerThis;

    @Inject(method={"onTrackingStart(Lnet/minecraft/world/entity/Entity;)V"}, at={@At(value="RETURN")})
    private void arclight$valid(Entity entity, CallbackInfo ci) {
        ((EntityBridge)entity).bridge$setValid(true);
    }

    @Inject(method={"onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V"}, at={@At(value="HEAD")})
    private void arclight$entityCleanup(Entity entity, CallbackInfo ci) {
        Iterator iterator;
        if (entity instanceof Player) {
            Player player = (Player)entity;
            iterator = ServerLifecycleHooks.getCurrentServer().m_129785_().iterator();
            while (iterator.hasNext()) {
                ServerLevel serverLevel = (ServerLevel)iterator.next();
                DimensionDataStorage worldData = serverLevel.m_8895_();
                for (Object o : worldData.f_78144_.values()) {
                    if (!(o instanceof MapItemSavedData)) continue;
                    MapItemSavedData map = (MapItemSavedData)o;
                    map.f_77896_.remove(player);
                    ((MapDataBridge)map).bridge$getCarriedBy().removeIf(holdingPlayer -> holdingPlayer.f_77959_ == entity);
                }
            }
        }
        if ((iterator = ((EntityBridge)entity).bridge$getBukkitEntity()) instanceof InventoryHolder) {
            InventoryHolder holder = (InventoryHolder)((Object)iterator);
            for (HumanEntity h : Lists.newArrayList(holder.getInventory().getViewers())) {
                h.closeInventory();
            }
        }
    }

    @Inject(method={"onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V"}, at={@At(value="RETURN")})
    private void arclight$invalid(Entity entity, CallbackInfo ci) {
        ((EntityBridge)entity).bridge$setValid(false);
        if (!(entity instanceof ServerPlayer)) {
            for (ServerPlayer player : this.outerThis.m_6907_()) {
                ((ServerPlayerEntityBridge)player).bridge$getBukkitEntity().onEntityRemove(entity);
            }
        }
    }
}

