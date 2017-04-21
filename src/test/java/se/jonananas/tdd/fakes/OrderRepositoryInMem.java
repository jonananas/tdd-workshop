package se.jonananas.tdd.fakes;

import com.google.common.collect.Sets;

import java.util.Set;

public class OrderRepositoryInMem implements OrderRepository {
    Set<Order> orders = Sets.newHashSet();

    @Override
    public void store(Order order) {
        orders.add(order);
    }

    @Override
    public boolean hasOrder(Order order) {
        return orders.contains(order);
    }
}
