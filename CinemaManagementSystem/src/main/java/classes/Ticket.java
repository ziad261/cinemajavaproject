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
import java.util.*;

/**
 *
 * @author alielrogbany
 */
public class Ticket implements Serializable {
    private static final long serialVersionUID = 3570081285649915464L;
    
    private Movie movie;
    private Room room;
    private Seat seat;
    private String time;
    private Customer customer;

    public Ticket(Movie movie, Room room, Seat seat, String time) {
        this.movie = movie;
        this.room = room;
        this.seat = seat;
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public Seat getSeat() {
        return seat;
    }

    public String getTime() {
        return time;
    }
    
    public Customer getCustomer(){
        return customer;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    
    protected boolean IsTicketBooked(ArrayList<Ticket> ticketsList){
        for (Ticket ticket : ticketsList){
            if (ticket.getMovie().equals(this.movie) && ticket.getRoom().equals(this.room) && ticket.getSeat().equals(this.seat) && ticket.getTime().equals(this.time)){
                return true;
            }
        }
        return false;
    }
    
    protected static ArrayList<Ticket> readTicketsFromFile() {
        ArrayList<Ticket> ticketsList = new ArrayList<Ticket>();

        File file = new File("tickets.dat");

        if (file.length() == 0) {
            // File is empty, return an empty list
            return ticketsList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ticketsList = (ArrayList<Ticket>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return ticketsList;
    }
    
    protected static boolean writeTicketToFile(ArrayList<Ticket> ticketsList) {
        try {
            File file = new File("tickets.dat");

            if (!file.exists()) {
                System.out.println("File not found. Creating a new file.");
                file.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(ticketsList);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean BookTicket(){
        ArrayList<Ticket> ticketsList = new ArrayList<Ticket>();
        ticketsList = readTicketsFromFile();
        if (!this.IsTicketBooked(ticketsList)){
            ticketsList.add(this);
            writeTicketToFile(ticketsList);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Ticket{" + "movie=" + movie + ", room=" + room + ", seat=" + seat + ", time=" + time + ", customer=" + customer + '}';
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
        final Ticket other = (Ticket) obj;
        if (this.seat != other.seat) {
            return false;
        }
        if (!Objects.equals(this.room, other.room)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return Objects.equals(this.movie, other.movie);
    } 
}
