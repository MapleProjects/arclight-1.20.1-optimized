/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntitySelector
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.PressurePlateBlock
 *  net.minecraft.world.level.block.PressurePlateBlock$Sensitivity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.phys.AABB
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.BasePressurePlateBlockMixin;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={PressurePlateBlock.class})
public abstract class PressurePlateBlockMixin
extends BasePressurePlateBlockMixin {
    @Shadow
    @Final
    private PressurePlateBlock.Sensitivity f_55250_;

    @Shadow
    protected abstract int m_6016_(BlockState var1);

    private static <T extends Entity> List<T> getEntities(Level world, AABB axisalignedbb, Class<T> oclass) {
        return world.m_6443_(oclass, axisalignedbb, EntitySelector.f_20408_.and(entity -> !entity.m_6090_()));
    }

    @Override
    @Overwrite
    protected int m_6693_(Level world, BlockPos blockposition) {
        Class<Entity> oclass = switch (this.f_55250_) {
            case PressurePlateBlock.Sensitivity.EVERYTHING -> Entity.class;
            case PressurePlateBlock.Sensitivity.MOBS -> LivingEntity.class;
            default -> throw new IncompatibleClassChangeError();
        };
        Class<Entity> oclass1 = oclass;
        for (Entity entity : PressurePlateBlockMixin.getEntities(world, f_49287_.m_82338_(blockposition), oclass)) {
            if (this.m_6016_(world.m_8055_(blockposition)) == 0) {
                Event cancellable;
                CraftWorld bworld = ((WorldBridge)world).bridge$getWorld();
                PluginManager manager = Bukkit.getPluginManager();
                if (entity instanceof Player) {
                    cancellable = CraftEventFactory.callPlayerInteractEvent((Player)entity, Action.PHYSICAL, blockposition, null, null, null);
                } else {
                    cancellable = new EntityInteractEvent(((EntityBridge)entity).bridge$getBukkitEntity(), bworld.getBlockAt(blockposition.m_123341_(), blockposition.m_123342_(), blockposition.m_123343_()));
                    manager.callEvent((EntityInteractEvent)cancellable);
                }
                if (cancellable.isCancelled()) continue;
            }
            return 15;
        }
        return 0;
    }
}

