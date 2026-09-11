/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 *  net.minecraft.world.phys.shapes.IndexMerger
 *  net.minecraft.world.phys.shapes.Shapes
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.At$Shift
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.optimization.general;

import io.izzel.arclight.common.mod.util.optimization.OptimizedIndirectMerger;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.world.phys.shapes.IndexMerger;
import net.minecraft.world.phys.shapes.Shapes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={Shapes.class})
public class VoxelShapesMixin {
    @Inject(method={"createIndexMerger"}, at={@At(value="NEW", target="net/minecraft/world/phys/shapes/IndirectMerger", shift=At.Shift.BEFORE)}, cancellable=true)
    private static void arclight$optimizedMerger(int p_199410_0_, DoubleList list1, DoubleList list2, boolean p_199410_3_, boolean p_199410_4_, CallbackInfoReturnable<IndexMerger> cir) {
        cir.setReturnValue((Object)new OptimizedIndirectMerger(list1, list2, p_199410_3_, p_199410_4_));
    }
}

