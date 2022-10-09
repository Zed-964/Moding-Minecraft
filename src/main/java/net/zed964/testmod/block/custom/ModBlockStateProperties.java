package net.zed964.testmod.block.custom;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.zed964.testmod.block.custom.building.SpecifyDirectionType;

public class ModBlockStateProperties extends BlockStateProperties {
    public static final EnumProperty<SpecifyDirectionType> SPECIFY_DIRECTION_TYPE = EnumProperty.create("type", SpecifyDirectionType.class);
}
