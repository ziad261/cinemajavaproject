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
public class SoftDrink extends Snack implements Serializable, IBuySnack {
    private static final long serialVersionUID = 3570081285649915464L;
    
    private String brand;

    public SoftDrink() {
    }

    public SoftDrink(String name, double price, String brand) {
        super(name, price);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    protected static ArrayList<SoftDrink> readSoftDrinksFromFile() {
        ArrayList<SoftDrink> softDrinksList = new ArrayList<SoftDrink>();

        File file = new File("softdrinks.dat");

        if (file.length() == 0) {
            // File is empty, return an empty list
            return softDrinksList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            softDrinksList = (ArrayList<SoftDrink>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return softDrinksList;
    }
    
    protected static boolean writeSoftDrinkToFile(ArrayList<SoftDrink> softDrinksList) {
        try {
            File file = new File("softdrinks.dat");

            if (!file.exists()) {
                System.out.println("File not found. Creating a new file.");
                file.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(softDrinksList);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Buy(){
        ArrayList<SoftDrink> softDrinksList = readSoftDrinksFromFile();
        softDrinksList.add(this);
        return writeSoftDrinkToFile(softDrinksList);
    }
}
