import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class LiveOrderBoardTest {

    private LiveOrderBoard board = new LiveOrderBoard();

    @Test
    public void registerOrderTest(){

        Order order = new Sell(1234, 3.5, 306);
        board.register(order);
        assertTrue(board.isRegistered(order));
    }

    @Test
    public void cancelOrderTest(){
        Order order = new Sell(1234, 3.5, 306);
        Order order1 = new Sell(134, 2.6, 256);
        Order order2 = new Sell(124, 1.5, 105);
        board.register(order);
        //  board.register(order1);
        board.register(order2);
        board.cancelOrder(order);
        board.cancelOrder(order1);
        board.cancelOrder(order2);
        assertFalse(board.isRegistered(order));

    }

    //Show summary test
    //Show merge test
    //Show highest price first for buy
    //Show lowest price first for sell

    @Test
    public void summaryTest(){
        List<Order> orders = Arrays.asList(
                new Sell(789, 4.6, 506));

        OrderSummary orderSummary = board.getSellOrderSummary(orders).get(0);

        assertEquals(506, orderSummary.getPrice(), 1);
    }

    @Test
    public void mergeTest(){
        List<Order> orders = Arrays.asList(
                new Sell(789, 4.6, 506),
                new Sell(123, 3.4, 506),
                new Sell(123, 2.0, 506));
        OrderSummary orderSummary = board.getSellOrderSummary(orders).get(0);

        assertEquals(10.0, orderSummary.getQuantity(), 1);
    }

    @Test
    //Sell Type ordering
    public void lowestPriceFirstTest() {

        List<Order> orders = Arrays.asList(
                new Sell(12, 1.0, 1000),
                new Sell(21, 2.5, 300),
                new Sell(34, 2.5, 400),
                new Sell(65, 2.5, 280));

        OrderSummary position1 = board.getSellOrderSummary(orders).get(0);
        OrderSummary position2 = board.getSellOrderSummary(orders).get(1);
        OrderSummary position3 = board.getSellOrderSummary(orders).get(2);
        OrderSummary position4 = board.getSellOrderSummary(orders).get(3);

        //Answer should be: (280, 300, 400, 1000)

        assertEquals(280, position1.getPrice(), 1);
        assertEquals(300, position2.getPrice(), 1);
        assertEquals(400, position3.getPrice(), 1);
        assertEquals(1000, position4.getPrice(), 1);

    }

    @Test
    //Buy Type ordering
    public void highestPriceFirstTest() {

        List<Order> orders = Arrays.asList(
                new Buy(98, 1.0, 2000),
                new Buy(76, 5.0, 300),
                new Buy(76, 5.0, 1500),
                new Buy(76, 5.0, 700));

        OrderSummary position1 = board.getBuyOrderSummary(orders).get(0);
        OrderSummary position2 = board.getBuyOrderSummary(orders).get(1);
        OrderSummary position3 = board.getBuyOrderSummary(orders).get(2);
        OrderSummary position4 = board.getBuyOrderSummary(orders).get(3);

        //Answer should be: (2000, 1500, 700, 300)

        assertEquals(1500, position1.getPrice(), 1);
        assertEquals(700, position2.getPrice(), 1);
        assertEquals(700, position3.getPrice(), 1);
        assertEquals(300, position4.getPrice(), 1);

    }

}
