/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.entity.BellBlockEntity
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BellBlockEntity;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={BellBlockEntity.class})
public class BellBlockEntityMixin {
    @Redirect(method={"makeRaidersGlow"}, at=@At(value="INVOKE", remap=false, target="Ljava/util/stream/Stream;forEach(Ljava/util/function/Consumer;)V"))
    private static void arclight$bellResonate(Stream<LivingEntity> instance, Consumer<? super LivingEntity> consumer, Level level, BlockPos pos) {
        List<org.bukkit.entity.LivingEntity> list = instance.map(it -> (org.bukkit.entity.LivingEntity)((Object)((EntityBridge)it).bridge$getBukkitEntity())).toList();
        CraftEventFactory.handleBellResonateEvent(level, pos, list).forEach(consumer);
    }
}

