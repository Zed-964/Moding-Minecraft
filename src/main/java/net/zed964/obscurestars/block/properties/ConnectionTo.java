package net.zed964.obscurestars.block.properties;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum ConnectionTo implements StringRepresentable {
    //-------- 2 Connections ----------
    UP_NORTH("up_north"),
    UP_EAST("up_east"),
    UP_WEST("up_west"),
    UP_SOUTH("up_south"),

    DOWN_NORTH("down_north"),
    DOWN_EAST("down_east"),
    DOWN_WEST("down_west"),
    DOWN_SOUTH("down_south"),

    NORTH_EAST("north_east"),
    NORTH_WEST("north_west"),
    SOUTH_EAST("south_east"),
    SOUTH_WEST("south_west"),

    //-------- 3 Connections ----------
    UP_NORTH_EAST("up_north_east"),
    UP_NORTH_WEST("up_north_west"),
    UP_SOUTH_EAST("up_south_east"),
    UP_SOUTH_WEST("up_south_west"),

    DOWN_NORTH_EAST("down_north_east"),
    DOWN_NORTH_WEST("down_north_west"),
    DOWN_SOUTH_EAST("down_south_east"),
    DOWN_SOUTH_WEST("down_south_west"),

    UP_NORTH_SOUTH("up_north_south"),
    UP_EAST_WEST("up_east_west"),
    DOWN_NORTH_SOUTH("down_north_south"),
    DOWN_EAST_WEST("down_east_west"),

    UP_DOWN_NORTH("up_down_north"),
    UP_DOWN_EAST("up_down_east"),
    UP_DOWN_WEST("up_down_west"),
    UP_DOWN_SOUTH("up_down_south"),

    NORTH_EAST_SOUTH("north_east_south"),
    EAST_SOUTH_WEST("east_south_west"),
    SOUTH_WEST_NORTH("south_west_north"),
    WEST_NORTH_EAST("west_north_east"),

    //-------- 4 Connections ----------
    UP_NORTH_EAST_SOUTH("up_north_east_south"),
    UP_EAST_SOUTH_WEST("up_east_south_west"),
    UP_SOUTH_WEST_NORTH("up_south_west_north"),
    UP_WEST_NORTH_EAST("up_west_north_east"),

    DOWN_NORTH_EAST_SOUTH("down_north_east_south"),
    DOWN_EAST_SOUTH_WEST("down_east_south_west"),
    DOWN_SOUTH_WEST_NORTH("down_south_west_north"),
    DOWN_WEST_NORTH_EAST("down_west_north_east"),

    UP_DOWN_NORTH_EAST("up_down_north_east"),
    UP_DOWN_NORTH_WEST("up_down_north_west"),
    UP_DOWN_SOUTH_EAST("up_down_south_east"),
    UP_DOWN_SOUTH_WEST("up_down_south_west"),

    NORTH_EAST_SOUTH_WEST("north_east_south_west"),

    //-------- 5 Connections ----------
    UP_DOWN_NORTH_EAST_SOUTH("up_down_north_east_south"),
    UP_DOWN_EAST_SOUTH_WEST("up_down_east_south_west"),
    UP_DOWN_SOUTH_WEST_NORTH("up_down_south_west_north"),
    UP_DOWN_WEST_NORTH_EAST("up_down_west_north_east"),

    UP_NORTH_EAST_SOUTH_WEST("up_north_east_south_west"),
    DOWN_NORTH_EAST_SOUTH_WEST("down_north_east_south_west"),


    FULL_CONNECTION("full_connection"),
    NO_CONNECTION("no_connection");

    private final String name;

    ConnectionTo(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
