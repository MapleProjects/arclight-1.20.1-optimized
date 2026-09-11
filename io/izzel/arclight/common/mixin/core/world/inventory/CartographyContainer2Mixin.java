/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.inventory.CartographyTableMenu
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.inventory.container.PosContainerBridge;
import net.minecraft.world.inventory.CartographyTableMenu;
import org.bukkit.Location;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets={"net/minecraft/world/inventory/CartographyTableMenu$2"})
public abstract class CartographyContainer2Mixin
implements IInventoryBridge {
    @Shadow(aliases={"this$0", "f_39182_"}, remap=false)
    private CartographyTableMenu outerThis;

    @Override
    public Location getLocation() {
        return ((PosContainerBridge)this.outerThis).bridge$getWorldLocation();
    }
}

