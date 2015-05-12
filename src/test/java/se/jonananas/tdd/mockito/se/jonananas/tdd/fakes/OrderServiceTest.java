package se.jonananas.tdd.mockito.se.jonananas.tdd.fakes;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class OrderServiceTest {

    @Test
    public void shouldNotHaveOrder() throws Exception {
        OrderService orderService = new OrderService(new OrderRepositoryInMem());
        Order order = new Order();

        Assertions.assertThat(orderService.hasOrder(order)).isFalse();
    }

    @Test
    public void shouldHaveAddedOrder() throws Exception {
        OrderService orderService = new OrderService(new OrderRepositoryInMem());
        Order order = new Order();
        orderService.addOrder(order);

        Assertions.assertThat(orderService.hasOrder(order)).isTrue();
    }
}
