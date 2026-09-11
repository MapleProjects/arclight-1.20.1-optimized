/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.effect.MobEffects
 *  net.minecraft.world.entity.raid.Raid
 *  net.minecraft.world.entity.raid.Raids
 *  net.minecraft.world.level.dimension.DimensionType
 *  net.minecraft.world.phys.Vec3
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.entity.raid;

import java.util.List;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.raid.Raid;
import net.minecraft.world.entity.raid.Raids;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={Raids.class})
public class RaidManagerMixin {
    @Shadow
    @Final
    public Map<Integer, Raid> f_37951_;

    @Inject(method={"createOrExtendRaid"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/raid/Raid;absorbBadOmen(Lnet/minecraft/world/entity/player/Player;)V")})
    public void arclight$raidTrigger(ServerPlayer playerEntity, CallbackInfoReturnable<Raid> cir, DimensionType dimensionType, BlockPos pos, BlockPos pos1, List<?> list, int i, Vec3 vec, Raid raid) {
        if (!CraftEventFactory.callRaidTriggerEvent(raid, playerEntity)) {
            playerEntity.m_21195_(MobEffects.f_19594_);
            this.f_37951_.remove(raid.m_37781_(), raid);
            cir.setReturnValue(null);
        }
    }
}

