/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.chat.Component
 *  net.minecraft.world.inventory.AbstractContainerMenu
 */
package io.izzel.arclight.common.bridge.core.inventory.container;

import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.AbstractContainerMenu;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.inventory.InventoryView;

public interface ContainerBridge {
    public InventoryView bridge$getBukkitView();

    public void bridge$transferTo(AbstractContainerMenu var1, CraftHumanEntity var2);

    public Component bridge$getTitle();

    public void bridge$setTitle(Component var1);

    public boolean bridge$isCheckReachable();
}

