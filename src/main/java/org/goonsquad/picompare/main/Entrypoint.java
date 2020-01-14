package org.goonsquad.picompare.main;

import org.goonsquad.picompare.config.Config;
import org.goonsquad.picompare.file.utils.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


public class Entrypoint {

    private static final Logger LOGGER = Logger.getLogger(Entrypoint.class.getName());

    public static void main(String[] args) throws IOException {
        File mainDir = FileUtils.getRootFolder();
        List<String> pictures = FileUtils.getImages(mainDir);
        pictures.forEach(picture -> {
            System.out.println(picture);
        });
    }

}
