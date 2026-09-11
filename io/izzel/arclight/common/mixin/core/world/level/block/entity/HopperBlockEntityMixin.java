/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.mixin.Eject
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.Direction
 *  net.minecraft.core.NonNullList
 *  net.minecraft.world.CompoundContainer
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.item.ItemEntity
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.LevelAccessor
 *  net.minecraft.world.level.block.entity.Hopper
 *  net.minecraft.world.level.block.entity.HopperBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.Redirect
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 *  org.spongepowered.asm.mixin.injection.callback.LocalCapture
 */
package io.izzel.arclight.common.mixin.core.world.level.block.entity;

import io.izzel.arclight.common.bridge.core.entity.EntityBridge;
import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import io.izzel.arclight.common.bridge.core.tileentity.TileEntityBridge;
import io.izzel.arclight.common.bridge.core.world.WorldBridge;
import io.izzel.arclight.common.mixin.core.world.level.block.entity.LockableBlockEntityMixin;
import io.izzel.arclight.common.mod.util.DistValidate;
import io.izzel.arclight.mixin.Eject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.Hopper;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryDoubleChest;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.event.inventory.HopperInventorySearchEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value={HopperBlockEntity.class})
public abstract class HopperBlockEntityMixin
extends LockableBlockEntityMixin {
    @Shadow
    private NonNullList<ItemStack> f_59301_;
    public List<HumanEntity> transaction = new ArrayList<HumanEntity>();
    private int maxStack = 64;

    @Shadow
    public abstract void m_6836_(int var1, ItemStack var2);

    @Shadow
    private static boolean m_155578_(Level p_155579_, BlockPos p_155580_, BlockState p_155581_, HopperBlockEntity p_155582_, BooleanSupplier p_155583_) {
        return false;
    }

    @Redirect(method={"pushItemsTick"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/entity/HopperBlockEntity;tryMoveItems(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/HopperBlockEntity;Ljava/util/function/BooleanSupplier;)Z"))
    private static boolean arclight$hopperCheck(Level level, BlockPos pos, BlockState state, HopperBlockEntity hopper, BooleanSupplier flag) {
        boolean result = HopperBlockEntityMixin.m_155578_(level, pos, state, hopper, flag);
        if (!result && DistValidate.isValid((LevelAccessor)level) && ((WorldBridge)level).bridge$spigotConfig().hopperCheck > 1) {
            hopper.m_59395_(((WorldBridge)level).bridge$spigotConfig().hopperCheck);
        }
        return result;
    }

    @Eject(method={"ejectItems"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/entity/HopperBlockEntity;addItem(Lnet/minecraft/world/Container;Lnet/minecraft/world/Container;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/Direction;)Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack arclight$moveItem(Container source, Container destination, ItemStack stack, Direction direction, CallbackInfoReturnable<Boolean> cir, Level level, BlockPos p_155564_, BlockState p_155565_, HopperBlockEntity entity) {
        CraftItemStack original = CraftItemStack.asCraftMirror(stack);
        Inventory destinationInventory = destination instanceof CompoundContainer ? new CraftInventoryDoubleChest((CompoundContainer)destination) : ((IInventoryBridge)destination).getOwnerInventory();
        InventoryMoveItemEvent event = new InventoryMoveItemEvent(((TileEntityBridge)entity).bridge$getOwner().getInventory(), original.clone(), destinationInventory, true);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            entity.m_59395_(((WorldBridge)level).bridge$spigotConfig().hopperTransfer);
            cir.setReturnValue((Object)false);
            return null;
        }
        return HopperBlockEntity.m_59326_((Container)source, (Container)destination, (ItemStack)CraftItemStack.asNMSCopy(event.getItem()), (Direction)direction);
    }

    @Eject(method={"tryTakeInItemFromSlot"}, at=@At(value="INVOKE", target="Lnet/minecraft/world/level/block/entity/HopperBlockEntity;addItem(Lnet/minecraft/world/Container;Lnet/minecraft/world/Container;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/Direction;)Lnet/minecraft/world/item/ItemStack;"))
    private static ItemStack arclight$pullItem(Container source, Container destination, ItemStack stack, Direction direction, CallbackInfoReturnable<Boolean> cir, Hopper hopper, Container inv, int index) {
        ItemStack origin = inv.m_8020_(index).m_41777_();
        CraftItemStack original = CraftItemStack.asCraftMirror(stack);
        Inventory sourceInventory = source instanceof CompoundContainer ? new CraftInventoryDoubleChest((CompoundContainer)source) : ((IInventoryBridge)source).getOwnerInventory();
        InventoryMoveItemEvent event = new InventoryMoveItemEvent(sourceInventory, original.clone(), ((IInventoryBridge)destination).getOwnerInventory(), false);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            inv.m_6836_(index, origin);
            if (destination instanceof HopperBlockEntity) {
                ((HopperBlockEntity)destination).m_59395_(8);
            }
            cir.setReturnValue((Object)false);
            return null;
        }
        return HopperBlockEntity.m_59326_((Container)source, (Container)destination, (ItemStack)CraftItemStack.asNMSCopy(event.getItem()), (Direction)direction);
    }

    @Inject(method={"addItem(Lnet/minecraft/world/Container;Lnet/minecraft/world/entity/item/ItemEntity;)Z"}, cancellable=true, at={@At(value="HEAD")})
    private static void arclight$pickupItem(Container inventory, ItemEntity itemEntity, CallbackInfoReturnable<Boolean> cir) {
        InventoryPickupItemEvent event = new InventoryPickupItemEvent(((IInventoryBridge)inventory).getOwnerInventory(), (Item)((Object)((EntityBridge)itemEntity).bridge$getBukkitEntity()));
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)false);
        }
    }

    private static Container runHopperInventorySearchEvent(Container inventory, CraftBlock hopper, CraftBlock searchLocation, HopperInventorySearchEvent.ContainerType containerType) {
        HopperInventorySearchEvent event = new HopperInventorySearchEvent(inventory != null ? new CraftInventory(inventory) : null, containerType, hopper, searchLocation);
        Bukkit.getServer().getPluginManager().callEvent(event);
        CraftInventory craftInventory = (CraftInventory)event.getInventory();
        return craftInventory != null ? craftInventory.getInventory() : null;
    }

    @Inject(method={"getAttachedContainer"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="RETURN")})
    private static void arclight$searchTo(Level level, BlockPos pos, BlockState p_155595_, CallbackInfoReturnable<Container> cir, Direction direction) {
        Container container = (Container)cir.getReturnValue();
        CraftBlock hopper = CraftBlock.at((LevelAccessor)level, pos);
        CraftBlock searchBlock = CraftBlock.at((LevelAccessor)level, pos.m_121945_(direction));
        cir.setReturnValue((Object)HopperBlockEntityMixin.runHopperInventorySearchEvent(container, hopper, searchBlock, HopperInventorySearchEvent.ContainerType.DESTINATION));
    }

    @Inject(method={"getSourceContainer"}, cancellable=true, locals=LocalCapture.CAPTURE_FAILHARD, at={@At(value="RETURN")})
    private static void arclight$searchFrom(Level level, Hopper hopper, CallbackInfoReturnable<Container> cir) {
        Container container = (Container)cir.getReturnValue();
        BlockPos blockPos = BlockPos.m_274561_((double)hopper.m_6343_(), (double)hopper.m_6358_(), (double)hopper.m_6446_());
        CraftBlock hopperBlock = CraftBlock.at((LevelAccessor)level, blockPos);
        CraftBlock containerBlock = CraftBlock.at((LevelAccessor)level, blockPos.m_7494_());
        cir.setReturnValue((Object)HopperBlockEntityMixin.runHopperInventorySearchEvent(container, hopperBlock, containerBlock, HopperInventorySearchEvent.ContainerType.SOURCE));
    }

    @Override
    public List<ItemStack> getContents() {
        return this.f_59301_;
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

