package net.zed964.testmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    //TABS for creative
    public static final CreativeModeTab TESTMOD_TAB = new CreativeModeTab("testmod_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.RAW_URANIUM.get());
        }
    };
}
