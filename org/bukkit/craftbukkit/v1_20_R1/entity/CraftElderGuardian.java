/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.monster.ElderGuardian
 *  net.minecraft.world.entity.monster.Guardian
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Guardian;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGuardian;

public class CraftElderGuardian
extends CraftGuardian
implements org.bukkit.entity.ElderGuardian {
    public CraftElderGuardian(CraftServer server, ElderGuardian entity) {
        super(server, (Guardian)entity);
    }

    @Override
    public String toString() {
        return "CraftElderGuardian";
    }

    @Override
    public boolean isElder() {
        return true;
    }
}

