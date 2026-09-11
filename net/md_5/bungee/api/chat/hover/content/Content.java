/*
 * Decompiled with CFR 0.152.
 */
package net.md_5.bungee.api.chat.hover.content;

import net.md_5.bungee.api.chat.HoverEvent;

public abstract class Content {
    public abstract HoverEvent.Action requiredAction();

    public void assertAction(HoverEvent.Action input) throws UnsupportedOperationException {
        if (input != this.requiredAction()) {
            throw new UnsupportedOperationException("Action " + (Object)((Object)input) + " not compatible! Expected " + (Object)((Object)this.requiredAction()));
        }
    }

    public String toString() {
        return "Content()";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Content)) {
            return false;
        }
        Content other = (Content)o;
        return other.canEqual(this);
    }

    protected boolean canEqual(Object other) {
        return other instanceof Content;
    }

    public int hashCode() {
        boolean result = true;
        return 1;
    }
}

