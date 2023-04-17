package se.jonananas.tdd.mockito;

public class OrderService {

	PaymentServiceClient paymentService;
 
	public OrderService(PaymentServiceClient paymentService) {
		this.paymentService = paymentService;
	}

	public void addOrder(String order) {
		paymentService.pay(order);
	}

	public int getNumberOfOrders() {
		return paymentService.getNumberOfOrdersFor("");
	}

}
