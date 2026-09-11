/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.CompoundContainer
 *  net.minecraft.world.Container
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets={"net/minecraft/world/level/block/ChestBlock$2$1"})
public class ChestBlock2_1Mixin {
    @Shadow(aliases={"f_51614_", "val$container"})
    private Container container;
    public CompoundContainer inventorylargechest;

    public ChestBlock2_1Mixin() {
        this.inventorylargechest = (CompoundContainer)this.container;
    }
}

