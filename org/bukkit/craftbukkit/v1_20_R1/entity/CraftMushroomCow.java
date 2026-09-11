/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.animal.Cow
 *  net.minecraft.world.entity.animal.MushroomCow
 *  net.minecraft.world.entity.animal.MushroomCow$MushroomType
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.MushroomCow;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftCow;
import org.bukkit.entity.MushroomCow;

public class CraftMushroomCow
extends CraftCow
implements org.bukkit.entity.MushroomCow {
    public CraftMushroomCow(CraftServer server, MushroomCow entity) {
        super(server, (Cow)entity);
    }

    public MushroomCow getHandle() {
        return (MushroomCow)this.entity;
    }

    @Override
    public MushroomCow.Variant getVariant() {
        return MushroomCow.Variant.values()[this.getHandle().m_28554_().ordinal()];
    }

    @Override
    public void setVariant(MushroomCow.Variant variant) {
        Preconditions.checkArgument((variant != null ? 1 : 0) != 0, (Object)"variant");
        this.getHandle().m_28464_(MushroomCow.MushroomType.values()[variant.ordinal()]);
    }

    @Override
    public String toString() {
        return "CraftMushroomCow";
    }
}

