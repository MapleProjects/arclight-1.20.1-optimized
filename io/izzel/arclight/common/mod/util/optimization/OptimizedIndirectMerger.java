/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.doubles.DoubleArrayList
 *  it.unimi.dsi.fastutil.doubles.DoubleList
 *  net.minecraft.world.phys.shapes.IndexMerger
 *  net.minecraft.world.phys.shapes.IndexMerger$IndexConsumer
 *  org.jetbrains.annotations.NotNull
 */
package io.izzel.arclight.common.mod.util.optimization;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.world.phys.shapes.IndexMerger;
import org.jetbrains.annotations.NotNull;

public class OptimizedIndirectMerger
implements IndexMerger {
    private final double[] merged;
    private final int[] indicesFirst;
    private final int[] indicesSecond;
    private final DoubleArrayList pairs;

    public OptimizedIndirectMerger(DoubleList aPoints, DoubleList bPoints, boolean flag1, boolean flag2) {
        double[] braw;
        double[] araw;
        if (aPoints instanceof DoubleArrayList) {
            araw = ((DoubleArrayList)aPoints).elements();
        } else {
            araw = new double[aPoints.size()];
            for (int i = 0; i < araw.length; ++i) {
                araw[i] = aPoints.getDouble(i);
            }
        }
        if (bPoints instanceof DoubleArrayList) {
            braw = ((DoubleArrayList)bPoints).elements();
        } else {
            braw = new double[bPoints.size()];
            for (int i = 0; i < braw.length; ++i) {
                braw[i] = bPoints.getDouble(i);
            }
        }
        int size = araw.length + braw.length;
        this.merged = new double[size];
        this.indicesFirst = new int[size];
        this.indicesSecond = new int[size];
        this.pairs = DoubleArrayList.wrap((double[])this.merged);
        this.merge(araw, braw, araw.length, braw.length, flag1, flag2);
    }

    private void merge(double[] aPoints, double[] bPoints, int aSize, int bSize, boolean flag1, boolean flag2) {
        int aIdx = 0;
        int bIdx = 0;
        double prev = 0.0;
        int a1 = 0;
        int a2 = 0;
        while (true) {
            boolean bWithinBounds;
            boolean aWithinBounds = aIdx < aSize;
            boolean bl = bWithinBounds = bIdx < bSize;
            if (!aWithinBounds && !bWithinBounds) break;
            boolean flip = aWithinBounds && (!bWithinBounds || aPoints[aIdx] < bPoints[bIdx] + 1.0E-7);
            double value = flip ? aPoints[aIdx++] : bPoints[bIdx++];
            if ((aIdx == 0 || !aWithinBounds) && !flip && !flag2 || (bIdx == 0 || !bWithinBounds) && flip && !flag1) continue;
            if (a2 == 0 || prev < value - 1.0E-7) {
                this.indicesFirst[a1] = aIdx - 1;
                this.indicesSecond[a1] = bIdx - 1;
                this.merged[a2] = value;
                ++a1;
                ++a2;
                prev = value;
                continue;
            }
            if (a2 <= 0) continue;
            this.indicesFirst[a1 - 1] = aIdx - 1;
            this.indicesSecond[a1 - 1] = bIdx - 1;
        }
        if (a2 == 0) {
            this.merged[a2++] = Math.min(aPoints[aSize - 1], bPoints[bSize - 1]);
        }
        this.pairs.size(a2);
    }

    @NotNull
    public DoubleList m_6241_() {
        return this.pairs;
    }

    public boolean m_6200_(@NotNull IndexMerger.IndexConsumer consumer) {
        int l = this.pairs.size() - 1;
        for (int i = 0; i < l; ++i) {
            if (consumer.m_82908_(this.indicesFirst[i], this.indicesSecond[i], i)) continue;
            return false;
        }
        return true;
    }

    public int size() {
        return this.pairs.size();
    }
}

