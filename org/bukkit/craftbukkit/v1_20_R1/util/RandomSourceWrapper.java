/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.RandomSource
 *  net.minecraft.world.level.levelgen.PositionalRandomFactory
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import java.util.Random;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.PositionalRandomFactory;

public final class RandomSourceWrapper
implements RandomSource {
    private final Random random;

    public RandomSourceWrapper(Random random) {
        this.random = random;
    }

    public RandomSource m_213769_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public PositionalRandomFactory m_188582_() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public synchronized void m_188584_(long seed) {
        this.random.setSeed(seed);
    }

    public int m_188502_() {
        return this.random.nextInt();
    }

    public int m_188503_(int bound) {
        return this.random.nextInt(bound);
    }

    public long m_188505_() {
        return this.random.nextLong();
    }

    public boolean m_188499_() {
        return this.random.nextBoolean();
    }

    public float m_188501_() {
        return this.random.nextFloat();
    }

    public double m_188500_() {
        return this.random.nextDouble();
    }

    public synchronized double m_188583_() {
        return this.random.nextGaussian();
    }

    public static final class RandomWrapper
    extends Random {
        private final RandomSource random;

        public RandomWrapper(RandomSource random) {
            this.random = random;
        }

        @Override
        public void setSeed(long l) {
            if (this.random != null) {
                this.random.m_188584_(l);
            }
        }

        @Override
        public int nextInt() {
            return this.random.m_188502_();
        }

        @Override
        public int nextInt(int i) {
            return this.random.m_188503_(i);
        }

        @Override
        public long nextLong() {
            return this.random.m_188505_();
        }

        @Override
        public boolean nextBoolean() {
            return this.random.m_188499_();
        }

        @Override
        public float nextFloat() {
            return this.random.m_188501_();
        }

        @Override
        public double nextDouble() {
            return this.random.m_188500_();
        }

        @Override
        public double nextGaussian() {
            return this.random.m_188583_();
        }

        @Override
        public int nextInt(int var0, int var1) {
            return this.random.m_216339_(var0, var1);
        }
    }
}

