package se.agileexchange.tdd.mockito;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

public class OrderServiceTest {
	
	private OrderService orderService;
	private BetalServiceClient betalService;

	@Before
	public void setup() {
		orderService = new OrderService();
		betalService = mock(BetalServiceClient.class);
		orderService.betalService = betalService; 
	}
	
	@Test
	public void shouldCallService() throws Exception {
		
		orderService.addOrder("anOrder");
		
		verify(betalService).pay(anyString());
	}
	
	@Test
	public void shouldReturnNumberOfOrders() throws Exception {
		// BUILD
		when(betalService.getNumberOfOrdersFor(anyString())).thenReturn(10);
		
		// OPERATE
		int numberOfOrders = orderService.getNumberOfOrders();
		
		// CHECK
		assertThat(numberOfOrders).isEqualTo(10);
	}

}
