package io.github.et.generator.conponent;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class EventButton extends Label {
    public EventButton(String text) throws FileNotFoundException {
        super(text);
        this.setFont(Font.loadFont(Objects.requireNonNull(new FileInputStream("./zh-cn.ttf")),30));
        this.setStyle(
                "-fx-background-color:white;" +
                        "-fx-background-radius:20;" +
                        "-fx-text-fill:brown;" +
                        "-fx-border-radius:20;" +
                        "-fx-padding: 10 20;"
        );
    }

}
