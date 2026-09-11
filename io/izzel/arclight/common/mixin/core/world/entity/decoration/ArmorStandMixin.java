/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.decoration.ArmorStand
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.entity.decoration;

import com.google.common.collect.Lists;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.LivingEntityMixin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={net.minecraft.world.entity.decoration.ArmorStand.class})
public abstract class ArmorStandMixin
extends LivingEntityMixin {
    @Shadow
    private boolean f_31540_;
    @Shadow
    @Final
    private NonNullList<net.minecraft.world.item.ItemStack> f_31538_;
    @Shadow
    @Final
    private NonNullList<net.minecraft.world.item.ItemStack> f_31539_;

    @Override
    @Shadow
    public abstract net.minecraft.world.item.ItemStack m_6844_(EquipmentSlot var1);

    @Override
    public float getBukkitYaw() {
        return this.m_146908_();
    }

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/entity/decoration/ArmorStand;kill()V")})
    public void arclight$damageDropOut(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (CraftEventFactory.handleNonLivingEntityDamageEvent((Entity)((net.minecraft.world.entity.decoration.ArmorStand)this), source, amount)) {
            cir.setReturnValue((Object)false);
        } else {
            this.arclight$callEntityDeath();
        }
    }

    @Inject(method={"hurt"}, cancellable=true, at={@At(value="FIELD", target="Lnet/minecraft/tags/DamageTypeTags;IS_EXPLOSION:Lnet/minecraft/tags/TagKey;")})
    public void arclight$damageNormal(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (CraftEventFactory.handleNonLivingEntityDamageEvent((Entity)((net.minecraft.world.entity.decoration.ArmorStand)this), source, amount, true, this.f_31540_)) {
            cir.setReturnValue((Object)false);
        }
    }

    @Redirect(method={"hurt"}, at=@At(value="FIELD", target="Lnet/minecraft/world/entity/decoration/ArmorStand;invisible:Z"))
    private boolean arclight$softenCondition(net.minecraft.world.entity.decoration.ArmorStand entity) {
        return false;
    }

    @Inject(method={"hurt"}, at={@At(value="INVOKE", ordinal=1, target="Lnet/minecraft/world/entity/decoration/ArmorStand;kill()V")})
    private void arclight$damageDeath1(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        this.arclight$callEntityDeath();
    }

    @Inject(method={"hurt"}, at={@At(value="INVOKE", ordinal=2, target="Lnet/minecraft/world/entity/decoration/ArmorStand;kill()V")})
    private void arclight$damageDeath2(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        this.arclight$callEntityDeath();
    }

    @Inject(method={"causeDamage"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/decoration/ArmorStand;kill()V")})
    private void arclight$deathEvent2(DamageSource source, float p_213817_2_, CallbackInfo ci) {
        this.arclight$callEntityDeath();
    }

    @Redirect(method={"brokenByAnything"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/decoration/ArmorStand;dropAllDeathLoot(Lnet/minecraft/world/damagesource/DamageSource;)V"))
    private void arclight$dropLater(net.minecraft.world.entity.decoration.ArmorStand entity, DamageSource damageSourceIn) {
    }

    @Redirect(method={"brokenByAnything"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/Block;popResource(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/item/ItemStack;)V"))
    private void arclight$captureDropsDeath(Level worldIn, BlockPos pos, net.minecraft.world.item.ItemStack stack) {
        this.arclight$tryCaptureDrops(worldIn, pos, stack);
    }

    @Inject(method={"brokenByAnything"}, at={@At(value="RETURN")})
    private void arclight$spawnLast(DamageSource source, CallbackInfo ci) {
        this.m_6668_(source);
    }

    @Override
    protected boolean m_6149_() {
        return true;
    }

    @Inject(method={"kill"}, at={@At(value="HEAD")})
    private void arclight$deathEvent(CallbackInfo ci) {
        this.arclight$callEntityDeath();
    }

    private void arclight$tryCaptureDrops(Level worldIn, BlockPos pos, net.minecraft.world.item.ItemStack stack) {
        if (!worldIn.f_46443_ && !stack.m_41619_() && worldIn.m_46469_().m_46207_(GameRules.f_46136_) && !worldIn.restoringBlockSnapshots) {
            ItemEntity itementity = new ItemEntity(worldIn, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), stack);
            this.arclight$drops().add(itementity);
        }
    }

    private Collection<ItemEntity> arclight$drops() {
        Collection<ItemEntity> drops = this.captureDrops();
        if (drops == null) {
            ArrayList<ItemEntity> list = new ArrayList<ItemEntity>();
            this.captureDrops(list);
            return list;
        }
        return drops;
    }

    private void arclight$callEntityDeath() {
        Collection<ItemEntity> captureDrops = this.captureDrops(null);
        List<Object> drops = captureDrops == null ? new ArrayList<ItemStack>() : (captureDrops instanceof List ? Lists.transform((List)((List)captureDrops), e -> CraftItemStack.asCraftMirror(e.m_32055_())) : captureDrops.stream().map(ItemEntity::m_32055_).map(CraftItemStack::asCraftMirror).collect(Collectors.toList()));
        CraftEventFactory.callEntityDeathEvent((LivingEntity)((net.minecraft.world.entity.decoration.ArmorStand)this), drops);
    }

    @Inject(method={"swapItem"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/player/Player;getAbilities()Lnet/minecraft/world/entity/player/Abilities;")})
    public void arclight$manipulateEvent(Player playerEntity, EquipmentSlot slotType, net.minecraft.world.item.ItemStack itemStack, InteractionHand hand, CallbackInfoReturnable<Boolean> cir) {
        net.minecraft.world.item.ItemStack itemStack1 = this.m_6844_(slotType);
        CraftItemStack armorStandItem = CraftItemStack.asCraftMirror(itemStack1);
        CraftItemStack playerHeldItem = CraftItemStack.asCraftMirror(itemStack);
        CraftPlayer player = ((ServerPlayerEntityBridge)playerEntity).bridge$getBukkitEntity();
        ArmorStand self = (ArmorStand)((Object)this.bridge$getBukkitEntity());
        org.bukkit.inventory.EquipmentSlot slot = CraftEquipmentSlot.getSlot(slotType);
        org.bukkit.inventory.EquipmentSlot bukkitHand = CraftEquipmentSlot.getHand(hand);
        PlayerArmorStandManipulateEvent event = new PlayerArmorStandManipulateEvent(player, self, playerHeldItem, armorStandItem, slot, bukkitHand);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)true);
        }
    }

    @Override
    public void setItemSlot(EquipmentSlot slotIn, net.minecraft.world.item.ItemStack stack, boolean silent) {
        switch (slotIn.m_20743_()) {
            case HAND: {
                this.bridge$playEquipSound(slotIn, (net.minecraft.world.item.ItemStack)this.f_31538_.set(slotIn.m_20749_(), (Object)stack), stack, silent);
                break;
            }
            case ARMOR: {
                this.bridge$playEquipSound(slotIn, (net.minecraft.world.item.ItemStack)this.f_31539_.set(slotIn.m_20749_(), (Object)stack), stack, silent);
            }
        }
    }
}

