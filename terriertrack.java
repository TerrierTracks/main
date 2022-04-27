import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Arrays;
import java.util.Comparator;

//main class for our Stock Tracker application
class terriertrack {

    // static variables: BALANCE shows how much money you have to spend, favorites
    // is a list of your favorite stocks, holdings is a list of the stocks you have
    // bought
    static double BALANCE = 1000;
    static Stock[] favorites = new Stock[5];
    static Stock[] holdings = new Stock[20];

    // ANSI Color Definitions for Printing Color in Terminal
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    // Object Class to represent a stock
    public static class Stock {

        // Attributes
        private String ticker;
        private String name;
        private double openingPrice;
        private double currPrice;
        private long marketCap;
        private double low;
        private double high;
        private int shares;

        // Constructor
        public Stock(String ticker, String name, double openingPrice, double currPrice, long marketCap, double low,
                double high) {
            this.ticker = ticker;
            this.name = name;
            this.openingPrice = openingPrice;
            this.currPrice = currPrice;
            this.marketCap = marketCap;
            this.low = low;
            this.high = high;
            this.shares = 1;
        }

        // getTicker - returns the company ticker of the stock
        public String getTicker() {
            return this.ticker;
        }

        // getName - returns the company name of the stock
        public String getName() {
            return this.name;
        }

        // getOpeningPrice - returns the stock's opening price
        public double getOpeningPrice() {
            return this.openingPrice;
        }

        // getPrice - returns the price of one share of the stock
        public double getCurrPrice() {
            return this.currPrice;
        }

        // getMarketCap - return the stock's market cap
        public long getMarketCap() {
            return this.marketCap;
        }

        // getHigh - returns the stock's high
        public double getHigh() {
            return this.high;
        }

        // getLow - returns the stock's low
        public double getLow() {
            return this.low;
        }

        // getShares - returns how many shares you have of a stock
        public int getShares() {
            return this.shares;
        }

        // updateShares - changes how many shares you have of a stock by n
        public void updateShares(int n) {
            this.shares += n;
        }

        // equals - checks if a stock is equal to another stock
        public boolean equals(Stock other) {
            String otherName = other.getName();
            double otherPrice = other.getCurrPrice();

            if (this.name.equals(otherName) && this.currPrice == otherPrice) {
                return true;
            }
            return false;
        }

        // toString - represents a stock when it is printed
        public String toString() {
            String str = "\n" + this.name + ":\n";
            str += "\n";
            str += "Ticker: " + this.ticker + "\n";
            str += "Opening Price: " + this.openingPrice + "\n";
            str += "Current Price: " + this.currPrice + "\n";
            str += "Market Cap: " + this.marketCap + "\n";
            str += "Low Value: " + this.low + "\n";
            str += "High Value: " + this.high;
            return str;
        }

        // dayChangePrice - calculates and returns the change between opening and
        // current price
        private double dayChangePrice() {
            double changePrice = this.currPrice - this.openingPrice;
            return changePrice;
        }

        // dayChangePercent - calculates and returns the percent change between opening
        // and current price
        private double dayChangePercent() {
            double changePercent = (this.dayChangePrice() / this.openingPrice) * 100;
            return changePercent;
        }
    }

    // displayWelcome - displays the welcome page
    private static void displayWelcome() {
        System.out.println("");
        System.out.println("Welcome to TerrierTrack!");
        System.out.println("");
    }

    // displayHome - displays the Home page
    private static void displayHome() {
        System.out.println("");
        System.out.println("Home Page");
        System.out.println("");
        System.out.println("You currently have $" + (String.format("%.2f", BALANCE)) + " in your account.\n");
        if (favorites[0] != null) {
            System.out.println("Your Favorite Stocks Are:");
            for (int i = 0; i < favorites.length; i++) {
                if (favorites[i] != null) {
                    displayStock(favorites[i]);
                }
            }

        }
        System.out.println("");

        System.out.println(" 1.) Search for a Company");
        System.out.println(" 2.) Show owned Stocks");
        System.out.println(" 3.) Buy a Stock");
        System.out.println(" 4.) Sell a Stock");
        System.out.println(" 5.) Add to Favorites");
        System.out.println(" 6.) Remove from Favorites");
        System.out.println(" 7.) Top Gainers");
        System.out.println(" 8.) Quit\n");

    }

    // Helper methods
    // -------------------------------------------------------------------------------------------------

    // parse - helper method for stock. Reads a csv file filled with stock
    // information and returns a list of the searched ticker's stock
    private static String[] parse(String ticker) {
        String line = "";
        String splitBy = ",";
        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("TerrierTracksStocks.csv"));
            while ((line = br.readLine()) != null) {
                String[] stock = line.split(splitBy); // use comma as separator
                if (stock[0].equals(ticker)) {
                    br.close();
                    return stock;
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return new String[0];
    }

    // search - searches for a stock of the inputed ticker
    private static Stock search(String ticker) {
        String[] stringList = parse(ticker);
        if (stringList.length == 7) {
            double openingPrice = Double.parseDouble(stringList[2]);
            double currPrice = Double.parseDouble(stringList[3]);
            long marketCap = Long.parseLong(stringList[4]);
            double low = Double.parseDouble(stringList[5]);
            double high = Double.parseDouble(stringList[6]);
            Stock s = new Stock(stringList[0], stringList[1], openingPrice, currPrice, marketCap, low, high);
            return s;
        }
        return null;

    }

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
            if (list[i] != null && list[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    // addToList - helper function that adds n shares of a stock s to a list of
    // stocks, returns true to indicate success
    private static boolean addToList(Stock s, Stock[] list, int n) {
        int index = findStock(s, list);
        if (index != -1) {
            list[index].updateShares(n);
            return true;
        }
        int j = findEmptySlot(list);

        if (j != -1) {
            s.updateShares(n - 1);
            list[j] = s;
            return true;
        }
        return false;
    }

    // removeFromList - helper function that removes stock s from a list of
    // stocks, returns true to indicate success
    private static boolean removeFromList(Stock s, Stock[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                continue;
            } else if (list[i].equals(s)) {
                list[i] = null;
                return true;
            } else {
                continue;
            }
        }
        System.out.println("Stock cannot be found\n");
        return false;
    }

    // buyStock - buys n shares of a Stock s
    private static boolean buyStock(Stock s, int n) {
        double p = s.getCurrPrice();
        if (n * p <= BALANCE && addToList(s, holdings, n)) {
            BALANCE -= n * p;
            return true;
        }
        return false;
    }

    // sellStock - sells n shares of a stock s
    private static boolean sellStock(Stock s, int n) {
        double p = s.getCurrPrice();
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

    // displayStock - displays key information of stock s in single line
    public static void displayStock(Stock s) {
        double change = s.dayChangePercent();

        if (change < 0.0) {
            System.out.println(s.getName() + " " + s.getCurrPrice() + " " + ANSI_RED
                    + String.format("%.2f", s.dayChangePercent()) + "%" + ANSI_RESET);
        } else {
            System.out.println(s.getName() + " " + s.getCurrPrice() + " " + ANSI_GREEN
                    + String.format("%.2f", s.dayChangePercent()) + "%" + ANSI_RESET);
        }
    }

    // topGainerAndLoser - displays the best and worst stock of the day
    public static Stock[] topGainerAndLoser() {
        double best = 0.0;
        double worst = 0.0;
        Stock[] stocks = new Stock[2];
        if (holdings.length == 0)
            return stocks;
        for (int i = 0; i < holdings.length; i++) {
            double change = holdings[i].dayChangePercent();
            if (change >= best) {
                best = change;
                stocks[0] = holdings[i];
            }
            if (change <= worst) {
                best = worst;
                stocks[1] = holdings[i];
            }
        }
        return stocks;
    }

    // Main method
    public static void main(String[] args) {

        // We start our interface on the welcome page.
        displayWelcome();

        while (true) {
            Scanner myObj = new Scanner(System.in); // Create a Scanner object
            System.out.println("Type 'Home' to go to the home page.");

            String input = myObj.nextLine(); // Read user input
            // myObj.close()

            if (input.equals("Home") || input.equals("home")) {
                break;
            } else {
                System.out.println(input + " is an invalid input. Try again.\n");
            }
        }

        // User has entered the Home Page, we display options for the user and ask
        // for input.
        while (true) {

            displayHome();

            Scanner myObj = new Scanner(System.in); // Create Scanner Object
            System.out.println("Type the number to choose an option.");

            String input = myObj.nextLine(); // Read user input
            // myObj.close()

            if (input.equals("1")) {

                String name;
                Stock s;
                while (true) {
                    Scanner value = new Scanner(System.in);
                    System.out.println("Enter a Valid Company Ticker.");
                    name = value.nextLine().toUpperCase();
                    // value.close()
                    s = search(name);
                    if (s == null) {
                        System.out.println("Sorry, that company doesn't exist in our system. Try again.");
                    } else {
                        System.out.println(s);
                        break;
                    }

                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

            } else if (input.equals("2")) {

                // This will display all the stocks in holdings, provided the list isn't empty
                int count = 0;

                for (int i = 0; i < holdings.length; i++) {
                    if (holdings[i] != null) {
                        System.out.println(holdings[i]);
                        System.out.println("Shares owned: " + holdings[i].getShares());
                        System.out.println("");
                    } else {
                        count++;
                    }

                }
                if (count == holdings.length) {
                    System.out.println("You don't currently own any stocks.\n");
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

            } else if (input.equals("3")) {

                // Get which stock they want to buy
                String name;
                Stock s;
                while (true) {
                    Scanner value = new Scanner(System.in);
                    System.out.println("What Stock do you wish to buy?");
                    name = value.nextLine().toUpperCase();
                    // value.close();
                    s = search(name);

                    if (s == null) {
                        System.out.println("We cannot find that stock, please enter another name.");
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
                    // value2.close();

                    n = Integer.parseInt(num);
                    if (n <= 0) {
                        System.out.println("Not a valid number of stocks to buy.\n");
                    } else {
                        break;
                    }
                }

                boolean success = buyStock(s, n);

                if (success) {
                    System.out.println(
                            "You have successfully bought " + n + " share(s) of " + name + ", congratulations.\n");
                } else {
                    System.out.println("Sorry, you don't have enough to buy " + n + " share(s) of " + name + ".\n");
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            } else if (input.equals("4")) {
                // Get which stock they want to sell
                String name;
                Stock s;
                int i;
                while (true) {
                    Scanner value = new Scanner(System.in);
                    System.out.println("What Stock do you wish to sell?");
                    name = value.nextLine().toUpperCase();
                    // value.close();
                    s = search(name);
                    i = findStock(s, holdings);

                    if (i == -1) {
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
                    // value2.close();

                    n = Integer.parseInt(num);
                    if (n <= 0) {
                        System.out.println("Not a valid number of stocks to sell.\n");
                    } else {
                        break;
                    }
                }

                boolean success = sellStock(holdings[i], n);

                if (success) {
                    System.out
                            .println("You have successfully sold " + n + " shares of " + name + ", congratulations.\n");
                } else {
                    System.out.println("Sorry, we could not sell your " + name + " stock.\n");
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

            } else if (input.equals("5")) {
                // Get which stock they want to add to favourites
                String name;
                Stock s;
                while (true) {
                    Scanner value = new Scanner(System.in);
                    System.out.println("What Stock do you wish to add to your favorites?");
                    name = value.nextLine().toUpperCase();
                    // value.close();
                    s = search(name);

                    if (s == null) {
                        System.out.println("We cannot find that stock name, please enter another name.");
                    } else {
                        addToList(s, favorites, 1);
                        System.out.println(name + " has been added to your favorites.\n");
                        break;
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

            } else if (input.equals("6")) {
                // Get which stock they want to remove from favourites
                String name;
                Stock s;
                while (true) {
                    Scanner value = new Scanner(System.in);
                    System.out.println("What Stock do you wish to remove from your favorites?");
                    name = value.nextLine().toUpperCase();
                    // value.close();
                    s = search(name);

                    if (s == null) {
                        System.out.println("We cannot find that stock name, please enter another name.");
                    } else {
                        removeFromList(s, favorites);
                        System.out.println(name + " has been removed from your favorites.\n");
                        break;
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            } else if (input.equals("7")) {
                // Display top gainer and loser of holdings 
                Stock[] sorted = topGainerAndLoser();
                if (sorted == null) {
                    System.out.println("You haven't bought any stocks yet.");
                }
                displayStock(sorted[0]);
                displayStock(sorted[1]);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            } else if (input.equals("8")) {
                System.out.println("Goodbye!\n");
                break;
            } else {
                System.out.println(input + " is an invalid input. Try again.");
            }
        }

    }
}
