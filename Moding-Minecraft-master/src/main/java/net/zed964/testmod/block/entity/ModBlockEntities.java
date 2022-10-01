package net.zed964.testmod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zed964.testmod.TestMod;
import net.zed964.testmod.block.ModBlocks;
import net.zed964.testmod.block.entity.custom.ControlleurBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, TestMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<ControlleurBlockEntity>> CONTROLLEUR_BLOCK_ENTITY = BLOCK_ENTITIES.register("controlleur_block_entity", () -> BlockEntityType.Builder.of(ControlleurBlockEntity::new, ModBlocks.CONTROLLEUR_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
