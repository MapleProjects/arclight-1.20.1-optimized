/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.Animal
 *  net.minecraft.world.entity.animal.Rabbit
 *  net.minecraft.world.entity.animal.Rabbit$Variant
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Rabbit;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAnimals;
import org.bukkit.entity.Rabbit;

public class CraftRabbit
extends CraftAnimals
implements org.bukkit.entity.Rabbit {
    public CraftRabbit(CraftServer server, Rabbit entity) {
        super(server, (Animal)entity);
    }

    public Rabbit getHandle() {
        return (Rabbit)this.entity;
    }

    @Override
    public String toString() {
        return "CraftRabbit{RabbitType=" + (Object)((Object)this.getRabbitType()) + "}";
    }

    @Override
    public Rabbit.Type getRabbitType() {
        return Rabbit.Type.values()[this.getHandle().m_28554_().ordinal()];
    }

    @Override
    public void setRabbitType(Rabbit.Type type) {
        this.getHandle().m_28464_(Rabbit.Variant.values()[type.ordinal()]);
    }
}

