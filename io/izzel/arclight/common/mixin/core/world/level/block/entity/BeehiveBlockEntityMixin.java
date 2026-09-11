/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.tags.EntityTypeTags
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.animal.Bee
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity$BeeData
 *  net.minecraft.world.level.block.entity.BeehiveBlockEntity$BeeReleaseStatus
 *  net.minecraft.world.level.block.state.BlockState
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import com.google.common.collect.Lists;
import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.BlockEntityMixin;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityEnterBlockEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BeehiveBlockEntity.class})
public abstract class BeehiveBlockEntityMixin
extends BlockEntityMixin {
    @Shadow
    @Final
    private List<BeehiveBlockEntity.BeeData> f_58732_;
    @Shadow
    @javax.annotation.Nullable
    public BlockPos f_58733_;
    public int maxBees = 3;
    private static transient boolean arclight$force;

    @Shadow
    private static boolean m_155136_(Level p_155137_, BlockPos p_155138_, BlockState p_155139_, BeehiveBlockEntity.BeeData p_155140_, @Nullable List<Entity> p_155141_, BeehiveBlockEntity.BeeReleaseStatus p_155142_, @Nullable BlockPos p_155143_) {
        return false;
    }

    @Overwrite
    public boolean m_58775_() {
        return this.f_58732_.size() >= this.maxBees;
    }

    @Redirect(method={"emptyAllLivingFromHive"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/animal/Bee;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void arclight$angryReason(Bee beeEntity, LivingEntity livingEntity) {
        ((MobEntityBridge)beeEntity).bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true);
        beeEntity.m_6710_(livingEntity);
    }

    public List<Entity> releaseBees(BlockState blockState, BeehiveBlockEntity.BeeReleaseStatus state, boolean force) {
        ArrayList list = Lists.newArrayList();
        this.f_58732_.removeIf(bee -> BeehiveBlockEntityMixin.releaseBee(this.f_58857_, this.f_58858_, blockState, bee, list, state, this.f_58733_, force));
        return list;
    }

    @Redirect(method={"addOccupantWithPresetTicks"}, at=@At(value="INVOKE", remap=false, target="Ljava/util/List;size()I"))
    private int arclight$maxBee(List<?> list) {
        return list.size() < this.maxBees ? 1 : 3;
    }

    @Inject(method={"addOccupantWithPresetTicks(Lnet/minecraft/world/entity/Entity;ZI)V"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;stopRiding()V")})
    private void arclight$beeEnterBlock(Entity entity, boolean p_226962_2_, int p_226962_3_, CallbackInfo ci) {
        if (this.f_58857_ != null) {
            EntityEnterBlockEvent event = new EntityEnterBlockEvent(((EntityBridge)entity).bridge$getBukkitEntity(), CraftBlock.at((LevelAccessor)this.f_58857_, this.f_58858_));
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                if (entity instanceof Bee) {
                    ((Bee)entity).m_27915_(400);
                }
                ci.cancel();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static boolean releaseBee(Level world, BlockPos pos, BlockState state, BeehiveBlockEntity.BeeData beeData, @javax.annotation.Nullable List<Entity> list, BeehiveBlockEntity.BeeReleaseStatus status, @javax.annotation.Nullable BlockPos pos1, boolean force) {
        arclight$force = force;
        try {
            boolean bl = BeehiveBlockEntityMixin.m_155136_(world, pos, state, beeData, list, status, pos1);
            return bl;
        }
        finally {
            arclight$force = false;
        }
    }

    @Redirect(method={"releaseOccupant"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;isNight()Z"))
    private static boolean arclight$bypassNightCheck(Level world) {
        return !arclight$force && world.m_46462_();
    }

    @Redirect(method={"releaseOccupant"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/Entity;getType()Lnet/minecraft/world/entity/EntityType;"))
    private static EntityType<?> arclight$spawnFirst(Entity entity, Level level) {
        EntityType type = entity.m_6095_();
        if (type.m_204039_(EntityTypeTags.f_13122_)) {
            ((WorldBridge)level).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.BEEHIVE);
            if (!level.m_7967_(entity)) {
                return EntityType.f_20462_;
            }
            return type;
        }
        return type;
    }

    @Redirect(method={"releaseOccupant"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private static boolean arclight$addedBefore(Level world, Entity entityIn) {
        return true;
    }

    @Inject(method={"load"}, at={@At(value="RETURN")})
    private void arclight$readMax(CompoundTag compound, CallbackInfo ci) {
        if (compound.m_128441_("Bukkit.MaxEntities")) {
            this.maxBees = compound.m_128451_("Bukkit.MaxEntities");
        }
    }

    @Inject(method={"saveAdditional"}, at={@At(value="RETURN")})
    private void arclight$writeMax(CompoundTag compound, CallbackInfo ci) {
        compound.m_128405_("Bukkit.MaxEntities", this.maxBees);
    }
}

