package com.aiden.librarysoftwarefx.managers;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static Map<String, Scene> scenes = new HashMap<>();
    private static Stage stage;


    public SceneManager() { }

    public static void setStage(Stage inputStage) {
        stage = inputStage;
    }

    public static void addScene(String name, Scene scene) {
        scenes.put(name, scene);
    }
    public static void switchToScene(String name) {
        if(!scenes.containsKey(name)) {
            throw new IllegalArgumentException("Scene " + name + " not located.");
        }
        stage.setScene(scenes.get(name));
        stage.show();
    }

}
