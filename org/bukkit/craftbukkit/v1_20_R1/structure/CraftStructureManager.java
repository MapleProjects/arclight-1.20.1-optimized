/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.NbtIo
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate
 *  net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager
 */
package org.bukkit.craftbukkit.v1_20_R1.structure;

import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_20_R1.structure.CraftStructure;
import org.bukkit.craftbukkit.v1_20_R1.util.CraftNamespacedKey;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

public class CraftStructureManager
implements StructureManager {
    private final StructureTemplateManager structureManager;

    public CraftStructureManager(StructureTemplateManager structureManager) {
        this.structureManager = structureManager;
    }

    @Override
    public Map<NamespacedKey, Structure> getStructures() {
        HashMap cachedStructures = new HashMap();
        for (Map.Entry entry : this.structureManager.f_230345_.entrySet()) {
            ((Optional)entry.getValue()).ifPresent(definedStructure -> {
                CraftStructure craftStructure = cachedStructures.put(CraftNamespacedKey.fromMinecraft((ResourceLocation)entry.getKey()), new CraftStructure((StructureTemplate)definedStructure));
            });
        }
        return Collections.unmodifiableMap(cachedStructures);
    }

    @Override
    public Structure getStructure(NamespacedKey structureKey) {
        Preconditions.checkArgument((structureKey != null ? 1 : 0) != 0, (Object)"NamespacedKey structureKey cannot be null");
        Optional definedStructure = (Optional)this.structureManager.f_230345_.get(CraftNamespacedKey.toMinecraft(structureKey));
        if (definedStructure == null) {
            return null;
        }
        return definedStructure.map(CraftStructure::new).orElse(null);
    }

    @Override
    public Structure loadStructure(NamespacedKey structureKey, boolean register) {
        ResourceLocation minecraftKey = this.createAndValidateMinecraftStructureKey(structureKey);
        Optional structure = (Optional)this.structureManager.f_230345_.get(minecraftKey);
        structure = structure == null ? Optional.empty() : structure;
        structure = structure.isPresent() ? structure : this.structureManager.m_230431_(minecraftKey);
        Optional optional = structure = structure.isPresent() ? structure : this.structureManager.m_230427_(minecraftKey);
        if (register) {
            this.structureManager.f_230345_.put(minecraftKey, structure);
        }
        return structure.map(CraftStructure::new).orElse(null);
    }

    @Override
    public Structure loadStructure(NamespacedKey structureKey) {
        return this.loadStructure(structureKey, true);
    }

    @Override
    public void saveStructure(NamespacedKey structureKey) {
        ResourceLocation minecraftKey = this.createAndValidateMinecraftStructureKey(structureKey);
        this.structureManager.m_230416_(minecraftKey);
    }

    @Override
    public void saveStructure(NamespacedKey structureKey, Structure structure) throws IOException {
        Preconditions.checkArgument((structureKey != null ? 1 : 0) != 0, (Object)"NamespacedKey structure cannot be null");
        Preconditions.checkArgument((structure != null ? 1 : 0) != 0, (Object)"Structure cannot be null");
        File structureFile = this.getStructureFile(structureKey);
        Files.createDirectories(structureFile.toPath().getParent(), new FileAttribute[0]);
        this.saveStructure(structureFile, structure);
    }

    @Override
    public Structure registerStructure(NamespacedKey structureKey, Structure structure) {
        Preconditions.checkArgument((structureKey != null ? 1 : 0) != 0, (Object)"NamespacedKey structureKey cannot be null");
        Preconditions.checkArgument((structure != null ? 1 : 0) != 0, (Object)"Structure cannot be null");
        ResourceLocation minecraftKey = this.createAndValidateMinecraftStructureKey(structureKey);
        Optional<StructureTemplate> optionalDefinedStructure = Optional.of(((CraftStructure)structure).getHandle());
        Optional<StructureTemplate> previousStructure = this.structureManager.f_230345_.put(minecraftKey, optionalDefinedStructure);
        return previousStructure == null ? null : (Structure)previousStructure.map(CraftStructure::new).orElse(null);
    }

    @Override
    public Structure unregisterStructure(NamespacedKey structureKey) {
        Preconditions.checkArgument((structureKey != null ? 1 : 0) != 0, (Object)"NamespacedKey structureKey cannot be null");
        ResourceLocation minecraftKey = this.createAndValidateMinecraftStructureKey(structureKey);
        Optional previousStructure = (Optional)this.structureManager.f_230345_.remove(minecraftKey);
        return previousStructure == null ? null : (Structure)previousStructure.map(CraftStructure::new).orElse(null);
    }

    @Override
    public void deleteStructure(NamespacedKey structureKey) throws IOException {
        this.deleteStructure(structureKey, true);
    }

    @Override
    public void deleteStructure(NamespacedKey structureKey, boolean unregister) throws IOException {
        ResourceLocation key = CraftNamespacedKey.toMinecraft(structureKey);
        if (unregister) {
            this.structureManager.f_230345_.remove(key);
        }
        Path path = this.structureManager.m_230361_(key, ".nbt");
        Files.deleteIfExists(path);
    }

    @Override
    public File getStructureFile(NamespacedKey structureKey) {
        ResourceLocation minecraftKey = this.createAndValidateMinecraftStructureKey(structureKey);
        return this.structureManager.m_230361_(minecraftKey, ".nbt").toFile();
    }

    @Override
    public Structure loadStructure(File file) throws IOException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"File cannot be null");
        FileInputStream fileinputstream = new FileInputStream(file);
        return this.loadStructure(fileinputstream);
    }

    @Override
    public Structure loadStructure(InputStream inputStream) throws IOException {
        Preconditions.checkArgument((inputStream != null ? 1 : 0) != 0, (Object)"inputStream cannot be null");
        return new CraftStructure(this.structureManager.m_230377_(inputStream));
    }

    @Override
    public void saveStructure(File file, Structure structure) throws IOException {
        Preconditions.checkArgument((file != null ? 1 : 0) != 0, (Object)"file cannot be null");
        Preconditions.checkArgument((structure != null ? 1 : 0) != 0, (Object)"structure cannot be null");
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        this.saveStructure(fileoutputstream, structure);
    }

    @Override
    public void saveStructure(OutputStream outputStream, Structure structure) throws IOException {
        Preconditions.checkArgument((outputStream != null ? 1 : 0) != 0, (Object)"outputStream cannot be null");
        Preconditions.checkArgument((structure != null ? 1 : 0) != 0, (Object)"structure cannot be null");
        CompoundTag nbttagcompound = ((CraftStructure)structure).getHandle().m_74618_(new CompoundTag());
        NbtIo.m_128947_((CompoundTag)nbttagcompound, (OutputStream)outputStream);
    }

    @Override
    public Structure createStructure() {
        return new CraftStructure(new StructureTemplate());
    }

    private ResourceLocation createAndValidateMinecraftStructureKey(NamespacedKey structureKey) {
        Preconditions.checkArgument((structureKey != null ? 1 : 0) != 0, (Object)"NamespacedKey structureKey cannot be null");
        ResourceLocation minecraftkey = CraftNamespacedKey.toMinecraft(structureKey);
        Preconditions.checkArgument((!minecraftkey.m_135815_().contains("//") ? 1 : 0) != 0, (Object)"Resource key for Structures can not contain \"//\"");
        return minecraftkey;
    }

    @Override
    public Structure copy(Structure structure) {
        Preconditions.checkArgument((structure != null ? 1 : 0) != 0, (Object)"Structure cannot be null");
        return new CraftStructure(this.structureManager.m_230404_(((CraftStructure)structure).getHandle().m_74618_(new CompoundTag())));
    }
}

