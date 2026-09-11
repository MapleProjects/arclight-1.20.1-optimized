/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.Direction
 *  net.minecraft.world.entity.decoration.HangingEntity
 *  net.minecraft.world.entity.decoration.ItemFrame
 *  net.minecraft.world.level.block.Blocks
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.Rotation;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHanging;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class CraftItemFrame
extends CraftHanging
implements org.bukkit.entity.ItemFrame {
    public CraftItemFrame(CraftServer server, ItemFrame entity) {
        super(server, (HangingEntity)entity);
    }

    @Override
    public boolean setFacingDirection(BlockFace face, boolean force) {
        ItemFrame hanging = this.getHandle();
        Direction oldDir = hanging.m_6350_();
        Direction newDir = CraftBlock.blockFaceToNotch(face);
        Preconditions.checkArgument((newDir != null ? 1 : 0) != 0, (String)"%s is not a valid facing direction", (Object)((Object)face));
        this.getHandle().m_6022_(newDir);
        if (!(force || this.getHandle().generation || hanging.m_7088_())) {
            hanging.m_6022_(oldDir);
            return false;
        }
        this.update();
        return true;
    }

    @Override
    protected void update() {
        super.update();
        this.getHandle().m_20088_().markDirty(ItemFrame.f_31757_);
        this.getHandle().m_20088_().markDirty(ItemFrame.f_31758_);
        if (!this.getHandle().generation) {
            this.getHandle().m_9236_().m_46717_(this.getHandle().f_31698_, Blocks.f_50016_);
        }
    }

    @Override
    public void setItem(ItemStack item) {
        this.setItem(item, true);
    }

    @Override
    public void setItem(ItemStack item, boolean playSound) {
        this.getHandle().setItem(CraftItemStack.asNMSCopy(item), !this.getHandle().generation, !this.getHandle().generation && playSound);
    }

    @Override
    public ItemStack getItem() {
        return CraftItemStack.asBukkitCopy(this.getHandle().m_31822_());
    }

    @Override
    public float getItemDropChance() {
        return this.getHandle().f_31754_;
    }

    @Override
    public void setItemDropChance(float chance) {
        Preconditions.checkArgument((0.0 <= (double)chance && (double)chance <= 1.0 ? 1 : 0) != 0, (String)"Chance (%s) outside range [0, 1]", (Object)Float.valueOf(chance));
        this.getHandle().f_31754_ = chance;
    }

    @Override
    public Rotation getRotation() {
        return this.toBukkitRotation(this.getHandle().m_31823_());
    }

    Rotation toBukkitRotation(int value) {
        switch (value) {
            case 0: {
                return Rotation.NONE;
            }
            case 1: {
                return Rotation.CLOCKWISE_45;
            }
            case 2: {
                return Rotation.CLOCKWISE;
            }
            case 3: {
                return Rotation.CLOCKWISE_135;
            }
            case 4: {
                return Rotation.FLIPPED;
            }
            case 5: {
                return Rotation.FLIPPED_45;
            }
            case 6: {
                return Rotation.COUNTER_CLOCKWISE;
            }
            case 7: {
                return Rotation.COUNTER_CLOCKWISE_45;
            }
        }
        throw new AssertionError((Object)("Unknown rotation " + value + " for " + this.getHandle()));
    }

    @Override
    public void setRotation(Rotation rotation) {
        Preconditions.checkArgument((rotation != null ? 1 : 0) != 0, (Object)"Rotation cannot be null");
        this.getHandle().m_31770_(CraftItemFrame.toInteger(rotation));
    }

    static int toInteger(Rotation rotation) {
        switch (rotation) {
            case NONE: {
                return 0;
            }
            case CLOCKWISE_45: {
                return 1;
            }
            case CLOCKWISE: {
                return 2;
            }
            case CLOCKWISE_135: {
                return 3;
            }
            case FLIPPED: {
                return 4;
            }
            case FLIPPED_45: {
                return 5;
            }
            case COUNTER_CLOCKWISE: {
                return 6;
            }
            case COUNTER_CLOCKWISE_45: {
                return 7;
            }
        }
        throw new IllegalArgumentException((Object)((Object)rotation) + " is not applicable to an ItemFrame");
    }

    @Override
    public boolean isVisible() {
        return !this.getHandle().m_20145_();
    }

    @Override
    public void setVisible(boolean visible) {
        this.getHandle().m_6842_(!visible);
    }

    @Override
    public boolean isFixed() {
        return this.getHandle().f_31755_;
    }

    @Override
    public void setFixed(boolean fixed) {
        this.getHandle().f_31755_ = fixed;
    }

    public ItemFrame getHandle() {
        return (ItemFrame)this.entity;
    }

    @Override
    public String toString() {
        return "CraftItemFrame{item=" + this.getItem() + ", rotation=" + (Object)((Object)this.getRotation()) + "}";
    }
}

