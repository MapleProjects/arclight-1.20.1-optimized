/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.permissions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum PermissionDefault {
    TRUE("true"),
    FALSE("false"),
    OP("op", "isop", "operator", "isoperator", "admin", "isadmin"),
    NOT_OP("!op", "notop", "!operator", "notoperator", "!admin", "notadmin");

    private final String[] names;
    private static final Map<String, PermissionDefault> lookup;

    static {
        lookup = new HashMap<String, PermissionDefault>();
        PermissionDefault[] permissionDefaultArray = PermissionDefault.values();
        int n = permissionDefaultArray.length;
        int n2 = 0;
        while (n2 < n) {
            PermissionDefault value = permissionDefaultArray[n2];
            String[] stringArray = value.names;
            int n3 = value.names.length;
            int n4 = 0;
            while (n4 < n3) {
                String name = stringArray[n4];
                lookup.put(name, value);
                ++n4;
            }
            ++n2;
        }
    }

    private PermissionDefault(String ... names) {
        this.names = names;
    }

    public boolean getValue(boolean op) {
        switch (this) {
            case TRUE: {
                return true;
            }
            case FALSE: {
                return false;
            }
            case OP: {
                return op;
            }
            case NOT_OP: {
                return !op;
            }
        }
        return false;
    }

    @Nullable
    public static PermissionDefault getByName(@NotNull String name) {
        return lookup.get(name.toLowerCase(Locale.ENGLISH).replaceAll("[^a-z!]", ""));
    }

    public String toString() {
        return this.names[0];
    }
}

