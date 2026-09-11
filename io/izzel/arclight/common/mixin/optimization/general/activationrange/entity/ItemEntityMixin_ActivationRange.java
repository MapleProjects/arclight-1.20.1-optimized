/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.EntityType
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.optimization.general.activationrange.entity;

import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.optimization.general.activationrange.EntityMixin_ActivationRange;
import io.izzel.arclight.common.mod.ArclightConstants;
import io.izzel.arclight.common.mod.util.DistValidate;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.ForgeEventFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ItemEntity.class})
public abstract class ItemEntityMixin_ActivationRange
extends EntityMixin_ActivationRange {
    @Shadow
    public int f_31986_;
    @Shadow
    public int f_31985_;
    @Shadow(remap=false)
    public int lifespan;
    private int lastTick = ArclightConstants.currentTick - 1;

    @Shadow
    public abstract ItemStack m_32055_();

    @Inject(method={"<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V"}, at={@At(value="RETURN")})
    private void activationRange$init(EntityType<? extends ItemEntity> entityType, Level world, CallbackInfo ci) {
        if (DistValidate.isValid((LevelAccessor)this.m_9236_())) {
            this.lifespan = ((WorldBridge)this.m_9236_()).bridge$spigotConfig().itemDespawnRate;
        }
    }

    @Inject(method={"<init>(Lnet/minecraft/world/level/Level;DDDLnet/minecraft/world/item/ItemStack;)V"}, at={@At(value="RETURN")})
    private void activationRange$init(Level worldIn, double x, double y, double z, ItemStack stack, CallbackInfo ci) {
        if (DistValidate.isValid((LevelAccessor)this.m_9236_()) && this.lifespan == 6000) {
            this.lifespan = ((WorldBridge)this.m_9236_()).bridge$spigotConfig().itemDespawnRate;
        }
    }

    @Override
    public void inactiveTick() {
        super.inactiveTick();
        int elapsedTicks = ArclightConstants.currentTick - this.lastTick;
        if (this.f_31986_ > 0 && this.f_31986_ != Short.MAX_VALUE && elapsedTicks > 0) {
            this.f_31986_ -= elapsedTicks;
        }
        if (this.f_31985_ != Short.MIN_VALUE) {
            this.f_31985_ += elapsedTicks;
        }
        this.lastTick = ArclightConstants.currentTick;
        if (!this.m_9236_().f_46443_ && this.f_31985_ >= this.lifespan) {
            int hook = ForgeEventFactory.onItemExpire((ItemEntity)((ItemEntity)this), (ItemStack)this.m_32055_());
            if (hook < 0) {
                this.m_146870_();
            } else {
                this.lifespan += hook;
            }
        }
    }
}

