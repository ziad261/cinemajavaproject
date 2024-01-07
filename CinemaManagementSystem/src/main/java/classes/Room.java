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
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author alielrogbany
 */
public class Room implements Serializable {
    public static final long serialVersionUID = 6529685098267757690L;
    
    private int id;
    private Screen screen;
    private Movie movie;
    private ArrayList<Seat> seats;

    public Room(int id, Screen screen, Movie movie, ArrayList<Seat> seats) {
        this.id = id;
        this.screen = screen;
        this.movie = movie;
        this.seats = seats;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
    
    protected boolean roomExists(List<Room> roomList) {
        for (Room room : roomList) {
            if (room.equals(this)) {
                return true;
            } else {
            }
        }
        return false;
    }

    public static ArrayList<Room> readRoomsFromFile() {
        ArrayList<Room> roomList = new ArrayList<>();

        File file = new File("rooms.dat");

        if (file.length() == 0) {
            // File is empty, return an empty list
            return roomList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            roomList = (ArrayList<Room>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return roomList;
    }
    
    protected static boolean writeRoomToFile(List<Room> roomsList) {
       try {
           File file = new File("rooms.dat");

           if (!file.exists()) {
               System.out.println("File not found. Creating a new file.");
               file.createNewFile();
           }

           try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
               oos.writeObject(roomsList);
               return true;
           }
       } catch (IOException e) {
       }
       return false;
    }
    
    public static ArrayList<Room> GetRoomsByMovie(ArrayList<Room> roomsList, Movie movie){
        ArrayList<Room> returnedRooms = new ArrayList<Room>();
        for (Room room : roomsList){
            if (room.getMovie().equals(movie)){
                returnedRooms.add(room);
            }
        }
        return returnedRooms;
    }
    
    public static ComboBoxModel<Room> GetRoomsCombobox(ArrayList<Room> roomsList){
        ComboBoxModel<Room> model = new DefaultComboBoxModel<>();
        if (roomsList.size() > 0){
            model = new DefaultComboBoxModel<>(roomsList.toArray(new Room[0]));
        }
        return model;
    }
    
    public boolean AddRoom(){
        ArrayList<Room> roomsList = readRoomsFromFile();
        if (!roomExists(roomsList)){
            roomsList.add(this);
            writeRoomToFile(roomsList);
            return true;
        }
        return false;
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
        final Room other = (Room) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return id + "";
    }
     
     
}
