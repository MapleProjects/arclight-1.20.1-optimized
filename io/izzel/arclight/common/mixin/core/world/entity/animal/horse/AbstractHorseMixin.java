/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.SimpleContainer
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.animal.horse.AbstractHorse
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal.horse;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.mixin.core.world.entity.animal.AnimalMixin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={AbstractHorse.class})
public abstract class AbstractHorseMixin
extends AnimalMixin {
    public int maxDomestication;

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<? extends AbstractHorse> type, Level worldIn, CallbackInfo ci) {
        this.maxDomestication = 100;
    }

    @Redirect(method={"createInventory"}, at=@At(value="NEW", target="net/minecraft/world/SimpleContainer"))
    private SimpleContainer arclight$createInv(int slots) {
        SimpleContainer inventory = new SimpleContainer(slots);
        ((IInventoryBridge)inventory).setOwner((InventoryHolder)((Object)this.getBukkitEntity()));
        return inventory;
    }

    @Inject(method={"handleEating"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/horse/AbstractHorse;heal(F)V")})
    private void arclight$healByEating(Player player, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        this.bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.EATING);
    }

    @Inject(method={"aiStep"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/horse/AbstractHorse;heal(F)V")})
    private void arclight$healByRegen(CallbackInfo ci) {
        this.bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.REGEN);
    }

    @Inject(method={"addAdditionalSaveData"}, at={@At(value="RETURN")})
    private void arclight$writeTemper(CompoundTag compound, CallbackInfo ci) {
        compound.m_128405_("Bukkit.MaxDomestication", this.maxDomestication);
    }

    @Inject(method={"readAdditionalSaveData"}, at={@At(value="RETURN")})
    private void arclight$readTemper(CompoundTag compound, CallbackInfo ci) {
        if (compound.m_128441_("Bukkit.MaxDomestication")) {
            this.maxDomestication = compound.m_128451_("Bukkit.MaxDomestication");
        }
    }

    @Inject(method={"handleStartJump"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$horseJump(int i, CallbackInfo ci) {
        float power = i >= 90 ? 1.0f : 0.4f + 0.4f * (float)i / 90.0f;
        if (!CraftEventFactory.callHorseJumpEvent((Entity)((AbstractHorse)this), power)) {
            ci.cancel();
        }
    }

    @Overwrite
    public int m_7555_() {
        return this.maxDomestication;
    }
}

