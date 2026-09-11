/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.stats.Stats
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ExperienceOrb
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.animal;

import io.izzel.arclight.common.bridge.core.entity.passive.AnimalEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.entity.AgeableMobMixin;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityEnterLoveModeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Animal.class})
public abstract class AnimalMixin
extends AgeableMobMixin
implements AnimalEntityBridge {
    @Shadow
    public int f_27554_;
    public ItemStack breedItem;
    private transient int arclight$loveTime;

    @Shadow
    public InteractionResult m_6071_(Player playerIn, InteractionHand hand) {
        return null;
    }

    @Shadow
    public abstract void m_27594_();

    @Shadow
    @Nullable
    public abstract ServerPlayer m_27592_();

    @Override
    @Overwrite
    public boolean m_6469_(DamageSource source, float amount) {
        return super.m_6469_(source, amount);
    }

    @Inject(method={"setInLove(Lnet/minecraft/world/entity/player/Player;)V"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$enterLove(Player player, CallbackInfo ci) {
        EntityEnterLoveModeEvent event = CraftEventFactory.callEntityEnterLoveModeEvent(player, (Animal)this, 600);
        if (event.isCancelled()) {
            ci.cancel();
        } else {
            this.arclight$loveTime = event.getTicksInLove();
        }
    }

    @Inject(method={"setInLove(Lnet/minecraft/world/entity/player/Player;)V"}, at={@At(value="FIELD", shift=At.Shift.AFTER, target="Lnet/minecraft/world/entity/animal/Animal;inLove:I")})
    private void arclight$inLove(Player player, CallbackInfo ci) {
        this.f_27554_ = this.arclight$loveTime;
        if (player != null) {
            this.breedItem = player.m_150109_().m_36056_();
        }
    }

    @Override
    public ItemStack bridge$getBreedItem() {
        return this.breedItem;
    }

    @Inject(method={"spawnChildFromBreeding"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V")})
    private void arclight$reason(ServerLevel level, Animal p_27565_, CallbackInfo ci) {
        ((WorldBridge)level).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.BREEDING);
    }

    @Overwrite
    public void m_277117_(ServerLevel worldserver, Animal entityanimal, @Nullable AgeableMob entityageable) {
        Optional<ServerPlayer> cause = Optional.ofNullable(this.m_27592_()).or(() -> Optional.ofNullable(entityanimal.m_27592_()));
        int experience = this.m_217043_().m_188503_(7) + 1;
        if (entityageable != null) {
            EntityBreedEvent entityBreedEvent = CraftEventFactory.callEntityBreedEvent((LivingEntity)entityageable, (LivingEntity)((Animal)this), (LivingEntity)entityanimal, cause.orElse(null), this.breedItem, experience);
            if (entityBreedEvent.isCancelled()) {
                return;
            }
            experience = entityBreedEvent.getExperience();
        }
        cause.ifPresent(entityplayer -> {
            entityplayer.m_36220_(Stats.f_12937_);
            CriteriaTriggers.f_10581_.m_147278_(entityplayer, (Animal)this, entityanimal, entityageable);
        });
        this.m_146762_(6000);
        entityanimal.m_146762_(6000);
        this.m_27594_();
        entityanimal.m_27594_();
        worldserver.m_7605_((Entity)((Animal)this), (byte)18);
        if (worldserver.m_46469_().m_46207_(GameRules.f_46135_) && experience > 0) {
            worldserver.m_7967_((Entity)new ExperienceOrb((Level)worldserver, this.m_20185_(), this.m_20186_(), this.m_20189_(), experience));
        }
    }
}

