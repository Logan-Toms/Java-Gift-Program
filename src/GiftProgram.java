/**
 * @Author Logan Toms
 * @Date 04/11/2023
 * @Description: This program is a gift basket ordering system.
 * It allows the user to order a gift basket, change the size of the gift basket, display a single gift basket, and display all gift baskets.
 */

import java.util.ArrayList;
import java.util.Scanner;

/**
 * GiftProgram
 * Description: This class contains the main method for the program.
 */
abstract class Gift {
    protected String orderNumber;
    protected String size;
    protected int numberOfFruits;
    protected double price;

    // Constructor
    public Gift(String orderNumber, String size) {
        this.orderNumber = orderNumber;
        this.size = size;
        this.numberOfFruits = calculateNumberOfFruits();
    }

    protected abstract double calculatePrice();

    // Calculates number of fruits based on size
    protected int calculateNumberOfFruits() {
        switch (size) {
            case "Small":
                return 6;
            case "Medium":
                return 9;
            case "Large":
                return 15;
            default:
                return 0;
        }
    }

    // Displays order details
    public void display() {
        System.out.println("Order Number: " + orderNumber);
        System.out.println("Size: " + size);
        System.out.println("Number of Fruits: " + numberOfFruits);
        System.out.println("Price: " + price);
    }

    /**
     * Getters and Setters
     * Description: Getters and setters for the Gift class.
     */

    // Gets order details
    public String getDetails() {
        String giftType = this instanceof FruitBasket ? "FruitBasket" : "SweetsBasket";
        return String.format("%s: A %s %s with %d Fruits", orderNumber, size, giftType, numberOfFruits);
    }
    
}

/**
 * FruitBasket
 * Description: This class represents a fruit basket.
 */
class FruitBasket extends Gift {
    private boolean citrusPreference;

    // Constructor
    public FruitBasket(String orderNumber, String size, boolean citrusPreference) {
        super(orderNumber, size);
        this.citrusPreference = citrusPreference;
        this.price = calculatePrice();
    }

    // Calculates price of fruit basket
    protected double calculatePrice() {
        double basePrice;
        switch (size) {
            case "Small":
                basePrice = 19.99;
                break;
            case "Medium":
                basePrice = 29.99;
                break;
            case "Large":
                basePrice = 39.99;
                break;
            default:
                basePrice = 0.0;
        }
        return basePrice + (citrusPreference ? 5.99 : 0);
    }

    // Displays order details for fruit basket
    public void display() {
        super.display();
        System.out.println("Citrus Preference: " + citrusPreference);
    }

    /**
     * Getters and Setters
     * Description: Getters and setters for the FruitBasket class.
     */
    
    // Gets order details for fruit basket
    @Override
    public String getDetails() {
        String baseDetails = super.getDetails();
        String citrusDetails = citrusPreference ? "with citrus" : "without citrus";
        return String.format("%s %s costing $%.2f", baseDetails, citrusDetails, price);
    }
    
    // Gets citrus preference
    public boolean getCitrusPreference() {
        return citrusPreference;
    }

    // Sets citrus preference
    public void setCitrusPreference(boolean citrusPreference) {
        this.citrusPreference = citrusPreference;
    }
    

}

/**
 * SweetsBasket
 * Description: This class represents a sweets basket.
 */
class SweetsBasket extends Gift {
    private boolean nutsPreference;
    
    // Constructor
    public SweetsBasket(String orderNumber, String size, boolean nutsPreference) {
        super(orderNumber, size);
        this.nutsPreference = nutsPreference;
        this.price = calculatePrice();
    }

    // Calculates price of sweets basket
    protected double calculatePrice() {
        double basePrice;
        switch (size) {
            case "Small":
                basePrice = 19.99;
                break;
            case "Medium":
                basePrice = 29.99;
                break;
            case "Large":
                basePrice = 39.99;
                break;
            default:
                basePrice = 0.0;
        }
        return basePrice + (nutsPreference ? 4.49 : 0);
    }

    // Displays order details for sweets basket
    public void display() {
        super.display();
        System.out.println("Nuts Preference: " + nutsPreference);
    }

    /**
     * Getters and Setters
     * Description: Getters and setters for the SweetsBasket class.
     */

     // Gets order details for sweets basket
    @Override
    public String getDetails() {
        String baseDetails = super.getDetails();
        String nutsDetails = nutsPreference ? "with nuts" : "without nuts";
        return String.format("%s %s costing $%.2f", baseDetails, nutsDetails, price);
    }

    // Gets nuts preference
    public boolean getNutsPreference() {
        return nutsPreference;
    }
    
    // Sets nuts preference
    public void setNutsPreference(boolean nutsPreference) {
        this.nutsPreference = nutsPreference;
    }
    
    
}

/**
 * GiftProgram
 * Description: This class contains the main method for the program.
 * Should be considered as a driver, that is, only the minimum number of code lines are in it. 
 * Most of the coding is done in methods that are outside of the main () element. 
 */
public class GiftProgram {
    public static void main(String[] args) {
        ArrayList<Gift> gifts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleAddGiftOrder(gifts, scanner);
                    break;
                case 2:
                    handleChangeGiftOrder(gifts, scanner);
                    break;
                case 3:
                    handleDisplaySingleGiftOrder(gifts, scanner);
                    break;
                case 4:
                    handleDisplayAllGiftOrders(gifts);
                    break;
                case 9:
                    handleExit(scanner);
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        }
    }

    // Other Methods located outside of the main () element.

    /**
     * displayMenu
     * Description: Displays the menu options for the program.
     */
    private static void displayMenu() {
        System.out.println("\nMENU:");
        System.out.println("1: Order a Gift Basket");
        System.out.println("2: Change Gift Basket");
        System.out.println("3: Display Gift Basket");
        System.out.println("4: Display all Gift Baskets");
        System.out.println("9: Exit program");
        System.out.print("\nEnter your selection: ");
    }
    
    /**
     * addGiftOrder
     * Description: Prompts user for gift basket order details and creates a new gift basket.
     * @param scanner
     * @return
     */
    private static Gift addGiftOrder(Scanner scanner) {
        System.out.print("\nDo you want a Fruit Basket (1) or a Sweets Basket (2): ");
        int basketChoice = scanner.nextInt();
    
        // Prompts user for order ID
        String orderId;
        while (true) {
            System.out.print("Create order number [FB or SB for type of gift and three integers] ");
            orderId = scanner.next();

            // Validate order ID
            if (isValidOrderId(orderId)) {
                break;
            } else {
                System.out.println("Invalid order ID. Please enter three integers after the type of gift (FB or SB).");
            }
        }
        
        // Prompts user for size of gift basket
        System.out.print("What size do you want: S, M, or L: ");
        String size = scanner.next();
        if (size.equals("S")) {
            size = "Small";
        } else if (size.equals("M")) {
            size = "Medium";
        } else if (size.equals("L")) {
            size = "Large";
        }        
        
        // Determine citrus or nuts preference
        // If Fruit Basket, ask for citrus preference, else ask for nuts preference
        boolean hasPreference;
        if (basketChoice == 1) {
            System.out.print("Do you want citrus fruits included? 1 for yes/2 for no: ");
            int preference = scanner.nextInt();
            hasPreference = preference == 1;
        } else {
            System.out.print("Do you want nuts included? 1 for yes/2 for no: ");
            int preference = scanner.nextInt();
            hasPreference = preference == 1;
        }
        
        // Create gift basket
        Gift gift;
        if (orderId.startsWith("FB") || orderId.startsWith("fb")) {
            gift = new FruitBasket(orderId, size, hasPreference);
        } else {
            gift = new SweetsBasket(orderId, size, hasPreference);
        }

        // Confirmation message
        System.out.println("\n" + gift.getDetails());
        System.out.println("Gift basket ordered successfully.");

        return gift;

    }    
    
    /**
     * changeGiftOrder
     * Description: Prompts user for gift basket order ID and changes the size of the gift basket.
     * @param gifts
     * @param scanner
     */
    private static void changeGiftOrder(ArrayList<Gift> gifts, Scanner scanner) {
        if (gifts.size() == 0) {
            System.out.println("\nNo gift has been ordered yet.");
            return;
        }
        
        System.out.print("Which Gift do you want to change? ");
        String orderId = scanner.next();
        
        // Find gift with the provided order ID
        Gift foundGift = null;
        for (Gift gift : gifts) {
            if (gift.orderNumber.equalsIgnoreCase(orderId)) {
                foundGift = gift;
                break;
            }
        }
        
        // If no gift found, display error message and return
        if (foundGift == null) {
            System.out.println("No gift found with the provided order ID.");
            return;
        }
        
        // Display gift details
        System.out.println("Current gift size is: " + foundGift.size);
        System.out.print("What size do you want [enter same size or one of order sizes]? ");
        String newSize = scanner.next();
        if (newSize.equalsIgnoreCase("S")) {
            newSize = "Small";
        } else if (newSize.equalsIgnoreCase("M")) {
            newSize = "Medium";
        } else if (newSize.equalsIgnoreCase("L")) {
            newSize = "Large";
        }
        // Update gift size
        foundGift.size = newSize;
    
        // If Fruit Basket, ask if the user wants to change their citrus preference, 
        if (foundGift instanceof FruitBasket) {
            FruitBasket fruitBasket = (FruitBasket) foundGift;
            if (fruitBasket.getCitrusPreference()) {
                System.out.println("Current basket contains citrus.");
            } else {
                System.out.println("Current basket does not contain citrus.");
            }
            System.out.print("Do you want citrus fruits included? 1 for yes/2 for no: ");
            int preference = scanner.nextInt();
            boolean hasPreference = preference == 1;
            // Update citrus preference
            fruitBasket.setCitrusPreference(hasPreference);
        } else { // else ask if the user wants to change their nuts preference
            SweetsBasket sweetsBasket = (SweetsBasket) foundGift;
            if (sweetsBasket.getNutsPreference()) {
                System.out.println("Current basket contains nuts.");
            } else {
                System.out.println("Current basket does not contain nuts.");
            }
            System.out.print("Do you want nuts included? 1 for yes/2 for no: ");
            int preference = scanner.nextInt();
            boolean hasPreference = preference == 1;
            // Update nuts preference
            sweetsBasket.setNutsPreference(hasPreference);
        }
    
        // Recalculate the price
        foundGift.price = foundGift.calculatePrice();
    }    
    
    /**
     * displaySingleGiftOrder
     * Description: Prompts user for gift basket order ID and displays the details of the gift basket.
     * @param gifts
     * @param scanner
     */
    private static void displaySingleGiftOrder(ArrayList<Gift> gifts, Scanner scanner) {
        if (gifts.size() == 0) {
            System.out.println("\nNo gift has been ordered yet.");
            return;
        }
    
        System.out.print("Enter order ID to display:");
        String orderId = scanner.next();
        
        // Find gift with the provided order ID
        Gift foundGift = null;
        for (Gift gift : gifts) {
            if (gift.orderNumber.equalsIgnoreCase(orderId)) {
                foundGift = gift;
                break;
            }
        }
        
        // If no gift found, display error message and return
        if (foundGift == null) {
            System.out.println("No gift found with the provided order ID.");
            return;
        }
        
        // Display gift details
        System.out.println(foundGift.getDetails());
    }       
    
    /**
     * displayAllGiftOrders
     * Description: Displays the details of all gift baskets.
     * @param gifts
     */
    private static void displayAllGiftOrders(ArrayList<Gift> gifts) {
        for (Gift gift : gifts) {
            System.out.println(gift.getDetails());
        }
    }
    
    /**
     * isValidOrderId
     * Description: Validates the order ID.
     * @param orderId
     * @return
     */
    private static boolean isValidOrderId(String orderId) {
        if (orderId.length() != 5) {
            return false;
        }
        
        // Check if the first two characters are FB or SB
        String prefix = orderId.substring(0, 2).toUpperCase();
        if (!prefix.equals("FB") && !prefix.equals("SB")) {
            return false;
        }
        
        // Check if the last three characters are numbers
        String number = orderId.substring(2);
        if (number.length() != 3) {
            return false;
        }
        
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }
    
        return true;
    }
    
    /**
     * handleMenuChoice
     * Description: Handles the menu choice.
     * @param choice
     * @param gifts
     * @param scanner
     */

    private static void handleAddGiftOrder(ArrayList<Gift> gifts, Scanner scanner) {
        Gift newGift = addGiftOrder(scanner);
        if (newGift != null) {
            gifts.add(newGift);
        }
    }

    private static void handleChangeGiftOrder(ArrayList<Gift> gifts, Scanner scanner) {
        changeGiftOrder(gifts, scanner);
    }

    private static void handleDisplaySingleGiftOrder(ArrayList<Gift> gifts, Scanner scanner) {
        displaySingleGiftOrder(gifts, scanner);
    }

    private static void handleDisplayAllGiftOrders(ArrayList<Gift> gifts) {
        displayAllGiftOrders(gifts);
    }

    private static void handleExit(Scanner scanner) {
        System.out.println("\nThank you for using the program. Goodbye!");
        scanner.close();
        System.exit(0);
    }
}
