package io.github.et.generator.conponent;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;

public class EventButton extends Button {
    public EventButton(String text) throws FileNotFoundException {
        super(text);
        this.setFont(Font.loadFont(Objects.requireNonNull(new FileInputStream("./zh-cn.ttf")),30));
    }

}
