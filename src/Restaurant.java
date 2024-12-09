import java.io.Serializable;
import java.util.ArrayList;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant implements Serializable {
    String name;
    String location;
    String category;
    ArrayList<Food> menu;
    // Restaurants
    public Restaurant(String name, String location, String category)
    {
        this.name = name;
        this.location = location;
        this.category = category;
        this.menu = new ArrayList<>();
    }
     public Restaurant()
     {}
    // add items of restaurant's menu
    void addMenuItem(String itemName, String itemType , double itemPrice)
    {
        menu.add(new Food(itemName, itemType, itemPrice));
    }

    // displays menu for a specific restaurant
   public  ArrayList<Food> displayMenu()
    {
        System.out.println("Menu for " + name + ":");
        for(Food item : menu)
        {
            System.out.println(item.name + "(" + item.type + ") " + ": " + item.price + " EGP");
        }
        return menu;

    }

    // Restaurants data
    //remove
    public static  ArrayList<Restaurant> initializeRestaurants()
    {
        // create an ArrayList for Restaurants
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant1 = new Restaurant("Kansas", "Cairo, Nasr City", "Fried Chicken");
        restaurant1.addMenuItem("Fried Chicken Bucket (12 pcs)", "Main Meal", 280.0);
        restaurant1.addMenuItem("Crispy Chicken Sandwich", "Main Meal", 100.0);
        restaurant1.addMenuItem("Chicken Wrap Meal", "Combo", 150.0);
        restaurant1.addMenuItem("Spicy Chicken Wings", "Appetizer", 120.0);
        restaurants.add(restaurant1);

        Restaurant restaurant2 = new Restaurant("Heart Attack","Giza, Dokki" ,"Fried Chicken");
        restaurant2.addMenuItem("Cheesy Fried Chicken", "Main Meal", 180.0);
        restaurant2.addMenuItem("Family Meal (20 pcs)", "Combo", 450.0);
        restaurant2.addMenuItem("Chicken Wrap with Fries", "Combo", 160.0);
        restaurant2.addMenuItem("Crispy Tenders", "Appetizer", 140.0);
        restaurants.add(restaurant2);

        Restaurant restaurant3 = new Restaurant("Pronto Pizza","Cairo, Heliopolis" ,"Pizza");
        restaurant3.addMenuItem("Margherita Pizza", "Main Meal", 100.0);
        restaurant3.addMenuItem("Pepperoni Pizza", "Main Meal", 150.0);
        restaurant3.addMenuItem("BBQ Chicken Pizza", "Main Meal", 160.0);
        restaurant3.addMenuItem("Vegetarian Pizza", "Main Meal", 130.0);
        restaurants.add(restaurant3);

        Restaurant restaurant4 = new Restaurant("Pizza Station", "Giza, Mohandessin", "Pizza");
        restaurant4.addMenuItem("Four-Cheese Pizza", "Main Meal", 180.0);
        restaurant4.addMenuItem("Seafood Pizza", "Main Meal", 200.0);
        restaurant4.addMenuItem("Truffle Mushroom Pizza", "Main Meal", 220.0);
        restaurant4.addMenuItem("Classic Pepperoni", "Main Meal", 160.0);
        restaurants.add(restaurant4);

        Restaurant restaurant5 = new Restaurant("Skyroof", "Alexandria, Corniche", "Sea Food");
        restaurant5.addMenuItem("Mixed Seafood Platter", "Main Meal", 450.0);
        restaurant5.addMenuItem("Shrimp Pasta", "Main Meal", 250.0);
        restaurant5.addMenuItem("Grilled Calamari", "Appetizer", 180.0);
        restaurant5.addMenuItem("Lemonade", "Drink", 25.0);
        restaurants.add(restaurant5);

        Restaurant restaurant6 = new Restaurant("Mandarine Koueider", "Cairo, Zamalek", "Dessert");
        restaurant6.addMenuItem("Kunafa with Cream", "Dessert", 90.0);
        restaurant6.addMenuItem("Chocolate Truffles", "Dessert", 150.0);
        restaurant6.addMenuItem("Ice Cream (per scoop)", "Dessert", 25.0);
        restaurants.add(restaurant6);

        Restaurant restaurant7 = new Restaurant("Cilantro Café", "Cairo, New Cairo", "Café");
        restaurant7.addMenuItem("Caramel Latte", "Beverage", 55.0);
        restaurant7.addMenuItem("Turkey & Cheese Sandwich", "Snack", 75.0);
        restaurant7.addMenuItem("Classic Cappuccino", "Beverage", 50.0);
        restaurant7.addMenuItem("Chocolate Croissant", "Pastry", 45.0);
        restaurants.add(restaurant7);

        return restaurants;
    }

    // save data from program to file ( output stream )
    private static void saveRestaurantsToFile(ArrayList<Restaurant> restaurants)
    {
        // oos: ObjectOutputStream
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Restaurants.txt"))) {
            oos.writeObject(restaurants);
            System.out.println("Restaurants saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving restaurants to file: " + e.getMessage());
        }
    }

    // load data from program to code ( input stream )
    protected static ArrayList<Restaurant> loadRestaurantsFromFile()
    {
        // ois: ObjectInputStream
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Restaurants.txt"))) {
            return (ArrayList<Restaurant>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading restaurants from file: " + e.getMessage());
            return null;
        }
    }

    public static int afterLog_in (int verify_log_in)
    {
        Scanner input = new Scanner(System.in);
        ArrayList<Restaurant> restaurants = initializeRestaurants();

        // Save restaurants data to file
        saveRestaurantsToFile(restaurants);

        // get restaurants data from file
        ArrayList<Restaurant> loadedRestaurants = loadRestaurantsFromFile();

        // checks whether I putted restaurants in the array list or not (.isEmpty() )
        // checks if the Restaurants has objects in memory or not (NULL)
        if (loadedRestaurants == null || loadedRestaurants.isEmpty())
        {
            System.out.println("No restaurants found in the file!");
          return 0;
        }

        //after the customer picks the government and area the available restaurant will appear
        System.out.println("Available Restaurants: ");
        for(int i = 0; i < loadedRestaurants.size(); i++)
        {
            System.out.println((i+1) +". " + loadedRestaurants.get(i).name + "  (" + loadedRestaurants.get(i).category + ")");
        }

        System.out.print("Select a Restaurant by Number: ");
        int restaurantChoice = input.nextInt();

        if(restaurantChoice > 0 && restaurantChoice <= loadedRestaurants.size())
        {

//            loadedRestaurants.get(restaurantChoice - 1).displayMenu();
             return  restaurantChoice;
            // add order class
        }
        else
        {
            System.out.println("Invalid restaurant choice");
        }
        return 0;
    }


}
