/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author alielrogbany
 */
public class Genre implements Serializable {
    private static final long serialVersionUID = 3570081285649915464L;
    
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    protected boolean GenreExists(ArrayList<Genre> genresList){
        for (Genre genre : genresList){
            if (genre.equals(this)){
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList<Genre> readGenresFromFile() {
        ArrayList<Genre> genresList = new ArrayList<Genre>();

        File file = new File("genres.dat");

        if (file.length() == 0) {
            // File is empty, return an empty list
            return genresList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            genresList = (ArrayList<Genre>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return genresList;
    }
    
    protected static boolean writeGenreToFile(ArrayList<Genre> genresList) {
        try {
            File file = new File("genres.dat");

            if (!file.exists()) {
                System.out.println("File not found. Creating a new file.");
                file.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(genresList);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean AddGenre(){
        ArrayList<Genre> genresList = new ArrayList<Genre>();
        genresList = readGenresFromFile();
        if (!this.GenreExists(genresList)){
            genresList.add(this);
            writeGenreToFile(genresList);
            return true;
        }
        else{
            return false;
        }
    }
    
    public static ComboBoxModel<classes.Genre> GetGenreCombobox(){
        ArrayList<classes.Genre> genresList = readGenresFromFile();
        ComboBoxModel<classes.Genre> model = new DefaultComboBoxModel<>(genresList.toArray(new classes.Genre[0]));
        return model;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Genre other = (Genre) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
