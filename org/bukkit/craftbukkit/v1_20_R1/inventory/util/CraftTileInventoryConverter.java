/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.BlockPos
 *  net.minecraft.world.Container
 *  net.minecraft.world.level.block.Blocks
 *  net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
 *  net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity
 *  net.minecraft.world.level.block.entity.BrewingStandBlockEntity
 *  net.minecraft.world.level.block.entity.DispenserBlockEntity
 *  net.minecraft.world.level.block.entity.DropperBlockEntity
 *  net.minecraft.world.level.block.entity.FurnaceBlockEntity
 *  net.minecraft.world.level.block.entity.HopperBlockEntity
 *  net.minecraft.world.level.block.entity.LecternBlockEntity
 *  net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity
 *  net.minecraft.world.level.block.entity.SmokerBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlastFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.entity.DropperBlockEntity;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.entity.LecternBlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.SmokerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryBrewer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryFurnace;
import org.bukkit.craftbukkit.v1_20_R1.inventory.util.CraftInventoryCreator;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class CraftTileInventoryConverter
implements CraftInventoryCreator.InventoryConverter {
    public abstract Container getTileEntity();

    @Override
    public Inventory createInventory(InventoryHolder holder, InventoryType type) {
        return this.getInventory(this.getTileEntity());
    }

    @Override
    public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
        Container te = this.getTileEntity();
        if (te instanceof RandomizableContainerBlockEntity) {
            ((RandomizableContainerBlockEntity)te).m_58638_(CraftChatMessage.fromStringOrNull(title));
        }
        return this.getInventory(te);
    }

    public Inventory getInventory(Container tileEntity) {
        return new CraftInventory(tileEntity);
    }

    public static class BlastFurnace
    extends CraftTileInventoryConverter {
        @Override
        public Container getTileEntity() {
            return new BlastFurnaceBlockEntity(BlockPos.f_121853_, Blocks.f_50620_.m_49966_());
        }
    }

    public static class BrewingStand
    extends CraftTileInventoryConverter {
        @Override
        public Container getTileEntity() {
            return new BrewingStandBlockEntity(BlockPos.f_121853_, Blocks.f_50255_.m_49966_());
        }

        @Override
        public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
            Container tileEntity = this.getTileEntity();
            if (tileEntity instanceof BrewingStandBlockEntity) {
                ((BrewingStandBlockEntity)tileEntity).m_58638_(CraftChatMessage.fromStringOrNull(title));
            }
            return this.getInventory(tileEntity);
        }

        @Override
        public Inventory getInventory(Container tileEntity) {
            return new CraftInventoryBrewer(tileEntity);
        }
    }

    public static class Dispenser
    extends CraftTileInventoryConverter {
        @Override
        public Container getTileEntity() {
            return new DispenserBlockEntity(BlockPos.f_121853_, Blocks.f_50061_.m_49966_());
        }
    }

    public static class Dropper
    extends CraftTileInventoryConverter {
        @Override
        public Container getTileEntity() {
            return new DropperBlockEntity(BlockPos.f_121853_, Blocks.f_50286_.m_49966_());
        }
    }

    public static class Furnace
    extends CraftTileInventoryConverter {
        @Override
        public Container getTileEntity() {
            FurnaceBlockEntity furnace = new FurnaceBlockEntity(BlockPos.f_121853_, Blocks.f_50094_.m_49966_());
            return furnace;
        }

        @Override
        public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
            Container tileEntity = this.getTileEntity();
            ((AbstractFurnaceBlockEntity)tileEntity).m_58638_(CraftChatMessage.fromStringOrNull(title));
            return this.getInventory(tileEntity);
        }

        @Override
        public Inventory getInventory(Container tileEntity) {
            return new CraftInventoryFurnace((AbstractFurnaceBlockEntity)tileEntity);
        }
    }

    public static class Hopper
    extends CraftTileInventoryConverter {
        @Override
        public Container getTileEntity() {
            return new HopperBlockEntity(BlockPos.f_121853_, Blocks.f_50332_.m_49966_());
        }
    }

    public static class Lectern
    extends CraftTileInventoryConverter {
        @Override
        public Container getTileEntity() {
            return new LecternBlockEntity((BlockPos)BlockPos.f_121853_, (BlockState)Blocks.f_50624_.m_49966_()).f_59525_;
        }
    }

    public static class Smoker
    extends CraftTileInventoryConverter {
        @Override
        public Container getTileEntity() {
            return new SmokerBlockEntity(BlockPos.f_121853_, Blocks.f_50619_.m_49966_());
        }
    }
}

