/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Preconditions
 *  org.jetbrains.annotations.NotNull
 */
package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface RecipeChoice
extends Predicate<ItemStack>,
Cloneable {
    @Deprecated
    @NotNull
    public ItemStack getItemStack();

    @NotNull
    public RecipeChoice clone();

    @Override
    public boolean test(@NotNull ItemStack var1);

    public static class ExactChoice
    implements RecipeChoice {
        private List<ItemStack> choices;

        public ExactChoice(@NotNull ItemStack stack) {
            this(Arrays.asList(stack));
        }

        public ExactChoice(ItemStack ... stacks) {
            this(Arrays.asList(stacks));
        }

        public ExactChoice(@NotNull List<ItemStack> choices) {
            Preconditions.checkArgument((choices != null ? 1 : 0) != 0, (Object)"choices");
            Preconditions.checkArgument((!choices.isEmpty() ? 1 : 0) != 0, (Object)"Must have at least one choice");
            for (ItemStack choice : choices) {
                Preconditions.checkArgument((choice != null ? 1 : 0) != 0, (Object)"Cannot have null choice");
            }
            this.choices = new ArrayList<ItemStack>(choices);
        }

        @Override
        @NotNull
        public ItemStack getItemStack() {
            return this.choices.get(0).clone();
        }

        @NotNull
        public List<ItemStack> getChoices() {
            return Collections.unmodifiableList(this.choices);
        }

        @Override
        @NotNull
        public ExactChoice clone() {
            try {
                ExactChoice clone = (ExactChoice)super.clone();
                clone.choices = new ArrayList<ItemStack>(this.choices);
                return clone;
            }
            catch (CloneNotSupportedException ex) {
                throw new AssertionError((Object)ex);
            }
        }

        @Override
        public boolean test(@NotNull ItemStack t) {
            for (ItemStack match : this.choices) {
                if (!t.isSimilar(match)) continue;
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hash = 7;
            hash = 41 * hash + Objects.hashCode(this.choices);
            return hash;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            ExactChoice other = (ExactChoice)obj;
            return Objects.equals(this.choices, other.choices);
        }

        public String toString() {
            return "ExactChoice{choices=" + this.choices + '}';
        }
    }

    public static class MaterialChoice
    implements RecipeChoice {
        private List<Material> choices;

        public MaterialChoice(@NotNull Material choice) {
            this(Arrays.asList(choice));
        }

        public MaterialChoice(Material ... choices) {
            this(Arrays.asList(choices));
        }

        public MaterialChoice(@NotNull Tag<Material> choices) {
            Preconditions.checkArgument((choices != null ? 1 : 0) != 0, (Object)"choices");
            this.choices = new ArrayList<Material>(choices.getValues());
        }

        public MaterialChoice(@NotNull List<Material> choices) {
            Preconditions.checkArgument((choices != null ? 1 : 0) != 0, (Object)"choices");
            Preconditions.checkArgument((!choices.isEmpty() ? 1 : 0) != 0, (Object)"Must have at least one choice");
            for (Material choice : choices) {
                Preconditions.checkArgument((choice != null ? 1 : 0) != 0, (Object)"Cannot have null choice");
            }
            this.choices = new ArrayList<Material>(choices);
        }

        @Override
        public boolean test(@NotNull ItemStack t) {
            for (Material match : this.choices) {
                if (t.getType() != match) continue;
                return true;
            }
            return false;
        }

        @Override
        @NotNull
        public ItemStack getItemStack() {
            ItemStack stack = new ItemStack(this.choices.get(0));
            if (this.choices.size() > 1) {
                stack.setDurability((short)Short.MAX_VALUE);
            }
            return stack;
        }

        @NotNull
        public List<Material> getChoices() {
            return Collections.unmodifiableList(this.choices);
        }

        @Override
        @NotNull
        public MaterialChoice clone() {
            try {
                MaterialChoice clone = (MaterialChoice)super.clone();
                clone.choices = new ArrayList<Material>(this.choices);
                return clone;
            }
            catch (CloneNotSupportedException ex) {
                throw new AssertionError((Object)ex);
            }
        }

        public int hashCode() {
            int hash = 3;
            hash = 37 * hash + Objects.hashCode(this.choices);
            return hash;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            MaterialChoice other = (MaterialChoice)obj;
            return Objects.equals(this.choices, other.choices);
        }

        public String toString() {
            return "MaterialChoice{choices=" + this.choices + '}';
        }
    }
}

