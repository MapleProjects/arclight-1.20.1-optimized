/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.boss.enderdragon.EnderDragon
 *  net.minecraft.world.entity.boss.enderdragon.phases.DragonPhaseInstance
 *  net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase
 *  net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhaseManager
 *  org.slf4j.Logger
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.entity.boss.enderdragon.phases;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonPhaseInstance;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhaseManager;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderDragon;
import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={EnderDragonPhaseManager.class})
public abstract class EnderDragonPhaseManagerMixin {
    @Shadow
    @Final
    private static Logger f_31408_;
    @Shadow
    @Final
    private EnderDragon f_31409_;
    @Shadow
    private DragonPhaseInstance f_31411_;

    @Shadow
    public abstract <T extends DragonPhaseInstance> T m_31418_(EnderDragonPhase<T> var1);

    @Overwrite
    public void m_31416_(EnderDragonPhase<?> phaseIn) {
        if (this.f_31411_ == null || phaseIn != this.f_31411_.m_7309_()) {
            if (this.f_31411_ != null) {
                this.f_31411_.m_7081_();
            }
            EnderDragonChangePhaseEvent event = new EnderDragonChangePhaseEvent((CraftEnderDragon)((EntityBridge)this.f_31409_).bridge$getBukkitEntity(), this.f_31411_ == null ? null : CraftEnderDragon.getBukkitPhase(this.f_31411_.m_7309_()), CraftEnderDragon.getBukkitPhase(phaseIn));
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            phaseIn = CraftEnderDragon.getMinecraftPhase(event.getNewPhase());
            this.f_31411_ = this.m_31418_(phaseIn);
            if (!this.f_31409_.m_9236_().f_46443_) {
                this.f_31409_.m_20088_().m_135381_(EnderDragon.f_31067_, (Object)phaseIn.m_31405_());
            }
            f_31408_.debug("Dragon is now in phase {} on the {}", (Object)phaseIn, (Object)(this.f_31409_.m_9236_().f_46443_ ? "client" : "server"));
            this.f_31411_.m_7083_();
        }
    }
}

