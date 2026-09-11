/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 */
package org.bukkit.craftbukkit.v1_20_R1.metadata;

import com.google.common.base.Preconditions;
import java.util.List;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.metadata.MetadataStore;
import org.bukkit.metadata.MetadataStoreBase;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class BlockMetadataStore
extends MetadataStoreBase<Block>
implements MetadataStore<Block> {
    private final World owningWorld;

    public BlockMetadataStore(World owningWorld) {
        this.owningWorld = owningWorld;
    }

    @Override
    protected String disambiguate(Block block, String metadataKey) {
        return String.valueOf(Integer.toString(block.getX())) + ":" + Integer.toString(block.getY()) + ":" + Integer.toString(block.getZ()) + ":" + metadataKey;
    }

    @Override
    public List<MetadataValue> getMetadata(Block block, String metadataKey) {
        Preconditions.checkArgument((block.getWorld() == this.owningWorld ? 1 : 0) != 0, (String)"Block does not belong to world %s", (Object)this.owningWorld.getName());
        return super.getMetadata(block, metadataKey);
    }

    @Override
    public boolean hasMetadata(Block block, String metadataKey) {
        Preconditions.checkArgument((block.getWorld() == this.owningWorld ? 1 : 0) != 0, (String)"Block does not belong to world %s", (Object)this.owningWorld.getName());
        return super.hasMetadata(block, metadataKey);
    }

    @Override
    public void removeMetadata(Block block, String metadataKey, Plugin owningPlugin) {
        Preconditions.checkArgument((block.getWorld() == this.owningWorld ? 1 : 0) != 0, (String)"Block does not belong to world %s", (Object)this.owningWorld.getName());
        super.removeMetadata(block, metadataKey, owningPlugin);
    }

    @Override
    public void setMetadata(Block block, String metadataKey, MetadataValue newMetadataValue) {
        Preconditions.checkArgument((block.getWorld() == this.owningWorld ? 1 : 0) != 0, (String)"Block does not belong to world %s", (Object)this.owningWorld.getName());
        super.setMetadata(block, metadataKey, newMetadataValue);
    }
}

