/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.level.block.RedStoneOreBlock
 *  net.minecraft.world.level.block.state.BlockState
 *  net.minecraft.world.level.block.state.properties.BooleanProperty
 */
package org.bukkit.craftbukkit.v1_20_R1.block.impl;

import net.minecraft.world.level.block.RedStoneOreBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.bukkit.block.data.Lightable;
import org.bukkit.craftbukkit.v1_20_R1.block.data.CraftBlockData;

public final class CraftRedstoneOre
extends CraftBlockData
implements Lightable {
    private static final BooleanProperty LIT = CraftRedstoneOre.getBoolean(RedStoneOreBlock.class, "lit");

    public CraftRedstoneOre() {
    }

    public CraftRedstoneOre(BlockState state) {
        super(state);
    }

    @Override
    public boolean isLit() {
        return (Boolean)this.get(LIT);
    }

    @Override
    public void setLit(boolean lit) {
        this.set(LIT, lit);
    }
}

