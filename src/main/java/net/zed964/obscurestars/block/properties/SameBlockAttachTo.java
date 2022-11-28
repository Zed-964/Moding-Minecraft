package net.zed964.obscurestars.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum SameBlockAttachTo implements StringRepresentable {
    AXIS_X("axis_x"),
    AXIS_Y("axis_y"),
    AXIS_Z("axis_z"),
    NO_ATTACH("no_attach");

    private final String name;

    SameBlockAttachTo(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
