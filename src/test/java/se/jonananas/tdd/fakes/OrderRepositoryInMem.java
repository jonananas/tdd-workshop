package se.jonananas.tdd.fakes;

import java.util.HashSet;
import java.util.Set;

public class OrderRepositoryInMem implements OrderRepository {
    Set<Order> orders = new HashSet<>();

    @Override
    public void store(Order order) {
        orders.add(order);
    }

    @Override
    public boolean hasOrder(int orderId) {
        return orders.stream().anyMatch(i -> i.getId() == orderId);
    }
}
