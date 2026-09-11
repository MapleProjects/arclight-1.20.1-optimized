/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.ai.goal.Goal
 *  net.minecraft.world.entity.monster.Guardian
 *  net.minecraft.world.entity.monster.Guardian$GuardianAttackGoal
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.ModifyArg
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Guardian;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value={Guardian.class})
public class GuardianMixin {
    public Guardian.GuardianAttackGoal guardianAttackGoal;

    @ModifyArg(method={"registerGoals"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/entity/ai/goal/GoalSelector;addGoal(ILnet/minecraft/world/entity/ai/goal/Goal;)V"))
    private Goal arclight$saveGoal(Goal goal) {
        if (goal instanceof Guardian.GuardianAttackGoal) {
            Guardian.GuardianAttackGoal guardianGoal;
            this.guardianAttackGoal = guardianGoal = (Guardian.GuardianAttackGoal)goal;
        }
        return goal;
    }
}

