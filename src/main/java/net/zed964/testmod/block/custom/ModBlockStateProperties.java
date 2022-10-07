package net.zed964.testmod.block.custom;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.zed964.testmod.block.custom.building.VerticalDirectionType;
import net.zed964.testmod.block.custom.building.DirectionType;

public class ModBlockStateProperties extends BlockStateProperties {
    public static final EnumProperty<VerticalDirectionType> HALF_SLAB_TYPE = EnumProperty.create("type", VerticalDirectionType.class);
}
