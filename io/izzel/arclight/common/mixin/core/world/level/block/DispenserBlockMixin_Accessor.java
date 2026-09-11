/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.dispenser.DispenseItemBehavior
 *  net.minecraft.world.item.Item
 *  net.minecraft.world.level.block.DispenserBlock
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import java.util.Map;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.DispenserBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={DispenserBlock.class})
public interface DispenserBlockMixin_Accessor {
    @Accessor(value="DISPENSER_REGISTRY")
    public static Map<Item, DispenseItemBehavior> getDispenseBehaviorRegistry() {
        return null;
    }
}

