package net.zed964.obscurestars.item;

import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zed964.obscurestars.ObscureStars;
import net.zed964.obscurestars.fluid.ModFluids;

public class ModItems {
    //List of items creating in this mod
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ObscureStars.MOD_ID);

    public static final RegistryObject<Item> ASTEROID_PIECE = ITEMS.register("asteroid_piece",() -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));

    //Raw
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> RAW_ALUMINUM = ITEMS.register("raw_aluminum", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));

    //Ingots
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> PLATINIUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));

    //Buckets
    public static final RegistryObject<Item> HYDROGEN_BUCKET = ITEMS.register("hydrogen_bucket", () -> new BucketItem(ModFluids.HYDROGEN_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));
    public static final RegistryObject<Item> FLUOR_BUCKET = ITEMS.register("fluor_bucket", () -> new BucketItem(ModFluids.FLUOR_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));
    public static final RegistryObject<Item> HYDRAZINE_BUCKET = ITEMS.register("hydrazine_bucket", () -> new BucketItem(ModFluids.HYDRAZINE_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));
    public static final RegistryObject<Item> UDMH_BUCKET = ITEMS.register("udmh_bucket", () -> new BucketItem(ModFluids.UDMH_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));
    public static final RegistryObject<Item> OXYGEN_BUCKET = ITEMS.register("oxygen_bucket", () -> new BucketItem(ModFluids.OXYGEN_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));
    public static final RegistryObject<Item> KEROSENE_BUCKET = ITEMS.register("kerosene_bucket", () -> new BucketItem(ModFluids.KEROSENE_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));
    public static final RegistryObject<Item> NITRIC_ACID_BUCKET = ITEMS.register("nitric_acid_bucket", () -> new BucketItem(ModFluids.NITRIC_ACID_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));
    public static final RegistryObject<Item> NITROGEN_PEROXIDE_BUCKET = ITEMS.register("nitrogen_peroxide_bucket", () -> new BucketItem(ModFluids.NITROGEN_PEROXIDE_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
