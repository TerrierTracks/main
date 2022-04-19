import java.util.Scanner;

//main class for our Stock Tracker application
class terriertrack {

    // displayWelcome - displays the welcome page
    private static void displayWelcome() {
        System.out.println("Wecome to TerrierTrack!");
        System.out.println("");
    }

    // displayHome - displays the Home page
    private static void displayHome() {
        System.out.println("Home Page");
        System.out.println("");

        System.out.println(" 1.) Search for a Company");
        System.out.println(" 2.) Get your Earnings");
        System.out.println(" 3.) Buy a Stock");
        System.out.println(" 4.) Sell a Stock");
        System.out.println(" 5.) Add to Favorites");
        System.out.println(" 6.) Remove from Favorites");

    }

    // Helper methods

    // search - helper method to search for a company

    // Main method
    public static void main(String[] args) {

        // We start our interface on the welcome page.
        displayWelcome();

        while (true) {
            Scanner myObj = new Scanner(System.in); // Create a Scanner object
            System.out.println("Type 'Home' to go to the home page.");

            String input = myObj.nextLine(); // Read user input

            myObj.close();

            if (input.equals("Home") || input.equals("home")) {
                break;
            } else {
                System.out.println(input + " is an invalid input. Try again.");
            }
        }

        // User has entered the Home Page, we display options for the user and ask
        // for input.
        while (true) {

            displayHome();

            Scanner myObj = new Scanner(System.in); // Create Scanner Object
            System.out.println("Type the number to choose an option.");

            String input = myObj.nextLine(); // Read user input

            myObj.close();

            if (input.equals("1")) {
                break;
            } else if (input.equals("2")) {
                break;
            } else if (input.equals("3")) {
                break;
            } else if (input.equals("4")) {
                break;
            } else if (input.equals("5")) {
                break;
            } else if (input.equals("6")) {
                break;
            } else {
                System.out.println(input + " is an invalid input. Try again.");
            }
        }

    }
}