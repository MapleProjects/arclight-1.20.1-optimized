/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.core.registries.BuiltInRegistries
 *  net.minecraft.world.entity.ai.attributes.Attribute
 *  net.minecraft.world.entity.ai.attributes.AttributeInstance
 *  net.minecraft.world.entity.ai.attributes.AttributeMap
 */
package org.bukkit.craftbukkit.v1_20_R1.attribute;

import com.google.common.base.Preconditions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.bukkit.Registry;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.craftbukkit.v1_20_R1.attribute.CraftAttributeInstance;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;

public class CraftAttributeMap
implements Attributable {
    private final AttributeMap handle;

    public CraftAttributeMap(AttributeMap handle) {
        this.handle = handle;
    }

    @Override
    public AttributeInstance getAttribute(Attribute attribute) {
        Preconditions.checkArgument((attribute != null ? 1 : 0) != 0, (Object)"attribute");
        net.minecraft.world.entity.ai.attributes.AttributeInstance nms = this.handle.m_22146_(CraftAttributeMap.toMinecraft(attribute));
        return nms == null ? null : new CraftAttributeInstance(nms, attribute);
    }

    public static net.minecraft.world.entity.ai.attributes.Attribute toMinecraft(Attribute attribute) {
        return (net.minecraft.world.entity.ai.attributes.Attribute)BuiltInRegistries.f_256951_.m_7745_(CraftNamespacedKey.toMinecraft(attribute.getKey()));
    }

    public static Attribute fromMinecraft(String nms) {
        return Registry.ATTRIBUTE.get(CraftNamespacedKey.fromString(nms));
    }
}

