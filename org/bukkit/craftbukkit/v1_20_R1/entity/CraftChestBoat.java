/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.vehicle.Boat
 *  net.minecraft.world.entity.vehicle.ChestBoat
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftBoat;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.loot.LootTable;

public class CraftChestBoat
extends CraftBoat
implements org.bukkit.entity.ChestBoat {
    private final Inventory inventory;

    public CraftChestBoat(CraftServer server, ChestBoat entity) {
        super(server, (Boat)entity);
        this.inventory = new CraftInventory((Container)entity);
    }

    public ChestBoat getHandle() {
        return (ChestBoat)this.entity;
    }

    @Override
    public String toString() {
        return "CraftChestBoat";
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    public void setLootTable(LootTable table) {
        this.setLootTable(table, this.getSeed());
    }

    @Override
    public LootTable getLootTable() {
        ResourceLocation nmsTable = this.getHandle().m_214142_();
        if (nmsTable == null) {
            return null;
        }
        NamespacedKey key = CraftNamespacedKey.fromMinecraft(nmsTable);
        return Bukkit.getLootTable(key);
    }

    @Override
    public void setSeed(long seed) {
        this.setLootTable(this.getLootTable(), seed);
    }

    @Override
    public long getSeed() {
        return this.getHandle().m_213803_();
    }

    private void setLootTable(LootTable table, long seed) {
        ResourceLocation newKey = table == null ? null : CraftNamespacedKey.toMinecraft(table.getKey());
        this.getHandle().m_214199_(newKey);
        this.getHandle().m_214065_(seed);
    }
}

