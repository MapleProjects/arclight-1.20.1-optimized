/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.phys.AABB
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.bridge.optimization.EntityBridge_ActivationRange;
import io.izzel.arclight.common.mod.ArclightConstants;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import org.spigotmc.ActivationRange;
import org.spigotmc.SpigotWorldConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Entity.class})
public abstract class EntityMixin_ActivationRange
implements EntityBridge_ActivationRange {
    @Shadow
    public int f_19797_;
    public ActivationRange.ActivationType activationType;
    public boolean defaultActivationState;
    public long activatedTick = Integer.MIN_VALUE;

    @Shadow
    public abstract void m_6210_();

    @Shadow
    public abstract Level m_9236_();

    @Shadow
    public abstract AABB m_20191_();

    @Shadow
    public abstract void m_146870_();

    @Inject(method={"<init>"}, at={@At(value="RETURN")})
    private void arclight$init(EntityType<?> entityTypeIn, Level worldIn, CallbackInfo ci) {
        SpigotWorldConfig config;
        this.activationType = ActivationRange.initializeEntityActivationType((Entity)this);
        this.defaultActivationState = DistValidate.isValid((LevelAccessor)worldIn) ? ((config = ((WorldBridge)worldIn).bridge$spigotConfig()) != null ? ActivationRange.initializeEntityActivationState((Entity)this, config) : false) : false;
    }

    public void inactiveTick() {
    }

    @Override
    public void bridge$inactiveTick() {
        this.inactiveTick();
    }

    @Override
    public void bridge$updateActivation() {
        if ((long)ArclightConstants.currentTick > this.activatedTick) {
            if (this.defaultActivationState) {
                this.activatedTick = ArclightConstants.currentTick;
            } else if (this.activationType.boundingBox.m_82381_(this.m_20191_())) {
                this.activatedTick = ArclightConstants.currentTick;
            }
        }
    }
}

