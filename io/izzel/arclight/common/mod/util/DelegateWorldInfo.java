/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Lifecycle
 *  net.minecraft.CrashReportCategory
 *  net.minecraft.core.BlockPos
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.Difficulty
 *  net.minecraft.world.level.GameRules
 *  net.minecraft.world.level.GameType
 *  net.minecraft.world.level.LevelHeightAccessor
 *  net.minecraft.world.level.LevelSettings
 *  net.minecraft.world.level.WorldDataConfiguration
 *  net.minecraft.world.level.border.WorldBorder$Settings
 *  net.minecraft.world.level.levelgen.WorldOptions
 *  net.minecraft.world.level.storage.PrimaryLevelData
 *  net.minecraft.world.level.storage.PrimaryLevelData$SpecialWorldProperty
 *  net.minecraft.world.level.storage.ServerLevelData
 *  net.minecraft.world.level.storage.WorldData
 *  net.minecraft.world.level.timers.TimerQueue
 *  org.jetbrains.annotations.Nullable
 */
package io.izzel.arclight.common.mod.util;

import com.mojang.serialization.Lifecycle;
import io.izzel.arclight.common.bridge.core.world.storage.DerivedWorldInfoBridge;
import io.izzel.arclight.common.bridge.core.world.storage.WorldInfoBridge;
import java.util.UUID;
import net.minecraft.CrashReportCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.storage.PrimaryLevelData;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.WorldData;
import net.minecraft.world.level.timers.TimerQueue;
import org.jetbrains.annotations.Nullable;

public class DelegateWorldInfo
extends PrimaryLevelData {
    private final ServerLevelData serverLevelData;

    public DelegateWorldInfo(LevelSettings levelSettings, WorldOptions worldOptions, PrimaryLevelData.SpecialWorldProperty specialWorldProperty, Lifecycle lifecycle, ServerLevelData serverLevelData) {
        super(levelSettings, worldOptions, specialWorldProperty, lifecycle);
        this.serverLevelData = serverLevelData;
    }

    public int m_6789_() {
        return this.serverLevelData.m_6789_();
    }

    public int m_6527_() {
        return this.serverLevelData.m_6527_();
    }

    public int m_6526_() {
        return this.serverLevelData.m_6526_();
    }

    public float m_6790_() {
        return this.serverLevelData.m_6790_();
    }

    public long m_6793_() {
        return this.serverLevelData.m_6793_();
    }

    public long m_6792_() {
        return this.serverLevelData.m_6792_();
    }

    public String m_5462_() {
        return this.serverLevelData.m_5462_();
    }

    public int m_6537_() {
        return this.serverLevelData.m_6537_();
    }

    public void m_6393_(int time) {
        this.serverLevelData.m_6393_(time);
    }

    public boolean m_6534_() {
        return this.serverLevelData.m_6534_();
    }

    public int m_6558_() {
        return this.serverLevelData.m_6558_();
    }

    public boolean m_6533_() {
        return this.serverLevelData.m_6533_();
    }

    public int m_6531_() {
        return this.serverLevelData.m_6531_();
    }

    public GameType m_5464_() {
        return this.serverLevelData.m_5464_();
    }

    public void m_6395_(int x) {
        this.serverLevelData.m_6395_(x);
    }

    public void m_6397_(int y) {
        this.serverLevelData.m_6397_(y);
    }

    public void m_6400_(int z) {
        this.serverLevelData.m_6400_(z);
    }

    public void m_7113_(float angle) {
        this.serverLevelData.m_7113_(angle);
    }

    public void m_6253_(long time) {
        this.serverLevelData.m_6253_(time);
    }

    public void m_6247_(long time) {
        this.serverLevelData.m_6247_(time);
    }

    public void m_7250_(BlockPos spawnPoint, float angle) {
        this.serverLevelData.m_7250_(spawnPoint, angle);
    }

    public void m_5557_(boolean thunderingIn) {
        this.serverLevelData.m_5557_(thunderingIn);
    }

    public void m_6398_(int time) {
        this.serverLevelData.m_6398_(time);
    }

    public void m_5565_(boolean isRaining) {
        this.serverLevelData.m_5565_(isRaining);
    }

    public void m_6399_(int time) {
        this.serverLevelData.m_6399_(time);
    }

    public void m_5458_(GameType type) {
        this.serverLevelData.m_5458_(type);
    }

    public boolean m_5466_() {
        return this.serverLevelData.m_5466_();
    }

    public boolean m_5468_() {
        return this.serverLevelData.m_5468_();
    }

    public boolean m_6535_() {
        return this.serverLevelData.m_6535_();
    }

    public void m_5555_(boolean initializedIn) {
        this.serverLevelData.m_5555_(initializedIn);
    }

    public GameRules m_5470_() {
        return this.serverLevelData.m_5470_();
    }

    public WorldBorder.Settings m_5813_() {
        return this.serverLevelData.m_5813_();
    }

    public void m_7831_(WorldBorder.Settings serializer) {
        this.serverLevelData.m_7831_(serializer);
    }

    public Difficulty m_5472_() {
        return this.serverLevelData.m_5472_();
    }

    public boolean m_5474_() {
        return this.serverLevelData.m_5474_();
    }

    public TimerQueue<MinecraftServer> m_7540_() {
        return this.serverLevelData.m_7540_();
    }

    public int m_6530_() {
        return this.serverLevelData.m_6530_();
    }

    public void m_6391_(int delay) {
        this.serverLevelData.m_6391_(delay);
    }

    public int m_6528_() {
        return this.serverLevelData.m_6528_();
    }

    public void m_6387_(int chance) {
        this.serverLevelData.m_6387_(chance);
    }

    @Nullable
    public UUID m_142403_() {
        return this.serverLevelData.m_142403_();
    }

    public void m_8115_(UUID id) {
        this.serverLevelData.m_8115_(id);
    }

    public void m_142471_(CrashReportCategory crashReportCategory, LevelHeightAccessor levelHeightAccessor) {
        this.serverLevelData.m_142471_(crashReportCategory, levelHeightAccessor);
    }

    public static DelegateWorldInfo wrap(ServerLevelData data) {
        return new DelegateWorldInfo(DelegateWorldInfo.worldSettings(data), DelegateWorldInfo.generatorSettings(data), DelegateWorldInfo.specialWorldProperty(data), DelegateWorldInfo.lifecycle(data), data);
    }

    private static LevelSettings worldSettings(ServerLevelData data) {
        if ((data = DelegateWorldInfo.resolveDelegate(data)) instanceof WorldInfoBridge) {
            WorldInfoBridge bridged = (WorldInfoBridge)data;
            return bridged.bridge$getWorldSettings();
        }
        if (data instanceof WorldData) {
            WorldData p = (WorldData)data;
            return p.m_5926_();
        }
        return new LevelSettings(data.m_5462_(), data.m_5464_(), data.m_5466_(), data.m_5472_(), data.m_5468_(), data.m_5470_(), WorldDataConfiguration.f_244649_);
    }

    private static WorldOptions generatorSettings(ServerLevelData data) {
        if ((data = DelegateWorldInfo.resolveDelegate(data)) instanceof WorldData) {
            WorldData p = (WorldData)data;
            return p.m_246337_();
        }
        return WorldOptions.m_247394_();
    }

    private static PrimaryLevelData.SpecialWorldProperty specialWorldProperty(ServerLevelData data) {
        if ((data = DelegateWorldInfo.resolveDelegate(data)) instanceof WorldData) {
            WorldData d = (WorldData)data;
            return d.m_5961_() ? PrimaryLevelData.SpecialWorldProperty.FLAT : (d.m_7513_() ? PrimaryLevelData.SpecialWorldProperty.DEBUG : PrimaryLevelData.SpecialWorldProperty.NONE);
        }
        return PrimaryLevelData.SpecialWorldProperty.NONE;
    }

    private static Lifecycle lifecycle(ServerLevelData data) {
        if ((data = DelegateWorldInfo.resolveDelegate(data)) instanceof WorldInfoBridge) {
            WorldInfoBridge bridged = (WorldInfoBridge)data;
            return bridged.bridge$getLifecycle();
        }
        if (data instanceof WorldData) {
            WorldData p = (WorldData)data;
            return p.m_5754_();
        }
        return Lifecycle.stable();
    }

    private static ServerLevelData resolveDelegate(ServerLevelData data) {
        if (data instanceof DerivedWorldInfoBridge) {
            DerivedWorldInfoBridge bridged = (DerivedWorldInfoBridge)data;
            return DelegateWorldInfo.resolveDelegate(bridged.bridge$getDelegate());
        }
        return data;
    }
}

