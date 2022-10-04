package net.zed964.testmod.block.custom;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.zed964.testmod.block.custom.building.HalfSlabType;
import net.zed964.testmod.block.custom.building.VerticalSlabType;

public class ModBlockStateProperties extends BlockStateProperties {
    public static final EnumProperty<VerticalSlabType> VERTICAL_SLAB_TYPE = EnumProperty.create("type", VerticalSlabType.class);
    public static final EnumProperty<HalfSlabType> HALF_SLAB_TYPE = EnumProperty.create("type", HalfSlabType.class);
}
