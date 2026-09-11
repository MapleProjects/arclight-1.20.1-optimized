/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.WeightedPressurePlateBlock
 *  net.minecraft.world.phys.AABB
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Redirect
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.BasePressurePlateBlockMixin;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraft.world.phys.AABB;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value={WeightedPressurePlateBlock.class})
public abstract class WeightedPressurePlateBlockMixin
extends BasePressurePlateBlockMixin {
    private static <T extends Entity> List<T> getEntities(Level world, AABB axisalignedbb, Class<T> oclass) {
        return world.m_6443_(oclass, axisalignedbb, EntitySelector.f_20408_.and(entity -> !entity.m_6090_()));
    }

    @Redirect(method={"getSignalStrength"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/WeightedPressurePlateBlock;getEntityCount(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/phys/AABB;Ljava/lang/Class;)I"))
    public int arclight$entityInteract(Level level, AABB aabb, Class<Entity> aClass, Level world, BlockPos pos) {
        int i = 0;
        for (Entity entity : WeightedPressurePlateBlockMixin.getEntities(level, aabb, aClass)) {
            Event cancellable;
            if (entity instanceof Player) {
                cancellable = CraftEventFactory.callPlayerInteractEvent((Player)entity, Action.PHYSICAL, pos, null, null, null);
            } else {
                cancellable = new EntityInteractEvent(((EntityBridge)entity).bridge$getBukkitEntity(), CraftBlock.at((LevelAccessor)world, pos));
                Bukkit.getPluginManager().callEvent((EntityInteractEvent)cancellable);
            }
            if (cancellable.isCancelled()) continue;
            ++i;
        }
        return i;
    }
}

