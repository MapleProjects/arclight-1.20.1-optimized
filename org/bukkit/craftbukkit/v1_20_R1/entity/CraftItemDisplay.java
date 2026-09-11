/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.Display
 *  net.minecraft.world.entity.Display$ItemDisplay
 *  net.minecraft.world.item.ItemDisplayContext
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.Display;
import net.minecraft.world.item.ItemDisplayContext;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftDisplay;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

public class CraftItemDisplay
extends CraftDisplay
implements ItemDisplay {
    public CraftItemDisplay(CraftServer server, Display.ItemDisplay entity) {
        super(server, (Display)entity);
    }

    public Display.ItemDisplay getHandle() {
        return (Display.ItemDisplay)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftItemDisplay";
    }

    @Override
    public ItemStack getItemStack() {
        return CraftItemStack.asBukkitCopy(this.getHandle().m_269568_());
    }

    @Override
    public void setItemStack(ItemStack item) {
        this.getHandle().m_269362_(CraftItemStack.asNMSCopy(item));
    }

    @Override
    public ItemDisplay.ItemDisplayTransform getItemDisplayTransform() {
        return ItemDisplay.ItemDisplayTransform.values()[this.getHandle().m_269386_().ordinal()];
    }

    @Override
    public void setItemDisplayTransform(ItemDisplay.ItemDisplayTransform display) {
        Preconditions.checkArgument((display != null ? 1 : 0) != 0, (Object)"Display cannot be null");
        this.getHandle().m_269028_((ItemDisplayContext)ItemDisplayContext.f_268648_.apply(display.ordinal()));
    }
}

