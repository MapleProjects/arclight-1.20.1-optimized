/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.npc.InventoryCarrier
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.npc;

import io.izzel.arclight.common.mod.server.ArclightContainer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={InventoryCarrier.class})
public interface InventoryCarrierMixin {
    @Shadow
    public SimpleContainer m_35311_();

    @Overwrite
    public static void m_219611_(Mob mob, InventoryCarrier carrier, ItemEntity itemEntity) {
        ItemStack itemstack = itemEntity.m_32055_();
        if (mob.m_7243_(itemstack)) {
            SimpleContainer simplecontainer = carrier.m_35311_();
            boolean flag = simplecontainer.m_19183_(itemstack);
            if (!flag) {
                return;
            }
            ItemStack remaining = ArclightContainer.copyOf(carrier.m_35311_()).m_19173_(itemstack);
            if (CraftEventFactory.callEntityPickupItemEvent((Entity)mob, itemEntity, remaining.m_41613_(), false).isCancelled()) {
                return;
            }
            mob.m_21053_(itemEntity);
            int i = itemstack.m_41613_();
            ItemStack itemstack1 = simplecontainer.m_19173_(itemstack);
            mob.m_7938_((Entity)itemEntity, i - itemstack1.m_41613_());
            if (itemstack1.m_41619_()) {
                itemEntity.m_146870_();
            } else {
                itemstack.m_41764_(itemstack1.m_41613_());
            }
        }
    }
}

