/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.network.protocol.Packet
 *  net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket
 *  net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket
 *  net.minecraft.server.level.ServerPlayer
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.player.Inventory
 *  net.minecraft.world.item.ItemStack
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.network.protocol.game.ClientboundSetCarriedItemPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CraftInventoryPlayer
extends CraftInventory
implements PlayerInventory,
EntityEquipment {
    public CraftInventoryPlayer(Inventory inventory) {
        super((Container)inventory);
    }

    public Inventory getInventory() {
        return (Inventory)this.inventory;
    }

    @Override
    public ItemStack[] getStorageContents() {
        return this.asCraftMirror((List<net.minecraft.world.item.ItemStack>)this.getInventory().f_35974_);
    }

    @Override
    public ItemStack getItemInMainHand() {
        return CraftItemStack.asCraftMirror(this.getInventory().m_36056_());
    }

    @Override
    public void setItemInMainHand(ItemStack item) {
        this.setItem(this.getHeldItemSlot(), item);
    }

    @Override
    public void setItemInMainHand(ItemStack item, boolean silent) {
        this.setItemInMainHand(item);
    }

    @Override
    public ItemStack getItemInOffHand() {
        return CraftItemStack.asCraftMirror((net.minecraft.world.item.ItemStack)this.getInventory().f_35976_.get(0));
    }

    @Override
    public void setItemInOffHand(ItemStack item) {
        ItemStack[] extra = this.getExtraContents();
        extra[0] = item;
        this.setExtraContents(extra);
    }

    @Override
    public void setItemInOffHand(ItemStack item, boolean silent) {
        this.setItemInOffHand(item);
    }

    @Override
    public ItemStack getItemInHand() {
        return this.getItemInMainHand();
    }

    @Override
    public void setItemInHand(ItemStack stack) {
        this.setItemInMainHand(stack);
    }

    @Override
    public void setItem(int index, ItemStack item) {
        super.setItem(index, item);
        if (this.getHolder() == null) {
            return;
        }
        ServerPlayer player = ((CraftPlayer)this.getHolder()).getHandle();
        if (player.f_8906_ == null) {
            return;
        }
        if (index < Inventory.m_36059_()) {
            index += 36;
        } else if (index > 39) {
            index += 5;
        } else if (index > 35) {
            index = 8 - (index - 36);
        }
        player.f_8906_.m_9829_((Packet)new ClientboundContainerSetSlotPacket(player.f_36095_.f_38840_, player.f_36095_.m_182425_(), index, CraftItemStack.asNMSCopy(item)));
    }

    @Override
    public void setItem(EquipmentSlot slot, ItemStack item) {
        Preconditions.checkArgument((slot != null ? 1 : 0) != 0, (Object)"slot must not be null");
        switch (slot) {
            case HAND: {
                this.setItemInMainHand(item);
                break;
            }
            case OFF_HAND: {
                this.setItemInOffHand(item);
                break;
            }
            case FEET: {
                this.setBoots(item);
                break;
            }
            case LEGS: {
                this.setLeggings(item);
                break;
            }
            case CHEST: {
                this.setChestplate(item);
                break;
            }
            case HEAD: {
                this.setHelmet(item);
                break;
            }
            default: {
                throw new IllegalArgumentException("Not implemented. This is a bug");
            }
        }
    }

    @Override
    public void setItem(EquipmentSlot slot, ItemStack item, boolean silent) {
        this.setItem(slot, item);
    }

    @Override
    public ItemStack getItem(EquipmentSlot slot) {
        Preconditions.checkArgument((slot != null ? 1 : 0) != 0, (Object)"slot must not be null");
        switch (slot) {
            case HAND: {
                return this.getItemInMainHand();
            }
            case OFF_HAND: {
                return this.getItemInOffHand();
            }
            case FEET: {
                return this.getBoots();
            }
            case LEGS: {
                return this.getLeggings();
            }
            case CHEST: {
                return this.getChestplate();
            }
            case HEAD: {
                return this.getHelmet();
            }
        }
        throw new IllegalArgumentException("Not implemented. This is a bug");
    }

    @Override
    public int getHeldItemSlot() {
        return this.getInventory().f_35977_;
    }

    @Override
    public void setHeldItemSlot(int slot) {
        Preconditions.checkArgument((slot >= 0 && slot < Inventory.m_36059_() ? 1 : 0) != 0, (String)"Slot (%s) is not between 0 and %s inclusive", (int)slot, (int)(Inventory.m_36059_() - 1));
        this.getInventory().f_35977_ = slot;
        ((CraftPlayer)this.getHolder()).getHandle().f_8906_.m_9829_((Packet)new ClientboundSetCarriedItemPacket(slot));
    }

    @Override
    public ItemStack getHelmet() {
        return this.getItem(this.getSize() - 2);
    }

    @Override
    public ItemStack getChestplate() {
        return this.getItem(this.getSize() - 3);
    }

    @Override
    public ItemStack getLeggings() {
        return this.getItem(this.getSize() - 4);
    }

    @Override
    public ItemStack getBoots() {
        return this.getItem(this.getSize() - 5);
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        this.setItem(this.getSize() - 2, helmet);
    }

    @Override
    public void setHelmet(ItemStack helmet, boolean silent) {
        this.setHelmet(helmet);
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        this.setItem(this.getSize() - 3, chestplate);
    }

    @Override
    public void setChestplate(ItemStack chestplate, boolean silent) {
        this.setChestplate(chestplate);
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        this.setItem(this.getSize() - 4, leggings);
    }

    @Override
    public void setLeggings(ItemStack leggings, boolean silent) {
        this.setLeggings(leggings);
    }

    @Override
    public void setBoots(ItemStack boots) {
        this.setItem(this.getSize() - 5, boots);
    }

    @Override
    public void setBoots(ItemStack boots, boolean silent) {
        this.setBoots(boots);
    }

    @Override
    public ItemStack[] getArmorContents() {
        return this.asCraftMirror((List<net.minecraft.world.item.ItemStack>)this.getInventory().f_35975_);
    }

    private void setSlots(ItemStack[] items, int baseSlot, int length) {
        if (items == null) {
            items = new ItemStack[length];
        }
        Preconditions.checkArgument((items.length <= length ? 1 : 0) != 0, (String)"items.length must be <= %s", (int)length);
        int i = 0;
        while (i < length) {
            if (i >= items.length) {
                this.setItem(baseSlot + i, null);
            } else {
                this.setItem(baseSlot + i, items[i]);
            }
            ++i;
        }
    }

    @Override
    public void setStorageContents(ItemStack[] items) throws IllegalArgumentException {
        this.setSlots(items, 0, this.getInventory().f_35974_.size());
    }

    @Override
    public void setArmorContents(ItemStack[] items) {
        this.setSlots(items, this.getInventory().f_35974_.size(), this.getInventory().f_35975_.size());
    }

    @Override
    public ItemStack[] getExtraContents() {
        return this.asCraftMirror((List<net.minecraft.world.item.ItemStack>)this.getInventory().f_35976_);
    }

    @Override
    public void setExtraContents(ItemStack[] items) {
        this.setSlots(items, this.getInventory().f_35974_.size() + this.getInventory().f_35975_.size(), this.getInventory().f_35976_.size());
    }

    @Override
    public HumanEntity getHolder() {
        return (HumanEntity)this.inventory.getOwner();
    }

    @Override
    public float getItemInHandDropChance() {
        return this.getItemInMainHandDropChance();
    }

    @Override
    public void setItemInHandDropChance(float chance) {
        this.setItemInMainHandDropChance(chance);
    }

    @Override
    public float getItemInMainHandDropChance() {
        return 1.0f;
    }

    @Override
    public void setItemInMainHandDropChance(float chance) {
        throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
    }

    @Override
    public float getItemInOffHandDropChance() {
        return 1.0f;
    }

    @Override
    public void setItemInOffHandDropChance(float chance) {
        throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
    }

    @Override
    public float getHelmetDropChance() {
        return 1.0f;
    }

    @Override
    public void setHelmetDropChance(float chance) {
        throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
    }

    @Override
    public float getChestplateDropChance() {
        return 1.0f;
    }

    @Override
    public void setChestplateDropChance(float chance) {
        throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
    }

    @Override
    public float getLeggingsDropChance() {
        return 1.0f;
    }

    @Override
    public void setLeggingsDropChance(float chance) {
        throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
    }

    @Override
    public float getBootsDropChance() {
        return 1.0f;
    }

    @Override
    public void setBootsDropChance(float chance) {
        throw new UnsupportedOperationException("Cannot set drop chance for PlayerInventory");
    }
}

