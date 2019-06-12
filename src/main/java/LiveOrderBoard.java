import java.util.*;
import java.util.stream.Collectors;


public class LiveOrderBoard {

    private HashSet<Order> orders = new HashSet<>();


    //Register Order
    public void register(Order order){

        orders.add(order);
    }

    //Checks if order already exists
    public boolean isRegistered(Order order){

        return orders.contains(order);
    }

    //Cancels order if already registered, else no order exists message shown
    public void cancelOrder(Order order) {

        if (orders.remove(order) == false) {
            System.out.println("No order exists");
        } else {
            orders.remove(order);
            System.out.println("Order Successfully Removed");
        }

    }

    //Buy Order Summary & ordering highest prices first
    public List<OrderSummary> getBuyOrderSummary(List<Order> order){

        List<OrderSummary> summary = new ArrayList<>();
            summary.addAll(buySummary(order, Comparator.naturalOrder()));
            Collections.reverse(summary);

        return summary;
    }

    //Sell Order Summary & ordering lowest prices first
    public List<OrderSummary> getSellOrderSummary(List<Order> order){

        List<OrderSummary> summary = new ArrayList<>();
        summary.addAll(sellSummary(order, Comparator.naturalOrder()));

        return summary;
    }


    //buy summary merging
    public List<OrderSummary> buySummary(List<Order> order, Comparator<Double> highestPriceFirst){

            return order.stream()
                    .filter(listOfOrders -> listOfOrders instanceof Buy)
                    .collect(Collectors.groupingBy((Order o) -> o.getPrice()))
                    .values().stream().map(OrderSummary::new)
                    .collect(Collectors.toList());
    }


    //sell summary merging
    public List<OrderSummary> sellSummary(List<Order> order, Comparator<Double> lowestPriceFirst){

            return order.stream()
                .filter(listOfOrders -> listOfOrders instanceof Sell)
                .collect(Collectors.groupingBy((Order o) -> o.getPrice()))
                .values().stream().map(OrderSummary::new)
                .collect(Collectors.toList());

    }

}

