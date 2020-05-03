
/**
 * This testclass shows several things
 * - How a mock framework like mockito can be used to mock slow depencies
 * - Build - Operate - Check pattern
 * - Simple Given - When - Then DSL
 */

package se.jonananas.tdd.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {
	
	private OrderService orderService;
	private PaymentServiceClient paymentService;

	@Before
	public void setup() {
		// BUILD
		orderService = new OrderService();
		paymentService = mock(PaymentServiceClient.class);
		orderService.betalService = paymentService;
	}
	
	@Test
	public void shouldCallService() {
		// OPERATE - call system under test
		orderService.addOrder("anOrder");

		// CHECK - verify outgoing message
		verify(paymentService).pay(anyString());
	}
	
	@Test
	public void shouldReturnNumberOfOrders() {
		// BUILD - fake outgoing query
		when(paymentService.getNumberOfOrdersFor(anyString())).thenReturn(10);
		
		// OPERATE - call system under test
		int numberOfOrders = orderService.getNumberOfOrders();
		
		// CHECK - verify query
		assertThat(numberOfOrders).isEqualTo(10);
	}

	@Test
	public void given_when_then_refactored_shouldReturnNumberOfOrders() throws Exception {
		givenBetalServiceHasThisManyOrders(10);
		
		int numberOfOrders = whenRetrievingNumberOfOrders();
		
		then(numberOfOrders).isEqualTo(10);
	}

	private AbstractIntegerAssert<?> then(int numberOfOrders) {
		return assertThat(numberOfOrders);
	}

	private int whenRetrievingNumberOfOrders() {
		return orderService.getNumberOfOrders();
	}

	private void givenBetalServiceHasThisManyOrders(final int ordersInBetalservice) {
		when(paymentService.getNumberOfOrdersFor(anyString())).thenReturn(ordersInBetalservice);
	}

}
