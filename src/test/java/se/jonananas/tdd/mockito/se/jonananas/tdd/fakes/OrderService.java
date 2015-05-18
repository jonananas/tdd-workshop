package se.jonananas.tdd.mockito.se.jonananas.tdd.fakes;

public class OrderService {

    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        this.orderRepository.store(order);
    }

    public boolean hasOrder(Order order) {
        return this.orderRepository.hasOrder(order);
    }
}
