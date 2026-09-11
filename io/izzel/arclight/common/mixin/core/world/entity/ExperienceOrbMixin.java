/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.Enchantment
 *  net.minecraft.world.item.enchantment.EnchantmentHelper
 *  net.minecraft.world.item.enchantment.Enchantments
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import io.izzel.arclight.common.mixin.core.world.entity.EntityMixin;
import java.util.Map;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerExpCooldownChangeEvent;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ExperienceOrb.class})
public abstract class ExperienceOrbMixin
extends EntityMixin {
    @Shadow
    private Player f_20771_;
    @Shadow
    public int f_20770_;
    private transient Player arclight$lastPlayer;

    @Override
    @Shadow
    public abstract boolean m_6469_(DamageSource var1, float var2);

    @Shadow
    protected abstract int m_20793_(int var1);

    @Shadow
    protected abstract int m_20798_(int var1);

    @Inject(method={"tick"}, at={@At(value="INVOKE", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/Entity;tick()V")})
    private void arclight$captureLast(CallbackInfo ci) {
        this.arclight$lastPlayer = this.f_20771_;
    }

    @Inject(method={"tick"}, at={@At(value="RETURN")})
    private void arclight$captureReset(CallbackInfo ci) {
        this.arclight$lastPlayer = null;
    }

    @Redirect(method={"tick"}, at=@At(value="FIELD", ordinal=4, target="Lnet/minecraft/world/entity/ExperienceOrb;followingPlayer:Lnet/minecraft/world/entity/player/Player;"))
    private Player arclight$targetPlayer(ExperienceOrb entity) {
        if (this.f_20771_ != this.arclight$lastPlayer) {
            LivingEntity target;
            EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent((Entity)((ExperienceOrb)this), (LivingEntity)this.f_20771_, this.f_20771_ != null ? EntityTargetEvent.TargetReason.CLOSEST_PLAYER : EntityTargetEvent.TargetReason.FORGOT_TARGET);
            LivingEntity livingEntity = target = event.getTarget() == null ? null : ((CraftLivingEntity)event.getTarget()).getHandle();
            if (event.isCancelled()) {
                this.f_20771_ = this.arclight$lastPlayer;
                return null;
            }
            this.f_20771_ = target instanceof Player ? (Player)target : null;
        }
        return this.f_20771_;
    }

    @Redirect(method={"playerTouch"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;giveExperiencePoints(I)V"))
    private void arclight$expChange(Player player, int amount) {
        player.m_6756_(CraftEventFactory.callPlayerExpChangeEvent(player, amount).getAmount());
    }

    @Redirect(method={"playerTouch"}, at=@At(value="FIELD", opcode=181, target="Lnet/minecraft/world/entity/player/Player;takeXpDelay:I"))
    private void arclight$cooldown(Player instance, int value) {
        instance.f_36101_ = CraftEventFactory.callPlayerXpCooldownEvent(instance, value, PlayerExpCooldownChangeEvent.ChangeReason.PICKUP_ORB).getNewCooldown();
    }

    @Overwrite
    private int m_147092_(Player player, int i) {
        Map.Entry entry = EnchantmentHelper.m_44839_((Enchantment)Enchantments.f_44962_, (LivingEntity)player, ItemStack::m_41768_);
        if (entry != null) {
            int k;
            ItemStack itemstack = (ItemStack)entry.getValue();
            int j = Math.min(this.m_20798_(this.f_20770_), itemstack.m_41773_());
            PlayerItemMendEvent event = CraftEventFactory.callPlayerItemMendEvent(player, (ExperienceOrb)this, itemstack, (EquipmentSlot)entry.getKey(), j);
            j = event.getRepairAmount();
            if (event.isCancelled()) {
                return i;
            }
            itemstack.m_41721_(itemstack.m_41773_() - j);
            this.f_20770_ = k = i - this.m_20793_(j);
            return k > 0 ? this.m_147092_(player, k) : 0;
        }
        return i;
    }

    @Inject(method={"getExperienceValue"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$higherLevelSplit(int expValue, CallbackInfoReturnable<Integer> cir) {
        if (expValue > 162670129) {
            cir.setReturnValue((Object)(expValue - 100000));
            return;
        }
        if (expValue > 81335063) {
            cir.setReturnValue((Object)81335063);
            return;
        }
        if (expValue > 40667527) {
            cir.setReturnValue((Object)40667527);
            return;
        }
        if (expValue > 20333759) {
            cir.setReturnValue((Object)20333759);
            return;
        }
        if (expValue > 10166857) {
            cir.setReturnValue((Object)10166857);
            return;
        }
        if (expValue > 5083423) {
            cir.setReturnValue((Object)5083423);
            return;
        }
        if (expValue > 2541701) {
            cir.setReturnValue((Object)2541701);
            return;
        }
        if (expValue > 1270849) {
            cir.setReturnValue((Object)1270849);
            return;
        }
        if (expValue > 635413) {
            cir.setReturnValue((Object)635413);
            return;
        }
        if (expValue > 317701) {
            cir.setReturnValue((Object)317701);
            return;
        }
        if (expValue > 158849) {
            cir.setReturnValue((Object)158849);
            return;
        }
        if (expValue > 79423) {
            cir.setReturnValue((Object)79423);
            return;
        }
        if (expValue > 39709) {
            cir.setReturnValue((Object)39709);
            return;
        }
        if (expValue > 19853) {
            cir.setReturnValue((Object)19853);
            return;
        }
        if (expValue > 9923) {
            cir.setReturnValue((Object)9923);
            return;
        }
        if (expValue > 4957) {
            cir.setReturnValue((Object)4957);
        }
    }
}

