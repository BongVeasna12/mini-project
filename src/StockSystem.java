import java.util.ArrayList;
import java.util.Scanner;

public class StockSystem {
    private static final ArrayList<String[]> stockList = new ArrayList<>();
    private static int currentPage = 1;
    private static int rowsPerPage = 5;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exitProgram = false;

        while (!exitProgram) {
            System.out.println("Stock Management System");
            System.out.println("-".repeat(100));
            System.out.println("1. Display Page    2. Write           3. Read            4. Update          5. Delete");
            System.out.println("6. First Page      7. Preview Page    8. Next Page       9. Search Page    10. Goto Page");
            System.out.println("11. Set Row        12. Help Page      13. Exit Program");
            System.out.println("-".repeat(100));
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> displayPage();
                case 2 -> write();
                case 3 -> read();
                case 4 -> update();
                case 5 -> delete();
                case 6 -> firstPage();
                case 7 -> previewPage();
                case 8 -> nextPage();
                case 9 -> searchPage();
                case 10 -> gotoPage();
                case 11 -> setRow();
                case 12 -> helpPage();
                case 13 -> exitProgram = true;
                default -> System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.println("Exiting program... Thank you!");
    }

    private static void displayPage() {
        int startIndex = (currentPage - 1) * rowsPerPage;
        int endIndex = Math.min(startIndex + rowsPerPage, stockList.size());

        System.out.println("Stock List (Page " + currentPage + "): ");
        System.out.println("-".repeat(100));
        System.out.println("ID\t\tName\t\tQuantity\t\tPrice");

        for (int i = startIndex; i < endIndex; i++) {
            String[] stock = stockList.get(i);
            System.out.println(stock[0] + "\t\t" +stock[1] + "\t\t" +stock[2] + "\t\t" +stock[3]);
        }

        System.out.println("-".repeat(100));
    }

    private static void write() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the stock ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter the stock name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the stock quantity: ");
        String quantity = scanner.nextLine();

        System.out.print("Enter the stock price: ");
        String price = scanner.nextLine();

        String[] stock = {id, name, quantity, price};  // Add price to the stock array
        stockList.add(stock);


        System.out.println("Stock added successfully.");
    }

    private static void read() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the stock ID: ");
        String id = scanner.nextLine();

        for (String[] stock : stockList) {
            if (stock[0].equals(id)) {
                System.out.println("Stock Details:");
                System.out.println("-".repeat(100));
                System.out.println("ID: " + stock[0]);
                System.out.println("Name: " + stock[1]);
                System.out.println("Quantity: " + stock[2]);
                System.out.println("Price: " + stock[3]);
                System.out.println("-".repeat(100));
                return;
            }
        }

        System.out.println("Stock with ID " + id + " not found.");
    }

    private static void update() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the stock ID: ");
        String id = scanner.nextLine();

        for (String[] stock : stockList) {
            if (stock[0].equals(id)) {
                System.out.print("Enter the new stock name: ");
                String name = scanner.nextLine();

                System.out.print("Enter the new stock quantity: ");
                String quantity = scanner.nextLine();

                System.out.print("Enter the new stock price: ");
                String price = scanner.nextLine();


                stock[1] = name;
                stock[2] = quantity;
                stock[3] = price;

                System.out.println("Stock updated successfully.");
                return;
            }
        }

        System.out.println("Stock with ID " + id + " not found.");
    }

    private static void delete() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the stock ID: ");
        String id = scanner.nextLine();

        for (int i = 0; i < stockList.size();i++) {
            String[] stock = stockList.get(i);
            if (stock[0].equals(id)) {
                stockList.remove(i);
                System.out.println("Stock deleted successfully.");
                return;
            }
        }

        System.out.println("Stock with ID " + id + " not found.");
    }

    private static void firstPage() {
        currentPage = 1;
        System.out.println(" first page.");
    }

    private static void previewPage() {
        if (currentPage > 1) {
            currentPage--;
            System.out.println(" previous page.");
        } else {
            System.out.println(" first page.");
        }
    }

    private static void nextPage() {
        int totalPages = (int) Math.ceil((double) stockList.size() / rowsPerPage);
        if (currentPage < totalPages) {
            currentPage++;
            System.out.println("next page.");
        } else {
            System.out.println("last page.");
        }
    }

    private static void searchPage() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the stock name: ");
        String searchName = scanner.nextLine();

        boolean found = false;

        for (String[] stock : stockList) {
            if (stock[1].equalsIgnoreCase(searchName)) {
                System.out.println("Stock Details:");
                System.out.println("ID: " + stock[0]);
                System.out.println("Name: " + stock[1]);
                System.out.println("Quantity: " + stock[2]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Stock with name " + searchName + " not found.");
        }
    }

    private static void gotoPage() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the page number: ");
        int page = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        int totalPages = (int) Math.ceil((double) stockList.size() / rowsPerPage);

        if (page >= 1 && page <= totalPages) {
            currentPage = page;
            System.out.println("Navigated to page " + currentPage + ".");
        } else {
            System.out.println("Invalid page number. Please try again.");
        }
    }

    private static void setRow() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows per page: ");
        int rows = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (rows > 0) {
            rowsPerPage = rows;
            System.out.println("Number of rows per page set to " + rowsPerPage + ".");
        } else {
            System.out.println("Invalid number of rows. Please try again.");
        }
    }

    private static void helpPage() {
        System.out.println("Help Page:");
        System.out.println("----------------------------------------------------");
        System.out.println("1. Display Page: Displays the current page of stocks.");
        System.out.println("2. Write: Adds a new stock to the system.");
        System.out.println("3. Read: Retrieves details of a stock based on the ID.");
        System.out.println("4. Update: Updates the details of a stock based on the ID.");
        System.out.println("5. Delete: Deletes a stock from the system based on the ID.");
        System.out.println("6. First Page: Navigates to the first page of stocks.");
        System.out.println("7. Preview Page: Navigates to the previous page of stocks.");
        System.out.println("8. Next Page: Navigates to the next page of stocks.");
        System.out.println("9. Search Page: Searches for stocks based on the name.");
        System.out.println("10. Goto Page: Navigates to a specific page of stocks.");
        System.out.println("11. Set Row: Sets the number of rows per page.");
        System.out.println("12. Help Page: Displays the help page.");
        System.out.println("13. Exit Program: Exits the stock management system.");
        System.out.println("----------------------------------------------------");
    }
}