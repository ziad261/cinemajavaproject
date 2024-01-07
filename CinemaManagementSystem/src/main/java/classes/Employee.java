package classes;

import java.util.ArrayList;
import java.util.HashMap;

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

        if(Global.Users.containsKey(username))
            if(Global.Users.get(username).password.equals(password))
                return  new Employee(Global.Users.get(username));

        return null;
    }

    public boolean Register(){

        if (!Global.Users.containsKey(username))
        {
            Global.Users.put(username,this);
            User.writeUserToFile("employees.dat");
            return true;
        }

        return false;
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
