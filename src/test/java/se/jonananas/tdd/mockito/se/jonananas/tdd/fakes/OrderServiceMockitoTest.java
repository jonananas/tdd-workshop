package se.jonananas.tdd.mockito.se.jonananas.tdd.fakes;

import static org.mockito.Mockito.when;

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
    public void shouldNotHaveOrderWhenEmpty() throws Exception {
        Assertions.assertThat(orderService.hasOrder(order)).isFalse();
    }

    @Test
    public void shouldHaveOrderWhenAdded() throws Exception {
        when(orderRepo.hasOrder(Mockito.<Order>any())).thenReturn(true);
        orderService.addOrder(order);

        Assertions.assertThat(orderService.hasOrder(order)).isTrue();
    }
}
