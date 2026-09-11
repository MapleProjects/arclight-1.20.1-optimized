/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.item.crafting.Recipe
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.LecternBlock
 *  net.minecraft.world.level.block.entity.LecternBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.tileentity.TileEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LecternBlock;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(targets={"net/minecraft/world/level/block/entity/LecternBlockEntity$1"})
public abstract class LecternTileEntity1Mixin
implements IInventoryBridge,
Container {
    @Shadow(aliases={"this$0", "f_59572_"}, remap=false)
    private LecternBlockEntity outerThis;
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();
    private int maxStack = 1;

    public void m_6836_(int index, ItemStack stack) {
        if (index == 0) {
            this.outerThis.m_59536_(stack);
            if (this.outerThis.m_58904_() != null) {
                LecternBlock.m_269306_(null, (Level)this.outerThis.m_58904_(), (BlockPos)this.outerThis.m_58899_(), (BlockState)this.outerThis.m_58900_(), (boolean)this.outerThis.m_59567_());
            }
        }
    }

    @Override
    public List<ItemStack> getContents() {
        return Collections.singletonList(this.outerThis.m_59566_());
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
    public InventoryHolder getOwner() {
        return ((TileEntityBridge)this.outerThis).bridge$getOwner();
    }

    @Override
    public void setOwner(InventoryHolder owner) {
    }

    public int m_6893_() {
        if (this.maxStack == 0) {
            this.maxStack = 1;
        }
        return this.maxStack;
    }

    @Override
    public void setMaxStackSize(int size) {
        this.maxStack = size;
    }

    @Override
    public Location getLocation() {
        if (this.outerThis.m_58904_() == null) {
            return null;
        }
        return new Location(((WorldBridge)this.outerThis.m_58904_()).bridge$getWorld(), this.outerThis.m_58899_().m_123341_(), this.outerThis.m_58899_().m_123342_(), this.outerThis.m_58899_().m_123343_());
    }

    @Override
    public Recipe<?> getCurrentRecipe() {
        return null;
    }

    @Override
    public void setCurrentRecipe(Recipe<?> recipe) {
    }

    public LecternBlockEntity getLectern() {
        return this.outerThis;
    }
}

