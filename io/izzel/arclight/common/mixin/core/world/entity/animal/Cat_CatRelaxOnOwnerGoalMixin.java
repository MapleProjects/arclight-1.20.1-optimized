/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.animal.Cat
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets={"net.minecraft.world.entity.animal.Cat$CatRelaxOnOwnerGoal"})
public class Cat_CatRelaxOnOwnerGoalMixin {
    @Shadow
    @Final
    private Cat f_28198_;

    @Redirect(method={"giveMorningGift"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$dropItem(Level instance, Entity entity) {
        EntityDropItemEvent event = new EntityDropItemEvent(((EntityBridge)this.f_28198_).bridge$getBukkitEntity(), (Item)((Object)((EntityBridge)entity).bridge$getBukkitEntity()));
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            return instance.m_7967_(entity);
        }
        return false;
    }
}

