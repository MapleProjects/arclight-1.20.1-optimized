/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.animal.Fox
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Invoker
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.entity.passive.FoxEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.animal.AnimalMixin;
import java.util.UUID;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={Fox.class})
public abstract class FoxMixin
extends AnimalMixin
implements FoxEntityBridge {
    @Override
    @Invoker(value="addTrustedUUID")
    public abstract void bridge$addTrustedUUID(UUID var1);

    @Redirect(method={"pickUpItem"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/Fox;canHoldItem(Lnet/minecraft/world/item/ItemStack;)Z"))
    private boolean arclight$pickupEvent(Fox foxEntity, ItemStack stack, ItemEntity itemEntity) {
        return CraftEventFactory.callEntityPickupItemEvent((Entity)((Fox)this), itemEntity, stack.m_41613_() - 1, !this.m_7252_(stack)).isCancelled();
    }
}

