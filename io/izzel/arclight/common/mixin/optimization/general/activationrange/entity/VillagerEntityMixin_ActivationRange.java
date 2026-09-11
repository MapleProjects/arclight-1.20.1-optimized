/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.npc.Villager
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange.entity;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.optimization.general.activationrange.EntityMixin_ActivationRange;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Villager.class})
public abstract class VillagerEntityMixin_ActivationRange
extends EntityMixin_ActivationRange {
    @Shadow
    protected abstract void m_8024_();

    @Override
    public void inactiveTick() {
        if (((WorldBridge)this.m_9236_()).bridge$spigotConfig().tickInactiveVillagers && ((Villager)this).m_21515_()) {
            this.m_8024_();
        }
        super.inactiveTick();
    }
}

