/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  com.google.common.base.Strings
 *  com.google.common.collect.ImmutableMap
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.map.MapCursor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Deprecated
public final class StructureType
implements Keyed {
    private static final Map<String, StructureType> structureTypeMap = new HashMap<String, StructureType>();
    public static final StructureType MINESHAFT = StructureType.register(new StructureType("mineshaft", MapCursor.Type.RED_X));
    public static final StructureType VILLAGE = StructureType.register(new StructureType("village", MapCursor.Type.MANSION));
    public static final StructureType NETHER_FORTRESS = StructureType.register(new StructureType("fortress", MapCursor.Type.RED_X));
    public static final StructureType STRONGHOLD = StructureType.register(new StructureType("stronghold", MapCursor.Type.MANSION));
    public static final StructureType JUNGLE_PYRAMID = StructureType.register(new StructureType("jungle_pyramid", MapCursor.Type.RED_X));
    public static final StructureType OCEAN_RUIN = StructureType.register(new StructureType("ocean_ruin", MapCursor.Type.TEMPLE));
    public static final StructureType DESERT_PYRAMID = StructureType.register(new StructureType("desert_pyramid", MapCursor.Type.RED_X));
    public static final StructureType IGLOO = StructureType.register(new StructureType("igloo", MapCursor.Type.RED_X));
    public static final StructureType SWAMP_HUT = StructureType.register(new StructureType("swamp_hut", MapCursor.Type.RED_X));
    public static final StructureType OCEAN_MONUMENT = StructureType.register(new StructureType("monument", MapCursor.Type.TEMPLE));
    public static final StructureType END_CITY = StructureType.register(new StructureType("end_city", MapCursor.Type.RED_X));
    public static final StructureType WOODLAND_MANSION = StructureType.register(new StructureType("mansion", MapCursor.Type.MANSION));
    public static final StructureType BURIED_TREASURE = StructureType.register(new StructureType("buried_treasure", MapCursor.Type.RED_X));
    public static final StructureType SHIPWRECK = StructureType.register(new StructureType("shipwreck", MapCursor.Type.RED_X));
    public static final StructureType PILLAGER_OUTPOST = StructureType.register(new StructureType("pillager_outpost", MapCursor.Type.RED_X));
    public static final StructureType NETHER_FOSSIL = StructureType.register(new StructureType("nether_fossil", MapCursor.Type.RED_X));
    public static final StructureType RUINED_PORTAL = StructureType.register(new StructureType("ruined_portal", MapCursor.Type.RED_X));
    public static final StructureType BASTION_REMNANT = StructureType.register(new StructureType("bastion_remnant", MapCursor.Type.RED_X));
    private final NamespacedKey key;
    private final MapCursor.Type mapCursor;

    private StructureType(@NotNull String name, @Nullable MapCursor.Type mapIcon) {
        Preconditions.checkArgument((!Strings.isNullOrEmpty((String)name) ? 1 : 0) != 0, (Object)"Structure name cannot be empty");
        this.key = NamespacedKey.minecraft(name);
        this.mapCursor = mapIcon;
    }

    @NotNull
    public String getName() {
        return this.key.getKey();
    }

    @Nullable
    public MapCursor.Type getMapIcon() {
        return this.mapCursor;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StructureType)) {
            return false;
        }
        StructureType that = (StructureType)other;
        return this.key.equals(that.key) && this.mapCursor == that.mapCursor;
    }

    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.key);
        hash = 71 * hash + Objects.hashCode((Object)this.mapCursor);
        return hash;
    }

    public String toString() {
        return "StructureType{key=" + this.key + ", cursor=" + (Object)((Object)this.mapCursor) + "}";
    }

    @NotNull
    private static <T extends StructureType> T register(@NotNull T type) {
        Preconditions.checkNotNull(type, (Object)"Cannot register null StructureType.");
        Preconditions.checkArgument((!structureTypeMap.containsKey(type.getName()) ? 1 : 0) != 0, (String)"Cannot register same StructureType twice. %s", (Object)type.getName());
        structureTypeMap.put(type.getName(), type);
        return type;
    }

    @NotNull
    public static Map<String, StructureType> getStructureTypes() {
        return ImmutableMap.copyOf(structureTypeMap);
    }

    @Override
    @NotNull
    public NamespacedKey getKey() {
        return this.key;
    }
}

