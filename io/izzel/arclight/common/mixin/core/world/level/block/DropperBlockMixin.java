/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.core.BlockSource
 *  net.minecraft.core.BlockSourceImpl
 *  net.minecraft.core.Direction
 *  net.minecraft.core.dispenser.DispenseItemBehavior
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.CompoundContainer
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.Level
 *  net.minecraft.world.level.block.DispenserBlock
 *  net.minecraft.world.level.block.DropperBlock
 *  net.minecraft.world.level.block.entity.DispenserBlockEntity
 *  net.minecraft.world.level.block.entity.HopperBlockEntity
 *  net.minecraft.world.level.block.state.properties.Property
 *  net.minecraftforge.items.VanillaInventoryCodeHooks
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Overwrite
 *  org.spongepowered.asm.mixin.Shadow
 */
package io.izzel.arclight.common.mixin.core.world.level.block;

import io.izzel.arclight.common.bridge.core.inventory.IInventoryBridge;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.BlockSourceImpl;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.CompoundContainer;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.DropperBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.items.VanillaInventoryCodeHooks;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryDoubleChest;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={DropperBlock.class})
public class DropperBlockMixin {
    @Shadow
    @Final
    private static DispenseItemBehavior f_52939_;

    @Overwrite
    public void m_5824_(ServerLevel worldIn, BlockPos pos) {
        BlockSourceImpl proxyblocksource = new BlockSourceImpl(worldIn, pos);
        DispenserBlockEntity dispensertileentity = (DispenserBlockEntity)proxyblocksource.m_8118_();
        int i = dispensertileentity.m_222761_(worldIn.m_213780_());
        if (i < 0) {
            worldIn.m_46796_(1001, pos, 0);
        } else {
            ItemStack itemstack = dispensertileentity.m_8020_(i);
            if (!itemstack.m_41619_() && VanillaInventoryCodeHooks.dropperInsertHook((Level)worldIn, (BlockPos)pos, (DispenserBlockEntity)dispensertileentity, (int)i, (ItemStack)itemstack)) {
                ItemStack itemstack1;
                Direction direction = (Direction)worldIn.m_8055_(pos).m_61143_((Property)DispenserBlock.f_52659_);
                Container iinventory = HopperBlockEntity.m_59390_((Level)worldIn, (BlockPos)pos.m_121945_(direction));
                if (iinventory == null) {
                    itemstack1 = f_52939_.m_6115_((BlockSource)proxyblocksource, itemstack);
                } else {
                    ItemStack split = itemstack.m_41777_().m_41620_(1);
                    CraftItemStack craftItemStack = CraftItemStack.asCraftMirror(split);
                    Inventory destinationInventory = iinventory instanceof CompoundContainer ? new CraftInventoryDoubleChest((CompoundContainer)iinventory) : ((IInventoryBridge)iinventory).getOwnerInventory();
                    InventoryMoveItemEvent event = new InventoryMoveItemEvent(((IInventoryBridge)dispensertileentity).getOwner().getInventory(), craftItemStack, destinationInventory, true);
                    Bukkit.getPluginManager().callEvent(event);
                    if (event.isCancelled()) {
                        return;
                    }
                    itemstack1 = HopperBlockEntity.m_59326_((Container)dispensertileentity, (Container)iinventory, (ItemStack)CraftItemStack.asNMSCopy(event.getItem()), (Direction)direction.m_122424_());
                    if (event.getItem().equals(craftItemStack) && itemstack1.m_41619_()) {
                        itemstack1 = itemstack.m_41777_();
                        itemstack1.m_41774_(1);
                    } else {
                        itemstack1 = itemstack.m_41777_();
                    }
                }
                dispensertileentity.m_6836_(i, itemstack1);
            }
        }
    }
}

