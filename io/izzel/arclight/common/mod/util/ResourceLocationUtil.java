/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.resources.ResourceLocation
 *  org.jetbrains.annotations.Contract
 */
package io.izzel.arclight.common.mod.util;

import com.google.common.base.Preconditions;
import java.util.Locale;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Contract;

public class ResourceLocationUtil {
    @Contract(value="null -> fail")
    public static String standardize(ResourceLocation location) {
        Preconditions.checkNotNull((Object)location, (Object)"location");
        return (location.m_135827_().equals("minecraft") ? location.m_135815_() : location.toString()).replace(':', '_').replaceAll("\\s+", "_").replaceAll("\\W", "").toUpperCase(Locale.ENGLISH);
    }

    public static String standardizeLower(ResourceLocation location) {
        return (location.m_135827_().equals("minecraft") ? location.m_135815_() : location.toString()).replace(':', '_').replaceAll("\\s+", "_").replaceAll("\\W", "").toLowerCase(Locale.ENGLISH);
    }
}

