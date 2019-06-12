import java.util.ArrayList;
import java.util.List;

//Helper Class
public class OrderSummary {

    private List<Order> orders = new ArrayList<>();
    private double quantity;
    private double price;

    public OrderSummary(List<Order> orders){

        this.orders = orders;
    }

    //Get the prices
     public double getPrice(){

        return orders.stream()
                .findFirst().get().getPrice();
      }

    //Get the quantities and find the total
     public double getQuantity(){

         return orders.stream()
                 .mapToDouble((Order o)
                         -> o.getOrderQuantity()).sum();
     }



}
