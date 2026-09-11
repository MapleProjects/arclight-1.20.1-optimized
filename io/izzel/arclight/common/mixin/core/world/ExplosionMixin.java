/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Pair
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectListIterator
 *  javax.annotation.Nullable
 *  net.minecraft.Util
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.core.particles.ParticleOptions
 *  net.minecraft.core.particles.ParticleTypes
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.sounds.SoundEvents
 *  net.minecraft.sounds.SoundSource
 *  net.minecraft.util.Mth
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.enchantment.ProtectionEnchantment
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Explosion$BlockInteraction
 *  net.minecraft.world.level.ExplosionDamageCalculator
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.BaseFireBlock
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.TntBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.level.material.FluidState
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.entity.PartEntity
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.gen.Accessor
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.ExplosionBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.entity.PartEntity;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.TNTPrimeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Explosion.class})
public abstract class ExplosionMixin
implements ExplosionBridge {
    @Shadow
    @Final
    private Level f_46012_;
    @Shadow
    @Final
    private Explosion.BlockInteraction f_46010_;
    @Shadow
    @Mutable
    @Final
    private float f_46017_;
    @Shadow
    @Final
    private ObjectArrayList<BlockPos> f_46020_;
    @Shadow
    @Final
    private double f_46013_;
    @Shadow
    @Final
    private double f_46014_;
    @Shadow
    @Final
    private double f_46015_;
    @Shadow
    @Final
    public Entity f_46016_;
    @Shadow
    @Final
    private Map<Player, Vec3> f_46021_;
    @Shadow
    @Final
    private boolean f_46009_;
    @Shadow
    @Final
    private RandomSource f_46011_;
    @Shadow
    @Final
    private ExplosionDamageCalculator f_46019_;
    public boolean wasCanceled = false;

    @Shadow
    public abstract DamageSource m_46077_();

    @Override
    @Accessor(value="source")
    public abstract Entity bridge$getExploder();

    @Override
    @Accessor(value="radius")
    public abstract float bridge$getSize();

    @Override
    @Accessor(value="radius")
    public abstract void bridge$setSize(float var1);

    @Override
    @Accessor(value="blockInteraction")
    public abstract Explosion.BlockInteraction bridge$getMode();

    @Shadow
    private static void m_46067_(ObjectArrayList<Pair<ItemStack, BlockPos>> dropPositionArray, ItemStack stack, BlockPos pos) {
    }

    @Shadow
    public abstract boolean m_254884_();

    @Shadow
    @Nullable
    public abstract LivingEntity m_252906_();

    @Shadow
    public static float m_46064_(Vec3 p_46065_, Entity p_46066_) {
        return 0.0f;
    }

    @Inject(method={"<init>(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/Entity;DDDFZLnet/minecraft/world/level/Explosion$BlockInteraction;)V"}, at={@At(value="RETURN")})
    public void arclight$adjustSize(Level worldIn, Entity exploderIn, double xIn, double yIn, double zIn, float sizeIn, boolean causesFireIn, Explosion.BlockInteraction modeIn, CallbackInfo ci) {
        this.f_46017_ = Math.max(sizeIn, 0.0f);
    }

    @Overwrite
    public void m_46061_() {
        if (this.f_46017_ < 0.1f) {
            return;
        }
        this.f_46012_.m_220400_(this.f_46016_, GameEvent.f_157812_, new Vec3(this.f_46013_, this.f_46014_, this.f_46015_));
        HashSet set = Sets.newHashSet();
        int i = 16;
        for (int j = 0; j < 16; ++j) {
            for (int k = 0; k < 16; ++k) {
                block2: for (int l = 0; l < 16; ++l) {
                    if (j != 0 && j != 15 && k != 0 && k != 15 && l != 0 && l != 15) continue;
                    double d0 = (float)j / 15.0f * 2.0f - 1.0f;
                    double d1 = (float)k / 15.0f * 2.0f - 1.0f;
                    double d2 = (float)l / 15.0f * 2.0f - 1.0f;
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 /= d3;
                    d1 /= d3;
                    d2 /= d3;
                    double d4 = this.f_46013_;
                    double d6 = this.f_46014_;
                    double d8 = this.f_46015_;
                    float f1 = 0.3f;
                    for (float f = this.f_46017_ * (0.7f + this.f_46012_.f_46441_.m_188501_() * 0.6f); f > 0.0f; f -= 0.22500001f) {
                        BlockPos blockpos = BlockPos.m_274561_((double)d4, (double)d6, (double)d8);
                        BlockState blockstate = this.f_46012_.m_8055_(blockpos);
                        FluidState fluidstate = this.f_46012_.m_6425_(blockpos);
                        if (!this.f_46012_.m_46739_(blockpos)) continue block2;
                        Optional optional = this.f_46019_.m_6617_((Explosion)this, (BlockGetter)this.f_46012_, blockpos, blockstate, fluidstate);
                        if (optional.isPresent()) {
                            f -= (((Float)optional.get()).floatValue() + 0.3f) * 0.3f;
                        }
                        if (f > 0.0f && this.f_46019_.m_6714_((Explosion)this, (BlockGetter)this.f_46012_, blockpos, blockstate, f)) {
                            set.add(blockpos);
                        }
                        d4 += d0 * (double)0.3f;
                        d6 += d1 * (double)0.3f;
                        d8 += d2 * (double)0.3f;
                    }
                }
            }
        }
        this.f_46020_.addAll((Collection)set);
        float f3 = this.f_46017_ * 2.0f;
        int k1 = Mth.m_14107_((double)(this.f_46013_ - (double)f3 - 1.0));
        int l1 = Mth.m_14107_((double)(this.f_46013_ + (double)f3 + 1.0));
        int i2 = Mth.m_14107_((double)(this.f_46014_ - (double)f3 - 1.0));
        int i1 = Mth.m_14107_((double)(this.f_46014_ + (double)f3 + 1.0));
        int j2 = Mth.m_14107_((double)(this.f_46015_ - (double)f3 - 1.0));
        int j1 = Mth.m_14107_((double)(this.f_46015_ + (double)f3 + 1.0));
        List list = this.f_46012_.m_45933_(this.f_46016_, new AABB((double)k1, (double)i2, (double)j2, (double)l1, (double)i1, (double)j1));
        ForgeEventFactory.onExplosionDetonate((Level)this.f_46012_, (Explosion)((Explosion)this), (List)list, (double)f3);
        Vec3 vec3d = new Vec3(this.f_46013_, this.f_46014_, this.f_46015_);
        for (Entity entity : list) {
            Player playerentity;
            double d9;
            double d7;
            double d5;
            double d13;
            double d12;
            if (entity.m_6128_() || !((d12 = Math.sqrt(entity.m_20238_(vec3d)) / (double)f3) <= 1.0) || (d13 = Math.sqrt((d5 = entity.m_20185_() - this.f_46013_) * d5 + (d7 = entity.m_20188_() - this.f_46014_) * d7 + (d9 = entity.m_20189_() - this.f_46015_) * d9)) == 0.0) continue;
            d5 /= d13;
            d7 /= d13;
            d9 /= d13;
            double d14 = Explosion.m_46064_((Vec3)vec3d, (Entity)entity);
            double d10 = (1.0 - d12) * d14;
            if (entity instanceof PartEntity) continue;
            CraftEventFactory.entityDamage = this.f_46016_;
            ((EntityBridge)entity).bridge$setLastDamageCancelled(false);
            PartEntity[] parts = entity.getParts();
            if (parts != null) {
                for (PartEntity part : parts) {
                    double d;
                    if (!list.contains(part)) continue;
                    double dist = Math.sqrt(part.m_20238_(vec3d)) / (double)f3;
                    if (!(d <= 1.0)) continue;
                    double dmg = (1.0 - dist) * (double)ExplosionMixin.m_46064_(vec3d, (Entity)part);
                    part.m_6469_(this.m_46077_(), (float)((int)((dmg * dmg + dmg) / 2.0 * 7.0 * (double)f3 + 1.0)));
                }
            } else {
                entity.m_6469_(this.m_46077_(), (float)((int)((d10 * d10 + d10) / 2.0 * 7.0 * (double)f3 + 1.0)));
            }
            CraftEventFactory.entityDamage = null;
            if (((EntityBridge)entity).bridge$isLastDamageCancelled()) continue;
            double d11 = entity instanceof LivingEntity ? ProtectionEnchantment.m_45135_((LivingEntity)((LivingEntity)entity), (double)d10) : d10;
            Vec3 vec3d1 = new Vec3(d5 *= d11, d7 *= d11, d9 *= d11);
            entity.m_20256_(entity.m_20184_().m_82549_(vec3d1));
            if (!(entity instanceof Player) || (playerentity = (Player)entity).m_5833_() || playerentity.m_7500_() && playerentity.m_150110_().f_35935_) continue;
            this.f_46021_.put(playerentity, vec3d1);
        }
    }

    @Override
    public boolean bridge$wasCancelled() {
        return this.wasCanceled;
    }

    @Overwrite
    public void m_46075_(boolean spawnParticles) {
        if (this.f_46012_.f_46443_) {
            this.f_46012_.m_7785_(this.f_46013_, this.f_46014_, this.f_46015_, SoundEvents.f_11913_, SoundSource.BLOCKS, 4.0f, (1.0f + (this.f_46012_.f_46441_.m_188501_() - this.f_46012_.f_46441_.m_188501_()) * 0.2f) * 0.7f, false);
        }
        boolean flag = this.m_254884_();
        if (spawnParticles) {
            if (!(this.f_46017_ < 2.0f) && flag) {
                this.f_46012_.m_7106_((ParticleOptions)ParticleTypes.f_123812_, this.f_46013_, this.f_46014_, this.f_46015_, 1.0, 0.0, 0.0);
            } else {
                this.f_46012_.m_7106_((ParticleOptions)ParticleTypes.f_123813_, this.f_46013_, this.f_46014_, this.f_46015_, 1.0, 0.0, 0.0);
            }
        }
        if (flag) {
            ObjectArrayList objectarraylist = new ObjectArrayList();
            boolean flag2 = this.m_252906_() instanceof Player;
            Util.m_214673_(this.f_46020_, (RandomSource)this.f_46012_.f_46441_);
            ObjectListIterator iterator = this.f_46020_.iterator();
            float yield = this.callBlockExplodeEvent();
            if (Float.isNaN(yield)) {
                this.wasCanceled = true;
                return;
            }
            while (iterator.hasNext()) {
                Level level;
                BlockPos blockpos = (BlockPos)iterator.next();
                BlockState blockstate = this.f_46012_.m_8055_(blockpos);
                Block block = blockstate.m_60734_();
                if (block instanceof TntBlock) {
                    BlockPos sourceBlock;
                    Entity sourceEntity = this.f_46016_ == null ? null : this.f_46016_;
                    BlockPos blockPos = sourceBlock = sourceEntity == null ? BlockPos.m_274561_((double)this.f_46013_, (double)this.f_46014_, (double)this.f_46015_) : null;
                    if (!CraftEventFactory.callTNTPrimeEvent(this.f_46012_, blockpos, TNTPrimeEvent.PrimeCause.EXPLOSION, sourceEntity, sourceBlock)) {
                        this.f_46012_.m_7260_(blockpos, Blocks.f_50016_.m_49966_(), blockstate, 3);
                        continue;
                    }
                }
                if (blockstate.m_60795_()) continue;
                BlockPos blockpos1 = blockpos.m_7949_();
                this.f_46012_.m_46473_().m_6180_("explosion_blocks");
                if (blockstate.canDropFromExplosion((BlockGetter)this.f_46012_, blockpos, (Explosion)this) && (level = this.f_46012_) instanceof ServerLevel) {
                    ServerLevel serverLevel = (ServerLevel)level;
                    BlockEntity tileentity = blockstate.m_155947_() ? this.f_46012_.m_7702_(blockpos) : null;
                    LootParams.Builder lootcontext$builder = new LootParams.Builder(serverLevel).m_287286_(LootContextParams.f_81460_, (Object)Vec3.m_82512_((Vec3i)blockpos)).m_287286_(LootContextParams.f_81463_, (Object)ItemStack.f_41583_).m_287289_(LootContextParams.f_81462_, (Object)tileentity).m_287289_(LootContextParams.f_81455_, (Object)this.f_46016_);
                    if (yield < 1.0f) {
                        lootcontext$builder.m_287286_(LootContextParams.f_81464_, (Object)Float.valueOf(1.0f / yield));
                    }
                    blockstate.m_222967_(serverLevel, blockpos, ItemStack.f_41583_, flag2);
                    blockstate.m_287290_(lootcontext$builder).forEach(stack -> ExplosionMixin.m_46067_((ObjectArrayList<Pair<ItemStack, BlockPos>>)objectarraylist, stack, blockpos1));
                }
                blockstate.onBlockExploded(this.f_46012_, blockpos, (Explosion)this);
                this.f_46012_.m_46473_().m_7238_();
            }
            for (Pair pair : objectarraylist) {
                Block.m_49840_((Level)this.f_46012_, (BlockPos)((BlockPos)pair.getSecond()), (ItemStack)((ItemStack)pair.getFirst()));
            }
        }
        if (this.f_46009_) {
            for (BlockPos blockpos2 : this.f_46020_) {
                BlockIgniteEvent event;
                if (this.f_46011_.m_188503_(3) != 0 || !this.f_46012_.m_8055_(blockpos2).m_60795_() || !this.f_46012_.m_8055_(blockpos2.m_7495_()).m_60804_((BlockGetter)this.f_46012_, blockpos2.m_7495_()) || (event = CraftEventFactory.callBlockIgniteEvent(this.f_46012_, blockpos2.m_123341_(), blockpos2.m_123342_(), blockpos2.m_123343_(), (Explosion)this)).isCancelled()) continue;
                this.f_46012_.m_46597_(blockpos2, BaseFireBlock.m_49245_((BlockGetter)this.f_46012_, (BlockPos)blockpos2));
            }
        }
    }

    @Inject(method={"addBlockDrops"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$fix(ObjectArrayList<Pair<ItemStack, BlockPos>> dropPositionArray, ItemStack stack, BlockPos pos, CallbackInfo ci) {
        if (stack.m_41619_()) {
            ci.cancel();
        }
    }

    private float callBlockExplodeEvent() {
        float bukkitYield;
        List<org.bukkit.block.Block> bukkitBlocks;
        boolean cancelled;
        CraftWorld world = ((WorldBridge)this.f_46012_).bridge$getWorld();
        CraftEntity exploder = this.f_46016_ == null ? null : ((EntityBridge)this.f_46016_).bridge$getBukkitEntity();
        Location location = new Location(world, this.f_46013_, this.f_46014_, this.f_46015_);
        ArrayList blockList = Lists.newArrayList();
        for (int i = this.f_46020_.size() - 1; i >= 0; --i) {
            BlockPos blockPos = (BlockPos)this.f_46020_.get(i);
            org.bukkit.block.Block block = world.getBlockAt(blockPos.m_123341_(), blockPos.m_123342_(), blockPos.m_123343_());
            if (block.getType().isAir()) continue;
            blockList.add(block);
        }
        if (exploder != null) {
            event = new EntityExplodeEvent(exploder, location, blockList, this.f_46010_ == Explosion.BlockInteraction.DESTROY_WITH_DECAY ? 1.0f / this.f_46017_ : 1.0f);
            Bukkit.getPluginManager().callEvent(event);
            cancelled = ((EntityExplodeEvent)event).isCancelled();
            bukkitBlocks = ((EntityExplodeEvent)event).blockList();
            bukkitYield = ((EntityExplodeEvent)event).getYield();
        } else {
            event = new BlockExplodeEvent(location.getBlock(), blockList, this.f_46010_ == Explosion.BlockInteraction.DESTROY_WITH_DECAY ? 1.0f / this.f_46017_ : 1.0f);
            Bukkit.getPluginManager().callEvent(event);
            cancelled = ((BlockExplodeEvent)event).isCancelled();
            bukkitBlocks = ((BlockExplodeEvent)event).blockList();
            bukkitYield = ((BlockExplodeEvent)event).getYield();
        }
        this.f_46020_.clear();
        for (org.bukkit.block.Block block : bukkitBlocks) {
            BlockPos blockPos = new BlockPos(block.getX(), block.getY(), block.getZ());
            this.f_46020_.add((Object)blockPos);
        }
        return cancelled ? Float.NaN : bukkitYield;
    }
}

