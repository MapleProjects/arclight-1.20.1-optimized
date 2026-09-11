/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.effect.MobEffect
 *  net.minecraft.world.effect.MobEffectInstance
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.item.ItemStack
 */
package io.izzel.arclight.common.bridge.core.entity;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import java.util.Optional;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public interface LivingEntityBridge
extends EntityBridge {
    public void bridge$setSlot(EquipmentSlot var1, ItemStack var2, boolean var3);

    public void bridge$playEquipSound(EquipmentSlot var1, ItemStack var2, ItemStack var3, boolean var4);

    public boolean bridge$canPickUpLoot();

    public boolean bridge$isForceDrops();

    public int bridge$getExpReward();

    public void bridge$setExpToDrop(int var1);

    public int bridge$getExpToDrop();

    public void bridge$pushHealReason(EntityRegainHealthEvent.RegainReason var1);

    public void bridge$heal(float var1, EntityRegainHealthEvent.RegainReason var2);

    public void bridge$pushEffectCause(EntityPotionEffectEvent.Cause var1);

    public boolean bridge$addEffect(MobEffectInstance var1, EntityPotionEffectEvent.Cause var2);

    public boolean bridge$removeEffect(MobEffect var1, EntityPotionEffectEvent.Cause var2);

    public boolean bridge$removeAllEffects(EntityPotionEffectEvent.Cause var1);

    public Optional<EntityPotionEffectEvent.Cause> bridge$getEffectCause();

    public EntityPotionEffectEvent.Action bridge$getAndResetAction();

    @Override
    public CraftLivingEntity bridge$getBukkitEntity();
}

