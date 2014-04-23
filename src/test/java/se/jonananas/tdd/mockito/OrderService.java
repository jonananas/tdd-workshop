package se.jonananas.tdd.mockito;

public class OrderService {

	BetalServiceClient betalService;
 
	public void addOrder(String order) {
		betalService.pay(order);
	}

	public int getNumberOfOrders() {
		return betalService.getNumberOfOrdersFor("");
	}

}
