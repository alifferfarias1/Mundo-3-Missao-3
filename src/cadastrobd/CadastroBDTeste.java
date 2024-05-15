package cadastrobd;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

import cadastrobd.model.Person;
import cadastrobd.model.PersonDAO;

/**
 * This class contains test cases for the database operations.
 * It demonstrates adding, updating, deleting, and displaying persons.
 * It includes both physical and legal persons.
 * @author Aliffer
 */
public class DatabaseTest {
    
    private static final Logger LOGGER = Logger.getLogger(DatabaseTest.class.getName());
    
    private final PersonDAO personDAO;
    
    public DatabaseTest() {
        personDAO = new PersonDAO();
    }
    
    private void run() {
        Person person = new Person(null, "John Doe", "123 Main St", "New York",
            "NY", "555-5555", "john.doe@example.com", "123456789");

        if (person.getName() == null || person.getName().trim().isEmpty()) {
            System.out.println("'name' cannot be empty or null.");
            return; 
        }
        
        try {
            System.out.println("---------------------------------");
            System.out.println("Person added with ID: " + personDAO.add(person));
            System.out.println("---------------------------------");
            person.display();
            person.setCity("Los Angeles");
            person.setState("CA");
            personDAO.update(person);
            System.out.println("---------------------------------");
            System.out.println("Person updated.");
            person.display();
            ArrayList<Person> personList = personDAO.getAllPersons();
            System.out.println("---------------------------------");
            System.out.println("Displaying all persons:");
            for (Person p : personList) {
                System.out.println("---------------------------------");
                p.display();
            }
            System.out.println("---------------------------------");
            personDAO.delete(person);
            System.out.println("---------------------------------");
            System.out.println("Person deleted.");
            personDAO.close();
        }
        catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
    }
    
    public static void main(String[] args) {
        new DatabaseTest().run();
    }
    
}
