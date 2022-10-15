package net.zed964.obscurestars.block.building;

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
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HorizontalStairsBlock extends Block implements SimpleWaterloggedBlock {
    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    protected static final VoxelShape VERTICAL_SLAB_NORTH_AABB = Block.box(0, 0, 0, 16, 16, 8);
    protected static final VoxelShape VERTICAL_HALF_SLAB_NORTH_AABB = Block.box(8, 0, 8, 16, 16, 16);

    protected static final VoxelShape VERTICAL_SLAB_EAST_AABB = Block.box(8, 0, 0, 16, 16, 16);
    protected static final VoxelShape VERTICAL_HALF_SLAB_EAST_AABB = Block.box(0, 0, 8, 8, 16, 16);

    protected static final VoxelShape VERTICAL_SLAB_WEST_AABB = Block.box(0, 0, 0, 8, 16, 16);
    protected static final VoxelShape VERTICAL_HALF_SLAB_WEST_AABB = Block.box(8, 0, 0, 16, 16, 8);

    protected static final VoxelShape VERTICAL_SLAB_SOUTH_AABB = Block.box(0, 0, 8, 16, 16, 16);
    protected static final VoxelShape VERTICAL_HALF_SLAB_SOUTH_AABB = Block.box(0, 0, 0, 8, 16, 8);


    public HorizontalStairsBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, Boolean.FALSE));
    }

    @Override
    public boolean useShapeForLightOcclusion(@NotNull BlockState pState) {
        return true;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, WATERLOGGED);
    }

    private static VoxelShape makaShape(VoxelShape shape1, VoxelShape shape2) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, shape1, BooleanOp.OR);
        shape = Shapes.join(shape, shape2, BooleanOp.OR);

        return shape;
    }

    @Override
    public @NotNull VoxelShape getShape(BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        Direction direction = pState.getValue(FACING);
        return switch (direction) {
            case EAST -> makaShape(VERTICAL_SLAB_EAST_AABB, VERTICAL_HALF_SLAB_EAST_AABB);
            case WEST -> makaShape(VERTICAL_SLAB_WEST_AABB, VERTICAL_HALF_SLAB_WEST_AABB);
            case SOUTH -> makaShape(VERTICAL_SLAB_SOUTH_AABB, VERTICAL_HALF_SLAB_SOUTH_AABB);
            default -> makaShape(VERTICAL_SLAB_NORTH_AABB, VERTICAL_HALF_SLAB_NORTH_AABB);
        };
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        BlockPos blockPos = pContext.getClickedPos();
        FluidState fluidState = pContext.getLevel().getFluidState(blockPos);
        BlockState blockState = this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        Direction directionPlacement = pContext.getHorizontalDirection();

        return switch (directionPlacement) {
            case EAST -> blockState.setValue(FACING, Direction.EAST);
            case WEST -> blockState.setValue(FACING, Direction.WEST);
            case SOUTH -> blockState.setValue(FACING, Direction.SOUTH);
            default -> blockState.setValue(FACING, Direction.NORTH);
        };
    }

    @Override
    public @NotNull FluidState getFluidState(BlockState pState) {
        return pState.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(pState);
    }
}
