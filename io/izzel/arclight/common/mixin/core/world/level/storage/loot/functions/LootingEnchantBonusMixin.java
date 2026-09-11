/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.level.storage.loot.providers.number.NumberProvider
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.storage.loot.functions;

import io.izzel.arclight.common.mod.ArclightConstants;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={LootingEnchantFunction.class})
public abstract class LootingEnchantBonusMixin {
    @Shadow
    @Final
    NumberProvider f_80776_;
    @Shadow
    @Final
    int f_80777_;

    @Shadow
    abstract boolean m_80798_();

    @Overwrite
    public ItemStack m_7372_(ItemStack stack, LootContext context) {
        Entity entity = (Entity)context.m_78953_(LootContextParams.f_81458_);
        if (entity instanceof LivingEntity) {
            int i = context.getLootingModifier();
            if (context.m_78936_(ArclightConstants.LOOTING_MOD)) {
                i = (Integer)context.m_78953_(ArclightConstants.LOOTING_MOD);
            }
            if (i <= 0) {
                return stack;
            }
            float f = (float)i * this.f_80776_.m_142688_(context);
            stack.m_41769_(Math.round(f));
            if (this.m_80798_() && stack.m_41613_() > this.f_80777_) {
                stack.m_41764_(this.f_80777_);
            }
        }
        return stack;
    }
}

