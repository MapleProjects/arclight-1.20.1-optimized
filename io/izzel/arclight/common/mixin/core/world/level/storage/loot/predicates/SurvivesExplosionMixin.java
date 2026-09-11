/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.level.storage.loot.predicates.ExplosionCondition
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 */
package io.izzel.arclight.common.mixin.core.world.level.storage.loot.predicates;

import net.minecraft.util.RandomSource;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.ExplosionCondition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(value={ExplosionCondition.class})
public class SurvivesExplosionMixin {
    @Overwrite
    public boolean test(LootContext context) {
        Float f = (Float)context.m_78953_(LootContextParams.f_81464_);
        if (f != null) {
            RandomSource random = context.m_230907_();
            float f1 = 1.0f / f.floatValue();
            return random.m_188501_() < f1;
        }
        return true;
    }
}

