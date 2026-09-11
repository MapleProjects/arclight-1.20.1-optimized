/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.world.Container
 *  net.minecraft.world.entity.Entity
 *  net.minecraft.world.entity.player.Player
 *  net.minecraft.world.item.ItemStack
 *  net.minecraft.world.level.storage.loot.LootContext
 *  net.minecraft.world.level.storage.loot.LootParams
 *  net.minecraft.world.level.storage.loot.LootParams$Builder
 *  net.minecraft.world.level.storage.loot.LootTable
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParam
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParamSet$Builder
 *  net.minecraft.world.level.storage.loot.parameters.LootContextParams
 *  net.minecraft.world.phys.Vec3
 */
package org.bukkit.craftbukkit.v1_20_R1;

import com.google.common.base.Preconditions;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftHumanEntity;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftLocation;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

public class CraftLootTable
implements LootTable {
    private final net.minecraft.world.level.storage.loot.LootTable handle;
    private final NamespacedKey key;

    public CraftLootTable(NamespacedKey key, net.minecraft.world.level.storage.loot.LootTable handle) {
        this.handle = handle;
        this.key = key;
    }

    public net.minecraft.world.level.storage.loot.LootTable getHandle() {
        return this.handle;
    }

    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext context) {
        Preconditions.checkArgument((context != null ? 1 : 0) != 0, (Object)"LootContext cannot be null");
        LootParams nmsContext = this.convertContext(context, random);
        ObjectArrayList nmsItems = this.handle.m_287195_(nmsContext);
        ArrayList<ItemStack> bukkit = new ArrayList<ItemStack>(nmsItems.size());
        for (net.minecraft.world.item.ItemStack item : nmsItems) {
            if (item.m_41619_()) continue;
            bukkit.add(CraftItemStack.asBukkitCopy(item));
        }
        return bukkit;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext context) {
        Preconditions.checkArgument((inventory != null ? 1 : 0) != 0, (Object)"Inventory cannot be null");
        Preconditions.checkArgument((context != null ? 1 : 0) != 0, (Object)"LootContext cannot be null");
        LootParams nmsContext = this.convertContext(context, random);
        CraftInventory craftInventory = (CraftInventory)inventory;
        Container handle = craftInventory.getInventory();
        this.getHandle().fillInventory(handle, nmsContext, random.nextLong(), true);
    }

    @Override
    public NamespacedKey getKey() {
        return this.key;
    }

    private LootParams convertContext(LootContext context, Random random) {
        Preconditions.checkArgument((context != null ? 1 : 0) != 0, (Object)"LootContext cannot be null");
        Location loc = context.getLocation();
        Preconditions.checkArgument((loc.getWorld() != null ? 1 : 0) != 0, (Object)"LootContext.getLocation#getWorld cannot be null");
        ServerLevel handle = ((CraftWorld)loc.getWorld()).getHandle();
        LootParams.Builder builder = new LootParams.Builder(handle);
        this.setMaybe(builder, LootContextParams.f_81460_, CraftLocation.toVec3D(loc));
        if (this.getHandle() != net.minecraft.world.level.storage.loot.LootTable.f_79105_) {
            if (context.getLootedEntity() != null) {
                Entity nmsLootedEntity = ((CraftEntity)context.getLootedEntity()).getHandle();
                this.setMaybe(builder, LootContextParams.f_81455_, nmsLootedEntity);
                this.setMaybe(builder, LootContextParams.f_81457_, handle.m_269111_().m_269264_());
                this.setMaybe(builder, LootContextParams.f_81460_, nmsLootedEntity.m_20182_());
            }
            if (context.getKiller() != null) {
                Player nmsKiller = ((CraftHumanEntity)context.getKiller()).getHandle();
                this.setMaybe(builder, LootContextParams.f_81458_, nmsKiller);
                this.setMaybe(builder, LootContextParams.f_81457_, handle.m_269111_().m_269075_(nmsKiller));
                this.setMaybe(builder, LootContextParams.f_81456_, nmsKiller);
                this.setMaybe(builder, LootContextParams.f_81463_, nmsKiller.m_21211_());
            }
            if (context.getLootingModifier() != -1) {
                this.setMaybe(builder, LootContextParams.LOOTING_MOD, context.getLootingModifier());
            }
        }
        LootContextParamSet.Builder nmsBuilder = new LootContextParamSet.Builder();
        for (LootContextParam param : this.getHandle().m_79122_().m_81394_()) {
            nmsBuilder.m_81406_(param);
        }
        for (LootContextParam param : this.getHandle().m_79122_().m_81398_()) {
            if (this.getHandle().m_79122_().m_81394_().contains(param)) continue;
            nmsBuilder.m_81408_(param);
        }
        nmsBuilder.m_81408_(LootContextParams.LOOTING_MOD);
        return builder.m_287235_(this.getHandle().m_79122_());
    }

    private <T> void setMaybe(LootParams.Builder builder, LootContextParam<T> param, T value) {
        if (this.getHandle().m_79122_().m_81394_().contains(param) || this.getHandle().m_79122_().m_81398_().contains(param)) {
            builder.m_287286_(param, value);
        }
    }

    public static LootContext convertContext(net.minecraft.world.level.storage.loot.LootContext info) {
        CraftEntity killer;
        Vec3 position = (Vec3)info.m_78953_(LootContextParams.f_81460_);
        if (position == null) {
            position = ((Entity)info.m_78953_(LootContextParams.f_81455_)).m_20182_();
        }
        Location location = CraftLocation.toBukkit(position, (World)info.m_78952_().getWorld());
        LootContext.Builder contextBuilder = new LootContext.Builder(location);
        if (info.m_78936_(LootContextParams.f_81458_) && (killer = ((Entity)info.m_78953_(LootContextParams.f_81458_)).getBukkitEntity()) instanceof CraftHumanEntity) {
            contextBuilder.killer((CraftHumanEntity)killer);
        }
        if (info.m_78936_(LootContextParams.f_81455_)) {
            contextBuilder.lootedEntity(((Entity)info.m_78953_(LootContextParams.f_81455_)).getBukkitEntity());
        }
        if (info.m_78936_(LootContextParams.LOOTING_MOD)) {
            contextBuilder.lootingModifier((Integer)info.m_78953_(LootContextParams.LOOTING_MOD));
        }
        contextBuilder.luck(info.m_78945_());
        return contextBuilder.build();
    }

    public String toString() {
        return this.getKey().toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LootTable)) {
            return false;
        }
        LootTable table = (LootTable)obj;
        return table.getKey().equals(this.getKey());
    }
}

