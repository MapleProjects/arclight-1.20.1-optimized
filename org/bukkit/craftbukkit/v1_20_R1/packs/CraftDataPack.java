/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.packs.repository.Pack
 *  net.minecraft.server.packs.repository.Pack$Info
 *  net.minecraft.server.packs.repository.Pack$ResourcesSupplier
 *  net.minecraft.server.packs.repository.PackCompatibility
 *  net.minecraft.server.packs.repository.PackSource
 */
package org.bukkit.craftbukkit.v1_20_R1.packs;

import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackCompatibility;
import net.minecraft.server.packs.repository.PackSource;
import org.bukkit.Bukkit;
import org.bukkit.FeatureFlag;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.CraftFeatureFlag;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftChatMessage;
import org.bukkit.packs.DataPack;

public class CraftDataPack
implements DataPack {
    private final Pack handle;

    public CraftDataPack(Pack handler) {
        this.handle = handler;
    }

    public Pack getHandle() {
        return this.handle;
    }

    public String getRawId() {
        return this.getHandle().m_10446_();
    }

    @Override
    public String getTitle() {
        return CraftChatMessage.fromComponent(this.getHandle().m_10429_());
    }

    @Override
    public String getDescription() {
        return CraftChatMessage.fromComponent(this.getHandle().m_10442_());
    }

    @Override
    public int getPackFormat() {
        Pack.Info info = Pack.m_246334_((String)this.getRawId(), (Pack.ResourcesSupplier)this.getHandle().f_244124_);
        return info == null ? 0 : info.f_244194_();
    }

    @Override
    public boolean isRequired() {
        return this.getHandle().m_10449_();
    }

    @Override
    public DataPack.Compatibility getCompatibility() {
        return switch (this.getHandle().m_10443_()) {
            case PackCompatibility.COMPATIBLE -> DataPack.Compatibility.COMPATIBLE;
            case PackCompatibility.TOO_NEW -> DataPack.Compatibility.NEW;
            case PackCompatibility.TOO_OLD -> DataPack.Compatibility.OLD;
            default -> throw new IncompatibleClassChangeError();
        };
    }

    @Override
    public boolean isEnabled() {
        return ((CraftServer)Bukkit.getServer()).getServer().m_129891_().m_10523_().contains(this.getRawId());
    }

    @Override
    public DataPack.Source getSource() {
        if (this.getHandle().m_10453_() == PackSource.f_10528_) {
            return DataPack.Source.BUILT_IN;
        }
        if (this.getHandle().m_10453_() == PackSource.f_244201_) {
            return DataPack.Source.FEATURE;
        }
        if (this.getHandle().m_10453_() == PackSource.f_10529_) {
            return DataPack.Source.WORLD;
        }
        if (this.getHandle().m_10453_() == PackSource.f_10530_) {
            return DataPack.Source.SERVER;
        }
        return DataPack.Source.DEFAULT;
    }

    @Override
    public Set<FeatureFlag> getRequestedFeatures() {
        return CraftFeatureFlag.getFromNMS(this.getHandle().m_245532_()).stream().map(FeatureFlag.class::cast).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public NamespacedKey getKey() {
        return NamespacedKey.fromString(this.getRawId());
    }

    public String toString() {
        String requestedFeatures = this.getRequestedFeatures().stream().map(featureFlag -> featureFlag.getKey().toString()).collect(Collectors.joining(","));
        return "CraftDataPack{rawId=" + this.getRawId() + ",id=" + this.getKey() + ",title=" + this.getTitle() + ",description=" + this.getDescription() + ",packformat=" + this.getPackFormat() + ",compatibility=" + (Object)((Object)this.getCompatibility()) + ",source=" + (Object)((Object)this.getSource()) + ",enabled=" + this.isEnabled() + ",requestedFeatures=[" + requestedFeatures + "]}";
    }
}

