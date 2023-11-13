package com.example.project3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.skin.ComboBoxListViewSkin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieListFile {
    public static MovieListFile create()
    {
        MovieListFile movieListFile = new MovieListFile();
        try {
            File movieList = new File("movies.txt");
            if (movieList.createNewFile()) {
                System.out.println("Yay you made the file.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return movieListFile;
    }

    public MovieListFile using(ObservableList<String> movieList)
    {
        try {
            FileWriter myWriter = new FileWriter("movies.txt");
            BufferedWriter bf = new BufferedWriter(myWriter);
            for (String each : movieList) {
                bf.write(each);
                bf.newLine();
            }
            bf.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return this;
    }

    public static ObservableList<String> read() {
        ObservableList<String> aList = FXCollections.observableArrayList();
        try {
            File movieListFile = new File("movies.txt");
            Scanner scanner = new Scanner(movieListFile);
            while (scanner.hasNextLine()) {
                aList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return aList;
    }
}
