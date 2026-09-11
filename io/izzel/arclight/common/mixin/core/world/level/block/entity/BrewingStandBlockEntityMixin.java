/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.Containers
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.BrewingStandBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraftforge.common.brewing.BrewingRecipeRegistry
 *  net.minecraftforge.event.ForgeEventFactory
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.tileentity.TileEntityBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.LockableBlockEntityMixin;
import io.izzel.arclight.common.mod.util.ArclightCaptures;
import io.izzel.arclight.mixin.Eject;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Containers;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.ForgeEventFactory;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.block.BrewingStartEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={BrewingStandBlockEntity.class})
public abstract class BrewingStandBlockEntityMixin
extends LockableBlockEntityMixin {
    @Shadow
    private NonNullList<net.minecraft.world.item.ItemStack> f_58975_;
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();
    private int maxStack = 64;

    @Eject(method={"serverTick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/item/ItemStack;shrink(I)V"))
    private static void arclight$brewFuel(net.minecraft.world.item.ItemStack stack, int count, CallbackInfo ci, Level level, BlockPos pos, BlockState state, BrewingStandBlockEntity entity) {
        BrewingStandFuelEvent event = new BrewingStandFuelEvent(CraftBlock.at((LevelAccessor)level, pos), CraftItemStack.asCraftMirror(stack), 20);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            ci.cancel();
        } else {
            entity.f_58979_ = event.getFuelPower();
            if (entity.f_58979_ > 0 && event.isConsuming()) {
                stack.m_41774_(count);
            }
        }
    }

    @Inject(method={"serverTick"}, at={@At(value="FIELD", opcode=181, target="Lnet/minecraft/world/level/block/entity/BrewingStandBlockEntity;ingredient:Lnet/minecraft/world/item/Item;")})
    private static void arclight$brewBegin(Level level, BlockPos pos, BlockState p_155288_, BrewingStandBlockEntity entity, CallbackInfo ci) {
        BrewingStartEvent event = new BrewingStartEvent(CraftBlock.at((LevelAccessor)level, pos), CraftItemStack.asCraftMirror(entity.m_8020_(3)), entity.f_58976_);
        Bukkit.getPluginManager().callEvent(event);
        entity.f_58976_ = event.getTotalBrewTime();
    }

    @Overwrite
    private static void m_155290_(Level level, BlockPos pos, NonNullList<net.minecraft.world.item.ItemStack> stacks) {
        InventoryHolder owner;
        if (ForgeEventFactory.onPotionAttemptBrew(stacks)) {
            return;
        }
        net.minecraft.world.item.ItemStack ing = (net.minecraft.world.item.ItemStack)stacks.get(3);
        ArrayList<ItemStack> brewResults = new ArrayList<ItemStack>(3);
        for (int i = 0; i < 3; ++i) {
            net.minecraft.world.item.ItemStack input = (net.minecraft.world.item.ItemStack)stacks.get(i);
            net.minecraft.world.item.ItemStack output = BrewingRecipeRegistry.getOutput((net.minecraft.world.item.ItemStack)input, (net.minecraft.world.item.ItemStack)ing);
            brewResults.add(i, CraftItemStack.asCraftMirror(output.m_41619_() ? input : output));
        }
        BrewingStandBlockEntity entity = (BrewingStandBlockEntity)ArclightCaptures.getTickingBlockEntity();
        InventoryHolder inventoryHolder = owner = entity == null ? null : ((TileEntityBridge)entity).bridge$getOwner();
        if (owner != null) {
            BrewEvent event = new BrewEvent(CraftBlock.at((LevelAccessor)level, pos), (BrewerInventory)owner.getInventory(), brewResults, entity.f_58979_);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            for (int i = 0; i < 3; ++i) {
                if (i < brewResults.size()) {
                    stacks.set(i, (Object)CraftItemStack.asNMSCopy((ItemStack)brewResults.get(i)));
                    continue;
                }
                stacks.set(i, (Object)net.minecraft.world.item.ItemStack.f_41583_);
            }
        }
        ForgeEventFactory.onPotionBrewed(stacks);
        if (ing.hasCraftingRemainingItem()) {
            net.minecraft.world.item.ItemStack containerItem = ing.getCraftingRemainingItem();
            ing.m_41774_(1);
            if (ing.m_41619_()) {
                ing = containerItem;
            } else {
                Containers.m_18992_((Level)level, (double)pos.m_123341_(), (double)pos.m_123342_(), (double)pos.m_123343_(), (net.minecraft.world.item.ItemStack)containerItem);
            }
        } else {
            ing.m_41774_(1);
        }
        stacks.set(3, (Object)ing);
        level.m_46796_(1035, pos, 0);
    }

    @Override
    public List<net.minecraft.world.item.ItemStack> getContents() {
        return this.f_58975_;
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

    public int m_6893_() {
        if (this.maxStack == 0) {
            this.maxStack = 64;
        }
        return this.maxStack;
    }

    @Override
    public void setMaxStackSize(int size) {
        this.maxStack = size;
    }
}

