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
public class Movie implements Serializable {
    private static final long serialVersionUID = 3570081285649915464L;
    
    private String name;
    private Genre genre;
    private int minimumAge;
    private float length;

    public Movie(String name, Genre Genre, int minimumAge, float length) {
        this.name = name;
        this.genre = Genre;
        this.minimumAge = minimumAge;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public float getLength() {
        return length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
    protected boolean MovieExists(ArrayList<Movie> moviesList){
        for (Movie movie : moviesList){
            if (movie.equals(this)){
                return true;
            }
        }
        return false;
    }
    
    protected static ArrayList<Movie> readMoviesFromFile() {
        ArrayList<Movie> moviesList = new ArrayList<Movie>();

        File file = new File("movies.dat");

        if (file.length() == 0) {
            // File is empty, return an empty list
            return moviesList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            moviesList = (ArrayList<Movie>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return moviesList;
    }
    
    protected static boolean writeMovieToFile(ArrayList<Movie> moviesList) {
        try {
            File file = new File("movies.dat");

            if (!file.exists()) {
                System.out.println("File not found. Creating a new file.");
                file.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(moviesList);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean AddMovie(){
        ArrayList<Movie> moviesList = readMoviesFromFile();
        if (!this.MovieExists(moviesList)){
            moviesList.add(this);
            writeMovieToFile(moviesList);
            return true;
        }
        else{
            return false;
        }
    }
    
    public static ComboBoxModel<classes.Movie> GetMovieCombobox(){
        ArrayList<classes.Movie> genresList = readMoviesFromFile();
        ComboBoxModel<classes.Movie> model = new DefaultComboBoxModel<>(genresList.toArray(new classes.Movie[0]));
        return model;
    }
    
    public String toString() {
        return name;
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
        final Movie other = (Movie) obj;
        if (this.minimumAge != other.minimumAge) {
            return false;
        }
        if (this.length != other.length) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.genre, other.genre);
    }
}
