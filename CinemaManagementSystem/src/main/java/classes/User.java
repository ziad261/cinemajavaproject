package classes;

import java.io.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Objects;

public class User implements Serializable {
    public static final long serialVersionUID = 6529685098267757690L;

    private static int lastAssignedId = 0;
    
    String name;
    String username;
    String email;
    String password;
    private int age;
    private int phoneNumber;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public User(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String name, String username, String email, String password, int age, int phoneNumber) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }
    
    public String GetUsername(){
        return username;
    }
    
    public String GetName(){
        return name;
    }
    
    public String GetEmail(){
        return email;
    }

    protected static boolean isUsernameTaken(ArrayList<User> customersList, String username) {
        for (User user : customersList) {
            if (user.username.equals(username)) {
                return true;
            }
        }
        return false;
    }

    protected static ArrayList<User> readUsersFromFile(String path) {
        ArrayList<User> userList = new ArrayList<User>();

        File file = new File(path);

        if (file.length() == 0) {
            // File is empty, return an empty list
            return userList;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            userList = (ArrayList<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    protected static boolean writeUserToFile(ArrayList<User> userList, String path) {
        try {
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("File not found. Creating a new file.");
                file.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(userList);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", username=" + username + ", email=" + email + '}';
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
        final User other = (User) obj;
        return Objects.equals(this.username, other.username);
    }
}