/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.inventory.ContainerLevelAccess
 *  net.minecraft.world.level.Level
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.util.IWorldPosCallableBridge;
import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import org.bukkit.Location;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={ContainerLevelAccess.class})
public interface ContainerLevelAccessMixin
extends IWorldPosCallableBridge {
    default public Level getWorld() {
        return this.bridge$getWorld();
    }

    default public BlockPos getPosition() {
        return this.bridge$getPosition();
    }

    default public Location getLocation() {
        return this.bridge$getLocation();
    }

    @Overwrite
    public static ContainerLevelAccess m_39289_(final Level world, final BlockPos pos) {
        class Anonymous
        implements ContainerLevelAccess,
        IWorldPosCallableBridge {
            Anonymous() {
            }

            public <T> Optional<T> m_6721_(BiFunction<Level, BlockPos, T> worldPosConsumer) {
                return Optional.of(worldPosConsumer.apply(world, pos));
            }

            @Override
            public Level bridge$getWorld() {
                return world;
            }

            @Override
            public BlockPos bridge$getPosition() {
                return pos;
            }
        }
        return new Anonymous();
    }
}

