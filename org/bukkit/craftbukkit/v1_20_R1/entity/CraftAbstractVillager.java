/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.AgeableMob
 *  net.minecraft.world.entity.npc.AbstractVillager
 *  net.minecraft.world.entity.npc.Villager
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import java.util.List;
import net.minecraft.world.Container;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftAgeable;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftMerchant;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.MerchantRecipe;

public class CraftAbstractVillager
extends CraftAgeable
implements org.bukkit.entity.AbstractVillager,
InventoryHolder {
    public CraftAbstractVillager(CraftServer server, AbstractVillager entity) {
        super(server, (AgeableMob)entity);
    }

    public AbstractVillager getHandle() {
        return (Villager)this.entity;
    }

    @Override
    public String toString() {
        return "CraftAbstractVillager";
    }

    @Override
    public Inventory getInventory() {
        return new CraftInventory((Container)this.getHandle().m_35311_());
    }

    private CraftMerchant getMerchant() {
        return this.getHandle().getCraftMerchant();
    }

    @Override
    public List<MerchantRecipe> getRecipes() {
        return this.getMerchant().getRecipes();
    }

    @Override
    public void setRecipes(List<MerchantRecipe> recipes) {
        this.getMerchant().setRecipes(recipes);
    }

    @Override
    public MerchantRecipe getRecipe(int i) {
        return this.getMerchant().getRecipe(i);
    }

    @Override
    public void setRecipe(int i, MerchantRecipe merchantRecipe) {
        this.getMerchant().setRecipe(i, merchantRecipe);
    }

    @Override
    public int getRecipeCount() {
        return this.getMerchant().getRecipeCount();
    }

    @Override
    public boolean isTrading() {
        return this.getTrader() != null;
    }

    @Override
    public HumanEntity getTrader() {
        return this.getMerchant().getTrader();
    }
}

