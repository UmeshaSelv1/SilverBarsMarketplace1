public class Order {

    private int userID;
    private double orderQuantity;
    private int price;

    //initialising Constructor
    public Order(int userID, double orderQuantity, int price){

        this.userID = userID;
        this.orderQuantity = orderQuantity;
        this.price = price;
    }

    public int getUserID(){
        return userID;
    }

    public double getOrderQuantity(){
        return orderQuantity;
    }

    public double getPrice(){
        return price;
    }

}
