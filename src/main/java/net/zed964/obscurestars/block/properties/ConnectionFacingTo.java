package net.zed964.obscurestars.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ConnectionFacingTo implements StringRepresentable {
    NORTH("north"),
    EAST("east"),
    WEST("west"),
    SOUTH("south"),

    NORTH_EAST("north_east"),
    NORTH_WEST("north_west"),
    SOUTH_EAST("south_east"),
    SOUTH_WEST("south_west"),

    NORTH_SOUTH("north_south"),
    EAST_WEST("east_west"),

    NORTH_EAST_SOUTH("north_east_south"),
    EAST_SOUTH_WEST("east_south_west"),
    SOUTH_WEST_NORTH("south_west_north"),
    WEST_NORTH_EAST("west_north_east"),


    FULL("full"),
    NONE("none");

    private final String name;

    ConnectionFacingTo(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
