/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.inventory.Slot
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.inventory;

import io.izzel.arclight.common.bridge.core.inventory.container.SlotBridge;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Slot.class})
public abstract class SlotMixin
implements SlotBridge {
    @Shadow
    protected abstract void m_6405_(int var1);

    @Override
    public void bridge$onSwapCraft(int numItemsCrafted) {
        this.m_6405_(numItemsCrafted);
    }
}

