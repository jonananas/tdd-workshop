package se.jonananas.tdd.mockito.se.jonananas.tdd.fakes;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceMockitoTest {

    @Mock
    OrderRepository orderRepo;
    private OrderService orderService;
    private Order order;

    @Before
    public void setup() {
        order = new Order();
        orderService = new OrderService(orderRepo);
    }

    @Test
    public void shouldNotHaveOrder() throws Exception {
        Assertions.assertThat(orderService.hasOrder(order)).isFalse();
    }

    @Test
    public void shouldHaveAddedOrder() throws Exception {
        Mockito.when(orderRepo.hasOrder(Mockito.<Order>any())).thenReturn(true);
        orderService.addOrder(order);

        Assertions.assertThat(orderService.hasOrder(order)).isTrue();
    }
}
