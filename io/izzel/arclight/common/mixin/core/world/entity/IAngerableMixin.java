/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.NeutralMob
 *  org.jetbrains.annotations.Nullable
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity;

import io.izzel.arclight.common.bridge.core.entity.MobEntityBridge;
import java.util.UUID;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.NeutralMob;
import org.bukkit.event.entity.EntityTargetEvent;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={NeutralMob.class})
public interface IAngerableMixin
extends MobEntityBridge {
    @Shadow
    public void m_6703_(@Nullable LivingEntity var1);

    @Shadow
    public void m_6925_(@Nullable UUID var1);

    @Shadow
    public void m_6710_(@Nullable LivingEntity var1);

    @Shadow
    public void m_7870_(int var1);

    @Overwrite
    default public void m_21662_() {
        this.m_6703_(null);
        this.m_6925_(null);
        this.bridge$pushGoalTargetReason(EntityTargetEvent.TargetReason.FORGOT_TARGET, true);
        this.m_6710_(null);
        this.m_7870_(0);
    }

    default public boolean setGoalTarget(LivingEntity livingEntity, EntityTargetEvent.TargetReason reason, boolean fireEvent) {
        this.bridge$pushGoalTargetReason(reason, fireEvent);
        this.m_6710_(livingEntity);
        return this.bridge$lastGoalTargetResult();
    }
}

