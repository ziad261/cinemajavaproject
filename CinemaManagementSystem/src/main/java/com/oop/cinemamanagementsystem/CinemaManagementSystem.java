package com.oop.cinemamanagementsystem;

import gui.LoginPage;
import classes.*;

import java.util.HashMap;

public class CinemaManagementSystem {

    public static void main(String[] args) {
        User.readUsersFromFile("customers.dat");
        User.readUsersFromFile("employees.dat");
        new LoginPage().setVisible(true);
    }
}
