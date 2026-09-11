/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.core.BlockPos
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.BlockEntityType
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.tileentity.TileEntityBridge;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataContainer;
import org.bukkit.craftbukkit.v1_20_R1.persistence.CraftPersistentDataTypeRegistry;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BlockEntity.class})
public abstract class BlockEntityMixin
implements TileEntityBridge {
    private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
    public CraftPersistentDataContainer persistentDataContainer;
    @Shadow
    @Nullable
    public Level f_58857_;
    @Shadow
    @Final
    protected BlockPos f_58858_;

    @Shadow
    public abstract net.minecraft.world.level.block.state.BlockState m_58900_();

    @Shadow
    public abstract void m_6596_();

    @Shadow
    public BlockPos m_58899_() {
        return null;
    }

    @Shadow
    public abstract boolean m_6326_();

    @Shadow
    protected static void m_155232_(Level p_155233_, BlockPos p_155234_, net.minecraft.world.level.block.state.BlockState p_155235_) {
    }

    @Shadow
    public abstract BlockEntityType<?> m_58903_();

    @Shadow
    public void m_142466_(CompoundTag p_155245_) {
    }

    @Shadow
    public void m_142339_(Level p_155231_) {
    }

    @Inject(method={"load"}, at={@At(value="RETURN")})
    public void arclight$loadPersistent(CompoundTag compound, CallbackInfo ci) {
        this.persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);
        CompoundTag persistentDataTag = compound.m_128469_("PublicBukkitValues");
        if (persistentDataTag != null) {
            this.persistentDataContainer.putAll(persistentDataTag);
        }
    }

    @Inject(method={"saveWithoutMetadata"}, at={@At(value="RETURN")})
    private void arclight$savePersistent(CallbackInfoReturnable<CompoundTag> cir) {
        if (this.persistentDataContainer != null && !this.persistentDataContainer.isEmpty()) {
            ((CompoundTag)cir.getReturnValue()).m_128365_("PublicBukkitValues", (Tag)this.persistentDataContainer.toTagCompound());
        }
    }

    public InventoryHolder getOwner() {
        if (this.f_58857_ == null) {
            return null;
        }
        CraftBlock block = CraftBlock.at((LevelAccessor)this.f_58857_, this.f_58858_);
        BlockState state = block.getState();
        if (state instanceof InventoryHolder) {
            return (InventoryHolder)((Object)state);
        }
        return null;
    }

    @Override
    public InventoryHolder bridge$getOwner() {
        return this.getOwner();
    }
}

