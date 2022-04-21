import java.util.Scanner;

//main class for our Stock Tracker application
class terriertrack {

    public class Stock {
        private String name;
        private int price;
        private int shares;

        public Stock(String name, int price) {
            this.name = name;
            this.price = price;
            this.shares = 1;
        }

        public String getName() {
            return this.name;
        }

        public int getPrice() {
            return this.price;
        }

        public int getShares() {
            return this.shares;
        }

        public void updateShares(int n) {
            this.shares += n;
        }
    }

    static int BALANCE = 1000;
    static Stock[] favorites = new Stock[5];
    static Stock[] holdings = new Stock[20];

    static int PRICE = 0;

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

    // addStock() - helper method to add stock, returns true to indicated success,
    // false for failure

    private static int findStock(Stock s, Stock[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean addToList(Stock s, Stock[] list, int n) {
        int len = list.length;
        int index = findStock(s, holdings);
        if (index != -1) {
            holdings[index].updateShares(n);
            return true;
        }
        if (len < 5) {
            list[len] = s;
            return true;
        }
        return false;
    }

    private static boolean buyStock(Stock s, int n) {
        int p = s.getPrice();
        if (n * p <= BALANCE && addToList(s, holdings, n)) {
            BALANCE -= p;
            return true;
        }
        return false;
    }

    private static boolean sellStock(Stock s, int n) {
        int p = s.getPrice();
        int index = findStock(s, holdings);
        if (index != -1 && n <= s.getShares()) {
            BALANCE += n * p;
            holdings[index].updateShares(-n);
            if (holdings[index].getShares() == 0) {
                holdings[index] = null;
            }
            return true;
        }
        return false;
    }

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

                // Get which stock they want to buy
                String name;
                Stock s;
                while (true) {
                    Scanner value = new Scanner(System.in);
                    System.out.println("What Stock do you wish to buy?");
                    name = value.nextLine();
                    value.close();
                    s = search(name);

                    if (s == null) {
                        System.out.println("We cannot find that stock name, please enter another name.");
                    } else {
                        break;
                    }
                }

                // Input number of shares

                Scanner value2 = new Scanner(System.in);
                System.out.println("How many shares of " + name + " would you like to buy?");
                String num = value2.nextLine();
                value2.close();

                int n = Integer.parseInt(num);
                boolean success = buyStock(s, n);

                if (success) {
                    System.out.println("You have successfully bought a share for " + name + ", congratulations.");
                } else {
                    System.out.println("Sorry, you don't have enough to buy a stock for " + name + ".");
                }
            } else if (input.equals("4")) {

            } else if (input.equals("5")) {

            } else if (input.equals("6")) {

            } else if (input.equals("7")) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println(input + " is an invalid input. Try again.");
            }
        }

    }
}