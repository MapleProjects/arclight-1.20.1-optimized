/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.attribute;

import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.NumberConversions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AttributeModifier
implements ConfigurationSerializable {
    private final UUID uuid;
    private final String name;
    private final double amount;
    private final Operation operation;
    private final EquipmentSlot slot;

    public AttributeModifier(@NotNull String name, double amount, @NotNull Operation operation) {
        this(UUID.randomUUID(), name, amount, operation);
    }

    public AttributeModifier(@NotNull UUID uuid, @NotNull String name, double amount, @NotNull Operation operation) {
        this(uuid, name, amount, operation, null);
    }

    public AttributeModifier(@NotNull UUID uuid, @NotNull String name, double amount, @NotNull Operation operation, @Nullable EquipmentSlot slot) {
        Preconditions.checkArgument((uuid != null ? 1 : 0) != 0, (Object)"UUID cannot be null");
        Preconditions.checkArgument((name != null ? 1 : 0) != 0, (Object)"Name cannot be null");
        Preconditions.checkArgument((operation != null ? 1 : 0) != 0, (Object)"Operation cannot be null");
        this.uuid = uuid;
        this.name = name;
        this.amount = amount;
        this.operation = operation;
        this.slot = slot;
    }

    @NotNull
    public UUID getUniqueId() {
        return this.uuid;
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public double getAmount() {
        return this.amount;
    }

    @NotNull
    public Operation getOperation() {
        return this.operation;
    }

    @Nullable
    public EquipmentSlot getSlot() {
        return this.slot;
    }

    @Override
    @NotNull
    public Map<String, Object> serialize() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("uuid", this.uuid.toString());
        data.put("name", this.name);
        data.put("operation", this.operation.ordinal());
        data.put("amount", this.amount);
        if (this.slot != null) {
            data.put("slot", this.slot.name());
        }
        return data;
    }

    public boolean equals(Object other) {
        boolean slots;
        if (!(other instanceof AttributeModifier)) {
            return false;
        }
        AttributeModifier mod = (AttributeModifier)other;
        boolean bl = this.slot != null ? this.slot == mod.slot : (slots = mod.slot == null);
        return this.uuid.equals(mod.uuid) && this.name.equals(mod.name) && this.amount == mod.amount && this.operation == mod.operation && slots;
    }

    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.uuid);
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + (int)(Double.doubleToLongBits(this.amount) ^ Double.doubleToLongBits(this.amount) >>> 32);
        hash = 17 * hash + Objects.hashCode((Object)this.operation);
        hash = 17 * hash + Objects.hashCode((Object)this.slot);
        return hash;
    }

    public String toString() {
        return "AttributeModifier{uuid=" + this.uuid.toString() + ", name=" + this.name + ", operation=" + this.operation.name() + ", amount=" + this.amount + ", slot=" + (this.slot != null ? this.slot.name() : "") + "}";
    }

    @NotNull
    public static AttributeModifier deserialize(@NotNull Map<String, Object> args) {
        if (args.containsKey("slot")) {
            return new AttributeModifier(UUID.fromString((String)args.get("uuid")), (String)args.get("name"), NumberConversions.toDouble(args.get("amount")), Operation.values()[NumberConversions.toInt(args.get("operation"))], EquipmentSlot.valueOf(args.get("slot").toString().toUpperCase()));
        }
        return new AttributeModifier(UUID.fromString((String)args.get("uuid")), (String)args.get("name"), NumberConversions.toDouble(args.get("amount")), Operation.values()[NumberConversions.toInt(args.get("operation"))]);
    }

    public static enum Operation {
        ADD_NUMBER,
        ADD_SCALAR,
        MULTIPLY_SCALAR_1;

    }
}

