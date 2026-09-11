/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.core.Rotations
 *  net.minecraft.world.entity.LivingEntity
 *  net.minecraft.world.entity.decoration.ArmorStand
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.core.Rotations;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.bukkit.craftbukkit.v1_20_R1.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

public class CraftArmorStand
extends CraftLivingEntity
implements org.bukkit.entity.ArmorStand {
    public CraftArmorStand(CraftServer server, ArmorStand entity) {
        super(server, (LivingEntity)entity);
    }

    @Override
    public String toString() {
        return "CraftArmorStand";
    }

    public ArmorStand getHandle() {
        return (ArmorStand)super.getHandle();
    }

    @Override
    public ItemStack getItemInHand() {
        return this.getEquipment().getItemInHand();
    }

    @Override
    public void setItemInHand(ItemStack item) {
        this.getEquipment().setItemInHand(item);
    }

    @Override
    public ItemStack getBoots() {
        return this.getEquipment().getBoots();
    }

    @Override
    public void setBoots(ItemStack item) {
        this.getEquipment().setBoots(item);
    }

    @Override
    public ItemStack getLeggings() {
        return this.getEquipment().getLeggings();
    }

    @Override
    public void setLeggings(ItemStack item) {
        this.getEquipment().setLeggings(item);
    }

    @Override
    public ItemStack getChestplate() {
        return this.getEquipment().getChestplate();
    }

    @Override
    public void setChestplate(ItemStack item) {
        this.getEquipment().setChestplate(item);
    }

    @Override
    public ItemStack getHelmet() {
        return this.getEquipment().getHelmet();
    }

    @Override
    public void setHelmet(ItemStack item) {
        this.getEquipment().setHelmet(item);
    }

    @Override
    public EulerAngle getBodyPose() {
        return CraftArmorStand.fromNMS(this.getHandle().f_31543_);
    }

    @Override
    public void setBodyPose(EulerAngle pose) {
        this.getHandle().m_31616_(CraftArmorStand.toNMS(pose));
    }

    @Override
    public EulerAngle getLeftArmPose() {
        return CraftArmorStand.fromNMS(this.getHandle().f_31544_);
    }

    @Override
    public void setLeftArmPose(EulerAngle pose) {
        this.getHandle().m_31623_(CraftArmorStand.toNMS(pose));
    }

    @Override
    public EulerAngle getRightArmPose() {
        return CraftArmorStand.fromNMS(this.getHandle().f_31545_);
    }

    @Override
    public void setRightArmPose(EulerAngle pose) {
        this.getHandle().m_31628_(CraftArmorStand.toNMS(pose));
    }

    @Override
    public EulerAngle getLeftLegPose() {
        return CraftArmorStand.fromNMS(this.getHandle().f_31525_);
    }

    @Override
    public void setLeftLegPose(EulerAngle pose) {
        this.getHandle().m_31639_(CraftArmorStand.toNMS(pose));
    }

    @Override
    public EulerAngle getRightLegPose() {
        return CraftArmorStand.fromNMS(this.getHandle().f_31526_);
    }

    @Override
    public void setRightLegPose(EulerAngle pose) {
        this.getHandle().m_31651_(CraftArmorStand.toNMS(pose));
    }

    @Override
    public EulerAngle getHeadPose() {
        return CraftArmorStand.fromNMS(this.getHandle().f_31542_);
    }

    @Override
    public void setHeadPose(EulerAngle pose) {
        this.getHandle().m_31597_(CraftArmorStand.toNMS(pose));
    }

    @Override
    public boolean hasBasePlate() {
        return !this.getHandle().m_31674_();
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        this.getHandle().m_31678_(!basePlate);
    }

    @Override
    public void setGravity(boolean gravity) {
        super.setGravity(gravity);
        this.getHandle().f_19794_ = !gravity;
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
    public boolean hasArms() {
        return this.getHandle().m_31671_();
    }

    @Override
    public void setArms(boolean arms) {
        this.getHandle().m_31675_(arms);
    }

    @Override
    public boolean isSmall() {
        return this.getHandle().m_31666_();
    }

    @Override
    public void setSmall(boolean small) {
        this.getHandle().m_31603_(small);
    }

    private static EulerAngle fromNMS(Rotations old) {
        return new EulerAngle(Math.toRadians(old.m_123156_()), Math.toRadians(old.m_123157_()), Math.toRadians(old.m_123158_()));
    }

    private static Rotations toNMS(EulerAngle old) {
        return new Rotations((float)Math.toDegrees(old.getX()), (float)Math.toDegrees(old.getY()), (float)Math.toDegrees(old.getZ()));
    }

    @Override
    public boolean isMarker() {
        return this.getHandle().m_31677_();
    }

    @Override
    public void setMarker(boolean marker) {
        this.getHandle().m_31681_(marker);
    }

    @Override
    public void addEquipmentLock(EquipmentSlot equipmentSlot, ArmorStand.LockType lockType) {
        this.getHandle().f_31541_ |= 1 << CraftEquipmentSlot.getNMS(equipmentSlot).m_20750_() + lockType.ordinal() * 8;
    }

    @Override
    public void removeEquipmentLock(EquipmentSlot equipmentSlot, ArmorStand.LockType lockType) {
        this.getHandle().f_31541_ &= ~(1 << CraftEquipmentSlot.getNMS(equipmentSlot).m_20750_() + lockType.ordinal() * 8);
    }

    @Override
    public boolean hasEquipmentLock(EquipmentSlot equipmentSlot, ArmorStand.LockType lockType) {
        return (this.getHandle().f_31541_ & 1 << CraftEquipmentSlot.getNMS(equipmentSlot).m_20750_() + lockType.ordinal() * 8) != 0;
    }
}

