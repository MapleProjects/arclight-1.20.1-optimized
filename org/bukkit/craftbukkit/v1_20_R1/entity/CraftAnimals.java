/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.animal.Animal
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.UUID;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Animal;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAgeable;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.entity.Animals;
import org.bukkit.inventory.ItemStack;

public class CraftAnimals
extends CraftAgeable
implements Animals {
    public CraftAnimals(CraftServer server, Animal entity) {
        super(server, (AgeableMob)entity);
    }

    public Animal getHandle() {
        return (Animal)this.entity;
    }

    @Override
    public String toString() {
        return "CraftAnimals";
    }

    @Override
    public UUID getBreedCause() {
        return this.getHandle().f_27555_;
    }

    @Override
    public void setBreedCause(UUID uuid) {
        this.getHandle().f_27555_ = uuid;
    }

    @Override
    public boolean isLoveMode() {
        return this.getHandle().m_27593_();
    }

    @Override
    public void setLoveModeTicks(int ticks) {
        Preconditions.checkArgument((ticks >= 0 ? 1 : 0) != 0, (Object)"Love mode ticks must be positive or 0");
        this.getHandle().m_27601_(ticks);
    }

    @Override
    public int getLoveModeTicks() {
        return this.getHandle().f_27554_;
    }

    @Override
    public boolean isBreedItem(ItemStack itemStack) {
        return this.getHandle().m_6898_(CraftItemStack.asNMSCopy(itemStack));
    }

    @Override
    public boolean isBreedItem(Material material) {
        return this.isBreedItem(new ItemStack(material));
    }
}

