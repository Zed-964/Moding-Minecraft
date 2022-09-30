package net.zed964.testmod.block.custom;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum VerticalSlabType implements StringRepresentable {
    RIGHT("right"),
    LEFT("left"),
    DOUBLE("double");

    private final String name;

    private VerticalSlabType(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}