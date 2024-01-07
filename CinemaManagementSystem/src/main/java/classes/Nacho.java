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
public class Nacho extends Snack implements Serializable, IBuySnack {
    private static final long serialVersionUID = 3570081285649915464L;
    
    private boolean withCheese;

    public Nacho() {
    }

    public Nacho(String name, double price, boolean withCheese) {
        super(name, price);
        this.withCheese = withCheese;
    }
    
    public boolean isWithCheese() {
        return withCheese;
    }

    public void setWithCheese(boolean withCheese) {
        this.withCheese = withCheese;
    }
    
    public static ArrayList<Nacho> readNachosFromFile() {
        ArrayList<Nacho> nachosList = new ArrayList<Nacho>();

        File file = new File("nachos.dat");

        if (file.length() == 0) {
            // File is empty, return an empty list
            return nachosList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            nachosList = (ArrayList<Nacho>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return nachosList;
    }
    
    protected static boolean writeNachoToFile(ArrayList<Nacho> nachosList) {
        try {
            File file = new File("nachos.dat");

            if (!file.exists()) {
                System.out.println("File not found. Creating a new file.");
                file.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(nachosList);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean Buy(){
        ArrayList<Nacho> nachosList = readNachosFromFile();
        nachosList.add(this);
        return writeNachoToFile(nachosList);
    }
}
