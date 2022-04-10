package se.jonananas.tdd.fakes;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceMockitoTest {

    public static final int ORDER_ID = 3;
    @Mock
    OrderRepository orderRepo;
    private OrderService orderService;
    private Order order;

    @Captor
    private ArgumentCaptor<Integer> capturedOrder;

    @BeforeEach
    public void setup() {
        order = Order.medId(ORDER_ID);
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
        orderService.hasOrder(order);

        verify(orderRepo).hasOrder(capturedOrder.capture());
        assertThat(capturedOrder.getValue()).isEqualTo(ORDER_ID);
    }

}
