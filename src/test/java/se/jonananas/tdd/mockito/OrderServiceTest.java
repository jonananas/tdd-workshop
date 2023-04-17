
/**
 * This testclass shows several things
 * - How a mock framework like mockito can be used to mock slow depencies
 * - Build - Operate - Check pattern
 * - Simple Given - When - Then DSL
 */

package se.jonananas.tdd.mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.AbstractIntegerAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
	
	private OrderService orderService;
	@Mock private PaymentServiceClient paymentService;

	// Query
	@Test
	public void shouldReturnNumberOfOrders() {
		// BUILD - fake outgoing query
		orderService = new OrderService(paymentService);
		when(paymentService.getNumberOfOrdersFor(anyString())).thenReturn(10);
		
		// OPERATE - call system under test
		int numberOfOrders = orderService.getNumberOfOrders();
		
		// CHECK - verify query
		assertThat(numberOfOrders).isEqualTo(10);
	}

	// Command
	@Test
	public void shouldCallService() {
		// BUILD
		orderService = new OrderService(paymentService);

		// OPERATE - call system under test
		orderService.addOrder("anOrder");

		// CHECK - verify outgoing message
		verify(paymentService).pay(anyString());
	}
	
	@Test
	public void given_when_then_refactored_shouldReturnNumberOfOrders() throws Exception {
		orderService = new OrderService(paymentService);
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
