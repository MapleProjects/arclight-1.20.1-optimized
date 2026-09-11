/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.Advancement
 *  org.spongepowered.asm.mixin.Mixin
 */
package io.izzel.arclight.common.mixin.core.advancements;

import io.izzel.arclight.common.bridge.core.advancement.AdvancementBridge;
import net.minecraft.advancements.Advancement;
import org.bukkit.craftbukkit.v1_20_R1.advancement.CraftAdvancement;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value={Advancement.class})
public class AdvancementMixin
implements AdvancementBridge {
    public final org.bukkit.advancement.Advancement bukkit = new CraftAdvancement((Advancement)this);

    @Override
    public org.bukkit.advancement.Advancement bridge$getBukkit() {
        return this.bukkit;
    }
}

