/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ninja.leaping.configurate.objectmapping.Setting
 *  ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
 */
package io.izzel.arclight.i18n.conf;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class MaterialPropertySpec
implements Cloneable {
    public static final MaterialPropertySpec EMPTY = new MaterialPropertySpec();
    @Setting(value="materialDataClass")
    public String materialDataClass;
    @Setting(value="maxStack")
    public Integer maxStack;
    @Setting(value="maxDurability")
    public Integer maxDurability;
    @Setting(value="edible")
    public Boolean edible;
    @Setting(value="record")
    public Boolean record;
    @Setting(value="solid")
    public Boolean solid;
    @Setting(value="air")
    public Boolean air;
    @Setting(value="transparent")
    public Boolean transparent;
    @Setting(value="flammable")
    public Boolean flammable;
    @Setting(value="burnable")
    public Boolean burnable;
    @Setting(value="fuel")
    public Boolean fuel;
    @Setting(value="occluding")
    public Boolean occluding;
    @Setting(value="gravity")
    public Boolean gravity;
    @Setting(value="interactable")
    public Boolean interactable;
    @Setting(value="hardness")
    public Float hardness;
    @Setting(value="blastResistance")
    public Float blastResistance;
    @Setting(value="craftingRemainingItem")
    public String craftingRemainingItem;
    @Setting(value="itemMetaType")
    public String itemMetaType;
    @Setting(value="blockStateClass")
    public String blockStateClass;

    public MaterialPropertySpec clone() {
        try {
            return (MaterialPropertySpec)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError((Object)e);
        }
    }

    public static enum MaterialType {
        VANILLA,
        FORGE;

    }
}

