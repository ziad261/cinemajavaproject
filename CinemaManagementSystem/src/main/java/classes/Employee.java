package classes;

import java.util.ArrayList;

public class Employee extends User{
    public static final long serialVersionUID = -4350323773326372865L;
    
    public Employee(String name, String username, String email, String password) {
        super(name, username, email, password);
    }
    
    public Employee(String username, String password){
        super(username, password);
    }

    public Employee(User user){
        super(user.name, user.username, user.email, user.password);
    }

    public Employee Login(){
        ArrayList<User> usersList = User.readUsersFromFile("employees.dat");
        for (User user : usersList)
        {
            if (user.username.equals(this.username))
            {
                if (user.password.equals(this.password))
                {
                    return new Employee(user);
                }
                System.out.println("Incorrect Credentials!");
                return null;
            }
        }
        return null;
    }

    public boolean Register(){
        ArrayList<User> customersList = User.readUsersFromFile("employees.dat");
        if (!User.isUsernameTaken(customersList, this.username)){
            customersList.add(this);
            User.writeUserToFile(customersList, "employees.dat");
            return true;
        }
        else{
            System.out.println("Customer already exists!");
            return false;
        }
    }
    
    public boolean AddGenre(Genre genre){
        return genre.AddGenre();
    }
    
    public boolean AddMovie(Movie movie){
        return movie.AddMovie();
    }
    
    public boolean AddRoom(Room room){
        return room.AddRoom();
    }
}
