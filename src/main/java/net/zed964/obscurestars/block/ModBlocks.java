package net.zed964.obscurestars.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zed964.obscurestars.ObscureStars;
import net.zed964.obscurestars.block.building.*;
import net.zed964.obscurestars.block.custom.SteelScaffoldingBlock;
import net.zed964.obscurestars.item.ModCreativeTab;
import net.zed964.obscurestars.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    //-------------List of blocks creating in this mod
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ObscureStars.MOD_ID);

    //-------------Asteroid and his derived
    public static final RegistryObject<Block> ASTEROID_BLOCK = registerBlock("asteroid_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_STAIRS = registerBlock("asteroid_stairs", () -> new StairBlock(() -> ModBlocks.ASTEROID_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_SLAB = registerBlock("asteroid_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_VERTICAL_SLAB = registerBlock("asteroid_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_HALF_SLAB = registerBlock("asteroid_half_slab", () -> new HalfSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_VERTICAL_HALF_SLAB = registerBlock("asteroid_vertical_half_slab", () -> new VerticalHalfSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_CORNER = registerBlock("asteroid_corner", () -> new CornerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_HORIZONTAL_STAIRS = registerBlock("asteroid_horizontal_stairs", () -> new HorizontalStairsBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_SEMI_BLOCK = registerBlock("asteroid_semi_block", () -> new SemiBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_STAIRS_OUTER = registerBlock("asteroid_stairs_outer", () -> new StairsOuterBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_STAIRS_INNER = registerBlock("asteroid_stairs_inner", () -> new StairsInnerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    //-------------Ressource from Minecraft ( in asteroid )
    public static final RegistryObject<Block> ASTEROID_COAL_ORE = registerBlock("asteroid_coal_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_IRON_ORE = registerBlock("asteroid_iron_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_COPPER_ORE = registerBlock("asteroid_copper_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_GOLD_ORE = registerBlock("asteroid_gold_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_REDSTONE_ORE = registerBlock("asteroid_redstone_ore", () -> new RedStoneOreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_EMERALD_ORE = registerBlock("asteroid_emerald_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_LAPIS_LAZULI_ORE = registerBlock("asteroid_lapis_lazuli_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_DIAMOND_ORE = registerBlock("asteroid_diamond_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_QUARTZ_ORE = registerBlock("asteroid_quartz_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    //-------------Ressource of the mod
    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_URANIUM_ORE = registerBlock("asteroid_uranium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> URANIUM_BLOCK = registerBlock("uranium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_URANIUM_BLOCk = registerBlock("raw_uranium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> PLATINUM_ORE = registerBlock("platinum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_PLATINUM_ORE = registerBlock("asteroid_platinum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> PLATINUM_BLOCK = registerBlock("platinum_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_PLATINUM_BLOCK = registerBlock("raw_platinum_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> ALUMINUM_ORE = registerBlock("aluminum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = registerBlock("deepslate_aluminum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_ALUMINUM_ORE = registerBlock("asteroid_aluminum_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ALUMINUM_BLOCK = registerBlock("aluminum_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_ALUMINUM_BLOCK = registerBlock("raw_aluminum_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> TITANIUM_ORE = registerBlock("titanium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_TITANIUM_ORE = registerBlock("deepslate_titanium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_TITANIUM_ORE = registerBlock("asteroid_titanium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> TITANIUM_BLOCK = registerBlock("titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_TITANIUM_BLOCK = registerBlock("raw_titanium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(8f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> IRIDIUM_ORE = registerBlock("iridium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_IRIDIUM_ORE = registerBlock("deepslate_iridium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_IRIDIUM_ORE = registerBlock("asteroid_iridium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> IRIDIUM_BLOCK = registerBlock("iridium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_IRIDIUM_BLOCK = registerBlock("raw_iridium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> OBSCURIUM_ORE = registerBlock("obscurium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_OBSCURIUM_ORE = registerBlock("deepslate_obscurium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_OBSCURIUM_ORE = registerBlock("asteroid_obscurium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> OBSCURIUM_BLOCK = registerBlock("obscurium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_OBSCURIUM_BLOCK = registerBlock("raw_obscurium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> STARSIUM_ORE = registerBlock("starsium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_STARSIUM_ORE = registerBlock("deepslate_starsium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_STARSIUM_ORE = registerBlock("asteroid_starsium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> STARSIUM_BLOCK = registerBlock("starsium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_STARSIUM_BLOCK = registerBlock("raw_starsium_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(10f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> ZINC_ORE = registerBlock("zinc_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ZINC_ORE = registerBlock("deepslate_zinc_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_ZINC_ORE = registerBlock("asteroid_zinc_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ZINC_BLOCK = registerBlock("zinc_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_ZINC_BLOCK = registerBlock("raw_zinc_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> NICKEL_ORE = registerBlock("nickel_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_NICKEL_ORE = registerBlock("deepslate_nickel_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> ASTEROID_NICKEL_ORE = registerBlock("asteroid_nickel_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> NICKEL_BLOCK = registerBlock("nickel_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> RAW_NICKEL_BLOCK = registerBlock("raw_nickel_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> QUARZ_ORE = registerBlock("quartz_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);
    public static final RegistryObject<Block> DEEPSLATE_QUARTZ_ORE = registerBlock("deepslate_quartz_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB);

    //-------------STRUCTURE ROCKEET COMPONENT
    public static final RegistryObject<Block> ALUMINUM_ALLOY_BLOCK = registerBlock("aluminum_alloy_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_STAIRS = registerBlock("aluminum_alloy_stairs", () -> new StairBlock(() -> ModBlocks.ASTEROID_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_SLAB = registerBlock("aluminum_alloy_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_VERTICAL_SLAB = registerBlock("aluminum_alloy_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_HALF_SLAB = registerBlock("aluminum_alloy_half_slab", () -> new HalfSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_VERTICAL_HALF_SLAB = registerBlock("aluminum_alloy_vertical_half_slab", () -> new VerticalHalfSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_CORNER = registerBlock("aluminum_alloy_corner", () -> new CornerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_HORIZONTAL_STAIRS = registerBlock("aluminum_alloy_horizontal_stairs", () -> new HorizontalStairsBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_SEMI_BLOCK = registerBlock("aluminum_alloy_semi_block", () -> new SemiBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_STAIRS_OUTER = registerBlock("aluminum_alloy_stairs_outer", () -> new StairsOuterBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> ALUMINUM_ALLOY_STAIRS_INNER = registerBlock("aluminum_alloy_stairs_inner", () -> new StairsInnerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(5f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);

    public static final RegistryObject<Block> OBSCURE_ALLOY_BLOCK = registerBlock("obscure_alloy_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_STAIRS = registerBlock("obscure_alloy_stairs", () -> new StairBlock(() -> ModBlocks.ASTEROID_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_SLAB = registerBlock("obscure_alloy_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_VERTICAL_SLAB = registerBlock("obscure_alloy_vertical_slab", () -> new VerticalSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_HALF_SLAB = registerBlock("obscure_alloy_half_slab", () -> new HalfSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_VERTICAL_HALF_SLAB = registerBlock("obscure_alloy_vertical_half_slab", () -> new VerticalHalfSlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_CORNER = registerBlock("obscure_alloy_corner", () -> new CornerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_HORIZONTAL_STAIRS = registerBlock("obscure_alloy_horizontal_stairs", () -> new HorizontalStairsBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_SEMI_BLOCK = registerBlock("obscure_alloy_semi_block", () -> new SemiBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_STAIRS_OUTER = registerBlock("obscure_alloy_stairs_outer", () -> new StairsOuterBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);
    public static final RegistryObject<Block> OBSCURE_ALLOY_STAIRS_INNER = registerBlock("obscure_alloy_stairs_inner", () -> new StairsInnerBlock(BlockBehaviour.Properties.of(Material.STONE).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);

    public static final RegistryObject<Block> STEEL_SCAFFOLDING = registerBlock("steel_scaffolding", () -> new SteelScaffoldingBlock(BlockBehaviour.Properties.copy(Blocks.GLASS).sound(SoundType.NETHERITE_BLOCK).strength(7f).requiresCorrectToolForDrops()), ModCreativeTab.OBSCURESTARS_TAB_ROCKET_COMPONENT);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
