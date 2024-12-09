import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner;
        customer customerData = new customer();
        while (true) {

            scanner = new Scanner(System.in);
            System.out.println("1. Are you an Admin");
            System.out.println("2. Are you a Delivery");
            System.out.println("3. Are you a customer");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
//            scanner.nextLine();
            if (choice >= 1 && choice <= 3) {
                while (true) {
                    if (customerData.login()) {
                        break;
                    } else {
                        System.out.println("Login failed! ");
                        System.out.println("1- Check your email or password ");
                        System.out.println("2- Are you dont AlReady have an account?Register ");
                        System.out.print("Choose an option: ");
                        int Login_choice = scanner.nextInt();
                        if (Login_choice == 1) {
                            continue;

                        } else {
                            customerData.Register();
                            break;
                        }
                    }
                }
                if (choice == 1) {
                    //admin
                } else if (choice == 2) {
                    //delivery
                } else if (choice == 3) {//customer
                    customerData.governorate();
                    //restaurant
                    int choice_Restaurant = Restaurant.afterLog_in(1);
                    while (true) {


                        System.out.println("========================================");
                        System.out.println("1 - create another order");
                        System.out.println("2 - update the order");  //error
                        System.out.println("3 - cancel this order and make another");
                        System.out.println("4 - Log out");
                        System.out.print("choose from (1 - 4) :");
                        int Order_choice = scanner.nextInt();
                        Order order = new Order(1, customerData.address);
                        if (Order_choice == 1) {

                            order.Createorder(choice_Restaurant);
                        } else if (Order_choice==2) {
                            order.update(choice_Restaurant);

                        }
                        else if (Order_choice == 3) {
                            order.changeState("canceled");
                        }
                        else {
                            break;
                        }
                    }





                    System.out.println("====== Thank you ======");

                }

            } else {
                System.out.println("Invalid choice ,please choose from (1-3)");
                continue;
            }
            break;
        }

    }

}


//                          while (true) {
//                              System.out.println("========================================");
//                              System.out.println("1 - create another order");
//                              System.out.println("2 - update the order");  //error
//                              System.out.println("3 - view order");
//                              System.out.println("4 - cancel this order and make another");
//                              System.out.print("choose from (1 - 4) :");
//                              int Order_choice = scanner.nextInt();
//                              if (Order_choice == 1) {
//                                  id++;
//                                  order[id].create_order(selectedRestaurant);
//                              }
//                              //for update
////                          else if(choice==2){
////                              id--;
////                          }
//                              else if (choice == 3) {
//                                  order[id].view_Order();
//                              }
//                              else if (choice == 4) {
//                                  System.out.println("order" + id + " is deleted ");
//                                  id--;//deleted this order
//                                  order[id].create_order(selectedRestaurant); //create another with the same order_id
//
//                              }
//                              System.out.println("Are you want to continue ?(y/n)");
//                              char continue_choice=scanner.next().charAt(0);
//                              if(continue_choice=='y'||continue_choice=='Y')
//                                  continue;
//                              else
//                                  break;
//                          }

//                      }
//                      else {
//                          System.out.println("Invalid restaurant choice.");
//                          continue;
//                      }



























