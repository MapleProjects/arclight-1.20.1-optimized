/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.InteractionHand
 *  net.minecraft.world.InteractionResult
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.Mob
 *  net.minecraft.world.entity.decoration.LeashFenceKnotEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.gameevent.GameEvent
 *  net.minecraft.world.phys.AABB
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.entity.decoration;

import io.izzel.arclight.common.mixin.core.world.entity.item.HangingEntityMixin;
import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={LeashFenceKnotEntity.class})
public abstract class LeashFenceKnotEntityMixin
extends HangingEntityMixin {
    @Overwrite
    public InteractionResult m_6096_(Player entityhuman, InteractionHand enumhand) {
        if (this.m_9236_().f_46443_) {
            return InteractionResult.SUCCESS;
        }
        boolean flag = false;
        double d0 = 7.0;
        List list = this.m_9236_().m_45976_(Mob.class, new AABB(this.m_20185_() - 7.0, this.m_20186_() - 7.0, this.m_20189_() - 7.0, this.m_20185_() + 7.0, this.m_20186_() + 7.0, this.m_20189_() + 7.0));
        for (Mob entityinsentient : list) {
            if (entityinsentient.m_21524_() != entityhuman) continue;
            if (CraftEventFactory.callPlayerLeashEntityEvent(entityinsentient, (Entity)((LeashFenceKnotEntity)this), entityhuman, enumhand).isCancelled()) {
                ((ServerPlayer)entityhuman).f_8906_.m_9829_((Packet)new ClientboundSetEntityLinkPacket((Entity)entityinsentient, entityinsentient.m_21524_()));
                continue;
            }
            entityinsentient.m_21463_((Entity)((LeashFenceKnotEntity)this), true);
            flag = true;
        }
        boolean flag1 = false;
        if (!flag) {
            boolean die = true;
            for (Mob entityinsentient : list) {
                if (!entityinsentient.m_21523_() || entityinsentient.m_21524_() != this) continue;
                if (CraftEventFactory.callPlayerUnleashEntityEvent(entityinsentient, entityhuman, enumhand).isCancelled()) {
                    die = false;
                    continue;
                }
                entityinsentient.m_21455_(true, !entityhuman.m_150110_().f_35937_);
                flag1 = true;
            }
            if (die) {
                this.m_146870_();
            }
        }
        if (flag || flag1) {
            this.m_146852_(GameEvent.f_157791_, (Entity)entityhuman);
        }
        return InteractionResult.CONSUME;
    }
}

