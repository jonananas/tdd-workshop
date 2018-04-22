package se.jonananas.tdd.fakes;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceMockitoTest {

    @Mock
    OrderRepository orderRepo;
    private OrderService orderService;
    private Order order;

    @Captor
    private ArgumentCaptor<Integer> capturedOrder;

    @Before
    public void setup() {
        order = Order.medId(3);
        orderService = new OrderService(orderRepo);
    }

    @Test
    public void shouldNotHaveOrderWhenEmpty() throws Exception {
        assertThat(orderService.hasOrder(order)).isFalse();
    }

    @Test
    public void shouldHaveOrderWhenAdded() throws Exception {
        when(orderRepo.hasOrder(anyInt())).thenReturn(true);
        orderService.addOrder(order);

        assertThat(orderService.hasOrder(order)).isTrue();
    }

    @Test
    public void shouldQueryRepoForOrder() throws Exception {
        when(orderRepo.hasOrder(anyInt())).thenReturn(true);
        orderService.addOrder(order);
        orderService.hasOrder(order);

        verify(orderRepo).hasOrder(capturedOrder.capture());
        assertThat(capturedOrder.getValue()).isEqualTo(3);
    }

}
