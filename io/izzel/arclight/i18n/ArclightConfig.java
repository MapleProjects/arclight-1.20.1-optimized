/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.reflect.TypeToken
 *  ninja.leaping.configurate.ConfigurationNode
 *  ninja.leaping.configurate.commented.CommentedConfigurationNode
 *  ninja.leaping.configurate.hocon.HoconConfigurationLoader
 *  ninja.leaping.configurate.hocon.HoconConfigurationLoader$Builder
 *  ninja.leaping.configurate.objectmapping.ObjectMappingException
 */
package io.izzel.arclight.i18n;

import com.google.common.reflect.TypeToken;
import io.izzel.arclight.i18n.ArclightLocale;
import io.izzel.arclight.i18n.conf.ConfigSpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.StringJoiner;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;

public class ArclightConfig {
    private static ArclightConfig instance;
    private final CommentedConfigurationNode node;
    private final ConfigSpec spec;

    public ArclightConfig(CommentedConfigurationNode node) throws ObjectMappingException {
        this.node = node;
        this.spec = (ConfigSpec)this.node.getValue(TypeToken.of(ConfigSpec.class));
    }

    public CommentedConfigurationNode getNode() {
        return this.node;
    }

    public ConfigSpec getSpec() {
        return this.spec;
    }

    public ConfigurationNode get(String path) {
        return this.node.getNode((Object[])path.split("\\."));
    }

    public static ConfigSpec spec() {
        return ArclightConfig.instance.spec;
    }

    private static void load() throws Exception {
        Path path = Paths.get("arclight.conf", new String[0]);
        CommentedConfigurationNode node = (CommentedConfigurationNode)((HoconConfigurationLoader.Builder)HoconConfigurationLoader.builder().setSource(() -> new BufferedReader(new InputStreamReader(ArclightConfig.class.getResourceAsStream("/META-INF/arclight.conf"), StandardCharsets.UTF_8)))).build().load();
        HoconConfigurationLoader loader = ((HoconConfigurationLoader.Builder)HoconConfigurationLoader.builder().setPath(path)).build();
        CommentedConfigurationNode cur = (CommentedConfigurationNode)loader.load();
        cur.mergeValuesFrom((ConfigurationNode)node);
        cur.getNode(new Object[]{"locale", "current"}).setValue((Object)ArclightLocale.getInstance().getCurrent());
        ArclightConfig.fillComments(cur, ArclightLocale.getInstance());
        instance = new ArclightConfig(cur);
        loader.save((ConfigurationNode)cur);
    }

    private static void fillComments(CommentedConfigurationNode node, ArclightLocale locale) {
        if (!node.getComment().isPresent()) {
            String path = ArclightConfig.pathOf((ConfigurationNode)node);
            Optional<String> option = locale.getOption("comments." + path + ".comment");
            option.ifPresent(arg_0 -> ((CommentedConfigurationNode)node).setComment(arg_0));
        }
        if (node.hasMapChildren()) {
            for (CommentedConfigurationNode value : node.getChildrenMap().values()) {
                ArclightConfig.fillComments(value, locale);
            }
        }
    }

    private static String pathOf(ConfigurationNode node) {
        StringJoiner joiner = new StringJoiner(".");
        for (Object o : node.getPath()) {
            if (o == null) continue;
            joiner.add(o.toString());
        }
        String s = joiner.toString();
        return s.isEmpty() ? "__root__" : s;
    }

    static {
        try {
            ArclightConfig.load();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

