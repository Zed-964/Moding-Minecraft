package net.zed964.obscurestars.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesManager {
    public static void createFolder(String name) {
        try {
            Files.createDirectories(Paths.get(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
