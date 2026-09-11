/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.attribute;

import java.util.Collection;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.jetbrains.annotations.NotNull;

public interface AttributeInstance {
    @NotNull
    public Attribute getAttribute();

    public double getBaseValue();

    public void setBaseValue(double var1);

    @NotNull
    public Collection<AttributeModifier> getModifiers();

    public void addModifier(@NotNull AttributeModifier var1);

    public void removeModifier(@NotNull AttributeModifier var1);

    public double getValue();

    public double getDefaultValue();
}

