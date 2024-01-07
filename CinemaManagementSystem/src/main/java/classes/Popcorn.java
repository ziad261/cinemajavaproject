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

/**
 *
 * @author Ali
 */
public class Popcorn extends Snack implements Serializable, IBuySnack {
    private static final long serialVersionUID = 3570081285649915464L;
    
    private String flavor;

    public Popcorn() {
    }

    public Popcorn(String name, double price, String flavor) {
        super(name, price);
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    
    protected static ArrayList<Popcorn> readPopcornsFromFile() {
        ArrayList<Popcorn> popcornsList = new ArrayList<Popcorn>();

        File file = new File("popcorns.dat");

        if (file.length() == 0) {
            // File is empty, return an empty list
            return popcornsList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            popcornsList = (ArrayList<Popcorn>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return popcornsList;
    }
    
    protected static boolean writePopcornToFile(ArrayList<Popcorn> popcornsList) {
        try {
            File file = new File("popcorns.dat");

            if (!file.exists()) {
                System.out.println("File not found. Creating a new file.");
                file.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(popcornsList);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Buy(){
        ArrayList<Popcorn> popcornsList = readPopcornsFromFile();
        popcornsList.add(this);
        return writePopcornToFile(popcornsList);
    }
}
