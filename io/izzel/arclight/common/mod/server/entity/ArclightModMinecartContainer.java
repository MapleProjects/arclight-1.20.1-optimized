/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.vehicle.AbstractMinecart
 *  net.minecraft.world.entity.vehicle.AbstractMinecartContainer
 */
package io.izzel.arclight.common.mod.server.entity;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.AbstractMinecartContainer;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftMinecartContainer;

public class ArclightModMinecartContainer
extends CraftMinecartContainer {
    public ArclightModMinecartContainer(CraftServer server, AbstractMinecartContainer entity) {
        super(server, (AbstractMinecart)entity);
    }
}

