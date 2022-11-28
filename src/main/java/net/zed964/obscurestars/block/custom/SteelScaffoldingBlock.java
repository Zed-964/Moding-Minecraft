package net.zed964.obscurestars.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zed964.obscurestars.block.properties.ConnectionTo;
import net.zed964.obscurestars.block.properties.ModBlockStateProperties;
import net.zed964.obscurestars.block.properties.SameBlockAttachTo;
import org.jetbrains.annotations.NotNull;

public class SteelScaffoldingBlock extends RotatedPillarBlock implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final EnumProperty<SameBlockAttachTo> ATTACH = ModBlockStateProperties.SAME_BLOCK_ATTACH_TO;
    public static final EnumProperty<ConnectionTo> CONNECT = ModBlockStateProperties.CONNECTION_TO;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape BASE_BOTTOM_AABB = Block.box(0, 0, 0, 16, 2, 16);
    private static final VoxelShape BASE_TOP_AABB = Block.box(0, 14, 0, 16, 16, 16);

    private static final VoxelShape SCAFFOLDING_VERTICAL_AABB = Block.box(1, 0, 1, 15, 16, 15);
    private static final VoxelShape SCAFFOLDING_HORIZONTAL_X_AABB = Block.box(0, 1, 1, 16, 15, 15);
    private static final VoxelShape SCAFFOLDING_HORIZONTAL_Z_AABB = Block.box(1, 1, 0, 15, 15, 16);

    private static final VoxelShape BASE_EAST_AABB = Block.box(14, 0, 0, 16, 16, 16);
    private static final VoxelShape BASE_WEST_AABB = Block.box(0, 0, 0, 2, 16, 16);
    private static final VoxelShape BASE_NORTH_AABB = Block.box(0, 0, 0, 16, 16, 2);
    private static final VoxelShape BASE_SOUTH_AABB = Block.box(0, 0, 14, 16, 16, 16);

    private static final VoxelShape CONNECTION_EAST_AABB = Block.box(13, 0, 0, 16, 16, 16);
    private static final VoxelShape CONNECTION_WEST_AABB = Block.box(0, 0, 0, 3, 16, 16);
    private static final VoxelShape CONNECTION_NORTH_AABB = Block.box(0, 0, 0, 16, 16, 3);
    private static final VoxelShape CONNECTION_SOUTH_AABB = Block.box(0, 0, 13, 16, 16, 16);

    public SteelScaffoldingBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(ATTACH, SameBlockAttachTo.NO_ATTACH).setValue(CONNECT, ConnectionTo.NO_CONNECTION).setValue(WATERLOGGED, Boolean.FALSE));
    }

    private VoxelShape makeShape(VoxelShape shape1, VoxelShape shape2) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, shape1, BooleanOp.OR);
        shape = Shapes.join(shape, shape2, BooleanOp.OR);

        return shape;
    }

    private BlockPos clikedBlockPos(Direction direction, BlockPos placementBlock) {
        BlockPos blockpos;
        int x, y, z;
        switch (direction) {
            case UP -> {
                y = placementBlock.getY();
                y = y - 1;
                blockpos = new BlockPos(placementBlock.getX(), y, placementBlock.getZ());
            }
            case DOWN -> {
                y = placementBlock.getY();
                y = y + 1;
                blockpos = new BlockPos(placementBlock.getX(), y, placementBlock.getZ());
            }
            default -> {
                z = placementBlock.getZ();
                z = z + 1;
                blockpos = new BlockPos(placementBlock.getX(), placementBlock.getY(), z);
            }
            case SOUTH -> {
                z = placementBlock.getZ();
                z = z - 1;
                blockpos = new BlockPos(placementBlock.getX(), placementBlock.getY(), z);
            }
            case EAST -> {
                x = placementBlock.getX();
                x = x - 1;
                blockpos = new BlockPos(x, placementBlock.getY(), placementBlock.getZ());
            }
            case WEST -> {
                x = placementBlock.getX();
                x = x + 1;
                blockpos = new BlockPos(x, placementBlock.getY(), placementBlock.getZ());
            }
        }
        return blockpos;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
    pBuilder.add(AXIS, FACING, ATTACH, CONNECT, WATERLOGGED);
    }

    @Override
    public void updateIndirectNeighbourShapes(BlockState pState, LevelAccessor pLevel, BlockPos pPos, int pFlags, int pRecursionLeft) {
        super.updateIndirectNeighbourShapes(pState, pLevel, pPos, pFlags, pRecursionLeft);
    }
/*
    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {

        return pState.setValue(ATTACH, SameBlockAttachTo.NO_ATTACH);
    }
*/
    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        Direction.Axis axis = pState.getValue(AXIS);
        SameBlockAttachTo  attach = pState.getValue(ATTACH);
        Direction direction = pState.getValue(FACING);

        VoxelShape shape = Shapes.empty();

        switch (attach) {
            case NO_ATTACH -> {
                 switch (direction) {
                    case UP -> shape = makeShape(SCAFFOLDING_VERTICAL_AABB, BASE_TOP_AABB);
                    case DOWN -> shape = makeShape(SCAFFOLDING_VERTICAL_AABB, BASE_BOTTOM_AABB);
                    case NORTH -> shape = makeShape(SCAFFOLDING_HORIZONTAL_Z_AABB, BASE_NORTH_AABB);
                    case SOUTH -> shape = makeShape(SCAFFOLDING_HORIZONTAL_Z_AABB, BASE_SOUTH_AABB);
                    case EAST -> shape = makeShape(SCAFFOLDING_HORIZONTAL_X_AABB, BASE_EAST_AABB);
                    case WEST -> shape = makeShape(SCAFFOLDING_HORIZONTAL_X_AABB, BASE_WEST_AABB);
                }
            }
            case AXIS_X -> {
                 switch (axis) {
                    case X -> shape = SCAFFOLDING_HORIZONTAL_X_AABB;
                    case Y -> {
                        if (direction == Direction.UP) {
                            shape = makeShape(SCAFFOLDING_VERTICAL_AABB, BASE_TOP_AABB);
                        } else {
                            shape = makeShape(SCAFFOLDING_VERTICAL_AABB, BASE_BOTTOM_AABB);
                        }
                    }
                    case Z -> {
                        if (direction == Direction.NORTH) {
                            shape = makeShape(SCAFFOLDING_HORIZONTAL_Z_AABB, BASE_NORTH_AABB);
                        } else {
                            shape = makeShape(SCAFFOLDING_HORIZONTAL_Z_AABB, BASE_SOUTH_AABB);
                        }
                    }
                }
            }
            case AXIS_Y -> {
                switch (axis) {
                    case X -> {
                        if (direction == Direction.EAST) {
                            shape = makeShape(SCAFFOLDING_HORIZONTAL_X_AABB, BASE_EAST_AABB);
                        } else {
                            shape = makeShape(SCAFFOLDING_HORIZONTAL_X_AABB, BASE_WEST_AABB);
                        }
                    }
                    case Y -> shape = SCAFFOLDING_VERTICAL_AABB;
                    case Z -> {
                        if (direction == Direction.NORTH) {
                            shape = makeShape(SCAFFOLDING_HORIZONTAL_Z_AABB, BASE_NORTH_AABB);
                        } else {
                            shape = makeShape(SCAFFOLDING_HORIZONTAL_Z_AABB, BASE_SOUTH_AABB);
                        }
                    }
                }
            }
            case AXIS_Z -> {
                switch (axis) {
                    case X -> {
                        if (direction == Direction.EAST) {
                            shape = makeShape(SCAFFOLDING_HORIZONTAL_X_AABB, BASE_EAST_AABB);
                        } else {
                            shape = makeShape(SCAFFOLDING_HORIZONTAL_X_AABB, BASE_WEST_AABB);
                        }
                    }
                    case Y -> {
                        if (direction == Direction.UP) {
                            shape = makeShape(SCAFFOLDING_VERTICAL_AABB, BASE_TOP_AABB);
                        } else {
                            shape = makeShape(SCAFFOLDING_VERTICAL_AABB, BASE_BOTTOM_AABB);
                        }
                    }
                    case Z -> shape = SCAFFOLDING_HORIZONTAL_Z_AABB;
                }
            }
        }
        return shape;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockPos = pContext.getClickedPos();
        FluidState fluidState = pContext.getLevel().getFluidState(blockPos);
        BlockState blockState =this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        Direction direction = pContext.getClickedFace();
        BlockPos blockClikedPos = clikedBlockPos(direction, blockPos);

        BlockState stateClikedBlock = pContext.getLevel().getBlockState(blockClikedPos);


        if (stateClikedBlock.getBlock() ==  this) {
             switch (stateClikedBlock.getValue(AXIS)) {
                case X -> {
                     return switch (direction) {
                        case UP -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.DOWN).setValue(ATTACH, SameBlockAttachTo.AXIS_X);
                        case DOWN -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.UP).setValue(ATTACH, SameBlockAttachTo.AXIS_X);
                        case SOUTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.NORTH).setValue(ATTACH, SameBlockAttachTo.AXIS_X);
                        case EAST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.WEST).setValue(ATTACH,SameBlockAttachTo.AXIS_X);
                        case WEST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.EAST).setValue(ATTACH, SameBlockAttachTo.AXIS_X);
                        default -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.SOUTH).setValue(ATTACH, SameBlockAttachTo.AXIS_X);
                    };
                }
                case Y -> {
                     return switch (direction) {
                        case UP -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.DOWN).setValue(ATTACH, SameBlockAttachTo.AXIS_Y);
                        case DOWN -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.UP).setValue(ATTACH, SameBlockAttachTo.AXIS_Y);
                        case SOUTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.NORTH).setValue(ATTACH, SameBlockAttachTo.AXIS_Y);
                        case EAST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.WEST).setValue(ATTACH,SameBlockAttachTo.AXIS_Y);
                        case WEST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.EAST).setValue(ATTACH, SameBlockAttachTo.AXIS_Y);
                        default -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.SOUTH).setValue(ATTACH, SameBlockAttachTo.AXIS_Y);
                    };
                }
                case Z -> {
                    return switch (direction) {
                        case UP -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.DOWN).setValue(ATTACH, SameBlockAttachTo.AXIS_Z);
                        case DOWN -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.UP).setValue(ATTACH, SameBlockAttachTo.AXIS_Z);
                        case SOUTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.NORTH).setValue(ATTACH, SameBlockAttachTo.AXIS_Z);
                        case EAST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.WEST).setValue(ATTACH,SameBlockAttachTo.AXIS_Z);
                        case WEST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.EAST).setValue(ATTACH, SameBlockAttachTo.AXIS_Z);
                        default -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.SOUTH).setValue(ATTACH, SameBlockAttachTo.AXIS_Z);
                    };
                }
            }

        } else {
            return switch (direction) {
                case UP -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.DOWN).setValue(ATTACH, SameBlockAttachTo.NO_ATTACH);
                case DOWN -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(FACING, Direction.UP).setValue(ATTACH, SameBlockAttachTo.NO_ATTACH);
                case SOUTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.NORTH).setValue(ATTACH, SameBlockAttachTo.NO_ATTACH);
                case EAST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.WEST).setValue(ATTACH, SameBlockAttachTo.NO_ATTACH);
                case WEST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(FACING, Direction.EAST).setValue(ATTACH, SameBlockAttachTo.NO_ATTACH);
                default -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(FACING, Direction.SOUTH).setValue(ATTACH, SameBlockAttachTo.NO_ATTACH);
            };
        }
        return blockState;
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return super.rotate(pState, pRotation);
    }

    @Override
    public BlockState mirror(BlockState pState, Mirror pMirror) {
        return super.mirror(pState, pMirror);
    }
}
