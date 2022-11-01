package net.zed964.obscurestars.fluid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
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

    //HYDROGEN
    public static final RegistryObject<FlowingFluid> HYDROGEN_FLUID = FLUIDS.register("hydrogen_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.HYDROGEN_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HYDROGEN_FLOWING = FLUIDS.register("hydrogen_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.HYDROGEN_PROPERTIES));
    public static final ForgeFlowingFluid.Properties HYDROGEN_PROPERTIES = new ForgeFlowingFluid.Properties(() -> HYDROGEN_FLUID.get(), () -> HYDROGEN_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).sound(SoundEvents.BUCKET_FILL).density(71).temperature(20).viscosity(6).color(0xffcac4c4).overlay(WATER_OVERLAY_RL)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.HYDROGEN_BLOCK.get()).bucket(() -> ModItems.HYDROGEN_BUCKET.get());
    public static final RegistryObject<LiquidBlock> HYDROGEN_BLOCK = ModBlocks.BLOCKS.register("hydrogen", () -> new LiquidBlock(() -> ModFluids.HYDROGEN_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    //FLUOR
    public static final RegistryObject<FlowingFluid> FLUOR_FLUID = FLUIDS.register("fluor_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.FLUOR_PROPERTIES));
    public static final RegistryObject<FlowingFluid> FLUOR_FLOWING = FLUIDS.register("fluor_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.FLUOR_PROPERTIES));
    public static final ForgeFlowingFluid.Properties FLUOR_PROPERTIES = new ForgeFlowingFluid.Properties(() -> FLUOR_FLUID.get(), () -> FLUOR_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).sound(SoundEvents.BUCKET_FILL).density(1510).temperature(85).viscosity(6).overlay(WATER_OVERLAY_RL).color(0xffd4af00)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.FLUOR_BLOCK.get()).bucket(() -> ModItems.FLUOR_BUCKET.get());
    public static final RegistryObject<LiquidBlock> FLUOR_BLOCK = ModBlocks.BLOCKS.register("fluor", () -> new LiquidBlock(() -> ModFluids.FLUOR_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    //HYDRAZINE
    public static final RegistryObject<FlowingFluid> HYDRAZINE_FLUID = FLUIDS.register("hydrazine_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.HYDRAZINE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> HYDRAZINE_FLOWING = FLUIDS.register("hydrazine_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.HYDRAZINE_PROPERTIES));
    public static final ForgeFlowingFluid.Properties HYDRAZINE_PROPERTIES = new ForgeFlowingFluid.Properties(() -> HYDRAZINE_FLUID.get(), () -> HYDRAZINE_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).sound(SoundEvents.BUCKET_FILL).density(1000).temperature(275).viscosity(6).overlay(WATER_OVERLAY_RL).color(0xffcfd8dc)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.HYDRAZINE_BLOCK.get()).bucket(() -> ModItems.HYDRAZINE_BUCKET.get());
    public static final RegistryObject<LiquidBlock> HYDRAZINE_BLOCK = ModBlocks.BLOCKS.register("hydrazine", () -> new LiquidBlock(() -> ModFluids.HYDRAZINE_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    //UDMH
    public static final RegistryObject<FlowingFluid> UDMH_FLUID = FLUIDS.register("udmh_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.UDMH_PROPERTIES));
    public static final RegistryObject<FlowingFluid> UDMH_FLOWING = FLUIDS.register("udmh_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.UDMH_PROPERTIES));
    public static final ForgeFlowingFluid.Properties UDMH_PROPERTIES = new ForgeFlowingFluid.Properties(() -> UDMH_FLUID.get(), () -> UDMH_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).sound(SoundEvents.BUCKET_FILL).density(793).temperature(215).viscosity(6).overlay(WATER_OVERLAY_RL).color(0xfff7f7f0)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.UDMH_BLOCK.get()).bucket(() -> ModItems.UDMH_BUCKET.get());
    public static final RegistryObject<LiquidBlock> UDMH_BLOCK = ModBlocks.BLOCKS.register("udmh", () -> new LiquidBlock(() -> ModFluids.UDMH_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    //OXYGEN
    public static final RegistryObject<FlowingFluid> OXYGEN_FLUID = FLUIDS.register("oxygen_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.OXYGEN_PROPERTIES));
    public static final RegistryObject<FlowingFluid> OXYGEN_FLOWING = FLUIDS.register("oxygen_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.OXYGEN_PROPERTIES));
    public static final ForgeFlowingFluid.Properties OXYGEN_PROPERTIES = new ForgeFlowingFluid.Properties(() -> OXYGEN_FLUID.get(), () -> OXYGEN_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).sound(SoundEvents.BUCKET_FILL).density(1141).temperature(90).viscosity(6).overlay(WATER_OVERLAY_RL).color(0xff89cff0)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.OXYGEN_BLOCK.get()).bucket(() -> ModItems.OXYGEN_BUCKET.get());
    public static final RegistryObject<LiquidBlock> OXYGEN_BLOCK = ModBlocks.BLOCKS.register("oxygen", () -> new LiquidBlock(() -> ModFluids.OXYGEN_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    //KEROSENE
    public static final RegistryObject<FlowingFluid> KEROSENE_FLUID = FLUIDS.register("kerosene_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.KEROSENE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> KEROSENE_FLOWING = FLUIDS.register("kerosene_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.KEROSENE_PROPERTIES));
    public static final ForgeFlowingFluid.Properties KEROSENE_PROPERTIES = new ForgeFlowingFluid.Properties(() -> KEROSENE_FLUID.get(), () -> KEROSENE_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).sound(SoundEvents.BUCKET_FILL).density(800).temperature(423).viscosity(6).overlay(WATER_OVERLAY_RL).color(0xfff5f5dc)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.KEROSENE_BLOCK.get()).bucket(() -> ModItems.KEROSENE_BUCKET.get());
    public static final RegistryObject<LiquidBlock> KEROSENE_BLOCK = ModBlocks.BLOCKS.register("kerosene", () -> new LiquidBlock(() -> ModFluids.KEROSENE_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    //NITRIC ACID
    public static final RegistryObject<FlowingFluid> NITRIC_ACID_FLUID = FLUIDS.register("nitric_acid_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.NITRIC_ACID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> NITRIC_ACID_FLOWING = FLUIDS.register("nitric_acid_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.NITRIC_ACID_PROPERTIES));
    public static final ForgeFlowingFluid.Properties NITRIC_ACID_PROPERTIES = new ForgeFlowingFluid.Properties(() -> NITRIC_ACID_FLUID.get(), () -> NITRIC_ACID_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).sound(SoundEvents.BUCKET_FILL).density(1510).temperature(233).viscosity(6).overlay(WATER_OVERLAY_RL).color(0xffffaf00)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.NITRIC_ACID_BLOCK.get()).bucket(() -> ModItems.NITRIC_ACID_BUCKET.get());
    public static final RegistryObject<LiquidBlock> NITRIC_ACID_BLOCK = ModBlocks.BLOCKS.register("nitric_acid", () -> new LiquidBlock(() -> ModFluids.NITRIC_ACID_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    //NITROGEN_PEROXIDE
    public static final RegistryObject<FlowingFluid> NITROGEN_PEROXIDE_FLUID = FLUIDS.register("nitrogen_peroxide_fluid", () -> new ForgeFlowingFluid.Source(ModFluids.NITROGEN_PEROXIDE_PROPERTIES));
    public static final RegistryObject<FlowingFluid> NITROGEN_PEROXIDE_FLOWING = FLUIDS.register("nitrogen_peroxide_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.NITROGEN_PEROXIDE_PROPERTIES));
    public static final ForgeFlowingFluid.Properties NITROGEN_PEROXIDE_PROPERTIES = new ForgeFlowingFluid.Properties(() -> NITROGEN_PEROXIDE_FLUID.get(), () -> NITROGEN_PEROXIDE_FLOWING.get(), FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL).sound(SoundEvents.BUCKET_FILL).density(1450).temperature(263).viscosity(6).overlay(WATER_OVERLAY_RL).color(0xffdeb765)).slopeFindDistance(3).levelDecreasePerBlock(2).block(() -> ModFluids.NITROGEN_PEROXIDE_BLOCK.get()).bucket(() -> ModItems.NITROGEN_PEROXIDE_BUCKET.get());
    public static final RegistryObject<LiquidBlock> NITROGEN_PEROXIDE_BLOCK = ModBlocks.BLOCKS.register("nitrogen_peroxide", () -> new LiquidBlock(() -> ModFluids.NITROGEN_PEROXIDE_FLUID.get(), BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100f).noDrops()));

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}
