/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.stats.Stats
 *  net.minecraft.tags.ItemTags
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.entity.projectile.FishingHook
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.storage.loot.BuiltInLootTables
 *  net.minecraft.world.level.storage.loot.LootParams
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.HitResult
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ItemFishedEvent
 *  net.minecraftforge.eventbus.api.Event
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.projectile;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.projectile.ProjectileMixin;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.Event;
import org.bukkit.Bukkit;
import org.bukkit.entity.FishHook;
import org.bukkit.event.player.PlayerFishEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={FishingHook.class})
public abstract class FishingHookMixin
extends ProjectileMixin {
    @Shadow
    public Entity f_37094_;
    @Shadow
    private int f_37089_;
    @Shadow
    @Final
    private int f_37096_;
    @Shadow
    private int f_37091_;
    @Shadow
    private int f_37090_;
    @Shadow
    @Final
    private int f_37097_;
    public int minWaitTime = 100;
    public int maxWaitTime = 600;
    public int minLureTime = 20;
    public int maxLureTime = 80;
    public float minLureAngle = 0.0f;
    public float maxLureAngle = 360.0f;
    public boolean applyLure = true;
    public boolean rainInfluenced = true;
    public boolean skyInfluenced = true;

    @Shadow
    public abstract Player m_37168_();

    @Shadow
    protected abstract void m_150155_(Entity var1);

    @Redirect(method={"checkCollision"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/projectile/FishingHook;onHit(Lnet/minecraft/world/phys/HitResult;)V"))
    private void arclight$collide(FishingHook fishingHook, HitResult hitResult) {
        this.preOnHit(hitResult);
    }

    @Inject(method={"catchingFish"}, at={@At(value="FIELD", shift=At.Shift.AFTER, ordinal=0, target="Lnet/minecraft/world/entity/projectile/FishingHook;timeUntilHooked:I")})
    private void arclight$attemptFail(BlockPos blockPos, CallbackInfo ci) {
        PlayerFishEvent event = new PlayerFishEvent(((ServerPlayerEntityBridge)this.m_37168_()).bridge$getBukkitEntity(), null, (FishHook)((Object)this.getBukkitEntity()), PlayerFishEvent.State.FAILED_ATTEMPT);
        Bukkit.getPluginManager().callEvent(event);
    }

    @Inject(method={"catchingFish"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/projectile/FishingHook;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V")})
    private void arclight$fishBite(BlockPos blockPos, CallbackInfo ci) {
        PlayerFishEvent event = new PlayerFishEvent(((ServerPlayerEntityBridge)this.m_37168_()).bridge$getBukkitEntity(), null, (FishHook)((Object)this.getBukkitEntity()), PlayerFishEvent.State.BITE);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }

    @Inject(method={"catchingFish"}, at={@At(value="RETURN")})
    private void arclight$modifyWaitingTime(BlockPos p_37146_, CallbackInfo ci) {
        if (this.f_37089_ <= 0 && this.f_37091_ <= 0 && this.f_37090_ <= 0) {
            this.f_37090_ = Mth.m_216271_((RandomSource)this.f_19796_, (int)this.minWaitTime, (int)this.maxWaitTime);
            this.f_37090_ -= this.applyLure ? this.f_37097_ * 20 * 5 : 0;
        }
    }

    @Redirect(method={"catchingFish"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;isRainingAt(Lnet/minecraft/core/BlockPos;)Z"))
    private boolean arclight$rainInfluenced(Level level, BlockPos pos) {
        return this.rainInfluenced && level.m_46758_(pos);
    }

    @Redirect(method={"catchingFish"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;canSeeSky(Lnet/minecraft/core/BlockPos;)Z"))
    private boolean arclight$skyInfluenced(Level instance, BlockPos blockPos) {
        return this.skyInfluenced && instance.m_45527_(blockPos);
    }

    @Redirect(method={"catchingFish"}, at=@At(value="INVOKE", ordinal=2, target="Lnet/minecraft/util/Mth;nextFloat(Lnet/minecraft/util/RandomSource;FF)F"))
    private float arclight$lureAngleParam(RandomSource random, float p_216269_, float p_216270_) {
        return Mth.m_216267_((RandomSource)random, (float)this.minLureAngle, (float)this.maxLureAngle);
    }

    @Redirect(method={"catchingFish"}, at=@At(value="INVOKE", ordinal=2, target="Lnet/minecraft/util/Mth;nextInt(Lnet/minecraft/util/RandomSource;II)I"))
    private int arclight$lureTimeParam(RandomSource random, int p_216273_, int p_216274_) {
        return Mth.m_216271_((RandomSource)random, (int)this.minLureTime, (int)this.maxLureTime);
    }

    @Overwrite
    public int m_37156_(ItemStack stack) {
        Player playerentity = this.m_37168_();
        if (!this.m_9236_().f_46443_ && playerentity != null) {
            PlayerFishEvent playerFishEvent;
            int i = 0;
            ItemFishedEvent event = null;
            if (this.f_37094_ != null) {
                PlayerFishEvent fishEvent = new PlayerFishEvent(((ServerPlayerEntityBridge)playerentity).bridge$getBukkitEntity(), ((EntityBridge)this.f_37094_).bridge$getBukkitEntity(), (FishHook)((Object)this.getBukkitEntity()), PlayerFishEvent.State.CAUGHT_ENTITY);
                Bukkit.getPluginManager().callEvent(fishEvent);
                if (fishEvent.isCancelled()) {
                    return 0;
                }
                this.m_150155_(this.f_37094_);
                CriteriaTriggers.f_10553_.m_40416_((ServerPlayer)playerentity, stack, (FishingHook)this, Collections.emptyList());
                this.m_9236_().m_7605_((Entity)((FishingHook)this), (byte)31);
                i = this.f_37094_ instanceof ItemEntity ? 3 : 5;
            } else if (this.f_37089_ > 0) {
                LootParams params = new LootParams.Builder((ServerLevel)this.m_9236_()).m_287286_(LootContextParams.f_81460_, (Object)this.m_20182_()).m_287286_(LootContextParams.f_81463_, (Object)stack).m_287286_(LootContextParams.f_81455_, (Object)((FishingHook)this)).m_287239_((float)this.f_37096_ + playerentity.m_36336_()).m_287235_(LootContextParamSets.f_81414_);
                LootTable loottable = this.m_9236_().m_7654_().m_278653_().m_278676_(BuiltInLootTables.f_78720_);
                ObjectArrayList list = loottable.m_287195_(params);
                event = new ItemFishedEvent((List)list, this.f_19861_ ? 2 : 1, (FishingHook)this);
                MinecraftForge.EVENT_BUS.post((Event)event);
                if (event.isCanceled()) {
                    this.m_146870_();
                    return event.getRodDamage();
                }
                CriteriaTriggers.f_10553_.m_40416_((ServerPlayer)playerentity, stack, (FishingHook)this, (Collection)list);
                for (ItemStack itemstack : list) {
                    ItemEntity itementity = new ItemEntity(this.m_9236_(), this.m_20185_(), this.m_20186_(), this.m_20189_(), itemstack);
                    PlayerFishEvent playerFishEvent2 = new PlayerFishEvent(((ServerPlayerEntityBridge)playerentity).bridge$getBukkitEntity(), ((EntityBridge)itementity).bridge$getBukkitEntity(), (FishHook)((Object)this.getBukkitEntity()), PlayerFishEvent.State.CAUGHT_FISH);
                    playerFishEvent2.setExpToDrop(this.f_19796_.m_188503_(6) + 1);
                    Bukkit.getPluginManager().callEvent(playerFishEvent2);
                    if (playerFishEvent2.isCancelled()) {
                        return 0;
                    }
                    double d0 = playerentity.m_20185_() - this.m_20185_();
                    double d1 = playerentity.m_20186_() - this.m_20186_();
                    double d2 = playerentity.m_20189_() - this.m_20189_();
                    double d3 = 0.1;
                    itementity.m_20334_(d0 * 0.1, d1 * 0.1 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * 0.08, d2 * 0.1);
                    this.m_9236_().m_7967_((Entity)itementity);
                    if (playerFishEvent2.getExpToDrop() > 0) {
                        playerentity.m_9236_().m_7967_((Entity)new ExperienceOrb(playerentity.m_9236_(), playerentity.m_20185_(), playerentity.m_20186_() + 0.5, playerentity.m_20189_() + 0.5, playerFishEvent2.getExpToDrop()));
                    }
                    if (!itemstack.m_204117_(ItemTags.f_13156_)) continue;
                    playerentity.m_36222_(Stats.f_12939_, 1);
                }
                i = 1;
            }
            if (this.f_19861_) {
                playerFishEvent = new PlayerFishEvent(((ServerPlayerEntityBridge)playerentity).bridge$getBukkitEntity(), null, (FishHook)((Object)this.getBukkitEntity()), PlayerFishEvent.State.IN_GROUND);
                Bukkit.getPluginManager().callEvent(playerFishEvent);
                if (playerFishEvent.isCancelled()) {
                    return 0;
                }
                i = 2;
            }
            if (i == 0) {
                playerFishEvent = new PlayerFishEvent(((ServerPlayerEntityBridge)playerentity).bridge$getBukkitEntity(), null, (FishHook)((Object)this.getBukkitEntity()), PlayerFishEvent.State.REEL_IN);
                Bukkit.getPluginManager().callEvent(playerFishEvent);
                if (playerFishEvent.isCancelled()) {
                    return 0;
                }
            }
            this.m_146870_();
            return event == null ? i : event.getRodDamage();
        }
        return 0;
    }
}

