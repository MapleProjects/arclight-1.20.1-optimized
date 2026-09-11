/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.advancements.AdvancementProgress
 *  net.minecraft.advancements.CriterionProgress
 *  net.minecraft.server.PlayerAdvancements
 */
package org.bukkit.craftbukkit.v1_20_R1.advancement;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import net.minecraft.advancements.CriterionProgress;
import net.minecraft.server.PlayerAdvancements;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.craftbukkit.v1_20_R1.advancement.CraftAdvancement;

public class CraftAdvancementProgress
implements AdvancementProgress {
    private final CraftAdvancement advancement;
    private final PlayerAdvancements playerData;
    private final net.minecraft.advancements.AdvancementProgress handle;

    public CraftAdvancementProgress(CraftAdvancement advancement, PlayerAdvancements player, net.minecraft.advancements.AdvancementProgress handle) {
        this.advancement = advancement;
        this.playerData = player;
        this.handle = handle;
    }

    @Override
    public Advancement getAdvancement() {
        return this.advancement;
    }

    @Override
    public boolean isDone() {
        return this.handle.m_8193_();
    }

    @Override
    public boolean awardCriteria(String criteria) {
        return this.playerData.m_135988_(this.advancement.getHandle(), criteria);
    }

    @Override
    public boolean revokeCriteria(String criteria) {
        return this.playerData.m_135998_(this.advancement.getHandle(), criteria);
    }

    @Override
    public Date getDateAwarded(String criteria) {
        CriterionProgress criterion = this.handle.m_8214_(criteria);
        return criterion == null ? null : criterion.m_12920_();
    }

    @Override
    public Collection<String> getRemainingCriteria() {
        return Collections.unmodifiableCollection(Lists.newArrayList((Iterable)this.handle.m_8219_()));
    }

    @Override
    public Collection<String> getAwardedCriteria() {
        return Collections.unmodifiableCollection(Lists.newArrayList((Iterable)this.handle.m_8220_()));
    }
}

