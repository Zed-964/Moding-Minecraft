package net.zed964.obscurestars.block.properties;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class ModBlockStateProperties extends BlockStateProperties {
    public static final EnumProperty<SpecifyDirectionTypeTo> SPECIFY_DIRECTION_TYPE_TO = EnumProperty.create("type", SpecifyDirectionTypeTo.class);
    public static final EnumProperty<SameBlockAttachTo> SAME_BLOCK_ATTACH_TO =  EnumProperty.create("attach", SameBlockAttachTo.class);
    public static final EnumProperty<ConnectionTo> CONNECTION_TO =  EnumProperty.create("connect", ConnectionTo.class);

}


