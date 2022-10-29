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
    public static final RegistryObject<Item> RAW_IRIDIUM = ITEMS.register("raw_iridium", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> RAW_TITANIUM = ITEMS.register("raw_titanium", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> RAW_OBSCURIUM = ITEMS.register("raw_obscurium", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> RAW_STARSIUM = ITEMS.register("raw_starsium", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> RAW_ZINC = ITEMS.register("raw_zinc", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> RAW_NICKEL = ITEMS.register("raw_nickel", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));

    //Ingots
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> PLATINIUM_INGOT = ITEMS.register("platinum_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> ALUMINIUM_INGOT = ITEMS.register("aluminum_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> IRIDIUM_INGOT = ITEMS.register("iridium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> OBSCURIUM_INGOT = ITEMS.register("obscurium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> STARSIUM_INGOT = ITEMS.register("starsium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> ZINC_INGOT = ITEMS.register("zinc_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> NICKEL_INGOT = ITEMS.register("nickel_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));

    //Nuggets
    public static final RegistryObject<Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> IRIDIUM_NUGGET = ITEMS.register("iridium_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> TITANIUM_NUGGET = ITEMS.register("titanium_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> OBSCURIUM_NUGGET = ITEMS.register("obscurium_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> STARSIUM_NUGGET = ITEMS.register("starsium_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> ZINC_NUGGET = ITEMS.register("zinc_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> NICKEL_NUGGET = ITEMS.register("nickel_nugget", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));

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
