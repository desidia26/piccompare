package org.goonsquad.picompare.file.utils;

import org.goonsquad.picompare.config.Config;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class FileUtils {

    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    public static File getRootFolder() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.showOpenDialog(null);
        return new File(fileChooser.getSelectedFile().getPath());
    }

    public static List<String> getImages(File root) throws IOException {
        PicompareFileVisitor visitor = new PicompareFileVisitor();
        Files.walkFileTree(Paths.get(root.getAbsolutePath()), visitor);
        return visitor.getPictures();
    }
}
