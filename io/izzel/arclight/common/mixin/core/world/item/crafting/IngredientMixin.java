/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Ingredient
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.item.crafting;

import io.izzel.arclight.common.bridge.core.item.crafting.IngredientBridge;
import javax.annotation.Nullable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={Ingredient.class})
public abstract class IngredientMixin
implements IngredientBridge {
    public boolean exact;

    @Shadow
    public abstract boolean m_43947_();

    @Shadow
    public abstract ItemStack[] m_43908_();

    @Overwrite
    public boolean test(@Nullable ItemStack itemstack) {
        ItemStack[] items;
        if (itemstack == null) {
            return false;
        }
        if (this.m_43947_()) {
            return itemstack.m_41619_();
        }
        for (ItemStack stack : items = this.m_43908_()) {
            if (!(this.exact ? ItemStack.m_150942_((ItemStack)itemstack, (ItemStack)stack) : stack.m_150930_(itemstack.m_41720_()))) continue;
            return true;
        }
        return false;
    }

    @Override
    public void bridge$setExact(boolean exact) {
        this.exact = exact;
    }

    @Override
    public boolean bridge$isExact() {
        return this.exact;
    }
}

