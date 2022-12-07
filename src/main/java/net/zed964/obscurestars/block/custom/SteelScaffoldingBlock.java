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
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.zed964.obscurestars.block.properties.BaseFacingTo;
import net.zed964.obscurestars.block.properties.ConnectionFacingTo;
import net.zed964.obscurestars.block.properties.ModBlockStateProperties;
import org.jetbrains.annotations.NotNull;

public class SteelScaffoldingBlock extends GlassBlock implements SimpleWaterloggedBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty BASE = ModBlockStateProperties.BASE;
    public static final EnumProperty<BaseFacingTo> BASE_FACING = ModBlockStateProperties.BASE_FACING_TO;
    public static final BooleanProperty CONNECTION = ModBlockStateProperties.CONNECTION;
    public static final EnumProperty<ConnectionFacingTo> CONNECTION_FACING = ModBlockStateProperties.CONNECTION_FACING_TO;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    private static final VoxelShape BASE_BOTTOM_AABB = Block.box(0, 0, 0, 16, 2, 16);
    private static final VoxelShape BASE_TOP_AABB = Block.box(0, 14, 0, 16, 16, 16);

    private static final VoxelShape SCAFFOLDING_VERTICAL_AABB = Block.box(1, 0, 1, 15, 16, 15);
    private static final VoxelShape SCAFFOLDING_HORIZONTAL_X_AABB = Block.box(0, 1, 1, 16, 15, 15);
    private static final VoxelShape SCAFFOLDING_HORIZONTAL_Z_AABB = Block.box(1, 1, 0, 15, 15, 16);

    private static final VoxelShape BASE_EAST_AABB = Block.box(0, 0, 0, 2, 16, 16);
    private static final VoxelShape BASE_WEST_AABB = Block.box(14, 0, 0, 16, 16, 16);
    private static final VoxelShape BASE_NORTH_AABB = Block.box(0, 0, 14, 16, 16, 16);
    private static final VoxelShape BASE_SOUTH_AABB = Block.box(0, 0, 0, 16, 16, 2);

    private static final VoxelShape CONNECTION_EAST_AABB = Block.box(0, 0, 0, 3, 16, 16);
    private static final VoxelShape CONNECTION_WEST_AABB = Block.box(13, 0, 0, 16, 16, 16);
    private static final VoxelShape CONNECTION_NORTH_AABB = Block.box(0, 0, 13, 16, 16, 16);
    private static final VoxelShape CONNECTION_SOUTH_AABB = Block.box(0, 0, 0, 16, 16, 3);

    public SteelScaffoldingBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(BASE_FACING, BaseFacingTo.NONE).setValue(CONNECTION, Boolean.FALSE).setValue(CONNECTION_FACING, ConnectionFacingTo.NONE).setValue(WATERLOGGED, Boolean.FALSE));
    }

    private VoxelShape makeShape(VoxelShape shape1, VoxelShape shape2) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, shape1, BooleanOp.OR);
        shape = Shapes.join(shape, shape2, BooleanOp.OR);

        return shape;
    }

    private BlockPos clickedBlockPos(Direction direction, BlockPos placementBlock) {
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
    pBuilder.add(AXIS, BASE, BASE_FACING, CONNECTION, CONNECTION_FACING, WATERLOGGED);
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState pState, @NotNull Direction pDirection, @NotNull BlockState pNeighborState, @NotNull LevelAccessor pLevel, @NotNull BlockPos pCurrentPos, @NotNull BlockPos pNeighborPos) {
        if (pNeighborState.getBlock() == this) {
            if (!pState.getValue(CONNECTION)) {
                if (!pState.getValue(BASE)) {
                    switch (pState.getValue(CONNECTION_FACING)) {
                        case NONE -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState;
                                case NORTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH);
                                case SOUTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH);
                                case EAST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.WEST);
                                case WEST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.EAST);
                            };
                        }
                        case NORTH -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE);
                                case SOUTH -> pState;
                                case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_SOUTH);
                                case EAST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_WEST);
                                case WEST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST);
                            };
                        }
                        case SOUTH -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE);
                                case NORTH -> pState;
                                case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_SOUTH);
                                case EAST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST);
                                case WEST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_EAST);
                            };
                        }
                        case EAST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE);
                                case WEST -> pState;
                                case NORTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_EAST);
                                case SOUTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST);
                                case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_WEST);
                            };
                        }
                        case WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE);
                                case EAST -> pState;
                                case NORTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST);
                                case SOUTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_WEST);
                                case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_WEST);
                            };
                        }
                        case NORTH_EAST -> {
                            return switch (pDirection) {
                                case UP, DOWN, SOUTH, WEST -> pState;
                                case NORTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                                case EAST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                            };
                        }
                        case NORTH_WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN, SOUTH, EAST -> pState;
                                case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                                case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                            };
                        }
                        case SOUTH_EAST -> {
                            return switch (pDirection) {
                                case UP, DOWN, NORTH, WEST -> pState;
                                case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                                case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                            };
                        }
                        case SOUTH_WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN, NORTH, EAST -> pState;
                                case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                                case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                            };
                        }
                        case NORTH_SOUTH -> {
                            return switch (pDirection) {
                                case UP, DOWN, NORTH, SOUTH -> pState;
                                case EAST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                                case WEST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                            };
                        }
                        case EAST_WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN, EAST, WEST -> pState;
                                case NORTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                                case SOUTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                            };
                        }
                        case NORTH_EAST_SOUTH -> {
                            return switch (pDirection) {
                                case UP, DOWN, NORTH, SOUTH, WEST -> pState;
                                case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                            };
                        }
                        case EAST_SOUTH_WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN, NORTH, EAST, WEST -> pState;
                                case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                            };
                        }
                        case SOUTH_WEST_NORTH -> {
                            return switch (pDirection) {
                                case UP, DOWN, NORTH, SOUTH, EAST -> pState;
                                case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                            };
                        }
                        case WEST_NORTH_EAST -> {
                            return switch (pDirection) {
                                case UP, DOWN, SOUTH, EAST, WEST -> pState;
                                case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                            };
                        }
                    }
                } else {
                    switch (pState.getValue(CONNECTION_FACING)) {
                        case NONE -> {
                             switch (pDirection) {
                                case UP, DOWN -> {
                                    if (pNeighborState.getValue(CONNECTION) ==  Boolean.TRUE) {
                                        return pState.setValue(BASE, Boolean.FALSE);
                                    } else {
                                        return pState;
                                    }
                                }
                                 case NORTH -> {
                                     return pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH);
                                 }
                                 case SOUTH -> {
                                     return pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH);
                                 }
                                 case EAST -> {
                                     return pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.WEST);
                                 }
                                 case WEST -> {
                                     return pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.EAST);
                                 }
                             }
                        }
                        case NORTH -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case SOUTH -> pState;
                                case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_SOUTH);
                                case EAST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_WEST);
                                case WEST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST);
                            };
                        }
                        case SOUTH -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case NORTH -> pState;
                                case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_SOUTH);
                                case EAST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST);
                                case WEST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_EAST);
                            };
                        }
                        case EAST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case WEST -> pState;
                                case NORTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_EAST);
                                case SOUTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST);
                                case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_WEST);
                            };
                        }
                        case WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case EAST -> pState;
                                case NORTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST);
                                case SOUTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_WEST);
                                case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_WEST);
                            };
                        }
                        case NORTH_EAST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case SOUTH, WEST -> pState;
                                case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                                case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                            };
                        }
                        case NORTH_WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case SOUTH, EAST -> pState;
                                case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                                case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                            };
                        }
                        case SOUTH_EAST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case NORTH, WEST -> pState;
                                case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                                case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                            };
                        }
                        case SOUTH_WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case NORTH, EAST -> pState;
                                case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                                case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                            };
                        }
                        case NORTH_SOUTH -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case NORTH, SOUTH -> pState;
                                case EAST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                                case WEST -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                            };
                        }
                        case EAST_WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case EAST, WEST -> pState;
                                case NORTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                                case SOUTH -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE).setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                            };
                        }
                        case NORTH_EAST_SOUTH -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case NORTH, SOUTH, WEST -> pState;
                                case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                            };
                        }
                        case EAST_SOUTH_WEST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case NORTH, EAST, WEST -> pState;
                                case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                            };
                        }
                        case SOUTH_WEST_NORTH -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case NORTH, SOUTH, EAST -> pState;
                                case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                            };
                        }
                        case WEST_NORTH_EAST -> {
                            return switch (pDirection) {
                                case UP, DOWN -> pState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.FALSE).setValue(CONNECTION, Boolean.TRUE);
                                case SOUTH, EAST, WEST -> pState;
                                case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                            };
                        }
                    }
                }
            } else {
                switch (pState.getValue(CONNECTION_FACING)) {
                    case NORTH -> {
                        return switch (pDirection) {
                            case UP, DOWN -> pState.setValue(BASE, Boolean.FALSE);
                            case SOUTH -> pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_SOUTH);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_WEST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST);
                        };
                    }
                    case SOUTH -> {
                        return switch (pDirection) {
                            case UP, DOWN -> pState.setValue(BASE, Boolean.FALSE);
                            case NORTH -> pState;
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_SOUTH);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_EAST);
                        };
                    }
                    case EAST -> {
                        return switch (pDirection) {
                            case UP, DOWN -> pState.setValue(BASE, Boolean.FALSE);
                            case WEST -> pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_EAST);
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_WEST);
                        };
                    }
                    case WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN -> pState.setValue(BASE, Boolean.FALSE);
                            case EAST -> pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST);
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_WEST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_WEST);
                        };
                    }
                    case NORTH_EAST -> {
                        return switch (pDirection) {
                            case UP, DOWN, SOUTH, WEST -> pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                        };
                    }
                    case NORTH_WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN, SOUTH, EAST -> pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                        };
                    }
                    case SOUTH_EAST -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, WEST -> pState;
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                        };
                    }
                    case SOUTH_WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, EAST -> pState;
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                        };
                    }
                    case NORTH_SOUTH -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, SOUTH -> pState;
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                        };
                    }
                    case EAST_WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN, EAST, WEST -> pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                        };
                    }
                    case NORTH_EAST_SOUTH -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, SOUTH, WEST -> pState;
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                        };
                    }
                    case EAST_SOUTH_WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, EAST, WEST -> pState;
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                        };
                    }
                    case SOUTH_WEST_NORTH -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, SOUTH, EAST -> pState;
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                        };
                    }
                    case WEST_NORTH_EAST -> {
                        return switch (pDirection) {
                            case UP, DOWN, SOUTH, EAST, WEST -> pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.FULL);
                        };
                    }
                }
            }
        } else if (pNeighborState.getBlock() ==  Blocks.AIR) {
            if (pState.getValue(CONNECTION)) {
                switch (pState.getValue(CONNECTION_FACING)) {
                    case NORTH -> {
                        return switch (pState.getValue(BASE_FACING)) {
                            case UP, DOWN, SOUTH, EAST, WEST ->  pState;
                            case NORTH -> pState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(CONNECTION, Boolean.FALSE);
                            case NONE -> pState.setValue(AXIS, Direction.Axis.Z).setValue(CONNECTION, Boolean.FALSE);
                        };
                    }
                    case SOUTH -> {
                        return switch (pState.getValue(BASE_FACING)) {
                            case UP, DOWN, NORTH, EAST, WEST ->  pState;
                            case SOUTH -> pState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(CONNECTION, Boolean.FALSE);
                            case NONE -> pState.setValue(AXIS, Direction.Axis.Z).setValue(CONNECTION, Boolean.FALSE);
                        };
                    }
                    case EAST -> {
                        return switch (pState.getValue(BASE_FACING)) {
                            case UP, DOWN, NORTH, SOUTH, WEST ->  pState;
                            case EAST -> pState.setValue(AXIS, Direction.Axis.X).setValue(BASE, Boolean.TRUE).setValue(CONNECTION, Boolean.FALSE);
                            case NONE -> pState.setValue(AXIS, Direction.Axis.X).setValue(CONNECTION, Boolean.FALSE);
                        };
                    }
                    case WEST -> {
                        return switch (pState.getValue(BASE_FACING)) {
                            case UP, DOWN, NORTH, SOUTH, EAST ->  pState;
                            case WEST -> pState.setValue(AXIS, Direction.Axis.X).setValue(BASE, Boolean.TRUE).setValue(CONNECTION, Boolean.FALSE);
                            case NONE -> pState.setValue(AXIS, Direction.Axis.X).setValue(CONNECTION, Boolean.FALSE);

                        };
                    }
                    case NORTH_EAST -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, EAST ->  pState;
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH);
                        };
                    }
                    case NORTH_WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, WEST ->  pState;
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH);
                        };
                    }
                    case SOUTH_EAST -> {
                        return switch (pDirection) {
                            case UP, DOWN, SOUTH, EAST ->  pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH);
                        };
                    }
                    case SOUTH_WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN, SOUTH, WEST ->  pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH);
                        };
                    }
                    case NORTH_SOUTH -> {
                        return switch (pDirection) {
                            case UP, DOWN, EAST, WEST ->  pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH);
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH);
                        };
                    }
                    case EAST_WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH, SOUTH ->  pState;
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST);
                        };
                    }
                    case NORTH_EAST_SOUTH -> {
                        return switch (pDirection) {
                            case UP, DOWN, EAST ->  pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST);
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_EAST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_SOUTH);
                        };
                    }
                    case EAST_SOUTH_WEST -> {
                        return switch (pDirection) {
                            case UP, DOWN, SOUTH ->  pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_WEST);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_EAST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST);
                        };
                    }
                    case SOUTH_WEST_NORTH -> {
                        return switch (pDirection) {
                            case UP, DOWN, WEST ->  pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_WEST);
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_SOUTH);
                        };
                    }
                    case WEST_NORTH_EAST -> {
                        return switch (pDirection) {
                            case UP, DOWN, NORTH ->  pState;
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_WEST);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_WEST);
                        };
                    }
                    case FULL -> {
                        return switch (pDirection) {
                            case UP, DOWN -> pState;
                            case NORTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.WEST_NORTH_EAST);
                            case SOUTH -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.EAST_SOUTH_WEST);
                            case EAST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH_EAST_SOUTH);
                            case WEST -> pState.setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH_WEST_NORTH);
                        };
                    }
                }
            }
        }
        return pState;
    }


    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState pState, @NotNull BlockGetter pLevel, @NotNull BlockPos pPos, @NotNull CollisionContext pContext) {
        VoxelShape shape;

        if (pState.getValue(BASE)) {
            shape = switch (pState.getValue(BASE_FACING)) {
                case UP -> makeShape(SCAFFOLDING_VERTICAL_AABB, BASE_TOP_AABB);
                case DOWN -> makeShape(SCAFFOLDING_VERTICAL_AABB, BASE_BOTTOM_AABB);
                case NORTH -> makeShape(SCAFFOLDING_HORIZONTAL_Z_AABB, BASE_NORTH_AABB);
                case SOUTH -> makeShape(SCAFFOLDING_HORIZONTAL_Z_AABB, BASE_SOUTH_AABB);
                case EAST -> makeShape(SCAFFOLDING_HORIZONTAL_X_AABB, BASE_EAST_AABB);
                case WEST -> makeShape(SCAFFOLDING_HORIZONTAL_X_AABB, BASE_WEST_AABB);
                case NONE -> Shapes.empty();
            };
        } else {
            shape = switch (pState.getValue(AXIS)) {
                case X -> SCAFFOLDING_HORIZONTAL_X_AABB;
                case Y -> SCAFFOLDING_VERTICAL_AABB;
                case Z -> SCAFFOLDING_HORIZONTAL_Z_AABB;
            };
        }

        if (pState.getValue(CONNECTION)) {
            return switch (pState.getValue(CONNECTION_FACING)) {
                case NORTH -> makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_NORTH_AABB);
                case SOUTH -> makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_SOUTH_AABB);
                case EAST -> makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_EAST_AABB);
                case WEST -> makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_WEST_AABB);
                case NORTH_EAST -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_NORTH_AABB), CONNECTION_EAST_AABB);
                case NORTH_WEST -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_NORTH_AABB), CONNECTION_WEST_AABB);
                case SOUTH_EAST -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_SOUTH_AABB), CONNECTION_EAST_AABB);
                case SOUTH_WEST -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_SOUTH_AABB), CONNECTION_WEST_AABB);
                case NORTH_SOUTH -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_NORTH_AABB), CONNECTION_SOUTH_AABB);
                case EAST_WEST -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_EAST_AABB), CONNECTION_WEST_AABB);
                case NORTH_EAST_SOUTH -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_NORTH_AABB), makeShape(CONNECTION_EAST_AABB, CONNECTION_SOUTH_AABB));
                case EAST_SOUTH_WEST -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_EAST_AABB), makeShape(CONNECTION_SOUTH_AABB, CONNECTION_WEST_AABB));
                case SOUTH_WEST_NORTH -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_SOUTH_AABB), makeShape(CONNECTION_WEST_AABB, CONNECTION_NORTH_AABB));
                case WEST_NORTH_EAST -> makeShape(makeShape(SCAFFOLDING_VERTICAL_AABB, CONNECTION_WEST_AABB), makeShape(CONNECTION_NORTH_AABB, CONNECTION_EAST_AABB));
                case FULL -> Shapes.block();
                case NONE -> Shapes.empty();
            };
        } else {
            return shape;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        Direction direction = pContext.getClickedFace();
        BlockPos blockPos = pContext.getClickedPos();
        BlockPos blockPosClicked = clickedBlockPos(direction, blockPos);
        FluidState fluidState = pContext.getLevel().getFluidState(blockPos);
        BlockState blockState = this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
        BlockState blockStateClicked = pContext.getLevel().getBlockState(blockPosClicked);

        if (blockStateClicked.getBlock() ==  this) {
            switch (blockStateClicked.getValue(AXIS)) {
                case X -> {
                    return switch (direction) {
                        case UP -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.DOWN);
                        case DOWN -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.UP);
                        case NORTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.NORTH).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH);
                        case SOUTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.SOUTH).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH);
                        case EAST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(CONNECTION_FACING, ConnectionFacingTo.EAST);
                        case WEST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(CONNECTION_FACING, ConnectionFacingTo.WEST);
                    };
                }
                case Y -> {
                    return switch (direction) {
                        case UP, DOWN -> blockState.setValue(AXIS, Direction.Axis.Y);
                        case NORTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.NORTH).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH);
                        case SOUTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.SOUTH).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH);
                        case EAST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.EAST).setValue(CONNECTION_FACING, ConnectionFacingTo.EAST);
                        case WEST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.WEST).setValue(CONNECTION_FACING, ConnectionFacingTo.WEST);
                    };
                }
                case Z -> {
                    return switch (direction) {
                        case UP -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.DOWN);
                        case DOWN -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.UP);
                        case NORTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(CONNECTION_FACING, ConnectionFacingTo.NORTH);
                        case SOUTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(CONNECTION_FACING, ConnectionFacingTo.SOUTH);
                        case EAST -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.EAST).setValue(CONNECTION_FACING, ConnectionFacingTo.EAST);
                        case WEST -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.WEST).setValue(CONNECTION_FACING, ConnectionFacingTo.WEST);
                    };
                }
            }
        } else {
            return switch (direction) {
                case UP -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.DOWN);
                case DOWN -> blockState.setValue(AXIS, Direction.Axis.Y).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.UP);
                case NORTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.NORTH);
                case SOUTH -> blockState.setValue(AXIS, Direction.Axis.Z).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.SOUTH);
                case EAST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.EAST);
                case WEST -> blockState.setValue(AXIS, Direction.Axis.X).setValue(BASE, Boolean.TRUE).setValue(BASE_FACING, BaseFacingTo.WEST);
            };
        }
        return blockState;
    }
}


