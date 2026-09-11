/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.RegistryAccess
 *  net.minecraft.resources.ResourceKey
 *  net.minecraft.world.damagesource.DamageSource
 *  net.minecraft.world.damagesource.DamageSources
 *  net.minecraft.world.damagesource.DamageType
 *  net.minecraft.world.damagesource.DamageTypes
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.damagesource;

import io.izzel.arclight.common.bridge.core.util.DamageSourceBridge;
import io.izzel.arclight.common.bridge.core.util.DamageSourcesBridge;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={DamageSources.class})
public abstract class DamageSourcesMixin
implements DamageSourcesBridge {
    public DamageSource melting;
    public DamageSource poison;

    @Shadow
    protected abstract DamageSource m_269079_(ResourceKey<DamageType> var1);

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(RegistryAccess p_270740_, CallbackInfo ci) {
        this.melting = ((DamageSourceBridge)this.m_269079_((ResourceKey<DamageType>)DamageTypes.f_268468_)).bridge$melting();
        this.poison = ((DamageSourceBridge)this.m_269079_((ResourceKey<DamageType>)DamageTypes.f_268515_)).bridge$poison();
    }

    @Override
    public DamageSource bridge$poison() {
        return this.poison;
    }

    @Override
    public DamageSource bridge$melting() {
        return this.melting;
    }
}

