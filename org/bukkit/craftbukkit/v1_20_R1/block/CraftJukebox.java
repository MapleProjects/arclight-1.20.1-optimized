/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.block.JukeboxBlock
 *  net.minecraft.world.level.block.entity.BlockEntity
 *  net.minecraft.world.level.block.entity.JukeboxBlockEntity
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.Property
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.Container;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Jukebox;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventoryJukebox;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.JukeboxInventory;

public class CraftJukebox
extends CraftBlockEntityState<JukeboxBlockEntity>
implements Jukebox {
    public CraftJukebox(World world, JukeboxBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public JukeboxInventory getSnapshotInventory() {
        return new CraftInventoryJukebox((Container)this.getSnapshot());
    }

    @Override
    public JukeboxInventory getInventory() {
        if (!this.isPlaced()) {
            return this.getSnapshotInventory();
        }
        return new CraftInventoryJukebox((Container)this.getTileEntity());
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public boolean update(boolean force, boolean applyPhysics) {
        boolean result = super.update(force, applyPhysics);
        if (result && this.isPlaced() && this.getType() == Material.JUKEBOX) {
            BlockEntity tileEntity;
            Material record = this.getPlaying();
            this.getWorldHandle().m_7731_(this.getPosition(), this.data, 3);
            BlockEntity blockEntity = tileEntity = this.getTileEntityFromWorld();
            if (blockEntity instanceof JukeboxBlockEntity var6_7) {
                CraftWorld world = (CraftWorld)this.getWorld();
                if (record.isAir()) {
                    void jukebox;
                    jukebox.m_272139_(net.minecraft.world.item.ItemStack.f_41583_);
                    world.playEffect(this.getLocation(), Effect.IRON_DOOR_CLOSE, 0);
                } else {
                    world.playEffect(this.getLocation(), Effect.RECORD_PLAY, record);
                }
            }
        }
        return result;
    }

    @Override
    public Material getPlaying() {
        return this.getRecord().getType();
    }

    @Override
    public void setPlaying(Material record) {
        if (record == null || CraftMagicNumbers.getItem(record) == null) {
            record = Material.AIR;
        }
        this.setRecord(new ItemStack(record));
    }

    @Override
    public boolean hasRecord() {
        return (Boolean)this.getHandle().m_61143_((Property)JukeboxBlock.f_54254_) != false && !this.getPlaying().isAir();
    }

    @Override
    public ItemStack getRecord() {
        net.minecraft.world.item.ItemStack record = ((JukeboxBlockEntity)this.getSnapshot()).m_272036_();
        return CraftItemStack.asBukkitCopy(record);
    }

    @Override
    public void setRecord(ItemStack record) {
        net.minecraft.world.item.ItemStack nms = CraftItemStack.asNMSCopy(record);
        JukeboxBlockEntity snapshot = (JukeboxBlockEntity)this.getSnapshot();
        snapshot.m_272139_(nms);
        snapshot.f_238572_ = snapshot.f_238695_;
        snapshot.f_238637_ = !nms.m_41619_();
        this.data = (BlockState)this.data.m_61124_((Property)JukeboxBlock.f_54254_, (Comparable)Boolean.valueOf(!nms.m_41619_()));
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public boolean isPlaying() {
        void jukebox;
        JukeboxBlockEntity jukeboxBlockEntity;
        BlockEntity tileEntity;
        this.requirePlaced();
        BlockEntity blockEntity = tileEntity = this.getTileEntityFromWorld();
        return blockEntity instanceof JukeboxBlockEntity && (jukeboxBlockEntity = (JukeboxBlockEntity)blockEntity) == (JukeboxBlockEntity)blockEntity && jukebox.m_272025_();
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public boolean startPlaying() {
        void jukebox;
        BlockEntity tileEntity;
        this.requirePlaced();
        BlockEntity blockEntity = tileEntity = this.getTileEntityFromWorld();
        if (!(blockEntity instanceof JukeboxBlockEntity var2_3)) {
            return false;
        }
        net.minecraft.world.item.ItemStack record = jukebox.m_272036_();
        if (record.m_41619_() || this.isPlaying()) {
            return false;
        }
        jukebox.f_238637_ = true;
        jukebox.f_238572_ = jukebox.f_238695_;
        this.getWorld().playEffect(this.getLocation(), Effect.RECORD_PLAY, CraftMagicNumbers.getMaterial(record.m_41720_()));
        return true;
    }

    @Override
    public void stopPlaying() {
        BlockEntity tileEntity;
        this.requirePlaced();
        BlockEntity blockEntity = tileEntity = this.getTileEntityFromWorld();
        if (!(blockEntity instanceof JukeboxBlockEntity var2_3)) {
            return;
        }
        jukebox.f_238637_ = false;
        this.getWorld().playEffect(this.getLocation(), Effect.IRON_DOOR_CLOSE, 0);
    }

    @Override
    public boolean eject() {
        this.ensureNoWorldGeneration();
        BlockEntity tileEntity = this.getTileEntityFromWorld();
        if (!(tileEntity instanceof JukeboxBlockEntity)) {
            return false;
        }
        JukeboxBlockEntity jukebox = (JukeboxBlockEntity)tileEntity;
        boolean result = !jukebox.m_272036_().m_41619_();
        jukebox.m_272252_();
        return result;
    }
}

