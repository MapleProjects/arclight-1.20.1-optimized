/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetHealthPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.food.FoodData
 *  net.minecraft.world.food.FoodProperties
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.item.ItemStack
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.food;

import io.izzel.arclight.common.bridge.core.entity.LivingEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.PlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.util.FoodStatsBridge;
import javax.annotation.Nullable;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={FoodData.class})
public abstract class FoodDataMixin
implements FoodStatsBridge {
    @Shadow
    public int f_38696_;
    @Shadow
    public float f_38697_;
    @Shadow
    private int f_38700_;
    private Player entityhuman;
    public int saturatedRegenRate = 10;
    public int unsaturatedRegenRate = 80;
    public int starvationRate = 80;

    @Shadow
    public abstract void m_38707_(int var1, float var2);

    public void arclight$constructor() {
        throw new RuntimeException();
    }

    public void arclight$constructor(Player playerEntity) {
        this.arclight$constructor();
        this.entityhuman = playerEntity;
    }

    @Redirect(method={"eat(Lnet/minecraft/world/item/Item;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)V"}, remap=false, at=@At(value="INVOKE", remap=true, target="Lnet/minecraft/world/food/FoodData;eat(IF)V"))
    private void arclight$foodLevelChange(FoodData foodStats, int foodLevelIn, float foodSaturationModifier, Item maybeFood, ItemStack stack, @Nullable LivingEntity entity) {
        Player p;
        Player player;
        Object object = this.entityhuman != null ? this.entityhuman : (player = entity instanceof Player ? (p = (Player)entity) : null);
        if (player == null) {
            foodStats.m_38707_(foodLevelIn, foodSaturationModifier);
            return;
        }
        if (this.entityhuman == null) {
            this.entityhuman = player;
        }
        FoodProperties food = maybeFood.getFoodProperties(stack, entity);
        int oldFoodLevel = this.f_38696_;
        FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(player, food.m_38744_() + oldFoodLevel, stack);
        if (!event.isCancelled()) {
            this.m_38707_(event.getFoodLevel() - oldFoodLevel, food.m_38745_());
        }
        ((ServerPlayerEntityBridge)player).bridge$getBukkitEntity().sendHealthUpdate();
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE_ASSIGN", remap=false, target="Ljava/lang/Math;max(II)I")})
    public void arclight$foodLevelChange2(Player player, CallbackInfo ci) {
        if (this.entityhuman == null) {
            return;
        }
        FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(this.entityhuman, Math.max(this.f_38700_ - 1, 0));
        this.f_38696_ = !event.isCancelled() ? event.getFoodLevel() : this.f_38700_;
        ((ServerPlayer)this.entityhuman).f_8906_.m_9829_((Packet)new ClientboundSetHealthPacket(((ServerPlayerEntityBridge)this.entityhuman).bridge$getBukkitEntity().getScaledHealth(), this.f_38696_, this.f_38697_));
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;heal(F)V")})
    public void arclight$heal(Player player, CallbackInfo ci) {
        if (this.entityhuman == null) {
            this.entityhuman = player;
        }
        ((LivingEntityBridge)player).bridge$pushHealReason(EntityRegainHealthEvent.RegainReason.SATIATED);
        ((PlayerEntityBridge)player).bridge$pushExhaustReason(EntityExhaustionEvent.ExhaustionReason.REGEN);
    }

    @Override
    public void bridge$setEntityHuman(Player playerEntity) {
        this.entityhuman = playerEntity;
    }
}

