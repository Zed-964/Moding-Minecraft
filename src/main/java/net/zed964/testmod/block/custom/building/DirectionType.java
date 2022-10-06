package net.zed964.testmod.block.custom.building;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum DirectionType implements StringRepresentable {
    NORTH("north"),
    EAST("east"),
    WEST("west"),
    SOUTH("south");

    private final String name;

    private DirectionType(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}