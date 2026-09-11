/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.world.entity.animal.AbstractGolem
 *  net.minecraft.world.entity.monster.Shulker
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import com.google.common.base.Preconditions;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.monster.Shulker;
import org.bukkit.DyeColor;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnemy;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftGolem;

public class CraftShulker
extends CraftGolem
implements org.bukkit.entity.Shulker,
CraftEnemy {
    public CraftShulker(CraftServer server, Shulker entity) {
        super(server, (AbstractGolem)entity);
    }

    @Override
    public String toString() {
        return "CraftShulker";
    }

    public Shulker getHandle() {
        return (Shulker)this.entity;
    }

    @Override
    public DyeColor getColor() {
        return DyeColor.getByWoolData((Byte)this.getHandle().m_20088_().m_135370_(Shulker.f_33393_));
    }

    @Override
    public void setColor(DyeColor color) {
        this.getHandle().m_20088_().m_135381_(Shulker.f_33393_, (Object)(color == null ? (byte)16 : (byte)color.getWoolData()));
    }

    @Override
    public float getPeek() {
        return (float)this.getHandle().m_33463_() / 100.0f;
    }

    @Override
    public void setPeek(float value) {
        Preconditions.checkArgument((value >= 0.0f && value <= 1.0f ? 1 : 0) != 0, (Object)"value needs to be in between or equal to 0 and 1");
        this.getHandle().m_33418_((int)(value * 100.0f));
    }

    @Override
    public BlockFace getAttachedFace() {
        return CraftBlock.notchToBlockFace(this.getHandle().m_33461_());
    }

    @Override
    public void setAttachedFace(BlockFace face) {
        Preconditions.checkNotNull((Object)((Object)face), (Object)"face cannot be null");
        Preconditions.checkArgument((boolean)face.isCartesian(), (String)"%s is not a valid block face to attach a shulker to, a cartesian block face is expected", (Object)((Object)face));
        this.getHandle().m_149788_(CraftBlock.blockFaceToNotch(face));
    }
}

