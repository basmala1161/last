
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Order {
    Scanner input=new Scanner(System.in);
    private int orderId;
    private LocalDate orderDate;
    private String orderLocation;
    private String orderState;
    private Map<Food, Integer> items;
    private double totalPrice;
    customer customerData=new customer();
    // Method to return current date
    public static LocalDate getCurrentDate() {
        return LocalDate.now(); // Returns the current date
    }
    public Order(int orderId,String orderLocation) {
        this.orderId = orderId;
        this.orderDate = getCurrentDate();
        this.orderLocation = customerData.address;
        this.orderState = "Pending";
        this.items = new LinkedHashMap<>();
        this.totalPrice = 0.0;

    }
    public void addItem(Food food, int quantity) {
        items.put(food, items.getOrDefault(food, 0) + quantity);
        totalPrice += food.getPrice() * quantity;
    }
    public void removeItem(Food food, int quantity) {
        items.put(food, items.getOrDefault(food, 0) + quantity);
        totalPrice -= food.getPrice() * quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void changeState(String newState) {
        this.orderState = newState;
    }
    public void Createorder(int restaurantChoice){

        Restaurant selectedRestaurant = Restaurant.loadRestaurantsFromFile().get(restaurantChoice - 1);
        selectedRestaurant.displayMenu();


        // Create Order
        Order order = new Order(1, selectedRestaurant.location);

        boolean ordering = true;
        while (ordering) {
            System.out.print("Enter the number of the menu item to add (or 0 to finish): ");
            int itemChoice = input.nextInt();

            if (itemChoice == 0) {
                ordering = false;
            } else if (itemChoice > 0 && itemChoice <= selectedRestaurant.menu.size()) {
                System.out.print("Enter the quantity: ");
                int quantity = input.nextInt();

                if (quantity > 0) {
                    Food selectedFood = selectedRestaurant.menu.get(itemChoice - 1);
                    order.addItem(selectedFood, quantity);
                    System.out.println(quantity + " x " + selectedFood.name + " added to your order.");
                } else {
                    System.out.println("Invalid quantity! Please enter a positive number.");
                }
            } else {
                System.out.println("Invalid item choice! Please select a valid menu item.");
            }
        }
        System.out.println("=========================");
        System.out.println("Order Summary:");
        System.out.println(order);


    }
   public void update(int restaurantChoice){
       System.out.println("1- Add new item");
       System.out.println("2- delete item");
       int update_choice=input.nextInt();
       if(update_choice==1){
        Createorder(restaurantChoice);
       }
       else {
           Restaurant selectedRestaurant = Restaurant.loadRestaurantsFromFile().get(restaurantChoice - 1);
           selectedRestaurant.displayMenu();


           // Create Order
           Order order = new Order(1, selectedRestaurant.location);

           boolean ordering = true;
           while (ordering) {
               System.out.print("Enter the number of the menu item to remove (or 0 to finish): ");
               int itemChoice = input.nextInt();

               if (itemChoice == 0) {
                   ordering = false;
               } else if (itemChoice > 0 && itemChoice <= selectedRestaurant.menu.size()) {
                   System.out.print("Enter the quantity: ");
                   int quantity = input.nextInt();

                   if (quantity > 0) {
                       Food selectedFood = selectedRestaurant.menu.get(itemChoice - 1);
                       order.removeItem(selectedFood, quantity);
                       System.out.println(quantity + " x " + selectedFood.name + " removed from your order");
                   } else {
                       System.out.println("Invalid quantity! Please enter a positive number.");
                   }
               } else {
                   System.out.println("Invalid item choice! Please select a valid menu item.");
               }
           }

       }
   }

    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order ID: ").append(orderId)
                .append("\nDate: ").append(orderDate)
                .append("\nLocation: ").append(customerData.address)
                .append("\nState: ").append(orderState)
                .append("\nTotal Price: ").append(totalPrice).append(" EGP")
                .append("\nItems:\n");

        for (Map.Entry<Food, Integer> entry : items.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            orderDetails.append("- ").append(food.name)
                    .append(" (").append(food.type).append(") - ")
                    .append(food.price).append(" EGP x")
                    .append(quantity).append("\n");
        }

        return orderDetails.toString();
    }

    public String getOrderLocation() {
        return  orderLocation;



    }
}
