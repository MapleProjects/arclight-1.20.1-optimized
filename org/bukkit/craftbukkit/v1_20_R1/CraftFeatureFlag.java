/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.flag.FeatureFlag
 *  net.minecraft.world.flag.FeatureFlagSet
 *  net.minecraft.world.flag.FeatureFlags
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.craftbukkit.v1_20_R1;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import org.bukkit.FeatureFlag;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.jetbrains.annotations.NotNull;

public class CraftFeatureFlag
implements FeatureFlag {
    private final NamespacedKey namespacedKey;
    private final net.minecraft.world.flag.FeatureFlag featureFlag;

    public CraftFeatureFlag(ResourceLocation minecraftKey, net.minecraft.world.flag.FeatureFlag featureFlag) {
        this.namespacedKey = CraftNamespacedKey.fromMinecraft(minecraftKey);
        this.featureFlag = featureFlag;
    }

    public net.minecraft.world.flag.FeatureFlag getHandle() {
        return this.featureFlag;
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.namespacedKey;
    }

    public String toString() {
        return "CraftDataPack{key=" + this.getKey() + ",keyUniverse=" + this.getHandle().f_243952_.toString() + "}";
    }

    public static Set<CraftFeatureFlag> getFromNMS(FeatureFlagSet featureFlagSet) {
        HashSet<CraftFeatureFlag> set = new HashSet<CraftFeatureFlag>();
        FeatureFlags.f_244280_.f_244560_.forEach((minecraftkey, featureflag) -> {
            if (featureFlagSet.m_245372_(featureflag)) {
                set.add(new CraftFeatureFlag((ResourceLocation)minecraftkey, (net.minecraft.world.flag.FeatureFlag)featureflag));
            }
        });
        return set;
    }

    public static CraftFeatureFlag getFromNMS(NamespacedKey namespacedKey) {
        return FeatureFlags.f_244280_.f_244560_.entrySet().stream().filter(entry -> CraftNamespacedKey.fromMinecraft((ResourceLocation)entry.getKey()).equals(namespacedKey)).findFirst().map(entry -> new CraftFeatureFlag((ResourceLocation)entry.getKey(), (net.minecraft.world.flag.FeatureFlag)entry.getValue())).orElse(null);
    }
}

