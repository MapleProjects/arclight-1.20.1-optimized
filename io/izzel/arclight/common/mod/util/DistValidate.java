/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.izzel.arclight.i18n.ArclightConfig
 *  net.minecraft.server.level.ServerLevel
 *  net.minecraft.server.level.WorldGenRegion
 *  net.minecraft.world.item.context.UseOnContext
 *  net.minecraft.world.level.BlockGetter
 *  net.minecraft.world.level.LevelAccessor
 *  org.apache.logging.log4j.Marker
 *  org.apache.logging.log4j.MarkerManager
 */
package io.izzel.arclight.common.mod.util;

import io.izzel.arclight.common.mod.ArclightMod;
import io.izzel.arclight.i18n.ArclightConfig;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class DistValidate {
    private static final Marker MARKER = MarkerManager.getMarker((String)"EXT_LOGIC");
    private static final Map<Class<?>, Boolean> SEEN_CLASSES = new ConcurrentHashMap();

    public static boolean isValid(UseOnContext context) {
        return context != null && DistValidate.isValid((LevelAccessor)context.m_43725_());
    }

    public static boolean isValid(LevelAccessor level) {
        return level != null && !level.m_5776_() && DistValidate.isLogicWorld(level);
    }

    public static boolean isValid(BlockGetter getter) {
        LevelAccessor level;
        return getter instanceof LevelAccessor && DistValidate.isValid(level = (LevelAccessor)getter);
    }

    private static boolean isLogicWorld(LevelAccessor level) {
        Class<?> cl = level.getClass();
        return cl == ServerLevel.class || cl == WorldGenRegion.class || DistValidate.isLogicWorld(cl);
    }

    private static boolean isLogicWorld(Class<?> cl) {
        return SEEN_CLASSES.computeIfAbsent(cl, c -> {
            String name = c.getName();
            boolean result = ArclightConfig.spec().getCompat().getExtraLogicWorlds().contains(cl.getName());
            ArclightMod.LOGGER.warn(MARKER, "Level class {} treated as logic world: {}", (Object)name, (Object)result);
            return result;
        });
    }
}

