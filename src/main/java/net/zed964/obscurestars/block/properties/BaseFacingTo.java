package net.zed964.obscurestars.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum BaseFacingTo implements StringRepresentable {
    UP("up"),
    DOWN("down"),
    NORTH("north"),
    SOUTH("south"),
    EAST("east"),
    WEST("west"),
    NONE("none");

    private final String name;

    BaseFacingTo(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
