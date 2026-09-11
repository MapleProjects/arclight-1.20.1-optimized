/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Vec3i
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.tags.BlockTags
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.boss.enderdragon.phases.DragonPhaseInstance
 *  net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Explosion
 *  net.minecraft.world.level.Explosion$BlockInteraction
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.Block
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.dimension.end.EndDragonFight
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  net.minecraftforge.common.ForgeHooks
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.entity.boss.enderdragon;

import io.izzel.arclight.common.mixin.core.world.entity.MobMixin;
import java.util.ArrayList;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.end.EndDragonFight;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={EnderDragon.class})
public abstract class EnderDragonMixin
extends MobMixin {
    @Shadow
    @Nullable
    private EndDragonFight f_31073_;
    private final Explosion explosionSource = new Explosion(this.m_9236_(), (Entity)((EnderDragon)this), null, null, Double.NaN, Double.NaN, Double.NaN, Float.NaN, true, Explosion.BlockInteraction.DESTROY);

    @Redirect(method={"aiStep"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/boss/enderdragon/phases/DragonPhaseInstance;getFlyTargetLocation()Lnet/minecraft/world/phys/Vec3;"))
    private Vec3 arclight$noMoveHovering(DragonPhaseInstance phase) {
        Vec3 vec3d = phase.m_5535_();
        return vec3d != null && phase.m_7309_() != EnderDragonPhase.f_31387_ ? vec3d : null;
    }

    @Redirect(method={"checkCrystals"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/boss/enderdragon/EnderDragon;setHealth(F)V"))
    private void arclight$regainHealth(EnderDragon enderDragonEntity, float health) {
        EntityRegainHealthEvent event = new EntityRegainHealthEvent(this.getBukkitEntity(), 1.0, EntityRegainHealthEvent.RegainReason.ENDER_CRYSTAL);
        Bukkit.getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            this.m_21153_((float)((double)this.m_21223_() + event.getAmount()));
        }
    }

    @Overwrite
    private boolean m_31139_(AABB axisalignedbb) {
        int i = Mth.m_14107_((double)axisalignedbb.f_82288_);
        int j = Mth.m_14107_((double)axisalignedbb.f_82289_);
        int k = Mth.m_14107_((double)axisalignedbb.f_82290_);
        int l = Mth.m_14107_((double)axisalignedbb.f_82291_);
        int i2 = Mth.m_14107_((double)axisalignedbb.f_82292_);
        int j2 = Mth.m_14107_((double)axisalignedbb.f_82293_);
        boolean flag = false;
        boolean flag2 = false;
        ArrayList<org.bukkit.block.Block> destroyedBlocks = new ArrayList<org.bukkit.block.Block>();
        for (int k2 = i; k2 <= l; ++k2) {
            for (int l2 = j; l2 <= i2; ++l2) {
                for (int i3 = k; i3 <= j2; ++i3) {
                    BlockPos blockposition = new BlockPos(k2, l2, i3);
                    BlockState iblockdata = this.m_9236_().m_8055_(blockposition);
                    if (iblockdata.m_60795_() || iblockdata.m_204336_(BlockTags.f_215822_)) continue;
                    if (ForgeHooks.canEntityDestroy((Level)this.m_9236_(), (BlockPos)blockposition, (LivingEntity)((EnderDragon)this)) && !iblockdata.m_204336_(BlockTags.f_13069_)) {
                        flag2 = true;
                        destroyedBlocks.add(CraftBlock.at((LevelAccessor)this.m_9236_(), blockposition));
                        continue;
                    }
                    flag = true;
                }
            }
        }
        if (!flag2) {
            return flag;
        }
        CraftLivingEntity bukkitEntity = this.getBukkitEntity();
        EntityExplodeEvent event = new EntityExplodeEvent(bukkitEntity, bukkitEntity.getLocation(), destroyedBlocks, 0.0f);
        bukkitEntity.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return flag;
        }
        if (event.getYield() == 0.0f) {
            for (org.bukkit.block.Block block2 : event.blockList()) {
                this.m_9236_().m_7471_(new BlockPos(block2.getX(), block2.getY(), block2.getZ()), false);
            }
        } else {
            for (org.bukkit.block.Block block2 : event.blockList()) {
                Material blockId = block2.getType();
                if (blockId.isAir()) continue;
                CraftBlock craftBlock = (CraftBlock)block2;
                BlockPos blockposition2 = craftBlock.getPosition();
                Block nmsBlock = craftBlock.getNMS().m_60734_();
                if (nmsBlock.m_6903_(this.explosionSource)) {
                    BlockEntity tileentity = craftBlock.getNMS().m_155947_() ? this.m_9236_().m_7702_(blockposition2) : null;
                    LootParams.Builder loottableinfo_builder = new LootParams.Builder((ServerLevel)this.m_9236_()).m_287286_(LootContextParams.f_81460_, (Object)Vec3.m_82512_((Vec3i)blockposition2)).m_287286_(LootContextParams.f_81463_, (Object)ItemStack.f_41583_).m_287286_(LootContextParams.f_81464_, (Object)Float.valueOf(1.0f / event.getYield())).m_287289_(LootContextParams.f_81462_, (Object)tileentity);
                    for (ItemStack stack : craftBlock.getNMS().m_287290_(loottableinfo_builder)) {
                        Block.m_49840_((Level)this.m_9236_(), (BlockPos)blockposition2, (ItemStack)stack);
                    }
                    craftBlock.getNMS().m_222967_((ServerLevel)this.m_9236_(), blockposition2, ItemStack.f_41583_, false);
                }
                nmsBlock.m_7592_(this.m_9236_(), blockposition2, this.explosionSource);
                this.m_9236_().m_7471_(blockposition2, false);
            }
        }
        if (flag2) {
            BlockPos blockposition3 = new BlockPos(i + this.f_19796_.m_188503_(l - i + 1), j + this.f_19796_.m_188503_(i2 - j + 1), k + this.f_19796_.m_188503_(j2 - k + 1));
            this.m_9236_().m_46796_(2008, blockposition3, 0);
        }
        return flag;
    }

    @Override
    public int getExpReward() {
        boolean flag = this.m_9236_().m_46469_().m_46207_(GameRules.f_46135_);
        int short0 = 500;
        if (this.f_31073_ != null && !this.f_31073_.m_64099_()) {
            short0 = 12000;
        }
        return flag ? short0 : 0;
    }
}

