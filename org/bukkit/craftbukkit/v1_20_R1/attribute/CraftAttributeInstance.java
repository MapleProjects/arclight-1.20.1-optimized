/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier
 *  net.minecraft.world.entity.ai.attributes.AttributeModifier$Operation
 */
package org.bukkit.craftbukkit.v1_20_R1.attribute;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;

public class CraftAttributeInstance
implements AttributeInstance {
    private final net.minecraft.world.entity.ai.attributes.AttributeInstance handle;
    private final Attribute attribute;

    public CraftAttributeInstance(net.minecraft.world.entity.ai.attributes.AttributeInstance handle, Attribute attribute) {
        this.handle = handle;
        this.attribute = attribute;
    }

    @Override
    public Attribute getAttribute() {
        return this.attribute;
    }

    @Override
    public double getBaseValue() {
        return this.handle.m_22115_();
    }

    @Override
    public void setBaseValue(double d) {
        this.handle.m_22100_(d);
    }

    @Override
    public Collection<AttributeModifier> getModifiers() {
        ArrayList<AttributeModifier> result = new ArrayList<AttributeModifier>();
        for (net.minecraft.world.entity.ai.attributes.AttributeModifier nms : this.handle.m_22122_()) {
            result.add(CraftAttributeInstance.convert(nms));
        }
        return result;
    }

    @Override
    public void addModifier(AttributeModifier modifier) {
        Preconditions.checkArgument((modifier != null ? 1 : 0) != 0, (Object)"modifier");
        this.handle.m_22125_(CraftAttributeInstance.convert(modifier));
    }

    @Override
    public void removeModifier(AttributeModifier modifier) {
        Preconditions.checkArgument((modifier != null ? 1 : 0) != 0, (Object)"modifier");
        this.handle.m_22130_(CraftAttributeInstance.convert(modifier));
    }

    @Override
    public double getValue() {
        return this.handle.m_22135_();
    }

    @Override
    public double getDefaultValue() {
        return this.handle.m_22099_().m_22082_();
    }

    public static net.minecraft.world.entity.ai.attributes.AttributeModifier convert(AttributeModifier bukkit) {
        return new net.minecraft.world.entity.ai.attributes.AttributeModifier(bukkit.getUniqueId(), bukkit.getName(), bukkit.getAmount(), AttributeModifier.Operation.values()[bukkit.getOperation().ordinal()]);
    }

    public static AttributeModifier convert(net.minecraft.world.entity.ai.attributes.AttributeModifier nms) {
        return new AttributeModifier(nms.m_22209_(), nms.m_22214_(), nms.m_22218_(), AttributeModifier.Operation.values()[nms.m_22217_().ordinal()]);
    }

    public static AttributeModifier convert(net.minecraft.world.entity.ai.attributes.AttributeModifier nms, EquipmentSlot slot) {
        return new AttributeModifier(nms.m_22209_(), nms.m_22214_(), nms.m_22218_(), AttributeModifier.Operation.values()[nms.m_22217_().ordinal()], slot);
    }
}

