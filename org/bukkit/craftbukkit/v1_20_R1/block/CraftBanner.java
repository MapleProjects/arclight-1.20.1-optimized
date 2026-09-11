/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.world.item.DyeColor
 *  net.minecraft.world.level.block.AbstractBannerBlock
 *  net.minecraft.world.level.block.entity.BannerBlockEntity
 */
package org.bukkit.craftbukkit.v1_20_R1.block;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.block.AbstractBannerBlock;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import org.bukkit.DyeColor;
import org.bukkit.World;
import org.bukkit.block.Banner;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlockEntityState;

public class CraftBanner
extends CraftBlockEntityState<BannerBlockEntity>
implements Banner {
    private DyeColor base;
    private List<Pattern> patterns;

    public CraftBanner(World world, BannerBlockEntity tileEntity) {
        super(world, tileEntity);
    }

    @Override
    public void load(BannerBlockEntity banner) {
        super.load(banner);
        this.base = DyeColor.getByWoolData((byte)((AbstractBannerBlock)this.data.m_60734_()).m_48674_().m_41060_());
        this.patterns = new ArrayList<Pattern>();
        if (banner.f_58475_ != null) {
            int i = 0;
            while (i < banner.f_58475_.size()) {
                CompoundTag p = (CompoundTag)banner.f_58475_.get(i);
                this.patterns.add(new Pattern(DyeColor.getByWoolData((byte)p.m_128451_("Color")), PatternType.getByIdentifier(p.m_128461_("Pattern"))));
                ++i;
            }
        }
    }

    @Override
    public DyeColor getBaseColor() {
        return this.base;
    }

    @Override
    public void setBaseColor(DyeColor color) {
        Preconditions.checkArgument((color != null ? 1 : 0) != 0, (Object)"color");
        this.base = color;
    }

    @Override
    public List<Pattern> getPatterns() {
        return new ArrayList<Pattern>(this.patterns);
    }

    @Override
    public void setPatterns(List<Pattern> patterns) {
        this.patterns = new ArrayList<Pattern>(patterns);
    }

    @Override
    public void addPattern(Pattern pattern) {
        this.patterns.add(pattern);
    }

    @Override
    public Pattern getPattern(int i) {
        return this.patterns.get(i);
    }

    @Override
    public Pattern removePattern(int i) {
        return this.patterns.remove(i);
    }

    @Override
    public void setPattern(int i, Pattern pattern) {
        this.patterns.set(i, pattern);
    }

    @Override
    public int numberOfPatterns() {
        return this.patterns.size();
    }

    @Override
    public void applyTo(BannerBlockEntity banner) {
        super.applyTo(banner);
        banner.f_58474_ = net.minecraft.world.item.DyeColor.m_41053_((int)this.base.getWoolData());
        ListTag newPatterns = new ListTag();
        for (Pattern p : this.patterns) {
            CompoundTag compound = new CompoundTag();
            compound.m_128405_("Color", (int)p.getColor().getWoolData());
            compound.m_128359_("Pattern", p.getPattern().getIdentifier());
            newPatterns.add((Object)compound);
        }
        banner.f_58475_ = newPatterns;
    }
}

