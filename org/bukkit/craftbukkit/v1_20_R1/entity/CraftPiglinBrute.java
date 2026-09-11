/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.piglin.AbstractPiglin
 *  net.minecraft.world.entity.monster.piglin.PiglinBrute
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPiglinAbstract;
import org.bukkit.entity.PiglinBrute;

public class CraftPiglinBrute
extends CraftPiglinAbstract
implements PiglinBrute {
    public CraftPiglinBrute(CraftServer server, net.minecraft.world.entity.monster.piglin.PiglinBrute entity) {
        super(server, (AbstractPiglin)entity);
    }

    public net.minecraft.world.entity.monster.piglin.PiglinBrute getHandle() {
        return (net.minecraft.world.entity.monster.piglin.PiglinBrute)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftPiglinBrute";
    }
}

