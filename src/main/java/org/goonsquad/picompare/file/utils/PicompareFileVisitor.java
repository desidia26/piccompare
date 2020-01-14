package org.goonsquad.picompare.file.utils;

import org.goonsquad.picompare.config.Config;

import static java.nio.file.FileVisitResult.CONTINUE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PicompareFileVisitor extends SimpleFileVisitor<Path> {

    private static List<String> pictures = new ArrayList<String>();
    private static final Logger LOGGER = Logger.getLogger(PicompareFileVisitor.class.getName());

    @Override
    public FileVisitResult visitFile(Path path,
                                     BasicFileAttributes basicFileAttributes) {
        if(fileIsPicture(path.toString().toLowerCase())){
            pictures.add(path.toString());
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path path, IOException ioException) {
        LOGGER.severe(path.toString()+ " COULD NOT BE VISITED. PROBABLY BECAUSE ACCESS RIGHTS OR SOME SHIT IDUNNO.");
        return CONTINUE;
    }

    private static Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    private static boolean fileIsPicture(String fileName) {
        Optional<String> fileExt = getFileExtension((fileName));
        if(!fileExt.isPresent()) {
            return false;
        }
        else {
            return Config.ALLOWED_TYPES.indexOf(getFileExtension(fileName).get()) != -1;
        }
    }

    public List<String> getPictures() {
        return pictures;
    }

}
