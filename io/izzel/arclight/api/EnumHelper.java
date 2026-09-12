/*
 * Decompiled with CFR 0.152.
 */
package io.izzel.arclight.api;

import io.izzel.arclight.api.Unsafe;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EnumHelper {
    private static final long[] ENUM_CACHE_OFFSETS;

    public static <T> T addEnum(Class<T> cl, String name, List<Class<?>> ctorTypes, List<Object> ctorParams) {
        try {
            Unsafe.ensureClassInitialized(cl);
            Field field = cl.getDeclaredField("ENUM$VALUES");
            Object base = Unsafe.staticFieldBase(field);
            long offset = Unsafe.staticFieldOffset(field);
            Object[] arr = (Object[])Unsafe.getObject(base, offset);
            Object[] newArr = (Object[])Array.newInstance(cl, arr.length + 1);
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            T newInstance = EnumHelper.makeEnum(cl, name, arr.length, ctorTypes, ctorParams);
            newArr[arr.length] = newInstance;
            Unsafe.putObject(base, offset, newArr);
            EnumHelper.reset(cl);
            return newInstance;
        }
        catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> void addEnums(Class<T> cl, List<T> list) {
        try {
            Field field = cl.getDeclaredField("ENUM$VALUES");
            Object base = Unsafe.staticFieldBase(field);
            long offset = Unsafe.staticFieldOffset(field);
            Object[] arr = (Object[])Unsafe.getObject(base, offset);
            Object[] newArr = (Object[])Array.newInstance(cl, arr.length + list.size());
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            for (int i = 0; i < list.size(); ++i) {
                newArr[arr.length + i] = list.get(i);
            }
            Unsafe.putObject(base, offset, newArr);
            EnumHelper.reset(cl);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> T makeEnum(Class<T> cl, String name, int i, List<Class<?>> ctorTypes, List<Object> ctorParams) {
        try {
            Unsafe.ensureClassInitialized(cl);
            ArrayList ctor = new ArrayList(ctorTypes.size() + 2);
            ctor.add(String.class);
            ctor.add(Integer.TYPE);
            ctor.addAll(ctorTypes);
            MethodHandle constructor = Unsafe.lookup().findConstructor(cl, MethodType.methodType(Void.TYPE, ctor));
            ArrayList<Object> param = new ArrayList<Object>(ctorParams.size() + 2);
            param.add(name);
            param.add(i);
            param.addAll(ctorParams);
            return (T)constructor.invokeWithArguments(param);
        }
        catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void reset(Class<?> cl) {
        for (long offset : ENUM_CACHE_OFFSETS) {
            Unsafe.putObjectVolatile(cl, offset, null);
        }
    }

    static {
        ArrayList<Long> offsets = new ArrayList<Long>();
        for (String s : new String[]{"enumConstantDirectory", "enumConstants", "enumVars"}) {
            try {
                Field field = Class.class.getDeclaredField(s);
                offsets.add(Unsafe.objectFieldOffset(field));
            }
            catch (NoSuchFieldException noSuchFieldException) {
                // empty catch block
            }
        }
        if (offsets.isEmpty()) {
            throw new IllegalStateException("Unable to find offsets for Enum");
        }
        long[] arr = new long[offsets.size()];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = (Long)offsets.get(i);
        }
        ENUM_CACHE_OFFSETS = arr;
    }
}

