/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.Advancement
 */
package org.bukkit.craftbukkit.v1_20_R1.advancement;

import java.util.Collection;
import java.util.Collections;
import net.minecraft.advancements.Advancement;
import org.bukkit.NamespacedKey;
import org.bukkit.advancement.AdvancementDisplay;
import org.bukkit.craftbukkit.v1_20_R1.advancement.CraftAdvancementDisplay;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;

public class CraftAdvancement
implements org.bukkit.advancement.Advancement {
    private final Advancement handle;

    public CraftAdvancement(Advancement handle) {
        this.handle = handle;
    }

    public Advancement getHandle() {
        return this.handle;
    }

    @Override
    public NamespacedKey getKey() {
        return CraftNamespacedKey.fromMinecraft(this.handle.m_138327_());
    }

    @Override
    public Collection<String> getCriteria() {
        return Collections.unmodifiableCollection(this.handle.m_138325_().keySet());
    }

    @Override
    public AdvancementDisplay getDisplay() {
        if (this.handle.m_138320_() == null) {
            return null;
        }
        return new CraftAdvancementDisplay(this.handle.m_138320_());
    }
}

