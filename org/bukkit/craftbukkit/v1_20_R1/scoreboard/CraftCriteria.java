/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  net.minecraft.world.scores.Objective
 *  net.minecraft.world.scores.criteria.ObjectiveCriteria
 */
package org.bukkit.craftbukkit.v1_20_R1.scoreboard;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.RenderType;

public final class CraftCriteria
implements Criteria {
    static final Map<String, CraftCriteria> DEFAULTS;
    static final CraftCriteria DUMMY;
    final ObjectiveCriteria criteria;
    final String bukkitName;

    static {
        ImmutableMap.Builder defaults = ImmutableMap.builder();
        for (Map.Entry entry : ObjectiveCriteria.f_166108_.entrySet()) {
            String name = (String)entry.getKey();
            ObjectiveCriteria criteria = (ObjectiveCriteria)entry.getValue();
            defaults.put((Object)name, (Object)new CraftCriteria(criteria));
        }
        DEFAULTS = defaults.build();
        DUMMY = DEFAULTS.get("dummy");
    }

    private CraftCriteria(String bukkitName) {
        this.bukkitName = bukkitName;
        this.criteria = CraftCriteria.DUMMY.criteria;
    }

    private CraftCriteria(ObjectiveCriteria criteria) {
        this.criteria = criteria;
        this.bukkitName = criteria.m_83620_();
    }

    @Override
    public String getName() {
        return this.bukkitName;
    }

    @Override
    public boolean isReadOnly() {
        return this.criteria.m_83621_();
    }

    @Override
    public RenderType getDefaultRenderType() {
        return RenderType.values()[this.criteria.m_83622_().ordinal()];
    }

    static CraftCriteria getFromNMS(Objective objective) {
        return DEFAULTS.get(objective.m_83321_().m_83620_());
    }

    public static CraftCriteria getFromBukkit(String name) {
        CraftCriteria criteria = DEFAULTS.get(name);
        if (criteria != null) {
            return criteria;
        }
        return ObjectiveCriteria.m_83614_((String)name).map(CraftCriteria::new).orElseGet(() -> new CraftCriteria(name));
    }

    public boolean equals(Object that) {
        if (!(that instanceof CraftCriteria)) {
            return false;
        }
        return ((CraftCriteria)that).bukkitName.equals(this.bukkitName);
    }

    public int hashCode() {
        return this.bukkitName.hashCode() ^ CraftCriteria.class.hashCode();
    }
}

