/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.potion;

import com.google.common.base.Preconditions;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

public final class PotionData {
    private final PotionType type;
    private final boolean extended;
    private final boolean upgraded;

    public PotionData(@NotNull PotionType type, boolean extended, boolean upgraded) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Potion Type must not be null");
        Preconditions.checkArgument((!upgraded || type.isUpgradeable() ? 1 : 0) != 0, (Object)"Potion Type is not upgradable");
        Preconditions.checkArgument((!extended || type.isExtendable() ? 1 : 0) != 0, (Object)"Potion Type is not extendable");
        Preconditions.checkArgument((!upgraded || !extended ? 1 : 0) != 0, (Object)"Potion cannot be both extended and upgraded");
        this.type = type;
        this.extended = extended;
        this.upgraded = upgraded;
    }

    public PotionData(@NotNull PotionType type) {
        this(type, false, false);
    }

    @NotNull
    public PotionType getType() {
        return this.type;
    }

    public boolean isUpgraded() {
        return this.upgraded;
    }

    public boolean isExtended() {
        return this.extended;
    }

    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 23 * hash + (this.extended ? 1 : 0);
        hash = 23 * hash + (this.upgraded ? 1 : 0);
        return hash;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        PotionData other = (PotionData)obj;
        return this.upgraded == other.upgraded && this.extended == other.extended && this.type == other.type;
    }
}

