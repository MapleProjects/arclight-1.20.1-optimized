/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.util.noise;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.util.noise.NoiseGenerator;
import org.jetbrains.annotations.NotNull;

public class PerlinNoiseGenerator
extends NoiseGenerator {
    protected static final int[][] grad3;
    private static final PerlinNoiseGenerator instance;

    static {
        int[][] nArrayArray = new int[12][];
        int[] nArray = new int[3];
        nArray[0] = 1;
        nArray[1] = 1;
        nArrayArray[0] = nArray;
        int[] nArray2 = new int[3];
        nArray2[0] = -1;
        nArray2[1] = 1;
        nArrayArray[1] = nArray2;
        int[] nArray3 = new int[3];
        nArray3[0] = 1;
        nArray3[1] = -1;
        nArrayArray[2] = nArray3;
        int[] nArray4 = new int[3];
        nArray4[0] = -1;
        nArray4[1] = -1;
        nArrayArray[3] = nArray4;
        int[] nArray5 = new int[3];
        nArray5[0] = 1;
        nArray5[2] = 1;
        nArrayArray[4] = nArray5;
        int[] nArray6 = new int[3];
        nArray6[0] = -1;
        nArray6[2] = 1;
        nArrayArray[5] = nArray6;
        int[] nArray7 = new int[3];
        nArray7[0] = 1;
        nArray7[2] = -1;
        nArrayArray[6] = nArray7;
        int[] nArray8 = new int[3];
        nArray8[0] = -1;
        nArray8[2] = -1;
        nArrayArray[7] = nArray8;
        int[] nArray9 = new int[3];
        nArray9[1] = 1;
        nArray9[2] = 1;
        nArrayArray[8] = nArray9;
        int[] nArray10 = new int[3];
        nArray10[1] = -1;
        nArray10[2] = 1;
        nArrayArray[9] = nArray10;
        int[] nArray11 = new int[3];
        nArray11[1] = 1;
        nArray11[2] = -1;
        nArrayArray[10] = nArray11;
        int[] nArray12 = new int[3];
        nArray12[1] = -1;
        nArray12[2] = -1;
        nArrayArray[11] = nArray12;
        grad3 = nArrayArray;
        instance = new PerlinNoiseGenerator();
    }

    protected PerlinNoiseGenerator() {
        int[] nArray = new int[256];
        nArray[0] = 151;
        nArray[1] = 160;
        nArray[2] = 137;
        nArray[3] = 91;
        nArray[4] = 90;
        nArray[5] = 15;
        nArray[6] = 131;
        nArray[7] = 13;
        nArray[8] = 201;
        nArray[9] = 95;
        nArray[10] = 96;
        nArray[11] = 53;
        nArray[12] = 194;
        nArray[13] = 233;
        nArray[14] = 7;
        nArray[15] = 225;
        nArray[16] = 140;
        nArray[17] = 36;
        nArray[18] = 103;
        nArray[19] = 30;
        nArray[20] = 69;
        nArray[21] = 142;
        nArray[22] = 8;
        nArray[23] = 99;
        nArray[24] = 37;
        nArray[25] = 240;
        nArray[26] = 21;
        nArray[27] = 10;
        nArray[28] = 23;
        nArray[29] = 190;
        nArray[30] = 6;
        nArray[31] = 148;
        nArray[32] = 247;
        nArray[33] = 120;
        nArray[34] = 234;
        nArray[35] = 75;
        nArray[37] = 26;
        nArray[38] = 197;
        nArray[39] = 62;
        nArray[40] = 94;
        nArray[41] = 252;
        nArray[42] = 219;
        nArray[43] = 203;
        nArray[44] = 117;
        nArray[45] = 35;
        nArray[46] = 11;
        nArray[47] = 32;
        nArray[48] = 57;
        nArray[49] = 177;
        nArray[50] = 33;
        nArray[51] = 88;
        nArray[52] = 237;
        nArray[53] = 149;
        nArray[54] = 56;
        nArray[55] = 87;
        nArray[56] = 174;
        nArray[57] = 20;
        nArray[58] = 125;
        nArray[59] = 136;
        nArray[60] = 171;
        nArray[61] = 168;
        nArray[62] = 68;
        nArray[63] = 175;
        nArray[64] = 74;
        nArray[65] = 165;
        nArray[66] = 71;
        nArray[67] = 134;
        nArray[68] = 139;
        nArray[69] = 48;
        nArray[70] = 27;
        nArray[71] = 166;
        nArray[72] = 77;
        nArray[73] = 146;
        nArray[74] = 158;
        nArray[75] = 231;
        nArray[76] = 83;
        nArray[77] = 111;
        nArray[78] = 229;
        nArray[79] = 122;
        nArray[80] = 60;
        nArray[81] = 211;
        nArray[82] = 133;
        nArray[83] = 230;
        nArray[84] = 220;
        nArray[85] = 105;
        nArray[86] = 92;
        nArray[87] = 41;
        nArray[88] = 55;
        nArray[89] = 46;
        nArray[90] = 245;
        nArray[91] = 40;
        nArray[92] = 244;
        nArray[93] = 102;
        nArray[94] = 143;
        nArray[95] = 54;
        nArray[96] = 65;
        nArray[97] = 25;
        nArray[98] = 63;
        nArray[99] = 161;
        nArray[100] = 1;
        nArray[101] = 216;
        nArray[102] = 80;
        nArray[103] = 73;
        nArray[104] = 209;
        nArray[105] = 76;
        nArray[106] = 132;
        nArray[107] = 187;
        nArray[108] = 208;
        nArray[109] = 89;
        nArray[110] = 18;
        nArray[111] = 169;
        nArray[112] = 200;
        nArray[113] = 196;
        nArray[114] = 135;
        nArray[115] = 130;
        nArray[116] = 116;
        nArray[117] = 188;
        nArray[118] = 159;
        nArray[119] = 86;
        nArray[120] = 164;
        nArray[121] = 100;
        nArray[122] = 109;
        nArray[123] = 198;
        nArray[124] = 173;
        nArray[125] = 186;
        nArray[126] = 3;
        nArray[127] = 64;
        nArray[128] = 52;
        nArray[129] = 217;
        nArray[130] = 226;
        nArray[131] = 250;
        nArray[132] = 124;
        nArray[133] = 123;
        nArray[134] = 5;
        nArray[135] = 202;
        nArray[136] = 38;
        nArray[137] = 147;
        nArray[138] = 118;
        nArray[139] = 126;
        nArray[140] = 255;
        nArray[141] = 82;
        nArray[142] = 85;
        nArray[143] = 212;
        nArray[144] = 207;
        nArray[145] = 206;
        nArray[146] = 59;
        nArray[147] = 227;
        nArray[148] = 47;
        nArray[149] = 16;
        nArray[150] = 58;
        nArray[151] = 17;
        nArray[152] = 182;
        nArray[153] = 189;
        nArray[154] = 28;
        nArray[155] = 42;
        nArray[156] = 223;
        nArray[157] = 183;
        nArray[158] = 170;
        nArray[159] = 213;
        nArray[160] = 119;
        nArray[161] = 248;
        nArray[162] = 152;
        nArray[163] = 2;
        nArray[164] = 44;
        nArray[165] = 154;
        nArray[166] = 163;
        nArray[167] = 70;
        nArray[168] = 221;
        nArray[169] = 153;
        nArray[170] = 101;
        nArray[171] = 155;
        nArray[172] = 167;
        nArray[173] = 43;
        nArray[174] = 172;
        nArray[175] = 9;
        nArray[176] = 129;
        nArray[177] = 22;
        nArray[178] = 39;
        nArray[179] = 253;
        nArray[180] = 19;
        nArray[181] = 98;
        nArray[182] = 108;
        nArray[183] = 110;
        nArray[184] = 79;
        nArray[185] = 113;
        nArray[186] = 224;
        nArray[187] = 232;
        nArray[188] = 178;
        nArray[189] = 185;
        nArray[190] = 112;
        nArray[191] = 104;
        nArray[192] = 218;
        nArray[193] = 246;
        nArray[194] = 97;
        nArray[195] = 228;
        nArray[196] = 251;
        nArray[197] = 34;
        nArray[198] = 242;
        nArray[199] = 193;
        nArray[200] = 238;
        nArray[201] = 210;
        nArray[202] = 144;
        nArray[203] = 12;
        nArray[204] = 191;
        nArray[205] = 179;
        nArray[206] = 162;
        nArray[207] = 241;
        nArray[208] = 81;
        nArray[209] = 51;
        nArray[210] = 145;
        nArray[211] = 235;
        nArray[212] = 249;
        nArray[213] = 14;
        nArray[214] = 239;
        nArray[215] = 107;
        nArray[216] = 49;
        nArray[217] = 192;
        nArray[218] = 214;
        nArray[219] = 31;
        nArray[220] = 181;
        nArray[221] = 199;
        nArray[222] = 106;
        nArray[223] = 157;
        nArray[224] = 184;
        nArray[225] = 84;
        nArray[226] = 204;
        nArray[227] = 176;
        nArray[228] = 115;
        nArray[229] = 121;
        nArray[230] = 50;
        nArray[231] = 45;
        nArray[232] = 127;
        nArray[233] = 4;
        nArray[234] = 150;
        nArray[235] = 254;
        nArray[236] = 138;
        nArray[237] = 236;
        nArray[238] = 205;
        nArray[239] = 93;
        nArray[240] = 222;
        nArray[241] = 114;
        nArray[242] = 67;
        nArray[243] = 29;
        nArray[244] = 24;
        nArray[245] = 72;
        nArray[246] = 243;
        nArray[247] = 141;
        nArray[248] = 128;
        nArray[249] = 195;
        nArray[250] = 78;
        nArray[251] = 66;
        nArray[252] = 215;
        nArray[253] = 61;
        nArray[254] = 156;
        nArray[255] = 180;
        int[] p = nArray;
        int i = 0;
        while (i < 512) {
            this.perm[i] = p[i & 0xFF];
            ++i;
        }
    }

    public PerlinNoiseGenerator(@NotNull World world) {
        this(new Random(world.getSeed()));
    }

    public PerlinNoiseGenerator(long seed) {
        this(new Random(seed));
    }

    public PerlinNoiseGenerator(@NotNull Random rand) {
        this.offsetX = rand.nextDouble() * 256.0;
        this.offsetY = rand.nextDouble() * 256.0;
        this.offsetZ = rand.nextDouble() * 256.0;
        int i = 0;
        while (i < 256) {
            this.perm[i] = rand.nextInt(256);
            ++i;
        }
        i = 0;
        while (i < 256) {
            int pos = rand.nextInt(256 - i) + i;
            int old = this.perm[i];
            this.perm[i] = this.perm[pos];
            this.perm[pos] = old;
            this.perm[i + 256] = this.perm[i];
            ++i;
        }
    }

    public static double getNoise(double x) {
        return instance.noise(x);
    }

    public static double getNoise(double x, double y) {
        return instance.noise(x, y);
    }

    public static double getNoise(double x, double y, double z) {
        return instance.noise(x, y, z);
    }

    @NotNull
    public static PerlinNoiseGenerator getInstance() {
        return instance;
    }

    @Override
    public double noise(double x, double y, double z) {
        int floorX = PerlinNoiseGenerator.floor(x += this.offsetX);
        int floorY = PerlinNoiseGenerator.floor(y += this.offsetY);
        int floorZ = PerlinNoiseGenerator.floor(z += this.offsetZ);
        int X = floorX & 0xFF;
        int Y = floorY & 0xFF;
        int Z = floorZ & 0xFF;
        double fX = PerlinNoiseGenerator.fade(x -= (double)floorX);
        double fY = PerlinNoiseGenerator.fade(y -= (double)floorY);
        double fZ = PerlinNoiseGenerator.fade(z -= (double)floorZ);
        int A = this.perm[X] + Y;
        int AA = this.perm[A] + Z;
        int AB = this.perm[A + 1] + Z;
        int B = this.perm[X + 1] + Y;
        int BA = this.perm[B] + Z;
        int BB = this.perm[B + 1] + Z;
        return PerlinNoiseGenerator.lerp(fZ, PerlinNoiseGenerator.lerp(fY, PerlinNoiseGenerator.lerp(fX, PerlinNoiseGenerator.grad(this.perm[AA], x, y, z), PerlinNoiseGenerator.grad(this.perm[BA], x - 1.0, y, z)), PerlinNoiseGenerator.lerp(fX, PerlinNoiseGenerator.grad(this.perm[AB], x, y - 1.0, z), PerlinNoiseGenerator.grad(this.perm[BB], x - 1.0, y - 1.0, z))), PerlinNoiseGenerator.lerp(fY, PerlinNoiseGenerator.lerp(fX, PerlinNoiseGenerator.grad(this.perm[AA + 1], x, y, z - 1.0), PerlinNoiseGenerator.grad(this.perm[BA + 1], x - 1.0, y, z - 1.0)), PerlinNoiseGenerator.lerp(fX, PerlinNoiseGenerator.grad(this.perm[AB + 1], x, y - 1.0, z - 1.0), PerlinNoiseGenerator.grad(this.perm[BB + 1], x - 1.0, y - 1.0, z - 1.0))));
    }

    public static double getNoise(double x, int octaves, double frequency, double amplitude) {
        return instance.noise(x, octaves, frequency, amplitude);
    }

    public static double getNoise(double x, double y, int octaves, double frequency, double amplitude) {
        return instance.noise(x, y, octaves, frequency, amplitude);
    }

    public static double getNoise(double x, double y, double z, int octaves, double frequency, double amplitude) {
        return instance.noise(x, y, z, octaves, frequency, amplitude);
    }
}

