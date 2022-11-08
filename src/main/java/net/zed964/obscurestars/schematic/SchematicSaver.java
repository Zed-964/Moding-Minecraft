package net.zed964.obscurestars.schematic;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.zed964.obscurestars.util.FilesManager;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;

public class SchematicSaver {
    String name = "Unnamed";
    BlockPos pos1 = null;
    BlockPos pos2 = null;
    Path path = null;

    public SchematicSaver(BlockPos pos1, BlockPos pos2) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        if(pos1 != null && pos2 != null) {
           path = SchematicSaver.create(name, pos1, pos2);
        }
    }

    public SchematicSaver(BlockPos pos1, BlockPos pos2, String pName) {
        this.pos1 = pos1;
        this.pos2 = pos2;
        if(pos1 != null && pos2 != null) {
           path = SchematicSaver.create(pName, pos1, pos2);
        }
    }

    public static Path create(String name, BlockPos pos1, BlockPos pos2) {
        StructureTemplate structure = new StructureTemplate();
        BoundingBox boundingBox = BoundingBox.fromCorners(pos1, pos2);
        BlockPos origin = new BlockPos(boundingBox.minX(), boundingBox.minY(), boundingBox.minZ());
        BlockPos bounds = new BlockPos(boundingBox.getXSpan(), boundingBox.getYSpan(), boundingBox.getZSpan());
        Level level = Minecraft.getInstance().level;

        assert level != null;
        structure.fillFromWorld(level, origin, bounds, false, Blocks.AIR);

        String folderPath = "schematics";
        String fileName = name + ".nbt";
        String filePath = folderPath + "/" + fileName;
        FilesManager.createFolder(folderPath);
        Path path = Paths.get(filePath);

        OutputStream outputStream = null;
        try {
            outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE);
            CompoundTag nbtTagCompoundTag = structure.save(new CompoundTag());
            NbtIo.writeCompressed(nbtTagCompoundTag, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                IOUtils.closeQuietly(outputStream);
        }
        return path;
    }

    public Path getPath() {
        return this.path;
    }

}
