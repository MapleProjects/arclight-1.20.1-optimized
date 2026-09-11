/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.yaml.snakeyaml.DumperOptions
 *  org.yaml.snakeyaml.DumperOptions$FlowStyle
 *  org.yaml.snakeyaml.LoaderOptions
 *  org.yaml.snakeyaml.Yaml
 *  org.yaml.snakeyaml.comments.CommentLine
 *  org.yaml.snakeyaml.comments.CommentType
 *  org.yaml.snakeyaml.constructor.BaseConstructor
 *  org.yaml.snakeyaml.error.YAMLException
 *  org.yaml.snakeyaml.nodes.AnchorNode
 *  org.yaml.snakeyaml.nodes.MappingNode
 *  org.yaml.snakeyaml.nodes.Node
 *  org.yaml.snakeyaml.nodes.NodeTuple
 *  org.yaml.snakeyaml.nodes.ScalarNode
 *  org.yaml.snakeyaml.nodes.SequenceNode
 *  org.yaml.snakeyaml.nodes.Tag
 *  org.yaml.snakeyaml.reader.UnicodeReader
 *  org.yaml.snakeyaml.representer.Representer
 */
package org.bukkit.configuration.file;

import com.google.common.base.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.comments.CommentLine;
import org.yaml.snakeyaml.comments.CommentType;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.error.YAMLException;
import org.yaml.snakeyaml.nodes.AnchorNode;
import org.yaml.snakeyaml.nodes.MappingNode;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.SequenceNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.reader.UnicodeReader;
import org.yaml.snakeyaml.representer.Representer;

public class YamlConfiguration
extends FileConfiguration {
    @Deprecated
    protected static final String COMMENT_PREFIX = "# ";
    @Deprecated
    protected static final String BLANK_CONFIG = "{}\n";
    private final DumperOptions yamlDumperOptions = new DumperOptions();
    private final LoaderOptions yamlLoaderOptions;
    private final YamlConstructor constructor;
    private final YamlRepresenter representer;
    private final Yaml yaml;

    public YamlConfiguration() {
        this.yamlDumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yamlLoaderOptions = new LoaderOptions();
        this.yamlLoaderOptions.setMaxAliasesForCollections(Integer.MAX_VALUE);
        this.yamlLoaderOptions.setCodePointLimit(Integer.MAX_VALUE);
        this.constructor = new YamlConstructor(this.yamlLoaderOptions);
        this.representer = new YamlRepresenter(this.yamlDumperOptions);
        this.representer.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yaml = new Yaml((BaseConstructor)this.constructor, (Representer)this.representer, this.yamlDumperOptions, this.yamlLoaderOptions);
    }

    @Override
    @NotNull
    public String saveToString() {
        this.yamlDumperOptions.setIndent(this.options().indent());
        this.yamlDumperOptions.setWidth(this.options().width());
        this.yamlDumperOptions.setProcessComments(this.options().parseComments());
        MappingNode node = this.toNodeTree(this);
        node.setBlockComments(this.getCommentLines(this.saveHeader(this.options().getHeader()), CommentType.BLOCK));
        node.setEndComments(this.getCommentLines(this.options().getFooter(), CommentType.BLOCK));
        StringWriter writer = new StringWriter();
        if (node.getBlockComments().isEmpty() && node.getEndComments().isEmpty() && node.getValue().isEmpty()) {
            writer.write("");
        } else {
            if (node.getValue().isEmpty()) {
                node.setFlowStyle(DumperOptions.FlowStyle.FLOW);
            }
            this.yaml.serialize((Node)node, (Writer)writer);
        }
        return writer.toString();
    }

    @Override
    public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {
        MappingNode node;
        Preconditions.checkArgument((contents != null ? 1 : 0) != 0, (Object)"Contents cannot be null");
        this.yamlLoaderOptions.setProcessComments(this.options().parseComments());
        try {
            Throwable throwable = null;
            Object var4_5 = null;
            try (UnicodeReader reader = new UnicodeReader((InputStream)new ByteArrayInputStream(contents.getBytes(StandardCharsets.UTF_8)));){
                Node rawNode = this.yaml.compose((Reader)reader);
                try {
                    node = (MappingNode)rawNode;
                }
                catch (ClassCastException e) {
                    throw new InvalidConfigurationException("Top level is not a Map.");
                }
            }
            catch (Throwable throwable2) {
                if (throwable == null) {
                    throwable = throwable2;
                } else if (throwable != throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
        }
        catch (IOException | ClassCastException | YAMLException e) {
            throw new InvalidConfigurationException(e);
        }
        this.map.clear();
        if (node != null) {
            this.adjustNodeComments(node);
            this.options().setHeader((List)this.loadHeader(this.getCommentLines(node.getBlockComments())));
            this.options().setFooter((List)this.getCommentLines(node.getEndComments()));
            this.fromNodeTree(node, this);
        }
    }

    private void adjustNodeComments(MappingNode node) {
        Node firstNode;
        List lines;
        if (node.getBlockComments() == null && !node.getValue().isEmpty() && (lines = (firstNode = ((NodeTuple)node.getValue().get(0)).getKeyNode()).getBlockComments()) != null) {
            int index = -1;
            int i = 0;
            while (i < lines.size()) {
                if (((CommentLine)lines.get(i)).getCommentType() == CommentType.BLANK_LINE) {
                    index = i;
                }
                ++i;
            }
            if (index != -1) {
                node.setBlockComments(lines.subList(0, index + 1));
                firstNode.setBlockComments(lines.subList(index + 1, lines.size()));
            }
        }
    }

    private void fromNodeTree(@NotNull MappingNode input, @NotNull ConfigurationSection section) {
        this.constructor.flattenMapping(input);
        for (NodeTuple nodeTuple : input.getValue()) {
            Node key = nodeTuple.getKeyNode();
            String keyString = String.valueOf(this.constructor.construct(key));
            Node value = nodeTuple.getValueNode();
            while (value instanceof AnchorNode) {
                value = ((AnchorNode)value).getRealNode();
            }
            if (value instanceof MappingNode && !this.hasSerializedTypeKey((MappingNode)value)) {
                this.fromNodeTree((MappingNode)value, section.createSection(keyString));
            } else {
                section.set(keyString, this.constructor.construct(value));
            }
            section.setComments(keyString, this.getCommentLines(key.getBlockComments()));
            if (value instanceof MappingNode || value instanceof SequenceNode) {
                section.setInlineComments(keyString, this.getCommentLines(key.getInLineComments()));
                continue;
            }
            section.setInlineComments(keyString, this.getCommentLines(value.getInLineComments()));
        }
    }

    private boolean hasSerializedTypeKey(MappingNode node) {
        for (NodeTuple nodeTuple : node.getValue()) {
            String key;
            Node keyNode = nodeTuple.getKeyNode();
            if (!(keyNode instanceof ScalarNode) || !(key = ((ScalarNode)keyNode).getValue()).equals("==")) continue;
            return true;
        }
        return false;
    }

    private MappingNode toNodeTree(@NotNull ConfigurationSection section) {
        ArrayList<NodeTuple> nodeTuples = new ArrayList<NodeTuple>();
        for (Map.Entry<String, Object> entry : section.getValues(false).entrySet()) {
            Node key = this.representer.represent(entry.getKey());
            Object value = entry.getValue() instanceof ConfigurationSection ? this.toNodeTree((ConfigurationSection)entry.getValue()) : this.representer.represent(entry.getValue());
            key.setBlockComments(this.getCommentLines(section.getComments(entry.getKey()), CommentType.BLOCK));
            if (value instanceof MappingNode || value instanceof SequenceNode) {
                key.setInLineComments(this.getCommentLines(section.getInlineComments(entry.getKey()), CommentType.IN_LINE));
            } else {
                value.setInLineComments(this.getCommentLines(section.getInlineComments(entry.getKey()), CommentType.IN_LINE));
            }
            nodeTuples.add(new NodeTuple(key, (Node)value));
        }
        return new MappingNode(Tag.MAP, nodeTuples, DumperOptions.FlowStyle.BLOCK);
    }

    private List<String> getCommentLines(List<CommentLine> comments) {
        ArrayList<String> lines = new ArrayList<String>();
        if (comments != null) {
            for (CommentLine comment : comments) {
                if (comment.getCommentType() == CommentType.BLANK_LINE) {
                    lines.add(null);
                    continue;
                }
                String line = comment.getValue();
                line = line.startsWith(" ") ? line.substring(1) : line;
                lines.add(line);
            }
        }
        return lines;
    }

    private List<CommentLine> getCommentLines(List<String> comments, CommentType commentType) {
        ArrayList<CommentLine> lines = new ArrayList<CommentLine>();
        for (String comment : comments) {
            if (comment == null) {
                lines.add(new CommentLine(null, null, "", CommentType.BLANK_LINE));
                continue;
            }
            String line = comment;
            line = line.isEmpty() ? line : " " + line;
            lines.add(new CommentLine(null, null, line, commentType));
        }
        return lines;
    }

    private List<String> loadHeader(List<String> header) {
        LinkedList<String> list = new LinkedList<String>(header);
        if (!list.isEmpty()) {
            list.removeLast();
        }
        while (!list.isEmpty() && list.peek() == null) {
            list.remove();
        }
        return list;
    }

    private List<String> saveHeader(List<String> header) {
        LinkedList<String> list = new LinkedList<String>(header);
        if (!list.isEmpty()) {
            list.add(null);
        }
        return list;
    }

    @Override
    @NotNull
    public YamlConfigurationOptions options() {
        if (this.options == null) {
            this.options = new YamlConfigurationOptions(this);
        }
        return (YamlConfigurationOptions)this.options;
    }

    @NotNull
    public static YamlConfiguration loadConfiguration(@NotNull File file) {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        }
        catch (FileNotFoundException fileNotFoundException) {
        }
        catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        }
        catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex);
        }
        return config;
    }

    @NotNull
    public static YamlConfiguration loadConfiguration(@NotNull Reader reader) {
        Preconditions.checkArgument((reader != null ? 1 : 0) != 0, (Object)"Stream cannot be null");
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(reader);
        }
        catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration from stream", ex);
        }
        catch (InvalidConfigurationException ex) {
            Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration from stream", ex);
        }
        return config;
    }
}

