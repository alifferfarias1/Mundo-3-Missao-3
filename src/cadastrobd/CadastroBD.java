package cadastrobd;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.SQLException;

import cadastrobd.model.Person;
import cadastrobd.model.PersonDAO;
import cadastrobd.model.util.DatabaseCredentials;

/**
 * This class manages the interaction with the user for database operations.
 * It includes functionalities to add, update, delete, and display persons.
 * It handles both physical and legal persons.
 * @author JohnDoe
 */
public class DatabaseManager {
    
    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class.getName());
    private Scanner scanner;
    private PersonDAO personDAO;
    
    public DatabaseManager() {
        scanner = new Scanner(System.in);
        personDAO = new PersonDAO();
    }
    
    private String askForInput(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
    
    private int askForIntInput(String question) {
        System.out.print(question);
        int intValue = 0;
        try {
            intValue = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return intValue;
    }
    
    private void printMenu() {
        System.out.println("\n==============================");
        System.out.println("1 - Add");
        System.out.println("2 - Update");
        System.out.println("3 - Delete");
        System.out.println("4 - Search by ID");
        System.out.println("5 - Display All");
        System.out.println("0 - Exit");
        System.out.println("==============================");
    }
    
    public void run() {
        int option = -1;
        while (option != 0) {
            printMenu();
            option = askForIntInput("CHOOSE: ");
            switch (option) {
                case 1: {
                    System.out.println("P - Physical Person | L - Legal Person");
                    String choice = askForInput("PERSON TYPE: ").toUpperCase();
                    if (choice.equals("P")) {
                        addPhysicalPerson();
                    } else if (choice.equals("L")) {
                        addLegalPerson();
                    } else {
                        System.out.println("Error: Invalid Choice!");
                    }
                    break;
                }
                case 2: {
                    System.out.println("P - Physical Person | L - Legal Person");
                    String choice = askForInput("PERSON TYPE: ").toUpperCase();
                    if (choice.equals("P")) {
                        updatePhysicalPerson();
                    } else if (choice.equals("L")) {
                        updateLegalPerson();
                    } else {
                        System.out.println("Error: Invalid Choice!");
                    }
                    break;
                }
                case 3: {
                    System.out.println("P - Physical Person | L - Legal Person");
                    String choice = askForInput("PERSON TYPE: ").toUpperCase();
                    if (choice.equals("P")) {
                        deletePhysicalPerson();
                    } else if (choice.equals("L")) {
                        deleteLegalPerson();
                    } else {
                        System.out.println("Error: Invalid Choice!");
                    }
                    break;
                }
                case 4: {
                    System.out.println("P - Physical Person | L - Legal Person");
                    String choice = askForInput("PERSON TYPE: ").toUpperCase();
                    if (choice.equals("P")) {
                        searchPhysicalPersonById();
                    } else if (choice.equals("L")) {
                        searchLegalPersonById();
                    } else {
                        System.out.println("Error: Invalid Choice!");
                    }
                    break;
                }
                case 5: {
                    displayAllPersons();
                    break;
                }
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Option!");
            }
        }
    }
    
    private void addPhysicalPerson() {
        // Implement logic to add a physical person
    }
    
    private void addLegalPerson() {
        // Implement logic to add a legal person
    }
    
    private void updatePhysicalPerson() {
        // Implement logic to update a physical person
    }
    
    private void updateLegalPerson() {
        // Implement logic to update a legal person
    }
    
    private void deletePhysicalPerson() {
        // Implement logic to delete a physical person
    }
    
    private void deleteLegalPerson() {
        // Implement logic to delete a legal person
    }
    
    private void searchPhysicalPersonById() {
        // Implement logic to search for a physical person by ID
    }
    
    private void searchLegalPersonById() {
        // Implement logic to search for a legal person by ID
    }
    
    private void displayAllPersons() {
        // Implement logic to display all persons
    }
    
    /**
     * Main method to start the program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DatabaseManager().run();
    }
}
