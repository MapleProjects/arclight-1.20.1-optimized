/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.bukkit.EntityTypeBridge;
import java.util.function.Function;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftRegionAccessor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.Consumer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={CraftRegionAccessor.class}, remap=false)
public abstract class CraftRegionAccessorMixin {
    @Shadow
    public abstract <T extends Entity> T addEntity(net.minecraft.world.entity.Entity var1, CreatureSpawnEvent.SpawnReason var2, Consumer<T> var3, boolean var4) throws IllegalArgumentException;

    @Inject(method={"spawnEntity(Lorg/bukkit/Location;Lorg/bukkit/entity/EntityType;)Lorg/bukkit/entity/Entity;"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$useFactory(Location loc, EntityType entityType, CallbackInfoReturnable<Entity> cir) {
        Function<Location, ? extends net.minecraft.world.entity.Entity> factory = ((EntityTypeBridge)((Object)entityType)).bridge$entityFactory();
        if (factory != null) {
            cir.setReturnValue(this.addEntity(factory.apply(loc), CreatureSpawnEvent.SpawnReason.CUSTOM, null, true));
        }
    }

    @Inject(method={"spawnEntity(Lorg/bukkit/Location;Lorg/bukkit/entity/EntityType;Z)Lorg/bukkit/entity/Entity;"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$useFactory(Location loc, EntityType entityType, boolean randomizeData, CallbackInfoReturnable<Entity> cir) {
        Function<Location, ? extends net.minecraft.world.entity.Entity> factory = ((EntityTypeBridge)((Object)entityType)).bridge$entityFactory();
        if (factory != null) {
            cir.setReturnValue(this.addEntity(factory.apply(loc), CreatureSpawnEvent.SpawnReason.CUSTOM, null, randomizeData));
        }
    }
}

