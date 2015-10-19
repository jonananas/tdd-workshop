package se.jonananas.tdd.mockito.se.jonananas.tdd.fakes;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {

	Order order;
	OrderService orderService;

	@Before
	public void setUp() throws Exception {
		order = new Order();
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
