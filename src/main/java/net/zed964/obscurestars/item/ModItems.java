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
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));
    public static final RegistryObject<Item> URANIUM_INGOT = ITEMS.register("uranium_ingot", () -> new Item(new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB)));

    //Buckets
    public static final RegistryObject<Item> HYDROGEN_BUCKET = ITEMS.register("hydrogen_bucket", () -> new BucketItem(ModFluids.HYDROGEN_FLUID, new Item.Properties().tab(ModCreativeTab.OBSCURESTARS_TAB).stacksTo(1)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
