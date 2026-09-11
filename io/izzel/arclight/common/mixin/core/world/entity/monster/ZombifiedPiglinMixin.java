/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.ai.attributes.Attributes
 *  net.minecraft.world.entity.monster.ZombifiedPiglin
 *  net.minecraft.world.phys.AABB
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import io.izzel.arclight.common.mixin.core.world.entity.monster.ZombieMixin;
import java.util.UUID;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Bukkit;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.PigZombieAngerEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={ZombifiedPiglin.class})
public abstract class ZombifiedPiglinMixin
extends ZombieMixin {
    @Shadow
    public abstract UUID m_6120_();

    @Shadow
    public abstract int m_6784_();

    @Overwrite
    private void m_34473_() {
        double d0 = this.m_21133_(Attributes.f_22277_);
        AABB axisalignedbb = AABB.m_82333_((Vec3)this.m_20182_()).m_82377_(d0, 10.0, d0);
        for (ZombifiedPiglin piglinEntity : this.m_9236_().m_45976_(ZombifiedPiglin.class, axisalignedbb)) {
            if (piglinEntity == this || piglinEntity.m_5448_() != null || piglinEntity.m_7307_((Entity)this.m_5448_())) continue;
            ((MobEntityBridge)piglinEntity).bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY, true);
            piglinEntity.m_6710_(this.m_5448_());
        }
    }

    @ModifyArg(method={"startPersistentAngerTimer"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/ZombifiedPiglin;setRemainingPersistentAngerTime(I)V"))
    private int arclight$pigAngry(int time) {
        Entity entity = ((ServerLevel)this.m_9236_()).m_8791_(this.m_6120_());
        PigZombieAngerEvent event = new PigZombieAngerEvent((PigZombie)((Object)this.getBukkitEntity()), entity == null ? null : ((EntityBridge)entity).bridge$getBukkitEntity(), time);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return this.m_6784_();
        }
        return event.getNewAnger();
    }
}

