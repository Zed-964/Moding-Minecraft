package net.zed964.testmod.block.custom.building;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum HalfSlabType implements StringRepresentable {
    DOWN_NORTH("down_north"),
    DOWN_EAST("down_east"),
    DOWN_WEST("down_west"),
    DOWN_SOUTH("down_south"),
    UP_NORTH("up_north"),
    UP_EAST("up_east"),
    UP_WEST("up_west"),
    UP_SOUTH("up_south");

    private final String name;

    private HalfSlabType(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.name;
    }

    public @NotNull String getSerializedName() {
        return this.name;
    }
}
