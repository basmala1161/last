
    import java.io.*;
import java.util.ArrayList;

    public class DeliveryStaff implements Serializable {
        private String firstName;
        private String lastName;
        private String location; // The place where the delivery person works
        private double averageReview;// Average review score
        public  String status;
        private ArrayList<Order> assignedOrders; // List of orders assigned to the delivery staff we must update it

        // Constructor
    public DeliveryStaff(String firstName, String lastName, String location, double averageReview) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.averageReview = averageReview;
        this.assignedOrders = new ArrayList<>();
    }

        // Getters and Setters
        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public double getAverageReview() {
            return averageReview;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setAverageReview(double averageReview) {
            if (averageReview < 0 || averageReview > 5) {
                throw new IllegalArgumentException("Review score must be between 0 and 5.");
            }
            this.averageReview = averageReview;
        }

        public ArrayList<Order> getAssignedOrders() {
            return new ArrayList<>(assignedOrders); // Return a copy to avoid external modification
        }

        public void setAssignedOrders(ArrayList<Order> assignedOrders) {
            this.assignedOrders = new ArrayList<>(assignedOrders); // Create a copy to maintain encapsulation
        }

        // Assign orders based on location


        public String getDeliveryStatus(Order order, DeliveryStaff deliveryStaff) {

            if (order.getOrderLocation().equalsIgnoreCase(this.location)) {


                if (deliveryStaff.getStatus().equals("Free")) {

                    switch (deliveryStaff.getStatus()) {
                        case "Pending":
                            return "Delivery is Pending";

                        case "Completed":
                            return "Delivery is Completed";
                        case "Delayed":
                            return "Delivery is Delayed";
                        default:
                            return "Unknown Order Status";
                    }
                } else if (deliveryStaff.getStatus().equals("Work")) {
                    return "Delivery staff is currently busy";
                } else {
                    return "Invalid Delivery Staff Status: " + deliveryStaff.getStatus();
                }
            } else {
                return "Order is not in your location";
            }
        }


        public void assignOrdersByLocation(ArrayList<Order> orders) {
            for (Order order : orders) {
                if (order.getOrderLocation().equalsIgnoreCase(this.location) && status.equals("Free")) {
                    this.assignedOrders.add(order);
                } else if (status.equals("work")) {
                    System.out.println("Delivery cannot be assigned because the worker is currently busy.");
                }


            }
        }


        // Assign orders from a file
        public void assignOrdersFromFile(String fileName) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
                ArrayList<Order> orders = (ArrayList<Order>) in.readObject();

                for (Order order : orders) {
                    if (order.getOrderLocation().equalsIgnoreCase(this.location) && status.equals("Free")) {
                        this.assignedOrders.add(order);
                    } else if (status.equals("work")) {
                        System.out.println("Delivery cannot be assigned because the worker is currently busy.");
                    }
                }
                System.out.println("Orders assigned from file.");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error reading orders from file: " + e.getMessage());
            }
        }



        // View assigned orders
        public void viewAssignedOrders() {
            System.out.println("Assigned Orders for " + firstName + " " + lastName + ":");
            if (assignedOrders.isEmpty()) {
                System.out.println("No orders assigned.");
            } else {
                for (Order order : assignedOrders) {
                    System.out.println(order);
                    System.out.println("*==========================*");
                }
            }
        }

        // toString Method
        @Override
        public String toString() {
            return "DeliveryStaff{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", location='" + location + '\'' +
                    ", averageReview=" + averageReview +
                    '}';
        }

        // Method to write DeliveryStaff object to file
        public void writeToFile(String fileName) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
                out.writeObject(this);  // Write the current object to the file
                System.out.println("DeliveryStaff object saved to file.");
            } catch (IOException e) {
                System.err.println("Error writing object to file: " + e.getMessage());
            }
        }

        // Method to read DeliveryStaff object from file
        public static DeliveryStaff readFromFile(String fileName) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
                DeliveryStaff deliveryStaff = (DeliveryStaff) in.readObject();
                return deliveryStaff;
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error reading object from file: " + e.getMessage());
            }
            return null;
        }

    }

