/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LightningBolt
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.LightningRodBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.world.server.ServerWorldBridge;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LightningRodBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LightningRodBlock.class})
public class LightningRodBlockMixin {
    @Inject(method={"onLightningStrike"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$redstoneChange(BlockState state, Level level, BlockPos pos, CallbackInfo ci) {
        boolean powered = (Boolean)state.m_61143_((Property)LightningRodBlock.f_153703_);
        int old = powered ? 15 : 0;
        int current = !powered ? 15 : 0;
        BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(CraftBlock.at((LevelAccessor)level, pos), old, current);
        Bukkit.getPluginManager().callEvent(eventRedstone);
        if (eventRedstone.getNewCurrent() <= 0) {
            ci.cancel();
        }
    }

    @Redirect(method={"onProjectileHit"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean arclight$strikeReason(Level level, Entity entity) {
        if (!DistValidate.isValid((LevelAccessor)level)) {
            return level.m_7967_(entity);
        }
        ((ServerWorldBridge)level).bridge$strikeLightning((LightningBolt)entity, LightningStrikeEvent.Cause.TRIDENT);
        return true;
    }
}

