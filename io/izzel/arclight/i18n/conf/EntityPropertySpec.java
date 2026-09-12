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
public class EntityPropertySpec
implements Cloneable {
    public static final EntityPropertySpec EMPTY = new EntityPropertySpec();
    @Setting(value="entityClass")
    public String entityClass;
    @Setting(value="entityImplClass")
    public String entityImplClass;

    public EntityPropertySpec clone() {
        try {
            return (EntityPropertySpec)super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

