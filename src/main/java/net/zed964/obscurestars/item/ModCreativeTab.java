package net.zed964.obscurestars.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    //TABS for creative
    public static final CreativeModeTab OBSCURESTARS_TAB = new CreativeModeTab("obscurestars_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_URANIUM.get());
        }
    };

    public static final CreativeModeTab OBSCURESTARS_TAB_ROCKET_COMPONENT = new CreativeModeTab("obscurestars_tab_rocket_component") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_URANIUM.get());
        }
    };
}
