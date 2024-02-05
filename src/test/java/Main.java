import io.github.et.generator.logic.RandomNumber;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        Process pro=Runtime.getRuntime().exec("gtk-launch Generator");
        InputStream a=pro.getInputStream();
        while(a.read()!=-1){
            System.out.println(a.read());
        }
    }

}
