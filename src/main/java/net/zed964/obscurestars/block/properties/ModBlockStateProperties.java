package net.zed964.obscurestars.block.properties;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockStateProperties extends BlockStateProperties {
    public static final BooleanProperty BASE = BooleanProperty.create("base");
    public static final BooleanProperty CONNECTION = BooleanProperty.create("connection");

    public static final EnumProperty<SpecifyDirectionTypeTo> SPECIFY_DIRECTION_TYPE_TO = EnumProperty.create("type", SpecifyDirectionTypeTo.class);
    public static final EnumProperty<BaseFacingTo> BASE_FACING_TO = EnumProperty.create("base_facing", BaseFacingTo.class);
    public static final EnumProperty<ConnectionFacingTo> CONNECTION_FACING_TO = EnumProperty.create("connection_facing", ConnectionFacingTo.class);

}


