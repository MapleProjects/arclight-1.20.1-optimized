/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.storage.loot.predicates;

import io.izzel.arclight.common.mod.ArclightConstants;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={LootItemRandomChanceWithLootingCondition.class})
public class RandomChanceWithLootingMixin {
    @Shadow
    @Final
    private float f_81953_;
    @Shadow
    @Final
    private float f_81954_;

    @Overwrite
    public boolean test(LootContext context) {
        int i = context.getLootingModifier();
        if (context.m_78936_(ArclightConstants.LOOTING_MOD)) {
            i = (Integer)context.m_78953_(ArclightConstants.LOOTING_MOD);
        }
        return context.m_230907_().m_188501_() < this.f_81953_ + (float)i * this.f_81954_;
    }
}

