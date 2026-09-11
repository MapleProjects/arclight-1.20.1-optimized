/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Cow
 *  net.minecraft.world.entity.animal.MushroomCow
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.animal.AnimalMixin;
import java.util.Collections;
import java.util.List;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.entity.Item;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={MushroomCow.class})
public abstract class MushroomCowMixin
extends AnimalMixin {
    @Shadow(remap=false)
    protected abstract List<ItemStack> shearInternal(SoundSource var1);

    @Redirect(method={"shearInternal"}, remap=false, at=@At(value="INVOKE", remap=true, target="Lnet/minecraft/world/entity/animal/MushroomCow;discard()V"))
    private void arclight$animalTransformPre(MushroomCow mushroomCow) {
    }

    @Inject(method={"shearInternal"}, remap=false, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", remap=true, target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z")})
    private void arclight$animalTransform(SoundSource p_28924_, CallbackInfoReturnable<List<ItemStack>> cir, Cow cowEntity) {
        if (CraftEventFactory.callEntityTransformEvent((LivingEntity)((MushroomCow)this), (LivingEntity)cowEntity, EntityTransformEvent.TransformReason.SHEARED).isCancelled()) {
            cir.setReturnValue(Collections.emptyList());
        } else {
            ((WorldBridge)this.m_9236_()).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.SHEARED);
            this.m_146870_();
        }
    }

    @Overwrite
    public void m_5851_(SoundSource pCategory) {
        for (ItemStack s : this.shearInternal(pCategory)) {
            ItemEntity itemEntity = new ItemEntity(this.m_9236_(), this.m_20185_(), this.m_20227_(1.0), this.m_20189_(), s);
            EntityDropItemEvent event = new EntityDropItemEvent(this.getBukkitEntity(), (Item)((Object)((EntityBridge)itemEntity).bridge$getBukkitEntity()));
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) continue;
            this.m_9236_().m_7967_((Entity)itemEntity);
        }
    }
}

