/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.util.RandomSource
 *  net.minecraft.util.random.SimpleWeightedRandomList
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.MobSpawnType
 *  net.minecraft.world.entity.SpawnPlacements
 *  net.minecraft.world.level.BaseSpawner
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LightLayer
 *  net.minecraft.world.level.ServerLevelAccessor
 *  net.minecraft.world.level.SpawnData
 *  net.minecraft.world.level.SpawnData$CustomSpawnRules
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.AABB
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.living.MobSpawnEvent$FinalizeSpawn
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.spawner;

import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BaseSpawner.class})
public abstract class BaseSpawnerMixin {
    @Shadow
    public SimpleWeightedRandomList<SpawnData> f_45443_;
    @Shadow
    public int f_45442_;
    @Shadow
    public int f_45449_;
    @Shadow
    public int f_45453_;
    @Shadow
    public int f_45451_;

    @Shadow
    protected abstract boolean m_151343_(Level var1, BlockPos var2);

    @Shadow
    protected abstract void m_151350_(Level var1, BlockPos var2);

    @Shadow
    protected abstract SpawnData m_253144_(@Nullable Level var1, RandomSource var2, BlockPos var3);

    @Inject(method={"setEntityId"}, at={@At(value="RETURN")})
    public void arclight$clearMobs(CallbackInfo ci) {
        this.f_45443_ = SimpleWeightedRandomList.m_185864_();
    }

    @Overwrite
    public void m_151311_(ServerLevel level, BlockPos pos) {
        if (this.m_151343_((Level)level, pos)) {
            if (this.f_45442_ == -1) {
                this.m_151350_((Level)level, pos);
            }
            if (this.f_45442_ > 0) {
                --this.f_45442_;
            } else {
                boolean flag = false;
                RandomSource randomsource = level.m_213780_();
                SpawnData spawnData = this.m_253144_((Level)level, randomsource, pos);
                for (int i = 0; i < this.f_45449_; ++i) {
                    SpawnData.CustomSpawnRules spawndata$customspawnrules;
                    double d2;
                    CompoundTag compoundtag = spawnData.m_186567_();
                    Optional optional = EntityType.m_20637_((CompoundTag)compoundtag);
                    if (optional.isEmpty()) {
                        this.m_151350_((Level)level, pos);
                        return;
                    }
                    ListTag listtag = compoundtag.m_128437_("Pos", 6);
                    int j = listtag.size();
                    double d0 = j >= 1 ? listtag.m_128772_(0) : (double)pos.m_123341_() + (level.f_46441_.m_188500_() - level.f_46441_.m_188500_()) * (double)this.f_45453_ + 0.5;
                    double d1 = j >= 2 ? listtag.m_128772_(1) : (double)(pos.m_123342_() + level.f_46441_.m_188503_(3) - 1);
                    double d = d2 = j >= 3 ? listtag.m_128772_(2) : (double)pos.m_123343_() + (level.f_46441_.m_188500_() - level.f_46441_.m_188500_()) * (double)this.f_45453_ + 0.5;
                    if (!level.m_45772_(((EntityType)optional.get()).m_20585_(d0, d1, d2))) continue;
                    BlockPos blockpos = BlockPos.m_274561_((double)d0, (double)d1, (double)d2);
                    if (!spawnData.m_186574_().isPresent() ? !SpawnPlacements.m_217074_((EntityType)((EntityType)optional.get()), (ServerLevelAccessor)level, (MobSpawnType)MobSpawnType.SPAWNER, (BlockPos)blockpos, (RandomSource)level.m_213780_()) : !((EntityType)optional.get()).m_20674_().m_21609_() && level.m_46791_() == Difficulty.PEACEFUL || !(spawndata$customspawnrules = (SpawnData.CustomSpawnRules)spawnData.m_186574_().get()).f_186584_().m_184578_((Comparable)Integer.valueOf(level.m_45517_(LightLayer.BLOCK, blockpos))) || !spawndata$customspawnrules.f_186585_().m_184578_((Comparable)Integer.valueOf(level.m_45517_(LightLayer.SKY, blockpos)))) continue;
                    Entity entity = EntityType.m_20645_((CompoundTag)compoundtag, (Level)level, p_151310_ -> {
                        p_151310_.m_7678_(d0, d1, d2, p_151310_.m_146908_(), p_151310_.m_146909_());
                        return p_151310_;
                    });
                    if (entity == null) {
                        this.m_151350_((Level)level, pos);
                        return;
                    }
                    int k = level.m_45976_(entity.getClass(), new AABB((double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), (double)(pos.m_123341_() + 1), (double)(pos.m_123342_() + 1), (double)(pos.m_123343_() + 1)).m_82400_((double)this.f_45453_)).size();
                    if (k >= this.f_45451_) {
                        this.m_151350_((Level)level, pos);
                        return;
                    }
                    entity.m_7678_(entity.m_20185_(), entity.m_20186_(), entity.m_20189_(), level.f_46441_.m_188501_() * 360.0f, 0.0f);
                    if (entity instanceof Mob) {
                        Mob mob = (Mob)entity;
                        if (!ForgeEventFactory.checkSpawnPositionSpawner((Mob)mob, (ServerLevelAccessor)level, (MobSpawnType)MobSpawnType.SPAWNER, (SpawnData)spawnData, (BaseSpawner)((BaseSpawner)this))) continue;
                        MobSpawnEvent.FinalizeSpawn event = ForgeEventFactory.onFinalizeSpawnSpawner((Mob)mob, (ServerLevelAccessor)level, (DifficultyInstance)level.m_6436_(entity.m_20183_()), null, (CompoundTag)compoundtag, (BaseSpawner)((BaseSpawner)this));
                        if (event != null && spawnData.m_186567_().m_128440_() == 1 && spawnData.m_186567_().m_128425_("id", 8)) {
                            ((Mob)entity).m_6518_((ServerLevelAccessor)level, event.getDifficulty(), event.getSpawnType(), event.getSpawnData(), event.getSpawnTag());
                        }
                        if (((WorldBridge)mob.m_9236_()).bridge$spigotConfig().nerfSpawnerMobs) {
                            ((MobEntityBridge)mob).bridge$setAware(false);
                        }
                    }
                    if (CraftEventFactory.callSpawnerSpawnEvent(entity, pos).isCancelled()) {
                        Entity vehicle = entity.m_20202_();
                        if (vehicle != null) {
                            vehicle.m_146870_();
                        }
                        for (Entity passenger : entity.m_146897_()) {
                            passenger.m_146870_();
                        }
                        continue;
                    }
                    if (CraftEventFactory.callSpawnerSpawnEvent(entity, pos).isCancelled()) continue;
                    ((ServerWorldBridge)level).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.SPAWNER);
                    if (!level.m_8860_(entity)) {
                        this.m_151350_((Level)level, pos);
                        return;
                    }
                    level.m_46796_(2004, pos, 0);
                    level.m_142346_(entity, GameEvent.f_157810_, blockpos);
                    if (entity instanceof Mob) {
                        ((Mob)entity).m_21373_();
                    }
                    flag = true;
                }
                if (flag) {
                    this.m_151350_((Level)level, pos);
                }
            }
        }
    }
}

