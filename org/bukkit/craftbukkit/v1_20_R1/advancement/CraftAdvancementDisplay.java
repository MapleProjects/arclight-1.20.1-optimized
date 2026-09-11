/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.advancements.DisplayInfo
 */
package org.bukkit.craftbukkit.v1_20_R1.advancement;

import net.minecraft.advancements.DisplayInfo;
import org.bukkit.advancement.AdvancementDisplay;
import org.bukkit.advancement.AdvancementDisplayType;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.inventory.ItemStack;

public class CraftAdvancementDisplay
implements AdvancementDisplay {
    private final DisplayInfo handle;

    public CraftAdvancementDisplay(DisplayInfo handle) {
        this.handle = handle;
    }

    public DisplayInfo getHandle() {
        return this.handle;
    }

    @Override
    public String getTitle() {
        return CraftChatMessage.fromComponent(this.handle.m_14977_());
    }

    @Override
    public String getDescription() {
        return CraftChatMessage.fromComponent(this.handle.m_14985_());
    }

    @Override
    public ItemStack getIcon() {
        return CraftItemStack.asBukkitCopy(this.handle.m_14990_());
    }

    @Override
    public boolean shouldShowToast() {
        return this.handle.m_14995_();
    }

    @Override
    public boolean shouldAnnounceChat() {
        return this.handle.m_14996_();
    }

    @Override
    public boolean isHidden() {
        return this.handle.m_14997_();
    }

    @Override
    public float getX() {
        return this.handle.m_14993_();
    }

    @Override
    public float getY() {
        return this.handle.m_14994_();
    }

    @Override
    public AdvancementDisplayType getType() {
        return AdvancementDisplayType.values()[this.handle.m_14992_().ordinal()];
    }
}

