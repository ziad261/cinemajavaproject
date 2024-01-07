/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author alielrogbany
 */
public class Seat implements Serializable {
    public static final long serialVersionUID = 6529685098267757690L;
    
    private int number;

    public Seat(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    public static ComboBoxModel<Seat> GetSeatsCombobox(ArrayList<Seat> seatsList){
        ComboBoxModel<Seat> model = new DefaultComboBoxModel<>();
        if (seatsList.size() > 0){
            model = new DefaultComboBoxModel<>(seatsList.toArray(new Seat[0]));
        }
        return model;
    }

    @Override
    public String toString() {
        return number + "";
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
        final Seat other = (Seat) obj;
        return this.number == other.number;
    }
    
    
}
