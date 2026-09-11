/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.entity;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Sittable;
import org.jetbrains.annotations.NotNull;

public interface Panda
extends Animals,
Sittable {
    @NotNull
    public Gene getMainGene();

    public void setMainGene(@NotNull Gene var1);

    @NotNull
    public Gene getHiddenGene();

    public void setHiddenGene(@NotNull Gene var1);

    public boolean isRolling();

    public void setRolling(boolean var1);

    public boolean isSneezing();

    public void setSneezing(boolean var1);

    public boolean isOnBack();

    public void setOnBack(boolean var1);

    public boolean isEating();

    public void setEating(boolean var1);

    public boolean isScared();

    public int getUnhappyTicks();

    public static enum Gene {
        NORMAL(false),
        LAZY(false),
        WORRIED(false),
        PLAYFUL(false),
        BROWN(true),
        WEAK(true),
        AGGRESSIVE(false);

        private final boolean recessive;

        private Gene(boolean recessive) {
            this.recessive = recessive;
        }

        public boolean isRecessive() {
            return this.recessive;
        }
    }
}

