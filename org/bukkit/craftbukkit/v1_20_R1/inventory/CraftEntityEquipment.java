/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.EquipmentSlot
 *  net.minecraft.world.entity.Mob
 */
package org.bukkit.craftbukkit.v1_20_R1.inventory;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class CraftEntityEquipment
implements EntityEquipment {
    private final CraftLivingEntity entity;

    public CraftEntityEquipment(CraftLivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public void setItem(org.bukkit.inventory.EquipmentSlot slot, ItemStack item) {
        this.setItem(slot, item, false);
    }

    @Override
    public void setItem(org.bukkit.inventory.EquipmentSlot slot, ItemStack item, boolean silent) {
        Preconditions.checkArgument((slot != null ? 1 : 0) != 0, (Object)"slot must not be null");
        EquipmentSlot nmsSlot = CraftEquipmentSlot.getNMS(slot);
        this.setEquipment(nmsSlot, item, silent);
    }

    @Override
    public ItemStack getItem(org.bukkit.inventory.EquipmentSlot slot) {
        Preconditions.checkArgument((slot != null ? 1 : 0) != 0, (Object)"slot must not be null");
        EquipmentSlot nmsSlot = CraftEquipmentSlot.getNMS(slot);
        return this.getEquipment(nmsSlot);
    }

    @Override
    public ItemStack getItemInMainHand() {
        return this.getEquipment(EquipmentSlot.MAINHAND);
    }

    @Override
    public void setItemInMainHand(ItemStack item) {
        this.setItemInMainHand(item, false);
    }

    @Override
    public void setItemInMainHand(ItemStack item, boolean silent) {
        this.setEquipment(EquipmentSlot.MAINHAND, item, silent);
    }

    @Override
    public ItemStack getItemInOffHand() {
        return this.getEquipment(EquipmentSlot.OFFHAND);
    }

    @Override
    public void setItemInOffHand(ItemStack item) {
        this.setItemInOffHand(item, false);
    }

    @Override
    public void setItemInOffHand(ItemStack item, boolean silent) {
        this.setEquipment(EquipmentSlot.OFFHAND, item, silent);
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
    public ItemStack getHelmet() {
        return this.getEquipment(EquipmentSlot.HEAD);
    }

    @Override
    public void setHelmet(ItemStack helmet) {
        this.setHelmet(helmet, false);
    }

    @Override
    public void setHelmet(ItemStack helmet, boolean silent) {
        this.setEquipment(EquipmentSlot.HEAD, helmet, silent);
    }

    @Override
    public ItemStack getChestplate() {
        return this.getEquipment(EquipmentSlot.CHEST);
    }

    @Override
    public void setChestplate(ItemStack chestplate) {
        this.setChestplate(chestplate, false);
    }

    @Override
    public void setChestplate(ItemStack chestplate, boolean silent) {
        this.setEquipment(EquipmentSlot.CHEST, chestplate, silent);
    }

    @Override
    public ItemStack getLeggings() {
        return this.getEquipment(EquipmentSlot.LEGS);
    }

    @Override
    public void setLeggings(ItemStack leggings) {
        this.setLeggings(leggings, false);
    }

    @Override
    public void setLeggings(ItemStack leggings, boolean silent) {
        this.setEquipment(EquipmentSlot.LEGS, leggings, silent);
    }

    @Override
    public ItemStack getBoots() {
        return this.getEquipment(EquipmentSlot.FEET);
    }

    @Override
    public void setBoots(ItemStack boots) {
        this.setBoots(boots, false);
    }

    @Override
    public void setBoots(ItemStack boots, boolean silent) {
        this.setEquipment(EquipmentSlot.FEET, boots, silent);
    }

    @Override
    public ItemStack[] getArmorContents() {
        ItemStack[] armor = new ItemStack[]{this.getEquipment(EquipmentSlot.FEET), this.getEquipment(EquipmentSlot.LEGS), this.getEquipment(EquipmentSlot.CHEST), this.getEquipment(EquipmentSlot.HEAD)};
        return armor;
    }

    @Override
    public void setArmorContents(ItemStack[] items) {
        this.setEquipment(EquipmentSlot.FEET, items.length >= 1 ? items[0] : null, false);
        this.setEquipment(EquipmentSlot.LEGS, items.length >= 2 ? items[1] : null, false);
        this.setEquipment(EquipmentSlot.CHEST, items.length >= 3 ? items[2] : null, false);
        this.setEquipment(EquipmentSlot.HEAD, items.length >= 4 ? items[3] : null, false);
    }

    private ItemStack getEquipment(EquipmentSlot slot) {
        return CraftItemStack.asBukkitCopy(this.entity.getHandle().m_6844_(slot));
    }

    private void setEquipment(EquipmentSlot slot, ItemStack stack, boolean silent) {
        this.entity.getHandle().setItemSlot(slot, CraftItemStack.asNMSCopy(stack), silent);
    }

    @Override
    public void clear() {
        EquipmentSlot[] equipmentSlotArray = EquipmentSlot.values();
        int n = equipmentSlotArray.length;
        int n2 = 0;
        while (n2 < n) {
            EquipmentSlot slot = equipmentSlotArray[n2];
            this.setEquipment(slot, null, false);
            ++n2;
        }
    }

    @Override
    public Entity getHolder() {
        return this.entity;
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
        return this.getDropChance(EquipmentSlot.MAINHAND);
    }

    @Override
    public void setItemInMainHandDropChance(float chance) {
        this.setDropChance(EquipmentSlot.MAINHAND, chance);
    }

    @Override
    public float getItemInOffHandDropChance() {
        return this.getDropChance(EquipmentSlot.OFFHAND);
    }

    @Override
    public void setItemInOffHandDropChance(float chance) {
        this.setDropChance(EquipmentSlot.OFFHAND, chance);
    }

    @Override
    public float getHelmetDropChance() {
        return this.getDropChance(EquipmentSlot.HEAD);
    }

    @Override
    public void setHelmetDropChance(float chance) {
        this.setDropChance(EquipmentSlot.HEAD, chance);
    }

    @Override
    public float getChestplateDropChance() {
        return this.getDropChance(EquipmentSlot.CHEST);
    }

    @Override
    public void setChestplateDropChance(float chance) {
        this.setDropChance(EquipmentSlot.CHEST, chance);
    }

    @Override
    public float getLeggingsDropChance() {
        return this.getDropChance(EquipmentSlot.LEGS);
    }

    @Override
    public void setLeggingsDropChance(float chance) {
        this.setDropChance(EquipmentSlot.LEGS, chance);
    }

    @Override
    public float getBootsDropChance() {
        return this.getDropChance(EquipmentSlot.FEET);
    }

    @Override
    public void setBootsDropChance(float chance) {
        this.setDropChance(EquipmentSlot.FEET, chance);
    }

    private void setDropChance(EquipmentSlot slot, float chance) {
        Preconditions.checkArgument((boolean)(this.entity.getHandle() instanceof Mob), (Object)"Cannot set drop chance for non-Mob entity");
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
            ((Mob)this.entity.getHandle()).f_21347_[slot.m_20749_()] = chance;
        } else {
            ((Mob)this.entity.getHandle()).f_21348_[slot.m_20749_()] = chance;
        }
    }

    private float getDropChance(EquipmentSlot slot) {
        if (!(this.entity.getHandle() instanceof Mob)) {
            return 1.0f;
        }
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
            return ((Mob)this.entity.getHandle()).f_21347_[slot.m_20749_()];
        }
        return ((Mob)this.entity.getHandle()).f_21348_[slot.m_20749_()];
    }
}

