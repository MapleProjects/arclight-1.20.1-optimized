/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.LightningBolt
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.entity.LightningStrike;

public class CraftLightningStrike
extends CraftEntity
implements LightningStrike {
    private final LightningStrike.Spigot spigot = new LightningStrike.Spigot(){

        @Override
        public boolean isSilent() {
            return CraftLightningStrike.this.getHandle().isSilent;
        }
    };

    public CraftLightningStrike(CraftServer server, LightningBolt entity) {
        super(server, (Entity)entity);
    }

    @Override
    public boolean isEffect() {
        return this.getHandle().f_20862_;
    }

    public LightningBolt getHandle() {
        return (LightningBolt)this.entity;
    }

    @Override
    public String toString() {
        return "CraftLightningStrike";
    }

    @Override
    public LightningStrike.Spigot spigot() {
        return this.spigot;
    }
}

