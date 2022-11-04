package net.zed964.obscurestars.schematic;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class SchematicSaver {
    String name = "Unnamed";
    BlockPos pos1 = null;
    BlockPos pos2 = null;

    public SchematicSaver(BlockPos pos1, BlockPos pos2) {
        if(pos1 != null && pos2 != null) {
            SchematicSaver.create(name, pos1, pos2);
        }
    }

    public SchematicSaver(BlockPos pos1, BlockPos pos2, String pName) {
        if(pos1 != null && pos2 != null) {
            SchematicSaver.create(pName, pos1, pos2);
        }
    }

    public static void create(String name, BlockPos pos1, BlockPos pos2) {
        StructureTemplate structure = new StructureTemplate();
        BoundingBox boundingBox = BoundingBox.fromCorners(pos1, pos2);
        BlockPos origin = new BlockPos(boundingBox.minX(), boundingBox.minY(), boundingBox.minZ());
        BlockPos bounds = new BlockPos(boundingBox.getXSpan(), boundingBox.getYSpan(), boundingBox.getZSpan());
        Level level = Minecraft.getInstance().level;


    }
}
