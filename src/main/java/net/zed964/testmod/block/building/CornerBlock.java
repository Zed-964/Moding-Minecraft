package net.zed964.testmod.block.building;

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
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zed964.testmod.block.custom.ModBlockStateProperties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CornerBlock extends Block implements SimpleWaterloggedBlock {
    public static final EnumProperty<SpecifyDirectionType> SPECIFY_FACING = ModBlockStateProperties.SPECIFY_DIRECTION_TYPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape DOWN_NORTH_AABB = Block.box(0, 0, 0, 8, 8, 8);
    protected static final VoxelShape DOWN_EAST_AABB = Block.box(8, 0, 0, 16, 8, 8);
    protected static final VoxelShape DOWN_WEST_AABB = Block.box(0, 0, 8, 8, 8, 16);
    protected static final VoxelShape DOWN_SOUTH_AABB = Block.box(8, 0, 8, 16, 8, 16);
    protected static final VoxelShape UP_NORTH_AABB = Block.box(0, 8, 0, 8, 16, 8);
    protected static final VoxelShape UP_EAST_AABB = Block.box(8, 8, 0, 16, 16, 8);
    protected static final VoxelShape UP_WEST_AABB = Block.box(0, 8, 8, 8, 16, 16);
    protected static final VoxelShape UP_SOUTH_AABB = Block.box(8, 8, 8, 16, 16, 16);

    public CornerBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(SPECIFY_FACING, SpecifyDirectionType.DOWN_NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(SPECIFY_FACING, WATERLOGGED);
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        SpecifyDirectionType specifyDirectionType = pState.getValue(SPECIFY_FACING);
        return switch (specifyDirectionType) {
            case DOWN_EAST -> DOWN_EAST_AABB;
            case DOWN_WEST -> DOWN_WEST_AABB;
            case DOWN_SOUTH -> DOWN_SOUTH_AABB;
            case UP_EAST -> UP_EAST_AABB;
            case UP_WEST -> UP_WEST_AABB;
            case UP_SOUTH -> UP_SOUTH_AABB;
            case UP_NORTH -> UP_NORTH_AABB;
            default -> DOWN_NORTH_AABB;
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockPos = pContext.getClickedPos();
        FluidState fluidState = pContext.getLevel().getFluidState(blockPos);
        BlockState blockState = this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        Direction direction = pContext.getClickedFace();
        Direction directionPlacement = pContext.getHorizontalDirection();

        if (direction == Direction.DOWN) {
            return switch (directionPlacement) {
                case EAST -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.UP_EAST);
                case WEST -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.UP_WEST);
                case SOUTH -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.UP_SOUTH);
                default -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.UP_NORTH);
            };
        } else {
            double placementCursor = pContext.getClickLocation().y - blockPos.getY();
            if (placementCursor < 0.5) {
                return switch (directionPlacement) {
                    case EAST -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.DOWN_EAST);
                    case WEST -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.DOWN_WEST);
                    case SOUTH -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.DOWN_SOUTH);
                    default -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.DOWN_NORTH);
                };
            } else {
                return switch (directionPlacement) {
                    case EAST -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.UP_EAST);
                    case WEST -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.UP_WEST);
                    case SOUTH -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.UP_SOUTH);
                    default -> blockState.setValue(SPECIFY_FACING, SpecifyDirectionType.UP_NORTH);
                };
            }
        }
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }
}
