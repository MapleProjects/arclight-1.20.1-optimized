/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.entity.animal.AbstractFish
 *  net.minecraft.world.entity.animal.TropicalFish
 */
package org.bukkit.craftbukkit.v1_20_R1.entity;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.entity.animal.AbstractFish;
import org.bukkit.DyeColor;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFish;
import org.bukkit.entity.TropicalFish;

public class CraftTropicalFish
extends CraftFish
implements TropicalFish {
    public CraftTropicalFish(CraftServer server, net.minecraft.world.entity.animal.TropicalFish entity) {
        super(server, (AbstractFish)entity);
    }

    public net.minecraft.world.entity.animal.TropicalFish getHandle() {
        return (net.minecraft.world.entity.animal.TropicalFish)this.entity;
    }

    @Override
    public String toString() {
        return "CraftTropicalFish";
    }

    @Override
    public DyeColor getPatternColor() {
        return CraftTropicalFish.getPatternColor(this.getHandle().m_30042_());
    }

    @Override
    public void setPatternColor(DyeColor color) {
        this.getHandle().m_30056_(CraftTropicalFish.getData(color, this.getBodyColor(), this.getPattern()));
    }

    @Override
    public DyeColor getBodyColor() {
        return CraftTropicalFish.getBodyColor(this.getHandle().m_30042_());
    }

    @Override
    public void setBodyColor(DyeColor color) {
        this.getHandle().m_30056_(CraftTropicalFish.getData(this.getPatternColor(), color, this.getPattern()));
    }

    @Override
    public TropicalFish.Pattern getPattern() {
        return CraftTropicalFish.getPattern(this.getHandle().m_30042_());
    }

    @Override
    public void setPattern(TropicalFish.Pattern pattern) {
        this.getHandle().m_30056_(CraftTropicalFish.getData(this.getPatternColor(), this.getBodyColor(), pattern));
    }

    public static int getData(DyeColor patternColor, DyeColor bodyColor, TropicalFish.Pattern type) {
        return patternColor.getWoolData() << 24 | bodyColor.getWoolData() << 16 | CraftPattern.values()[type.ordinal()].getDataValue();
    }

    public static DyeColor getPatternColor(int data) {
        return DyeColor.getByWoolData((byte)(data >> 24 & 0xFF));
    }

    public static DyeColor getBodyColor(int data) {
        return DyeColor.getByWoolData((byte)(data >> 16 & 0xFF));
    }

    public static TropicalFish.Pattern getPattern(int data) {
        return CraftPattern.fromData(data & 0xFFFF);
    }

    public static enum CraftPattern {
        KOB(0, false),
        SUNSTREAK(1, false),
        SNOOPER(2, false),
        DASHER(3, false),
        BRINELY(4, false),
        SPOTTY(5, false),
        FLOPPER(0, true),
        STRIPEY(1, true),
        GLITTER(2, true),
        BLOCKFISH(3, true),
        BETTY(4, true),
        CLAYFISH(5, true);

        private final int variant;
        private final boolean large;
        private static final Map<Integer, TropicalFish.Pattern> BY_DATA;

        static {
            BY_DATA = new HashMap<Integer, TropicalFish.Pattern>();
            CraftPattern[] craftPatternArray = CraftPattern.values();
            int n = craftPatternArray.length;
            int n2 = 0;
            while (n2 < n) {
                CraftPattern type = craftPatternArray[n2];
                BY_DATA.put(type.getDataValue(), TropicalFish.Pattern.values()[type.ordinal()]);
                ++n2;
            }
        }

        public static TropicalFish.Pattern fromData(int data) {
            return BY_DATA.get(data);
        }

        private CraftPattern(int variant, boolean large) {
            this.variant = variant;
            this.large = large;
        }

        public int getDataValue() {
            return this.variant << 8 | (this.large ? 1 : 0);
        }
    }
}

