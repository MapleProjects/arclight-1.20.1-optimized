/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ninja.leaping.configurate.objectmapping.Setting
 *  ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable
 */
package io.izzel.arclight.i18n.conf;

import io.izzel.arclight.i18n.conf.EntityPropertySpec;
import io.izzel.arclight.i18n.conf.MaterialPropertySpec;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class CompatSpec {
    @Setting(value="material-property-overrides")
    private Map<String, MaterialPropertySpec> materials;
    @Setting(value="entity-property-overrides")
    private Map<String, EntityPropertySpec> entities;
    @Setting(value="symlink-world")
    private boolean symlinkWorld;
    @Setting(value="extra-logic-worlds")
    private List<String> extraLogicWorlds;
    @Setting(value="forward-permission")
    private String forwardPermission;
    @Setting(value="valid-username-regex")
    private String validUsernameRegex;
    @Setting(value="lenient-item-tag-match")
    private boolean lenientItemTagMatch;
    @Setting(value="isolate-plugin-class-loaders")
    private List<String> isolatePluginClassLoaders;
    @Setting(value="isolate-adventure-from-modloader")
    private boolean isolateAdventureFromModloader;

    public Map<String, MaterialPropertySpec> getMaterials() {
        return this.materials;
    }

    public Optional<MaterialPropertySpec> getMaterial(String key) {
        return Optional.ofNullable(this.materials.get(key));
    }

    public Map<String, EntityPropertySpec> getEntities() {
        return this.entities;
    }

    public Optional<EntityPropertySpec> getEntity(String key) {
        return Optional.ofNullable(this.entities.get(key));
    }

    public boolean isSymlinkWorld() {
        return this.symlinkWorld;
    }

    public List<String> getExtraLogicWorlds() {
        return this.extraLogicWorlds;
    }

    public boolean isForwardPermission() {
        return Objects.equals(this.forwardPermission, "true");
    }

    public boolean isForwardPermissionReverse() {
        return Objects.equals(this.forwardPermission, "reverse");
    }

    public String getValidUsernameRegex() {
        return this.validUsernameRegex;
    }

    public boolean isLenientItemTagMatch() {
        return this.lenientItemTagMatch;
    }

    public boolean isIsolatedPluginClassLoaders(String name) {
        for (String prefix : this.isolatePluginClassLoaders) {
            if (!name.startsWith(prefix)) continue;
            return true;
        }
        return false;
    }

    public boolean isAdventureIsolatedFromML() {
        return this.isolateAdventureFromModloader;
    }
}

