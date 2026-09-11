/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.yaml.snakeyaml.nodes.Tag
 *  org.yaml.snakeyaml.resolver.Resolver
 */
package org.bukkit.plugin;

import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.resolver.Resolver;

final class PluginDescriptionResolver
extends Resolver {
    PluginDescriptionResolver() {
    }

    protected void addImplicitResolvers() {
        this.addImplicitResolver(Tag.BOOL, BOOL, "yYnNtTfFoO");
        this.addImplicitResolver(Tag.INT, INT, "-+0123456789");
        this.addImplicitResolver(Tag.MERGE, MERGE, "<");
        this.addImplicitResolver(Tag.NULL, NULL, "~nN\u0000");
        this.addImplicitResolver(Tag.NULL, EMPTY, null);
        this.addImplicitResolver(Tag.TIMESTAMP, TIMESTAMP, "0123456789");
    }
}

