/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.critereon.PlayerTrigger
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.entity.raid.Raid
 *  net.minecraft.world.entity.raid.Raider
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.Slice
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.raid;

import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.core.world.raid.RaidBridge;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raider;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.raid.RaidStopEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Raid.class})
public class RaidMixin
implements RaidBridge {
    @Shadow
    @Final
    private Map<Integer, Set<Raider>> f_37671_;
    @Shadow
    @Final
    private ServerLevel f_37675_;
    private transient List<Player> arclight$winners;
    private transient Raider arclight$leader;
    private transient List<Raider> arclight$raiders;

    @Inject(method={"tick"}, at={@At(value="INVOKE", ordinal=0, target="Lnet/minecraft/world/entity/raid/Raid;stop()V")}, slice={@Slice(from=@At(value="FIELD", target="Lnet/minecraft/world/Difficulty;PEACEFUL:Lnet/minecraft/world/Difficulty;"))})
    public void arclight$stopPeace(CallbackInfo ci) {
        CraftEventFactory.callRaidStopEvent((Raid)this, RaidStopEvent.Reason.PEACE);
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;stop()V")}, slice={@Slice(from=@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;isVillage(Lnet/minecraft/core/BlockPos;)Z"), to=@At(value="FIELD", opcode=181, target="Lnet/minecraft/world/entity/raid/Raid;ticksActive:J"))})
    public void arclight$stopNotInVillage(CallbackInfo ci) {
        CraftEventFactory.callRaidStopEvent((Raid)this, RaidStopEvent.Reason.NOT_IN_VILLAGE);
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;stop()V")}, slice={@Slice(from=@At(value="FIELD", opcode=181, target="Lnet/minecraft/world/entity/raid/Raid;ticksActive:J"), to=@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;getTotalRaidersAlive()I"))})
    public void arclight$stopTimeout(CallbackInfo ci) {
        CraftEventFactory.callRaidStopEvent((Raid)this, RaidStopEvent.Reason.TIMEOUT);
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;stop()V")}, slice={@Slice(from=@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;shouldSpawnGroup()Z"), to=@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;isStarted()Z"))})
    public void arclight$stopUnspawnable(CallbackInfo ci) {
        CraftEventFactory.callRaidStopEvent((Raid)this, RaidStopEvent.Reason.UNSPAWNABLE);
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;stop()V")}, slice={@Slice(from=@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;isOver()Z"))})
    public void arclight$stopFinish(CallbackInfo ci) {
        CraftEventFactory.callRaidStopEvent((Raid)this, RaidStopEvent.Reason.FINISHED);
    }

    @Inject(method={"tick"}, at={@At(value="FIELD", shift=At.Shift.BY, by=2, target="Lnet/minecraft/world/entity/raid/Raid$RaidStatus;LOSS:Lnet/minecraft/world/entity/raid/Raid$RaidStatus;")})
    public void arclight$finishNone(CallbackInfo ci) {
        CraftEventFactory.callRaidFinishEvent((Raid)this, new ArrayList<Player>());
    }

    @Redirect(method={"tick"}, at=@At(value="INVOKE", target="Lnet/minecraft/advancements/critereon/PlayerTrigger;trigger(Lnet/minecraft/server/level/ServerPlayer;)V"))
    public void arclight$addWinner(PlayerTrigger trigger, ServerPlayer player) {
        trigger.m_222618_(player);
        if (this.arclight$winners == null) {
            this.arclight$winners = new ArrayList<Player>();
        }
        this.arclight$winners.add(((ServerPlayerEntityBridge)player).bridge$getBukkitEntity());
    }

    @Inject(method={"tick"}, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;setDirty()V")})
    public void arclight$finish(CallbackInfo ci) {
        ArrayList<Player> winners = this.arclight$winners == null ? new ArrayList() : this.arclight$winners;
        this.arclight$winners = null;
        CraftEventFactory.callRaidFinishEvent((Raid)this, winners);
    }

    @Redirect(method={"spawnGroup"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;setLeader(ILnet/minecraft/world/entity/raid/Raider;)V"))
    public void arclight$captureLeader(Raid raid, int raidId, Raider entity) {
        raid.m_37710_(raidId, entity);
        this.arclight$leader = entity;
    }

    @Redirect(method={"spawnGroup"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;joinRaid(ILnet/minecraft/world/entity/raid/Raider;Lnet/minecraft/core/BlockPos;Z)V"))
    public void arclight$captureRaider(Raid raid, int wave, Raider entity, BlockPos pos, boolean flag) {
        raid.m_37713_(wave, entity, pos, flag);
        if (this.arclight$raiders == null) {
            this.arclight$raiders = new ArrayList<Raider>();
        }
        this.arclight$raiders.add(entity);
    }

    @Inject(method={"spawnGroup"}, at={@At(value="RETURN")})
    public void arclight$spawnWave(BlockPos pos, CallbackInfo ci) {
        CraftEventFactory.callRaidSpawnWaveEvent((Raid)this, this.arclight$leader, this.arclight$raiders);
    }

    @Inject(method={"joinRaid(ILnet/minecraft/world/entity/raid/Raider;Lnet/minecraft/core/BlockPos;Z)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/server/level/ServerLevel;addFreshEntityWithPassengers(Lnet/minecraft/world/entity/Entity;)V")})
    public void arclight$addEntity(int wave, Raider p_221317_2_, BlockPos p_221317_3_, boolean p_221317_4_, CallbackInfo ci) {
        ((WorldBridge)this.f_37675_).bridge$pushAddEntityReason(CreatureSpawnEvent.SpawnReason.RAID);
    }

    public Collection<Raider> getRaiders() {
        HashSet<Raider> set = new HashSet<Raider>();
        for (Set<Raider> entities : this.f_37671_.values()) {
            set.addAll(entities);
        }
        return set;
    }

    @Override
    public Collection<Raider> bridge$getRaiders() {
        return this.getRaiders();
    }
}

