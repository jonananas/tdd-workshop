package se.jonananas.tdd.fakes;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

	Order order;
	OrderService orderService;

	@BeforeEach
	public void setUp() throws Exception {
		order = Order.medId(3);
		orderService = new OrderService(new OrderRepositoryInMem());
	}

	@Test
	public void shouldNotHaveOrder() throws Exception {

		Assertions.assertThat(orderService.hasOrder(order)).isFalse();
	}

	@Test
	public void shouldHaveAddedOrder() throws Exception {
		orderService.addOrder(order);

		Assertions.assertThat(orderService.hasOrder(order)).isTrue();
	}
}
