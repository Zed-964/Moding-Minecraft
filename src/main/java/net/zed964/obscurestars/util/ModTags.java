package net.zed964.obscurestars.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.zed964.obscurestars.ObscureStars;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> ROCKET_COMPONENT_TIER1 = tag("rocket_component_tier1");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ObscureStars.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ObscureStars.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}
