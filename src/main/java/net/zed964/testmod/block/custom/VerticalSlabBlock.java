package net.zed964.testmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VerticalSlabBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<VerticalSlabType> TYPE = ModBlockStateProperties.VERTICAL_SLAB_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape NORTH_AABB = Block.box(0, 0, 0, 16, 16, 8);
    protected static final VoxelShape EAST_AABB = Block.box(8, 0, 0, 16, 16, 16);
    protected static final VoxelShape WEST_AABB = Block.box(0, 0, 0, 8, 16, 16);
    protected static final VoxelShape SOUTH_AABB = Block.box(0, 0, 8, 16, 16, 16);

    public VerticalSlabBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(TYPE, VerticalSlabType.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public boolean useShapeForLightOcclusion(BlockState pState) {
        return pState.getValue(TYPE) != VerticalSlabType.DOUBLE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TYPE, WATERLOGGED);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        VerticalSlabType verticalSlabType = pState.getValue(TYPE);
        return switch (verticalSlabType) {
            case DOUBLE -> Shapes.block();
            case EAST -> EAST_AABB;
            case WEST -> WEST_AABB;
            case SOUTH -> SOUTH_AABB;
            default -> NORTH_AABB;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockPos = pContext.getClickedPos();
        BlockState blockState = pContext.getLevel().getBlockState(blockPos);

        if (blockState.is(this)) {
            return blockState.setValue(TYPE, VerticalSlabType.DOUBLE).setValue(WATERLOGGED, Boolean.FALSE);
        } else {
            FluidState fluidState = pContext.getLevel().getFluidState(blockPos);
            BlockState blockState1 = this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
            Direction direction = pContext.getClickedFace();

            switch (direction.getOpposite()) {
                case EAST:
                    return blockState1.setValue(TYPE, VerticalSlabType.EAST);
                case WEST:
                    return blockState1.setValue(TYPE, VerticalSlabType.WEST);
                case SOUTH:
                    return blockState1.setValue(TYPE, VerticalSlabType.SOUTH);
                case UP:
                    Direction.AxisDirection direction1 = direction.getAxisDirection();
                    if (direction1 == Direction.AxisDirection.POSITIVE) {
                        if(direction.getAxis() == Direction.Axis.X) {
                            return blockState1.setValue(TYPE, VerticalSlabType.EAST);
                        } else if (direction.getAxis() == Direction.Axis.Z) {
                            return blockState1.setValue(TYPE, VerticalSlabType.SOUTH);
                        }
                    } else {
                        if(direction.getAxis() == Direction.Axis.X) {
                            return blockState1.setValue(TYPE, VerticalSlabType.WEST);
                        } else if (direction.getAxis() == Direction.Axis.Z) {
                            return blockState1.setValue(TYPE, VerticalSlabType.NORTH);
                        }
                    }
                case DOWN:
                    switch (direction.getAxis()) {
                        case X:
                            if (direction.getAxisDirection() == Direction.AxisDirection.NEGATIVE) {
                                return blockState1.setValue(TYPE, VerticalSlabType.WEST);
                            } else {
                                return blockState1.setValue(TYPE, VerticalSlabType.EAST);
                            }
                        case Z:
                            if (direction.getAxisDirection() == Direction.AxisDirection.NEGATIVE) {
                                return blockState1.setValue(TYPE, VerticalSlabType.NORTH);
                            } else {
                                return blockState1.setValue(TYPE, VerticalSlabType.SOUTH);
                            }
                    }
                default:
                    return blockState1.setValue(TYPE, VerticalSlabType.NORTH);
            }
        }
    }
}