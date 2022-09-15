package net.zed964.testmod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zed964.testmod.TestMod;
import net.zed964.testmod.item.ModCreativeTab;
import net.zed964.testmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    //List of blocks creating in this mod
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);

    public static final RegistryObject<Block> ASTEROID_BLOCK = registerBlock("asteroid_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_COAL_ORE = registerBlock("asteroid_coal_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_IRON_ORE = registerBlock("asteroid_iron_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_COPPER_ORE = registerBlock("asteroid_copper_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_GOLD_ORE = registerBlock("asteroid_gold_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_REDSTONE_ORE = registerBlock("asteroid_redstone_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_EMERALD_ORE = registerBlock("asteroid_emerald_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_LAPIS_LAZULI_ORE = registerBlock("asteroid_lapis_lazuli_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_DIAMOND_ORE = registerBlock("asteroid_diamond_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_QUARTZ_ORE = registerBlock("asteroid_quartz_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> ASTEROID_URANIUM_ORE = registerBlock("asteroid_uranium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);
    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(9f).requiresCorrectToolForDrops()), ModCreativeTab.TESTMOD_TAB);


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
