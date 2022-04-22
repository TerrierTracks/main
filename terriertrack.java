import java.util.Scanner;

//main class for our Stock Tracker application
class terriertrack {

    // static variables: BALANCE shows how much money you have to spend, favorites
    // is a list of your favorite stocks, holdings is a list of the stocks you have
    // bought
    static int BALANCE = 1000;
    static Stock[] favorites = new Stock[5];
    static Stock[] holdings = new Stock[20];

    // Object Class to represent a stock
    public class Stock {

        // Attributes
        private String name;
        private int price;
        private int shares;

        // Constructors
        public Stock(String name, int price) {
            this.name = name;
            this.price = price;
            this.shares = 1;
        }

        // getName - returns the company name of the stock
        public String getName() {
            return this.name;
        }

        // getPrice - returns the price of one share of the stock
        public int getPrice() {
            return this.price;
        }

        // getShares - returns how many shares you have of a stock
        public int getShares() {
            return this.shares;
        }

        // updateShares - changes how many shares you have of a stock by n
        public void updateShares(int n) {
            this.shares += n;
        }

        public boolean equals(Stock other) {
            String otherName = other.getName();
            int otherPrice = other.getPrice();

            if (this.name.equals(otherName) && this.price == otherPrice) {
                return true;
            }
            return false;
        }
    }

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
        System.out.println(" 7.) Quit");

    }

    // Helper methods

    private static int findEmptySlot(Stock[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                return i;
            }
        }
        return -1;
    }

    // findStock - searches a list of stocks for a particular stock, returns the
    // index if it finds it, and -1 otherwise
    private static int findStock(Stock s, Stock[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    // addToList - helper function that adds n shares of a stock s to a list of
    // stocks, returns true to indicate success
    private static boolean addToList(Stock s, Stock[] list, int n) {
        int index = findStock(s, holdings);
        if (index != -1) {
            holdings[index].updateShares(n);
            return true;
        }
        int j = findEmptySlot(list);

        if (j != -1) {
            holdings[j] = s;
            return true;
        }
        return false;
    }

    // buyStock - buys n shares of a Stock s
    private static boolean buyStock(Stock s, int n) {
        int p = s.getPrice();
        if (n * p <= BALANCE && addToList(s, holdings, n)) {
            BALANCE -= p;
            return true;
        }
        return false;
    }

    // sellStock - sells n shares of a stock s
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

            } else if (input.equals("2")) {

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
                int n;
                while (true) {
                    Scanner value2 = new Scanner(System.in);
                    System.out.println("How many shares of " + name + " would you like to buy?");
                    String num = value2.nextLine();
                    value2.close();

                    n = Integer.parseInt(num);
                    if (n <= 0) {
                        System.out.println("Not a valid number of stocks to buy.");
                    } else {
                        break;
                    }
                }

                boolean success = buyStock(s, n);

                if (success) {
                    System.out.println("You have successfully bought a share for " + name + ", congratulations.");
                } else {
                    System.out.println("Sorry, you don't have enough to buy a stock for " + name + ".");
                }
            } else if (input.equals("4")) {
                // Get which stock they want to buy
                String name;
                Stock s;
                while (true) {
                    Scanner value = new Scanner(System.in);
                    System.out.println("What Stock do you wish to sell?");
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
                int n;
                while (true) {
                    Scanner value2 = new Scanner(System.in);
                    System.out.println("How many shares of " + name + " would you like to sell?");
                    String num = value2.nextLine();
                    value2.close();

                    n = Integer.parseInt(num);
                    if (n <= 0) {
                        System.out.println("Not a valid number of stocks to sell.");
                    } else {
                        break;
                    }
                }

                boolean success = sellStock(s, n);

                if (success) {
                    System.out.println("You have successfully bought a share for " + name + ", congratulations.");
                } else {
                    System.out.println("Sorry, you don't have enough to buy a stock for " + name + ".");
                }

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