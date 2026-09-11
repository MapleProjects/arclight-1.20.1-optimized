/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.NonNullList
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.BlockEntityMixin;
import io.izzel.arclight.common.mod.util.DistValidate;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ChiseledBookShelfBlockEntity.class})
public abstract class ChiseledBookShelfBlockEntityMixin
extends BlockEntityMixin
implements IInventoryBridge,
Container {
    @Shadow
    @Final
    private NonNullList<ItemStack> f_260576_;
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();
    private int maxStack = 1;

    @Override
    public List<ItemStack> getContents() {
        return this.f_260576_;
    }

    @Override
    public void onOpen(CraftHumanEntity who) {
        this.transaction.add(who);
    }

    @Override
    public void onClose(CraftHumanEntity who) {
        this.transaction.remove(who);
    }

    @Override
    public List<HumanEntity> getViewers() {
        return this.transaction;
    }

    @Override
    public void setOwner(InventoryHolder owner) {
    }

    @Override
    public void setMaxStackSize(int size) {
        this.maxStack = size;
    }

    public int m_6893_() {
        return this.maxStack;
    }

    @Override
    public Location getLocation() {
        if (!DistValidate.isValid((LevelAccessor)this.f_58857_)) {
            return null;
        }
        return new Location(((WorldBridge)this.f_58857_).bridge$getWorld(), this.f_58858_.m_123341_(), this.f_58858_.m_123342_(), this.f_58858_.m_123343_());
    }

    @Inject(method={"updateState"}, cancellable=true, at={@At(value="HEAD")})
    private void arclight$skipIfNull(int p_261806_, CallbackInfo ci) {
        if (this.f_58857_ == null) {
            ci.cancel();
        }
    }

    @Inject(method={"load"}, at={@At(value="HEAD")})
    private void arclight$load(CompoundTag p_277597_, CallbackInfo ci) {
        super.m_142466_(p_277597_);
    }
}

