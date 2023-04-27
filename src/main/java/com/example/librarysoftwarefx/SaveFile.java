package com.example.librarysoftwarefx;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;


public class SaveFile {
    private String filePath;

    public SaveFile(String path) {
        this.filePath = path;
    }
    public boolean exists() { return new File(this.filePath).exists(); }

    public void generateFile() throws URISyntaxException, IOException {
        // We need to ensure that the save file will be generated in the same
        // directory as the .jar file, so we get the path of the .jar
        String jarPath = Main.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();
        // Get the path of the directory that the .jar is in
        String jarDirectory = new File(jarPath).getParent();

        // Create the file
        File saveFile = new File(jarDirectory + File.separator + this.filePath);
        saveFile.createNewFile();
    }
}
