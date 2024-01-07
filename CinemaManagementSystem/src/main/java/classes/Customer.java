package classes;

import java.util.ArrayList;

public class Customer extends User {
    public static final long serialVersionUID = 4573431032693225828L;

    public Customer(String name, String username, String email, String password) {
        super(name, username, email, password);
    }
    
    public Customer(String username, String password){
        super(username, password);
    }

    public Customer(User user){
        super(user.name, user.username, user.email, user.password);
    }

    public Customer Login(){
        ArrayList<User> usersList = User.readUsersFromFile("customers.dat");
        for (User user : usersList)
        {
            if (user.username.equals(this.username))
            {
                if (user.password.equals(this.password))
                {
                    return new Customer(user);
                }
                System.out.println("Incorrect Credentials!");
                return null;
            }
        }
        return null;
    }

    public boolean Register(){
        ArrayList<User> customersList = User.readUsersFromFile("customers.dat");
        if (!User.isUsernameTaken(customersList, this.username)){
            customersList.add(this);
            User.writeUserToFile(customersList, "customers.dat");
            return true;
        }
        else{
            System.out.println("Customer already exists!");
            return false;
        }
    }
    
    public boolean BuyTicket(Ticket ticket){
        ticket.setCustomer(this);
        return ticket.BookTicket();
    }
    
    public boolean BuySnack(Slushy slushy){
        slushy.setCustomer(this);
        return slushy.Buy();
    }
    
    public boolean BuySnack(Popcorn popcorn){
        popcorn.setCustomer(this);
        return popcorn.Buy();
    }
    
    public boolean BuySnack(Nacho nacho){
        nacho.setCustomer(this);
        return nacho.Buy();
    }
    
    public boolean BuySnack(SoftDrink softDrink){
        softDrink.setCustomer(this);
        return softDrink.Buy();
    }
    
    public ArrayList<Ticket> GetTickets(){
        ArrayList<Ticket> allTickets = Ticket.readTicketsFromFile();
        ArrayList<Ticket> customerTickets = new ArrayList<Ticket>();
        for (Ticket ticket : allTickets){
            if (ticket.getCustomer().equals(this)){
                customerTickets.add(ticket);
            }
        }
        return customerTickets;
    }
}
