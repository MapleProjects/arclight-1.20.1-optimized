/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.brigadier.StringReader
 *  com.mojang.brigadier.exceptions.CommandSyntaxException
 *  net.minecraft.nbt.CollectionTag
 *  net.minecraft.nbt.CompoundTag
 *  net.minecraft.nbt.DoubleTag
 *  net.minecraft.nbt.IntTag
 *  net.minecraft.nbt.ListTag
 *  net.minecraft.nbt.StringTag
 *  net.minecraft.nbt.Tag
 *  net.minecraft.nbt.TagParser
 */
package org.bukkit.craftbukkit.v1_20_R1.util;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.nbt.CollectionTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;

public class CraftNBTTagConfigSerializer {
    private static final Pattern ARRAY = Pattern.compile("^\\[.*]");
    private static final Pattern INTEGER = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)?i", 2);
    private static final Pattern DOUBLE = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", 2);
    private static final TagParser MOJANGSON_PARSER = new TagParser(new StringReader(""));

    public static Object serialize(Tag base) {
        if (base instanceof CompoundTag) {
            HashMap<String, Object> innerMap = new HashMap<String, Object>();
            for (String key : ((CompoundTag)base).m_128431_()) {
                innerMap.put(key, CraftNBTTagConfigSerializer.serialize(((CompoundTag)base).m_128423_(key)));
            }
            return innerMap;
        }
        if (base instanceof ListTag) {
            ArrayList<Object> baseList = new ArrayList<Object>();
            int i = 0;
            while (i < ((CollectionTag)base).size()) {
                baseList.add(CraftNBTTagConfigSerializer.serialize((Tag)((CollectionTag)base).get(i)));
                ++i;
            }
            return baseList;
        }
        if (base instanceof StringTag) {
            return base.m_7916_();
        }
        if (base instanceof IntTag) {
            return String.valueOf(base.toString()) + "i";
        }
        return base.toString();
    }

    public static Tag deserialize(Object object) {
        if (object instanceof Map) {
            CompoundTag compound = new CompoundTag();
            for (Map.Entry entry : ((Map)object).entrySet()) {
                compound.m_128365_((String)entry.getKey(), CraftNBTTagConfigSerializer.deserialize(entry.getValue()));
            }
            return compound;
        }
        if (object instanceof List) {
            List list = (List)object;
            if (list.isEmpty()) {
                return new ListTag();
            }
            ListTag tagList = new ListTag();
            for (Object tag : list) {
                tagList.add((Object)CraftNBTTagConfigSerializer.deserialize(tag));
            }
            return tagList;
        }
        if (object instanceof String) {
            String string = (String)object;
            if (ARRAY.matcher(string).matches()) {
                try {
                    return new TagParser(new StringReader(string)).m_129375_();
                }
                catch (CommandSyntaxException e) {
                    throw new RuntimeException("Could not deserialize found list ", e);
                }
            }
            if (INTEGER.matcher(string).matches()) {
                return IntTag.m_128679_((int)Integer.parseInt(string.substring(0, string.length() - 1)));
            }
            if (DOUBLE.matcher(string).matches()) {
                return DoubleTag.m_128500_((double)Double.parseDouble(string.substring(0, string.length() - 1)));
            }
            Tag nbtBase = MOJANGSON_PARSER.m_129368_(string);
            if (nbtBase instanceof IntTag) {
                return StringTag.m_129297_((String)nbtBase.m_7916_());
            }
            if (nbtBase instanceof DoubleTag) {
                return StringTag.m_129297_((String)String.valueOf(((DoubleTag)nbtBase).m_7061_()));
            }
            return nbtBase;
        }
        throw new RuntimeException("Could not deserialize NBTBase");
    }
}

