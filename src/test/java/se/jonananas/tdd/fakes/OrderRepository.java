package se.jonananas.tdd.fakes;

public interface OrderRepository {

    void store(Order order);

    boolean hasOrder(int orderId);
}
