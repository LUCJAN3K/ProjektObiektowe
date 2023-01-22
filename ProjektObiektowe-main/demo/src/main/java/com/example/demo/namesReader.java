package com.example.demo;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import java.lang.Math;
public class namesReader {

    public static String readNames() {
        try {
            List<String> names = new ArrayList<String>();
            List<String> surnames = new ArrayList<String>();
            File namesFile = new File("src/main/java/com/example/demo/names.txt");
            File surnamesFile = new File("src/main/java/com/example/demo/surnames.txt");
            Scanner reader = new Scanner(namesFile);
            Scanner reader2 = new Scanner(surnamesFile);
            while (reader.hasNextLine()) {
                String name = reader.nextLine();
                names.add(name);
            }
            while (reader2.hasNextLine()){
                String surname = reader2.nextLine();
                surnames.add(surname);
            }
            reader.close();
            return names.get((int) ((Math.random() * (names.size() - 0)) + 0))+" "+surnames.get((int) ((Math.random() * (surnames.size() - 0)) + 0));
        } catch (FileNotFoundException error) {
            Platform.exit();
            error.printStackTrace();
        }
        return "Magda Gessler";
    }
}
