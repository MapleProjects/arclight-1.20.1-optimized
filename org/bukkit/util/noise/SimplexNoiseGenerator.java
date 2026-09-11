/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util.noise;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.util.noise.PerlinNoiseGenerator;
import org.jetbrains.annotations.NotNull;

public class SimplexNoiseGenerator
extends PerlinNoiseGenerator {
    protected static final double SQRT_3 = Math.sqrt(3.0);
    protected static final double SQRT_5 = Math.sqrt(5.0);
    protected static final double F2 = 0.5 * (SQRT_3 - 1.0);
    protected static final double G2 = (3.0 - SQRT_3) / 6.0;
    protected static final double G22 = G2 * 2.0 - 1.0;
    protected static final double F3 = 0.3333333333333333;
    protected static final double G3 = 0.16666666666666666;
    protected static final double F4 = (SQRT_5 - 1.0) / 4.0;
    protected static final double G4 = (5.0 - SQRT_5) / 20.0;
    protected static final double G42 = G4 * 2.0;
    protected static final double G43 = G4 * 3.0;
    protected static final double G44 = G4 * 4.0 - 1.0;
    protected static final int[][] grad4;
    protected static final int[][] simplex;
    protected double offsetW;
    private static final SimplexNoiseGenerator instance;

    static {
        int[][] nArrayArray = new int[32][];
        int[] nArray = new int[4];
        nArray[1] = 1;
        nArray[2] = 1;
        nArray[3] = 1;
        nArrayArray[0] = nArray;
        int[] nArray2 = new int[4];
        nArray2[1] = 1;
        nArray2[2] = 1;
        nArray2[3] = -1;
        nArrayArray[1] = nArray2;
        int[] nArray3 = new int[4];
        nArray3[1] = 1;
        nArray3[2] = -1;
        nArray3[3] = 1;
        nArrayArray[2] = nArray3;
        int[] nArray4 = new int[4];
        nArray4[1] = 1;
        nArray4[2] = -1;
        nArray4[3] = -1;
        nArrayArray[3] = nArray4;
        int[] nArray5 = new int[4];
        nArray5[1] = -1;
        nArray5[2] = 1;
        nArray5[3] = 1;
        nArrayArray[4] = nArray5;
        int[] nArray6 = new int[4];
        nArray6[1] = -1;
        nArray6[2] = 1;
        nArray6[3] = -1;
        nArrayArray[5] = nArray6;
        int[] nArray7 = new int[4];
        nArray7[1] = -1;
        nArray7[2] = -1;
        nArray7[3] = 1;
        nArrayArray[6] = nArray7;
        int[] nArray8 = new int[4];
        nArray8[1] = -1;
        nArray8[2] = -1;
        nArray8[3] = -1;
        nArrayArray[7] = nArray8;
        int[] nArray9 = new int[4];
        nArray9[0] = 1;
        nArray9[2] = 1;
        nArray9[3] = 1;
        nArrayArray[8] = nArray9;
        int[] nArray10 = new int[4];
        nArray10[0] = 1;
        nArray10[2] = 1;
        nArray10[3] = -1;
        nArrayArray[9] = nArray10;
        int[] nArray11 = new int[4];
        nArray11[0] = 1;
        nArray11[2] = -1;
        nArray11[3] = 1;
        nArrayArray[10] = nArray11;
        int[] nArray12 = new int[4];
        nArray12[0] = 1;
        nArray12[2] = -1;
        nArray12[3] = -1;
        nArrayArray[11] = nArray12;
        int[] nArray13 = new int[4];
        nArray13[0] = -1;
        nArray13[2] = 1;
        nArray13[3] = 1;
        nArrayArray[12] = nArray13;
        int[] nArray14 = new int[4];
        nArray14[0] = -1;
        nArray14[2] = 1;
        nArray14[3] = -1;
        nArrayArray[13] = nArray14;
        int[] nArray15 = new int[4];
        nArray15[0] = -1;
        nArray15[2] = -1;
        nArray15[3] = 1;
        nArrayArray[14] = nArray15;
        int[] nArray16 = new int[4];
        nArray16[0] = -1;
        nArray16[2] = -1;
        nArray16[3] = -1;
        nArrayArray[15] = nArray16;
        int[] nArray17 = new int[4];
        nArray17[0] = 1;
        nArray17[1] = 1;
        nArray17[3] = 1;
        nArrayArray[16] = nArray17;
        int[] nArray18 = new int[4];
        nArray18[0] = 1;
        nArray18[1] = 1;
        nArray18[3] = -1;
        nArrayArray[17] = nArray18;
        int[] nArray19 = new int[4];
        nArray19[0] = 1;
        nArray19[1] = -1;
        nArray19[3] = 1;
        nArrayArray[18] = nArray19;
        int[] nArray20 = new int[4];
        nArray20[0] = 1;
        nArray20[1] = -1;
        nArray20[3] = -1;
        nArrayArray[19] = nArray20;
        int[] nArray21 = new int[4];
        nArray21[0] = -1;
        nArray21[1] = 1;
        nArray21[3] = 1;
        nArrayArray[20] = nArray21;
        int[] nArray22 = new int[4];
        nArray22[0] = -1;
        nArray22[1] = 1;
        nArray22[3] = -1;
        nArrayArray[21] = nArray22;
        int[] nArray23 = new int[4];
        nArray23[0] = -1;
        nArray23[1] = -1;
        nArray23[3] = 1;
        nArrayArray[22] = nArray23;
        int[] nArray24 = new int[4];
        nArray24[0] = -1;
        nArray24[1] = -1;
        nArray24[3] = -1;
        nArrayArray[23] = nArray24;
        int[] nArray25 = new int[4];
        nArray25[0] = 1;
        nArray25[1] = 1;
        nArray25[2] = 1;
        nArrayArray[24] = nArray25;
        int[] nArray26 = new int[4];
        nArray26[0] = 1;
        nArray26[1] = 1;
        nArray26[2] = -1;
        nArrayArray[25] = nArray26;
        int[] nArray27 = new int[4];
        nArray27[0] = 1;
        nArray27[1] = -1;
        nArray27[2] = 1;
        nArrayArray[26] = nArray27;
        int[] nArray28 = new int[4];
        nArray28[0] = 1;
        nArray28[1] = -1;
        nArray28[2] = -1;
        nArrayArray[27] = nArray28;
        int[] nArray29 = new int[4];
        nArray29[0] = -1;
        nArray29[1] = 1;
        nArray29[2] = 1;
        nArrayArray[28] = nArray29;
        int[] nArray30 = new int[4];
        nArray30[0] = -1;
        nArray30[1] = 1;
        nArray30[2] = -1;
        nArrayArray[29] = nArray30;
        int[] nArray31 = new int[4];
        nArray31[0] = -1;
        nArray31[1] = -1;
        nArray31[2] = 1;
        nArrayArray[30] = nArray31;
        int[] nArray32 = new int[4];
        nArray32[0] = -1;
        nArray32[1] = -1;
        nArray32[2] = -1;
        nArrayArray[31] = nArray32;
        grad4 = nArrayArray;
        int[][] nArrayArray2 = new int[64][];
        int[] nArray33 = new int[4];
        nArray33[1] = 1;
        nArray33[2] = 2;
        nArray33[3] = 3;
        nArrayArray2[0] = nArray33;
        int[] nArray34 = new int[4];
        nArray34[1] = 1;
        nArray34[2] = 3;
        nArray34[3] = 2;
        nArrayArray2[1] = nArray34;
        nArrayArray2[2] = new int[4];
        int[] nArray35 = new int[4];
        nArray35[1] = 2;
        nArray35[2] = 3;
        nArray35[3] = 1;
        nArrayArray2[3] = nArray35;
        nArrayArray2[4] = new int[4];
        nArrayArray2[5] = new int[4];
        nArrayArray2[6] = new int[4];
        int[] nArray36 = new int[4];
        nArray36[0] = 1;
        nArray36[1] = 2;
        nArray36[2] = 3;
        nArrayArray2[7] = nArray36;
        int[] nArray37 = new int[4];
        nArray37[1] = 2;
        nArray37[2] = 1;
        nArray37[3] = 3;
        nArrayArray2[8] = nArray37;
        nArrayArray2[9] = new int[4];
        int[] nArray38 = new int[4];
        nArray38[1] = 3;
        nArray38[2] = 1;
        nArray38[3] = 2;
        nArrayArray2[10] = nArray38;
        int[] nArray39 = new int[4];
        nArray39[1] = 3;
        nArray39[2] = 2;
        nArray39[3] = 1;
        nArrayArray2[11] = nArray39;
        nArrayArray2[12] = new int[4];
        nArrayArray2[13] = new int[4];
        nArrayArray2[14] = new int[4];
        int[] nArray40 = new int[4];
        nArray40[0] = 1;
        nArray40[1] = 3;
        nArray40[2] = 2;
        nArrayArray2[15] = nArray40;
        nArrayArray2[16] = new int[4];
        nArrayArray2[17] = new int[4];
        nArrayArray2[18] = new int[4];
        nArrayArray2[19] = new int[4];
        nArrayArray2[20] = new int[4];
        nArrayArray2[21] = new int[4];
        nArrayArray2[22] = new int[4];
        nArrayArray2[23] = new int[4];
        int[] nArray41 = new int[4];
        nArray41[0] = 1;
        nArray41[1] = 2;
        nArray41[3] = 3;
        nArrayArray2[24] = nArray41;
        nArrayArray2[25] = new int[4];
        int[] nArray42 = new int[4];
        nArray42[0] = 1;
        nArray42[1] = 3;
        nArray42[3] = 2;
        nArrayArray2[26] = nArray42;
        nArrayArray2[27] = new int[4];
        nArrayArray2[28] = new int[4];
        nArrayArray2[29] = new int[4];
        int[] nArray43 = new int[4];
        nArray43[0] = 2;
        nArray43[1] = 3;
        nArray43[3] = 1;
        nArrayArray2[30] = nArray43;
        int[] nArray44 = new int[4];
        nArray44[0] = 2;
        nArray44[1] = 3;
        nArray44[2] = 1;
        nArrayArray2[31] = nArray44;
        int[] nArray45 = new int[4];
        nArray45[0] = 1;
        nArray45[2] = 2;
        nArray45[3] = 3;
        nArrayArray2[32] = nArray45;
        int[] nArray46 = new int[4];
        nArray46[0] = 1;
        nArray46[2] = 3;
        nArray46[3] = 2;
        nArrayArray2[33] = nArray46;
        nArrayArray2[34] = new int[4];
        nArrayArray2[35] = new int[4];
        nArrayArray2[36] = new int[4];
        int[] nArray47 = new int[4];
        nArray47[0] = 2;
        nArray47[2] = 3;
        nArray47[3] = 1;
        nArrayArray2[37] = nArray47;
        nArrayArray2[38] = new int[4];
        int[] nArray48 = new int[4];
        nArray48[0] = 2;
        nArray48[1] = 1;
        nArray48[2] = 3;
        nArrayArray2[39] = nArray48;
        nArrayArray2[40] = new int[4];
        nArrayArray2[41] = new int[4];
        nArrayArray2[42] = new int[4];
        nArrayArray2[43] = new int[4];
        nArrayArray2[44] = new int[4];
        nArrayArray2[45] = new int[4];
        nArrayArray2[46] = new int[4];
        nArrayArray2[47] = new int[4];
        int[] nArray49 = new int[4];
        nArray49[0] = 2;
        nArray49[2] = 1;
        nArray49[3] = 3;
        nArrayArray2[48] = nArray49;
        nArrayArray2[49] = new int[4];
        nArrayArray2[50] = new int[4];
        nArrayArray2[51] = new int[4];
        int[] nArray50 = new int[4];
        nArray50[0] = 3;
        nArray50[2] = 1;
        nArray50[3] = 2;
        nArrayArray2[52] = nArray50;
        int[] nArray51 = new int[4];
        nArray51[0] = 3;
        nArray51[2] = 2;
        nArray51[3] = 1;
        nArrayArray2[53] = nArray51;
        nArrayArray2[54] = new int[4];
        int[] nArray52 = new int[4];
        nArray52[0] = 3;
        nArray52[1] = 1;
        nArray52[2] = 2;
        nArrayArray2[55] = nArray52;
        int[] nArray53 = new int[4];
        nArray53[0] = 2;
        nArray53[1] = 1;
        nArray53[3] = 3;
        nArrayArray2[56] = nArray53;
        nArrayArray2[57] = new int[4];
        nArrayArray2[58] = new int[4];
        nArrayArray2[59] = new int[4];
        int[] nArray54 = new int[4];
        nArray54[0] = 3;
        nArray54[1] = 1;
        nArray54[3] = 2;
        nArrayArray2[60] = nArray54;
        nArrayArray2[61] = new int[4];
        int[] nArray55 = new int[4];
        nArray55[0] = 3;
        nArray55[1] = 2;
        nArray55[3] = 1;
        nArrayArray2[62] = nArray55;
        int[] nArray56 = new int[4];
        nArray56[0] = 3;
        nArray56[1] = 2;
        nArray56[2] = 1;
        nArrayArray2[63] = nArray56;
        simplex = nArrayArray2;
        instance = new SimplexNoiseGenerator();
    }

    protected SimplexNoiseGenerator() {
    }

    public SimplexNoiseGenerator(@NotNull World world) {
        this(new Random(world.getSeed()));
    }

    public SimplexNoiseGenerator(long seed) {
        this(new Random(seed));
    }

    public SimplexNoiseGenerator(@NotNull Random rand) {
        super(rand);
        this.offsetW = rand.nextDouble() * 256.0;
    }

    protected static double dot(@NotNull int[] g, double x, double y) {
        return (double)g[0] * x + (double)g[1] * y;
    }

    protected static double dot(@NotNull int[] g, double x, double y, double z) {
        return (double)g[0] * x + (double)g[1] * y + (double)g[2] * z;
    }

    protected static double dot(@NotNull int[] g, double x, double y, double z, double w) {
        return (double)g[0] * x + (double)g[1] * y + (double)g[2] * z + (double)g[3] * w;
    }

    public static double getNoise(double xin) {
        return instance.noise(xin);
    }

    public static double getNoise(double xin, double yin) {
        return instance.noise(xin, yin);
    }

    public static double getNoise(double xin, double yin, double zin) {
        return instance.noise(xin, yin, zin);
    }

    public static double getNoise(double x, double y, double z, double w) {
        return instance.noise(x, y, z, w);
    }

    @Override
    public double noise(double xin, double yin, double zin) {
        double n3;
        double n2;
        double n1;
        double n0;
        int k2;
        int j2;
        int i2;
        int k1;
        int j1;
        int i1;
        double s = ((xin += this.offsetX) + (yin += this.offsetY) + (zin += this.offsetZ)) * 0.3333333333333333;
        int i = SimplexNoiseGenerator.floor(xin + s);
        int j = SimplexNoiseGenerator.floor(yin + s);
        int k = SimplexNoiseGenerator.floor(zin + s);
        double t = (double)(i + j + k) * 0.16666666666666666;
        double X0 = (double)i - t;
        double Y0 = (double)j - t;
        double Z0 = (double)k - t;
        double x0 = xin - X0;
        double y0 = yin - Y0;
        double z0 = zin - Z0;
        if (x0 >= y0) {
            if (y0 >= z0) {
                i1 = 1;
                j1 = 0;
                k1 = 0;
                i2 = 1;
                j2 = 1;
                k2 = 0;
            } else if (x0 >= z0) {
                i1 = 1;
                j1 = 0;
                k1 = 0;
                i2 = 1;
                j2 = 0;
                k2 = 1;
            } else {
                i1 = 0;
                j1 = 0;
                k1 = 1;
                i2 = 1;
                j2 = 0;
                k2 = 1;
            }
        } else if (y0 < z0) {
            i1 = 0;
            j1 = 0;
            k1 = 1;
            i2 = 0;
            j2 = 1;
            k2 = 1;
        } else if (x0 < z0) {
            i1 = 0;
            j1 = 1;
            k1 = 0;
            i2 = 0;
            j2 = 1;
            k2 = 1;
        } else {
            i1 = 0;
            j1 = 1;
            k1 = 0;
            i2 = 1;
            j2 = 1;
            k2 = 0;
        }
        double x1 = x0 - (double)i1 + 0.16666666666666666;
        double y1 = y0 - (double)j1 + 0.16666666666666666;
        double z1 = z0 - (double)k1 + 0.16666666666666666;
        double x2 = x0 - (double)i2 + 0.3333333333333333;
        double y2 = y0 - (double)j2 + 0.3333333333333333;
        double z2 = z0 - (double)k2 + 0.3333333333333333;
        double x3 = x0 - 1.0 + 0.5;
        double y3 = y0 - 1.0 + 0.5;
        double z3 = z0 - 1.0 + 0.5;
        int ii = i & 0xFF;
        int jj = j & 0xFF;
        int kk = k & 0xFF;
        int gi0 = this.perm[ii + this.perm[jj + this.perm[kk]]] % 12;
        int gi1 = this.perm[ii + i1 + this.perm[jj + j1 + this.perm[kk + k1]]] % 12;
        int gi2 = this.perm[ii + i2 + this.perm[jj + j2 + this.perm[kk + k2]]] % 12;
        int gi3 = this.perm[ii + 1 + this.perm[jj + 1 + this.perm[kk + 1]]] % 12;
        double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0;
        if (t0 < 0.0) {
            n0 = 0.0;
        } else {
            t0 *= t0;
            n0 = t0 * t0 * SimplexNoiseGenerator.dot(grad3[gi0], x0, y0, z0);
        }
        double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1;
        if (t1 < 0.0) {
            n1 = 0.0;
        } else {
            t1 *= t1;
            n1 = t1 * t1 * SimplexNoiseGenerator.dot(grad3[gi1], x1, y1, z1);
        }
        double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2;
        if (t2 < 0.0) {
            n2 = 0.0;
        } else {
            t2 *= t2;
            n2 = t2 * t2 * SimplexNoiseGenerator.dot(grad3[gi2], x2, y2, z2);
        }
        double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3;
        if (t3 < 0.0) {
            n3 = 0.0;
        } else {
            t3 *= t3;
            n3 = t3 * t3 * SimplexNoiseGenerator.dot(grad3[gi3], x3, y3, z3);
        }
        return 32.0 * (n0 + n1 + n2 + n3);
    }

    @Override
    public double noise(double xin, double yin) {
        double n2;
        double n1;
        double n0;
        int j1;
        int i1;
        double Y0;
        double y0;
        int j;
        double t;
        double s;
        int i;
        double X0;
        double x0;
        if ((x0 = (xin += this.offsetX) - (X0 = (double)(i = SimplexNoiseGenerator.floor(xin + (s = (xin + (yin += this.offsetY)) * F2))) - (t = (double)(i + (j = SimplexNoiseGenerator.floor(yin + s))) * G2))) > (y0 = yin - (Y0 = (double)j - t))) {
            i1 = 1;
            j1 = 0;
        } else {
            i1 = 0;
            j1 = 1;
        }
        double x1 = x0 - (double)i1 + G2;
        double y1 = y0 - (double)j1 + G2;
        double x2 = x0 + G22;
        double y2 = y0 + G22;
        int ii = i & 0xFF;
        int jj = j & 0xFF;
        int gi0 = this.perm[ii + this.perm[jj]] % 12;
        int gi1 = this.perm[ii + i1 + this.perm[jj + j1]] % 12;
        int gi2 = this.perm[ii + 1 + this.perm[jj + 1]] % 12;
        double t0 = 0.5 - x0 * x0 - y0 * y0;
        if (t0 < 0.0) {
            n0 = 0.0;
        } else {
            t0 *= t0;
            n0 = t0 * t0 * SimplexNoiseGenerator.dot(grad3[gi0], x0, y0);
        }
        double t1 = 0.5 - x1 * x1 - y1 * y1;
        if (t1 < 0.0) {
            n1 = 0.0;
        } else {
            t1 *= t1;
            n1 = t1 * t1 * SimplexNoiseGenerator.dot(grad3[gi1], x1, y1);
        }
        double t2 = 0.5 - x2 * x2 - y2 * y2;
        if (t2 < 0.0) {
            n2 = 0.0;
        } else {
            t2 *= t2;
            n2 = t2 * t2 * SimplexNoiseGenerator.dot(grad3[gi2], x2, y2);
        }
        return 70.0 * (n0 + n1 + n2);
    }

    public double noise(double x, double y, double z, double w) {
        double n4;
        double n3;
        double n2;
        double n1;
        double n0;
        double s = ((x += this.offsetX) + (y += this.offsetY) + (z += this.offsetZ) + (w += this.offsetW)) * F4;
        int i = SimplexNoiseGenerator.floor(x + s);
        int j = SimplexNoiseGenerator.floor(y + s);
        int k = SimplexNoiseGenerator.floor(z + s);
        int l = SimplexNoiseGenerator.floor(w + s);
        double t = (double)(i + j + k + l) * G4;
        double X0 = (double)i - t;
        double Y0 = (double)j - t;
        double Z0 = (double)k - t;
        double W0 = (double)l - t;
        double x0 = x - X0;
        double y0 = y - Y0;
        double z0 = z - Z0;
        double w0 = w - W0;
        int c1 = x0 > y0 ? 32 : 0;
        int c2 = x0 > z0 ? 16 : 0;
        int c3 = y0 > z0 ? 8 : 0;
        int c4 = x0 > w0 ? 4 : 0;
        int c5 = y0 > w0 ? 2 : 0;
        int c6 = z0 > w0 ? 1 : 0;
        int c = c1 + c2 + c3 + c4 + c5 + c6;
        int i1 = simplex[c][0] >= 3 ? 1 : 0;
        int j1 = simplex[c][1] >= 3 ? 1 : 0;
        int k1 = simplex[c][2] >= 3 ? 1 : 0;
        int l1 = simplex[c][3] >= 3 ? 1 : 0;
        int i2 = simplex[c][0] >= 2 ? 1 : 0;
        int j2 = simplex[c][1] >= 2 ? 1 : 0;
        int k2 = simplex[c][2] >= 2 ? 1 : 0;
        int l2 = simplex[c][3] >= 2 ? 1 : 0;
        int i3 = simplex[c][0] >= 1 ? 1 : 0;
        int j3 = simplex[c][1] >= 1 ? 1 : 0;
        int k3 = simplex[c][2] >= 1 ? 1 : 0;
        int l3 = simplex[c][3] >= 1 ? 1 : 0;
        double x1 = x0 - (double)i1 + G4;
        double y1 = y0 - (double)j1 + G4;
        double z1 = z0 - (double)k1 + G4;
        double w1 = w0 - (double)l1 + G4;
        double x2 = x0 - (double)i2 + G42;
        double y2 = y0 - (double)j2 + G42;
        double z2 = z0 - (double)k2 + G42;
        double w2 = w0 - (double)l2 + G42;
        double x3 = x0 - (double)i3 + G43;
        double y3 = y0 - (double)j3 + G43;
        double z3 = z0 - (double)k3 + G43;
        double w3 = w0 - (double)l3 + G43;
        double x4 = x0 + G44;
        double y4 = y0 + G44;
        double z4 = z0 + G44;
        double w4 = w0 + G44;
        int ii = i & 0xFF;
        int jj = j & 0xFF;
        int kk = k & 0xFF;
        int ll = l & 0xFF;
        int gi0 = this.perm[ii + this.perm[jj + this.perm[kk + this.perm[ll]]]] % 32;
        int gi1 = this.perm[ii + i1 + this.perm[jj + j1 + this.perm[kk + k1 + this.perm[ll + l1]]]] % 32;
        int gi2 = this.perm[ii + i2 + this.perm[jj + j2 + this.perm[kk + k2 + this.perm[ll + l2]]]] % 32;
        int gi3 = this.perm[ii + i3 + this.perm[jj + j3 + this.perm[kk + k3 + this.perm[ll + l3]]]] % 32;
        int gi4 = this.perm[ii + 1 + this.perm[jj + 1 + this.perm[kk + 1 + this.perm[ll + 1]]]] % 32;
        double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0;
        if (t0 < 0.0) {
            n0 = 0.0;
        } else {
            t0 *= t0;
            n0 = t0 * t0 * SimplexNoiseGenerator.dot(grad4[gi0], x0, y0, z0, w0);
        }
        double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1;
        if (t1 < 0.0) {
            n1 = 0.0;
        } else {
            t1 *= t1;
            n1 = t1 * t1 * SimplexNoiseGenerator.dot(grad4[gi1], x1, y1, z1, w1);
        }
        double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2;
        if (t2 < 0.0) {
            n2 = 0.0;
        } else {
            t2 *= t2;
            n2 = t2 * t2 * SimplexNoiseGenerator.dot(grad4[gi2], x2, y2, z2, w2);
        }
        double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3;
        if (t3 < 0.0) {
            n3 = 0.0;
        } else {
            t3 *= t3;
            n3 = t3 * t3 * SimplexNoiseGenerator.dot(grad4[gi3], x3, y3, z3, w3);
        }
        double t4 = 0.6 - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4;
        if (t4 < 0.0) {
            n4 = 0.0;
        } else {
            t4 *= t4;
            n4 = t4 * t4 * SimplexNoiseGenerator.dot(grad4[gi4], x4, y4, z4, w4);
        }
        return 27.0 * (n0 + n1 + n2 + n3 + n4);
    }

    @NotNull
    public static SimplexNoiseGenerator getInstance() {
        return instance;
    }
}

