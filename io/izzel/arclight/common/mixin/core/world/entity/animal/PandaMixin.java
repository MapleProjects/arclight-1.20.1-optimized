/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.animal.Panda
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.mixin.core.world.entity.animal.AnimalMixin;
import java.util.function.Predicate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Panda.class})
public abstract class PandaMixin
extends AnimalMixin {
    @Shadow
    @Final
    static Predicate<ItemEntity> f_29071_;

    @Overwrite
    protected void m_7581_(ItemEntity itemEntity) {
        boolean cancel;
        boolean bl = cancel = this.m_6844_(EquipmentSlot.MAINHAND).m_41619_() && f_29071_.test(itemEntity);
        if (!CraftEventFactory.callEntityPickupItemEvent((Entity)((Panda)this), itemEntity, 0, cancel).isCancelled()) {
            ItemStack itemstack = itemEntity.m_32055_();
            this.m_8061_(EquipmentSlot.MAINHAND, itemstack);
            this.f_21347_[EquipmentSlot.MAINHAND.m_20749_()] = 2.0f;
            this.m_7938_((Entity)itemEntity, itemstack.m_41613_());
            itemEntity.m_146870_();
        }
    }
}

