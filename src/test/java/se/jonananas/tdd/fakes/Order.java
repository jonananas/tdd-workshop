package se.jonananas.tdd.fakes;

public class Order {

    private int orderId;

    private Order(int orderId) {
        this.orderId = orderId;
    }

    public static Order medId(int orderId) {
        return new Order(orderId);
    }

    public int getId() {
        return orderId;
    }
}
