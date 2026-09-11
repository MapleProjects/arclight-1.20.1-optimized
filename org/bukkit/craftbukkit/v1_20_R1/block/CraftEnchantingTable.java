/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import net.minecraft.world.level.block.entity.EnchantmentTableBlockEntity;
import org.bukkit.World;
import org.bukkit.block.EnchantingTable;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;

public class CraftEnchantingTable
extends CraftBlockEntityState<EnchantmentTableBlockEntity>
implements EnchantingTable {
    public CraftEnchantingTable(World world, EnchantmentTableBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public String getCustomName() {
        EnchantmentTableBlockEntity enchant = (EnchantmentTableBlockEntity)this.getSnapshot();
        return enchant.m_8077_() ? CraftChatMessage.fromComponent(enchant.m_7770_()) : null;
    }

    @Override
    public void setCustomName(String name) {
        ((EnchantmentTableBlockEntity)this.getSnapshot()).m_59272_(CraftChatMessage.fromStringOrNull(name));
    }

    @Override
    public void applyTo(EnchantmentTableBlockEntity enchantingTable) {
        super.applyTo(enchantingTable);
        if (!((EnchantmentTableBlockEntity)this.getSnapshot()).m_8077_()) {
            enchantingTable.m_59272_(null);
        }
    }
}

