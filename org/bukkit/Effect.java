/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit;

import com.google.common.collect.Maps;
import java.util.Map;
import org.bukkit.Axis;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.potion.Potion;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Effect {
    CLICK2(1000, Type.SOUND),
    CLICK1(1001, Type.SOUND),
    BOW_FIRE(1002, Type.SOUND),
    DOOR_TOGGLE(1006, Type.SOUND),
    IRON_DOOR_TOGGLE(1005, Type.SOUND),
    TRAPDOOR_TOGGLE(1007, Type.SOUND),
    IRON_TRAPDOOR_TOGGLE(1037, Type.SOUND),
    FENCE_GATE_TOGGLE(1008, Type.SOUND),
    DOOR_CLOSE(1012, Type.SOUND),
    IRON_DOOR_CLOSE(1011, Type.SOUND),
    TRAPDOOR_CLOSE(1013, Type.SOUND),
    IRON_TRAPDOOR_CLOSE(1036, Type.SOUND),
    FENCE_GATE_CLOSE(1014, Type.SOUND),
    EXTINGUISH(1009, Type.SOUND),
    RECORD_PLAY(1010, Type.SOUND, Material.class),
    GHAST_SHRIEK(1015, Type.SOUND),
    GHAST_SHOOT(1016, Type.SOUND),
    BLAZE_SHOOT(1018, Type.SOUND),
    ZOMBIE_CHEW_WOODEN_DOOR(1019, Type.SOUND),
    ZOMBIE_CHEW_IRON_DOOR(1020, Type.SOUND),
    ZOMBIE_DESTROY_DOOR(1021, Type.SOUND),
    SMOKE(2000, Type.VISUAL, BlockFace.class),
    STEP_SOUND(2001, Type.SOUND, Material.class),
    POTION_BREAK(2002, Type.VISUAL, Potion.class),
    INSTANT_POTION_BREAK(2007, Type.VISUAL, Color.class),
    ENDER_SIGNAL(2003, Type.VISUAL),
    MOBSPAWNER_FLAMES(2004, Type.VISUAL),
    BREWING_STAND_BREW(1035, Type.SOUND),
    CHORUS_FLOWER_GROW(1033, Type.SOUND),
    CHORUS_FLOWER_DEATH(1034, Type.SOUND),
    PORTAL_TRAVEL(1032, Type.SOUND),
    ENDEREYE_LAUNCH(1003, Type.SOUND),
    FIREWORK_SHOOT(1004, Type.SOUND),
    VILLAGER_PLANT_GROW(2005, Type.VISUAL, Integer.class),
    DRAGON_BREATH(2006, Type.VISUAL),
    ANVIL_BREAK(1029, Type.SOUND),
    ANVIL_USE(1030, Type.SOUND),
    ANVIL_LAND(1031, Type.SOUND),
    ENDERDRAGON_SHOOT(1017, Type.SOUND),
    WITHER_BREAK_BLOCK(1022, Type.SOUND),
    WITHER_SHOOT(1024, Type.SOUND),
    ZOMBIE_INFECT(1026, Type.SOUND),
    ZOMBIE_CONVERTED_VILLAGER(1027, Type.SOUND),
    BAT_TAKEOFF(1025, Type.SOUND),
    END_GATEWAY_SPAWN(3000, Type.VISUAL),
    ENDERDRAGON_GROWL(3001, Type.SOUND),
    PHANTOM_BITE(1039, Type.SOUND),
    ZOMBIE_CONVERTED_TO_DROWNED(1040, Type.SOUND),
    HUSK_CONVERTED_TO_ZOMBIE(1041, Type.SOUND),
    GRINDSTONE_USE(1042, Type.SOUND),
    BOOK_PAGE_TURN(1043, Type.SOUND),
    SMITHING_TABLE_USE(1044, Type.SOUND),
    POINTED_DRIPSTONE_LAND(1045, Type.SOUND),
    POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON(1046, Type.SOUND),
    POINTED_DRIPSTONE_DRIP_WATER_INTO_CAULDRON(1047, Type.SOUND),
    SKELETON_CONVERTED_TO_STRAY(1048, Type.SOUND),
    COMPOSTER_FILL_ATTEMPT(1500, Type.VISUAL, Boolean.class),
    LAVA_INTERACT(1501, Type.VISUAL),
    REDSTONE_TORCH_BURNOUT(1502, Type.VISUAL),
    END_PORTAL_FRAME_FILL(1503, Type.VISUAL),
    DRIPPING_DRIPSTONE(1504, Type.VISUAL),
    BONE_MEAL_USE(1505, Type.VISUAL, Integer.class),
    ENDER_DRAGON_DESTROY_BLOCK(2008, Type.VISUAL),
    SPONGE_DRY(2009, Type.VISUAL),
    ELECTRIC_SPARK(3002, Type.VISUAL, Axis.class),
    COPPER_WAX_ON(3003, Type.VISUAL),
    COPPER_WAX_OFF(3004, Type.VISUAL),
    OXIDISED_COPPER_SCRAPE(3005, Type.VISUAL);

    private final int id;
    private final Type type;
    private final Class<?> data;
    private static final Map<Integer, Effect> BY_ID;

    static {
        BY_ID = Maps.newHashMap();
        Effect[] effectArray = Effect.values();
        int n = effectArray.length;
        int n2 = 0;
        while (n2 < n) {
            Effect effect = effectArray[n2];
            BY_ID.put(effect.id, effect);
            ++n2;
        }
    }

    private Effect(int id, Type type) {
        this(id, type, null);
    }

    private Effect(int id, Type type, Class<?> data) {
        this.id = id;
        this.type = type;
        this.data = data;
    }

    @Deprecated
    public int getId() {
        return this.id;
    }

    @NotNull
    public Type getType() {
        return this.type;
    }

    @Nullable
    public Class<?> getData() {
        return this.data;
    }

    @Deprecated
    @Nullable
    public static Effect getById(int id) {
        return BY_ID.get(id);
    }

    public static enum Type {
        SOUND,
        VISUAL;

    }
}

