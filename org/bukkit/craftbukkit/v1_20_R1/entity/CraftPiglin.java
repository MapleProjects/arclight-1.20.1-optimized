/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.monster.piglin.AbstractPiglin
 *  net.minecraft.world.entity.monster.piglin.Piglin
 *  net.minecraft.world.item.Item
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.world.Container;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.item.Item;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPiglinAbstract;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftMagicNumbers;
import org.bukkit.entity.Piglin;
import org.bukkit.inventory.Inventory;

public class CraftPiglin
extends CraftPiglinAbstract
implements Piglin {
    public CraftPiglin(CraftServer server, net.minecraft.world.entity.monster.piglin.Piglin entity) {
        super(server, (AbstractPiglin)entity);
    }

    @Override
    public boolean isAbleToHunt() {
        return this.getHandle().f_34679_;
    }

    @Override
    public void setIsAbleToHunt(boolean flag) {
        this.getHandle().f_34679_ = flag;
    }

    @Override
    public boolean addBarterMaterial(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"material cannot be null");
        Item item = CraftMagicNumbers.getItem(material);
        return this.getHandle().allowedBarterItems.add(item);
    }

    @Override
    public boolean removeBarterMaterial(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"material cannot be null");
        Item item = CraftMagicNumbers.getItem(material);
        return this.getHandle().allowedBarterItems.remove(item);
    }

    @Override
    public boolean addMaterialOfInterest(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"material cannot be null");
        Item item = CraftMagicNumbers.getItem(material);
        return this.getHandle().interestItems.add(item);
    }

    @Override
    public boolean removeMaterialOfInterest(Material material) {
        Preconditions.checkArgument((material != null ? 1 : 0) != 0, (Object)"material cannot be null");
        Item item = CraftMagicNumbers.getItem(material);
        return this.getHandle().interestItems.remove(item);
    }

    @Override
    public Set<Material> getInterestList() {
        return Collections.unmodifiableSet(this.getHandle().interestItems.stream().map(CraftMagicNumbers::getMaterial).collect(Collectors.toSet()));
    }

    @Override
    public Set<Material> getBarterList() {
        return Collections.unmodifiableSet(this.getHandle().allowedBarterItems.stream().map(CraftMagicNumbers::getMaterial).collect(Collectors.toSet()));
    }

    @Override
    public Inventory getInventory() {
        return new CraftInventory((Container)this.getHandle().f_34678_);
    }

    public net.minecraft.world.entity.monster.piglin.Piglin getHandle() {
        return (net.minecraft.world.entity.monster.piglin.Piglin)super.getHandle();
    }

    @Override
    public String toString() {
        return "CraftPiglin";
    }
}

