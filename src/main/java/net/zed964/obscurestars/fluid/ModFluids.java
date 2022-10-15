package net.zed964.obscurestars.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zed964.obscurestars.ObscureStars;
import net.zed964.obscurestars.block.ModBlocks;
import net.zed964.obscurestars.item.ModItems;

public class ModFluids {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, ObscureStars.MOD_ID);

    public static final RegistryObject<FlowingFluid> HYDROGEN_FLUID = FLUIDS.register("honey_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.HYDROGEN_PROPERTIES));

    public static final RegistryObject<FlowingFluid> HYDROGEN_FLOWING = FLUIDS.register("honey_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.HYDROGEN_PROPERTIES));

    public static final ForgeFlowingFluid.Properties HYDROGEN_PROPERTIES = new ForgeFlowingFluid.Properties(() -> HYDROGEN_FLUID.get(), () -> HYDROGEN_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).density(71).temperature(20).viscosity(6).overlay(WATER_OVERLAY_RL).color(0xffcac4c4)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.HYDROGEN_BLOCK.get()).bucket(() -> ModItems.HYDROGEN_BUCKET.get());

    public static final RegistryObject<LiquidBlock> HYDROGEN_BLOCK = ModBlocks.BLOCKS.register("honey", () -> new LiquidBlock(() -> ModFluids.HYDROGEN_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
