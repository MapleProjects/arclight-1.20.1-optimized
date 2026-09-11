/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.SpellcasterIllager
 *  net.minecraft.world.entity.monster.SpellcasterIllager$IllagerSpell
 *  net.minecraft.world.entity.monster.SpellcasterIllager$SpellcasterUseSpellGoal
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.entity.monster;

import net.minecraft.world.entity.monster.SpellcasterIllager;
import org.bukkit.craftbukkit.v1_20_R1.event.CraftEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={SpellcasterIllager.SpellcasterUseSpellGoal.class})
public abstract class SpellcastingIllager_UseSpellGoalMixin {
    @Shadow(aliases={"this$0", "f_33776_"}, remap=false)
    private SpellcasterIllager outerThis;

    @Shadow(aliases={"m_7269_"})
    protected abstract SpellcasterIllager.IllagerSpell m_7269_();

    @Inject(method={"tick"}, cancellable=true, at={@At(value="INVOKE", target="Lnet/minecraft/world/entity/monster/SpellcasterIllager$SpellcasterUseSpellGoal;performSpellCasting()V")})
    private void arclight$castSpell(CallbackInfo ci) {
        if (!CraftEventFactory.handleEntitySpellCastEvent(this.outerThis, this.m_7269_())) {
            ci.cancel();
        }
    }
}

