/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.potion;

import com.google.common.base.Preconditions;
import java.util.Collection;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionBrewer;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class Potion {
    private boolean extended = false;
    private boolean splash = false;
    private int level = 1;
    private PotionType type;
    private static PotionBrewer brewer;
    private static final int EXTENDED_BIT = 64;
    private static final int POTION_BIT = 15;
    private static final int SPLASH_BIT = 16384;
    private static final int TIER_BIT = 32;
    private static final int TIER_SHIFT = 5;

    public Potion(@NotNull PotionType type) {
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Null PotionType");
        this.type = type;
    }

    public Potion(@NotNull PotionType type, int level) {
        this(type);
        Preconditions.checkArgument((type != null ? 1 : 0) != 0, (Object)"Type cannot be null");
        Preconditions.checkArgument((level > 0 && level < 3 ? 1 : 0) != 0, (Object)"Level must be 1 or 2");
        this.level = level;
    }

    @Deprecated
    public Potion(@NotNull PotionType type, int level, boolean splash) {
        this(type, level);
        this.splash = splash;
    }

    @Deprecated
    public Potion(@NotNull PotionType type, int level, boolean splash, boolean extended) {
        this(type, level, splash);
        this.extended = extended;
    }

    @NotNull
    public Potion splash() {
        this.setSplash(true);
        return this;
    }

    @NotNull
    public Potion extend() {
        this.setHasExtendedDuration(true);
        return this;
    }

    public void apply(@NotNull ItemStack to) {
        Preconditions.checkArgument((to != null ? 1 : 0) != 0, (Object)"itemstack cannot be null");
        Preconditions.checkArgument((boolean)to.hasItemMeta(), (Object)"given itemstack is not a potion");
        Preconditions.checkArgument((boolean)(to.getItemMeta() instanceof PotionMeta), (Object)"given itemstack is not a potion");
        PotionMeta meta = (PotionMeta)to.getItemMeta();
        meta.setBasePotionData(new PotionData(this.type, this.extended, this.level == 2));
        to.setItemMeta(meta);
    }

    public void apply(@NotNull LivingEntity to) {
        Preconditions.checkArgument((to != null ? 1 : 0) != 0, (Object)"entity cannot be null");
        to.addPotionEffects(this.getEffects());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Potion other = (Potion)obj;
        return this.extended == other.extended && this.splash == other.splash && this.level == other.level && this.type == other.type;
    }

    @NotNull
    public Collection<PotionEffect> getEffects() {
        return Potion.getBrewer().getEffects(this.type, this.level == 2, this.extended);
    }

    public int getLevel() {
        return this.level;
    }

    @NotNull
    public PotionType getType() {
        return this.type;
    }

    public boolean hasExtendedDuration() {
        return this.extended;
    }

    public int hashCode() {
        int prime = 31;
        int result = 31 + this.level;
        result = 31 * result + (this.extended ? 1231 : 1237);
        result = 31 * result + (this.splash ? 1231 : 1237);
        result = 31 * result + (this.type == null ? 0 : this.type.hashCode());
        return result;
    }

    public boolean isSplash() {
        return this.splash;
    }

    public void setHasExtendedDuration(boolean isExtended) {
        Preconditions.checkArgument((this.type == null || !this.type.isInstant() ? 1 : 0) != 0, (Object)"Instant potions cannot be extended");
        this.extended = isExtended;
    }

    public void setSplash(boolean isSplash) {
        this.splash = isSplash;
    }

    public void setType(@NotNull PotionType type) {
        this.type = type;
    }

    public void setLevel(int level) {
        Preconditions.checkArgument((this.type != null ? 1 : 0) != 0, (Object)"No-effect potions don't have a level.");
        Preconditions.checkArgument((level > 0 && level <= 2 ? 1 : 0) != 0, (Object)"Level must be between 1 and 2 for this potion");
        this.level = level;
    }

    @Deprecated
    public short toDamageValue() {
        return 0;
    }

    @NotNull
    public ItemStack toItemStack(int amount) {
        Material material = this.isSplash() ? Material.SPLASH_POTION : Material.POTION;
        ItemStack itemStack = new ItemStack(material, amount);
        PotionMeta meta = (PotionMeta)itemStack.getItemMeta();
        meta.setBasePotionData(new PotionData(this.type, this.level == 2, this.extended));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    @NotNull
    public static Potion fromDamage(int damage) {
        Potion potion;
        PotionType type;
        switch (damage & 0xF) {
            case 0: {
                type = PotionType.WATER;
                break;
            }
            case 1: {
                type = PotionType.REGEN;
                break;
            }
            case 2: {
                type = PotionType.SPEED;
                break;
            }
            case 3: {
                type = PotionType.FIRE_RESISTANCE;
                break;
            }
            case 4: {
                type = PotionType.POISON;
                break;
            }
            case 5: {
                type = PotionType.INSTANT_HEAL;
                break;
            }
            case 6: {
                type = PotionType.NIGHT_VISION;
                break;
            }
            case 8: {
                type = PotionType.WEAKNESS;
                break;
            }
            case 9: {
                type = PotionType.STRENGTH;
                break;
            }
            case 10: {
                type = PotionType.SLOWNESS;
                break;
            }
            case 11: {
                type = PotionType.JUMP;
                break;
            }
            case 12: {
                type = PotionType.INSTANT_DAMAGE;
                break;
            }
            case 13: {
                type = PotionType.WATER_BREATHING;
                break;
            }
            case 14: {
                type = PotionType.INVISIBILITY;
                break;
            }
            default: {
                type = PotionType.WATER;
            }
        }
        if (type == null || type == PotionType.WATER) {
            potion = new Potion(PotionType.WATER);
        } else {
            int level = (damage & 0x20) >> 5;
            potion = new Potion(type, ++level);
        }
        if ((damage & 0x4000) != 0) {
            potion = potion.splash();
        }
        if ((damage & 0x40) != 0) {
            potion = potion.extend();
        }
        return potion;
    }

    @NotNull
    public static Potion fromItemStack(@NotNull ItemStack item) {
        Preconditions.checkArgument((item != null ? 1 : 0) != 0, (Object)"item cannot be null");
        if (item.getType() != Material.POTION) {
            throw new IllegalArgumentException("item is not a potion");
        }
        return Potion.fromDamage(item.getDurability());
    }

    @NotNull
    public static PotionBrewer getBrewer() {
        return brewer;
    }

    public static void setPotionBrewer(@NotNull PotionBrewer other) {
        if (brewer != null) {
            throw new IllegalArgumentException("brewer can only be set internally");
        }
        brewer = other;
    }

    @Deprecated
    public int getNameId() {
        return 0;
    }
}

